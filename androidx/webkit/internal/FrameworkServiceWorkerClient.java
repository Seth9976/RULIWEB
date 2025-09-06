package androidx.webkit.internal;

import android.webkit.ServiceWorkerClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.webkit.ServiceWorkerClientCompat;

public class FrameworkServiceWorkerClient extends ServiceWorkerClient {
    private final ServiceWorkerClientCompat mImpl;

    public FrameworkServiceWorkerClient(ServiceWorkerClientCompat serviceWorkerClientCompat0) {
        this.mImpl = serviceWorkerClientCompat0;
    }

    @Override  // android.webkit.ServiceWorkerClient
    public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest0) {
        return this.mImpl.shouldInterceptRequest(webResourceRequest0);
    }
}

