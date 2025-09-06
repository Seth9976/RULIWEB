package com.google.android.gms.dynamic;

final class zaa implements OnDelegateCreatedListener {
    final DeferredLifecycleHelper zaa;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper0) {
        this.zaa = deferredLifecycleHelper0;
        super();
    }

    @Override  // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate0) {
        this.zaa.zaa = lifecycleDelegate0;
        for(Object object0: this.zaa.zac) {
            ((zah)object0).zab(this.zaa.zaa);
        }
        this.zaa.zac.clear();
        DeferredLifecycleHelper.zad(this.zaa, null);
    }
}

