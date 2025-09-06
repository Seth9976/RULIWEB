package com.google.android.gms.internal.measurement;

public final class zzfz extends zzlb implements zzmj {
    private static final zzfz zza;
    private int zzd;
    private String zze;
    private String zzf;
    private zzfn zzg;

    static {
        zzfz zzfz0 = new zzfz();
        zzfz.zza = zzfz0;
        zzlb.zzbO(zzfz.class, zzfz0);
    }

    private zzfz() {
        this.zze = "";
        this.zzf = "";
    }

    static zzfz zza() {
        return zzfz.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfz.zzbL(zzfz.zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
                }
                case 3: {
                    return new zzfz();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfy(null);
                        }
                        case 5: {
                            return zzfz.zza;
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

