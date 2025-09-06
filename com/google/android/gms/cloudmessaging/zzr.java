package com.google.android.gms.cloudmessaging;

import android.os.Bundle;

final class zzr extends zzp {
    zzr(int v, int v1, Bundle bundle0) {
        super(v, 1, bundle0);
    }

    @Override  // com.google.android.gms.cloudmessaging.zzp
    final void zza(Bundle bundle0) {
        Bundle bundle1 = bundle0.getBundle("data");
        if(bundle1 == null) {
            bundle1 = Bundle.EMPTY;
        }
        this.zzd(bundle1);
    }

    @Override  // com.google.android.gms.cloudmessaging.zzp
    final boolean zzb() {
        return false;
    }
}

