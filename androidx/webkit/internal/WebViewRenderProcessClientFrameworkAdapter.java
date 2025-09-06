package androidx.webkit.internal;

import android.webkit.WebView;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;

public class WebViewRenderProcessClientFrameworkAdapter extends WebViewRenderProcessClient {
    private final androidx.webkit.WebViewRenderProcessClient mWebViewRenderProcessClient;

    public WebViewRenderProcessClientFrameworkAdapter(androidx.webkit.WebViewRenderProcessClient webViewRenderProcessClient0) {
        this.mWebViewRenderProcessClient = webViewRenderProcessClient0;
    }

    public androidx.webkit.WebViewRenderProcessClient getFrameworkRenderProcessClient() {
        return this.mWebViewRenderProcessClient;
    }

    @Override  // android.webkit.WebViewRenderProcessClient
    public void onRenderProcessResponsive(WebView webView0, WebViewRenderProcess webViewRenderProcess0) {
        WebViewRenderProcessImpl webViewRenderProcessImpl0 = WebViewRenderProcessImpl.forFrameworkObject(webViewRenderProcess0);
        this.mWebViewRenderProcessClient.onRenderProcessResponsive(webView0, webViewRenderProcessImpl0);
    }

    @Override  // android.webkit.WebViewRenderProcessClient
    public void onRenderProcessUnresponsive(WebView webView0, WebViewRenderProcess webViewRenderProcess0) {
        WebViewRenderProcessImpl webViewRenderProcessImpl0 = WebViewRenderProcessImpl.forFrameworkObject(webViewRenderProcess0);
        this.mWebViewRenderProcessClient.onRenderProcessUnresponsive(webView0, webViewRenderProcessImpl0);
    }
}

