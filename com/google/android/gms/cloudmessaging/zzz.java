package com.google.android.gms.cloudmessaging;

import java.util.concurrent.Executor;

public final class zzz implements Executor {
    public static final zzz zza;

    static {
        zzz.zza = new zzz();
    }

    @Override
    public final void execute(Runnable runnable0) {
        runnable0.run();
    }
}

