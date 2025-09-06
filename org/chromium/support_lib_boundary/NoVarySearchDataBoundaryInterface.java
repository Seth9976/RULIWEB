package org.chromium.support_lib_boundary;

import java.util.List;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface NoVarySearchDataBoundaryInterface {
    List getConsideredQueryParameters();

    boolean getIgnoreDifferencesInParameters();

    List getIgnoredQueryParameters();

    boolean getVaryOnKeyOrder();
}

