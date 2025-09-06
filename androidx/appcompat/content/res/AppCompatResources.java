package androidx.appcompat.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.ContextCompat;

public final class AppCompatResources {
    public static ColorStateList getColorStateList(Context context0, int v) {
        return ContextCompat.getColorStateList(context0, v);
    }

    public static Drawable getDrawable(Context context0, int v) {
        return ResourceManagerInternal.get().getDrawable(context0, v);
    }
}

