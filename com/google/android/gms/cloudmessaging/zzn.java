package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

final class zzn {
    private final Messenger zza;
    private final zzd zzb;

    zzn(IBinder iBinder0) throws RemoteException {
        String s = iBinder0.getInterfaceDescriptor();
        if("android.os.IMessenger".equals(s)) {
            this.zza = new Messenger(iBinder0);
            this.zzb = null;
            return;
        }
        if("com.google.android.gms.iid.IMessengerCompat".equals(s)) {
            this.zzb = new zzd(iBinder0);
            this.zza = null;
            return;
        }
        String s1 = String.valueOf(s);
        Log.w("MessengerIpcClient", (s1.length() == 0 ? new String("Invalid interface descriptor: ") : "Invalid interface descriptor: " + s1));
        throw new RemoteException();
    }

    final void zza(Message message0) throws RemoteException {
        Messenger messenger0 = this.zza;
        if(messenger0 != null) {
            messenger0.send(message0);
            return;
        }
        zzd zzd0 = this.zzb;
        if(zzd0 == null) {
            throw new IllegalStateException("Both messengers are null");
        }
        zzd0.zzb(message0);
    }
}

