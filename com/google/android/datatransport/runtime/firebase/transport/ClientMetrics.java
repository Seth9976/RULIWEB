package com.google.android.datatransport.runtime.firebase.transport;

import com.google.android.datatransport.runtime.ProtoEncoderDoNotUse;
import com.google.firebase.encoders.annotations.Encodable.Field;
import com.google.firebase.encoders.annotations.Encodable.Ignore;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ClientMetrics {
    public static final class Builder {
        private String app_namespace_;
        private GlobalMetrics global_metrics_;
        private List log_source_metrics_;
        private TimeWindow window_;

        Builder() {
            this.window_ = null;
            this.log_source_metrics_ = new ArrayList();
            this.global_metrics_ = null;
            this.app_namespace_ = "";
        }

        public Builder addLogSourceMetrics(LogSourceMetrics logSourceMetrics0) {
            this.log_source_metrics_.add(logSourceMetrics0);
            return this;
        }

        public ClientMetrics build() {
            return new ClientMetrics(this.window_, Collections.unmodifiableList(this.log_source_metrics_), this.global_metrics_, this.app_namespace_);
        }

        public Builder setAppNamespace(String s) {
            this.app_namespace_ = s;
            return this;
        }

        public Builder setGlobalMetrics(GlobalMetrics globalMetrics0) {
            this.global_metrics_ = globalMetrics0;
            return this;
        }

        public Builder setLogSourceMetricsList(List list0) {
            this.log_source_metrics_ = list0;
            return this;
        }

        public Builder setWindow(TimeWindow timeWindow0) {
            this.window_ = timeWindow0;
            return this;
        }
    }

    private static final ClientMetrics DEFAULT_INSTANCE;
    private final String app_namespace_;
    private final GlobalMetrics global_metrics_;
    private final List log_source_metrics_;
    private final TimeWindow window_;

    static {
        ClientMetrics.DEFAULT_INSTANCE = new Builder().build();
    }

    ClientMetrics(TimeWindow timeWindow0, List list0, GlobalMetrics globalMetrics0, String s) {
        this.window_ = timeWindow0;
        this.log_source_metrics_ = list0;
        this.global_metrics_ = globalMetrics0;
        this.app_namespace_ = s;
    }

    public String getAppNamespace() {
        return this.app_namespace_;
    }

    public static ClientMetrics getDefaultInstance() {
        return ClientMetrics.DEFAULT_INSTANCE;
    }

    @Ignore
    public GlobalMetrics getGlobalMetrics() {
        return this.global_metrics_ == null ? GlobalMetrics.getDefaultInstance() : this.global_metrics_;
    }

    @Field(name = "globalMetrics")
    public GlobalMetrics getGlobalMetricsInternal() {
        return this.global_metrics_;
    }

    @Field(name = "logSourceMetrics")
    public List getLogSourceMetricsList() {
        return this.log_source_metrics_;
    }

    @Ignore
    public TimeWindow getWindow() {
        return this.window_ == null ? TimeWindow.getDefaultInstance() : this.window_;
    }

    @Field(name = "window")
    public TimeWindow getWindowInternal() {
        return this.window_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() [...] // 潜在的解密器

    public void writeTo(OutputStream outputStream0) throws IOException {
        ProtoEncoderDoNotUse.encode(this, outputStream0);
    }
}

