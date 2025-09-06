package com.google.android.material.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.R.attr;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.LinkedHashSet;
import java.util.Set;

public final class MaterialTimePicker extends DialogFragment implements OnDoubleTapListener {
    public static final class Builder {
        private Integer inputMode;
        private CharSequence negativeButtonText;
        private int negativeButtonTextResId;
        private int overrideThemeResId;
        private CharSequence positiveButtonText;
        private int positiveButtonTextResId;
        private TimeModel time;
        private CharSequence titleText;
        private int titleTextResId;

        public Builder() {
            this.time = new TimeModel();
            this.titleTextResId = 0;
            this.positiveButtonTextResId = 0;
            this.negativeButtonTextResId = 0;
            this.overrideThemeResId = 0;
        }

        static TimeModel access$000(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.time;
        }

        static Integer access$100(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.inputMode;
        }

        static int access$200(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.titleTextResId;
        }

        static CharSequence access$300(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.titleText;
        }

        static int access$400(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.positiveButtonTextResId;
        }

        static CharSequence access$500(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.positiveButtonText;
        }

        static int access$600(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.negativeButtonTextResId;
        }

        static CharSequence access$700(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.negativeButtonText;
        }

        static int access$800(Builder materialTimePicker$Builder0) {
            return materialTimePicker$Builder0.overrideThemeResId;
        }

        public MaterialTimePicker build() {
            return MaterialTimePicker.newInstance(this);
        }

        public Builder setHour(int v) {
            this.time.setHourOfDay(v);
            return this;
        }

        public Builder setInputMode(int v) {
            this.inputMode = v;
            return this;
        }

        public Builder setMinute(int v) {
            this.time.setMinute(v);
            return this;
        }

        public Builder setNegativeButtonText(int v) {
            this.negativeButtonTextResId = v;
            return this;
        }

        public Builder setNegativeButtonText(CharSequence charSequence0) {
            this.negativeButtonText = charSequence0;
            return this;
        }

        public Builder setPositiveButtonText(int v) {
            this.positiveButtonTextResId = v;
            return this;
        }

        public Builder setPositiveButtonText(CharSequence charSequence0) {
            this.positiveButtonText = charSequence0;
            return this;
        }

        public Builder setTheme(int v) {
            this.overrideThemeResId = v;
            return this;
        }

        public Builder setTimeFormat(int v) {
            int v1 = this.time.hour;
            int v2 = this.time.minute;
            TimeModel timeModel0 = new TimeModel(v);
            this.time = timeModel0;
            timeModel0.setMinute(v2);
            this.time.setHourOfDay(v1);
            return this;
        }

        public Builder setTitleText(int v) {
            this.titleTextResId = v;
            return this;
        }

        public Builder setTitleText(CharSequence charSequence0) {
            this.titleText = charSequence0;
            return this;
        }
    }

    public static final int INPUT_MODE_CLOCK = 0;
    static final String INPUT_MODE_EXTRA = "TIME_PICKER_INPUT_MODE";
    public static final int INPUT_MODE_KEYBOARD = 1;
    static final String NEGATIVE_BUTTON_TEXT_EXTRA = "TIME_PICKER_NEGATIVE_BUTTON_TEXT";
    static final String NEGATIVE_BUTTON_TEXT_RES_EXTRA = "TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES";
    static final String OVERRIDE_THEME_RES_ID = "TIME_PICKER_OVERRIDE_THEME_RES_ID";
    static final String POSITIVE_BUTTON_TEXT_EXTRA = "TIME_PICKER_POSITIVE_BUTTON_TEXT";
    static final String POSITIVE_BUTTON_TEXT_RES_EXTRA = "TIME_PICKER_POSITIVE_BUTTON_TEXT_RES";
    static final String TIME_MODEL_EXTRA = "TIME_PICKER_TIME_MODEL";
    static final String TITLE_RES_EXTRA = "TIME_PICKER_TITLE_RES";
    static final String TITLE_TEXT_EXTRA = "TIME_PICKER_TITLE_TEXT";
    private TimePickerPresenter activePresenter;
    private Button cancelButton;
    private final Set cancelListeners;
    private int clockIcon;
    private final Set dismissListeners;
    private int inputMode;
    private int keyboardIcon;
    private MaterialButton modeButton;
    private final Set negativeButtonListeners;
    private CharSequence negativeButtonText;
    private int negativeButtonTextResId;
    private int overrideThemeResId;
    private final Set positiveButtonListeners;
    private CharSequence positiveButtonText;
    private int positiveButtonTextResId;
    private ViewStub textInputStub;
    private TimeModel time;
    private TimePickerClockPresenter timePickerClockPresenter;
    private TimePickerTextInputPresenter timePickerTextInputPresenter;
    private TimePickerView timePickerView;
    private int titleResId;
    private CharSequence titleText;

