package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;

final class AutoValue_SendRequest extends SendRequest {
    static final class Builder extends com.google.android.datatransport.runtime.SendRequest.Builder {
        private Encoding encoding;
        private Event event;
        private Transformer transformer;
        private TransportContext transportContext;
        private String transportName;

        @Override  // com.google.android.datatransport.runtime.SendRequest$Builder
        public SendRequest build() {
            String s = this.transportContext == null ? " transportContext" : "";
            if(this.transportName == null) {
                s = s + " transportName";
            }
            if(this.event == null) {
                s = s + " event";
            }
            if(this.transformer == null) {
                s = s + " transformer";
            }
            if(this.encoding == null) {
                s = s + " encoding";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_SendRequest(this.transportContext, this.transportName, this.event, this.transformer, this.encoding, null);
        }

        @Override  // com.google.android.datatransport.runtime.SendRequest$Builder
        com.google.android.datatransport.runtime.SendRequest.Builder setEncoding(Encoding encoding0) {
            if(encoding0 == null) {
                throw new NullPointerException("Null encoding");
            }
            this.encoding = encoding0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.SendRequest$Builder
        com.google.android.datatransport.runtime.SendRequest.Builder setEvent(Event event0) {
            if(event0 == null) {
                throw new NullPointerException("Null event");
            }
            this.event = event0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.SendRequest$Builder
        com.google.android.datatransport.runtime.SendRequest.Builder setTransformer(Transformer transformer0) {
            if(transformer0 == null) {
                throw new NullPointerException("Null transformer");
            }
            this.transformer = transformer0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.SendRequest$Builder
        public com.google.android.datatransport.runtime.SendRequest.Builder setTransportContext(TransportContext transportContext0) {
            if(transportContext0 == null) {
                throw new NullPointerException("Null transportContext");
            }
            this.transportContext = transportContext0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.SendRequest$Builder
        public com.google.android.datatransport.runtime.SendRequest.Builder setTransportName(String s) {
            if(s == null) {
                throw new NullPointerException("Null transportName");
            }
            this.transportName = s;
            return this;
        }
    }

    private final Encoding encoding;
    private final Event event;
    private final Transformer transformer;
    private final TransportContext transportContext;
    private final String transportName;

    private AutoValue_SendRequest(TransportContext transportContext0, String s, Event event0, Transformer transformer0, Encoding encoding0) {
        this.transportContext = transportContext0;
        this.transportName = s;
        this.event = event0;
        this.transformer = transformer0;
        this.encoding = encoding0;
    }

    AutoValue_SendRequest(TransportContext transportContext0, String s, Event event0, Transformer transformer0, Encoding encoding0, com.google.android.datatransport.runtime.AutoValue_SendRequest.1 autoValue_SendRequest$10) {
        this(transportContext0, s, event0, transformer0, encoding0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof SendRequest) {
            TransportContext transportContext0 = ((SendRequest)object0).getTransportContext();
            if(this.transportContext.equals(transportContext0)) {
                String s = ((SendRequest)object0).getTransportName();
                if(this.transportName.equals(s)) {
                    Event event0 = ((SendRequest)object0).getEvent();
                    if(this.event.equals(event0)) {
                        Transformer transformer0 = ((SendRequest)object0).getTransformer();
                        if(this.transformer.equals(transformer0)) {
                            Encoding encoding0 = ((SendRequest)object0).getEncoding();
                            return this.encoding.equals(encoding0);
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.SendRequest
    public Encoding getEncoding() {
        return this.encoding;
    }

    @Override  // com.google.android.datatransport.runtime.SendRequest
    Event getEvent() {
        return this.event;
    }

    @Override  // com.google.android.datatransport.runtime.SendRequest
    Transformer getTransformer() {
        return this.transformer;
    }

    @Override  // com.google.android.datatransport.runtime.SendRequest
    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    @Override  // com.google.android.datatransport.runtime.SendRequest
    public String getTransportName() {
        return this.transportName;
    }

    @Override
    public int hashCode() {
        return ((((this.transportContext.hashCode() ^ 1000003) * 1000003 ^ this.transportName.hashCode()) * 1000003 ^ this.event.hashCode()) * 1000003 ^ this.transformer.hashCode()) * 1000003 ^ this.encoding.hashCode();
    }

    @Override
    public String toString() {
        return "SendRequest{transportContext=" + this.transportContext + ", transportName=" + this.transportName + ", event=" + this.event + ", transformer=" + this.transformer + ", encoding=" + this.encoding + "}";
    }

    class com.google.android.datatransport.runtime.AutoValue_SendRequest.1 {
    }

}

