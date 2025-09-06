package androidx.activity;

import androidx.activity.result.contract.ActivityResultContract.SynchronousResult;

public final class ComponentActivity.activityResultRegistry.1..ExternalSyntheticLambda0 implements Runnable {
    public final androidx.activity.ComponentActivity.activityResultRegistry.1 f$0;
    public final int f$1;
    public final SynchronousResult f$2;

    public ComponentActivity.activityResultRegistry.1..ExternalSyntheticLambda0(androidx.activity.ComponentActivity.activityResultRegistry.1 componentActivity$activityResultRegistry$10, int v, SynchronousResult activityResultContract$SynchronousResult0) {
        this.f$0 = componentActivity$activityResultRegistry$10;
        this.f$1 = v;
        this.f$2 = activityResultContract$SynchronousResult0;
    }

    @Override
    public final void run() {
        androidx.activity.ComponentActivity.activityResultRegistry.1.onLaunch$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}

