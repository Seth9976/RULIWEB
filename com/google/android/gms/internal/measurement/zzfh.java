package com.google.android.gms.internal.measurement;

public final class zzfh extends zzlb implements zzmj {
    private static final zzfh zza;
    private int zzd;
    private String zze;
    private String zzf;

    static {
        zzfh zzfh0 = new zzfh();
        zzfh.zza = zzfh0;
        zzlb.zzbO(zzfh.class, zzfh0);
    }

    private zzfh() {
        this.zze = "";
        this.zzf = "";
    }

    static zzfh zza() {
        return zzfh.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfh.zzbL(zzfh.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
                }
                case 3: {
                    return new zzfh();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfg(null);
                        }
                        case 5: {
                            return zzfh.zza;
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

