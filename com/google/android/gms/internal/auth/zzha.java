package com.google.android.gms.internal.auth;

import java.util.Arrays;

public final class zzha {
    private static final zzha zza;
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private boolean zze;

    static {
        zzha.zza = new zzha(0, new int[0], new Object[0], false);
    }

    private zzha() {
        this(0, new int[8], new Object[8], true);
    }

    private zzha(int v, int[] arr_v, Object[] arr_object, boolean z) {
        this.zzb = v;
        this.zzc = arr_v;
        this.zzd = arr_object;
        this.zze = z;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzha)) {
            return false;
        }
        int v = this.zzb;
        if(v == ((zzha)object0).zzb) {
            int[] arr_v = this.zzc;
            int[] arr_v1 = ((zzha)object0).zzc;
            for(int v1 = 0; v1 < v; ++v1) {
                if(arr_v[v1] != arr_v1[v1]) {
                    return false;
                }
            }
            Object[] arr_object = this.zzd;
            Object[] arr_object1 = ((zzha)object0).zzd;
            int v2 = this.zzb;
            for(int v3 = 0; v3 < v2; ++v3) {
                if(!arr_object[v3].equals(arr_object1[v3])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int v = this.zzb;
        int[] arr_v = this.zzc;
        int v2 = 17;
        int v4 = 17;
        for(int v3 = 0; v3 < v; ++v3) {
            v4 = v4 * 0x1F + arr_v[v3];
        }
        Object[] arr_object = this.zzd;
        int v5 = this.zzb;
        for(int v1 = 0; v1 < v5; ++v1) {
            v2 = v2 * 0x1F + arr_object[v1].hashCode();
        }
        return ((v + 0x20F) * 0x1F + v4) * 0x1F + v2;
    }

    public static zzha zza() {
        return zzha.zza;
    }

    final zzha zzb(zzha zzha0) {
        if(zzha0.equals(zzha.zza)) {
            return this;
        }
        this.zze();
        int v = this.zzb + zzha0.zzb;
        this.zzi(v);
        System.arraycopy(zzha0.zzc, 0, this.zzc, this.zzb, zzha0.zzb);
        System.arraycopy(zzha0.zzd, 0, this.zzd, this.zzb, zzha0.zzb);
        this.zzb = v;
        return this;
    }

    static zzha zzc(zzha zzha0, zzha zzha1) {
        int v = zzha0.zzb + zzha1.zzb;
        int[] arr_v = Arrays.copyOf(zzha0.zzc, v);
        System.arraycopy(zzha1.zzc, 0, arr_v, zzha0.zzb, zzha1.zzb);
        Object[] arr_object = Arrays.copyOf(zzha0.zzd, v);
        System.arraycopy(zzha1.zzd, 0, arr_object, zzha0.zzb, zzha1.zzb);
        return new zzha(v, arr_v, arr_object, true);
    }

    static zzha zzd() {
        return new zzha(0, new int[8], new Object[8], true);
    }

    final void zze() {
        if(!this.zze) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzf() {
        if(this.zze) {
            this.zze = false;
        }
    }

    final void zzg(StringBuilder stringBuilder0, int v) {
        for(int v1 = 0; v1 < this.zzb; ++v1) {
            zzfz.zzb(stringBuilder0, v, String.valueOf(this.zzc[v1] >>> 3), this.zzd[v1]);
        }
    }

    final void zzh(int v, Object object0) {
        this.zze();
        this.zzi(this.zzb + 1);
        int v1 = this.zzb;
        this.zzc[v1] = v;
        this.zzd[v1] = object0;
        this.zzb = v1 + 1;
    }

    private final void zzi(int v) {
        int[] arr_v = this.zzc;
        if(v > arr_v.length) {
            int v1 = this.zzb + this.zzb / 2;
            if(v1 >= v) {
                v = v1;
            }
            if(v < 8) {
                v = 8;
            }
            this.zzc = Arrays.copyOf(arr_v, v);
            this.zzd = Arrays.copyOf(this.zzd, v);
        }
    }
}

