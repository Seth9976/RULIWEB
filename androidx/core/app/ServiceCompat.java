package androidx.core.app;

import android.app.Notification;
import android.app.Service;
import android.os.Build.VERSION;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ServiceCompat {
    static class Api24Impl {
        static void stopForeground(Service service0, int v) {
            service0.stopForeground(v);
        }
    }

    static class Api29Impl {
        static void startForeground(Service service0, int v, Notification notification0, int v1) {
            if(v1 != -1 && v1 != 0) {
                service0.startForeground(v, notification0, v1 & 0xFF);
                return;
            }
            service0.startForeground(v, notification0, v1);
        }
    }

    static class Api34Impl {
        static void startForeground(Service service0, int v, Notification notification0, int v1) {
            if(v1 != -1 && v1 != 0) {
                service0.startForeground(v, notification0, v1 & 0x40000FFF);
                return;
            }
            service0.startForeground(v, notification0, v1);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StopForegroundFlags {
    }

    private static final int FOREGROUND_SERVICE_TYPE_ALLOWED_SINCE_Q = 0xFF;
    private static final int FOREGROUND_SERVICE_TYPE_ALLOWED_SINCE_U = 0x40000FFF;
    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;

    public static void startForeground(Service service0, int v, Notification notification0, int v1) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.startForeground(service0, v, notification0, v1);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.startForeground(service0, v, notification0, v1);
            return;
        }
        service0.startForeground(v, notification0);
    }

    public static void stopForeground(Service service0, int v) {
        boolean z = true;
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.stopForeground(service0, v);
            return;
        }
        if((v & 1) == 0) {
            z = false;
        }
        service0.stopForeground(z);
    }
}

