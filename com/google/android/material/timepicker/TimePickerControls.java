package com.google.android.material.timepicker;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

interface TimePickerControls {
    @Retention(RetentionPolicy.SOURCE)
    public @interface ActiveSelection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClockPeriod {
    }

    void setActiveSelection(int arg1);

    void setHandRotation(float arg1);

    void setValues(String[] arg1, int arg2);

    void updateTime(int arg1, int arg2, int arg3);
}

