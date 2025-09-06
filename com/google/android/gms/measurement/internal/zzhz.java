package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

final class zzhz implements zzlo {
    final zzik zza;

    zzhz(zzik zzik0) {
        this.zza = zzik0;
        super();
    }

    @Override  // com.google.android.gms.measurement.internal.zzlo
    public final void zza(String s, String s1, Bundle bundle0) {
        if(!TextUtils.isEmpty(s)) {
            this.zza.zzF("auto", "_err", bundle0, s);
            return;
        }
        this.zza.zzD("auto", "_err", bundle0);
    }
}

