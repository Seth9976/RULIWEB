package com.google.android.gms.cloudmessaging;

import android.os.Bundle;

final class zzo extends zzp {
    zzo(int v, int v1, Bundle bundle0) {
        super(v, 2, bundle0);
    }

    @Override  // com.google.android.gms.cloudmessaging.zzp
    final void zza(Bundle bundle0) {
        if(bundle0.getBoolean("ack", false)) {
            this.zzd(null);
            return;
        }
        this.zzc(new zzq(4, "Invalid response to one way request", null));
    }

    @Override  // com.google.android.gms.cloudmessaging.zzp
    final boolean zzb() {
        return true;
    }
}

