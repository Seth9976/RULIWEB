package com.google.firebase.messaging;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import java.io.IOException;

public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    static final class MessagingClientEventEncoder implements ObjectEncoder {
        private static final FieldDescriptor ANALYTICSLABEL_DESCRIPTOR;
        private static final FieldDescriptor BULKID_DESCRIPTOR;
        private static final FieldDescriptor CAMPAIGNID_DESCRIPTOR;
        private static final FieldDescriptor COLLAPSEKEY_DESCRIPTOR;
        private static final FieldDescriptor COMPOSERLABEL_DESCRIPTOR;
        private static final FieldDescriptor EVENT_DESCRIPTOR;
        static final MessagingClientEventEncoder INSTANCE;
        private static final FieldDescriptor INSTANCEID_DESCRIPTOR;
        private static final FieldDescriptor MESSAGEID_DESCRIPTOR;
        private static final FieldDescriptor MESSAGETYPE_DESCRIPTOR;
        private static final FieldDescriptor PACKAGENAME_DESCRIPTOR;
        private static final FieldDescriptor PRIORITY_DESCRIPTOR;
        private static final FieldDescriptor PROJECTNUMBER_DESCRIPTOR;
        private static final FieldDescriptor SDKPLATFORM_DESCRIPTOR;
        private static final FieldDescriptor TOPIC_DESCRIPTOR;
        private static final FieldDescriptor TTL_DESCRIPTOR;

        static {
            MessagingClientEventEncoder.INSTANCE = new MessagingClientEventEncoder();
            MessagingClientEventEncoder.PROJECTNUMBER_DESCRIPTOR = FieldDescriptor.builder("projectNumber").withProperty(AtProtobuf.builder().tag(1).build()).build();
            MessagingClientEventEncoder.MESSAGEID_DESCRIPTOR = FieldDescriptor.builder("messageId").withProperty(AtProtobuf.builder().tag(2).build()).build();
            MessagingClientEventEncoder.INSTANCEID_DESCRIPTOR = FieldDescriptor.builder("instanceId").withProperty(AtProtobuf.builder().tag(3).build()).build();
            MessagingClientEventEncoder.MESSAGETYPE_DESCRIPTOR = FieldDescriptor.builder("messageType").withProperty(AtProtobuf.builder().tag(4).build()).build();
            MessagingClientEventEncoder.SDKPLATFORM_DESCRIPTOR = FieldDescriptor.builder("sdkPlatform").withProperty(AtProtobuf.builder().tag(5).build()).build();
            MessagingClientEventEncoder.PACKAGENAME_DESCRIPTOR = FieldDescriptor.builder("packageName").withProperty(AtProtobuf.builder().tag(6).build()).build();
            MessagingClientEventEncoder.COLLAPSEKEY_DESCRIPTOR = FieldDescriptor.builder("collapseKey").withProperty(AtProtobuf.builder().tag(7).build()).build();
            MessagingClientEventEncoder.PRIORITY_DESCRIPTOR = FieldDescriptor.builder("priority").withProperty(AtProtobuf.builder().tag(8).build()).build();
            MessagingClientEventEncoder.TTL_DESCRIPTOR = FieldDescriptor.builder("ttl").withProperty(AtProtobuf.builder().tag(9).build()).build();
            MessagingClientEventEncoder.TOPIC_DESCRIPTOR = FieldDescriptor.builder("topic").withProperty(AtProtobuf.builder().tag(10).build()).build();
            MessagingClientEventEncoder.BULKID_DESCRIPTOR = FieldDescriptor.builder("bulkId").withProperty(AtProtobuf.builder().tag(11).build()).build();
            MessagingClientEventEncoder.EVENT_DESCRIPTOR = FieldDescriptor.builder("event").withProperty(AtProtobuf.builder().tag(12).build()).build();
            MessagingClientEventEncoder.ANALYTICSLABEL_DESCRIPTOR = FieldDescriptor.builder("analyticsLabel").withProperty(AtProtobuf.builder().tag(13).build()).build();
            MessagingClientEventEncoder.CAMPAIGNID_DESCRIPTOR = FieldDescriptor.builder("campaignId").withProperty(AtProtobuf.builder().tag(14).build()).build();
            MessagingClientEventEncoder.COMPOSERLABEL_DESCRIPTOR = FieldDescriptor.builder("composerLabel").withProperty(AtProtobuf.builder().tag(15).build()).build();
        }

        public void encode(MessagingClientEvent messagingClientEvent0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            objectEncoderContext0.add(MessagingClientEventEncoder.PROJECTNUMBER_DESCRIPTOR, messagingClientEvent0.getProjectNumber());
            objectEncoderContext0.add(MessagingClientEventEncoder.MESSAGEID_DESCRIPTOR, messagingClientEvent0.getMessageId());
            objectEncoderContext0.add(MessagingClientEventEncoder.INSTANCEID_DESCRIPTOR, messagingClientEvent0.getInstanceId());
            objectEncoderContext0.add(MessagingClientEventEncoder.MESSAGETYPE_DESCRIPTOR, messagingClientEvent0.getMessageType());
            objectEncoderContext0.add(MessagingClientEventEncoder.SDKPLATFORM_DESCRIPTOR, messagingClientEvent0.getSdkPlatform());
            objectEncoderContext0.add(MessagingClientEventEncoder.PACKAGENAME_DESCRIPTOR, messagingClientEvent0.getPackageName());
            objectEncoderContext0.add(MessagingClientEventEncoder.COLLAPSEKEY_DESCRIPTOR, messagingClientEvent0.getCollapseKey());
            objectEncoderContext0.add(MessagingClientEventEncoder.PRIORITY_DESCRIPTOR, messagingClientEvent0.getPriority());
            objectEncoderContext0.add(MessagingClientEventEncoder.TTL_DESCRIPTOR, messagingClientEvent0.getTtl());
            objectEncoderContext0.add(MessagingClientEventEncoder.TOPIC_DESCRIPTOR, messagingClientEvent0.getTopic());
            objectEncoderContext0.add(MessagingClientEventEncoder.BULKID_DESCRIPTOR, messagingClientEvent0.getBulkId());
            objectEncoderContext0.add(MessagingClientEventEncoder.EVENT_DESCRIPTOR, messagingClientEvent0.getEvent());
            objectEncoderContext0.add(MessagingClientEventEncoder.ANALYTICSLABEL_DESCRIPTOR, messagingClientEvent0.getAnalyticsLabel());
            objectEncoderContext0.add(MessagingClientEventEncoder.CAMPAIGNID_DESCRIPTOR, messagingClientEvent0.getCampaignId());
            objectEncoderContext0.add(MessagingClientEventEncoder.COMPOSERLABEL_DESCRIPTOR, messagingClientEvent0.getComposerLabel());
        }

        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException {
            this.encode(((MessagingClientEvent)object0), ((ObjectEncoderContext)object1));
        }
    }

    static final class MessagingClientEventExtensionEncoder implements ObjectEncoder {
        static final MessagingClientEventExtensionEncoder INSTANCE;
        private static final FieldDescriptor MESSAGINGCLIENTEVENT_DESCRIPTOR;

        static {
            MessagingClientEventExtensionEncoder.INSTANCE = new MessagingClientEventExtensionEncoder();
            MessagingClientEventExtensionEncoder.MESSAGINGCLIENTEVENT_DESCRIPTOR = FieldDescriptor.builder("messagingClientEvent").withProperty(AtProtobuf.builder().tag(1).build()).build();
        }

        public void encode(MessagingClientEventExtension messagingClientEventExtension0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            MessagingClientEvent messagingClientEvent0 = messagingClientEventExtension0.getMessagingClientEventInternal();
            objectEncoderContext0.add(MessagingClientEventExtensionEncoder.MESSAGINGCLIENTEVENT_DESCRIPTOR, messagingClientEvent0);
        }

        // 检测为 Lambda 实现
        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException [...]
    }

    static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder {
        static final ProtoEncoderDoNotUseEncoder INSTANCE;
        private static final FieldDescriptor MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR;

        static {
            ProtoEncoderDoNotUseEncoder.INSTANCE = new ProtoEncoderDoNotUseEncoder();
            ProtoEncoderDoNotUseEncoder.MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR = FieldDescriptor.of("messagingClientEventExtension");
        }

        public void encode(ProtoEncoderDoNotUse protoEncoderDoNotUse0, ObjectEncoderContext objectEncoderContext0) throws IOException {
            MessagingClientEventExtension messagingClientEventExtension0 = protoEncoderDoNotUse0.getMessagingClientEventExtension();
            objectEncoderContext0.add(ProtoEncoderDoNotUseEncoder.MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR, messagingClientEventExtension0);
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
        encoderConfig0.registerEncoder(MessagingClientEventExtension.class, (Object object0, Object object1) -> this.encode(((MessagingClientEventExtension)object0), ((ObjectEncoderContext)object1)));
        encoderConfig0.registerEncoder(MessagingClientEvent.class, MessagingClientEventEncoder.INSTANCE);
    }
}

