package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzjf extends zzjb {
    static final zzjf zza;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private static final Object[] zzd;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] arr_object = new Object[0];
        zzjf.zzd = arr_object;
        zzjf.zza = new zzjf(arr_object, 0, arr_object, 0, 0);
    }

    zzjf(Object[] arr_object, int v, Object[] arr_object1, int v1, int v2) {
        this.zzb = arr_object;
        this.zze = v;
        this.zzc = arr_object1;
        this.zzf = v1;
        this.zzg = v2;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    public final boolean contains(@CheckForNull Object object0) {
        Object[] arr_object = this.zzc;
        if(object0 != null && arr_object.length != 0) {
            for(int v = zzit.zza(object0.hashCode()); true; v = v1 + 1) {
                int v1 = v & this.zzf;
                Object object1 = arr_object[v1];
                if(object1 == null) {
                    return false;
                }
                if(object1.equals(object0)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    public final int hashCode() {
        return this.zze;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    public final Iterator iterator() {
        return this.zzg().zzk(0);
    }

    @Override
    public final int size() {
        return this.zzg;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzb, 0, arr_object, 0, this.zzg);
        return this.zzg;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zzb() {
        return this.zzg;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    public final zzjh zzd() {
        return this.zzg().zzk(0);
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final Object[] zze() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    final zzja zzh() {
        return zzja.zzg(this.zzb, this.zzg);
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    final boolean zzj() {
        return true;
    }
}

