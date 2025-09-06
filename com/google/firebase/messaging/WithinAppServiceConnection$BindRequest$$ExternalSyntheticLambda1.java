package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledFuture;

public final class WithinAppServiceConnection.BindRequest..ExternalSyntheticLambda1 implements OnCompleteListener {
    public final ScheduledFuture f$0;

    public WithinAppServiceConnection.BindRequest..ExternalSyntheticLambda1(ScheduledFuture scheduledFuture0) {
        this.f$0 = scheduledFuture0;
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task0) {
        BindRequest.lambda$arrangeTimeout$1(this.f$0, task0);
    }
}

