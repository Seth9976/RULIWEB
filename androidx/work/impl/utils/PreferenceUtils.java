package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;

public class PreferenceUtils {
    public static final String CREATE_PREFERENCE = "CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))";
    public static final String INSERT_PREFERENCE = "INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)";
    public static final String KEY_LAST_CANCEL_ALL_TIME_MS = "last_cancel_all_time_ms";
    private static final String KEY_LAST_FORCE_STOP_MS = "last_force_stop_ms";
    public static final String KEY_RESCHEDULE_NEEDED = "reschedule_needed";
    public static final String PREFERENCES_FILE_NAME = "androidx.work.util.preferences";
    private final WorkDatabase mWorkDatabase;

    public PreferenceUtils(WorkDatabase workDatabase0) {
        this.mWorkDatabase = workDatabase0;
    }

    public long getLastCancelAllTimeMillis() {
        Long long0 = this.mWorkDatabase.preferenceDao().getLongValue("last_cancel_all_time_ms");
        return long0 == null ? 0L : ((long)long0);
    }

    public LiveData getLastCancelAllTimeMillisLiveData() {
        return Transformations.map(this.mWorkDatabase.preferenceDao().getObservableLongValue("last_cancel_all_time_ms"), (Long long0) -> (long0 == null ? 0L : ((long)long0)));
    }

    public long getLastForceStopEventMillis() {
        Long long0 = this.mWorkDatabase.preferenceDao().getLongValue("last_force_stop_ms");
        return long0 == null ? 0L : ((long)long0);
    }

    public boolean getNeedsReschedule() {
        Long long0 = this.mWorkDatabase.preferenceDao().getLongValue("reschedule_needed");
        return long0 != null && ((long)long0) == 1L;
    }

    // 检测为 Lambda 实现
    static Long lambda$getLastCancelAllTimeMillisLiveData$0(Long long0) [...]

    public static void migrateLegacyPreferences(Context context0, SupportSQLiteDatabase supportSQLiteDatabase0) {
        SharedPreferences sharedPreferences0 = context0.getSharedPreferences("androidx.work.util.preferences", 0);
        if(!sharedPreferences0.contains("reschedule_needed") && !sharedPreferences0.contains("last_cancel_all_time_ms")) {
            return;
        }
        long v = 0L;
        long v1 = sharedPreferences0.getLong("last_cancel_all_time_ms", 0L);
        if(sharedPreferences0.getBoolean("reschedule_needed", false)) {
            v = 1L;
        }
        supportSQLiteDatabase0.beginTransaction();
        try {
            supportSQLiteDatabase0.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"last_cancel_all_time_ms", v1});
            supportSQLiteDatabase0.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", v});
            sharedPreferences0.edit().clear().apply();
            supportSQLiteDatabase0.setTransactionSuccessful();
        }
        finally {
            supportSQLiteDatabase0.endTransaction();
        }
    }

    public void setLastCancelAllTimeMillis(long v) {
        Preference preference0 = new Preference("last_cancel_all_time_ms", v);
        this.mWorkDatabase.preferenceDao().insertPreference(preference0);
    }

    public void setLastForceStopEventMillis(long v) {
        Preference preference0 = new Preference("last_force_stop_ms", v);
        this.mWorkDatabase.preferenceDao().insertPreference(preference0);
    }

    public void setNeedsReschedule(boolean z) {
        Preference preference0 = new Preference("reschedule_needed", z);
        this.mWorkDatabase.preferenceDao().insertPreference(preference0);
    }
}

