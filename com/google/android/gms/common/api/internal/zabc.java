package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;

final class zabc extends zau {
    final zabe zaa;

    zabc(zabe zabe0, Looper looper0) {
        this.zaa = zabe0;
        super(looper0);
    }

    @Override  // android.os.Handler
    public final void handleMessage(Message message0) {
        switch(message0.what) {
            case 1: {
                zabe.zaj(this.zaa);
                return;
            }
            case 2: {
                zabe.zai(this.zaa);
                return;
            }
            default: {
                Log.w("GoogleApiClientImpl", "Unknown message id: " + message0.what);
            }
        }
    }
}

