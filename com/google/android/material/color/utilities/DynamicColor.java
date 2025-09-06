package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public final class DynamicColor {
    public final Function background;
    public final ContrastCurve contrastCurve;
    private final HashMap hctCache;
    public final boolean isBackground;
    public final String name;
    public final Function opacity;
    public final Function palette;
    public final Function secondBackground;
    public final Function tone;
    public final Function toneDeltaPair;

    public DynamicColor(String s, Function function0, Function function1, boolean z, Function function2, Function function3, ContrastCurve contrastCurve0, Function function4) {
        this.hctCache = new HashMap();
        this.name = s;
        this.palette = function0;
        this.tone = function1;
        this.isBackground = z;
        this.background = function2;
        this.secondBackground = function3;
        this.contrastCurve = contrastCurve0;
        this.toneDeltaPair = function4;
        this.opacity = null;
    }

    public DynamicColor(String s, Function function0, Function function1, boolean z, Function function2, Function function3, ContrastCurve contrastCurve0, Function function4, Function function5) {
        this.hctCache = new HashMap();
        this.name = s;
        this.palette = function0;
        this.tone = function1;
        this.isBackground = z;
        this.background = function2;
        this.secondBackground = function3;
        this.contrastCurve = contrastCurve0;
        this.toneDeltaPair = function4;
        this.opacity = function5;
    }

    // 去混淆评级： 低(20)
    public static double enableLightForeground(double f) {
        return !DynamicColor.tonePrefersLightForeground(f) || DynamicColor.toneAllowsLightForeground(f) ? f : 49.0;
    }

    public static double foregroundTone(double f, double f1) {
        double f2 = Contrast.lighterUnsafe(f, f1);
        double f3 = Contrast.darkerUnsafe(f, f1);
        double f4 = Contrast.ratioOfTones(f2, f);
        double f5 = Contrast.ratioOfTones(f3, f);
        if(DynamicColor.tonePrefersLightForeground(f)) {
            return f4 < f1 && f4 < f5 && (Math.abs(f4 - f5) >= 0.1 || f4 >= f1 || f5 >= f1) ? f3 : f2;
        }
        return f5 >= f1 || f5 >= f4 ? f3 : f2;
    }

    public static DynamicColor fromArgb(String s, int v) {
        Hct hct0 = Hct.fromInt(v);
        return DynamicColor.fromPalette(s, (DynamicScheme dynamicScheme0) -> TonalPalette.fromInt(v), (DynamicScheme dynamicScheme0) -> hct0.getTone());
    }

    public static DynamicColor fromPalette(String s, Function function0, Function function1) {
        return new DynamicColor(s, function0, function1, false, null, null, null, null);
    }

    public static DynamicColor fromPalette(String s, Function function0, Function function1, boolean z) {
        return new DynamicColor(s, function0, function1, z, null, null, null, null);
    }

    public int getArgb(DynamicScheme dynamicScheme0) {
        int v = this.getHct(dynamicScheme0).toInt();
        return this.opacity == null ? v : MathUtils.clampInt(0, 0xFF, ((int)Math.round(((double)(((Double)this.opacity.apply(dynamicScheme0)))) * 255.0))) << 24 | v & 0xFFFFFF;
    }

    public Hct getHct(DynamicScheme dynamicScheme0) {
        Hct hct0 = (Hct)this.hctCache.get(dynamicScheme0);
        if(hct0 != null) {
            return hct0;
        }
        double f = this.getTone(dynamicScheme0);
        Hct hct1 = ((TonalPalette)this.palette.apply(dynamicScheme0)).getHct(f);
        if(this.hctCache.size() > 4) {
            this.hctCache.clear();
        }
        this.hctCache.put(dynamicScheme0, hct1);
        return hct1;
    }

    public double getTone(DynamicScheme dynamicScheme0) {
        double f11;
        DynamicColor dynamicColor2;
        boolean z = true;
        boolean z1 = dynamicScheme0.contrastLevel < 0.0;
        Function function0 = this.toneDeltaPair;
        if(function0 != null) {
            ToneDeltaPair toneDeltaPair0 = (ToneDeltaPair)function0.apply(dynamicScheme0);
            DynamicColor dynamicColor0 = toneDeltaPair0.getRoleA();
            DynamicColor dynamicColor1 = toneDeltaPair0.getRoleB();
            double f = toneDeltaPair0.getDelta();
            TonePolarity tonePolarity0 = toneDeltaPair0.getPolarity();
            boolean z2 = toneDeltaPair0.getStayTogether();
            double f1 = ((DynamicColor)this.background.apply(dynamicScheme0)).getTone(dynamicScheme0);
            if(tonePolarity0 == TonePolarity.NEARER || tonePolarity0 == TonePolarity.LIGHTER && !dynamicScheme0.isDark || tonePolarity0 == TonePolarity.DARKER && dynamicScheme0.isDark) {
                dynamicColor2 = dynamicColor0;
            }
            else {
                z = false;
                dynamicColor2 = dynamicColor1;
            }
            DynamicColor dynamicColor3 = z ? dynamicColor1 : dynamicColor0;
            boolean z3 = this.name.equals(dynamicColor2.name);
            double f2 = dynamicScheme0.isDark ? 1.0 : -1.0;
            double f3 = dynamicColor2.contrastCurve.getContrast(dynamicScheme0.contrastLevel);
            double f4 = dynamicColor3.contrastCurve.getContrast(dynamicScheme0.contrastLevel);
            double f5 = (double)(((Double)dynamicColor2.tone.apply(dynamicScheme0)));
            if(Contrast.ratioOfTones(f1, f5) < f3) {
                f5 = DynamicColor.foregroundTone(f1, f3);
            }
            double f6 = (double)(((Double)dynamicColor3.tone.apply(dynamicScheme0)));
            if(Contrast.ratioOfTones(f1, f6) < f4) {
                f6 = DynamicColor.foregroundTone(f1, f4);
            }
            if(z1) {
                f5 = DynamicColor.foregroundTone(f1, f3);
                f6 = DynamicColor.foregroundTone(f1, f4);
            }
            if((f6 - f5) * f2 < f) {
                double f7 = f * f2;
                f6 = MathUtils.clampDouble(0.0, 100.0, f5 + f7);
                if((f6 - f5) * f2 < f) {
                    f5 = MathUtils.clampDouble(0.0, 100.0, f6 - f7);
                }
            }
            if(50.0 <= f5 && f5 < 60.0) {
                if(f2 > 0.0) {
                    return z3 ? 60.0 : Math.max(f6, f * f2 + 60.0);
                }
                return z3 ? 49.0 : Math.min(f6, f * f2 + 49.0);
            }
            if(50.0 <= f6 && f6 < 60.0) {
                if(z2) {
                    if(f2 > 0.0) {
                        return z3 ? 60.0 : Math.max(f6, f * f2 + 60.0);
                    }
                    return z3 ? 49.0 : Math.min(f6, f * f2 + 49.0);
                }
                if(f2 > 0.0) {
                    return z3 ? f5 : 60.0;
                }
                return z3 ? f5 : 49.0;
            }
            return z3 ? f5 : f6;
        }
        double f8 = (double)(((Double)this.tone.apply(dynamicScheme0)));
        Function function1 = this.background;
        if(function1 == null) {
            return f8;
        }
        double f9 = ((DynamicColor)function1.apply(dynamicScheme0)).getTone(dynamicScheme0);
        double f10 = this.contrastCurve.getContrast(dynamicScheme0.contrastLevel);
        if(Contrast.ratioOfTones(f9, f8) < f10) {
            f8 = DynamicColor.foregroundTone(f9, f10);
        }
        if(z1) {
            f8 = DynamicColor.foregroundTone(f9, f10);
        }
        if(!this.isBackground || 50.0 > f8 || f8 >= 60.0) {
            f11 = f8;
        }
        else if(Contrast.ratioOfTones(49.0, f9) >= f10) {
            f11 = 49.0;
        }
        else {
            f11 = 60.0;
        }
        if(this.secondBackground != null) {
            double f12 = ((DynamicColor)this.background.apply(dynamicScheme0)).getTone(dynamicScheme0);
            double f13 = ((DynamicColor)this.secondBackground.apply(dynamicScheme0)).getTone(dynamicScheme0);
            double f14 = Math.max(f12, f13);
            double f15 = Math.min(f12, f13);
            if(Contrast.ratioOfTones(f14, f11) < f10 || Contrast.ratioOfTones(f15, f11) < f10) {
                double f16 = Contrast.lighter(f14, f10);
                double f17 = Contrast.darker(f15, f10);
                ArrayList arrayList0 = new ArrayList();
                int v = Double.compare(f16, -1.0);
                if(v != 0) {
                    arrayList0.add(f16);
                }
                int v1 = Double.compare(f17, -1.0);
                if(v1 != 0) {
                    arrayList0.add(f17);
                }
                if(!DynamicColor.tonePrefersLightForeground(f12) && !DynamicColor.tonePrefersLightForeground(f13)) {
                    if(arrayList0.size() == 1) {
                        return (double)(((Double)arrayList0.get(0)));
                    }
                    return v1 == 0 ? 0.0 : f17;
                }
                return v == 0 ? 100.0 : f16;
            }
        }
        return f11;
    }

    // 检测为 Lambda 实现
    static TonalPalette lambda$fromArgb$0(TonalPalette tonalPalette0, DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$fromArgb$1(Hct hct0, DynamicScheme dynamicScheme0) [...]

    public static boolean toneAllowsLightForeground(double f) {
        return Math.round(f) <= 49L;
    }

    public static boolean tonePrefersLightForeground(double f) {
        return Math.round(f) < 60L;
    }
}

