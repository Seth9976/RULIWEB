package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import org.xmlpull.v1.XmlPullParser;

public class PathInterpolatorCompat implements Interpolator {
    public static final double EPSILON = 0.00001;
    public static final int MAX_NUM_POINTS = 3000;
    private static final float PRECISION = 0.002f;
    private float[] mX;
    private float[] mY;

    public PathInterpolatorCompat(Context context0, AttributeSet attributeSet0, XmlPullParser xmlPullParser0) {
        this(context0.getResources(), context0.getTheme(), attributeSet0, xmlPullParser0);
    }

    public PathInterpolatorCompat(Resources resources0, Resources.Theme resources$Theme0, AttributeSet attributeSet0, XmlPullParser xmlPullParser0) {
        TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
        this.parseInterpolatorFromTypeArray(typedArray0, xmlPullParser0);
        typedArray0.recycle();
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
        float[] arr_f = this.mX;
        float f1 = arr_f[v];
        float f2 = arr_f[v1];
        float f3 = f1 - f2;
        if(f3 == 0.0f) {
            return this.mY[v1];
        }
        float[] arr_f1 = this.mY;
        float f4 = arr_f1[v1];
        return f4 + (f - f2) / f3 * (arr_f1[v] - f4);
    }

    private void initCubic(float f, float f1, float f2, float f3) {
        Path path0 = new Path();
        path0.moveTo(0.0f, 0.0f);
        path0.cubicTo(f, f1, f2, f3, 1.0f, 1.0f);
        this.initPath(path0);
    }

    private void initPath(Path path0) {
        int v = 0;
        PathMeasure pathMeasure0 = new PathMeasure(path0, false);
        float f = pathMeasure0.getLength();
        int v1 = Math.min(3000, ((int)(f / 0.002f)) + 1);
        if(v1 <= 0) {
            throw new IllegalArgumentException("The Path has a invalid length " + f);
        }
        this.mX = new float[v1];
        this.mY = new float[v1];
        float[] arr_f = new float[2];
        for(int v2 = 0; v2 < v1; ++v2) {
            pathMeasure0.getPosTan(((float)v2) * f / ((float)(v1 - 1)), arr_f, null);
            this.mX[v2] = arr_f[0];
            this.mY[v2] = arr_f[1];
        }
        if(((double)Math.abs(this.mX[0])) > 0.00001 || ((double)Math.abs(this.mY[0])) > 0.00001 || ((double)Math.abs(this.mX[v1 - 1] - 1.0f)) > 0.00001 || ((double)Math.abs(this.mY[v1 - 1] - 1.0f)) > 0.00001) {
            throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1) start: " + this.mX[0] + "," + this.mY[0] + " end:" + this.mX[v1 - 1] + "," + this.mY[v1 - 1]);
        }
        float f1 = 0.0f;
        for(int v3 = 0; v < v1; ++v3) {
            float[] arr_f1 = this.mX;
            float f2 = arr_f1[v3];
            if(f2 < f1) {
                throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f2);
            }
            arr_f1[v] = f2;
            ++v;
            f1 = f2;
        }
        if(pathMeasure0.nextContour()) {
            throw new IllegalArgumentException("The Path should be continuous, can\'t have 2+ contours");
        }
    }

    private void initQuad(float f, float f1) {
        Path path0 = new Path();
        path0.moveTo(0.0f, 0.0f);
        path0.quadTo(f, f1, 1.0f, 1.0f);
        this.initPath(path0);
    }

    private void parseInterpolatorFromTypeArray(TypedArray typedArray0, XmlPullParser xmlPullParser0) {
        if(TypedArrayUtils.hasAttribute(xmlPullParser0, "pathData")) {
            String s = TypedArrayUtils.getNamedString(typedArray0, xmlPullParser0, "pathData", 4);
            Path path0 = PathParser.createPathFromPathData(s);
            if(path0 == null) {
                throw new InflateException("The path is null, which is created from " + s);
            }
            this.initPath(path0);
            return;
        }
        if(!TypedArrayUtils.hasAttribute(xmlPullParser0, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        }
        if(!TypedArrayUtils.hasAttribute(xmlPullParser0, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        float f = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "controlX1", 0, 0.0f);
        float f1 = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "controlY1", 1, 0.0f);
        boolean z = TypedArrayUtils.hasAttribute(xmlPullParser0, "controlX2");
        if(z != TypedArrayUtils.hasAttribute(xmlPullParser0, "controlY2")) {
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
        }
        if(!z) {
            this.initQuad(f, f1);
            return;
        }
        this.initCubic(f, f1, TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "controlX2", 2, 0.0f), TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "controlY2", 3, 0.0f));
    }
}