    public MaterialTimePicker() {
        this.positiveButtonListeners = new LinkedHashSet();
        this.negativeButtonListeners = new LinkedHashSet();
        this.cancelListeners = new LinkedHashSet();
        this.dismissListeners = new LinkedHashSet();
        this.titleResId = 0;
        this.positiveButtonTextResId = 0;
        this.negativeButtonTextResId = 0;
        this.inputMode = 0;
        this.overrideThemeResId = 0;
    }

    public boolean addOnCancelListener(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        return this.cancelListeners.add(dialogInterface$OnCancelListener0);
    }

    public boolean addOnDismissListener(DialogInterface.OnDismissListener dialogInterface$OnDismissListener0) {
        return this.dismissListeners.add(dialogInterface$OnDismissListener0);
    }

    public boolean addOnNegativeButtonClickListener(View.OnClickListener view$OnClickListener0) {
        return this.negativeButtonListeners.add(view$OnClickListener0);
    }

    public boolean addOnPositiveButtonClickListener(View.OnClickListener view$OnClickListener0) {
        return this.positiveButtonListeners.add(view$OnClickListener0);
    }

    public void clearOnCancelListeners() {
        this.cancelListeners.clear();
    }

    public void clearOnDismissListeners() {
        this.dismissListeners.clear();
    }

    public void clearOnNegativeButtonClickListeners() {
        this.negativeButtonListeners.clear();
    }

    public void clearOnPositiveButtonClickListeners() {
        this.positiveButtonListeners.clear();
    }

    private Pair dataForMode(int v) {
        switch(v) {
            case 0: {
                return new Pair(this.keyboardIcon, string.material_timepicker_text_input_mode_description);
            }
            case 1: {
                return new Pair(this.clockIcon, string.material_timepicker_clock_mode_description);
            }
            default: {
                throw new IllegalArgumentException("no icon for mode: " + v);
            }
        }
    }

    public int getHour() {
        return this.time.hour % 24;
    }

    public int getInputMode() {
        return this.inputMode;
    }

    public int getMinute() {
        return this.time.minute;
    }

    private int getThemeResId() {
        int v = this.overrideThemeResId;
        if(v != 0) {
            return v;
        }
        TypedValue typedValue0 = MaterialAttributes.resolve(this.requireContext(), attr.materialTimePickerTheme);
        return typedValue0 == null ? 0 : typedValue0.data;
    }

    TimePickerClockPresenter getTimePickerClockPresenter() {
        return this.timePickerClockPresenter;
    }

    private TimePickerPresenter initializeOrRetrieveActivePresenterForMode(int v, TimePickerView timePickerView0, ViewStub viewStub0) {
        if(v == 0) {
            TimePickerClockPresenter timePickerClockPresenter0 = this.timePickerClockPresenter == null ? new TimePickerClockPresenter(timePickerView0, this.time) : this.timePickerClockPresenter;
            this.timePickerClockPresenter = timePickerClockPresenter0;
            return timePickerClockPresenter0;
        }
        if(this.timePickerTextInputPresenter == null) {
            this.timePickerTextInputPresenter = new TimePickerTextInputPresenter(((LinearLayout)viewStub0.inflate()), this.time);
        }
        this.timePickerTextInputPresenter.clearCheck();
        return this.timePickerTextInputPresenter;
    }

