package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

final class zzhu implements Runnable {
    final Bundle zza;
    final zzik zzb;

    zzhu(zzik zzik0, Bundle bundle0) {
        this.zzb = zzik0;
        this.zza = bundle0;
        super();
    }

    @Override
    public final void run() {
        zzau zzau0;
        zzik zzik0 = this.zzb;
        Bundle bundle0 = this.zza;
        zzik0.zzg();
        zzik0.zza();
        Preconditions.checkNotNull(bundle0);
        String s = Preconditions.checkNotEmpty(bundle0.getString("name"));
        if(!zzik0.zzt.zzJ()) {
            zzik0.zzt.zzaA().zzj().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzlk zzlk0 = new zzlk(s, 0L, null, "");
        try {
            zzau0 = zzik0.zzt.zzv().zzz(bundle0.getString("app_id"), bundle0.getString("expired_event_name"), bundle0.getBundle("expired_event_params"), "", bundle0.getLong("creation_timestamp"), true, true);
        }
        catch(IllegalArgumentException unused_ex) {
            return;
        }
        zzac zzac0 = new zzac(bundle0.getString("app_id"), "", zzlk0, bundle0.getLong("creation_timestamp"), bundle0.getBoolean("active"), bundle0.getString("trigger_event_name"), null, bundle0.getLong("trigger_timeout"), null, bundle0.getLong("time_to_live"), zzau0);
        zzik0.zzt.zzt().zzE(zzac0);
    }
}

