package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build.VERSION;

public final class AppOpsManagerCompat {
    static class Api23Impl {
        static Object getSystemService(Context context0, Class class0) {
            return context0.getSystemService(class0);
        }

        static int noteProxyOp(AppOpsManager appOpsManager0, String s, String s1) {
            return appOpsManager0.noteProxyOp(s, s1);
        }

        static int noteProxyOpNoThrow(AppOpsManager appOpsManager0, String s, String s1) {
            return appOpsManager0.noteProxyOpNoThrow(s, s1);
        }

        static String permissionToOp(String s) {
            return AppOpsManager.permissionToOp(s);
        }
    }

    static class Api29Impl {
        static int checkOpNoThrow(AppOpsManager appOpsManager0, String s, int v, String s1) {
            return appOpsManager0 == null ? 1 : appOpsManager0.checkOpNoThrow(s, v, s1);
        }

        static String getOpPackageName(Context context0) {
            return context0.getOpPackageName();
        }

        static AppOpsManager getSystemService(Context context0) {
            return (AppOpsManager)context0.getSystemService(AppOpsManager.class);
        }
    }

    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    public static int checkOrNoteProxyOp(Context context0, int v, String s, String s1) {
        if(Build.VERSION.SDK_INT >= 29) {
            AppOpsManager appOpsManager0 = Api29Impl.getSystemService(context0);
            int v1 = Api29Impl.checkOpNoThrow(appOpsManager0, s, Binder.getCallingUid(), s1);
            return v1 == 0 ? Api29Impl.checkOpNoThrow(appOpsManager0, s, v, Api29Impl.getOpPackageName(context0)) : v1;
        }
        return AppOpsManagerCompat.noteProxyOpNoThrow(context0, s, s1);
    }

    public static int noteOp(Context context0, String s, int v, String s1) {
        return ((AppOpsManager)context0.getSystemService("appops")).noteOp(s, v, s1);
    }

    public static int noteOpNoThrow(Context context0, String s, int v, String s1) {
        return ((AppOpsManager)context0.getSystemService("appops")).noteOpNoThrow(s, v, s1);
    }

    public static int noteProxyOp(Context context0, String s, String s1) {
        return Build.VERSION.SDK_INT < 23 ? 1 : Api23Impl.noteProxyOp(((AppOpsManager)Api23Impl.getSystemService(context0, AppOpsManager.class)), s, s1);
    }

    public static int noteProxyOpNoThrow(Context context0, String s, String s1) {
        return Build.VERSION.SDK_INT < 23 ? 1 : Api23Impl.noteProxyOpNoThrow(((AppOpsManager)Api23Impl.getSystemService(context0, AppOpsManager.class)), s, s1);
    }

    public static String permissionToOp(String s) {
        return Build.VERSION.SDK_INT < 23 ? null : Api23Impl.permissionToOp(s);
    }
}

