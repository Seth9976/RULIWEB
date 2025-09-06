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

public final class RsaSsaPkcs1Params extends GeneratedMessageLite implements RsaSsaPkcs1ParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements RsaSsaPkcs1ParamsOrBuilder {
        private Builder() {
            super(RsaSsaPkcs1Params.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.RsaSsaPkcs1Params.1 rsaSsaPkcs1Params$10) {
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

        public Builder clearHashType() {
            this.copyOnWrite();
            ((RsaSsaPkcs1Params)this.instance).clearHashType();
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

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
        public HashType getHashType() {
            return ((RsaSsaPkcs1Params)this.instance).getHashType();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
        public int getHashTypeValue() {
            return ((RsaSsaPkcs1Params)this.instance).getHashTypeValue();
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

        public Builder setHashType(HashType hashType0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1Params)this.instance).setHashType(hashType0);
            return this;
        }

        public Builder setHashTypeValue(int v) {
            this.copyOnWrite();
            ((RsaSsaPkcs1Params)this.instance).setHashTypeValue(v);
            return this;
        }
    }

    private static final RsaSsaPkcs1Params DEFAULT_INSTANCE = null;
    public static final int HASH_TYPE_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int hashType_;

    static {
        RsaSsaPkcs1Params rsaSsaPkcs1Params0 = new RsaSsaPkcs1Params();
        RsaSsaPkcs1Params.DEFAULT_INSTANCE = rsaSsaPkcs1Params0;
        GeneratedMessageLite.registerDefaultInstance(RsaSsaPkcs1Params.class, rsaSsaPkcs1Params0);
    }

    private void clearHashType() {
        this.hashType_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.RsaSsaPkcs1Params.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new RsaSsaPkcs1Params();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return RsaSsaPkcs1Params.newMessageInfo(RsaSsaPkcs1Params.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"hashType_"});
            }
            case 4: {
                return RsaSsaPkcs1Params.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = RsaSsaPkcs1Params.PARSER;
                if(parser0 == null) {
                    Class class0 = RsaSsaPkcs1Params.class;
                    synchronized(class0) {
                        Parser parser1 = RsaSsaPkcs1Params.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(RsaSsaPkcs1Params.DEFAULT_INSTANCE);
                            RsaSsaPkcs1Params.PARSER = parser1;
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

    public static RsaSsaPkcs1Params getDefaultInstance() {
        return RsaSsaPkcs1Params.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
    public HashType getHashType() {
        HashType hashType0 = HashType.forNumber(this.hashType_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
    public int getHashTypeValue() {
        return this.hashType_;
    }

    public static Builder newBuilder() {
        return (Builder)RsaSsaPkcs1Params.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
        return (Builder)RsaSsaPkcs1Params.DEFAULT_INSTANCE.createBuilder(rsaSsaPkcs1Params0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static RsaSsaPkcs1Params parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1Params)RsaSsaPkcs1Params.parseDelimitedFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1Params parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1Params)RsaSsaPkcs1Params.parseDelimitedFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1Params parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, byteString0);
    }

    public static RsaSsaPkcs1Params parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1Params parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static RsaSsaPkcs1Params parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1Params parseFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1Params parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1Params parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static RsaSsaPkcs1Params parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1Params parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, arr_b);
    }

    public static RsaSsaPkcs1Params parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params)GeneratedMessageLite.parseFrom(RsaSsaPkcs1Params.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return RsaSsaPkcs1Params.DEFAULT_INSTANCE.getParserForType();
    }

    private void setHashType(HashType hashType0) {
        this.hashType_ = hashType0.getNumber();
    }

    private void setHashTypeValue(int v) {
        this.hashType_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

