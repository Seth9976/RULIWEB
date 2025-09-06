package com.google.android.gms.common.api.internal;

final class zabl implements BackgroundStateChangeListener {
    final GoogleApiManager zaa;

    zabl(GoogleApiManager googleApiManager0) {
        this.zaa = googleApiManager0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.BackgroundDetector$BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        this.zaa.zar.sendMessage(this.zaa.zar.obtainMessage(1, Boolean.valueOf(z)));
    }
}

