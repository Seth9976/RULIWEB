package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;

abstract class SendRequest {
    public static abstract class Builder {
        public abstract SendRequest build();

        abstract Builder setEncoding(Encoding arg1);

        abstract Builder setEvent(Event arg1);

        public Builder setEvent(Event event0, Encoding encoding0, Transformer transformer0) {
            this.setEvent(event0);
            this.setEncoding(encoding0);
            this.setTransformer(transformer0);
            return this;
        }

        abstract Builder setTransformer(Transformer arg1);

        public abstract Builder setTransportContext(TransportContext arg1);

        public abstract Builder setTransportName(String arg1);
    }

    public static Builder builder() {
        return new com.google.android.datatransport.runtime.AutoValue_SendRequest.Builder();
    }

    public abstract Encoding getEncoding();

    abstract Event getEvent();

    public byte[] getPayload() {
        return (byte[])this.getTransformer().apply(this.getEvent().getPayload());
    }

    abstract Transformer getTransformer();

    public abstract TransportContext getTransportContext();

    public abstract String getTransportName();
}

