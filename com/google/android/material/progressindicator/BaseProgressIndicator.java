package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.ProgressBar;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public abstract class BaseProgressIndicator extends ProgressBar {
    @Retention(RetentionPolicy.SOURCE)
    public @interface HideAnimationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowAnimationBehavior {
    }

    static final float DEFAULT_OPACITY = 0.2f;
    static final int DEF_STYLE_RES = 0;
    public static final int HIDE_ESCAPE = 3;
    public static final int HIDE_INWARD = 2;
    public static final int HIDE_NONE = 0;
    public static final int HIDE_OUTWARD = 1;
    static final int MAX_ALPHA = 0xFF;
    static final int MAX_HIDE_DELAY = 1000;
    public static final int SHOW_INWARD = 2;
    public static final int SHOW_NONE = 0;
    public static final int SHOW_OUTWARD = 1;
    AnimatorDurationScaleProvider animatorDurationScaleProvider;
    private final Runnable delayedHide;
    private final Runnable delayedShow;
    private final AnimationCallback hideAnimationCallback;
    private boolean isIndeterminateModeChangeRequested;
    private boolean isParentDoneInitializing;
    private long lastShowStartTime;
    private final int minHideDelay;
    private final int showDelay;
    BaseProgressIndicatorSpec spec;
    private int storedProgress;
    private boolean storedProgressAnimated;
    private final AnimationCallback switchIndeterminateModeCallback;
    private int visibilityAfterHide;

    static {
        BaseProgressIndicator.DEF_STYLE_RES = style.Widget_MaterialComponents_ProgressIndicator;
    }

    protected BaseProgressIndicator(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, BaseProgressIndicator.DEF_STYLE_RES), attributeSet0, v);
        this.lastShowStartTime = -1L;
        this.isIndeterminateModeChangeRequested = false;
        this.visibilityAfterHide = 4;
        this.delayedShow = () -> {
            if(BaseProgressIndicator.this.minHideDelay > 0) {
                BaseProgressIndicator.this.lastShowStartTime = SystemClock.uptimeMillis();
            }
            BaseProgressIndicator.this.setVisibility(0);
        };
        this.delayedHide = new Runnable() {
            @Override
            public void run() {
                BaseProgressIndicator.this.internalHide();
                BaseProgressIndicator.this.lastShowStartTime = -1L;
            }
        };
        this.switchIndeterminateModeCallback = new AnimationCallback() {
            @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
            public void onAnimationEnd(Drawable drawable0) {
                BaseProgressIndicator.this.setIndeterminate(false);
                int v = BaseProgressIndicator.this.storedProgress;
                boolean z = BaseProgressIndicator.this.storedProgressAnimated;
                BaseProgressIndicator.this.setProgressCompat(v, z);
            }
        };
        this.hideAnimationCallback = new AnimationCallback() {
            @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
            public void onAnimationEnd(Drawable drawable0) {
                super.onAnimationEnd(drawable0);
                if(!BaseProgressIndicator.this.isIndeterminateModeChangeRequested) {
                    BaseProgressIndicator.this.setVisibility(BaseProgressIndicator.this.visibilityAfterHide);
                }
            }
        };
        Context context1 = this.getContext();
        this.spec = this.createSpec(context1, attributeSet0);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.BaseProgressIndicator, v, v1, new int[0]);
        this.showDelay = typedArray0.getInt(styleable.BaseProgressIndicator_showDelay, -1);
        this.minHideDelay = Math.min(typedArray0.getInt(styleable.BaseProgressIndicator_minHideDelay, -1), 1000);
        typedArray0.recycle();
        this.animatorDurationScaleProvider = new AnimatorDurationScaleProvider();
        this.isParentDoneInitializing = true;
    }

    protected void applyNewVisibility(boolean z) {
        if(!this.isParentDoneInitializing) {
            return;
        }
        ((DrawableWithAnimatedVisibilityChange)this.getCurrentDrawable()).setVisible(this.visibleToUser(), false, z);
    }

    abstract BaseProgressIndicatorSpec createSpec(Context arg1, AttributeSet arg2);

    @Override  // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        return this.isIndeterminate() ? this.getIndeterminateDrawable() : this.getProgressDrawable();
    }

    private DrawingDelegate getCurrentDrawingDelegate() {
        if(this.isIndeterminate()) {
            return this.getIndeterminateDrawable() == null ? null : this.getIndeterminateDrawable().getDrawingDelegate();
        }
        return this.getProgressDrawable() == null ? null : this.getProgressDrawable().getDrawingDelegate();
    }

    public int getHideAnimationBehavior() {
        return this.spec.hideAnimationBehavior;
    }

    @Override  // android.widget.ProgressBar
    public Drawable getIndeterminateDrawable() {
        return this.getIndeterminateDrawable();
    }

    public IndeterminateDrawable getIndeterminateDrawable() {
        return (IndeterminateDrawable)super.getIndeterminateDrawable();
    }

    public int[] getIndicatorColor() {
        return this.spec.indicatorColors;
    }

    public int getIndicatorTrackGapSize() {
        return this.spec.indicatorTrackGapSize;
    }

    @Override  // android.widget.ProgressBar
    public Drawable getProgressDrawable() {
        return this.getProgressDrawable();
    }

    public DeterminateDrawable getProgressDrawable() {
        return (DeterminateDrawable)super.getProgressDrawable();
    }

    public int getShowAnimationBehavior() {
        return this.spec.showAnimationBehavior;
    }

    public int getTrackColor() {
        return this.spec.trackColor;
    }

    public int getTrackCornerRadius() {
        return this.spec.trackCornerRadius;
    }

    public int getTrackThickness() {
        return this.spec.trackThickness;
    }

    public void hide() {
        if(this.getVisibility() != 0) {
            this.removeCallbacks(this.delayedShow);
            return;
        }
        this.removeCallbacks(this.delayedHide);
        long v = SystemClock.uptimeMillis() - this.lastShowStartTime;
        int v1 = this.minHideDelay;
        if(v >= ((long)v1)) {
            this.delayedHide.run();
            return;
        }
        this.postDelayed(this.delayedHide, ((long)v1) - v);
    }

    private void internalHide() {
        ((DrawableWithAnimatedVisibilityChange)this.getCurrentDrawable()).setVisible(false, false, true);
        if(this.isNoLongerNeedToBeVisible()) {
            this.setVisibility(4);
        }
    }

    // 检测为 Lambda 实现
    private void internalShow() [...]

    @Override  // android.view.View
    public void invalidate() {
        super.invalidate();
        if(this.getCurrentDrawable() != null) {
            this.getCurrentDrawable().invalidateSelf();
        }
    }

    boolean isEffectivelyVisible() {
        for(View view0 = this; true; view0 = (View)viewParent0) {
            if(view0.getVisibility() != 0) {
                return false;
            }
            ViewParent viewParent0 = view0.getParent();
            if(viewParent0 == null) {
                return this.getWindowVisibility() == 0;
            }
            if(!(viewParent0 instanceof View)) {
                return true;
            }
        }
    }

    // 去混淆评级： 低(30)
    private boolean isNoLongerNeedToBeVisible() {
        return (this.getProgressDrawable() == null || !this.getProgressDrawable().isVisible()) && (this.getIndeterminateDrawable() == null || !this.getIndeterminateDrawable().isVisible());
    }

    @Override  // android.widget.ProgressBar
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.registerAnimationCallbacks();
        if(this.visibleToUser()) {
            this.internalShow();
        }
    }

    @Override  // android.widget.ProgressBar
    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.delayedHide);
        this.removeCallbacks(this.delayedShow);
        ((DrawableWithAnimatedVisibilityChange)this.getCurrentDrawable()).hideNow();
        this.unregisterAnimationCallbacks();
        super.onDetachedFromWindow();
    }

    @Override  // android.widget.ProgressBar
    protected void onDraw(Canvas canvas0) {
        synchronized(this) {
            int v1 = canvas0.save();
            if(this.getPaddingLeft() != 0 || this.getPaddingTop() != 0) {
                canvas0.translate(((float)this.getPaddingLeft()), ((float)this.getPaddingTop()));
            }
            if(this.getPaddingRight() != 0 || this.getPaddingBottom() != 0) {
                canvas0.clipRect(0, 0, this.getWidth() - (this.getPaddingLeft() + this.getPaddingRight()), this.getHeight() - (this.getPaddingTop() + this.getPaddingBottom()));
            }
            this.getCurrentDrawable().draw(canvas0);
            canvas0.restoreToCount(v1);
        }
    }

    @Override  // android.widget.ProgressBar
    protected void onMeasure(int v, int v1) {
        synchronized(this) {
            DrawingDelegate drawingDelegate0 = this.getCurrentDrawingDelegate();
            if(drawingDelegate0 == null) {
                return;
            }
            this.setMeasuredDimension((drawingDelegate0.getPreferredWidth() >= 0 ? drawingDelegate0.getPreferredWidth() + this.getPaddingLeft() + this.getPaddingRight() : BaseProgressIndicator.getDefaultSize(this.getSuggestedMinimumWidth(), v)), (drawingDelegate0.getPreferredHeight() >= 0 ? drawingDelegate0.getPreferredHeight() + this.getPaddingTop() + this.getPaddingBottom() : BaseProgressIndicator.getDefaultSize(this.getSuggestedMinimumHeight(), v1)));
        }
    }

    @Override  // android.view.View
    protected void onVisibilityChanged(View view0, int v) {
        super.onVisibilityChanged(view0, v);
        this.applyNewVisibility(v == 0);
    }

    @Override  // android.view.View
    protected void onWindowVisibilityChanged(int v) {
        super.onWindowVisibilityChanged(v);
        this.applyNewVisibility(false);
    }

    private void registerAnimationCallbacks() {
        if(this.getProgressDrawable() != null && this.getIndeterminateDrawable() != null) {
            this.getIndeterminateDrawable().getAnimatorDelegate().registerAnimatorsCompleteCallback(this.switchIndeterminateModeCallback);
        }
        if(this.getProgressDrawable() != null) {
            this.getProgressDrawable().registerAnimationCallback(this.hideAnimationCallback);
        }
        if(this.getIndeterminateDrawable() != null) {
            this.getIndeterminateDrawable().registerAnimationCallback(this.hideAnimationCallback);
        }
    }

    public void setAnimatorDurationScaleProvider(AnimatorDurationScaleProvider animatorDurationScaleProvider0) {
        this.animatorDurationScaleProvider = animatorDurationScaleProvider0;
        if(this.getProgressDrawable() != null) {
            this.getProgressDrawable().animatorDurationScaleProvider = animatorDurationScaleProvider0;
        }
        if(this.getIndeterminateDrawable() != null) {
            this.getIndeterminateDrawable().animatorDurationScaleProvider = animatorDurationScaleProvider0;
        }
    }

    public void setHideAnimationBehavior(int v) {
        this.spec.hideAnimationBehavior = v;
        this.invalidate();
    }

    @Override  // android.widget.ProgressBar
    public void setIndeterminate(boolean z) {
        synchronized(this) {
            if(z == this.isIndeterminate()) {
                return;
            }
            DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange0 = (DrawableWithAnimatedVisibilityChange)this.getCurrentDrawable();
            if(drawableWithAnimatedVisibilityChange0 != null) {
                drawableWithAnimatedVisibilityChange0.hideNow();
            }
            super.setIndeterminate(z);
            DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange1 = (DrawableWithAnimatedVisibilityChange)this.getCurrentDrawable();
            if(drawableWithAnimatedVisibilityChange1 != null) {
                drawableWithAnimatedVisibilityChange1.setVisible(this.visibleToUser(), false, false);
            }
            if(drawableWithAnimatedVisibilityChange1 instanceof IndeterminateDrawable && this.visibleToUser()) {
                ((IndeterminateDrawable)drawableWithAnimatedVisibilityChange1).getAnimatorDelegate().startAnimator();
            }
            this.isIndeterminateModeChangeRequested = false;
        }
    }

    @Override  // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable0) {
        if(drawable0 == null) {
            super.setIndeterminateDrawable(null);
            return;
        }
        if(!(drawable0 instanceof IndeterminateDrawable)) {
            throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
        }
        ((DrawableWithAnimatedVisibilityChange)drawable0).hideNow();
        super.setIndeterminateDrawable(drawable0);
    }

    public void setIndicatorColor(int[] arr_v) {
        if(arr_v.length == 0) {
            arr_v = new int[]{MaterialColors.getColor(this.getContext(), attr.colorPrimary, -1)};
        }
        if(!Arrays.equals(this.getIndicatorColor(), arr_v)) {
            this.spec.indicatorColors = arr_v;
            this.getIndeterminateDrawable().getAnimatorDelegate().invalidateSpecValues();
            this.invalidate();
        }
    }

    public void setIndicatorTrackGapSize(int v) {
        if(this.spec.indicatorTrackGapSize != v) {
            this.spec.indicatorTrackGapSize = v;
            this.spec.validateSpec();
            this.invalidate();
        }
    }

    @Override  // android.widget.ProgressBar
    public void setProgress(int v) {
        synchronized(this) {
            if(this.isIndeterminate()) {
                return;
            }
            this.setProgressCompat(v, false);
        }
    }

    public void setProgressCompat(int v, boolean z) {
        if(!this.isIndeterminate()) {
            super.setProgress(v);
            if(this.getProgressDrawable() != null && !z) {
                this.getProgressDrawable().jumpToCurrentState();
            }
        }
        else if(this.getProgressDrawable() != null) {
            this.storedProgress = v;
            this.storedProgressAnimated = z;
            this.isIndeterminateModeChangeRequested = true;
            if(this.getIndeterminateDrawable().isVisible() && this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.getContext().getContentResolver()) != 0.0f) {
                this.getIndeterminateDrawable().getAnimatorDelegate().requestCancelAnimatorAfterCurrentCycle();
                return;
            }
            IndeterminateDrawable indeterminateDrawable0 = this.getIndeterminateDrawable();
            this.switchIndeterminateModeCallback.onAnimationEnd(indeterminateDrawable0);
        }
    }

    @Override  // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable0) {
        if(drawable0 == null) {
            super.setProgressDrawable(null);
            return;
        }
        if(!(drawable0 instanceof DeterminateDrawable)) {
            throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
        }
        ((DeterminateDrawable)drawable0).hideNow();
        super.setProgressDrawable(((DeterminateDrawable)drawable0));
        ((DeterminateDrawable)drawable0).setLevelByFraction(((float)this.getProgress()) / ((float)this.getMax()));
    }

    public void setShowAnimationBehavior(int v) {
        this.spec.showAnimationBehavior = v;
        this.invalidate();
    }

    public void setTrackColor(int v) {
        if(this.spec.trackColor != v) {
            this.spec.trackColor = v;
            this.invalidate();
        }
    }

    public void setTrackCornerRadius(int v) {
        if(this.spec.trackCornerRadius != v) {
            this.spec.trackCornerRadius = Math.min(v, this.spec.trackThickness / 2);
            this.invalidate();
        }
    }

    public void setTrackThickness(int v) {
        if(this.spec.trackThickness != v) {
            this.spec.trackThickness = v;
            this.requestLayout();
        }
    }

    public void setVisibilityAfterHide(int v) {
        if(v != 0 && v != 4 && v != 8) {
            throw new IllegalArgumentException("The component\'s visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.visibilityAfterHide = v;
    }

    public void show() {
        if(this.showDelay > 0) {
            this.removeCallbacks(this.delayedShow);
            this.postDelayed(this.delayedShow, ((long)this.showDelay));
            return;
        }
        this.delayedShow.run();
    }

    private void unregisterAnimationCallbacks() {
        if(this.getIndeterminateDrawable() != null) {
            this.getIndeterminateDrawable().unregisterAnimationCallback(this.hideAnimationCallback);
            this.getIndeterminateDrawable().getAnimatorDelegate().unregisterAnimatorsCompleteCallback();
        }
        if(this.getProgressDrawable() != null) {
            this.getProgressDrawable().unregisterAnimationCallback(this.hideAnimationCallback);
        }
    }

    // 去混淆评级： 低(20)
    boolean visibleToUser() {
        return ViewCompat.isAttachedToWindow(this) && this.getWindowVisibility() == 0 && this.isEffectivelyVisible();
    }

    class com.google.android.material.progressindicator.BaseProgressIndicator.1 implements Runnable {
        @Override
        public void run() {
            BaseProgressIndicator.this.internalShow();
        }
    }

}

