package androidx.core.view.insets;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

public final class SystemBarStateMonitor..ExternalSyntheticLambda0 implements OnApplyWindowInsetsListener {
    public final SystemBarStateMonitor f$0;

    public SystemBarStateMonitor..ExternalSyntheticLambda0(SystemBarStateMonitor systemBarStateMonitor0) {
        this.f$0 = systemBarStateMonitor0;
    }

    @Override  // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
        return this.f$0.lambda$new$0$androidx-core-view-insets-SystemBarStateMonitor(view0, windowInsetsCompat0);
    }
}

