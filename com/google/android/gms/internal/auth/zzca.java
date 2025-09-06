package com.google.android.gms.internal.auth;

import android.database.ContentObserver;
import android.os.Handler;

final class zzca extends ContentObserver {
    zzca(Handler handler0) {
        super(null);
    }

    @Override  // android.database.ContentObserver
    public final void onChange(boolean z) {
        zzcb.zzb().set(true);
    }
}

