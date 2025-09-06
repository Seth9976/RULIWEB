package androidx.core.location;

import android.location.GnssStatus;
import java.util.concurrent.Executor;

public final class LocationManagerCompat.PreRGnssStatusTransport..ExternalSyntheticLambda1 implements Runnable {
    public final PreRGnssStatusTransport f$0;
    public final Executor f$1;
    public final GnssStatus f$2;

    public LocationManagerCompat.PreRGnssStatusTransport..ExternalSyntheticLambda1(PreRGnssStatusTransport locationManagerCompat$PreRGnssStatusTransport0, Executor executor0, GnssStatus gnssStatus0) {
        this.f$0 = locationManagerCompat$PreRGnssStatusTransport0;
        this.f$1 = executor0;
        this.f$2 = gnssStatus0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onSatelliteStatusChanged$3$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport(this.f$1, this.f$2);
    }
}

