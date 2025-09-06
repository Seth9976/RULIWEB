package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgr extends zzlb implements zzmj {
    private static final zzgr zza;
    private int zzd;
    private String zze;
    private zzli zzf;

    static {
        zzgr zzgr0 = new zzgr();
        zzgr.zza = zzgr0;
        zzlb.zzbO(zzgr.class, zzgr0);
    }

    private zzgr() {
        this.zze = "";
        this.zzf = zzgr.zzbH();
    }

    static zzgr zza() {
        return zzgr.zza;
    }

    public final String zzb() [...] // 潜在的解密器

    public final List zzc() {
        return this.zzf;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgr.zzbL(zzgr.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001B", new Object[]{"zzd", "zze", "zzf", zzgy.class});
                }
                case 3: {
                    return new zzgr();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzgq(null);
                        }
                        case 5: {
                            return zzgr.zza;
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

