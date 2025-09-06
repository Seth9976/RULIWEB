package androidx.webkit.internal;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.webkit.ServiceWorkerClientCompat;
import org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface;

public class ServiceWorkerClientAdapter implements ServiceWorkerClientBoundaryInterface {
    private final ServiceWorkerClientCompat mClient;

    public ServiceWorkerClientAdapter(ServiceWorkerClientCompat serviceWorkerClientCompat0) {
        this.mClient = serviceWorkerClientCompat0;
    }

    @Override  // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[]{"SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST"};
    }

    @Override  // org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface
    public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest0) {
        return this.mClient.shouldInterceptRequest(webResourceRequest0);
    }
}

