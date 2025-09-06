package com.google.android.gms.internal.measurement;

public final class zzer extends zzlb implements zzmj {
    private static final zzer zza;
    private int zzd;
    private int zze;
    private boolean zzf;
    private String zzg;
    private String zzh;
    private String zzi;

    static {
        zzer zzer0 = new zzer();
        zzer.zza = zzer0;
        zzlb.zzbO(zzer.class, zzer0);
    }

    private zzer() {
        this.zzg = "";
        this.zzh = "";
        this.zzi = "";
    }

    static zzer zza() {
        return zzer.zza;
    }

    public static zzer zzb() {
        return zzer.zza;
    }

    public final String zzc() [...] // 潜在的解密器

    public final String zzd() [...] // 潜在的解密器

    public final String zze() [...] // 潜在的解密器

    public final boolean zzf() {
        return this.zzf;
    }

    public final boolean zzg() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzj() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzk() {
        return (this.zzd & 8) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzer.zzbL(zzer.zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zzd", "zze", zzep.zza, "zzf", "zzg", "zzh", "zzi"});
                }
                case 3: {
                    return new zzer();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzen(null);
                        }
                        case 5: {
                            return zzer.zza;
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

    public final int zzm() {
        int v = zzeq.zza(this.zze);
        return v == 0 ? 1 : v;
    }
}

