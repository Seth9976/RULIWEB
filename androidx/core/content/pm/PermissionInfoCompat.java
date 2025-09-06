package androidx.core.content.pm;

import android.content.pm.PermissionInfo;
import android.os.Build.VERSION;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionInfoCompat {
    static class Api28Impl {
        static int getProtection(PermissionInfo permissionInfo0) {
            return permissionInfo0.getProtection();
        }

        static int getProtectionFlags(PermissionInfo permissionInfo0) {
            return permissionInfo0.getProtectionFlags();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Protection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProtectionFlags {
    }

    public static int getProtection(PermissionInfo permissionInfo0) {
        return Build.VERSION.SDK_INT < 28 ? permissionInfo0.protectionLevel & 15 : Api28Impl.getProtection(permissionInfo0);
    }

    public static int getProtectionFlags(PermissionInfo permissionInfo0) {
        return Build.VERSION.SDK_INT < 28 ? permissionInfo0.protectionLevel & -16 : Api28Impl.getProtectionFlags(permissionInfo0);
    }
}

