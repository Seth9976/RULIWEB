package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi.AddAccountResult;
import com.google.android.gms.common.api.Status;

final class zzai implements AddAccountResult {
    private static final Account zza;
    private final Status zzb;
    private final Account zzc;

    static {
        zzai.zza = new Account("DUMMY_NAME", "com.google");
    }

    public zzai(Status status0, Account account0) {
        this.zzb = status0;
        if(account0 == null) {
            account0 = zzai.zza;
        }
        this.zzc = account0;
    }

    @Override  // com.google.android.gms.auth.account.WorkAccountApi$AddAccountResult
    public final Account getAccount() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzb;
    }
}

