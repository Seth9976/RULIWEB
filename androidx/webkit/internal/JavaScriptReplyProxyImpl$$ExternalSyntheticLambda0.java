package androidx.webkit.internal;

import java.util.concurrent.Callable;
import org.chromium.support_lib_boundary.JsReplyProxyBoundaryInterface;

public final class JavaScriptReplyProxyImpl..ExternalSyntheticLambda0 implements Callable {
    public final JsReplyProxyBoundaryInterface f$0;

    public JavaScriptReplyProxyImpl..ExternalSyntheticLambda0(JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface0) {
        this.f$0 = jsReplyProxyBoundaryInterface0;
    }

    @Override
    public final Object call() {
        return JavaScriptReplyProxyImpl.lambda$forInvocationHandler$0(this.f$0);
    }
}

