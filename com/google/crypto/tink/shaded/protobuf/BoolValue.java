package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BoolValue extends GeneratedMessageLite implements BoolValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements BoolValueOrBuilder {
        private Builder() {
            super(BoolValue.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.BoolValue.1 boolValue$10) {
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((BoolValue)this.instance).clearValue();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BoolValueOrBuilder
        public boolean getValue() {
            return ((BoolValue)this.instance).getValue();
        }

        public Builder setValue(boolean z) {
            this.copyOnWrite();
            ((BoolValue)this.instance).setValue(z);
            return this;
        }
    }

    private static final BoolValue DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private boolean value_;

    static {
        BoolValue boolValue0 = new BoolValue();
        BoolValue.DEFAULT_INSTANCE = boolValue0;
        GeneratedMessageLite.registerDefaultInstance(BoolValue.class, boolValue0);
    }

    private void clearValue() {
        this.value_ = false;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.BoolValue.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new BoolValue();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return BoolValue.newMessageInfo(BoolValue.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007", new Object[]{"value_"});
            }
            case 4: {
                return BoolValue.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = BoolValue.PARSER;
                if(parser0 == null) {
                    Class class0 = BoolValue.class;
                    synchronized(class0) {
                        Parser parser1 = BoolValue.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(BoolValue.DEFAULT_INSTANCE);
                            BoolValue.PARSER = parser1;
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

    public static BoolValue getDefaultInstance() {
        return BoolValue.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.BoolValueOrBuilder
    public boolean getValue() {
        return this.value_;
    }

    public static Builder newBuilder() {
        return (Builder)BoolValue.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BoolValue boolValue0) {
        return (Builder)BoolValue.DEFAULT_INSTANCE.createBuilder(boolValue0);
    }

    public static BoolValue of(boolean z) {
        return (BoolValue)BoolValue.newBuilder().setValue(z).build();
    }

    public static BoolValue parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (BoolValue)BoolValue.parseDelimitedFrom(BoolValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static BoolValue parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (BoolValue)BoolValue.parseDelimitedFrom(BoolValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static BoolValue parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, byteString0);
    }

    public static BoolValue parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static BoolValue parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static BoolValue parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static BoolValue parseFrom(InputStream inputStream0) throws IOException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static BoolValue parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static BoolValue parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static BoolValue parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static BoolValue parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, arr_b);
    }

    public static BoolValue parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (BoolValue)GeneratedMessageLite.parseFrom(BoolValue.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return BoolValue.DEFAULT_INSTANCE.getParserForType();
    }

    private void setValue(boolean z) {
        this.value_ = z;
    }
}

