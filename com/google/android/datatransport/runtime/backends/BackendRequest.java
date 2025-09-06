package com.google.android.datatransport.runtime.backends;

public abstract class BackendRequest {
    public static abstract class Builder {
        public abstract BackendRequest build();

        public abstract Builder setEvents(Iterable arg1);

        public abstract Builder setExtras(byte[] arg1);
    }

    public static Builder builder() {
        return new com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest.Builder();
    }

    public static BackendRequest create(Iterable iterable0) {
        return BackendRequest.builder().setEvents(iterable0).build();
    }

    public abstract Iterable getEvents();

    public abstract byte[] getExtras();
}

