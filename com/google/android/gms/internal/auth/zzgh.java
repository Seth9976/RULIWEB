package com.google.android.gms.internal.auth;

final class zzgh implements zzfu {
    private final zzfx zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzgh(zzfx zzfx0, String s, Object[] arr_object) {
        this.zza = zzfx0;
        this.zzb = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001A";
        this.zzc = arr_object;
        this.zzd = 1;
    }

    @Override  // com.google.android.gms.internal.auth.zzfu
    public final zzfx zza() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.auth.zzfu
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    @Override  // com.google.android.gms.internal.auth.zzfu
    public final int zzc() {
        return (this.zzd & 1) == 0 ? 2 : 1;
    }

    final String zzd() {
        return this.zzb;
    }

    final Object[] zze() {
        return this.zzc;
    }
}

