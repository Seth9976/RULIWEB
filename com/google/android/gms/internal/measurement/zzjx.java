package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

class zzjx extends zzjw {
    protected final byte[] zza;

    zzjx(byte[] arr_b) {
        arr_b.getClass();
        this.zza = arr_b;
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzka)) {
            return false;
        }
        if(this.zzd() != ((zzka)object0).zzd()) {
            return false;
        }
        if(this.zzd() == 0) {
            return true;
        }
        if(object0 instanceof zzjx) {
            int v = this.zzk();
            int v1 = ((zzjx)object0).zzk();
            if(v != 0 && v1 != 0 && v != v1) {
                return false;
            }
            int v2 = this.zzd();
            if(v2 > ((zzjx)object0).zzd()) {
                throw new IllegalArgumentException("Length too large: " + v2 + this.zzd());
            }
            if(v2 > ((zzjx)object0).zzd()) {
                throw new IllegalArgumentException("Ran off end of other: 0, " + v2 + ", " + ((zzjx)object0).zzd());
            }
            if(((zzjx)object0) instanceof zzjx) {
                byte[] arr_b = this.zza;
                byte[] arr_b1 = ((zzjx)object0).zza;
                ((zzjx)object0).zzc();
                int v3 = 0;
                for(int v4 = 0; v3 < v2; ++v4) {
                    if(arr_b[v3] != arr_b1[v4]) {
                        return false;
                    }
                    ++v3;
                }
                return true;
            }
            return ((zzjx)object0).zzf(0, v2).equals(this.zzf(0, v2));
        }
        return object0.equals(this);
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    public byte zza(int v) {
        return this.zza[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    byte zzb(int v) {
        return this.zza[v];
    }

    protected int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    public int zzd() {
        return this.zza.length;
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    protected final int zze(int v, int v1, int v2) {
        return zzlj.zzb(v, this.zza, 0, v2);
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    public final zzka zzf(int v, int v1) {
        int v2 = zzjx.zzj(0, v1, this.zzd());
        return v2 == 0 ? zzka.zzb : new zzju(this.zza, 0, v2);
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    protected final String zzg(Charset charset0) {
        int v = this.zzd();
        return new String(this.zza, 0, v, charset0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    final void zzh(zzjq zzjq0) throws IOException {
        int v = this.zzd();
        ((zzkf)zzjq0).zzc(this.zza, 0, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzka
    public final boolean zzi() {
        int v = this.zzd();
        return zznz.zze(this.zza, 0, v);
    }
}

