package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
import java.util.HashMap;

public class KeyCache {
    HashMap mMap;

    public KeyCache() {
        this.mMap = new HashMap();
    }

    public float getFloatValue(Object object0, String s, int v) {
        if(!this.mMap.containsKey(object0)) {
            return NaNf;
        }
        HashMap hashMap0 = (HashMap)this.mMap.get(object0);
        if(hashMap0 != null && hashMap0.containsKey(s)) {
            float[] arr_f = (float[])hashMap0.get(s);
            if(arr_f == null) {
                return NaNf;
            }
            return arr_f.length <= v ? NaNf : arr_f[v];
        }
        return NaNf;
    }

    public void setFloatValue(Object object0, String s, int v, float f) {
        if(!this.mMap.containsKey(object0)) {
            HashMap hashMap0 = new HashMap();
            float[] arr_f = new float[v + 1];
            arr_f[v] = f;
            hashMap0.put(s, arr_f);
            this.mMap.put(object0, hashMap0);
            return;
        }
        HashMap hashMap1 = (HashMap)this.mMap.get(object0);
        if(hashMap1 == null) {
            hashMap1 = new HashMap();
        }
        if(!hashMap1.containsKey(s)) {
            float[] arr_f1 = new float[v + 1];
            arr_f1[v] = f;
            hashMap1.put(s, arr_f1);
            this.mMap.put(object0, hashMap1);
            return;
        }
        float[] arr_f2 = (float[])hashMap1.get(s);
        if(arr_f2 == null) {
            arr_f2 = new float[0];
        }
        if(arr_f2.length <= v) {
            arr_f2 = Arrays.copyOf(arr_f2, v + 1);
        }
        arr_f2[v] = f;
        hashMap1.put(s, arr_f2);
    }
}

