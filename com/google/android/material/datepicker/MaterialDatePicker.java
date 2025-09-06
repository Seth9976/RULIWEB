package com.google.android.material.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.drawable;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;

public final class MaterialDatePicker extends DialogFragment {
    public static final class Builder {
        CalendarConstraints calendarConstraints;
        final DateSelector dateSelector;
        DayViewDecorator dayViewDecorator;
        int inputMode;
        CharSequence negativeButtonContentDescription;
        int negativeButtonContentDescriptionResId;
        CharSequence negativeButtonText;
        int negativeButtonTextResId;
        int overrideThemeResId;
        CharSequence positiveButtonContentDescription;
        int positiveButtonContentDescriptionResId;
        CharSequence positiveButtonText;
        int positiveButtonTextResId;
        Object selection;
        CharSequence titleText;
        int titleTextResId;

        private Builder(DateSelector dateSelector0) {
            this.overrideThemeResId = 0;
            this.titleTextResId = 0;
            this.titleText = null;
            this.positiveButtonTextResId = 0;
            this.positiveButtonText = null;
            this.positiveButtonContentDescriptionResId = 0;
            this.positiveButtonContentDescription = null;
            this.negativeButtonTextResId = 0;
            this.negativeButtonText = null;
            this.negativeButtonContentDescriptionResId = 0;
            this.negativeButtonContentDescription = null;
            this.selection = null;
            this.inputMode = 0;
            this.dateSelector = dateSelector0;
        }

        public MaterialDatePicker build() {
            if(this.calendarConstraints == null) {
                this.calendarConstraints = new com.google.android.material.datepicker.CalendarConstraints.Builder().build();
            }
            if(this.titleTextResId == 0) {
                this.titleTextResId = this.dateSelector.getDefaultTitleResId();
            }
            Object object0 = this.selection;
            if(object0 != null) {
                this.dateSelector.setSelection(object0);
            }
            if(this.calendarConstraints.getOpenAt() == null) {
                this.calendarConstraints.setOpenAt(this.createDefaultOpenAt());
            }
            return MaterialDatePicker.newInstance(this);
        }

        private Month createDefaultOpenAt() {
            if(!this.dateSelector.getSelectedDays().isEmpty()) {
                Object object0 = this.dateSelector.getSelectedDays().iterator().next();
                Month month0 = Month.create(((long)(((Long)object0))));
                if(Builder.monthInValidRange(month0, this.calendarConstraints)) {
                    return month0;
                }
            }
            Month month1 = Month.current();
            return Builder.monthInValidRange(month1, this.calendarConstraints) ? month1 : this.calendarConstraints.getStart();
        }

        public static Builder customDatePicker(DateSelector dateSelector0) {
            return new Builder(dateSelector0);
        }

        public static Builder datePicker() {
            return new Builder(new SingleDateSelector());
        }

        public static Builder dateRangePicker() {
            return new Builder(new RangeDateSelector());
        }

        private static boolean monthInValidRange(Month month0, CalendarConstraints calendarConstraints0) {
            return month0.compareTo(calendarConstraints0.getStart()) >= 0 && month0.compareTo(calendarConstraints0.getEnd()) <= 0;
        }

        public Builder setCalendarConstraints(CalendarConstraints calendarConstraints0) {
            this.calendarConstraints = calendarConstraints0;
            return this;
        }

        public Builder setDayViewDecorator(DayViewDecorator dayViewDecorator0) {
            this.dayViewDecorator = dayViewDecorator0;
            return this;
        }

        public Builder setInputMode(int v) {
            this.inputMode = v;
            return this;
        }

        public Builder setNegativeButtonContentDescription(int v) {
            this.negativeButtonContentDescriptionResId = v;
            this.negativeButtonContentDescription = null;
            return this;
        }

        public Builder setNegativeButtonContentDescription(CharSequence charSequence0) {
            this.negativeButtonContentDescription = charSequence0;
            this.negativeButtonContentDescriptionResId = 0;
            return this;
        }

        public Builder setNegativeButtonText(int v) {
            this.negativeButtonTextResId = v;
            this.negativeButtonText = null;
            return this;
        }

