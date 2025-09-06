package org.chromium.support_lib_boundary;

import android.os.CancellationSignal;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ServiceWorkerController;
import android.webkit.WebStorage;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface ProfileBoundaryInterface {
    void clearPrefetch(String arg1, Executor arg2, InvocationHandler arg3);

    CookieManager getCookieManager();

    GeolocationPermissions getGeoLocationPermissions();

    String getName();

    ServiceWorkerController getServiceWorkerController();

    WebStorage getWebStorage();

    void prefetchUrl(String arg1, CancellationSignal arg2, Executor arg3, InvocationHandler arg4);

    void prefetchUrl(String arg1, CancellationSignal arg2, Executor arg3, InvocationHandler arg4, InvocationHandler arg5);

    void setSpeculativeLoadingConfig(InvocationHandler arg1);
}

