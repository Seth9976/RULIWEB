package com.google.android.gms.internal.measurement;

public final class zzfn extends zzlb implements zzmj {
    private static final zzfn zza;
    private int zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;

    static {
        zzfn zzfn0 = new zzfn();
        zzfn.zza = zzfn0;
        zzlb.zzbO(zzfn.class, zzfn0);
    }

    private zzfn() {
        this.zze = "";
        this.zzf = "";
        this.zzg = "";
        this.zzh = "";
        this.zzi = "";
        this.zzj = "";
        this.zzk = "";
    }

    static zzfn zza() {
        return zzfn.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfn.zzbL(zzfn.zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
                }
                case 3: {
                    return new zzfn();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfm(null);
                        }
                        case 5: {
                            return zzfn.zza;
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

