package com.google.android.gms.internal.fido;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import com.google.android.gms.fido.u2f.U2fPendingIntent;

public final class zzt implements U2fPendingIntent {
    private final PendingIntent zza;

    public zzt(PendingIntent pendingIntent0) {
        this.zza = pendingIntent0;
    }

    @Override  // com.google.android.gms.fido.u2f.U2fPendingIntent
    public final boolean hasPendingIntent() {
        return this.zza != null;
    }

    @Override  // com.google.android.gms.fido.u2f.U2fPendingIntent
    public final void launchPendingIntent(Activity activity0, int v) throws IntentSender.SendIntentException {
        PendingIntent pendingIntent0 = this.zza;
        if(pendingIntent0 == null) {
            throw new IllegalStateException("No PendingIntent available");
        }
        activity0.startIntentSenderForResult(pendingIntent0.getIntentSender(), v, null, 0, 0, 0);
    }
}

