package androidx.core.content.res;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;

final class CamUtils {
    static final float[][] CAM16RGB_TO_XYZ;
    static final float[][] SRGB_TO_XYZ;
    static final float[] WHITE_POINT_D65;
    static final float[][] XYZ_TO_CAM16RGB;

    static {
        CamUtils.XYZ_TO_CAM16RGB = new float[][]{new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
        CamUtils.CAM16RGB_TO_XYZ = new float[][]{new float[]{1.862068f, -1.011255f, 0.149187f}, new float[]{0.387527f, 0.621447f, -0.008974f}, new float[]{-0.015842f, -0.034123f, 1.049964f}};
        CamUtils.WHITE_POINT_D65 = new float[]{95.046997f, 100.0f, 108.883003f};
        CamUtils.SRGB_TO_XYZ = new float[][]{new float[]{0.412339f, 0.357621f, 0.18051f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.019321f, 0.119164f, 0.950345f}};
    }

    static int intFromLStar(float f) {
        if(f < 1.0f) {
            return 0xFF000000;
        }
        if(f > 99.0f) {
            return -1;
        }
        float f1 = (f + 16.0f) / 116.0f;
        float f2 = f1 * f1 * f1;
        float f3 = f2 > 0.008856f ? f2 : (f1 * 116.0f - 16.0f) / 903.296326f;
        if(f2 <= 0.008856f) {
            f2 = (f1 * 116.0f - 16.0f) / 903.296326f;
        }
        return ColorUtils.XYZToColor(f3 * CamUtils.WHITE_POINT_D65[0], (f > 8.0f ? f1 * f1 * f1 : f / 903.296326f) * CamUtils.WHITE_POINT_D65[1], f2 * CamUtils.WHITE_POINT_D65[2]);
    }

    static float lStarFromInt(int v) {
        return CamUtils.lStarFromY(CamUtils.yFromInt(v));
    }

    static float lStarFromY(float f) {
        return f / 100.0f <= 0.008856f ? f / 100.0f * 903.296326f : ((float)Math.cbrt(f / 100.0f)) * 116.0f - 16.0f;
    }

    static float lerp(float f, float f1, float f2) [...] // Inlined contents

    static float linearized(int v) {
        return ((float)v) / 255.0f <= 0.04045f ? ((float)v) / 255.0f / 12.92f * 100.0f : ((float)Math.pow((((float)v) / 255.0f + 0.055f) / 1.055f, 2.4)) * 100.0f;
    }

    static void xyzFromInt(int v, float[] arr_f) {
        float f = CamUtils.linearized(Color.red(v));
        float f1 = CamUtils.linearized(Color.green(v));
        float f2 = CamUtils.linearized(Color.blue(v));
        float[] arr_f1 = CamUtils.SRGB_TO_XYZ[0];
        arr_f[0] = arr_f1[0] * f + arr_f1[1] * f1 + arr_f1[2] * f2;
        float[] arr_f2 = CamUtils.SRGB_TO_XYZ[1];
        arr_f[1] = arr_f2[0] * f + arr_f2[1] * f1 + arr_f2[2] * f2;
        float[] arr_f3 = CamUtils.SRGB_TO_XYZ[2];
        arr_f[2] = f * arr_f3[0] + f1 * arr_f3[1] + f2 * arr_f3[2];
    }

    static float yFromInt(int v) {
        float f = CamUtils.linearized(Color.red(v));
        float f1 = CamUtils.linearized(Color.green(v));
        float f2 = CamUtils.linearized(Color.blue(v));
        float[] arr_f = CamUtils.SRGB_TO_XYZ[1];
        return f * arr_f[0] + f1 * arr_f[1] + f2 * arr_f[2];
    }

    static float yFromLStar(float f) [...] // Inlined contents
}

