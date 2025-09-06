package androidx.webkit;

import androidx.webkit.internal.ProxyControllerImpl;
import java.util.concurrent.Executor;

public abstract class ProxyController {
    static class LAZY_HOLDER {
        static final ProxyController INSTANCE;

        static {
            LAZY_HOLDER.INSTANCE = new ProxyControllerImpl();
        }
    }

    public abstract void clearProxyOverride(Executor arg1, Runnable arg2);

    public static ProxyController getInstance() {
        if(!WebViewFeature.isFeatureSupported("PROXY_OVERRIDE")) {
            throw new UnsupportedOperationException("Proxy override not supported");
        }
        return LAZY_HOLDER.INSTANCE;
    }

    public abstract void setProxyOverride(ProxyConfig arg1, Executor arg2, Runnable arg3);
}

