package com.google.android.gms.auth.api.signin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zbt;

@Deprecated
public final class RevocationBoundService extends Service {
    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        if(!"com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(intent0.getAction()) && !"com.google.android.gms.auth.api.signin.RevocationBoundService.clearClientState".equals(intent0.getAction())) {
            Log.w("RevocationService", "Unknown action sent to RevocationBoundService: " + intent0.getAction());
            return null;
        }
        if(Log.isLoggable("RevocationService", 2)) {
            Log.v("RevocationService", "RevocationBoundService handling " + intent0.getAction());
        }
        return new zbt(this);
    }
}

