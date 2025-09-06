package androidx.webkit;

import java.util.HashMap;
import java.util.Map;

public final class SpeculativeLoadingParameters {
    public static final class Builder {
        private final Map mAdditionalHeaders;
        private NoVarySearchHeader mExpectedNoVarySearchHeader;
        private boolean mIsJavaScriptEnabled;

        public Builder() {
            this.mAdditionalHeaders = new HashMap();
            this.mExpectedNoVarySearchHeader = null;
            this.mIsJavaScriptEnabled = false;
        }

        public Builder addAdditionalHeader(String s, String s1) {
            this.mAdditionalHeaders.put(s, s1);
            return this;
        }

        public Builder addAdditionalHeaders(Map map0) {
            this.mAdditionalHeaders.putAll(map0);
            return this;
        }

        public SpeculativeLoadingParameters build() {
            return new SpeculativeLoadingParameters(this.mAdditionalHeaders, this.mExpectedNoVarySearchHeader, this.mIsJavaScriptEnabled, null);
        }

        public Builder setExpectedNoVarySearchData(NoVarySearchHeader noVarySearchHeader0) {
            this.mExpectedNoVarySearchHeader = noVarySearchHeader0;
            return this;
        }

        public Builder setJavaScriptEnabled(boolean z) {
            this.mIsJavaScriptEnabled = z;
            return this;
        }
    }

    private final Map mAdditionalHeaders;
    private final NoVarySearchHeader mExpectedNoVarySearchHeader;
    private final boolean mIsJavaScriptEnabled;

    private SpeculativeLoadingParameters(Map map0, NoVarySearchHeader noVarySearchHeader0, boolean z) {
        this.mAdditionalHeaders = map0;
        this.mExpectedNoVarySearchHeader = noVarySearchHeader0;
        this.mIsJavaScriptEnabled = z;
    }

    SpeculativeLoadingParameters(Map map0, NoVarySearchHeader noVarySearchHeader0, boolean z, androidx.webkit.SpeculativeLoadingParameters.1 speculativeLoadingParameters$10) {
        this(map0, noVarySearchHeader0, z);
    }

    public Map getAdditionalHeaders() {
        return this.mAdditionalHeaders;
    }

    public NoVarySearchHeader getExpectedNoVarySearchData() {
        return this.mExpectedNoVarySearchHeader;
    }

    public boolean isJavaScriptEnabled() {
        return this.mIsJavaScriptEnabled;
    }

    class androidx.webkit.SpeculativeLoadingParameters.1 {
    }

}

