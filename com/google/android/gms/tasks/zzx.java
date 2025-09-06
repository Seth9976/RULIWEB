package com.google.android.gms.tasks;

import java.util.concurrent.TimeoutException;

public final class zzx implements Runnable {
    public final TaskCompletionSource zza;

    public zzx(TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
    }

    @Override
    public final void run() {
        TimeoutException timeoutException0 = new TimeoutException();
        this.zza.trySetException(timeoutException0);
    }
}

