package androidx.webkit.internal;

import android.os.Looper;
import android.webkit.TracingController;
import android.webkit.WebView;
import androidx.print.PrintHelper..ExternalSyntheticApiModelOutline0;
import androidx.webkit.TracingConfig;
import java.io.OutputStream;
import java.util.concurrent.Executor;

public class ApiHelperForP {
    public static TracingController getTracingControllerInstance() {
        return TracingController.getInstance();
    }

    public static ClassLoader getWebViewClassLoader() {
        return WebView.getWebViewClassLoader();
    }

    public static Looper getWebViewLooper(WebView webView0) {
        return webView0.getWebViewLooper();
    }

    public static boolean isTracing(TracingController tracingController0) {
        return tracingController0.isTracing();
    }

    public static void setDataDirectorySuffix(String s) {
        WebView.setDataDirectorySuffix(s);
    }

    public static void start(TracingController tracingController0, TracingConfig tracingConfig0) {
        tracingController0.start(PrintHelper..ExternalSyntheticApiModelOutline0.m().addCategories(new int[]{tracingConfig0.getPredefinedCategories()}).addCategories(tracingConfig0.getCustomIncludedCategories()).setTracingMode(tracingConfig0.getTracingMode()).build());
    }

    public static boolean stop(TracingController tracingController0, OutputStream outputStream0, Executor executor0) {
        return tracingController0.stop(outputStream0, executor0);
    }
}

