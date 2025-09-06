package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider {
    private static String getDateFormatPattern(int v) {
        switch(v) {
            case 0: {
                return "EEEE, MMMM d, y";
            }
            case 1: {
                return "MMMM d, y";
            }
            case 2: {
                return "MMM d, y";
            }
            case 3: {
                return "M/d/yy";
            }
            default: {
                throw new IllegalArgumentException("Unknown DateFormat style: " + v);
            }
        }
    }

    private static String getDatePartOfDateTimePattern(int v) {
        switch(v) {
            case 0: {
                return "EEEE, MMMM d, yyyy";
            }
            case 1: {
                return "MMMM d, yyyy";
            }
            case 2: {
                return "MMM d, yyyy";
            }
            case 3: {
                return "M/d/yy";
            }
            default: {
                throw new IllegalArgumentException("Unknown DateFormat style: " + v);
            }
        }
    }

    private static String getTimePartOfDateTimePattern(int v) {
        switch(v) {
            case 0: 
            case 1: {
                return "h:mm:ss a z";
            }
            case 2: {
                return "h:mm:ss a";
            }
            case 3: {
                return "h:mm a";
            }
            default: {
                throw new IllegalArgumentException("Unknown DateFormat style: " + v);
            }
        }
    }

    public static DateFormat getUSDateFormat(int v) {
        return new SimpleDateFormat(PreJava9DateFormatProvider.getDateFormatPattern(v), Locale.US);
    }

    public static DateFormat getUSDateTimeFormat(int v, int v1) {
        return new SimpleDateFormat(PreJava9DateFormatProvider.getDatePartOfDateTimePattern(v) + " " + PreJava9DateFormatProvider.getTimePartOfDateTimePattern(v1), Locale.US);
    }
}

