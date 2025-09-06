package androidx.core.location;

import android.location.LocationManager;
import java.util.concurrent.Callable;

public final class LocationManagerCompat..ExternalSyntheticLambda4 implements Callable {
    public final LocationManager f$0;
    public final GpsStatusTransport f$1;

    public LocationManagerCompat..ExternalSyntheticLambda4(LocationManager locationManager0, GpsStatusTransport locationManagerCompat$GpsStatusTransport0) {
        this.f$0 = locationManager0;
        this.f$1 = locationManagerCompat$GpsStatusTransport0;
    }

    @Override
    public final Object call() {
        return LocationManagerCompat.lambda$registerGnssStatusCallback$1(this.f$0, this.f$1);
    }
}

