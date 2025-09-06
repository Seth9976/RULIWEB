package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final class zzx implements SuccessContinuation {
    public static final zzx zza;

    static {
        zzx.zza = new zzx();
    }

    @Override  // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object object0) {
        return Rpc.zza(((Bundle)object0));
    }
}

