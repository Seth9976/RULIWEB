package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import com.google.android.material.R.color;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;

class FloatingActionButtonImplLollipop extends FloatingActionButtonImpl {
    static class AlwaysStatefulMaterialShapeDrawable extends MaterialShapeDrawable {
        AlwaysStatefulMaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel0) {
            super(shapeAppearanceModel0);
        }

        @Override  // com.google.android.material.shape.MaterialShapeDrawable
        public boolean isStateful() {
            return true;
        }
    }

    private StateListAnimator stateListAnimator;

    FloatingActionButtonImplLollipop(FloatingActionButton floatingActionButton0, ShadowViewDelegate shadowViewDelegate0) {
        super(floatingActionButton0, shadowViewDelegate0);
    }

    BorderDrawable createBorderDrawable(int v, ColorStateList colorStateList0) {
        Context context0 = this.view.getContext();
        BorderDrawable borderDrawable0 = new BorderDrawable(((ShapeAppearanceModel)Preconditions.checkNotNull(this.shapeAppearance)));
        borderDrawable0.setGradientColors(ContextCompat.getColor(context0, color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context0, color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context0, color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context0, color.design_fab_stroke_end_outer_color));
        borderDrawable0.setBorderWidth(((float)v));
        borderDrawable0.setBorderTint(colorStateList0);
        return borderDrawable0;
    }

    private StateListAnimator createDefaultStateListAnimator(float f, float f1, float f2) {
        StateListAnimator stateListAnimator0 = new StateListAnimator();
        stateListAnimator0.addState(FloatingActionButtonImplLollipop.PRESSED_ENABLED_STATE_SET, this.createElevationAnimator(f, f2));
        stateListAnimator0.addState(FloatingActionButtonImplLollipop.HOVERED_FOCUSED_ENABLED_STATE_SET, this.createElevationAnimator(f, f1));
        stateListAnimator0.addState(FloatingActionButtonImplLollipop.FOCUSED_ENABLED_STATE_SET, this.createElevationAnimator(f, f1));
        stateListAnimator0.addState(FloatingActionButtonImplLollipop.HOVERED_ENABLED_STATE_SET, this.createElevationAnimator(f, f1));
        AnimatorSet animatorSet0 = new AnimatorSet();
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(ObjectAnimator.ofFloat(this.view, "elevation", new float[]{f}).setDuration(0L));
        if(Build.VERSION.SDK_INT >= 22 && Build.VERSION.SDK_INT <= 24) {
            arrayList0.add(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{this.view.getTranslationZ()}).setDuration(100L));
        }
        arrayList0.add(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{0.0f}).setDuration(100L));
        animatorSet0.playSequentially(((Animator[])arrayList0.toArray(new Animator[0])));
        animatorSet0.setInterpolator(FloatingActionButtonImplLollipop.ELEVATION_ANIM_INTERPOLATOR);
        stateListAnimator0.addState(FloatingActionButtonImplLollipop.ENABLED_STATE_SET, animatorSet0);
        stateListAnimator0.addState(FloatingActionButtonImplLollipop.EMPTY_STATE_SET, this.createElevationAnimator(0.0f, 0.0f));
        return stateListAnimator0;
    }

    private Animator createElevationAnimator(float f, float f1) {
        Animator animator0 = new AnimatorSet();
        ((AnimatorSet)animator0).play(ObjectAnimator.ofFloat(this.view, "elevation", new float[]{f}).setDuration(0L)).with(ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Z, new float[]{f1}).setDuration(100L));
        ((AnimatorSet)animator0).setInterpolator(FloatingActionButtonImplLollipop.ELEVATION_ANIM_INTERPOLATOR);
        return animator0;
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    MaterialShapeDrawable createShapeDrawable() {
        return new AlwaysStatefulMaterialShapeDrawable(((ShapeAppearanceModel)Preconditions.checkNotNull(this.shapeAppearance)));
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public float getElevation() {
        return this.view.getElevation();
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void getPadding(Rect rect0) {
        if(this.shadowViewDelegate.isCompatPaddingEnabled()) {
            super.getPadding(rect0);
            return;
        }
        if(!this.shouldExpandBoundsForA11y()) {
            int v = (this.minTouchTargetSize - this.view.getSizeDimension()) / 2;
            rect0.set(v, v, v, v);
            return;
        }
        rect0.set(0, 0, 0, 0);
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void initializeBackgroundDrawable(ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0, ColorStateList colorStateList1, int v) {
        LayerDrawable layerDrawable0;
        this.shapeDrawable = this.createShapeDrawable();
        this.shapeDrawable.setTintList(colorStateList0);
        if(porterDuff$Mode0 != null) {
            this.shapeDrawable.setTintMode(porterDuff$Mode0);
        }
        this.shapeDrawable.initializeElevationOverlay(this.view.getContext());
        if(v > 0) {
            this.borderDrawable = this.createBorderDrawable(v, colorStateList0);
            layerDrawable0 = new LayerDrawable(new Drawable[]{((Drawable)Preconditions.checkNotNull(this.borderDrawable)), ((Drawable)Preconditions.checkNotNull(this.shapeDrawable))});
        }
        else {
            this.borderDrawable = null;
            layerDrawable0 = this.shapeDrawable;
        }
        this.rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(colorStateList1), layerDrawable0, null);
        this.contentBackground = this.rippleDrawable;
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void jumpDrawableToCurrentState() {
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void onCompatShadowChanged() {
        this.updatePadding();
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void onDrawableStateChanged(int[] arr_v) {
        if(Build.VERSION.SDK_INT == 21) {
            if(this.view.isEnabled()) {
                this.view.setElevation(this.elevation);
                if(this.view.isPressed()) {
                    this.view.setTranslationZ(this.pressedTranslationZ);
                    return;
                }
                if(!this.view.isFocused() && !this.view.isHovered()) {
                    this.view.setTranslationZ(0.0f);
                    return;
                }
                this.view.setTranslationZ(this.hoveredFocusedTranslationZ);
                return;
            }
            this.view.setElevation(0.0f);
            this.view.setTranslationZ(0.0f);
        }
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void onElevationsChanged(float f, float f1, float f2) {
        if(Build.VERSION.SDK_INT == 21) {
            this.view.refreshDrawableState();
        }
        else if(this.view.getStateListAnimator() == this.stateListAnimator) {
            this.stateListAnimator = this.createDefaultStateListAnimator(f, f1, f2);
            this.view.setStateListAnimator(this.stateListAnimator);
        }
        if(this.shouldAddPadding()) {
            this.updatePadding();
        }
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    boolean requirePreDrawListener() {
        return false;
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void setRippleColor(ColorStateList colorStateList0) {
        if(this.rippleDrawable instanceof RippleDrawable) {
            ((RippleDrawable)this.rippleDrawable).setColor(RippleUtils.sanitizeRippleDrawableColor(colorStateList0));
            return;
        }
        super.setRippleColor(colorStateList0);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    boolean shouldAddPadding() {
        return this.shadowViewDelegate.isCompatPaddingEnabled() || !this.shouldExpandBoundsForA11y();
    }

    @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void updateFromViewRotation() {
    }
}

