package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final class WithinAppServiceBinder..ExternalSyntheticLambda0 implements OnCompleteListener {
    public final BindRequest f$0;

    public WithinAppServiceBinder..ExternalSyntheticLambda0(BindRequest withinAppServiceConnection$BindRequest0) {
        this.f$0 = withinAppServiceConnection$BindRequest0;
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task0) {
        WithinAppServiceBinder.lambda$send$0(this.f$0, task0);
    }
}

