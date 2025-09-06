package androidx.webkit;

public class SpeculativeLoadingConfig {
    public static final class Builder {
        private int mMaxPrefetches;
        private int mPrefetchTTLSeconds;

        public Builder() {
            this.mPrefetchTTLSeconds = 60;
            this.mMaxPrefetches = 10;
        }

        public SpeculativeLoadingConfig build() {
            return new SpeculativeLoadingConfig(this.mPrefetchTTLSeconds, this.mMaxPrefetches, null);
        }

        public Builder setMaxPrefetches(int v) {
            if(v > 20) {
                throw new IllegalArgumentException("Max prefetches cannot exceed20");
            }
            if(v < 1) {
                throw new IllegalArgumentException("Max prefetches must be greater than 0");
            }
            this.mMaxPrefetches = v;
            return this;
        }

        public Builder setPrefetchTtlSeconds(int v) {
            if(v <= 0) {
                throw new IllegalArgumentException("Prefetch TTL must be greater than 0");
            }
            this.mPrefetchTTLSeconds = v;
            return this;
        }
    }

    public static final int ABSOLUTE_MAX_PREFETCHES = 20;
    public static final int DEFAULT_MAX_PREFETCHES = 10;
    public static final int DEFAULT_TTL_SECS = 60;
    private final int mMaxPrefetches;
    private final int mPrefetchTTLSeconds;

    private SpeculativeLoadingConfig(int v, int v1) {
        this.mPrefetchTTLSeconds = v;
        this.mMaxPrefetches = v1;
    }

    SpeculativeLoadingConfig(int v, int v1, androidx.webkit.SpeculativeLoadingConfig.1 speculativeLoadingConfig$10) {
        this(v, v1);
    }

    public int getMaxPrefetches() {
        return this.mMaxPrefetches;
    }

    public int getPrefetchTtlSeconds() {
        return this.mPrefetchTTLSeconds;
    }

    class androidx.webkit.SpeculativeLoadingConfig.1 {
    }

}

