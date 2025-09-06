package com.google.android.gms.internal.measurement;

public final class zzgg extends zzlb implements zzmj {
    private static final zzgg zza;
    private int zzd;
    private int zze;
    private zzli zzf;

    static {
        zzgg zzgg0 = new zzgg();
        zzgg.zza = zzgg0;
        zzlb.zzbO(zzgg.class, zzgg0);
    }

    private zzgg() {
        this.zze = 1;
        this.zzf = zzgg.zzbH();
    }

    public static zzge zza() {
        return (zzge)zzgg.zza.zzbA();
    }

    static zzgg zzb() {
        return zzgg.zza;
    }

    static void zzc(zzgg zzgg0, zzfv zzfv0) {
        zzfv0.getClass();
        zzli zzli0 = zzgg0.zzf;
        if(!zzli0.zzc()) {
            zzgg0.zzf = zzlb.zzbI(zzli0);
        }
        zzgg0.zzf.add(zzfv0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgg.zzbL(zzgg.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001B", new Object[]{"zzd", "zze", zzgf.zza, "zzf", zzfv.class});
                }
                case 3: {
                    return new zzgg();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzge(null);
                        }
                        case 5: {
                            return zzgg.zza;
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

