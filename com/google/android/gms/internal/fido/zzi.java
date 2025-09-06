package com.google.android.gms.internal.fido;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import com.google.android.gms.fido.fido2.Fido2PendingIntent;

@Deprecated
public final class zzi implements Fido2PendingIntent {
    private final PendingIntent zza;

    public zzi(PendingIntent pendingIntent0) {
        this.zza = pendingIntent0;
    }

    @Override  // com.google.android.gms.fido.fido2.Fido2PendingIntent
    public final boolean hasPendingIntent() {
        return this.zza != null;
    }

    @Override  // com.google.android.gms.fido.fido2.Fido2PendingIntent
    public final void launchPendingIntent(Activity activity0, int v) throws IntentSender.SendIntentException {
        PendingIntent pendingIntent0 = this.zza;
        if(pendingIntent0 == null) {
            throw new IllegalStateException("No PendingIntent available");
        }
        activity0.startIntentSenderForResult(pendingIntent0.getIntentSender(), v, null, 0, 0, 0);
    }
}

