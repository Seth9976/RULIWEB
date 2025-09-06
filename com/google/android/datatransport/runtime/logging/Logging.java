package com.google.android.datatransport.runtime.logging;

import android.os.Build.VERSION;
import android.util.Log;

public final class Logging {
    private static final String LOG_PREFIX = "TRuntime.";
    private static final int MAX_LOG_TAG_SIZE_IN_SDK_N = 23;

    private static String concatTag(String s, String s1) {
        String s2 = s + s1;
        return s2.length() <= 23 ? s2 : s2.substring(0, 23);
    }

    public static void d(String s, String s1) {
        String s2 = Logging.getTag(s);
        if(Log.isLoggable(s2, 3)) {
            Log.d(s2, s1);
        }
    }

    public static void d(String s, String s1, Object object0) {
        String s2 = Logging.getTag(s);
        if(Log.isLoggable(s2, 3)) {
            Log.d(s2, String.format(s1, object0));
        }
    }

    public static void d(String s, String s1, Object object0, Object object1) {
        String s2 = Logging.getTag(s);
        if(Log.isLoggable(s2, 3)) {
            Log.d(s2, String.format(s1, object0, object1));
        }
    }

    public static void d(String s, String s1, Object[] arr_object) {
        String s2 = Logging.getTag(s);
        if(Log.isLoggable(s2, 3)) {
            Log.d(s2, String.format(s1, arr_object));
        }
    }

    public static void e(String s, String s1, Throwable throwable0) {
        String s2 = Logging.getTag(s);
        if(Log.isLoggable(s2, 6)) {
            Log.e(s2, s1, throwable0);
        }
    }

    private static String getTag(String s) {
        return Build.VERSION.SDK_INT >= 26 ? "TRuntime." + s : Logging.concatTag("TRuntime.", s);
    }

    public static void i(String s, String s1, Object object0) {
        String s2 = Logging.getTag(s);
        if(Log.isLoggable(s2, 4)) {
            Log.i(s2, String.format(s1, object0));
        }
    }

    public static void w(String s, String s1, Object object0) {
        String s2 = Logging.getTag(s);
        if(Log.isLoggable(s2, 5)) {
            Log.w(s2, String.format(s1, object0));
        }
    }
}

