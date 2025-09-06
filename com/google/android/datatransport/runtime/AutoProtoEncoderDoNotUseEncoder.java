package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;
import java.io.IOException;

public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    static final class ClientMetricsEncoder implements ObjectEncoder {
        private static final FieldDescriptor APPNAMESPACE_DESCRIPTOR;
        private static final FieldDescriptor GLOBALMETRICS_DESCRIPTOR;
        static final ClientMetricsEncoder INSTANCE;
        private static final FieldDescriptor LOGSOURCEMETRICS_DESCRIPTOR;
        private static final FieldDescriptor WINDOW_DESCRIPTOR;

        static {
            ClientMetricsEncoder.INSTANCE = new ClientMetricsEncoder();
            ClientMetricsEncoder.WINDOW_DESCRIPTOR = FieldDescriptor.builder("window").withProperty(AtProtobuf.builder().tag(1).build()).build();
            ClientMetricsEncoder.LOGSOURCEMETRICS_DESCRIPTOR = FieldDescriptor.builder("logSourceMetrics").withProperty(AtProtobuf.builder().tag(2).build()).build();
            ClientMetricsEncoder.GLOBALMETRICS_DESCRIPTOR = FieldDescriptor.builder("globalMetrics").withProperty(AtProtobuf.builder().tag(3).build()).build();
            ClientMetricsEncoder.APPNAMESPACE_DESCRIPTOR = FieldDescriptor.builder("appNamespace").withProperty(AtProtobuf.builder().tag(4).build()).build();
        }

        public void encode(ClientMetrics clientMetrics0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            objectEncoderContext0.add(ClientMetricsEncoder.WINDOW_DESCRIPTOR, clientMetrics0.getWindowInternal());
            objectEncoderContext0.add(ClientMetricsEncoder.LOGSOURCEMETRICS_DESCRIPTOR, clientMetrics0.getLogSourceMetricsList());
            objectEncoderContext0.add(ClientMetricsEncoder.GLOBALMETRICS_DESCRIPTOR, clientMetrics0.getGlobalMetricsInternal());
            objectEncoderContext0.add(ClientMetricsEncoder.APPNAMESPACE_DESCRIPTOR, clientMetrics0.getAppNamespace());
        }

        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException {
            this.encode(((ClientMetrics)object0), ((ObjectEncoderContext)object1));
        }
    }

    static final class GlobalMetricsEncoder implements ObjectEncoder {
        static final GlobalMetricsEncoder INSTANCE;
        private static final FieldDescriptor STORAGEMETRICS_DESCRIPTOR;

        static {
            GlobalMetricsEncoder.INSTANCE = new GlobalMetricsEncoder();
            GlobalMetricsEncoder.STORAGEMETRICS_DESCRIPTOR = FieldDescriptor.builder("storageMetrics").withProperty(AtProtobuf.builder().tag(1).build()).build();
        }

        public void encode(GlobalMetrics globalMetrics0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            StorageMetrics storageMetrics0 = globalMetrics0.getStorageMetricsInternal();
            objectEncoderContext0.add(GlobalMetricsEncoder.STORAGEMETRICS_DESCRIPTOR, storageMetrics0);
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class LogEventDroppedEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTSDROPPEDCOUNT_DESCRIPTOR;
        static final LogEventDroppedEncoder INSTANCE;
        private static final FieldDescriptor REASON_DESCRIPTOR;

        static {
            LogEventDroppedEncoder.INSTANCE = new LogEventDroppedEncoder();
            LogEventDroppedEncoder.EVENTSDROPPEDCOUNT_DESCRIPTOR = FieldDescriptor.builder("eventsDroppedCount").withProperty(AtProtobuf.builder().tag(1).build()).build();
            LogEventDroppedEncoder.REASON_DESCRIPTOR = FieldDescriptor.builder("reason").withProperty(AtProtobuf.builder().tag(3).build()).build();
        }

        public void encode(LogEventDropped logEventDropped0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            objectEncoderContext0.add(LogEventDroppedEncoder.EVENTSDROPPEDCOUNT_DESCRIPTOR, logEventDropped0.getEventsDroppedCount());
            objectEncoderContext0.add(LogEventDroppedEncoder.REASON_DESCRIPTOR, logEventDropped0.getReason());
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class LogSourceMetricsEncoder implements ObjectEncoder {
        static final LogSourceMetricsEncoder INSTANCE;
        private static final FieldDescriptor LOGEVENTDROPPED_DESCRIPTOR;
        private static final FieldDescriptor LOGSOURCE_DESCRIPTOR;

        static {
            LogSourceMetricsEncoder.INSTANCE = new LogSourceMetricsEncoder();
            LogSourceMetricsEncoder.LOGSOURCE_DESCRIPTOR = FieldDescriptor.builder("logSource").withProperty(AtProtobuf.builder().tag(1).build()).build();
            LogSourceMetricsEncoder.LOGEVENTDROPPED_DESCRIPTOR = FieldDescriptor.builder("logEventDropped").withProperty(AtProtobuf.builder().tag(2).build()).build();
        }

        public void encode(LogSourceMetrics logSourceMetrics0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            objectEncoderContext0.add(LogSourceMetricsEncoder.LOGSOURCE_DESCRIPTOR, logSourceMetrics0.getLogSource());
            objectEncoderContext0.add(LogSourceMetricsEncoder.LOGEVENTDROPPED_DESCRIPTOR, logSourceMetrics0.getLogEventDroppedList());
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLIENTMETRICS_DESCRIPTOR;
        static final ProtoEncoderDoNotUseEncoder INSTANCE;

        static {
            ProtoEncoderDoNotUseEncoder.INSTANCE = new ProtoEncoderDoNotUseEncoder();
            ProtoEncoderDoNotUseEncoder.CLIENTMETRICS_DESCRIPTOR = FieldDescriptor.of("clientMetrics");
        }

        public void encode(ProtoEncoderDoNotUse protoEncoderDoNotUse0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            ClientMetrics clientMetrics0 = protoEncoderDoNotUse0.getClientMetrics();
            objectEncoderContext0.add(ProtoEncoderDoNotUseEncoder.CLIENTMETRICS_DESCRIPTOR, clientMetrics0);
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class StorageMetricsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CURRENTCACHESIZEBYTES_DESCRIPTOR;
        static final StorageMetricsEncoder INSTANCE;
        private static final FieldDescriptor MAXCACHESIZEBYTES_DESCRIPTOR;

        static {
            StorageMetricsEncoder.INSTANCE = new StorageMetricsEncoder();
            StorageMetricsEncoder.CURRENTCACHESIZEBYTES_DESCRIPTOR = FieldDescriptor.builder("currentCacheSizeBytes").withProperty(AtProtobuf.builder().tag(1).build()).build();
            StorageMetricsEncoder.MAXCACHESIZEBYTES_DESCRIPTOR = FieldDescriptor.builder("maxCacheSizeBytes").withProperty(AtProtobuf.builder().tag(2).build()).build();
        }

        public void encode(StorageMetrics storageMetrics0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            objectEncoderContext0.add(StorageMetricsEncoder.CURRENTCACHESIZEBYTES_DESCRIPTOR, storageMetrics0.getCurrentCacheSizeBytes());
            objectEncoderContext0.add(StorageMetricsEncoder.MAXCACHESIZEBYTES_DESCRIPTOR, storageMetrics0.getMaxCacheSizeBytes());
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class TimeWindowEncoder implements ObjectEncoder {
        private static final FieldDescriptor ENDMS_DESCRIPTOR;
        static final TimeWindowEncoder INSTANCE;
        private static final FieldDescriptor STARTMS_DESCRIPTOR;

        static {
            TimeWindowEncoder.INSTANCE = new TimeWindowEncoder();
            TimeWindowEncoder.STARTMS_DESCRIPTOR = FieldDescriptor.builder("startMs").withProperty(AtProtobuf.builder().tag(1).build()).build();
            TimeWindowEncoder.ENDMS_DESCRIPTOR = FieldDescriptor.builder("endMs").withProperty(AtProtobuf.builder().tag(2).build()).build();
        }

        public void encode(TimeWindow timeWindow0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            objectEncoderContext0.add(TimeWindowEncoder.STARTMS_DESCRIPTOR, timeWindow0.getStartMs());
            objectEncoderContext0.add(TimeWindowEncoder.ENDMS_DESCRIPTOR, timeWindow0.getEndMs());
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG;

    static {
        AutoProtoEncoderDoNotUseEncoder.CONFIG = new AutoProtoEncoderDoNotUseEncoder();
    }

    @Override  // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig encoderConfig0) {
        encoderConfig0.registerEncoder(ProtoEncoderDoNotUse.class, (Object object0, Object object1) -> this.encode(((ProtoEncoderDoNotUse)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(ClientMetrics.class, ClientMetricsEncoder.INSTANCE);
        encoderConfig0.registerEncoder(TimeWindow.class, (Object object0, Object object1) -> this.encode(((TimeWindow)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(LogSourceMetrics.class, (Object object0, Object object1) -> this.encode(((LogSourceMetrics)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(LogEventDropped.class, (Object object0, Object object1) -> this.encode(((LogEventDropped)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(GlobalMetrics.class, (Object object0, Object object1) -> this.encode(((GlobalMetrics)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(StorageMetrics.class, (Object object0, Object object1) -> this.encode(((StorageMetrics)object0), ((ObjectEncoderContext)object1)));
    }
}

