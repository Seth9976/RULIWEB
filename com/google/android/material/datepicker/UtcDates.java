package com.google.android.material.datepicker;

import android.content.res.Resources;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import com.google.android.material.R.string;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

class UtcDates {
    static final String UTC = "UTC";
    static AtomicReference timeSourceRef;

    static {
        UtcDates.timeSourceRef = new AtomicReference();
    }

    static long canonicalYearMonthDay(long v) {
        Calendar calendar0 = UtcDates.getUtcCalendar();
        calendar0.setTimeInMillis(v);
        return UtcDates.getDayCopy(calendar0).getTimeInMillis();
    }

    private static int findCharactersInDateFormatPattern(String s, String s1, int v, int v1) {
        while(v1 >= 0 && v1 < s.length() && s1.indexOf(s.charAt(v1)) == -1) {
            if(s.charAt(v1) == 39) {
                while(true) {
                    v1 += v;
                    if(v1 < 0 || v1 >= s.length() || s.charAt(v1) == 39) {
                        break;
                    }
                }
            }
            v1 += v;
        }
        return v1;
    }

    static DateFormat getAbbrMonthDayFormat(Locale locale0) {
        return UtcDates.getAndroidFormat("MMMd", locale0);
    }

    private static DateFormat getAndroidFormat(String s, Locale locale0) {
        DateFormat dateFormat0 = DateFormat.getInstanceForSkeleton(s, locale0);
        dateFormat0.setTimeZone(UtcDates.getUtcAndroidTimeZone());
        dateFormat0.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        return dateFormat0;
    }

    static String getDatePatternAsInputFormat(String s) {
        return s.replaceAll("[^dMy/\\-.]", "").replaceAll("d{1,2}", "dd").replaceAll("M{1,2}", "MM").replaceAll("y{1,4}", "yyyy").replaceAll("\\.$", "").replaceAll("My", "M/y");
    }

    static Calendar getDayCopy(Calendar calendar0) {
        Calendar calendar1 = UtcDates.getUtcCalendarOf(calendar0);
        Calendar calendar2 = UtcDates.getUtcCalendar();
        calendar2.set(calendar1.get(1), calendar1.get(2), calendar1.get(5));
        return calendar2;
    }

