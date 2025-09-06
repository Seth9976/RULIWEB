package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class UInt32Value extends GeneratedMessageLite implements UInt32ValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements UInt32ValueOrBuilder {
        private Builder() {
            super(UInt32Value.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.UInt32Value.1 uInt32Value$10) {
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((UInt32Value)this.instance).clearValue();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UInt32ValueOrBuilder
        public int getValue() {
            return ((UInt32Value)this.instance).getValue();
        }

        public Builder setValue(int v) {
            this.copyOnWrite();
            ((UInt32Value)this.instance).setValue(v);
            return this;
        }
    }

    private static final UInt32Value DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private int value_;

    static {
        UInt32Value uInt32Value0 = new UInt32Value();
        UInt32Value.DEFAULT_INSTANCE = uInt32Value0;
        GeneratedMessageLite.registerDefaultInstance(UInt32Value.class, uInt32Value0);
    }

    private void clearValue() {
        this.value_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.UInt32Value.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new UInt32Value();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return UInt32Value.newMessageInfo(UInt32Value.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"value_"});
            }
            case 4: {
                return UInt32Value.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = UInt32Value.PARSER;
                if(parser0 == null) {
                    Class class0 = UInt32Value.class;
                    synchronized(class0) {
                        Parser parser1 = UInt32Value.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(UInt32Value.DEFAULT_INSTANCE);
                            UInt32Value.PARSER = parser1;
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

    public static UInt32Value getDefaultInstance() {
        return UInt32Value.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.UInt32ValueOrBuilder
    public int getValue() {
        return this.value_;
    }

    public static Builder newBuilder() {
        return (Builder)UInt32Value.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(UInt32Value uInt32Value0) {
        return (Builder)UInt32Value.DEFAULT_INSTANCE.createBuilder(uInt32Value0);
    }

    public static UInt32Value of(int v) {
        return (UInt32Value)UInt32Value.newBuilder().setValue(v).build();
    }

    public static UInt32Value parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (UInt32Value)UInt32Value.parseDelimitedFrom(UInt32Value.DEFAULT_INSTANCE, inputStream0);
    }

    public static UInt32Value parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (UInt32Value)UInt32Value.parseDelimitedFrom(UInt32Value.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static UInt32Value parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, byteString0);
    }

    public static UInt32Value parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static UInt32Value parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static UInt32Value parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static UInt32Value parseFrom(InputStream inputStream0) throws IOException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, inputStream0);
    }

    public static UInt32Value parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static UInt32Value parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static UInt32Value parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static UInt32Value parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, arr_b);
    }

    public static UInt32Value parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (UInt32Value)GeneratedMessageLite.parseFrom(UInt32Value.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return UInt32Value.DEFAULT_INSTANCE.getParserForType();
    }

    private void setValue(int v) {
        this.value_ = v;
    }
}

