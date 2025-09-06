package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.CheckForNull;

final class zziq implements zzim, Serializable {
    final Object zza;

    zziq(Object object0) {
        this.zza = object0;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(@CheckForNull Object object0) {
        return object0 instanceof zziq ? zzih.zza(this.zza, ((zziq)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    @Override
    public final String toString() {
        return "Suppliers.ofInstance(" + this.zza.toString() + ")";
    }

    @Override  // com.google.android.gms.internal.measurement.zzim
    public final Object zza() {
        return this.zza;
    }
}

