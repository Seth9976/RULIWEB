package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zza implements Runnable {
    final LifecycleCallback zza;
    final String zzb;
    final zzb zzc;

    zza(zzb zzb0, LifecycleCallback lifecycleCallback0, String s) {
        this.zzc = zzb0;
        this.zza = lifecycleCallback0;
        this.zzb = s;
        super();
    }

    @Override
    public final void run() {
        zzb zzb0 = this.zzc;
        if(zzb.zza(zzb0) > 0) {
            Bundle bundle0 = zzb.zzb(zzb0) == null ? null : zzb.zzb(zzb0).getBundle(this.zzb);
            this.zza.onCreate(bundle0);
        }
        if(zzb.zza(this.zzc) >= 2) {
            this.zza.onStart();
        }
        if(zzb.zza(this.zzc) >= 3) {
            this.zza.onResume();
        }
        if(zzb.zza(this.zzc) >= 4) {
            this.zza.onStop();
        }
        if(zzb.zza(this.zzc) >= 5) {
            this.zza.onDestroy();
        }
    }
}

