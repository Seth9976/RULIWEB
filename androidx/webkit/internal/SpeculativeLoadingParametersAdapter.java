package androidx.webkit.internal;

import androidx.webkit.NoVarySearchHeader;
import androidx.webkit.SpeculativeLoadingParameters;
import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;
import org.chromium.support_lib_boundary.SpeculativeLoadingParametersBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class SpeculativeLoadingParametersAdapter implements SpeculativeLoadingParametersBoundaryInterface {
    private final SpeculativeLoadingParameters mSpeculativeLoadingParameters;

    public SpeculativeLoadingParametersAdapter(SpeculativeLoadingParameters speculativeLoadingParameters0) {
        this.mSpeculativeLoadingParameters = speculativeLoadingParameters0;
    }

    @Override  // org.chromium.support_lib_boundary.SpeculativeLoadingParametersBoundaryInterface
    public Map getAdditionalHeaders() {
        SpeculativeLoadingParameters speculativeLoadingParameters0 = this.mSpeculativeLoadingParameters;
        return speculativeLoadingParameters0 == null ? new HashMap() : speculativeLoadingParameters0.getAdditionalHeaders();
    }

    @Override  // org.chromium.support_lib_boundary.SpeculativeLoadingParametersBoundaryInterface
    public InvocationHandler getNoVarySearchData() {
        SpeculativeLoadingParameters speculativeLoadingParameters0 = this.mSpeculativeLoadingParameters;
        if(speculativeLoadingParameters0 == null) {
            return null;
        }
        NoVarySearchHeader noVarySearchHeader0 = speculativeLoadingParameters0.getExpectedNoVarySearchData();
        return noVarySearchHeader0 == null ? null : BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new NoVarySearchHeaderAdapter(noVarySearchHeader0));
    }

    @Override  // org.chromium.support_lib_boundary.SpeculativeLoadingParametersBoundaryInterface
    public boolean isJavaScriptEnabled() {
        return this.mSpeculativeLoadingParameters == null ? false : this.mSpeculativeLoadingParameters.isJavaScriptEnabled();
    }
}

