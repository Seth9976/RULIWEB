package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;

final class zzbr extends zzbd {
    final zzbs zza;

    zzbr(zzbs zzbs0) {
        this.zza = zzbs0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzbd
    public final void zzc(String s) {
        if(s != null) {
            zzbv zzbv0 = new zzbv(s);
            this.zza.setResult(zzbv0);
            return;
        }
        zzbv zzbv1 = new zzbv(new Status(3006));
        this.zza.setResult(zzbv1);
    }
}

