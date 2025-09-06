package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

class CardViewApi21Impl implements CardViewImpl {
    @Override  // androidx.cardview.widget.CardViewImpl
    public ColorStateList getBackgroundColor(CardViewDelegate cardViewDelegate0) {
        return this.getCardBackground(cardViewDelegate0).getColor();
    }

    private RoundRectDrawable getCardBackground(CardViewDelegate cardViewDelegate0) {
        return (RoundRectDrawable)cardViewDelegate0.getCardBackground();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getElevation(CardViewDelegate cardViewDelegate0) {
        return cardViewDelegate0.getCardView().getElevation();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getMaxElevation(CardViewDelegate cardViewDelegate0) {
        return this.getCardBackground(cardViewDelegate0).getPadding();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getMinHeight(CardViewDelegate cardViewDelegate0) {
        return this.getRadius(cardViewDelegate0) * 2.0f;
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getMinWidth(CardViewDelegate cardViewDelegate0) {
        return this.getRadius(cardViewDelegate0) * 2.0f;
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public float getRadius(CardViewDelegate cardViewDelegate0) {
        return this.getCardBackground(cardViewDelegate0).getRadius();
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void initStatic() {
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void initialize(CardViewDelegate cardViewDelegate0, Context context0, ColorStateList colorStateList0, float f, float f1, float f2) {
        cardViewDelegate0.setCardBackground(new RoundRectDrawable(colorStateList0, f));
        View view0 = cardViewDelegate0.getCardView();
        view0.setClipToOutline(true);
        view0.setElevation(f1);
        this.setMaxElevation(cardViewDelegate0, f2);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate0) {
        this.setMaxElevation(cardViewDelegate0, this.getMaxElevation(cardViewDelegate0));
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate0) {
        this.setMaxElevation(cardViewDelegate0, this.getMaxElevation(cardViewDelegate0));
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setBackgroundColor(CardViewDelegate cardViewDelegate0, ColorStateList colorStateList0) {
        this.getCardBackground(cardViewDelegate0).setColor(colorStateList0);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setElevation(CardViewDelegate cardViewDelegate0, float f) {
        cardViewDelegate0.getCardView().setElevation(f);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setMaxElevation(CardViewDelegate cardViewDelegate0, float f) {
        this.getCardBackground(cardViewDelegate0).setPadding(f, cardViewDelegate0.getUseCompatPadding(), cardViewDelegate0.getPreventCornerOverlap());
        this.updatePadding(cardViewDelegate0);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void setRadius(CardViewDelegate cardViewDelegate0, float f) {
        this.getCardBackground(cardViewDelegate0).setRadius(f);
    }

    @Override  // androidx.cardview.widget.CardViewImpl
    public void updatePadding(CardViewDelegate cardViewDelegate0) {
        if(!cardViewDelegate0.getUseCompatPadding()) {
            cardViewDelegate0.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float f = this.getMaxElevation(cardViewDelegate0);
        float f1 = this.getRadius(cardViewDelegate0);
        int v = (int)Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(f, f1, cardViewDelegate0.getPreventCornerOverlap()));
        int v1 = (int)Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(f, f1, cardViewDelegate0.getPreventCornerOverlap()));
        cardViewDelegate0.setShadowPadding(v, v1, v, v1);
    }
}

