package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.RangeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R.attr;
import com.google.android.material.R.color;
import com.google.android.material.R.dimen;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

abstract class BaseSlider extends View {
    class AccessibilityEventSender implements Runnable {
        int virtualViewId;

        private AccessibilityEventSender() {
            this.virtualViewId = -1;
        }

        AccessibilityEventSender(com.google.android.material.slider.BaseSlider.1 baseSlider$10) {
        }

        @Override
        public void run() {
            BaseSlider.this.accessibilityHelper.sendEventForVirtualView(this.virtualViewId, 4);
        }

        void setVirtualViewId(int v) {
            this.virtualViewId = v;
        }
    }

    static class AccessibilityHelper extends ExploreByTouchHelper {
        private final BaseSlider slider;
        final Rect virtualViewBounds;

        AccessibilityHelper(BaseSlider baseSlider0) {
            super(baseSlider0);
            this.virtualViewBounds = new Rect();
            this.slider = baseSlider0;
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected int getVirtualViewAt(float f, float f1) {
            for(int v = 0; v < this.slider.getValues().size(); ++v) {
                this.slider.updateBoundsForVirtualViewId(v, this.virtualViewBounds);
                if(this.virtualViewBounds.contains(((int)f), ((int)f1))) {
                    return v;
                }
            }
            return -1;
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected void getVisibleVirtualViews(List list0) {
            for(int v = 0; v < this.slider.getValues().size(); ++v) {
                list0.add(v);
            }
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected boolean onPerformActionForVirtualView(int v, int v1, Bundle bundle0) {
            if(!this.slider.isEnabled()) {
                return false;
            }
            if(v1 != 0x1000 && v1 != 0x2000) {
                if(v1 != 0x102003D) {
                    return false;
                }
                if(bundle0 != null && bundle0.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    float f = bundle0.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
                    if(this.slider.snapThumbToValue(v, f)) {
                        this.slider.updateHaloHotspot();
                        this.slider.postInvalidate();
                        this.invalidateVirtualView(v);
                        return true;
                    }
                }
                return false;
            }
            float f1 = this.slider.calculateStepIncrement(20);
            if(v1 == 0x2000) {
                f1 = -f1;
            }
            if(this.slider.isRtl()) {
                f1 = -f1;
            }
            float f2 = MathUtils.clamp(((float)(((Float)this.slider.getValues().get(v)))) + f1, this.slider.getValueFrom(), this.slider.getValueTo());
            if(this.slider.snapThumbToValue(v, f2)) {
                this.slider.updateHaloHotspot();
                this.slider.postInvalidate();
                this.invalidateVirtualView(v);
                return true;
            }
            return false;
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateNodeForVirtualView(int v, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SET_PROGRESS);
            List list0 = this.slider.getValues();
            float f = (float)(((Float)list0.get(v)));
            float f1 = this.slider.getValueFrom();
            float f2 = this.slider.getValueTo();
            if(this.slider.isEnabled()) {
                if(f > f1) {
                    accessibilityNodeInfoCompat0.addAction(0x2000);
                }
                if(f < f2) {
                    accessibilityNodeInfoCompat0.addAction(0x1000);
                }
            }
            accessibilityNodeInfoCompat0.setRangeInfo(RangeInfoCompat.obtain(1, f1, f2, f));
            accessibilityNodeInfoCompat0.setClassName("android.widget.SeekBar");
            StringBuilder stringBuilder0 = new StringBuilder();
            if(this.slider.getContentDescription() != null) {
                stringBuilder0.append(this.slider.getContentDescription());
                stringBuilder0.append(",");
            }
            String s = this.slider.formatValue(f);
            String s1 = this.slider.getContext().getString(string.material_slider_value);
            if(list0.size() > 1) {
                s1 = this.startOrEndDescription(v);
            }
            stringBuilder0.append(String.format(Locale.US, "%s, %s", s1, s));
            accessibilityNodeInfoCompat0.setContentDescription(stringBuilder0.toString());
            this.slider.updateBoundsForVirtualViewId(v, this.virtualViewBounds);
            accessibilityNodeInfoCompat0.setBoundsInParent(this.virtualViewBounds);
        }

        private String startOrEndDescription(int v) {
            if(v == this.slider.getValues().size() - 1) {
                return this.slider.getContext().getString(string.material_slider_range_end);
            }
            return v == 0 ? this.slider.getContext().getString(string.material_slider_range_start) : "";
        }
    }

    static enum FullCornerDirection {
        BOTH,
        LEFT,
        RIGHT,
        NONE;

    }

    static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean hasFocus;
        float stepSize;
        float valueFrom;
        float valueTo;
        ArrayList values;

        static {
            SliderState.CREATOR = new Parcelable.Creator() {
                public SliderState createFromParcel(Parcel parcel0) {
                    return new SliderState(parcel0, null);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SliderState[] newArray(int v) {
                    return new SliderState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        private SliderState(Parcel parcel0) {
            super(parcel0);
            this.valueFrom = parcel0.readFloat();
            this.valueTo = parcel0.readFloat();
            ArrayList arrayList0 = new ArrayList();
            this.values = arrayList0;
            parcel0.readList(arrayList0, Float.class.getClassLoader());
            this.stepSize = parcel0.readFloat();
            this.hasFocus = parcel0.createBooleanArray()[0];
        }

        SliderState(Parcel parcel0, com.google.android.material.slider.BaseSlider.1 baseSlider$10) {
            this(parcel0);
        }

        SliderState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.View$BaseSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeFloat(this.valueFrom);
            parcel0.writeFloat(this.valueTo);
            parcel0.writeList(this.values);
            parcel0.writeFloat(this.stepSize);
            parcel0.writeBooleanArray(new boolean[]{this.hasFocus});
        }
    }

    private static final int DEFAULT_LABEL_ANIMATION_ENTER_DURATION = 83;
    private static final int DEFAULT_LABEL_ANIMATION_EXIT_DURATION = 0x75;
    static final int DEF_STYLE_RES = 0;
    private static final String EXCEPTION_ILLEGAL_DISCRETE_VALUE = "Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION = "minSeparation(%s) must be greater or equal to 0";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE = "minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT = "minSeparation(%s) cannot be set as a dimension when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_STEP_SIZE = "The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range";
    private static final String EXCEPTION_ILLEGAL_VALUE = "Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_FROM = "valueFrom(%s) must be smaller than valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_TO = "valueTo(%s) must be greater than valueFrom(%s)";
    private static final int HALO_ALPHA = 0x3F;
    private static final int LABEL_ANIMATION_ENTER_DURATION_ATTR = 0;
    private static final int LABEL_ANIMATION_ENTER_EASING_ATTR = 0;
    private static final int LABEL_ANIMATION_EXIT_DURATION_ATTR = 0;
    private static final int LABEL_ANIMATION_EXIT_EASING_ATTR = 0;
    private static final int MIN_TOUCH_TARGET_DP = 0x30;
    private static final String TAG = "BaseSlider";
    private static final double THRESHOLD = 0.0001;
    private static final float THUMB_WIDTH_PRESSED_RATIO = 0.5f;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    static final int UNIT_PX = 0;
    static final int UNIT_VALUE = 1;
    private static final String WARNING_FLOATING_POINT_ERROR = "Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.";
    private AccessibilityEventSender accessibilityEventSender;
    private final AccessibilityHelper accessibilityHelper;
    private final AccessibilityManager accessibilityManager;
    private int activeThumbIdx;
    private final Paint activeTicksPaint;
    private final Paint activeTrackPaint;
    private final List changeListeners;
    private final RectF cornerRect;
    private Drawable customThumbDrawable;
    private List customThumbDrawablesForValues;
    private final MaterialShapeDrawable defaultThumbDrawable;
    private int defaultThumbRadius;
    private int defaultThumbTrackGapSize;
    private int defaultThumbWidth;
    private int defaultTickActiveRadius;
    private int defaultTickInactiveRadius;
    private int defaultTrackHeight;
    private boolean dirtyConfig;
    private int focusedThumbIdx;
    private boolean forceDrawCompatHalo;
    private LabelFormatter formatter;
    private ColorStateList haloColor;
    private final Paint haloPaint;
    private int haloRadius;
    private final Paint inactiveTicksPaint;
    private final Paint inactiveTrackPaint;
    private boolean isLongPress;
    private int labelBehavior;
    private int labelPadding;
    private int labelStyle;
    private final List labels;
    private boolean labelsAreAnimatedIn;
    private ValueAnimator labelsInAnimator;
    private ValueAnimator labelsOutAnimator;
    private MotionEvent lastEvent;
    private int minTickSpacing;
    private int minTouchTargetSize;
    private int minTrackSidePadding;
    private int minWidgetHeight;
    private final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    private final int scaledTouchSlop;
    private int separationUnit;
    private float stepSize;
    private final Paint stopIndicatorPaint;
    private int thumbHeight;
    private boolean thumbIsPressed;
    private final Paint thumbPaint;
    private int thumbTrackGapSize;
    private int thumbWidth;
    private int tickActiveRadius;
    private ColorStateList tickColorActive;
    private ColorStateList tickColorInactive;
    private int tickInactiveRadius;
    private boolean tickVisible;
    private float[] ticksCoordinates;
    private float touchDownX;
    private final List touchListeners;
    private float touchPosition;
    private ColorStateList trackColorActive;
    private ColorStateList trackColorInactive;
    private int trackHeight;
    private int trackInsideCornerSize;
    private final Path trackPath;
    private final RectF trackRect;
    private int trackSidePadding;
    private int trackStopIndicatorSize;
    private int trackWidth;
    private float valueFrom;
    private float valueTo;
    private ArrayList values;
    private int widgetHeight;

    // 检测为 Lambda 实现
    public static void $r8$lambda$WXiNVeXFM7RTh57Z9Tr5jBbN9l4(BaseSlider baseSlider0) [...]

    static {
        BaseSlider.DEF_STYLE_RES = style.Widget_MaterialComponents_Slider;
        BaseSlider.LABEL_ANIMATION_ENTER_DURATION_ATTR = attr.motionDurationMedium4;
        BaseSlider.LABEL_ANIMATION_EXIT_DURATION_ATTR = attr.motionDurationShort3;
        BaseSlider.LABEL_ANIMATION_ENTER_EASING_ATTR = attr.motionEasingEmphasizedInterpolator;
        BaseSlider.LABEL_ANIMATION_EXIT_EASING_ATTR = attr.motionEasingEmphasizedAccelerateInterpolator;
    }

    public BaseSlider(Context context0) {
        this(context0, null);
    }

    public BaseSlider(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.sliderStyle);
    }

    public BaseSlider(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, BaseSlider.DEF_STYLE_RES), attributeSet0, v);
        this.labels = new ArrayList();
        this.changeListeners = new ArrayList();
        this.touchListeners = new ArrayList();
        this.labelsAreAnimatedIn = false;
        this.defaultThumbWidth = -1;
        this.defaultThumbTrackGapSize = -1;
        this.thumbIsPressed = false;
        this.values = new ArrayList();
        this.activeThumbIdx = -1;
        this.focusedThumbIdx = -1;
        this.stepSize = 0.0f;
        this.tickVisible = true;
        this.isLongPress = false;
        this.trackPath = new Path();
        this.trackRect = new RectF();
        this.cornerRect = new RectF();
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
        this.defaultThumbDrawable = materialShapeDrawable0;
        this.customThumbDrawablesForValues = Collections.EMPTY_LIST;
        this.separationUnit = 0;
        this.onScrollChangedListener = () -> this.updateLabels();
        Context context1 = this.getContext();
        this.inactiveTrackPaint = new Paint();
        this.activeTrackPaint = new Paint();
        Paint paint0 = new Paint(1);
        this.thumbPaint = paint0;
        paint0.setStyle(Paint.Style.FILL);
        paint0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint1 = new Paint(1);
        this.haloPaint = paint1;
        paint1.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.inactiveTicksPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint();
        this.activeTicksPaint = paint3;
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeCap(Paint.Cap.ROUND);
        Paint paint4 = new Paint();
        this.stopIndicatorPaint = paint4;
        paint4.setStyle(Paint.Style.FILL);
        paint4.setStrokeCap(Paint.Cap.ROUND);
        this.loadResources(context1.getResources());
        this.processAttributes(context1, attributeSet0, v);
        this.setFocusable(true);
        this.setClickable(true);
        materialShapeDrawable0.setShadowCompatibilityMode(2);
        this.scaledTouchSlop = ViewConfiguration.get(context1).getScaledTouchSlop();
        AccessibilityHelper baseSlider$AccessibilityHelper0 = new AccessibilityHelper(this);
        this.accessibilityHelper = baseSlider$AccessibilityHelper0;
        ViewCompat.setAccessibilityDelegate(this, baseSlider$AccessibilityHelper0);
        this.accessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
    }

    public void addOnChangeListener(BaseOnChangeListener baseOnChangeListener0) {
        this.changeListeners.add(baseOnChangeListener0);
    }

    public void addOnSliderTouchListener(BaseOnSliderTouchListener baseOnSliderTouchListener0) {
        this.touchListeners.add(baseOnSliderTouchListener0);
    }

    private void adjustCustomThumbDrawableBounds(Drawable drawable0) {
        int v = drawable0.getIntrinsicWidth();
        int v1 = drawable0.getIntrinsicHeight();
        if(v == -1 && v1 == -1) {
            drawable0.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
            return;
        }
        float f = ((float)Math.max(this.thumbWidth, this.thumbHeight)) / ((float)Math.max(v, v1));
        drawable0.setBounds(0, 0, ((int)(((float)v) * f)), ((int)(((float)v1) * f)));
    }

    private void attachLabelToContentView(TooltipDrawable tooltipDrawable0) {
        tooltipDrawable0.setRelativeToView(ViewUtils.getContentView(this));
    }

    private Float calculateIncrementForKey(int v) {
        float f = this.isLongPress ? this.calculateStepIncrement(20) : this.calculateStepIncrement();
        switch(v) {
            case 21: {
                if(!this.isRtl()) {
                    f = -f;
                }
                return f;
            }
            case 22: {
                if(this.isRtl()) {
                    f = -f;
                }
                return f;
            }
            case 69: {
                return (float)(-f);
            }
            case 70: 
            case 81: {
                return f;
            }
            default: {
                return null;
            }
        }
    }

    private float calculateStepIncrement() {
        return this.stepSize == 0.0f ? 1.0f : this.stepSize;
    }

    private float calculateStepIncrement(int v) {
        float f = this.calculateStepIncrement();
        float f1 = (this.valueTo - this.valueFrom) / f;
        return f1 <= ((float)v) ? f : ((float)Math.round(f1 / ((float)v))) * f;
    }

    // 去混淆评级： 低(20)
    private int calculateTrackCenter() {
        return this.widgetHeight / 2 + (this.labelBehavior != 1 && !this.shouldAlwaysShowLabel() ? 0 : ((TooltipDrawable)this.labels.get(0)).getIntrinsicHeight());
    }

    public void clearOnChangeListeners() {
        this.changeListeners.clear();
    }

    public void clearOnSliderTouchListeners() {
        this.touchListeners.clear();
    }

    private ValueAnimator createLabelAnimator(boolean z) {
        TimeInterpolator timeInterpolator0;
        int v;
        float f = 0.0f;
        float f1 = BaseSlider.getAnimatorCurrentValueOrDefault((z ? this.labelsOutAnimator : this.labelsInAnimator), (z ? 0.0f : 1.0f));
        if(z) {
            f = 1.0f;
        }
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{f1, f});
        if(z) {
            v = MotionUtils.resolveThemeDuration(this.getContext(), BaseSlider.LABEL_ANIMATION_ENTER_DURATION_ATTR, 83);
            timeInterpolator0 = MotionUtils.resolveThemeInterpolator(this.getContext(), BaseSlider.LABEL_ANIMATION_ENTER_EASING_ATTR, AnimationUtils.DECELERATE_INTERPOLATOR);
        }
        else {
            v = MotionUtils.resolveThemeDuration(this.getContext(), BaseSlider.LABEL_ANIMATION_EXIT_DURATION_ATTR, 0x75);
            timeInterpolator0 = MotionUtils.resolveThemeInterpolator(this.getContext(), BaseSlider.LABEL_ANIMATION_EXIT_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
        valueAnimator0.setDuration(((long)v));
        valueAnimator0.setInterpolator(timeInterpolator0);
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                for(Object object0: BaseSlider.this.labels) {
                    ((TooltipDrawable)object0).setRevealFraction(f);
                }
                ViewCompat.postInvalidateOnAnimation(BaseSlider.this);
            }
        });
        return valueAnimator0;
    }

