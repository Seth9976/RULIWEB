package com.google.android.gms.internal.measurement;

public final class zzfr extends zzlb implements zzmj {
    private static final zzfr zza;
    private int zzd;
    private int zze;
    private long zzf;

    static {
        zzfr zzfr0 = new zzfr();
        zzfr.zza = zzfr0;
        zzlb.zzbO(zzfr.class, zzfr0);
    }

    public final int zza() {
        return this.zze;
    }

    public final long zzb() {
        return this.zzf;
    }

    public static zzfq zzc() {
        return (zzfq)zzfr.zza.zzbA();
    }

    static zzfr zzd() {
        return zzfr.zza;
    }

    static void zze(zzfr zzfr0, int v) {
        zzfr0.zzd |= 1;
        zzfr0.zze = v;
    }

    static void zzf(zzfr zzfr0, long v) {
        zzfr0.zzd |= 2;
        zzfr0.zzf = v;
    }

    public final boolean zzg() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 1) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfr.zzbL(zzfr.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zzd", "zze", "zzf"});
                }
                case 3: {
                    return new zzfr();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfq(null);
                        }
                        case 5: {
                            return zzfr.zza;
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
}

