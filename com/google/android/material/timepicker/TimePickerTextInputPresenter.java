package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.id;
import com.google.android.material.R.string;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import java.lang.reflect.Field;
import java.util.Locale;

class TimePickerTextInputPresenter implements TimePickerPresenter, OnSelectionChange {
    private final TimePickerTextInputKeyController controller;
    private final EditText hourEditText;
    private final ChipTextInputComboView hourTextInput;
    private final TextWatcher hourTextWatcher;
    private final EditText minuteEditText;
    private final ChipTextInputComboView minuteTextInput;
    private final TextWatcher minuteTextWatcher;
    private final TimeModel time;
    private final LinearLayout timePickerView;
    private MaterialButtonToggleGroup toggle;

    public TimePickerTextInputPresenter(LinearLayout linearLayout0, TimeModel timeModel0) {
        this.minuteTextWatcher = new TextWatcherAdapter() {
            @Override  // com.google.android.material.internal.TextWatcherAdapter
            public void afterTextChanged(Editable editable0) {
                try {
                    if(TextUtils.isEmpty(editable0)) {
                        TimePickerTextInputPresenter.this.time.setMinute(0);
                        return;
                    }
                    int v = Integer.parseInt(editable0.toString());
                    TimePickerTextInputPresenter.this.time.setMinute(v);
                }
                catch(NumberFormatException unused_ex) {
                }
            }
        };
        this.hourTextWatcher = new TextWatcherAdapter() {
            @Override  // com.google.android.material.internal.TextWatcherAdapter
            public void afterTextChanged(Editable editable0) {
                try {
                    if(TextUtils.isEmpty(editable0)) {
                        TimePickerTextInputPresenter.this.time.setHour(0);
                        return;
                    }
                    int v = Integer.parseInt(editable0.toString());
                    TimePickerTextInputPresenter.this.time.setHour(v);
                }
                catch(NumberFormatException unused_ex) {
                }
            }
        };
        this.timePickerView = linearLayout0;
        this.time = timeModel0;
        Resources resources0 = linearLayout0.getResources();
        ChipTextInputComboView chipTextInputComboView0 = (ChipTextInputComboView)linearLayout0.findViewById(id.material_minute_text_input);
        this.minuteTextInput = chipTextInputComboView0;
        ChipTextInputComboView chipTextInputComboView1 = (ChipTextInputComboView)linearLayout0.findViewById(id.material_hour_text_input);
        this.hourTextInput = chipTextInputComboView1;
        TextView textView0 = (TextView)chipTextInputComboView0.findViewById(id.material_label);
        TextView textView1 = (TextView)chipTextInputComboView1.findViewById(id.material_label);
        textView0.setText(resources0.getString(string.material_timepicker_minute));
        textView1.setText(resources0.getString(string.material_timepicker_hour));
        chipTextInputComboView0.setTag(id.selection_type, 12);
        chipTextInputComboView1.setTag(id.selection_type, 10);
        if(timeModel0.format == 0) {
            this.setupPeriodToggle();
        }
        com.google.android.material.timepicker.TimePickerTextInputPresenter.3 timePickerTextInputPresenter$30 = new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                int v = (int)(((Integer)view0.getTag(id.selection_type)));
                TimePickerTextInputPresenter.this.onSelectionChanged(v);
            }
        };
        chipTextInputComboView1.setOnClickListener(timePickerTextInputPresenter$30);
        chipTextInputComboView0.setOnClickListener(timePickerTextInputPresenter$30);
        chipTextInputComboView1.addInputFilter(timeModel0.getHourInputValidator());
        chipTextInputComboView0.addInputFilter(timeModel0.getMinuteInputValidator());
        this.hourEditText = chipTextInputComboView1.getTextInput().getEditText();
        this.minuteEditText = chipTextInputComboView0.getTextInput().getEditText();
        this.controller = new TimePickerTextInputKeyController(chipTextInputComboView1, chipTextInputComboView0, timeModel0);
        chipTextInputComboView1.setChipDelegate(new ClickActionDelegate(linearLayout0.getContext(), string.material_hour_selection) {
            @Override  // com.google.android.material.timepicker.ClickActionDelegate
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setContentDescription(view0.getResources().getString(timeModel0.getHourContentDescriptionResId(), new Object[]{String.valueOf(timeModel0.getHourForDisplay())}));
            }
        });
        chipTextInputComboView0.setChipDelegate(new ClickActionDelegate(linearLayout0.getContext(), string.material_minute_selection) {
            @Override  // com.google.android.material.timepicker.ClickActionDelegate
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setContentDescription(view0.getResources().getString(string.material_minute_suffix, new Object[]{String.valueOf(timeModel0.minute)}));
            }
        });
        this.initialize();
    }

    private void addTextWatchers() {
        this.hourEditText.addTextChangedListener(this.hourTextWatcher);
        this.minuteEditText.addTextChangedListener(this.minuteTextWatcher);
    }

    public void clearCheck() {
        this.minuteTextInput.setChecked(false);
        this.hourTextInput.setChecked(false);
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void hide() {
        View view0 = this.timePickerView.getFocusedChild();
        if(view0 != null) {
            ViewUtils.hideKeyboard(view0, false);
        }
        this.timePickerView.setVisibility(8);
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void initialize() {
        this.addTextWatchers();
        this.setTime(this.time);
        this.controller.bind();
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void invalidate() {
        this.setTime(this.time);
    }

    // 检测为 Lambda 实现
    void lambda$setupPeriodToggle$0$com-google-android-material-timepicker-TimePickerTextInputPresenter(MaterialButtonToggleGroup materialButtonToggleGroup0, int v, boolean z) [...]

    @Override  // com.google.android.material.timepicker.TimePickerView$OnSelectionChange
    public void onSelectionChanged(int v) {
        this.time.selection = v;
        boolean z = true;
        this.minuteTextInput.setChecked(v == 12);
        ChipTextInputComboView chipTextInputComboView0 = this.hourTextInput;
        if(v != 10) {
            z = false;
        }
        chipTextInputComboView0.setChecked(z);
        this.updateSelection();
    }

    private void removeTextWatchers() {
        this.hourEditText.removeTextChangedListener(this.hourTextWatcher);
        this.minuteEditText.removeTextChangedListener(this.minuteTextWatcher);
    }

    public void resetChecked() {
        boolean z = true;
        this.minuteTextInput.setChecked(this.time.selection == 12);
        ChipTextInputComboView chipTextInputComboView0 = this.hourTextInput;
        if(this.time.selection != 10) {
            z = false;
        }
        chipTextInputComboView0.setChecked(z);
    }

    private static void setCursorDrawableColor(EditText editText0, int v) {
        try {
            Context context0 = editText0.getContext();
            Field field0 = TextView.class.getDeclaredField("mCursorDrawableRes");
            field0.setAccessible(true);
            int v1 = field0.getInt(editText0);
            Field field1 = TextView.class.getDeclaredField("mEditor");
            field1.setAccessible(true);
            Object object0 = field1.get(editText0);
            Field field2 = object0.getClass().getDeclaredField("mCursorDrawable");
            field2.setAccessible(true);
            Drawable drawable0 = AppCompatResources.getDrawable(context0, v1);
            drawable0.setColorFilter(v, PorterDuff.Mode.SRC_IN);
            field2.set(object0, new Drawable[]{drawable0, drawable0});
        }
        catch(Throwable unused_ex) {
        }
    }

    private void setTime(TimeModel timeModel0) {
        this.removeTextWatchers();
        Locale locale0 = this.timePickerView.getResources().getConfiguration().locale;
        Object[] arr_object = {timeModel0.minute};
        Object[] arr_object1 = {timeModel0.getHourForDisplay()};
        this.minuteTextInput.setText(String.format(locale0, "%02d", arr_object));
        this.hourTextInput.setText(String.format(locale0, "%02d", arr_object1));
        this.addTextWatchers();
        this.updateSelection();
    }

    private void setupPeriodToggle() {
        MaterialButtonToggleGroup materialButtonToggleGroup0 = (MaterialButtonToggleGroup)this.timePickerView.findViewById(id.material_clock_period_toggle);
        this.toggle = materialButtonToggleGroup0;
        materialButtonToggleGroup0.addOnButtonCheckedListener((MaterialButtonToggleGroup materialButtonToggleGroup0, int v, boolean z) -> {
            if(!z) {
                return;
            }
            this.time.setPeriod((v == id.material_clock_period_pm_button ? 1 : 0));
        });
        this.toggle.setVisibility(0);
        this.updateSelection();
    }

    @Override  // com.google.android.material.timepicker.TimePickerPresenter
    public void show() {
        this.timePickerView.setVisibility(0);
        this.onSelectionChanged(this.time.selection);
    }

    private void updateSelection() {
        MaterialButtonToggleGroup materialButtonToggleGroup0 = this.toggle;
        if(materialButtonToggleGroup0 == null) {
            return;
        }
        materialButtonToggleGroup0.check((this.time.period == 0 ? id.material_clock_period_am_button : id.material_clock_period_pm_button));
    }
}

