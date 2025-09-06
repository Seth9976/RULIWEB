package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

final class zzhv extends zzib {
    zzhv(zzhy zzhy0, String s, Boolean boolean0, boolean z) {
        super(zzhy0, s, boolean0, true, null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzib
    @Nullable
    final Object zza(Object object0) {
        if(zzha.zzc.matcher(((CharSequence)object0)).matches()) {
            return true;
        }
        if(zzha.zzd.matcher(((CharSequence)object0)).matches()) {
            return false;
        }
        Log.e("PhenotypeFlag", "Invalid boolean value for " + this.zzb + ": " + ((String)object0));
        return null;
    }
}

