package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.material.R.attr;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class SingleDateSelector implements DateSelector {
    public static final Parcelable.Creator CREATOR;
    private CharSequence error;
    private Long selectedItem;
    private SimpleDateFormat textInputFormat;

    static {
        SingleDateSelector.CREATOR = new Parcelable.Creator() {
            public SingleDateSelector createFromParcel(Parcel parcel0) {
                SingleDateSelector singleDateSelector0 = new SingleDateSelector();
                singleDateSelector0.selectedItem = (Long)parcel0.readValue(Long.class.getClassLoader());
                return singleDateSelector0;
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public SingleDateSelector[] newArray(int v) {
                return new SingleDateSelector[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    private void clearSelection() {
        this.selectedItem = null;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public int getDefaultThemeResId(Context context0) {
        return MaterialAttributes.resolveOrThrow(context0, attr.materialCalendarTheme, MaterialDatePicker.class.getCanonicalName());
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public int getDefaultTitleResId() {
        return string.mtrl_picker_date_header_title;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.datepicker.DateSelector
    public String getError() {
        return TextUtils.isEmpty(this.error) ? null : this.error.toString();
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public Collection getSelectedDays() {
        Collection collection0 = new ArrayList();
        Long long0 = this.selectedItem;
        if(long0 != null) {
            ((ArrayList)collection0).add(long0);
        }
        return collection0;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public Collection getSelectedRanges() {
        return new ArrayList();
    }

    public Long getSelection() {
        return this.selectedItem;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public Object getSelection() {
        return this.getSelection();
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public String getSelectionContentDescription(Context context0) {
        String s;
        Resources resources0 = context0.getResources();
        Long long0 = this.selectedItem;
        if(long0 == null) {
            s = resources0.getString(string.mtrl_picker_announce_current_selection_none);
            return resources0.getString(string.mtrl_picker_announce_current_selection, new Object[]{s});
        }
        s = DateStrings.getYearMonthDay(((long)long0));
        return resources0.getString(string.mtrl_picker_announce_current_selection, new Object[]{s});
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public String getSelectionDisplayString(Context context0) {
        Resources resources0 = context0.getResources();
        Long long0 = this.selectedItem;
        if(long0 == null) {
            return resources0.getString(string.mtrl_picker_date_header_unselected);
        }
        Object[] arr_object = {DateStrings.getYearMonthDay(((long)long0))};
        return resources0.getString(string.mtrl_picker_date_header_selected, arr_object);
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public boolean isSelectionComplete() {
        return this.selectedItem != null;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public View onCreateTextInputView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0, CalendarConstraints calendarConstraints0, OnSelectionChangedListener onSelectionChangedListener0) {
        View view0 = layoutInflater0.inflate(layout.mtrl_picker_text_input_date, viewGroup0, false);
        View view1 = view0.findViewById(id.mtrl_picker_text_input_date);
        EditText editText0 = ((TextInputLayout)view1).getEditText();
        if(ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters()) {
            editText0.setInputType(17);
        }
        SimpleDateFormat simpleDateFormat0 = this.textInputFormat;
        boolean z = simpleDateFormat0 != null;
        if(!z) {
            simpleDateFormat0 = UtcDates.getDefaultTextInputFormat();
        }
        String s = z ? simpleDateFormat0.toPattern() : UtcDates.getDefaultTextInputHint(view0.getResources(), simpleDateFormat0);
        ((TextInputLayout)view1).setPlaceholderText(s);
        Long long0 = this.selectedItem;
        if(long0 != null) {
            editText0.setText(simpleDateFormat0.format(long0));
        }
        editText0.addTextChangedListener(new DateFormatTextWatcher(s, simpleDateFormat0, ((TextInputLayout)view1), calendarConstraints0) {
            @Override  // com.google.android.material.datepicker.DateFormatTextWatcher
            void onInvalidDate() {
                CharSequence charSequence0 = ((TextInputLayout)view1).getError();
                SingleDateSelector.this.error = charSequence0;
                onSelectionChangedListener0.onIncompleteSelectionChanged();
            }

            @Override  // com.google.android.material.datepicker.DateFormatTextWatcher
            void onValidDate(Long long0) {
                if(long0 == null) {
                    SingleDateSelector.this.clearSelection();
                }
                else {
                    SingleDateSelector.this.select(((long)long0));
                }
                SingleDateSelector.this.error = null;
                onSelectionChangedListener0.onSelectionChanged(SingleDateSelector.this.getSelection());
            }
        });
        DateSelector.-CC.showKeyboardWithAutoHideBehavior(new EditText[]{editText0});
        return view0;
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public void select(long v) {
        this.selectedItem = v;
    }

    public void setSelection(Long long0) {
        this.selectedItem = long0 == null ? null : UtcDates.canonicalYearMonthDay(((long)long0));
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public void setSelection(Object object0) {
        this.setSelection(((Long)object0));
    }

    @Override  // com.google.android.material.datepicker.DateSelector
    public void setTextInputFormat(SimpleDateFormat simpleDateFormat0) {
        if(simpleDateFormat0 != null) {
            simpleDateFormat0 = (SimpleDateFormat)UtcDates.getNormalizedFormat(simpleDateFormat0);
        }
        this.textInputFormat = simpleDateFormat0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeValue(this.selectedItem);
    }
}

