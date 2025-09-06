package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzr implements RemoteCall {
    public final zzab zza;
    public final Account zzb;

    public zzr(zzab zzab0, Account account0) {
        this.zza = zzab0;
        this.zzb = account0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        ((zzp)((zzi)object0).getService()).zzg(new zzaa(this.zza, ((TaskCompletionSource)object1)), this.zzb);
    }
}

