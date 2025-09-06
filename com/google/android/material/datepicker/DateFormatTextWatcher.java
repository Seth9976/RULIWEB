package com.google.android.material.datepicker;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.R.string;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

abstract class DateFormatTextWatcher extends TextWatcherAdapter {
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String formatHint;
    private int lastLength;
    private final String outOfRange;
    private final Runnable setErrorCallback;
    private Runnable setRangeErrorCallback;
    private final TextInputLayout textInputLayout;

    DateFormatTextWatcher(String s, DateFormat dateFormat0, TextInputLayout textInputLayout0, CalendarConstraints calendarConstraints0) {
        this.lastLength = 0;
        this.formatHint = s;
        this.dateFormat = dateFormat0;
        this.textInputLayout = textInputLayout0;
        this.constraints = calendarConstraints0;
        this.outOfRange = textInputLayout0.getContext().getString(string.mtrl_picker_out_of_range);
        this.setErrorCallback = () -> {
            Context context0 = this.textInputLayout.getContext();
            String s1 = context0.getString(string.mtrl_picker_invalid_format);
            String s2 = context0.getString(string.mtrl_picker_invalid_format_use);
            String s3 = context0.getString(string.mtrl_picker_invalid_format_example);
            Date date0 = new Date(UtcDates.getTodayCalendar().getTimeInMillis());
            Object[] arr_object = {this.sanitizeDateString(this.dateFormat.format(date0))};
            this.textInputLayout.setError(s1 + "\n" + String.format(s2, this.sanitizeDateString(s)) + "\n" + String.format(s3, arr_object));
            this.onInvalidDate();
        };
    }

    @Override  // com.google.android.material.internal.TextWatcherAdapter
    public void afterTextChanged(Editable editable0) {
        if(!Locale.getDefault().getLanguage().equals("ko") && editable0.length() != 0 && editable0.length() < this.formatHint.length() && editable0.length() >= this.lastLength) {
            int v = editable0.length();
            int v1 = this.formatHint.charAt(v);
            if(!Character.isLetterOrDigit(((char)v1))) {
                editable0.append(((char)v1));
            }
        }
    }

    @Override  // com.google.android.material.internal.TextWatcherAdapter
    public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
        this.lastLength = charSequence0.length();
    }

    private Runnable createRangeErrorCallback(long v) {
        return () -> {
            Object[] arr_object = {this.sanitizeDateString(DateStrings.getDateString(v))};
            this.textInputLayout.setError(String.format(this.outOfRange, arr_object));
            this.onInvalidDate();
        };
    }

    // 检测为 Lambda 实现
    void lambda$createRangeErrorCallback$1$com-google-android-material-datepicker-DateFormatTextWatcher(long v) [...]

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-datepicker-DateFormatTextWatcher(String s) [...]

    void onInvalidDate() {
    }

    @Override  // com.google.android.material.internal.TextWatcherAdapter
    public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
        this.textInputLayout.removeCallbacks(this.setErrorCallback);
        this.textInputLayout.removeCallbacks(this.setRangeErrorCallback);
        this.textInputLayout.setError(null);
        this.onValidDate(null);
        if(!TextUtils.isEmpty(charSequence0) && charSequence0.length() >= this.formatHint.length()) {
            try {
                Date date0 = this.dateFormat.parse(charSequence0.toString());
                this.textInputLayout.setError(null);
                long v3 = date0.getTime();
                if(this.constraints.getDateValidator().isValid(v3) && this.constraints.isWithinBounds(v3)) {
                    this.onValidDate(date0.getTime());
                    return;
                }
                Runnable runnable0 = this.createRangeErrorCallback(v3);
                this.setRangeErrorCallback = runnable0;
                this.runValidation(this.textInputLayout, runnable0);
            }
            catch(ParseException unused_ex) {
                this.runValidation(this.textInputLayout, this.setErrorCallback);
            }
        }
    }

    abstract void onValidDate(Long arg1);

    public void runValidation(View view0, Runnable runnable0) {
        view0.post(runnable0);
    }

    private String sanitizeDateString(String s) {
        return s.replace(' ', ' ');
    }
}

