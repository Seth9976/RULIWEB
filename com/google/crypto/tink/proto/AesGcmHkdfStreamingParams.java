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

public final class AesGcmHkdfStreamingParams extends GeneratedMessageLite implements AesGcmHkdfStreamingParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesGcmHkdfStreamingParamsOrBuilder {
        private Builder() {
            super(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.1 aesGcmHkdfStreamingParams$10) {
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
            ((AesGcmHkdfStreamingParams)this.instance).clearCiphertextSegmentSize();
            return this;
        }

        public Builder clearDerivedKeySize() {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingParams)this.instance).clearDerivedKeySize();
            return this;
        }

        public Builder clearHkdfHashType() {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingParams)this.instance).clearHkdfHashType();
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

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
        public int getCiphertextSegmentSize() {
            return ((AesGcmHkdfStreamingParams)this.instance).getCiphertextSegmentSize();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
        public int getDerivedKeySize() {
            return ((AesGcmHkdfStreamingParams)this.instance).getDerivedKeySize();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
        public HashType getHkdfHashType() {
            return ((AesGcmHkdfStreamingParams)this.instance).getHkdfHashType();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
        public int getHkdfHashTypeValue() {
            return ((AesGcmHkdfStreamingParams)this.instance).getHkdfHashTypeValue();
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

        public Builder setCiphertextSegmentSize(int v) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingParams)this.instance).setCiphertextSegmentSize(v);
            return this;
        }

        public Builder setDerivedKeySize(int v) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingParams)this.instance).setDerivedKeySize(v);
            return this;
        }

        public Builder setHkdfHashType(HashType hashType0) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingParams)this.instance).setHkdfHashType(hashType0);
            return this;
        }

        public Builder setHkdfHashTypeValue(int v) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingParams)this.instance).setHkdfHashTypeValue(v);
            return this;
        }
    }

    public static final int CIPHERTEXT_SEGMENT_SIZE_FIELD_NUMBER = 1;
    private static final AesGcmHkdfStreamingParams DEFAULT_INSTANCE = null;
    public static final int DERIVED_KEY_SIZE_FIELD_NUMBER = 2;
    public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER;
    private int ciphertextSegmentSize_;
    private int derivedKeySize_;
    private int hkdfHashType_;

    static {
        AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0 = new AesGcmHkdfStreamingParams();
        AesGcmHkdfStreamingParams.DEFAULT_INSTANCE = aesGcmHkdfStreamingParams0;
        GeneratedMessageLite.registerDefaultInstance(AesGcmHkdfStreamingParams.class, aesGcmHkdfStreamingParams0);
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

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesGcmHkdfStreamingParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesGcmHkdfStreamingParams.newMessageInfo(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\u000B\u0003\f", new Object[]{"ciphertextSegmentSize_", "derivedKeySize_", "hkdfHashType_"});
            }
            case 4: {
                return AesGcmHkdfStreamingParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesGcmHkdfStreamingParams.PARSER;
                if(parser0 == null) {
                    Class class0 = AesGcmHkdfStreamingParams.class;
                    synchronized(class0) {
                        Parser parser1 = AesGcmHkdfStreamingParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE);
                            AesGcmHkdfStreamingParams.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize_;
    }

    public static AesGcmHkdfStreamingParams getDefaultInstance() {
        return AesGcmHkdfStreamingParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
    public int getDerivedKeySize() {
        return this.derivedKeySize_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
    public HashType getHkdfHashType() {
        HashType hashType0 = HashType.forNumber(this.hkdfHashType_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingParamsOrBuilder
    public int getHkdfHashTypeValue() {
        return this.hkdfHashType_;
    }

    public static Builder newBuilder() {
        return (Builder)AesGcmHkdfStreamingParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0) {
        return (Builder)AesGcmHkdfStreamingParams.DEFAULT_INSTANCE.createBuilder(aesGcmHkdfStreamingParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesGcmHkdfStreamingParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesGcmHkdfStreamingParams)AesGcmHkdfStreamingParams.parseDelimitedFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmHkdfStreamingParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingParams)AesGcmHkdfStreamingParams.parseDelimitedFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, byteString0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(InputStream inputStream0) throws IOException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, arr_b);
    }

    public static AesGcmHkdfStreamingParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesGcmHkdfStreamingParams.DEFAULT_INSTANCE.getParserForType();
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

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

