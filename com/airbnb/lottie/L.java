package com.airbnb.lottie;

import androidx.core.os.TraceCompat;

public class L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static int depthPastMaxDepth = 0;
    private static String[] sections = null;
    private static long[] startTimeNs = null;
    private static int traceDepth = 0;
    private static boolean traceEnabled = false;

    static {
    }

    public static void beginSection(String s) {
        if(!L.traceEnabled) {
            return;
        }
        int v = L.traceDepth;
        if(v == 20) {
            ++L.depthPastMaxDepth;
            return;
        }
        L.sections[v] = s;
        L.startTimeNs[v] = System.nanoTime();
        TraceCompat.beginSection(s);
        ++L.traceDepth;
    }

    public static float endSection(String s) {
        int v = L.depthPastMaxDepth;
        if(v > 0) {
            L.depthPastMaxDepth = v - 1;
            return 0.0f;
        }
        if(!L.traceEnabled) {
            return 0.0f;
        }
        int v1 = L.traceDepth - 1;
        L.traceDepth = v1;
        if(v1 == -1) {
            throw new IllegalStateException("Can\'t end trace section. There are none.");
        }
        if(!s.equals(L.sections[v1])) {
            throw new IllegalStateException("Unbalanced trace call " + s + ". Expected " + L.sections[L.traceDepth] + ".");
        }
        TraceCompat.endSection();
        return ((float)(System.nanoTime() - L.startTimeNs[L.traceDepth])) / 1000000.0f;
    }

    public static void setTraceEnabled(boolean z) {
        if(L.traceEnabled != z) {
            L.traceEnabled = z;
            if(z) {
                L.sections = new String[20];
                L.startTimeNs = new long[20];
            }
        }
    }
}