    private void createLabelPool() {
        int v;
        if(this.labels.size() > this.values.size()) {
            List list0 = this.labels.subList(this.values.size(), this.labels.size());
            for(Object object0: list0) {
                TooltipDrawable tooltipDrawable0 = (TooltipDrawable)object0;
                if(ViewCompat.isAttachedToWindow(this)) {
                    this.detachLabelFromContentView(tooltipDrawable0);
                }
            }
            list0.clear();
        }
        while(true) {
            v = 0;
            if(this.labels.size() >= this.values.size()) {
                break;
            }
            TooltipDrawable tooltipDrawable1 = TooltipDrawable.createFromAttributes(this.getContext(), null, 0, this.labelStyle);
            this.labels.add(tooltipDrawable1);
            if(ViewCompat.isAttachedToWindow(this)) {
                this.attachLabelToContentView(tooltipDrawable1);
            }
        }
        if(this.labels.size() != 1) {
            v = 1;
        }
        for(Object object1: this.labels) {
            ((TooltipDrawable)object1).setStrokeWidth(((float)v));
        }
    }

    private void detachLabelFromContentView(TooltipDrawable tooltipDrawable0) {
        ViewOverlayImpl viewOverlayImpl0 = ViewUtils.getContentViewOverlay(this);
        if(viewOverlayImpl0 != null) {
            viewOverlayImpl0.remove(tooltipDrawable0);
            tooltipDrawable0.detachView(ViewUtils.getContentView(this));
        }
    }

    private float dimenToValue(float f) {
        return f == 0.0f ? 0.0f : (f - ((float)this.trackSidePadding)) / ((float)this.trackWidth) * (this.valueFrom - this.valueTo) + this.valueFrom;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent0) {
        return this.accessibilityHelper.dispatchHoverEvent(motionEvent0) || super.dispatchHoverEvent(motionEvent0);
    }

