package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

final class zzeq {
    final zzgv zza;
    private static final zzeq zzb;
    private boolean zzc;
    private boolean zzd;

    static {
        zzeq.zzb = new zzeq(true);
    }

    private zzeq() {
        this.zza = new zzgl(16);
    }

    private zzeq(boolean z) {
        zzgl zzgl0 = new zzgl(0);
        super();
        this.zza = zzgl0;
        this.zzb();
        this.zzb();
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        zzeq zzeq0 = new zzeq();
        for(int v = 0; v < this.zza.zzb(); ++v) {
            Map.Entry map$Entry0 = this.zza.zzg(v);
            zzeq0.zzc(((zzep)map$Entry0.getKey()), map$Entry0.getValue());
        }
        for(Object object0: this.zza.zzc()) {
            zzeq0.zzc(((zzep)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
        }
        zzeq0.zzd = this.zzd;
        return zzeq0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzeq ? this.zza.equals(((zzeq)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode();
    }

    public static zzeq zza() {
        throw null;
    }

    public final void zzb() {
        if(!this.zzc) {
            for(int v = 0; v < this.zza.zzb(); ++v) {
                Map.Entry map$Entry0 = this.zza.zzg(v);
                if(map$Entry0.getValue() instanceof zzev) {
                    ((zzev)map$Entry0.getValue()).zzi();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzc(zzep zzep0, Object object0) {
        if(zzep0.zzc()) {
            if(!(object0 instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList0 = new ArrayList();
            arrayList0.addAll(((List)object0));
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                zzeq.zzd(zzep0, arrayList0.get(v1));
            }
            object0 = arrayList0;
        }
        else {
            zzeq.zzd(zzep0, object0);
        }
        if(object0 instanceof zzfc) {
            this.zzd = true;
        }
        this.zza.zze(zzep0, object0);
    }

    private static final void zzd(zzep zzep0, Object object0) {
        boolean z;
        zzho zzho0 = zzep0.zzb();
        object0.getClass();
        switch(zzho0.zza().ordinal()) {
            case 0: {
                z = object0 instanceof Integer;
                goto label_15;
            }
            case 1: {
                z = object0 instanceof Long;
                goto label_15;
            }
            case 2: {
                z = object0 instanceof Float;
                goto label_15;
            }
            case 3: {
                z = object0 instanceof Double;
                goto label_15;
            }
            case 4: {
                z = object0 instanceof Boolean;
                goto label_15;
            }
            case 5: {
                z = object0 instanceof String;
            label_15:
                if(z) {
                    return;
                }
                break;
            }
            case 6: {
                if(object0 instanceof zzef || object0 instanceof byte[]) {
                    return;
                }
                break;
            }
            case 7: {
                if(object0 instanceof Integer || object0 instanceof zzex) {
                    return;
                }
                break;
            }
            case 8: {
                if(object0 instanceof zzfx || object0 instanceof zzfc) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", zzep0.zza(), zzep0.zzb().zza(), object0.getClass().getName()));
    }
}

