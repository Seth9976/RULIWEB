package androidx.webkit;

import android.app.PendingIntent;
import android.os.Build.VERSION;
import android.webkit.SafeBrowsingResponse;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.internal.SafeBrowsingResponseImpl;
import androidx.webkit.internal.WebResourceErrorImpl;
import androidx.webkit.internal.WebViewFeatureInternal;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebViewClientBoundaryInterface;

public class WebViewClientCompat extends WebViewClient implements WebViewClientBoundaryInterface {
    @Retention(RetentionPolicy.SOURCE)
    public @interface SafeBrowsingThreat {
    }

    private static final String[] sSupportedFeatures;

    static {
        WebViewClientCompat.sSupportedFeatures = new String[]{"VISUAL_STATE_CALLBACK", "RECEIVE_WEB_RESOURCE_ERROR", "RECEIVE_HTTP_ERROR", "SHOULD_OVERRIDE_WITH_REDIRECTS", "SAFE_BROWSING_HIT"};
    }

    @Override  // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public final String[] getSupportedFeatures() {
        return WebViewClientCompat.sSupportedFeatures;
    }

    @Override  // android.webkit.WebViewClient, org.chromium.support_lib_boundary.WebViewClientBoundaryInterface
    public void onPageCommitVisible(WebView webView0, String s) {
    }

    @Override  // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView0, WebResourceRequest webResourceRequest0, WebResourceError webResourceError0) {
        if(Build.VERSION.SDK_INT < 23) {
            return;
        }
        this.onReceivedError(webView0, webResourceRequest0, new WebResourceErrorImpl(webResourceError0));
    }

    public void onReceivedError(WebView webView0, WebResourceRequest webResourceRequest0, WebResourceErrorCompat webResourceErrorCompat0) {
        if(WebViewFeature.isFeatureSupported("WEB_RESOURCE_ERROR_GET_CODE") && WebViewFeature.isFeatureSupported("WEB_RESOURCE_ERROR_GET_DESCRIPTION") && webResourceRequest0.isForMainFrame()) {
            this.onReceivedError(webView0, webResourceErrorCompat0.getErrorCode(), webResourceErrorCompat0.getDescription().toString(), webResourceRequest0.getUrl().toString());
        }
    }

    @Override  // org.chromium.support_lib_boundary.WebViewClientBoundaryInterface
    public final void onReceivedError(WebView webView0, WebResourceRequest webResourceRequest0, InvocationHandler invocationHandler0) {
        this.onReceivedError(webView0, webResourceRequest0, new WebResourceErrorImpl(invocationHandler0));
    }

    @Override  // android.webkit.WebViewClient, org.chromium.support_lib_boundary.WebViewClientBoundaryInterface
    public void onReceivedHttpError(WebView webView0, WebResourceRequest webResourceRequest0, WebResourceResponse webResourceResponse0) {
    }

    @Override  // android.webkit.WebViewClient
    public final void onSafeBrowsingHit(WebView webView0, WebResourceRequest webResourceRequest0, int v, SafeBrowsingResponse safeBrowsingResponse0) {
        this.onSafeBrowsingHit(webView0, webResourceRequest0, v, new SafeBrowsingResponseImpl(safeBrowsingResponse0));
    }

    public void onSafeBrowsingHit(WebView webView0, WebResourceRequest webResourceRequest0, int v, SafeBrowsingResponseCompat safeBrowsingResponseCompat0) {
        if(!WebViewFeature.isFeatureSupported("SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL")) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        safeBrowsingResponseCompat0.showInterstitial(true);
    }

    @Override  // org.chromium.support_lib_boundary.WebViewClientBoundaryInterface
    public final void onSafeBrowsingHit(WebView webView0, WebResourceRequest webResourceRequest0, int v, InvocationHandler invocationHandler0) {
        this.onSafeBrowsingHit(webView0, webResourceRequest0, v, new SafeBrowsingResponseImpl(invocationHandler0));
    }

    public boolean onWebAuthnIntent(WebView webView0, PendingIntent pendingIntent0, InvocationHandler invocationHandler0) {
        return false;
    }

    @Override  // android.webkit.WebViewClient, org.chromium.support_lib_boundary.WebViewClientBoundaryInterface
    public boolean shouldOverrideUrlLoading(WebView webView0, WebResourceRequest webResourceRequest0) {
        return this.shouldOverrideUrlLoading(webView0, webResourceRequest0.getUrl().toString());
    }
}

