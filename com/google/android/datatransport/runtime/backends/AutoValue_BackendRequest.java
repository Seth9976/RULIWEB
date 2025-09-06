package com.google.android.datatransport.runtime.backends;

import java.util.Arrays;

final class AutoValue_BackendRequest extends BackendRequest {
    static final class Builder extends com.google.android.datatransport.runtime.backends.BackendRequest.Builder {
        private Iterable events;
        private byte[] extras;

        @Override  // com.google.android.datatransport.runtime.backends.BackendRequest$Builder
        public BackendRequest build() {
            String s = this.events == null ? " events" : "";
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_BackendRequest(this.events, this.extras, null);
        }

        @Override  // com.google.android.datatransport.runtime.backends.BackendRequest$Builder
        public com.google.android.datatransport.runtime.backends.BackendRequest.Builder setEvents(Iterable iterable0) {
            if(iterable0 == null) {
                throw new NullPointerException("Null events");
            }
            this.events = iterable0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.backends.BackendRequest$Builder
        public com.google.android.datatransport.runtime.backends.BackendRequest.Builder setExtras(byte[] arr_b) {
            this.extras = arr_b;
            return this;
        }
    }

    private final Iterable events;
    private final byte[] extras;

    private AutoValue_BackendRequest(Iterable iterable0, byte[] arr_b) {
        this.events = iterable0;
        this.extras = arr_b;
    }

    AutoValue_BackendRequest(Iterable iterable0, byte[] arr_b, com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest.1 autoValue_BackendRequest$10) {
        this(iterable0, arr_b);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof BackendRequest) {
            Iterable iterable0 = ((BackendRequest)object0).getEvents();
            if(this.events.equals(iterable0)) {
                byte[] arr_b = ((BackendRequest)object0) instanceof AutoValue_BackendRequest ? ((AutoValue_BackendRequest)(((BackendRequest)object0))).extras : ((BackendRequest)object0).getExtras();
                return Arrays.equals(this.extras, arr_b);
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.backends.BackendRequest
    public Iterable getEvents() {
        return this.events;
    }

    @Override  // com.google.android.datatransport.runtime.backends.BackendRequest
    public byte[] getExtras() {
        return this.extras;
    }

    @Override
    public int hashCode() {
        int v = Arrays.hashCode(this.extras);
        return (this.events.hashCode() ^ 1000003) * 1000003 ^ v;
    }

    @Override
    public String toString() {
        return "BackendRequest{events=" + this.events + ", extras=" + Arrays.toString(this.extras) + "}";
    }

    class com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest.1 {
    }

}

