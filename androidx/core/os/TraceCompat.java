package androidx.core.os;

import android.os.Build.VERSION;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

@Deprecated
public final class TraceCompat {
    static class Api29Impl {
        static void beginAsyncSection(String s, int v) {
            Trace.beginAsyncSection(s, v);
        }

        static void endAsyncSection(String s, int v) {
            Trace.endAsyncSection(s, v);
        }

        static boolean isEnabled() {
            return Trace.isEnabled();
        }

        static void setCounter(String s, long v) {
            Trace.setCounter(s, v);
        }
    }

    private static final String TAG = "TraceCompat";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    static {
        if(Build.VERSION.SDK_INT < 29) {
            try {
                TraceCompat.sTraceTagApp = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                TraceCompat.sIsTagEnabledMethod = Trace.class.getMethod("isTagEnabled", Long.TYPE);
                TraceCompat.sAsyncTraceBeginMethod = Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                TraceCompat.sAsyncTraceEndMethod = Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                TraceCompat.sTraceCounterMethod = Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }
            catch(Exception exception0) {
                Log.i("TraceCompat", "Unable to initialize via reflection.", exception0);
            }
        }
    }

    public static void beginAsyncSection(String s, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.beginAsyncSection(s, v);
            return;
        }
        try {
            TraceCompat.sAsyncTraceBeginMethod.invoke(null, TraceCompat.sTraceTagApp, s, v);
        }
        catch(Exception unused_ex) {
            Log.v("TraceCompat", "Unable to invoke asyncTraceBegin() via reflection.");
        }
    }

    public static void beginSection(String s) {
        Trace.beginSection(s);
    }

    public static void endAsyncSection(String s, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.endAsyncSection(s, v);
            return;
        }
        try {
            TraceCompat.sAsyncTraceEndMethod.invoke(null, TraceCompat.sTraceTagApp, s, v);
        }
        catch(Exception unused_ex) {
            Log.v("TraceCompat", "Unable to invoke endAsyncSection() via reflection.");
        }
    }

    public static void endSection() {
        Trace.endSection();
    }

    public static boolean isEnabled() {
        if(Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.isEnabled();
        }
        try {
            return ((Boolean)TraceCompat.sIsTagEnabledMethod.invoke(null, TraceCompat.sTraceTagApp)).booleanValue();
        }
        catch(Exception unused_ex) {
            Log.v("TraceCompat", "Unable to invoke isTagEnabled() via reflection.");
            return false;
        }
    }

    public static void setCounter(String s, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setCounter(s, ((long)v));
            return;
        }
        try {
            TraceCompat.sTraceCounterMethod.invoke(null, TraceCompat.sTraceTagApp, s, v);
        }
        catch(Exception unused_ex) {
            Log.v("TraceCompat", "Unable to invoke traceCounter() via reflection.");
        }
    }
}

