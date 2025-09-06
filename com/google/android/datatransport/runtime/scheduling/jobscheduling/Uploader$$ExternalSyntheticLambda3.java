package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;

public final class Uploader..ExternalSyntheticLambda3 implements Runnable {
    public final Uploader f$0;
    public final TransportContext f$1;
    public final int f$2;
    public final Runnable f$3;

    public Uploader..ExternalSyntheticLambda3(Uploader uploader0, TransportContext transportContext0, int v, Runnable runnable0) {
        this.f$0 = uploader0;
        this.f$1 = transportContext0;
        this.f$2 = v;
        this.f$3 = runnable0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$upload$1$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(this.f$1, this.f$2, this.f$3);
    }
}

