package com.google.android.gms.common.api.internal;

final class zav implements Runnable {
    final zaaa zaa;

    zav(zaaa zaaa0) {
        this.zaa = zaaa0;
        super();
    }

    @Override
    public final void run() {
        this.zaa.zam.lock();
        try {
            zaaa.zap(this.zaa);
        }
        finally {
            this.zaa.zam.unlock();
        }
    }
}

