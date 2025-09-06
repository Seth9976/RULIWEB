package androidx.webkit.internal;

import android.webkit.WebResourceError;
import androidx.webkit.WebResourceErrorCompat;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.support_lib_boundary.WebResourceErrorBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebResourceErrorImpl extends WebResourceErrorCompat {
    private WebResourceErrorBoundaryInterface mBoundaryInterface;
    private WebResourceError mFrameworksImpl;

    public WebResourceErrorImpl(WebResourceError webResourceError0) {
        this.mFrameworksImpl = webResourceError0;
    }

    public WebResourceErrorImpl(InvocationHandler invocationHandler0) {
        this.mBoundaryInterface = (WebResourceErrorBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebResourceErrorBoundaryInterface.class, invocationHandler0);
    }

    private WebResourceErrorBoundaryInterface getBoundaryInterface() {
        if(this.mBoundaryInterface == null) {
            InvocationHandler invocationHandler0 = WebViewGlueCommunicator.getCompatConverter().convertWebResourceError(this.mFrameworksImpl);
            this.mBoundaryInterface = (WebResourceErrorBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebResourceErrorBoundaryInterface.class, invocationHandler0);
        }
        return this.mBoundaryInterface;
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.WebResourceErrorCompat
    public CharSequence getDescription() {
        return ApiHelperForM.getDescription(this.getFrameworksImpl());
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.WebResourceErrorCompat
    public int getErrorCode() {
        return ApiHelperForM.getErrorCode(this.getFrameworksImpl());
    }

    private WebResourceError getFrameworksImpl() {
        if(this.mFrameworksImpl == null) {
            this.mFrameworksImpl = WebViewGlueCommunicator.getCompatConverter().convertWebResourceError(Proxy.getInvocationHandler(this.mBoundaryInterface));
        }
        return this.mFrameworksImpl;
    }
}

