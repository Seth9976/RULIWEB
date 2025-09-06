package com.google.android.gms.internal.auth;

import android.util.Log;
import javax.annotation.Nullable;

final class zzcv extends zzdc {
    zzcv(zzcz zzcz0, String s, Long long0, boolean z) {
        super(zzcz0, s, long0, true, null);
    }

    @Override  // com.google.android.gms.internal.auth.zzdc
    @Nullable
    final Object zza(Object object0) {
        try {
            return Long.parseLong(((String)object0));
        }
        catch(NumberFormatException unused_ex) {
            Log.e("PhenotypeFlag", "Invalid long value for " + this.zzc + ": " + ((String)object0));
            return null;
        }
    }
}

