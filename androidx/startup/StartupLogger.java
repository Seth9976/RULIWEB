package androidx.startup;

import android.util.Log;

public final class StartupLogger {
    static final boolean DEBUG = false;
    private static final String TAG = "StartupLogger";

    public static void e(String s, Throwable throwable0) {
        Log.e("StartupLogger", s, throwable0);
    }

    public static void i(String s) {
        Log.i("StartupLogger", s);
    }

    public static void w(String s) {
        Log.w("StartupLogger", s);
    }
}

