package androidx.webkit.internal;

import android.net.Uri;
import android.webkit.WebView;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat.WebMessageListener;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessageListenerBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebMessageListenerAdapter implements WebMessageListenerBoundaryInterface {
    private final WebMessageListener mWebMessageListener;

    public WebMessageListenerAdapter(WebMessageListener webViewCompat$WebMessageListener0) {
        this.mWebMessageListener = webViewCompat$WebMessageListener0;
    }

    @Override  // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[]{"WEB_MESSAGE_LISTENER", "WEB_MESSAGE_ARRAY_BUFFER"};
    }

    @Override  // org.chromium.support_lib_boundary.WebMessageListenerBoundaryInterface
    public void onPostMessage(WebView webView0, InvocationHandler invocationHandler0, Uri uri0, boolean z, InvocationHandler invocationHandler1) {
        WebMessageCompat webMessageCompat0 = WebMessageAdapter.webMessageCompatFromBoundaryInterface(((WebMessageBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebMessageBoundaryInterface.class, invocationHandler0)));
        if(webMessageCompat0 != null) {
            JavaScriptReplyProxyImpl javaScriptReplyProxyImpl0 = JavaScriptReplyProxyImpl.forInvocationHandler(invocationHandler1);
            this.mWebMessageListener.onPostMessage(webView0, webMessageCompat0, uri0, z, javaScriptReplyProxyImpl0);
        }
    }
}

