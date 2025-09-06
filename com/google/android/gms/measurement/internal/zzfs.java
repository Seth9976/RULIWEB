package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzr;
import java.util.List;

final class zzfs implements zzr {
    final zzfu zza;

    zzfs(zzfu zzfu0) {
        this.zza = zzfu0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzr
    public final void zza(int v, String s, List list0, boolean z, boolean z1) {
        zzer zzer0;
        switch(v - 1) {
            case 0: {
                zzer0 = this.zza.zzt.zzaA().zzc();
                break;
            }
            case 1: {
                if(z) {
                    zzer0 = this.zza.zzt.zzaA().zzh();
                }
                else if(z1) {
                    zzer0 = this.zza.zzt.zzaA().zzd();
                }
                else {
                    zzer0 = this.zza.zzt.zzaA().zze();
                }
                break;
            }
            case 3: {
                zzer0 = this.zza.zzt.zzaA().zzj();
                break;
            }
            default: {
                if(v - 1 != 4) {
                    zzer0 = this.zza.zzt.zzaA().zzi();
                }
                else if(z) {
                    zzer0 = this.zza.zzt.zzaA().zzm();
                }
                else if(z1) {
                    zzer0 = this.zza.zzt.zzaA().zzk();
                }
                else {
                    zzer0 = this.zza.zzt.zzaA().zzl();
                }
            }
        }
        int v1 = list0.size();
        if(v1 != 1) {
            switch(v1) {
                case 2: {
                    zzer0.zzc(s, list0.get(0), list0.get(1));
                    return;
                }
                case 3: {
                    zzer0.zzd(s, list0.get(0), list0.get(1), list0.get(2));
                    return;
                }
                default: {
                    zzer0.zza(s);
                    return;
                }
            }
        }
        zzer0.zzb(s, list0.get(0));
    }
}

