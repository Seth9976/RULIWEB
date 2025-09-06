package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VolleyLog {
    static class MarkerLog {
        static class Marker {
            public final String name;
            public final long thread;
            public final long time;

            public Marker(String s, long v, long v1) {
                this.name = s;
                this.thread = v;
                this.time = v1;
            }
        }

        public static final boolean ENABLED;
        private static final long MIN_DURATION_FOR_LOGGING_MS;
        private boolean mFinished;
        private final List mMarkers;

        static {
            MarkerLog.ENABLED = VolleyLog.DEBUG;
        }

        MarkerLog() {
            this.mMarkers = new ArrayList();
            this.mFinished = false;
        }

        public void add(String s, long v) {
            synchronized(this) {
                if(!this.mFinished) {
                    Marker volleyLog$MarkerLog$Marker0 = new Marker(s, v, SystemClock.elapsedRealtime());
                    this.mMarkers.add(volleyLog$MarkerLog$Marker0);
                    return;
                }
            }
            throw new IllegalStateException("Marker added to finished log");
        }

        @Override
        protected void finalize() throws Throwable {
            if(!this.mFinished) {
                this.finish("Request on the loose");
                VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public void finish(String s) {
            synchronized(this) {
                this.mFinished = true;
                long v1 = this.getTotalDuration();
                if(v1 <= 0L) {
                    return;
                }
                long v2 = ((Marker)this.mMarkers.get(0)).time;
                VolleyLog.d("(%-4d ms) %s", new Object[]{v1, s});
                for(Object object0: this.mMarkers) {
                    VolleyLog.d("(+%-4d) [%2d] %s", new Object[]{((long)(((Marker)object0).time - v2)), ((Marker)object0).thread, ((Marker)object0).name});
                    v2 = ((Marker)object0).time;
                }
            }
        }

        private long getTotalDuration() {
            if(this.mMarkers.size() == 0) {
                return 0L;
            }
            long v = ((Marker)this.mMarkers.get(0)).time;
            return ((Marker)this.mMarkers.get(this.mMarkers.size() - 1)).time - v;
        }
    }

    private static final String CLASS_NAME = null;
    public static boolean DEBUG = false;
    public static String TAG = "Volley";

    static {
        VolleyLog.DEBUG = Log.isLoggable("Volley", 2);
        VolleyLog.CLASS_NAME = "com.android.volley.VolleyLog";
    }

    private static String buildMessage(String s, Object[] arr_object) {
        if(arr_object != null) {
            s = String.format(Locale.US, s, arr_object);
        }
        StackTraceElement[] arr_stackTraceElement = new Throwable().fillInStackTrace().getStackTrace();
        for(int v = 2; v < arr_stackTraceElement.length; ++v) {
            if(!arr_stackTraceElement[v].getClassName().equals(VolleyLog.CLASS_NAME)) {
                String s1 = arr_stackTraceElement[v].getClassName();
                String s2 = s1.substring(s1.lastIndexOf(46) + 1);
                String s3 = s2.substring(s2.lastIndexOf(36) + 1) + "." + arr_stackTraceElement[v].getMethodName();
                return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), s3, s);
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), "<unknown>", s);
    }

    public static void d(String s, Object[] arr_object) {
        Log.d(VolleyLog.TAG, VolleyLog.buildMessage(s, arr_object));
    }

    public static void e(String s, Object[] arr_object) {
        Log.e(VolleyLog.TAG, VolleyLog.buildMessage(s, arr_object));
    }

    public static void e(Throwable throwable0, String s, Object[] arr_object) {
        Log.e(VolleyLog.TAG, VolleyLog.buildMessage(s, arr_object), throwable0);
    }

    public static void setTag(String s) {
        VolleyLog.d("Changing log tag to %s", new Object[]{s});
        VolleyLog.TAG = s;
        VolleyLog.DEBUG = Log.isLoggable(s, 2);
    }

    public static void v(String s, Object[] arr_object) {
        if(VolleyLog.DEBUG) {
            Log.v(VolleyLog.TAG, VolleyLog.buildMessage(s, arr_object));
        }
    }

    public static void wtf(String s, Object[] arr_object) {
        Log.wtf(VolleyLog.TAG, VolleyLog.buildMessage(s, arr_object));
    }

    public static void wtf(Throwable throwable0, String s, Object[] arr_object) {
        Log.wtf(VolleyLog.TAG, VolleyLog.buildMessage(s, arr_object), throwable0);
    }
}

