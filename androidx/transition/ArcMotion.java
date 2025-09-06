package androidx.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import org.xmlpull.v1.XmlPullParser;

public class ArcMotion extends PathMotion {
    private static final float DEFAULT_MAX_ANGLE_DEGREES = 70.0f;
    private static final float DEFAULT_MAX_TANGENT;
    private static final float DEFAULT_MIN_ANGLE_DEGREES;
    private float mMaximumAngle;
    private float mMaximumTangent;
    private float mMinimumHorizontalAngle;
    private float mMinimumHorizontalTangent;
    private float mMinimumVerticalAngle;
    private float mMinimumVerticalTangent;

    static {
        ArcMotion.DEFAULT_MAX_TANGENT = 0.700208f;
    }

    public ArcMotion() {
        this.mMinimumHorizontalAngle = 0.0f;
        this.mMinimumVerticalAngle = 0.0f;
        this.mMaximumAngle = 70.0f;
        this.mMinimumHorizontalTangent = 0.0f;
        this.mMinimumVerticalTangent = 0.0f;
        this.mMaximumTangent = ArcMotion.DEFAULT_MAX_TANGENT;
    }

    public ArcMotion(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mMinimumHorizontalAngle = 0.0f;
        this.mMinimumVerticalAngle = 0.0f;
        this.mMaximumAngle = 70.0f;
        this.mMinimumHorizontalTangent = 0.0f;
        this.mMinimumVerticalTangent = 0.0f;
        this.mMaximumTangent = ArcMotion.DEFAULT_MAX_TANGENT;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.ARC_MOTION);
        this.setMinimumVerticalAngle(TypedArrayUtils.getNamedFloat(typedArray0, ((XmlPullParser)attributeSet0), "minimumVerticalAngle", 1, 0.0f));
        this.setMinimumHorizontalAngle(TypedArrayUtils.getNamedFloat(typedArray0, ((XmlPullParser)attributeSet0), "minimumHorizontalAngle", 0, 0.0f));
        this.setMaximumAngle(TypedArrayUtils.getNamedFloat(typedArray0, ((XmlPullParser)attributeSet0), "maximumAngle", 2, 70.0f));
        typedArray0.recycle();
    }

    public float getMaximumAngle() {
        return this.mMaximumAngle;
    }

    public float getMinimumHorizontalAngle() {
        return this.mMinimumHorizontalAngle;
    }

    public float getMinimumVerticalAngle() {
        return this.mMinimumVerticalAngle;
    }

    @Override  // androidx.transition.PathMotion
    public Path getPath(float f, float f1, float f2, float f3) {
        float f12;
        float f11;
        float f10;
        Path path0 = new Path();
        path0.moveTo(f, f1);
        float f4 = f2 - f;
        float f5 = f3 - f1;
        float f6 = f4 * f4 + f5 * f5;
        float f7 = (f + f2) / 2.0f;
        float f8 = (f1 + f3) / 2.0f;
        boolean z = f1 > f3;
        if(Math.abs(f4) < Math.abs(f5)) {
            float f9 = Math.abs(f6 / (f5 * 2.0f));
            if(z) {
                f10 = f9 + f3;
                f11 = f2;
            }
            else {
                f10 = f9 + f1;
                f11 = f;
            }
            f12 = this.mMinimumVerticalTangent;
        }
        else {
            float f13 = f6 / (f4 * 2.0f);
            if(z) {
                f10 = f1;
                f11 = f13 + f;
            }
            else {
                f11 = f2 - f13;
                f10 = f3;
            }
            f12 = this.mMinimumHorizontalTangent;
        }
        float f14 = 0.25f * f6 * f12 * f12;
        float f15 = (f7 - f11) * (f7 - f11) + (f8 - f10) * (f8 - f10);
        float f16 = 0.25f * f6 * this.mMaximumTangent * this.mMaximumTangent;
        if(f15 >= f14) {
            f14 = f15 > f16 ? f16 : 0.0f;
        }
        if(f14 != 0.0f) {
            float f17 = (float)Math.sqrt(f14 / f15);
            f11 = (f11 - f7) * f17 + f7;
            f10 = f8 + f17 * (f10 - f8);
        }
        path0.cubicTo((f + f11) / 2.0f, (f1 + f10) / 2.0f, (f11 + f2) / 2.0f, (f10 + f3) / 2.0f, f2, f3);
        return path0;
    }

    public void setMaximumAngle(float f) {
        this.mMaximumAngle = f;
        this.mMaximumTangent = ArcMotion.toTangent(f);
    }

    public void setMinimumHorizontalAngle(float f) {
        this.mMinimumHorizontalAngle = f;
        this.mMinimumHorizontalTangent = ArcMotion.toTangent(f);
    }

    public void setMinimumVerticalAngle(float f) {
        this.mMinimumVerticalAngle = f;
        this.mMinimumVerticalTangent = ArcMotion.toTangent(f);
    }

    private static float toTangent(float f) {
        if(f < 0.0f || f > 90.0f) {
            throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        }
        return (float)Math.tan(Math.toRadians(f / 2.0f));
    }
}

