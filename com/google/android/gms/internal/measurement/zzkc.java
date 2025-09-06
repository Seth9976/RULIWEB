package com.google.android.gms.internal.measurement;

final class zzkc extends zzke {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze;

    zzkc(byte[] arr_b, int v, int v1, boolean z, zzkb zzkb0) {
        super(null);
        this.zze = 0x7FFFFFFF;
        this.zzb = arr_b;
        this.zzc = 0;
    }

    public final int zza(int v) throws zzll {
        int v1 = this.zze;
        this.zze = 0;
        int v2 = this.zzc + this.zzd;
        this.zzc = v2;
        if(v2 > 0) {
            this.zzd = v2;
            this.zzc = 0;
            return v1;
        }
        this.zzd = 0;
        return v1;
    }
}

