package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface JsReplyProxyBoundaryInterface extends IsomorphicObjectBoundaryInterface {
    void postMessage(String arg1);

    void postMessageWithPayload(InvocationHandler arg1);
}

