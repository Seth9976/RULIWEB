package com.google.android.gms.internal.measurement;

public final class zzfv extends zzlb implements zzmj {
    private static final zzfv zza;
    private int zzd;
    private String zze;
    private long zzf;

    static {
        zzfv zzfv0 = new zzfv();
        zzfv.zza = zzfv0;
        zzlb.zzbO(zzfv.class, zzfv0);
    }

    private zzfv() {
        this.zze = "";
    }

    public static zzfu zza() {
        return (zzfu)zzfv.zza.zzbA();
    }

    static zzfv zzb() {
        return zzfv.zza;
    }

    static void zzc(zzfv zzfv0, String s) {
        s.getClass();
        zzfv0.zzd |= 1;
        zzfv0.zze = s;
    }

    static void zzd(zzfv zzfv0, long v) {
        zzfv0.zzd |= 2;
        zzfv0.zzf = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfv.zzbL(zzfv.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zzd", "zze", "zzf"});
                }
                case 3: {
                    return new zzfv();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfu(null);
                        }
                        case 5: {
                            return zzfv.zza;
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

