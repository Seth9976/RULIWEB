package androidx.core.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public final class PathInterpolatorCompat {
    static class Api21Impl {
        static Interpolator createPathInterpolator(float f, float f1) {
            return new PathInterpolator(f, f1);
        }

        static Interpolator createPathInterpolator(float f, float f1, float f2, float f3) {
            return new PathInterpolator(f, f1, f2, f3);
        }

        static Interpolator createPathInterpolator(Path path0) {
            return new PathInterpolator(path0);
        }
    }

    public static Interpolator create(float f, float f1) {
        return Api21Impl.createPathInterpolator(f, f1);
    }

    public static Interpolator create(float f, float f1, float f2, float f3) {
        return Api21Impl.createPathInterpolator(f, f1, f2, f3);
    }

    public static Interpolator create(Path path0) {
        return Api21Impl.createPathInterpolator(path0);
    }
}

