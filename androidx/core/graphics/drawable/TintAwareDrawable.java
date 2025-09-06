package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public interface TintAwareDrawable {
    void setTint(int arg1);

    void setTintList(ColorStateList arg1);

    void setTintMode(PorterDuff.Mode arg1);
}

