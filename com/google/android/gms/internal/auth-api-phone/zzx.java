package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzx implements RemoteCall {
    public final zzab zza;

    public zzx(zzab zzab0) {
        this.zza = zzab0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        ((zzh)((zzw)object0).getService()).zzg(new zzz(this.zza, ((TaskCompletionSource)object1)));
    }
}

