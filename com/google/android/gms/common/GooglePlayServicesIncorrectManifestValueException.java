package com.google.android.gms.common;

public final class GooglePlayServicesIncorrectManifestValueException extends GooglePlayServicesManifestException {
    public GooglePlayServicesIncorrectManifestValueException(int v) {
        super(v, "The meta-data tag in your app\'s AndroidManifest.xml does not have the right value.  Expected " + GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE + " but found " + v + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
    }
}

