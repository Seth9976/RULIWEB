package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzei extends zzlb implements zzmj {
    private static final zzei zza;
    private int zzd;
    private int zze;
    private zzli zzf;
    private zzli zzg;
    private boolean zzh;
    private boolean zzi;

    static {
        zzei zzei0 = new zzei();
        zzei.zza = zzei0;
        zzlb.zzbO(zzei.class, zzei0);
    }

    private zzei() {
        this.zzf = zzei.zzbH();
        this.zzg = zzei.zzbH();
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzg.size();
    }

    public final int zzc() {
        return this.zzf.size();
    }

    static zzei zzd() {
        return zzei.zza;
    }

    public final zzek zze(int v) {
        return (zzek)this.zzg.get(v);
    }

    public final zzet zzf(int v) {
        return (zzet)this.zzf.get(v);
    }

    public final List zzg() {
        return this.zzg;
    }

    public final List zzh() {
        return this.zzf;
    }

    static void zzi(zzei zzei0, int v, zzet zzet0) {
        zzet0.getClass();
        zzli zzli0 = zzei0.zzf;
        if(!zzli0.zzc()) {
            zzei0.zzf = zzlb.zzbI(zzli0);
        }
        zzei0.zzf.set(v, zzet0);
    }

    static void zzj(zzei zzei0, int v, zzek zzek0) {
        zzek0.getClass();
        zzli zzli0 = zzei0.zzg;
        if(!zzli0.zzc()) {
            zzei0.zzg = zzlb.zzbI(zzli0);
        }
        zzei0.zzg.set(v, zzek0);
    }

    public final boolean zzk() {
        return (this.zzd & 1) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzei.zzbL(zzei.zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001B\u0003\u001B\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zzd", "zze", "zzf", zzet.class, "zzg", zzek.class, "zzh", "zzi"});
                }
                case 3: {
                    return new zzei();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzeh(null);
                        }
                        case 5: {
                            return zzei.zza;
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

