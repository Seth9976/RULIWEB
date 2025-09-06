package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class CardViewApi17Impl extends CardViewBaseImpl {
    @Override  // androidx.cardview.widget.CardViewBaseImpl
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectHelper() {
            @Override  // androidx.cardview.widget.RoundRectDrawableWithShadow$RoundRectHelper
            public void drawRoundRect(Canvas canvas0, RectF rectF0, float f, Paint paint0) {
                canvas0.drawRoundRect(rectF0, f, f, paint0);
            }
        };
    }
}

