package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

final class zzhw extends zzib {
    zzhw(zzhy zzhy0, String s, Double double0, boolean z) {
        super(zzhy0, "measurement.test.double_flag", double0, true, null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzib
    @Nullable
    final Object zza(Object object0) {
        try {
            return Double.parseDouble(((String)object0));
        }
        catch(NumberFormatException unused_ex) {
            Log.e("PhenotypeFlag", "Invalid double value for " + this.zzb + ": " + ((String)object0));
            return null;
        }
    }
}

