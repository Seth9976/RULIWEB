package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzs implements RemoteCall {
    public final zzab zza;
    public final Account zzb;
    public final String zzc;
    public final Bundle zzd;

    public zzs(zzab zzab0, Account account0, String s, Bundle bundle0) {
        this.zza = zzab0;
        this.zzb = account0;
        this.zzc = s;
        this.zzd = bundle0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        ((zzp)((zzi)object0).getService()).zzf(new zzw(this.zza, ((TaskCompletionSource)object1)), this.zzb, this.zzc, this.zzd);
    }
}

