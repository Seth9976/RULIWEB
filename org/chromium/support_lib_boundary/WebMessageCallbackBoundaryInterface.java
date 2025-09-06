package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebMessageCallbackBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    void onMessage(InvocationHandler arg1, InvocationHandler arg2);
}

