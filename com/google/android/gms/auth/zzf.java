package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.internal.auth.zze;
import java.io.IOException;

public final class zzf implements zzk {
    public final Account zza;

    public zzf(Account account0) {
        this.zza = account0;
    }

    @Override  // com.google.android.gms.auth.zzk
    public final Object zza(IBinder iBinder0) {
        Bundle bundle0 = zze.zzb(iBinder0).zzf(this.zza);
        if(bundle0 == null) {
            throw new IOException("Service call returned null.");
        }
        return bundle0;
    }
}

