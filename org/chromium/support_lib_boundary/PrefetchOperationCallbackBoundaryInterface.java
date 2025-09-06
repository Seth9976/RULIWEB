package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface PrefetchOperationCallbackBoundaryInterface {
    void onFailure(InvocationHandler arg1);

    void onSuccess();
}

