package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;

public final class zze implements ServiceConnection {
    final BaseGmsClient zza;
    private final int zzb;

    public zze(BaseGmsClient baseGmsClient0, int v) {
        this.zza = baseGmsClient0;
        super();
        this.zzb = v;
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        BaseGmsClient baseGmsClient0 = this.zza;
        if(iBinder0 == null) {
            BaseGmsClient.zzk(baseGmsClient0, 16);
            return;
        }
        synchronized(baseGmsClient0.zzq) {
            BaseGmsClient baseGmsClient1 = this.zza;
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IGmsServiceBroker iGmsServiceBroker0 = iInterface0 == null || !(iInterface0 instanceof IGmsServiceBroker) ? new zzad(iBinder0) : ((IGmsServiceBroker)iInterface0);
            baseGmsClient1.zzr = iGmsServiceBroker0;
        }
        this.zza.zzl(0, null, this.zzb);
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        synchronized(this.zza.zzq) {
            this.zza.zzr = null;
        }
        Message message0 = this.zza.zzb.obtainMessage(6, this.zzb, 1);
        this.zza.zzb.sendMessage(message0);
    }
}

