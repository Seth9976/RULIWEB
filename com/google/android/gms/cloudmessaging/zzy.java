package com.google.android.gms.cloudmessaging;

import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;

public final class zzy implements Runnable {
    public final TaskCompletionSource zza;

    public zzy(TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
    }

    @Override
    public final void run() {
        IOException iOException0 = new IOException("TIMEOUT");
        if(this.zza.trySetException(iOException0)) {
            Log.w("Rpc", "No response");
        }
    }
}

