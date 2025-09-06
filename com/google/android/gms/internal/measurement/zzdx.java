package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzdx extends zzdu {
    final Bundle zza;
    final Activity zzb;
    final zzee zzc;

    zzdx(zzee zzee0, Bundle bundle0, Activity activity0) {
        this.zzc = zzee0;
        this.zza = bundle0;
        this.zzb = activity0;
        super(zzee0.zza, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        Bundle bundle0;
        if(this.zza == null) {
            bundle0 = null;
        }
        else {
            bundle0 = new Bundle();
            if(this.zza.containsKey("com.google.app_measurement.screen_service")) {
                Object object0 = this.zza.get("com.google.app_measurement.screen_service");
                if(object0 instanceof Bundle) {
                    bundle0.putBundle("com.google.app_measurement.screen_service", ((Bundle)object0));
                }
            }
        }
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzc.zza))).onActivityCreated(ObjectWrapper.wrap(this.zzb), bundle0, this.zzi);
    }
}

