package com.google.android.material.floatingactionbutton;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Matrix;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.ViewTreeObserver;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.integer;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;

class FloatingActionButtonImpl {
    class DisabledElevationAnimation extends ShadowAnimatorImpl {
        DisabledElevationAnimation() {
            super(null);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$ShadowAnimatorImpl
        protected float getTargetShadowSize() {
            return 0.0f;
        }
    }

    class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl {
        ElevateToHoveredFocusedTranslationZAnimation() {
            super(null);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$ShadowAnimatorImpl
        protected float getTargetShadowSize() {
            return FloatingActionButtonImpl.this.elevation + FloatingActionButtonImpl.this.hoveredFocusedTranslationZ;
        }
    }

    class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl {
        ElevateToPressedTranslationZAnimation() {
            super(null);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$ShadowAnimatorImpl
        protected float getTargetShadowSize() {
            return FloatingActionButtonImpl.this.elevation + FloatingActionButtonImpl.this.pressedTranslationZ;
        }
    }

    interface InternalTransformationCallback {
        void onScaleChanged();

        void onTranslationChanged();
    }

    interface InternalVisibilityChangedListener {
        void onHidden();

        void onShown();
    }

    class ResetElevationAnimation extends ShadowAnimatorImpl {
        ResetElevationAnimation() {
            super(null);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$ShadowAnimatorImpl
        protected float getTargetShadowSize() {
            return FloatingActionButtonImpl.this.elevation;
        }
    }

    abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private float shadowSizeEnd;
        private float shadowSizeStart;
        private boolean validValues;

        private ShadowAnimatorImpl() {
        }

        ShadowAnimatorImpl(com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.1 floatingActionButtonImpl$10) {
        }

        protected abstract float getTargetShadowSize();

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            FloatingActionButtonImpl.this.updateShapeElevation(((float)(((int)this.shadowSizeEnd))));
            this.validValues = false;
        }

        @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator0) {
            if(!this.validValues) {
                this.shadowSizeStart = FloatingActionButtonImpl.this.shapeDrawable == null ? 0.0f : FloatingActionButtonImpl.this.shapeDrawable.getElevation();
                this.shadowSizeEnd = this.getTargetShadowSize();
                this.validValues = true;
            }
            float f = this.shadowSizeStart;
            float f1 = this.shadowSizeEnd - f;
            float f2 = valueAnimator0.getAnimatedFraction();
            FloatingActionButtonImpl.this.updateShapeElevation(((float)(((int)(f + f1 * f2)))));
        }
    }

    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_SHOWING = 2;
    static final long ELEVATION_ANIM_DELAY = 100L;
    static final long ELEVATION_ANIM_DURATION = 100L;
    static final TimeInterpolator ELEVATION_ANIM_INTERPOLATOR = null;
    static final int[] EMPTY_STATE_SET = null;
    static final int[] ENABLED_STATE_SET = null;
    static final int[] FOCUSED_ENABLED_STATE_SET = null;
    private static final int HIDE_ANIM_DURATION_ATTR = 0;
    private static final int HIDE_ANIM_EASING_ATTR = 0;
    private static final float HIDE_ICON_SCALE = 0.4f;
    private static final float HIDE_OPACITY = 0.0f;
    private static final float HIDE_SCALE = 0.4f;
    static final int[] HOVERED_ENABLED_STATE_SET = null;
    static final int[] HOVERED_FOCUSED_ENABLED_STATE_SET = null;
    static final int[] PRESSED_ENABLED_STATE_SET = null;
    static final float SHADOW_MULTIPLIER = 1.5f;
    private static final int SHOW_ANIM_DURATION_ATTR = 0;
    private static final int SHOW_ANIM_EASING_ATTR = 0;
    private static final float SHOW_ICON_SCALE = 1.0f;
    private static final float SHOW_OPACITY = 1.0f;
    private static final float SHOW_SCALE = 1.0f;
    private static final float SPEC_HIDE_ICON_SCALE;
    private static final float SPEC_HIDE_SCALE;
    private int animState;
    BorderDrawable borderDrawable;
    Drawable contentBackground;
    private Animator currentAnimator;
    float elevation;
    boolean ensureMinTouchTargetSize;
    private ArrayList hideListeners;
    private MotionSpec hideMotionSpec;
    float hoveredFocusedTranslationZ;
    private float imageMatrixScale;
    private int maxImageSize;
    int minTouchTargetSize;
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    float pressedTranslationZ;
    Drawable rippleDrawable;
    private float rotation;
    boolean shadowPaddingEnabled;
    final ShadowViewDelegate shadowViewDelegate;
    ShapeAppearanceModel shapeAppearance;
    MaterialShapeDrawable shapeDrawable;
    private ArrayList showListeners;
    private MotionSpec showMotionSpec;
    private final StateListAnimator stateListAnimator;
    private final Matrix tmpMatrix;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;
    private ArrayList transformationCallbacks;
    final FloatingActionButton view;

