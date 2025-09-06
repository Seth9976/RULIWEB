package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

public final class zznl {
    private static final zznl zza;
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    static {
        zznl.zza = new zznl(0, new int[0], new Object[0], false);
    }

    private zznl() {
        this(0, new int[8], new Object[8], true);
    }

    private zznl(int v, int[] arr_v, Object[] arr_object, boolean z) {
        this.zze = -1;
        this.zzb = v;
        this.zzc = arr_v;
        this.zzd = arr_object;
        this.zzf = z;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zznl)) {
            return false;
        }
        int v = this.zzb;
        if(v == ((zznl)object0).zzb) {
            int[] arr_v = this.zzc;
            int[] arr_v1 = ((zznl)object0).zzc;
            for(int v1 = 0; v1 < v; ++v1) {
                if(arr_v[v1] != arr_v1[v1]) {
                    return false;
                }
            }
            Object[] arr_object = this.zzd;
            Object[] arr_object1 = ((zznl)object0).zzd;
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

    public final int zza() {
        int v5;
        int v = this.zze;
        if(v == -1) {
            int v2 = 0;
            for(int v1 = 0; v1 < this.zzb; ++v1) {
                int v3 = this.zzc[v1];
                int v4 = v3 >>> 3;
                switch(v3 & 7) {
                    case 0: {
                        v5 = zzki.zzx(v4 << 3) + zzki.zzy(((long)(((Long)this.zzd[v1]))));
                        break;
                    }
                    case 1: {
                        ((Long)this.zzd[v1]).longValue();
                        v5 = zzki.zzx(v4 << 3) + 8;
                        break;
                    }
                    case 2: {
                        int v7 = ((zzka)this.zzd[v1]).zzd();
                        v5 = zzki.zzx(v4 << 3) + (zzki.zzx(v7) + v7);
                        break;
                    }
                    case 3: {
                        int v6 = zzki.zzx(v4 << 3);
                        v5 = v6 + v6 + ((zznl)this.zzd[v1]).zza();
                        break;
                    }
                    case 5: {
                        ((Integer)this.zzd[v1]).intValue();
                        v5 = zzki.zzx(v4 << 3) + 4;
                        break;
                    }
                    default: {
                        throw new IllegalStateException(zzll.zza());
                    }
                }
                v2 += v5;
            }
            this.zze = v2;
            return v2;
        }
        return v;
    }

    public final int zzb() {
        int v = this.zze;
        if(v == -1) {
            int v2 = 0;
            for(int v1 = 0; v1 < this.zzb; ++v1) {
                int v3 = this.zzc[v1] >>> 3;
                int v4 = ((zzka)this.zzd[v1]).zzd();
                v2 = zzki.zzx(v3) + zzki.zzx(v4) + v2 + v4 + 4;
            }
            this.zze = v2;
            return v2;
        }
        return v;
    }

    public static zznl zzc() {
        return zznl.zza;
    }

    final zznl zzd(zznl zznl0) {
        if(zznl0.equals(zznl.zza)) {
            return this;
        }
        this.zzg();
        int v = this.zzb + zznl0.zzb;
        this.zzl(v);
        System.arraycopy(zznl0.zzc, 0, this.zzc, this.zzb, zznl0.zzb);
        System.arraycopy(zznl0.zzd, 0, this.zzd, this.zzb, zznl0.zzb);
        this.zzb = v;
        return this;
    }

    static zznl zze(zznl zznl0, zznl zznl1) {
        int v = zznl0.zzb + zznl1.zzb;
        int[] arr_v = Arrays.copyOf(zznl0.zzc, v);
        System.arraycopy(zznl1.zzc, 0, arr_v, zznl0.zzb, zznl1.zzb);
        Object[] arr_object = Arrays.copyOf(zznl0.zzd, v);
        System.arraycopy(zznl1.zzd, 0, arr_object, zznl0.zzb, zznl1.zzb);
        return new zznl(v, arr_v, arr_object, true);
    }

    static zznl zzf() {
        return new zznl(0, new int[8], new Object[8], true);
    }

    final void zzg() {
        if(!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if(this.zzf) {
            this.zzf = false;
        }
    }

    final void zzi(StringBuilder stringBuilder0, int v) {
        for(int v1 = 0; v1 < this.zzb; ++v1) {
            zzmk.zzb(stringBuilder0, v, String.valueOf(this.zzc[v1] >>> 3), this.zzd[v1]);
        }
    }

    final void zzj(int v, Object object0) {
        this.zzg();
        this.zzl(this.zzb + 1);
        int v1 = this.zzb;
        this.zzc[v1] = v;
        this.zzd[v1] = object0;
        this.zzb = v1 + 1;
    }

    public final void zzk(zzoc zzoc0) throws IOException {
        if(this.zzb != 0) {
            for(int v = 0; v < this.zzb; ++v) {
                int v1 = this.zzc[v];
                Object object0 = this.zzd[v];
                int v2 = v1 & 7;
                int v3 = v1 >>> 3;
                switch(v2) {
                    case 0: {
                        zzoc0.zzt(v3, ((long)(((Long)object0))));
                        break;
                    }
                    case 1: {
                        zzoc0.zzm(v3, ((long)(((Long)object0))));
                        break;
                    }
                    case 2: {
                        zzoc0.zzd(v3, ((zzka)object0));
                        break;
                    }
                    case 3: {
                        zzoc0.zzE(v3);
                        ((zznl)object0).zzk(zzoc0);
                        zzoc0.zzh(v3);
                        break;
                    }
                    default: {
                        if(v2 != 5) {
                            throw new RuntimeException(zzll.zza());
                        }
                        zzoc0.zzk(v3, ((int)(((Integer)object0))));
                        break;
                    }
                }
            }
        }
    }

    private final void zzl(int v) {
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

