package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zze;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

final class zzae extends ApiMethodImpl {
    final String zza;

    zzae(zzal zzal0, Api api0, GoogleApiClient googleApiClient0, String s) {
        this.zza = s;
        super(api0, googleApiClient0);
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result createFailedResult(Status status0) {
        return new zzai(status0, null);
    }

    @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    protected final void doExecute(AnyClient api$AnyClient0) throws RemoteException {
        ((zze)((zzam)api$AnyClient0).getService()).zzd(new zzad(this), this.zza);
    }

    @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final void setResult(Object object0) {
        super.setResult(((Result)object0));
    }
}

