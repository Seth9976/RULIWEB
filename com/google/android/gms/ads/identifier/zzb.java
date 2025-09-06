package com.google.android.gms.ads.identifier;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzb extends Thread {
    final CountDownLatch zza;
    boolean zzb;
    private final WeakReference zzc;
    private final long zzd;

    public zzb(AdvertisingIdClient advertisingIdClient0, long v) {
        this.zzc = new WeakReference(advertisingIdClient0);
        this.zzd = v;
        this.zza = new CountDownLatch(1);
        this.zzb = false;
        this.start();
    }

    @Override
    public final void run() {
        try {
            if(!this.zza.await(this.zzd, TimeUnit.MILLISECONDS)) {
                this.zza();
            }
        }
        catch(InterruptedException unused_ex) {
            this.zza();
        }
    }

    private final void zza() {
        AdvertisingIdClient advertisingIdClient0 = (AdvertisingIdClient)this.zzc.get();
        if(advertisingIdClient0 != null) {
            advertisingIdClient0.zza();
            this.zzb = true;
        }
    }
}

