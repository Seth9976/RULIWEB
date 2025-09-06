package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;

public final class zzfm {
    private final zzfm.zza zza;

    public zzfm(zzfm.zza zzfm$zza0) {
        Preconditions.checkNotNull(zzfm$zza0);
        this.zza = zzfm$zza0;
    }

    public final void zza(Context context0, Intent intent0) {
        zzet zzet0 = zzgd.zzp(context0, null, null).zzaA();
        if(intent0 == null) {
            zzet0.zzk().zza("Receiver called with null intent");
            return;
        }
        String s = intent0.getAction();
        zzet0.zzj().zzb("Local receiver got", s);
        if("com.google.android.gms.measurement.UPLOAD".equals(s)) {
            Intent intent1 = new Intent().setClassName(context0, "com.google.android.gms.measurement.AppMeasurementService");
            intent1.setAction("com.google.android.gms.measurement.UPLOAD");
            zzet0.zzj().zza("Starting wakeful intent.");
            this.zza.doStartService(context0, intent1);
            return;
        }
        if("com.android.vending.INSTALL_REFERRER".equals(s)) {
            zzet0.zzk().zza("Install Referrer Broadcasts are deprecated");
        }
    }
}

