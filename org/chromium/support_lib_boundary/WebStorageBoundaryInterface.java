package org.chromium.support_lib_boundary;

import java.util.concurrent.Executor;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebStorageBoundaryInterface {
    void deleteBrowsingData(Executor arg1, Runnable arg2);

    String deleteBrowsingDataForSite(String arg1, Executor arg2, Runnable arg3);
}

