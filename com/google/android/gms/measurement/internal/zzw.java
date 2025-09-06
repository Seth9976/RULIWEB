package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzft;
import java.util.List;

final class zzw {
    final zzaa zza;
    private zzft zzb;
    private Long zzc;
    private long zzd;

    zzw(zzaa zzaa0, zzv zzv0) {
        this.zza = zzaa0;
        super();
    }

    final zzft zza(String s, zzft zzft0) {
        List list0 = zzft0.zzi();
        this.zza.zzf.zzu();
        Long long0 = (Long)zzlj.zzD(zzft0, "_eid");
        if(long0 != null) {
            this.zzc = long0;
            this.zzb = zzft0;
            this.zza.zzf.zzu();
            Long long1 = 0L;
            Object object0 = zzlj.zzD(zzft0, "_epc");
            if(object0 != null) {
                long1 = object0;
            }
            long v = (long)long1;
            this.zzd = v;
            if(v <= 0L) {
                this.zza.zzt.zzaA().zzh().zzb("Complex event with zero extra param count. eventName", "");
            }
            else {
                this.zza.zzf.zzh().zzJ(s, ((Long)Preconditions.checkNotNull(long0)), this.zzd, zzft0);
            }
        }
        zzfs zzfs0 = (zzfs)zzft0.zzbB();
        zzfs0.zzi("");
        zzfs0.zzg();
        zzfs0.zzd(list0);
        return (zzft)zzfs0.zzaD();
    }
}

