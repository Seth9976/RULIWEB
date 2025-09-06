package androidx.webkit;

import android.webkit.CookieManager;
import androidx.webkit.internal.CookieManagerAdapter;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import java.util.List;

public class CookieManagerCompat {
    private static CookieManagerAdapter getAdapter(CookieManager cookieManager0) {
        return WebViewGlueCommunicator.getCompatConverter().convertCookieManager(cookieManager0);
    }

    public static List getCookieInfo(CookieManager cookieManager0, String s) {
        if(!WebViewFeatureInternal.GET_COOKIE_INFO.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return CookieManagerCompat.getAdapter(cookieManager0).getCookieInfo(s);
    }
}

