package com.google.android.material.timepicker;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.string;

class TimePickerClockPresenter implements OnActionUpListener, OnRotateListener, TimePickerPresenter, OnPeriodChangeListener, OnSelectionChange {
    private static final int DEGREES_PER_HOUR = 30;
    private static final int DEGREES_PER_MINUTE = 6;
    private static final String[] HOUR_CLOCK_24_VALUES;
    private static final String[] HOUR_CLOCK_VALUES;
    private static final String[] MINUTE_CLOCK_VALUES;
    private boolean broadcasting;
    private float hourRotation;
    private float minuteRotation;
    private final TimeModel time;
    private final TimePickerView timePickerView;

    static {
        TimePickerClockPresenter.HOUR_CLOCK_VALUES = new String[]{"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        TimePickerClockPresenter.HOUR_CLOCK_24_VALUES = new String[]{"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        TimePickerClockPresenter.MINUTE_CLOCK_VALUES = new String[]{"00", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
    }

    public TimePickerClockPresenter(TimePickerView timePickerView0, TimeModel timeModel0) {
        this.broadcasting = false;
        this.timePickerView = timePickerView0;
        this.time = timeModel0;
        this.initialize();
    }

    private String[] getHourClockValues() {
        return this.time.format == 1 ? TimePickerClockPresenter.HOUR_CLOCK_24_VALUES : TimePickerClockPresenter.HOUR_CLOCK_VALUES;
    }

    private int getHourRotation() {
        return this.time.getHourForDisplay() * 30 % 360;
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void hide() {
        this.timePickerView.setVisibility(8);
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void initialize() {
        if(this.time.format == 0) {
            this.timePickerView.showToggle();
        }
        this.timePickerView.addOnRotateListener(this);
        this.timePickerView.setOnSelectionChangeListener(this);
        this.timePickerView.setOnPeriodChangeListener(this);
        this.timePickerView.setOnActionUpListener(this);
        this.updateValues();
        this.invalidate();
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void invalidate() {
        this.hourRotation = (float)this.getHourRotation();
        this.minuteRotation = (float)(this.time.minute * 6);
        this.setSelection(this.time.selection, false);
        this.updateTime();
    }

    @Override  // com.google.android.material.timepicker.ClockHandView$OnActionUpListener
    public void onActionUp(float f, boolean z) {
        this.broadcasting = true;
        int v = this.time.minute;
        int v1 = this.time.hour;
        if(this.time.selection == 10) {
            this.timePickerView.setHandRotation(this.hourRotation, false);
            AccessibilityManager accessibilityManager0 = (AccessibilityManager)ContextCompat.getSystemService(this.timePickerView.getContext(), AccessibilityManager.class);
            if(accessibilityManager0 == null || !accessibilityManager0.isTouchExplorationEnabled()) {
                this.setSelection(12, true);
            }
        }
        else {
            int v2 = Math.round(f);
            if(!z) {
                this.time.setMinute((v2 + 15) / 30 * 5);
                this.minuteRotation = (float)(this.time.minute * 6);
            }
            this.timePickerView.setHandRotation(this.minuteRotation, z);
        }
        this.broadcasting = false;
        this.updateTime();
        this.performHapticFeedback(v1, v);
    }

    @Override  // com.google.android.material.timepicker.TimePickerView$OnPeriodChangeListener
    public void onPeriodChange(int v) {
        this.time.setPeriod(v);
    }

    @Override  // com.google.android.material.timepicker.ClockHandView$OnRotateListener
    public void onRotate(float f, boolean z) {
        if(!this.broadcasting) {
            int v = this.time.hour;
            int v1 = this.time.minute;
            int v2 = Math.round(f);
            if(this.time.selection == 12) {
                this.time.setMinute((v2 + 3) / 6);
                this.minuteRotation = (float)Math.floor(this.time.minute * 6);
            }
            else {
                int v3 = (v2 + 15) / 30;
                if(this.time.format == 1) {
                    v3 %= 12;
                    if(this.timePickerView.getCurrentLevel() == 2) {
                        v3 += 12;
                    }
                }
                this.time.setHour(v3);
                this.hourRotation = (float)this.getHourRotation();
            }
            if(!z) {
                this.updateTime();
                this.performHapticFeedback(v, v1);
            }
        }
    }

    @Override  // com.google.android.material.timepicker.TimePickerView$OnSelectionChange
    public void onSelectionChanged(int v) {
        this.setSelection(v, true);
    }

    private void performHapticFeedback(int v, int v1) {
        if(this.time.minute == v1 && this.time.hour == v) {
            return;
        }
        this.timePickerView.performHapticFeedback(4);
    }

    void setSelection(int v, boolean z) {
        this.timePickerView.setAnimateOnTouchUp(v == 12);
        this.time.selection = v;
        String[] arr_s = v == 12 ? this.getHourClockValues() : TimePickerClockPresenter.MINUTE_CLOCK_VALUES;
        this.timePickerView.setValues(arr_s, (v == 12 ? this.time.getHourContentDescriptionResId() : string.material_minute_suffix));
        this.updateCurrentLevel();
        this.timePickerView.setHandRotation((v == 12 ? this.hourRotation : this.minuteRotation), z);
        this.timePickerView.setActiveSelection(v);
        com.google.android.material.timepicker.TimePickerClockPresenter.1 timePickerClockPresenter$10 = new ClickActionDelegate(this.timePickerView.getContext(), string.material_hour_selection) {
            @Override  // com.google.android.material.timepicker.ClickActionDelegate
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setContentDescription(view0.getResources().getString(TimePickerClockPresenter.this.time.getHourContentDescriptionResId(), new Object[]{String.valueOf(TimePickerClockPresenter.this.time.getHourForDisplay())}));
            }
        };
        this.timePickerView.setMinuteHourDelegate(timePickerClockPresenter$10);
        com.google.android.material.timepicker.TimePickerClockPresenter.2 timePickerClockPresenter$20 = new ClickActionDelegate(this.timePickerView.getContext(), string.material_minute_selection) {
            @Override  // com.google.android.material.timepicker.ClickActionDelegate
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setContentDescription(view0.getResources().getString(string.material_minute_suffix, new Object[]{String.valueOf(TimePickerClockPresenter.this.time.minute)}));
            }
        };
        this.timePickerView.setHourClickDelegate(timePickerClockPresenter$20);
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void show() {
        this.timePickerView.setVisibility(0);
    }

    private void updateCurrentLevel() {
        this.timePickerView.setCurrentLevel((this.time.selection != 10 || this.time.format != 1 || this.time.hour < 12 ? 1 : 2));
    }

    private void updateTime() {
        int v = this.time.period;
        int v1 = this.time.getHourForDisplay();
        this.timePickerView.updateTime(v, v1, this.time.minute);
    }

    private void updateValues() {
        this.updateValues(TimePickerClockPresenter.HOUR_CLOCK_VALUES, "%d");
        this.updateValues(TimePickerClockPresenter.MINUTE_CLOCK_VALUES, "%02d");
    }

    private void updateValues(String[] arr_s, String s) {
        for(int v = 0; v < arr_s.length; ++v) {
            arr_s[v] = TimeModel.formatText(this.timePickerView.getResources(), arr_s[v], s);
        }
    }
}

