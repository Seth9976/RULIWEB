package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.annotations.Encodable.Field;
import java.util.List;

final class AutoValue_LogRequest extends LogRequest {
    static final class Builder extends com.google.android.datatransport.cct.internal.LogRequest.Builder {
        private ClientInfo clientInfo;
        private List logEvents;
        private Integer logSource;
        private String logSourceName;
        private QosTier qosTier;
        private Long requestTimeMs;
        private Long requestUptimeMs;

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        public LogRequest build() {
            String s = this.requestTimeMs == null ? " requestTimeMs" : "";
            if(this.requestUptimeMs == null) {
                s = s + " requestUptimeMs";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_LogRequest(((long)this.requestTimeMs), ((long)this.requestUptimeMs), this.clientInfo, this.logSource, this.logSourceName, this.logEvents, this.qosTier, null);
        }

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        public com.google.android.datatransport.cct.internal.LogRequest.Builder setClientInfo(ClientInfo clientInfo0) {
            this.clientInfo = clientInfo0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        public com.google.android.datatransport.cct.internal.LogRequest.Builder setLogEvents(List list0) {
            this.logEvents = list0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        com.google.android.datatransport.cct.internal.LogRequest.Builder setLogSource(Integer integer0) {
            this.logSource = integer0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        com.google.android.datatransport.cct.internal.LogRequest.Builder setLogSourceName(String s) {
            this.logSourceName = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        public com.google.android.datatransport.cct.internal.LogRequest.Builder setQosTier(QosTier qosTier0) {
            this.qosTier = qosTier0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        public com.google.android.datatransport.cct.internal.LogRequest.Builder setRequestTimeMs(long v) {
            this.requestTimeMs = v;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogRequest$Builder
        public com.google.android.datatransport.cct.internal.LogRequest.Builder setRequestUptimeMs(long v) {
            this.requestUptimeMs = v;
            return this;
        }
    }

    private final ClientInfo clientInfo;
    private final List logEvents;
    private final Integer logSource;
    private final String logSourceName;
    private final QosTier qosTier;
    private final long requestTimeMs;
    private final long requestUptimeMs;

    private AutoValue_LogRequest(long v, long v1, ClientInfo clientInfo0, Integer integer0, String s, List list0, QosTier qosTier0) {
        this.requestTimeMs = v;
        this.requestUptimeMs = v1;
        this.clientInfo = clientInfo0;
        this.logSource = integer0;
        this.logSourceName = s;
        this.logEvents = list0;
        this.qosTier = qosTier0;
    }

    AutoValue_LogRequest(long v, long v1, ClientInfo clientInfo0, Integer integer0, String s, List list0, QosTier qosTier0, com.google.android.datatransport.cct.internal.AutoValue_LogRequest.1 autoValue_LogRequest$10) {
        this(v, v1, clientInfo0, integer0, s, list0, qosTier0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof LogRequest) {
            long v = ((LogRequest)object0).getRequestTimeMs();
            if(this.requestTimeMs == v) {
                long v1 = ((LogRequest)object0).getRequestUptimeMs();
                if(this.requestUptimeMs == v1) {
                    ClientInfo clientInfo0 = this.clientInfo;
                    if(clientInfo0 != null) {
                        if(clientInfo0.equals(((LogRequest)object0).getClientInfo())) {
                        label_12:
                            Integer integer0 = this.logSource;
                            if(integer0 != null) {
                                if(integer0.equals(((LogRequest)object0).getLogSource())) {
                                label_17:
                                    String s = this.logSourceName;
                                    if(s != null) {
                                        if(s.equals(((LogRequest)object0).getLogSourceName())) {
                                        label_22:
                                            List list0 = this.logEvents;
                                            if(list0 == null) {
                                                if(((LogRequest)object0).getLogEvents() == null) {
                                                    return this.qosTier == null ? ((LogRequest)object0).getQosTier() == null : this.qosTier.equals(((LogRequest)object0).getQosTier());
                                                }
                                            }
                                            else if(list0.equals(((LogRequest)object0).getLogEvents())) {
                                                return this.qosTier == null ? ((LogRequest)object0).getQosTier() == null : this.qosTier.equals(((LogRequest)object0).getQosTier());
                                            }
                                        }
                                    }
                                    else if(((LogRequest)object0).getLogSourceName() == null) {
                                        goto label_22;
                                    }
                                }
                            }
                            else if(((LogRequest)object0).getLogSource() == null) {
                                goto label_17;
                            }
                        }
                    }
                    else if(((LogRequest)object0).getClientInfo() == null) {
                        goto label_12;
                    }
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogRequest
    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogRequest
    @Field(name = "logEvent")
    public List getLogEvents() {
        return this.logEvents;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogRequest
    public Integer getLogSource() {
        return this.logSource;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogRequest
    public String getLogSourceName() {
        return this.logSourceName;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogRequest
    public QosTier getQosTier() {
        return this.qosTier;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestTimeMs() {
        return this.requestTimeMs;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestUptimeMs() {
        return this.requestUptimeMs;
    }

    @Override
    public int hashCode() {
        int v = ((((int)(this.requestTimeMs ^ this.requestTimeMs >>> 0x20)) ^ 1000003) * 1000003 ^ ((int)(this.requestUptimeMs ^ this.requestUptimeMs >>> 0x20))) * 1000003;
        int v1 = 0;
        int v2 = this.clientInfo == null ? 0 : this.clientInfo.hashCode();
        int v3 = this.logSource == null ? 0 : this.logSource.hashCode();
        int v4 = this.logSourceName == null ? 0 : this.logSourceName.hashCode();
        int v5 = this.logEvents == null ? 0 : this.logEvents.hashCode();
        QosTier qosTier0 = this.qosTier;
        if(qosTier0 != null) {
            v1 = qosTier0.hashCode();
        }
        return ((((v ^ v2) * 1000003 ^ v3) * 1000003 ^ v4) * 1000003 ^ v5) * 1000003 ^ v1;
    }

    @Override
    public String toString() {
        return "LogRequest{requestTimeMs=" + this.requestTimeMs + ", requestUptimeMs=" + this.requestUptimeMs + ", clientInfo=" + this.clientInfo + ", logSource=" + this.logSource + ", logSourceName=" + this.logSourceName + ", logEvents=" + this.logEvents + ", qosTier=" + this.qosTier + "}";
    }

    class com.google.android.datatransport.cct.internal.AutoValue_LogRequest.1 {
    }

}

