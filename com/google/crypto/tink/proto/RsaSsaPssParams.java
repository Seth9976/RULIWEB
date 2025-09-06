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

public final class RsaSsaPssParams extends GeneratedMessageLite implements RsaSsaPssParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements RsaSsaPssParamsOrBuilder {
        private Builder() {
            super(RsaSsaPssParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.RsaSsaPssParams.1 rsaSsaPssParams$10) {
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

        public Builder clearMgf1Hash() {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).clearMgf1Hash();
            return this;
        }

        public Builder clearSaltLength() {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).clearSaltLength();
            return this;
        }

        public Builder clearSigHash() {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).clearSigHash();
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

        @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
        public HashType getMgf1Hash() {
            return ((RsaSsaPssParams)this.instance).getMgf1Hash();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
        public int getMgf1HashValue() {
            return ((RsaSsaPssParams)this.instance).getMgf1HashValue();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
        public int getSaltLength() {
            return ((RsaSsaPssParams)this.instance).getSaltLength();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
        public HashType getSigHash() {
            return ((RsaSsaPssParams)this.instance).getSigHash();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
        public int getSigHashValue() {
            return ((RsaSsaPssParams)this.instance).getSigHashValue();
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

        public Builder setMgf1Hash(HashType hashType0) {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).setMgf1Hash(hashType0);
            return this;
        }

        public Builder setMgf1HashValue(int v) {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).setMgf1HashValue(v);
            return this;
        }

        public Builder setSaltLength(int v) {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).setSaltLength(v);
            return this;
        }

        public Builder setSigHash(HashType hashType0) {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).setSigHash(hashType0);
            return this;
        }

        public Builder setSigHashValue(int v) {
            this.copyOnWrite();
            ((RsaSsaPssParams)this.instance).setSigHashValue(v);
            return this;
        }
    }

    private static final RsaSsaPssParams DEFAULT_INSTANCE = null;
    public static final int MGF1_HASH_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int SALT_LENGTH_FIELD_NUMBER = 3;
    public static final int SIG_HASH_FIELD_NUMBER = 1;
    private int mgf1Hash_;
    private int saltLength_;
    private int sigHash_;

    static {
        RsaSsaPssParams rsaSsaPssParams0 = new RsaSsaPssParams();
        RsaSsaPssParams.DEFAULT_INSTANCE = rsaSsaPssParams0;
        GeneratedMessageLite.registerDefaultInstance(RsaSsaPssParams.class, rsaSsaPssParams0);
    }

    private void clearMgf1Hash() {
        this.mgf1Hash_ = 0;
    }

    private void clearSaltLength() {
        this.saltLength_ = 0;
    }

    private void clearSigHash() {
        this.sigHash_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.RsaSsaPssParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new RsaSsaPssParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return RsaSsaPssParams.newMessageInfo(RsaSsaPssParams.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\u0004", new Object[]{"sigHash_", "mgf1Hash_", "saltLength_"});
            }
            case 4: {
                return RsaSsaPssParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = RsaSsaPssParams.PARSER;
                if(parser0 == null) {
                    Class class0 = RsaSsaPssParams.class;
                    synchronized(class0) {
                        Parser parser1 = RsaSsaPssParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(RsaSsaPssParams.DEFAULT_INSTANCE);
                            RsaSsaPssParams.PARSER = parser1;
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

    public static RsaSsaPssParams getDefaultInstance() {
        return RsaSsaPssParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
    public HashType getMgf1Hash() {
        HashType hashType0 = HashType.forNumber(this.mgf1Hash_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
    public int getMgf1HashValue() {
        return this.mgf1Hash_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
    public int getSaltLength() {
        return this.saltLength_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
    public HashType getSigHash() {
        HashType hashType0 = HashType.forNumber(this.sigHash_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPssParamsOrBuilder
    public int getSigHashValue() {
        return this.sigHash_;
    }

    public static Builder newBuilder() {
        return (Builder)RsaSsaPssParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RsaSsaPssParams rsaSsaPssParams0) {
        return (Builder)RsaSsaPssParams.DEFAULT_INSTANCE.createBuilder(rsaSsaPssParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static RsaSsaPssParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPssParams)RsaSsaPssParams.parseDelimitedFrom(RsaSsaPssParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPssParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPssParams)RsaSsaPssParams.parseDelimitedFrom(RsaSsaPssParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPssParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, byteString0);
    }

    public static RsaSsaPssParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static RsaSsaPssParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static RsaSsaPssParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPssParams parseFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPssParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPssParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static RsaSsaPssParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static RsaSsaPssParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, arr_b);
    }

    public static RsaSsaPssParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPssParams)GeneratedMessageLite.parseFrom(RsaSsaPssParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return RsaSsaPssParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setMgf1Hash(HashType hashType0) {
        this.mgf1Hash_ = hashType0.getNumber();
    }

    private void setMgf1HashValue(int v) {
        this.mgf1Hash_ = v;
    }

    private void setSaltLength(int v) {
        this.saltLength_ = v;
    }

    private void setSigHash(HashType hashType0) {
        this.sigHash_ = hashType0.getNumber();
    }

    private void setSigHashValue(int v) {
        this.sigHash_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

