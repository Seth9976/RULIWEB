package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;

final class zau extends zaa {
    final AtomicReference zaa;
    final TaskCompletionSource zab;
    final InstallStatusListener zac;
    final zay zad;

    zau(zay zay0, AtomicReference atomicReference0, TaskCompletionSource taskCompletionSource0, InstallStatusListener installStatusListener0) {
        this.zad = zay0;
        this.zaa = atomicReference0;
        this.zab = taskCompletionSource0;
        this.zac = installStatusListener0;
        super();
    }

    @Override  // com.google.android.gms.common.moduleinstall.internal.zaa
    public final void zad(Status status0, ModuleInstallResponse moduleInstallResponse0) {
        if(moduleInstallResponse0 != null) {
            this.zaa.set(moduleInstallResponse0);
        }
        TaskUtil.trySetResultOrApiException(status0, null, this.zab);
        if(status0.isSuccess() && (moduleInstallResponse0 == null || !moduleInstallResponse0.zaa())) {
            return;
        }
        ListenerKey listenerHolder$ListenerKey0 = ListenerHolders.createListenerKey(this.zac, "InstallStatusListener");
        this.zad.doUnregisterEventListener(listenerHolder$ListenerKey0, 27306);
    }
}

