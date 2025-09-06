package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
final class ManifestSchemaFactory implements SchemaFactory {
    static class CompositeMessageInfoFactory implements MessageInfoFactory {
        private MessageInfoFactory[] factories;

        CompositeMessageInfoFactory(MessageInfoFactory[] arr_messageInfoFactory) {
            this.factories = arr_messageInfoFactory;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
        public boolean isSupported(Class class0) {
            MessageInfoFactory[] arr_messageInfoFactory = this.factories;
            for(int v = 0; v < arr_messageInfoFactory.length; ++v) {
                if(arr_messageInfoFactory[v].isSupported(class0)) {
                    return true;
                }
            }
            return false;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class class0) {
            MessageInfoFactory[] arr_messageInfoFactory = this.factories;
            for(int v = 0; v < arr_messageInfoFactory.length; ++v) {
                MessageInfoFactory messageInfoFactory0 = arr_messageInfoFactory[v];
                if(messageInfoFactory0.isSupported(class0)) {
                    return messageInfoFactory0.messageInfoFor(class0);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: " + class0.getName());
        }
    }

    private static final MessageInfoFactory EMPTY_FACTORY;
    private final MessageInfoFactory messageInfoFactory;

    static {
        ManifestSchemaFactory.EMPTY_FACTORY = new MessageInfoFactory() {
            @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
            public boolean isSupported(Class clazz) {
                return false;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
            public MessageInfo messageInfoFor(Class class0) {
                throw new IllegalStateException("This should never be called.");
            }
        };
    }

    public ManifestSchemaFactory() {
        this(ManifestSchemaFactory.getDefaultMessageInfoFactory());
    }

    private ManifestSchemaFactory(MessageInfoFactory messageInfoFactory0) {
        this.messageInfoFactory = (MessageInfoFactory)Internal.checkNotNull(messageInfoFactory0, "messageInfoFactory");
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.SchemaFactory
    public Schema createSchema(Class class0) {
        SchemaUtil.requireGeneratedMessage(class0);
        MessageInfo messageInfo0 = this.messageInfoFactory.messageInfoFor(class0);
        if(messageInfo0.isMessageSetWireFormat()) {
            return GeneratedMessageLite.class.isAssignableFrom(class0) ? MessageSetSchema.newSchema(SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.lite(), messageInfo0.getDefaultInstance()) : MessageSetSchema.newSchema(SchemaUtil.proto2UnknownFieldSetSchema(), ExtensionSchemas.full(), messageInfo0.getDefaultInstance());
        }
        return ManifestSchemaFactory.newSchema(class0, messageInfo0);
    }

    private static MessageInfoFactory getDefaultMessageInfoFactory() {
        return new CompositeMessageInfoFactory(new MessageInfoFactory[]{GeneratedMessageInfoFactory.getInstance(), ManifestSchemaFactory.getDescriptorMessageInfoFactory()});
    }

    private static MessageInfoFactory getDescriptorMessageInfoFactory() {
        try {
            return (MessageInfoFactory)Class.forName("com.google.crypto.tink.shaded.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        }
        catch(Exception unused_ex) {
            return ManifestSchemaFactory.EMPTY_FACTORY;
        }
    }

    private static boolean isProto2(MessageInfo messageInfo0) {
        return messageInfo0.getSyntax() == ProtoSyntax.PROTO2;
    }

    private static Schema newSchema(Class class0, MessageInfo messageInfo0) {
        if(GeneratedMessageLite.class.isAssignableFrom(class0)) {
            return ManifestSchemaFactory.isProto2(messageInfo0) ? MessageSchema.newSchema(class0, messageInfo0, NewInstanceSchemas.lite(), ListFieldSchema.lite(), SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.lite(), MapFieldSchemas.lite()) : MessageSchema.newSchema(class0, messageInfo0, NewInstanceSchemas.lite(), ListFieldSchema.lite(), SchemaUtil.unknownFieldSetLiteSchema(), null, MapFieldSchemas.lite());
        }
        return ManifestSchemaFactory.isProto2(messageInfo0) ? MessageSchema.newSchema(class0, messageInfo0, NewInstanceSchemas.full(), ListFieldSchema.full(), SchemaUtil.proto2UnknownFieldSetSchema(), ExtensionSchemas.full(), MapFieldSchemas.full()) : MessageSchema.newSchema(class0, messageInfo0, NewInstanceSchemas.full(), ListFieldSchema.full(), SchemaUtil.proto3UnknownFieldSetSchema(), null, MapFieldSchemas.full());
    }
}

