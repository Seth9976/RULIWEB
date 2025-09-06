package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.annotations.Encodable.Field;
import com.google.firebase.encoders.annotations.Encodable.Ignore;

public final class GlobalMetrics {
    public static final class Builder {
        private StorageMetrics storage_metrics_;

        Builder() {
            this.storage_metrics_ = null;
        }

        public GlobalMetrics build() {
            return new GlobalMetrics(this.storage_metrics_);
        }

        public Builder setStorageMetrics(StorageMetrics storageMetrics0) {
            this.storage_metrics_ = storageMetrics0;
            return this;
        }
    }

    private static final GlobalMetrics DEFAULT_INSTANCE;
    private final StorageMetrics storage_metrics_;

    static {
        GlobalMetrics.DEFAULT_INSTANCE = new Builder().build();
    }

    GlobalMetrics(StorageMetrics storageMetrics0) {
        this.storage_metrics_ = storageMetrics0;
    }

    public static GlobalMetrics getDefaultInstance() {
        return GlobalMetrics.DEFAULT_INSTANCE;
    }

    @Ignore
    public StorageMetrics getStorageMetrics() {
        return this.storage_metrics_ == null ? StorageMetrics.getDefaultInstance() : this.storage_metrics_;
    }

    @Field(name = "storageMetrics")
    public StorageMetrics getStorageMetricsInternal() {
        return this.storage_metrics_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

