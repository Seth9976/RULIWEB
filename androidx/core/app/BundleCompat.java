package androidx.core.app;

import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.ReplaceWith;

@Deprecated
public final class BundleCompat {
    @ReplaceWith(expression = "bundle.getBinder(key)")
    @Deprecated
    public static IBinder getBinder(Bundle bundle0, String s) {
        return bundle0.getBinder(s);
    }

    @ReplaceWith(expression = "bundle.putBinder(key, binder)")
    @Deprecated
    public static void putBinder(Bundle bundle0, String s, IBinder iBinder0) {
        bundle0.putBinder(s, iBinder0);
    }
}

