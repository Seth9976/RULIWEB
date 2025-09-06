package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;

final class zaaa implements Notifier {
    final ModuleInstallStatusUpdate zaa;

    zaaa(zaab zaab0, ModuleInstallStatusUpdate moduleInstallStatusUpdate0) {
        this.zaa = moduleInstallStatusUpdate0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.ListenerHolder$Notifier
    public final void notifyListener(Object object0) {
        ((InstallStatusListener)object0).onInstallStatusUpdated(this.zaa);
    }

    @Override  // com.google.android.gms.common.api.internal.ListenerHolder$Notifier
    public final void onNotifyListenerFailed() {
    }
}

