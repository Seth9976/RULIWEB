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

public final class EciesHkdfKemParams extends GeneratedMessageLite implements EciesHkdfKemParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EciesHkdfKemParamsOrBuilder {
        private Builder() {
            super(EciesHkdfKemParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EciesHkdfKemParams.1 eciesHkdfKemParams$10) {
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

        public Builder clearCurveType() {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).clearCurveType();
            return this;
        }

        public Builder clearHkdfHashType() {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).clearHkdfHashType();
            return this;
        }

        public Builder clearHkdfSalt() {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).clearHkdfSalt();
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

        @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public EllipticCurveType getCurveType() {
            return ((EciesHkdfKemParams)this.instance).getCurveType();
        }

        @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public int getCurveTypeValue() {
            return ((EciesHkdfKemParams)this.instance).getCurveTypeValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public HashType getHkdfHashType() {
            return ((EciesHkdfKemParams)this.instance).getHkdfHashType();
        }

        @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public int getHkdfHashTypeValue() {
            return ((EciesHkdfKemParams)this.instance).getHkdfHashTypeValue();
        }

        @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
        public ByteString getHkdfSalt() {
            return ((EciesHkdfKemParams)this.instance).getHkdfSalt();
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

        public Builder setCurveType(EllipticCurveType ellipticCurveType0) {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).setCurveType(ellipticCurveType0);
            return this;
        }

        public Builder setCurveTypeValue(int v) {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).setCurveTypeValue(v);
            return this;
        }

        public Builder setHkdfHashType(HashType hashType0) {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).setHkdfHashType(hashType0);
            return this;
        }

        public Builder setHkdfHashTypeValue(int v) {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).setHkdfHashTypeValue(v);
            return this;
        }

        public Builder setHkdfSalt(ByteString byteString0) {
            this.copyOnWrite();
            ((EciesHkdfKemParams)this.instance).setHkdfSalt(byteString0);
            return this;
        }
    }

    public static final int CURVE_TYPE_FIELD_NUMBER = 1;
    private static final EciesHkdfKemParams DEFAULT_INSTANCE = null;
    public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 2;
    public static final int HKDF_SALT_FIELD_NUMBER = 11;
    private static volatile Parser PARSER;
    private int curveType_;
    private int hkdfHashType_;
    private ByteString hkdfSalt_;

    static {
        EciesHkdfKemParams eciesHkdfKemParams0 = new EciesHkdfKemParams();
        EciesHkdfKemParams.DEFAULT_INSTANCE = eciesHkdfKemParams0;
        GeneratedMessageLite.registerDefaultInstance(EciesHkdfKemParams.class, eciesHkdfKemParams0);
    }

    private EciesHkdfKemParams() {
        this.hkdfSalt_ = ByteString.EMPTY;
    }

    private void clearCurveType() {
        this.curveType_ = 0;
    }

    private void clearHkdfHashType() {
        this.hkdfHashType_ = 0;
    }

    private void clearHkdfSalt() {
        this.hkdfSalt_ = EciesHkdfKemParams.getDefaultInstance().getHkdfSalt();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EciesHkdfKemParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EciesHkdfKemParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EciesHkdfKemParams.newMessageInfo(EciesHkdfKemParams.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u000B\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000B\n", new Object[]{"curveType_", "hkdfHashType_", "hkdfSalt_"});
            }
            case 4: {
                return EciesHkdfKemParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EciesHkdfKemParams.PARSER;
                if(parser0 == null) {
                    Class class0 = EciesHkdfKemParams.class;
                    synchronized(class0) {
                        Parser parser1 = EciesHkdfKemParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EciesHkdfKemParams.DEFAULT_INSTANCE);
                            EciesHkdfKemParams.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public EllipticCurveType getCurveType() {
        EllipticCurveType ellipticCurveType0 = EllipticCurveType.forNumber(this.curveType_);
        return ellipticCurveType0 == null ? EllipticCurveType.UNRECOGNIZED : ellipticCurveType0;
    }

    @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public int getCurveTypeValue() {
        return this.curveType_;
    }

    public static EciesHkdfKemParams getDefaultInstance() {
        return EciesHkdfKemParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public HashType getHkdfHashType() {
        HashType hashType0 = HashType.forNumber(this.hkdfHashType_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public int getHkdfHashTypeValue() {
        return this.hkdfHashType_;
    }

    @Override  // com.google.crypto.tink.proto.EciesHkdfKemParamsOrBuilder
    public ByteString getHkdfSalt() {
        return this.hkdfSalt_;
    }

    public static Builder newBuilder() {
        return (Builder)EciesHkdfKemParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EciesHkdfKemParams eciesHkdfKemParams0) {
        return (Builder)EciesHkdfKemParams.DEFAULT_INSTANCE.createBuilder(eciesHkdfKemParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EciesHkdfKemParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EciesHkdfKemParams)EciesHkdfKemParams.parseDelimitedFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesHkdfKemParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesHkdfKemParams)EciesHkdfKemParams.parseDelimitedFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesHkdfKemParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, byteString0);
    }

    public static EciesHkdfKemParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EciesHkdfKemParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EciesHkdfKemParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EciesHkdfKemParams parseFrom(InputStream inputStream0) throws IOException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesHkdfKemParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesHkdfKemParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EciesHkdfKemParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EciesHkdfKemParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, arr_b);
    }

    public static EciesHkdfKemParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams)GeneratedMessageLite.parseFrom(EciesHkdfKemParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EciesHkdfKemParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setCurveType(EllipticCurveType ellipticCurveType0) {
        this.curveType_ = ellipticCurveType0.getNumber();
    }

    private void setCurveTypeValue(int v) {
        this.curveType_ = v;
    }

    private void setHkdfHashType(HashType hashType0) {
        this.hkdfHashType_ = hashType0.getNumber();
    }

    private void setHkdfHashTypeValue(int v) {
        this.hkdfHashType_ = v;
    }

    private void setHkdfSalt(ByteString byteString0) {
        byteString0.getClass();
        this.hkdfSalt_ = byteString0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

