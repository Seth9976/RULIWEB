package com.google.android.material.progressindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.R.drawable;

public final class IndeterminateDrawable extends DrawableWithAnimatedVisibilityChange {
    private IndeterminateAnimatorDelegate animatorDelegate;
    private DrawingDelegate drawingDelegate;
    private Drawable staticDummyDrawable;

    IndeterminateDrawable(Context context0, BaseProgressIndicatorSpec baseProgressIndicatorSpec0, DrawingDelegate drawingDelegate0, IndeterminateAnimatorDelegate indeterminateAnimatorDelegate0) {
        super(context0, baseProgressIndicatorSpec0);
        this.setDrawingDelegate(drawingDelegate0);
        this.setAnimatorDelegate(indeterminateAnimatorDelegate0);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void clearAnimationCallbacks() {
        super.clearAnimationCallbacks();
    }

    public static IndeterminateDrawable createCircularDrawable(Context context0, CircularProgressIndicatorSpec circularProgressIndicatorSpec0) {
        return IndeterminateDrawable.createCircularDrawable(context0, circularProgressIndicatorSpec0, new CircularDrawingDelegate(circularProgressIndicatorSpec0));
    }

    static IndeterminateDrawable createCircularDrawable(Context context0, CircularProgressIndicatorSpec circularProgressIndicatorSpec0, CircularDrawingDelegate circularDrawingDelegate0) {
        IndeterminateDrawable indeterminateDrawable0 = new IndeterminateDrawable(context0, circularProgressIndicatorSpec0, circularDrawingDelegate0, new CircularIndeterminateAnimatorDelegate(circularProgressIndicatorSpec0));
        indeterminateDrawable0.setStaticDummyDrawable(VectorDrawableCompat.create(context0.getResources(), drawable.indeterminate_static, null));
        return indeterminateDrawable0;
    }

    public static IndeterminateDrawable createLinearDrawable(Context context0, LinearProgressIndicatorSpec linearProgressIndicatorSpec0) {
        return IndeterminateDrawable.createLinearDrawable(context0, linearProgressIndicatorSpec0, new LinearDrawingDelegate(linearProgressIndicatorSpec0));
    }

    static IndeterminateDrawable createLinearDrawable(Context context0, LinearProgressIndicatorSpec linearProgressIndicatorSpec0, LinearDrawingDelegate linearDrawingDelegate0) {
        return linearProgressIndicatorSpec0.indeterminateAnimationType == 0 ? new IndeterminateDrawable(context0, linearProgressIndicatorSpec0, linearDrawingDelegate0, new LinearIndeterminateContiguousAnimatorDelegate(linearProgressIndicatorSpec0)) : new IndeterminateDrawable(context0, linearProgressIndicatorSpec0, linearDrawingDelegate0, new LinearIndeterminateDisjointAnimatorDelegate(context0, linearProgressIndicatorSpec0));
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        int v3;
        Rect rect0 = new Rect();
        if(!this.getBounds().isEmpty() && this.isVisible() && canvas0.getClipBounds(rect0)) {
            if(this.isSystemAnimatorDisabled()) {
                Drawable drawable0 = this.staticDummyDrawable;
                if(drawable0 != null) {
                    drawable0.setBounds(this.getBounds());
                    DrawableCompat.setTint(this.staticDummyDrawable, this.baseSpec.indicatorColors[0]);
                    this.staticDummyDrawable.draw(canvas0);
                    return;
                }
            }
            canvas0.save();
            this.drawingDelegate.validateSpecAndAdjustCanvas(canvas0, this.getBounds(), this.getGrowFraction(), this.isShowing(), this.isHiding());
            int v1 = this.baseSpec.indicatorTrackGapSize;
            int v2 = this.getAlpha();
            if(v1 == 0) {
                this.drawingDelegate.fillTrack(canvas0, this.paint, 0.0f, 1.0f, this.baseSpec.trackColor, v2, 0);
                v3 = 0;
            }
            else {
                ActiveIndicator drawingDelegate$ActiveIndicator0 = (ActiveIndicator)this.animatorDelegate.activeIndicators.get(0);
                Object object0 = this.animatorDelegate.activeIndicators.get(this.animatorDelegate.activeIndicators.size() - 1);
                DrawingDelegate drawingDelegate0 = this.drawingDelegate;
                if(drawingDelegate0 instanceof LinearDrawingDelegate) {
                    v3 = v1;
                    drawingDelegate0.fillTrack(canvas0, this.paint, 0.0f, drawingDelegate$ActiveIndicator0.startFraction, this.baseSpec.trackColor, v2, v3);
                    this.drawingDelegate.fillTrack(canvas0, this.paint, ((ActiveIndicator)object0).endFraction, 1.0f, this.baseSpec.trackColor, v2, v3);
                }
                else {
                    v3 = v1;
                    v2 = 0;
                    drawingDelegate0.fillTrack(canvas0, this.paint, ((ActiveIndicator)object0).endFraction, drawingDelegate$ActiveIndicator0.startFraction + 1.0f, this.baseSpec.trackColor, 0, v3);
                }
            }
            for(int v = 0; v < this.animatorDelegate.activeIndicators.size(); ++v) {
                ActiveIndicator drawingDelegate$ActiveIndicator1 = (ActiveIndicator)this.animatorDelegate.activeIndicators.get(v);
                this.drawingDelegate.fillIndicator(canvas0, this.paint, drawingDelegate$ActiveIndicator1, this.getAlpha());
                if(v > 0 && v3 > 0) {
                    ActiveIndicator drawingDelegate$ActiveIndicator2 = (ActiveIndicator)this.animatorDelegate.activeIndicators.get(v - 1);
                    this.drawingDelegate.fillTrack(canvas0, this.paint, drawingDelegate$ActiveIndicator2.endFraction, drawingDelegate$ActiveIndicator1.startFraction, this.baseSpec.trackColor, v2, v3);
                }
            }
            canvas0.restore();
        }
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public int getAlpha() {
        return super.getAlpha();
    }

    IndeterminateAnimatorDelegate getAnimatorDelegate() {
        return this.animatorDelegate;
    }

    DrawingDelegate getDrawingDelegate() {
        return this.drawingDelegate;
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

    public Drawable getStaticDummyDrawable() {
        return this.staticDummyDrawable;
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

    private boolean isSystemAnimatorDisabled() {
        return this.animatorDurationScaleProvider != null && this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver()) == 0.0f;
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void registerAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        super.registerAnimationCallback(animatable2Compat$AnimationCallback0);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void setAlpha(int v) {
        super.setAlpha(v);
    }

    void setAnimatorDelegate(IndeterminateAnimatorDelegate indeterminateAnimatorDelegate0) {
        this.animatorDelegate = indeterminateAnimatorDelegate0;
        indeterminateAnimatorDelegate0.registerDrawable(this);
    }

    @Override  // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public void setColorFilter(ColorFilter colorFilter0) {
        super.setColorFilter(colorFilter0);
    }

    void setDrawingDelegate(DrawingDelegate drawingDelegate0) {
        this.drawingDelegate = drawingDelegate0;
    }

    public void setStaticDummyDrawable(Drawable drawable0) {
        this.staticDummyDrawable = drawable0;
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
        if(this.isSystemAnimatorDisabled()) {
            Drawable drawable0 = this.staticDummyDrawable;
            if(drawable0 != null) {
                return drawable0.setVisible(z, z1);
            }
        }
        if(!this.isRunning()) {
            this.animatorDelegate.cancelAnimatorImmediately();
        }
        if(z && (z2 || Build.VERSION.SDK_INT <= 22 && !this.isSystemAnimatorDisabled())) {
            this.animatorDelegate.startAnimator();
        }
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

