package androidx.webkit.internal;

import android.webkit.ValueCallback;
import androidx.webkit.PrerenderOperationCallback;

public final class WebViewProviderAdapter..ExternalSyntheticLambda2 implements ValueCallback {
    public final PrerenderOperationCallback f$0;

    public WebViewProviderAdapter..ExternalSyntheticLambda2(PrerenderOperationCallback prerenderOperationCallback0) {
        this.f$0 = prerenderOperationCallback0;
    }

    @Override  // android.webkit.ValueCallback
    public final void onReceiveValue(Object object0) {
        WebViewProviderAdapter.lambda$prerenderUrlAsync$0(this.f$0, ((Void)object0));
    }
}

