package androidx.webkit.internal;

import androidx.webkit.NoVarySearchHeader;
import java.util.List;
import org.chromium.support_lib_boundary.NoVarySearchDataBoundaryInterface;

public class NoVarySearchHeaderAdapter implements NoVarySearchDataBoundaryInterface {
    private final NoVarySearchHeader mImpl;

    public NoVarySearchHeaderAdapter(NoVarySearchHeader noVarySearchHeader0) {
        this.mImpl = noVarySearchHeader0;
    }

    @Override  // org.chromium.support_lib_boundary.NoVarySearchDataBoundaryInterface
    public List getConsideredQueryParameters() {
        return this.mImpl.consideredQueryParameters;
    }

    @Override  // org.chromium.support_lib_boundary.NoVarySearchDataBoundaryInterface
    public boolean getIgnoreDifferencesInParameters() {
        return this.mImpl.ignoreDifferencesInParameters;
    }

    @Override  // org.chromium.support_lib_boundary.NoVarySearchDataBoundaryInterface
    public List getIgnoredQueryParameters() {
        return this.mImpl.ignoredQueryParameters;
    }

    @Override  // org.chromium.support_lib_boundary.NoVarySearchDataBoundaryInterface
    public boolean getVaryOnKeyOrder() {
        return this.mImpl.varyOnKeyOrder;
    }
}

