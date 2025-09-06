package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

final class zzks {
    final zzng zza;
    private static final zzks zzb;
    private boolean zzc;
    private boolean zzd;

    static {
        zzks.zzb = new zzks(true);
    }

    private zzks() {
        this.zza = new zzmw(16);
    }

    private zzks(boolean z) {
        zzmw zzmw0 = new zzmw(0);
        super();
        this.zza = zzmw0;
        this.zzb();
        this.zzb();
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        zzks zzks0 = new zzks();
        for(int v = 0; v < this.zza.zzb(); ++v) {
            Map.Entry map$Entry0 = this.zza.zzg(v);
            zzks0.zzc(((zzkr)map$Entry0.getKey()), map$Entry0.getValue());
        }
        for(Object object0: this.zza.zzc()) {
            zzks0.zzc(((zzkr)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
        }
        zzks0.zzd = this.zzd;
        return zzks0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzks ? this.zza.equals(((zzks)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode();
    }

    public static zzks zza() {
        throw null;
    }

    public final void zzb() {
        if(!this.zzc) {
            for(int v = 0; v < this.zza.zzb(); ++v) {
                Map.Entry map$Entry0 = this.zza.zzg(v);
                if(map$Entry0.getValue() instanceof zzlb) {
                    ((zzlb)map$Entry0.getValue()).zzbM();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzc(zzkr zzkr0, Object object0) {
        if(zzkr0.zzc()) {
            if(!(object0 instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList0 = new ArrayList();
            arrayList0.addAll(((List)object0));
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                zzks.zzd(zzkr0, arrayList0.get(v1));
            }
            object0 = arrayList0;
        }
        else {
            zzks.zzd(zzkr0, object0);
        }
        if(object0 instanceof zzln) {
            this.zzd = true;
        }
        this.zza.zze(zzkr0, object0);
    }

    private static final void zzd(zzkr zzkr0, Object object0) {
        boolean z;
        zzoa zzoa0 = zzkr0.zzb();
        object0.getClass();
        switch(zzoa0.zza().ordinal()) {
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
                if(object0 instanceof zzka || object0 instanceof byte[]) {
                    return;
                }
                break;
            }
            case 7: {
                if(object0 instanceof Integer || object0 instanceof zzld) {
                    return;
                }
                break;
            }
            case 8: {
                if(object0 instanceof zzmi || object0 instanceof zzln) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", zzkr0.zza(), zzkr0.zzb().zza(), object0.getClass().getName()));
    }
}

