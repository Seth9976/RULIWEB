package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode.VmPolicy.Builder;
import android.os.StrictMode.VmPolicy;
import android.os.StrictMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

final class zzp implements ServiceConnection, zzt {
    final zzs zza;
    private final Map zzb;
    private int zzc;
    private boolean zzd;
    private IBinder zze;
    private final zzo zzf;
    private ComponentName zzg;

    public zzp(zzs zzs0, zzo zzo0) {
        this.zza = zzs0;
        super();
        this.zzf = zzo0;
        this.zzb = new HashMap();
        this.zzc = 2;
    }

    @Override  // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName0) {
        this.onServiceDisconnected(componentName0);
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        synchronized(zzs.zzh(this.zza)) {
            zzs.zzf(this.zza).removeMessages(1, this.zzf);
            this.zze = iBinder0;
            this.zzg = componentName0;
            for(Object object0: this.zzb.values()) {
                ((ServiceConnection)object0).onServiceConnected(componentName0, iBinder0);
            }
            this.zzc = 1;
        }
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        synchronized(zzs.zzh(this.zza)) {
            zzs.zzf(this.zza).removeMessages(1, this.zzf);
            this.zze = null;
            this.zzg = componentName0;
            for(Object object0: this.zzb.values()) {
                ((ServiceConnection)object0).onServiceDisconnected(componentName0);
            }
            this.zzc = 2;
        }
    }

    public final int zza() {
        return this.zzc;
    }

    public final ComponentName zzb() {
        return this.zzg;
    }

    public final IBinder zzc() {
        return this.zze;
    }

    public final void zzd(ServiceConnection serviceConnection0, ServiceConnection serviceConnection1, String s) {
        this.zzb.put(serviceConnection0, serviceConnection1);
    }

    public final void zze(String s, Executor executor0) {
        this.zzc = 3;
        StrictMode.VmPolicy strictMode$VmPolicy0 = StrictMode.getVmPolicy();
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(strictMode$VmPolicy0).permitUnsafeIntentLaunch().build());
        try {
            Intent intent0 = this.zzf.zzb(zzs.zze(this.zza));
            boolean z = zzs.zzg(this.zza).zza(zzs.zze(this.zza), s, intent0, this, 0x1081, executor0);
            this.zzd = z;
            if(z) {
                Message message0 = zzs.zzf(this.zza).obtainMessage(1, this.zzf);
                zzs.zzf(this.zza).sendMessageDelayed(message0, zzs.zzd(this.zza));
            }
            else {
                try {
                    this.zzc = 2;
                    zzs.zzg(this.zza).unbindService(zzs.zze(this.zza), this);
                }
                catch(IllegalArgumentException unused_ex) {
                }
            }
        }
        finally {
            StrictMode.setVmPolicy(strictMode$VmPolicy0);
        }
    }

    public final void zzf(ServiceConnection serviceConnection0, String s) {
        this.zzb.remove(serviceConnection0);
    }

    public final void zzg(String s) {
        zzs.zzf(this.zza).removeMessages(1, this.zzf);
        zzs.zzg(this.zza).unbindService(zzs.zze(this.zza), this);
        this.zzd = false;
        this.zzc = 2;
    }

    public final boolean zzh(ServiceConnection serviceConnection0) {
        return this.zzb.containsKey(serviceConnection0);
    }

    public final boolean zzi() {
        return this.zzb.isEmpty();
    }

    public final boolean zzj() {
        return this.zzd;
    }
}

