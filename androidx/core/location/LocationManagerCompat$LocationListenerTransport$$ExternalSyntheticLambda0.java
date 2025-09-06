package androidx.core.location;

public final class LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda0 implements Runnable {
    public final LocationListenerTransport f$0;
    public final String f$1;

    public LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda0(LocationListenerTransport locationManagerCompat$LocationListenerTransport0, String s) {
        this.f$0 = locationManagerCompat$LocationListenerTransport0;
        this.f$1 = s;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onProviderEnabled$4$androidx-core-location-LocationManagerCompat$LocationListenerTransport(this.f$1);
    }
}

