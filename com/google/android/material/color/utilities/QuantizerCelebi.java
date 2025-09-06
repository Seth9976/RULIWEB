package com.google.android.material.color.utilities;

import java.util.Map;
import java.util.Set;

public final class QuantizerCelebi {
    public static Map quantize(int[] arr_v, int v) {
        Set set0 = new QuantizerWu().quantize(arr_v, v).colorToCount.keySet();
        int[] arr_v1 = new int[set0.size()];
        int v1 = 0;
        for(Object object0: set0) {
            arr_v1[v1] = (int)(((Integer)object0));
            ++v1;
        }
        return QuantizerWsmeans.quantize(arr_v, arr_v1, v);
    }
}

