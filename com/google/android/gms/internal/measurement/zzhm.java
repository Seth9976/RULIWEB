package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzhm extends ContentObserver {
    zzhm(zzhn zzhn0, Handler handler0) {
        super(null);
    }

    @Override  // android.database.ContentObserver
    public final void onChange(boolean z) {
        zzib.zzc();
    }
}

