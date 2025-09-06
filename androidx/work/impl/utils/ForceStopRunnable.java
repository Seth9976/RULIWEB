package androidx.work.impl.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationExitInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.core.os.UserManagerCompat;
import androidx.core.util.Consumer;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo.State;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabasePathHelper;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ForceStopRunnable implements Runnable {
    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String TAG;

        static {
            BroadcastReceiver.TAG = "WM-ForceStopRunnable$Rc";
        }

        @Override  // android.content.BroadcastReceiver
        public void onReceive(Context context0, Intent intent0) {
            if(intent0 != null && "ACTION_FORCE_STOP_RESCHEDULE".equals(intent0.getAction())) {
                Logger.get().verbose("WM-ForceStopRunnable$Rc", "Rescheduling alarm that keeps track of force-stops.");
                ForceStopRunnable.setAlarm(context0);
            }
        }
    }

    static final String ACTION_FORCE_STOP_RESCHEDULE = "ACTION_FORCE_STOP_RESCHEDULE";
    private static final int ALARM_ID = -1;
    private static final long BACKOFF_DURATION_MS = 300L;
    static final int MAX_ATTEMPTS = 3;
    private static final String TAG;
    private static final long TEN_YEARS;
    private final Context mContext;
    private final PreferenceUtils mPreferenceUtils;
    private int mRetryCount;
    private final WorkManagerImpl mWorkManager;

    static {
        ForceStopRunnable.TAG = "WM-ForceStopRunnable";
        ForceStopRunnable.TEN_YEARS = TimeUnit.DAYS.toMillis(3650L);
    }

    public ForceStopRunnable(Context context0, WorkManagerImpl workManagerImpl0) {
        this.mContext = context0.getApplicationContext();
        this.mWorkManager = workManagerImpl0;
        this.mPreferenceUtils = workManagerImpl0.getPreferenceUtils();
        this.mRetryCount = 0;
    }

    public boolean cleanUp() {
        boolean z = Build.VERSION.SDK_INT < 23 ? false : SystemJobScheduler.reconcileJobs(this.mContext, this.mWorkManager.getWorkDatabase());
        WorkDatabase workDatabase0 = this.mWorkManager.getWorkDatabase();
        WorkSpecDao workSpecDao0 = workDatabase0.workSpecDao();
        WorkProgressDao workProgressDao0 = workDatabase0.workProgressDao();
        workDatabase0.beginTransaction();
        try {
            List list0 = workSpecDao0.getRunningWork();
            boolean z1 = list0 != null && !list0.isEmpty();
            if(z1) {
                for(Object object0: list0) {
                    workSpecDao0.setState(State.ENQUEUED, ((WorkSpec)object0).id);
                    workSpecDao0.setStopReason(((WorkSpec)object0).id, 0xFFFFFE00);
                    workSpecDao0.markWorkSpecScheduled(((WorkSpec)object0).id, -1L);
                }
            }
            workProgressDao0.deleteAll();
            workDatabase0.setTransactionSuccessful();
            return z1 || z;
        }
        finally {
            workDatabase0.endTransaction();
        }
    }

    public void forceStopRunnable() {
        boolean z = this.cleanUp();
        if(this.shouldRescheduleWorkers()) {
            Logger.get().debug("WM-ForceStopRunnable", "Rescheduling Workers.");
            this.mWorkManager.rescheduleEligibleWork();
            this.mWorkManager.getPreferenceUtils().setNeedsReschedule(false);
            return;
        }
        if(this.isForceStopped()) {
            Logger.get().debug("WM-ForceStopRunnable", "Application was force-stopped, rescheduling.");
            this.mWorkManager.rescheduleEligibleWork();
            long v = this.mWorkManager.getConfiguration().getClock().currentTimeMillis();
            this.mPreferenceUtils.setLastForceStopEventMillis(v);
            return;
        }
        if(z) {
            Logger.get().debug("WM-ForceStopRunnable", "Found unfinished work, scheduling it.");
            Schedulers.schedule(this.mWorkManager.getConfiguration(), this.mWorkManager.getWorkDatabase(), this.mWorkManager.getSchedulers());
        }
    }

    static Intent getIntent(Context context0) {
        Intent intent0 = new Intent();
        intent0.setComponent(new ComponentName(context0, BroadcastReceiver.class));
        intent0.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent0;
    }

    private static PendingIntent getPendingIntent(Context context0, int v) {
        return PendingIntent.getBroadcast(context0, -1, ForceStopRunnable.getIntent(context0), v);
    }

    public boolean isForceStopped() {
        try {
            PendingIntent pendingIntent0 = ForceStopRunnable.getPendingIntent(this.mContext, (Build.VERSION.SDK_INT < 0x1F ? 0x20000000 : 0x22000000));
            if(Build.VERSION.SDK_INT < 30) {
                if(pendingIntent0 != null) {
                    return false;
                }
                ForceStopRunnable.setAlarm(this.mContext);
                return true;
            }
            if(pendingIntent0 != null) {
                pendingIntent0.cancel();
            }
            List list0 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(((ActivityManager)this.mContext.getSystemService("activity")), null, 0, 0);
            if(list0 == null || list0.isEmpty()) {
                return false;
            }
            long v = this.mPreferenceUtils.getLastForceStopEventMillis();
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                ApplicationExitInfo applicationExitInfo0 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(list0.get(v1));
                if(NetworkApi23..ExternalSyntheticApiModelOutline0.m(applicationExitInfo0) == 10 && NetworkApi23..ExternalSyntheticApiModelOutline0.m(applicationExitInfo0) >= v) {
                    return true;
                }
            }
            return false;
        }
        catch(SecurityException | IllegalArgumentException securityException0) {
            Logger.get().warning("WM-ForceStopRunnable", "Ignoring exception", securityException0);
            return true;
        }
    }

    public boolean multiProcessChecks() {
        Configuration configuration0 = this.mWorkManager.getConfiguration();
        if(TextUtils.isEmpty(configuration0.getDefaultProcessName())) {
            Logger.get().debug("WM-ForceStopRunnable", "The default process name was not specified.");
            return true;
        }
        boolean z = ProcessUtils.isDefaultProcess(this.mContext, configuration0);
        Logger.get().debug("WM-ForceStopRunnable", "Is default app process = " + z);
        return z;
    }

    @Override
    public void run() {
        try {
            if(!this.multiProcessChecks()) {
                return;
            }
            while(true) {
                try {
                    WorkDatabasePathHelper.migrateDatabase(this.mContext);
                }
                catch(SQLiteException sQLiteException0) {
                    Logger.get().error("WM-ForceStopRunnable", "Unexpected SQLite exception during migrations");
                    IllegalStateException illegalStateException0 = new IllegalStateException("Unexpected SQLite exception during migrations", sQLiteException0);
                    Consumer consumer0 = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();
                    if(consumer0 == null) {
                        throw illegalStateException0;
                    }
                    consumer0.accept(illegalStateException0);
                    break;
                }
                Logger.get().debug("WM-ForceStopRunnable", "Performing cleanup operations.");
                try {
                    this.forceStopRunnable();
                    break;
                }
                catch(SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteDiskIOException | SQLiteFullException | SQLiteTableLockedException sQLiteAccessPermException0) {
                    int v1 = this.mRetryCount + 1;
                    this.mRetryCount = v1;
                    if(v1 >= 3) {
                        String s = UserManagerCompat.isUserUnlocked(this.mContext) ? "The file system on the device is in a bad state. WorkManager cannot access the app\'s internal data store." : "WorkManager can\'t be accessed from direct boot, because credential encrypted storage isn\'t accessible.\nDon\'t access or initialise WorkManager from directAware components. See https://developer.android.com/training/articles/direct-boot";
                        Logger.get().error("WM-ForceStopRunnable", s, sQLiteAccessPermException0);
                        IllegalStateException illegalStateException1 = new IllegalStateException(s, sQLiteAccessPermException0);
                        Consumer consumer1 = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();
                        if(consumer1 == null) {
                            throw illegalStateException1;
                        }
                        Logger.get().debug("WM-ForceStopRunnable", "Routing exception to the specified exception handler", illegalStateException1);
                        consumer1.accept(illegalStateException1);
                        break;
                    }
                    Logger.get().debug("WM-ForceStopRunnable", "Retrying after " + ((long)v1) * 300L, sQLiteAccessPermException0);
                    this.sleep(((long)this.mRetryCount) * 300L);
                }
            }
        }
        finally {
            this.mWorkManager.onForceStopRunnableCompleted();
        }
    }

    static void setAlarm(Context context0) {
        AlarmManager alarmManager0 = (AlarmManager)context0.getSystemService("alarm");
        PendingIntent pendingIntent0 = ForceStopRunnable.getPendingIntent(context0, (Build.VERSION.SDK_INT < 0x1F ? 0x8000000 : 0xA000000));
        long v = System.currentTimeMillis() + ForceStopRunnable.TEN_YEARS;
        if(alarmManager0 != null) {
            alarmManager0.setExact(0, v, pendingIntent0);
        }
    }

    public boolean shouldRescheduleWorkers() {
        return this.mWorkManager.getPreferenceUtils().getNeedsReschedule();
    }

    public void sleep(long v) {
        try {
            Thread.sleep(v);
        }
        catch(InterruptedException unused_ex) {
        }
    }
}

