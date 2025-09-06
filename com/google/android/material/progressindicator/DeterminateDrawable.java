package com.google.android.material.progressindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;

public final class DeterminateDrawable extends DrawableWithAnimatedVisibilityChange {
    static final float GAP_RAMP_DOWN_THRESHOLD = 0.01f;
    private static final FloatPropertyCompat INDICATOR_LENGTH_IN_LEVEL = null;
    private static final int MAX_DRAWABLE_LEVEL = 10000;
    private static final float SPRING_FORCE_STIFFNESS = 50.0f;
    private final ActiveIndicator activeIndicator;
    private DrawingDelegate drawingDelegate;
    private boolean skipAnimationOnLevelChange;
    private final SpringAnimation springAnimation;
    private final SpringForce springForce;

    static {
        DeterminateDrawable.INDICATOR_LENGTH_IN_LEVEL = new FloatPropertyCompat("indicatorLevel") {
            public float getValue(DeterminateDrawable determinateDrawable0) {
                return determinateDrawable0.getIndicatorFraction() * 10000.0f;
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((DeterminateDrawable)object0));
            }

            public void setValue(DeterminateDrawable determinateDrawable0, float f) {
                determinateDrawable0.setIndicatorFraction(f / 10000.0f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((DeterminateDrawable)object0), f);
            }
        };
    }

    DeterminateDrawable(Context context0, BaseProgressIndicatorSpec baseProgressIndicatorSpec0, DrawingDelegate drawingDelegate0) {
        super(context0, baseProgressIndicatorSpec0);
        this.skipAnimationOnLevelChange = false;
        this.setDrawingDelegate(drawingDelegate0);
        this.activeIndicator = new ActiveIndicator();
        SpringForce springForce0 = new SpringForce();
        this.springForce = springForce0;
        springForce0.setDampingRatio(1.0f);
        springForce0.setStiffness(50.0f);
        SpringAnimation springAnimation0 = new SpringAnimation(this, DeterminateDrawable.INDICATOR_LENGTH_IN_LEVEL);
        this.springAnimation = springAnimation0;
        springAnimation0.setSpring(springForce0);
        this.setGrowFraction(1.0f);
    }

    public void addSpringAnimationEndListener(OnAnimationEndListener dynamicAnimation$OnAnimationEndListener0) {
        this.springAnimation.addEndListener(dynamicAnimation$OnAnimationEndListener0);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void clearAnimationCallbacks() {
        super.clearAnimationCallbacks();
    }

    public static DeterminateDrawable createCircularDrawable(Context context0, CircularProgressIndicatorSpec circularProgressIndicatorSpec0) {
        return DeterminateDrawable.createCircularDrawable(context0, circularProgressIndicatorSpec0, new CircularDrawingDelegate(circularProgressIndicatorSpec0));
    }

    static DeterminateDrawable createCircularDrawable(Context context0, CircularProgressIndicatorSpec circularProgressIndicatorSpec0, CircularDrawingDelegate circularDrawingDelegate0) {
        return new DeterminateDrawable(context0, circularProgressIndicatorSpec0, circularDrawingDelegate0);
    }

    public static DeterminateDrawable createLinearDrawable(Context context0, LinearProgressIndicatorSpec linearProgressIndicatorSpec0) {
        return DeterminateDrawable.createLinearDrawable(context0, linearProgressIndicatorSpec0, new LinearDrawingDelegate(linearProgressIndicatorSpec0));
    }

    static DeterminateDrawable createLinearDrawable(Context context0, LinearProgressIndicatorSpec linearProgressIndicatorSpec0, LinearDrawingDelegate linearDrawingDelegate0) {
        return new DeterminateDrawable(context0, linearProgressIndicatorSpec0, linearDrawingDelegate0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        Rect rect0 = new Rect();
        if(!this.getBounds().isEmpty() && this.isVisible() && canvas0.getClipBounds(rect0)) {
            canvas0.save();
            this.drawingDelegate.validateSpecAndAdjustCanvas(canvas0, this.getBounds(), this.getGrowFraction(), this.isShowing(), this.isHiding());
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setAntiAlias(true);
            this.activeIndicator.color = this.baseSpec.indicatorColors[0];
            if(this.baseSpec.indicatorTrackGapSize > 0) {
                int v = this.drawingDelegate instanceof LinearDrawingDelegate ? this.baseSpec.indicatorTrackGapSize : ((int)(((float)this.baseSpec.indicatorTrackGapSize) * MathUtils.clamp(this.getIndicatorFraction(), 0.0f, 0.01f) / 0.01f));
                this.drawingDelegate.fillTrack(canvas0, this.paint, this.getIndicatorFraction(), 1.0f, this.baseSpec.trackColor, this.getAlpha(), v);
            }
            else {
                this.drawingDelegate.fillTrack(canvas0, this.paint, 0.0f, 1.0f, this.baseSpec.trackColor, this.getAlpha(), 0);
            }
            DrawingDelegate drawingDelegate0 = this.drawingDelegate;
            Paint paint0 = this.paint;
            int v1 = this.getAlpha();
            drawingDelegate0.fillIndicator(canvas0, paint0, this.activeIndicator, v1);
            this.drawingDelegate.drawStopIndicator(canvas0, this.paint, this.baseSpec.indicatorColors[0], this.getAlpha());
            canvas0.restore();
        }
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public int getAlpha() {
        return super.getAlpha();
    }

    DrawingDelegate getDrawingDelegate() {
        return this.drawingDelegate;
    }

    private float getIndicatorFraction() {
        return this.activeIndicator.endFraction;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.drawingDelegate.getPreferredWidth();
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public int getOpacity() {
        return super.getOpacity();
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean hideNow() {
        return super.hideNow();
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean isHiding() {
        return super.isHiding();
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean isRunning() {
        return super.isRunning();
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override  // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.springAnimation.skipToEnd();
        this.setIndicatorFraction(((float)this.getLevel()) / 10000.0f);
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int v) {
        if(this.skipAnimationOnLevelChange) {
            this.springAnimation.skipToEnd();
            this.setIndicatorFraction(((float)v) / 10000.0f);
            return true;
        }
        this.springAnimation.setStartValue(this.getIndicatorFraction() * 10000.0f);
        this.springAnimation.animateToFinalPosition(((float)v));
        return true;
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void registerAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        super.registerAnimationCallback(animatable2Compat$AnimationCallback0);
    }

    public void removeSpringAnimationEndListener(OnAnimationEndListener dynamicAnimation$OnAnimationEndListener0) {
        this.springAnimation.removeEndListener(dynamicAnimation$OnAnimationEndListener0);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void setAlpha(int v) {
        super.setAlpha(v);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void setColorFilter(ColorFilter colorFilter0) {
        super.setColorFilter(colorFilter0);
    }

    void setDrawingDelegate(DrawingDelegate drawingDelegate0) {
        this.drawingDelegate = drawingDelegate0;
    }

    private void setIndicatorFraction(float f) {
        this.activeIndicator.endFraction = f;
        this.invalidateSelf();
    }

    void setLevelByFraction(float f) {
        this.setLevel(((int)(f * 10000.0f)));
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean setVisible(boolean z, boolean z1) {
        return super.setVisible(z, z1);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean setVisible(boolean z, boolean z1, boolean z2) {
        return super.setVisible(z, z1, z2);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    boolean setVisibleInternal(boolean z, boolean z1, boolean z2) {
        boolean z3 = super.setVisibleInternal(z, z1, z2);
        float f = this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver());
        if(f == 0.0f) {
            this.skipAnimationOnLevelChange = true;
            return z3;
        }
        this.skipAnimationOnLevelChange = false;
        this.springForce.setStiffness(50.0f / f);
        return z3;
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void start() {
        super.start();
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void stop() {
        super.stop();
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean unregisterAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        return super.unregisterAnimationCallback(animatable2Compat$AnimationCallback0);
    }
}

