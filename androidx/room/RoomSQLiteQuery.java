package androidx.room;

import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u000E\b\u0007\u0018\u0000 62\u00020\u00012\u00020\u0002:\u000256B\u000F\b\u0002\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0018\u0010\"\u001A\u00020#2\u0006\u0010$\u001A\u00020\u00042\u0006\u0010%\u001A\u00020\u0010H\u0016J\u0018\u0010&\u001A\u00020#2\u0006\u0010$\u001A\u00020\u00042\u0006\u0010%\u001A\u00020\'H\u0016J\u0018\u0010(\u001A\u00020#2\u0006\u0010$\u001A\u00020\u00042\u0006\u0010%\u001A\u00020)H\u0016J\u0010\u0010*\u001A\u00020#2\u0006\u0010$\u001A\u00020\u0004H\u0016J\u0018\u0010+\u001A\u00020#2\u0006\u0010$\u001A\u00020\u00042\u0006\u0010%\u001A\u00020\u001BH\u0016J\u0010\u0010,\u001A\u00020#2\u0006\u0010-\u001A\u00020\u0002H\u0016J\b\u0010.\u001A\u00020#H\u0016J\b\u0010/\u001A\u00020#H\u0016J\u000E\u00100\u001A\u00020#2\u0006\u00101\u001A\u00020\u0000J\u0016\u00102\u001A\u00020#2\u0006\u0010\u001A\u001A\u00020\u001B2\u0006\u00103\u001A\u00020\u0004J\u0006\u00104\u001A\u00020#R\u001E\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0006\u001A\u00020\u0004@RX\u0096\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0014\u0010\n\u001A\u00020\u000BX\u0082\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rR \u0010\u000E\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000F8\u0006X\u0087\u0004\u00A2\u0006\n\n\u0002\u0010\u0012\u0012\u0004\b\u0011\u0010\rR\u0016\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\tR\u0016\u0010\u0014\u001A\u00020\u00158\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\rR\u0016\u0010\u0017\u001A\u00020\u00188\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\rR\u0010\u0010\u001A\u001A\u0004\u0018\u00010\u001BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001C\u001A\u00020\u001B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001D\u0010\u001ER \u0010\u001F\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u000F8\u0006X\u0087\u0004\u00A2\u0006\n\n\u0002\u0010!\u0012\u0004\b \u0010\r\u00A8\u00067"}, d2 = {"Landroidx/room/RoomSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteProgram;", "capacity", "", "(I)V", "<set-?>", "argCount", "getArgCount", "()I", "bindingTypes", "", "getBindingTypes$annotations", "()V", "blobBindings", "", "", "getBlobBindings$annotations", "[[B", "getCapacity", "doubleBindings", "", "getDoubleBindings$annotations", "longBindings", "", "getLongBindings$annotations", "query", "", "sql", "getSql", "()Ljava/lang/String;", "stringBindings", "getStringBindings$annotations", "[Ljava/lang/String;", "bindBlob", "", "index", "value", "bindDouble", "", "bindLong", "", "bindNull", "bindString", "bindTo", "statement", "clearBindings", "close", "copyArgumentsFrom", "other", "init", "initArgCount", "release", "Binding", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class RoomSQLiteQuery implements SupportSQLiteProgram, SupportSQLiteQuery, AutoCloseable {
    @Retention(RetentionPolicy.SOURCE)
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\b\u0081\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/room/RoomSQLiteQuery$Binding;", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface Binding {
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001A\u00020\u000F2\u0006\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u0004H\u0007J\u0010\u0010\u0015\u001A\u00020\u000F2\u0006\u0010\u0016\u001A\u00020\u0017H\u0007J\r\u0010\u0018\u001A\u00020\u0019H\u0000¢\u0006\u0002\b\u001AR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001A\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002R\u000E\u0010\u0007\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001A\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000B\u0010\u0002R\u000E\u0010\f\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\"\u0010\r\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000F0\u000E8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0002¨\u0006\u001B"}, d2 = {"Landroidx/room/RoomSQLiteQuery$Companion;", "", "()V", "BLOB", "", "DESIRED_POOL_SIZE", "getDESIRED_POOL_SIZE$annotations", "DOUBLE", "LONG", "NULL", "POOL_LIMIT", "getPOOL_LIMIT$annotations", "STRING", "queryPool", "Ljava/util/TreeMap;", "Landroidx/room/RoomSQLiteQuery;", "getQueryPool$annotations", "acquire", "query", "", "argumentCount", "copyFrom", "supportSQLiteQuery", "Landroidx/sqlite/db/SupportSQLiteQuery;", "prunePoolLocked", "", "prunePoolLocked$room_runtime_release", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final RoomSQLiteQuery acquire(String s, int v) {
            Intrinsics.checkNotNullParameter(s, "query");
            synchronized(RoomSQLiteQuery.queryPool) {
                Map.Entry map$Entry0 = RoomSQLiteQuery.queryPool.ceilingEntry(v);
                if(map$Entry0 != null) {
                    Object object0 = map$Entry0.getKey();
                    RoomSQLiteQuery.queryPool.remove(object0);
                    RoomSQLiteQuery roomSQLiteQuery0 = (RoomSQLiteQuery)map$Entry0.getValue();
                    roomSQLiteQuery0.init(s, v);
                    Intrinsics.checkNotNullExpressionValue(roomSQLiteQuery0, "sqliteQuery");
                    return roomSQLiteQuery0;
                }
            }
            RoomSQLiteQuery roomSQLiteQuery1 = new RoomSQLiteQuery(v, null);
            roomSQLiteQuery1.init(s, v);
            return roomSQLiteQuery1;
        }

        @JvmStatic
        public final RoomSQLiteQuery copyFrom(SupportSQLiteQuery supportSQLiteQuery0) {
            Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "supportSQLiteQuery");
            RoomSQLiteQuery roomSQLiteQuery0 = this.acquire(supportSQLiteQuery0.getSql(), supportSQLiteQuery0.getArgCount());
            supportSQLiteQuery0.bindTo(new Object() {
                private final RoomSQLiteQuery $$delegate_0;

                {
                    this.$$delegate_0 = roomSQLiteQuery0;
                }

                @Override  // androidx.sqlite.db.SupportSQLiteProgram
                public void bindBlob(int v, byte[] arr_b) {
                    Intrinsics.checkNotNullParameter(arr_b, "value");
                    this.$$delegate_0.bindBlob(v, arr_b);
                }

                @Override  // androidx.sqlite.db.SupportSQLiteProgram
                public void bindDouble(int v, double f) {
                    this.$$delegate_0.bindDouble(v, f);
                }

                @Override  // androidx.sqlite.db.SupportSQLiteProgram
                public void bindLong(int v, long v1) {
                    this.$$delegate_0.bindLong(v, v1);
                }

                @Override  // androidx.sqlite.db.SupportSQLiteProgram
                public void bindNull(int v) {
                    this.$$delegate_0.bindNull(v);
                }

                @Override  // androidx.sqlite.db.SupportSQLiteProgram
                public void bindString(int v, String s) {
                    Intrinsics.checkNotNullParameter(s, "value");
                    this.$$delegate_0.bindString(v, s);
                }

                @Override  // androidx.sqlite.db.SupportSQLiteProgram
                public void clearBindings() {
                    this.$$delegate_0.clearBindings();
                }

                @Override
                public void close() {
                }
            });
            return roomSQLiteQuery0;
        }

        public static void getDESIRED_POOL_SIZE$annotations() {
        }

        public static void getPOOL_LIMIT$annotations() {
        }

        public static void getQueryPool$annotations() {
        }

        public final void prunePoolLocked$room_runtime_release() {
            if(RoomSQLiteQuery.queryPool.size() > 15) {
                int v = RoomSQLiteQuery.queryPool.size() - 10;
                Iterator iterator0 = RoomSQLiteQuery.queryPool.descendingKeySet().iterator();
                Intrinsics.checkNotNullExpressionValue(iterator0, "queryPool.descendingKeySet().iterator()");
                while(v > 0) {
                    iterator0.next();
                    iterator0.remove();
                    --v;
                }
            }
        }
    }

    private static final int BLOB = 5;
    public static final Companion Companion = null;
    public static final int DESIRED_POOL_SIZE = 10;
    private static final int DOUBLE = 3;
    private static final int LONG = 2;
    private static final int NULL = 1;
    public static final int POOL_LIMIT = 15;
    private static final int STRING = 4;
    private int argCount;
    private final int[] bindingTypes;
    public final byte[][] blobBindings;
    private final int capacity;
    public final double[] doubleBindings;
    public final long[] longBindings;
    private volatile String query;
    public static final TreeMap queryPool;
    public final String[] stringBindings;

    static {
        RoomSQLiteQuery.Companion = new Companion(null);
        RoomSQLiteQuery.queryPool = new TreeMap();
    }

    private RoomSQLiteQuery(int v) {
        this.capacity = v;
        this.bindingTypes = new int[v + 1];
        this.longBindings = new long[v + 1];
        this.doubleBindings = new double[v + 1];
        this.stringBindings = new String[v + 1];
        this.blobBindings = new byte[v + 1][];
    }

    public RoomSQLiteQuery(int v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v);
    }

    @JvmStatic
    public static final RoomSQLiteQuery acquire(String s, int v) {
        return RoomSQLiteQuery.Companion.acquire(s, v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int v, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "value");
        this.bindingTypes[v] = 5;
        this.blobBindings[v] = arr_b;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int v, double f) {
        this.bindingTypes[v] = 3;
        this.doubleBindings[v] = f;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int v, long v1) {
        this.bindingTypes[v] = 2;
        this.longBindings[v] = v1;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int v) {
        this.bindingTypes[v] = 1;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int v, String s) {
        Intrinsics.checkNotNullParameter(s, "value");
        this.bindingTypes[v] = 4;
        this.stringBindings[v] = s;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram0) {
        Intrinsics.checkNotNullParameter(supportSQLiteProgram0, "statement");
        int v = this.getArgCount();
        if(1 <= v) {
            for(int v1 = 1; true; ++v1) {
                switch(this.bindingTypes[v1]) {
                    case 1: {
                        supportSQLiteProgram0.bindNull(v1);
                        break;
                    }
                    case 2: {
                        supportSQLiteProgram0.bindLong(v1, this.longBindings[v1]);
                        break;
                    }
                    case 3: {
                        supportSQLiteProgram0.bindDouble(v1, this.doubleBindings[v1]);
                        break;
                    }
                    case 4: {
                        String s = this.stringBindings[v1];
                        if(s == null) {
                            throw new IllegalArgumentException("Required value was null.");
                        }
                        supportSQLiteProgram0.bindString(v1, s);
                        break;
                    }
                    case 5: {
                        byte[] arr_b = this.blobBindings[v1];
                        if(arr_b == null) {
                            throw new IllegalArgumentException("Required value was null.");
                        }
                        supportSQLiteProgram0.bindBlob(v1, arr_b);
                        break;
                    }
                }
                if(v1 == v) {
                    break;
                }
            }
        }
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        Arrays.fill(this.bindingTypes, 1);
        Arrays.fill(this.stringBindings, null);
        Arrays.fill(this.blobBindings, null);
        this.query = null;
    }

    @Override
    public void close() {
    }

    public final void copyArgumentsFrom(RoomSQLiteQuery roomSQLiteQuery0) {
        Intrinsics.checkNotNullParameter(roomSQLiteQuery0, "other");
        int v = roomSQLiteQuery0.getArgCount();
        System.arraycopy(roomSQLiteQuery0.bindingTypes, 0, this.bindingTypes, 0, v + 1);
        System.arraycopy(roomSQLiteQuery0.longBindings, 0, this.longBindings, 0, v + 1);
        System.arraycopy(roomSQLiteQuery0.stringBindings, 0, this.stringBindings, 0, v + 1);
        System.arraycopy(roomSQLiteQuery0.blobBindings, 0, this.blobBindings, 0, v + 1);
        System.arraycopy(roomSQLiteQuery0.doubleBindings, 0, this.doubleBindings, 0, v + 1);
    }

    @JvmStatic
    public static final RoomSQLiteQuery copyFrom(SupportSQLiteQuery supportSQLiteQuery0) {
        return RoomSQLiteQuery.Companion.copyFrom(supportSQLiteQuery0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        return this.argCount;
    }

    private static void getBindingTypes$annotations() {
    }

    public static void getBlobBindings$annotations() {
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public static void getDoubleBindings$annotations() {
    }

    public static void getLongBindings$annotations() {
    }

    @Override  // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        String s = this.query;
        if(s == null) {
            throw new IllegalStateException("Required value was null.");
        }
        return s;
    }

    public static void getStringBindings$annotations() {
    }

    public final void init(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "query");
        this.query = s;
        this.argCount = v;
    }

    public final void release() {
        synchronized(RoomSQLiteQuery.queryPool) {
            RoomSQLiteQuery.queryPool.put(this.capacity, this);
            RoomSQLiteQuery.Companion.prunePoolLocked$room_runtime_release();
        }
    }
}

