package com.google.android.gms.internal.common;

import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;

public class zzi extends Handler {
    private final Looper zza;

    public zzi() {
        this.zza = Looper.getMainLooper();
    }

    public zzi(Looper looper0) {
        super(looper0);
        this.zza = Looper.getMainLooper();
    }

    public zzi(Looper looper0, Handler.Callback handler$Callback0) {
        super(looper0, handler$Callback0);
        this.zza = Looper.getMainLooper();
    }
}

