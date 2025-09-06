package androidx.activity;

import android.content.IntentSender.SendIntentException;

public final class ComponentActivity.activityResultRegistry.1..ExternalSyntheticLambda1 implements Runnable {
    public final androidx.activity.ComponentActivity.activityResultRegistry.1 f$0;
    public final int f$1;
    public final IntentSender.SendIntentException f$2;

    public ComponentActivity.activityResultRegistry.1..ExternalSyntheticLambda1(androidx.activity.ComponentActivity.activityResultRegistry.1 componentActivity$activityResultRegistry$10, int v, IntentSender.SendIntentException intentSender$SendIntentException0) {
        this.f$0 = componentActivity$activityResultRegistry$10;
        this.f$1 = v;
        this.f$2 = intentSender$SendIntentException0;
    }

    @Override
    public final void run() {
        androidx.activity.ComponentActivity.activityResultRegistry.1.onLaunch$lambda$1(this.f$0, this.f$1, this.f$2);
    }
}

