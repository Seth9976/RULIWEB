package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

final class zzlb implements Runnable {
    final String zza;
    final String zzb;
    final Bundle zzc;
    final zzlc zzd;

    zzlb(zzlc zzlc0, String s, String s1, Bundle bundle0) {
        this.zzd = zzlc0;
        this.zza = s;
        this.zzb = "_err";
        this.zzc = bundle0;
        super();
    }

    @Override
    public final void run() {
        zzlp zzlp0 = this.zzd.zza.zzv();
        long v = this.zzd.zza.zzax().currentTimeMillis();
        zzau zzau0 = (zzau)Preconditions.checkNotNull(zzlp0.zzz(this.zza, this.zzb, this.zzc, "auto", v, false, true));
        this.zzd.zza.zzF(zzau0, this.zza);
    }
}

