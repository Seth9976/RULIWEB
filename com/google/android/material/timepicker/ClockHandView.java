package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.math.MathUtils;
import com.google.android.material.motion.MotionUtils;
import java.util.ArrayList;
import java.util.List;

class ClockHandView extends View {
    public interface OnActionUpListener {
        void onActionUp(float arg1, boolean arg2);
    }

    public interface OnRotateListener {
        void onRotate(float arg1, boolean arg2);
    }

    private static final int DEFAULT_ANIMATION_DURATION = 200;
    private boolean animatingOnTouchUp;
    private final int animationDuration;
    private final TimeInterpolator animationInterpolator;
    private final float centerDotRadius;
    private boolean changedDuringTouch;
    private int circleRadius;
    private int currentLevel;
    private double degRad;
    private float downX;
    private float downY;
    private boolean isInTapRegion;
    private boolean isMultiLevel;
    private final List listeners;
    private OnActionUpListener onActionUpListener;
    private float originalDeg;
    private final Paint paint;
    private final ValueAnimator rotationAnimator;
    private final int scaledTouchSlop;
    private final RectF selectorBox;
    private final int selectorRadius;
    private final int selectorStrokeWidth;

    public ClockHandView(Context context0) {
        this(context0, null);
    }

    public ClockHandView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialClockStyle);
    }

    public ClockHandView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.rotationAnimator = new ValueAnimator();
        this.listeners = new ArrayList();
        Paint paint0 = new Paint();
        this.paint = paint0;
        this.selectorBox = new RectF();
        this.currentLevel = 1;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ClockHandView, v, style.Widget_MaterialComponents_TimePicker_Clock);
        this.animationDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationLong2, 200);
        this.animationInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.circleRadius = typedArray0.getDimensionPixelSize(styleable.ClockHandView_materialCircleRadius, 0);
        this.selectorRadius = typedArray0.getDimensionPixelSize(styleable.ClockHandView_selectorSize, 0);
        Resources resources0 = this.getResources();
        this.selectorStrokeWidth = resources0.getDimensionPixelSize(dimen.material_clock_hand_stroke_width);
        this.centerDotRadius = (float)resources0.getDimensionPixelSize(dimen.material_clock_hand_center_dot_radius);
        int v1 = typedArray0.getColor(styleable.ClockHandView_clockHandColor, 0);
        paint0.setAntiAlias(true);
        paint0.setColor(v1);
        this.setHandRotation(0.0f);
        this.scaledTouchSlop = ViewConfiguration.get(context0).getScaledTouchSlop();
        ViewCompat.setImportantForAccessibility(this, 2);
        typedArray0.recycle();
    }

    public void addOnRotateListener(OnRotateListener clockHandView$OnRotateListener0) {
        this.listeners.add(clockHandView$OnRotateListener0);
    }

    private void adjustLevel(float f, float f1) {
        this.currentLevel = MathUtils.dist(this.getWidth() / 2, this.getHeight() / 2, f, f1) <= ((float)this.getLeveledCircleRadius(2)) + ViewUtils.dpToPx(this.getContext(), 12) ? 2 : 1;
    }

    private void drawSelector(Canvas canvas0) {
        int v = this.getHeight();
        int v1 = this.getWidth();
        int v2 = this.getLeveledCircleRadius(this.currentLevel);
        float f = ((float)Math.cos(this.degRad)) * ((float)v2) + ((float)(v1 / 2));
        float f1 = ((float)v2) * ((float)Math.sin(this.degRad)) + ((float)(v / 2));
        this.paint.setStrokeWidth(0.0f);
        canvas0.drawCircle(f, f1, ((float)this.selectorRadius), this.paint);
        double f2 = Math.sin(this.degRad);
        double f3 = Math.cos(this.degRad);
        double f4 = (double)(((float)(v2 - this.selectorRadius)));
        this.paint.setStrokeWidth(((float)this.selectorStrokeWidth));
        canvas0.drawLine(((float)(v1 / 2)), ((float)(v / 2)), ((float)(v1 / 2 + ((int)(f3 * f4)))), ((float)(v / 2 + ((int)(f4 * f2)))), this.paint);
        canvas0.drawCircle(((float)(v1 / 2)), ((float)(v / 2)), this.centerDotRadius, this.paint);
    }

    int getCurrentLevel() {
        return this.currentLevel;
    }

    public RectF getCurrentSelectorBox() {
        return this.selectorBox;
    }

    private int getDegreesFromXY(float f, float f1) {
        int v = this.getWidth();
        int v1 = (int)Math.toDegrees(Math.atan2(f1 - ((float)(this.getHeight() / 2)), f - ((float)(v / 2))));
        return v1 + 90 >= 0 ? v1 + 90 : v1 + 450;
    }

    public float getHandRotation() {
        return this.originalDeg;
    }

    private int getLeveledCircleRadius(int v) {
        return v == 2 ? Math.round(((float)this.circleRadius) * 0.66f) : this.circleRadius;
    }

    public int getSelectorRadius() {
        return this.selectorRadius;
    }

    private Pair getValuesForAnimation(float f) {
        float f1 = this.getHandRotation();
        if(Math.abs(f1 - f) > 180.0f) {
            if(f1 > 180.0f && f < 180.0f) {
                f += 360.0f;
            }
            if(f1 < 180.0f && f > 180.0f) {
                f1 += 360.0f;
            }
        }
        return new Pair(f1, f);
    }

    private boolean handleTouchInput(float f, float f1, boolean z, boolean z1, boolean z2) {
        int v = this.getDegreesFromXY(f, f1);
        boolean z3 = false;
        boolean z4 = this.getHandRotation() != ((float)v);
        if(z1 && z4) {
            return true;
        }
        if(!z4 && !z) {
            return false;
        }
        if(z2 && this.animatingOnTouchUp) {
            z3 = true;
        }
        this.setHandRotation(((float)v), z3);
        return true;
    }

    // 检测为 Lambda 实现
    void lambda$setHandRotation$0$com-google-android-material-timepicker-ClockHandView(ValueAnimator valueAnimator0) [...]

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        this.drawSelector(canvas0);
    }

    @Override  // android.view.View
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        if(!this.rotationAnimator.isRunning()) {
            this.setHandRotation(this.getHandRotation());
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        boolean z2;
        boolean z1;
        boolean z;
        int v = motionEvent0.getActionMasked();
        float f = motionEvent0.getX();
        float f1 = motionEvent0.getY();
        switch(v) {
            case 0: {
                this.downX = f;
                this.downY = f1;
                this.isInTapRegion = true;
                this.changedDuringTouch = false;
                z = false;
                z1 = true;
                z2 = false;
                break;
            }
            case 1: {
            label_17:
                int v1 = (int)(f - this.downX);
                int v2 = (int)(f1 - this.downY);
                this.isInTapRegion = v1 * v1 + v2 * v2 > this.scaledTouchSlop;
                z = this.changedDuringTouch;
                if(this.isMultiLevel) {
                    this.adjustLevel(f, f1);
                }
                z2 = v == 1;
                z1 = false;
                break;
            }
            default: {
                if(v == 2) {
                    goto label_17;
                }
                else {
                    z = false;
                    z1 = false;
                    z2 = false;
                }
            }
        }
        int v3 = this.changedDuringTouch | this.handleTouchInput(f, f1, z, z1, z2);
        this.changedDuringTouch = v3;
        if(v3 != 0 && z2) {
            OnActionUpListener clockHandView$OnActionUpListener0 = this.onActionUpListener;
            if(clockHandView$OnActionUpListener0 != null) {
                clockHandView$OnActionUpListener0.onActionUp(((float)this.getDegreesFromXY(f, f1)), this.isInTapRegion);
            }
        }
        return true;
    }

    public void setAnimateOnTouchUp(boolean z) {
        this.animatingOnTouchUp = z;
    }

    public void setCircleRadius(int v) {
        this.circleRadius = v;
        this.invalidate();
    }

    void setCurrentLevel(int v) {
        this.currentLevel = v;
        this.invalidate();
    }

    public void setHandRotation(float f) {
        this.setHandRotation(f, false);
    }

    public void setHandRotation(float f, boolean z) {
        ValueAnimator valueAnimator0 = this.rotationAnimator;
        if(valueAnimator0 != null) {
            valueAnimator0.cancel();
        }
        if(!z) {
            this.setHandRotationInternal(f, false);
            return;
        }
        Pair pair0 = this.getValuesForAnimation(f);
        this.rotationAnimator.setFloatValues(new float[]{((float)(((Float)pair0.first))), ((float)(((Float)pair0.second)))});
        this.rotationAnimator.setDuration(((long)this.animationDuration));
        this.rotationAnimator.setInterpolator(this.animationInterpolator);
        ClockHandView..ExternalSyntheticLambda0 clockHandView$$ExternalSyntheticLambda00 = (ValueAnimator valueAnimator0) -> this.setHandRotationInternal(((float)(((Float)valueAnimator0.getAnimatedValue()))), true);
        this.rotationAnimator.addUpdateListener(clockHandView$$ExternalSyntheticLambda00);
        com.google.android.material.timepicker.ClockHandView.1 clockHandView$10 = new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationCancel(Animator animator0) {
                animator0.end();
            }
        };
        this.rotationAnimator.addListener(clockHandView$10);
        this.rotationAnimator.start();
    }

    private void setHandRotationInternal(float f, boolean z) {
        this.originalDeg = f % 360.0f;
        this.degRad = Math.toRadians(f % 360.0f - 90.0f);
        int v = this.getHeight();
        int v1 = this.getWidth();
        float f1 = (float)this.getLeveledCircleRadius(this.currentLevel);
        float f2 = ((float)(v1 / 2)) + ((float)Math.cos(this.degRad)) * f1;
        float f3 = ((float)(v / 2)) + f1 * ((float)Math.sin(this.degRad));
        this.selectorBox.set(f2 - ((float)this.selectorRadius), f3 - ((float)this.selectorRadius), f2 + ((float)this.selectorRadius), f3 + ((float)this.selectorRadius));
        for(Object object0: this.listeners) {
            ((OnRotateListener)object0).onRotate(f % 360.0f, z);
        }
        this.invalidate();
    }

    void setMultiLevel(boolean z) {
        if(this.isMultiLevel && !z) {
            this.currentLevel = 1;
        }
        this.isMultiLevel = z;
        this.invalidate();
    }

    public void setOnActionUpListener(OnActionUpListener clockHandView$OnActionUpListener0) {
        this.onActionUpListener = clockHandView$OnActionUpListener0;
    }
}

