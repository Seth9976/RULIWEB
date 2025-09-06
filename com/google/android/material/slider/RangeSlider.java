package com.google.android.material.slider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.google.android.material.R.attr;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import java.util.ArrayList;
import java.util.List;

public class RangeSlider extends BaseSlider {
    public interface OnChangeListener extends BaseOnChangeListener {
        void onValueChange(RangeSlider arg1, float arg2, boolean arg3);
    }

    public interface OnSliderTouchListener extends BaseOnSliderTouchListener {
        void onStartTrackingTouch(RangeSlider arg1);

        void onStopTrackingTouch(RangeSlider arg1);
    }

    static class RangeSliderState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        private float minSeparation;
        private int separationUnit;

        static {
            RangeSliderState.CREATOR = new Parcelable.Creator() {
                public RangeSliderState createFromParcel(Parcel parcel0) {
                    return new RangeSliderState(parcel0, null);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public RangeSliderState[] newArray(int v) {
                    return new RangeSliderState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        private RangeSliderState(Parcel parcel0) {
            super(parcel0.readParcelable(RangeSliderState.class.getClassLoader()));
            this.minSeparation = parcel0.readFloat();
            this.separationUnit = parcel0.readInt();
        }

        RangeSliderState(Parcel parcel0, com.google.android.material.slider.RangeSlider.1 rangeSlider$10) {
            this(parcel0);
        }

        RangeSliderState(Parcelable parcelable0) {
            super(parcelable0);
        }

        static float access$000(RangeSliderState rangeSlider$RangeSliderState0) {
            return rangeSlider$RangeSliderState0.minSeparation;
        }

        static float access$002(RangeSliderState rangeSlider$RangeSliderState0, float f) {
            rangeSlider$RangeSliderState0.minSeparation = f;
            return f;
        }

        static int access$100(RangeSliderState rangeSlider$RangeSliderState0) {
            return rangeSlider$RangeSliderState0.separationUnit;
        }

        static int access$102(RangeSliderState rangeSlider$RangeSliderState0, int v) {
            rangeSlider$RangeSliderState0.separationUnit = v;
            return v;
        }

        @Override  // android.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeFloat(this.minSeparation);
            parcel0.writeInt(this.separationUnit);
        }
    }

    private float minSeparation;
    private int separationUnit;

    public RangeSlider(Context context0) {
        this(context0, null);
    }

    public RangeSlider(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.sliderStyle);
    }

    public RangeSlider(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.RangeSlider, v, RangeSlider.DEF_STYLE_RES, new int[0]);
        if(typedArray0.hasValue(styleable.RangeSlider_values)) {
            int v1 = typedArray0.getResourceId(styleable.RangeSlider_values, 0);
            this.setValues(RangeSlider.convertToFloat(typedArray0.getResources().obtainTypedArray(v1)));
        }
        this.minSeparation = typedArray0.getDimension(styleable.RangeSlider_minSeparation, 0.0f);
        typedArray0.recycle();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void addOnChangeListener(BaseOnChangeListener baseOnChangeListener0) {
        super.addOnChangeListener(baseOnChangeListener0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void addOnSliderTouchListener(BaseOnSliderTouchListener baseOnSliderTouchListener0) {
        super.addOnSliderTouchListener(baseOnSliderTouchListener0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void clearOnChangeListeners() {
        super.clearOnChangeListeners();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void clearOnSliderTouchListeners() {
        super.clearOnSliderTouchListeners();
    }

    private static List convertToFloat(TypedArray typedArray0) {
        List list0 = new ArrayList();
        for(int v = 0; v < typedArray0.length(); ++v) {
            list0.add(typedArray0.getFloat(v, -1.0f));
        }
        return list0;
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public boolean dispatchHoverEvent(MotionEvent motionEvent0) {
        return super.dispatchHoverEvent(motionEvent0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        return super.dispatchKeyEvent(keyEvent0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getActiveThumbIndex() {
        return super.getActiveThumbIndex();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getFocusedThumbIndex() {
        return super.getFocusedThumbIndex();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getHaloRadius() {
        return super.getHaloRadius();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getHaloTintList() {
        return super.getHaloTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getLabelBehavior() {
        return super.getLabelBehavior();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public float getMinSeparation() {
        return this.minSeparation;
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public float getStepSize() {
        return super.getStepSize();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public float getThumbElevation() {
        return super.getThumbElevation();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getThumbHeight() {
        return super.getThumbHeight();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getThumbRadius() {
        return super.getThumbRadius();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getThumbStrokeColor() {
        return super.getThumbStrokeColor();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public float getThumbStrokeWidth() {
        return super.getThumbStrokeWidth();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getThumbTintList() {
        return super.getThumbTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getThumbTrackGapSize() {
        return super.getThumbTrackGapSize();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getThumbWidth() {
        return super.getThumbWidth();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getTickActiveRadius() {
        return super.getTickActiveRadius();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getTickActiveTintList() {
        return super.getTickActiveTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getTickInactiveRadius() {
        return super.getTickInactiveRadius();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getTickInactiveTintList() {
        return super.getTickInactiveTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getTickTintList() {
        return super.getTickTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getTrackActiveTintList() {
        return super.getTrackActiveTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getTrackHeight() {
        return super.getTrackHeight();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getTrackInactiveTintList() {
        return super.getTrackInactiveTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getTrackInsideCornerSize() {
        return super.getTrackInsideCornerSize();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getTrackSidePadding() {
        return super.getTrackSidePadding();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getTrackStopIndicatorSize() {
        return super.getTrackStopIndicatorSize();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public ColorStateList getTrackTintList() {
        return super.getTrackTintList();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public int getTrackWidth() {
        return super.getTrackWidth();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public float getValueFrom() {
        return super.getValueFrom();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public float getValueTo() {
        return super.getValueTo();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public List getValues() {
        return super.getValues();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public boolean hasLabelFormatter() {
        return super.hasLabelFormatter();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public boolean isTickVisible() {
        return super.isTickVisible();
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        return super.onKeyDown(v, keyEvent0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public boolean onKeyUp(int v, KeyEvent keyEvent0) {
        return super.onKeyUp(v, keyEvent0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        super.onRestoreInstanceState(((RangeSliderState)parcelable0).getSuperState());
        this.minSeparation = RangeSliderState.access$000(((RangeSliderState)parcelable0));
        int v = RangeSliderState.access$100(((RangeSliderState)parcelable0));
        this.separationUnit = v;
        this.setSeparationUnit(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new RangeSliderState(super.onSaveInstanceState());
        RangeSliderState.access$002(((RangeSliderState)parcelable0), this.minSeparation);
        RangeSliderState.access$102(((RangeSliderState)parcelable0), this.separationUnit);
        return parcelable0;
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return super.onTouchEvent(motionEvent0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void removeOnChangeListener(BaseOnChangeListener baseOnChangeListener0) {
        super.removeOnChangeListener(baseOnChangeListener0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void removeOnSliderTouchListener(BaseOnSliderTouchListener baseOnSliderTouchListener0) {
        super.removeOnSliderTouchListener(baseOnSliderTouchListener0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawable(int v) {
        super.setCustomThumbDrawable(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawable(Drawable drawable0) {
        super.setCustomThumbDrawable(drawable0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawablesForValues(int[] arr_v) {
        super.setCustomThumbDrawablesForValues(arr_v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawablesForValues(Drawable[] arr_drawable) {
        super.setCustomThumbDrawablesForValues(arr_drawable);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setEnabled(boolean z) {
        super.setEnabled(z);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setFocusedThumbIndex(int v) {
        super.setFocusedThumbIndex(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setHaloRadius(int v) {
        super.setHaloRadius(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setHaloRadiusResource(int v) {
        super.setHaloRadiusResource(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setHaloTintList(ColorStateList colorStateList0) {
        super.setHaloTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setLabelBehavior(int v) {
        super.setLabelBehavior(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setLabelFormatter(LabelFormatter labelFormatter0) {
        super.setLabelFormatter(labelFormatter0);
    }

    public void setMinSeparation(float f) {
        this.minSeparation = f;
        this.separationUnit = 0;
        this.setSeparationUnit(0);
    }

    public void setMinSeparationValue(float f) {
        this.minSeparation = f;
        this.separationUnit = 1;
        this.setSeparationUnit(1);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setStepSize(float f) {
        super.setStepSize(f);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbElevation(float f) {
        super.setThumbElevation(f);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbElevationResource(int v) {
        super.setThumbElevationResource(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbHeight(int v) {
        super.setThumbHeight(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbHeightResource(int v) {
        super.setThumbHeightResource(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbRadius(int v) {
        super.setThumbRadius(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbRadiusResource(int v) {
        super.setThumbRadiusResource(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbStrokeColor(ColorStateList colorStateList0) {
        super.setThumbStrokeColor(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbStrokeColorResource(int v) {
        super.setThumbStrokeColorResource(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbStrokeWidth(float f) {
        super.setThumbStrokeWidth(f);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbStrokeWidthResource(int v) {
        super.setThumbStrokeWidthResource(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbTintList(ColorStateList colorStateList0) {
        super.setThumbTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbTrackGapSize(int v) {
        super.setThumbTrackGapSize(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbWidth(int v) {
        super.setThumbWidth(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setThumbWidthResource(int v) {
        super.setThumbWidthResource(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTickActiveRadius(int v) {
        super.setTickActiveRadius(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTickActiveTintList(ColorStateList colorStateList0) {
        super.setTickActiveTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTickInactiveRadius(int v) {
        super.setTickInactiveRadius(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTickInactiveTintList(ColorStateList colorStateList0) {
        super.setTickInactiveTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTickTintList(ColorStateList colorStateList0) {
        super.setTickTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTickVisible(boolean z) {
        super.setTickVisible(z);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTrackActiveTintList(ColorStateList colorStateList0) {
        super.setTrackActiveTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTrackHeight(int v) {
        super.setTrackHeight(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTrackInactiveTintList(ColorStateList colorStateList0) {
        super.setTrackInactiveTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTrackInsideCornerSize(int v) {
        super.setTrackInsideCornerSize(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTrackStopIndicatorSize(int v) {
        super.setTrackStopIndicatorSize(v);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setTrackTintList(ColorStateList colorStateList0) {
        super.setTrackTintList(colorStateList0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setValueFrom(float f) {
        super.setValueFrom(f);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setValueTo(float f) {
        super.setValueTo(f);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setValues(List list0) {
        super.setValues(list0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setValues(Float[] arr_float) {
        super.setValues(arr_float);
    }

    class com.google.android.material.slider.RangeSlider.1 {
    }

}

