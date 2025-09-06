package com.google.android.gms.internal.measurement;

final class zzju extends zzjx {
    private final int zzc;

    zzju(byte[] arr_b, int v, int v1) {
        super(arr_b);
        zzju.zzj(0, v1, arr_b.length);
        this.zzc = v1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjx
    public final byte zza(int v) {
        int v1 = this.zzc;
        if((v1 - (v + 1) | v) < 0) {
            throw v >= 0 ? new ArrayIndexOutOfBoundsException("Index > length: " + v + ", " + v1) : new ArrayIndexOutOfBoundsException("Index < 0: " + v);
        }
        return this.zza[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzjx
    final byte zzb(int v) {
        return this.zza[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzjx
    protected final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjx
    public final int zzd() {
        return this.zzc;
    }
}

