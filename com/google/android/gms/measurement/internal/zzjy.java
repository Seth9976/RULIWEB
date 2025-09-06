package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

public final class zzjy implements ServiceConnection, BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    final zzjz zza;
    private volatile boolean zzb;
    private volatile zzep zzc;

    protected zzjy(zzjz zzjz0) {
        this.zza = zzjz0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized(this) {
            try {
                Preconditions.checkNotNull(this.zzc);
                zzej zzej0 = (zzej)this.zzc.getService();
                this.zza.zzt.zzaB().zzp(new zzjv(this, zzej0));
            }
            catch(DeadObjectException | IllegalStateException unused_ex) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzet zzet0 = this.zza.zzt.zzl();
        if(zzet0 != null) {
            zzet0.zzk().zzb("Service connection failed", connectionResult0);
        }
        synchronized(this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.zza.zzt.zzaB().zzp(new zzjx(this));
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zza.zzt.zzaA().zzc().zza("Service connection suspended");
        this.zza.zzt.zzaB().zzp(new zzjw(this));
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        zzej zzej0;
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        synchronized(this) {
            if(iBinder0 == null) {
                this.zzb = false;
                this.zza.zzt.zzaA().zzd().zza("Service connected with null binder");
                return;
            }
            try {
                zzej0 = null;
                String s = iBinder0.getInterfaceDescriptor();
                if("com.google.android.gms.measurement.internal.IMeasurementService".equals(s)) {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zzej zzej1 = iInterface0 instanceof zzej ? ((zzej)iInterface0) : new zzeh(iBinder0);
                    zzej0 = zzej1;
                    this.zza.zzt.zzaA().zzj().zza("Bound to IMeasurementService interface");
                }
                else {
                    this.zza.zzt.zzaA().zzd().zzb("Got binder with a wrong descriptor", s);
                }
            }
            catch(RemoteException unused_ex) {
                this.zza.zzt.zzaA().zzd().zza("Service connect failed to get IMeasurementService");
            }
            if(zzej0 == null) {
                try {
                    this.zzb = false;
                    ConnectionTracker.getInstance().unbindService(this.zza.zzt.zzaw(), zzjz.zzi(this.zza));
                }
                catch(IllegalArgumentException unused_ex) {
                }
            }
            else {
                this.zza.zzt.zzaB().zzp(new zzjt(this, zzej0));
            }
        }
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zza.zzt.zzaA().zzc().zza("Service disconnected");
        this.zza.zzt.zzaB().zzp(new zzju(this, componentName0));
    }

    static void zza(zzjy zzjy0, boolean z) {
        zzjy0.zzb = false;
    }

    public final void zzb(Intent intent0) {
        this.zza.zzg();
        Context context0 = this.zza.zzt.zzaw();
        ConnectionTracker connectionTracker0 = ConnectionTracker.getInstance();
        synchronized(this) {
            if(this.zzb) {
                this.zza.zzt.zzaA().zzj().zza("Connection attempt already in progress");
                return;
            }
            this.zza.zzt.zzaA().zzj().zza("Using local app measurement service");
            this.zzb = true;
            connectionTracker0.bindService(context0, intent0, zzjz.zzi(this.zza), 0x81);
        }
    }

    public final void zzc() {
        this.zza.zzg();
        Context context0 = this.zza.zzt.zzaw();
        synchronized(this) {
            if(this.zzb) {
                this.zza.zzt.zzaA().zzj().zza("Connection attempt already in progress");
                return;
            }
            if(this.zzc != null && (this.zzc.isConnecting() || this.zzc.isConnected())) {
                this.zza.zzt.zzaA().zzj().zza("Already awaiting connection attempt");
                return;
            }
            this.zzc = new zzep(context0, Looper.getMainLooper(), this, this);
            this.zza.zzt.zzaA().zzj().zza("Connecting to remote service");
            this.zzb = true;
            Preconditions.checkNotNull(this.zzc);
            this.zzc.checkAvailabilityAndConnect();
        }
    }

    public final void zzd() {
        if(this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }
}

