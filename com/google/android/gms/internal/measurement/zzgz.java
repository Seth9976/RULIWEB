package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzgz extends ContentObserver {
    zzgz(Handler handler0) {
        super(null);
    }

    @Override  // android.database.ContentObserver
    public final void onChange(boolean z) {
        zzha.zzb().set(true);
    }
}

