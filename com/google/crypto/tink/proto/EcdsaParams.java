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

public final class EcdsaParams extends GeneratedMessageLite implements EcdsaParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EcdsaParamsOrBuilder {
        private Builder() {
            super(EcdsaParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EcdsaParams.1 ecdsaParams$10) {
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

        public Builder clearCurve() {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).clearCurve();
            return this;
        }

        public Builder clearEncoding() {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).clearEncoding();
            return this;
        }

        public Builder clearHashType() {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).clearHashType();
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

        @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
        public EllipticCurveType getCurve() {
            return ((EcdsaParams)this.instance).getCurve();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
        public int getCurveValue() {
            return ((EcdsaParams)this.instance).getCurveValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
        public EcdsaSignatureEncoding getEncoding() {
            return ((EcdsaParams)this.instance).getEncoding();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
        public int getEncodingValue() {
            return ((EcdsaParams)this.instance).getEncodingValue();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
        public HashType getHashType() {
            return ((EcdsaParams)this.instance).getHashType();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
        public int getHashTypeValue() {
            return ((EcdsaParams)this.instance).getHashTypeValue();
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

        public Builder setCurve(EllipticCurveType ellipticCurveType0) {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).setCurve(ellipticCurveType0);
            return this;
        }

        public Builder setCurveValue(int v) {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).setCurveValue(v);
            return this;
        }

        public Builder setEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding0) {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).setEncoding(ecdsaSignatureEncoding0);
            return this;
        }

        public Builder setEncodingValue(int v) {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).setEncodingValue(v);
            return this;
        }

        public Builder setHashType(HashType hashType0) {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).setHashType(hashType0);
            return this;
        }

        public Builder setHashTypeValue(int v) {
            this.copyOnWrite();
            ((EcdsaParams)this.instance).setHashTypeValue(v);
            return this;
        }
    }

    public static final int CURVE_FIELD_NUMBER = 2;
    private static final EcdsaParams DEFAULT_INSTANCE = null;
    public static final int ENCODING_FIELD_NUMBER = 3;
    public static final int HASH_TYPE_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int curve_;
    private int encoding_;
    private int hashType_;

    static {
        EcdsaParams ecdsaParams0 = new EcdsaParams();
        EcdsaParams.DEFAULT_INSTANCE = ecdsaParams0;
        GeneratedMessageLite.registerDefaultInstance(EcdsaParams.class, ecdsaParams0);
    }

    private void clearCurve() {
        this.curve_ = 0;
    }

    private void clearEncoding() {
        this.encoding_ = 0;
    }

    private void clearHashType() {
        this.hashType_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EcdsaParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EcdsaParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EcdsaParams.newMessageInfo(EcdsaParams.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"hashType_", "curve_", "encoding_"});
            }
            case 4: {
                return EcdsaParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EcdsaParams.PARSER;
                if(parser0 == null) {
                    Class class0 = EcdsaParams.class;
                    synchronized(class0) {
                        Parser parser1 = EcdsaParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EcdsaParams.DEFAULT_INSTANCE);
                            EcdsaParams.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
    public EllipticCurveType getCurve() {
        EllipticCurveType ellipticCurveType0 = EllipticCurveType.forNumber(this.curve_);
        return ellipticCurveType0 == null ? EllipticCurveType.UNRECOGNIZED : ellipticCurveType0;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
    public int getCurveValue() {
        return this.curve_;
    }

    public static EcdsaParams getDefaultInstance() {
        return EcdsaParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
    public EcdsaSignatureEncoding getEncoding() {
        EcdsaSignatureEncoding ecdsaSignatureEncoding0 = EcdsaSignatureEncoding.forNumber(this.encoding_);
        return ecdsaSignatureEncoding0 == null ? EcdsaSignatureEncoding.UNRECOGNIZED : ecdsaSignatureEncoding0;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
    public int getEncodingValue() {
        return this.encoding_;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
    public HashType getHashType() {
        HashType hashType0 = HashType.forNumber(this.hashType_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaParamsOrBuilder
    public int getHashTypeValue() {
        return this.hashType_;
    }

    public static Builder newBuilder() {
        return (Builder)EcdsaParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EcdsaParams ecdsaParams0) {
        return (Builder)EcdsaParams.DEFAULT_INSTANCE.createBuilder(ecdsaParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EcdsaParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EcdsaParams)EcdsaParams.parseDelimitedFrom(EcdsaParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EcdsaParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaParams)EcdsaParams.parseDelimitedFrom(EcdsaParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EcdsaParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, byteString0);
    }

    public static EcdsaParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EcdsaParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EcdsaParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EcdsaParams parseFrom(InputStream inputStream0) throws IOException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EcdsaParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EcdsaParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EcdsaParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EcdsaParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, arr_b);
    }

    public static EcdsaParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaParams)GeneratedMessageLite.parseFrom(EcdsaParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EcdsaParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setCurve(EllipticCurveType ellipticCurveType0) {
        this.curve_ = ellipticCurveType0.getNumber();
    }

    private void setCurveValue(int v) {
        this.curve_ = v;
    }

    private void setEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding0) {
        this.encoding_ = ecdsaSignatureEncoding0.getNumber();
    }

    private void setEncodingValue(int v) {
        this.encoding_ = v;
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

