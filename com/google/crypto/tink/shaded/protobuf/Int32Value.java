package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Int32Value extends GeneratedMessageLite implements Int32ValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements Int32ValueOrBuilder {
        private Builder() {
            super(Int32Value.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Int32Value.1 int32Value$10) {
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((Int32Value)this.instance).clearValue();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Int32ValueOrBuilder
        public int getValue() {
            return ((Int32Value)this.instance).getValue();
        }

        public Builder setValue(int v) {
            this.copyOnWrite();
            ((Int32Value)this.instance).setValue(v);
            return this;
        }
    }

    private static final Int32Value DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private int value_;

    static {
        Int32Value int32Value0 = new Int32Value();
        Int32Value.DEFAULT_INSTANCE = int32Value0;
        GeneratedMessageLite.registerDefaultInstance(Int32Value.class, int32Value0);
    }

    private void clearValue() {
        this.value_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Int32Value.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Int32Value();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Int32Value.newMessageInfo(Int32Value.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004", new Object[]{"value_"});
            }
            case 4: {
                return Int32Value.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Int32Value.PARSER;
                if(parser0 == null) {
                    Class class0 = Int32Value.class;
                    synchronized(class0) {
                        Parser parser1 = Int32Value.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Int32Value.DEFAULT_INSTANCE);
                            Int32Value.PARSER = parser1;
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

    public static Int32Value getDefaultInstance() {
        return Int32Value.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Int32ValueOrBuilder
    public int getValue() {
        return this.value_;
    }

    public static Builder newBuilder() {
        return (Builder)Int32Value.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Int32Value int32Value0) {
        return (Builder)Int32Value.DEFAULT_INSTANCE.createBuilder(int32Value0);
    }

    public static Int32Value of(int v) {
        return (Int32Value)Int32Value.newBuilder().setValue(v).build();
    }

    public static Int32Value parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Int32Value)Int32Value.parseDelimitedFrom(Int32Value.DEFAULT_INSTANCE, inputStream0);
    }

    public static Int32Value parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Int32Value)Int32Value.parseDelimitedFrom(Int32Value.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Int32Value parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, byteString0);
    }

    public static Int32Value parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Int32Value parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Int32Value parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Int32Value parseFrom(InputStream inputStream0) throws IOException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, inputStream0);
    }

    public static Int32Value parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Int32Value parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Int32Value parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Int32Value parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, arr_b);
    }

    public static Int32Value parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Int32Value)GeneratedMessageLite.parseFrom(Int32Value.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Int32Value.DEFAULT_INSTANCE.getParserForType();
    }

    private void setValue(int v) {
        this.value_ = v;
    }
}

