package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzhf;

final class zzdv extends zzch {
    private final zzhf zza;

    zzdv(zzhf zzhf0) {
        this.zza = zzhf0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzci
    public final int zzd() {
        return System.identityHashCode(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzci
    public final void zze(String s, String s1, Bundle bundle0, long v) {
        this.zza.interceptEvent(s, s1, bundle0, v);
    }
}

