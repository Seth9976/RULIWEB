package androidx.core.util;

import android.util.Half;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\n\n\u0002\u0010\u000E\n\u0000\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0003H\u0087\b\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0004H\u0087\b\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, d2 = {"toHalf", "Landroid/util/Half;", "", "", "", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class HalfKt {
    public static final Half toHalf(double f) {
        return Half.valueOf(((float)f));
    }

    public static final Half toHalf(float f) {
        return Half.valueOf(f);
    }

    public static final Half toHalf(String s) {
        return Half.valueOf(s);
    }

    public static final Half toHalf(short v) {
        return Half.valueOf(v);
    }
}

