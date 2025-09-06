package com.google.android.gms.tasks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzad implements zzae {
    private final CountDownLatch zza;

    private zzad() {
        this.zza = new CountDownLatch(1);
    }

    zzad(zzac zzac0) {
        this.zza = new CountDownLatch(1);
    }

    @Override  // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.zza.countDown();
    }

    @Override  // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exception0) {
        this.zza.countDown();
    }

    @Override  // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object object0) {
        this.zza.countDown();
    }

    public final void zza() throws InterruptedException {
        this.zza.await();
    }

    public final boolean zzb(long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.zza.await(v, timeUnit0);
    }
}

