package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

final class zzhu extends zzib {
    zzhu(zzhy zzhy0, String s, Long long0, boolean z) {
        super(zzhy0, s, long0, true, null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzib
    @Nullable
    final Object zza(Object object0) {
        try {
            return Long.parseLong(((String)object0));
        }
        catch(NumberFormatException unused_ex) {
            Log.e("PhenotypeFlag", "Invalid long value for " + this.zzb + ": " + ((String)object0));
            return null;
        }
    }
}

