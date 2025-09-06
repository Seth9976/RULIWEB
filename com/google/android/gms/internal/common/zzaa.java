package com.google.android.gms.internal.common;

import java.util.Arrays;

class zzaa extends zzab {
    Object[] zza;
    int zzb;
    boolean zzc;

    zzaa(int v) {
        this.zza = new Object[4];
        this.zzb = 0;
    }

    public final zzaa zza(Object object0) {
        object0.getClass();
        int v = this.zzb;
        Object[] arr_object = this.zza;
        if(arr_object.length < v + 1) {
            int v1 = arr_object.length + (arr_object.length >> 1) + 1;
            if(v1 < v + 1) {
                int v2 = Integer.highestOneBit(v);
                v1 = v2 + v2;
            }
            if(v1 < 0) {
                v1 = 0x7FFFFFFF;
            }
            this.zza = Arrays.copyOf(arr_object, v1);
            this.zzc = false;
        }
        else if(this.zzc) {
            this.zza = (Object[])arr_object.clone();
            this.zzc = false;
        }
        int v3 = this.zzb;
        this.zzb = v3 + 1;
        this.zza[v3] = object0;
        return this;
    }
}

