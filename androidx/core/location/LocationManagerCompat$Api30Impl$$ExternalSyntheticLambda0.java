package androidx.core.location;

import android.location.Location;
import java.util.function.Consumer;

public final class LocationManagerCompat.Api30Impl..ExternalSyntheticLambda0 implements Consumer {
    public final androidx.core.util.Consumer f$0;

    public LocationManagerCompat.Api30Impl..ExternalSyntheticLambda0(androidx.core.util.Consumer consumer0) {
        this.f$0 = consumer0;
    }

    @Override
    public final void accept(Object object0) {
        this.f$0.accept(((Location)object0));
    }
}

