package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public interface TintableCheckedTextView {
    ColorStateList getSupportCheckMarkTintList();

    PorterDuff.Mode getSupportCheckMarkTintMode();

    void setSupportCheckMarkTintList(ColorStateList arg1);

    void setSupportCheckMarkTintMode(PorterDuff.Mode arg1);
}

