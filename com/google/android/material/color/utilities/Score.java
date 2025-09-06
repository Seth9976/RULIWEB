package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

public final class Score {
    static class ScoredComparator implements Comparator {
        public int compare(ScoredHCT score$ScoredHCT0, ScoredHCT score$ScoredHCT1) {
            return Double.compare(score$ScoredHCT1.score, score$ScoredHCT0.score);
        }

        @Override
        public int compare(Object object0, Object object1) {
            return this.compare(((ScoredHCT)object0), ((ScoredHCT)object1));
        }
    }

    static class ScoredHCT {
        public final Hct hct;
        public final double score;

        public ScoredHCT(Hct hct0, double f) {
            this.hct = hct0;
            this.score = f;
        }
    }

    private static final int BLUE_500 = 0xFF4285F4;
    private static final double CUTOFF_CHROMA = 5.0;
    private static final double CUTOFF_EXCITED_PROPORTION = 0.01;
    private static final int MAX_COLOR_COUNT = 4;
    private static final double TARGET_CHROMA = 48.0;
    private static final double WEIGHT_CHROMA_ABOVE = 0.3;
    private static final double WEIGHT_CHROMA_BELOW = 0.1;
    private static final double WEIGHT_PROPORTION = 0.7;

    public static List score(Map map0) {
        return Score.score(map0, 4, 0xFF4285F4, true);
    }

    public static List score(Map map0, int v) {
        return Score.score(map0, v, 0xFF4285F4, true);
    }

    public static List score(Map map0, int v, int v1) {
        return Score.score(map0, v, v1, true);
    }

    public static List score(Map map0, int v, int v1, boolean z) {
        ArrayList arrayList0 = new ArrayList();
        int[] arr_v = new int[360];
        double f = 0.0;
        for(Object object0: map0.entrySet()) {
            Hct hct0 = Hct.fromInt(((int)(((Integer)((Map.Entry)object0).getKey()))));
            arrayList0.add(hct0);
            int v2 = (int)Math.floor(hct0.getHue());
            int v3 = (int)(((Integer)((Map.Entry)object0).getValue()));
            arr_v[v2] += v3;
            f += (double)v3;
        }
        double[] arr_f = new double[360];
        for(int v4 = 0; v4 < 360; ++v4) {
            double f1 = ((double)arr_v[v4]) / f;
            for(int v5 = v4 - 14; v5 < v4 + 16; ++v5) {
                int v6 = MathUtils.sanitizeDegreesInt(v5);
                arr_f[v6] += f1;
            }
        }
        ArrayList arrayList1 = new ArrayList();
        for(Object object1: arrayList0) {
            Hct hct1 = (Hct)object1;
            double f2 = arr_f[MathUtils.sanitizeDegreesInt(((int)Math.round(hct1.getHue())))];
            if(!z || hct1.getChroma() >= 5.0 && f2 > 0.01) {
                arrayList1.add(new ScoredHCT(hct1, f2 * 70.0 + (hct1.getChroma() - 48.0) * (hct1.getChroma() < 48.0 ? 0.1 : 0.3)));
            }
        }
        Collections.sort(arrayList1, new ScoredComparator());
        ArrayList arrayList2 = new ArrayList();
        int v7 = 90;
        while(v7 >= 15) {
            arrayList2.clear();
            for(Object object2: arrayList1) {
                Hct hct2 = ((ScoredHCT)object2).hct;
                for(Object object3: arrayList2) {
                    if(MathUtils.differenceDegrees(hct2.getHue(), ((Hct)object3).getHue()) >= ((double)v7)) {
                        continue;
                    }
                    goto label_49;
                }
                arrayList2.add(hct2);
            label_49:
                if(arrayList2.size() >= v) {
                    break;
                }
            }
            if(arrayList2.size() >= v) {
                break;
            }
            --v7;
        }
        List list0 = new ArrayList();
        if(arrayList2.isEmpty()) {
            list0.add(v1);
            return list0;
        }
        for(Object object4: arrayList2) {
            list0.add(((Hct)object4).toInt());
        }
        return list0;
    }
}

