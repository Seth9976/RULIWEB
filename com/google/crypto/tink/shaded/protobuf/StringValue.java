package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class StringValue extends GeneratedMessageLite implements StringValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements StringValueOrBuilder {
        private Builder() {
            super(StringValue.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.StringValue.1 stringValue$10) {
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((StringValue)this.instance).clearValue();
            return this;
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.StringValueOrBuilder
        public String getValue() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.StringValueOrBuilder
        public ByteString getValueBytes() {
            return ((StringValue)this.instance).getValueBytes();
        }

        public Builder setValue(String s) {
            this.copyOnWrite();
            ((StringValue)this.instance).setValue(s);
            return this;
        }

        public Builder setValueBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((StringValue)this.instance).setValueBytes(byteString0);
            return this;
        }
    }

    private static final StringValue DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private String value_;

    static {
        StringValue stringValue0 = new StringValue();
        StringValue.DEFAULT_INSTANCE = stringValue0;
        GeneratedMessageLite.registerDefaultInstance(StringValue.class, stringValue0);
    }

    private StringValue() {
        this.value_ = "";
    }

    private void clearValue() {
        this.value_ = "";
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.StringValue.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new StringValue();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return StringValue.newMessageInfo(StringValue.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"value_"});
            }
            case 4: {
                return StringValue.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = StringValue.PARSER;
                if(parser0 == null) {
                    Class class0 = StringValue.class;
                    synchronized(class0) {
                        Parser parser1 = StringValue.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(StringValue.DEFAULT_INSTANCE);
                            StringValue.PARSER = parser1;
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

    public static StringValue getDefaultInstance() {
        return StringValue.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.StringValueOrBuilder
    public String getValue() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.StringValueOrBuilder
    public ByteString getValueBytes() {
        return ByteString.copyFromUtf8(this.value_);
    }

    public static Builder newBuilder() {
        return (Builder)StringValue.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(StringValue stringValue0) {
        return (Builder)StringValue.DEFAULT_INSTANCE.createBuilder(stringValue0);
    }

    public static StringValue of(String s) {
        return (StringValue)StringValue.newBuilder().setValue(s).build();
    }

    public static StringValue parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (StringValue)StringValue.parseDelimitedFrom(StringValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static StringValue parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (StringValue)StringValue.parseDelimitedFrom(StringValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static StringValue parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, byteString0);
    }

    public static StringValue parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static StringValue parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static StringValue parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static StringValue parseFrom(InputStream inputStream0) throws IOException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static StringValue parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static StringValue parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static StringValue parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static StringValue parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, arr_b);
    }

    public static StringValue parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (StringValue)GeneratedMessageLite.parseFrom(StringValue.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return StringValue.DEFAULT_INSTANCE.getParserForType();
    }

    private void setValue(String s) {
        s.getClass();
        this.value_ = s;
    }

    private void setValueBytes(ByteString byteString0) {
        StringValue.checkByteStringIsUtf8(byteString0);
        this.value_ = byteString0.toStringUtf8();
    }
}

