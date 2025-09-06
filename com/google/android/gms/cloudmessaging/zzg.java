package com.google.android.gms.cloudmessaging;

public final class zzg implements Runnable {
    public final zzm zza;

    public zzg(zzm zzm0) {
        this.zza = zzm0;
    }

    @Override
    public final void run() {
        this.zza.zza(2, "Service disconnected");
    }
}

