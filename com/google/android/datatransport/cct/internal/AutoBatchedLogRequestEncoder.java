package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;
import java.util.List;

public final class AutoBatchedLogRequestEncoder implements Configurator {
    static final class AndroidClientInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor APPLICATIONBUILD_DESCRIPTOR;
        private static final FieldDescriptor COUNTRY_DESCRIPTOR;
        private static final FieldDescriptor DEVICE_DESCRIPTOR;
        private static final FieldDescriptor FINGERPRINT_DESCRIPTOR;
        private static final FieldDescriptor HARDWARE_DESCRIPTOR;
        static final AndroidClientInfoEncoder INSTANCE;
        private static final FieldDescriptor LOCALE_DESCRIPTOR;
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR;
        private static final FieldDescriptor MCCMNC_DESCRIPTOR;
        private static final FieldDescriptor MODEL_DESCRIPTOR;
        private static final FieldDescriptor OSBUILD_DESCRIPTOR;
        private static final FieldDescriptor PRODUCT_DESCRIPTOR;
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR;

        static {
            AndroidClientInfoEncoder.INSTANCE = new AndroidClientInfoEncoder();
            AndroidClientInfoEncoder.SDKVERSION_DESCRIPTOR = FieldDescriptor.of("sdkVersion");
            AndroidClientInfoEncoder.MODEL_DESCRIPTOR = FieldDescriptor.of("model");
            AndroidClientInfoEncoder.HARDWARE_DESCRIPTOR = FieldDescriptor.of("hardware");
            AndroidClientInfoEncoder.DEVICE_DESCRIPTOR = FieldDescriptor.of("device");
            AndroidClientInfoEncoder.PRODUCT_DESCRIPTOR = FieldDescriptor.of("product");
            AndroidClientInfoEncoder.OSBUILD_DESCRIPTOR = FieldDescriptor.of("osBuild");
            AndroidClientInfoEncoder.MANUFACTURER_DESCRIPTOR = FieldDescriptor.of("manufacturer");
            AndroidClientInfoEncoder.FINGERPRINT_DESCRIPTOR = FieldDescriptor.of("fingerprint");
            AndroidClientInfoEncoder.LOCALE_DESCRIPTOR = FieldDescriptor.of("locale");
            AndroidClientInfoEncoder.COUNTRY_DESCRIPTOR = FieldDescriptor.of("country");
            AndroidClientInfoEncoder.MCCMNC_DESCRIPTOR = FieldDescriptor.of("mccMnc");
            AndroidClientInfoEncoder.APPLICATIONBUILD_DESCRIPTOR = FieldDescriptor.of("applicationBuild");
        }

