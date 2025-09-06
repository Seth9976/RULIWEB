package androidx.webkit.internal;

import android.os.Handler;
import android.webkit.WebMessage;
import android.webkit.WebMessagePort;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat.WebMessageCallbackCompat;
import androidx.webkit.WebMessagePortCompat;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.support_lib_boundary.WebMessagePortBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebMessagePortImpl extends WebMessagePortCompat {
    private WebMessagePortBoundaryInterface mBoundaryInterface;
    private WebMessagePort mFrameworksImpl;

    public WebMessagePortImpl(WebMessagePort webMessagePort0) {
        this.mFrameworksImpl = webMessagePort0;
    }

    public WebMessagePortImpl(InvocationHandler invocationHandler0) {
        this.mBoundaryInterface = (WebMessagePortBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebMessagePortBoundaryInterface.class, invocationHandler0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.WebMessagePortCompat
    public void close() {
        ApiHelperForM.close(this.getFrameworksImpl());
    }

    public static WebMessage compatToFrameworkMessage(WebMessageCompat webMessageCompat0) {
        return ApiHelperForM.createWebMessage(webMessageCompat0);
    }

    public static WebMessagePort[] compatToPorts(WebMessagePortCompat[] arr_webMessagePortCompat) {
        if(arr_webMessagePortCompat == null) {
            return null;
        }
        WebMessagePort[] arr_webMessagePort = new WebMessagePort[arr_webMessagePortCompat.length];
        for(int v = 0; v < arr_webMessagePortCompat.length; ++v) {
            arr_webMessagePort[v] = arr_webMessagePortCompat[v].getFrameworkPort();
        }
        return arr_webMessagePort;
    }

    public static WebMessageCompat frameworkMessageToCompat(WebMessage webMessage0) {
        return ApiHelperForM.createWebMessageCompat(webMessage0);
    }

    private WebMessagePortBoundaryInterface getBoundaryInterface() {
        if(this.mBoundaryInterface == null) {
            InvocationHandler invocationHandler0 = WebViewGlueCommunicator.getCompatConverter().convertWebMessagePort(this.mFrameworksImpl);
            this.mBoundaryInterface = (WebMessagePortBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebMessagePortBoundaryInterface.class, invocationHandler0);
        }
        return this.mBoundaryInterface;
    }

    @Override  // androidx.webkit.WebMessagePortCompat
    public WebMessagePort getFrameworkPort() {
        return this.getFrameworksImpl();
    }

    private WebMessagePort getFrameworksImpl() {
        if(this.mFrameworksImpl == null) {
            this.mFrameworksImpl = WebViewGlueCommunicator.getCompatConverter().convertWebMessagePort(Proxy.getInvocationHandler(this.mBoundaryInterface));
        }
        return this.mFrameworksImpl;
    }

    @Override  // androidx.webkit.WebMessagePortCompat
    public InvocationHandler getInvocationHandler() {
        return Proxy.getInvocationHandler(this.getBoundaryInterface());
    }

    public static WebMessagePortCompat[] portsToCompat(WebMessagePort[] arr_webMessagePort) {
        if(arr_webMessagePort == null) {
            return null;
        }
        WebMessagePortCompat[] arr_webMessagePortCompat = new WebMessagePortCompat[arr_webMessagePort.length];
        for(int v = 0; v < arr_webMessagePort.length; ++v) {
            arr_webMessagePortCompat[v] = new WebMessagePortImpl(arr_webMessagePort[v]);
        }
        return arr_webMessagePortCompat;
    }

    @Override  // androidx.webkit.WebMessagePortCompat
    public void postMessage(WebMessageCompat webMessageCompat0) {
        M apiFeature$M0 = WebViewFeatureInternal.WEB_MESSAGE_PORT_POST_MESSAGE;
        if(webMessageCompat0.getType() == 0) {
            ApiHelperForM.postMessage(this.getFrameworksImpl(), WebMessagePortImpl.compatToFrameworkMessage(webMessageCompat0));
            return;
        }
        if(!apiFeature$M0.isSupportedByWebView() || !WebMessageAdapter.isMessagePayloadTypeSupportedByWebView(webMessageCompat0.getType())) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.getBoundaryInterface().postMessage(BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessageAdapter(webMessageCompat0)));
    }

    @Override  // androidx.webkit.WebMessagePortCompat
    public void setWebMessageCallback(Handler handler0, WebMessageCallbackCompat webMessagePortCompat$WebMessageCallbackCompat0) {
        if(WebViewFeatureInternal.CREATE_WEB_MESSAGE_CHANNEL.isSupportedByWebView()) {
            this.getBoundaryInterface().setWebMessageCallback(BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessageCallbackAdapter(webMessagePortCompat$WebMessageCallbackCompat0)), handler0);
            return;
        }
        ApiHelperForM.setWebMessageCallback(this.getFrameworksImpl(), webMessagePortCompat$WebMessageCallbackCompat0, handler0);
    }

    @Override  // androidx.webkit.WebMessagePortCompat
    public void setWebMessageCallback(WebMessageCallbackCompat webMessagePortCompat$WebMessageCallbackCompat0) {
        if(WebViewFeatureInternal.WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK.isSupportedByWebView()) {
            this.getBoundaryInterface().setWebMessageCallback(BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessageCallbackAdapter(webMessagePortCompat$WebMessageCallbackCompat0)));
            return;
        }
        ApiHelperForM.setWebMessageCallback(this.getFrameworksImpl(), webMessagePortCompat$WebMessageCallbackCompat0);
    }
}

