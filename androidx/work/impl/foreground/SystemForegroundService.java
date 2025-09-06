package androidx.work.impl.foreground;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;

public class SystemForegroundService extends LifecycleService implements Callback {
    static class Api29Impl {
        static void startForeground(Service service0, int v, Notification notification0, int v1) {
            service0.startForeground(v, notification0, v1);
        }
    }

    static class Api31Impl {
        static void startForeground(Service service0, int v, Notification notification0, int v1) {
            try {
                service0.startForeground(v, notification0, v1);
            }
            catch(ForegroundServiceStartNotAllowedException foregroundServiceStartNotAllowedException0) {
                Logger.get().warning("WM-SystemFgService", "Unable to start foreground service", foregroundServiceStartNotAllowedException0);
            }
            catch(SecurityException securityException0) {
                Logger.get().warning("WM-SystemFgService", "Unable to start foreground service", securityException0);
            }
        }
    }

    private static final String TAG;
    SystemForegroundDispatcher mDispatcher;
    private boolean mIsShutdown;
    NotificationManager mNotificationManager;
    private static SystemForegroundService sForegroundService;

    static {
        SystemForegroundService.TAG = "WM-SystemFgService";
        SystemForegroundService.sForegroundService = null;
    }

    // 去混淆评级： 低(20)
    static String access$000() [...] // 潜在的解密器

    @Override  // androidx.work.impl.foreground.SystemForegroundDispatcher$Callback
    public void cancelNotification(int v) {
        this.mNotificationManager.cancel(v);
    }

    public static SystemForegroundService getInstance() {
        return SystemForegroundService.sForegroundService;
    }

    private void initializeDispatcher() {
        this.mNotificationManager = (NotificationManager)this.getApplicationContext().getSystemService("notification");
        SystemForegroundDispatcher systemForegroundDispatcher0 = new SystemForegroundDispatcher(this.getApplicationContext());
        this.mDispatcher = systemForegroundDispatcher0;
        systemForegroundDispatcher0.setCallback(this);
    }

    @Override  // androidx.work.impl.foreground.SystemForegroundDispatcher$Callback
    public void notify(int v, Notification notification0) {
        this.mNotificationManager.notify(v, notification0);
    }

    @Override  // androidx.lifecycle.LifecycleService
    public void onCreate() {
        super.onCreate();
        SystemForegroundService.sForegroundService = this;
        this.initializeDispatcher();
    }

    @Override  // androidx.lifecycle.LifecycleService
    public void onDestroy() {
        super.onDestroy();
        this.mDispatcher.onDestroy();
    }

    @Override  // androidx.lifecycle.LifecycleService
    public int onStartCommand(Intent intent0, int v, int v1) {
        super.onStartCommand(intent0, v, v1);
        if(this.mIsShutdown) {
            Logger.get().info("WM-SystemFgService", "Re-initializing SystemForegroundService after a request to shut-down.");
            this.mDispatcher.onDestroy();
            this.initializeDispatcher();
            this.mIsShutdown = false;
        }
        if(intent0 != null) {
            this.mDispatcher.onStartCommand(intent0);
        }
        return 3;
    }

    @Override  // android.app.Service
    public void onTimeout(int v) {
        if(Build.VERSION.SDK_INT >= 35) {
            return;
        }
        this.mDispatcher.onTimeout(v, 0x800);
    }

    @Override  // android.app.Service
    public void onTimeout(int v, int v1) {
        this.mDispatcher.onTimeout(v, v1);
    }

    @Override  // androidx.work.impl.foreground.SystemForegroundDispatcher$Callback
    public void startForeground(int v, int v1, Notification notification0) {
        if(Build.VERSION.SDK_INT >= 0x1F) {
            Api31Impl.startForeground(this, v, notification0, v1);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.startForeground(this, v, notification0, v1);
            return;
        }
        this.startForeground(v, notification0);
    }

    @Override  // androidx.work.impl.foreground.SystemForegroundDispatcher$Callback
    public void stop() {
        this.mIsShutdown = true;
        Logger.get().debug("WM-SystemFgService", "Shutting down.");
        if(Build.VERSION.SDK_INT >= 26) {
            this.stopForeground(true);
        }
        SystemForegroundService.sForegroundService = null;
        this.stopSelf();
    }
}

