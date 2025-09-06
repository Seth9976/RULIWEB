package androidx.webkit.internal;

import android.webkit.WebView;
import androidx.webkit.WebViewRenderProcess;
import androidx.webkit.WebViewRenderProcessClient;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface;

public class WebViewRenderProcessClientAdapter implements WebViewRendererClientBoundaryInterface {
    private final Executor mExecutor;
    private final WebViewRenderProcessClient mWebViewRenderProcessClient;
    private static final String[] sSupportedFeatures;

    static {
        WebViewRenderProcessClientAdapter.sSupportedFeatures = new String[]{"WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE"};
    }

    public WebViewRenderProcessClientAdapter(Executor executor0, WebViewRenderProcessClient webViewRenderProcessClient0) {
        this.mExecutor = executor0;
        this.mWebViewRenderProcessClient = webViewRenderProcessClient0;
    }

    @Override  // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public final String[] getSupportedFeatures() {
        return WebViewRenderProcessClientAdapter.sSupportedFeatures;
    }

    public WebViewRenderProcessClient getWebViewRenderProcessClient() {
        return this.mWebViewRenderProcessClient;
    }

    // 检测为 Lambda 实现
    static void lambda$onRendererResponsive$1(WebViewRenderProcessClient webViewRenderProcessClient0, WebView webView0, WebViewRenderProcess webViewRenderProcess0) [...]

    // 检测为 Lambda 实现
    static void lambda$onRendererUnresponsive$0(WebViewRenderProcessClient webViewRenderProcessClient0, WebView webView0, WebViewRenderProcess webViewRenderProcess0) [...]

    @Override  // org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface
    public final void onRendererResponsive(WebView webView0, InvocationHandler invocationHandler0) {
        WebViewRenderProcessImpl webViewRenderProcessImpl0 = WebViewRenderProcessImpl.forInvocationHandler(invocationHandler0);
        WebViewRenderProcessClient webViewRenderProcessClient0 = this.mWebViewRenderProcessClient;
        Executor executor0 = this.mExecutor;
        if(executor0 == null) {
            webViewRenderProcessClient0.onRenderProcessResponsive(webView0, webViewRenderProcessImpl0);
            return;
        }
        executor0.execute(() -> webViewRenderProcessClient0.onRenderProcessResponsive(webView0, webViewRenderProcessImpl0));
    }

    @Override  // org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface
    public final void onRendererUnresponsive(WebView webView0, InvocationHandler invocationHandler0) {
        WebViewRenderProcessImpl webViewRenderProcessImpl0 = WebViewRenderProcessImpl.forInvocationHandler(invocationHandler0);
        WebViewRenderProcessClient webViewRenderProcessClient0 = this.mWebViewRenderProcessClient;
        Executor executor0 = this.mExecutor;
        if(executor0 == null) {
            webViewRenderProcessClient0.onRenderProcessUnresponsive(webView0, webViewRenderProcessImpl0);
            return;
        }
        executor0.execute(() -> webViewRenderProcessClient0.onRenderProcessUnresponsive(webView0, webViewRenderProcessImpl0));
    }
}

