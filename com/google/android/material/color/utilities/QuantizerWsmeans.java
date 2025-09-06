package com.google.android.material.color.utilities;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public final class QuantizerWsmeans {
    static final class Distance implements Comparable {
        double distance;
        int index;

        Distance() {
            this.index = -1;
            this.distance = -1.0;
        }

        public int compareTo(Distance quantizerWsmeans$Distance0) {
            return this.distance.compareTo(quantizerWsmeans$Distance0.distance);
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((Distance)object0));
        }
    }

    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0;

    public static Map quantize(int[] arr_v, int[] arr_v1, int v) {
        double[] arr_f6;
        double[] arr_f5;
        int v28;
        Map map0;
        Random random0 = new Random(272008L);
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        double[][] arr2_f = new double[arr_v.length][];
        int[] arr_v2 = new int[arr_v.length];
        PointProviderLab pointProviderLab0 = new PointProviderLab();
        int v2 = 0;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            int v3 = arr_v[v1];
            Integer integer0 = (Integer)linkedHashMap0.get(v3);
            if(integer0 == null) {
                arr2_f[v2] = pointProviderLab0.fromInt(v3);
                arr_v2[v2] = v3;
                ++v2;
                linkedHashMap0.put(v3, 1);
            }
            else {
                linkedHashMap0.put(v3, ((int)(((int)integer0) + 1)));
            }
        }
        int[] arr_v3 = new int[v2];
        for(int v4 = 0; v4 < v2; ++v4) {
            arr_v3[v4] = (int)(((Integer)linkedHashMap0.get(((int)arr_v2[v4]))));
        }
        int v5 = arr_v1.length == 0 ? Math.min(v, v2) : Math.min(Math.min(v, v2), arr_v1.length);
        double[][] arr2_f1 = new double[v5][];
        int v7 = 0;
        for(int v6 = 0; v6 < arr_v1.length; ++v6) {
            arr2_f1[v6] = pointProviderLab0.fromInt(arr_v1[v6]);
            ++v7;
        }
        int v8 = v5 - v7;
        if(v8 > 0) {
            for(int v9 = 0; v9 < v8; ++v9) {
            }
        }
        int[] arr_v4 = new int[v2];
        for(int v10 = 0; v10 < v2; ++v10) {
            arr_v4[v10] = random0.nextInt(v5);
        }
        int[][] arr2_v = new int[v5][];
        for(int v11 = 0; v11 < v5; ++v11) {
            arr2_v[v11] = new int[v5];
        }
        Distance[][] arr2_quantizerWsmeans$Distance = new Distance[v5][];
        for(int v12 = 0; v12 < v5; ++v12) {
            arr2_quantizerWsmeans$Distance[v12] = new Distance[v5];
            for(int v13 = 0; v13 < v5; ++v13) {
                Distance[] arr_quantizerWsmeans$Distance = arr2_quantizerWsmeans$Distance[v12];
                arr_quantizerWsmeans$Distance[v13] = new Distance();
            }
        }
        int[] arr_v5 = new int[v5];
        int v14 = 0;
        while(v14 < 10) {
            for(int v15 = 0; v15 < v5; ++v15) {
                for(int v16 = v15 + 1; v16 < v5; ++v16) {
                    double f = pointProviderLab0.distance(arr2_f1[v15], arr2_f1[v16]);
                    arr2_quantizerWsmeans$Distance[v16][v15].distance = f;
                    arr2_quantizerWsmeans$Distance[v16][v15].index = v15;
                    arr2_quantizerWsmeans$Distance[v15][v16].distance = f;
                    arr2_quantizerWsmeans$Distance[v15][v16].index = v16;
                }
                Arrays.sort(arr2_quantizerWsmeans$Distance[v15]);
                for(int v17 = 0; v17 < v5; ++v17) {
                    arr2_v[v15][v17] = arr2_quantizerWsmeans$Distance[v15][v17].index;
                }
            }
            int v19 = 0;
            for(int v18 = 0; v18 < v2; ++v18) {
                double[] arr_f = arr2_f[v18];
                int v20 = arr_v4[v18];
                double f1 = pointProviderLab0.distance(arr_f, arr2_f1[v20]);
                double f2 = f1;
                int v21 = -1;
                for(int v22 = 0; v22 < v5; ++v22) {
                    if(arr2_quantizerWsmeans$Distance[v20][v22].distance < 4.0 * f1) {
                        double f3 = pointProviderLab0.distance(arr_f, arr2_f1[v22]);
                        if(f3 < f2) {
                            f2 = f3;
                            v21 = v22;
                        }
                    }
                }
                if(v21 != -1 && Math.abs(Math.sqrt(f2) - Math.sqrt(f1)) > 3.0) {
                    ++v19;
                    arr_v4[v18] = v21;
                }
            }
            if(v19 == 0 && v14 != 0) {
                map0 = new LinkedHashMap();
                v28 = 0;
            }
            else {
                double[] arr_f1 = new double[v5];
                double[] arr_f2 = new double[v5];
                double[] arr_f3 = new double[v5];
                Arrays.fill(arr_v5, 0);
                for(int v23 = 0; v23 < v2; ++v23) {
                    int v24 = arr_v4[v23];
                    double[] arr_f4 = arr2_f[v23];
                    int v25 = arr_v3[v23];
                    arr_v5[v24] += v25;
                    arr_f1[v24] += arr_f4[0] * ((double)v25);
                    arr_f2[v24] += arr_f4[1] * ((double)v25);
                    arr_f3[v24] += arr_f4[2] * ((double)v25);
                }
                int v26 = 0;
                while(v26 < v5) {
                    int v27 = arr_v5[v26];
                    if(v27 == 0) {
                        arr2_f1[v26] = new double[]{0.0, 0.0, 0.0};
                        arr_f5 = arr_f1;
                        arr_f6 = arr_f2;
                    }
                    else {
                        arr_f5 = arr_f1;
                        arr_f6 = arr_f2;
                        double f4 = arr_f1[v26] / ((double)v27);
                        double f5 = arr_f6[v26] / ((double)v27);
                        double f6 = arr_f3[v26] / ((double)v27);
                        double[] arr_f7 = arr2_f1[v26];
                        arr_f7[0] = f4;
                        arr_f7[1] = f5;
                        arr_f7[2] = f6;
                    }
                    ++v26;
                    arr_f1 = arr_f5;
                    arr_f2 = arr_f6;
                }
                ++v14;
                continue;
            }
            goto label_152;
        }
        map0 = new LinkedHashMap();
        v28 = 0;
    label_152:
        while(v28 < v5) {
            int v29 = arr_v5[v28];
            if(v29 != 0) {
                int v30 = pointProviderLab0.toInt(arr2_f1[v28]);
                if(!map0.containsKey(v30)) {
                    map0.put(v30, v29);
                }
            }
            ++v28;
        }
        return map0;
    }
}

