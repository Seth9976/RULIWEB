package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgt extends zzlb implements zzmj {
    private static final zzgt zza;
    private int zzd;
    private zzli zze;
    private zzgp zzf;

    static {
        zzgt zzgt0 = new zzgt();
        zzgt.zza = zzgt0;
        zzlb.zzbO(zzgt.class, zzgt0);
    }

    private zzgt() {
        this.zze = zzgt.zzbH();
    }

    public final zzgp zza() {
        return this.zzf == null ? zzgp.zzc() : this.zzf;
    }

    public final List zzc() {
        return this.zze;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgt.zzbL(zzgt.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001B\u0002ဉ\u0000", new Object[]{"zzd", "zze", zzgy.class, "zzf"});
                }
                case 3: {
                    return new zzgt();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzgs(null);
                        }
                        case 5: {
                            return zzgt.zza;
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

