package com.google.android.gms.internal.fido;

import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzay extends zzau {
    final transient Object zza;

    zzay(Object object0) {
        object0.getClass();
        this.zza = object0;
    }

    @Override
    public final boolean contains(@CheckForNull Object object0) {
        return this.zza.equals(object0);
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    public final Iterator iterator() {
        return new zzav(this.zza);
    }

    @Override
    public final int size() {
        return 1;
    }

    @Override
    public final String toString() {
        return "[" + this.zza.toString() + "]";
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zza(Object[] arr_object, int v) {
        arr_object[0] = this.zza;
        return 1;
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    public final zzaz zzd() {
        return new zzav(this.zza);
    }
}

