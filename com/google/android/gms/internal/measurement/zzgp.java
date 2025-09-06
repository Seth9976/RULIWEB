package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgp extends zzlb implements zzmj {
    private static final zzgp zza;
    private zzli zzd;

    static {
        zzgp zzgp0 = new zzgp();
        zzgp.zza = zzgp0;
        zzlb.zzbO(zzgp.class, zzgp0);
    }

    private zzgp() {
        this.zzd = zzgp.zzbH();
    }

    public final int zza() {
        return this.zzd.size();
    }

    static zzgp zzb() {
        return zzgp.zza;
    }

    public static zzgp zzc() {
        return zzgp.zza;
    }

    public final List zzd() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        switch(v - 1) {
            case 0: {
                return (byte)1;
            }
            case 2: {
                return zzgp.zzbL(zzgp.zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001B", new Object[]{"zzd", zzgr.class});
            }
            case 3: {
                return new zzgp();
            }
            case 4: {
                return new zzgo(null);
            }
            case 5: {
                return zzgp.zza;
            }
            default: {
                return null;
            }
        }
    }
}

