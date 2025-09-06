package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

class CardViewBaseImpl implements CardViewImpl {
    final RectF mCornerRect;

    CardViewBaseImpl() {
        this.mCornerRect = new RectF();
    }

    private RoundRectDrawableWithShadow createBackground(Context context0, ColorStateList colorStateList0, float f, float f1, float f2) {
        return new RoundRectDrawableWithShadow(context0.getResources(), colorStateList0, f, f1, f2);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public ColorStateList getBackgroundColor(CardViewDelegate cardViewDelegate0) {
        return this.getShadowBackground(cardViewDelegate0).getColor();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getElevation(CardViewDelegate cardViewDelegate0) {
        return this.getShadowBackground(cardViewDelegate0).getShadowSize();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getMaxElevation(CardViewDelegate cardViewDelegate0) {
        return this.getShadowBackground(cardViewDelegate0).getMaxShadowSize();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getMinHeight(CardViewDelegate cardViewDelegate0) {
        return this.getShadowBackground(cardViewDelegate0).getMinHeight();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getMinWidth(CardViewDelegate cardViewDelegate0) {
        return this.getShadowBackground(cardViewDelegate0).getMinWidth();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getRadius(CardViewDelegate cardViewDelegate0) {
        return this.getShadowBackground(cardViewDelegate0).getCornerRadius();
    }

    private RoundRectDrawableWithShadow getShadowBackground(CardViewDelegate cardViewDelegate0) {
        return (RoundRectDrawableWithShadow)cardViewDelegate0.getCardBackground();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectHelper() {
            @Override  // androidx.cardview.widget.RoundRectDrawableWithShadow$RoundRectHelper
            public void drawRoundRect(Canvas canvas0, RectF rectF0, float f, Paint paint0) {
                float f1 = rectF0.width() - 2.0f * f - 1.0f;
                float f2 = rectF0.height();
                if(f >= 1.0f) {
                    float f3 = -(f + 0.5f);
                    CardViewBaseImpl.this.mCornerRect.set(f3, f3, f + 0.5f, f + 0.5f);
                    int v = canvas0.save();
                    canvas0.translate(rectF0.left + (f + 0.5f), rectF0.top + (f + 0.5f));
                    canvas0.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint0);
                    canvas0.translate(f1, 0.0f);
                    canvas0.rotate(90.0f);
                    canvas0.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint0);
                    canvas0.translate(f2 - 2.0f * f - 1.0f, 0.0f);
                    canvas0.rotate(90.0f);
                    canvas0.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint0);
                    canvas0.translate(f1, 0.0f);
                    canvas0.rotate(90.0f);
                    canvas0.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint0);
                    canvas0.restoreToCount(v);
                    canvas0.drawRect(rectF0.left + (f + 0.5f) - 1.0f, rectF0.top, rectF0.right - (f + 0.5f) + 1.0f, rectF0.top + (f + 0.5f), paint0);
                    canvas0.drawRect(rectF0.left + (f + 0.5f) - 1.0f, rectF0.bottom - (f + 0.5f), rectF0.right - (f + 0.5f) + 1.0f, rectF0.bottom, paint0);
                }
                canvas0.drawRect(rectF0.left, rectF0.top + f, rectF0.right, rectF0.bottom - f, paint0);
            }
        };
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void initialize(CardViewDelegate cardViewDelegate0, Context context0, ColorStateList colorStateList0, float f, float f1, float f2) {
        RoundRectDrawableWithShadow roundRectDrawableWithShadow0 = this.createBackground(context0, colorStateList0, f, f1, f2);
        roundRectDrawableWithShadow0.setAddPaddingForCorners(cardViewDelegate0.getPreventCornerOverlap());
        cardViewDelegate0.setCardBackground(roundRectDrawableWithShadow0);
        this.updatePadding(cardViewDelegate0);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate0) {
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate0) {
        this.getShadowBackground(cardViewDelegate0).setAddPaddingForCorners(cardViewDelegate0.getPreventCornerOverlap());
        this.updatePadding(cardViewDelegate0);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setBackgroundColor(CardViewDelegate cardViewDelegate0, ColorStateList colorStateList0) {
        this.getShadowBackground(cardViewDelegate0).setColor(colorStateList0);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setElevation(CardViewDelegate cardViewDelegate0, float f) {
        this.getShadowBackground(cardViewDelegate0).setShadowSize(f);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setMaxElevation(CardViewDelegate cardViewDelegate0, float f) {
        this.getShadowBackground(cardViewDelegate0).setMaxShadowSize(f);
        this.updatePadding(cardViewDelegate0);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setRadius(CardViewDelegate cardViewDelegate0, float f) {
        this.getShadowBackground(cardViewDelegate0).setCornerRadius(f);
        this.updatePadding(cardViewDelegate0);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void updatePadding(CardViewDelegate cardViewDelegate0) {
        Rect rect0 = new Rect();
        this.getShadowBackground(cardViewDelegate0).getMaxShadowAndCornerPadding(rect0);
        cardViewDelegate0.setMinWidthHeightInternal(((int)Math.ceil(this.getMinWidth(cardViewDelegate0))), ((int)Math.ceil(this.getMinHeight(cardViewDelegate0))));
        cardViewDelegate0.setShadowPadding(rect0.left, rect0.top, rect0.right, rect0.bottom);
    }
}

