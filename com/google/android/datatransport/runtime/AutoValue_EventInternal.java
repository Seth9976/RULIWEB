package com.google.android.datatransport.runtime;

import java.util.Map;

final class AutoValue_EventInternal extends EventInternal {
    static final class Builder extends com.google.android.datatransport.runtime.EventInternal.Builder {
        private Map autoMetadata;
        private Integer code;
        private EncodedPayload encodedPayload;
        private Long eventMillis;
        private String transportName;
        private Long uptimeMillis;

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        public EventInternal build() {
            String s = this.transportName == null ? " transportName" : "";
            if(this.encodedPayload == null) {
                s = s + " encodedPayload";
            }
            if(this.eventMillis == null) {
                s = s + " eventMillis";
            }
            if(this.uptimeMillis == null) {
                s = s + " uptimeMillis";
            }
            if(this.autoMetadata == null) {
                s = s + " autoMetadata";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, ((long)this.eventMillis), ((long)this.uptimeMillis), this.autoMetadata, null);
        }

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        protected Map getAutoMetadata() {
            Map map0 = this.autoMetadata;
            if(map0 == null) {
                throw new IllegalStateException("Property \"autoMetadata\" has not been set");
            }
            return map0;
        }

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        protected com.google.android.datatransport.runtime.EventInternal.Builder setAutoMetadata(Map map0) {
            if(map0 == null) {
                throw new NullPointerException("Null autoMetadata");
            }
            this.autoMetadata = map0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        public com.google.android.datatransport.runtime.EventInternal.Builder setCode(Integer integer0) {
            this.code = integer0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        public com.google.android.datatransport.runtime.EventInternal.Builder setEncodedPayload(EncodedPayload encodedPayload0) {
            if(encodedPayload0 == null) {
                throw new NullPointerException("Null encodedPayload");
            }
            this.encodedPayload = encodedPayload0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        public com.google.android.datatransport.runtime.EventInternal.Builder setEventMillis(long v) {
            this.eventMillis = v;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        public com.google.android.datatransport.runtime.EventInternal.Builder setTransportName(String s) {
            if(s == null) {
                throw new NullPointerException("Null transportName");
            }
            this.transportName = s;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.EventInternal$Builder
        public com.google.android.datatransport.runtime.EventInternal.Builder setUptimeMillis(long v) {
            this.uptimeMillis = v;
            return this;
        }
    }

    private final Map autoMetadata;
    private final Integer code;
    private final EncodedPayload encodedPayload;
    private final long eventMillis;
    private final String transportName;
    private final long uptimeMillis;

    private AutoValue_EventInternal(String s, Integer integer0, EncodedPayload encodedPayload0, long v, long v1, Map map0) {
        this.transportName = s;
        this.code = integer0;
        this.encodedPayload = encodedPayload0;
        this.eventMillis = v;
        this.uptimeMillis = v1;
        this.autoMetadata = map0;
    }

    AutoValue_EventInternal(String s, Integer integer0, EncodedPayload encodedPayload0, long v, long v1, Map map0, com.google.android.datatransport.runtime.AutoValue_EventInternal.1 autoValue_EventInternal$10) {
        this(s, integer0, encodedPayload0, v, v1, map0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof EventInternal) {
            String s = ((EventInternal)object0).getTransportName();
            if(this.transportName.equals(s)) {
                Integer integer0 = this.code;
                if(integer0 != null) {
                    if(integer0.equals(((EventInternal)object0).getCode())) {
                    label_10:
                        EncodedPayload encodedPayload0 = ((EventInternal)object0).getEncodedPayload();
                        if(this.encodedPayload.equals(encodedPayload0)) {
                            long v = ((EventInternal)object0).getEventMillis();
                            if(this.eventMillis == v) {
                                long v1 = ((EventInternal)object0).getUptimeMillis();
                                if(this.uptimeMillis == v1) {
                                    Map map0 = ((EventInternal)object0).getAutoMetadata();
                                    return this.autoMetadata.equals(map0);
                                }
                            }
                        }
                    }
                }
                else if(((EventInternal)object0).getCode() == null) {
                    goto label_10;
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.EventInternal
    protected Map getAutoMetadata() {
        return this.autoMetadata;
    }

    @Override  // com.google.android.datatransport.runtime.EventInternal
    public Integer getCode() {
        return this.code;
    }

    @Override  // com.google.android.datatransport.runtime.EventInternal
    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    @Override  // com.google.android.datatransport.runtime.EventInternal
    public long getEventMillis() {
        return this.eventMillis;
    }

    @Override  // com.google.android.datatransport.runtime.EventInternal
    public String getTransportName() {
        return this.transportName;
    }

    @Override  // com.google.android.datatransport.runtime.EventInternal
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    @Override
    public int hashCode() {
        int v = this.transportName.hashCode();
        return this.code == null ? ((((v ^ 1000003) * 0xD5009D89 ^ this.encodedPayload.hashCode()) * 1000003 ^ ((int)(this.eventMillis ^ this.eventMillis >>> 0x20))) * 1000003 ^ ((int)(this.uptimeMillis ^ this.uptimeMillis >>> 0x20))) * 1000003 ^ this.autoMetadata.hashCode() : (((((v ^ 1000003) * 1000003 ^ this.code.hashCode()) * 1000003 ^ this.encodedPayload.hashCode()) * 1000003 ^ ((int)(this.eventMillis ^ this.eventMillis >>> 0x20))) * 1000003 ^ ((int)(this.uptimeMillis ^ this.uptimeMillis >>> 0x20))) * 1000003 ^ this.autoMetadata.hashCode();
    }

    @Override
    public String toString() {
        return "EventInternal{transportName=" + this.transportName + ", code=" + this.code + ", encodedPayload=" + this.encodedPayload + ", eventMillis=" + this.eventMillis + ", uptimeMillis=" + this.uptimeMillis + ", autoMetadata=" + this.autoMetadata + "}";
    }

    class com.google.android.datatransport.runtime.AutoValue_EventInternal.1 {
    }

}

