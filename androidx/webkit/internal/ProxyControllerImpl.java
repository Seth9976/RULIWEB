package androidx.webkit.internal;

import androidx.webkit.ProxyConfig.ProxyRule;
import androidx.webkit.ProxyConfig;
import androidx.webkit.ProxyController;
import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface;

public class ProxyControllerImpl extends ProxyController {
    private ProxyControllerBoundaryInterface mBoundaryInterface;

    @Override  // androidx.webkit.ProxyController
    public void clearProxyOverride(Executor executor0, Runnable runnable0) {
        if(!WebViewFeatureInternal.PROXY_OVERRIDE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.getBoundaryInterface().clearProxyOverride(runnable0, executor0);
    }

    private ProxyControllerBoundaryInterface getBoundaryInterface() {
        if(this.mBoundaryInterface == null) {
            this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getProxyController();
        }
        return this.mBoundaryInterface;
    }

    public static String[][] proxyRulesToStringArray(List list0) {
        int[] arr_v = new int[2];
        arr_v[1] = 2;
        arr_v[0] = list0.size();
        String[][] arr2_s = (String[][])Array.newInstance(String.class, arr_v);
        for(int v = 0; v < list0.size(); ++v) {
            String[] arr_s = arr2_s[v];
            arr_s[0] = ((ProxyRule)list0.get(v)).getSchemeFilter();
            String[] arr_s1 = arr2_s[v];
            arr_s1[1] = ((ProxyRule)list0.get(v)).getUrl();
        }
        return arr2_s;
    }

    @Override  // androidx.webkit.ProxyController
    public void setProxyOverride(ProxyConfig proxyConfig0, Executor executor0, Runnable runnable0) {
        NoFramework apiFeature$NoFramework0 = WebViewFeatureInternal.PROXY_OVERRIDE;
        NoFramework apiFeature$NoFramework1 = WebViewFeatureInternal.PROXY_OVERRIDE_REVERSE_BYPASS;
        String[][] arr2_s = ProxyControllerImpl.proxyRulesToStringArray(proxyConfig0.getProxyRules());
        Object[] arr_object = proxyConfig0.getBypassRules().toArray(new String[0]);
        if(apiFeature$NoFramework0.isSupportedByWebView() && !proxyConfig0.isReverseBypassEnabled()) {
            this.getBoundaryInterface().setProxyOverride(arr2_s, ((String[])arr_object), runnable0, executor0);
            return;
        }
        if(!apiFeature$NoFramework0.isSupportedByWebView() || !apiFeature$NoFramework1.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.getBoundaryInterface().setProxyOverride(arr2_s, ((String[])arr_object), runnable0, executor0, proxyConfig0.isReverseBypassEnabled());
    }
}

