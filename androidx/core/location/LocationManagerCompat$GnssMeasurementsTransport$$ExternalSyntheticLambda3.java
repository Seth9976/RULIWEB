package androidx.core.location;

import java.util.concurrent.Executor;

public final class LocationManagerCompat.GnssMeasurementsTransport..ExternalSyntheticLambda3 implements Runnable {
    public final GnssMeasurementsTransport f$0;
    public final Executor f$1;
    public final int f$2;

    public LocationManagerCompat.GnssMeasurementsTransport..ExternalSyntheticLambda3(GnssMeasurementsTransport locationManagerCompat$GnssMeasurementsTransport0, Executor executor0, int v) {
        this.f$0 = locationManagerCompat$GnssMeasurementsTransport0;
        this.f$1 = executor0;
        this.f$2 = v;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onStatusChanged$1$androidx-core-location-LocationManagerCompat$GnssMeasurementsTransport(this.f$1, this.f$2);
    }
}

