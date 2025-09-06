package androidx.core.graphics;

import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0086\b\u001A\r\u0010\u0005\u001A\u00020\u0006*\u00020\u0002H\u0086\b¨\u0006\u0007"}, d2 = {"toColorFilter", "Landroid/graphics/PorterDuffColorFilter;", "Landroid/graphics/PorterDuff$Mode;", "color", "", "toXfermode", "Landroid/graphics/PorterDuffXfermode;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class PorterDuffKt {
    public static final PorterDuffColorFilter toColorFilter(PorterDuff.Mode porterDuff$Mode0, int v) {
        return new PorterDuffColorFilter(v, porterDuff$Mode0);
    }

    public static final PorterDuffXfermode toXfermode(PorterDuff.Mode porterDuff$Mode0) {
        return new PorterDuffXfermode(porterDuff$Mode0);
    }
}

