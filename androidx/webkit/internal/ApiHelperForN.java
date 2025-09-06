package androidx.webkit.internal;

import android.content.Context;
import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import androidx.webkit.ServiceWorkerClientCompat;
import java.io.File;

public class ApiHelperForN {
    public static boolean getAllowContentAccess(ServiceWorkerWebSettings serviceWorkerWebSettings0) {
        return serviceWorkerWebSettings0.getAllowContentAccess();
    }

    public static boolean getAllowFileAccess(ServiceWorkerWebSettings serviceWorkerWebSettings0) {
        return serviceWorkerWebSettings0.getAllowFileAccess();
    }

    public static boolean getBlockNetworkLoads(ServiceWorkerWebSettings serviceWorkerWebSettings0) {
        return serviceWorkerWebSettings0.getBlockNetworkLoads();
    }

    public static int getCacheMode(ServiceWorkerWebSettings serviceWorkerWebSettings0) {
        return serviceWorkerWebSettings0.getCacheMode();
    }

    public static File getDataDir(Context context0) {
        return context0.getDataDir();
    }

    public static int getDisabledActionModeMenuItems(WebSettings webSettings0) {
        return webSettings0.getDisabledActionModeMenuItems();
    }

    public static ServiceWorkerController getServiceWorkerControllerInstance() {
        return ServiceWorkerController.getInstance();
    }

    public static ServiceWorkerWebSettings getServiceWorkerWebSettings(ServiceWorkerController serviceWorkerController0) {
        return serviceWorkerController0.getServiceWorkerWebSettings();
    }

    public static ServiceWorkerWebSettingsImpl getServiceWorkerWebSettingsImpl(ServiceWorkerController serviceWorkerController0) {
        return new ServiceWorkerWebSettingsImpl(ApiHelperForN.getServiceWorkerWebSettings(serviceWorkerController0));
    }

    public static boolean isRedirect(WebResourceRequest webResourceRequest0) {
        return webResourceRequest0.isRedirect();
    }

    public static void setAllowContentAccess(ServiceWorkerWebSettings serviceWorkerWebSettings0, boolean z) {
        serviceWorkerWebSettings0.setAllowContentAccess(z);
    }

    public static void setAllowFileAccess(ServiceWorkerWebSettings serviceWorkerWebSettings0, boolean z) {
        serviceWorkerWebSettings0.setAllowFileAccess(z);
    }

    public static void setBlockNetworkLoads(ServiceWorkerWebSettings serviceWorkerWebSettings0, boolean z) {
        serviceWorkerWebSettings0.setBlockNetworkLoads(z);
    }

    public static void setCacheMode(ServiceWorkerWebSettings serviceWorkerWebSettings0, int v) {
        serviceWorkerWebSettings0.setCacheMode(v);
    }

    public static void setDisabledActionModeMenuItems(WebSettings webSettings0, int v) {
        webSettings0.setDisabledActionModeMenuItems(v);
    }

    public static void setServiceWorkerClient(ServiceWorkerController serviceWorkerController0, ServiceWorkerClient serviceWorkerClient0) {
        serviceWorkerController0.setServiceWorkerClient(serviceWorkerClient0);
    }

    public static void setServiceWorkerClientCompat(ServiceWorkerController serviceWorkerController0, ServiceWorkerClientCompat serviceWorkerClientCompat0) {
        serviceWorkerController0.setServiceWorkerClient(new FrameworkServiceWorkerClient(serviceWorkerClientCompat0));
    }
}

