package org.chromium.support_lib_boundary;

import android.os.Handler;
import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebMessagePortBoundaryInterface {
    void close();

    void postMessage(InvocationHandler arg1);

    void setWebMessageCallback(InvocationHandler arg1);

    void setWebMessageCallback(InvocationHandler arg1, Handler arg2);
}

