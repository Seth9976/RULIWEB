package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public interface TintableBackgroundView {
    ColorStateList getSupportBackgroundTintList();

    PorterDuff.Mode getSupportBackgroundTintMode();

    void setSupportBackgroundTintList(ColorStateList arg1);

    void setSupportBackgroundTintMode(PorterDuff.Mode arg1);
}

