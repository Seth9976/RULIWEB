package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
    private final int zza;

    GooglePlayServicesAvailabilityException(int v, String s, Intent intent0) {
        super(s, intent0);
        this.zza = v;
    }

    public int getConnectionStatusCode() {
        return this.zza;
    }
}

