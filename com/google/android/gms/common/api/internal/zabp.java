package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks;

final class zabp implements SignOutCallbacks {
    final zabq zaa;

    zabp(zabq zabq0) {
        this.zaa = zabq0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks
    public final void onSignOutComplete() {
        zabo zabo0 = new zabo(this);
        this.zaa.zaa.zar.post(zabo0);
    }
}

