package androidx.core.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

class PathInterpolatorApi14 implements Interpolator {
    private static final float PRECISION = 0.002f;
    private final float[] mX;
    private final float[] mY;

    PathInterpolatorApi14(float f, float f1) {
        this(PathInterpolatorApi14.createQuad(f, f1));
    }

    PathInterpolatorApi14(float f, float f1, float f2, float f3) {
        this(PathInterpolatorApi14.createCubic(f, f1, f2, f3));
    }

    PathInterpolatorApi14(Path path0) {
        PathMeasure pathMeasure0 = new PathMeasure(path0, false);
        float f = pathMeasure0.getLength();
        int v = ((int)(f / 0.002f)) + 1;
        this.mX = new float[v];
        this.mY = new float[v];
        float[] arr_f = new float[2];
        for(int v1 = 0; v1 < v; ++v1) {
            pathMeasure0.getPosTan(((float)v1) * f / ((float)(((int)(f / 0.002f)))), arr_f, null);
            this.mX[v1] = arr_f[0];
            this.mY[v1] = arr_f[1];
        }
    }

    private static Path createCubic(float f, float f1, float f2, float f3) {
        Path path0 = new Path();
        path0.moveTo(0.0f, 0.0f);
        path0.cubicTo(f, f1, f2, f3, 1.0f, 1.0f);
        return path0;
    }

    private static Path createQuad(float f, float f1) {
        Path path0 = new Path();
        path0.moveTo(0.0f, 0.0f);
        path0.quadTo(f, f1, 1.0f, 1.0f);
        return path0;
    }

    @Override  // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if(f <= 0.0f) {
            return 0.0f;
        }
        if(f >= 1.0f) {
            return 1.0f;
        }
        int v = this.mX.length - 1;
        int v1 = 0;
        while(v - v1 > 1) {
            int v2 = (v1 + v) / 2;
            if(f < this.mX[v2]) {
                v = v2;
            }
            else {
                v1 = v2;
            }
        }
        float f1 = this.mX[v];
        float f2 = this.mX[v1];
        float f3 = f1 - f2;
        if(f3 == 0.0f) {
            return this.mY[v1];
        }
        float f4 = this.mY[v1];
        return f4 + (f - f2) / f3 * (this.mY[v] - f4);
    }
}

