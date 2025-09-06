package androidx.core.location;

import android.location.Location;

public final class LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda3 implements Runnable {
    public final LocationListenerTransport f$0;
    public final Location f$1;

    public LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda3(LocationListenerTransport locationManagerCompat$LocationListenerTransport0, Location location0) {
        this.f$0 = locationManagerCompat$LocationListenerTransport0;
        this.f$1 = location0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onLocationChanged$0$androidx-core-location-LocationManagerCompat$LocationListenerTransport(this.f$1);
    }
}

