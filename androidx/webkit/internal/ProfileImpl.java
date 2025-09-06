package androidx.webkit.internal;

import android.os.CancellationSignal;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ServiceWorkerController;
import android.webkit.WebStorage;
import androidx.webkit.OutcomeReceiverCompat;
import androidx.webkit.Profile;
import androidx.webkit.SpeculativeLoadingConfig;
import androidx.webkit.SpeculativeLoadingParameters;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.ProfileBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class ProfileImpl implements Profile {
    private final ProfileBoundaryInterface mProfileImpl;

    private ProfileImpl() {
        this.mProfileImpl = null;
    }

    ProfileImpl(ProfileBoundaryInterface profileBoundaryInterface0) {
        this.mProfileImpl = profileBoundaryInterface0;
    }

    @Override  // androidx.webkit.Profile
    public void clearPrefetchAsync(String s, Executor executor0, OutcomeReceiverCompat outcomeReceiverCompat0) {
        if(!WebViewFeatureInternal.PROFILE_URL_PREFETCH.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        InvocationHandler invocationHandler0 = PrefetchOperationCallbackAdapter.buildInvocationHandler(outcomeReceiverCompat0);
        this.mProfileImpl.clearPrefetch(s, executor0, invocationHandler0);
    }

    @Override  // androidx.webkit.Profile
    public CookieManager getCookieManager() throws IllegalStateException {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.mProfileImpl.getCookieManager();
    }

    @Override  // androidx.webkit.Profile
    public GeolocationPermissions getGeolocationPermissions() throws IllegalStateException {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.mProfileImpl.getGeoLocationPermissions();
    }

    @Override  // androidx.webkit.Profile
    public String getName() {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.mProfileImpl.getName();
    }

    @Override  // androidx.webkit.Profile
    public ServiceWorkerController getServiceWorkerController() throws IllegalStateException {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.mProfileImpl.getServiceWorkerController();
    }

    @Override  // androidx.webkit.Profile
    public WebStorage getWebStorage() throws IllegalStateException {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.mProfileImpl.getWebStorage();
    }

    @Override  // androidx.webkit.Profile
    public void prefetchUrlAsync(String s, CancellationSignal cancellationSignal0, Executor executor0, OutcomeReceiverCompat outcomeReceiverCompat0) {
        if(!WebViewFeatureInternal.PROFILE_URL_PREFETCH.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        InvocationHandler invocationHandler0 = PrefetchOperationCallbackAdapter.buildInvocationHandler(outcomeReceiverCompat0);
        this.mProfileImpl.prefetchUrl(s, cancellationSignal0, executor0, invocationHandler0);
    }

    @Override  // androidx.webkit.Profile
    public void prefetchUrlAsync(String s, CancellationSignal cancellationSignal0, Executor executor0, SpeculativeLoadingParameters speculativeLoadingParameters0, OutcomeReceiverCompat outcomeReceiverCompat0) {
        if(!WebViewFeatureInternal.PROFILE_URL_PREFETCH.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new SpeculativeLoadingParametersAdapter(speculativeLoadingParameters0));
        InvocationHandler invocationHandler1 = PrefetchOperationCallbackAdapter.buildInvocationHandler(outcomeReceiverCompat0);
        this.mProfileImpl.prefetchUrl(s, cancellationSignal0, executor0, invocationHandler0, invocationHandler1);
    }

    @Override  // androidx.webkit.Profile
    public void setSpeculativeLoadingConfig(SpeculativeLoadingConfig speculativeLoadingConfig0) {
        if(!WebViewFeatureInternal.SPECULATIVE_LOADING_CONFIG.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new SpeculativeLoadingConfigAdapter(speculativeLoadingConfig0));
        this.mProfileImpl.setSpeculativeLoadingConfig(invocationHandler0);
    }
}