    // 检测为 Lambda 实现
    void lambda$onViewCreated$0$com-google-android-material-timepicker-MaterialTimePicker() [...]

    private static MaterialTimePicker newInstance(Builder materialTimePicker$Builder0) {
        MaterialTimePicker materialTimePicker0 = new MaterialTimePicker();
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("TIME_PICKER_TIME_MODEL", Builder.access$000(materialTimePicker$Builder0));
        if(Builder.access$100(materialTimePicker$Builder0) != null) {
            bundle0.putInt("TIME_PICKER_INPUT_MODE", ((int)Builder.access$100(materialTimePicker$Builder0)));
        }
        bundle0.putInt("TIME_PICKER_TITLE_RES", Builder.access$200(materialTimePicker$Builder0));
        if(Builder.access$300(materialTimePicker$Builder0) != null) {
            bundle0.putCharSequence("TIME_PICKER_TITLE_TEXT", Builder.access$300(materialTimePicker$Builder0));
        }
        bundle0.putInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", Builder.access$400(materialTimePicker$Builder0));
        if(Builder.access$500(materialTimePicker$Builder0) != null) {
            bundle0.putCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT", Builder.access$500(materialTimePicker$Builder0));
        }
        bundle0.putInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", Builder.access$600(materialTimePicker$Builder0));
        if(Builder.access$700(materialTimePicker$Builder0) != null) {
            bundle0.putCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT", Builder.access$700(materialTimePicker$Builder0));
        }
        bundle0.putInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", Builder.access$800(materialTimePicker$Builder0));
        materialTimePicker0.setArguments(bundle0);
        return materialTimePicker0;
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final void onCancel(DialogInterface dialogInterface0) {
        for(Object object0: this.cancelListeners) {
            ((DialogInterface.OnCancelListener)object0).onCancel(dialogInterface0);
        }
        super.onCancel(dialogInterface0);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            bundle0 = this.getArguments();
        }
        this.restoreState(bundle0);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle0) {
        Dialog dialog0 = new Dialog(this.requireContext(), this.getThemeResId());
        Context context0 = dialog0.getContext();
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(context0, null, attr.materialTimePickerStyle, style.Widget_MaterialComponents_TimePicker);
        TypedArray typedArray0 = context0.obtainStyledAttributes(null, styleable.MaterialTimePicker, attr.materialTimePickerStyle, style.Widget_MaterialComponents_TimePicker);
        this.clockIcon = typedArray0.getResourceId(styleable.MaterialTimePicker_clockIcon, 0);
        this.keyboardIcon = typedArray0.getResourceId(styleable.MaterialTimePicker_keyboardIcon, 0);
        int v = typedArray0.getColor(styleable.MaterialTimePicker_backgroundTint, 0);
        typedArray0.recycle();
        materialShapeDrawable0.initializeElevationOverlay(context0);
        materialShapeDrawable0.setFillColor(ColorStateList.valueOf(v));
        Window window0 = dialog0.getWindow();
        window0.setBackgroundDrawable(materialShapeDrawable0);
        window0.requestFeature(1);
        window0.setLayout(-2, -2);
        materialShapeDrawable0.setElevation(ViewCompat.getElevation(window0.getDecorView()));
        return dialog0;
    }

