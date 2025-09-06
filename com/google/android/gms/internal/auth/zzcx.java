package com.google.android.gms.internal.auth;

import android.util.Log;
import javax.annotation.Nullable;

final class zzcx extends zzdc {
    zzcx(zzcz zzcz0, String s, Double double0, boolean z) {
        super(zzcz0, s, double0, true, null);
    }

    @Override  // com.google.android.gms.internal.auth.zzdc
    @Nullable
    final Object zza(Object object0) {
        try {
            return Double.parseDouble(((String)object0));
        }
        catch(NumberFormatException unused_ex) {
            Log.e("PhenotypeFlag", "Invalid double value for " + this.zzc + ": " + ((String)object0));
            return null;
        }
    }
}

