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

public final class RsaSsaPkcs1KeyFormat extends GeneratedMessageLite implements RsaSsaPkcs1KeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements RsaSsaPkcs1KeyFormatOrBuilder {
        private Builder() {
            super(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat.1 rsaSsaPkcs1KeyFormat$10) {
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
            ((RsaSsaPkcs1KeyFormat)this.instance).clearModulusSizeInBits();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((RsaSsaPkcs1KeyFormat)this.instance).clearParams();
            return this;
        }

        public Builder clearPublicExponent() {
            this.copyOnWrite();
            ((RsaSsaPkcs1KeyFormat)this.instance).clearPublicExponent();
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

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
        public int getModulusSizeInBits() {
            return ((RsaSsaPkcs1KeyFormat)this.instance).getModulusSizeInBits();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
        public RsaSsaPkcs1Params getParams() {
            return ((RsaSsaPkcs1KeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
        public ByteString getPublicExponent() {
            return ((RsaSsaPkcs1KeyFormat)this.instance).getPublicExponent();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
        public boolean hasParams() {
            return ((RsaSsaPkcs1KeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1KeyFormat)this.instance).mergeParams(rsaSsaPkcs1Params0);
            return this;
        }

        public Builder setModulusSizeInBits(int v) {
            this.copyOnWrite();
            ((RsaSsaPkcs1KeyFormat)this.instance).setModulusSizeInBits(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.RsaSsaPkcs1Params.Builder rsaSsaPkcs1Params$Builder0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1KeyFormat)this.instance).setParams(((RsaSsaPkcs1Params)rsaSsaPkcs1Params$Builder0.build()));
            return this;
        }

        public Builder setParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1KeyFormat)this.instance).setParams(rsaSsaPkcs1Params0);
            return this;
        }

        public Builder setPublicExponent(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1KeyFormat)this.instance).setPublicExponent(byteString0);
            return this;
        }
    }

    private static final RsaSsaPkcs1KeyFormat DEFAULT_INSTANCE = null;
    public static final int MODULUS_SIZE_IN_BITS_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_EXPONENT_FIELD_NUMBER = 3;
    private int modulusSizeInBits_;
    private RsaSsaPkcs1Params params_;
    private ByteString publicExponent_;

    static {
        RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat0 = new RsaSsaPkcs1KeyFormat();
        RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE = rsaSsaPkcs1KeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(RsaSsaPkcs1KeyFormat.class, rsaSsaPkcs1KeyFormat0);
    }

    private RsaSsaPkcs1KeyFormat() {
        this.publicExponent_ = ByteString.EMPTY;
    }

    private void clearModulusSizeInBits() {
        this.modulusSizeInBits_ = 0;
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearPublicExponent() {
        this.publicExponent_ = RsaSsaPkcs1KeyFormat.getDefaultInstance().getPublicExponent();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new RsaSsaPkcs1KeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return RsaSsaPkcs1KeyFormat.newMessageInfo(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000B\u0003\n", new Object[]{"params_", "modulusSizeInBits_", "publicExponent_"});
            }
            case 4: {
                return RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = RsaSsaPkcs1KeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = RsaSsaPkcs1KeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = RsaSsaPkcs1KeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE);
                            RsaSsaPkcs1KeyFormat.PARSER = parser1;
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

    public static RsaSsaPkcs1KeyFormat getDefaultInstance() {
        return RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
    public int getModulusSizeInBits() {
        return this.modulusSizeInBits_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
    public RsaSsaPkcs1Params getParams() {
        return this.params_ == null ? RsaSsaPkcs1Params.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
    public ByteString getPublicExponent() {
        return this.publicExponent_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
        rsaSsaPkcs1Params0.getClass();
        if(this.params_ != null && this.params_ != RsaSsaPkcs1Params.getDefaultInstance()) {
            this.params_ = (RsaSsaPkcs1Params)((com.google.crypto.tink.proto.RsaSsaPkcs1Params.Builder)RsaSsaPkcs1Params.newBuilder(this.params_).mergeFrom(rsaSsaPkcs1Params0)).buildPartial();
            return;
        }
        this.params_ = rsaSsaPkcs1Params0;
    }

    public static Builder newBuilder() {
        return (Builder)RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat0) {
        return (Builder)RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE.createBuilder(rsaSsaPkcs1KeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static RsaSsaPkcs1KeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1KeyFormat)RsaSsaPkcs1KeyFormat.parseDelimitedFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1KeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1KeyFormat)RsaSsaPkcs1KeyFormat.parseDelimitedFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static RsaSsaPkcs1KeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return RsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setModulusSizeInBits(int v) {
        this.modulusSizeInBits_ = v;
    }

    private void setParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
        rsaSsaPkcs1Params0.getClass();
        this.params_ = rsaSsaPkcs1Params0;
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

