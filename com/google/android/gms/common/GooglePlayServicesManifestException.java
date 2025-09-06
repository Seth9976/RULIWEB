package com.google.android.gms.common;

public class GooglePlayServicesManifestException extends IllegalStateException {
    private final int zza;

    public GooglePlayServicesManifestException(int v, String s) {
        super(s);
        this.zza = v;
    }

    public int getActualVersion() {
        return this.zza;
    }

    public int getExpectedVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}

