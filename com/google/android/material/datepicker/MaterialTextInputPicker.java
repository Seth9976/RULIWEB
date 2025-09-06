package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class MaterialTextInputPicker extends PickerFragment {
    private static final String CALENDAR_CONSTRAINTS_KEY = "CALENDAR_CONSTRAINTS_KEY";
    private static final String DATE_SELECTOR_KEY = "DATE_SELECTOR_KEY";
    private static final String THEME_RES_ID_KEY = "THEME_RES_ID_KEY";
    private CalendarConstraints calendarConstraints;
    private DateSelector dateSelector;
    private int themeResId;

    @Override  // com.google.android.material.datepicker.PickerFragment
    public DateSelector getDateSelector() {
        DateSelector dateSelector0 = this.dateSelector;
        if(dateSelector0 == null) {
            throw new IllegalStateException("dateSelector should not be null. Use MaterialTextInputPicker#newInstance() to create this fragment with a DateSelector, and call this method after the fragment has been created.");
        }
        return dateSelector0;
    }

    static MaterialTextInputPicker newInstance(DateSelector dateSelector0, int v, CalendarConstraints calendarConstraints0) {
        MaterialTextInputPicker materialTextInputPicker0 = new MaterialTextInputPicker();
        Bundle bundle0 = new Bundle();
        bundle0.putInt("THEME_RES_ID_KEY", v);
        bundle0.putParcelable("DATE_SELECTOR_KEY", dateSelector0);
        bundle0.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints0);
        materialTextInputPicker0.setArguments(bundle0);
        return materialTextInputPicker0;
    }

    @Override  // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            bundle0 = this.getArguments();
        }
        this.themeResId = bundle0.getInt("THEME_RES_ID_KEY");
        this.dateSelector = (DateSelector)bundle0.getParcelable("DATE_SELECTOR_KEY");
        this.calendarConstraints = (CalendarConstraints)bundle0.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override  // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        LayoutInflater layoutInflater1 = layoutInflater0.cloneInContext(new ContextThemeWrapper(this.getContext(), this.themeResId));
        return this.dateSelector.onCreateTextInputView(layoutInflater1, viewGroup0, bundle0, this.calendarConstraints, new OnSelectionChangedListener() {
            @Override  // com.google.android.material.datepicker.OnSelectionChangedListener
            public void onIncompleteSelectionChanged() {
                for(Object object0: MaterialTextInputPicker.this.onSelectionChangedListeners) {
                    ((OnSelectionChangedListener)object0).onIncompleteSelectionChanged();
                }
            }

            @Override  // com.google.android.material.datepicker.OnSelectionChangedListener
            public void onSelectionChanged(Object object0) {
                for(Object object1: MaterialTextInputPicker.this.onSelectionChangedListeners) {
                    ((OnSelectionChangedListener)object1).onSelectionChanged(object0);
                }
            }
        });
    }

    @Override  // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putInt("THEME_RES_ID_KEY", this.themeResId);
        bundle0.putParcelable("DATE_SELECTOR_KEY", this.dateSelector);
        bundle0.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.calendarConstraints);
    }
}

