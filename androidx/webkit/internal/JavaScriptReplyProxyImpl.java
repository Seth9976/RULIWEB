package androidx.webkit.internal;

import androidx.webkit.JavaScriptReplyProxy;
import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import org.chromium.support_lib_boundary.JsReplyProxyBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class JavaScriptReplyProxyImpl extends JavaScriptReplyProxy {
    private final JsReplyProxyBoundaryInterface mBoundaryInterface;

    public JavaScriptReplyProxyImpl(JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface0) {
        this.mBoundaryInterface = jsReplyProxyBoundaryInterface0;
    }

    public static JavaScriptReplyProxyImpl forInvocationHandler(InvocationHandler invocationHandler0) {
        JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface0 = (JsReplyProxyBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(JsReplyProxyBoundaryInterface.class, invocationHandler0);
        return (JavaScriptReplyProxyImpl)jsReplyProxyBoundaryInterface0.getOrCreatePeer(() -> new JavaScriptReplyProxyImpl(jsReplyProxyBoundaryInterface0));
    }

    // 检测为 Lambda 实现
    static Object lambda$forInvocationHandler$0(JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface0) throws Exception [...]

    @Override  // androidx.webkit.JavaScriptReplyProxy
    public void postMessage(String s) {
        if(!WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.mBoundaryInterface.postMessage(s);
    }

    @Override  // androidx.webkit.JavaScriptReplyProxy
    public void postMessage(byte[] arr_b) {
        Objects.requireNonNull(arr_b, "ArrayBuffer must be non-null");
        if(!WebViewFeatureInternal.WEB_MESSAGE_ARRAY_BUFFER.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessagePayloadAdapter(arr_b));
        this.mBoundaryInterface.postMessageWithPayload(invocationHandler0);
    }
}

