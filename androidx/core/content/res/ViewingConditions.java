package androidx.core.content.res;

final class ViewingConditions {
    static final ViewingConditions DEFAULT;
    private final float mAw;
    private final float mC;
    private final float mFl;
    private final float mFlRoot;
    private final float mN;
    private final float mNbb;
    private final float mNc;
    private final float mNcb;
    private final float[] mRgbD;
    private final float mZ;

    static {
        ViewingConditions.DEFAULT = ViewingConditions.make(CamUtils.WHITE_POINT_D65, 11.725677f, 50.0f, 2.0f, false);
    }

    private ViewingConditions(float f, float f1, float f2, float f3, float f4, float f5, float[] arr_f, float f6, float f7, float f8) {
        this.mN = f;
        this.mAw = f1;
        this.mNbb = f2;
        this.mNcb = f3;
        this.mC = f4;
        this.mNc = f5;
        this.mRgbD = arr_f;
        this.mFl = f6;
        this.mFlRoot = f7;
        this.mZ = f8;
    }

    float getAw() {
        return this.mAw;
    }

    float getC() {
        return this.mC;
    }

    float getFl() {
        return this.mFl;
    }

    float getFlRoot() {
        return this.mFlRoot;
    }

    float getN() {
        return this.mN;
    }

    float getNbb() {
        return this.mNbb;
    }

    float getNc() {
        return this.mNc;
    }

    float getNcb() {
        return this.mNcb;
    }

    float[] getRgbD() {
        return this.mRgbD;
    }

    float getZ() {
        return this.mZ;
    }

    static ViewingConditions make(float[] arr_f, float f, float f1, float f2, boolean z) {
        float f3 = arr_f[0];
        float[] arr_f1 = CamUtils.XYZ_TO_CAM16RGB[0];
        float f4 = arr_f1[0] * f3;
        float f5 = arr_f[1];
        float f6 = f4 + arr_f1[1] * f5;
        float f7 = arr_f[2];
        float f8 = f6 + arr_f1[2] * f7;
        float[] arr_f2 = CamUtils.XYZ_TO_CAM16RGB[1];
        float f9 = arr_f2[0] * f3 + arr_f2[1] * f5 + arr_f2[2] * f7;
        float[] arr_f3 = CamUtils.XYZ_TO_CAM16RGB[2];
        float f10 = f3 * arr_f3[0] + f5 * arr_f3[1] + f7 * arr_f3[2];
        float f11 = f2 / 10.0f + 0.8f;
        float f12 = z ? 1.0f : (1.0f - ((float)Math.exp((-f - 42.0f) / 92.0f)) * 0.277778f) * f11;
        if(((double)f12) > 1.0) {
            f12 = 1.0f;
        }
        else if(((double)f12) < 0.0) {
            f12 = 0.0f;
        }
        float[] arr_f4 = {100.0f / f8 * f12 + 1.0f - f12, 100.0f / f9 * f12 + 1.0f - f12, 100.0f / f10 * f12 + 1.0f - f12};
        float f13 = 1.0f / (5.0f * f + 1.0f);
        float f14 = f13 * f13 * f13 * f13;
        float f15 = f14 * f + 0.1f * (1.0f - f14) * (1.0f - f14) * ((float)Math.cbrt(((double)f) * 5.0));
        float f16 = CamUtils.yFromLStar(f1) / arr_f[1];
        float f17 = 0.725f / ((float)Math.pow(f16, 0.2));
        float f18 = (float)Math.pow(((double)(arr_f4[0] * f15 * f8)) / 100.0, 0.42);
        float f19 = (float)Math.pow(((double)(arr_f4[1] * f15 * f9)) / 100.0, 0.42);
        float f20 = (float)Math.pow(((double)(arr_f4[2] * f15 * f10)) / 100.0, 0.42);
        new float[]{f18 * 400.0f / (f18 + 27.129999f), f19 * 400.0f / (f19 + 27.129999f), 400.0f * f20 / (f20 + 27.129999f)};
        return new ViewingConditions(f16, (f18 * 400.0f / (f18 + 27.129999f) * 2.0f + f19 * 400.0f / (f19 + 27.129999f) + 400.0f * f20 / (f20 + 27.129999f) * 0.05f) * f17, f17, f17, (((double)f11) >= 0.9 ? 0.59f + 0.1f * ((f11 - 0.9f) * 10.0f) : 0.525f + 0.065f * ((f11 - 0.8f) * 10.0f)), f11, arr_f4, f15, ((float)Math.pow(f15, 0.25)), ((float)Math.sqrt(f16)) + 1.48f);
    }
}

