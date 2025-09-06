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

public final class EciesAeadHkdfParams extends GeneratedMessageLite implements EciesAeadHkdfParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EciesAeadHkdfParamsOrBuilder {
        private Builder() {
            super(EciesAeadHkdfParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EciesAeadHkdfParams.1 eciesAeadHkdfParams$10) {
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

        public Builder clearDemParams() {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).clearDemParams();
            return this;
        }

        public Builder clearEcPointFormat() {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).clearEcPointFormat();
            return this;
        }

        public Builder clearKemParams() {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).clearKemParams();
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

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
        public EciesAeadDemParams getDemParams() {
            return ((EciesAeadHkdfParams)this.instance).getDemParams();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
        public EcPointFormat getEcPointFormat() {
            return ((EciesAeadHkdfParams)this.instance).getEcPointFormat();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
        public int getEcPointFormatValue() {
            return ((EciesAeadHkdfParams)this.instance).getEcPointFormatValue();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
        public EciesHkdfKemParams getKemParams() {
            return ((EciesAeadHkdfParams)this.instance).getKemParams();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
        public boolean hasDemParams() {
            return ((EciesAeadHkdfParams)this.instance).hasDemParams();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
        public boolean hasKemParams() {
            return ((EciesAeadHkdfParams)this.instance).hasKemParams();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        public Builder mergeDemParams(EciesAeadDemParams eciesAeadDemParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).mergeDemParams(eciesAeadDemParams0);
            return this;
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

        public Builder mergeKemParams(EciesHkdfKemParams eciesHkdfKemParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).mergeKemParams(eciesHkdfKemParams0);
            return this;
        }

        public Builder setDemParams(com.google.crypto.tink.proto.EciesAeadDemParams.Builder eciesAeadDemParams$Builder0) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).setDemParams(((EciesAeadDemParams)eciesAeadDemParams$Builder0.build()));
            return this;
        }

        public Builder setDemParams(EciesAeadDemParams eciesAeadDemParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).setDemParams(eciesAeadDemParams0);
            return this;
        }

        public Builder setEcPointFormat(EcPointFormat ecPointFormat0) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).setEcPointFormat(ecPointFormat0);
            return this;
        }

