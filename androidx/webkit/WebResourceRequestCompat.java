package androidx.webkit;

import android.webkit.WebResourceRequest;
import androidx.webkit.internal.ApiHelperForN;
import androidx.webkit.internal.WebResourceRequestAdapter;
import androidx.webkit.internal.WebViewGlueCommunicator;

public class WebResourceRequestCompat {
    private static WebResourceRequestAdapter getAdapter(WebResourceRequest webResourceRequest0) {
        return WebViewGlueCommunicator.getCompatConverter().convertWebResourceRequest(webResourceRequest0);
    }

    // 去混淆评级： 低(40)
    public static boolean isRedirect(WebResourceRequest webResourceRequest0) {
        return ApiHelperForN.isRedirect(webResourceRequest0);
    }
}

