package androidx.tracing;

import android.os.Trace;

final class TraceApi29Impl {
    public static void beginAsyncSection(String s, int v) {
        Trace.beginAsyncSection(s, v);
    }

    public static void endAsyncSection(String s, int v) {
        Trace.endAsyncSection(s, v);
    }

    public static boolean isEnabled() {
        return Trace.isEnabled();
    }

    public static void setCounter(String s, int v) {
        Trace.setCounter(s, ((long)v));
    }
}

