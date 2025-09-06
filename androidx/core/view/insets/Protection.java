package androidx.core.view.insets;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.Insets;

public abstract class Protection {
    static class Attributes {
        interface Callback {
            void onAlphaChanged(float arg1);

            void onDrawableChanged(Drawable arg1);

            void onHeightChanged(int arg1);

            void onMarginChanged(Insets arg1);

            void onTranslationXChanged(float arg1);

            void onTranslationYChanged(float arg1);

            void onVisibilityChanged(boolean arg1);

            void onWidthChanged(int arg1);
        }

        private static final int UNSPECIFIED = -1;
        private float mAlpha;
        private Callback mCallback;
        private Drawable mDrawable;
        private int mHeight;
        private Insets mMargin;
        private float mTranslationX;
        private float mTranslationY;
        private boolean mVisible;
        private int mWidth;

        Attributes() {
            this.mWidth = -1;
            this.mHeight = -1;
            this.mMargin = Insets.NONE;
            this.mVisible = false;
            this.mDrawable = null;
            this.mTranslationX = 0.0f;
            this.mTranslationY = 0.0f;
            this.mAlpha = 1.0f;
        }

        float getAlpha() {
            return this.mAlpha;
        }

        Drawable getDrawable() {
            return this.mDrawable;
        }

        int getHeight() {
            return this.mHeight;
        }

        Insets getMargin() {
            return this.mMargin;
        }

        float getTranslationX() {
            return this.mTranslationX;
        }

        float getTranslationY() {
            return this.mTranslationY;
        }

        int getWidth() {
            return this.mWidth;
        }

        boolean isVisible() {
            return this.mVisible;
        }

        private void setAlpha(float f) {
            if(this.mAlpha != f) {
                this.mAlpha = f;
                Callback protection$Attributes$Callback0 = this.mCallback;
                if(protection$Attributes$Callback0 != null) {
                    protection$Attributes$Callback0.onAlphaChanged(f);
                }
            }
        }

        void setCallback(Callback protection$Attributes$Callback0) {
            if(this.mCallback != null && protection$Attributes$Callback0 != null) {
                throw new IllegalStateException("Trying to overwrite the existing callback. Did you send one protection to multiple ProtectionLayouts?");
            }
            this.mCallback = protection$Attributes$Callback0;
        }

        private void setDrawable(Drawable drawable0) {
            this.mDrawable = drawable0;
            Callback protection$Attributes$Callback0 = this.mCallback;
            if(protection$Attributes$Callback0 != null) {
                protection$Attributes$Callback0.onDrawableChanged(drawable0);
            }
        }

        private void setHeight(int v) {
            if(this.mHeight != v) {
                this.mHeight = v;
                Callback protection$Attributes$Callback0 = this.mCallback;
                if(protection$Attributes$Callback0 != null) {
                    protection$Attributes$Callback0.onHeightChanged(v);
                }
            }
        }

        private void setMargin(Insets insets0) {
            if(!this.mMargin.equals(insets0)) {
                this.mMargin = insets0;
                Callback protection$Attributes$Callback0 = this.mCallback;
                if(protection$Attributes$Callback0 != null) {
                    protection$Attributes$Callback0.onMarginChanged(insets0);
                }
            }
        }

        private void setTranslationX(float f) {
            if(this.mTranslationX != f) {
                this.mTranslationX = f;
                Callback protection$Attributes$Callback0 = this.mCallback;
                if(protection$Attributes$Callback0 != null) {
                    protection$Attributes$Callback0.onTranslationXChanged(f);
                }
            }
        }

        private void setTranslationY(float f) {
            if(this.mTranslationY != f) {
                this.mTranslationY = f;
                Callback protection$Attributes$Callback0 = this.mCallback;
                if(protection$Attributes$Callback0 != null) {
                    protection$Attributes$Callback0.onTranslationYChanged(f);
                }
            }
        }

        private void setVisible(boolean z) {
            if(this.mVisible != z) {
                this.mVisible = z;
                Callback protection$Attributes$Callback0 = this.mCallback;
                if(protection$Attributes$Callback0 != null) {
                    protection$Attributes$Callback0.onVisibilityChanged(z);
                }
            }
        }

        private void setWidth(int v) {
            if(this.mWidth != v) {
                this.mWidth = v;
                Callback protection$Attributes$Callback0 = this.mCallback;
                if(protection$Attributes$Callback0 != null) {
                    protection$Attributes$Callback0.onWidthChanged(v);
                }
            }
        }
    }

