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

public final class HpkeParams extends GeneratedMessageLite implements HpkeParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HpkeParamsOrBuilder {
        private Builder() {
            super(HpkeParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HpkeParams.1 hpkeParams$10) {
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

        public Builder clearAead() {
            this.copyOnWrite();
            ((HpkeParams)this.instance).clearAead();
            return this;
        }

        public Builder clearKdf() {
            this.copyOnWrite();
            ((HpkeParams)this.instance).clearKdf();
            return this;
        }

        public Builder clearKem() {
            this.copyOnWrite();
            ((HpkeParams)this.instance).clearKem();
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

        @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
        public HpkeAead getAead() {
            return ((HpkeParams)this.instance).getAead();
        }

        @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
        public int getAeadValue() {
            return ((HpkeParams)this.instance).getAeadValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
        public HpkeKdf getKdf() {
            return ((HpkeParams)this.instance).getKdf();
        }

        @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
        public int getKdfValue() {
            return ((HpkeParams)this.instance).getKdfValue();
        }

        @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
        public HpkeKem getKem() {
            return ((HpkeParams)this.instance).getKem();
        }

        @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
        public int getKemValue() {
            return ((HpkeParams)this.instance).getKemValue();
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

        public Builder setAead(HpkeAead hpkeAead0) {
            this.copyOnWrite();
            ((HpkeParams)this.instance).setAead(hpkeAead0);
            return this;
        }

        public Builder setAeadValue(int v) {
            this.copyOnWrite();
            ((HpkeParams)this.instance).setAeadValue(v);
            return this;
        }

        public Builder setKdf(HpkeKdf hpkeKdf0) {
            this.copyOnWrite();
            ((HpkeParams)this.instance).setKdf(hpkeKdf0);
            return this;
        }

        public Builder setKdfValue(int v) {
            this.copyOnWrite();
            ((HpkeParams)this.instance).setKdfValue(v);
            return this;
        }

        public Builder setKem(HpkeKem hpkeKem0) {
            this.copyOnWrite();
            ((HpkeParams)this.instance).setKem(hpkeKem0);
            return this;
        }

        public Builder setKemValue(int v) {
            this.copyOnWrite();
            ((HpkeParams)this.instance).setKemValue(v);
            return this;
        }
    }

    public static final int AEAD_FIELD_NUMBER = 3;
    private static final HpkeParams DEFAULT_INSTANCE = null;
    public static final int KDF_FIELD_NUMBER = 2;
    public static final int KEM_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int aead_;
    private int kdf_;
    private int kem_;

    static {
        HpkeParams hpkeParams0 = new HpkeParams();
        HpkeParams.DEFAULT_INSTANCE = hpkeParams0;
        GeneratedMessageLite.registerDefaultInstance(HpkeParams.class, hpkeParams0);
    }

    private void clearAead() {
        this.aead_ = 0;
    }

    private void clearKdf() {
        this.kdf_ = 0;
    }

    private void clearKem() {
        this.kem_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.HpkeParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HpkeParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HpkeParams.newMessageInfo(HpkeParams.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"kem_", "kdf_", "aead_"});
            }
            case 4: {
                return HpkeParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HpkeParams.PARSER;
                if(parser0 == null) {
                    Class class0 = HpkeParams.class;
                    synchronized(class0) {
                        Parser parser1 = HpkeParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HpkeParams.DEFAULT_INSTANCE);
                            HpkeParams.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
    public HpkeAead getAead() {
        HpkeAead hpkeAead0 = HpkeAead.forNumber(this.aead_);
        return hpkeAead0 == null ? HpkeAead.UNRECOGNIZED : hpkeAead0;
    }

    @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
    public int getAeadValue() {
        return this.aead_;
    }

    public static HpkeParams getDefaultInstance() {
        return HpkeParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
    public HpkeKdf getKdf() {
        HpkeKdf hpkeKdf0 = HpkeKdf.forNumber(this.kdf_);
        return hpkeKdf0 == null ? HpkeKdf.UNRECOGNIZED : hpkeKdf0;
    }

    @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
    public int getKdfValue() {
        return this.kdf_;
    }

    @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
    public HpkeKem getKem() {
        HpkeKem hpkeKem0 = HpkeKem.forNumber(this.kem_);
        return hpkeKem0 == null ? HpkeKem.UNRECOGNIZED : hpkeKem0;
    }

    @Override  // com.google.crypto.tink.proto.HpkeParamsOrBuilder
    public int getKemValue() {
        return this.kem_;
    }

    public static Builder newBuilder() {
        return (Builder)HpkeParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HpkeParams hpkeParams0) {
        return (Builder)HpkeParams.DEFAULT_INSTANCE.createBuilder(hpkeParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HpkeParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HpkeParams)HpkeParams.parseDelimitedFrom(HpkeParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkeParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkeParams)HpkeParams.parseDelimitedFrom(HpkeParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkeParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, byteString0);
    }

    public static HpkeParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HpkeParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HpkeParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HpkeParams parseFrom(InputStream inputStream0) throws IOException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkeParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkeParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HpkeParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HpkeParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, arr_b);
    }

    public static HpkeParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkeParams)GeneratedMessageLite.parseFrom(HpkeParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HpkeParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAead(HpkeAead hpkeAead0) {
        this.aead_ = hpkeAead0.getNumber();
    }

    private void setAeadValue(int v) {
        this.aead_ = v;
    }

    private void setKdf(HpkeKdf hpkeKdf0) {
        this.kdf_ = hpkeKdf0.getNumber();
    }

    private void setKdfValue(int v) {
        this.kdf_ = v;
    }

    private void setKem(HpkeKem hpkeKem0) {
        this.kem_ = hpkeKem0.getNumber();
    }

    private void setKemValue(int v) {
        this.kem_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

