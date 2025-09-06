package androidx.webkit.internal;

import android.webkit.ServiceWorkerController;
import androidx.webkit.ServiceWorkerClientCompat;
import androidx.webkit.ServiceWorkerControllerCompat;
import androidx.webkit.ServiceWorkerWebSettingsCompat;
import org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface;

public class ServiceWorkerControllerImpl extends ServiceWorkerControllerCompat {
    private ServiceWorkerControllerBoundaryInterface mBoundaryInterface;
    private ServiceWorkerController mFrameworksImpl;
    private final ServiceWorkerWebSettingsCompat mWebSettings;

    public ServiceWorkerControllerImpl() {
        this.mFrameworksImpl = ApiHelperForN.getServiceWorkerControllerInstance();
        this.mBoundaryInterface = null;
        this.mWebSettings = ApiHelperForN.getServiceWorkerWebSettingsImpl(this.getFrameworksImpl());
    }

    private ServiceWorkerControllerBoundaryInterface getBoundaryInterface() {
        if(this.mBoundaryInterface == null) {
            this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getServiceWorkerController();
        }
        return this.mBoundaryInterface;
    }

    private ServiceWorkerController getFrameworksImpl() {
        if(this.mFrameworksImpl == null) {
            this.mFrameworksImpl = ApiHelperForN.getServiceWorkerControllerInstance();
        }
        return this.mFrameworksImpl;
    }

    @Override  // androidx.webkit.ServiceWorkerControllerCompat
    public ServiceWorkerWebSettingsCompat getServiceWorkerWebSettings() {
        return this.mWebSettings;
    }

    @Override  // androidx.webkit.ServiceWorkerControllerCompat
    public void setServiceWorkerClient(ServiceWorkerClientCompat serviceWorkerClientCompat0) {
        if(serviceWorkerClientCompat0 == null) {
            ApiHelperForN.setServiceWorkerClient(this.getFrameworksImpl(), null);
            return;
        }
        ApiHelperForN.setServiceWorkerClientCompat(this.getFrameworksImpl(), serviceWorkerClientCompat0);
    }
}