    private static final long DEFAULT_DURATION_IN = 333L;
    private static final long DEFAULT_DURATION_OUT = 0xA6L;
    private static final Interpolator DEFAULT_INTERPOLATOR_FADE_IN;
    private static final Interpolator DEFAULT_INTERPOLATOR_FADE_OUT;
    private static final Interpolator DEFAULT_INTERPOLATOR_MOVE_IN;
    private static final Interpolator DEFAULT_INTERPOLATOR_MOVE_OUT;
    private final Attributes mAttributes;
    private Object mController;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;
    private final int mSide;
    private float mSystemAlpha;
    private float mSystemInsetAmount;
    private float mUserAlpha;
    private ValueAnimator mUserAlphaAnimator;
    private float mUserInsetAmount;
    private ValueAnimator mUserInsetAmountAnimator;

    static {
        Protection.DEFAULT_INTERPOLATOR_MOVE_IN = new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f);
        Protection.DEFAULT_INTERPOLATOR_MOVE_OUT = new PathInterpolator(0.6f, 0.0f, 1.0f, 1.0f);
        Protection.DEFAULT_INTERPOLATOR_FADE_IN = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
        Protection.DEFAULT_INTERPOLATOR_FADE_OUT = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
    }

    public Protection(int v) {
        this.mAttributes = new Attributes();
        this.mInsets = Insets.NONE;
        this.mInsetsIgnoringVisibility = Insets.NONE;
        this.mSystemAlpha = 1.0f;
        this.mUserAlpha = 1.0f;
        this.mSystemInsetAmount = 1.0f;
        this.mUserInsetAmount = 1.0f;
        this.mController = null;
        this.mUserAlphaAnimator = null;
        this.mUserInsetAmountAnimator = null;
        if(v != 1 && v != 2 && v != 4 && v != 8) {
            throw new IllegalArgumentException("Unexpected side: " + v);
        }
        this.mSide = v;
    }

    public void animateAlpha(float f) {
        this.cancelUserAlphaAnimation();
        float f1 = this.mUserAlpha;
        if(f == f1) {
            return;
        }
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{f1, f});
        this.mUserAlphaAnimator = valueAnimator0;
        if(this.mUserAlpha < f) {
            valueAnimator0.setDuration(333L);
            this.mUserAlphaAnimator.setInterpolator(Protection.DEFAULT_INTERPOLATOR_FADE_IN);
        }
        else {
            valueAnimator0.setDuration(0xA6L);
            this.mUserAlphaAnimator.setInterpolator(Protection.DEFAULT_INTERPOLATOR_FADE_OUT);
        }
        this.mUserAlphaAnimator.addUpdateListener((ValueAnimator valueAnimator0) -> this.setAlphaInternal(((float)(((Float)valueAnimator0.getAnimatedValue())))));
        this.mUserAlphaAnimator.start();
    }

    public void animateInsetsAmount(float f) {
        this.cancelUserInsetsAmountAnimation();
        float f1 = this.mUserInsetAmount;
        if(f == f1) {
            return;
        }
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{f1, f});
        this.mUserInsetAmountAnimator = valueAnimator0;
        if(this.mUserInsetAmount < f) {
            valueAnimator0.setDuration(333L);
            this.mUserInsetAmountAnimator.setInterpolator(Protection.DEFAULT_INTERPOLATOR_MOVE_IN);
        }
        else {
            valueAnimator0.setDuration(0xA6L);
            this.mUserInsetAmountAnimator.setInterpolator(Protection.DEFAULT_INTERPOLATOR_MOVE_OUT);
        }
        this.mUserInsetAmountAnimator.addUpdateListener((ValueAnimator valueAnimator0) -> this.setAlphaInternal(((float)(((Float)valueAnimator0.getAnimatedValue())))));
        this.mUserInsetAmountAnimator.start();
    }

    private void cancelUserAlphaAnimation() {
        ValueAnimator valueAnimator0 = this.mUserAlphaAnimator;
        if(valueAnimator0 != null) {
            valueAnimator0.cancel();
            this.mUserAlphaAnimator = null;
        }
    }

    private void cancelUserInsetsAmountAnimation() {
        ValueAnimator valueAnimator0 = this.mUserInsetAmountAnimator;
        if(valueAnimator0 != null) {
            valueAnimator0.cancel();
            this.mUserInsetAmountAnimator = null;
        }
    }

    void dispatchColorHint(int v) {
    }

    Insets dispatchInsets(Insets insets0, Insets insets1, Insets insets2) {
        this.mInsets = insets0;
        this.mInsetsIgnoringVisibility = insets1;
        this.mAttributes.setMargin(insets2);
        return this.updateLayout();
    }

    public float getAlpha() {
        return this.mUserAlpha;
    }

    Attributes getAttributes() {
        return this.mAttributes;
    }

    Object getController() {
        return this.mController;
    }

    public float getInsetAmount() {
        return this.mUserInsetAmount;
    }

    public int getSide() {
        return this.mSide;
    }

    int getThickness(int v) {
        return v;
    }

    // 检测为 Lambda 实现
    void lambda$animateAlpha$0$androidx-core-view-insets-Protection(ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$animateInsetsAmount$1$androidx-core-view-insets-Protection(ValueAnimator valueAnimator0) [...]

    boolean occupiesCorners() {
        return false;
    }

    public void setAlpha(float f) {
        if(f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Alpha must in a range of [0, 1]. Got: " + f);
        }
        this.cancelUserAlphaAnimation();
        this.setAlphaInternal(f);
    }

    private void setAlphaInternal(float f) {
        this.mUserAlpha = f;
        this.updateAlpha();
    }

    void setController(Object object0) {
        this.mController = object0;
    }

    void setDrawable(Drawable drawable0) {
        this.mAttributes.setDrawable(drawable0);
    }

    public void setInsetAmount(float f) {
        if(f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Inset amount must in a range of [0, 1]. Got: " + f);
        }
        this.cancelUserInsetsAmountAnimation();
        this.setInsetAmountInternal(f);
    }

    private void setInsetAmountInternal(float f) {
        this.mUserInsetAmount = f;
        this.updateInsetAmount();
    }

    void setSystemAlpha(float f) {
        this.mSystemAlpha = f;
        this.updateAlpha();
    }

    void setSystemInsetAmount(float f) {
        this.mSystemInsetAmount = f;
        this.updateInsetAmount();
    }

    void setSystemVisible(boolean z) {
        this.mAttributes.setVisible(z);
    }

    private void updateAlpha() {
        this.mAttributes.setAlpha(this.mSystemAlpha * this.mUserAlpha);
    }

    private void updateInsetAmount() {
        float f = this.mUserInsetAmount * this.mSystemInsetAmount;
        switch(this.mSide) {
            case 1: {
                this.mAttributes.setTranslationX(-(1.0f - f) * ((float)this.mAttributes.mWidth));
                return;
            }
            case 2: {
                this.mAttributes.setTranslationY(-(1.0f - f) * ((float)this.mAttributes.mHeight));
                return;
            }
            case 4: {
                this.mAttributes.setTranslationX((1.0f - f) * ((float)this.mAttributes.mWidth));
                return;
            }
            case 8: {
                this.mAttributes.setTranslationY((1.0f - f) * ((float)this.mAttributes.mHeight));
            }
        }
    }

    Insets updateLayout() {
        int v;
        Insets insets0 = Insets.NONE;
        boolean z = true;
        switch(this.mSide) {
            case 1: {
                v = this.mInsets.left;
                this.mAttributes.setWidth(this.getThickness(this.mInsetsIgnoringVisibility.left));
                if(this.occupiesCorners()) {
                    insets0 = Insets.of(this.getThickness(v), 0, 0, 0);
                }
                break;
            }
            case 2: {
                v = this.mInsets.top;
                this.mAttributes.setHeight(this.getThickness(this.mInsetsIgnoringVisibility.top));
                if(this.occupiesCorners()) {
                    insets0 = Insets.of(0, this.getThickness(v), 0, 0);
                }
                break;
            }
            case 4: {
                v = this.mInsets.right;
                this.mAttributes.setWidth(this.getThickness(this.mInsetsIgnoringVisibility.right));
                if(this.occupiesCorners()) {
                    insets0 = Insets.of(0, 0, this.getThickness(v), 0);
                }
                break;
            }
            case 8: {
                v = this.mInsets.bottom;
                this.mAttributes.setHeight(this.getThickness(this.mInsetsIgnoringVisibility.bottom));
                if(this.occupiesCorners()) {
                    insets0 = Insets.of(0, 0, 0, this.getThickness(v));
                }
                break;
            }
            default: {
                v = 0;
            }
        }
        if(v <= 0) {
            z = false;
        }
        this.setSystemVisible(z);
        float f = 1.0f;
        this.setSystemAlpha((v <= 0 ? 0.0f : 1.0f));
        if(v <= 0) {
            f = 0.0f;
        }
        this.setSystemInsetAmount(f);
        return insets0;
    }
}

