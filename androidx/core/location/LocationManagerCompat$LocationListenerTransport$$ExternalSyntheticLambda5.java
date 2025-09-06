package androidx.core.location;

import android.os.Bundle;

public final class LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda5 implements Runnable {
    public final LocationListenerTransport f$0;
    public final String f$1;
    public final int f$2;
    public final Bundle f$3;

    public LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda5(LocationListenerTransport locationManagerCompat$LocationListenerTransport0, String s, int v, Bundle bundle0) {
        this.f$0 = locationManagerCompat$LocationListenerTransport0;
        this.f$1 = s;
        this.f$2 = v;
        this.f$3 = bundle0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onStatusChanged$3$androidx-core-location-LocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2, this.f$3);
    }
}

