package com.google.android.gms.measurement.internal;

import java.util.HashMap;
import java.util.concurrent.Callable;

public final class zzfn implements Callable {
    public final zzfu zza;
    public final String zzb;

    public zzfn(zzfu zzfu0, String s) {
        this.zza = zzfu0;
        this.zzb = s;
    }

    @Override
    public final Object call() {
        zzh zzh0 = this.zza.zzf.zzh().zzj(this.zzb);
        HashMap hashMap0 = new HashMap();
        hashMap0.put("platform", "android");
        hashMap0.put("package_name", this.zzb);
        this.zza.zzt.zzf().zzh();
        hashMap0.put("gmp_version", 79000L);
        if(zzh0 != null) {
            String s = zzh0.zzy();
            if(s != null) {
                hashMap0.put("app_version", s);
            }
            hashMap0.put("app_version_int", zzh0.zzb());
            hashMap0.put("dynamite_version", zzh0.zzk());
        }
        return hashMap0;
    }
}

