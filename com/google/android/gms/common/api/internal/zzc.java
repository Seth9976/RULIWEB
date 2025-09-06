package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzc implements Runnable {
    final LifecycleCallback zza;
    final String zzb;
    final zzd zzc;

    zzc(zzd zzd0, LifecycleCallback lifecycleCallback0, String s) {
        this.zzc = zzd0;
        this.zza = lifecycleCallback0;
        this.zzb = s;
        super();
    }

    @Override
    public final void run() {
        zzd zzd0 = this.zzc;
        if(zzd.zza(zzd0) > 0) {
            Bundle bundle0 = zzd.zzb(zzd0) == null ? null : zzd.zzb(zzd0).getBundle(this.zzb);
            this.zza.onCreate(bundle0);
        }
        if(zzd.zza(this.zzc) >= 2) {
            this.zza.onStart();
        }
        if(zzd.zza(this.zzc) >= 3) {
            this.zza.onResume();
        }
        if(zzd.zza(this.zzc) >= 4) {
            this.zza.onStop();
        }
        if(zzd.zza(this.zzc) >= 5) {
            this.zza.onDestroy();
        }
    }
}

