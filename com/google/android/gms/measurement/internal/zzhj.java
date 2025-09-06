package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

public final class zzhj implements Runnable {
    public final zzik zza;
    public final Bundle zzb;
    public final long zzc;

    public zzhj(zzik zzik0, Bundle bundle0, long v) {
        this.zza = zzik0;
        this.zzb = bundle0;
        this.zzc = v;
    }

    @Override
    public final void run() {
        zzik zzik0 = this.zza;
        Bundle bundle0 = this.zzb;
        long v = this.zzc;
        if(TextUtils.isEmpty(zzik0.zzt.zzh().zzm())) {
            zzik0.zzS(bundle0, 0, v);
            return;
        }
        zzik0.zzt.zzaA().zzl().zza("Using developer consent only; google app id found");
    }
}

