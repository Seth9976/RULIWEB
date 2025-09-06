package com.google.android.gms.internal.auth;

import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.CheckForNull;

final class zzdn implements zzdj, Serializable {
    final Object zza;

    zzdn(Object object0) {
        this.zza = object0;
    }

    @Override
    public final boolean equals(@CheckForNull Object object0) {
        if(object0 instanceof zzdn) {
            Object object1 = ((zzdn)object0).zza;
            return this.zza == object1 || this.zza.equals(object1);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    @Override
    public final String toString() {
        return "Suppliers.ofInstance(" + this.zza.toString() + ")";
    }

    @Override  // com.google.android.gms.internal.auth.zzdj
    public final Object zza() {
        return this.zza;
    }
}

