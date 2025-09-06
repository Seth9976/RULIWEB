package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Option extends GeneratedMessageLite implements OptionOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements OptionOrBuilder {
        private Builder() {
            super(Option.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Option.1 option$10) {
        }

        public Builder clearName() {
            this.copyOnWrite();
            ((Option)this.instance).clearName();
            return this;
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((Option)this.instance).clearValue();
            return this;
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
        public String getName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
        public ByteString getNameBytes() {
            return ((Option)this.instance).getNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
        public Any getValue() {
            return ((Option)this.instance).getValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
        public boolean hasValue() {
            return ((Option)this.instance).hasValue();
        }

        public Builder mergeValue(Any any0) {
            this.copyOnWrite();
            ((Option)this.instance).mergeValue(any0);
            return this;
        }

        public Builder setName(String s) {
            this.copyOnWrite();
            ((Option)this.instance).setName(s);
            return this;
        }

        public Builder setNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Option)this.instance).setNameBytes(byteString0);
            return this;
        }

        public Builder setValue(com.google.crypto.tink.shaded.protobuf.Any.Builder any$Builder0) {
            this.copyOnWrite();
            ((Option)this.instance).setValue(((Any)any$Builder0.build()));
            return this;
        }

        public Builder setValue(Any any0) {
            this.copyOnWrite();
            ((Option)this.instance).setValue(any0);
            return this;
        }
    }

    private static final Option DEFAULT_INSTANCE = null;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 2;
    private String name_;
    private Any value_;

    static {
        Option option0 = new Option();
        Option.DEFAULT_INSTANCE = option0;
        GeneratedMessageLite.registerDefaultInstance(Option.class, option0);
    }

    private Option() {
        this.name_ = "";
    }

    private void clearName() {
        this.name_ = "";
    }

    private void clearValue() {
        this.value_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Option.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Option();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Option.newMessageInfo(Option.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "value_"});
            }
            case 4: {
                return Option.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Option.PARSER;
                if(parser0 == null) {
                    Class class0 = Option.class;
                    synchronized(class0) {
                        Parser parser1 = Option.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Option.DEFAULT_INSTANCE);
                            Option.PARSER = parser1;
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

    public static Option getDefaultInstance() {
        return Option.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
    public String getName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
    public Any getValue() {
        return this.value_ == null ? Any.getDefaultInstance() : this.value_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.OptionOrBuilder
    public boolean hasValue() {
        return this.value_ != null;
    }

    private void mergeValue(Any any0) {
        any0.getClass();
        if(this.value_ != null && this.value_ != Any.getDefaultInstance()) {
            this.value_ = (Any)((com.google.crypto.tink.shaded.protobuf.Any.Builder)Any.newBuilder(this.value_).mergeFrom(any0)).buildPartial();
            return;
        }
        this.value_ = any0;
    }

    public static Builder newBuilder() {
        return (Builder)Option.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Option option0) {
        return (Builder)Option.DEFAULT_INSTANCE.createBuilder(option0);
    }

    public static Option parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Option)Option.parseDelimitedFrom(Option.DEFAULT_INSTANCE, inputStream0);
    }

    public static Option parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Option)Option.parseDelimitedFrom(Option.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Option parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, byteString0);
    }

    public static Option parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Option parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Option parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Option parseFrom(InputStream inputStream0) throws IOException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, inputStream0);
    }

    public static Option parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Option parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Option parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Option parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, arr_b);
    }

    public static Option parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Option)GeneratedMessageLite.parseFrom(Option.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Option.DEFAULT_INSTANCE.getParserForType();
    }

    private void setName(String s) {
        s.getClass();
        this.name_ = s;
    }

    private void setNameBytes(ByteString byteString0) {
        Option.checkByteStringIsUtf8(byteString0);
        this.name_ = byteString0.toStringUtf8();
    }

    private void setValue(Any any0) {
        any0.getClass();
        this.value_ = any0;
    }
}

