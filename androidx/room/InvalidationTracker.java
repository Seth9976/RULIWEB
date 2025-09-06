package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u00AC\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0016\u0018\u0000 e2\u00020\u0001:\u0005efghiB#\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0012\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006\u00A2\u0006\u0002\u0010\u0007BV\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0012\u0010\b\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t\u0012\u001D\u0010\n\u001A\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u000F\u0012\r\u0012\u0004\u0012\u00020\u00060\u000B\u00A2\u0006\u0002\b\f0\t\u0012\u0012\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006\u00A2\u0006\u0002\u0010\rJ\u0010\u00108\u001A\u0002092\u0006\u0010:\u001A\u00020\"H\u0017J\u0010\u0010;\u001A\u0002092\u0006\u0010:\u001A\u00020\"H\u0017J9\u0010<\u001A\b\u0012\u0004\u0012\u0002H>0=\"\u0004\b\u0000\u0010>2\u000E\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\u000E\u0010?\u001A\n\u0012\u0006\u0012\u0004\u0018\u0001H>0@H\u0017\u00A2\u0006\u0002\u0010AJA\u0010<\u001A\b\u0012\u0004\u0012\u0002H>0=\"\u0004\b\u0000\u0010>2\u000E\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\u0006\u0010B\u001A\u00020\u00192\u000E\u0010?\u001A\n\u0012\u0006\u0012\u0004\u0018\u0001H>0@H\u0017\u00A2\u0006\u0002\u0010CJ\r\u0010D\u001A\u00020\u0019H\u0000\u00A2\u0006\u0002\bEJ\u0015\u0010F\u001A\u0002092\u0006\u0010\u0002\u001A\u00020GH\u0000\u00A2\u0006\u0002\bHJ!\u0010I\u001A\u0002092\u0012\u0010J\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\u0007\u00A2\u0006\u0002\u0010KJ\b\u0010L\u001A\u000209H\u0002J\b\u0010M\u001A\u000209H\u0016J\b\u0010N\u001A\u000209H\u0017J\u0010\u0010O\u001A\u0002092\u0006\u0010:\u001A\u00020\"H\u0017J%\u0010P\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\u000E\u0010Q\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005H\u0002\u00A2\u0006\u0002\u0010RJ\u0015\u0010S\u001A\u0002092\u0006\u0010\u000E\u001A\u00020\u000FH\u0000\u00A2\u0006\u0002\bTJ%\u0010U\u001A\u0002092\u0006\u0010V\u001A\u00020W2\u0006\u0010X\u001A\u00020\u00062\u0006\u0010Y\u001A\u00020ZH\u0000\u00A2\u0006\u0002\b[J\u0018\u0010\\\u001A\u0002092\u0006\u0010]\u001A\u00020G2\u0006\u0010^\u001A\u000200H\u0002J\r\u0010_\u001A\u000209H\u0000\u00A2\u0006\u0002\b`J\u0018\u0010a\u001A\u0002092\u0006\u0010]\u001A\u00020G2\u0006\u0010^\u001A\u000200H\u0002J\r\u0010b\u001A\u000209H\u0000\u00A2\u0006\u0002\bcJ\u0015\u0010b\u001A\u0002092\u0006\u0010\u0002\u001A\u00020GH\u0000\u00A2\u0006\u0002\bcJ%\u0010d\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\u000E\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005H\u0002\u00A2\u0006\u0002\u0010RR\u0010\u0010\u000E\u001A\u0004\u0018\u00010\u000FX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001C\u0010\u0010\u001A\u0004\u0018\u00010\u0011X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u0017R\u000E\u0010\u0018\u001A\u00020\u0019X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001A\u001A\u00020\u001BX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001C\u001A\u0004\u0018\u00010\u001DX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001E\u001A\u00020\u001FX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\"\u0010 \u001A\u000E\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!8\u0000X\u0081\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b$\u0010%R\u0016\u0010&\u001A\u00020\'8GX\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b(\u0010)R\u0016\u0010*\u001A\u00020+8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b,\u0010-R\u001A\u0010\b\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010.\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R \u0010/\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002000\tX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b1\u00102R\u001E\u00103\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0080\u0004\u00A2\u0006\n\n\u0002\u00106\u001A\u0004\b4\u00105R\u000E\u00107\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R%\u0010\n\u001A\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u000F\u0012\r\u0012\u0004\u0012\u00020\u00060\u000B\u00A2\u0006\u0002\b\f0\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006j"}, d2 = {"Landroidx/room/InvalidationTracker;", "", "database", "Landroidx/room/RoomDatabase;", "tableNames", "", "", "(Landroidx/room/RoomDatabase;[Ljava/lang/String;)V", "shadowTablesMap", "", "viewTables", "", "Lkotlin/jvm/JvmSuppressWildcards;", "(Landroidx/room/RoomDatabase;Ljava/util/Map;Ljava/util/Map;[Ljava/lang/String;)V", "autoCloser", "Landroidx/room/AutoCloser;", "cleanupStatement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "getCleanupStatement$room_runtime_release", "()Landroidx/sqlite/db/SupportSQLiteStatement;", "setCleanupStatement$room_runtime_release", "(Landroidx/sqlite/db/SupportSQLiteStatement;)V", "getDatabase$room_runtime_release", "()Landroidx/room/RoomDatabase;", "initialized", "", "invalidationLiveDataContainer", "Landroidx/room/InvalidationLiveDataContainer;", "multiInstanceInvalidationClient", "Landroidx/room/MultiInstanceInvalidationClient;", "observedTableTracker", "Landroidx/room/InvalidationTracker$ObservedTableTracker;", "observerMap", "Landroidx/arch/core/internal/SafeIterableMap;", "Landroidx/room/InvalidationTracker$Observer;", "Landroidx/room/InvalidationTracker$ObserverWrapper;", "getObserverMap$room_runtime_release", "()Landroidx/arch/core/internal/SafeIterableMap;", "pendingRefresh", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getPendingRefresh", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "refreshRunnable", "Ljava/lang/Runnable;", "getRefreshRunnable$annotations", "()V", "syncTriggersLock", "tableIdLookup", "", "getTableIdLookup$room_runtime_release", "()Ljava/util/Map;", "tablesNames", "getTablesNames$room_runtime_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "trackerLock", "addObserver", "", "observer", "addWeakObserver", "createLiveData", "Landroidx/lifecycle/LiveData;", "T", "computeFunction", "Ljava/util/concurrent/Callable;", "([Ljava/lang/String;Ljava/util/concurrent/Callable;)Landroidx/lifecycle/LiveData;", "inTransaction", "([Ljava/lang/String;ZLjava/util/concurrent/Callable;)Landroidx/lifecycle/LiveData;", "ensureInitialization", "ensureInitialization$room_runtime_release", "internalInit", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "internalInit$room_runtime_release", "notifyObserversByTableNames", "tables", "([Ljava/lang/String;)V", "onAutoCloseCallback", "refreshVersionsAsync", "refreshVersionsSync", "removeObserver", "resolveViews", "names", "([Ljava/lang/String;)[Ljava/lang/String;", "setAutoCloser", "setAutoCloser$room_runtime_release", "startMultiInstanceInvalidation", "context", "Landroid/content/Context;", "name", "serviceIntent", "Landroid/content/Intent;", "startMultiInstanceInvalidation$room_runtime_release", "startTrackingTable", "db", "tableId", "stopMultiInstanceInvalidation", "stopMultiInstanceInvalidation$room_runtime_release", "stopTrackingTable", "syncTriggers", "syncTriggers$room_runtime_release", "validateAndResolveTableNames", "Companion", "ObservedTableTracker", "Observer", "ObserverWrapper", "WeakObserver", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class InvalidationTracker {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u001D\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00020\u00042\u0006\u0010\u0016\u001A\u00020\u0004H\u0000¢\u0006\u0002\b\u0017R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001A\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001A\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u000E\u0010\n\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00040\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000E\u0010\u000E\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/room/InvalidationTracker$Companion;", "", "()V", "CREATE_TRACKING_TABLE_SQL", "", "INVALIDATED_COLUMN_NAME", "RESET_UPDATED_TABLES_SQL", "getRESET_UPDATED_TABLES_SQL$room_runtime_release$annotations", "SELECT_UPDATED_TABLES_SQL", "getSELECT_UPDATED_TABLES_SQL$room_runtime_release$annotations", "TABLE_ID_COLUMN_NAME", "TRIGGERS", "", "[Ljava/lang/String;", "UPDATE_TABLE_NAME", "beginTransactionInternal", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "beginTransactionInternal$room_runtime_release", "getTriggerName", "tableName", "triggerType", "getTriggerName$room_runtime_release", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final void beginTransactionInternal$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "database");
            if(supportSQLiteDatabase0.isWriteAheadLoggingEnabled()) {
                supportSQLiteDatabase0.beginTransactionNonExclusive();
                return;
            }
            supportSQLiteDatabase0.beginTransaction();
        }

        public static void getRESET_UPDATED_TABLES_SQL$room_runtime_release$annotations() {
        }

        public static void getSELECT_UPDATED_TABLES_SQL$room_runtime_release$annotations() {
        }

        public final String getTriggerName$room_runtime_release(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "tableName");
            Intrinsics.checkNotNullParameter(s1, "triggerType");
            return "`room_table_modification_trigger_" + s + '_' + s1 + '`';
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0013\u001A\u0004\u0018\u00010\u0010H\u0007J\u0012\u0010\u0014\u001A\u00020\u00062\n\u0010\u0015\u001A\u00020\u0010\"\u00020\u0003J\u0012\u0010\u0016\u001A\u00020\u00062\n\u0010\u0015\u001A\u00020\u0010\"\u00020\u0003J\u0006\u0010\u0017\u001A\u00020\u0018R\u001A\u0010\u0005\u001A\u00020\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000B\u001A\u00020\f¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Landroidx/room/InvalidationTracker$ObservedTableTracker;", "", "tableCount", "", "(I)V", "needsSync", "", "getNeedsSync", "()Z", "setNeedsSync", "(Z)V", "tableObservers", "", "getTableObservers", "()[J", "triggerStateChanges", "", "triggerStates", "", "getTablesToSync", "onAdded", "tableIds", "onRemoved", "resetTriggerState", "", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ObservedTableTracker {
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/room/InvalidationTracker$ObservedTableTracker$Companion;", "", "()V", "ADD", "", "NO_OP", "REMOVE", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.room.InvalidationTracker.ObservedTableTracker.Companion {
            private androidx.room.InvalidationTracker.ObservedTableTracker.Companion() {
            }

            public androidx.room.InvalidationTracker.ObservedTableTracker.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final int ADD = 1;
        public static final androidx.room.InvalidationTracker.ObservedTableTracker.Companion Companion = null;
        public static final int NO_OP = 0;
        public static final int REMOVE = 2;
        private boolean needsSync;
        private final long[] tableObservers;
        private final int[] triggerStateChanges;
        private final boolean[] triggerStates;

        static {
            ObservedTableTracker.Companion = new androidx.room.InvalidationTracker.ObservedTableTracker.Companion(null);
        }

        public ObservedTableTracker(int v) {
            this.tableObservers = new long[v];
            this.triggerStates = new boolean[v];
            this.triggerStateChanges = new int[v];
        }

        public final boolean getNeedsSync() {
            return this.needsSync;
        }

        public final long[] getTableObservers() {
            return this.tableObservers;
        }

        public final int[] getTablesToSync() {
            synchronized(this) {
                if(!this.needsSync) {
                    return null;
                }
                long[] arr_v = this.tableObservers;
                int v1 = 0;
                for(int v2 = 0; true; ++v2) {
                    int v3 = 1;
                    if(v1 >= arr_v.length) {
                        break;
                    }
                    boolean z = arr_v[v1] > 0L;
                    boolean[] arr_z = this.triggerStates;
                    if(z == arr_z[v2]) {
                        this.triggerStateChanges[v2] = 0;
                    }
                    else {
                        int[] arr_v1 = this.triggerStateChanges;
                        if(!z) {
                            v3 = 2;
                        }
                        arr_v1[v2] = v3;
                    }
                    arr_z[v2] = z;
                    ++v1;
                }
                this.needsSync = false;
                return (int[])this.triggerStateChanges.clone();
            }
        }

        public final boolean onAdded(int[] arr_v) {
            Intrinsics.checkNotNullParameter(arr_v, "tableIds");
            synchronized(this) {
                boolean z = false;
                for(int v1 = 0; v1 < arr_v.length; ++v1) {
                    int v2 = arr_v[v1];
                    long v3 = this.tableObservers[v2];
                    this.tableObservers[v2] = v3 + 1L;
                    if(v3 == 0L) {
                        z = true;
                        this.needsSync = true;
                    }
                }
                return z;
            }
        }

        public final boolean onRemoved(int[] arr_v) {
            Intrinsics.checkNotNullParameter(arr_v, "tableIds");
            synchronized(this) {
                boolean z = false;
                for(int v1 = 0; v1 < arr_v.length; ++v1) {
                    int v2 = arr_v[v1];
                    long v3 = this.tableObservers[v2];
                    this.tableObservers[v2] = v3 - 1L;
                    if(v3 == 1L) {
                        z = true;
                        this.needsSync = true;
                    }
                }
                return z;
            }
        }

        public final void resetTriggerState() {
            synchronized(this) {
                Arrays.fill(this.triggerStates, false);
                this.needsSync = true;
            }
        }

        public final void setNeedsSync(boolean z) {
            this.needsSync = z;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0010\"\n\u0000\b&\u0018\u00002\u00020\u0001B#\b\u0014\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0012\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0002\u0010\u0006B\u0015\u0012\u000E\u0010\u0007\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\bJ\u0016\u0010\u0010\u001A\u00020\u00112\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00030\u0012H&R\u0014\u0010\t\u001A\u00020\n8PX\u0090\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u001E\u0010\u0007\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000F\u001A\u0004\b\r\u0010\u000E¨\u0006\u0013"}, d2 = {"Landroidx/room/InvalidationTracker$Observer;", "", "firstTable", "", "rest", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "tables", "([Ljava/lang/String;)V", "isRemote", "", "isRemote$room_runtime_release", "()Z", "getTables$room_runtime_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "onInvalidated", "", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Observer {
        private final String[] tables;

        protected Observer(String s, String[] arr_s) {
            Intrinsics.checkNotNullParameter(s, "firstTable");
            Intrinsics.checkNotNullParameter(arr_s, "rest");
            List list0 = CollectionsKt.createListBuilder();
            CollectionsKt.addAll(list0, arr_s);
            list0.add(s);
            this(((String[])CollectionsKt.build(list0).toArray(new String[0])));
        }

        public Observer(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "tables");
            super();
            this.tables = arr_s;
        }

        public final String[] getTables$room_runtime_release() {
            return this.tables;
        }

        public boolean isRemote$room_runtime_release() {
            return false;
        }

        public abstract void onInvalidated(Set arg1);
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u000E\u0010\u0006\u001A\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u001D\u0010\u0011\u001A\u00020\u00122\u000E\u0010\u0013\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\rH\u0000¢\u0006\u0002\b\u0015J\u001F\u0010\u0016\u001A\u00020\u00122\u000E\u0010\u0017\u001A\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007H\u0000¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\f\u001A\b\u0012\u0004\u0012\u00020\b0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0018\u0010\u0006\u001A\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u001A"}, d2 = {"Landroidx/room/InvalidationTracker$ObserverWrapper;", "", "observer", "Landroidx/room/InvalidationTracker$Observer;", "tableIds", "", "tableNames", "", "", "(Landroidx/room/InvalidationTracker$Observer;[I[Ljava/lang/String;)V", "getObserver$room_runtime_release", "()Landroidx/room/InvalidationTracker$Observer;", "singleTableSet", "", "getTableIds$room_runtime_release", "()[I", "[Ljava/lang/String;", "notifyByTableInvalidStatus", "", "invalidatedTablesIds", "", "notifyByTableInvalidStatus$room_runtime_release", "notifyByTableNames", "tables", "notifyByTableNames$room_runtime_release", "([Ljava/lang/String;)V", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ObserverWrapper {
        private final Observer observer;
        private final Set singleTableSet;
        private final int[] tableIds;
        private final String[] tableNames;

        public ObserverWrapper(Observer invalidationTracker$Observer0, int[] arr_v, String[] arr_s) {
            Intrinsics.checkNotNullParameter(invalidationTracker$Observer0, "observer");
            Intrinsics.checkNotNullParameter(arr_v, "tableIds");
            Intrinsics.checkNotNullParameter(arr_s, "tableNames");
            super();
            this.observer = invalidationTracker$Observer0;
            this.tableIds = arr_v;
            this.tableNames = arr_s;
            this.singleTableSet = arr_s.length == 0 ? SetsKt.emptySet() : SetsKt.setOf(arr_s[0]);
            if(arr_v.length != arr_s.length) {
                throw new IllegalStateException("Check failed.");
            }
        }

        public final Observer getObserver$room_runtime_release() {
            return this.observer;
        }

        public final int[] getTableIds$room_runtime_release() {
            return this.tableIds;
        }

        public final void notifyByTableInvalidStatus$room_runtime_release(Set set0) {
            Set set2;
            int v = 0;
            Intrinsics.checkNotNullParameter(set0, "invalidatedTablesIds");
            int[] arr_v = this.tableIds;
            switch(arr_v.length) {
                case 0: {
                    set2 = SetsKt.emptySet();
                    break;
                }
                case 1: {
                    set2 = set0.contains(((int)arr_v[0])) ? this.singleTableSet : SetsKt.emptySet();
                    break;
                }
                default: {
                    Set set1 = SetsKt.createSetBuilder();
                    int[] arr_v1 = this.tableIds;
                    for(int v1 = 0; v < arr_v1.length; ++v1) {
                        if(set0.contains(((int)arr_v1[v]))) {
                            set1.add(this.tableNames[v1]);
                        }
                        ++v;
                    }
                    set2 = SetsKt.build(set1);
                }
            }
            if(!set2.isEmpty()) {
                this.observer.onInvalidated(set2);
            }
        }

        public final void notifyByTableNames$room_runtime_release(String[] arr_s) {
            Set set1;
            Intrinsics.checkNotNullParameter(arr_s, "tables");
        alab1:
            switch(this.tableNames.length) {
                case 0: {
                    set1 = SetsKt.emptySet();
                    break;
                }
                case 1: {
                    int v2 = 0;
                    while(true) {
                        if(v2 < arr_s.length) {
                            if(StringsKt.equals(arr_s[v2], this.tableNames[0], true)) {
                                set1 = this.singleTableSet;
                                break alab1;
                            }
                            ++v2;
                            continue;
                        }
                        set1 = SetsKt.emptySet();
                        break alab1;
                    }
                }
                default: {
                    Set set0 = SetsKt.createSetBuilder();
                    for(int v = 0; v < arr_s.length; ++v) {
                        String s = arr_s[v];
                        String[] arr_s1 = this.tableNames;
                        for(int v1 = 0; v1 < arr_s1.length; ++v1) {
                            String s1 = arr_s1[v1];
                            if(StringsKt.equals(s1, s, true)) {
                                set0.add(s1);
                            }
                        }
                    }
                    set1 = SetsKt.build(set0);
                }
            }
            if(!set1.isEmpty()) {
                this.observer.onInvalidated(set1);
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000E\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0001¢\u0006\u0002\u0010\u0005J\u0016\u0010\f\u001A\u00020\r2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000FH\u0016R\u0017\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u0011"}, d2 = {"Landroidx/room/InvalidationTracker$WeakObserver;", "Landroidx/room/InvalidationTracker$Observer;", "tracker", "Landroidx/room/InvalidationTracker;", "delegate", "(Landroidx/room/InvalidationTracker;Landroidx/room/InvalidationTracker$Observer;)V", "delegateRef", "Ljava/lang/ref/WeakReference;", "getDelegateRef", "()Ljava/lang/ref/WeakReference;", "getTracker", "()Landroidx/room/InvalidationTracker;", "onInvalidated", "", "tables", "", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class WeakObserver extends Observer {
        private final WeakReference delegateRef;
        private final InvalidationTracker tracker;

        public WeakObserver(InvalidationTracker invalidationTracker0, Observer invalidationTracker$Observer0) {
            Intrinsics.checkNotNullParameter(invalidationTracker0, "tracker");
            Intrinsics.checkNotNullParameter(invalidationTracker$Observer0, "delegate");
            super(invalidationTracker$Observer0.getTables$room_runtime_release());
            this.tracker = invalidationTracker0;
            this.delegateRef = new WeakReference(invalidationTracker$Observer0);
        }

        public final WeakReference getDelegateRef() {
            return this.delegateRef;
        }

        public final InvalidationTracker getTracker() {
            return this.tracker;
        }

        @Override  // androidx.room.InvalidationTracker$Observer
        public void onInvalidated(Set set0) {
            Intrinsics.checkNotNullParameter(set0, "tables");
            Observer invalidationTracker$Observer0 = (Observer)this.delegateRef.get();
            if(invalidationTracker$Observer0 == null) {
                this.tracker.removeObserver(this);
                return;
            }
            invalidationTracker$Observer0.onInvalidated(set0);
        }
    }

    private static final String CREATE_TRACKING_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)";
    public static final Companion Companion = null;
    private static final String INVALIDATED_COLUMN_NAME = "invalidated";
    public static final String RESET_UPDATED_TABLES_SQL = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1";
    public static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE invalidated = 1;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = null;
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    private AutoCloser autoCloser;
    private volatile SupportSQLiteStatement cleanupStatement;
    private final RoomDatabase database;
    private volatile boolean initialized;
    private final InvalidationLiveDataContainer invalidationLiveDataContainer;
    private MultiInstanceInvalidationClient multiInstanceInvalidationClient;
    private final ObservedTableTracker observedTableTracker;
    private final SafeIterableMap observerMap;
    private final AtomicBoolean pendingRefresh;
    public final Runnable refreshRunnable;
    private final Map shadowTablesMap;
    private final Object syncTriggersLock;
    private final Map tableIdLookup;
    private final String[] tablesNames;
    private final Object trackerLock;
    private final Map viewTables;

    // 检测为 Lambda 实现
    public static void $r8$lambda$UljetDAmKS13HgVIbnOntOl3h0c(InvalidationTracker invalidationTracker0) [...]

    static {
        InvalidationTracker.Companion = new Companion(null);
        InvalidationTracker.TRIGGERS = new String[]{"UPDATE", "DELETE", "INSERT"};
    }

    public InvalidationTracker(RoomDatabase roomDatabase0, Map map0, Map map1, String[] arr_s) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "database");
        String s3;
        Intrinsics.checkNotNullParameter(map0, "shadowTablesMap");
        Intrinsics.checkNotNullParameter(map1, "viewTables");
        Intrinsics.checkNotNullParameter(arr_s, "tableNames");
        super();
        this.database = roomDatabase0;
        this.shadowTablesMap = map0;
        this.viewTables = map1;
        this.pendingRefresh = new AtomicBoolean(false);
        this.observedTableTracker = new ObservedTableTracker(arr_s.length);
        this.invalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase0);
        this.observerMap = new SafeIterableMap();
        this.syncTriggersLock = new Object();
        this.trackerLock = new Object();
        this.tableIdLookup = new LinkedHashMap();
        String[] arr_s1 = new String[arr_s.length];
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s1 = s.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(locale)");
            this.tableIdLookup.put(s1, v);
            String s2 = (String)this.shadowTablesMap.get(arr_s[v]);
            if(s2 == null) {
                s3 = null;
            }
            else {
                Locale locale1 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale1, "US");
                s3 = s2.toLowerCase(locale1);
                Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).toLowerCase(locale)");
            }
            if(s3 != null) {
                s1 = s3;
            }
            arr_s1[v] = s1;
        }
        this.tablesNames = arr_s1;
        for(Object object0: this.shadowTablesMap.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            String s4 = (String)map$Entry0.getValue();
            Locale locale2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale2, "US");
            String s5 = s4.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(s5, "this as java.lang.String).toLowerCase(locale)");
            if(this.tableIdLookup.containsKey(s5)) {
                String s6 = (String)map$Entry0.getKey();
                Locale locale3 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale3, "US");
                String s7 = s6.toLowerCase(locale3);
                Intrinsics.checkNotNullExpressionValue(s7, "this as java.lang.String).toLowerCase(locale)");
                Object object1 = MapsKt.getValue(this.tableIdLookup, s5);
                this.tableIdLookup.put(s7, object1);
            }
        }
        this.refreshRunnable = new Runnable() {
            private final Set checkUpdatedTable() {
                Set set0 = SetsKt.createSetBuilder();
                SimpleSQLiteQuery simpleSQLiteQuery0 = new SimpleSQLiteQuery("SELECT * FROM room_table_modification_log WHERE invalidated = 1;");
                Closeable closeable0 = RoomDatabase.query$default(InvalidationTracker.this.getDatabase$room_runtime_release(), simpleSQLiteQuery0, null, 2, null);
                while(true) {
                    try {
                        if(!((Cursor)closeable0).moveToNext()) {
                            break;
                        }
                        set0.add(((Cursor)closeable0).getInt(0));
                    }
                    catch(Throwable throwable0) {
                        CloseableKt.closeFinally(closeable0, throwable0);
                        throw throwable0;
                    }
                }
                CloseableKt.closeFinally(closeable0, null);
                Set set1 = SetsKt.build(set0);
                if(!set1.isEmpty()) {
                    if(InvalidationTracker.this.getCleanupStatement$room_runtime_release() == null) {
                        throw new IllegalStateException("Required value was null.");
                    }
                    SupportSQLiteStatement supportSQLiteStatement0 = InvalidationTracker.this.getCleanupStatement$room_runtime_release();
                    if(supportSQLiteStatement0 == null) {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                    supportSQLiteStatement0.executeUpdateDelete();
                    return set1;
                }
                return set1;
            }

            @Override
            public void run() {
                Set set0;
                Lock lock0 = InvalidationTracker.this.getDatabase$room_runtime_release().getCloseLock$room_runtime_release();
                lock0.lock();
                try {
                    try {
                        if(!InvalidationTracker.this.ensureInitialization$room_runtime_release()) {
                            goto label_63;
                        }
                        else if(!InvalidationTracker.this.getPendingRefresh().compareAndSet(true, false)) {
                            goto label_58;
                        }
                        else if(!InvalidationTracker.this.getDatabase$room_runtime_release().inTransaction()) {
                            SupportSQLiteDatabase supportSQLiteDatabase0 = InvalidationTracker.this.getDatabase$room_runtime_release().getOpenHelper().getWritableDatabase();
                            supportSQLiteDatabase0.beginTransactionNonExclusive();
                            try {
                                set0 = this.checkUpdatedTable();
                                supportSQLiteDatabase0.setTransactionSuccessful();
                            }
                            finally {
                                supportSQLiteDatabase0.endTransaction();
                            }
                            goto label_36;
                        }
                        else {
                            goto label_53;
                        }
                        return;
                    }
                    catch(IllegalStateException illegalStateException0) {
                    }
                    catch(SQLiteException sQLiteException0) {
                        goto label_23;
                    }
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", illegalStateException0);
                    set0 = SetsKt.emptySet();
                }
                catch(Throwable throwable0) {
                    goto label_31;
                }
                lock0.unlock();
                AutoCloser autoCloser0 = InvalidationTracker.access$getAutoCloser$p(InvalidationTracker.this);
                if(autoCloser0 != null) {
                    autoCloser0.decrementCountAndScheduleClose();
                    goto label_40;
                    try {
                    label_23:
                        Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", sQLiteException0);
                        set0 = SetsKt.emptySet();
                    }
                    catch(Throwable throwable0) {
                        goto label_31;
                    }
                    lock0.unlock();
                    autoCloser0 = InvalidationTracker.access$getAutoCloser$p(InvalidationTracker.this);
                    if(autoCloser0 != null) {
                        autoCloser0.decrementCountAndScheduleClose();
                        goto label_40;
                    label_31:
                        lock0.unlock();
                        AutoCloser autoCloser1 = InvalidationTracker.access$getAutoCloser$p(InvalidationTracker.this);
                        if(autoCloser1 != null) {
                            autoCloser1.decrementCountAndScheduleClose();
                        }
                        throw throwable0;
                    label_36:
                        lock0.unlock();
                        autoCloser0 = InvalidationTracker.access$getAutoCloser$p(InvalidationTracker.this);
                        if(autoCloser0 != null) {
                            autoCloser0.decrementCountAndScheduleClose();
                        }
                    }
                }
            label_40:
                if(!set0.isEmpty()) {
                    synchronized(InvalidationTracker.this.getObserverMap$room_runtime_release()) {
                        for(Object object0: InvalidationTracker.this.getObserverMap$room_runtime_release()) {
                            ((ObserverWrapper)((Map.Entry)object0).getValue()).notifyByTableInvalidStatus$room_runtime_release(set0);
                        }
                    }
                    return;
                label_53:
                    lock0.unlock();
                    AutoCloser autoCloser2 = InvalidationTracker.access$getAutoCloser$p(InvalidationTracker.this);
                    if(autoCloser2 != null) {
                        autoCloser2.decrementCountAndScheduleClose();
                        return;
                    label_58:
                        lock0.unlock();
                        AutoCloser autoCloser3 = InvalidationTracker.access$getAutoCloser$p(InvalidationTracker.this);
                        if(autoCloser3 != null) {
                            autoCloser3.decrementCountAndScheduleClose();
                            return;
                        label_63:
                            lock0.unlock();
                            AutoCloser autoCloser4 = InvalidationTracker.access$getAutoCloser$p(InvalidationTracker.this);
                            if(autoCloser4 != null) {
                                autoCloser4.decrementCountAndScheduleClose();
                            }
                        }
                    }
                }
            }
        };
    }

    public InvalidationTracker(RoomDatabase roomDatabase0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "database");
        Intrinsics.checkNotNullParameter(arr_s, "tableNames");
        this(roomDatabase0, MapsKt.emptyMap(), MapsKt.emptyMap(), ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    public static final AutoCloser access$getAutoCloser$p(InvalidationTracker invalidationTracker0) {
        return invalidationTracker0.autoCloser;
    }

    public void addObserver(Observer invalidationTracker$Observer0) {
        Intrinsics.checkNotNullParameter(invalidationTracker$Observer0, "observer");
        String[] arr_s = this.resolveViews(invalidationTracker$Observer0.getTables$room_runtime_release());
        ArrayList arrayList0 = new ArrayList(arr_s.length);
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s1 = s.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(locale)");
            Integer integer0 = (Integer)this.tableIdLookup.get(s1);
            if(integer0 == null) {
                throw new IllegalArgumentException("There is no table with name " + s);
            }
            integer0.intValue();
            arrayList0.add(integer0);
        }
        int[] arr_v = CollectionsKt.toIntArray(arrayList0);
        ObserverWrapper invalidationTracker$ObserverWrapper0 = new ObserverWrapper(invalidationTracker$Observer0, arr_v, arr_s);
        synchronized(this.observerMap) {
            ObserverWrapper invalidationTracker$ObserverWrapper1 = (ObserverWrapper)this.observerMap.putIfAbsent(invalidationTracker$Observer0, invalidationTracker$ObserverWrapper0);
        }
        if(invalidationTracker$ObserverWrapper1 == null) {
            int[] arr_v1 = Arrays.copyOf(arr_v, arr_v.length);
            if(this.observedTableTracker.onAdded(arr_v1)) {
                this.syncTriggers$room_runtime_release();
            }
        }
    }

    public void addWeakObserver(Observer invalidationTracker$Observer0) {
        Intrinsics.checkNotNullParameter(invalidationTracker$Observer0, "observer");
        this.addObserver(new WeakObserver(this, invalidationTracker$Observer0));
    }

    @Deprecated(message = "Use [createLiveData(String[], boolean, Callable)]")
    public LiveData createLiveData(String[] arr_s, Callable callable0) {
        Intrinsics.checkNotNullParameter(arr_s, "tableNames");
        Intrinsics.checkNotNullParameter(callable0, "computeFunction");
        return this.createLiveData(arr_s, false, callable0);
    }

    public LiveData createLiveData(String[] arr_s, boolean z, Callable callable0) {
        Intrinsics.checkNotNullParameter(arr_s, "tableNames");
        Intrinsics.checkNotNullParameter(callable0, "computeFunction");
        String[] arr_s1 = this.validateAndResolveTableNames(arr_s);
        return this.invalidationLiveDataContainer.create(arr_s1, z, callable0);
    }

    public final boolean ensureInitialization$room_runtime_release() {
        if(!this.database.isOpenInternal()) {
            return false;
        }
        if(!this.initialized) {
            this.database.getOpenHelper().getWritableDatabase();
        }
        if(!this.initialized) {
            Log.e("ROOM", "database is not initialized even though it is open");
            return false;
        }
        return true;
    }

    public final SupportSQLiteStatement getCleanupStatement$room_runtime_release() {
        return this.cleanupStatement;
    }

    public final RoomDatabase getDatabase$room_runtime_release() {
        return this.database;
    }

    public final SafeIterableMap getObserverMap$room_runtime_release() {
        return this.observerMap;
    }

    public final AtomicBoolean getPendingRefresh() {
        return this.pendingRefresh;
    }

    public static void getRefreshRunnable$annotations() {
    }

    public final Map getTableIdLookup$room_runtime_release() {
        return this.tableIdLookup;
    }

    public final String[] getTablesNames$room_runtime_release() {
        return this.tablesNames;
    }

    public final void internalInit$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "database");
        synchronized(this.trackerLock) {
            if(this.initialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            supportSQLiteDatabase0.execSQL("PRAGMA temp_store = MEMORY;");
            supportSQLiteDatabase0.execSQL("PRAGMA recursive_triggers=\'ON\';");
            supportSQLiteDatabase0.execSQL("CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            this.syncTriggers$room_runtime_release(supportSQLiteDatabase0);
            this.cleanupStatement = supportSQLiteDatabase0.compileStatement("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1");
            this.initialized = true;
        }
    }

    public final void notifyObserversByTableNames(String[] arr_s) {
        Intrinsics.checkNotNullParameter(arr_s, "tables");
        synchronized(this.observerMap) {
            for(Object object0: this.observerMap) {
                Intrinsics.checkNotNullExpressionValue(((Map.Entry)object0), "(observer, wrapper)");
                Observer invalidationTracker$Observer0 = (Observer)((Map.Entry)object0).getKey();
                ObserverWrapper invalidationTracker$ObserverWrapper0 = (ObserverWrapper)((Map.Entry)object0).getValue();
                if(!invalidationTracker$Observer0.isRemote$room_runtime_release()) {
                    invalidationTracker$ObserverWrapper0.notifyByTableNames$room_runtime_release(arr_s);
                }
            }
        }
    }

    private final void onAutoCloseCallback() {
        synchronized(this.trackerLock) {
            this.initialized = false;
            this.observedTableTracker.resetTriggerState();
            SupportSQLiteStatement supportSQLiteStatement0 = this.cleanupStatement;
            if(supportSQLiteStatement0 != null) {
                supportSQLiteStatement0.close();
            }
        }
    }

    public void refreshVersionsAsync() {
        if(this.pendingRefresh.compareAndSet(false, true)) {
            AutoCloser autoCloser0 = this.autoCloser;
            if(autoCloser0 != null) {
                autoCloser0.incrementCountAndEnsureDbIsOpen();
            }
            this.database.getQueryExecutor().execute(this.refreshRunnable);
        }
    }

    public void refreshVersionsSync() {
        AutoCloser autoCloser0 = this.autoCloser;
        if(autoCloser0 != null) {
            autoCloser0.incrementCountAndEnsureDbIsOpen();
        }
        this.syncTriggers$room_runtime_release();
        this.refreshRunnable.run();
    }

    public void removeObserver(Observer invalidationTracker$Observer0) {
        ObserverWrapper invalidationTracker$ObserverWrapper0;
        Intrinsics.checkNotNullParameter(invalidationTracker$Observer0, "observer");
        synchronized(this.observerMap) {
            invalidationTracker$ObserverWrapper0 = (ObserverWrapper)this.observerMap.remove(invalidationTracker$Observer0);
        }
        if(invalidationTracker$ObserverWrapper0 != null) {
            int[] arr_v = invalidationTracker$ObserverWrapper0.getTableIds$room_runtime_release();
            int[] arr_v1 = Arrays.copyOf(arr_v, arr_v.length);
            if(this.observedTableTracker.onRemoved(arr_v1)) {
                this.syncTriggers$room_runtime_release();
            }
        }
    }

    private final String[] resolveViews(String[] arr_s) {
        Set set0 = SetsKt.createSetBuilder();
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s1 = s.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(locale)");
            if(this.viewTables.containsKey(s1)) {
                Locale locale1 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale1, "US");
                String s2 = s.toLowerCase(locale1);
                Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).toLowerCase(locale)");
                Object object0 = this.viewTables.get(s2);
                Intrinsics.checkNotNull(object0);
                set0.addAll(((Collection)object0));
            }
            else {
                set0.add(s);
            }
        }
        return (String[])SetsKt.build(set0).toArray(new String[0]);
    }

    public final void setAutoCloser$room_runtime_release(AutoCloser autoCloser0) {
        Intrinsics.checkNotNullParameter(autoCloser0, "autoCloser");
        this.autoCloser = autoCloser0;
        autoCloser0.setAutoCloseCallback(() -> this.onAutoCloseCallback());
    }

    public final void setCleanupStatement$room_runtime_release(SupportSQLiteStatement supportSQLiteStatement0) {
        this.cleanupStatement = supportSQLiteStatement0;
    }

    public final void startMultiInstanceInvalidation$room_runtime_release(Context context0, String s, Intent intent0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(intent0, "serviceIntent");
        this.multiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context0, s, intent0, this, this.database.getQueryExecutor());
    }

    private final void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase0, int v) {
        supportSQLiteDatabase0.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + v + ", 0)");
        String s = this.tablesNames[v];
        String[] arr_s = InvalidationTracker.TRIGGERS;
        for(int v1 = 0; v1 < arr_s.length; ++v1) {
            String s1 = arr_s[v1];
            String s2 = "CREATE TEMP TRIGGER IF NOT EXISTS " + InvalidationTracker.Companion.getTriggerName$room_runtime_release(s, s1) + " AFTER " + s1 + " ON `" + s + "` BEGIN UPDATE room_table_modification_log SET invalidated = 1 WHERE table_id = " + v + " AND invalidated = 0; END";
            Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
            supportSQLiteDatabase0.execSQL(s2);
        }
    }

    public final void stopMultiInstanceInvalidation$room_runtime_release() {
        MultiInstanceInvalidationClient multiInstanceInvalidationClient0 = this.multiInstanceInvalidationClient;
        if(multiInstanceInvalidationClient0 != null) {
            multiInstanceInvalidationClient0.stop();
        }
        this.multiInstanceInvalidationClient = null;
    }

    private final void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase0, int v) {
        String s = this.tablesNames[v];
        String[] arr_s = InvalidationTracker.TRIGGERS;
        for(int v1 = 0; v1 < arr_s.length; ++v1) {
            String s1 = "DROP TRIGGER IF EXISTS " + InvalidationTracker.Companion.getTriggerName$room_runtime_release(s, arr_s[v1]);
            Intrinsics.checkNotNullExpressionValue(s1, "StringBuilder().apply(builderAction).toString()");
            supportSQLiteDatabase0.execSQL(s1);
        }
    }

    public final void syncTriggers$room_runtime_release() {
        if(!this.database.isOpenInternal()) {
            return;
        }
        this.syncTriggers$room_runtime_release(this.database.getOpenHelper().getWritableDatabase());
    }

    public final void syncTriggers$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "database");
        if(!supportSQLiteDatabase0.inTransaction()) {
            try {
                Lock lock0 = this.database.getCloseLock$room_runtime_release();
                lock0.lock();
                try {
                    Object object0 = this.syncTriggersLock;
                    synchronized(object0) {
                        int[] arr_v = this.observedTableTracker.getTablesToSync();
                        if(arr_v != null) {
                            InvalidationTracker.Companion.beginTransactionInternal$room_runtime_release(supportSQLiteDatabase0);
                            try {
                                int v3 = 0;
                                for(int v4 = 0; v3 < arr_v.length; ++v4) {
                                    switch(arr_v[v3]) {
                                        case 1: {
                                            this.startTrackingTable(supportSQLiteDatabase0, v4);
                                            break;
                                        }
                                        case 2: {
                                            this.stopTrackingTable(supportSQLiteDatabase0, v4);
                                        }
                                    }
                                    ++v3;
                                }
                                supportSQLiteDatabase0.setTransactionSuccessful();
                            }
                            finally {
                                supportSQLiteDatabase0.endTransaction();
                            }
                        }
                    }
                }
                finally {
                    lock0.unlock();
                }
            }
            catch(IllegalStateException illegalStateException0) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", illegalStateException0);
            }
            catch(SQLiteException sQLiteException0) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", sQLiteException0);
            }
        }
    }

    private final String[] validateAndResolveTableNames(String[] arr_s) {
        String[] arr_s1 = this.resolveViews(arr_s);
        for(int v = 0; v < arr_s1.length; ++v) {
            String s = arr_s1[v];
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s1 = s.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(locale)");
            if(!this.tableIdLookup.containsKey(s1)) {
                throw new IllegalArgumentException(("There is no table with name " + s).toString());
            }
        }
        return arr_s1;
    }
}

