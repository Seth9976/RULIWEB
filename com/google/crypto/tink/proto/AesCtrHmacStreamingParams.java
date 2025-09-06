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

public final class AesCtrHmacStreamingParams extends GeneratedMessageLite implements AesCtrHmacStreamingParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCtrHmacStreamingParamsOrBuilder {
        private Builder() {
            super(AesCtrHmacStreamingParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCtrHmacStreamingParams.1 aesCtrHmacStreamingParams$10) {
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

        public Builder clearCiphertextSegmentSize() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).clearCiphertextSegmentSize();
            return this;
        }

        public Builder clearDerivedKeySize() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).clearDerivedKeySize();
            return this;
        }

        public Builder clearHkdfHashType() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).clearHkdfHashType();
            return this;
        }

        public Builder clearHmacParams() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).clearHmacParams();
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

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
        public int getCiphertextSegmentSize() {
            return ((AesCtrHmacStreamingParams)this.instance).getCiphertextSegmentSize();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
        public int getDerivedKeySize() {
            return ((AesCtrHmacStreamingParams)this.instance).getDerivedKeySize();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
        public HashType getHkdfHashType() {
            return ((AesCtrHmacStreamingParams)this.instance).getHkdfHashType();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
        public int getHkdfHashTypeValue() {
            return ((AesCtrHmacStreamingParams)this.instance).getHkdfHashTypeValue();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
        public HmacParams getHmacParams() {
            return ((AesCtrHmacStreamingParams)this.instance).getHmacParams();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
        public boolean hasHmacParams() {
            return ((AesCtrHmacStreamingParams)this.instance).hasHmacParams();
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

        public Builder mergeHmacParams(HmacParams hmacParams0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).mergeHmacParams(hmacParams0);
            return this;
        }

        public Builder setCiphertextSegmentSize(int v) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).setCiphertextSegmentSize(v);
            return this;
        }

        public Builder setDerivedKeySize(int v) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).setDerivedKeySize(v);
            return this;
        }

        public Builder setHkdfHashType(HashType hashType0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).setHkdfHashType(hashType0);
            return this;
        }

        public Builder setHkdfHashTypeValue(int v) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).setHkdfHashTypeValue(v);
            return this;
        }

        public Builder setHmacParams(com.google.crypto.tink.proto.HmacParams.Builder hmacParams$Builder0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).setHmacParams(((HmacParams)hmacParams$Builder0.build()));
            return this;
        }

        public Builder setHmacParams(HmacParams hmacParams0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingParams)this.instance).setHmacParams(hmacParams0);
            return this;
        }
    }

    public static final int CIPHERTEXT_SEGMENT_SIZE_FIELD_NUMBER = 1;
    private static final AesCtrHmacStreamingParams DEFAULT_INSTANCE = null;
    public static final int DERIVED_KEY_SIZE_FIELD_NUMBER = 2;
    public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 3;
    public static final int HMAC_PARAMS_FIELD_NUMBER = 4;
    private static volatile Parser PARSER;
    private int ciphertextSegmentSize_;
    private int derivedKeySize_;
    private int hkdfHashType_;
    private HmacParams hmacParams_;

    static {
        AesCtrHmacStreamingParams aesCtrHmacStreamingParams0 = new AesCtrHmacStreamingParams();
        AesCtrHmacStreamingParams.DEFAULT_INSTANCE = aesCtrHmacStreamingParams0;
        GeneratedMessageLite.registerDefaultInstance(AesCtrHmacStreamingParams.class, aesCtrHmacStreamingParams0);
    }

    private void clearCiphertextSegmentSize() {
        this.ciphertextSegmentSize_ = 0;
    }

    private void clearDerivedKeySize() {
        this.derivedKeySize_ = 0;
    }

    private void clearHkdfHashType() {
        this.hkdfHashType_ = 0;
    }

    private void clearHmacParams() {
        this.hmacParams_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCtrHmacStreamingParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCtrHmacStreamingParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCtrHmacStreamingParams.newMessageInfo(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\u000B\u0003\f\u0004\t", new Object[]{"ciphertextSegmentSize_", "derivedKeySize_", "hkdfHashType_", "hmacParams_"});
            }
            case 4: {
                return AesCtrHmacStreamingParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCtrHmacStreamingParams.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCtrHmacStreamingParams.class;
                    synchronized(class0) {
                        Parser parser1 = AesCtrHmacStreamingParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCtrHmacStreamingParams.DEFAULT_INSTANCE);
                            AesCtrHmacStreamingParams.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize_;
    }

    public static AesCtrHmacStreamingParams getDefaultInstance() {
        return AesCtrHmacStreamingParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
    public int getDerivedKeySize() {
        return this.derivedKeySize_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
    public HashType getHkdfHashType() {
        HashType hashType0 = HashType.forNumber(this.hkdfHashType_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
    public int getHkdfHashTypeValue() {
        return this.hkdfHashType_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
    public HmacParams getHmacParams() {
        return this.hmacParams_ == null ? HmacParams.getDefaultInstance() : this.hmacParams_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingParamsOrBuilder
    public boolean hasHmacParams() {
        return this.hmacParams_ != null;
    }

    private void mergeHmacParams(HmacParams hmacParams0) {
        hmacParams0.getClass();
        if(this.hmacParams_ != null && this.hmacParams_ != HmacParams.getDefaultInstance()) {
            this.hmacParams_ = (HmacParams)((com.google.crypto.tink.proto.HmacParams.Builder)HmacParams.newBuilder(this.hmacParams_).mergeFrom(hmacParams0)).buildPartial();
            return;
        }
        this.hmacParams_ = hmacParams0;
    }

    public static Builder newBuilder() {
        return (Builder)AesCtrHmacStreamingParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCtrHmacStreamingParams aesCtrHmacStreamingParams0) {
        return (Builder)AesCtrHmacStreamingParams.DEFAULT_INSTANCE.createBuilder(aesCtrHmacStreamingParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCtrHmacStreamingParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacStreamingParams)AesCtrHmacStreamingParams.parseDelimitedFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacStreamingParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingParams)AesCtrHmacStreamingParams.parseDelimitedFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCtrHmacStreamingParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCtrHmacStreamingParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingParams parseFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacStreamingParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCtrHmacStreamingParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCtrHmacStreamingParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCtrHmacStreamingParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setCiphertextSegmentSize(int v) {
        this.ciphertextSegmentSize_ = v;
    }

    private void setDerivedKeySize(int v) {
        this.derivedKeySize_ = v;
    }

    private void setHkdfHashType(HashType hashType0) {
        this.hkdfHashType_ = hashType0.getNumber();
    }

    private void setHkdfHashTypeValue(int v) {
        this.hkdfHashType_ = v;
    }

    private void setHmacParams(HmacParams hmacParams0) {
        hmacParams0.getClass();
        this.hmacParams_ = hmacParams0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

