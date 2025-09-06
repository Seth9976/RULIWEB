package com.google.android.gms.common.data;

import java.util.ArrayList;

public final class FreezableUtils {
    public static ArrayList freeze(ArrayList arrayList0) {
        ArrayList arrayList1 = new ArrayList(arrayList0.size());
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList1.add(((Freezable)arrayList0.get(v1)).freeze());
        }
        return arrayList1;
    }

    public static ArrayList freeze(Freezable[] arr_freezable) {
        ArrayList arrayList0 = new ArrayList(arr_freezable.length);
        for(int v = 0; v < arr_freezable.length; ++v) {
            arrayList0.add(arr_freezable[v].freeze());
        }
        return arrayList0;
    }

    public static ArrayList freezeIterable(Iterable iterable0) {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: iterable0) {
            arrayList0.add(((Freezable)object0).freeze());
        }
        return arrayList0;
    }
}

