package com.google.android.gms.common;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;

final class zad extends zau {
    final GoogleApiAvailability zaa;
    private final Context zab;

    public zad(GoogleApiAvailability googleApiAvailability0, Context context0) {
        this.zaa = googleApiAvailability0;
        super((Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper()));
        this.zab = context0.getApplicationContext();
    }

    @Override  // android.os.Handler
    public final void handleMessage(Message message0) {
        if(message0.what != 1) {
            Log.w("GoogleApiAvailability", "Don\'t know how to handle this message: " + message0.what);
            return;
        }
        int v = this.zaa.isGooglePlayServicesAvailable(this.zab);
        if(this.zaa.isUserResolvableError(v)) {
            this.zaa.showErrorNotification(this.zab, v);
        }
    }
}

