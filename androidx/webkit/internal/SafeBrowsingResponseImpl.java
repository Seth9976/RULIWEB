package androidx.webkit.internal;

import android.webkit.SafeBrowsingResponse;
import androidx.webkit.SafeBrowsingResponseCompat;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.support_lib_boundary.SafeBrowsingResponseBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class SafeBrowsingResponseImpl extends SafeBrowsingResponseCompat {
    private SafeBrowsingResponseBoundaryInterface mBoundaryInterface;
    private SafeBrowsingResponse mFrameworksImpl;

    public SafeBrowsingResponseImpl(SafeBrowsingResponse safeBrowsingResponse0) {
        this.mFrameworksImpl = safeBrowsingResponse0;
    }

    public SafeBrowsingResponseImpl(InvocationHandler invocationHandler0) {
        this.mBoundaryInterface = (SafeBrowsingResponseBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(SafeBrowsingResponseBoundaryInterface.class, invocationHandler0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.SafeBrowsingResponseCompat
    public void backToSafety(boolean z) {
        ApiHelperForOMR1.backToSafety(this.getFrameworksImpl(), z);
    }

    private SafeBrowsingResponseBoundaryInterface getBoundaryInterface() {
        if(this.mBoundaryInterface == null) {
            InvocationHandler invocationHandler0 = WebViewGlueCommunicator.getCompatConverter().convertSafeBrowsingResponse(this.mFrameworksImpl);
            this.mBoundaryInterface = (SafeBrowsingResponseBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(SafeBrowsingResponseBoundaryInterface.class, invocationHandler0);
        }
        return this.mBoundaryInterface;
    }

    private SafeBrowsingResponse getFrameworksImpl() {
        if(this.mFrameworksImpl == null) {
            this.mFrameworksImpl = WebViewGlueCommunicator.getCompatConverter().convertSafeBrowsingResponse(Proxy.getInvocationHandler(this.mBoundaryInterface));
        }
        return this.mFrameworksImpl;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.SafeBrowsingResponseCompat
    public void proceed(boolean z) {
        ApiHelperForOMR1.proceed(this.getFrameworksImpl(), z);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.SafeBrowsingResponseCompat
    public void showInterstitial(boolean z) {
        ApiHelperForOMR1.showInterstitial(this.getFrameworksImpl(), z);
    }
}

