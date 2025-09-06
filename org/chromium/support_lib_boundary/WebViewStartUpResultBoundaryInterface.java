package org.chromium.support_lib_boundary;

import java.util.List;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebViewStartUpResultBoundaryInterface {
    List getBlockingStartUpLocations();

    Long getMaxTimePerTaskInUiThreadMillis();

    Long getTotalTimeInUiThreadMillis();
}

