package com.google.android.gms.internal.auth;

import sun.misc.Unsafe;

final class zzhg extends zzhi {
    zzhg(Unsafe unsafe0) {
        super(unsafe0);
    }

    @Override  // com.google.android.gms.internal.auth.zzhi
    public final double zza(Object object0, long v) {
        return Double.longBitsToDouble(this.zza.getLong(object0, v));
    }

    @Override  // com.google.android.gms.internal.auth.zzhi
    public final float zzb(Object object0, long v) {
        return Float.intBitsToFloat(this.zza.getInt(object0, v));
    }

    @Override  // com.google.android.gms.internal.auth.zzhi
    public final void zzc(Object object0, long v, boolean z) {
        if(zzhj.zza) {
            zzhj.zzi(object0, v, z);
            return;
        }
        zzhj.zzj(object0, v, z);
    }

    @Override  // com.google.android.gms.internal.auth.zzhi
    public final void zzd(Object object0, long v, double f) {
        this.zza.putLong(object0, v, Double.doubleToLongBits(f));
    }

    @Override  // com.google.android.gms.internal.auth.zzhi
    public final void zze(Object object0, long v, float f) {
        this.zza.putInt(object0, v, Float.floatToIntBits(f));
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.auth.zzhi
    public final boolean zzf(Object object0, long v) {
        return zzhj.zza ? zzhj.zzq(object0, v) : zzhj.zzr(object0, v);
    }
}

