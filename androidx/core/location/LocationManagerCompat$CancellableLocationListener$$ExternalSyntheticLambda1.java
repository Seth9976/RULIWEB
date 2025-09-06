package androidx.core.location;

import android.location.Location;
import androidx.core.util.Consumer;

public final class LocationManagerCompat.CancellableLocationListener..ExternalSyntheticLambda1 implements Runnable {
    public final Consumer f$0;
    public final Location f$1;

    public LocationManagerCompat.CancellableLocationListener..ExternalSyntheticLambda1(Consumer consumer0, Location location0) {
        this.f$0 = consumer0;
        this.f$1 = location0;
    }

    @Override
    public final void run() {
        this.f$0.accept(this.f$1);
    }
}

