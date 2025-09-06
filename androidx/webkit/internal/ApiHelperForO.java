package androidx.webkit.internal;

import android.content.pm.PackageInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ApiHelperForO {
    public static PackageInfo getCurrentWebViewPackage() {
        return WebView.getCurrentWebViewPackage();
    }

    public static boolean getSafeBrowsingEnabled(WebSettings webSettings0) {
        return webSettings0.getSafeBrowsingEnabled();
    }

    public static WebChromeClient getWebChromeClient(WebView webView0) {
        return webView0.getWebChromeClient();
    }

    public static WebViewClient getWebViewClient(WebView webView0) {
        return webView0.getWebViewClient();
    }

    public static void setSafeBrowsingEnabled(WebSettings webSettings0, boolean z) {
        webSettings0.setSafeBrowsingEnabled(z);
    }
}

