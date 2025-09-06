package com.google.android.gms.internal.measurement;

import java.io.Serializable;

public final class zzir {
    public static zzim zza(zzim zzim0) {
        if(!(zzim0 instanceof zzip) && !(zzim0 instanceof zzin)) {
            return zzim0 instanceof Serializable ? new zzin(zzim0) : new zzip(zzim0);
        }
        return zzim0;
    }

    public static zzim zzb(Object object0) {
        return new zziq(object0);
    }
}

