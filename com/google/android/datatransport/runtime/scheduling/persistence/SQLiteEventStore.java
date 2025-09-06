package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal.Builder;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped.Reason;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class SQLiteEventStore implements ClientHealthMetricsStore, EventStore, SynchronizationGuard, AutoCloseable {
    interface Function {
        Object apply(Object arg1);
    }

    static class Metadata {
        final String key;
        final String value;

        private Metadata(String s, String s1) {
            this.key = s;
            this.value = s1;
        }

        Metadata(String s, String s1, com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.1 sQLiteEventStore$10) {
            this(s, s1);
        }
    }

    interface Producer {
        Object produce();
    }

    private static final int LOCK_RETRY_BACK_OFF_MILLIS = 50;
    private static final String LOG_TAG = "SQLiteEventStore";
    static final int MAX_RETRIES = 16;
    private static final Encoding PROTOBUF_ENCODING;
    private final EventStoreConfig config;
    private final Clock monotonicClock;
    private final Provider packageName;
    private final SchemaManager schemaManager;
    private final Clock wallClock;

    static {
        SQLiteEventStore.PROTOBUF_ENCODING = Encoding.of("proto");
    }

    @Inject
    SQLiteEventStore(Clock clock0, Clock clock1, EventStoreConfig eventStoreConfig0, SchemaManager schemaManager0, @Named("PACKAGE_NAME") Provider provider0) {
        this.schemaManager = schemaManager0;
        this.wallClock = clock0;
        this.monotonicClock = clock1;
        this.config = eventStoreConfig0;
        this.packageName = provider0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public int cleanUp() {
        return (int)(((Integer)this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            String[] arr_s = {String.valueOf(this.wallClock.getTime() - this.config.getEventCleanUpAge())};
            SQLiteEventStore.tryWithCursor(sQLiteDatabase0.rawQuery("SELECT COUNT(*), transport_name FROM events WHERE timestamp_ms < ? GROUP BY transport_name", arr_s), (Cursor cursor0) -> {
                while(cursor0.moveToNext()) {
                    int v = cursor0.getInt(0);
                    String s = cursor0.getString(1);
                    this.recordLogEventDropped(((long)v), Reason.MESSAGE_TOO_OLD, s);
                }
                return null;
            });
            return sQLiteDatabase0.delete("events", "timestamp_ms < ?", arr_s);
        })));
    }

    public void clearDb() {
        this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            sQLiteDatabase0.delete("events", null, new String[0]);
            sQLiteDatabase0.delete("transport_contexts", null, new String[0]);
            return null;
        });
    }

    @Override
    public void close() {
        this.schemaManager.close();
    }

    private Reason convertToReason(int v) {
        if(v == Reason.REASON_UNKNOWN.getNumber()) {
            return Reason.REASON_UNKNOWN;
        }
        if(v == Reason.MESSAGE_TOO_OLD.getNumber()) {
            return Reason.MESSAGE_TOO_OLD;
        }
        if(v == Reason.CACHE_FULL.getNumber()) {
            return Reason.CACHE_FULL;
        }
        if(v == Reason.PAYLOAD_TOO_BIG.getNumber()) {
            return Reason.PAYLOAD_TOO_BIG;
        }
        if(v == Reason.MAX_RETRIES_REACHED.getNumber()) {
            return Reason.MAX_RETRIES_REACHED;
        }
        if(v == Reason.INVALID_PAYLOD.getNumber()) {
            return Reason.INVALID_PAYLOD;
        }
        if(v == Reason.SERVER_ERROR.getNumber()) {
            return Reason.SERVER_ERROR;
        }
        Logging.d("SQLiteEventStore", "%n is not valid. No matched LogEventDropped-Reason found. Treated it as REASON_UNKNOWN", v);
        return Reason.REASON_UNKNOWN;
    }

    private void ensureBeginTransaction(SQLiteDatabase sQLiteDatabase0) {
        this.retryIfDbLocked(() -> {
            sQLiteDatabase0.beginTransaction();
            return null;
        }, (Throwable throwable0) -> throw new SynchronizationException("Timed out while trying to acquire the lock.", throwable0));
    }

    private long ensureTransportContext(SQLiteDatabase sQLiteDatabase0, TransportContext transportContext0) {
        Long long0 = this.getTransportContextId(sQLiteDatabase0, transportContext0);
        if(long0 != null) {
            return (long)long0;
        }
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("backend_name", transportContext0.getBackendName());
        contentValues0.put("priority", PriorityMapping.toInt(transportContext0.getPriority()));
        contentValues0.put("next_request_ms", 0);
        if(transportContext0.getExtras() != null) {
            contentValues0.put("extras", Base64.encodeToString(transportContext0.getExtras(), 0));
        }
        return sQLiteDatabase0.insert("transport_contexts", null, contentValues0);
    }

    long getByteSize() {
        return this.getPageCount() * this.getPageSize();
    }

    SQLiteDatabase getDb() {
        Objects.requireNonNull(this.schemaManager);
        return (SQLiteDatabase)this.retryIfDbLocked(new SQLiteEventStore..ExternalSyntheticLambda25(this.schemaManager), (Throwable throwable0) -> throw new SynchronizationException("Timed out while trying to open db.", throwable0));
    }

    private GlobalMetrics getGlobalMetrics() {
        return GlobalMetrics.newBuilder().setStorageMetrics(StorageMetrics.newBuilder().setCurrentCacheSizeBytes(this.getByteSize()).setMaxCacheSizeBytes(EventStoreConfig.DEFAULT.getMaxStorageSizeInBytes()).build()).build();
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public long getNextCallTime(TransportContext transportContext0) {
        return (long)(((Long)SQLiteEventStore.tryWithCursor(this.getDb().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{transportContext0.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext0.getPriority()))}), (Cursor cursor0) -> // 去混淆评级： 低(20)
        (cursor0.moveToNext() ? cursor0.getLong(0) : 0L))));
    }

    private long getPageCount() {
        return this.getDb().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    private long getPageSize() {
        return this.getDb().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    private TimeWindow getTimeWindow() {
        return (TimeWindow)this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> ((TimeWindow)SQLiteEventStore.tryWithCursor(sQLiteDatabase0.rawQuery("SELECT last_metrics_upload_ms FROM global_log_event_state LIMIT 1", new String[0]), (Cursor cursor0) -> {
            cursor0.moveToNext();
            long v1 = cursor0.getLong(0);
            return TimeWindow.newBuilder().setStartMs(v1).setEndMs(v).build();
        })));
    }

    private Long getTransportContextId(SQLiteDatabase sQLiteDatabase0, TransportContext transportContext0) {
        StringBuilder stringBuilder0 = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList0 = new ArrayList(Arrays.asList(new String[]{transportContext0.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext0.getPriority()))}));
        if(transportContext0.getExtras() == null) {
            stringBuilder0.append(" and extras is null");
        }
        else {
            stringBuilder0.append(" and extras = ?");
            arrayList0.add(Base64.encodeToString(transportContext0.getExtras(), 0));
        }
        Object[] arr_object = arrayList0.toArray(new String[0]);
        return (Long)SQLiteEventStore.tryWithCursor(sQLiteDatabase0.query("transport_contexts", new String[]{"_id"}, stringBuilder0.toString(), ((String[])arr_object), null, null, null), (Cursor cursor0) -> (cursor0.moveToNext() ? cursor0.getLong(0) : null));
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public boolean hasPendingEventsFor(TransportContext transportContext0) {
        return ((Boolean)this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            Long long0 = this.getTransportContextId(sQLiteDatabase0, transportContext0);
            return long0 == null ? false : ((Boolean)SQLiteEventStore.tryWithCursor(this.getDb().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{long0.toString()}), new SQLiteEventStore..ExternalSyntheticLambda22()));
        })).booleanValue();
    }

    Object inTransaction(Function sQLiteEventStore$Function0) {
        SQLiteDatabase sQLiteDatabase0 = this.getDb();
        sQLiteDatabase0.beginTransaction();
        try {
            Object object0 = sQLiteEventStore$Function0.apply(sQLiteDatabase0);
            sQLiteDatabase0.setTransactionSuccessful();
            return object0;
        }
        finally {
            sQLiteDatabase0.endTransaction();
        }
    }

    private boolean isStorageAtLimit() {
        return this.getPageCount() * this.getPageSize() >= this.config.getMaxStorageSizeInBytes();
    }

    private List join(List list0, Map map0) {
        ListIterator listIterator0 = list0.listIterator();
        while(listIterator0.hasNext()) {
            PersistedEvent persistedEvent0 = (PersistedEvent)listIterator0.next();
            if(map0.containsKey(persistedEvent0.getId())) {
                Builder eventInternal$Builder0 = persistedEvent0.getEvent().toBuilder();
                for(Object object0: ((Set)map0.get(persistedEvent0.getId()))) {
                    eventInternal$Builder0.addMetadata(((Metadata)object0).key, ((Metadata)object0).value);
                }
                listIterator0.set(PersistedEvent.create(persistedEvent0.getId(), persistedEvent0.getTransportContext(), eventInternal$Builder0.build()));
            }
        }
        return list0;
    }

    // 检测为 Lambda 实现
    Object lambda$cleanUp$11$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(Cursor cursor0) [...]

    // 检测为 Lambda 实现
    Integer lambda$cleanUp$12$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(long v, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static Object lambda$clearDb$13(SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static Object lambda$ensureBeginTransaction$24(SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static Object lambda$ensureBeginTransaction$25(Throwable throwable0) [...]

    // 检测为 Lambda 实现
    static SQLiteDatabase lambda$getDb$0(Throwable throwable0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Long lambda$getNextCallTime$5(Cursor cursor0) [...]

    // 检测为 Lambda 实现
    static TimeWindow lambda$getTimeWindow$21(long v, Cursor cursor0) [...]

    // 检测为 Lambda 实现
    static TimeWindow lambda$getTimeWindow$22(long v, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static Long lambda$getTransportContextId$2(Cursor cursor0) [...]

    // 检测为 Lambda 实现
    Boolean lambda$hasPendingEventsFor$6$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(TransportContext transportContext0, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static List lambda$loadActiveContexts$10(SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static List lambda$loadActiveContexts$9(Cursor cursor0) [...]

    // 检测为 Lambda 实现
    List lambda$loadBatch$8$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(TransportContext transportContext0, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    ClientMetrics lambda$loadClientMetrics$19$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(Map map0, com.google.android.datatransport.runtime.firebase.transport.ClientMetrics.Builder clientMetrics$Builder0, Cursor cursor0) [...]

    // 检测为 Lambda 实现
    ClientMetrics lambda$loadClientMetrics$20$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(String s, Map map0, com.google.android.datatransport.runtime.firebase.transport.ClientMetrics.Builder clientMetrics$Builder0, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    Object lambda$loadEvents$14$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(List list0, TransportContext transportContext0, Cursor cursor0) [...]

    // 检测为 Lambda 实现
    static Object lambda$loadMetadata$16(Map map0, Cursor cursor0) [...]

    // 检测为 Lambda 实现
    Long lambda$persist$1$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(EventInternal eventInternal0, TransportContext transportContext0, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static byte[] lambda$readPayload$15(Cursor cursor0) [...]

    // 检测为 Lambda 实现
    Object lambda$recordFailure$3$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(Cursor cursor0) [...]

    // 检测为 Lambda 实现
    Object lambda$recordFailure$4$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(String s, String s1, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static Boolean lambda$recordLogEventDropped$17(Cursor cursor0) [...]

    // 检测为 Lambda 实现
    static Object lambda$recordLogEventDropped$18(String s, Reason logEventDropped$Reason0, long v, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static Object lambda$recordNextCallTime$7(long v, TransportContext transportContext0, SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    Object lambda$resetClientMetrics$23$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(SQLiteDatabase sQLiteDatabase0) [...]

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable loadActiveContexts() {
        return (Iterable)this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> ((List)SQLiteEventStore.tryWithCursor(sQLiteDatabase0.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), (Cursor cursor0) -> {
            List list0 = new ArrayList();
            while(cursor0.moveToNext()) {
                list0.add(TransportContext.builder().setBackendName(cursor0.getString(1)).setPriority(PriorityMapping.valueOf(cursor0.getInt(2))).setExtras(SQLiteEventStore.maybeBase64Decode(cursor0.getString(3))).build());
            }
            return list0;
        })));
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable loadBatch(TransportContext transportContext0) {
        return (Iterable)this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            List list0 = this.loadEvents(sQLiteDatabase0, transportContext0, this.config.getLoadBatchSize());
            Priority[] arr_priority = Priority.values();
            for(int v = 0; v < arr_priority.length; ++v) {
                Priority priority0 = arr_priority[v];
                if(priority0 != transportContext0.getPriority()) {
                    int v1 = this.config.getLoadBatchSize() - list0.size();
                    if(v1 <= 0) {
                        break;
                    }
                    list0.addAll(this.loadEvents(sQLiteDatabase0, transportContext0.withPriority(priority0), v1));
                }
            }
            return this.join(list0, this.loadMetadata(sQLiteDatabase0, list0));
        });
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public ClientMetrics loadClientMetrics() {
        com.google.android.datatransport.runtime.firebase.transport.ClientMetrics.Builder clientMetrics$Builder0 = ClientMetrics.newBuilder();
        return (ClientMetrics)this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> ((ClientMetrics)SQLiteEventStore.tryWithCursor(sQLiteDatabase0.rawQuery("SELECT log_source, reason, events_dropped_count FROM log_event_dropped", new String[0]), (Cursor cursor0) -> {
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(0);
                Reason logEventDropped$Reason0 = this.convertToReason(cursor0.getInt(1));
                long v = cursor0.getLong(2);
                if(!map0.containsKey(s)) {
                    map0.put(s, new ArrayList());
                }
                ((List)map0.get(s)).add(LogEventDropped.newBuilder().setReason(logEventDropped$Reason0).setEventsDroppedCount(v).build());
            }
            this.populateLogSourcesMetrics(clientMetrics$Builder0, map0);
            clientMetrics$Builder0.setWindow(this.getTimeWindow());
            clientMetrics$Builder0.setGlobalMetrics(this.getGlobalMetrics());
            clientMetrics$Builder0.setAppNamespace(((String)this.packageName.get()));
            return clientMetrics$Builder0.build();
        })));
    }

    private List loadEvents(SQLiteDatabase sQLiteDatabase0, TransportContext transportContext0, int v) {
        List list0 = new ArrayList();
        Long long0 = this.getTransportContextId(sQLiteDatabase0, transportContext0);
        if(long0 == null) {
            return list0;
        }
        SQLiteEventStore.tryWithCursor(sQLiteDatabase0.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline"}, "context_id = ?", new String[]{long0.toString()}, null, null, null, String.valueOf(v)), (Cursor cursor0) -> {
            while(cursor0.moveToNext()) {
                boolean z = false;
                long v = cursor0.getLong(0);
                if(cursor0.getInt(7) != 0) {
                    z = true;
                }
                Builder eventInternal$Builder0 = EventInternal.builder().setTransportName(cursor0.getString(1)).setEventMillis(cursor0.getLong(2)).setUptimeMillis(cursor0.getLong(3));
                if(z) {
                    eventInternal$Builder0.setEncodedPayload(new EncodedPayload(SQLiteEventStore.toEncoding(cursor0.getString(4)), cursor0.getBlob(5)));
                }
                else {
                    eventInternal$Builder0.setEncodedPayload(new EncodedPayload(SQLiteEventStore.toEncoding(cursor0.getString(4)), this.readPayload(v)));
                }
                if(!cursor0.isNull(6)) {
                    eventInternal$Builder0.setCode(cursor0.getInt(6));
                }
                list0.add(PersistedEvent.create(v, transportContext0, eventInternal$Builder0.build()));
            }
            return null;
        });
        return list0;
    }

    private Map loadMetadata(SQLiteDatabase sQLiteDatabase0, List list0) {
        Map map0 = new HashMap();
        StringBuilder stringBuilder0 = new StringBuilder("event_id IN (");
        for(int v = 0; v < list0.size(); ++v) {
            stringBuilder0.append(((PersistedEvent)list0.get(v)).getId());
            if(v < list0.size() - 1) {
                stringBuilder0.append(',');
            }
        }
        stringBuilder0.append(')');
        SQLiteEventStore.tryWithCursor(sQLiteDatabase0.query("event_metadata", new String[]{"event_id", "name", "value"}, stringBuilder0.toString(), null, null, null, null), (Cursor cursor0) -> {
            while(cursor0.moveToNext()) {
                long v = cursor0.getLong(0);
                Set set0 = (Set)map0.get(v);
                if(set0 == null) {
                    set0 = new HashSet();
                    map0.put(v, set0);
                }
                set0.add(new Metadata(cursor0.getString(1), cursor0.getString(2), null));
            }
            return null;
        });
        return map0;
    }

    private static byte[] maybeBase64Decode(String s) {
        return s == null ? null : Base64.decode(s, 0);
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public PersistedEvent persist(TransportContext transportContext0, EventInternal eventInternal0) {
        Logging.d("SQLiteEventStore", "Storing event with priority=%s, name=%s for destination %s", new Object[]{transportContext0.getPriority(), eventInternal0.getTransportName(), transportContext0.getBackendName()});
        long v = (long)(((Long)this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            if(this.isStorageAtLimit()) {
                String s = eventInternal0.getTransportName();
                this.recordLogEventDropped(1L, Reason.CACHE_FULL, s);
                return -1L;
            }
            long v = this.ensureTransportContext(sQLiteDatabase0, transportContext0);
            int v1 = this.config.getMaxBlobByteSizePerRow();
            byte[] arr_b = eventInternal0.getEncodedPayload().getBytes();
            boolean z = arr_b.length <= v1;
            ContentValues contentValues0 = new ContentValues();
            contentValues0.put("context_id", v);
            contentValues0.put("transport_name", eventInternal0.getTransportName());
            contentValues0.put("timestamp_ms", eventInternal0.getEventMillis());
            contentValues0.put("uptime_ms", eventInternal0.getUptimeMillis());
            contentValues0.put("payload_encoding", eventInternal0.getEncodedPayload().getEncoding().getName());
            contentValues0.put("code", eventInternal0.getCode());
            contentValues0.put("num_attempts", 0);
            contentValues0.put("inline", Boolean.valueOf(z));
            contentValues0.put("payload", (z ? arr_b : new byte[0]));
            long v3 = sQLiteDatabase0.insert("events", null, contentValues0);
            if(!z) {
                int v4 = (int)Math.ceil(((double)arr_b.length) / ((double)v1));
                for(int v2 = 1; v2 <= v4; ++v2) {
                    byte[] arr_b1 = Arrays.copyOfRange(arr_b, (v2 - 1) * v1, Math.min(v2 * v1, arr_b.length));
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("event_id", v3);
                    contentValues1.put("sequence_num", v2);
                    contentValues1.put("bytes", arr_b1);
                    sQLiteDatabase0.insert("event_payloads", null, contentValues1);
                }
            }
            for(Object object0: eventInternal0.getMetadata().entrySet()) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", v3);
                contentValues2.put("name", ((String)((Map.Entry)object0).getKey()));
                contentValues2.put("value", ((String)((Map.Entry)object0).getValue()));
                sQLiteDatabase0.insert("event_metadata", null, contentValues2);
            }
            return v3;
        })));
        return v >= 1L ? PersistedEvent.create(v, transportContext0, eventInternal0) : null;
    }

    private void populateLogSourcesMetrics(com.google.android.datatransport.runtime.firebase.transport.ClientMetrics.Builder clientMetrics$Builder0, Map map0) {
        for(Object object0: map0.entrySet()) {
            clientMetrics$Builder0.addLogSourceMetrics(LogSourceMetrics.newBuilder().setLogSource(((String)((Map.Entry)object0).getKey())).setLogEventDroppedList(((List)((Map.Entry)object0).getValue())).build());
        }
    }

    private byte[] readPayload(long v) {
        return (byte[])SQLiteEventStore.tryWithCursor(this.getDb().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(v)}, null, null, "sequence_num"), (Cursor cursor0) -> {
            ArrayList arrayList0 = new ArrayList();
            int v;
            for(v = 0; cursor0.moveToNext(); v += arr_b.length) {
                byte[] arr_b = cursor0.getBlob(0);
                arrayList0.add(arr_b);
            }
            byte[] arr_b1 = new byte[v];
            int v2 = 0;
            for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
                byte[] arr_b2 = (byte[])arrayList0.get(v1);
                System.arraycopy(arr_b2, 0, arr_b1, v2, arr_b2.length);
                v2 += arr_b2.length;
            }
            return arr_b1;
        });
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordFailure(Iterable iterable0) {
        if(!iterable0.iterator().hasNext()) {
            return;
        }
        this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            sQLiteDatabase0.compileStatement("UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + SQLiteEventStore.toIdList(iterable0)).execute();
            SQLiteEventStore.tryWithCursor(sQLiteDatabase0.rawQuery("SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name", null), (Cursor cursor0) -> {
                while(cursor0.moveToNext()) {
                    int v = cursor0.getInt(0);
                    String s = cursor0.getString(1);
                    this.recordLogEventDropped(((long)v), Reason.MAX_RETRIES_REACHED, s);
                }
                return null;
            });
            sQLiteDatabase0.compileStatement("DELETE FROM events WHERE num_attempts >= 16").execute();
            return null;
        });
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public void recordLogEventDropped(long v, Reason logEventDropped$Reason0, String s) {
        this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            if(!((Boolean)SQLiteEventStore.tryWithCursor(sQLiteDatabase0.rawQuery("SELECT 1 FROM log_event_dropped WHERE log_source = ? AND reason = ?", new String[]{s, Integer.toString(logEventDropped$Reason0.getNumber())}), (Cursor cursor0) -> (cursor0.getCount() <= 0 ? false : true))).booleanValue()) {
                ContentValues contentValues0 = new ContentValues();
                contentValues0.put("log_source", s);
                contentValues0.put("reason", logEventDropped$Reason0.getNumber());
                contentValues0.put("events_dropped_count", v);
                sQLiteDatabase0.insert("log_event_dropped", null, contentValues0);
                return null;
            }
            sQLiteDatabase0.execSQL("UPDATE log_event_dropped SET events_dropped_count = events_dropped_count + " + v + " WHERE log_source = ? AND reason = ?", new String[]{s, Integer.toString(logEventDropped$Reason0.getNumber())});
            return null;
        });
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordNextCallTime(TransportContext transportContext0, long v) {
        this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            ContentValues contentValues0 = new ContentValues();
            contentValues0.put("next_request_ms", v);
            if(sQLiteDatabase0.update("transport_contexts", contentValues0, "backend_name = ? and priority = ?", new String[]{transportContext0.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext0.getPriority()))}) < 1) {
                contentValues0.put("backend_name", transportContext0.getBackendName());
                contentValues0.put("priority", PriorityMapping.toInt(transportContext0.getPriority()));
                sQLiteDatabase0.insert("transport_contexts", null, contentValues0);
            }
            return null;
        });
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordSuccess(Iterable iterable0) {
        if(!iterable0.iterator().hasNext()) {
            return;
        }
        this.getDb().compileStatement("DELETE FROM events WHERE _id in " + SQLiteEventStore.toIdList(iterable0)).execute();
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public void resetClientMetrics() {
        this.inTransaction((SQLiteDatabase sQLiteDatabase0) -> {
            sQLiteDatabase0.compileStatement("DELETE FROM log_event_dropped").execute();
            sQLiteDatabase0.compileStatement("UPDATE global_log_event_state SET last_metrics_upload_ms=" + this.wallClock.getTime()).execute();
            return null;
        });
    }

    private Object retryIfDbLocked(Producer sQLiteEventStore$Producer0, Function sQLiteEventStore$Function0) {
        long v = this.monotonicClock.getTime();
        while(true) {
            try {
                return sQLiteEventStore$Producer0.produce();
            }
            catch(SQLiteDatabaseLockedException sQLiteDatabaseLockedException0) {
            }
            if(this.monotonicClock.getTime() >= ((long)this.config.getCriticalSectionEnterTimeoutMs()) + v) {
                break;
            }
            SystemClock.sleep(50L);
        }
        return sQLiteEventStore$Function0.apply(sQLiteDatabaseLockedException0);
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard
    public Object runCriticalSection(CriticalSection synchronizationGuard$CriticalSection0) {
        SQLiteDatabase sQLiteDatabase0 = this.getDb();
        this.ensureBeginTransaction(sQLiteDatabase0);
        try {
            Object object0 = synchronizationGuard$CriticalSection0.execute();
            sQLiteDatabase0.setTransactionSuccessful();
            return object0;
        }
        finally {
            sQLiteDatabase0.endTransaction();
        }
    }

    private static Encoding toEncoding(String s) {
        return s == null ? SQLiteEventStore.PROTOBUF_ENCODING : Encoding.of(s);
    }

    private static String toIdList(Iterable iterable0) {
        StringBuilder stringBuilder0 = new StringBuilder("(");
        Iterator iterator0 = iterable0.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            stringBuilder0.append(((PersistedEvent)object0).getId());
            if(iterator0.hasNext()) {
                stringBuilder0.append(',');
            }
        }
        stringBuilder0.append(')');
        return stringBuilder0.toString();
    }

    static Object tryWithCursor(Cursor cursor0, Function sQLiteEventStore$Function0) {
        try(cursor0) {
            return sQLiteEventStore$Function0.apply(cursor0);
        }
    }

    class com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.1 {
    }

}

