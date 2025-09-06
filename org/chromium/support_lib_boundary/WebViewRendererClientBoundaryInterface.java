package org.chromium.support_lib_boundary;

import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebViewRendererClientBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    void onRendererResponsive(WebView arg1, InvocationHandler arg2);

    void onRendererUnresponsive(WebView arg1, InvocationHandler arg2);
}

