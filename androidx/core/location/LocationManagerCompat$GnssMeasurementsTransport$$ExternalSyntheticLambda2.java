package androidx.core.location;

import android.location.GnssMeasurementsEvent;
import java.util.concurrent.Executor;

public final class LocationManagerCompat.GnssMeasurementsTransport..ExternalSyntheticLambda2 implements Runnable {
    public final GnssMeasurementsTransport f$0;
    public final Executor f$1;
    public final GnssMeasurementsEvent f$2;

    public LocationManagerCompat.GnssMeasurementsTransport..ExternalSyntheticLambda2(GnssMeasurementsTransport locationManagerCompat$GnssMeasurementsTransport0, Executor executor0, GnssMeasurementsEvent gnssMeasurementsEvent0) {
        this.f$0 = locationManagerCompat$GnssMeasurementsTransport0;
        this.f$1 = executor0;
        this.f$2 = gnssMeasurementsEvent0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onGnssMeasurementsReceived$0$androidx-core-location-LocationManagerCompat$GnssMeasurementsTransport(this.f$1, this.f$2);
    }
}

