package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;

public final class zzg implements zzk {
    public final Account zza;
    public final String zzb;
    public final Bundle zzc;
    public final Context zzd;

    public zzg(Account account0, String s, Bundle bundle0, Context context0) {
        this.zza = account0;
        this.zzb = s;
        this.zzc = bundle0;
        this.zzd = context0;
    }

    @Override  // com.google.android.gms.auth.zzk
    public final Object zza(IBinder iBinder0) {
        return zzl.zzb(this.zza, this.zzb, this.zzc, this.zzd, iBinder0);
    }
}

