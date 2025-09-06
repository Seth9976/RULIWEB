package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;

public class ResolvableApiException extends ApiException {
    public ResolvableApiException(Status status0) {
        super(status0);
    }

    public PendingIntent getResolution() {
        return this.getStatus().getResolution();
    }

    public void startResolutionForResult(Activity activity0, int v) throws IntentSender.SendIntentException {
        this.getStatus().startResolutionForResult(activity0, v);
    }
}

