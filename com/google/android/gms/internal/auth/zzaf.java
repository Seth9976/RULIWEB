package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;

final class zzaf extends zzah {
    final zzag zza;

    zzaf(zzag zzag0) {
        this.zza = zzag0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzah
    public final void zzc(boolean z) {
        this.zza.setResult(new zzak((z ? Status.RESULT_SUCCESS : zzal.zza())));
    }
}

