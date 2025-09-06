package org.chromium.support_lib_boundary;

import java.io.OutputStream;
import java.util.Collection;
import java.util.concurrent.Executor;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface TracingControllerBoundaryInterface {
    boolean isTracing();

    void start(int arg1, Collection arg2, int arg3) throws IllegalStateException, IllegalArgumentException;

    boolean stop(OutputStream arg1, Executor arg2);
}