        public void encode(AndroidClientInfo androidClientInfo0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            Integer integer0 = androidClientInfo0.getSdkVersion();
            objectEncoderContext0.add(AndroidClientInfoEncoder.SDKVERSION_DESCRIPTOR, integer0);
            String s = androidClientInfo0.getModel();
            objectEncoderContext0.add(AndroidClientInfoEncoder.MODEL_DESCRIPTOR, s);
            String s1 = androidClientInfo0.getHardware();
            objectEncoderContext0.add(AndroidClientInfoEncoder.HARDWARE_DESCRIPTOR, s1);
            String s2 = androidClientInfo0.getDevice();
            objectEncoderContext0.add(AndroidClientInfoEncoder.DEVICE_DESCRIPTOR, s2);
            String s3 = androidClientInfo0.getProduct();
            objectEncoderContext0.add(AndroidClientInfoEncoder.PRODUCT_DESCRIPTOR, s3);
            String s4 = androidClientInfo0.getOsBuild();
            objectEncoderContext0.add(AndroidClientInfoEncoder.OSBUILD_DESCRIPTOR, s4);
            String s5 = androidClientInfo0.getManufacturer();
            objectEncoderContext0.add(AndroidClientInfoEncoder.MANUFACTURER_DESCRIPTOR, s5);
            String s6 = androidClientInfo0.getFingerprint();
            objectEncoderContext0.add(AndroidClientInfoEncoder.FINGERPRINT_DESCRIPTOR, s6);
            String s7 = androidClientInfo0.getLocale();
            objectEncoderContext0.add(AndroidClientInfoEncoder.LOCALE_DESCRIPTOR, s7);
            String s8 = androidClientInfo0.getCountry();
            objectEncoderContext0.add(AndroidClientInfoEncoder.COUNTRY_DESCRIPTOR, s8);
            String s9 = androidClientInfo0.getMccMnc();
            objectEncoderContext0.add(AndroidClientInfoEncoder.MCCMNC_DESCRIPTOR, s9);
            String s10 = androidClientInfo0.getApplicationBuild();
            objectEncoderContext0.add(AndroidClientInfoEncoder.APPLICATIONBUILD_DESCRIPTOR, s10);
        }

        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException {
            this.encode(((AndroidClientInfo)object0), ((ObjectEncoderContext)object1));
        }
    }

    static final class BatchedLogRequestEncoder implements ObjectEncoder {
        static final BatchedLogRequestEncoder INSTANCE;
        private static final FieldDescriptor LOGREQUEST_DESCRIPTOR;

        static {
            BatchedLogRequestEncoder.INSTANCE = new BatchedLogRequestEncoder();
            BatchedLogRequestEncoder.LOGREQUEST_DESCRIPTOR = FieldDescriptor.of("logRequest");
        }

        public void encode(BatchedLogRequest batchedLogRequest0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            List list0 = batchedLogRequest0.getLogRequests();
            objectEncoderContext0.add(BatchedLogRequestEncoder.LOGREQUEST_DESCRIPTOR, list0);
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class ClientInfoEncoder implements ObjectEncoder {
        private static final FieldDescriptor ANDROIDCLIENTINFO_DESCRIPTOR;
        private static final FieldDescriptor CLIENTTYPE_DESCRIPTOR;
        static final ClientInfoEncoder INSTANCE;

        static {
            ClientInfoEncoder.INSTANCE = new ClientInfoEncoder();
            ClientInfoEncoder.CLIENTTYPE_DESCRIPTOR = FieldDescriptor.of("clientType");
            ClientInfoEncoder.ANDROIDCLIENTINFO_DESCRIPTOR = FieldDescriptor.of("androidClientInfo");
        }

        public void encode(ClientInfo clientInfo0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            ClientType clientInfo$ClientType0 = clientInfo0.getClientType();
            objectEncoderContext0.add(ClientInfoEncoder.CLIENTTYPE_DESCRIPTOR, clientInfo$ClientType0);
            AndroidClientInfo androidClientInfo0 = clientInfo0.getAndroidClientInfo();
            objectEncoderContext0.add(ClientInfoEncoder.ANDROIDCLIENTINFO_DESCRIPTOR, androidClientInfo0);
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class LogEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTCODE_DESCRIPTOR;
        private static final FieldDescriptor EVENTTIMEMS_DESCRIPTOR;
        private static final FieldDescriptor EVENTUPTIMEMS_DESCRIPTOR;
        static final LogEventEncoder INSTANCE;
        private static final FieldDescriptor NETWORKCONNECTIONINFO_DESCRIPTOR;
        private static final FieldDescriptor SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR;
        private static final FieldDescriptor SOURCEEXTENSION_DESCRIPTOR;
        private static final FieldDescriptor TIMEZONEOFFSETSECONDS_DESCRIPTOR;

        static {
            LogEventEncoder.INSTANCE = new LogEventEncoder();
            LogEventEncoder.EVENTTIMEMS_DESCRIPTOR = FieldDescriptor.of("eventTimeMs");
            LogEventEncoder.EVENTCODE_DESCRIPTOR = FieldDescriptor.of("eventCode");
            LogEventEncoder.EVENTUPTIMEMS_DESCRIPTOR = FieldDescriptor.of("eventUptimeMs");
            LogEventEncoder.SOURCEEXTENSION_DESCRIPTOR = FieldDescriptor.of("sourceExtension");
            LogEventEncoder.SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR = FieldDescriptor.of("sourceExtensionJsonProto3");
            LogEventEncoder.TIMEZONEOFFSETSECONDS_DESCRIPTOR = FieldDescriptor.of("timezoneOffsetSeconds");
            LogEventEncoder.NETWORKCONNECTIONINFO_DESCRIPTOR = FieldDescriptor.of("networkConnectionInfo");
        }

        public void encode(LogEvent logEvent0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            long v = logEvent0.getEventTimeMs();
            objectEncoderContext0.add(LogEventEncoder.EVENTTIMEMS_DESCRIPTOR, v);
            Integer integer0 = logEvent0.getEventCode();
            objectEncoderContext0.add(LogEventEncoder.EVENTCODE_DESCRIPTOR, integer0);
            long v1 = logEvent0.getEventUptimeMs();
            objectEncoderContext0.add(LogEventEncoder.EVENTUPTIMEMS_DESCRIPTOR, v1);
            byte[] arr_b = logEvent0.getSourceExtension();
            objectEncoderContext0.add(LogEventEncoder.SOURCEEXTENSION_DESCRIPTOR, arr_b);
            String s = logEvent0.getSourceExtensionJsonProto3();
            objectEncoderContext0.add(LogEventEncoder.SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR, s);
            long v2 = logEvent0.getTimezoneOffsetSeconds();
            objectEncoderContext0.add(LogEventEncoder.TIMEZONEOFFSETSECONDS_DESCRIPTOR, v2);
            NetworkConnectionInfo networkConnectionInfo0 = logEvent0.getNetworkConnectionInfo();
            objectEncoderContext0.add(LogEventEncoder.NETWORKCONNECTIONINFO_DESCRIPTOR, networkConnectionInfo0);
        }

        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException {
            this.encode(((LogEvent)object0), ((ObjectEncoderContext)object1));
        }
    }

    static final class LogRequestEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLIENTINFO_DESCRIPTOR;
        static final LogRequestEncoder INSTANCE;
        private static final FieldDescriptor LOGEVENT_DESCRIPTOR;
        private static final FieldDescriptor LOGSOURCENAME_DESCRIPTOR;
        private static final FieldDescriptor LOGSOURCE_DESCRIPTOR;
        private static final FieldDescriptor QOSTIER_DESCRIPTOR;
        private static final FieldDescriptor REQUESTTIMEMS_DESCRIPTOR;
        private static final FieldDescriptor REQUESTUPTIMEMS_DESCRIPTOR;

        static {
            LogRequestEncoder.INSTANCE = new LogRequestEncoder();
            LogRequestEncoder.REQUESTTIMEMS_DESCRIPTOR = FieldDescriptor.of("requestTimeMs");
            LogRequestEncoder.REQUESTUPTIMEMS_DESCRIPTOR = FieldDescriptor.of("requestUptimeMs");
            LogRequestEncoder.CLIENTINFO_DESCRIPTOR = FieldDescriptor.of("clientInfo");
            LogRequestEncoder.LOGSOURCE_DESCRIPTOR = FieldDescriptor.of("logSource");
            LogRequestEncoder.LOGSOURCENAME_DESCRIPTOR = FieldDescriptor.of("logSourceName");
            LogRequestEncoder.LOGEVENT_DESCRIPTOR = FieldDescriptor.of("logEvent");
            LogRequestEncoder.QOSTIER_DESCRIPTOR = FieldDescriptor.of("qosTier");
        }

        public void encode(LogRequest logRequest0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            long v = logRequest0.getRequestTimeMs();
            objectEncoderContext0.add(LogRequestEncoder.REQUESTTIMEMS_DESCRIPTOR, v);
            long v1 = logRequest0.getRequestUptimeMs();
            objectEncoderContext0.add(LogRequestEncoder.REQUESTUPTIMEMS_DESCRIPTOR, v1);
            ClientInfo clientInfo0 = logRequest0.getClientInfo();
            objectEncoderContext0.add(LogRequestEncoder.CLIENTINFO_DESCRIPTOR, clientInfo0);
            Integer integer0 = logRequest0.getLogSource();
            objectEncoderContext0.add(LogRequestEncoder.LOGSOURCE_DESCRIPTOR, integer0);
            String s = logRequest0.getLogSourceName();
            objectEncoderContext0.add(LogRequestEncoder.LOGSOURCENAME_DESCRIPTOR, s);
            List list0 = logRequest0.getLogEvents();
            objectEncoderContext0.add(LogRequestEncoder.LOGEVENT_DESCRIPTOR, list0);
            QosTier qosTier0 = logRequest0.getQosTier();
            objectEncoderContext0.add(LogRequestEncoder.QOSTIER_DESCRIPTOR, qosTier0);
        }

        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException {
            this.encode(((LogRequest)object0), ((ObjectEncoderContext)object1));
        }
    }

    static final class NetworkConnectionInfoEncoder implements ObjectEncoder {
        static final NetworkConnectionInfoEncoder INSTANCE;
        private static final FieldDescriptor MOBILESUBTYPE_DESCRIPTOR;
        private static final FieldDescriptor NETWORKTYPE_DESCRIPTOR;

        static {
            NetworkConnectionInfoEncoder.INSTANCE = new NetworkConnectionInfoEncoder();
            NetworkConnectionInfoEncoder.NETWORKTYPE_DESCRIPTOR = FieldDescriptor.of("networkType");
            NetworkConnectionInfoEncoder.MOBILESUBTYPE_DESCRIPTOR = FieldDescriptor.of("mobileSubtype");
        }

        public void encode(NetworkConnectionInfo networkConnectionInfo0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            NetworkType networkConnectionInfo$NetworkType0 = networkConnectionInfo0.getNetworkType();
            objectEncoderContext0.add(NetworkConnectionInfoEncoder.NETWORKTYPE_DESCRIPTOR, networkConnectionInfo$NetworkType0);
            MobileSubtype networkConnectionInfo$MobileSubtype0 = networkConnectionInfo0.getMobileSubtype();
            objectEncoderContext0.add(NetworkConnectionInfoEncoder.MOBILESUBTYPE_DESCRIPTOR, networkConnectionInfo$MobileSubtype0);
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG;

    static {
        AutoBatchedLogRequestEncoder.CONFIG = new AutoBatchedLogRequestEncoder();
    }

    @Override  // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig encoderConfig0) {
        encoderConfig0.registerEncoder(BatchedLogRequest.class, (Object object0, Object object1) -> this.encode(((BatchedLogRequest)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(AutoValue_BatchedLogRequest.class, (Object object0, Object object1) -> this.encode(((BatchedLogRequest)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(LogRequest.class, LogRequestEncoder.INSTANCE);
        encoderConfig0.registerEncoder(AutoValue_LogRequest.class, LogRequestEncoder.INSTANCE);
        encoderConfig0.registerEncoder(ClientInfo.class, (Object object0, Object object1) -> this.encode(((ClientInfo)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(AutoValue_ClientInfo.class, (Object object0, Object object1) -> this.encode(((ClientInfo)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(AndroidClientInfo.class, AndroidClientInfoEncoder.INSTANCE);
        encoderConfig0.registerEncoder(AutoValue_AndroidClientInfo.class, AndroidClientInfoEncoder.INSTANCE);
        encoderConfig0.registerEncoder(LogEvent.class, LogEventEncoder.INSTANCE);
        encoderConfig0.registerEncoder(AutoValue_LogEvent.class, LogEventEncoder.INSTANCE);
        encoderConfig0.registerEncoder(NetworkConnectionInfo.class, (Object object0, Object object1) -> this.encode(((NetworkConnectionInfo)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(AutoValue_NetworkConnectionInfo.class, (Object object0, Object object1) -> this.encode(((NetworkConnectionInfo)object0), ((ObjectEncoderContext)object1)));
    }
}

