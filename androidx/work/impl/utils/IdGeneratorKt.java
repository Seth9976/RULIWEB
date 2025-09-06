package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A\u0018\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0000\u001A\u0014\u0010\f\u001A\u00020\u0001*\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0003H\u0002\u001A\u001C\u0010\u000F\u001A\u00020\u0007*\u00020\r2\u0006\u0010\u000E\u001A\u00020\u00032\u0006\u0010\u0010\u001A\u00020\u0001H\u0002\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"INITIAL_ID", "", "NEXT_ALARM_MANAGER_ID_KEY", "", "NEXT_JOB_SCHEDULER_ID_KEY", "PREFERENCE_FILE_KEY", "migrateLegacyIdGenerator", "", "context", "Landroid/content/Context;", "sqLiteDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "nextId", "Landroidx/work/impl/WorkDatabase;", "key", "updatePreference", "value", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class IdGeneratorKt {
    public static final int INITIAL_ID = 0;
    public static final String NEXT_ALARM_MANAGER_ID_KEY = "next_alarm_manager_id";
    public static final String NEXT_JOB_SCHEDULER_ID_KEY = "next_job_scheduler_id";
    public static final String PREFERENCE_FILE_KEY = "androidx.work.util.id";

    public static final int access$nextId(WorkDatabase workDatabase0, String s) {
        return IdGeneratorKt.nextId(workDatabase0, s);
    }

    public static final void access$updatePreference(WorkDatabase workDatabase0, String s, int v) {
        IdGeneratorKt.updatePreference(workDatabase0, s, v);
    }

    public static final void migrateLegacyIdGenerator(Context context0, SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "sqLiteDatabase");
        SharedPreferences sharedPreferences0 = context0.getSharedPreferences("androidx.work.util.id", 0);
        if(!sharedPreferences0.contains("next_job_scheduler_id") && !sharedPreferences0.contains("next_job_scheduler_id")) {
            return;
        }
        int v = sharedPreferences0.getInt("next_job_scheduler_id", 0);
        int v1 = sharedPreferences0.getInt("next_alarm_manager_id", 0);
        supportSQLiteDatabase0.beginTransaction();
        try {
            supportSQLiteDatabase0.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_job_scheduler_id", v});
            supportSQLiteDatabase0.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_alarm_manager_id", v1});
            sharedPreferences0.edit().clear().apply();
            supportSQLiteDatabase0.setTransactionSuccessful();
        }
        finally {
            supportSQLiteDatabase0.endTransaction();
        }
    }

    private static final int nextId(WorkDatabase workDatabase0, String s) {
        Long long0 = workDatabase0.preferenceDao().getLongValue(s);
        int v = 0;
        int v1 = long0 == null ? 0 : ((int)(((long)long0)));
        if(v1 != 0x7FFFFFFF) {
            v = v1 + 1;
        }
        IdGeneratorKt.updatePreference(workDatabase0, s, v);
        return v1;
    }

    private static final void updatePreference(WorkDatabase workDatabase0, String s, int v) {
        workDatabase0.preferenceDao().insertPreference(new Preference(s, ((long)v)));
    }
}

