package androidx.webkit.internal;

import android.webkit.ValueCallback;
import androidx.webkit.PrerenderOperationCallback;

public final class WebViewProviderAdapter..ExternalSyntheticLambda3 implements ValueCallback {
    public final PrerenderOperationCallback f$0;

    public WebViewProviderAdapter..ExternalSyntheticLambda3(PrerenderOperationCallback prerenderOperationCallback0) {
        this.f$0 = prerenderOperationCallback0;
    }

    @Override  // android.webkit.ValueCallback
    public final void onReceiveValue(Object object0) {
        WebViewProviderAdapter.lambda$prerenderUrlAsync$1(this.f$0, ((Throwable)object0));
    }
}

