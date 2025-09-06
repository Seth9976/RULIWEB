package com.google.android.gms.fido.u2f;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;

@Deprecated
public interface U2fPendingIntent {
    boolean hasPendingIntent();

    void launchPendingIntent(Activity arg1, int arg2) throws IntentSender.SendIntentException;
}

