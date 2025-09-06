package androidx.core.view;

import android.view.ViewConfiguration;
import androidx.core.util.Supplier;

public final class ViewConfigurationCompat..ExternalSyntheticLambda0 implements Supplier {
    public final ViewConfiguration f$0;

    public ViewConfigurationCompat..ExternalSyntheticLambda0(ViewConfiguration viewConfiguration0) {
        this.f$0 = viewConfiguration0;
    }

    @Override  // androidx.core.util.Supplier
    public final Object get() {
        return this.f$0.getScaledMaximumFlingVelocity();
    }
}

