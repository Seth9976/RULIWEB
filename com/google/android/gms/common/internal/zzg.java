package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

public final class zzg extends zza {
    final BaseGmsClient zze;

    public zzg(BaseGmsClient baseGmsClient0, int v, Bundle bundle0) {
        this.zze = baseGmsClient0;
        super(baseGmsClient0, v, null);
    }

    @Override  // com.google.android.gms.common.internal.zza
    protected final void zzb(ConnectionResult connectionResult0) {
        this.zze.zzc.onReportServiceBinding(connectionResult0);
        this.zze.onConnectionFailed(connectionResult0);
    }

    @Override  // com.google.android.gms.common.internal.zza
    protected final boolean zzd() {
        this.zze.zzc.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
        return true;
    }
}

