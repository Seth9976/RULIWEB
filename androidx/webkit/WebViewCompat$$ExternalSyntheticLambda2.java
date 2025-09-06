package androidx.webkit;

public final class WebViewCompat..ExternalSyntheticLambda2 implements Runnable {
    public final WebViewStartUpCallback f$0;
    public final WebViewStartUpResult f$1;

    public WebViewCompat..ExternalSyntheticLambda2(WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0, WebViewStartUpResult webViewStartUpResult0) {
        this.f$0 = webViewCompat$WebViewStartUpCallback0;
        this.f$1 = webViewStartUpResult0;
    }

    @Override
    public final void run() {
        WebViewCompat.lambda$startUpWebView$0(this.f$0, this.f$1);
    }
}

