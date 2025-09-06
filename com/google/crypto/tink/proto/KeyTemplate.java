package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class KeyTemplate extends GeneratedMessageLite implements KeyTemplateOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KeyTemplateOrBuilder {
        private Builder() {
            super(KeyTemplate.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KeyTemplate.1 keyTemplate$10) {
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public MessageLite build() {
            return super.build();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public MessageLite buildPartial() {
            return super.buildPartial();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder clear() {
            return super.clear();
        }

        public Builder clearOutputPrefixType() {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).clearOutputPrefixType();
            return this;
        }

        public Builder clearTypeUrl() {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).clearTypeUrl();
            return this;
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).clearValue();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder clone() {
            return super.clone();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder clone() {
            return super.clone();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
        public OutputPrefixType getOutputPrefixType() {
            return ((KeyTemplate)this.instance).getOutputPrefixType();
        }

        @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
        public int getOutputPrefixTypeValue() {
            return ((KeyTemplate)this.instance).getOutputPrefixTypeValue();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
        public String getTypeUrl() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
        public ByteString getTypeUrlBytes() {
            return ((KeyTemplate)this.instance).getTypeUrlBytes();
        }

        @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
        public ByteString getValue() {
            return ((KeyTemplate)this.instance).getValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return super.mergeFrom(codedInputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(ByteString byteString0) throws InvalidProtocolBufferException {
            return super.mergeFrom(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(byteString0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0) throws IOException {
            return super.mergeFrom(codedInputStream0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return super.mergeFrom(codedInputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(MessageLite messageLite0) {
            return super.mergeFrom(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(InputStream inputStream0) throws IOException {
            return super.mergeFrom(inputStream0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return super.mergeFrom(inputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, extensionRegistryLite0);
        }

        public Builder setOutputPrefixType(OutputPrefixType outputPrefixType0) {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).setOutputPrefixType(outputPrefixType0);
            return this;
        }

        public Builder setOutputPrefixTypeValue(int v) {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).setOutputPrefixTypeValue(v);
            return this;
        }

        public Builder setTypeUrl(String s) {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).setTypeUrl(s);
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).setTypeUrlBytes(byteString0);
            return this;
        }

        public Builder setValue(ByteString byteString0) {
            this.copyOnWrite();
            ((KeyTemplate)this.instance).setValue(byteString0);
            return this;
        }
    }

    private static final KeyTemplate DEFAULT_INSTANCE = null;
    public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int outputPrefixType_;
    private String typeUrl_;
    private ByteString value_;

    static {
        KeyTemplate keyTemplate0 = new KeyTemplate();
        KeyTemplate.DEFAULT_INSTANCE = keyTemplate0;
        GeneratedMessageLite.registerDefaultInstance(KeyTemplate.class, keyTemplate0);
    }

    private KeyTemplate() {
        this.typeUrl_ = "";
        this.value_ = ByteString.EMPTY;
    }

    private void clearOutputPrefixType() {
        this.outputPrefixType_ = 0;
    }

    private void clearTypeUrl() {
        this.typeUrl_ = "";
    }

    private void clearValue() {
        this.value_ = KeyTemplate.getDefaultInstance().getValue();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KeyTemplate.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KeyTemplate();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KeyTemplate.newMessageInfo(KeyTemplate.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"typeUrl_", "value_", "outputPrefixType_"});
            }
            case 4: {
                return KeyTemplate.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KeyTemplate.PARSER;
                if(parser0 == null) {
                    Class class0 = KeyTemplate.class;
                    synchronized(class0) {
                        Parser parser1 = KeyTemplate.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KeyTemplate.DEFAULT_INSTANCE);
                            KeyTemplate.PARSER = parser1;
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

    public static KeyTemplate getDefaultInstance() {
        return KeyTemplate.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
    public OutputPrefixType getOutputPrefixType() {
        OutputPrefixType outputPrefixType0 = OutputPrefixType.forNumber(this.outputPrefixType_);
        return outputPrefixType0 == null ? OutputPrefixType.UNRECOGNIZED : outputPrefixType0;
    }

    @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
    public int getOutputPrefixTypeValue() {
        return this.outputPrefixType_;
    }

    @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
    public String getTypeUrl() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
    public ByteString getTypeUrlBytes() {
        return ByteString.copyFromUtf8(this.typeUrl_);
    }

    @Override  // com.google.crypto.tink.proto.KeyTemplateOrBuilder
    public ByteString getValue() {
        return this.value_;
    }

    public static Builder newBuilder() {
        return (Builder)KeyTemplate.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KeyTemplate keyTemplate0) {
        return (Builder)KeyTemplate.DEFAULT_INSTANCE.createBuilder(keyTemplate0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KeyTemplate parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KeyTemplate)KeyTemplate.parseDelimitedFrom(KeyTemplate.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeyTemplate parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyTemplate)KeyTemplate.parseDelimitedFrom(KeyTemplate.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeyTemplate parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, byteString0);
    }

    public static KeyTemplate parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KeyTemplate parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KeyTemplate parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KeyTemplate parseFrom(InputStream inputStream0) throws IOException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeyTemplate parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeyTemplate parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KeyTemplate parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KeyTemplate parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, arr_b);
    }

    public static KeyTemplate parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyTemplate)GeneratedMessageLite.parseFrom(KeyTemplate.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KeyTemplate.DEFAULT_INSTANCE.getParserForType();
    }

    private void setOutputPrefixType(OutputPrefixType outputPrefixType0) {
        this.outputPrefixType_ = outputPrefixType0.getNumber();
    }

    private void setOutputPrefixTypeValue(int v) {
        this.outputPrefixType_ = v;
    }

    private void setTypeUrl(String s) {
        s.getClass();
        this.typeUrl_ = s;
    }

    private void setTypeUrlBytes(ByteString byteString0) {
        KeyTemplate.checkByteStringIsUtf8(byteString0);
        this.typeUrl_ = byteString0.toStringUtf8();
    }

    private void setValue(ByteString byteString0) {
        byteString0.getClass();
        this.value_ = byteString0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

