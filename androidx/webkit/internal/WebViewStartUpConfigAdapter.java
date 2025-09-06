package androidx.webkit.internal;

import androidx.webkit.WebViewStartUpConfig;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewStartUpConfigBoundaryInterface;

public class WebViewStartUpConfigAdapter implements WebViewStartUpConfigBoundaryInterface {
    private final WebViewStartUpConfig mWebViewStartUpConfig;

    public WebViewStartUpConfigAdapter(WebViewStartUpConfig webViewStartUpConfig0) {
        this.mWebViewStartUpConfig = webViewStartUpConfig0;
    }

    @Override  // org.chromium.support_lib_boundary.WebViewStartUpConfigBoundaryInterface
    public Executor getBackgroundExecutor() {
        return this.mWebViewStartUpConfig.getBackgroundExecutor();
    }

    @Override  // org.chromium.support_lib_boundary.WebViewStartUpConfigBoundaryInterface
    public boolean shouldRunUiThreadStartUpTasks() {
        return this.mWebViewStartUpConfig.shouldRunUiThreadStartUpTasks();
    }
}

