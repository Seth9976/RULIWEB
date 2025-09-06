package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zabx extends BroadcastReceiver {
    Context zaa;
    private final zabw zab;

    public zabx(zabw zabw0) {
        this.zab = zabw0;
    }

    @Override  // android.content.BroadcastReceiver
    public final void onReceive(Context context0, Intent intent0) {
        Uri uri0 = intent0.getData();
        if("com.google.android.gms".equals((uri0 == null ? null : uri0.getSchemeSpecificPart()))) {
            this.zab.zaa();
            this.zab();
        }
    }

    public final void zaa(Context context0) {
        this.zaa = context0;
    }

    public final void zab() {
        synchronized(this) {
            Context context0 = this.zaa;
            if(context0 != null) {
                context0.unregisterReceiver(this);
            }
            this.zaa = null;
        }
    }
}

