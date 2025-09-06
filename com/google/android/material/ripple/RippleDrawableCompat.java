package com.google.android.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.TintAwareDrawable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;

public class RippleDrawableCompat extends Drawable implements TintAwareDrawable, Shapeable {
    static final class RippleDrawableCompatState extends Drawable.ConstantState {
        MaterialShapeDrawable delegate;
        boolean shouldDrawDelegate;

        public RippleDrawableCompatState(RippleDrawableCompatState rippleDrawableCompat$RippleDrawableCompatState0) {
            this.delegate = (MaterialShapeDrawable)rippleDrawableCompat$RippleDrawableCompatState0.delegate.getConstantState().newDrawable();
            this.shouldDrawDelegate = rippleDrawableCompat$RippleDrawableCompatState0.shouldDrawDelegate;
        }

        public RippleDrawableCompatState(MaterialShapeDrawable materialShapeDrawable0) {
            this.delegate = materialShapeDrawable0;
            this.shouldDrawDelegate = false;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            return this.newDrawable();
        }

        public RippleDrawableCompat newDrawable() {
            return new RippleDrawableCompat(new RippleDrawableCompatState(this), null);
        }
    }

    private RippleDrawableCompatState drawableState;

    private RippleDrawableCompat(RippleDrawableCompatState rippleDrawableCompat$RippleDrawableCompatState0) {
        this.drawableState = rippleDrawableCompat$RippleDrawableCompatState0;
    }

    RippleDrawableCompat(RippleDrawableCompatState rippleDrawableCompat$RippleDrawableCompatState0, com.google.android.material.ripple.RippleDrawableCompat.1 rippleDrawableCompat$10) {
        this(rippleDrawableCompat$RippleDrawableCompatState0);
    }

    public RippleDrawableCompat(ShapeAppearanceModel shapeAppearanceModel0) {
        this(new RippleDrawableCompatState(new MaterialShapeDrawable(shapeAppearanceModel0)));
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        if(this.drawableState.shouldDrawDelegate) {
            this.drawableState.delegate.draw(canvas0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.drawableState.delegate.getOpacity();
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.delegate.getShapeAppearanceModel();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable mutate() {
        return this.mutate();
    }

    public RippleDrawableCompat mutate() {
        this.drawableState = new RippleDrawableCompatState(this.drawableState);
        return this;
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        super.onBoundsChange(rect0);
        this.drawableState.delegate.setBounds(rect0);
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        boolean z = super.onStateChange(arr_v);
        if(this.drawableState.delegate.setState(arr_v)) {
            z = true;
        }
        boolean z1 = RippleUtils.shouldDrawRippleCompat(arr_v);
        if(this.drawableState.shouldDrawDelegate != z1) {
            this.drawableState.shouldDrawDelegate = z1;
            return true;
        }
        return z;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.drawableState.delegate.setAlpha(v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.drawableState.delegate.setColorFilter(colorFilter0);
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.drawableState.delegate.setShapeAppearanceModel(shapeAppearanceModel0);
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int v) {
        this.drawableState.delegate.setTint(v);
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList0) {
        this.drawableState.delegate.setTintList(colorStateList0);
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.drawableState.delegate.setTintMode(porterDuff$Mode0);
    }

    class com.google.android.material.ripple.RippleDrawableCompat.1 {
    }

}

