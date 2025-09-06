package androidx.webkit;

import androidx.webkit.internal.ServiceWorkerControllerImpl;

public abstract class ServiceWorkerControllerCompat {
    static class LAZY_HOLDER {
        static final ServiceWorkerControllerCompat INSTANCE;

        static {
            LAZY_HOLDER.INSTANCE = new ServiceWorkerControllerImpl();
        }
    }

    public static ServiceWorkerControllerCompat getInstance() {
        return LAZY_HOLDER.INSTANCE;
    }

    public abstract ServiceWorkerWebSettingsCompat getServiceWorkerWebSettings();

    public abstract void setServiceWorkerClient(ServiceWorkerClientCompat arg1);
}

