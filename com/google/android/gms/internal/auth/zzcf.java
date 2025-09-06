package com.google.android.gms.internal.auth;

import android.database.ContentObserver;
import android.os.Handler;

final class zzcf extends ContentObserver {
    final zzcg zza;

    zzcf(zzcg zzcg0, Handler handler0) {
        this.zza = zzcg0;
        super(null);
    }

    @Override  // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zza.zze();
    }
}

