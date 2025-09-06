package androidx.webkit;

import java.util.List;

public interface WebViewStartUpResult {
    List getBlockingStartUpLocations();

    Long getMaxTimePerTaskInUiThreadMillis();

    Long getTotalTimeInUiThreadMillis();
}

