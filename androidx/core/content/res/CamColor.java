package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

public class CamColor {
    private static final float CHROMA_SEARCH_ENDPOINT = 0.4f;
    private static final float DE_MAX = 1.0f;
    private static final float DL_MAX = 0.2f;
    private static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01f;
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;
    private final float mJ;
    private final float mJstar;
    private final float mM;
    private final float mQ;
    private final float mS;

    CamColor(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.mHue = f;
        this.mChroma = f1;
        this.mJ = f2;
        this.mQ = f3;
        this.mM = f4;
        this.mS = f5;
        this.mJstar = f6;
        this.mAstar = f7;
        this.mBstar = f8;
    }

    float distance(CamColor camColor0) {
        float f = this.getJStar();
        float f1 = camColor0.getJStar();
        float f2 = this.getAStar();
        float f3 = camColor0.getAStar();
        float f4 = this.getBStar();
        float f5 = camColor0.getBStar();
        return (float)(Math.pow(Math.sqrt((f - f1) * (f - f1) + (f2 - f3) * (f2 - f3) + (f4 - f5) * (f4 - f5)), 0.63) * 1.41);
    }

    private static CamColor findCamByJ(float f, float f1, float f2) {
        float f3 = 100.0f;
        float f4 = 1000.0f;
        CamColor camColor0 = null;
        float f5 = 1000.0f;
        float f6 = 0.0f;
        while(Math.abs(f6 - f3) > 0.01f) {
            float f7 = (f3 - f6) / 2.0f + f6;
            int v = CamColor.fromJch(f7, f1, f).viewedInSrgb();
            float f8 = CamUtils.lStarFromInt(v);
            float f9 = Math.abs(f2 - f8);
            if(f9 < 0.2f) {
                CamColor camColor1 = CamColor.fromColor(v);
                float f10 = camColor1.distance(CamColor.fromJch(camColor1.getJ(), camColor1.getChroma(), f));
                if(f10 <= 1.0f) {
                    camColor0 = camColor1;
                    f4 = f9;
                    f5 = f10;
                }
            }
            if(f4 == 0.0f && f5 == 0.0f) {
                return camColor0;
            }
            if(f8 < f2) {
                f6 = f7;
            }
            else {
                f3 = f7;
            }
        }
        return camColor0;
    }

    static CamColor fromColor(int v) {
        float[] arr_f = new float[7];
        float[] arr_f1 = new float[3];
        CamColor.fromColorInViewingConditions(v, ViewingConditions.DEFAULT, arr_f, arr_f1);
        return new CamColor(arr_f1[0], arr_f1[1], arr_f[0], arr_f[1], arr_f[2], arr_f[3], arr_f[4], arr_f[5], arr_f[6]);
    }

