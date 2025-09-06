package androidx.webkit.internal;

import java.util.List;
import org.chromium.support_lib_boundary.WebViewCookieManagerBoundaryInterface;

public class CookieManagerAdapter {
    private final WebViewCookieManagerBoundaryInterface mBoundaryInterface;

    public CookieManagerAdapter(WebViewCookieManagerBoundaryInterface webViewCookieManagerBoundaryInterface0) {
        this.mBoundaryInterface = webViewCookieManagerBoundaryInterface0;
    }

    public List getCookieInfo(String s) {
        return this.mBoundaryInterface.getCookieInfo(s);
    }
}

