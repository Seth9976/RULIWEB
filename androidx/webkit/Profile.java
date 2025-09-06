package androidx.webkit;

import android.os.CancellationSignal;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ServiceWorkerController;
import android.webkit.WebStorage;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.Executor;

public interface Profile {
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    public @interface ExperimentalUrlPrefetch {
    }

    public static final String DEFAULT_PROFILE_NAME = "Default";

    void clearPrefetchAsync(String arg1, Executor arg2, OutcomeReceiverCompat arg3);

    CookieManager getCookieManager();

    GeolocationPermissions getGeolocationPermissions();

    String getName();

    ServiceWorkerController getServiceWorkerController();

    WebStorage getWebStorage();

    void prefetchUrlAsync(String arg1, CancellationSignal arg2, Executor arg3, OutcomeReceiverCompat arg4);

    void prefetchUrlAsync(String arg1, CancellationSignal arg2, Executor arg3, SpeculativeLoadingParameters arg4, OutcomeReceiverCompat arg5);

    void setSpeculativeLoadingConfig(SpeculativeLoadingConfig arg1);
}

