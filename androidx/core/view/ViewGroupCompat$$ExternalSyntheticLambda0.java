package androidx.core.view;

import android.view.View.OnApplyWindowInsetsListener;
import android.view.View;
import android.view.WindowInsets;

public final class ViewGroupCompat..ExternalSyntheticLambda0 implements View.OnApplyWindowInsetsListener {
    public final WindowInsets[] f$0;
    public final View.OnApplyWindowInsetsListener f$1;

    public ViewGroupCompat..ExternalSyntheticLambda0(WindowInsets[] arr_windowInsets, View.OnApplyWindowInsetsListener view$OnApplyWindowInsetsListener0) {
        this.f$0 = arr_windowInsets;
        this.f$1 = view$OnApplyWindowInsetsListener0;
    }

    @Override  // android.view.View$OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view0, WindowInsets windowInsets0) {
        return ViewGroupCompat.lambda$dispatchApplyWindowInsets$1(this.f$0, this.f$1, view0, windowInsets0);
    }
}

