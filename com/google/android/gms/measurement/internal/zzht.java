package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

final class zzht implements Runnable {
    final Bundle zza;
    final zzik zzb;

    zzht(zzik zzik0, Bundle bundle0) {
        this.zzb = zzik0;
        this.zza = bundle0;
        super();
    }

    @Override
    public final void run() {
        zzau zzau2;
        zzau zzau1;
        zzau zzau0;
        zzik zzik0 = this.zzb;
        Bundle bundle0 = this.zza;
        zzik0.zzg();
        zzik0.zza();
        Preconditions.checkNotNull(bundle0);
        String s = bundle0.getString("name");
        String s1 = bundle0.getString("origin");
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        Preconditions.checkNotNull(bundle0.get("value"));
        if(!zzik0.zzt.zzJ()) {
            zzik0.zzt.zzaA().zzj().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzlk zzlk0 = new zzlk(s, bundle0.getLong("triggered_timestamp"), bundle0.get("value"), s1);
        try {
            zzau0 = zzik0.zzt.zzv().zzz(bundle0.getString("app_id"), bundle0.getString("triggered_event_name"), bundle0.getBundle("triggered_event_params"), s1, 0L, true, true);
            zzau1 = zzik0.zzt.zzv().zzz(bundle0.getString("app_id"), bundle0.getString("timed_out_event_name"), bundle0.getBundle("timed_out_event_params"), s1, 0L, true, true);
            zzau2 = zzik0.zzt.zzv().zzz(bundle0.getString("app_id"), bundle0.getString("expired_event_name"), bundle0.getBundle("expired_event_params"), s1, 0L, true, true);
        }
        catch(IllegalArgumentException unused_ex) {
            return;
        }
        zzac zzac0 = new zzac(bundle0.getString("app_id"), s1, zzlk0, bundle0.getLong("creation_timestamp"), false, bundle0.getString("trigger_event_name"), zzau1, bundle0.getLong("trigger_timeout"), zzau0, bundle0.getLong("time_to_live"), zzau2);
        zzik0.zzt.zzt().zzE(zzac0);
    }
}

