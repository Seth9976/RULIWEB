package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.text.SimpleDateFormat;
import java.util.Collection;

public interface DateSelector extends Parcelable {
    int getDefaultThemeResId(Context arg1);

    int getDefaultTitleResId();

    String getError();

    Collection getSelectedDays();

    Collection getSelectedRanges();

    Object getSelection();

    String getSelectionContentDescription(Context arg1);

    String getSelectionDisplayString(Context arg1);

    boolean isSelectionComplete();

    View onCreateTextInputView(LayoutInflater arg1, ViewGroup arg2, Bundle arg3, CalendarConstraints arg4, OnSelectionChangedListener arg5);

    void select(long arg1);

    void setSelection(Object arg1);

    void setTextInputFormat(SimpleDateFormat arg1);
}

