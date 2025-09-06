package androidx.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import org.xmlpull.v1.XmlPullParser;

public class PatternPathMotion extends PathMotion {
    private Path mOriginalPatternPath;
    private final Path mPatternPath;
    private final Matrix mTempMatrix;

    public PatternPathMotion() {
        Path path0 = new Path();
        this.mPatternPath = path0;
        this.mTempMatrix = new Matrix();
        path0.lineTo(1.0f, 0.0f);
        this.mOriginalPatternPath = path0;
    }

    public PatternPathMotion(Context context0, AttributeSet attributeSet0) {
        this.mPatternPath = new Path();
        this.mTempMatrix = new Matrix();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.PATTERN_PATH_MOTION);
        try {
            String s = TypedArrayUtils.getNamedString(typedArray0, ((XmlPullParser)attributeSet0), "patternPathData", 0);
            if(s != null) {
                this.setPatternPath(PathParser.createPathFromPathData(s));
                return;
            }
        }
        finally {
            typedArray0.recycle();
        }
        throw new RuntimeException("pathData must be supplied for patternPathMotion");
    }

    public PatternPathMotion(Path path0) {
        this.mPatternPath = new Path();
        this.mTempMatrix = new Matrix();
        this.setPatternPath(path0);
    }

    private static float distance(float f, float f1) {
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    @Override  // androidx.transition.PathMotion
    public Path getPath(float f, float f1, float f2, float f3) {
        float f4 = f2 - f;
        float f5 = f3 - f1;
        float f6 = PatternPathMotion.distance(f4, f5);
        this.mTempMatrix.setScale(f6, f6);
        this.mTempMatrix.postRotate(((float)Math.toDegrees(Math.atan2(f5, f4))));
        this.mTempMatrix.postTranslate(f, f1);
        Path path0 = new Path();
        this.mPatternPath.transform(this.mTempMatrix, path0);
        return path0;
    }

    public Path getPatternPath() {
        return this.mOriginalPatternPath;
    }

    public void setPatternPath(Path path0) {
        PathMeasure pathMeasure0 = new PathMeasure(path0, false);
        float f = pathMeasure0.getLength();
        float[] arr_f = new float[2];
        pathMeasure0.getPosTan(f, arr_f, null);
        float f1 = arr_f[0];
        float f2 = arr_f[1];
        pathMeasure0.getPosTan(0.0f, arr_f, null);
        float f3 = arr_f[0];
        float f4 = arr_f[1];
        if(f3 == f1 && f4 == f2) {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        }
        this.mTempMatrix.setTranslate(-f3, -f4);
        float f5 = f1 - f3;
        float f6 = f2 - f4;
        float f7 = PatternPathMotion.distance(f5, f6);
        this.mTempMatrix.postScale(1.0f / f7, 1.0f / f7);
        this.mTempMatrix.postRotate(((float)Math.toDegrees(-Math.atan2(f6, f5))));
        path0.transform(this.mTempMatrix, this.mPatternPath);
        this.mOriginalPatternPath = path0;
    }
}

