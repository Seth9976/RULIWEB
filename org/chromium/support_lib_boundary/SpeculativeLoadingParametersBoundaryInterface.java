package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;
import java.util.Map;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface SpeculativeLoadingParametersBoundaryInterface {
    Map getAdditionalHeaders();

    InvocationHandler getNoVarySearchData();

    boolean isJavaScriptEnabled();
}

