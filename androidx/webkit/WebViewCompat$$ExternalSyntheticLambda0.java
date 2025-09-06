package androidx.webkit;

public final class WebViewCompat..ExternalSyntheticLambda0 implements WebViewStartUpCallback {
    public final WebViewStartUpCallback f$0;

    public WebViewCompat..ExternalSyntheticLambda0(WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0) {
        this.f$0 = webViewCompat$WebViewStartUpCallback0;
    }

    @Override  // androidx.webkit.WebViewCompat$WebViewStartUpCallback
    public final void onSuccess(WebViewStartUpResult webViewStartUpResult0) {
        WebViewCompat.lambda$startUpWebView$1(this.f$0, webViewStartUpResult0);
    }
}