    static {
        FloatingActionButtonImpl.ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        FloatingActionButtonImpl.SHOW_ANIM_DURATION_ATTR = attr.motionDurationLong2;
        FloatingActionButtonImpl.SHOW_ANIM_EASING_ATTR = attr.motionEasingEmphasizedInterpolator;
        FloatingActionButtonImpl.HIDE_ANIM_DURATION_ATTR = attr.motionDurationMedium1;
        FloatingActionButtonImpl.HIDE_ANIM_EASING_ATTR = attr.motionEasingEmphasizedAccelerateInterpolator;
        FloatingActionButtonImpl.PRESSED_ENABLED_STATE_SET = new int[]{0x10100A7, 0x101009E};
        FloatingActionButtonImpl.HOVERED_FOCUSED_ENABLED_STATE_SET = new int[]{0x1010367, 0x101009C, 0x101009E};
        FloatingActionButtonImpl.FOCUSED_ENABLED_STATE_SET = new int[]{0x101009C, 0x101009E};
        FloatingActionButtonImpl.HOVERED_ENABLED_STATE_SET = new int[]{0x1010367, 0x101009E};
        FloatingActionButtonImpl.ENABLED_STATE_SET = new int[]{0x101009E};
        FloatingActionButtonImpl.EMPTY_STATE_SET = new int[0];
    }

