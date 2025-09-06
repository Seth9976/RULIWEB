package org.chromium.support_lib_boundary;

import android.net.Uri;
import android.os.CancellationSignal;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebViewProviderBoundaryInterface {
    InvocationHandler addDocumentStartJavaScript(String arg1, String[] arg2);

    void addWebMessageListener(String arg1, String[] arg2, InvocationHandler arg3);

    InvocationHandler[] createWebMessageChannel();

    InvocationHandler getProfile();

    WebChromeClient getWebChromeClient();

    WebViewClient getWebViewClient();

    InvocationHandler getWebViewRenderer();

    InvocationHandler getWebViewRendererClient();

    void insertVisualStateCallback(long arg1, InvocationHandler arg2);

    boolean isAudioMuted();

    void postMessageToMainFrame(InvocationHandler arg1, Uri arg2);

    void prerenderUrl(String arg1, CancellationSignal arg2, Executor arg3, ValueCallback arg4, ValueCallback arg5);

    void prerenderUrl(String arg1, CancellationSignal arg2, Executor arg3, InvocationHandler arg4, ValueCallback arg5, ValueCallback arg6);

    void removeWebMessageListener(String arg1);

    void setAudioMuted(boolean arg1);

    void setProfile(String arg1);

    void setWebViewRendererClient(InvocationHandler arg1);
}

