package org.chromium.support_lib_boundary;

import java.util.concurrent.Executor;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface ProxyControllerBoundaryInterface {
    void clearProxyOverride(Runnable arg1, Executor arg2);

    void setProxyOverride(String[][] arg1, String[] arg2, Runnable arg3, Executor arg4);

    void setProxyOverride(String[][] arg1, String[] arg2, Runnable arg3, Executor arg4, boolean arg5);
}

