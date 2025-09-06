package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

final class zzlc implements zzlo {
    final zzlh zza;

    zzlc(zzlh zzlh0) {
        this.zza = zzlh0;
        super();
    }

    @Override  // com.google.android.gms.measurement.internal.zzlo
    public final void zza(String s, String s1, Bundle bundle0) {
        if(TextUtils.isEmpty(s)) {
            zzlh zzlh0 = this.zza;
            if(zzlh.zzo(zzlh0) != null) {
                zzlh.zzo(zzlh0).zzaA().zzd().zzb("AppId not known when logging event", "_err");
            }
            return;
        }
        this.zza.zzaB().zzp(new zzlb(this, s, "_err", bundle0));
    }
}

