package com.google.android.gms.internal.measurement;

import java.util.Arrays;

public final class zzix extends zziu {
    public zzix() {
        super(4);
    }

    public final zzix zza(Object[] arr_object) {
        zzjd.zzb(arr_object, 15);
        int v = this.zzb;
        Object[] arr_object1 = this.zza;
        if(arr_object1.length < v + 15) {
            int v1 = arr_object1.length + (arr_object1.length >> 1) + 1;
            if(v1 < v + 15) {
                int v2 = Integer.highestOneBit(v + 14);
                v1 = v2 + v2;
            }
            if(v1 < 0) {
                v1 = 0x7FFFFFFF;
            }
            this.zza = Arrays.copyOf(arr_object1, v1);
            this.zzc = false;
        }
        else if(this.zzc) {
            this.zza = (Object[])arr_object1.clone();
            this.zzc = false;
        }
        System.arraycopy(arr_object, 0, this.zza, this.zzb, 15);
        this.zzb += 15;
        return this;
    }

    public final zzja zzb() {
        this.zzc = true;
        return zzja.zzg(this.zza, this.zzb);
    }
}