        public Builder setEcPointFormatValue(int v) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).setEcPointFormatValue(v);
            return this;
        }

        public Builder setKemParams(com.google.crypto.tink.proto.EciesHkdfKemParams.Builder eciesHkdfKemParams$Builder0) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).setKemParams(((EciesHkdfKemParams)eciesHkdfKemParams$Builder0.build()));
            return this;
        }

        public Builder setKemParams(EciesHkdfKemParams eciesHkdfKemParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfParams)this.instance).setKemParams(eciesHkdfKemParams0);
            return this;
        }
    }

    private static final EciesAeadHkdfParams DEFAULT_INSTANCE = null;
    public static final int DEM_PARAMS_FIELD_NUMBER = 2;
    public static final int EC_POINT_FORMAT_FIELD_NUMBER = 3;
    public static final int KEM_PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private EciesAeadDemParams demParams_;
    private int ecPointFormat_;
    private EciesHkdfKemParams kemParams_;

    static {
        EciesAeadHkdfParams eciesAeadHkdfParams0 = new EciesAeadHkdfParams();
        EciesAeadHkdfParams.DEFAULT_INSTANCE = eciesAeadHkdfParams0;
        GeneratedMessageLite.registerDefaultInstance(EciesAeadHkdfParams.class, eciesAeadHkdfParams0);
    }

    private void clearDemParams() {
        this.demParams_ = null;
    }

    private void clearEcPointFormat() {
        this.ecPointFormat_ = 0;
    }

    private void clearKemParams() {
        this.kemParams_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EciesAeadHkdfParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EciesAeadHkdfParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EciesAeadHkdfParams.newMessageInfo(EciesAeadHkdfParams.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"kemParams_", "demParams_", "ecPointFormat_"});
            }
            case 4: {
                return EciesAeadHkdfParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EciesAeadHkdfParams.PARSER;
                if(parser0 == null) {
                    Class class0 = EciesAeadHkdfParams.class;
                    synchronized(class0) {
                        Parser parser1 = EciesAeadHkdfParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EciesAeadHkdfParams.DEFAULT_INSTANCE);
                            EciesAeadHkdfParams.PARSER = parser1;
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

    public static EciesAeadHkdfParams getDefaultInstance() {
        return EciesAeadHkdfParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public EciesAeadDemParams getDemParams() {
        return this.demParams_ == null ? EciesAeadDemParams.getDefaultInstance() : this.demParams_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public EcPointFormat getEcPointFormat() {
        EcPointFormat ecPointFormat0 = EcPointFormat.forNumber(this.ecPointFormat_);
        return ecPointFormat0 == null ? EcPointFormat.UNRECOGNIZED : ecPointFormat0;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public int getEcPointFormatValue() {
        return this.ecPointFormat_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public EciesHkdfKemParams getKemParams() {
        return this.kemParams_ == null ? EciesHkdfKemParams.getDefaultInstance() : this.kemParams_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public boolean hasDemParams() {
        return this.demParams_ != null;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public boolean hasKemParams() {
        return this.kemParams_ != null;
    }

    private void mergeDemParams(EciesAeadDemParams eciesAeadDemParams0) {
        eciesAeadDemParams0.getClass();
        if(this.demParams_ != null && this.demParams_ != EciesAeadDemParams.getDefaultInstance()) {
            this.demParams_ = (EciesAeadDemParams)((com.google.crypto.tink.proto.EciesAeadDemParams.Builder)EciesAeadDemParams.newBuilder(this.demParams_).mergeFrom(eciesAeadDemParams0)).buildPartial();
            return;
        }
        this.demParams_ = eciesAeadDemParams0;
    }

    private void mergeKemParams(EciesHkdfKemParams eciesHkdfKemParams0) {
        eciesHkdfKemParams0.getClass();
        if(this.kemParams_ != null && this.kemParams_ != EciesHkdfKemParams.getDefaultInstance()) {
            this.kemParams_ = (EciesHkdfKemParams)((com.google.crypto.tink.proto.EciesHkdfKemParams.Builder)EciesHkdfKemParams.newBuilder(this.kemParams_).mergeFrom(eciesHkdfKemParams0)).buildPartial();
            return;
        }
        this.kemParams_ = eciesHkdfKemParams0;
    }

    public static Builder newBuilder() {
        return (Builder)EciesAeadHkdfParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EciesAeadHkdfParams eciesAeadHkdfParams0) {
        return (Builder)EciesAeadHkdfParams.DEFAULT_INSTANCE.createBuilder(eciesAeadHkdfParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EciesAeadHkdfParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfParams)EciesAeadHkdfParams.parseDelimitedFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfParams)EciesAeadHkdfParams.parseDelimitedFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, byteString0);
    }

    public static EciesAeadHkdfParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EciesAeadHkdfParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfParams parseFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EciesAeadHkdfParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, arr_b);
    }

    public static EciesAeadHkdfParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams)GeneratedMessageLite.parseFrom(EciesAeadHkdfParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EciesAeadHkdfParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setDemParams(EciesAeadDemParams eciesAeadDemParams0) {
        eciesAeadDemParams0.getClass();
        this.demParams_ = eciesAeadDemParams0;
    }

    private void setEcPointFormat(EcPointFormat ecPointFormat0) {
        this.ecPointFormat_ = ecPointFormat0.getNumber();
    }

    private void setEcPointFormatValue(int v) {
        this.ecPointFormat_ = v;
    }

    private void setKemParams(EciesHkdfKemParams eciesHkdfKemParams0) {
        eciesHkdfKemParams0.getClass();
        this.kemParams_ = eciesHkdfKemParams0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

