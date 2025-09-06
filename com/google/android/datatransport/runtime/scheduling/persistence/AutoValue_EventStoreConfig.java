package com.google.android.datatransport.runtime.scheduling.persistence;

final class AutoValue_EventStoreConfig extends EventStoreConfig {
    static final class Builder extends com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder {
        private Integer criticalSectionEnterTimeoutMs;
        private Long eventCleanUpAge;
        private Integer loadBatchSize;
        private Integer maxBlobByteSizePerRow;
        private Long maxStorageSizeInBytes;

        @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig$Builder
        EventStoreConfig build() {
            String s = this.maxStorageSizeInBytes == null ? " maxStorageSizeInBytes" : "";
            if(this.loadBatchSize == null) {
                s = s + " loadBatchSize";
            }
            if(this.criticalSectionEnterTimeoutMs == null) {
                s = s + " criticalSectionEnterTimeoutMs";
            }
            if(this.eventCleanUpAge == null) {
                s = s + " eventCleanUpAge";
            }
            if(this.maxBlobByteSizePerRow == null) {
                s = s + " maxBlobByteSizePerRow";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_EventStoreConfig(((long)this.maxStorageSizeInBytes), ((int)this.loadBatchSize), ((int)this.criticalSectionEnterTimeoutMs), ((long)this.eventCleanUpAge), ((int)this.maxBlobByteSizePerRow), null);
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig$Builder
        com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder setCriticalSectionEnterTimeoutMs(int v) {
            this.criticalSectionEnterTimeoutMs = v;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig$Builder
        com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder setEventCleanUpAge(long v) {
            this.eventCleanUpAge = v;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig$Builder
        com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder setLoadBatchSize(int v) {
            this.loadBatchSize = v;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig$Builder
        com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder setMaxBlobByteSizePerRow(int v) {
            this.maxBlobByteSizePerRow = v;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig$Builder
        com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder setMaxStorageSizeInBytes(long v) {
            this.maxStorageSizeInBytes = v;
            return this;
        }
    }

    private final int criticalSectionEnterTimeoutMs;
    private final long eventCleanUpAge;
    private final int loadBatchSize;
    private final int maxBlobByteSizePerRow;
    private final long maxStorageSizeInBytes;

    private AutoValue_EventStoreConfig(long v, int v1, int v2, long v3, int v4) {
        this.maxStorageSizeInBytes = v;
        this.loadBatchSize = v1;
        this.criticalSectionEnterTimeoutMs = v2;
        this.eventCleanUpAge = v3;
        this.maxBlobByteSizePerRow = v4;
    }

    AutoValue_EventStoreConfig(long v, int v1, int v2, long v3, int v4, com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig.1 autoValue_EventStoreConfig$10) {
        this(v, v1, v2, v3, v4);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof EventStoreConfig) {
            long v = ((EventStoreConfig)object0).getMaxStorageSizeInBytes();
            if(this.maxStorageSizeInBytes == v) {
                int v1 = ((EventStoreConfig)object0).getLoadBatchSize();
                if(this.loadBatchSize == v1) {
                    int v2 = ((EventStoreConfig)object0).getCriticalSectionEnterTimeoutMs();
                    if(this.criticalSectionEnterTimeoutMs == v2) {
                        long v3 = ((EventStoreConfig)object0).getEventCleanUpAge();
                        if(this.eventCleanUpAge == v3) {
                            int v4 = ((EventStoreConfig)object0).getMaxBlobByteSizePerRow();
                            return this.maxBlobByteSizePerRow == v4;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int getCriticalSectionEnterTimeoutMs() {
        return this.criticalSectionEnterTimeoutMs;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    long getEventCleanUpAge() {
        return this.eventCleanUpAge;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int getLoadBatchSize() {
        return this.loadBatchSize;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int getMaxBlobByteSizePerRow() {
        return this.maxBlobByteSizePerRow;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    long getMaxStorageSizeInBytes() {
        return this.maxStorageSizeInBytes;
    }

    @Override
    public int hashCode() {
        return this.maxBlobByteSizePerRow ^ ((((((int)(this.maxStorageSizeInBytes ^ this.maxStorageSizeInBytes >>> 0x20)) ^ 1000003) * 1000003 ^ this.loadBatchSize) * 1000003 ^ this.criticalSectionEnterTimeoutMs) * 1000003 ^ ((int)(this.eventCleanUpAge ^ this.eventCleanUpAge >>> 0x20))) * 1000003;
    }

    @Override
    public String toString() {
        return "EventStoreConfig{maxStorageSizeInBytes=" + this.maxStorageSizeInBytes + ", loadBatchSize=" + this.loadBatchSize + ", criticalSectionEnterTimeoutMs=" + this.criticalSectionEnterTimeoutMs + ", eventCleanUpAge=" + this.eventCleanUpAge + ", maxBlobByteSizePerRow=" + this.maxBlobByteSizePerRow + "}";
    }

    class com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig.1 {
    }

}

