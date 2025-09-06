package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzhe extends ContentObserver {
    final zzhf zza;

    zzhe(zzhf zzhf0, Handler handler0) {
        this.zza = zzhf0;
        super(null);
    }

    @Override  // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zza.zzf();
    }
}

