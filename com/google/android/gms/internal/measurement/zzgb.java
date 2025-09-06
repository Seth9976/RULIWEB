package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgb extends zzlb implements zzmj {
    private static final zzgb zza;
    private zzli zzd;

    static {
        zzgb zzgb0 = new zzgb();
        zzgb.zza = zzgb0;
        zzlb.zzbO(zzgb.class, zzgb0);
    }

    private zzgb() {
        this.zzd = zzgb.zzbH();
    }

    public static zzga zza() {
        return (zzga)zzgb.zza.zzbA();
    }

    static zzgb zzb() {
        return zzgb.zza;
    }

    public final zzgd zzc(int v) {
        return (zzgd)this.zzd.get(0);
    }

    public final List zzd() {
        return this.zzd;
    }

    static void zze(zzgb zzgb0, zzgd zzgd0) {
        zzgd0.getClass();
        zzli zzli0 = zzgb0.zzd;
        if(!zzli0.zzc()) {
            zzgb0.zzd = zzlb.zzbI(zzli0);
        }
        zzgb0.zzd.add(zzgd0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        switch(v - 1) {
            case 0: {
                return (byte)1;
            }
            case 2: {
                return zzgb.zzbL(zzgb.zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001B", new Object[]{"zzd", zzgd.class});
            }
            case 3: {
                return new zzgb();
            }
            case 4: {
                return new zzga(null);
            }
            case 5: {
                return zzgb.zza;
            }
            default: {
                return null;
            }
        }
    }
}

