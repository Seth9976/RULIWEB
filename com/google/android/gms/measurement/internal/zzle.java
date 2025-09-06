package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzgd;
import java.util.ArrayList;
import java.util.List;

final class zzle {
    zzgd zza;
    List zzb;
    List zzc;
    long zzd;
    final zzlh zze;

    zzle(zzlh zzlh0, zzld zzld0) {
        this.zze = zzlh0;
        super();
    }

    public final boolean zza(long v, zzft zzft0) {
        Preconditions.checkNotNull(zzft0);
        if(this.zzc == null) {
            this.zzc = new ArrayList();
        }
        if(this.zzb == null) {
            this.zzb = new ArrayList();
        }
        if(!this.zzc.isEmpty() && zzle.zzb(((zzft)this.zzc.get(0))) != zzle.zzb(zzft0)) {
            return false;
        }
        long v1 = this.zzd + ((long)zzft0.zzbz());
        this.zze.zzg();
        if(v1 >= ((long)Math.max(0, ((int)(((Integer)zzeg.zzi.zza(null))))))) {
            return false;
        }
        this.zzd = v1;
        this.zzc.add(zzft0);
        this.zzb.add(v);
        int v2 = this.zzc.size();
        this.zze.zzg();
        return v2 < Math.max(1, ((int)(((Integer)zzeg.zzj.zza(null)))));
    }

    private static final long zzb(zzft zzft0) {
        return zzft0.zzd() / 1000L / 60L / 60L;
    }
}

