package com.google.android.gms.internal.measurement;

public final class zzfp extends zzlb implements zzmj {
    private static final zzfp zza;
    private int zzd;
    private int zze;
    private zzgi zzf;
    private zzgi zzg;
    private boolean zzh;

    static {
        zzfp zzfp0 = new zzfp();
        zzfp.zza = zzfp0;
        zzlb.zzbO(zzfp.class, zzfp0);
    }

    public final int zza() {
        return this.zze;
    }

    public static zzfo zzb() {
        return (zzfo)zzfp.zza.zzbA();
    }

    static zzfp zzc() {
        return zzfp.zza;
    }

    public final zzgi zzd() {
        return this.zzf == null ? zzgi.zzg() : this.zzf;
    }

    public final zzgi zze() {
        return this.zzg == null ? zzgi.zzg() : this.zzg;
    }

    static void zzf(zzfp zzfp0, int v) {
        zzfp0.zzd |= 1;
        zzfp0.zze = v;
    }

    static void zzg(zzfp zzfp0, zzgi zzgi0) {
        zzgi0.getClass();
        zzfp0.zzf = zzgi0;
        zzfp0.zzd |= 2;
    }

    static void zzh(zzfp zzfp0, zzgi zzgi0) {
        zzfp0.zzg = zzgi0;
        zzfp0.zzd |= 4;
    }

    static void zzi(zzfp zzfp0, boolean z) {
        zzfp0.zzd |= 8;
        zzfp0.zzh = z;
    }

    public final boolean zzj() {
        return this.zzh;
    }

    public final boolean zzk() {
        return (this.zzd & 1) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfp.zzbL(zzfp.zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
                }
                case 3: {
                    return new zzfp();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfo(null);
                        }
                        case 5: {
                            return zzfp.zza;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }
        }
        return (byte)1;
    }

    public final boolean zzm() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzn() {
        return (this.zzd & 4) != 0;
    }
}

