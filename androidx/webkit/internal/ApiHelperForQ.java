package androidx.webkit.internal;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import java.util.concurrent.Executor;

public class ApiHelperForQ {
    @Deprecated
    public static int getForceDark(WebSettings webSettings0) {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(webSettings0);
    }

    public static WebViewRenderProcess getWebViewRenderProcess(WebView webView0) {
        return webView0.getWebViewRenderProcess();
    }

    public static WebViewRenderProcessClient getWebViewRenderProcessClient(WebView webView0) {
        return webView0.getWebViewRenderProcessClient();
    }

    @Deprecated
    public static void setForceDark(WebSettings webSettings0, int v) {
        NetworkApi23..ExternalSyntheticApiModelOutline0.m(webSettings0, v);
    }

    public static void setWebViewRenderProcessClient(WebView webView0, androidx.webkit.WebViewRenderProcessClient webViewRenderProcessClient0) {
        webView0.setWebViewRenderProcessClient((webViewRenderProcessClient0 == null ? null : new WebViewRenderProcessClientFrameworkAdapter(webViewRenderProcessClient0)));
    }

    public static void setWebViewRenderProcessClient(WebView webView0, Executor executor0, androidx.webkit.WebViewRenderProcessClient webViewRenderProcessClient0) {
        NetworkApi23..ExternalSyntheticApiModelOutline0.m(webView0, executor0, (webViewRenderProcessClient0 == null ? null : new WebViewRenderProcessClientFrameworkAdapter(webViewRenderProcessClient0)));
    }

    public static boolean terminate(WebViewRenderProcess webViewRenderProcess0) {
        return webViewRenderProcess0.terminate();
    }
}

