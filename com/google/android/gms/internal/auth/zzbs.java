package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbs extends zzbj {
    zzbs(zzbt zzbt0, GoogleApiClient googleApiClient0) {
        super(googleApiClient0);
    }

    @Override  // com.google.android.gms.internal.auth.zzbj
    protected final void zza(Context context0, zzbh zzbh0) throws RemoteException {
        zzbh0.zzd(new zzbr(this));
    }
}

