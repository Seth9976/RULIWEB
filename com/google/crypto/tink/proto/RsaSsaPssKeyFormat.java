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

public final class RsaSsaPssKeyFormat extends GeneratedMessageLite implements RsaSsaPssKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements RsaSsaPssKeyFormatOrBuilder {
        private Builder() {
            super(RsaSsaPssKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.RsaSsaPssKeyFormat.1 rsaSsaPssKeyFormat$10) {
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

        public Builder clearModulusSizeInBits() {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).clearModulusSizeInBits();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).clearParams();
            return this;
        }

        public Builder clearPublicExponent() {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).clearPublicExponent();
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

        @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
        public int getModulusSizeInBits() {
            return ((RsaSsaPssKeyFormat)this.instance).getModulusSizeInBits();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
        public RsaSsaPssParams getParams() {
            return ((RsaSsaPssKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
        public ByteString getPublicExponent() {
            return ((RsaSsaPssKeyFormat)this.instance).getPublicExponent();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
        public boolean hasParams() {
            return ((RsaSsaPssKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(RsaSsaPssParams rsaSsaPssParams0) {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).mergeParams(rsaSsaPssParams0);
            return this;
        }

        public Builder setModulusSizeInBits(int v) {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).setModulusSizeInBits(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.RsaSsaPssParams.Builder rsaSsaPssParams$Builder0) {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).setParams(((RsaSsaPssParams)rsaSsaPssParams$Builder0.build()));
            return this;
        }

        public Builder setParams(RsaSsaPssParams rsaSsaPssParams0) {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).setParams(rsaSsaPssParams0);
            return this;
        }

        public Builder setPublicExponent(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPssKeyFormat)this.instance).setPublicExponent(byteString0);
            return this;
        }
    }

    private static final RsaSsaPssKeyFormat DEFAULT_INSTANCE = null;
    public static final int MODULUS_SIZE_IN_BITS_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_EXPONENT_FIELD_NUMBER = 3;
    private int modulusSizeInBits_;
    private RsaSsaPssParams params_;
    private ByteString publicExponent_;

    static {
        RsaSsaPssKeyFormat rsaSsaPssKeyFormat0 = new RsaSsaPssKeyFormat();
        RsaSsaPssKeyFormat.DEFAULT_INSTANCE = rsaSsaPssKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(RsaSsaPssKeyFormat.class, rsaSsaPssKeyFormat0);
    }

    private RsaSsaPssKeyFormat() {
        this.publicExponent_ = ByteString.EMPTY;
    }

    private void clearModulusSizeInBits() {
        this.modulusSizeInBits_ = 0;
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearPublicExponent() {
        this.publicExponent_ = RsaSsaPssKeyFormat.getDefaultInstance().getPublicExponent();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.RsaSsaPssKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new RsaSsaPssKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return RsaSsaPssKeyFormat.newMessageInfo(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000B\u0003\n", new Object[]{"params_", "modulusSizeInBits_", "publicExponent_"});
            }
            case 4: {
                return RsaSsaPssKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = RsaSsaPssKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = RsaSsaPssKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = RsaSsaPssKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(RsaSsaPssKeyFormat.DEFAULT_INSTANCE);
                            RsaSsaPssKeyFormat.PARSER = parser1;
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

    public static RsaSsaPssKeyFormat getDefaultInstance() {
        return RsaSsaPssKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
    public int getModulusSizeInBits() {
        return this.modulusSizeInBits_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
    public RsaSsaPssParams getParams() {
        return this.params_ == null ? RsaSsaPssParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
    public ByteString getPublicExponent() {
        return this.publicExponent_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(RsaSsaPssParams rsaSsaPssParams0) {
        rsaSsaPssParams0.getClass();
        if(this.params_ != null && this.params_ != RsaSsaPssParams.getDefaultInstance()) {
            this.params_ = (RsaSsaPssParams)((com.google.crypto.tink.proto.RsaSsaPssParams.Builder)RsaSsaPssParams.newBuilder(this.params_).mergeFrom(rsaSsaPssParams0)).buildPartial();
            return;
        }
        this.params_ = rsaSsaPssParams0;
    }

    public static Builder newBuilder() {
        return (Builder)RsaSsaPssKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RsaSsaPssKeyFormat rsaSsaPssKeyFormat0) {
        return (Builder)RsaSsaPssKeyFormat.DEFAULT_INSTANCE.createBuilder(rsaSsaPssKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static RsaSsaPssKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPssKeyFormat)RsaSsaPssKeyFormat.parseDelimitedFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPssKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPssKeyFormat)RsaSsaPssKeyFormat.parseDelimitedFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPssKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static RsaSsaPssKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static RsaSsaPssKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static RsaSsaPssKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPssKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPssKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPssKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static RsaSsaPssKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static RsaSsaPssKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static RsaSsaPssKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPssKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return RsaSsaPssKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setModulusSizeInBits(int v) {
        this.modulusSizeInBits_ = v;
    }

    private void setParams(RsaSsaPssParams rsaSsaPssParams0) {
        rsaSsaPssParams0.getClass();
        this.params_ = rsaSsaPssParams0;
    }

    private void setPublicExponent(ByteString byteString0) {
        byteString0.getClass();
        this.publicExponent_ = byteString0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

