package com.google.android.material.slider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.google.android.material.R.attr;

public class Slider extends BaseSlider {
    public interface OnChangeListener extends BaseOnChangeListener {
        void onValueChange(Slider arg1, float arg2, boolean arg3);
    }

    public interface OnSliderTouchListener extends BaseOnSliderTouchListener {
        void onStartTrackingTouch(Slider arg1);

        void onStopTrackingTouch(Slider arg1);
    }

    public Slider(Context context0) {
        this(context0, null);
    }

    public Slider(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.sliderStyle);
    }

    public Slider(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, new int[]{0x1010024});
        if(typedArray0.hasValue(0)) {
            this.setValue(typedArray0.getFloat(0, 0.0f));
        }
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

    public float getValue() {
        return (float)(((Float)this.getValues().get(0)));
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
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return super.onTouchEvent(motionEvent0);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    protected boolean pickActiveThumb() {
        if(this.getActiveThumbIndex() != -1) {
            return true;
        }
        this.setActiveThumbIndex(0);
        return true;
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

    public void setValue(float f) {
        this.setValues(new Float[]{f});
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setValueFrom(float f) {
        super.setValueFrom(f);
    }

    @Override  // com.google.android.material.slider.BaseSlider
    public void setValueTo(float f) {
        super.setValueTo(f);
    }
}

