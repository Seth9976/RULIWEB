package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class FloatValue extends GeneratedMessageLite implements FloatValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements FloatValueOrBuilder {
        private Builder() {
            super(FloatValue.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.FloatValue.1 floatValue$10) {
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((FloatValue)this.instance).clearValue();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FloatValueOrBuilder
        public float getValue() {
            return ((FloatValue)this.instance).getValue();
        }

        public Builder setValue(float f) {
            this.copyOnWrite();
            ((FloatValue)this.instance).setValue(f);
            return this;
        }
    }

    private static final FloatValue DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private float value_;

    static {
        FloatValue floatValue0 = new FloatValue();
        FloatValue.DEFAULT_INSTANCE = floatValue0;
        GeneratedMessageLite.registerDefaultInstance(FloatValue.class, floatValue0);
    }

    private void clearValue() {
        this.value_ = 0.0f;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.FloatValue.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new FloatValue();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return FloatValue.newMessageInfo(FloatValue.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0001", new Object[]{"value_"});
            }
            case 4: {
                return FloatValue.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = FloatValue.PARSER;
                if(parser0 == null) {
                    Class class0 = FloatValue.class;
                    synchronized(class0) {
                        Parser parser1 = FloatValue.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(FloatValue.DEFAULT_INSTANCE);
                            FloatValue.PARSER = parser1;
                        }
                        return parser1;
                    }
                }
                return parser0;
            }
            case 6: {
                return (byte)1;
            }
            case 7: {
                return null;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    public static FloatValue getDefaultInstance() {
        return FloatValue.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FloatValueOrBuilder
    public float getValue() {
        return this.value_;
    }

    public static Builder newBuilder() {
        return (Builder)FloatValue.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(FloatValue floatValue0) {
        return (Builder)FloatValue.DEFAULT_INSTANCE.createBuilder(floatValue0);
    }

    public static FloatValue of(float f) {
        return (FloatValue)FloatValue.newBuilder().setValue(f).build();
    }

    public static FloatValue parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (FloatValue)FloatValue.parseDelimitedFrom(FloatValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static FloatValue parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (FloatValue)FloatValue.parseDelimitedFrom(FloatValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static FloatValue parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, byteString0);
    }

    public static FloatValue parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static FloatValue parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static FloatValue parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static FloatValue parseFrom(InputStream inputStream0) throws IOException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static FloatValue parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static FloatValue parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static FloatValue parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static FloatValue parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, arr_b);
    }

    public static FloatValue parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (FloatValue)GeneratedMessageLite.parseFrom(FloatValue.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return FloatValue.DEFAULT_INSTANCE.getParserForType();
    }

    private void setValue(float f) {
        this.value_ = f;
    }
}

