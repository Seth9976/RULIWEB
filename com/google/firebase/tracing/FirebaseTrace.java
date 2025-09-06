package com.google.firebase.tracing;

import android.os.Trace;

public final class FirebaseTrace {
    public static void popTrace() {
        Trace.endSection();
    }

    public static void pushTrace(String s) {
        Trace.beginSection(s);
    }
}

