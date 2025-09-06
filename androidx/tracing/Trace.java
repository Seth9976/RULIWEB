package androidx.tracing;

import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Trace {
    static final int MAX_TRACE_LABEL_LENGTH = 0x7F;
    static final String TAG = "Trace";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static boolean sHasAppTracingEnabled;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    public static void beginAsyncSection(String s, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            TraceApi29Impl.beginAsyncSection(Trace.truncatedTraceSectionLabel(s), v);
            return;
        }
        Trace.beginAsyncSectionFallback(Trace.truncatedTraceSectionLabel(s), v);
    }

    private static void beginAsyncSectionFallback(String s, int v) {
        try {
            if(Trace.sAsyncTraceBeginMethod == null) {
                Trace.sAsyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
            }
            Trace.sAsyncTraceBeginMethod.invoke(null, Trace.sTraceTagApp, s, v);
        }
        catch(Exception exception0) {
            Trace.handleException("asyncTraceBegin", exception0);
        }
    }

    public static void beginSection(String s) {
        TraceApi18Impl.beginSection(Trace.truncatedTraceSectionLabel(s));
    }

    public static void endAsyncSection(String s, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            TraceApi29Impl.endAsyncSection(Trace.truncatedTraceSectionLabel(s), v);
            return;
        }
        Trace.endAsyncSectionFallback(Trace.truncatedTraceSectionLabel(s), v);
    }

    private static void endAsyncSectionFallback(String s, int v) {
        try {
            if(Trace.sAsyncTraceEndMethod == null) {
                Trace.sAsyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
            }
            Trace.sAsyncTraceEndMethod.invoke(null, Trace.sTraceTagApp, s, v);
        }
        catch(Exception exception0) {
            Trace.handleException("asyncTraceEnd", exception0);
        }
    }

    public static void endSection() {
        TraceApi18Impl.endSection();
    }

    public static void forceEnableAppTracing() {
        if(Build.VERSION.SDK_INT < 0x1F && !Trace.sHasAppTracingEnabled) {
            try {
                Trace.sHasAppTracingEnabled = true;
                android.os.Trace.class.getMethod("setAppTracingAllowed", Boolean.TYPE).invoke(null, Boolean.TRUE);
            }
            catch(Exception exception0) {
                Trace.handleException("setAppTracingAllowed", exception0);
            }
        }
    }

    private static void handleException(String s, Exception exception0) {
        if(exception0 instanceof InvocationTargetException) {
            Throwable throwable0 = exception0.getCause();
            throw throwable0 instanceof RuntimeException ? ((RuntimeException)throwable0) : new RuntimeException(throwable0);
        }
        Log.v("Trace", "Unable to call " + s + " via reflection", exception0);
    }

    public static boolean isEnabled() {
        return Build.VERSION.SDK_INT < 29 ? Trace.isEnabledFallback() : TraceApi29Impl.isEnabled();
    }

    private static boolean isEnabledFallback() {
        try {
            if(Trace.sIsTagEnabledMethod == null) {
                Trace.sTraceTagApp = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);
                Trace.sIsTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean)Trace.sIsTagEnabledMethod.invoke(null, Trace.sTraceTagApp)).booleanValue();
        }
        catch(Exception exception0) {
            Trace.handleException("isTagEnabled", exception0);
            return false;
        }
    }

    public static void setCounter(String s, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            TraceApi29Impl.setCounter(Trace.truncatedTraceSectionLabel(s), v);
            return;
        }
        Trace.setCounterFallback(Trace.truncatedTraceSectionLabel(s), v);
    }

    private static void setCounterFallback(String s, int v) {
        try {
            if(Trace.sTraceCounterMethod == null) {
                Trace.sTraceCounterMethod = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }
            Trace.sTraceCounterMethod.invoke(null, Trace.sTraceTagApp, s, v);
        }
        catch(Exception exception0) {
            Trace.handleException("traceCounter", exception0);
        }
    }

    private static String truncatedTraceSectionLabel(String s) {
        return s.length() > 0x7F ? s.substring(0, 0x7F) : s;
    }
}

