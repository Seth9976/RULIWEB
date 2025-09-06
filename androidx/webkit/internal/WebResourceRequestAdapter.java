package androidx.webkit.internal;

import org.chromium.support_lib_boundary.WebResourceRequestBoundaryInterface;

public class WebResourceRequestAdapter {
    private final WebResourceRequestBoundaryInterface mBoundaryInterface;

    public WebResourceRequestAdapter(WebResourceRequestBoundaryInterface webResourceRequestBoundaryInterface0) {
        this.mBoundaryInterface = webResourceRequestBoundaryInterface0;
    }

    public boolean isRedirect() {
        return this.mBoundaryInterface.isRedirect();
    }
}

