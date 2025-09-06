package androidx.webkit.internal;

import androidx.webkit.WebViewCompat.VisualStateCallback;
import org.chromium.support_lib_boundary.VisualStateCallbackBoundaryInterface;

public class VisualStateCallbackAdapter implements VisualStateCallbackBoundaryInterface {
    private final VisualStateCallback mVisualStateCallback;

    public VisualStateCallbackAdapter(VisualStateCallback webViewCompat$VisualStateCallback0) {
        this.mVisualStateCallback = webViewCompat$VisualStateCallback0;
    }

    @Override  // org.chromium.support_lib_boundary.VisualStateCallbackBoundaryInterface
    public void onComplete(long v) {
        this.mVisualStateCallback.onComplete(v);
    }
}

