package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzem;
import com.google.android.gms.internal.measurement.zzet;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzoy;

final class zzz extends zzy {
    final zzaa zza;
    private final zzet zzh;

    zzz(zzaa zzaa0, String s, int v, zzet zzet0) {
        this.zza = zzaa0;
        super(s, v);
        this.zzh = zzet0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzy
    final int zza() {
        return this.zzh.zza();
    }

    @Override  // com.google.android.gms.measurement.internal.zzy
    final boolean zzb() {
        return false;
    }

    @Override  // com.google.android.gms.measurement.internal.zzy
    final boolean zzc() {
        return true;
    }

    final boolean zzd(Long long0, Long long1, zzgm zzgm0, boolean z) {
        zzoy.zzc();
        boolean z1 = this.zza.zzt.zzf().zzs(this.zzb, zzeg.zzW);
        boolean z2 = this.zzh.zzi();
        boolean z3 = this.zzh.zzg() || this.zzh.zzh() || z2;
        Integer integer0 = null;
        if(z && !z3) {
            zzer zzer0 = this.zza.zzt.zzaA().zzj();
            Integer integer1 = this.zzc;
            if(this.zzh.zzj()) {
                integer0 = this.zzh.zza();
            }
            zzer0.zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", integer1, integer0);
            return true;
        }
        zzem zzem0 = this.zzh.zzb();
        boolean z4 = zzem0.zzg();
        if(!zzgm0.zzr()) {
            if(!zzgm0.zzq()) {
                if(!zzgm0.zzt()) {
                    this.zza.zzt.zzaA().zzk().zzb("User property has no value, property", this.zza.zzt.zzj().zzf(""));
                }
                else if(zzem0.zzk()) {
                    integer0 = zzz.zzj(zzz.zzf("", zzem0.zzd(), this.zza.zzt.zzaA()), z4);
                }
                else if(!zzem0.zzi()) {
                    this.zza.zzt.zzaA().zzk().zzb("No string or number filter defined. property", this.zza.zzt.zzj().zzf(""));
                }
                else {
                    this.zza.zzt.zzaA().zzk().zzc("Invalid user property value for Numeric number filter. property, value", this.zza.zzt.zzj().zzf(""), "");
                }
            }
            else if(!zzem0.zzi()) {
                this.zza.zzt.zzaA().zzk().zzb("No number filter for double property. property", this.zza.zzt.zzj().zzf(""));
            }
            else {
                integer0 = zzz.zzj(zzz.zzg(zzgm0.zza(), zzem0.zzc()), z4);
            }
        }
        else if(!zzem0.zzi()) {
            this.zza.zzt.zzaA().zzk().zzb("No number filter for long property. property", this.zza.zzt.zzj().zzf(""));
        }
        else {
            integer0 = zzz.zzj(zzz.zzh(zzgm0.zzb(), zzem0.zzc()), z4);
        }
        zzer zzer1 = this.zza.zzt.zzaA().zzj();
        String s = integer0 == null ? "null" : integer0;
        zzer1.zzb("Property filter result", s);
        if(integer0 == null) {
            return false;
        }
        this.zzd = Boolean.TRUE;
        if(z2 && !((Boolean)integer0).booleanValue()) {
            return true;
        }
        if(!z || this.zzh.zzg()) {
            this.zze = integer0;
        }
        if(((Boolean)integer0).booleanValue() && z3 && zzgm0.zzs()) {
            long v = long0 == null ? zzgm0.zzc() : ((long)long0);
            if(z1 && this.zzh.zzg() && !this.zzh.zzh() && long1 != null) {
                v = (long)long1;
            }
            if(this.zzh.zzh()) {
                this.zzg = v;
                return true;
            }
            this.zzf = v;
        }
        return true;
    }
}

