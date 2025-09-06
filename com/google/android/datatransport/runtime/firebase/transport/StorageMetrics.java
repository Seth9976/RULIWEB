package com.google.android.datatransport.runtime.firebase.transport;

public final class StorageMetrics {
    public static final class Builder {
        private long current_cache_size_bytes_;
        private long max_cache_size_bytes_;

        Builder() {
            this.current_cache_size_bytes_ = 0L;
            this.max_cache_size_bytes_ = 0L;
        }

        public StorageMetrics build() {
            return new StorageMetrics(this.current_cache_size_bytes_, this.max_cache_size_bytes_);
        }

        public Builder setCurrentCacheSizeBytes(long v) {
            this.current_cache_size_bytes_ = v;
            return this;
        }

        public Builder setMaxCacheSizeBytes(long v) {
            this.max_cache_size_bytes_ = v;
            return this;
        }
    }

    private static final StorageMetrics DEFAULT_INSTANCE;
    private final long current_cache_size_bytes_;
    private final long max_cache_size_bytes_;

    static {
        StorageMetrics.DEFAULT_INSTANCE = new Builder().build();
    }

    StorageMetrics(long v, long v1) {
        this.current_cache_size_bytes_ = v;
        this.max_cache_size_bytes_ = v1;
    }

    public long getCurrentCacheSizeBytes() {
        return this.current_cache_size_bytes_;
    }

    public static StorageMetrics getDefaultInstance() {
        return StorageMetrics.DEFAULT_INSTANCE;
    }

    public long getMaxCacheSizeBytes() {
        return this.max_cache_size_bytes_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

