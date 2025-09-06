package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final class WakeLockHolder..ExternalSyntheticLambda0 implements OnCompleteListener {
    public final Intent f$0;

    public WakeLockHolder..ExternalSyntheticLambda0(Intent intent0) {
        this.f$0 = intent0;
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task0) {
        WakeLockHolder.lambda$sendWakefulServiceIntent$0(this.f$0, task0);
    }
}

