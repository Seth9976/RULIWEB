package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzhg;

final class zzdw extends zzch {
    private final zzhg zza;

    zzdw(zzhg zzhg0) {
        this.zza = zzhg0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzci
    public final int zzd() {
        return System.identityHashCode(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzci
    public final void zze(String s, String s1, Bundle bundle0, long v) {
        this.zza.onEvent(s, s1, bundle0, v);
    }
}

