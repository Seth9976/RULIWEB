package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.TimeUnit;

public final class zzh implements Runnable {
    public final zzm zza;

    public zzh(zzm zzm0) {
        this.zza = zzm0;
    }

    @Override
    public final void run() {
        zzp zzp0;
        zzm zzm0 = this.zza;
        while(true) {
            synchronized(zzm0) {
                if(zzm0.zza != 2) {
                    return;
                }
                if(zzm0.zzd.isEmpty()) {
                    zzm0.zzf();
                    return;
                }
                zzp0 = (zzp)zzm0.zzd.poll();
                zzm0.zze.put(zzp0.zza, zzp0);
                zzs.zze(zzm0.zzf).schedule(new zzk(zzm0, zzp0), 30L, TimeUnit.SECONDS);
            }
            if(Log.isLoggable("MessengerIpcClient", 3)) {
                Log.d("MessengerIpcClient", "Sending " + zzp0);
            }
            Message message0 = Message.obtain();
            message0.what = zzp0.zzc;
            message0.arg1 = zzp0.zza;
            message0.replyTo = zzm0.zzb;
            Bundle bundle0 = new Bundle();
            bundle0.putBoolean("oneWay", zzp0.zzb());
            bundle0.putString("pkg", "com.ruliweb.www.ruliapp");
            bundle0.putBundle("data", zzp0.zzd);
            message0.setData(bundle0);
            try {
                zzm0.zzc.zza(message0);
            }
            catch(RemoteException remoteException0) {
                zzm0.zza(2, remoteException0.getMessage());
            }
        }
    }
}