    static void fromColorInViewingConditions(int v, ViewingConditions viewingConditions0, float[] arr_f, float[] arr_f1) {
        CamUtils.xyzFromInt(v, arr_f1);
        float f = arr_f1[0];
        float[] arr_f2 = CamUtils.XYZ_TO_CAM16RGB[0];
        float f1 = arr_f2[0] * f;
        float f2 = arr_f1[1];
        float f3 = f1 + arr_f2[1] * f2;
        float f4 = arr_f1[2];
        float f5 = f3 + arr_f2[2] * f4;
        float[] arr_f3 = CamUtils.XYZ_TO_CAM16RGB[1];
        float f6 = arr_f3[0] * f + arr_f3[1] * f2 + arr_f3[2] * f4;
        float[] arr_f4 = CamUtils.XYZ_TO_CAM16RGB[2];
        float f7 = f * arr_f4[0] + f2 * arr_f4[1] + f4 * arr_f4[2];
        float f8 = viewingConditions0.getRgbD()[0] * f5;
        float f9 = viewingConditions0.getRgbD()[1] * f6;
        float f10 = viewingConditions0.getRgbD()[2] * f7;
        float f11 = (float)Math.pow(((double)(viewingConditions0.getFl() * Math.abs(f8))) / 100.0, 0.42);
        float f12 = (float)Math.pow(((double)(viewingConditions0.getFl() * Math.abs(f9))) / 100.0, 0.42);
        float f13 = (float)Math.pow(((double)(viewingConditions0.getFl() * Math.abs(f10))) / 100.0, 0.42);
        float f14 = Math.signum(f8) * 400.0f * f11 / (f11 + 27.129999f);
        float f15 = Math.signum(f9) * 400.0f * f12 / (f12 + 27.129999f);
        float f16 = Math.signum(f10) * 400.0f * f13 / (f13 + 27.129999f);
        float f17 = ((float)(((double)f14) * 11.0 + ((double)f15) * -12.0 + ((double)f16))) / 11.0f;
        float f18 = ((float)(((double)(f14 + f15)) - ((double)f16) * 2.0)) / 9.0f;
        float f19 = ((float)Math.atan2(f18, f17)) * 180.0f / 3.141593f;
        if(f19 < 0.0f) {
            f19 += 360.0f;
        }
        else if(f19 >= 360.0f) {
            f19 -= 360.0f;
        }
        float f20 = ((float)Math.pow((f14 * 40.0f + f15 * 20.0f + f16) / 20.0f * viewingConditions0.getNbb() / viewingConditions0.getAw(), viewingConditions0.getC() * viewingConditions0.getZ())) * 100.0f;
        float f21 = 4.0f / viewingConditions0.getC() * ((float)Math.sqrt(f20 / 100.0f)) * (viewingConditions0.getAw() + 4.0f) * viewingConditions0.getFlRoot();
        float f22 = ((float)Math.pow(1.64 - Math.pow(0.29, viewingConditions0.getN()), 0.73)) * ((float)Math.pow(((float)(Math.cos(((double)(((double)f19) < 20.14 ? f19 + 360.0f : f19)) * 3.141593 / 180.0 + 2.0) + 3.8)) * 961.538452f * viewingConditions0.getNc() * viewingConditions0.getNcb() * ((float)Math.sqrt(f17 * f17 + f18 * f18)) / ((f14 * 20.0f + f15 * 20.0f + 21.0f * f16) / 20.0f + 0.305f), 0.9));
        float f23 = ((float)Math.sqrt(((double)f20) / 100.0)) * f22;
        float f24 = viewingConditions0.getFlRoot() * f23;
        float f25 = ((float)Math.sqrt(f22 * viewingConditions0.getC() / (viewingConditions0.getAw() + 4.0f))) * 50.0f;
        float f26 = ((float)Math.log(0.0228f * f24 + 1.0f)) * 43.85965f;
        double f27 = (double)(3.141593f * f19 / 180.0f);
        float f28 = ((float)Math.cos(f27)) * f26;
        float f29 = f26 * ((float)Math.sin(f27));
        arr_f1[0] = f19;
        arr_f1[1] = f23;
        if(arr_f != null) {
            arr_f[0] = f20;
            arr_f[1] = f21;
            arr_f[2] = f24;
            arr_f[3] = f25;
            arr_f[4] = 1.7f * f20 / (0.007f * f20 + 1.0f);
            arr_f[5] = f28;
            arr_f[6] = f29;
        }
    }

