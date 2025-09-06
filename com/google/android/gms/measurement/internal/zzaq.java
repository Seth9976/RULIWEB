package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzaq {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final Long zzh;
    final Long zzi;
    final Long zzj;
    final Boolean zzk;

    zzaq(String s, String s1, long v, long v1, long v2, long v3, long v4, Long long0, Long long1, Long long2, Boolean boolean0) {
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        boolean z = true;
        Preconditions.checkArgument(v >= 0L);
        Preconditions.checkArgument(v1 >= 0L);
        Preconditions.checkArgument(v2 >= 0L);
        if(v4 < 0L) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.zza = s;
        this.zzb = s1;
        this.zzc = v;
        this.zzd = v1;
        this.zze = v2;
        this.zzf = v3;
        this.zzg = v4;
        this.zzh = long0;
        this.zzi = long1;
        this.zzj = long2;
        this.zzk = boolean0;
    }

    final zzaq zza(Long long0, Long long1, Boolean boolean0) {
        if(boolean0 != null) {
            boolean0.booleanValue();
        }
        return new zzaq(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, long0, long1, boolean0);
    }

    final zzaq zzb(long v, long v1) {
        return new zzaq(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, v, v1, this.zzi, this.zzj, this.zzk);
    }

    final zzaq zzc(long v) {
        return new zzaq(this.zza, this.zzb, this.zzc, this.zzd, this.zze, v, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk);
    }
}

