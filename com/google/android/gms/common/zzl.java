package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzl extends zzj {
    private static final WeakReference zza;
    private WeakReference zzb;

    static {
        zzl.zza = new WeakReference(null);
    }

    zzl(byte[] arr_b) {
        super(arr_b);
        this.zzb = zzl.zza;
    }

    protected abstract byte[] zzb();

    @Override  // com.google.android.gms.common.zzj
    final byte[] zzf() {
        synchronized(this) {
            byte[] arr_b = (byte[])this.zzb.get();
            if(arr_b == null) {
                arr_b = this.zzb();
                this.zzb = new WeakReference(arr_b);
            }
            return arr_b;
        }
    }
}

