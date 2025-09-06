package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class EnumValue extends GeneratedMessageLite implements EnumValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EnumValueOrBuilder {
        private Builder() {
            super(EnumValue.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.EnumValue.1 enumValue$10) {
        }

        public Builder addAllOptions(Iterable iterable0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).addAllOptions(iterable0);
            return this;
        }

        public Builder addOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).addOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(int v, Option option0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).addOptions(v, option0);
            return this;
        }

        public Builder addOptions(com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).addOptions(((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(Option option0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).addOptions(option0);
            return this;
        }

        public Builder clearName() {
            this.copyOnWrite();
            ((EnumValue)this.instance).clearName();
            return this;
        }

        public Builder clearNumber() {
            this.copyOnWrite();
            ((EnumValue)this.instance).clearNumber();
            return this;
        }

        public Builder clearOptions() {
            this.copyOnWrite();
            ((EnumValue)this.instance).clearOptions();
            return this;
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
        public String getName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
        public ByteString getNameBytes() {
            return ((EnumValue)this.instance).getNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
        public int getNumber() {
            return ((EnumValue)this.instance).getNumber();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
        public Option getOptions(int v) {
            return ((EnumValue)this.instance).getOptions(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
        public int getOptionsCount() {
            return ((EnumValue)this.instance).getOptionsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
        public List getOptionsList() {
            return Collections.unmodifiableList(((EnumValue)this.instance).getOptionsList());
        }

        public Builder removeOptions(int v) {
            this.copyOnWrite();
            ((EnumValue)this.instance).removeOptions(v);
            return this;
        }

        public Builder setName(String s) {
            this.copyOnWrite();
            ((EnumValue)this.instance).setName(s);
            return this;
        }

        public Builder setNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).setNameBytes(byteString0);
            return this;
        }

        public Builder setNumber(int v) {
            this.copyOnWrite();
            ((EnumValue)this.instance).setNumber(v);
            return this;
        }

        public Builder setOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).setOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder setOptions(int v, Option option0) {
            this.copyOnWrite();
            ((EnumValue)this.instance).setOptions(v, option0);
            return this;
        }
    }

    private static final EnumValue DEFAULT_INSTANCE = null;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int NUMBER_FIELD_NUMBER = 2;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private static volatile Parser PARSER;
    private String name_;
    private int number_;
    private ProtobufList options_;

    static {
        EnumValue enumValue0 = new EnumValue();
        EnumValue.DEFAULT_INSTANCE = enumValue0;
        GeneratedMessageLite.registerDefaultInstance(EnumValue.class, enumValue0);
    }

    private EnumValue() {
        this.name_ = "";
        this.options_ = EnumValue.emptyProtobufList();
    }

    private void addAllOptions(Iterable iterable0) {
        this.ensureOptionsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.options_);
    }

    private void addOptions(int v, Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.add(v, option0);
    }

    private void addOptions(Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.add(option0);
    }

    private void clearName() {
        this.name_ = "";
    }

    private void clearNumber() {
        this.number_ = 0;
    }

    private void clearOptions() {
        this.options_ = EnumValue.emptyProtobufList();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.EnumValue.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EnumValue();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EnumValue.newMessageInfo(EnumValue.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u0004\u0003\u001B", new Object[]{"name_", "number_", "options_", Option.class});
            }
            case 4: {
                return EnumValue.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EnumValue.PARSER;
                if(parser0 == null) {
                    Class class0 = EnumValue.class;
                    synchronized(class0) {
                        Parser parser1 = EnumValue.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EnumValue.DEFAULT_INSTANCE);
                            EnumValue.PARSER = parser1;
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

    private void ensureOptionsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.options_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.options_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    public static EnumValue getDefaultInstance() {
        return EnumValue.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
    public String getName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
    public int getNumber() {
        return this.number_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
    public Option getOptions(int v) {
        return (Option)this.options_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.EnumValueOrBuilder
    public List getOptionsList() {
        return this.options_;
    }

    public OptionOrBuilder getOptionsOrBuilder(int v) {
        return (OptionOrBuilder)this.options_.get(v);
    }

    public List getOptionsOrBuilderList() {
        return this.options_;
    }

    public static Builder newBuilder() {
        return (Builder)EnumValue.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EnumValue enumValue0) {
        return (Builder)EnumValue.DEFAULT_INSTANCE.createBuilder(enumValue0);
    }

    public static EnumValue parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EnumValue)EnumValue.parseDelimitedFrom(EnumValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static EnumValue parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EnumValue)EnumValue.parseDelimitedFrom(EnumValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EnumValue parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, byteString0);
    }

    public static EnumValue parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EnumValue parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EnumValue parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EnumValue parseFrom(InputStream inputStream0) throws IOException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, inputStream0);
    }

    public static EnumValue parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EnumValue parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EnumValue parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EnumValue parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, arr_b);
    }

    public static EnumValue parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EnumValue)GeneratedMessageLite.parseFrom(EnumValue.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EnumValue.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeOptions(int v) {
        this.ensureOptionsIsMutable();
        this.options_.remove(v);
    }

    private void setName(String s) {
        s.getClass();
        this.name_ = s;
    }

    private void setNameBytes(ByteString byteString0) {
        EnumValue.checkByteStringIsUtf8(byteString0);
        this.name_ = byteString0.toStringUtf8();
    }

    private void setNumber(int v) {
        this.number_ = v;
    }

    private void setOptions(int v, Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.set(v, option0);
    }
}

