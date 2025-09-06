package com.google.android.gms.cloudmessaging;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.cloudmessaging.zzf;

final class zzaa extends zzf {
    final Rpc zza;

    zzaa(Rpc rpc0, Looper looper0) {
        this.zza = rpc0;
        super(looper0);
    }

    @Override  // android.os.Handler
    public final void handleMessage(Message message0) {
        Rpc.zzc(this.zza, message0);
    }
}

