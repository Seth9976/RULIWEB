package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.common.zzi;
import java.util.HashMap;
import java.util.concurrent.Executor;

final class zzs extends GmsClientSupervisor {
    private final HashMap zzb;
    private final Context zzc;
    private volatile Handler zzd;
    private final zzr zze;
    private final ConnectionTracker zzf;
    private final long zzg;
    private final long zzh;
    private volatile Executor zzi;

    zzs(Context context0, Looper looper0, Executor executor0) {
        this.zzb = new HashMap();
        zzr zzr0 = new zzr(this, null);
        this.zze = zzr0;
        this.zzc = context0.getApplicationContext();
        this.zzd = new zzi(looper0, zzr0);
        this.zzf = ConnectionTracker.getInstance();
        this.zzg = 5000L;
        this.zzh = 300000L;
        this.zzi = executor0;
    }

    @Override  // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final void zza(zzo zzo0, ServiceConnection serviceConnection0, String s) {
        Preconditions.checkNotNull(serviceConnection0, "ServiceConnection must not be null");
        synchronized(this.zzb) {
            zzp zzp0 = (zzp)this.zzb.get(zzo0);
            if(zzp0 != null) {
                if(!zzp0.zzh(serviceConnection0)) {
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + zzo0.toString());
                }
                zzp0.zzf(serviceConnection0, s);
                if(zzp0.zzi()) {
                    Message message0 = this.zzd.obtainMessage(0, zzo0);
                    this.zzd.sendMessageDelayed(message0, this.zzg);
                }
                return;
            }
        }
        throw new IllegalStateException("Nonexistent connection status for service config: " + zzo0.toString());
    }

    @Override  // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final boolean zzc(zzo zzo0, ServiceConnection serviceConnection0, String s, Executor executor0) {
        Preconditions.checkNotNull(serviceConnection0, "ServiceConnection must not be null");
        synchronized(this.zzb) {
            zzp zzp0 = (zzp)this.zzb.get(zzo0);
            if(executor0 == null) {
                executor0 = this.zzi;
            }
            if(zzp0 == null) {
                zzp0 = new zzp(this, zzo0);
                zzp0.zzd(serviceConnection0, serviceConnection0, s);
                zzp0.zze(s, executor0);
                this.zzb.put(zzo0, zzp0);
                return zzp0.zzj();
            }
            this.zzd.removeMessages(0, zzo0);
            if(!zzp0.zzh(serviceConnection0)) {
                zzp0.zzd(serviceConnection0, serviceConnection0, s);
                switch(zzp0.zza()) {
                    case 1: {
                        serviceConnection0.onServiceConnected(zzp0.zzb(), zzp0.zzc());
                        break;
                    }
                    case 2: {
                        zzp0.zze(s, executor0);
                    }
                }
                return zzp0.zzj();
            }
        }
        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + zzo0.toString());
    }

    static long zzd(zzs zzs0) {
        return zzs0.zzh;
    }

    static Context zze(zzs zzs0) {
        return zzs0.zzc;
    }

    static Handler zzf(zzs zzs0) {
        return zzs0.zzd;
    }

    static ConnectionTracker zzg(zzs zzs0) {
        return zzs0.zzf;
    }

    final void zzi(Executor executor0) {
        synchronized(this.zzb) {
            this.zzi = executor0;
        }
    }

    final void zzj(Looper looper0) {
        synchronized(this.zzb) {
            this.zzd = new zzi(looper0, this.zze);
        }
    }
}