        public Builder setNegativeButtonText(CharSequence charSequence0) {
            this.negativeButtonText = charSequence0;
            this.negativeButtonTextResId = 0;
            return this;
        }

        public Builder setPositiveButtonContentDescription(int v) {
            this.positiveButtonContentDescriptionResId = v;
            this.positiveButtonContentDescription = null;
            return this;
        }

        public Builder setPositiveButtonContentDescription(CharSequence charSequence0) {
            this.positiveButtonContentDescription = charSequence0;
            this.positiveButtonContentDescriptionResId = 0;
            return this;
        }

        public Builder setPositiveButtonText(int v) {
            this.positiveButtonTextResId = v;
            this.positiveButtonText = null;
            return this;
        }

        public Builder setPositiveButtonText(CharSequence charSequence0) {
            this.positiveButtonText = charSequence0;
            this.positiveButtonTextResId = 0;
            return this;
        }

        public Builder setSelection(Object object0) {
            this.selection = object0;
            return this;
        }

        public Builder setTextInputFormat(SimpleDateFormat simpleDateFormat0) {
            this.dateSelector.setTextInputFormat(simpleDateFormat0);
            return this;
        }

        public Builder setTheme(int v) {
            this.overrideThemeResId = v;
            return this;
        }

        public Builder setTitleText(int v) {
            this.titleTextResId = v;
            this.titleText = null;
            return this;
        }

        public Builder setTitleText(CharSequence charSequence0) {
            this.titleText = charSequence0;
            this.titleTextResId = 0;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InputMode {
    }

    private static final String CALENDAR_CONSTRAINTS_KEY = "CALENDAR_CONSTRAINTS_KEY";
    static final Object CANCEL_BUTTON_TAG = null;
    static final Object CONFIRM_BUTTON_TAG = null;
    private static final String DATE_SELECTOR_KEY = "DATE_SELECTOR_KEY";
    private static final String DAY_VIEW_DECORATOR_KEY = "DAY_VIEW_DECORATOR_KEY";
    public static final int INPUT_MODE_CALENDAR = 0;
    private static final String INPUT_MODE_KEY = "INPUT_MODE_KEY";
    public static final int INPUT_MODE_TEXT = 1;
    private static final String NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY = "NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY";
    private static final String NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY = "NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY";
    private static final String NEGATIVE_BUTTON_TEXT_KEY = "NEGATIVE_BUTTON_TEXT_KEY";
    private static final String NEGATIVE_BUTTON_TEXT_RES_ID_KEY = "NEGATIVE_BUTTON_TEXT_RES_ID_KEY";
    private static final String OVERRIDE_THEME_RES_ID = "OVERRIDE_THEME_RES_ID";
    private static final String POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY = "POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY";
    private static final String POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY = "POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY";
    private static final String POSITIVE_BUTTON_TEXT_KEY = "POSITIVE_BUTTON_TEXT_KEY";
    private static final String POSITIVE_BUTTON_TEXT_RES_ID_KEY = "POSITIVE_BUTTON_TEXT_RES_ID_KEY";
    private static final String TITLE_TEXT_KEY = "TITLE_TEXT_KEY";
    private static final String TITLE_TEXT_RES_ID_KEY = "TITLE_TEXT_RES_ID_KEY";
    static final Object TOGGLE_BUTTON_TAG;
    private MaterialShapeDrawable background;
    private MaterialCalendar calendar;
    private CalendarConstraints calendarConstraints;
    private Button confirmButton;
    private DateSelector dateSelector;
    private DayViewDecorator dayViewDecorator;
    private boolean edgeToEdgeEnabled;
    private CharSequence fullTitleText;
    private boolean fullscreen;
    private TextView headerSelectionText;
    private TextView headerTitleTextView;
    private CheckableImageButton headerToggleButton;
    private int inputMode;
    private CharSequence negativeButtonContentDescription;
    private int negativeButtonContentDescriptionResId;
    private CharSequence negativeButtonText;
    private int negativeButtonTextResId;
    private final LinkedHashSet onCancelListeners;
    private final LinkedHashSet onDismissListeners;
    private final LinkedHashSet onNegativeButtonClickListeners;
    private final LinkedHashSet onPositiveButtonClickListeners;
    private int overrideThemeResId;
    private PickerFragment pickerFragment;
    private CharSequence positiveButtonContentDescription;
    private int positiveButtonContentDescriptionResId;
    private CharSequence positiveButtonText;
    private int positiveButtonTextResId;
    private CharSequence singleLineTitleText;
    private CharSequence titleText;
    private int titleTextResId;

    static {
        MaterialDatePicker.CONFIRM_BUTTON_TAG = "CONFIRM_BUTTON_TAG";
        MaterialDatePicker.CANCEL_BUTTON_TAG = "CANCEL_BUTTON_TAG";
        MaterialDatePicker.TOGGLE_BUTTON_TAG = "TOGGLE_BUTTON_TAG";
    }

    public MaterialDatePicker() {
        this.onPositiveButtonClickListeners = new LinkedHashSet();
        this.onNegativeButtonClickListeners = new LinkedHashSet();
        this.onCancelListeners = new LinkedHashSet();
        this.onDismissListeners = new LinkedHashSet();
    }

    public boolean addOnCancelListener(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        return this.onCancelListeners.add(dialogInterface$OnCancelListener0);
    }

    public boolean addOnDismissListener(DialogInterface.OnDismissListener dialogInterface$OnDismissListener0) {
        return this.onDismissListeners.add(dialogInterface$OnDismissListener0);
    }

    public boolean addOnNegativeButtonClickListener(View.OnClickListener view$OnClickListener0) {
        return this.onNegativeButtonClickListeners.add(view$OnClickListener0);
    }

    public boolean addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener materialPickerOnPositiveButtonClickListener0) {
        return this.onPositiveButtonClickListeners.add(materialPickerOnPositiveButtonClickListener0);
    }

