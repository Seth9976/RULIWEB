package androidx.webkit;

public final class WebViewCompat..ExternalSyntheticLambda3 implements Runnable {
    public final WebViewStartUpConfig f$0;
    public final WebViewStartUpCallback f$1;

    public WebViewCompat..ExternalSyntheticLambda3(WebViewStartUpConfig webViewStartUpConfig0, WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0) {
        this.f$0 = webViewStartUpConfig0;
        this.f$1 = webViewCompat$WebViewStartUpCallback0;
    }

    @Override
    public final void run() {
        WebViewCompat.lambda$startUpWebView$3(this.f$0, this.f$1);
    }
}

