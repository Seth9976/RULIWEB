package org.chromium.support_lib_boundary;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebViewClientBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    void onPageCommitVisible(WebView arg1, String arg2);

    void onReceivedError(WebView arg1, WebResourceRequest arg2, InvocationHandler arg3);

    void onReceivedHttpError(WebView arg1, WebResourceRequest arg2, WebResourceResponse arg3);

    void onSafeBrowsingHit(WebView arg1, WebResourceRequest arg2, int arg3, InvocationHandler arg4);

    boolean shouldOverrideUrlLoading(WebView arg1, WebResourceRequest arg2);
}

