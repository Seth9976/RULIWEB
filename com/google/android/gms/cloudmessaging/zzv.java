package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

public final class zzv implements Continuation {
    public static final zzv zza;

    static {
        zzv.zza = new zzv();
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) {
        if(task0.isSuccessful()) {
            return (Bundle)task0.getResult();
        }
        if(Log.isLoggable("Rpc", 3)) {
            Log.d("Rpc", "Error making request: " + task0.getException());
        }
        throw new IOException("SERVICE_NOT_AVAILABLE", task0.getException());
    }
}

