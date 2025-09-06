package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BytesValue extends GeneratedMessageLite implements BytesValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements BytesValueOrBuilder {
        private Builder() {
            super(BytesValue.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.BytesValue.1 bytesValue$10) {
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((BytesValue)this.instance).clearValue();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BytesValueOrBuilder
        public ByteString getValue() {
            return ((BytesValue)this.instance).getValue();
        }

        public Builder setValue(ByteString byteString0) {
            this.copyOnWrite();
            ((BytesValue)this.instance).setValue(byteString0);
            return this;
        }
    }

    private static final BytesValue DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private ByteString value_;

    static {
        BytesValue bytesValue0 = new BytesValue();
        BytesValue.DEFAULT_INSTANCE = bytesValue0;
        GeneratedMessageLite.registerDefaultInstance(BytesValue.class, bytesValue0);
    }

    private BytesValue() {
        this.value_ = ByteString.EMPTY;
    }

    private void clearValue() {
        this.value_ = BytesValue.getDefaultInstance().getValue();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.BytesValue.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new BytesValue();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return BytesValue.newMessageInfo(BytesValue.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"value_"});
            }
            case 4: {
                return BytesValue.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = BytesValue.PARSER;
                if(parser0 == null) {
                    Class class0 = BytesValue.class;
                    synchronized(class0) {
                        Parser parser1 = BytesValue.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(BytesValue.DEFAULT_INSTANCE);
                            BytesValue.PARSER = parser1;
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

    public static BytesValue getDefaultInstance() {
        return BytesValue.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.BytesValueOrBuilder
    public ByteString getValue() {
        return this.value_;
    }

    public static Builder newBuilder() {
        return (Builder)BytesValue.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BytesValue bytesValue0) {
        return (Builder)BytesValue.DEFAULT_INSTANCE.createBuilder(bytesValue0);
    }

    public static BytesValue of(ByteString byteString0) {
        return (BytesValue)BytesValue.newBuilder().setValue(byteString0).build();
    }

    public static BytesValue parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (BytesValue)BytesValue.parseDelimitedFrom(BytesValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static BytesValue parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (BytesValue)BytesValue.parseDelimitedFrom(BytesValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static BytesValue parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, byteString0);
    }

    public static BytesValue parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static BytesValue parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static BytesValue parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static BytesValue parseFrom(InputStream inputStream0) throws IOException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static BytesValue parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static BytesValue parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static BytesValue parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static BytesValue parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, arr_b);
    }

    public static BytesValue parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (BytesValue)GeneratedMessageLite.parseFrom(BytesValue.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return BytesValue.DEFAULT_INSTANCE.getParserForType();
    }

    private void setValue(ByteString byteString0) {
        byteString0.getClass();
        this.value_ = byteString0;
    }
}

