package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbm extends zzbd {
    final TaskCompletionSource zza;

    zzbm(zzbo zzbo0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzbd
    public final void zzb(ProxyResponse proxyResponse0) throws RemoteException {
        TaskUtil.setResultOrApiException(new Status(proxyResponse0.googlePlayServicesStatusCode), proxyResponse0, this.zza);
    }
}

