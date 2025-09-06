package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;

public final class zzf implements Handler.Callback {
    public final zzm zza;

    public zzf(zzm zzm0) {
        this.zza = zzm0;
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        zzp zzp0;
        zzm zzm0 = this.zza;
        int v = message0.arg1;
        if(Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Received response to request: " + v);
        }
        synchronized(zzm0) {
            zzp0 = (zzp)zzm0.zze.get(v);
            if(zzp0 == null) {
                Log.w("MessengerIpcClient", "Received response for unknown request: " + v);
                return true;
            }
            zzm0.zze.remove(v);
            zzm0.zzf();
        }
        Bundle bundle0 = message0.getData();
        if(bundle0.getBoolean("unsupported", false)) {
            zzp0.zzc(new zzq(4, "Not supported by GmsCore", null));
            return true;
        }
        zzp0.zza(bundle0);
        return true;
    }
}

