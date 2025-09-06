package com.google.android.gms.internal.measurement;

public final class zzem extends zzlb implements zzmj {
    private static final zzem zza;
    private int zzd;
    private zzey zze;
    private zzer zzf;
    private boolean zzg;
    private String zzh;

    static {
        zzem zzem0 = new zzem();
        zzem.zza = zzem0;
        zzlb.zzbO(zzem.class, zzem0);
    }

    private zzem() {
        this.zzh = "";
    }

    static zzem zza() {
        return zzem.zza;
    }

    public static zzem zzb() {
        return zzem.zza;
    }

    public final zzer zzc() {
        return this.zzf == null ? zzer.zzb() : this.zzf;
    }

    public final zzey zzd() {
        return this.zze == null ? zzey.zzc() : this.zze;
    }

    public final String zze() [...] // 潜在的解密器

    static void zzf(zzem zzem0, String s) {
        zzem0.zzd |= 8;
        zzem0.zzh = s;
    }

    public final boolean zzg() {
        return this.zzg;
    }

    public final boolean zzh() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzj() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzk() {
        return (this.zzd & 1) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzem.zzbL(zzem.zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
                }
                case 3: {
                    return new zzem();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzel(null);
                        }
                        case 5: {
                            return zzem.zza;
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

