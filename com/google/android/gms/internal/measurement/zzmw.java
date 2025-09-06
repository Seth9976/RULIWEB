package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzmw extends zzng {
    zzmw(int v) {
        super(v, null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzng
    public final void zza() {
        if(!this.zzj()) {
            for(int v = 0; v < this.zzb(); ++v) {
                Map.Entry map$Entry0 = this.zzg(v);
                if(((zzkr)map$Entry0.getKey()).zzc()) {
                    map$Entry0.setValue(Collections.unmodifiableList(((List)map$Entry0.getValue())));
                }
            }
            for(Object object0: this.zzc()) {
                Map.Entry map$Entry1 = (Map.Entry)object0;
                if(((zzkr)map$Entry1.getKey()).zzc()) {
                    map$Entry1.setValue(Collections.unmodifiableList(((List)map$Entry1.getValue())));
                }
            }
        }
        super.zza();
    }
}

