package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzu implements RemoteCall {
    public final zzab zza;
    public final AccountChangeEventsRequest zzb;

    public zzu(zzab zzab0, AccountChangeEventsRequest accountChangeEventsRequest0) {
        this.zza = zzab0;
        this.zzb = accountChangeEventsRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzz zzz0 = new zzz(this.zza, ((TaskCompletionSource)object1));
        ((zzp)((zzi)object0).getService()).zze(zzz0, this.zzb);
    }
}

