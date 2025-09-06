package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.RemoteException;
import com.google.android.gms.auth.account.zze;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

final class zzag extends ApiMethodImpl {
    final Account zza;

    zzag(zzal zzal0, Api api0, GoogleApiClient googleApiClient0, Account account0) {
        this.zza = account0;
        super(api0, googleApiClient0);
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result createFailedResult(Status status0) {
        return new zzak(status0);
    }

    @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    protected final void doExecute(AnyClient api$AnyClient0) throws RemoteException {
        ((zze)((zzam)api$AnyClient0).getService()).zze(new zzaf(this), this.zza);
    }

    @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final void setResult(Object object0) {
        super.setResult(((Result)object0));
    }
}

