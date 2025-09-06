package androidx.webkit.internal;

import android.webkit.WebView;
import androidx.webkit.WebViewRenderProcess;
import androidx.webkit.WebViewRenderProcessClient;

public final class WebViewRenderProcessClientAdapter..ExternalSyntheticLambda1 implements Runnable {
    public final WebViewRenderProcessClient f$0;
    public final WebView f$1;
    public final WebViewRenderProcess f$2;

    public WebViewRenderProcessClientAdapter..ExternalSyntheticLambda1(WebViewRenderProcessClient webViewRenderProcessClient0, WebView webView0, WebViewRenderProcess webViewRenderProcess0) {
        this.f$0 = webViewRenderProcessClient0;
        this.f$1 = webView0;
        this.f$2 = webViewRenderProcess0;
    }

    @Override
    public final void run() {
        WebViewRenderProcessClientAdapter.lambda$onRendererUnresponsive$0(this.f$0, this.f$1, this.f$2);
    }
}

