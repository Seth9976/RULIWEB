package com.google.android.gms.common.api.internal;

abstract class zaav implements Runnable {
    final zaaw zab;

    zaav(zaaw zaaw0, zaau zaau0) {
        this.zab = zaaw0;
        super();
    }

    @Override
    public final void run() {
        zaaw.zap(this.zab).lock();
        try {
            if(!Thread.interrupted()) {
                this.zaa();
            }
        }
        catch(RuntimeException runtimeException0) {
            zaaw.zak(this.zab).zam(runtimeException0);
        }
        finally {
            zaaw.zap(this.zab).unlock();
        }
    }

    protected abstract void zaa();
}

