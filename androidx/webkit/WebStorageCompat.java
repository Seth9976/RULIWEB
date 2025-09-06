package androidx.webkit;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebStorage;
import androidx.webkit.internal.WebStorageAdapter;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import java.util.concurrent.Executor;

public final class WebStorageCompat {
    public static void deleteBrowsingData(WebStorage webStorage0, Runnable runnable0) {
        WebStorageCompat.deleteBrowsingData(webStorage0, (Runnable runnable0) -> new Handler(Looper.getMainLooper()).post(runnable0), runnable0);
    }

    public static void deleteBrowsingData(WebStorage webStorage0, Executor executor0, Runnable runnable0) {
        if(!WebViewFeatureInternal.DELETE_BROWSING_DATA.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        WebStorageCompat.getAdapter(webStorage0).deleteBrowsingData(executor0, runnable0);
    }

    public static String deleteBrowsingDataForSite(WebStorage webStorage0, String s, Runnable runnable0) {
        return WebStorageCompat.deleteBrowsingDataForSite(webStorage0, s, (Runnable runnable0) -> new Handler(Looper.getMainLooper()).post(runnable0), runnable0);
    }

    public static String deleteBrowsingDataForSite(WebStorage webStorage0, String s, Executor executor0, Runnable runnable0) {
        if(!WebViewFeatureInternal.DELETE_BROWSING_DATA.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return WebStorageCompat.getAdapter(webStorage0).deleteBrowsingDataForSite(s, executor0, runnable0);
    }

    private static WebStorageAdapter getAdapter(WebStorage webStorage0) {
        return WebViewGlueCommunicator.getCompatConverter().convertWebStorage(webStorage0);
    }

    // 检测为 Lambda 实现
    static void lambda$deleteBrowsingData$0(Runnable runnable0) [...]

    // 检测为 Lambda 实现
    static void lambda$deleteBrowsingDataForSite$1(Runnable runnable0) [...]
}

