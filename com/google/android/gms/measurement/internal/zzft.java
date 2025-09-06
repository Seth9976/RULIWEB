package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzo;
import java.util.Map;

final class zzft implements zzo {
    final String zza;
    final zzfu zzb;

    zzft(zzfu zzfu0, String s) {
        this.zzb = zzfu0;
        this.zza = s;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzo
    public final String zza(String s) {
        Map map0 = (Map)zzfu.zzj(this.zzb).get(this.zza);
        return map0 == null || !map0.containsKey(s) ? null : ((String)map0.get(s));
    }
}

