package androidx.webkit.internal;

import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat;
import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebMessageAdapter implements WebMessageBoundaryInterface {
    private final WebMessageCompat mWebMessageCompat;
    private static final String[] sFeatures;

    static {
        WebMessageAdapter.sFeatures = new String[]{"WEB_MESSAGE_ARRAY_BUFFER"};
    }

    public WebMessageAdapter(WebMessageCompat webMessageCompat0) {
        this.mWebMessageCompat = webMessageCompat0;
    }

    @Override  // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    @Deprecated
    public String getData() {
        return this.mWebMessageCompat.getData();
    }

    @Override  // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    public InvocationHandler getMessagePayload() {
        switch(this.mWebMessageCompat.getType()) {
            case 0: {
                return BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessagePayloadAdapter(this.mWebMessageCompat.getData()));
            }
            case 1: {
                return BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessagePayloadAdapter(((byte[])Objects.requireNonNull(this.mWebMessageCompat.getArrayBuffer()))));
            }
            default: {
                throw new IllegalStateException("Unknown web message payload type: " + this.mWebMessageCompat.getType());
            }
        }
    }

    @Override  // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    public InvocationHandler[] getPorts() {
        WebMessagePortCompat[] arr_webMessagePortCompat = this.mWebMessageCompat.getPorts();
        if(arr_webMessagePortCompat == null) {
            return null;
        }
        InvocationHandler[] arr_invocationHandler = new InvocationHandler[arr_webMessagePortCompat.length];
        for(int v = 0; v < arr_webMessagePortCompat.length; ++v) {
            arr_invocationHandler[v] = arr_webMessagePortCompat[v].getInvocationHandler();
        }
        return arr_invocationHandler;
    }

    @Override  // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return WebMessageAdapter.sFeatures;
    }

    // 去混淆评级： 低(20)
    public static boolean isMessagePayloadTypeSupportedByWebView(int v) {
        return v == 0 || v == 1 && WebViewFeatureInternal.WEB_MESSAGE_ARRAY_BUFFER.isSupportedByWebView();
    }

    private static WebMessagePortCompat[] toWebMessagePortCompats(InvocationHandler[] arr_invocationHandler) {
        WebMessagePortCompat[] arr_webMessagePortCompat = new WebMessagePortCompat[arr_invocationHandler.length];
        for(int v = 0; v < arr_invocationHandler.length; ++v) {
            arr_webMessagePortCompat[v] = new WebMessagePortImpl(arr_invocationHandler[v]);
        }
        return arr_webMessagePortCompat;
    }

    public static WebMessageCompat webMessageCompatFromBoundaryInterface(WebMessageBoundaryInterface webMessageBoundaryInterface0) {
        WebMessagePortCompat[] arr_webMessagePortCompat = WebMessageAdapter.toWebMessagePortCompats(webMessageBoundaryInterface0.getPorts());
        if(WebViewFeatureInternal.WEB_MESSAGE_ARRAY_BUFFER.isSupportedByWebView()) {
            InvocationHandler invocationHandler0 = webMessageBoundaryInterface0.getMessagePayload();
            WebMessagePayloadBoundaryInterface webMessagePayloadBoundaryInterface0 = (WebMessagePayloadBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebMessagePayloadBoundaryInterface.class, invocationHandler0);
            switch(webMessagePayloadBoundaryInterface0.getType()) {
                case 0: {
                    return new WebMessageCompat(webMessagePayloadBoundaryInterface0.getAsString(), arr_webMessagePortCompat);
                }
                case 1: {
                    return new WebMessageCompat(webMessagePayloadBoundaryInterface0.getAsArrayBuffer(), arr_webMessagePortCompat);
                }
                default: {
                    return null;
                }
            }
        }
        return new WebMessageCompat(webMessageBoundaryInterface0.getData(), arr_webMessagePortCompat);
    }
}

