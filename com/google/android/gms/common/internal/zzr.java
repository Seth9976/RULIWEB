package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;

final class zzr implements Handler.Callback {
    final zzs zza;

    zzr(zzs zzs0, zzq zzq0) {
        this.zza = zzs0;
        super();
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        switch(message0.what) {
            case 0: {
                HashMap hashMap0 = this.zza.zzb;
                synchronized(hashMap0) {
                    zzo zzo0 = (zzo)message0.obj;
                    zzp zzp0 = (zzp)this.zza.zzb.get(zzo0);
                    if(zzp0 != null && zzp0.zzi()) {
                        if(zzp0.zzj()) {
                            zzp0.zzg("GmsClientSupervisor");
                        }
                        this.zza.zzb.remove(zzo0);
                    }
                    return true;
                }
            }
            case 1: {
                HashMap hashMap1 = this.zza.zzb;
                synchronized(hashMap1) {
                    zzo zzo1 = (zzo)message0.obj;
                    zzp zzp1 = (zzp)this.zza.zzb.get(zzo1);
                    if(zzp1 != null && zzp1.zza() == 3) {
                        Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + zzo1, new Exception());
                        ComponentName componentName0 = zzp1.zzb() == null ? zzo1.zza() : zzp1.zzb();
                        if(componentName0 == null) {
                            String s = zzo1.zzc();
                            Preconditions.checkNotNull(s);
                            componentName0 = new ComponentName(s, "unknown");
                        }
                        zzp1.onServiceDisconnected(componentName0);
                    }
                    return true;
                }
            }
            default: {
                return false;
            }
        }
    }
}

