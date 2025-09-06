package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzz implements Runnable {
    final zzw zza;
    final Callable zzb;

    zzz(zzw zzw0, Callable callable0) {
        this.zza = zzw0;
        this.zzb = callable0;
        super();
    }

    @Override
    public final void run() {
        try {
            Object object0 = this.zzb.call();
            this.zza.zzb(object0);
        }
        catch(Exception exception0) {
            this.zza.zza(exception0);
        }
        catch(Throwable throwable0) {
            RuntimeException runtimeException0 = new RuntimeException(throwable0);
            this.zza.zza(runtimeException0);
        }
    }
}