    static SimpleDateFormat getDefaultTextInputFormat() {
        SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat(UtcDates.getDatePatternAsInputFormat(((SimpleDateFormat)java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toPattern()), Locale.getDefault());
        simpleDateFormat0.setTimeZone(UtcDates.getTimeZone());
        simpleDateFormat0.setLenient(false);
        return simpleDateFormat0;
    }

    static String getDefaultTextInputHint(Resources resources0, SimpleDateFormat simpleDateFormat0) {
        String s = simpleDateFormat0.toPattern();
        String s1 = resources0.getString(string.mtrl_picker_text_input_year_abbr);
        String s2 = resources0.getString(string.mtrl_picker_text_input_month_abbr);
        String s3 = resources0.getString(string.mtrl_picker_text_input_day_abbr);
        if(Locale.getDefault().getLanguage().equals("ko")) {
            s = s.replaceAll("d+", "d").replaceAll("M+", "M").replaceAll("y+", "y");
        }
        return s.replace("d", s3).replace("M", s2).replace("y", s1);
    }

    private static java.text.DateFormat getFormat(int v, Locale locale0) {
        java.text.DateFormat dateFormat0 = java.text.DateFormat.getDateInstance(v, locale0);
        dateFormat0.setTimeZone(UtcDates.getTimeZone());
        return dateFormat0;
    }

    static java.text.DateFormat getFullFormat() {
        return UtcDates.getFullFormat(Locale.getDefault());
    }

    static java.text.DateFormat getFullFormat(Locale locale0) {
        return UtcDates.getFormat(0, locale0);
    }

    static java.text.DateFormat getMediumFormat() {
        return UtcDates.getMediumFormat(Locale.getDefault());
    }

    static java.text.DateFormat getMediumFormat(Locale locale0) {
        return UtcDates.getFormat(2, locale0);
    }

    static java.text.DateFormat getMediumNoYear() {
        return UtcDates.getMediumNoYear(Locale.getDefault());
    }

    static java.text.DateFormat getMediumNoYear(Locale locale0) {
        java.text.DateFormat dateFormat0 = (SimpleDateFormat)UtcDates.getMediumFormat(locale0);
        ((SimpleDateFormat)dateFormat0).applyPattern(UtcDates.removeYearFromDateFormatPattern(((SimpleDateFormat)dateFormat0).toPattern()));
        return dateFormat0;
    }

    static DateFormat getMonthWeekdayDayFormat(Locale locale0) {
        return UtcDates.getAndroidFormat("MMMMEEEEd", locale0);
    }

    static java.text.DateFormat getNormalizedFormat(java.text.DateFormat dateFormat0) {
        java.text.DateFormat dateFormat1 = (java.text.DateFormat)dateFormat0.clone();
        dateFormat1.setTimeZone(UtcDates.getTimeZone());
        return dateFormat1;
    }

    static SimpleDateFormat getSimpleFormat(String s) {
        return UtcDates.getSimpleFormat(s, Locale.getDefault());
    }

    private static SimpleDateFormat getSimpleFormat(String s, Locale locale0) {
        SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat(s, locale0);
        simpleDateFormat0.setTimeZone(UtcDates.getTimeZone());
        return simpleDateFormat0;
    }

    static TimeSource getTimeSource() {
        TimeSource timeSource0 = (TimeSource)UtcDates.timeSourceRef.get();
        return timeSource0 == null ? TimeSource.system() : timeSource0;
    }

    private static TimeZone getTimeZone() {
        return TimeZone.getTimeZone("UTC");
    }

    static Calendar getTodayCalendar() {
        Calendar calendar0 = UtcDates.getTimeSource().now();
        calendar0.set(11, 0);
        calendar0.set(12, 0);
        calendar0.set(13, 0);
        calendar0.set(14, 0);
        calendar0.setTimeZone(UtcDates.getTimeZone());
        return calendar0;
    }

    private static android.icu.util.TimeZone getUtcAndroidTimeZone() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    static Calendar getUtcCalendar() {
        return UtcDates.getUtcCalendarOf(null);
    }

    static Calendar getUtcCalendarOf(Calendar calendar0) {
        Calendar calendar1 = Calendar.getInstance(UtcDates.getTimeZone());
        if(calendar0 == null) {
            calendar1.clear();
            return calendar1;
        }
        calendar1.setTimeInMillis(calendar0.getTimeInMillis());
        return calendar1;
    }

    static DateFormat getYearAbbrMonthDayFormat(Locale locale0) {
        return UtcDates.getAndroidFormat("yMMMd", locale0);
    }

    static DateFormat getYearMonthFormat(Locale locale0) {
        return UtcDates.getAndroidFormat("yMMMM", locale0);
    }

    static DateFormat getYearMonthWeekdayDayFormat(Locale locale0) {
        return UtcDates.getAndroidFormat("yMMMMEEEEd", locale0);
    }

    private static String removeYearFromDateFormatPattern(String s) {
        int v = UtcDates.findCharactersInDateFormatPattern(s, "yY", 1, 0);
        if(v >= s.length()) {
            return s;
        }
        String s1 = "EMd";
        int v1 = UtcDates.findCharactersInDateFormatPattern(s, "EMd", 1, v);
        if(v1 < s.length()) {
            s1 = "EMd,";
        }
        return s.replace(s.substring(UtcDates.findCharactersInDateFormatPattern(s, s1, -1, v) + 1, v1), " ").trim();
    }

    static void setTimeSource(TimeSource timeSource0) {
        UtcDates.timeSourceRef.set(timeSource0);
    }
}

