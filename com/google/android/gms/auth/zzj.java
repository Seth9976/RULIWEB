package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzby;
import com.google.android.gms.internal.auth.zze;
import java.io.IOException;

final class zzj implements zzk {
    final String zza;
    final Context zzb;

    zzj(String s, Context context0) {
        this.zza = s;
        this.zzb = context0;
        super();
    }

    @Override  // com.google.android.gms.auth.zzk
    public final Object zza(IBinder iBinder0) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle0 = zze.zzb(iBinder0).zzg(this.zza);
        zzl.zzc(bundle0);
        String s = bundle0.getString("Error");
        Intent intent0 = (Intent)bundle0.getParcelable("userRecoveryIntent");
        PendingIntent pendingIntent0 = (PendingIntent)bundle0.getParcelable("userRecoveryPendingIntent");
        zzby zzby0 = zzby.zza(s);
        if(zzby.zzc.equals(zzby0)) {
            return true;
        }
        zzl.zzd(this.zzb, "requestGoogleAccountsAccess", s, intent0, pendingIntent0);
        throw new GoogleAuthException("Invalid state. Shouldn\'t happen");
    }
}

