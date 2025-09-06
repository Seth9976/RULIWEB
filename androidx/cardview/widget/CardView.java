package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import androidx.cardview.R.attr;
import androidx.cardview.R.color;
import androidx.cardview.R.style;
import androidx.cardview.R.styleable;

public class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR;
    private static final CardViewImpl IMPL;
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    static {
        CardView.COLOR_BACKGROUND_ATTR = new int[]{0x1010031};
        CardViewApi21Impl cardViewApi21Impl0 = new CardViewApi21Impl();
        CardView.IMPL = cardViewApi21Impl0;
        cardViewApi21Impl0.initStatic();
    }

    public CardView(Context context0) {
        this(context0, null);
    }

    public CardView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.cardViewStyle);
    }

    public CardView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        ColorStateList colorStateList0;
        Rect rect0 = new Rect();
        this.mContentPadding = rect0;
        this.mShadowBounds = new Rect();
        androidx.cardview.widget.CardView.1 cardView$10 = new CardViewDelegate() {
            private Drawable mCardBackground;

            @Override  // androidx.cardview.widget.CardViewDelegate
            public Drawable getCardBackground() {
                return this.mCardBackground;
            }

            @Override  // androidx.cardview.widget.CardViewDelegate
            public View getCardView() {
                return CardView.this;
            }

            @Override  // androidx.cardview.widget.CardViewDelegate
            public boolean getPreventCornerOverlap() {
                return CardView.this.getPreventCornerOverlap();
            }

            @Override  // androidx.cardview.widget.CardViewDelegate
            public boolean getUseCompatPadding() {
                return CardView.this.getUseCompatPadding();
            }

            @Override  // androidx.cardview.widget.CardViewDelegate
            public void setCardBackground(Drawable drawable0) {
                this.mCardBackground = drawable0;
                CardView.this.setBackgroundDrawable(drawable0);
            }

            @Override  // androidx.cardview.widget.CardViewDelegate
            public void setMinWidthHeightInternal(int v, int v1) {
                if(v > CardView.this.mUserSetMinWidth) {
                    CardView.this.super.setMinimumWidth(v);
                }
                if(v1 > CardView.this.mUserSetMinHeight) {
                    CardView.this.super.setMinimumHeight(v1);
                }
            }

            @Override  // androidx.cardview.widget.CardViewDelegate
            public void setShadowPadding(int v, int v1, int v2, int v3) {
                CardView.this.mShadowBounds.set(v, v1, v2, v3);
                CardView.this.super.setPadding(v + CardView.this.mContentPadding.left, v1 + CardView.this.mContentPadding.top, v2 + CardView.this.mContentPadding.right, v3 + CardView.this.mContentPadding.bottom);
            }
        };
        this.mCardViewDelegate = cardView$10;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.CardView, v, style.CardView);
        if(typedArray0.hasValue(styleable.CardView_cardBackgroundColor)) {
            colorStateList0 = typedArray0.getColorStateList(styleable.CardView_cardBackgroundColor);
        }
        else {
            TypedArray typedArray1 = this.getContext().obtainStyledAttributes(CardView.COLOR_BACKGROUND_ATTR);
            int v1 = typedArray1.getColor(0, 0);
            typedArray1.recycle();
            float[] arr_f = new float[3];
            Color.colorToHSV(v1, arr_f);
            colorStateList0 = ColorStateList.valueOf((arr_f[2] > 0.5f ? this.getResources().getColor(color.cardview_light_background) : this.getResources().getColor(color.cardview_dark_background)));
        }
        float f = typedArray0.getDimension(styleable.CardView_cardCornerRadius, 0.0f);
        float f1 = typedArray0.getDimension(styleable.CardView_cardElevation, 0.0f);
        float f2 = typedArray0.getDimension(styleable.CardView_cardMaxElevation, 0.0f);
        this.mCompatPadding = typedArray0.getBoolean(styleable.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = typedArray0.getBoolean(styleable.CardView_cardPreventCornerOverlap, true);
        int v2 = typedArray0.getDimensionPixelSize(styleable.CardView_contentPadding, 0);
        rect0.left = typedArray0.getDimensionPixelSize(styleable.CardView_contentPaddingLeft, v2);
        rect0.top = typedArray0.getDimensionPixelSize(styleable.CardView_contentPaddingTop, v2);
        rect0.right = typedArray0.getDimensionPixelSize(styleable.CardView_contentPaddingRight, v2);
        rect0.bottom = typedArray0.getDimensionPixelSize(styleable.CardView_contentPaddingBottom, v2);
        this.mUserSetMinWidth = typedArray0.getDimensionPixelSize(styleable.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = typedArray0.getDimensionPixelSize(styleable.CardView_android_minHeight, 0);
        typedArray0.recycle();
        CardView.IMPL.initialize(cardView$10, context0, colorStateList0, f, f1, (f1 > f2 ? f1 : f2));
    }

    public ColorStateList getCardBackgroundColor() {
        return CardView.IMPL.getBackgroundColor(this.mCardViewDelegate);
    }

    public float getCardElevation() {
        return CardView.IMPL.getElevation(this.mCardViewDelegate);
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public float getMaxCardElevation() {
        return CardView.IMPL.getMaxElevation(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public float getRadius() {
        return CardView.IMPL.getRadius(this.mCardViewDelegate);
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    @Override  // android.widget.FrameLayout
    protected void onMeasure(int v, int v1) {
        CardViewImpl cardViewImpl0 = CardView.IMPL;
        if(!(cardViewImpl0 instanceof CardViewApi21Impl)) {
            int v2 = View.MeasureSpec.getMode(v);
            if(v2 == 0x80000000 || v2 == 0x40000000) {
                v = View.MeasureSpec.makeMeasureSpec(Math.max(((int)Math.ceil(cardViewImpl0.getMinWidth(this.mCardViewDelegate))), View.MeasureSpec.getSize(v)), v2);
            }
            int v3 = View.MeasureSpec.getMode(v1);
            if(v3 == 0x80000000 || v3 == 0x40000000) {
                v1 = View.MeasureSpec.makeMeasureSpec(Math.max(((int)Math.ceil(cardViewImpl0.getMinHeight(this.mCardViewDelegate))), View.MeasureSpec.getSize(v1)), v3);
            }
            super.onMeasure(v, v1);
            return;
        }
        super.onMeasure(v, v1);
    }

    public void setCardBackgroundColor(int v) {
        ColorStateList colorStateList0 = ColorStateList.valueOf(v);
        CardView.IMPL.setBackgroundColor(this.mCardViewDelegate, colorStateList0);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList0) {
        CardView.IMPL.setBackgroundColor(this.mCardViewDelegate, colorStateList0);
    }

    public void setCardElevation(float f) {
        CardView.IMPL.setElevation(this.mCardViewDelegate, f);
    }

    public void setContentPadding(int v, int v1, int v2, int v3) {
        this.mContentPadding.set(v, v1, v2, v3);
        CardView.IMPL.updatePadding(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float f) {
        CardView.IMPL.setMaxElevation(this.mCardViewDelegate, f);
    }

    @Override  // android.view.View
    public void setMinimumHeight(int v) {
        this.mUserSetMinHeight = v;
        super.setMinimumHeight(v);
    }

    @Override  // android.view.View
    public void setMinimumWidth(int v) {
        this.mUserSetMinWidth = v;
        super.setMinimumWidth(v);
    }

    @Override  // android.view.View
    public void setPadding(int v, int v1, int v2, int v3) {
    }

    @Override  // android.view.View
    public void setPaddingRelative(int v, int v1, int v2, int v3) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if(z != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = z;
            CardView.IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        }
    }

    public void setRadius(float f) {
        CardView.IMPL.setRadius(this.mCardViewDelegate, f);
    }

    public void setUseCompatPadding(boolean z) {
        if(this.mCompatPadding != z) {
            this.mCompatPadding = z;
            CardView.IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        }
    }
}

