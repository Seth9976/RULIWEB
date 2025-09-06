package androidx.tracing;

import android.os.Trace;

final class TraceApi18Impl {
    public static void beginSection(String s) {
        Trace.beginSection(s);
    }

    public static void endSection() {
        Trace.endSection();
    }
}

