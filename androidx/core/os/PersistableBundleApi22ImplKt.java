package androidx.core.os;

import android.os.PersistableBundle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0018\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\u0006\u0010\t\u001A\u00020\nH\u0007J\"\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\u0006\u0010\t\u001A\u00020\fH\u0007¨\u0006\r"}, d2 = {"Landroidx/core/os/PersistableBundleApi22ImplKt;", "", "()V", "putBoolean", "", "persistableBundle", "Landroid/os/PersistableBundle;", "key", "", "value", "", "putBooleanArray", "", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class PersistableBundleApi22ImplKt {
    public static final PersistableBundleApi22ImplKt INSTANCE;

    static {
        PersistableBundleApi22ImplKt.INSTANCE = new PersistableBundleApi22ImplKt();
    }

    @JvmStatic
    public static final void putBoolean(PersistableBundle persistableBundle0, String s, boolean z) {
        persistableBundle0.putBoolean(s, z);
    }

    @JvmStatic
    public static final void putBooleanArray(PersistableBundle persistableBundle0, String s, boolean[] arr_z) {
        persistableBundle0.putBooleanArray(s, arr_z);
    }
}

