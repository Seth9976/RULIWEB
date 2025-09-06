package org.chromium.support_lib_boundary;

import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebkitToCompatConverterBoundaryInterface {
    InvocationHandler convertCookieManager(Object arg1);

    Object convertSafeBrowsingResponse(InvocationHandler arg1);

    InvocationHandler convertSafeBrowsingResponse(Object arg1);

    Object convertServiceWorkerSettings(InvocationHandler arg1);

    InvocationHandler convertServiceWorkerSettings(Object arg1);

    InvocationHandler convertSettings(WebSettings arg1);

    Object convertWebMessagePort(InvocationHandler arg1);

    InvocationHandler convertWebMessagePort(Object arg1);

    Object convertWebResourceError(InvocationHandler arg1);

    InvocationHandler convertWebResourceError(Object arg1);

    InvocationHandler convertWebResourceRequest(WebResourceRequest arg1);

    InvocationHandler convertWebStorage(Object arg1);
}

