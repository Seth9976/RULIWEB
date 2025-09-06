package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Status;

final class zaab implements StatusListener {
    final BasePendingResult zaa;
    final zaad zab;

    zaab(zaad zaad0, BasePendingResult basePendingResult0) {
        this.zab = zaad0;
        this.zaa = basePendingResult0;
        super();
    }

    @Override  // com.google.android.gms.common.api.PendingResult$StatusListener
    public final void onComplete(Status status0) {
        zaad.zaa(this.zab).remove(this.zaa);
    }
}

