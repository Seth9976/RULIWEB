package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;

final class zaab extends zag {
    private final ListenerHolder zaa;

    public zaab(ListenerHolder listenerHolder0) {
        this.zaa = listenerHolder0;
    }

    @Override  // com.google.android.gms.common.moduleinstall.internal.zah
    public final void zab(ModuleInstallStatusUpdate moduleInstallStatusUpdate0) {
        zaaa zaaa0 = new zaaa(this, moduleInstallStatusUpdate0);
        this.zaa.notifyListener(zaaa0);
    }
}

