package androidx.core.os;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserManager;

public class UserManagerCompat {
    static class Api24Impl {
        static boolean isUserUnlocked(Context context0) {
            return ((UserManager)context0.getSystemService(UserManager.class)).isUserUnlocked();
        }
    }

    public static boolean isUserUnlocked(Context context0) {
        return Build.VERSION.SDK_INT < 24 ? true : Api24Impl.isUserUnlocked(context0);
    }
}

