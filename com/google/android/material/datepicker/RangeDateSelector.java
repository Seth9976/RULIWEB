package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class RangeDateSelector implements DateSelector {
    public static final Parcelable.Creator CREATOR;
    private CharSequence error;
    private final String invalidRangeEndError;
    private String invalidRangeStartError;
    private Long proposedTextEnd;
    private Long proposedTextStart;
    private Long selectedEndItem;
    private Long selectedStartItem;
    private SimpleDateFormat textInputFormat;

    static {
        RangeDateSelector.CREATOR = new Parcelable.Creator() {
            public RangeDateSelector createFromParcel(Parcel parcel0) {
                RangeDateSelector rangeDateSelector0 = new RangeDateSelector();
                rangeDateSelector0.selectedStartItem = (Long)parcel0.readValue(Long.class.getClassLoader());
                rangeDateSelector0.selectedEndItem = (Long)parcel0.readValue(Long.class.getClassLoader());
                return rangeDateSelector0;
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public RangeDateSelector[] newArray(int v) {
                return new RangeDateSelector[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    public RangeDateSelector() {
        this.invalidRangeEndError = " ";
        this.selectedStartItem = null;
        this.selectedEndItem = null;
        this.proposedTextStart = null;
        this.proposedTextEnd = null;
    }

    private void clearInvalidRange(TextInputLayout textInputLayout0, TextInputLayout textInputLayout1) {
        if(textInputLayout0.getError() != null && this.invalidRangeStartError.contentEquals(textInputLayout0.getError())) {
            textInputLayout0.setError(null);
        }
        if(textInputLayout1.getError() != null && " ".contentEquals(textInputLayout1.getError())) {
            textInputLayout1.setError(null);
        }
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public int getDefaultThemeResId(Context context0) {
        Resources resources0 = context0.getResources();
        DisplayMetrics displayMetrics0 = resources0.getDisplayMetrics();
        int v = resources0.getDimensionPixelSize(dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis);
        return Math.min(displayMetrics0.widthPixels, displayMetrics0.heightPixels) <= v ? MaterialAttributes.resolveOrThrow(context0, attr.materialCalendarFullscreenTheme, MaterialDatePicker.class.getCanonicalName()) : MaterialAttributes.resolveOrThrow(context0, attr.materialCalendarTheme, MaterialDatePicker.class.getCanonicalName());
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public int getDefaultTitleResId() {
        return string.mtrl_picker_range_header_title;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.datepicker.DateSelector
    public String getError() {
        return TextUtils.isEmpty(this.error) ? null : this.error.toString();
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public Collection getSelectedDays() {
        Collection collection0 = new ArrayList();
        Long long0 = this.selectedStartItem;
        if(long0 != null) {
            ((ArrayList)collection0).add(long0);
        }
        Long long1 = this.selectedEndItem;
        if(long1 != null) {
            ((ArrayList)collection0).add(long1);
        }
        return collection0;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public Collection getSelectedRanges() {
        Collection collection0 = new ArrayList();
        ((ArrayList)collection0).add(new Pair(this.selectedStartItem, this.selectedEndItem));
        return collection0;
    }

    public Pair getSelection() {
        return new Pair(this.selectedStartItem, this.selectedEndItem);
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public Object getSelection() {
        return this.getSelection();
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public String getSelectionContentDescription(Context context0) {
        Resources resources0 = context0.getResources();
        Pair pair0 = DateStrings.getDateRangeString(this.selectedStartItem, this.selectedEndItem);
        String s = pair0.first == null ? resources0.getString(string.mtrl_picker_announce_current_selection_none) : ((String)pair0.first);
        if(pair0.second == null) {
            String s1 = resources0.getString(string.mtrl_picker_announce_current_selection_none);
            return resources0.getString(string.mtrl_picker_announce_current_range_selection, new Object[]{s, s1});
        }
        return resources0.getString(string.mtrl_picker_announce_current_range_selection, new Object[]{s, ((String)pair0.second)});
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public String getSelectionDisplayString(Context context0) {
        Resources resources0 = context0.getResources();
        Long long0 = this.selectedStartItem;
        if(long0 == null && this.selectedEndItem == null) {
            return resources0.getString(string.mtrl_picker_range_header_unselected);
        }
        Long long1 = this.selectedEndItem;
        if(long1 == null) {
            Object[] arr_object = {DateStrings.getDateString(((long)this.selectedStartItem))};
            return resources0.getString(string.mtrl_picker_range_header_only_start_selected, arr_object);
        }
        if(long0 == null) {
            Object[] arr_object1 = {DateStrings.getDateString(((long)this.selectedEndItem))};
            return resources0.getString(string.mtrl_picker_range_header_only_end_selected, arr_object1);
        }
        Pair pair0 = DateStrings.getDateRangeString(long0, long1);
        return resources0.getString(string.mtrl_picker_range_header_selected, new Object[]{pair0.first, pair0.second});
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public boolean isSelectionComplete() {
        return this.selectedStartItem != null && this.selectedEndItem != null && this.isValidRange(((long)this.selectedStartItem), ((long)this.selectedEndItem));
    }

    private boolean isValidRange(long v, long v1) {
        return v <= v1;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public View onCreateTextInputView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0, CalendarConstraints calendarConstraints0, OnSelectionChangedListener onSelectionChangedListener0) {
        View view0 = layoutInflater0.inflate(layout.mtrl_picker_text_input_date_range, viewGroup0, false);
        View view1 = view0.findViewById(id.mtrl_picker_text_input_range_start);
        View view2 = view0.findViewById(id.mtrl_picker_text_input_range_end);
        EditText editText0 = ((TextInputLayout)view1).getEditText();
        EditText editText1 = ((TextInputLayout)view2).getEditText();
        if(ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters()) {
            editText0.setInputType(17);
            editText1.setInputType(17);
        }
        this.invalidRangeStartError = view0.getResources().getString(string.mtrl_picker_invalid_range);
        SimpleDateFormat simpleDateFormat0 = this.textInputFormat;
        boolean z = simpleDateFormat0 != null;
        if(!z) {
            simpleDateFormat0 = UtcDates.getDefaultTextInputFormat();
        }
        Long long0 = this.selectedStartItem;
        if(long0 != null) {
            editText0.setText(simpleDateFormat0.format(long0));
            this.proposedTextStart = this.selectedStartItem;
        }
        Long long1 = this.selectedEndItem;
        if(long1 != null) {
            editText1.setText(simpleDateFormat0.format(long1));
            this.proposedTextEnd = this.selectedEndItem;
        }
        String s = z ? simpleDateFormat0.toPattern() : UtcDates.getDefaultTextInputHint(view0.getResources(), simpleDateFormat0);
        ((TextInputLayout)view1).setPlaceholderText(s);
        ((TextInputLayout)view2).setPlaceholderText(s);
        editText0.addTextChangedListener(new DateFormatTextWatcher(s, simpleDateFormat0, ((TextInputLayout)view1), calendarConstraints0) {
            @Override  // com.google.android.material.datepicker.DateFormatTextWatcher
            void onInvalidDate() {
                RangeDateSelector.this.proposedTextStart = null;
                RangeDateSelector.this.updateIfValidTextProposal(((TextInputLayout)view1), ((TextInputLayout)view2), onSelectionChangedListener0);
            }

            @Override  // com.google.android.material.datepicker.DateFormatTextWatcher
            void onValidDate(Long long0) {
                RangeDateSelector.this.proposedTextStart = long0;
                RangeDateSelector.this.updateIfValidTextProposal(((TextInputLayout)view1), ((TextInputLayout)view2), onSelectionChangedListener0);
            }
        });
        editText1.addTextChangedListener(new DateFormatTextWatcher(s, simpleDateFormat0, ((TextInputLayout)view2), calendarConstraints0) {
            @Override  // com.google.android.material.datepicker.DateFormatTextWatcher
            void onInvalidDate() {
                RangeDateSelector.this.proposedTextEnd = null;
                RangeDateSelector.this.updateIfValidTextProposal(((TextInputLayout)view1), ((TextInputLayout)view2), onSelectionChangedListener0);
            }

            @Override  // com.google.android.material.datepicker.DateFormatTextWatcher
            void onValidDate(Long long0) {
                RangeDateSelector.this.proposedTextEnd = long0;
                RangeDateSelector.this.updateIfValidTextProposal(((TextInputLayout)view1), ((TextInputLayout)view2), onSelectionChangedListener0);
            }
        });
        DateSelector.-CC.showKeyboardWithAutoHideBehavior(new EditText[]{editText0, editText1});
        return view0;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public void select(long v) {
        Long long0 = this.selectedStartItem;
        if(long0 == null) {
            this.selectedStartItem = v;
            return;
        }
        if(this.selectedEndItem == null && this.isValidRange(((long)long0), v)) {
            this.selectedEndItem = v;
            return;
        }
        this.selectedEndItem = null;
        this.selectedStartItem = v;
    }

    private void setInvalidRange(TextInputLayout textInputLayout0, TextInputLayout textInputLayout1) {
        textInputLayout0.setError(this.invalidRangeStartError);
        textInputLayout1.setError(" ");
    }

    public void setSelection(Pair pair0) {
        if(pair0.first != null && pair0.second != null) {
            Preconditions.checkArgument(this.isValidRange(((long)(((Long)pair0.first))), ((long)(((Long)pair0.second)))));
        }
        Long long0 = null;
        this.selectedStartItem = pair0.first == null ? null : UtcDates.canonicalYearMonthDay(((long)(((Long)pair0.first))));
        if(pair0.second != null) {
            long0 = UtcDates.canonicalYearMonthDay(((long)(((Long)pair0.second))));
        }
        this.selectedEndItem = long0;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public void setSelection(Object object0) {
        this.setSelection(((Pair)object0));
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public void setTextInputFormat(SimpleDateFormat simpleDateFormat0) {
        if(simpleDateFormat0 != null) {
            simpleDateFormat0 = (SimpleDateFormat)UtcDates.getNormalizedFormat(simpleDateFormat0);
        }
        this.textInputFormat = simpleDateFormat0;
    }

    private void updateError(TextInputLayout textInputLayout0, TextInputLayout textInputLayout1) {
        if(!TextUtils.isEmpty(textInputLayout0.getError())) {
            this.error = textInputLayout0.getError();
            return;
        }
        if(!TextUtils.isEmpty(textInputLayout1.getError())) {
            this.error = textInputLayout1.getError();
            return;
        }
        this.error = null;
    }

    private void updateIfValidTextProposal(TextInputLayout textInputLayout0, TextInputLayout textInputLayout1, OnSelectionChangedListener onSelectionChangedListener0) {
        Long long0 = this.proposedTextStart;
        if(long0 == null || this.proposedTextEnd == null) {
            this.clearInvalidRange(textInputLayout0, textInputLayout1);
            onSelectionChangedListener0.onIncompleteSelectionChanged();
        }
        else if(this.isValidRange(((long)long0), ((long)this.proposedTextEnd))) {
            this.selectedStartItem = this.proposedTextStart;
            this.selectedEndItem = this.proposedTextEnd;
            onSelectionChangedListener0.onSelectionChanged(this.getSelection());
        }
        else {
            this.setInvalidRange(textInputLayout0, textInputLayout1);
            onSelectionChangedListener0.onIncompleteSelectionChanged();
        }
        this.updateError(textInputLayout0, textInputLayout1);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeValue(this.selectedStartItem);
        parcel0.writeValue(this.selectedEndItem);
    }
}

