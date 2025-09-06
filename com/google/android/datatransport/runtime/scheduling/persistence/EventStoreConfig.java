package com.google.android.datatransport.runtime.scheduling.persistence;

abstract class EventStoreConfig {
    static abstract class Builder {
        abstract EventStoreConfig build();

        abstract Builder setCriticalSectionEnterTimeoutMs(int arg1);

        abstract Builder setEventCleanUpAge(long arg1);

        abstract Builder setLoadBatchSize(int arg1);

        abstract Builder setMaxBlobByteSizePerRow(int arg1);

        abstract Builder setMaxStorageSizeInBytes(long arg1);
    }

    static final EventStoreConfig DEFAULT = null;
    private static final long DURATION_ONE_WEEK_MS = 604800000L;
    private static final int LOAD_BATCH_SIZE = 200;
    private static final int LOCK_TIME_OUT_MS = 10000;
    private static final int MAX_BLOB_BYTE_SIZE_PER_ROW = 0x14000;
    private static final long MAX_DB_STORAGE_SIZE_IN_BYTES = 0xA00000L;

    static {
        EventStoreConfig.DEFAULT = EventStoreConfig.builder().setMaxStorageSizeInBytes(0xA00000L).setLoadBatchSize(200).setCriticalSectionEnterTimeoutMs(10000).setEventCleanUpAge(604800000L).setMaxBlobByteSizePerRow(0x14000).build();
    }

    static Builder builder() {
        return new com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig.Builder();
    }

    abstract int getCriticalSectionEnterTimeoutMs();

    abstract long getEventCleanUpAge();

    abstract int getLoadBatchSize();

    abstract int getMaxBlobByteSizePerRow();

    abstract long getMaxStorageSizeInBytes();

    Builder toBuilder() {
        return EventStoreConfig.builder().setMaxStorageSizeInBytes(this.getMaxStorageSizeInBytes()).setLoadBatchSize(this.getLoadBatchSize()).setCriticalSectionEnterTimeoutMs(this.getCriticalSectionEnterTimeoutMs()).setEventCleanUpAge(this.getEventCleanUpAge()).setMaxBlobByteSizePerRow(this.getMaxBlobByteSizePerRow());
    }
}

