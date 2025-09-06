package com.google.android.gms.cloudmessaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledFuture;

public final class zzw implements OnCompleteListener {
    public final Rpc zza;
    public final String zzb;
    public final ScheduledFuture zzc;

    public zzw(Rpc rpc0, String s, ScheduledFuture scheduledFuture0) {
        this.zza = rpc0;
        this.zzb = s;
        this.zzc = scheduledFuture0;
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task0) {
        this.zza.zzd(this.zzb, this.zzc, task0);
    }
}

