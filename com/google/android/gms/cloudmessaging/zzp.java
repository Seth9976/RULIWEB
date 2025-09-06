package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzp {
    final int zza;
    final TaskCompletionSource zzb;
    final int zzc;
    final Bundle zzd;

    zzp(int v, int v1, Bundle bundle0) {
        this.zzb = new TaskCompletionSource();
        this.zza = v;
        this.zzc = v1;
        this.zzd = bundle0;
    }

    @Override
    public final String toString() {
        return "Request { what=" + this.zzc + " id=" + this.zza + " oneWay=" + this.zzb() + "}";
    }

    abstract void zza(Bundle arg1);

    abstract boolean zzb();

    final void zzc(zzq zzq0) {
        if(Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Failing " + this + " with " + zzq0);
        }
        this.zzb.setException(zzq0);
    }

    final void zzd(Object object0) {
        if(Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Finishing " + this + " with " + object0);
        }
        this.zzb.setResult(object0);
    }
}

