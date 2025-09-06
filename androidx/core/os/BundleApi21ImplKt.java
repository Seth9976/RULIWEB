package androidx.core.os;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\nH\u0007J\"\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\fH\u0007¨\u0006\r"}, d2 = {"Landroidx/core/os/BundleApi21ImplKt;", "", "()V", "putSize", "", "bundle", "Landroid/os/Bundle;", "key", "", "value", "Landroid/util/Size;", "putSizeF", "Landroid/util/SizeF;", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class BundleApi21ImplKt {
    public static final BundleApi21ImplKt INSTANCE;

    static {
        BundleApi21ImplKt.INSTANCE = new BundleApi21ImplKt();
    }

    @JvmStatic
    public static final void putSize(Bundle bundle0, String s, Size size0) {
        bundle0.putSize(s, size0);
    }

    @JvmStatic
    public static final void putSizeF(Bundle bundle0, String s, SizeF sizeF0) {
        bundle0.putSizeF(s, sizeF0);
    }
}