    @Override  // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        View view0 = (ViewGroup)layoutInflater0.inflate(layout.material_timepicker_dialog, viewGroup0);
        TimePickerView timePickerView0 = (TimePickerView)((ViewGroup)view0).findViewById(id.material_timepicker_view);
        this.timePickerView = timePickerView0;
        timePickerView0.setOnDoubleTapListener(this);
        this.textInputStub = (ViewStub)((ViewGroup)view0).findViewById(id.material_textinput_timepicker);
        this.modeButton = (MaterialButton)((ViewGroup)view0).findViewById(id.material_timepicker_mode_button);
        TextView textView0 = (TextView)((ViewGroup)view0).findViewById(id.header_title);
        int v = this.titleResId;
        if(v != 0) {
            textView0.setText(v);
        }
        else if(!TextUtils.isEmpty(this.titleText)) {
            textView0.setText(this.titleText);
        }
        this.updateInputMode(this.modeButton);
        Button button0 = (Button)((ViewGroup)view0).findViewById(id.material_timepicker_ok_button);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                for(Object object0: MaterialTimePicker.this.positiveButtonListeners) {
                    ((View.OnClickListener)object0).onClick(view0);
                }
                MaterialTimePicker.this.dismiss();
            }
        });
        int v1 = this.positiveButtonTextResId;
        if(v1 != 0) {
            button0.setText(v1);
        }
        else if(!TextUtils.isEmpty(this.positiveButtonText)) {
            button0.setText(this.positiveButtonText);
        }
        Button button1 = (Button)((ViewGroup)view0).findViewById(id.material_timepicker_cancel_button);
        this.cancelButton = button1;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                for(Object object0: MaterialTimePicker.this.negativeButtonListeners) {
                    ((View.OnClickListener)object0).onClick(view0);
                }
                MaterialTimePicker.this.dismiss();
            }
        });
        int v2 = this.negativeButtonTextResId;
        if(v2 != 0) {
            this.cancelButton.setText(v2);
        }
        else if(!TextUtils.isEmpty(this.negativeButtonText)) {
            this.cancelButton.setText(this.negativeButtonText);
        }
        this.updateCancelButtonVisibility();
        this.modeButton.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                MaterialTimePicker.this.inputMode = MaterialTimePicker.this.inputMode == 0 ? 1 : 0;
                MaterialTimePicker.this.updateInputMode(MaterialTimePicker.this.modeButton);
            }
        });
        return view0;
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void onDestroyView() {
        super.onDestroyView();
        this.activePresenter = null;
        this.timePickerClockPresenter = null;
        this.timePickerTextInputPresenter = null;
        TimePickerView timePickerView0 = this.timePickerView;
        if(timePickerView0 != null) {
            timePickerView0.setOnDoubleTapListener(null);
            this.timePickerView = null;
        }
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final void onDismiss(DialogInterface dialogInterface0) {
        for(Object object0: this.dismissListeners) {
            ((DialogInterface.OnDismissListener)object0).onDismiss(dialogInterface0);
        }
        super.onDismiss(dialogInterface0);
    }

    @Override  // com.google.android.material.timepicker.TimePickerView$OnDoubleTapListener
    public void onDoubleTap() {
        this.inputMode = 1;
        this.updateInputMode(this.modeButton);
        this.timePickerTextInputPresenter.resetChecked();
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putParcelable("TIME_PICKER_TIME_MODEL", this.time);
        bundle0.putInt("TIME_PICKER_INPUT_MODE", this.inputMode);
        bundle0.putInt("TIME_PICKER_TITLE_RES", this.titleResId);
        bundle0.putCharSequence("TIME_PICKER_TITLE_TEXT", this.titleText);
        bundle0.putInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", this.positiveButtonTextResId);
        bundle0.putCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT", this.positiveButtonText);
        bundle0.putInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", this.negativeButtonTextResId);
        bundle0.putCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT", this.negativeButtonText);
        bundle0.putInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", this.overrideThemeResId);
    }

    @Override  // androidx.fragment.app.Fragment
    public void onViewCreated(View view0, Bundle bundle0) {
        super.onViewCreated(view0, bundle0);
        if(this.activePresenter instanceof TimePickerTextInputPresenter) {
            view0.postDelayed(() -> {
                TimePickerPresenter timePickerPresenter0 = this.activePresenter;
                if(timePickerPresenter0 instanceof TimePickerTextInputPresenter) {
                    ((TimePickerTextInputPresenter)timePickerPresenter0).resetChecked();
                }
            }, 100L);
        }
    }

    public boolean removeOnCancelListener(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        return this.cancelListeners.remove(dialogInterface$OnCancelListener0);
    }

    public boolean removeOnDismissListener(DialogInterface.OnDismissListener dialogInterface$OnDismissListener0) {
        return this.dismissListeners.remove(dialogInterface$OnDismissListener0);
    }

    public boolean removeOnNegativeButtonClickListener(View.OnClickListener view$OnClickListener0) {
        return this.negativeButtonListeners.remove(view$OnClickListener0);
    }

    public boolean removeOnPositiveButtonClickListener(View.OnClickListener view$OnClickListener0) {
        return this.positiveButtonListeners.remove(view$OnClickListener0);
    }

    private void restoreState(Bundle bundle0) {
        if(bundle0 == null) {
            return;
        }
        TimeModel timeModel0 = (TimeModel)bundle0.getParcelable("TIME_PICKER_TIME_MODEL");
        this.time = timeModel0;
        if(timeModel0 == null) {
            this.time = new TimeModel();
        }
        this.inputMode = bundle0.getInt("TIME_PICKER_INPUT_MODE", (this.time.format == 1 ? 1 : 0));
        this.titleResId = bundle0.getInt("TIME_PICKER_TITLE_RES", 0);
        this.titleText = bundle0.getCharSequence("TIME_PICKER_TITLE_TEXT");
        this.positiveButtonTextResId = bundle0.getInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", 0);
        this.positiveButtonText = bundle0.getCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT");
        this.negativeButtonTextResId = bundle0.getInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", 0);
        this.negativeButtonText = bundle0.getCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT");
        this.overrideThemeResId = bundle0.getInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", 0);
    }

    void setActivePresenter(TimePickerPresenter timePickerPresenter0) {
        this.activePresenter = timePickerPresenter0;
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        this.updateCancelButtonVisibility();
    }

    public void setHour(int v) {
        this.time.setHour(v);
        TimePickerPresenter timePickerPresenter0 = this.activePresenter;
        if(timePickerPresenter0 != null) {
            timePickerPresenter0.invalidate();
        }
    }

    public void setMinute(int v) {
        this.time.setMinute(v);
        TimePickerPresenter timePickerPresenter0 = this.activePresenter;
        if(timePickerPresenter0 != null) {
            timePickerPresenter0.invalidate();
        }
    }

    private void updateCancelButtonVisibility() {
        Button button0 = this.cancelButton;
        if(button0 != null) {
            button0.setVisibility((this.isCancelable() ? 0 : 8));
        }
    }

    private void updateInputMode(MaterialButton materialButton0) {
        if(materialButton0 != null && this.timePickerView != null && this.textInputStub != null) {
            TimePickerPresenter timePickerPresenter0 = this.activePresenter;
            if(timePickerPresenter0 != null) {
                timePickerPresenter0.hide();
            }
            TimePickerPresenter timePickerPresenter1 = this.initializeOrRetrieveActivePresenterForMode(this.inputMode, this.timePickerView, this.textInputStub);
            this.activePresenter = timePickerPresenter1;
            timePickerPresenter1.show();
            this.activePresenter.invalidate();
            Pair pair0 = this.dataForMode(this.inputMode);
            materialButton0.setIconResource(((int)(((Integer)pair0.first))));
            materialButton0.setContentDescription(this.getResources().getString(((int)(((Integer)pair0.second)))));
            materialButton0.sendAccessibilityEvent(4);
        }
    }
}

