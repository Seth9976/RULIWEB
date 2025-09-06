package androidx.core.graphics.drawable;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0003H\u0086\b¨\u0006\u0004"}, d2 = {"toDrawable", "Landroid/graphics/drawable/ColorDrawable;", "Landroid/graphics/Color;", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ColorDrawableKt {
    public static final ColorDrawable toDrawable(int v) {
        return new ColorDrawable(v);
    }

    public static final ColorDrawable toDrawable(Color color0) {
        return new ColorDrawable(color0.toArgb());
    }
}

