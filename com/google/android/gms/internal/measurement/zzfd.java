package com.google.android.gms.internal.measurement;

public final class zzfd extends zzlb implements zzmj {
    private static final zzfd zza;
    private int zzd;
    private String zze;
    private boolean zzf;
    private boolean zzg;
    private int zzh;

    static {
        zzfd zzfd0 = new zzfd();
        zzfd.zza = zzfd0;
        zzlb.zzbO(zzfd.class, zzfd0);
    }

    private zzfd() {
        this.zze = "";
    }

    public final int zza() {
        return this.zzh;
    }

    static zzfd zzb() {
        return zzfd.zza;
    }

    public final String zzc() [...] // 潜在的解密器

    static void zzd(zzfd zzfd0, String s) {
        s.getClass();
        zzfd0.zzd |= 1;
        zzfd0.zze = s;
    }

    public final boolean zze() {
        return this.zzf;
    }

    public final boolean zzf() {
        return this.zzg;
    }

    public final boolean zzg() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 8) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfd.zzbL(zzfd.zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
                }
                case 3: {
                    return new zzfd();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfc(null);
                        }
                        case 5: {
                            return zzfd.zza;
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