    public void clearOnCancelListeners() {
        this.onCancelListeners.clear();
    }

    public void clearOnDismissListeners() {
        this.onDismissListeners.clear();
    }

    public void clearOnNegativeButtonClickListeners() {
        this.onNegativeButtonClickListeners.clear();
    }

    public void clearOnPositiveButtonClickListeners() {
        this.onPositiveButtonClickListeners.clear();
    }

    private static Drawable createHeaderToggleDrawable(Context context0) {
        Drawable drawable0 = new StateListDrawable();
        Drawable drawable1 = AppCompatResources.getDrawable(context0, drawable.material_ic_calendar_black_24dp);
        ((StateListDrawable)drawable0).addState(new int[]{0x10100A0}, drawable1);
        Drawable drawable2 = AppCompatResources.getDrawable(context0, drawable.material_ic_edit_black_24dp);
        ((StateListDrawable)drawable0).addState(new int[0], drawable2);
        return drawable0;
    }

    private void enableEdgeToEdgeIfNeeded(Window window0) {
        if(this.edgeToEdgeEnabled) {
            return;
        }
        View view0 = this.requireView().findViewById(id.fullscreen_header);
        EdgeToEdgeUtils.applyEdgeToEdge(window0, true, ViewUtils.getBackgroundColor(view0), null);
        int v = view0.getPaddingTop();
        ViewCompat.setOnApplyWindowInsetsListener(view0, new OnApplyWindowInsetsListener() {
            @Override  // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                int v = windowInsetsCompat0.getInsets(0x207).top;
                if(view0.getLayoutParams().height >= 0) {
                    ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
                    viewGroup$LayoutParams0.height = view0.getLayoutParams().height + v;
                    ViewGroup.LayoutParams viewGroup$LayoutParams1 = view0.getLayoutParams();
                    view0.setLayoutParams(viewGroup$LayoutParams1);
                }
                int v1 = view0.getPaddingLeft();
                int v2 = view0.getPaddingRight();
                int v3 = view0.getPaddingBottom();
                view0.setPadding(v1, v + v, v2, v3);
                return windowInsetsCompat0;
            }
        });
        this.edgeToEdgeEnabled = true;
    }

    private DateSelector getDateSelector() {
        if(this.dateSelector == null) {
            this.dateSelector = (DateSelector)this.getArguments().getParcelable("DATE_SELECTOR_KEY");
        }
        return this.dateSelector;
    }

