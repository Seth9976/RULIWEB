package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TemperatureCache {
    private final Hct input;
    private Hct precomputedComplement;
    private List precomputedHctsByHue;
    private List precomputedHctsByTemp;
    private Map precomputedTempsByHct;

    private TemperatureCache() {
        throw new UnsupportedOperationException();
    }

    public TemperatureCache(Hct hct0) {
        this.input = hct0;
    }

    public List getAnalogousColors() {
        return this.getAnalogousColors(5, 12);
    }

    public List getAnalogousColors(int v, int v1) {
        int v2 = (int)Math.round(this.input.getHue());
        Hct hct0 = (Hct)this.getHctsByHue().get(v2);
        double f = this.getRelativeTemperature(hct0);
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(hct0);
        double f1 = 0.0;
        double f2 = 0.0;
        int v3 = 0;
        while(v3 < 360) {
            int v4 = MathUtils.sanitizeDegreesInt(v2 + v3);
            double f3 = this.getRelativeTemperature(((Hct)this.getHctsByHue().get(v4)));
            f2 += Math.abs(f3 - f);
            ++v3;
            f = f3;
        }
        double f4 = f2 / ((double)v1);
        double f5 = this.getRelativeTemperature(hct0);
        int v5 = 1;
        while(arrayList0.size() < v1) {
            int v6 = MathUtils.sanitizeDegreesInt(v2 + v5);
            Hct hct1 = (Hct)this.getHctsByHue().get(v6);
            double f6 = this.getRelativeTemperature(hct1);
            f1 += Math.abs(f6 - f5);
            boolean z = f1 >= ((double)arrayList0.size()) * f4;
            for(int v7 = 1; z && arrayList0.size() < v1; ++v7) {
                arrayList0.add(hct1);
                z = f1 >= ((double)(arrayList0.size() + v7)) * f4;
            }
            ++v5;
            if(v5 > 360) {
                while(arrayList0.size() < v1) {
                    arrayList0.add(hct1);
                }
                break;
            }
            else {
                f5 = f6;
                continue;
            }
            goto label_42;
        }
        List list0 = new ArrayList();
        list0.add(this.input);
        int v8 = (int)Math.floor((((double)v) - 1.0) / 2.0);
    label_42:
        for(int v9 = 1; v9 < v8 + 1; ++v9) {
            int v10;
            for(v10 = -v9; v10 < 0; v10 += arrayList0.size()) {
            }
            if(v10 >= arrayList0.size()) {
                v10 %= arrayList0.size();
            }
            list0.add(0, ((Hct)arrayList0.get(v10)));
        }
        for(int v11 = 1; v11 < v - v8; ++v11) {
            int v12;
            for(v12 = v11; v12 < 0; v12 += arrayList0.size()) {
            }
            if(v12 >= arrayList0.size()) {
                v12 %= arrayList0.size();
            }
            list0.add(((Hct)arrayList0.get(v12)));
        }
        return list0;
    }

    private Hct getColdest() {
        return (Hct)this.getHctsByTemp().get(0);
    }

    public Hct getComplement() {
        Hct hct0 = this.precomputedComplement;
        if(hct0 != null) {
            return hct0;
        }
        double f = this.getColdest().getHue();
        double f1 = (double)(((Double)this.getTempsByHct().get(this.getColdest())));
        double f2 = this.getWarmest().getHue();
        double f3 = (double)(((Double)this.getTempsByHct().get(this.getWarmest())));
        boolean z = TemperatureCache.isBetween(this.input.getHue(), f, f2);
        double f4 = z ? f2 : f;
        Hct hct1 = (Hct)this.getHctsByHue().get(((int)Math.round(this.input.getHue())));
        double f5 = this.getRelativeTemperature(this.input);
        double f6 = 1000.0;
        for(double f7 = 0.0; f7 <= 360.0; ++f7) {
            double f8 = MathUtils.sanitizeDegreesDouble(1.0 * f7 + f4);
            if(TemperatureCache.isBetween(f8, f4, (z ? f : f2))) {
                Hct hct2 = (Hct)this.getHctsByHue().get(((int)Math.round(f8)));
                double f9 = Math.abs(1.0 - f5 - (((double)(((Double)this.getTempsByHct().get(hct2)))) - f1) / (f3 - f1));
                if(f9 < f6) {
                    hct1 = hct2;
                    f6 = f9;
                }
            }
        }
        this.precomputedComplement = hct1;
        return hct1;
    }

    private List getHctsByHue() {
        List list0 = this.precomputedHctsByHue;
        if(list0 != null) {
            return list0;
        }
        ArrayList arrayList0 = new ArrayList();
        for(double f = 0.0; f <= 360.0; ++f) {
            arrayList0.add(Hct.from(f, this.input.getChroma(), this.input.getTone()));
        }
        List list1 = Collections.unmodifiableList(arrayList0);
        this.precomputedHctsByHue = list1;
        return list1;
    }

    private List getHctsByTemp() {
        List list0 = this.precomputedHctsByTemp;
        if(list0 != null) {
            return list0;
        }
        List list1 = new ArrayList(this.getHctsByHue());
        list1.add(this.input);
        Collections.sort(list1, Comparator.comparing((Hct hct0) -> ((Double)this.getTempsByHct().get(hct0)), new TemperatureCache..ExternalSyntheticLambda2()));
        this.precomputedHctsByTemp = list1;
        return list1;
    }

    public double getRelativeTemperature(Hct hct0) {
        double f = ((double)(((Double)this.getTempsByHct().get(this.getWarmest())))) - ((double)(((Double)this.getTempsByHct().get(this.getColdest()))));
        return f == 0.0 ? 0.5 : (((double)(((Double)this.getTempsByHct().get(hct0)))) - ((double)(((Double)this.getTempsByHct().get(this.getColdest()))))) / f;
    }

    private Map getTempsByHct() {
        Map map0 = this.precomputedTempsByHct;
        if(map0 != null) {
            return map0;
        }
        ArrayList arrayList0 = new ArrayList(this.getHctsByHue());
        arrayList0.add(this.input);
        Map map1 = new HashMap();
        for(Object object0: arrayList0) {
            map1.put(((Hct)object0), TemperatureCache.rawTemperature(((Hct)object0)));
        }
        this.precomputedTempsByHct = map1;
        return map1;
    }

    private Hct getWarmest() {
        return (Hct)this.getHctsByTemp().get(this.getHctsByTemp().size() - 1);
    }

    private static boolean isBetween(double f, double f1, double f2) {
        return f1 < f2 ? f1 <= f && f <= f2 : f1 <= f || f <= f2;
    }

    // 检测为 Lambda 实现
    Double lambda$getHctsByTemp$0$com-google-android-material-color-utilities-TemperatureCache(Hct hct0) [...]

    public static double rawTemperature(Hct hct0) {
        double[] arr_f = ColorUtils.labFromArgb(hct0.toInt());
        return Math.pow(Math.hypot(arr_f[1], arr_f[2]), 1.07) * 0.02 * Math.cos(Math.toRadians(MathUtils.sanitizeDegreesDouble(MathUtils.sanitizeDegreesDouble(Math.toDegrees(Math.atan2(arr_f[2], arr_f[1]))) - 50.0))) - 0.5;
    }
}

