package com.google.android.gms.internal.auth;

final class zzdz extends zzec {
    private final int zzc;

    zzdz(byte[] arr_b, int v, int v1) {
        super(arr_b);
        zzdz.zzi(0, v1, arr_b.length);
        this.zzc = v1;
    }

    @Override  // com.google.android.gms.internal.auth.zzec
    public final byte zza(int v) {
        int v1 = this.zzc;
        if((v1 - (v + 1) | v) < 0) {
            throw v >= 0 ? new ArrayIndexOutOfBoundsException("Index > length: " + v + ", " + v1) : new ArrayIndexOutOfBoundsException("Index < 0: " + v);
        }
        return this.zza[v];
    }

    @Override  // com.google.android.gms.internal.auth.zzec
    final byte zzb(int v) {
        return this.zza[v];
    }

    @Override  // com.google.android.gms.internal.auth.zzec
    protected final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.auth.zzec
    public final int zzd() {
        return this.zzc;
    }
}

