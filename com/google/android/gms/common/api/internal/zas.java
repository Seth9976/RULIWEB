package com.google.android.gms.common.api.internal;

final class zas {
    final BasePendingResult zaa;

    zas(BasePendingResult basePendingResult0, zar zar0) {
        this.zaa = basePendingResult0;
        super();
    }

    @Override
    protected final void finalize() throws Throwable {
        BasePendingResult.zal(this.zaa.zaj);
        super.finalize();
    }
}

