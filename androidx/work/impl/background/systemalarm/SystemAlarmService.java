package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.utils.WakeLocks;

public class SystemAlarmService extends LifecycleService implements CommandsCompletedListener {
    private static final String TAG;
    private SystemAlarmDispatcher mDispatcher;
    private boolean mIsShutdown;

    static {
        SystemAlarmService.TAG = "WM-SystemAlarmService";
    }

    private void initializeDispatcher() {
        SystemAlarmDispatcher systemAlarmDispatcher0 = new SystemAlarmDispatcher(this);
        this.mDispatcher = systemAlarmDispatcher0;
        systemAlarmDispatcher0.setCompletedListener(this);
    }

    @Override  // androidx.work.impl.background.systemalarm.SystemAlarmDispatcher$CommandsCompletedListener
    public void onAllCommandsCompleted() {
        this.mIsShutdown = true;
        Logger.get().debug("WM-SystemAlarmService", "All commands completed in dispatcher");
        WakeLocks.checkWakeLocks();
        this.stopSelf();
    }

    @Override  // androidx.lifecycle.LifecycleService
    public void onCreate() {
        super.onCreate();
        this.initializeDispatcher();
        this.mIsShutdown = false;
    }

    @Override  // androidx.lifecycle.LifecycleService
    public void onDestroy() {
        super.onDestroy();
        this.mIsShutdown = true;
        this.mDispatcher.onDestroy();
    }

    @Override  // androidx.lifecycle.LifecycleService
    public int onStartCommand(Intent intent0, int v, int v1) {
        super.onStartCommand(intent0, v, v1);
        if(this.mIsShutdown) {
            Logger.get().info("WM-SystemAlarmService", "Re-initializing SystemAlarmDispatcher after a request to shut-down.");
            this.mDispatcher.onDestroy();
            this.initializeDispatcher();
            this.mIsShutdown = false;
        }
        if(intent0 != null) {
            this.mDispatcher.add(intent0, v1);
        }
        return 3;
    }
}

