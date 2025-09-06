package androidx.work.impl;

import android.content.Context;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\u000E"}, d2 = {"Landroidx/work/impl/RescheduleMigration;", "Landroidx/room/migration/Migration;", "mContext", "Landroid/content/Context;", "startVersion", "", "endVersion", "(Landroid/content/Context;II)V", "getMContext", "()Landroid/content/Context;", "migrate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class RescheduleMigration extends Migration {
    private final Context mContext;

    public RescheduleMigration(Context context0, int v, int v1) {
        Intrinsics.checkNotNullParameter(context0, "mContext");
        super(v, v1);
        this.mContext = context0;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    @Override  // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        if(this.endVersion >= 10) {
            supportSQLiteDatabase0.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", 1});
            return;
        }
        this.mContext.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
    }
}