    @Override  // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        return super.dispatchKeyEvent(keyEvent0);
    }

    private void dispatchOnChangedFromUser(int v) {
        for(Object object0: this.changeListeners) {
            ((BaseOnChangeListener)object0).onValueChange(this, ((float)(((Float)this.values.get(v)))), true);
        }
        if(this.accessibilityManager != null && this.accessibilityManager.isEnabled()) {
            this.scheduleAccessibilityEventSender(v);
        }
    }

    private void dispatchOnChangedProgrammatically() {
        for(Object object0: this.changeListeners) {
            BaseOnChangeListener baseOnChangeListener0 = (BaseOnChangeListener)object0;
            for(Object object1: this.values) {
                baseOnChangeListener0.onValueChange(this, ((float)(((Float)object1))), false);
            }
        }
    }

    private void drawActiveTrack(Canvas canvas0, int v, int v1) {
        float[] arr_f = this.getActiveRange();
        int v2 = this.trackSidePadding;
        float f = ((float)v2) + arr_f[1] * ((float)v);
        float f1 = ((float)v2) + arr_f[0] * ((float)v);
        if(this.hasGapBetweenThumbAndTrack()) {
            FullCornerDirection baseSlider$FullCornerDirection0 = FullCornerDirection.NONE;
            if(this.values.size() == 1) {
                baseSlider$FullCornerDirection0 = this.isRtl() ? FullCornerDirection.RIGHT : FullCornerDirection.LEFT;
            }
            for(int v3 = 0; v3 < this.values.size(); ++v3) {
                if(this.values.size() > 1) {
                    if(v3 > 0) {
                        f1 = this.valueToX(((float)(((Float)this.values.get(v3 - 1)))));
                    }
                    float f2 = this.valueToX(((float)(((Float)this.values.get(v3)))));
                    if(this.isRtl()) {
                        f = f1;
                        f1 = f2;
                    }
                    else {
                        f = f2;
                    }
                }
                switch(com.google.android.material.slider.BaseSlider.3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection[baseSlider$FullCornerDirection0.ordinal()]) {
                    case 1: {
                        f1 += (float)this.thumbTrackGapSize;
                        f -= (float)this.thumbTrackGapSize;
                        break;
                    }
                    case 2: {
                        f1 -= ((float)this.trackHeight) / 2.0f;
                        f -= (float)this.thumbTrackGapSize;
                        break;
                    }
                    case 3: {
                        f1 += (float)this.thumbTrackGapSize;
                        f += ((float)this.trackHeight) / 2.0f;
                    }
                }
                if(f1 < f) {
                    this.trackRect.set(f1, ((float)v1) - ((float)this.trackHeight) / 2.0f, f, ((float)v1) + ((float)this.trackHeight) / 2.0f);
                    this.updateTrack(canvas0, this.activeTrackPaint, this.trackRect, baseSlider$FullCornerDirection0);
                }
            }
            return;
        }
        this.activeTrackPaint.setStyle(Paint.Style.STROKE);
        this.activeTrackPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas0.drawLine(f1, ((float)v1), f, ((float)v1), this.activeTrackPaint);
    }

    private void drawInactiveTrack(Canvas canvas0, int v, int v1) {
        float[] arr_f = this.getActiveRange();
        int v2 = this.trackSidePadding;
        float f = ((float)v2) + arr_f[1] * ((float)v);
        if(f < ((float)(v2 + v))) {
            if(this.hasGapBetweenThumbAndTrack()) {
                this.trackRect.set(f + ((float)this.thumbTrackGapSize), ((float)v1) - ((float)this.trackHeight) / 2.0f, ((float)(this.trackSidePadding + v)) + ((float)this.trackHeight) / 2.0f, ((float)v1) + ((float)this.trackHeight) / 2.0f);
                this.updateTrack(canvas0, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.RIGHT);
            }
            else {
                this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
                this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
                canvas0.drawLine(f, ((float)v1), ((float)(this.trackSidePadding + v)), ((float)v1), this.inactiveTrackPaint);
            }
        }
        int v3 = this.trackSidePadding;
        float f1 = arr_f[0] * ((float)v) + ((float)v3);
        if(f1 > ((float)v3)) {
            if(this.hasGapBetweenThumbAndTrack()) {
                this.trackRect.set(((float)this.trackSidePadding) - ((float)this.trackHeight) / 2.0f, ((float)v1) - ((float)this.trackHeight) / 2.0f, f1 - ((float)this.thumbTrackGapSize), ((float)v1) + ((float)this.trackHeight) / 2.0f);
                this.updateTrack(canvas0, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.LEFT);
                return;
            }
            this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
            this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
            canvas0.drawLine(((float)this.trackSidePadding), ((float)v1), f1, ((float)v1), this.inactiveTrackPaint);
        }
    }

    private void drawThumbDrawable(Canvas canvas0, int v, int v1, float f, Drawable drawable0) {
        canvas0.save();
        canvas0.translate(((float)(this.trackSidePadding + ((int)(this.normalizeValue(f) * ((float)v))))) - ((float)drawable0.getBounds().width()) / 2.0f, ((float)v1) - ((float)drawable0.getBounds().height()) / 2.0f);
        drawable0.draw(canvas0);
        canvas0.restore();
    }

    private void drawThumbs(Canvas canvas0, int v, int v1) {
        for(int v2 = 0; v2 < this.values.size(); ++v2) {
            float f = (float)(((Float)this.values.get(v2)));
            Drawable drawable0 = this.customThumbDrawable;
            if(drawable0 != null) {
                this.drawThumbDrawable(canvas0, v, v1, f, drawable0);
            }
            else if(v2 < this.customThumbDrawablesForValues.size()) {
                this.drawThumbDrawable(canvas0, v, v1, f, ((Drawable)this.customThumbDrawablesForValues.get(v2)));
            }
            else {
                if(!this.isEnabled()) {
                    canvas0.drawCircle(((float)this.trackSidePadding) + this.normalizeValue(f) * ((float)v), ((float)v1), ((float)this.getThumbRadius()), this.thumbPaint);
                }
                this.drawThumbDrawable(canvas0, v, v1, f, this.defaultThumbDrawable);
            }
        }
    }

    @Override  // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int v = this.getColorForState(this.trackColorInactive);
        this.inactiveTrackPaint.setColor(v);
        int v1 = this.getColorForState(this.trackColorActive);
        this.activeTrackPaint.setColor(v1);
        int v2 = this.getColorForState(this.tickColorInactive);
        this.inactiveTicksPaint.setColor(v2);
        int v3 = this.getColorForState(this.tickColorActive);
        this.activeTicksPaint.setColor(v3);
        int v4 = this.getColorForState(this.trackColorActive);
        this.stopIndicatorPaint.setColor(v4);
        for(Object object0: this.labels) {
            TooltipDrawable tooltipDrawable0 = (TooltipDrawable)object0;
            if(tooltipDrawable0.isStateful()) {
                tooltipDrawable0.setState(this.getDrawableState());
            }
        }
        if(this.defaultThumbDrawable.isStateful()) {
            int[] arr_v = this.getDrawableState();
            this.defaultThumbDrawable.setState(arr_v);
        }
        int v5 = this.getColorForState(this.haloColor);
        this.haloPaint.setColor(v5);
        this.haloPaint.setAlpha(0x3F);
    }

    private void ensureLabelsAdded() {
        if(!this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = true;
            ValueAnimator valueAnimator0 = this.createLabelAnimator(true);
            this.labelsInAnimator = valueAnimator0;
            this.labelsOutAnimator = null;
            valueAnimator0.start();
        }
        Iterator iterator0 = this.labels.iterator();
        for(int v = 0; v < this.values.size() && iterator0.hasNext(); ++v) {
            if(v != this.focusedThumbIdx) {
                Object object0 = iterator0.next();
                this.setValueForLabel(((TooltipDrawable)object0), ((float)(((Float)this.values.get(v)))));
            }
        }
        if(!iterator0.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", this.labels.size(), this.values.size()));
        }
        Object object1 = iterator0.next();
        this.setValueForLabel(((TooltipDrawable)object1), ((float)(((Float)this.values.get(this.focusedThumbIdx)))));
    }

    private void ensureLabelsRemoved() {
        if(this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = false;
            ValueAnimator valueAnimator0 = this.createLabelAnimator(false);
            this.labelsOutAnimator = valueAnimator0;
            this.labelsInAnimator = null;
            valueAnimator0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    super.onAnimationEnd(animator0);
                    ViewOverlayImpl viewOverlayImpl0 = ViewUtils.getContentViewOverlay(BaseSlider.this);
                    for(Object object0: BaseSlider.this.labels) {
                        viewOverlayImpl0.remove(((TooltipDrawable)object0));
                    }
                }
            });
            this.labelsOutAnimator.start();
        }
    }

    private void focusThumbOnFocusGained(int v) {
        switch(v) {
            case 1: {
                this.moveFocus(0x7FFFFFFF);
                return;
            }
            case 2: {
                this.moveFocus(0x80000000);
                return;
            }
            case 17: {
                this.moveFocusInAbsoluteDirection(0x7FFFFFFF);
                return;
            }
            case 66: {
                this.moveFocusInAbsoluteDirection(0x80000000);
            }
        }
    }

    void forceDrawCompatHalo(boolean z) {
        this.forceDrawCompatHalo = z;
    }

    private String formatValue(float f) {
        if(this.hasLabelFormatter()) {
            return this.formatter.getFormattedValue(f);
        }
        return ((float)(((int)f))) == f ? String.format("%.0f", f) : String.format("%.2f", f);
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.View
    public CharSequence getAccessibilityClassName() {
        return "android.widget.SeekBar";
    }

    final int getAccessibilityFocusedVirtualViewId() {
        return this.accessibilityHelper.getAccessibilityFocusedVirtualViewId();
    }

    private float[] getActiveRange() {
        float f = (float)(((Float)this.values.get(0)));
        float f1 = (float)(((Float)this.values.get(this.values.size() - 1)));
        if(this.values.size() == 1) {
            f = this.valueFrom;
        }
        float f2 = this.normalizeValue(f);
        float f3 = this.normalizeValue(f1);
        return this.isRtl() ? new float[]{f3, f2} : new float[]{f2, f3};
    }

    public int getActiveThumbIndex() {
        return this.activeThumbIdx;
    }

    private static float getAnimatorCurrentValueOrDefault(ValueAnimator valueAnimator0, float f) {
        if(valueAnimator0 != null && valueAnimator0.isRunning()) {
            f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            valueAnimator0.cancel();
        }
        return f;
    }

    private float getClampedValue(int v, float f) {
        float f1 = this.getMinSeparation();
        if(this.separationUnit == 0) {
            f1 = this.dimenToValue(f1);
        }
        if(this.isRtl()) {
            f1 = -f1;
        }
        float f2 = v + 1 < this.values.size() ? ((float)(((Float)this.values.get(v + 1)))) - f1 : this.valueTo;
        return v - 1 >= 0 ? MathUtils.clamp(f, ((float)(((Float)this.values.get(v - 1)))) + f1, f2) : MathUtils.clamp(f, this.valueFrom, f2);
    }

    private int getColorForState(ColorStateList colorStateList0) {
        return colorStateList0.getColorForState(this.getDrawableState(), colorStateList0.getDefaultColor());
    }

    private float[] getCornerRadii(float f, float f1) {
        return new float[]{f, f, f1, f1, f1, f1, f, f};
    }

    public int getFocusedThumbIndex() {
        return this.focusedThumbIdx;
    }

    public int getHaloRadius() {
        return this.haloRadius;
    }

    public ColorStateList getHaloTintList() {
        return this.haloColor;
    }

    public int getLabelBehavior() {
        return this.labelBehavior;
    }

    protected float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public float getThumbElevation() {
        return this.defaultThumbDrawable.getElevation();
    }

    public int getThumbHeight() {
        return this.thumbHeight;
    }

    public int getThumbRadius() {
        return this.thumbWidth / 2;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.defaultThumbDrawable.getStrokeColor();
    }

    public float getThumbStrokeWidth() {
        return this.defaultThumbDrawable.getStrokeWidth();
    }

    public ColorStateList getThumbTintList() {
        return this.defaultThumbDrawable.getFillColor();
    }

    public int getThumbTrackGapSize() {
        return this.thumbTrackGapSize;
    }

    public int getThumbWidth() {
        return this.thumbWidth;
    }

    public int getTickActiveRadius() {
        return this.tickActiveRadius;
    }

    public ColorStateList getTickActiveTintList() {
        return this.tickColorActive;
    }

    public int getTickInactiveRadius() {
        return this.tickInactiveRadius;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.tickColorInactive;
    }

    public ColorStateList getTickTintList() {
        if(!this.tickColorInactive.equals(this.tickColorActive)) {
            throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
        }
        return this.tickColorActive;
    }

    public ColorStateList getTrackActiveTintList() {
        return this.trackColorActive;
    }

    public int getTrackHeight() {
        return this.trackHeight;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.trackColorInactive;
    }

    public int getTrackInsideCornerSize() {
        return this.trackInsideCornerSize;
    }

    public int getTrackSidePadding() {
        return this.trackSidePadding;
    }

    public int getTrackStopIndicatorSize() {
        return this.trackStopIndicatorSize;
    }

    public ColorStateList getTrackTintList() {
        if(!this.trackColorInactive.equals(this.trackColorActive)) {
            throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
        }
        return this.trackColorActive;
    }

    public int getTrackWidth() {
        return this.trackWidth;
    }

    public float getValueFrom() {
        return this.valueFrom;
    }

    private float getValueOfTouchPosition() {
        double f = this.snapPosition(this.touchPosition);
        if(this.isRtl()) {
            f = 1.0 - f;
        }
        return (float)(f * ((double)(this.valueTo - this.valueFrom)) + ((double)this.valueFrom));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f = this.touchPosition;
        return (this.isRtl() ? 1.0f - f : this.touchPosition) * (this.valueTo - this.valueFrom) + this.valueFrom;
    }

    public float getValueTo() {
        return this.valueTo;
    }

    List getValues() {
        return new ArrayList(this.values);
    }

    private boolean hasGapBetweenThumbAndTrack() {
        return this.thumbTrackGapSize > 0;
    }

    public boolean hasLabelFormatter() {
        return this.formatter != null;
    }

    private Drawable initializeCustomThumbDrawable(Drawable drawable0) {
        Drawable drawable1 = drawable0.mutate().getConstantState().newDrawable();
        this.adjustCustomThumbDrawableBounds(drawable1);
        return drawable1;
    }

    private void invalidateTrack() {
        this.inactiveTrackPaint.setStrokeWidth(((float)this.trackHeight));
        this.activeTrackPaint.setStrokeWidth(((float)this.trackHeight));
    }

    private boolean isInVerticalScrollingContainer() {
        for(ViewParent viewParent0 = this.getParent(); viewParent0 instanceof ViewGroup; viewParent0 = viewParent0.getParent()) {
            if((((ViewGroup)viewParent0).canScrollVertically(1) || ((ViewGroup)viewParent0).canScrollVertically(-1)) && ((ViewGroup)viewParent0).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMouseEvent(MotionEvent motionEvent0) {
        return motionEvent0.getToolType(0) == 3;
    }

    private boolean isMultipleOfStepSize(double f) {
        double f1 = new BigDecimal(Double.toString(f)).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double)Math.round(f1)) - f1) < 0.0001;
    }

    // 去混淆评级： 低(20)
    private boolean isPotentialVerticalScroll(MotionEvent motionEvent0) {
        return !BaseSlider.isMouseEvent(motionEvent0) && this.isInVerticalScrollingContainer();
    }

    final boolean isRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    private boolean isSliderVisibleOnScreen() {
        Rect rect0 = new Rect();
        ViewUtils.getContentView(this).getHitRect(rect0);
        return this.getLocalVisibleRect(rect0);
    }

    public boolean isTickVisible() {
        return this.tickVisible;
    }

    private void loadResources(Resources resources0) {
        this.minWidgetHeight = resources0.getDimensionPixelSize(dimen.mtrl_slider_widget_height);
        int v = resources0.getDimensionPixelOffset(dimen.mtrl_slider_track_side_padding);
        this.minTrackSidePadding = v;
        this.trackSidePadding = v;
        this.defaultThumbRadius = resources0.getDimensionPixelSize(dimen.mtrl_slider_thumb_radius);
        this.defaultTrackHeight = resources0.getDimensionPixelSize(dimen.mtrl_slider_track_height);
        this.defaultTickActiveRadius = resources0.getDimensionPixelSize(dimen.mtrl_slider_tick_radius);
        this.defaultTickInactiveRadius = resources0.getDimensionPixelSize(dimen.mtrl_slider_tick_radius);
        this.minTickSpacing = resources0.getDimensionPixelSize(dimen.mtrl_slider_tick_min_spacing);
        this.labelPadding = resources0.getDimensionPixelSize(dimen.mtrl_slider_label_padding);
    }

    private void maybeCalculateTicksCoordinates() {
        if(this.stepSize > 0.0f) {
            this.validateConfigurationIfDirty();
            int v = Math.min(((int)((this.valueTo - this.valueFrom) / this.stepSize + 1.0f)), this.trackWidth / this.minTickSpacing + 1);
            if(this.ticksCoordinates == null || this.ticksCoordinates.length != v * 2) {
                this.ticksCoordinates = new float[v * 2];
            }
            float f = ((float)this.trackWidth) / ((float)(v - 1));
            for(int v1 = 0; v1 < v * 2; v1 += 2) {
                float[] arr_f = this.ticksCoordinates;
                arr_f[v1] = ((float)this.trackSidePadding) + ((float)v1) / 2.0f * f;
                arr_f[v1 + 1] = (float)this.calculateTrackCenter();
            }
        }
    }

    private void maybeDrawCompatHalo(Canvas canvas0, int v, int v1) {
        if(this.shouldDrawCompatHalo()) {
            int v2 = (int)(((float)this.trackSidePadding) + this.normalizeValue(((float)(((Float)this.values.get(this.focusedThumbIdx))))) * ((float)v));
            if(Build.VERSION.SDK_INT < 28) {
                canvas0.clipRect(((float)(v2 - this.haloRadius)), ((float)(v1 - this.haloRadius)), ((float)(v2 + this.haloRadius)), ((float)(this.haloRadius + v1)), Region.Op.UNION);
            }
            canvas0.drawCircle(((float)v2), ((float)v1), ((float)this.haloRadius), this.haloPaint);
        }
    }

    private void maybeDrawStopIndicator(Canvas canvas0, int v) {
        if(this.trackStopIndicatorSize > 0) {
            if(this.values.size() >= 1) {
                float f = (float)(((Float)this.values.get(this.values.size() - 1)));
                float f1 = this.valueTo;
                if(f < f1) {
                    canvas0.drawPoint(this.valueToX(f1), ((float)v), this.stopIndicatorPaint);
                }
            }
            if(this.values.size() > 1) {
                float f2 = (float)(((Float)this.values.get(0)));
                float f3 = this.valueFrom;
                if(f2 > f3) {
                    canvas0.drawPoint(this.valueToX(f3), ((float)v), this.stopIndicatorPaint);
                }
            }
        }
    }

    private void maybeDrawTicks(Canvas canvas0) {
        if(this.tickVisible && this.stepSize > 0.0f) {
            float[] arr_f = this.getActiveRange();
            int v = (int)Math.ceil(arr_f[0] * (((float)this.ticksCoordinates.length) / 2.0f - 1.0f));
            int v1 = (int)Math.floor(arr_f[1] * (((float)this.ticksCoordinates.length) / 2.0f - 1.0f));
            if(v > 0) {
                canvas0.drawPoints(this.ticksCoordinates, 0, v * 2, this.inactiveTicksPaint);
            }
            if(v <= v1) {
                canvas0.drawPoints(this.ticksCoordinates, v * 2, (v1 - v + 1) * 2, this.activeTicksPaint);
            }
            int v2 = (v1 + 1) * 2;
            float[] arr_f1 = this.ticksCoordinates;
            if(v2 < arr_f1.length) {
                canvas0.drawPoints(arr_f1, v2, arr_f1.length - v2, this.inactiveTicksPaint);
            }
        }
    }

    private boolean maybeIncreaseTrackSidePadding() {
        int v = this.minTrackSidePadding + Math.max(Math.max(Math.max(this.thumbWidth / 2 - this.defaultThumbRadius, 0), Math.max((this.trackHeight - this.defaultTrackHeight) / 2, 0)), Math.max(Math.max(this.tickActiveRadius - this.defaultTickActiveRadius, 0), Math.max(this.tickInactiveRadius - this.defaultTickInactiveRadius, 0)));
        if(this.trackSidePadding == v) {
            return false;
        }
        this.trackSidePadding = v;
        if(ViewCompat.isLaidOut(this)) {
            this.updateTrackWidth(this.getWidth());
        }
        return true;
    }

    private boolean maybeIncreaseWidgetHeight() {
        int v = this.getPaddingTop();
        int v1 = this.getPaddingBottom();
        int v2 = Math.max(this.trackHeight + (v + v1), this.thumbHeight + this.getPaddingTop() + this.getPaddingBottom());
        int v3 = Math.max(this.minWidgetHeight, v2);
        if(v3 == this.widgetHeight) {
            return false;
        }
        this.widgetHeight = v3;
        return true;
    }

    private boolean moveFocus(int v) {
        int v1 = this.focusedThumbIdx;
        int v2 = (int)MathUtils.clamp(((long)v1) + ((long)v), 0L, this.values.size() - 1);
        this.focusedThumbIdx = v2;
        if(v2 == v1) {
            return false;
        }
        if(this.activeThumbIdx != -1) {
            this.activeThumbIdx = v2;
        }
        this.updateHaloHotspot();
        this.postInvalidate();
        return true;
    }

    private boolean moveFocusInAbsoluteDirection(int v) {
        if(this.isRtl()) {
            if(v == 0x80000000) {
                return this.moveFocus(0x7FFFFFFF);
            }
            v = -v;
        }
        return this.moveFocus(v);
    }

    private float normalizeValue(float f) {
        float f1 = (f - this.valueFrom) / (this.valueTo - this.valueFrom);
        return this.isRtl() ? 1.0f - f1 : f1;
    }

    @Override  // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
        for(Object object0: this.labels) {
            this.attachLabelToContentView(((TooltipDrawable)object0));
        }
    }

    @Override  // android.view.View
    protected void onDetachedFromWindow() {
        AccessibilityEventSender baseSlider$AccessibilityEventSender0 = this.accessibilityEventSender;
        if(baseSlider$AccessibilityEventSender0 != null) {
            this.removeCallbacks(baseSlider$AccessibilityEventSender0);
        }
        this.labelsAreAnimatedIn = false;
        for(Object object0: this.labels) {
            this.detachLabelFromContentView(((TooltipDrawable)object0));
        }
        this.getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
        super.onDetachedFromWindow();
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        if(this.dirtyConfig) {
            this.validateConfigurationIfDirty();
            this.maybeCalculateTicksCoordinates();
        }
        super.onDraw(canvas0);
        int v = this.calculateTrackCenter();
        float f = (float)(((Float)this.values.get(0)));
        float f1 = (float)(((Float)this.values.get(this.values.size() - 1)));
        if(f1 < this.valueTo || this.values.size() > 1 && f > this.valueFrom) {
            this.drawInactiveTrack(canvas0, this.trackWidth, v);
        }
        if(f1 > this.valueFrom) {
            this.drawActiveTrack(canvas0, this.trackWidth, v);
        }
        this.maybeDrawTicks(canvas0);
        this.maybeDrawStopIndicator(canvas0, v);
        if((this.thumbIsPressed || this.isFocused()) && this.isEnabled()) {
            this.maybeDrawCompatHalo(canvas0, this.trackWidth, v);
        }
        this.updateLabels();
        this.drawThumbs(canvas0, this.trackWidth, v);
    }

    @Override  // android.view.View
    protected void onFocusChanged(boolean z, int v, Rect rect0) {
        super.onFocusChanged(z, v, rect0);
        if(!z) {
            this.activeThumbIdx = -1;
            this.accessibilityHelper.clearKeyboardFocusForVirtualView(this.focusedThumbIdx);
            return;
        }
        this.focusThumbOnFocusGained(v);
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(this.focusedThumbIdx);
    }

    @Override  // android.view.View
    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        if(!this.isEnabled()) {
            return super.onKeyDown(v, keyEvent0);
        }
        if(this.values.size() == 1) {
            this.activeThumbIdx = 0;
        }
        if(this.activeThumbIdx == -1) {
            Boolean boolean0 = this.onKeyDownNoActiveThumb(v, keyEvent0);
            return boolean0 == null ? super.onKeyDown(v, keyEvent0) : boolean0.booleanValue();
        }
        this.isLongPress |= keyEvent0.isLongPress();
        Float float0 = this.calculateIncrementForKey(v);
        if(float0 != null) {
            if(this.snapActiveThumbToValue(((float)(((Float)this.values.get(this.activeThumbIdx)))) + ((float)float0))) {
                this.updateHaloHotspot();
                this.postInvalidate();
            }
            return true;
        }
        switch(v) {
            case 61: {
                if(keyEvent0.hasNoModifiers()) {
                    return this.moveFocus(1);
                }
                return keyEvent0.isShiftPressed() ? this.moveFocus(-1) : false;
            }
            case 23: 
            case 66: {
                this.activeThumbIdx = -1;
                this.postInvalidate();
                return true;
            }
            default: {
                return super.onKeyDown(v, keyEvent0);
            }
        }
    }

    private Boolean onKeyDownNoActiveThumb(int v, KeyEvent keyEvent0) {
        switch(v) {
            case 21: {
                this.moveFocusInAbsoluteDirection(-1);
                return true;
            }
            case 22: {
                this.moveFocusInAbsoluteDirection(1);
                return true;
            }
            case 61: {
                if(keyEvent0.hasNoModifiers()) {
                    return Boolean.valueOf(this.moveFocus(1));
                }
                return keyEvent0.isShiftPressed() ? Boolean.valueOf(this.moveFocus(-1)) : false;
            }
            case 23: 
            case 66: {
                this.activeThumbIdx = this.focusedThumbIdx;
                this.postInvalidate();
                return true;
            }
            case 69: {
                this.moveFocus(-1);
                return true;
            }
            case 70: 
            case 81: {
                this.moveFocus(1);
                return true;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // android.view.View
    public boolean onKeyUp(int v, KeyEvent keyEvent0) {
        this.isLongPress = false;
        return super.onKeyUp(v, keyEvent0);
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, View.MeasureSpec.makeMeasureSpec(this.widgetHeight + (this.labelBehavior != 1 && !this.shouldAlwaysShowLabel() ? 0 : ((TooltipDrawable)this.labels.get(0)).getIntrinsicHeight()), 0x40000000));
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        super.onRestoreInstanceState(((SliderState)parcelable0).getSuperState());
        this.valueFrom = ((SliderState)parcelable0).valueFrom;
        this.valueTo = ((SliderState)parcelable0).valueTo;
        this.setValuesInternal(((SliderState)parcelable0).values);
        this.stepSize = ((SliderState)parcelable0).stepSize;
        if(((SliderState)parcelable0).hasFocus) {
            this.requestFocus();
        }
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SliderState(super.onSaveInstanceState());
        parcelable0.valueFrom = this.valueFrom;
        parcelable0.valueTo = this.valueTo;
        parcelable0.values = new ArrayList(this.values);
        parcelable0.stepSize = this.stepSize;
        parcelable0.hasFocus = this.hasFocus();
        return parcelable0;
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        this.updateTrackWidth(v);
        this.updateHaloHotspot();
    }

    private void onStartTrackingTouch() {
        for(Object object0: this.touchListeners) {
            ((BaseOnSliderTouchListener)object0).onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        for(Object object0: this.touchListeners) {
            ((BaseOnSliderTouchListener)object0).onStopTrackingTouch(this);
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(!this.isEnabled()) {
            return false;
        }
        float f = motionEvent0.getX();
        this.touchPosition = Math.min(1.0f, Math.max(0.0f, (f - ((float)this.trackSidePadding)) / ((float)this.trackWidth)));
        int v = motionEvent0.getActionMasked();
        switch(v) {
            case 0: {
                this.touchDownX = f;
                if(!this.isPotentialVerticalScroll(motionEvent0)) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    if(this.pickActiveThumb()) {
                        this.requestFocus();
                        this.thumbIsPressed = true;
                        this.snapTouchPosition();
                        this.updateHaloHotspot();
                        if(this.hasGapBetweenThumbAndTrack()) {
                            this.defaultThumbWidth = this.thumbWidth;
                            this.defaultThumbTrackGapSize = this.thumbTrackGapSize;
                            int v1 = Math.round(((float)this.thumbWidth) * 0.5f);
                            int v2 = this.thumbWidth - v1;
                            this.setThumbWidth(v1);
                            this.setThumbTrackGapSize(this.thumbTrackGapSize - v2 / 2);
                        }
                        this.invalidate();
                        this.onStartTrackingTouch();
                    }
                }
                break;
            }
            case 1: {
            label_26:
                this.thumbIsPressed = false;
                if(this.lastEvent != null && this.lastEvent.getActionMasked() == 0 && Math.abs(this.lastEvent.getX() - motionEvent0.getX()) <= ((float)this.scaledTouchSlop) && Math.abs(this.lastEvent.getY() - motionEvent0.getY()) <= ((float)this.scaledTouchSlop) && this.pickActiveThumb()) {
                    this.onStartTrackingTouch();
                }
                if(this.activeThumbIdx != -1) {
                    this.snapTouchPosition();
                    this.updateHaloHotspot();
                    if(this.hasGapBetweenThumbAndTrack()) {
                        int v3 = this.defaultThumbWidth;
                        if(v3 != -1 && this.defaultThumbTrackGapSize != -1) {
                            this.setThumbWidth(v3);
                            this.setThumbTrackGapSize(this.defaultThumbTrackGapSize);
                        }
                    }
                    this.activeThumbIdx = -1;
                    this.onStopTrackingTouch();
                }
                this.invalidate();
                break;
            }
            case 2: {
                if(!this.thumbIsPressed) {
                    if(this.isPotentialVerticalScroll(motionEvent0) && Math.abs(f - this.touchDownX) < ((float)this.scaledTouchSlop)) {
                        return false;
                    }
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    this.onStartTrackingTouch();
                }
                if(this.pickActiveThumb()) {
                    this.thumbIsPressed = true;
                    this.snapTouchPosition();
                    this.updateHaloHotspot();
                    this.invalidate();
                }
                break;
            }
            default: {
                if(v == 3) {
                    goto label_26;
                }
            }
        }
        this.setPressed(this.thumbIsPressed);
        this.lastEvent = MotionEvent.obtain(motionEvent0);
        return true;
    }

    @Override  // android.view.View
    protected void onVisibilityChanged(View view0, int v) {
        super.onVisibilityChanged(view0, v);
        if(v != 0) {
            ViewOverlayImpl viewOverlayImpl0 = ViewUtils.getContentViewOverlay(this);
            if(viewOverlayImpl0 != null) {
                for(Object object0: this.labels) {
                    viewOverlayImpl0.remove(((TooltipDrawable)object0));
                }
            }
        }
    }

    protected boolean pickActiveThumb() {
        boolean z;
        if(this.activeThumbIdx != -1) {
            return true;
        }
        float f = this.getValueOfTouchPositionAbsolute();
        float f1 = this.valueToX(f);
        this.activeThumbIdx = 0;
        float f2 = Math.abs(((float)(((Float)this.values.get(0)))) - f);
        for(int v = 1; v < this.values.size(); ++v) {
            float f3 = Math.abs(((float)(((Float)this.values.get(v)))) - f);
            float f4 = this.valueToX(((float)(((Float)this.values.get(v)))));
            if(Float.compare(f3, f2) > 0) {
                break;
            }
            if(!this.isRtl()) {
                if(f4 - f1 < 0.0f) {
                    z = true;
                }
            }
            else if(f4 - f1 > 0.0f) {
                z = true;
            }
            else {
                z = false;
            }
            if(Float.compare(f3, f2) < 0) {
                this.activeThumbIdx = v;
                f2 = f3;
            }
            else if(Float.compare(f3, f2) == 0) {
                if(Math.abs(f4 - f1) < ((float)this.scaledTouchSlop)) {
                    this.activeThumbIdx = -1;
                    return false;
                }
                if(z) {
                    this.activeThumbIdx = v;
                    f2 = f3;
                }
            }
        }
        return this.activeThumbIdx != -1;
    }

    private void positionLabel(TooltipDrawable tooltipDrawable0, float f) {
        int v = this.trackSidePadding + ((int)(this.normalizeValue(f) * ((float)this.trackWidth))) - tooltipDrawable0.getIntrinsicWidth() / 2;
        int v1 = this.calculateTrackCenter() - (this.labelPadding + this.thumbHeight / 2);
        tooltipDrawable0.setBounds(v, v1 - tooltipDrawable0.getIntrinsicHeight(), tooltipDrawable0.getIntrinsicWidth() + v, v1);
        Rect rect0 = new Rect(tooltipDrawable0.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, rect0);
        tooltipDrawable0.setBounds(rect0);
    }

    private void processAttributes(Context context0, AttributeSet attributeSet0, int v) {
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.Slider, v, BaseSlider.DEF_STYLE_RES, new int[0]);
        this.labelStyle = typedArray0.getResourceId(styleable.Slider_labelStyle, style.Widget_MaterialComponents_Tooltip);
        this.valueFrom = typedArray0.getFloat(styleable.Slider_android_valueFrom, 0.0f);
        this.valueTo = typedArray0.getFloat(styleable.Slider_android_valueTo, 1.0f);
        this.setValues(new Float[]{this.valueFrom});
        this.stepSize = typedArray0.getFloat(styleable.Slider_android_stepSize, 0.0f);
        float f = (float)Math.ceil(ViewUtils.dpToPx(this.getContext(), 0x30));
        this.minTouchTargetSize = (int)Math.ceil(typedArray0.getDimension(styleable.Slider_minTouchTargetSize, f));
        boolean z = typedArray0.hasValue(styleable.Slider_trackColor);
        int v1 = z ? styleable.Slider_trackColor : styleable.Slider_trackColorActive;
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(context0, typedArray0, (z ? styleable.Slider_trackColor : styleable.Slider_trackColorInactive));
        if(colorStateList0 == null) {
            colorStateList0 = AppCompatResources.getColorStateList(context0, color.material_slider_inactive_track_color);
        }
        this.setTrackInactiveTintList(colorStateList0);
        ColorStateList colorStateList1 = MaterialResources.getColorStateList(context0, typedArray0, v1);
        if(colorStateList1 == null) {
            colorStateList1 = AppCompatResources.getColorStateList(context0, color.material_slider_active_track_color);
        }
        this.setTrackActiveTintList(colorStateList1);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context0, typedArray0, styleable.Slider_thumbColor);
        this.defaultThumbDrawable.setFillColor(colorStateList2);
        if(typedArray0.hasValue(styleable.Slider_thumbStrokeColor)) {
            this.setThumbStrokeColor(MaterialResources.getColorStateList(context0, typedArray0, styleable.Slider_thumbStrokeColor));
        }
        this.setThumbStrokeWidth(typedArray0.getDimension(styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context0, typedArray0, styleable.Slider_haloColor);
        if(colorStateList3 == null) {
            colorStateList3 = AppCompatResources.getColorStateList(context0, color.material_slider_halo_color);
        }
        this.setHaloTintList(colorStateList3);
        this.tickVisible = typedArray0.getBoolean(styleable.Slider_tickVisible, true);
        boolean z1 = typedArray0.hasValue(styleable.Slider_tickColor);
        int v2 = z1 ? styleable.Slider_tickColor : styleable.Slider_tickColorActive;
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context0, typedArray0, (z1 ? styleable.Slider_tickColor : styleable.Slider_tickColorInactive));
        if(colorStateList4 == null) {
            colorStateList4 = AppCompatResources.getColorStateList(context0, color.material_slider_inactive_tick_marks_color);
        }
        this.setTickInactiveTintList(colorStateList4);
        ColorStateList colorStateList5 = MaterialResources.getColorStateList(context0, typedArray0, v2);
        if(colorStateList5 == null) {
            colorStateList5 = AppCompatResources.getColorStateList(context0, color.material_slider_active_tick_marks_color);
        }
        this.setTickActiveTintList(colorStateList5);
        this.setThumbTrackGapSize(typedArray0.getDimensionPixelSize(styleable.Slider_thumbTrackGapSize, 0));
        this.setTrackStopIndicatorSize(typedArray0.getDimensionPixelSize(styleable.Slider_trackStopIndicatorSize, 0));
        this.setTrackInsideCornerSize(typedArray0.getDimensionPixelSize(styleable.Slider_trackInsideCornerSize, 0));
        int v3 = typedArray0.getDimensionPixelSize(styleable.Slider_thumbRadius, 0);
        int v4 = typedArray0.getDimensionPixelSize(styleable.Slider_thumbWidth, v3 * 2);
        int v5 = typedArray0.getDimensionPixelSize(styleable.Slider_thumbHeight, v3 * 2);
        this.setThumbWidth(v4);
        this.setThumbHeight(v5);
        this.setHaloRadius(typedArray0.getDimensionPixelSize(styleable.Slider_haloRadius, 0));
        this.setThumbElevation(typedArray0.getDimension(styleable.Slider_thumbElevation, 0.0f));
        this.setTrackHeight(typedArray0.getDimensionPixelSize(styleable.Slider_trackHeight, 0));
        this.setTickActiveRadius(typedArray0.getDimensionPixelSize(styleable.Slider_tickRadiusActive, this.trackStopIndicatorSize / 2));
        this.setTickInactiveRadius(typedArray0.getDimensionPixelSize(styleable.Slider_tickRadiusInactive, this.trackStopIndicatorSize / 2));
        this.setLabelBehavior(typedArray0.getInt(styleable.Slider_labelBehavior, 0));
        if(!typedArray0.getBoolean(styleable.Slider_android_enabled, true)) {
            this.setEnabled(false);
        }
        typedArray0.recycle();
    }

    public void removeOnChangeListener(BaseOnChangeListener baseOnChangeListener0) {
        this.changeListeners.remove(baseOnChangeListener0);
    }

    public void removeOnSliderTouchListener(BaseOnSliderTouchListener baseOnSliderTouchListener0) {
        this.touchListeners.remove(baseOnSliderTouchListener0);
    }

    private void scheduleAccessibilityEventSender(int v) {
        AccessibilityEventSender baseSlider$AccessibilityEventSender0 = this.accessibilityEventSender;
        if(baseSlider$AccessibilityEventSender0 == null) {
            this.accessibilityEventSender = new AccessibilityEventSender(this, null);
        }
        else {
            this.removeCallbacks(baseSlider$AccessibilityEventSender0);
        }
        this.accessibilityEventSender.setVirtualViewId(v);
        this.postDelayed(this.accessibilityEventSender, 200L);
    }

    protected void setActiveThumbIndex(int v) {
        this.activeThumbIdx = v;
    }

    void setCustomThumbDrawable(int v) {
        this.setCustomThumbDrawable(this.getResources().getDrawable(v));
    }

    void setCustomThumbDrawable(Drawable drawable0) {
        this.customThumbDrawable = this.initializeCustomThumbDrawable(drawable0);
        this.customThumbDrawablesForValues.clear();
        this.postInvalidate();
    }

    void setCustomThumbDrawablesForValues(int[] arr_v) {
        Drawable[] arr_drawable = new Drawable[arr_v.length];
        for(int v = 0; v < arr_v.length; ++v) {
            arr_drawable[v] = this.getResources().getDrawable(arr_v[v]);
        }
        this.setCustomThumbDrawablesForValues(arr_drawable);
    }

    void setCustomThumbDrawablesForValues(Drawable[] arr_drawable) {
        this.customThumbDrawable = null;
        this.customThumbDrawablesForValues = new ArrayList();
        for(int v = 0; v < arr_drawable.length; ++v) {
            this.customThumbDrawablesForValues.add(this.initializeCustomThumbDrawable(arr_drawable[v]));
        }
        this.postInvalidate();
    }

    @Override  // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.setLayerType((z ? 0 : 2), null);
    }

    public void setFocusedThumbIndex(int v) {
        if(v < 0 || v >= this.values.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.focusedThumbIdx = v;
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(v);
        this.postInvalidate();
    }

    public void setHaloRadius(int v) {
        if(v == this.haloRadius) {
            return;
        }
        this.haloRadius = v;
        Drawable drawable0 = this.getBackground();
        if(!this.shouldDrawCompatHalo() && drawable0 instanceof RippleDrawable) {
            DrawableUtils.setRippleDrawableRadius(((RippleDrawable)drawable0), this.haloRadius);
            return;
        }
        this.postInvalidate();
    }

    public void setHaloRadiusResource(int v) {
        this.setHaloRadius(this.getResources().getDimensionPixelSize(v));
    }

    public void setHaloTintList(ColorStateList colorStateList0) {
        if(colorStateList0.equals(this.haloColor)) {
            return;
        }
        this.haloColor = colorStateList0;
        Drawable drawable0 = this.getBackground();
        if(!this.shouldDrawCompatHalo() && drawable0 instanceof RippleDrawable) {
            ((RippleDrawable)drawable0).setColor(colorStateList0);
            return;
        }
        int v = this.getColorForState(colorStateList0);
        this.haloPaint.setColor(v);
        this.haloPaint.setAlpha(0x3F);
        this.invalidate();
    }

    public void setLabelBehavior(int v) {
        if(this.labelBehavior != v) {
            this.labelBehavior = v;
            this.requestLayout();
        }
    }

    public void setLabelFormatter(LabelFormatter labelFormatter0) {
        this.formatter = labelFormatter0;
    }

    protected void setSeparationUnit(int v) {
        this.separationUnit = v;
        this.dirtyConfig = true;
        this.postInvalidate();
    }

    public void setStepSize(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", f, this.valueFrom, this.valueTo));
        }
        if(this.stepSize != f) {
            this.stepSize = f;
            this.dirtyConfig = true;
            this.postInvalidate();
        }
    }

    public void setThumbElevation(float f) {
        this.defaultThumbDrawable.setElevation(f);
    }

    public void setThumbElevationResource(int v) {
        this.setThumbElevation(this.getResources().getDimension(v));
    }

    public void setThumbHeight(int v) {
        if(v == this.thumbHeight) {
            return;
        }
        this.thumbHeight = v;
        this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, v);
        Drawable drawable0 = this.customThumbDrawable;
        if(drawable0 != null) {
            this.adjustCustomThumbDrawableBounds(drawable0);
        }
        for(Object object0: this.customThumbDrawablesForValues) {
            this.adjustCustomThumbDrawableBounds(((Drawable)object0));
        }
        this.updateWidgetLayout();
    }

    public void setThumbHeightResource(int v) {
        this.setThumbHeight(this.getResources().getDimensionPixelSize(v));
    }

    public void setThumbRadius(int v) {
        this.setThumbWidth(v * 2);
        this.setThumbHeight(v * 2);
    }

    public void setThumbRadiusResource(int v) {
        this.setThumbRadius(this.getResources().getDimensionPixelSize(v));
    }

    public void setThumbStrokeColor(ColorStateList colorStateList0) {
        this.defaultThumbDrawable.setStrokeColor(colorStateList0);
        this.postInvalidate();
    }

    public void setThumbStrokeColorResource(int v) {
        if(v != 0) {
            this.setThumbStrokeColor(AppCompatResources.getColorStateList(this.getContext(), v));
        }
    }

    public void setThumbStrokeWidth(float f) {
        this.defaultThumbDrawable.setStrokeWidth(f);
        this.postInvalidate();
    }

    public void setThumbStrokeWidthResource(int v) {
        if(v != 0) {
            this.setThumbStrokeWidth(this.getResources().getDimension(v));
        }
    }

    public void setThumbTintList(ColorStateList colorStateList0) {
        if(colorStateList0.equals(this.defaultThumbDrawable.getFillColor())) {
            return;
        }
        this.defaultThumbDrawable.setFillColor(colorStateList0);
        this.invalidate();
    }

    public void setThumbTrackGapSize(int v) {
        if(this.thumbTrackGapSize == v) {
            return;
        }
        this.thumbTrackGapSize = v;
        this.invalidate();
    }

    public void setThumbWidth(int v) {
        if(v == this.thumbWidth) {
            return;
        }
        this.thumbWidth = v;
        ShapeAppearanceModel shapeAppearanceModel0 = ShapeAppearanceModel.builder().setAllCorners(0, ((float)this.thumbWidth) / 2.0f).build();
        this.defaultThumbDrawable.setShapeAppearanceModel(shapeAppearanceModel0);
        this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
        Drawable drawable0 = this.customThumbDrawable;
        if(drawable0 != null) {
            this.adjustCustomThumbDrawableBounds(drawable0);
        }
        for(Object object0: this.customThumbDrawablesForValues) {
            this.adjustCustomThumbDrawableBounds(((Drawable)object0));
        }
        this.updateWidgetLayout();
    }

    public void setThumbWidthResource(int v) {
        this.setThumbWidth(this.getResources().getDimensionPixelSize(v));
    }

    public void setTickActiveRadius(int v) {
        if(this.tickActiveRadius != v) {
            this.tickActiveRadius = v;
            this.activeTicksPaint.setStrokeWidth(((float)(v * 2)));
            this.updateWidgetLayout();
        }
    }

    public void setTickActiveTintList(ColorStateList colorStateList0) {
        if(colorStateList0.equals(this.tickColorActive)) {
            return;
        }
        this.tickColorActive = colorStateList0;
        int v = this.getColorForState(colorStateList0);
        this.activeTicksPaint.setColor(v);
        this.invalidate();
    }

    public void setTickInactiveRadius(int v) {
        if(this.tickInactiveRadius != v) {
            this.tickInactiveRadius = v;
            this.inactiveTicksPaint.setStrokeWidth(((float)(v * 2)));
            this.updateWidgetLayout();
        }
    }

    public void setTickInactiveTintList(ColorStateList colorStateList0) {
        if(colorStateList0.equals(this.tickColorInactive)) {
            return;
        }
        this.tickColorInactive = colorStateList0;
        int v = this.getColorForState(colorStateList0);
        this.inactiveTicksPaint.setColor(v);
        this.invalidate();
    }

    public void setTickTintList(ColorStateList colorStateList0) {
        this.setTickInactiveTintList(colorStateList0);
        this.setTickActiveTintList(colorStateList0);
    }

    public void setTickVisible(boolean z) {
        if(this.tickVisible != z) {
            this.tickVisible = z;
            this.postInvalidate();
        }
    }

    public void setTrackActiveTintList(ColorStateList colorStateList0) {
        if(colorStateList0.equals(this.trackColorActive)) {
            return;
        }
        this.trackColorActive = colorStateList0;
        int v = this.getColorForState(colorStateList0);
        this.activeTrackPaint.setColor(v);
        int v1 = this.getColorForState(this.trackColorActive);
        this.stopIndicatorPaint.setColor(v1);
        this.invalidate();
    }

    public void setTrackHeight(int v) {
        if(this.trackHeight != v) {
            this.trackHeight = v;
            this.invalidateTrack();
            this.updateWidgetLayout();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList0) {
        if(colorStateList0.equals(this.trackColorInactive)) {
            return;
        }
        this.trackColorInactive = colorStateList0;
        int v = this.getColorForState(colorStateList0);
        this.inactiveTrackPaint.setColor(v);
        this.invalidate();
    }

    public void setTrackInsideCornerSize(int v) {
        if(this.trackInsideCornerSize == v) {
            return;
        }
        this.trackInsideCornerSize = v;
        this.invalidate();
    }

    public void setTrackStopIndicatorSize(int v) {
        if(this.trackStopIndicatorSize == v) {
            return;
        }
        this.trackStopIndicatorSize = v;
        this.stopIndicatorPaint.setStrokeWidth(((float)v));
        this.invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList0) {
        this.setTrackInactiveTintList(colorStateList0);
        this.setTrackActiveTintList(colorStateList0);
    }

    private void setValueForLabel(TooltipDrawable tooltipDrawable0, float f) {
        tooltipDrawable0.setText(this.formatValue(f));
        this.positionLabel(tooltipDrawable0, f);
        ViewUtils.getContentViewOverlay(this).add(tooltipDrawable0);
    }

    public void setValueFrom(float f) {
        this.valueFrom = f;
        this.dirtyConfig = true;
        this.postInvalidate();
    }

    public void setValueTo(float f) {
        this.valueTo = f;
        this.dirtyConfig = true;
        this.postInvalidate();
    }

    void setValues(List list0) {
        this.setValuesInternal(new ArrayList(list0));
    }

    void setValues(Float[] arr_float) {
        ArrayList arrayList0 = new ArrayList();
        Collections.addAll(arrayList0, arr_float);
        this.setValuesInternal(arrayList0);
    }

    private void setValuesInternal(ArrayList arrayList0) {
        if(arrayList0.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList0);
        if(this.values.size() == arrayList0.size() && this.values.equals(arrayList0)) {
            return;
        }
        this.values = arrayList0;
        this.dirtyConfig = true;
        this.focusedThumbIdx = 0;
        this.updateHaloHotspot();
        this.createLabelPool();
        this.dispatchOnChangedProgrammatically();
        this.postInvalidate();
    }

    private boolean shouldAlwaysShowLabel() {
        return this.labelBehavior == 3;
    }

    // 去混淆评级： 低(20)
    private boolean shouldDrawCompatHalo() {
        return this.forceDrawCompatHalo || !(this.getBackground() instanceof RippleDrawable);
    }

    private boolean snapActiveThumbToValue(float f) {
        return this.snapThumbToValue(this.activeThumbIdx, f);
    }

    private double snapPosition(float f) {
        float f1 = this.stepSize;
        if(f1 > 0.0f) {
            int v = (int)((this.valueTo - this.valueFrom) / f1);
            return ((double)Math.round(f * ((float)v))) / ((double)v);
        }
        return (double)f;
    }

    private boolean snapThumbToValue(int v, float f) {
        this.focusedThumbIdx = v;
        if(((double)Math.abs(f - ((float)(((Float)this.values.get(v)))))) < 0.0001) {
            return false;
        }
        Float float0 = this.getClampedValue(v, f);
        this.values.set(v, float0);
        this.dispatchOnChangedFromUser(v);
        return true;
    }

    private boolean snapTouchPosition() {
        return this.snapActiveThumbToValue(this.getValueOfTouchPosition());
    }

    void updateBoundsForVirtualViewId(int v, Rect rect0) {
        int v1 = this.trackSidePadding + ((int)(this.normalizeValue(((float)(((Float)this.getValues().get(v))))) * ((float)this.trackWidth)));
        int v2 = this.calculateTrackCenter();
        int v3 = Math.max(this.thumbWidth / 2, this.minTouchTargetSize / 2);
        int v4 = Math.max(this.thumbHeight / 2, this.minTouchTargetSize / 2);
        rect0.set(v1 - v3, v2 - v4, v1 + v3, v2 + v4);
    }

    private void updateHaloHotspot() {
        if(!this.shouldDrawCompatHalo() && this.getMeasuredWidth() > 0) {
            Drawable drawable0 = this.getBackground();
            if(drawable0 instanceof RippleDrawable) {
                int v = (int)(this.normalizeValue(((float)(((Float)this.values.get(this.focusedThumbIdx))))) * ((float)this.trackWidth) + ((float)this.trackSidePadding));
                int v1 = this.calculateTrackCenter();
                DrawableCompat.setHotspotBounds(drawable0, v - this.haloRadius, v1 - this.haloRadius, v + this.haloRadius, v1 + this.haloRadius);
            }
        }
    }

    private void updateLabels() {
        switch(this.labelBehavior) {
            case 0: 
            case 1: {
                if(this.activeThumbIdx != -1 && this.isEnabled()) {
                    this.ensureLabelsAdded();
                    return;
                }
                this.ensureLabelsRemoved();
                return;
            }
            case 2: {
                this.ensureLabelsRemoved();
                return;
            }
            case 3: {
                if(this.isEnabled() && this.isSliderVisibleOnScreen()) {
                    this.ensureLabelsAdded();
                    return;
                }
                this.ensureLabelsRemoved();
                return;
            }
            default: {
                throw new IllegalArgumentException("Unexpected labelBehavior: " + this.labelBehavior);
            }
        }
    }

    private void updateTrack(Canvas canvas0, Paint paint0, RectF rectF0, FullCornerDirection baseSlider$FullCornerDirection0) {
        float f = ((float)this.trackHeight) / 2.0f;
        float f1 = ((float)this.trackHeight) / 2.0f;
        switch(com.google.android.material.slider.BaseSlider.3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection[baseSlider$FullCornerDirection0.ordinal()]) {
            case 1: {
                f = (float)this.trackInsideCornerSize;
                f1 = (float)this.trackInsideCornerSize;
                break;
            }
            case 2: {
                f1 = (float)this.trackInsideCornerSize;
                break;
            }
            case 3: {
                f = (float)this.trackInsideCornerSize;
            }
        }
        paint0.setStyle(Paint.Style.FILL);
        paint0.setStrokeCap(Paint.Cap.BUTT);
        paint0.setAntiAlias(true);
        this.trackPath.reset();
        if(rectF0.width() >= f + f1) {
            this.trackPath.addRoundRect(rectF0, this.getCornerRadii(f, f1), Path.Direction.CW);
            canvas0.drawPath(this.trackPath, paint0);
            return;
        }
        float f2 = Math.min(f, f1);
        float f3 = Math.max(f, f1);
        canvas0.save();
        this.trackPath.addRoundRect(rectF0, f2, f2, Path.Direction.CW);
        canvas0.clipPath(this.trackPath);
        switch(com.google.android.material.slider.BaseSlider.3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection[baseSlider$FullCornerDirection0.ordinal()]) {
            case 2: {
                this.cornerRect.set(rectF0.left, rectF0.top, rectF0.left + 2.0f * f3, rectF0.bottom);
                break;
            }
            case 3: {
                this.cornerRect.set(rectF0.right - 2.0f * f3, rectF0.top, rectF0.right, rectF0.bottom);
                break;
            }
            default: {
                float f4 = rectF0.centerX();
                float f5 = rectF0.top;
                float f6 = rectF0.centerX();
                this.cornerRect.set(f4 - f3, f5, f6 + f3, rectF0.bottom);
            }
        }
        canvas0.drawRoundRect(this.cornerRect, f3, f3, paint0);
        canvas0.restore();
    }

    private void updateTrackWidth(int v) {
        this.trackWidth = Math.max(v - this.trackSidePadding * 2, 0);
        this.maybeCalculateTicksCoordinates();
    }

    private void updateWidgetLayout() {
        boolean z = this.maybeIncreaseWidgetHeight();
        boolean z1 = this.maybeIncreaseTrackSidePadding();
        if(z) {
            this.requestLayout();
            return;
        }
        if(z1) {
            this.postInvalidate();
        }
    }

    private void validateConfigurationIfDirty() {
        if(this.dirtyConfig) {
            this.validateValueFrom();
            this.validateValueTo();
            this.validateStepSize();
            this.validateValues();
            this.validateMinSeparation();
            this.warnAboutFloatingPointError();
            this.dirtyConfig = false;
        }
    }

    private void validateMinSeparation() {
        float f = this.getMinSeparation();
        if(f < 0.0f) {
            throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal to 0", f));
        }
        float f1 = this.stepSize;
        if(f1 > 0.0f && f > 0.0f) {
            if(this.separationUnit != 1) {
                throw new IllegalStateException(String.format("minSeparation(%s) cannot be set as a dimension when using stepSize(%s)", f, this.stepSize));
            }
            if(f < f1 || !this.isMultipleOfStepSize(((double)f))) {
                throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)", f, this.stepSize, this.stepSize));
            }
        }
    }

    private void validateStepSize() {
        if(this.stepSize > 0.0f && !this.valueLandsOnTick(this.valueTo)) {
            throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", this.stepSize, this.valueFrom, this.valueTo));
        }
    }

    private void validateValueFrom() {
        if(this.valueFrom >= this.valueTo) {
            throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", this.valueFrom, this.valueTo));
        }
    }

    private void validateValueTo() {
        if(this.valueTo <= this.valueFrom) {
            throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", this.valueTo, this.valueFrom));
        }
    }

    private void validateValues() {
        for(Object object0: this.values) {
            Float float0 = (Float)object0;
            if(((float)float0) < this.valueFrom || ((float)float0) > this.valueTo) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", float0, this.valueFrom, this.valueTo));
            }
            if(this.stepSize > 0.0f && !this.valueLandsOnTick(((float)float0))) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", float0, this.valueFrom, this.stepSize, this.stepSize));
            }
        }
    }

    private boolean valueLandsOnTick(float f) {
        return this.isMultipleOfStepSize(new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(this.valueFrom)), MathContext.DECIMAL64).doubleValue());
    }

    private float valueToX(float f) {
        return this.normalizeValue(f) * ((float)this.trackWidth) + ((float)this.trackSidePadding);
    }

    private void warnAboutFloatingPointError() {
        float f = this.stepSize;
        if(f != 0.0f) {
            if(((float)(((int)f))) != f) {
                Log.w("BaseSlider", String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "stepSize", f));
            }
            float f1 = this.valueFrom;
            if(((float)(((int)f1))) != f1) {
                Log.w("BaseSlider", String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueFrom", f1));
            }
            float f2 = this.valueTo;
            if(((float)(((int)f2))) != f2) {
                Log.w("BaseSlider", String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueTo", f2));
            }
        }
    }

    class com.google.android.material.slider.BaseSlider.3 {
        static final int[] $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection;

        static {
            int[] arr_v = new int[FullCornerDirection.values().length];
            com.google.android.material.slider.BaseSlider.3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection = arr_v;
            try {
                arr_v[FullCornerDirection.NONE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.android.material.slider.BaseSlider.3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection[FullCornerDirection.LEFT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.android.material.slider.BaseSlider.3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection[FullCornerDirection.RIGHT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.android.material.slider.BaseSlider.3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection[FullCornerDirection.BOTH.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

