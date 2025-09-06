package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class DataHolderNotifier implements Notifier {
    private final DataHolder zaa;

    protected DataHolderNotifier(DataHolder dataHolder0) {
        this.zaa = dataHolder0;
    }

    @Override  // com.google.android.gms.common.api.internal.ListenerHolder$Notifier
    public final void notifyListener(Object object0) {
        this.notifyListener(object0, this.zaa);
    }

    protected abstract void notifyListener(Object arg1, DataHolder arg2);

    @Override  // com.google.android.gms.common.api.internal.ListenerHolder$Notifier
    public void onNotifyListenerFailed() {
        DataHolder dataHolder0 = this.zaa;
        if(dataHolder0 != null) {
            dataHolder0.close();
        }
    }
}

