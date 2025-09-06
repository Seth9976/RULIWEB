package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final class zzge implements Runnable {
    public final zzgv zza;
    public final String zzb;
    public final Bundle zzc;

    public zzge(zzgv zzgv0, String s, Bundle bundle0) {
        this.zza = zzgv0;
        this.zzb = s;
        this.zzc = bundle0;
    }

    @Override
    public final void run() {
        this.zza.zzw(this.zzb, this.zzc);
    }
}

