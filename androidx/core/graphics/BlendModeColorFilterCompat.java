package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.os.Build.VERSION;

public class BlendModeColorFilterCompat {
    static class Api29Impl {
        static ColorFilter createBlendModeColorFilter(int v, Object object0) {
            return new BlendModeColorFilter(v, ((BlendMode)object0));
        }
    }

    public static ColorFilter createBlendModeColorFilterCompat(int v, BlendModeCompat blendModeCompat0) {
        if(Build.VERSION.SDK_INT >= 29) {
            Object object0 = androidx.core.graphics.BlendModeUtils.Api29Impl.obtainBlendModeFromCompat(blendModeCompat0);
            return object0 == null ? null : Api29Impl.createBlendModeColorFilter(v, object0);
        }
        PorterDuff.Mode porterDuff$Mode0 = BlendModeUtils.obtainPorterDuffFromCompat(blendModeCompat0);
        return porterDuff$Mode0 != null ? new PorterDuffColorFilter(v, porterDuff$Mode0) : null;
    }
}

