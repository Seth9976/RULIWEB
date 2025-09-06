package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zze;
import java.io.IOException;

final class zzi implements zzk {
    final AccountChangeEventsRequest zza;

    zzi(AccountChangeEventsRequest accountChangeEventsRequest0) {
        this.zza = accountChangeEventsRequest0;
        super();
    }

    @Override  // com.google.android.gms.auth.zzk
    public final Object zza(IBinder iBinder0) throws RemoteException, IOException, GoogleAuthException {
        AccountChangeEventsResponse accountChangeEventsResponse0 = zze.zzb(iBinder0).zzh(this.zza);
        zzl.zzc(accountChangeEventsResponse0);
        return accountChangeEventsResponse0.getEvents();
    }
}

