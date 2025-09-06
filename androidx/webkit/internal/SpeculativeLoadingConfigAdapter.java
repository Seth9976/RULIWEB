package androidx.webkit.internal;

import androidx.webkit.SpeculativeLoadingConfig;
import org.chromium.support_lib_boundary.SpeculativeLoadingConfigBoundaryInterface;

public class SpeculativeLoadingConfigAdapter implements SpeculativeLoadingConfigBoundaryInterface {
    private final SpeculativeLoadingConfig mSpeculativeLoadingConfig;

    public SpeculativeLoadingConfigAdapter(SpeculativeLoadingConfig speculativeLoadingConfig0) {
        this.mSpeculativeLoadingConfig = speculativeLoadingConfig0;
    }

    @Override  // org.chromium.support_lib_boundary.SpeculativeLoadingConfigBoundaryInterface
    public int getMaxPrefetches() {
        return this.mSpeculativeLoadingConfig.getMaxPrefetches();
    }

    @Override  // org.chromium.support_lib_boundary.SpeculativeLoadingConfigBoundaryInterface
    public int getMaxPrerenders() {
        return 0;
    }

    @Override  // org.chromium.support_lib_boundary.SpeculativeLoadingConfigBoundaryInterface
    public int getPrefetchTTLSeconds() {
        return this.mSpeculativeLoadingConfig.getPrefetchTtlSeconds();
    }
}

