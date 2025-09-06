package androidx.webkit.internal;

import android.webkit.WebView;
import androidx.webkit.WebViewCompat.WebViewStartUpCallback;
import androidx.webkit.WebViewStartUpConfig;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.DropDataContentProviderBoundaryInterface;
import org.chromium.support_lib_boundary.ProfileStoreBoundaryInterface;
import org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface;
import org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface;
import org.chromium.support_lib_boundary.StaticsBoundaryInterface;
import org.chromium.support_lib_boundary.TracingControllerBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;
import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebViewProviderFactoryAdapter implements WebViewProviderFactory {
    final WebViewProviderFactoryBoundaryInterface mImpl;

    public WebViewProviderFactoryAdapter(WebViewProviderFactoryBoundaryInterface webViewProviderFactoryBoundaryInterface0) {
        this.mImpl = webViewProviderFactoryBoundaryInterface0;
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public WebViewProviderBoundaryInterface createWebView(WebView webView0) {
        InvocationHandler invocationHandler0 = this.mImpl.createWebView(webView0);
        return (WebViewProviderBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewProviderBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public DropDataContentProviderBoundaryInterface getDropDataProvider() {
        InvocationHandler invocationHandler0 = this.mImpl.getDropDataProvider();
        return (DropDataContentProviderBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(DropDataContentProviderBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public ProfileStoreBoundaryInterface getProfileStore() {
        InvocationHandler invocationHandler0 = this.mImpl.getProfileStore();
        return (ProfileStoreBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ProfileStoreBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public ProxyControllerBoundaryInterface getProxyController() {
        InvocationHandler invocationHandler0 = this.mImpl.getProxyController();
        return (ProxyControllerBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ProxyControllerBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public ServiceWorkerControllerBoundaryInterface getServiceWorkerController() {
        InvocationHandler invocationHandler0 = this.mImpl.getServiceWorkerController();
        return (ServiceWorkerControllerBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ServiceWorkerControllerBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public StaticsBoundaryInterface getStatics() {
        InvocationHandler invocationHandler0 = this.mImpl.getStatics();
        return (StaticsBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(StaticsBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public TracingControllerBoundaryInterface getTracingController() {
        InvocationHandler invocationHandler0 = this.mImpl.getTracingController();
        return (TracingControllerBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(TracingControllerBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public String[] getWebViewFeatures() {
        return this.mImpl.getSupportedFeatures();
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public WebkitToCompatConverterBoundaryInterface getWebkitToCompatConverter() {
        InvocationHandler invocationHandler0 = this.mImpl.getWebkitToCompatConverter();
        return (WebkitToCompatConverterBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebkitToCompatConverterBoundaryInterface.class, invocationHandler0);
    }

    @Override  // androidx.webkit.internal.WebViewProviderFactory
    public void startUpWebView(WebViewStartUpConfig webViewStartUpConfig0, WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0) {
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebViewStartUpConfigAdapter(webViewStartUpConfig0));
        InvocationHandler invocationHandler1 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebViewStartUpCallbackAdapter(webViewCompat$WebViewStartUpCallback0));
        this.mImpl.startUpWebView(invocationHandler0, invocationHandler1);
    }
}

