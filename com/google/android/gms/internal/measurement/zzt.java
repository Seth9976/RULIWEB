package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzt extends zzai {
    private final zzr zza;

    public zzt(zzr zzr0) {
        super("internal.logger");
        this.zza = zzr0;
        this.zze.put("log", new zzs(this, false, true));
        this.zze.put("silent", new zzp(this, "silent"));
        ((zzai)this.zze.get("silent")).zzr("log", new zzs(this, true, true));
        this.zze.put("unmonitored", new zzq(this, "unmonitored"));
        ((zzai)this.zze.get("unmonitored")).zzr("log", new zzs(this, false, false));
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        return zzap.zzf;
    }
}