    private static CharSequence getFirstLineBySeparator(CharSequence charSequence0) {
        if(charSequence0 != null) {
            String[] arr_s = TextUtils.split(String.valueOf(charSequence0), "\n");
            return arr_s.length > 1 ? arr_s[0] : charSequence0;
        }
        return null;
    }

    private String getHeaderContentDescription() {
        return this.getDateSelector().getSelectionContentDescription(this.requireContext());
    }

    public String getHeaderText() {
        return this.getDateSelector().getSelectionDisplayString(this.getContext());
    }

    public int getInputMode() {
        return this.inputMode;
    }

    private static int getPaddedPickerWidth(Context context0) {
        Resources resources0 = context0.getResources();
        int v = resources0.getDimensionPixelOffset(dimen.mtrl_calendar_content_padding);
        Month month0 = Month.current();
        int v1 = resources0.getDimensionPixelSize(dimen.mtrl_calendar_day_width);
        int v2 = resources0.getDimensionPixelOffset(dimen.mtrl_calendar_month_horizontal_padding);
        return v * 2 + v1 * month0.daysInWeek + (month0.daysInWeek - 1) * v2;
    }

    public final Object getSelection() {
        return this.getDateSelector().getSelection();
    }

    private int getThemeResId(Context context0) {
        int v = this.overrideThemeResId;
        return v == 0 ? this.getDateSelector().getDefaultThemeResId(context0) : v;
    }

    private void initHeaderToggle(Context context0) {
        this.headerToggleButton.setTag(MaterialDatePicker.TOGGLE_BUTTON_TAG);
        this.headerToggleButton.setImageDrawable(MaterialDatePicker.createHeaderToggleDrawable(context0));
        this.headerToggleButton.setChecked(this.inputMode != 0);
        ViewCompat.setAccessibilityDelegate(this.headerToggleButton, null);
        this.updateToggleContentDescription(this.headerToggleButton);
        this.headerToggleButton.setOnClickListener((View view0) -> {
            this.confirmButton.setEnabled(this.getDateSelector().isSelectionComplete());
            this.headerToggleButton.toggle();
            this.inputMode = this.inputMode == 1 ? 0 : 1;
            this.updateToggleContentDescription(this.headerToggleButton);
            this.startPickerFragment();
        });
    }

    static boolean isFullscreen(Context context0) {
        return MaterialDatePicker.readMaterialCalendarStyleBoolean(context0, 0x101020D);
    }

    private boolean isLandscape() {
        return this.getResources().getConfiguration().orientation == 2;
    }

    static boolean isNestedScrollable(Context context0) {
        return MaterialDatePicker.readMaterialCalendarStyleBoolean(context0, attr.nestedScrollable);
    }

    // 检测为 Lambda 实现
    void lambda$initHeaderToggle$0$com-google-android-material-datepicker-MaterialDatePicker(View view0) [...]

