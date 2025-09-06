package com.google.android.gms.internal.auth;

import java.nio.charset.Charset;

class zzec extends zzeb {
    protected final byte[] zza;

    zzec(byte[] arr_b) {
        arr_b.getClass();
        this.zza = arr_b;
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzef)) {
            return false;
        }
        if(this.zzd() != ((zzef)object0).zzd()) {
            return false;
        }
        if(this.zzd() == 0) {
            return true;
        }
        if(object0 instanceof zzec) {
            int v = this.zzj();
            int v1 = ((zzec)object0).zzj();
            if(v != 0 && v1 != 0 && v != v1) {
                return false;
            }
            int v2 = this.zzd();
            if(v2 > ((zzec)object0).zzd()) {
                throw new IllegalArgumentException("Length too large: " + v2 + this.zzd());
            }
            if(v2 > ((zzec)object0).zzd()) {
                throw new IllegalArgumentException("Ran off end of other: 0, " + v2 + ", " + ((zzec)object0).zzd());
            }
            if(((zzec)object0) instanceof zzec) {
                byte[] arr_b = this.zza;
                byte[] arr_b1 = ((zzec)object0).zza;
                ((zzec)object0).zzc();
                int v3 = 0;
                for(int v4 = 0; v3 < v2; ++v4) {
                    if(arr_b[v3] != arr_b1[v4]) {
                        return false;
                    }
                    ++v3;
                }
                return true;
            }
            return ((zzec)object0).zzf(0, v2).equals(this.zzf(0, v2));
        }
        return object0.equals(this);
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    public byte zza(int v) {
        return this.zza[v];
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    byte zzb(int v) {
        return this.zza[v];
    }

    protected int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    public int zzd() {
        return this.zza.length;
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    protected final int zze(int v, int v1, int v2) {
        return zzfa.zzb(v, this.zza, 0, v2);
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    public final zzef zzf(int v, int v1) {
        int v2 = zzec.zzi(0, v1, this.zzd());
        return v2 == 0 ? zzef.zzb : new zzdz(this.zza, 0, v2);
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    protected final String zzg(Charset charset0) {
        int v = this.zzd();
        return new String(this.zza, 0, v, charset0);
    }

    @Override  // com.google.android.gms.internal.auth.zzef
    public final boolean zzh() {
        int v = this.zzd();
        return zzhn.zzc(this.zza, 0, v);
    }
}