    private static CamColor fromJch(float f, float f1, float f2) {
        return CamColor.fromJchInFrame(f, f1, f2, ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float f, float f1, float f2, ViewingConditions viewingConditions0) {
        float f3 = viewingConditions0.getFlRoot() * f1;
        float f4 = ((float)Math.log(((double)f3) * 0.0228 + 1.0)) * 43.85965f;
        double f5 = (double)(3.141593f * f2 / 180.0f);
        return new CamColor(f2, f1, f, 4.0f / viewingConditions0.getC() * ((float)Math.sqrt(((double)f) / 100.0)) * (viewingConditions0.getAw() + 4.0f) * viewingConditions0.getFlRoot(), f3, ((float)Math.sqrt(f1 / ((float)Math.sqrt(((double)f) / 100.0)) * viewingConditions0.getC() / (viewingConditions0.getAw() + 4.0f))) * 50.0f, 1.7f * f / (0.007f * f + 1.0f), ((float)Math.cos(f5)) * f4, f4 * ((float)Math.sin(f5)));
    }

    float getAStar() {
        return this.mAstar;
    }

    float getBStar() {
        return this.mBstar;
    }

    float getChroma() {
        return this.mChroma;
    }

    float getHue() {
        return this.mHue;
    }

    float getJ() {
        return this.mJ;
    }

    float getJStar() {
        return this.mJstar;
    }

    float getM() {
        return this.mM;
    }

    public static void getM3HCTfromColor(int v, float[] arr_f) {
        CamColor.fromColorInViewingConditions(v, ViewingConditions.DEFAULT, null, arr_f);
        arr_f[2] = CamUtils.lStarFromInt(v);
    }

    float getQ() {
        return this.mQ;
    }

    float getS() {
        return this.mS;
    }

    public static int toColor(float f, float f1, float f2) {
        return CamColor.toColor(f, f1, f2, ViewingConditions.DEFAULT);
    }

    static int toColor(float f, float f1, float f2, ViewingConditions viewingConditions0) {
        if(((double)f1) >= 1.0 && ((double)Math.round(f2)) > 0.0 && ((double)Math.round(f2)) < 100.0) {
            float f3 = f < 0.0f ? 0.0f : Math.min(360.0f, f);
            float f4 = f1;
            CamColor camColor0 = null;
            float f5 = 0.0f;
            boolean z = true;
            while(Math.abs(f5 - f1) >= 0.4f) {
                CamColor camColor1 = CamColor.findCamByJ(f3, f4, f2);
                if(z) {
                    if(camColor1 != null) {
                        return camColor1.viewed(viewingConditions0);
                    }
                    f4 = (f1 - f5) / 2.0f + f5;
                    z = false;
                }
                else {
                    if(camColor1 == null) {
                        f1 = f4;
                    }
                    else {
                        f5 = f4;
                        camColor0 = camColor1;
                    }
                    f4 = (f1 - f5) / 2.0f + f5;
                }
            }
            return camColor0 == null ? CamUtils.intFromLStar(f2) : camColor0.viewed(viewingConditions0);
        }
        return CamUtils.intFromLStar(f2);
    }

    int viewed(ViewingConditions viewingConditions0) {
        float f = (float)Math.pow(((double)(((double)this.getChroma()) == 0.0 || ((double)this.getJ()) == 0.0 ? 0.0f : this.getChroma() / ((float)Math.sqrt(((double)this.getJ()) / 100.0)))) / Math.pow(1.64 - Math.pow(0.29, viewingConditions0.getN()), 0.73), 1.111111);
        double f1 = (double)(this.getHue() * 3.141593f / 180.0f);
        float f2 = viewingConditions0.getAw() * ((float)Math.pow(((double)this.getJ()) / 100.0, 1.0 / ((double)viewingConditions0.getC()) / ((double)viewingConditions0.getZ()))) / viewingConditions0.getNbb();
        float f3 = (float)Math.sin(f1);
        float f4 = (float)Math.cos(f1);
        float f5 = (f2 + 0.305f) * 23.0f * f / (((float)(Math.cos(f1 + 2.0) + 3.8)) * 961.538452f * viewingConditions0.getNc() * viewingConditions0.getNcb() * 23.0f + 11.0f * f * f4 + f * 108.0f * f3);
        float f6 = f4 * f5;
        float f7 = f5 * f3;
        float f8 = (451.0f * f6 + f2 * 460.0f + 288.0f * f7) / 1403.0f;
        float f9 = (f2 * 460.0f - 891.0f * f6 - 261.0f * f7) / 1403.0f;
        float f10 = (f2 * 460.0f - f6 * 220.0f - f7 * 6300.0f) / 1403.0f;
        float f11 = Math.signum(f8) * (100.0f / viewingConditions0.getFl()) * ((float)Math.pow(((float)Math.max(0.0, ((double)Math.abs(f8)) * 27.13 / (400.0 - ((double)Math.abs(f8))))), 2.380952)) / viewingConditions0.getRgbD()[0];
        float f12 = Math.signum(f9) * (100.0f / viewingConditions0.getFl()) * ((float)Math.pow(((float)Math.max(0.0, ((double)Math.abs(f9)) * 27.13 / (400.0 - ((double)Math.abs(f9))))), 2.380952)) / viewingConditions0.getRgbD()[1];
        float f13 = Math.signum(f10) * (100.0f / viewingConditions0.getFl()) * ((float)Math.pow(((float)Math.max(0.0, ((double)Math.abs(f10)) * 27.13 / (400.0 - ((double)Math.abs(f10))))), 2.380952)) / viewingConditions0.getRgbD()[2];
        float[] arr_f = CamUtils.CAM16RGB_TO_XYZ[0];
        float f14 = arr_f[0] * f11 + arr_f[1] * f12 + arr_f[2] * f13;
        float[] arr_f1 = CamUtils.CAM16RGB_TO_XYZ[1];
        float f15 = arr_f1[0] * f11 + arr_f1[1] * f12 + arr_f1[2] * f13;
        float[] arr_f2 = CamUtils.CAM16RGB_TO_XYZ[2];
        return ColorUtils.XYZToColor(f14, f15, f11 * arr_f2[0] + f12 * arr_f2[1] + f13 * arr_f2[2]);
    }

    int viewedInSrgb() {
        return this.viewed(ViewingConditions.DEFAULT);
    }
}

