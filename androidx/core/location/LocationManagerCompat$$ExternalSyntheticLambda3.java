package androidx.core.location;

import android.os.CancellationSignal.OnCancelListener;

public final class LocationManagerCompat..ExternalSyntheticLambda3 implements CancellationSignal.OnCancelListener {
    public final CancellableLocationListener f$0;

    public LocationManagerCompat..ExternalSyntheticLambda3(CancellableLocationListener locationManagerCompat$CancellableLocationListener0) {
        this.f$0 = locationManagerCompat$CancellableLocationListener0;
    }

    @Override  // android.os.CancellationSignal$OnCancelListener
    public final void onCancel() {
        this.f$0.cancel();
    }
}