    static MaterialDatePicker newInstance(Builder materialDatePicker$Builder0) {
        MaterialDatePicker materialDatePicker0 = new MaterialDatePicker();
        Bundle bundle0 = new Bundle();
        bundle0.putInt("OVERRIDE_THEME_RES_ID", materialDatePicker$Builder0.overrideThemeResId);
        bundle0.putParcelable("DATE_SELECTOR_KEY", materialDatePicker$Builder0.dateSelector);
        bundle0.putParcelable("CALENDAR_CONSTRAINTS_KEY", materialDatePicker$Builder0.calendarConstraints);
        bundle0.putParcelable("DAY_VIEW_DECORATOR_KEY", materialDatePicker$Builder0.dayViewDecorator);
        bundle0.putInt("TITLE_TEXT_RES_ID_KEY", materialDatePicker$Builder0.titleTextResId);
        bundle0.putCharSequence("TITLE_TEXT_KEY", materialDatePicker$Builder0.titleText);
        bundle0.putInt("INPUT_MODE_KEY", materialDatePicker$Builder0.inputMode);
        bundle0.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", materialDatePicker$Builder0.positiveButtonTextResId);
        bundle0.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", materialDatePicker$Builder0.positiveButtonText);
        bundle0.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", materialDatePicker$Builder0.positiveButtonContentDescriptionResId);
        bundle0.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", materialDatePicker$Builder0.positiveButtonContentDescription);
        bundle0.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", materialDatePicker$Builder0.negativeButtonTextResId);
        bundle0.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", materialDatePicker$Builder0.negativeButtonText);
        bundle0.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", materialDatePicker$Builder0.negativeButtonContentDescriptionResId);
        bundle0.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", materialDatePicker$Builder0.negativeButtonContentDescription);
        materialDatePicker0.setArguments(bundle0);
        return materialDatePicker0;
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final void onCancel(DialogInterface dialogInterface0) {
        for(Object object0: this.onCancelListeners) {
            ((DialogInterface.OnCancelListener)object0).onCancel(dialogInterface0);
        }
        super.onCancel(dialogInterface0);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            bundle0 = this.getArguments();
        }
        this.overrideThemeResId = bundle0.getInt("OVERRIDE_THEME_RES_ID");
        this.dateSelector = (DateSelector)bundle0.getParcelable("DATE_SELECTOR_KEY");
        this.calendarConstraints = (CalendarConstraints)bundle0.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.dayViewDecorator = (DayViewDecorator)bundle0.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.titleTextResId = bundle0.getInt("TITLE_TEXT_RES_ID_KEY");
        this.titleText = bundle0.getCharSequence("TITLE_TEXT_KEY");
        this.inputMode = bundle0.getInt("INPUT_MODE_KEY");
        this.positiveButtonTextResId = bundle0.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.positiveButtonText = bundle0.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.positiveButtonContentDescriptionResId = bundle0.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.positiveButtonContentDescription = bundle0.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        this.negativeButtonTextResId = bundle0.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.negativeButtonText = bundle0.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        this.negativeButtonContentDescriptionResId = bundle0.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.negativeButtonContentDescription = bundle0.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        CharSequence charSequence0 = this.titleText == null ? this.requireContext().getResources().getText(this.titleTextResId) : this.titleText;
        this.fullTitleText = charSequence0;
        this.singleLineTitleText = MaterialDatePicker.getFirstLineBySeparator(charSequence0);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle0) {
        Dialog dialog0 = new Dialog(this.requireContext(), this.getThemeResId(this.requireContext()));
        Context context0 = dialog0.getContext();
        this.fullscreen = MaterialDatePicker.isFullscreen(context0);
        this.background = new MaterialShapeDrawable(context0, null, attr.materialCalendarStyle, style.Widget_MaterialComponents_MaterialCalendar);
        TypedArray typedArray0 = context0.obtainStyledAttributes(null, styleable.MaterialCalendar, attr.materialCalendarStyle, style.Widget_MaterialComponents_MaterialCalendar);
        int v = typedArray0.getColor(styleable.MaterialCalendar_backgroundTint, 0);
        typedArray0.recycle();
        this.background.initializeElevationOverlay(context0);
        this.background.setFillColor(ColorStateList.valueOf(v));
        this.background.setElevation(ViewCompat.getElevation(dialog0.getWindow().getDecorView()));
        return dialog0;
    }

