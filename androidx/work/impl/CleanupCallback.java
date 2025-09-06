package androidx.work.impl;

import androidx.room.RoomDatabase.Callback;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.Clock;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001A\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0014\u0010\u000B\u001A\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000E¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/CleanupCallback;", "Landroidx/room/RoomDatabase$Callback;", "clock", "Landroidx/work/Clock;", "(Landroidx/work/Clock;)V", "getClock", "()Landroidx/work/Clock;", "pruneDate", "", "getPruneDate", "()J", "pruneSQL", "", "getPruneSQL", "()Ljava/lang/String;", "onOpen", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CleanupCallback extends Callback {
    private final Clock clock;

    public CleanupCallback(Clock clock0) {
        Intrinsics.checkNotNullParameter(clock0, "clock");
        super();
        this.clock = clock0;
    }

    public final Clock getClock() {
        return this.clock;
    }

    private final long getPruneDate() {
        return this.clock.currentTimeMillis() - WorkDatabaseKt.PRUNE_THRESHOLD_MILLIS;
    }

    private final String getPruneSQL() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < " + this.getPruneDate() + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }

    @Override  // androidx.room.RoomDatabase$Callback
    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        super.onOpen(supportSQLiteDatabase0);
        supportSQLiteDatabase0.beginTransaction();
        try {
            supportSQLiteDatabase0.execSQL(this.getPruneSQL());
            supportSQLiteDatabase0.setTransactionSuccessful();
        }
        finally {
            supportSQLiteDatabase0.endTransaction();
        }
    }
}

