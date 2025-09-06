package com.google.android.gms.internal.measurement;

public final class zzfj extends zzlb implements zzmj {
    private static final zzfj zza;
    private int zzd;
    private String zze;
    private String zzf;

    static {
        zzfj zzfj0 = new zzfj();
        zzfj.zza = zzfj0;
        zzlb.zzbO(zzfj.class, zzfj0);
    }

    private zzfj() {
        this.zze = "";
        this.zzf = "";
    }

    static zzfj zza() {
        return zzfj.zza;
    }

    public final String zzb() [...] // 潜在的解密器

    public final String zzc() [...] // 潜在的解密器

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfj.zzbL(zzfj.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
                }
                case 3: {
                    return new zzfj();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfi(null);
                        }
                        case 5: {
                            return zzfj.zza;
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

