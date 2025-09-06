package androidx.webkit.internal;

import android.webkit.CookieManager;
import android.webkit.SafeBrowsingResponse;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebMessagePort;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebResourceRequestBoundaryInterface;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import org.chromium.support_lib_boundary.WebStorageBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewCookieManagerBoundaryInterface;
import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebkitToCompatConverter {
    private final WebkitToCompatConverterBoundaryInterface mImpl;

    public WebkitToCompatConverter(WebkitToCompatConverterBoundaryInterface webkitToCompatConverterBoundaryInterface0) {
        this.mImpl = webkitToCompatConverterBoundaryInterface0;
    }

    public CookieManagerAdapter convertCookieManager(CookieManager cookieManager0) {
        InvocationHandler invocationHandler0 = this.mImpl.convertCookieManager(cookieManager0);
        return new CookieManagerAdapter(((WebViewCookieManagerBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewCookieManagerBoundaryInterface.class, invocationHandler0)));
    }

    public SafeBrowsingResponse convertSafeBrowsingResponse(InvocationHandler invocationHandler0) {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(this.mImpl.convertSafeBrowsingResponse(invocationHandler0));
    }

    public InvocationHandler convertSafeBrowsingResponse(SafeBrowsingResponse safeBrowsingResponse0) {
        return this.mImpl.convertSafeBrowsingResponse(safeBrowsingResponse0);
    }

    public ServiceWorkerWebSettings convertServiceWorkerSettings(InvocationHandler invocationHandler0) {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(this.mImpl.convertServiceWorkerSettings(invocationHandler0));
    }

    public InvocationHandler convertServiceWorkerSettings(ServiceWorkerWebSettings serviceWorkerWebSettings0) {
        return this.mImpl.convertServiceWorkerSettings(serviceWorkerWebSettings0);
    }

    public WebSettingsAdapter convertSettings(WebSettings webSettings0) {
        InvocationHandler invocationHandler0 = this.mImpl.convertSettings(webSettings0);
        return new WebSettingsAdapter(((WebSettingsBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebSettingsBoundaryInterface.class, invocationHandler0)));
    }

    public WebMessagePort convertWebMessagePort(InvocationHandler invocationHandler0) {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(this.mImpl.convertWebMessagePort(invocationHandler0));
    }

    public InvocationHandler convertWebMessagePort(WebMessagePort webMessagePort0) {
        return this.mImpl.convertWebMessagePort(webMessagePort0);
    }

    public WebResourceError convertWebResourceError(InvocationHandler invocationHandler0) {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(this.mImpl.convertWebResourceError(invocationHandler0));
    }

    public InvocationHandler convertWebResourceError(WebResourceError webResourceError0) {
        return this.mImpl.convertWebResourceError(webResourceError0);
    }

    public WebResourceRequestAdapter convertWebResourceRequest(WebResourceRequest webResourceRequest0) {
        InvocationHandler invocationHandler0 = this.mImpl.convertWebResourceRequest(webResourceRequest0);
        return new WebResourceRequestAdapter(((WebResourceRequestBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebResourceRequestBoundaryInterface.class, invocationHandler0)));
    }

    public WebStorageAdapter convertWebStorage(WebStorage webStorage0) {
        InvocationHandler invocationHandler0 = this.mImpl.convertWebStorage(webStorage0);
        return new WebStorageAdapter(((WebStorageBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebStorageBoundaryInterface.class, invocationHandler0)));
    }
}