    FloatingActionButtonImpl(FloatingActionButton floatingActionButton0, ShadowViewDelegate shadowViewDelegate0) {
        this.shadowPaddingEnabled = true;
        this.imageMatrixScale = 1.0f;
        this.animState = 0;
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpMatrix = new Matrix();
        this.view = floatingActionButton0;
        this.shadowViewDelegate = shadowViewDelegate0;
        StateListAnimator stateListAnimator0 = new StateListAnimator();
        this.stateListAnimator = stateListAnimator0;
        ValueAnimator valueAnimator0 = this.createElevationAnimator(new ElevateToPressedTranslationZAnimation(this));
        stateListAnimator0.addState(FloatingActionButtonImpl.PRESSED_ENABLED_STATE_SET, valueAnimator0);
        ValueAnimator valueAnimator1 = this.createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation(this));
        stateListAnimator0.addState(FloatingActionButtonImpl.HOVERED_FOCUSED_ENABLED_STATE_SET, valueAnimator1);
        ValueAnimator valueAnimator2 = this.createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation(this));
        stateListAnimator0.addState(FloatingActionButtonImpl.FOCUSED_ENABLED_STATE_SET, valueAnimator2);
        ValueAnimator valueAnimator3 = this.createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation(this));
        stateListAnimator0.addState(FloatingActionButtonImpl.HOVERED_ENABLED_STATE_SET, valueAnimator3);
        ValueAnimator valueAnimator4 = this.createElevationAnimator(new ResetElevationAnimation(this));
        stateListAnimator0.addState(FloatingActionButtonImpl.ENABLED_STATE_SET, valueAnimator4);
        ValueAnimator valueAnimator5 = this.createElevationAnimator(new DisabledElevationAnimation(this));
        stateListAnimator0.addState(FloatingActionButtonImpl.EMPTY_STATE_SET, valueAnimator5);
        this.rotation = floatingActionButton0.getRotation();
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        if(this.hideListeners == null) {
            this.hideListeners = new ArrayList();
        }
        this.hideListeners.add(animator$AnimatorListener0);
    }

    void addOnShowAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        if(this.showListeners == null) {
            this.showListeners = new ArrayList();
        }
        this.showListeners.add(animator$AnimatorListener0);
    }

    void addTransformationCallback(InternalTransformationCallback floatingActionButtonImpl$InternalTransformationCallback0) {
        if(this.transformationCallbacks == null) {
            this.transformationCallbacks = new ArrayList();
        }
        this.transformationCallbacks.add(floatingActionButtonImpl$InternalTransformationCallback0);
    }

    private void calculateImageMatrixFromScale(float f, Matrix matrix0) {
        matrix0.reset();
        Drawable drawable0 = this.view.getDrawable();
        if(drawable0 != null && this.maxImageSize != 0) {
            float f1 = (float)drawable0.getIntrinsicWidth();
            float f2 = (float)drawable0.getIntrinsicHeight();
            this.tmpRectF1.set(0.0f, 0.0f, f1, f2);
            this.tmpRectF2.set(0.0f, 0.0f, ((float)this.maxImageSize), ((float)this.maxImageSize));
            matrix0.setRectToRect(this.tmpRectF1, this.tmpRectF2, Matrix.ScaleToFit.CENTER);
            matrix0.postScale(f, f, ((float)this.maxImageSize) / 2.0f, ((float)this.maxImageSize) / 2.0f);
        }
    }

    private AnimatorSet createAnimator(MotionSpec motionSpec0, float f, float f1, float f2) {
        ArrayList arrayList0 = new ArrayList();
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this.view, View.ALPHA, new float[]{f});
        motionSpec0.getTiming("opacity").apply(objectAnimator0);
        arrayList0.add(objectAnimator0);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{f1});
        motionSpec0.getTiming("scale").apply(objectAnimator1);
        this.workAroundOreoBug(objectAnimator1);
        arrayList0.add(objectAnimator1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{f1});
        motionSpec0.getTiming("scale").apply(objectAnimator2);
        this.workAroundOreoBug(objectAnimator2);
        arrayList0.add(objectAnimator2);
        this.calculateImageMatrixFromScale(f2, this.tmpMatrix);
        ImageMatrixProperty imageMatrixProperty0 = new ImageMatrixProperty();
        com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.3 floatingActionButtonImpl$30 = new MatrixEvaluator() {
            @Override  // com.google.android.material.animation.MatrixEvaluator
            public Matrix evaluate(float f, Matrix matrix0, Matrix matrix1) {
                FloatingActionButtonImpl.this.imageMatrixScale = f;
                return super.evaluate(f, matrix0, matrix1);
            }

            @Override  // com.google.android.material.animation.MatrixEvaluator
            public Object evaluate(float f, Object object0, Object object1) {
                return this.evaluate(f, ((Matrix)object0), ((Matrix)object1));
            }
        };
        Matrix[] arr_matrix = {new Matrix(this.tmpMatrix)};
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofObject(this.view, imageMatrixProperty0, floatingActionButtonImpl$30, arr_matrix);
        motionSpec0.getTiming("iconScale").apply(objectAnimator3);
        arrayList0.add(objectAnimator3);
        AnimatorSet animatorSet0 = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet0, arrayList0);
        return animatorSet0;
    }

    private AnimatorSet createDefaultAnimator(float f, float f1, float f2, int v, int v1) {
        AnimatorSet animatorSet0 = new AnimatorSet();
        ArrayList arrayList0 = new ArrayList();
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                FloatingActionButtonImpl.this.view.setAlpha(AnimationUtils.lerp(this.view.getAlpha(), f, 0.0f, 0.2f, f));
                FloatingActionButtonImpl.this.view.setScaleX(this.view.getScaleX() + f * (f1 - this.view.getScaleX()));
                FloatingActionButtonImpl.this.view.setScaleY(this.view.getScaleY() + f * (f1 - this.view.getScaleY()));
                FloatingActionButtonImpl.this.imageMatrixScale = this.imageMatrixScale + f * (f2 - this.imageMatrixScale);
                FloatingActionButtonImpl.this.calculateImageMatrixFromScale(this.imageMatrixScale + f * (f2 - this.imageMatrixScale), new Matrix(this.tmpMatrix));
                FloatingActionButtonImpl.this.view.setImageMatrix(new Matrix(this.tmpMatrix));
            }
        });
        arrayList0.add(valueAnimator0);
        AnimatorSetCompat.playTogether(animatorSet0, arrayList0);
        animatorSet0.setDuration(((long)MotionUtils.resolveThemeDuration(this.view.getContext(), v, this.view.getContext().getResources().getInteger(integer.material_motion_duration_long_1))));
        animatorSet0.setInterpolator(MotionUtils.resolveThemeInterpolator(this.view.getContext(), v1, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet0;
    }

    private ValueAnimator createElevationAnimator(ShadowAnimatorImpl floatingActionButtonImpl$ShadowAnimatorImpl0) {
        ValueAnimator valueAnimator0 = new ValueAnimator();
        valueAnimator0.setInterpolator(FloatingActionButtonImpl.ELEVATION_ANIM_INTERPOLATOR);
        valueAnimator0.setDuration(100L);
        valueAnimator0.addListener(floatingActionButtonImpl$ShadowAnimatorImpl0);
        valueAnimator0.addUpdateListener(floatingActionButtonImpl$ShadowAnimatorImpl0);
        valueAnimator0.setFloatValues(new float[]{0.0f, 1.0f});
        return valueAnimator0;
    }

    MaterialShapeDrawable createShapeDrawable() {
        return new MaterialShapeDrawable(((ShapeAppearanceModel)Preconditions.checkNotNull(this.shapeAppearance)));
    }

    final Drawable getContentBackground() {
        return this.contentBackground;
    }

    float getElevation() {
        return this.elevation;
    }

    boolean getEnsureMinTouchTargetSize() {
        return this.ensureMinTouchTargetSize;
    }

    final MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    float getHoveredFocusedTranslationZ() {
        return this.hoveredFocusedTranslationZ;
    }

    private ViewTreeObserver.OnPreDrawListener getOrCreatePreDrawListener() {
        if(this.preDrawListener == null) {
            this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                @Override  // android.view.ViewTreeObserver$OnPreDrawListener
                public boolean onPreDraw() {
                    FloatingActionButtonImpl.this.onPreDraw();
                    return true;
                }
            };
        }
        return this.preDrawListener;
    }

    void getPadding(Rect rect0) {
        int v = this.getTouchTargetPadding();
        float f = this.shadowPaddingEnabled ? this.getElevation() + this.pressedTranslationZ : 0.0f;
        int v1 = Math.max(v, ((int)Math.ceil(f)));
        int v2 = Math.max(v, ((int)Math.ceil(f * 1.5f)));
        rect0.set(v1, v2, v1, v2);
    }

    float getPressedTranslationZ() {
        return this.pressedTranslationZ;
    }

    final ShapeAppearanceModel getShapeAppearance() {
        return this.shapeAppearance;
    }

    final MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    // 去混淆评级： 低(20)
    int getTouchTargetPadding() {
        return this.ensureMinTouchTargetSize ? Math.max((this.minTouchTargetSize - this.view.getSizeDimension()) / 2, 0) : 0;
    }

    void hide(InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener0, boolean z) {
        if(!this.isOrWillBeHidden()) {
            Animator animator0 = this.currentAnimator;
            if(animator0 != null) {
                animator0.cancel();
            }
            if(this.shouldAnimateVisibilityChange()) {
                MotionSpec motionSpec0 = this.hideMotionSpec;
                AnimatorSet animatorSet0 = motionSpec0 == null ? this.createDefaultAnimator(0.0f, 0.4f, 0.4f, FloatingActionButtonImpl.HIDE_ANIM_DURATION_ATTR, FloatingActionButtonImpl.HIDE_ANIM_EASING_ATTR) : this.createAnimator(motionSpec0, 0.0f, 0.0f, 0.0f);
                animatorSet0.addListener(new AnimatorListenerAdapter() {
                    private boolean cancelled;

                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationCancel(Animator animator0) {
                        this.cancelled = true;
                    }

                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationEnd(Animator animator0) {
                        FloatingActionButtonImpl.this.animState = 0;
                        FloatingActionButtonImpl.this.currentAnimator = null;
                        if(!this.cancelled) {
                            FloatingActionButtonImpl.this.view.internalSetVisibility((z ? 8 : 4), z);
                            InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener0 = floatingActionButtonImpl$InternalVisibilityChangedListener0;
                            if(floatingActionButtonImpl$InternalVisibilityChangedListener0 != null) {
                                floatingActionButtonImpl$InternalVisibilityChangedListener0.onHidden();
                            }
                        }
                    }

                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationStart(Animator animator0) {
                        FloatingActionButtonImpl.this.view.internalSetVisibility(0, z);
                        FloatingActionButtonImpl.this.animState = 1;
                        FloatingActionButtonImpl.this.currentAnimator = animator0;
                        this.cancelled = false;
                    }
                });
                ArrayList arrayList0 = this.hideListeners;
                if(arrayList0 != null) {
                    for(Object object0: arrayList0) {
                        animatorSet0.addListener(((Animator.AnimatorListener)object0));
                    }
                }
                animatorSet0.start();
                return;
            }
            this.view.internalSetVisibility((z ? 8 : 4), z);
            if(floatingActionButtonImpl$InternalVisibilityChangedListener0 != null) {
                floatingActionButtonImpl$InternalVisibilityChangedListener0.onHidden();
            }
        }
    }

    void initializeBackgroundDrawable(ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0, ColorStateList colorStateList1, int v) {
        MaterialShapeDrawable materialShapeDrawable0 = this.createShapeDrawable();
        this.shapeDrawable = materialShapeDrawable0;
        materialShapeDrawable0.setTintList(colorStateList0);
        if(porterDuff$Mode0 != null) {
            this.shapeDrawable.setTintMode(porterDuff$Mode0);
        }
        this.shapeDrawable.setShadowColor(-12303292);
        this.shapeDrawable.initializeElevationOverlay(this.view.getContext());
        RippleDrawableCompat rippleDrawableCompat0 = new RippleDrawableCompat(this.shapeDrawable.getShapeAppearanceModel());
        rippleDrawableCompat0.setTintList(RippleUtils.sanitizeRippleDrawableColor(colorStateList1));
        this.rippleDrawable = rippleDrawableCompat0;
        this.contentBackground = new LayerDrawable(new Drawable[]{((Drawable)Preconditions.checkNotNull(this.shapeDrawable)), rippleDrawableCompat0});
    }

    boolean isOrWillBeHidden() {
        return this.view.getVisibility() == 0 ? this.animState == 1 : this.animState != 2;
    }

    boolean isOrWillBeShown() {
        return this.view.getVisibility() == 0 ? this.animState != 1 : this.animState == 2;
    }

    void jumpDrawableToCurrentState() {
        this.stateListAnimator.jumpToCurrentState();
    }

    void onAttachedToWindow() {
        MaterialShapeDrawable materialShapeDrawable0 = this.shapeDrawable;
        if(materialShapeDrawable0 != null) {
            MaterialShapeUtils.setParentAbsoluteElevation(this.view, materialShapeDrawable0);
        }
        if(this.requirePreDrawListener()) {
            this.view.getViewTreeObserver().addOnPreDrawListener(this.getOrCreatePreDrawListener());
        }
    }

    void onCompatShadowChanged() {
    }

    void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver0 = this.view.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener viewTreeObserver$OnPreDrawListener0 = this.preDrawListener;
        if(viewTreeObserver$OnPreDrawListener0 != null) {
            viewTreeObserver0.removeOnPreDrawListener(viewTreeObserver$OnPreDrawListener0);
            this.preDrawListener = null;
        }
    }

    void onDrawableStateChanged(int[] arr_v) {
        this.stateListAnimator.setState(arr_v);
    }

    void onElevationsChanged(float f, float f1, float f2) {
        this.jumpDrawableToCurrentState();
        this.updatePadding();
        this.updateShapeElevation(f);
    }

    void onPaddingUpdated(Rect rect0) {
        Preconditions.checkNotNull(this.contentBackground, "Didn\'t initialize content background");
        if(this.shouldAddPadding()) {
            InsetDrawable insetDrawable0 = new InsetDrawable(this.contentBackground, rect0.left, rect0.top, rect0.right, rect0.bottom);
            this.shadowViewDelegate.setBackgroundDrawable(insetDrawable0);
            return;
        }
        this.shadowViewDelegate.setBackgroundDrawable(this.contentBackground);
    }

    void onPreDraw() {
        float f = this.view.getRotation();
        if(this.rotation != f) {
            this.rotation = f;
            this.updateFromViewRotation();
        }
    }

    void onScaleChanged() {
        ArrayList arrayList0 = this.transformationCallbacks;
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                ((InternalTransformationCallback)object0).onScaleChanged();
            }
        }
    }

    void onTranslationChanged() {
        ArrayList arrayList0 = this.transformationCallbacks;
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                ((InternalTransformationCallback)object0).onTranslationChanged();
            }
        }
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        ArrayList arrayList0 = this.hideListeners;
        if(arrayList0 == null) {
            return;
        }
        arrayList0.remove(animator$AnimatorListener0);
    }

    void removeOnShowAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        ArrayList arrayList0 = this.showListeners;
        if(arrayList0 == null) {
            return;
        }
        arrayList0.remove(animator$AnimatorListener0);
    }

    void removeTransformationCallback(InternalTransformationCallback floatingActionButtonImpl$InternalTransformationCallback0) {
        ArrayList arrayList0 = this.transformationCallbacks;
        if(arrayList0 == null) {
            return;
        }
        arrayList0.remove(floatingActionButtonImpl$InternalTransformationCallback0);
    }

    boolean requirePreDrawListener() {
        return true;
    }

    void setBackgroundTintList(ColorStateList colorStateList0) {
        MaterialShapeDrawable materialShapeDrawable0 = this.shapeDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setTintList(colorStateList0);
        }
        BorderDrawable borderDrawable0 = this.borderDrawable;
        if(borderDrawable0 != null) {
            borderDrawable0.setBorderTint(colorStateList0);
        }
    }

    void setBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        MaterialShapeDrawable materialShapeDrawable0 = this.shapeDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setTintMode(porterDuff$Mode0);
        }
    }

    final void setElevation(float f) {
        if(this.elevation != f) {
            this.elevation = f;
            this.onElevationsChanged(f, this.hoveredFocusedTranslationZ, this.pressedTranslationZ);
        }
    }

    void setEnsureMinTouchTargetSize(boolean z) {
        this.ensureMinTouchTargetSize = z;
    }

    final void setHideMotionSpec(MotionSpec motionSpec0) {
        this.hideMotionSpec = motionSpec0;
    }

    final void setHoveredFocusedTranslationZ(float f) {
        if(this.hoveredFocusedTranslationZ != f) {
            this.hoveredFocusedTranslationZ = f;
            this.onElevationsChanged(this.elevation, f, this.pressedTranslationZ);
        }
    }

    final void setImageMatrixScale(float f) {
        this.imageMatrixScale = f;
        this.calculateImageMatrixFromScale(f, this.tmpMatrix);
        this.view.setImageMatrix(this.tmpMatrix);
    }

    final void setMaxImageSize(int v) {
        if(this.maxImageSize != v) {
            this.maxImageSize = v;
            this.updateImageMatrixScale();
        }
    }

    void setMinTouchTargetSize(int v) {
        this.minTouchTargetSize = v;
    }

    final void setPressedTranslationZ(float f) {
        if(this.pressedTranslationZ != f) {
            this.pressedTranslationZ = f;
            this.onElevationsChanged(this.elevation, this.hoveredFocusedTranslationZ, f);
        }
    }

    void setRippleColor(ColorStateList colorStateList0) {
        Drawable drawable0 = this.rippleDrawable;
        if(drawable0 != null) {
            DrawableCompat.setTintList(drawable0, RippleUtils.sanitizeRippleDrawableColor(colorStateList0));
        }
    }

    void setShadowPaddingEnabled(boolean z) {
        this.shadowPaddingEnabled = z;
        this.updatePadding();
    }

    final void setShapeAppearance(ShapeAppearanceModel shapeAppearanceModel0) {
        this.shapeAppearance = shapeAppearanceModel0;
        MaterialShapeDrawable materialShapeDrawable0 = this.shapeDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setShapeAppearanceModel(shapeAppearanceModel0);
        }
        Drawable drawable0 = this.rippleDrawable;
        if(drawable0 instanceof Shapeable) {
            ((Shapeable)drawable0).setShapeAppearanceModel(shapeAppearanceModel0);
        }
        BorderDrawable borderDrawable0 = this.borderDrawable;
        if(borderDrawable0 != null) {
            borderDrawable0.setShapeAppearanceModel(shapeAppearanceModel0);
        }
    }

    final void setShowMotionSpec(MotionSpec motionSpec0) {
        this.showMotionSpec = motionSpec0;
    }

    boolean shouldAddPadding() {
        return true;
    }

    // 去混淆评级： 低(20)
    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this.view) && !this.view.isInEditMode();
    }

    final boolean shouldExpandBoundsForA11y() {
        return !this.ensureMinTouchTargetSize || this.view.getSizeDimension() >= this.minTouchTargetSize;
    }

    void show(InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener0, boolean z) {
        if(!this.isOrWillBeShown()) {
            Animator animator0 = this.currentAnimator;
            if(animator0 != null) {
                animator0.cancel();
            }
            boolean z1 = this.showMotionSpec == null;
            if(this.shouldAnimateVisibilityChange()) {
                if(this.view.getVisibility() != 0) {
                    float f = 0.0f;
                    this.view.setAlpha(0.0f);
                    this.view.setScaleY((z1 ? 0.4f : 0.0f));
                    this.view.setScaleX((z1 ? 0.4f : 0.0f));
                    if(z1) {
                        f = 0.4f;
                    }
                    this.setImageMatrixScale(f);
                }
                MotionSpec motionSpec0 = this.showMotionSpec;
                AnimatorSet animatorSet0 = motionSpec0 == null ? this.createDefaultAnimator(1.0f, 1.0f, 1.0f, FloatingActionButtonImpl.SHOW_ANIM_DURATION_ATTR, FloatingActionButtonImpl.SHOW_ANIM_EASING_ATTR) : this.createAnimator(motionSpec0, 1.0f, 1.0f, 1.0f);
                animatorSet0.addListener(new AnimatorListenerAdapter() {
                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationEnd(Animator animator0) {
                        FloatingActionButtonImpl.this.animState = 0;
                        FloatingActionButtonImpl.this.currentAnimator = null;
                        InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener0 = floatingActionButtonImpl$InternalVisibilityChangedListener0;
                        if(floatingActionButtonImpl$InternalVisibilityChangedListener0 != null) {
                            floatingActionButtonImpl$InternalVisibilityChangedListener0.onShown();
                        }
                    }

                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationStart(Animator animator0) {
                        FloatingActionButtonImpl.this.view.internalSetVisibility(0, z);
                        FloatingActionButtonImpl.this.animState = 2;
                        FloatingActionButtonImpl.this.currentAnimator = animator0;
                    }
                });
                ArrayList arrayList0 = this.showListeners;
                if(arrayList0 != null) {
                    for(Object object0: arrayList0) {
                        animatorSet0.addListener(((Animator.AnimatorListener)object0));
                    }
                }
                animatorSet0.start();
                return;
            }
            this.view.internalSetVisibility(0, z);
            this.view.setAlpha(1.0f);
            this.view.setScaleY(1.0f);
            this.view.setScaleX(1.0f);
            this.setImageMatrixScale(1.0f);
            if(floatingActionButtonImpl$InternalVisibilityChangedListener0 != null) {
                floatingActionButtonImpl$InternalVisibilityChangedListener0.onShown();
            }
        }
    }

    void updateFromViewRotation() {
        MaterialShapeDrawable materialShapeDrawable0 = this.shapeDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setShadowCompatRotation(((int)this.rotation));
        }
    }

    final void updateImageMatrixScale() {
        this.setImageMatrixScale(this.imageMatrixScale);
    }

    final void updatePadding() {
        this.getPadding(this.tmpRect);
        this.onPaddingUpdated(this.tmpRect);
        this.shadowViewDelegate.setShadowPadding(this.tmpRect.left, this.tmpRect.top, this.tmpRect.right, this.tmpRect.bottom);
    }

    void updateShapeElevation(float f) {
        MaterialShapeDrawable materialShapeDrawable0 = this.shapeDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setElevation(f);
        }
    }

    private void workAroundOreoBug(ObjectAnimator objectAnimator0) {
        if(Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator0.setEvaluator(new TypeEvaluator() {
            FloatEvaluator floatEvaluator;

            {
                this.floatEvaluator = new FloatEvaluator();
            }

            public Float evaluate(float f, Float float0, Float float1) {
                float f1 = (float)this.floatEvaluator.evaluate(f, float0, float1);
                if(f1 < 0.1f) {
                    f1 = 0.0f;
                }
                return f1;
            }

            @Override  // android.animation.TypeEvaluator
            public Object evaluate(float f, Object object0, Object object1) {
                return this.evaluate(f, ((Float)object0), ((Float)object1));
            }
        });
    }
}

