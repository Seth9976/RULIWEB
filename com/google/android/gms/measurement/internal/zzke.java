package com.google.android.gms.measurement.internal;

final class zzke implements Runnable {
    final zzlh zza;
    final Runnable zzb;

    zzke(zzkg zzkg0, zzlh zzlh0, Runnable runnable0) {
        this.zza = zzlh0;
        this.zzb = runnable0;
        super();
    }

    @Override
    public final void run() {
        this.zza.zzA();
        this.zza.zzz(this.zzb);
        this.zza.zzX();
    }
}

