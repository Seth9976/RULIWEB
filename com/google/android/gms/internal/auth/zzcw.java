package com.google.android.gms.internal.auth;

import android.util.Log;
import javax.annotation.Nullable;

final class zzcw extends zzdc {
    zzcw(zzcz zzcz0, String s, Boolean boolean0, boolean z) {
        super(zzcz0, s, boolean0, true, null);
    }

    @Override  // com.google.android.gms.internal.auth.zzdc
    @Nullable
    final Object zza(Object object0) {
        if(zzcb.zzc.matcher(((CharSequence)object0)).matches()) {
            return true;
        }
        if(zzcb.zzd.matcher(((CharSequence)object0)).matches()) {
            return false;
        }
        Log.e("PhenotypeFlag", "Invalid boolean value for " + this.zzc + ": " + ((String)object0));
        return null;
    }
}

