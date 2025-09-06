package androidx.core.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.annotation.ReplaceWith;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public final class VelocityTrackerCompat {
    static class Api34Impl {
        static float getAxisVelocity(VelocityTracker velocityTracker0, int v) {
            return velocityTracker0.getAxisVelocity(v);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker0, int v, int v1) {
            return velocityTracker0.getAxisVelocity(v, v1);
        }

        static boolean isAxisSupported(VelocityTracker velocityTracker0, int v) {
            return velocityTracker0.isAxisSupported(v);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VelocityTrackableMotionEventAxis {
    }

    private static Map sFallbackTrackers;

    static {
        VelocityTrackerCompat.sFallbackTrackers = Collections.synchronizedMap(new WeakHashMap());
    }

    public static void addMovement(VelocityTracker velocityTracker0, MotionEvent motionEvent0) {
        velocityTracker0.addMovement(motionEvent0);
        if(Build.VERSION.SDK_INT < 34 && motionEvent0.getSource() == 0x400000) {
            if(!VelocityTrackerCompat.sFallbackTrackers.containsKey(velocityTracker0)) {
                VelocityTrackerCompat.sFallbackTrackers.put(velocityTracker0, new VelocityTrackerFallback());
            }
            ((VelocityTrackerFallback)VelocityTrackerCompat.sFallbackTrackers.get(velocityTracker0)).addMovement(motionEvent0);
        }
    }

    public static void clear(VelocityTracker velocityTracker0) {
        velocityTracker0.clear();
        VelocityTrackerCompat.removeFallbackForTracker(velocityTracker0);
    }

    public static void computeCurrentVelocity(VelocityTracker velocityTracker0, int v) {
        VelocityTrackerCompat.computeCurrentVelocity(velocityTracker0, v, 3.402823E+38f);
    }

    public static void computeCurrentVelocity(VelocityTracker velocityTracker0, int v, float f) {
        velocityTracker0.computeCurrentVelocity(v, f);
        VelocityTrackerFallback velocityTrackerFallback0 = VelocityTrackerCompat.getFallbackTrackerOrNull(velocityTracker0);
        if(velocityTrackerFallback0 != null) {
            velocityTrackerFallback0.computeCurrentVelocity(v, f);
        }
    }

    public static float getAxisVelocity(VelocityTracker velocityTracker0, int v) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getAxisVelocity(velocityTracker0, v);
        }
        if(v == 0) {
            return velocityTracker0.getXVelocity();
        }
        if(v == 1) {
            return velocityTracker0.getYVelocity();
        }
        VelocityTrackerFallback velocityTrackerFallback0 = VelocityTrackerCompat.getFallbackTrackerOrNull(velocityTracker0);
        return velocityTrackerFallback0 == null ? 0.0f : velocityTrackerFallback0.getAxisVelocity(v);
    }

    public static float getAxisVelocity(VelocityTracker velocityTracker0, int v, int v1) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getAxisVelocity(velocityTracker0, v, v1);
        }
        if(v == 0) {
            return velocityTracker0.getXVelocity(v1);
        }
        return v == 1 ? velocityTracker0.getYVelocity(v1) : 0.0f;
    }

    private static VelocityTrackerFallback getFallbackTrackerOrNull(VelocityTracker velocityTracker0) {
        return (VelocityTrackerFallback)VelocityTrackerCompat.sFallbackTrackers.get(velocityTracker0);
    }

    @ReplaceWith(expression = "tracker.getXVelocity(pointerId)")
    @Deprecated
    public static float getXVelocity(VelocityTracker velocityTracker0, int v) {
        return velocityTracker0.getXVelocity(v);
    }

    @ReplaceWith(expression = "tracker.getYVelocity(pointerId)")
    @Deprecated
    public static float getYVelocity(VelocityTracker velocityTracker0, int v) {
        return velocityTracker0.getYVelocity(v);
    }

    public static boolean isAxisSupported(VelocityTracker velocityTracker0, int v) {
        return Build.VERSION.SDK_INT < 34 ? v == 0 || v == 1 || v == 26 : Api34Impl.isAxisSupported(velocityTracker0, v);
    }

    public static void recycle(VelocityTracker velocityTracker0) {
        velocityTracker0.recycle();
        VelocityTrackerCompat.removeFallbackForTracker(velocityTracker0);
    }

    private static void removeFallbackForTracker(VelocityTracker velocityTracker0) {
        VelocityTrackerCompat.sFallbackTrackers.remove(velocityTracker0);
    }
}

