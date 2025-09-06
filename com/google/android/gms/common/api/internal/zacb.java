package com.google.android.gms.common.api.internal;

public final class zacb implements Runnable {
    public final ListenerHolder zaa;
    public final Notifier zab;

    public zacb(ListenerHolder listenerHolder0, Notifier listenerHolder$Notifier0) {
        this.zaa = listenerHolder0;
        this.zab = listenerHolder$Notifier0;
    }

    @Override
    public final void run() {
        this.zaa.zaa(this.zab);
    }
}