    @Override  // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        View view0 = layoutInflater0.inflate((this.fullscreen ? layout.mtrl_picker_fullscreen : layout.mtrl_picker_dialog), viewGroup0);
        Context context0 = view0.getContext();
        if(this.fullscreen) {
            view0.findViewById(id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(MaterialDatePicker.getPaddedPickerWidth(context0), -2));
        }
        else {
            view0.findViewById(id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(MaterialDatePicker.getPaddedPickerWidth(context0), -1));
        }
        TextView textView0 = (TextView)view0.findViewById(id.mtrl_picker_header_selection_text);
        this.headerSelectionText = textView0;
        ViewCompat.setAccessibilityLiveRegion(textView0, 1);
        this.headerToggleButton = (CheckableImageButton)view0.findViewById(id.mtrl_picker_header_toggle);
        this.headerTitleTextView = (TextView)view0.findViewById(id.mtrl_picker_title_text);
        this.initHeaderToggle(context0);
        this.confirmButton = (Button)view0.findViewById(id.confirm_button);
        if(this.getDateSelector().isSelectionComplete()) {
            this.confirmButton.setEnabled(true);
        }
        else {
            this.confirmButton.setEnabled(false);
        }
        this.confirmButton.setTag(MaterialDatePicker.CONFIRM_BUTTON_TAG);
        CharSequence charSequence0 = this.positiveButtonText;
        if(charSequence0 == null) {
            int v = this.positiveButtonTextResId;
            if(v != 0) {
                this.confirmButton.setText(v);
            }
        }
        else {
            this.confirmButton.setText(charSequence0);
        }
        CharSequence charSequence1 = this.positiveButtonContentDescription;
        if(charSequence1 != null) {
            this.confirmButton.setContentDescription(charSequence1);
        }
        else if(this.positiveButtonContentDescriptionResId != 0) {
            this.confirmButton.setContentDescription(this.getContext().getResources().getText(this.positiveButtonContentDescriptionResId));
        }
        this.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                for(Object object0: MaterialDatePicker.this.onPositiveButtonClickListeners) {
                    ((MaterialPickerOnPositiveButtonClickListener)object0).onPositiveButtonClick(MaterialDatePicker.this.getSelection());
                }
                MaterialDatePicker.this.dismiss();
            }
        });
        Button button0 = (Button)view0.findViewById(id.cancel_button);
        button0.setTag(MaterialDatePicker.CANCEL_BUTTON_TAG);
        CharSequence charSequence2 = this.negativeButtonText;
        if(charSequence2 == null) {
            int v1 = this.negativeButtonTextResId;
            if(v1 != 0) {
                button0.setText(v1);
            }
        }
        else {
            button0.setText(charSequence2);
        }
        CharSequence charSequence3 = this.negativeButtonContentDescription;
        if(charSequence3 != null) {
            button0.setContentDescription(charSequence3);
        }
        else if(this.negativeButtonContentDescriptionResId != 0) {
            button0.setContentDescription(this.getContext().getResources().getText(this.negativeButtonContentDescriptionResId));
        }
        button0.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                for(Object object0: MaterialDatePicker.this.onNegativeButtonClickListeners) {
                    ((View.OnClickListener)object0).onClick(view0);
                }
                MaterialDatePicker.this.dismiss();
            }
        });
        return view0;
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final void onDismiss(DialogInterface dialogInterface0) {
        for(Object object0: this.onDismissListeners) {
            ((DialogInterface.OnDismissListener)object0).onDismiss(dialogInterface0);
        }
        ViewGroup viewGroup0 = (ViewGroup)this.getView();
        if(viewGroup0 != null) {
            viewGroup0.removeAllViews();
        }
        super.onDismiss(dialogInterface0);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public final void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putInt("OVERRIDE_THEME_RES_ID", this.overrideThemeResId);
        bundle0.putParcelable("DATE_SELECTOR_KEY", this.dateSelector);
        com.google.android.material.datepicker.CalendarConstraints.Builder calendarConstraints$Builder0 = new com.google.android.material.datepicker.CalendarConstraints.Builder(this.calendarConstraints);
        Month month0 = this.calendar == null ? null : this.calendar.getCurrentMonth();
        if(month0 != null) {
            calendarConstraints$Builder0.setOpenAt(month0.timeInMillis);
        }
        bundle0.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints$Builder0.build());
        bundle0.putParcelable("DAY_VIEW_DECORATOR_KEY", this.dayViewDecorator);
        bundle0.putInt("TITLE_TEXT_RES_ID_KEY", this.titleTextResId);
        bundle0.putCharSequence("TITLE_TEXT_KEY", this.titleText);
        bundle0.putInt("INPUT_MODE_KEY", this.inputMode);
        bundle0.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.positiveButtonTextResId);
        bundle0.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.positiveButtonText);
        bundle0.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.positiveButtonContentDescriptionResId);
        bundle0.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.positiveButtonContentDescription);
        bundle0.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.negativeButtonTextResId);
        bundle0.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.negativeButtonText);
        bundle0.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.negativeButtonContentDescriptionResId);
        bundle0.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.negativeButtonContentDescription);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void onStart() {
        super.onStart();
        Window window0 = this.requireDialog().getWindow();
        if(this.fullscreen) {
            window0.setLayout(-1, -1);
            window0.setBackgroundDrawable(this.background);
            this.enableEdgeToEdgeIfNeeded(window0);
        }
        else {
            window0.setLayout(-2, -2);
            int v = this.getResources().getDimensionPixelOffset(dimen.mtrl_calendar_dialog_background_inset);
            Rect rect0 = new Rect(v, v, v, v);
            window0.setBackgroundDrawable(new InsetDrawable(this.background, v, v, v, v));
            window0.getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(this.requireDialog(), rect0));
        }
        this.startPickerFragment();
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void onStop() {
        this.pickerFragment.clearOnSelectionChangedListeners();
        super.onStop();
    }

    static boolean readMaterialCalendarStyleBoolean(Context context0, int v) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context0, attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{v});
        boolean z = typedArray0.getBoolean(0, false);
        typedArray0.recycle();
        return z;
    }

    public boolean removeOnCancelListener(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        return this.onCancelListeners.remove(dialogInterface$OnCancelListener0);
    }

    public boolean removeOnDismissListener(DialogInterface.OnDismissListener dialogInterface$OnDismissListener0) {
        return this.onDismissListeners.remove(dialogInterface$OnDismissListener0);
    }

    public boolean removeOnNegativeButtonClickListener(View.OnClickListener view$OnClickListener0) {
        return this.onNegativeButtonClickListeners.remove(view$OnClickListener0);
    }

    public boolean removeOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener materialPickerOnPositiveButtonClickListener0) {
        return this.onPositiveButtonClickListeners.remove(materialPickerOnPositiveButtonClickListener0);
    }

    private void startPickerFragment() {
        int v = this.getThemeResId(this.requireContext());
        MaterialCalendar materialCalendar0 = MaterialCalendar.newInstance(this.getDateSelector(), v, this.calendarConstraints, this.dayViewDecorator);
        this.calendar = materialCalendar0;
        if(this.inputMode == 1) {
            materialCalendar0 = MaterialTextInputPicker.newInstance(this.getDateSelector(), v, this.calendarConstraints);
        }
        this.pickerFragment = materialCalendar0;
        this.updateTitle();
        this.updateHeader(this.getHeaderText());
        FragmentTransaction fragmentTransaction0 = this.getChildFragmentManager().beginTransaction();
        fragmentTransaction0.replace(id.mtrl_calendar_frame, this.pickerFragment);
        fragmentTransaction0.commitNow();
        this.pickerFragment.addOnSelectionChangedListener(new OnSelectionChangedListener() {
            @Override  // com.google.android.material.datepicker.OnSelectionChangedListener
            public void onIncompleteSelectionChanged() {
                MaterialDatePicker.this.confirmButton.setEnabled(false);
            }

            @Override  // com.google.android.material.datepicker.OnSelectionChangedListener
            public void onSelectionChanged(Object object0) {
                String s = MaterialDatePicker.this.getHeaderText();
                MaterialDatePicker.this.updateHeader(s);
                boolean z = MaterialDatePicker.this.getDateSelector().isSelectionComplete();
                MaterialDatePicker.this.confirmButton.setEnabled(z);
            }
        });
    }

    public static long thisMonthInUtcMilliseconds() {
        return Month.current().timeInMillis;
    }

    public static long todayInUtcMilliseconds() {
        return UtcDates.getTodayCalendar().getTimeInMillis();
    }

    void updateHeader(String s) {
        this.headerSelectionText.setContentDescription(this.getHeaderContentDescription());
        this.headerSelectionText.setText(s);
    }

    private void updateTitle() {
        this.headerTitleTextView.setText((this.inputMode != 1 || !this.isLandscape() ? this.fullTitleText : this.singleLineTitleText));
    }

    private void updateToggleContentDescription(CheckableImageButton checkableImageButton0) {
        String s = this.inputMode == 1 ? checkableImageButton0.getContext().getString(string.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton0.getContext().getString(string.mtrl_picker_toggle_to_text_input_mode);
        this.headerToggleButton.setContentDescription(s);
    }
}

