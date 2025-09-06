package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

abstract class zzbj extends ApiMethodImpl {
    public zzbj(GoogleApiClient googleApiClient0) {
        super(AuthProxy.API, googleApiClient0);
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result createFailedResult(Status status0) {
        return new zzbv(status0);
    }

    @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    protected final void doExecute(AnyClient api$AnyClient0) throws RemoteException {
        this.zza(((zzbe)api$AnyClient0).getContext(), ((zzbh)((zzbe)api$AnyClient0).getService()));
    }

    @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final void setResult(Object object0) {
        super.setResult(((Result)object0));
    }

    protected abstract void zza(Context arg1, zzbh arg2) throws RemoteException;
}

