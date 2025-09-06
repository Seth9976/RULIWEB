package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public interface TintableCompoundDrawablesView {
    ColorStateList getSupportCompoundDrawablesTintList();

    PorterDuff.Mode getSupportCompoundDrawablesTintMode();

    void setSupportCompoundDrawablesTintList(ColorStateList arg1);

    void setSupportCompoundDrawablesTintMode(PorterDuff.Mode arg1);
}

