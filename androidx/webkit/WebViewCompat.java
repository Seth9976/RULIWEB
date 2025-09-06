package androidx.webkit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.internal.ApiFeature.M;
import androidx.webkit.internal.ApiHelperForM;
import androidx.webkit.internal.ApiHelperForO;
import androidx.webkit.internal.ApiHelperForOMR1;
import androidx.webkit.internal.ApiHelperForP;
import androidx.webkit.internal.ApiHelperForQ;
import androidx.webkit.internal.WebMessageAdapter;
import androidx.webkit.internal.WebMessagePortImpl;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import androidx.webkit.internal.WebViewProviderAdapter;
import androidx.webkit.internal.WebViewProviderFactory;
import androidx.webkit.internal.WebViewRenderProcessClientFrameworkAdapter;
import androidx.webkit.internal.WebViewRenderProcessImpl;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

public class WebViewCompat {
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    public @interface ExperimentalAsyncStartUp {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    public @interface ExperimentalUrlPrerender {
    }

    static class NullReturningWebViewStartUpResult implements WebViewStartUpResult {
        private NullReturningWebViewStartUpResult() {
        }

        NullReturningWebViewStartUpResult(androidx.webkit.WebViewCompat.1 webViewCompat$10) {
        }

        @Override  // androidx.webkit.WebViewStartUpResult
        public List getBlockingStartUpLocations() {
            return null;
        }

        @Override  // androidx.webkit.WebViewStartUpResult
        public Long getMaxTimePerTaskInUiThreadMillis() {
            return null;
        }

        @Override  // androidx.webkit.WebViewStartUpResult
        public Long getTotalTimeInUiThreadMillis() {
            return null;
        }
    }

    public interface VisualStateCallback {
        void onComplete(long arg1);
    }

    public interface WebMessageListener {
        void onPostMessage(WebView arg1, WebMessageCompat arg2, Uri arg3, boolean arg4, JavaScriptReplyProxy arg5);
    }

    public interface WebViewStartUpCallback {
        void onSuccess(WebViewStartUpResult arg1);
    }

    private static final Uri EMPTY_URI;
    private static final Uri WILDCARD_URI;

    static {
        WebViewCompat.WILDCARD_URI = Uri.parse("*");
        WebViewCompat.EMPTY_URI = Uri.parse("");
    }

    public static ScriptHandler addDocumentStartJavaScript(WebView webView0, String s, Set set0) {
        if(!WebViewFeatureInternal.DOCUMENT_START_SCRIPT.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebViewCompat.getProvider(webView0).addDocumentStartJavaScript(s, ((String[])set0.toArray(new String[0])));
    }

    public static void addWebMessageListener(WebView webView0, String s, Set set0, WebMessageListener webViewCompat$WebMessageListener0) {
        if(!WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.getProvider(webView0).addWebMessageListener(s, ((String[])set0.toArray(new String[0])), webViewCompat$WebMessageListener0);
    }

    private static void checkThread(WebView webView0) {
        if(Build.VERSION.SDK_INT >= 28) {
            Looper looper0 = ApiHelperForP.getWebViewLooper(webView0);
            if(looper0 != Looper.myLooper()) {
                throw new RuntimeException("A WebView method was called on thread \'jeb-dexdec-sb-st-13293\'. All WebView methods must be called on the same thread. (Expected Looper " + looper0 + " called on " + Looper.myLooper() + ", FYI main Looper is " + Looper.getMainLooper() + ")");
            }
            return;
        }
        try {
            Method method0 = WebView.class.getDeclaredMethod("checkThread", null);
            method0.setAccessible(true);
            method0.invoke(webView0, null);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException exception0) {
            throw new RuntimeException(exception0);
        }
    }

    private static WebViewProviderBoundaryInterface createProvider(WebView webView0) {
        return WebViewCompat.getFactory().createWebView(webView0);
    }

    // 去混淆评级： 低(40)
    public static WebMessagePortCompat[] createWebMessageChannel(WebView webView0) {
        return WebMessagePortImpl.portsToCompat(ApiHelperForM.createWebMessageChannel(webView0));
    }

    public static PackageInfo getCurrentLoadedWebViewPackage() {
        if(Build.VERSION.SDK_INT >= 26) {
            return ApiHelperForO.getCurrentWebViewPackage();
        }
        try {
            return WebViewCompat.getLoadedWebViewPackageInfo();
        }
        catch(ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException unused_ex) {
            return null;
        }
    }

    public static PackageInfo getCurrentWebViewPackage(Context context0) {
        PackageInfo packageInfo0 = WebViewCompat.getCurrentLoadedWebViewPackage();
        return packageInfo0 == null ? WebViewCompat.getNotYetLoadedWebViewPackageInfo(context0) : packageInfo0;
    }

    private static WebViewProviderFactory getFactory() {
        return WebViewGlueCommunicator.getFactory();
    }

    private static PackageInfo getLoadedWebViewPackageInfo() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (PackageInfo)Class.forName("android.webkit.WebViewFactory").getMethod("getLoadedPackageInfo", null).invoke(null, null);
    }

    private static PackageInfo getNotYetLoadedWebViewPackageInfo(Context context0) {
        String s;
        try {
            s = Build.VERSION.SDK_INT > 23 ? ((String)Class.forName("android.webkit.WebViewUpdateService").getMethod("getCurrentWebViewPackageName", null).invoke(null, null)) : ((String)Class.forName("android.webkit.WebViewFactory").getMethod("getWebViewPackageName", null).invoke(null, null));
            if(s == null) {
                return null;
            }
        }
        catch(ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException unused_ex) {
            return null;
        }
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            return packageManager0.getPackageInfo(s, 0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    public static Profile getProfile(WebView webView0) {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebViewCompat.getProvider(webView0).getProfile();
    }

    private static WebViewProviderAdapter getProvider(WebView webView0) {
        return new WebViewProviderAdapter(WebViewCompat.createProvider(webView0));
    }

    // 去混淆评级： 低(40)
    public static Uri getSafeBrowsingPrivacyPolicyUrl() {
        return ApiHelperForOMR1.getSafeBrowsingPrivacyPolicyUrl();
    }

    public static String getVariationsHeader() {
        if(!WebViewFeatureInternal.GET_VARIATIONS_HEADER.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebViewCompat.getFactory().getStatics().getVariationsHeader();
    }

    // 去混淆评级： 低(40)
    public static WebChromeClient getWebChromeClient(WebView webView0) {
        return ApiHelperForO.getWebChromeClient(webView0);
    }

    // 去混淆评级： 低(40)
    public static WebViewClient getWebViewClient(WebView webView0) {
        return ApiHelperForO.getWebViewClient(webView0);
    }

    public static WebViewRenderProcess getWebViewRenderProcess(WebView webView0) {
        android.webkit.WebViewRenderProcess webViewRenderProcess0 = ApiHelperForQ.getWebViewRenderProcess(webView0);
        return webViewRenderProcess0 != null ? WebViewRenderProcessImpl.forFrameworkObject(webViewRenderProcess0) : null;
    }

    // 去混淆评级： 低(25)
    public static WebViewRenderProcessClient getWebViewRenderProcessClient(WebView webView0) {
        android.webkit.WebViewRenderProcessClient webViewRenderProcessClient0 = ApiHelperForQ.getWebViewRenderProcessClient(webView0);
        return webViewRenderProcessClient0 == null || !(webViewRenderProcessClient0 instanceof WebViewRenderProcessClientFrameworkAdapter) ? null : ((WebViewRenderProcessClientFrameworkAdapter)webViewRenderProcessClient0).getFrameworkRenderProcessClient();
    }

    public static boolean isAudioMuted(WebView webView0) {
        if(!WebViewFeatureInternal.MUTE_AUDIO.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebViewCompat.getProvider(webView0).isAudioMuted();
    }

    public static boolean isMultiProcessEnabled() {
        if(!WebViewFeatureInternal.MULTI_PROCESS.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebViewCompat.getFactory().getStatics().isMultiProcessEnabled();
    }

    // 检测为 Lambda 实现
    static void lambda$startUpWebView$0(WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0, WebViewStartUpResult webViewStartUpResult0) [...]

    // 检测为 Lambda 实现
    static void lambda$startUpWebView$1(WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0, WebViewStartUpResult webViewStartUpResult0) [...]

    // 检测为 Lambda 实现
    static void lambda$startUpWebView$2(WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0) [...]

    // 检测为 Lambda 实现
    static void lambda$startUpWebView$3(WebViewStartUpConfig webViewStartUpConfig0, WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0) [...]

    // 去混淆评级： 低(20)
    public static void postVisualStateCallback(WebView webView0, long v, VisualStateCallback webViewCompat$VisualStateCallback0) {
        ApiHelperForM.postVisualStateCallback(webView0, v, webViewCompat$VisualStateCallback0);
    }

    public static void postWebMessage(WebView webView0, WebMessageCompat webMessageCompat0, Uri uri0) {
        if(WebViewCompat.WILDCARD_URI.equals(uri0)) {
            uri0 = WebViewCompat.EMPTY_URI;
        }
        M apiFeature$M0 = WebViewFeatureInternal.POST_WEB_MESSAGE;
        if(webMessageCompat0.getType() == 0) {
            ApiHelperForM.postWebMessage(webView0, WebMessagePortImpl.compatToFrameworkMessage(webMessageCompat0), uri0);
            return;
        }
        if(!apiFeature$M0.isSupportedByWebView() || !WebMessageAdapter.isMessagePayloadTypeSupportedByWebView(webMessageCompat0.getType())) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.checkThread(webView0);
        WebViewCompat.getProvider(webView0).postWebMessage(webMessageCompat0, uri0);
    }

    public static void prerenderUrlAsync(WebView webView0, String s, CancellationSignal cancellationSignal0, Executor executor0, PrerenderOperationCallback prerenderOperationCallback0) {
        if(!WebViewFeatureInternal.PRERENDER_WITH_URL.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.getProvider(webView0).prerenderUrlAsync(s, cancellationSignal0, executor0, prerenderOperationCallback0);
    }

    public static void prerenderUrlAsync(WebView webView0, String s, CancellationSignal cancellationSignal0, Executor executor0, SpeculativeLoadingParameters speculativeLoadingParameters0, PrerenderOperationCallback prerenderOperationCallback0) {
        if(!WebViewFeatureInternal.PRERENDER_WITH_URL.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.getProvider(webView0).prerenderUrlAsync(s, cancellationSignal0, executor0, speculativeLoadingParameters0, prerenderOperationCallback0);
    }

    public static void removeWebMessageListener(WebView webView0, String s) {
        if(!WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.getProvider(webView0).removeWebMessageListener(s);
    }

    public static void setAudioMuted(WebView webView0, boolean z) {
        if(!WebViewFeatureInternal.MUTE_AUDIO.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.getProvider(webView0).setAudioMuted(z);
    }

    public static void setDefaultTrafficStatsTag(int v) {
        if(!WebViewFeatureInternal.DEFAULT_TRAFFICSTATS_TAGGING.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.getFactory().getStatics().setDefaultTrafficStatsTag(v);
    }

    public static void setProfile(WebView webView0, String s) {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebViewCompat.getProvider(webView0).setProfileWithName(s);
    }

    public static void setSafeBrowsingAllowlist(Set set0, ValueCallback valueCallback0) {
        if(WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_PREFERRED.isSupportedByWebView()) {
            WebViewCompat.getFactory().getStatics().setSafeBrowsingAllowlist(set0, valueCallback0);
            return;
        }
        ApiHelperForOMR1.setSafeBrowsingWhitelist(new ArrayList(set0), valueCallback0);
    }

    @Deprecated
    public static void setSafeBrowsingWhitelist(List list0, ValueCallback valueCallback0) {
        WebViewCompat.setSafeBrowsingAllowlist(new HashSet(list0), valueCallback0);
    }

    // 去混淆评级： 低(20)
    public static void setWebViewRenderProcessClient(WebView webView0, WebViewRenderProcessClient webViewRenderProcessClient0) {
        ApiHelperForQ.setWebViewRenderProcessClient(webView0, webViewRenderProcessClient0);
    }

    // 去混淆评级： 低(20)
    public static void setWebViewRenderProcessClient(WebView webView0, Executor executor0, WebViewRenderProcessClient webViewRenderProcessClient0) {
        ApiHelperForQ.setWebViewRenderProcessClient(webView0, executor0, webViewRenderProcessClient0);
    }

    // 去混淆评级： 低(20)
    @Deprecated
    public static void startSafeBrowsing(Context context0, ValueCallback valueCallback0) {
        ApiHelperForOMR1.startSafeBrowsing(context0, valueCallback0);
    }

    public static void startUpWebView(WebViewStartUpConfig webViewStartUpConfig0, WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0) {
        webViewStartUpConfig0.getBackgroundExecutor().execute(() -> {
            WebViewGlueCommunicator.getWebViewClassLoader();
            if(WebViewFeatureInternal.ASYNC_WEBVIEW_STARTUP.isSupportedByWebView()) {
                WebViewCompat.getFactory().startUpWebView(webViewStartUpConfig0, (WebViewStartUpResult webViewStartUpResult0) -> new Handler(Looper.getMainLooper()).post(() -> webViewCompat$WebViewStartUpCallback0.onSuccess(webViewStartUpResult0)));
                return;
            }
            if(webViewStartUpConfig0.shouldRunUiThreadStartUpTasks()) {
                WebSettings.getDefaultUserAgent(null);
            }
            new Handler(Looper.getMainLooper()).post(() -> webViewCompat$WebViewStartUpCallback0.onSuccess(new NullReturningWebViewStartUpResult(null)));
        });
    }

    class androidx.webkit.WebViewCompat.1 {
    }

}

