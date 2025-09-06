package com.google.android.gms.internal.measurement;

public final class zzet extends zzlb implements zzmj {
    private static final zzet zza;
    private int zzd;
    private int zze;
    private String zzf;
    private zzem zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;

    static {
        zzet zzet0 = new zzet();
        zzet.zza = zzet0;
        zzlb.zzbO(zzet.class, zzet0);
    }

    private zzet() {
        this.zzf = "";
    }

    public final int zza() {
        return this.zze;
    }

    public final zzem zzb() {
        return this.zzg == null ? zzem.zzb() : this.zzg;
    }

    public static zzes zzc() {
        return (zzes)zzet.zza.zzbA();
    }

    static zzet zzd() {
        return zzet.zza;
    }

    public final String zze() [...] // 潜在的解密器

    static void zzf(zzet zzet0, String s) {
        zzet0.zzd |= 2;
        zzet0.zzf = s;
    }

    public final boolean zzg() {
        return this.zzh;
    }

    public final boolean zzh() {
        return this.zzi;
    }

    public final boolean zzi() {
        return this.zzj;
    }

    public final boolean zzj() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzk() {
        return (this.zzd & 0x20) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzet.zzbL(zzet.zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
                }
                case 3: {
                    return new zzet();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzes(null);
                        }
                        case 5: {
                            return zzet.zza;
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

