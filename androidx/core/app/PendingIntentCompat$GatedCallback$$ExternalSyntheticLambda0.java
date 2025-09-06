package androidx.core.app;

import android.app.PendingIntent.OnFinished;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public final class PendingIntentCompat.GatedCallback..ExternalSyntheticLambda0 implements PendingIntent.OnFinished {
    public final GatedCallback f$0;

    public PendingIntentCompat.GatedCallback..ExternalSyntheticLambda0(GatedCallback pendingIntentCompat$GatedCallback0) {
        this.f$0 = pendingIntentCompat$GatedCallback0;
    }

    @Override  // android.app.PendingIntent$OnFinished
    public final void onSendFinished(PendingIntent pendingIntent0, Intent intent0, int v, String s, Bundle bundle0) {
        this.f$0.onSendFinished(pendingIntent0, intent0, v, s, bundle0);
    }
}

