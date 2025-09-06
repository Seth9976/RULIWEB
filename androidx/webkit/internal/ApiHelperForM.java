package androidx.webkit.internal;

import android.net.Uri;
import android.os.Handler;
import android.webkit.WebMessage;
import android.webkit.WebMessagePort.WebMessageCallback;
import android.webkit.WebMessagePort;
import android.webkit.WebResourceError;
import android.webkit.WebSettings;
import android.webkit.WebView.VisualStateCallback;
import android.webkit.WebView;
import androidx.print.PrintHelper..ExternalSyntheticApiModelOutline0;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat.WebMessageCallbackCompat;
import androidx.webkit.WebViewCompat.VisualStateCallback;

public class ApiHelperForM {
    public static void close(WebMessagePort webMessagePort0) {
        webMessagePort0.close();
    }

    public static WebMessage createWebMessage(WebMessageCompat webMessageCompat0) {
        return PrintHelper..ExternalSyntheticApiModelOutline0.m(webMessageCompat0.getData(), WebMessagePortImpl.compatToPorts(webMessageCompat0.getPorts()));
    }

    public static WebMessagePort[] createWebMessageChannel(WebView webView0) {
        return webView0.createWebMessageChannel();
    }

    public static WebMessageCompat createWebMessageCompat(WebMessage webMessage0) {
        return new WebMessageCompat(webMessage0.getData(), WebMessagePortImpl.portsToCompat(webMessage0.getPorts()));
    }

    public static CharSequence getDescription(WebResourceError webResourceError0) {
        return webResourceError0.getDescription();
    }

    public static int getErrorCode(WebResourceError webResourceError0) {
        return webResourceError0.getErrorCode();
    }

    public static boolean getOffscreenPreRaster(WebSettings webSettings0) {
        return webSettings0.getOffscreenPreRaster();
    }

    public static void postMessage(WebMessagePort webMessagePort0, WebMessage webMessage0) {
        webMessagePort0.postMessage(webMessage0);
    }

    public static void postVisualStateCallback(WebView webView0, long v, VisualStateCallback webViewCompat$VisualStateCallback0) {
        webView0.postVisualStateCallback(v, new WebView.VisualStateCallback() {
            @Override  // android.webkit.WebView$VisualStateCallback
            public void onComplete(long v) {
                webViewCompat$VisualStateCallback0.onComplete(v);
            }
        });
    }

    public static void postWebMessage(WebView webView0, WebMessage webMessage0, Uri uri0) {
        webView0.postWebMessage(webMessage0, uri0);
    }

    public static void setOffscreenPreRaster(WebSettings webSettings0, boolean z) {
        webSettings0.setOffscreenPreRaster(z);
    }

    public static void setWebMessageCallback(WebMessagePort webMessagePort0, WebMessageCallbackCompat webMessagePortCompat$WebMessageCallbackCompat0) {
        webMessagePort0.setWebMessageCallback(new WebMessagePort.WebMessageCallback() {
            @Override  // android.webkit.WebMessagePort$WebMessageCallback
            public void onMessage(WebMessagePort webMessagePort0, WebMessage webMessage0) {
                new WebMessagePortImpl(webMessagePort0);
                WebMessagePortImpl.frameworkMessageToCompat(webMessage0);
            }
        });
    }

    public static void setWebMessageCallback(WebMessagePort webMessagePort0, WebMessageCallbackCompat webMessagePortCompat$WebMessageCallbackCompat0, Handler handler0) {
        webMessagePort0.setWebMessageCallback(new WebMessagePort.WebMessageCallback() {
            @Override  // android.webkit.WebMessagePort$WebMessageCallback
            public void onMessage(WebMessagePort webMessagePort0, WebMessage webMessage0) {
                new WebMessagePortImpl(webMessagePort0);
                WebMessagePortImpl.frameworkMessageToCompat(webMessage0);
            }
        }, handler0);
    }
}

