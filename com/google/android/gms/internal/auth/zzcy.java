package com.google.android.gms.internal.auth;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;

final class zzcy extends zzdc {
    final zzhy zza;

    zzcy(zzcz zzcz0, String s, Object object0, boolean z, zzhy zzhy0) {
        this.zza = zzhy0;
        super(zzcz0, "getTokenRefactor__blocked_packages", object0, true, null);
    }

    @Override  // com.google.android.gms.internal.auth.zzdc
    final Object zza(Object object0) {
        try {
            return zzhs.zzp(Base64.decode(((String)object0), 3));
        }
        catch(IOException | IllegalArgumentException unused_ex) {
            Log.e("PhenotypeFlag", "Invalid byte[] value for " + this.zzc + ": " + ((String)object0));
            return null;
        }
    }
}

