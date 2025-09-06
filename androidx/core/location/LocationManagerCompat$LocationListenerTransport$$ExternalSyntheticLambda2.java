package androidx.core.location;

import java.util.List;

public final class LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda2 implements Runnable {
    public final LocationListenerTransport f$0;
    public final List f$1;

    public LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda2(LocationListenerTransport locationManagerCompat$LocationListenerTransport0, List list0) {
        this.f$0 = locationManagerCompat$LocationListenerTransport0;
        this.f$1 = list0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onLocationChanged$1$androidx-core-location-LocationManagerCompat$LocationListenerTransport(this.f$1);
    }
}

