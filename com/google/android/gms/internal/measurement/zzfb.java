package com.google.android.gms.internal.measurement;

public final class zzfb extends zzlb implements zzmj {
    private static final zzfb zza;
    private int zzd;
    private String zze;
    private zzli zzf;
    private boolean zzg;

    static {
        zzfb zzfb0 = new zzfb();
        zzfb.zza = zzfb0;
        zzlb.zzbO(zzfb.class, zzfb0);
    }

    private zzfb() {
        this.zze = "";
        this.zzf = zzfb.zzbH();
    }

    static zzfb zza() {
        return zzfb.zza;
    }

    public final String zzb() [...] // 潜在的解密器

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfb.zzbL(zzfb.zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001B\u0003ဇ\u0001", new Object[]{"zzd", "zze", "zzf", zzfh.class, "zzg"});
                }
                case 3: {
                    return new zzfb();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfa(null);
                        }
                        case 5: {
                            return zzfb.zza;
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

