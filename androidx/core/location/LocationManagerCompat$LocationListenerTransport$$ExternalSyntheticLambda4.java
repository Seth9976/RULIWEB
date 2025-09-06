package androidx.core.location;

public final class LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda4 implements Runnable {
    public final LocationListenerTransport f$0;
    public final int f$1;

    public LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda4(LocationListenerTransport locationManagerCompat$LocationListenerTransport0, int v) {
        this.f$0 = locationManagerCompat$LocationListenerTransport0;
        this.f$1 = v;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onFlushComplete$2$androidx-core-location-LocationManagerCompat$LocationListenerTransport(this.f$1);
    }
}

