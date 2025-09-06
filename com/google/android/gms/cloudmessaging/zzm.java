package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.cloudmessaging.zzf;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import jeb.synthetic.FIN;

final class zzm implements ServiceConnection {
    int zza;
    final Messenger zzb;
    zzn zzc;
    final Queue zzd;
    final SparseArray zze;
    final zzs zzf;

    zzm(zzs zzs0, zzl zzl0) {
        this.zzf = zzs0;
        super();
        this.zza = 0;
        this.zzb = new Messenger(new zzf(Looper.getMainLooper(), new com.google.android.gms.cloudmessaging.zzf(this)));
        this.zzd = new ArrayDeque();
        this.zze = new SparseArray();
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        if(Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        zzj zzj0 = new zzj(this, iBinder0);
        zzs.zze(this.zzf).execute(zzj0);
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        if(Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zzg zzg0 = () -> synchronized(this) {
            this.zzb(2, "Service disconnected", null);
        };
        zzs.zze(this.zzf).execute(zzg0);
    }

    // 检测为 Lambda 实现
    final void zza(int v, String s) [...]

    final void zzb(int v, String s, Throwable throwable0) {
        synchronized(this) {
            if(Log.isLoggable("MessengerIpcClient", 3)) {
                String s1 = String.valueOf(s);
                Log.d("MessengerIpcClient", (s1.length() == 0 ? new String("Disconnected: ") : "Disconnected: " + s1));
            }
            int v2 = this.zza;
            if(v2 != 0) {
                switch(v2) {
                    case 1: 
                    case 2: {
                        if(Log.isLoggable("MessengerIpcClient", 2)) {
                            Log.v("MessengerIpcClient", "Unbinding service");
                        }
                        this.zza = 4;
                        ConnectionTracker.getInstance().unbindService(zzs.zza(this.zzf), this);
                        zzq zzq0 = new zzq(v, s, throwable0);
                        for(Object object0: this.zzd) {
                            ((zzp)object0).zzc(zzq0);
                        }
                        this.zzd.clear();
                        for(int v3 = 0; v3 < this.zze.size(); ++v3) {
                            ((zzp)this.zze.valueAt(v3)).zzc(zzq0);
                        }
                        this.zze.clear();
                        return;
                    }
                    case 3: {
                        this.zza = 4;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
        }
        throw new IllegalStateException();
    }

    final void zzc() {
        zzh zzh0 = new zzh(this);
        zzs.zze(this.zzf).execute(zzh0);
    }

    // 检测为 Lambda 实现
    final void zzd() [...]

    final void zze(int v) {
        synchronized(this) {
            zzp zzp0 = (zzp)this.zze.get(v);
            if(zzp0 != null) {
                Log.w("MessengerIpcClient", "Timing out request: " + v);
                this.zze.remove(v);
                zzp0.zzc(new zzq(3, "Timed out waiting for response", null));
                this.zzf();
            }
        }
    }

    final void zzf() {
        synchronized(this) {
            if(this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
                if(Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
                }
                this.zza = 3;
                ConnectionTracker.getInstance().unbindService(zzs.zza(this.zzf), this);
            }
        }
    }

    final boolean zzg(zzp zzp0) {
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        switch(this.zza) {
            case 0: {
                this.zzd.add(zzp0);
                Preconditions.checkState(this.zza == 0);
                if(Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                }
                this.zza = 1;
                Intent intent0 = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent0.setPackage("com.google.android.gms");
                try {
                    if(ConnectionTracker.getInstance().bindService(zzs.zza(this.zzf), intent0, this, 1)) {
                        goto label_20;
                    }
                    else {
                        this.zza(0, "Unable to bind to service");
                    }
                }
                catch(SecurityException securityException0) {
                    this.zzb(0, "Unable to bind to service", securityException0);
                }
                goto label_22;
            label_20:
                zzi zzi0 = () -> synchronized(this) {
                    if(this.zza == 1) {
                        this.zza(1, "Timed out while binding");
                    }
                };
                zzs.zze(this.zzf).schedule(zzi0, 30L, TimeUnit.SECONDS);
            label_22:
                FIN.finallyExec$NT(v);
                return true;
            }
            case 1: {
                this.zzd.add(zzp0);
                FIN.finallyExec$NT(v);
                return true;
            }
            case 2: {
                this.zzd.add(zzp0);
                this.zzc();
                FIN.finallyExec$NT(v);
                return true;
            }
            default: {
                FIN.finallyCodeBegin$NT(v);
                __monitor_exit(this);
                FIN.finallyCodeEnd$NT(v);
                return false;
            }
        }
    }
}

