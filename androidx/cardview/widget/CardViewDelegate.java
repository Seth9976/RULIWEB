package androidx.cardview.widget;

import android.graphics.drawable.Drawable;
import android.view.View;

interface CardViewDelegate {
    Drawable getCardBackground();

    View getCardView();

    boolean getPreventCornerOverlap();

    boolean getUseCompatPadding();

    void setCardBackground(Drawable arg1);

    void setMinWidthHeightInternal(int arg1, int arg2);

    void setShadowPadding(int arg1, int arg2, int arg3, int arg4);
}

