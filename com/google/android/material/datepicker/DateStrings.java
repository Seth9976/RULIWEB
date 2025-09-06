package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.format.DateUtils;
import androidx.core.util.Pair;
import com.google.android.material.R.string;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class DateStrings {
    static Pair getDateRangeString(Long long0, Long long1) {
        return DateStrings.getDateRangeString(long0, long1, null);
    }

    static Pair getDateRangeString(Long long0, Long long1, SimpleDateFormat simpleDateFormat0) {
        if(long0 == null && long1 == null) {
            return Pair.create(null, null);
        }
        if(long0 == null) {
            return Pair.create(null, DateStrings.getDateString(((long)long1), simpleDateFormat0));
        }
        if(long1 == null) {
            return Pair.create(DateStrings.getDateString(((long)long0), simpleDateFormat0), null);
        }
        Calendar calendar0 = UtcDates.getTodayCalendar();
        Calendar calendar1 = UtcDates.getUtcCalendar();
        calendar1.setTimeInMillis(((long)long0));
        Calendar calendar2 = UtcDates.getUtcCalendar();
        calendar2.setTimeInMillis(((long)long1));
        if(simpleDateFormat0 != null) {
            Date date0 = new Date(((long)long0));
            Date date1 = new Date(((long)long1));
            return Pair.create(simpleDateFormat0.format(date0), simpleDateFormat0.format(date1));
        }
        if(calendar1.get(1) == calendar2.get(1)) {
            return calendar1.get(1) == calendar0.get(1) ? Pair.create(DateStrings.getMonthDay(((long)long0), Locale.getDefault()), DateStrings.getMonthDay(((long)long1), Locale.getDefault())) : Pair.create(DateStrings.getMonthDay(((long)long0), Locale.getDefault()), DateStrings.getYearMonthDay(((long)long1), Locale.getDefault()));
        }
        return Pair.create(DateStrings.getYearMonthDay(((long)long0), Locale.getDefault()), DateStrings.getYearMonthDay(((long)long1), Locale.getDefault()));
    }

    static String getDateString(long v) {
        return DateStrings.getDateString(v, null);
    }

    static String getDateString(long v, SimpleDateFormat simpleDateFormat0) {
        if(simpleDateFormat0 != null) {
            return simpleDateFormat0.format(new Date(v));
        }
        return DateStrings.isDateWithinCurrentYear(v) ? DateStrings.getMonthDay(v) : DateStrings.getYearMonthDay(v);
    }

    static String getDayContentDescription(Context context0, long v, boolean z, boolean z1, boolean z2) {
        String s = DateStrings.getOptionalYearMonthDayOfWeekDay(v);
        if(z) {
            s = String.format(context0.getString(string.mtrl_picker_today_description), s);
        }
        if(z1) {
            return String.format(context0.getString(string.mtrl_picker_start_date_description), s);
        }
        return z2 ? String.format(context0.getString(string.mtrl_picker_end_date_description), s) : s;
    }

    static String getMonthDay(long v) {
        return DateStrings.getMonthDay(v, Locale.getDefault());
    }

    static String getMonthDay(long v, Locale locale0) {
        return Build.VERSION.SDK_INT < 24 ? UtcDates.getMediumNoYear(locale0).format(new Date(v)) : UtcDates.getAbbrMonthDayFormat(locale0).format(new Date(v));
    }

    static String getMonthDayOfWeekDay(long v) {
        return DateStrings.getMonthDayOfWeekDay(v, Locale.getDefault());
    }

    static String getMonthDayOfWeekDay(long v, Locale locale0) {
        return Build.VERSION.SDK_INT < 24 ? UtcDates.getFullFormat(locale0).format(new Date(v)) : UtcDates.getMonthWeekdayDayFormat(locale0).format(new Date(v));
    }

    // 去混淆评级： 低(20)
    static String getOptionalYearMonthDayOfWeekDay(long v) {
        return DateStrings.isDateWithinCurrentYear(v) ? DateStrings.getMonthDayOfWeekDay(v) : DateStrings.getYearMonthDayOfWeekDay(v);
    }

    static String getYearContentDescription(Context context0, int v) {
        return UtcDates.getTodayCalendar().get(1) == v ? String.format(context0.getString(string.mtrl_picker_navigate_to_current_year_description), v) : String.format(context0.getString(string.mtrl_picker_navigate_to_year_description), v);
    }

    static String getYearMonth(long v) {
        return Build.VERSION.SDK_INT < 24 ? DateUtils.formatDateTime(null, v, 0x2024) : UtcDates.getYearMonthFormat(Locale.getDefault()).format(new Date(v));
    }

    static String getYearMonthDay(long v) {
        return DateStrings.getYearMonthDay(v, Locale.getDefault());
    }

    static String getYearMonthDay(long v, Locale locale0) {
        return Build.VERSION.SDK_INT < 24 ? UtcDates.getMediumFormat(locale0).format(new Date(v)) : UtcDates.getYearAbbrMonthDayFormat(locale0).format(new Date(v));
    }

    static String getYearMonthDayOfWeekDay(long v) {
        return DateStrings.getYearMonthDayOfWeekDay(v, Locale.getDefault());
    }

    static String getYearMonthDayOfWeekDay(long v, Locale locale0) {
        return Build.VERSION.SDK_INT < 24 ? UtcDates.getFullFormat(locale0).format(new Date(v)) : UtcDates.getYearMonthWeekdayDayFormat(locale0).format(new Date(v));
    }

    private static boolean isDateWithinCurrentYear(long v) {
        Calendar calendar0 = UtcDates.getTodayCalendar();
        Calendar calendar1 = UtcDates.getUtcCalendar();
        calendar1.setTimeInMillis(v);
        return calendar0.get(1) == calendar1.get(1);
    }
}

