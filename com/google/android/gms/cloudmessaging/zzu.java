package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final class zzu implements Continuation {
    public final Rpc zza;
    public final Bundle zzb;

    public zzu(Rpc rpc0, Bundle bundle0) {
        this.zza = rpc0;
        this.zzb = bundle0;
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) {
        return this.zza.zzb(this.zzb, task0);
    }
}

