package androidx.webkit.internal;

import androidx.webkit.WebViewRenderProcess;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.util.WeakHashMap;
import org.chromium.support_lib_boundary.WebViewRendererBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebViewRenderProcessImpl extends WebViewRenderProcess {
    private WebViewRendererBoundaryInterface mBoundaryInterface;
    private WeakReference mFrameworkObject;
    private static final WeakHashMap sFrameworkMap;

    static {
        WebViewRenderProcessImpl.sFrameworkMap = new WeakHashMap();
    }

    public WebViewRenderProcessImpl(android.webkit.WebViewRenderProcess webViewRenderProcess0) {
        this.mFrameworkObject = new WeakReference(webViewRenderProcess0);
    }

    public WebViewRenderProcessImpl(WebViewRendererBoundaryInterface webViewRendererBoundaryInterface0) {
        this.mBoundaryInterface = webViewRendererBoundaryInterface0;
    }

    public static WebViewRenderProcessImpl forFrameworkObject(android.webkit.WebViewRenderProcess webViewRenderProcess0) {
        WeakHashMap weakHashMap0 = WebViewRenderProcessImpl.sFrameworkMap;
        WebViewRenderProcessImpl webViewRenderProcessImpl0 = (WebViewRenderProcessImpl)weakHashMap0.get(webViewRenderProcess0);
        if(webViewRenderProcessImpl0 != null) {
            return webViewRenderProcessImpl0;
        }
        WebViewRenderProcessImpl webViewRenderProcessImpl1 = new WebViewRenderProcessImpl(webViewRenderProcess0);
        weakHashMap0.put(webViewRenderProcess0, webViewRenderProcessImpl1);
        return webViewRenderProcessImpl1;
    }

    public static WebViewRenderProcessImpl forInvocationHandler(InvocationHandler invocationHandler0) {
        WebViewRendererBoundaryInterface webViewRendererBoundaryInterface0 = (WebViewRendererBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewRendererBoundaryInterface.class, invocationHandler0);
        return (WebViewRenderProcessImpl)webViewRendererBoundaryInterface0.getOrCreatePeer(() -> new WebViewRenderProcessImpl(webViewRendererBoundaryInterface0));
    }

    // 检测为 Lambda 实现
    static Object lambda$forInvocationHandler$0(WebViewRendererBoundaryInterface webViewRendererBoundaryInterface0) throws Exception [...]

    // 去混淆评级： 低(25)
    @Override  // androidx.webkit.WebViewRenderProcess
    public boolean terminate() {
        android.webkit.WebViewRenderProcess webViewRenderProcess0 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(this.mFrameworkObject.get());
        return webViewRenderProcess0 != null && ApiHelperForQ.terminate(webViewRenderProcess0);
    }
}

