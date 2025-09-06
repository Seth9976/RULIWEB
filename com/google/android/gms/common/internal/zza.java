package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

abstract class zza extends zzc {
    public final int zza;
    public final Bundle zzb;
    final BaseGmsClient zzc;

    protected zza(BaseGmsClient baseGmsClient0, int v, Bundle bundle0) {
        this.zzc = baseGmsClient0;
        super(baseGmsClient0, Boolean.TRUE);
        this.zza = v;
        this.zzb = bundle0;
    }

    @Override  // com.google.android.gms.common.internal.zzc
    protected final void zza(Object object0) {
        PendingIntent pendingIntent0 = null;
        if(this.zza == 0) {
            if(!this.zzd()) {
                BaseGmsClient.zzi(this.zzc, 1, null);
                this.zzb(new ConnectionResult(8, null));
            }
            return;
        }
        BaseGmsClient.zzi(this.zzc, 1, null);
        Bundle bundle0 = this.zzb;
        if(bundle0 != null) {
            pendingIntent0 = (PendingIntent)bundle0.getParcelable("pendingIntent");
        }
        this.zzb(new ConnectionResult(this.zza, pendingIntent0));
    }

    protected abstract void zzb(ConnectionResult arg1);

    @Override  // com.google.android.gms.common.internal.zzc
    protected final void zzc() {
    }

    protected abstract boolean zzd();
}

