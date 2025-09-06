package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.android.gms.internal.measurement.zzbr;

public final class zzfk implements ServiceConnection {
    final zzfl zza;
    private final String zzb;

    zzfk(zzfl zzfl0, String s) {
        this.zza = zzfl0;
        super();
        this.zzb = s;
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        if(iBinder0 != null) {
            try {
                zzbr zzbr0 = zzbq.zzb(iBinder0);
                if(zzbr0 == null) {
                    this.zza.zza.zzaA().zzk().zza("Install Referrer Service implementation was not found");
                    return;
                }
                this.zza.zza.zzaA().zzj().zza("Install Referrer Service connected");
                this.zza.zza.zzaB().zzp(new zzfj(this, zzbr0, this));
            }
            catch(RuntimeException runtimeException0) {
                this.zza.zza.zzaA().zzk().zzb("Exception occurred while calling Install Referrer API", runtimeException0);
            }
            return;
        }
        this.zza.zza.zzaA().zzk().zza("Install Referrer connection returned with null binder");
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        this.zza.zza.zzaA().zzj().zza("Install Referrer Service disconnected");
    }
}

