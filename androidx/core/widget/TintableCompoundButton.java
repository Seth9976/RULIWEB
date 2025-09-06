package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public interface TintableCompoundButton {
    ColorStateList getSupportButtonTintList();

    PorterDuff.Mode getSupportButtonTintMode();

    void setSupportButtonTintList(ColorStateList arg1);

    void setSupportButtonTintMode(PorterDuff.Mode arg1);
}

