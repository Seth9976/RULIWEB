package androidx.webkit.internal;

import android.webkit.ServiceWorkerWebSettings;
import androidx.webkit.ServiceWorkerWebSettingsCompat;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Set;
import org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class ServiceWorkerWebSettingsImpl extends ServiceWorkerWebSettingsCompat {
    private ServiceWorkerWebSettingsBoundaryInterface mBoundaryInterface;
    private ServiceWorkerWebSettings mFrameworksImpl;

    public ServiceWorkerWebSettingsImpl(ServiceWorkerWebSettings serviceWorkerWebSettings0) {
        this.mFrameworksImpl = serviceWorkerWebSettings0;
    }

    public ServiceWorkerWebSettingsImpl(InvocationHandler invocationHandler0) {
        this.mBoundaryInterface = (ServiceWorkerWebSettingsBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ServiceWorkerWebSettingsBoundaryInterface.class, invocationHandler0);
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public boolean getAllowContentAccess() {
        return ApiHelperForN.getAllowContentAccess(this.getFrameworksImpl());
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public boolean getAllowFileAccess() {
        return ApiHelperForN.getAllowFileAccess(this.getFrameworksImpl());
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public boolean getBlockNetworkLoads() {
        return ApiHelperForN.getBlockNetworkLoads(this.getFrameworksImpl());
    }

    private ServiceWorkerWebSettingsBoundaryInterface getBoundaryInterface() {
        if(this.mBoundaryInterface == null) {
            InvocationHandler invocationHandler0 = WebViewGlueCommunicator.getCompatConverter().convertServiceWorkerSettings(this.mFrameworksImpl);
            this.mBoundaryInterface = (ServiceWorkerWebSettingsBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ServiceWorkerWebSettingsBoundaryInterface.class, invocationHandler0);
        }
        return this.mBoundaryInterface;
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public int getCacheMode() {
        return ApiHelperForN.getCacheMode(this.getFrameworksImpl());
    }

    private ServiceWorkerWebSettings getFrameworksImpl() {
        if(this.mFrameworksImpl == null) {
            this.mFrameworksImpl = WebViewGlueCommunicator.getCompatConverter().convertServiceWorkerSettings(Proxy.getInvocationHandler(this.mBoundaryInterface));
        }
        return this.mFrameworksImpl;
    }

    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public Set getRequestedWithHeaderOriginAllowList() {
        if(!WebViewFeatureInternal.REQUESTED_WITH_HEADER_ALLOW_LIST.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.getBoundaryInterface().getRequestedWithHeaderOriginAllowList();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public void setAllowContentAccess(boolean z) {
        ApiHelperForN.setAllowContentAccess(this.getFrameworksImpl(), z);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public void setAllowFileAccess(boolean z) {
        ApiHelperForN.setAllowFileAccess(this.getFrameworksImpl(), z);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public void setBlockNetworkLoads(boolean z) {
        ApiHelperForN.setBlockNetworkLoads(this.getFrameworksImpl(), z);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public void setCacheMode(int v) {
        ApiHelperForN.setCacheMode(this.getFrameworksImpl(), v);
    }

    @Override  // androidx.webkit.ServiceWorkerWebSettingsCompat
    public void setRequestedWithHeaderOriginAllowList(Set set0) {
        if(!WebViewFeatureInternal.REQUESTED_WITH_HEADER_ALLOW_LIST.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.getBoundaryInterface().setRequestedWithHeaderOriginAllowList(set0);
    }
}

