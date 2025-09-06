package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

public final class QuantizerWu implements Quantizer {
    static final class Box {
        int b0;
        int b1;
        int g0;
        int g1;
        int r0;
        int r1;
        int vol;

        private Box() {
            this.r0 = 0;
            this.r1 = 0;
            this.g0 = 0;
            this.g1 = 0;
            this.b0 = 0;
            this.b1 = 0;
            this.vol = 0;
        }

        Box(com.google.android.material.color.utilities.QuantizerWu.1 quantizerWu$10) {
        }
    }

    static final class CreateBoxesResult {
        int resultCount;

        CreateBoxesResult(int v, int v1) {
            this.resultCount = v1;
        }
    }

    static enum Direction {
        RED,
        GREEN,
        BLUE;

    }

    static final class MaximizeResult {
        int cutLocation;
        double maximum;

        MaximizeResult(int v, double f) {
            this.cutLocation = v;
            this.maximum = f;
        }
    }

    private static final int INDEX_BITS = 5;
    private static final int INDEX_COUNT = 33;
    private static final int TOTAL_SIZE = 0x8C61;
    Box[] cubes;
    double[] moments;
    int[] momentsB;
    int[] momentsG;
    int[] momentsR;
    int[] weights;

    static int bottom(Box quantizerWu$Box0, Direction quantizerWu$Direction0, int[] arr_v) {
        switch(com.google.android.material.color.utilities.QuantizerWu.1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[quantizerWu$Direction0.ordinal()]) {
            case 1: {
                return -arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b1] + arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] + arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b1] - arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0];
            }
            case 2: {
                return -arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b1] + arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0] + arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b1] - arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0];
            }
            case 3: {
                return -arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] + arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0] + arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] - arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0];
            }
            default: {
                throw new IllegalArgumentException("unexpected direction " + quantizerWu$Direction0);
            }
        }
    }

    void constructHistogram(Map map0) {
        this.weights = new int[0x8C61];
        this.momentsR = new int[0x8C61];
        this.momentsG = new int[0x8C61];
        this.momentsB = new int[0x8C61];
        this.moments = new double[0x8C61];
        for(Object object0: map0.entrySet()) {
            int v = (int)(((Integer)((Map.Entry)object0).getKey()));
            int v1 = (int)(((Integer)((Map.Entry)object0).getValue()));
            int v2 = v >> 16 & 0xFF;
            int v3 = v >> 8 & 0xFF;
            int v4 = QuantizerWu.getIndex((v2 >> 3) + 1, (v3 >> 3) + 1, ((v & 0xFF) >> 3) + 1);
            this.weights[v4] += v1;
            this.momentsR[v4] += v2 * v1;
            this.momentsG[v4] += v3 * v1;
            this.momentsB[v4] += (v & 0xFF) * v1;
            this.moments[v4] += (double)(v1 * (v2 * v2 + v3 * v3 + (v & 0xFF) * (v & 0xFF)));
        }
    }

    CreateBoxesResult createBoxes(int v) {
        this.cubes = new Box[v];
        for(int v1 = 0; v1 < v; ++v1) {
            Box[] arr_quantizerWu$Box = this.cubes;
            arr_quantizerWu$Box[v1] = new Box(null);
        }
        double[] arr_f = new double[v];
        this.cubes[0].r1 = 0x20;
        this.cubes[0].g1 = 0x20;
        this.cubes[0].b1 = 0x20;
        int v2 = 1;
        for(int v3 = 0; v2 < v; v3 = v4) {
            if(this.cut(this.cubes[v3], this.cubes[v2]).booleanValue()) {
                arr_f[v3] = this.cubes[v3].vol <= 1 ? 0.0 : this.variance(this.cubes[v3]);
                arr_f[v2] = this.cubes[v2].vol <= 1 ? 0.0 : this.variance(this.cubes[v2]);
            }
            else {
                arr_f[v3] = 0.0;
                --v2;
            }
            double f = arr_f[0];
            int v4 = 0;
            for(int v5 = 1; v5 <= v2; ++v5) {
                double f1 = arr_f[v5];
                if(f1 > f) {
                    v4 = v5;
                    f = f1;
                }
            }
            if(f <= 0.0) {
                return new CreateBoxesResult(v, v2 + 1);
            }
            ++v2;
        }
        return new CreateBoxesResult(v, v);
    }

    void createMoments() {
        for(int v = 1; v < 33; ++v) {
            int[] arr_v = new int[33];
            int[] arr_v1 = new int[33];
            int[] arr_v2 = new int[33];
            int[] arr_v3 = new int[33];
            double[] arr_f = new double[33];
            for(int v1 = 1; v1 < 33; ++v1) {
                int v2 = 0;
                double f = 0.0;
                int v3 = 0;
                int v4 = 0;
                int v5 = 0;
                for(int v6 = 1; v6 < 33; ++v6) {
                    int v7 = 1089 * v + 33 * v1 + v6;
                    v2 += this.weights[v7];
                    v3 += this.momentsR[v7];
                    v4 += this.momentsG[v7];
                    v5 += this.momentsB[v7];
                    f += this.moments[v7];
                    arr_v[v6] += v2;
                    arr_v1[v6] += v3;
                    arr_v2[v6] += v4;
                    arr_v3[v6] += v5;
                    arr_f[v6] += f;
                    int v8 = 1089 * (v - 1) + 33 * v1 + v6;
                    this.weights[v7] = this.weights[v8] + arr_v[v6];
                    this.momentsR[v7] = this.momentsR[v8] + arr_v1[v6];
                    this.momentsG[v7] = this.momentsG[v8] + arr_v2[v6];
                    this.momentsB[v7] = this.momentsB[v8] + arr_v3[v6];
                    this.moments[v7] = this.moments[v8] + arr_f[v6];
                }
            }
        }
    }

    List createResult(int v) {
        List list0 = new ArrayList();
        for(int v1 = 0; v1 < v; ++v1) {
            Box quantizerWu$Box0 = this.cubes[v1];
            int v2 = QuantizerWu.volume(quantizerWu$Box0, this.weights);
            if(v2 > 0) {
                int v3 = QuantizerWu.volume(quantizerWu$Box0, this.momentsR);
                int v4 = QuantizerWu.volume(quantizerWu$Box0, this.momentsG);
                list0.add(((int)(QuantizerWu.volume(quantizerWu$Box0, this.momentsB) / v2 & 0xFF | ((v3 / v2 & 0xFF) << 16 | 0xFF000000 | (v4 / v2 & 0xFF) << 8))));
            }
        }
        return list0;
    }

    Boolean cut(Box quantizerWu$Box0, Box quantizerWu$Box1) {
        Direction quantizerWu$Direction0;
        int v = QuantizerWu.volume(quantizerWu$Box0, this.momentsR);
        int v1 = QuantizerWu.volume(quantizerWu$Box0, this.momentsG);
        int v2 = QuantizerWu.volume(quantizerWu$Box0, this.momentsB);
        int v3 = QuantizerWu.volume(quantizerWu$Box0, this.weights);
        MaximizeResult quantizerWu$MaximizeResult0 = this.maximize(quantizerWu$Box0, Direction.RED, quantizerWu$Box0.r0 + 1, quantizerWu$Box0.r1, v, v1, v2, v3);
        MaximizeResult quantizerWu$MaximizeResult1 = this.maximize(quantizerWu$Box0, Direction.GREEN, quantizerWu$Box0.g0 + 1, quantizerWu$Box0.g1, v, v1, v2, v3);
        MaximizeResult quantizerWu$MaximizeResult2 = this.maximize(quantizerWu$Box0, Direction.BLUE, quantizerWu$Box0.b0 + 1, quantizerWu$Box0.b1, v, v1, v2, v3);
        double f = quantizerWu$MaximizeResult0.maximum;
        double f1 = quantizerWu$MaximizeResult1.maximum;
        double f2 = quantizerWu$MaximizeResult2.maximum;
        if(f < f1 || f < f2) {
            quantizerWu$Direction0 = f1 < f || f1 < f2 ? Direction.BLUE : Direction.GREEN;
        }
        else {
            if(quantizerWu$MaximizeResult0.cutLocation < 0) {
                return false;
            }
            quantizerWu$Direction0 = Direction.RED;
        }
        quantizerWu$Box1.r1 = quantizerWu$Box0.r1;
        quantizerWu$Box1.g1 = quantizerWu$Box0.g1;
        quantizerWu$Box1.b1 = quantizerWu$Box0.b1;
        switch(com.google.android.material.color.utilities.QuantizerWu.1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[quantizerWu$Direction0.ordinal()]) {
            case 1: {
                quantizerWu$Box0.r1 = quantizerWu$MaximizeResult0.cutLocation;
                quantizerWu$Box1.r0 = quantizerWu$Box0.r1;
                quantizerWu$Box1.g0 = quantizerWu$Box0.g0;
                quantizerWu$Box1.b0 = quantizerWu$Box0.b0;
                break;
            }
            case 2: {
                quantizerWu$Box0.g1 = quantizerWu$MaximizeResult1.cutLocation;
                quantizerWu$Box1.r0 = quantizerWu$Box0.r0;
                quantizerWu$Box1.g0 = quantizerWu$Box0.g1;
                quantizerWu$Box1.b0 = quantizerWu$Box0.b0;
                break;
            }
            case 3: {
                quantizerWu$Box0.b1 = quantizerWu$MaximizeResult2.cutLocation;
                quantizerWu$Box1.r0 = quantizerWu$Box0.r0;
                quantizerWu$Box1.g0 = quantizerWu$Box0.g0;
                quantizerWu$Box1.b0 = quantizerWu$Box0.b1;
            }
        }
        quantizerWu$Box0.vol = (quantizerWu$Box0.r1 - quantizerWu$Box0.r0) * (quantizerWu$Box0.g1 - quantizerWu$Box0.g0) * (quantizerWu$Box0.b1 - quantizerWu$Box0.b0);
        quantizerWu$Box1.vol = (quantizerWu$Box1.r1 - quantizerWu$Box1.r0) * (quantizerWu$Box1.g1 - quantizerWu$Box1.g0) * (quantizerWu$Box1.b1 - quantizerWu$Box1.b0);
        return true;
    }

    // 去混淆评级： 低(20)
    static int getIndex(int v, int v1, int v2) [...] // Inlined contents

    MaximizeResult maximize(Box quantizerWu$Box0, Direction quantizerWu$Direction0, int v, int v1, int v2, int v3, int v4, int v5) {
        int v6 = QuantizerWu.bottom(quantizerWu$Box0, quantizerWu$Direction0, this.momentsR);
        int v7 = QuantizerWu.bottom(quantizerWu$Box0, quantizerWu$Direction0, this.momentsG);
        int v8 = QuantizerWu.bottom(quantizerWu$Box0, quantizerWu$Direction0, this.momentsB);
        int v9 = QuantizerWu.bottom(quantizerWu$Box0, quantizerWu$Direction0, this.weights);
        double f = 0.0;
        int v10 = -1;
        for(int v11 = v; v11 < v1; ++v11) {
            int v12 = QuantizerWu.top(quantizerWu$Box0, quantizerWu$Direction0, v11, this.momentsR) + v6;
            int v13 = QuantizerWu.top(quantizerWu$Box0, quantizerWu$Direction0, v11, this.momentsG) + v7;
            int v14 = QuantizerWu.top(quantizerWu$Box0, quantizerWu$Direction0, v11, this.momentsB) + v8;
            int v15 = QuantizerWu.top(quantizerWu$Box0, quantizerWu$Direction0, v11, this.weights) + v9;
            if(v15 != 0) {
                int v16 = v5 - v15;
                if(v16 != 0) {
                    double f1 = ((double)(v12 * v12 + v13 * v13 + v14 * v14)) / ((double)v15) + ((double)((v2 - v12) * (v2 - v12) + (v3 - v13) * (v3 - v13) + (v4 - v14) * (v4 - v14))) / ((double)v16);
                    if(f1 > f) {
                        f = f1;
                        v10 = v11;
                    }
                }
            }
        }
        return new MaximizeResult(v10, f);
    }

    @Override  // com.google.android.material.color.utilities.Quantizer
    public QuantizerResult quantize(int[] arr_v, int v) {
        this.constructHistogram(new QuantizerMap().quantize(arr_v, v).colorToCount);
        this.createMoments();
        List list0 = this.createResult(this.createBoxes(v).resultCount);
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        for(Object object0: list0) {
            ((Integer)object0).intValue();
            linkedHashMap0.put(((Integer)object0), 0);
        }
        return new QuantizerResult(linkedHashMap0);
    }

    static int top(Box quantizerWu$Box0, Direction quantizerWu$Direction0, int v, int[] arr_v) {
        switch(com.google.android.material.color.utilities.QuantizerWu.1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[quantizerWu$Direction0.ordinal()]) {
            case 1: {
                return arr_v[1089 * v + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b1] - arr_v[1089 * v + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] - arr_v[1089 * v + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b1] + arr_v[1089 * v + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0];
            }
            case 2: {
                return arr_v[1089 * quantizerWu$Box0.r1 + 33 * v + quantizerWu$Box0.b1] - arr_v[1089 * quantizerWu$Box0.r1 + 33 * v + quantizerWu$Box0.b0] - arr_v[1089 * quantizerWu$Box0.r0 + 33 * v + quantizerWu$Box0.b1] + arr_v[1089 * quantizerWu$Box0.r0 + 33 * v + quantizerWu$Box0.b0];
            }
            case 3: {
                return arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g1 + v] - arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + v] - arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + v] + arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + v];
            }
            default: {
                throw new IllegalArgumentException("unexpected direction " + quantizerWu$Direction0);
            }
        }
    }

    double variance(Box quantizerWu$Box0) {
        int v = QuantizerWu.volume(quantizerWu$Box0, this.momentsR);
        int v1 = QuantizerWu.volume(quantizerWu$Box0, this.momentsG);
        int v2 = QuantizerWu.volume(quantizerWu$Box0, this.momentsB);
        return this.moments[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b1] - this.moments[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] - this.moments[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b1] + this.moments[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0] - this.moments[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b1] + this.moments[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] + this.moments[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b1] - this.moments[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0] - ((double)(v * v + v1 * v1 + v2 * v2)) / ((double)QuantizerWu.volume(quantizerWu$Box0, this.weights));
    }

    static int volume(Box quantizerWu$Box0, int[] arr_v) {
        return arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b1] - arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] - arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b1] + arr_v[1089 * quantizerWu$Box0.r1 + 33 * quantizerWu$Box0.g0 + quantizerWu$Box0.b0] - arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b1] + arr_v[1089 * quantizerWu$Box0.r0 + 33 * quantizerWu$Box0.g1 + quantizerWu$Box0.b0] + arr_v[QuantizerWu.getIndex(quantizerWu$Box0.r0, quantizerWu$Box0.g0, quantizerWu$Box0.b1)] - arr_v[QuantizerWu.getIndex(quantizerWu$Box0.r0, quantizerWu$Box0.g0, quantizerWu$Box0.b0)];
    }

    class com.google.android.material.color.utilities.QuantizerWu.1 {
        static final int[] $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction;

        static {
            int[] arr_v = new int[Direction.values().length];
            com.google.android.material.color.utilities.QuantizerWu.1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction = arr_v;
            try {
                arr_v[Direction.RED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.android.material.color.utilities.QuantizerWu.1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[Direction.GREEN.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.android.material.color.utilities.QuantizerWu.1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[Direction.BLUE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

