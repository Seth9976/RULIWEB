package androidx.webkit;

import android.webkit.WebView;

public abstract class WebViewRenderProcessClient {
    public abstract void onRenderProcessResponsive(WebView arg1, WebViewRenderProcess arg2);

    public abstract void onRenderProcessUnresponsive(WebView arg1, WebViewRenderProcess arg2);
}

