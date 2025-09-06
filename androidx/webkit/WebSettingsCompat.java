package androidx.webkit;

import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebSettings;
import androidx.webkit.internal.ApiHelperForM;
import androidx.webkit.internal.ApiHelperForN;
import androidx.webkit.internal.ApiHelperForO;
import androidx.webkit.internal.ApiHelperForQ;
import androidx.webkit.internal.WebSettingsAdapter;
import androidx.webkit.internal.WebSettingsNoOpAdapter;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

public class WebSettingsCompat {
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
    public @interface ExperimentalBackForwardCache {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
    public @interface ExperimentalSpeculativeLoading {
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER, ElementType.METHOD})
    public @interface ForceDark {
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER, ElementType.METHOD})
    public @interface ForceDarkStrategy {
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER, ElementType.METHOD})
    public @interface MenuItemFlags {
    }

    public static final int ATTRIBUTION_BEHAVIOR_APP_SOURCE_AND_APP_TRIGGER = 3;
    public static final int ATTRIBUTION_BEHAVIOR_APP_SOURCE_AND_WEB_TRIGGER = 1;
    public static final int ATTRIBUTION_BEHAVIOR_DISABLED = 0;
    public static final int ATTRIBUTION_BEHAVIOR_WEB_SOURCE_AND_WEB_TRIGGER = 2;
    @Deprecated
    public static final int DARK_STRATEGY_PREFER_WEB_THEME_OVER_USER_AGENT_DARKENING = 2;
    @Deprecated
    public static final int DARK_STRATEGY_USER_AGENT_DARKENING_ONLY = 0;
    @Deprecated
    public static final int DARK_STRATEGY_WEB_THEME_DARKENING_ONLY = 1;
    @Deprecated
    public static final int FORCE_DARK_AUTO = 1;
    @Deprecated
    public static final int FORCE_DARK_OFF = 0;
    @Deprecated
    public static final int FORCE_DARK_ON = 2;
    public static final int SPECULATIVE_LOADING_DISABLED = 0;
    public static final int SPECULATIVE_LOADING_PRERENDER_ENABLED = 1;
    private static final String TAG = "WebSettingsCompat";
    public static final int WEB_AUTHENTICATION_SUPPORT_FOR_APP = 1;
    public static final int WEB_AUTHENTICATION_SUPPORT_FOR_BROWSER = 2;
    public static final int WEB_AUTHENTICATION_SUPPORT_NONE;

    private static WebSettingsAdapter getAdapter(WebSettings webSettings0) {
        try {
            return WebViewGlueCommunicator.getCompatConverter().convertSettings(webSettings0);
        }
        catch(ClassCastException classCastException0) {
            if(Build.VERSION.SDK_INT != 30 || !"android.webkit.WebSettingsWrapper".equals(webSettings0.getClass().getCanonicalName())) {
                throw classCastException0;
            }
            Log.e("WebSettingsCompat", "Error converting WebSettings to Chrome implementation. All AndroidX method calls on this WebSettings instance will be no-op calls. See https://crbug.com/388824130 for more info.", classCastException0);
            return new WebSettingsNoOpAdapter();
        }
    }

    public static int getAttributionRegistrationBehavior(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.ATTRIBUTION_REGISTRATION_BEHAVIOR.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getAttributionRegistrationBehavior();
    }

    public static boolean getBackForwardCacheEnabled(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.BACK_FORWARD_CACHE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getBackForwardCacheEnabled();
    }

    // 去混淆评级： 低(40)
    public static int getDisabledActionModeMenuItems(WebSettings webSettings0) {
        return ApiHelperForN.getDisabledActionModeMenuItems(webSettings0);
    }

    public static boolean getEnterpriseAuthenticationAppLinkPolicyEnabled(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getEnterpriseAuthenticationAppLinkPolicyEnabled();
    }

    // 去混淆评级： 低(40)
    @Deprecated
    public static int getForceDark(WebSettings webSettings0) {
        return ApiHelperForQ.getForceDark(webSettings0);
    }

    @Deprecated
    public static int getForceDarkStrategy(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.FORCE_DARK_STRATEGY.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getForceDark();
    }

    // 去混淆评级： 低(40)
    public static boolean getOffscreenPreRaster(WebSettings webSettings0) {
        return ApiHelperForM.getOffscreenPreRaster(webSettings0);
    }

    public static Set getRequestedWithHeaderOriginAllowList(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.REQUESTED_WITH_HEADER_ALLOW_LIST.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getRequestedWithHeaderOriginAllowList();
    }

    // 去混淆评级： 低(40)
    public static boolean getSafeBrowsingEnabled(WebSettings webSettings0) {
        return ApiHelperForO.getSafeBrowsingEnabled(webSettings0);
    }

    public static int getSpeculativeLoadingStatus(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.SPECULATIVE_LOADING.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getSpeculativeLoadingStatus();
    }

    public static UserAgentMetadata getUserAgentMetadata(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.USER_AGENT_METADATA.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getUserAgentMetadata();
    }

    public static int getWebAuthenticationSupport(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.WEB_AUTHENTICATION.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getWebAuthenticationSupport();
    }

    public static WebViewMediaIntegrityApiStatusConfig getWebViewMediaIntegrityApiStatus(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.WEBVIEW_MEDIA_INTEGRITY_API_STATUS.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).getWebViewMediaIntegrityApiStatus();
    }

    public static boolean isAlgorithmicDarkeningAllowed(WebSettings webSettings0) {
        if(!WebViewFeatureInternal.ALGORITHMIC_DARKENING.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebSettingsCompat.getAdapter(webSettings0).isAlgorithmicDarkeningAllowed();
    }

    public static void setAlgorithmicDarkeningAllowed(WebSettings webSettings0, boolean z) {
        if(!WebViewFeatureInternal.ALGORITHMIC_DARKENING.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setAlgorithmicDarkeningAllowed(z);
    }

    public static void setAttributionRegistrationBehavior(WebSettings webSettings0, int v) {
        if(!WebViewFeatureInternal.ATTRIBUTION_REGISTRATION_BEHAVIOR.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setAttributionRegistrationBehavior(v);
    }

    public static void setBackForwardCacheEnabled(WebSettings webSettings0, boolean z) {
        if(!WebViewFeatureInternal.BACK_FORWARD_CACHE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setBackForwardCacheEnabled(z);
    }

    // 去混淆评级： 低(20)
    public static void setDisabledActionModeMenuItems(WebSettings webSettings0, int v) {
        ApiHelperForN.setDisabledActionModeMenuItems(webSettings0, v);
    }

    public static void setEnterpriseAuthenticationAppLinkPolicyEnabled(WebSettings webSettings0, boolean z) {
        if(!WebViewFeatureInternal.ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setEnterpriseAuthenticationAppLinkPolicyEnabled(z);
    }

    // 去混淆评级： 低(20)
    @Deprecated
    public static void setForceDark(WebSettings webSettings0, int v) {
        ApiHelperForQ.setForceDark(webSettings0, v);
    }

    @Deprecated
    public static void setForceDarkStrategy(WebSettings webSettings0, int v) {
        if(!WebViewFeatureInternal.FORCE_DARK_STRATEGY.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setForceDarkStrategy(v);
    }

    // 去混淆评级： 低(20)
    public static void setOffscreenPreRaster(WebSettings webSettings0, boolean z) {
        ApiHelperForM.setOffscreenPreRaster(webSettings0, z);
    }

    public static void setRequestedWithHeaderOriginAllowList(WebSettings webSettings0, Set set0) {
        if(!WebViewFeatureInternal.REQUESTED_WITH_HEADER_ALLOW_LIST.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setRequestedWithHeaderOriginAllowList(set0);
    }

    // 去混淆评级： 低(20)
    public static void setSafeBrowsingEnabled(WebSettings webSettings0, boolean z) {
        ApiHelperForO.setSafeBrowsingEnabled(webSettings0, z);
    }

    public static void setSpeculativeLoadingStatus(WebSettings webSettings0, int v) {
        if(!WebViewFeatureInternal.SPECULATIVE_LOADING.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setSpeculativeLoadingStatus(v);
    }

    public static void setUserAgentMetadata(WebSettings webSettings0, UserAgentMetadata userAgentMetadata0) {
        if(!WebViewFeatureInternal.USER_AGENT_METADATA.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setUserAgentMetadata(userAgentMetadata0);
    }

    public static void setWebAuthenticationSupport(WebSettings webSettings0, int v) {
        if(!WebViewFeatureInternal.WEB_AUTHENTICATION.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setWebAuthenticationSupport(v);
    }

    public static void setWebViewMediaIntegrityApiStatus(WebSettings webSettings0, WebViewMediaIntegrityApiStatusConfig webViewMediaIntegrityApiStatusConfig0) {
        if(!WebViewFeatureInternal.WEBVIEW_MEDIA_INTEGRITY_API_STATUS.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebSettingsCompat.getAdapter(webSettings0).setWebViewMediaIntegrityApiStatus(webViewMediaIntegrityApiStatusConfig0);
    }
}

