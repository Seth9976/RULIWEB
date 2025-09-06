package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.common.zzi;
import java.util.concurrent.Executor;

public class HandlerExecutor implements Executor {
    private final Handler zza;

    public HandlerExecutor(Looper looper0) {
        this.zza = new zzi(looper0);
    }

    @Override
    public final void execute(Runnable runnable0) {
        this.zza.post(runnable0);
    }
}

