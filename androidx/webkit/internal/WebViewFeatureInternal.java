package androidx.webkit.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebViewFeatureInternal {
    public static final T ALGORITHMIC_DARKENING;
    public static final NoFramework ASYNC_WEBVIEW_STARTUP;
    public static final NoFramework ATTRIBUTION_REGISTRATION_BEHAVIOR;
    public static final NoFramework BACK_FORWARD_CACHE;
    public static final M CREATE_WEB_MESSAGE_CHANNEL;
    public static final NoFramework DEFAULT_TRAFFICSTATS_TAGGING;
    public static final NoFramework DELETE_BROWSING_DATA;
    public static final N DISABLED_ACTION_MODE_MENU_ITEMS;
    public static final NoFramework DOCUMENT_START_SCRIPT;
    public static final NoFramework ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY;
    public static final Q FORCE_DARK;
    public static final NoFramework FORCE_DARK_STRATEGY;
    public static final NoFramework GET_COOKIE_INFO;
    public static final NoFramework GET_VARIATIONS_HEADER;
    public static final O GET_WEB_CHROME_CLIENT;
    public static final O GET_WEB_VIEW_CLIENT;
    public static final Q GET_WEB_VIEW_RENDERER;
    public static final NoFramework MULTI_PROCESS;
    public static final NoFramework MULTI_PROFILE;
    public static final NoFramework MUTE_AUDIO;
    public static final M OFF_SCREEN_PRERASTER;
    public static final M POST_WEB_MESSAGE;
    public static final NoFramework PRERENDER_WITH_URL;
    public static final NoFramework PROFILE_URL_PREFETCH;
    public static final NoFramework PROXY_OVERRIDE;
    public static final NoFramework PROXY_OVERRIDE_REVERSE_BYPASS;
    public static final M RECEIVE_HTTP_ERROR;
    public static final M RECEIVE_WEB_RESOURCE_ERROR;
    public static final NoFramework REQUESTED_WITH_HEADER_ALLOW_LIST;
    @Deprecated
    public static final O_MR1 SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_DEPRECATED;
    @Deprecated
    public static final O_MR1 SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_PREFERRED;
    public static final O_MR1 SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_DEPRECATED;
    public static final O_MR1 SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_PREFERRED;
    public static final O SAFE_BROWSING_ENABLE;
    public static final O_MR1 SAFE_BROWSING_HIT;
    public static final O_MR1 SAFE_BROWSING_PRIVACY_POLICY_URL;
    public static final O_MR1 SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY;
    public static final O_MR1 SAFE_BROWSING_RESPONSE_PROCEED;
    public static final O_MR1 SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL;
    public static final N SERVICE_WORKER_BASIC_USAGE;
    public static final N SERVICE_WORKER_BLOCK_NETWORK_LOADS;
    public static final N SERVICE_WORKER_CACHE_MODE;
    public static final N SERVICE_WORKER_CONTENT_ACCESS;
    public static final N SERVICE_WORKER_FILE_ACCESS;
    public static final N SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST;
    public static final N SHOULD_OVERRIDE_WITH_REDIRECTS;
    public static final NoFramework SPECULATIVE_LOADING;
    public static final NoFramework SPECULATIVE_LOADING_CONFIG;
    public static final androidx.webkit.internal.StartupApiFeature.NoFramework STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES;
    public static final P STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX;
    public static final androidx.webkit.internal.StartupApiFeature.NoFramework STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH;
    public static final O_MR1 START_SAFE_BROWSING;
    public static final androidx.webkit.internal.ApiFeature.P TRACING_CONTROLLER_BASIC_USAGE;
    public static final NoFramework USER_AGENT_METADATA;
    public static final M VISUAL_STATE_CALLBACK;
    public static final NoFramework WEBVIEW_MEDIA_INTEGRITY_API_STATUS;
    public static final NoFramework WEB_AUTHENTICATION;
    public static final NoFramework WEB_MESSAGE_ARRAY_BUFFER;
    public static final M WEB_MESSAGE_CALLBACK_ON_MESSAGE;
    public static final NoFramework WEB_MESSAGE_LISTENER;
    public static final M WEB_MESSAGE_PORT_CLOSE;
    public static final M WEB_MESSAGE_PORT_POST_MESSAGE;
    public static final M WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK;
    public static final M WEB_RESOURCE_ERROR_GET_CODE;
    public static final M WEB_RESOURCE_ERROR_GET_DESCRIPTION;
    public static final N WEB_RESOURCE_REQUEST_IS_REDIRECT;
    public static final Q WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE;
    public static final Q WEB_VIEW_RENDERER_TERMINATE;

    static {
        WebViewFeatureInternal.VISUAL_STATE_CALLBACK = new M("VISUAL_STATE_CALLBACK", "VISUAL_STATE_CALLBACK");
        WebViewFeatureInternal.OFF_SCREEN_PRERASTER = new M("OFF_SCREEN_PRERASTER", "OFF_SCREEN_PRERASTER");
        WebViewFeatureInternal.SAFE_BROWSING_ENABLE = new O("SAFE_BROWSING_ENABLE", "SAFE_BROWSING_ENABLE");
        WebViewFeatureInternal.DISABLED_ACTION_MODE_MENU_ITEMS = new N("DISABLED_ACTION_MODE_MENU_ITEMS", "DISABLED_ACTION_MODE_MENU_ITEMS");
        WebViewFeatureInternal.START_SAFE_BROWSING = new O_MR1("START_SAFE_BROWSING", "START_SAFE_BROWSING");
        WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_DEPRECATED = new O_MR1("SAFE_BROWSING_WHITELIST", "SAFE_BROWSING_WHITELIST");
        WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_PREFERRED = new O_MR1("SAFE_BROWSING_WHITELIST", "SAFE_BROWSING_ALLOWLIST");
        WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_DEPRECATED = new O_MR1("SAFE_BROWSING_ALLOWLIST", "SAFE_BROWSING_WHITELIST");
        WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_PREFERRED = new O_MR1("SAFE_BROWSING_ALLOWLIST", "SAFE_BROWSING_ALLOWLIST");
        WebViewFeatureInternal.SAFE_BROWSING_PRIVACY_POLICY_URL = new O_MR1("SAFE_BROWSING_PRIVACY_POLICY_URL", "SAFE_BROWSING_PRIVACY_POLICY_URL");
        WebViewFeatureInternal.SERVICE_WORKER_BASIC_USAGE = new N("SERVICE_WORKER_BASIC_USAGE", "SERVICE_WORKER_BASIC_USAGE");
        WebViewFeatureInternal.SERVICE_WORKER_CACHE_MODE = new N("SERVICE_WORKER_CACHE_MODE", "SERVICE_WORKER_CACHE_MODE");
        WebViewFeatureInternal.SERVICE_WORKER_CONTENT_ACCESS = new N("SERVICE_WORKER_CONTENT_ACCESS", "SERVICE_WORKER_CONTENT_ACCESS");
        WebViewFeatureInternal.SERVICE_WORKER_FILE_ACCESS = new N("SERVICE_WORKER_FILE_ACCESS", "SERVICE_WORKER_FILE_ACCESS");
        WebViewFeatureInternal.SERVICE_WORKER_BLOCK_NETWORK_LOADS = new N("SERVICE_WORKER_BLOCK_NETWORK_LOADS", "SERVICE_WORKER_BLOCK_NETWORK_LOADS");
        WebViewFeatureInternal.SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST = new N("SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST", "SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST");
        WebViewFeatureInternal.RECEIVE_WEB_RESOURCE_ERROR = new M("RECEIVE_WEB_RESOURCE_ERROR", "RECEIVE_WEB_RESOURCE_ERROR");
        WebViewFeatureInternal.RECEIVE_HTTP_ERROR = new M("RECEIVE_HTTP_ERROR", "RECEIVE_HTTP_ERROR");
        WebViewFeatureInternal.SHOULD_OVERRIDE_WITH_REDIRECTS = new N("SHOULD_OVERRIDE_WITH_REDIRECTS", "SHOULD_OVERRIDE_WITH_REDIRECTS");
        WebViewFeatureInternal.SAFE_BROWSING_HIT = new O_MR1("SAFE_BROWSING_HIT", "SAFE_BROWSING_HIT");
        WebViewFeatureInternal.WEB_RESOURCE_REQUEST_IS_REDIRECT = new N("WEB_RESOURCE_REQUEST_IS_REDIRECT", "WEB_RESOURCE_REQUEST_IS_REDIRECT");
        WebViewFeatureInternal.WEB_RESOURCE_ERROR_GET_DESCRIPTION = new M("WEB_RESOURCE_ERROR_GET_DESCRIPTION", "WEB_RESOURCE_ERROR_GET_DESCRIPTION");
        WebViewFeatureInternal.WEB_RESOURCE_ERROR_GET_CODE = new M("WEB_RESOURCE_ERROR_GET_CODE", "WEB_RESOURCE_ERROR_GET_CODE");
        WebViewFeatureInternal.SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY = new O_MR1("SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY", "SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY");
        WebViewFeatureInternal.SAFE_BROWSING_RESPONSE_PROCEED = new O_MR1("SAFE_BROWSING_RESPONSE_PROCEED", "SAFE_BROWSING_RESPONSE_PROCEED");
        WebViewFeatureInternal.SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL = new O_MR1("SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL", "SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL");
        WebViewFeatureInternal.WEB_MESSAGE_PORT_POST_MESSAGE = new M("WEB_MESSAGE_PORT_POST_MESSAGE", "WEB_MESSAGE_PORT_POST_MESSAGE");
        WebViewFeatureInternal.WEB_MESSAGE_PORT_CLOSE = new M("WEB_MESSAGE_PORT_CLOSE", "WEB_MESSAGE_PORT_CLOSE");
        WebViewFeatureInternal.WEB_MESSAGE_ARRAY_BUFFER = new NoFramework("WEB_MESSAGE_ARRAY_BUFFER", "WEB_MESSAGE_ARRAY_BUFFER");
        WebViewFeatureInternal.WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK = new M("WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK", "WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK");
        WebViewFeatureInternal.CREATE_WEB_MESSAGE_CHANNEL = new M("CREATE_WEB_MESSAGE_CHANNEL", "CREATE_WEB_MESSAGE_CHANNEL");
        WebViewFeatureInternal.POST_WEB_MESSAGE = new M("POST_WEB_MESSAGE", "POST_WEB_MESSAGE");
        WebViewFeatureInternal.WEB_MESSAGE_CALLBACK_ON_MESSAGE = new M("WEB_MESSAGE_CALLBACK_ON_MESSAGE", "WEB_MESSAGE_CALLBACK_ON_MESSAGE");
        WebViewFeatureInternal.GET_WEB_VIEW_CLIENT = new O("GET_WEB_VIEW_CLIENT", "GET_WEB_VIEW_CLIENT");
        WebViewFeatureInternal.GET_WEB_CHROME_CLIENT = new O("GET_WEB_CHROME_CLIENT", "GET_WEB_CHROME_CLIENT");
        WebViewFeatureInternal.GET_WEB_VIEW_RENDERER = new Q("GET_WEB_VIEW_RENDERER", "GET_WEB_VIEW_RENDERER");
        WebViewFeatureInternal.WEB_VIEW_RENDERER_TERMINATE = new Q("WEB_VIEW_RENDERER_TERMINATE", "WEB_VIEW_RENDERER_TERMINATE");
        WebViewFeatureInternal.TRACING_CONTROLLER_BASIC_USAGE = new androidx.webkit.internal.ApiFeature.P("TRACING_CONTROLLER_BASIC_USAGE", "TRACING_CONTROLLER_BASIC_USAGE");
        WebViewFeatureInternal.STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX = new P("STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX", "STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX");
        WebViewFeatureInternal.STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH = new androidx.webkit.internal.StartupApiFeature.NoFramework("STARTUP_FEATURE_SET_DIRECTORY_BASE_PATHS", "STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH");
        WebViewFeatureInternal.STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES = new androidx.webkit.internal.StartupApiFeature.NoFramework("STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES", "STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES");
        WebViewFeatureInternal.WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE = new Q("WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE", "WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE");
        WebViewFeatureInternal.ALGORITHMIC_DARKENING = new T("ALGORITHMIC_DARKENING", "ALGORITHMIC_DARKENING") {
            private final Pattern mVersionPattern;

            {
                String s = "ALGORITHMIC_DARKENING";  // 捕获的参数
                String s1 = "ALGORITHMIC_DARKENING";  // 捕获的参数
                this.mVersionPattern = Pattern.compile("\\A\\d+");
            }

            @Override  // androidx.webkit.internal.ApiFeature
            public boolean isSupportedByWebView() {
                boolean z = super.isSupportedByWebView();
                if(z && Build.VERSION.SDK_INT < 29) {
                    PackageInfo packageInfo0 = WebViewCompat.getCurrentLoadedWebViewPackage();
                    if(packageInfo0 == null) {
                        return false;
                    }
                    Matcher matcher0 = this.mVersionPattern.matcher(packageInfo0.versionName);
                    return matcher0.find() && Integer.parseInt(packageInfo0.versionName.substring(matcher0.start(), matcher0.end())) >= 105;
                }
                return z;
            }
        };
        WebViewFeatureInternal.PROXY_OVERRIDE = new NoFramework("PROXY_OVERRIDE", "PROXY_OVERRIDE:3");
        WebViewFeatureInternal.MULTI_PROCESS = new NoFramework("MULTI_PROCESS", "MULTI_PROCESS_QUERY");
        WebViewFeatureInternal.FORCE_DARK = new Q("FORCE_DARK", "FORCE_DARK");
        WebViewFeatureInternal.FORCE_DARK_STRATEGY = new NoFramework("FORCE_DARK_STRATEGY", "FORCE_DARK_BEHAVIOR");
        WebViewFeatureInternal.WEB_MESSAGE_LISTENER = new NoFramework("WEB_MESSAGE_LISTENER", "WEB_MESSAGE_LISTENER");
        WebViewFeatureInternal.DOCUMENT_START_SCRIPT = new NoFramework("DOCUMENT_START_SCRIPT", "DOCUMENT_START_SCRIPT:1");
        WebViewFeatureInternal.PROXY_OVERRIDE_REVERSE_BYPASS = new NoFramework("PROXY_OVERRIDE_REVERSE_BYPASS", "PROXY_OVERRIDE_REVERSE_BYPASS");
        WebViewFeatureInternal.GET_VARIATIONS_HEADER = new NoFramework("GET_VARIATIONS_HEADER", "GET_VARIATIONS_HEADER");
        WebViewFeatureInternal.ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY = new NoFramework("ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY", "ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY");
        WebViewFeatureInternal.GET_COOKIE_INFO = new NoFramework("GET_COOKIE_INFO", "GET_COOKIE_INFO");
        WebViewFeatureInternal.REQUESTED_WITH_HEADER_ALLOW_LIST = new NoFramework("REQUESTED_WITH_HEADER_ALLOW_LIST", "REQUESTED_WITH_HEADER_ALLOW_LIST");
        WebViewFeatureInternal.USER_AGENT_METADATA = new NoFramework("USER_AGENT_METADATA", "USER_AGENT_METADATA");
        WebViewFeatureInternal.MULTI_PROFILE = new NoFramework("MULTI_PROFILE", "MULTI_PROFILE") {
            @Override  // androidx.webkit.internal.ApiFeature
            public boolean isSupportedByWebView() {
                if(!super.isSupportedByWebView()) {
                    return false;
                }
                return WebViewFeature.isFeatureSupported("MULTI_PROCESS") ? WebViewCompat.isMultiProcessEnabled() : false;
            }
        };
        WebViewFeatureInternal.ATTRIBUTION_REGISTRATION_BEHAVIOR = new NoFramework("ATTRIBUTION_REGISTRATION_BEHAVIOR", "ATTRIBUTION_BEHAVIOR");
        WebViewFeatureInternal.WEBVIEW_MEDIA_INTEGRITY_API_STATUS = new NoFramework("WEBVIEW_MEDIA_INTEGRITY_API_STATUS", "WEBVIEW_INTEGRITY_API_STATUS");
        WebViewFeatureInternal.MUTE_AUDIO = new NoFramework("MUTE_AUDIO", "MUTE_AUDIO");
        WebViewFeatureInternal.WEB_AUTHENTICATION = new NoFramework("WEB_AUTHENTICATION", "WEB_AUTHENTICATION");
        WebViewFeatureInternal.SPECULATIVE_LOADING = new NoFramework("SPECULATIVE_LOADING_STATUS", "SPECULATIVE_LOADING");
        WebViewFeatureInternal.BACK_FORWARD_CACHE = new NoFramework("BACK_FORWARD_CACHE", "BACK_FORWARD_CACHE");
        WebViewFeatureInternal.DELETE_BROWSING_DATA = new NoFramework("DELETE_BROWSING_DATA", "WEB_STORAGE_DELETE_BROWSING_DATA");
        WebViewFeatureInternal.PROFILE_URL_PREFETCH = new NoFramework("PREFETCH_URL_V3", "PREFETCH_URL_V3") {
            @Override  // androidx.webkit.internal.ApiFeature
            public boolean isSupportedByWebView() {
                return WebViewFeature.isFeatureSupported("MULTI_PROFILE") ? super.isSupportedByWebView() : false;
            }
        };
        WebViewFeatureInternal.ASYNC_WEBVIEW_STARTUP = new NoFramework("IMPLEMENTATION_ONLY_FEATURE", "ASYNC_WEBVIEW_STARTUP");
        WebViewFeatureInternal.DEFAULT_TRAFFICSTATS_TAGGING = new NoFramework("DEFAULT_TRAFFICSTATS_TAGGING", "DEFAULT_TRAFFICSTATS_TAGGING");
        WebViewFeatureInternal.PRERENDER_WITH_URL = new NoFramework("PRERENDER_URL_V2", "PRERENDER_URL_V2");
        WebViewFeatureInternal.SPECULATIVE_LOADING_CONFIG = new NoFramework("SPECULATIVE_LOADING_CONFIG", "SPECULATIVE_LOADING_CONFIG_V2");
    }

    public static UnsupportedOperationException getUnsupportedOperationException() {
        return new UnsupportedOperationException("This method is not supported by the current version of the framework and the current WebView APK");
    }

    public static boolean isStartupFeatureSupported(String s, Context context0) {
        return WebViewFeatureInternal.isStartupFeatureSupported(s, StartupApiFeature.values(), context0);
    }

    public static boolean isStartupFeatureSupported(String s, Collection collection0, Context context0) {
        HashSet hashSet0 = new HashSet();
        for(Object object0: collection0) {
            StartupApiFeature startupApiFeature0 = (StartupApiFeature)object0;
            if(startupApiFeature0.getPublicFeatureName().equals(s)) {
                hashSet0.add(startupApiFeature0);
            }
        }
        if(hashSet0.isEmpty()) {
            throw new RuntimeException("Unknown feature " + s);
        }
        for(Object object1: hashSet0) {
            if(((StartupApiFeature)object1).isSupported(context0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static boolean isSupported(String s) {
        return WebViewFeatureInternal.isSupported(s, ApiFeature.values());
    }

    public static boolean isSupported(String s, Collection collection0) {
        HashSet hashSet0 = new HashSet();
        for(Object object0: collection0) {
            ConditionallySupportedFeature conditionallySupportedFeature0 = (ConditionallySupportedFeature)object0;
            if(conditionallySupportedFeature0.getPublicFeatureName().equals(s)) {
                hashSet0.add(conditionallySupportedFeature0);
            }
        }
        if(hashSet0.isEmpty()) {
            throw new RuntimeException("Unknown feature " + s);
        }
        for(Object object1: hashSet0) {
            if(((ConditionallySupportedFeature)object1).isSupported()) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }
}

