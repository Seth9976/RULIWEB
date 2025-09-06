package androidx.core.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.util.ObjectsCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker {
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionResult {
    }

    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_DENIED_APP_OP = -2;
    public static final int PERMISSION_GRANTED;

    public static int checkCallingOrSelfPermission(Context context0, String s) {
        return Binder.getCallingPid() == Process.myPid() ? PermissionChecker.checkPermission(context0, s, Binder.getCallingPid(), Binder.getCallingUid(), "com.ruliweb.www.ruliapp") : PermissionChecker.checkPermission(context0, s, Binder.getCallingPid(), Binder.getCallingUid(), null);
    }

    public static int checkCallingPermission(Context context0, String s, String s1) {
        return Binder.getCallingPid() == Process.myPid() ? -1 : PermissionChecker.checkPermission(context0, s, Binder.getCallingPid(), Binder.getCallingUid(), s1);
    }

    public static int checkPermission(Context context0, String s, int v, int v1, String s1) {
        if(context0.checkPermission(s, v, v1) == -1) {
            return -1;
        }
        String s2 = AppOpsManagerCompat.permissionToOp(s);
        if(s2 == null) {
            return 0;
        }
        if(s1 == null) {
            String[] arr_s = context0.getPackageManager().getPackagesForUid(v1);
            if(arr_s != null && arr_s.length > 0) {
                s1 = arr_s[0];
                return (Process.myUid() != v1 || !ObjectsCompat.equals("com.ruliweb.www.ruliapp", s1) ? AppOpsManagerCompat.noteProxyOpNoThrow(context0, s2, s1) : AppOpsManagerCompat.checkOrNoteProxyOp(context0, v1, s2, s1)) == 0 ? 0 : -2;
            }
            return -1;
        }
        return (Process.myUid() != v1 || !ObjectsCompat.equals("com.ruliweb.www.ruliapp", s1) ? AppOpsManagerCompat.noteProxyOpNoThrow(context0, s2, s1) : AppOpsManagerCompat.checkOrNoteProxyOp(context0, v1, s2, s1)) == 0 ? 0 : -2;
    }

    public static int checkSelfPermission(Context context0, String s) {
        return PermissionChecker.checkPermission(context0, s, Process.myPid(), Process.myUid(), "com.ruliweb.www.ruliapp");
    }
}

