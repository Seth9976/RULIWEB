package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzjg extends zzjb {
    final transient Object zza;

    zzjg(Object object0) {
        object0.getClass();
        this.zza = object0;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    public final boolean contains(@CheckForNull Object object0) {
        return this.zza.equals(object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    public final Iterator iterator() {
        return new zzjc(this.zza);
    }

    @Override
    public final int size() {
        return 1;
    }

    @Override
    public final String toString() {
        return "[" + this.zza.toString() + "]";
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zza(Object[] arr_object, int v) {
        arr_object[0] = this.zza;
        return 1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjb
    public final zzjh zzd() {
        return new zzjc(this.zza);
    }
}

