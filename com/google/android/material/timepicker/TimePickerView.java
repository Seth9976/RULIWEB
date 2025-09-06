package com.google.android.material.timepicker;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import java.util.Locale;

class TimePickerView extends ConstraintLayout implements TimePickerControls {
    interface OnDoubleTapListener {
        void onDoubleTap();
    }

    interface OnPeriodChangeListener {
        void onPeriodChange(int arg1);
    }

    interface OnSelectionChange {
        void onSelectionChanged(int arg1);
    }

    static final String GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME = "android.view.View";
    private final ClockFaceView clockFace;
    private final ClockHandView clockHandView;
    private final Chip hourView;
    private final Chip minuteView;
    private OnDoubleTapListener onDoubleTapListener;
    private OnPeriodChangeListener onPeriodChangeListener;
    private OnSelectionChange onSelectionChangeListener;
    private final View.OnClickListener selectionListener;
    private final MaterialButtonToggleGroup toggle;

    public TimePickerView(Context context0) {
        this(context0, null);
    }

    public TimePickerView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public TimePickerView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.selectionListener = new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                if(TimePickerView.this.onSelectionChangeListener != null) {
                    TimePickerView.this.onSelectionChangeListener.onSelectionChanged(((int)(((Integer)view0.getTag(id.selection_type)))));
                }
            }
        };
        LayoutInflater.from(context0).inflate(layout.material_timepicker, this);
        this.clockFace = (ClockFaceView)this.findViewById(id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup0 = (MaterialButtonToggleGroup)this.findViewById(id.material_clock_period_toggle);
        this.toggle = materialButtonToggleGroup0;
        materialButtonToggleGroup0.addOnButtonCheckedListener((MaterialButtonToggleGroup materialButtonToggleGroup0, int v, boolean z) -> if(z && this.onPeriodChangeListener != null) {
            this.onPeriodChangeListener.onPeriodChange((v == id.material_clock_period_pm_button ? 1 : 0));
        });
        this.minuteView = (Chip)this.findViewById(id.material_minute_tv);
        this.hourView = (Chip)this.findViewById(id.material_hour_tv);
        this.clockHandView = (ClockHandView)this.findViewById(id.material_clock_hand);
        this.setupDoubleTap();
        this.setUpDisplay();
    }

    public void addOnRotateListener(OnRotateListener clockHandView$OnRotateListener0) {
        this.clockHandView.addOnRotateListener(clockHandView$OnRotateListener0);
    }

    int getCurrentLevel() {
        return this.clockFace.getCurrentLevel();
    }

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-timepicker-TimePickerView(MaterialButtonToggleGroup materialButtonToggleGroup0, int v, boolean z) [...]

    @Override  // android.view.View
    protected void onVisibilityChanged(View view0, int v) {
        super.onVisibilityChanged(view0, v);
        if(view0 == this && v == 0) {
            this.hourView.sendAccessibilityEvent(8);
        }
    }

    @Override  // com.google.android.material.timepicker.TimePickerControls
    public void setActiveSelection(int v) {
        boolean z = true;
        this.updateSelection(this.minuteView, v == 12);
        Chip chip0 = this.hourView;
        if(v != 10) {
            z = false;
        }
        this.updateSelection(chip0, z);
    }

    public void setAnimateOnTouchUp(boolean z) {
        this.clockHandView.setAnimateOnTouchUp(z);
    }

    void setCurrentLevel(int v) {
        this.clockFace.setCurrentLevel(v);
    }

    @Override  // com.google.android.material.timepicker.TimePickerControls
    public void setHandRotation(float f) {
        this.clockHandView.setHandRotation(f);
    }

    public void setHandRotation(float f, boolean z) {
        this.clockHandView.setHandRotation(f, z);
    }

    public void setHourClickDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat0) {
        ViewCompat.setAccessibilityDelegate(this.minuteView, accessibilityDelegateCompat0);
    }

    public void setMinuteHourDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat0) {
        ViewCompat.setAccessibilityDelegate(this.hourView, accessibilityDelegateCompat0);
    }

    public void setOnActionUpListener(OnActionUpListener clockHandView$OnActionUpListener0) {
        this.clockHandView.setOnActionUpListener(clockHandView$OnActionUpListener0);
    }

    void setOnDoubleTapListener(OnDoubleTapListener timePickerView$OnDoubleTapListener0) {
        this.onDoubleTapListener = timePickerView$OnDoubleTapListener0;
    }

    void setOnPeriodChangeListener(OnPeriodChangeListener timePickerView$OnPeriodChangeListener0) {
        this.onPeriodChangeListener = timePickerView$OnPeriodChangeListener0;
    }

    void setOnSelectionChangeListener(OnSelectionChange timePickerView$OnSelectionChange0) {
        this.onSelectionChangeListener = timePickerView$OnSelectionChange0;
    }

    private void setUpDisplay() {
        this.minuteView.setTag(id.selection_type, 12);
        this.hourView.setTag(id.selection_type, 10);
        this.minuteView.setOnClickListener(this.selectionListener);
        this.hourView.setOnClickListener(this.selectionListener);
        this.minuteView.setAccessibilityClassName("android.view.View");
        this.hourView.setAccessibilityClassName("android.view.View");
    }

    @Override  // com.google.android.material.timepicker.TimePickerControls
    public void setValues(String[] arr_s, int v) {
        this.clockFace.setValues(arr_s, v);
    }

    private void setupDoubleTap() {
        com.google.android.material.timepicker.TimePickerView.3 timePickerView$30 = new View.OnTouchListener() {
            // 去混淆评级： 低(20)
            @Override  // android.view.View$OnTouchListener
            public boolean onTouch(View view0, MotionEvent motionEvent0) {
                return ((Checkable)view0).isChecked() ? new GestureDetector(this.getContext(), new com.google.android.material.timepicker.TimePickerView.2(this)).onTouchEvent(motionEvent0) : false;
            }
        };
        this.minuteView.setOnTouchListener(timePickerView$30);
        this.hourView.setOnTouchListener(timePickerView$30);

        class com.google.android.material.timepicker.TimePickerView.2 extends GestureDetector.SimpleOnGestureListener {
            @Override  // android.view.GestureDetector$SimpleOnGestureListener
            public boolean onDoubleTap(MotionEvent motionEvent0) {
                OnDoubleTapListener timePickerView$OnDoubleTapListener0 = TimePickerView.this.onDoubleTapListener;
                if(timePickerView$OnDoubleTapListener0 != null) {
                    timePickerView$OnDoubleTapListener0.onDoubleTap();
                    return true;
                }
                return false;
            }
        }

    }

    public void showToggle() {
        this.toggle.setVisibility(0);
    }

    private void updateSelection(Chip chip0, boolean z) {
        chip0.setChecked(z);
        ViewCompat.setAccessibilityLiveRegion(chip0, (z ? 2 : 0));
    }

    @Override  // com.google.android.material.timepicker.TimePickerControls
    public void updateTime(int v, int v1, int v2) {
        this.toggle.check((v == 1 ? id.material_clock_period_pm_button : id.material_clock_period_am_button));
        Locale locale0 = this.getResources().getConfiguration().locale;
        String s = String.format(locale0, "%02d", v2);
        String s1 = String.format(locale0, "%02d", v1);
        if(!TextUtils.equals(this.minuteView.getText(), s)) {
            this.minuteView.setText(s);
        }
        if(!TextUtils.equals(this.hourView.getText(), s1)) {
            this.hourView.setText(s1);
        }
    }
}

