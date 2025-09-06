package org.chromium.support_lib_boundary;

import android.net.Uri;
import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebMessageListenerBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    void onPostMessage(WebView arg1, InvocationHandler arg2, Uri arg3, boolean arg4, InvocationHandler arg5);
}

