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

public final class EciesAeadDemParams extends GeneratedMessageLite implements EciesAeadDemParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EciesAeadDemParamsOrBuilder {
        private Builder() {
            super(EciesAeadDemParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EciesAeadDemParams.1 eciesAeadDemParams$10) {
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

        public Builder clearAeadDem() {
            this.copyOnWrite();
            ((EciesAeadDemParams)this.instance).clearAeadDem();
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

        @Override  // com.google.crypto.tink.proto.EciesAeadDemParamsOrBuilder
        public KeyTemplate getAeadDem() {
            return ((EciesAeadDemParams)this.instance).getAeadDem();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadDemParamsOrBuilder
        public boolean hasAeadDem() {
            return ((EciesAeadDemParams)this.instance).hasAeadDem();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        public Builder mergeAeadDem(KeyTemplate keyTemplate0) {
            this.copyOnWrite();
            ((EciesAeadDemParams)this.instance).mergeAeadDem(keyTemplate0);
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

        public Builder setAeadDem(com.google.crypto.tink.proto.KeyTemplate.Builder keyTemplate$Builder0) {
            this.copyOnWrite();
            ((EciesAeadDemParams)this.instance).setAeadDem(((KeyTemplate)keyTemplate$Builder0.build()));
            return this;
        }

        public Builder setAeadDem(KeyTemplate keyTemplate0) {
            this.copyOnWrite();
            ((EciesAeadDemParams)this.instance).setAeadDem(keyTemplate0);
            return this;
        }
    }

    public static final int AEAD_DEM_FIELD_NUMBER = 2;
    private static final EciesAeadDemParams DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private KeyTemplate aeadDem_;

    static {
        EciesAeadDemParams eciesAeadDemParams0 = new EciesAeadDemParams();
        EciesAeadDemParams.DEFAULT_INSTANCE = eciesAeadDemParams0;
        GeneratedMessageLite.registerDefaultInstance(EciesAeadDemParams.class, eciesAeadDemParams0);
    }

    private void clearAeadDem() {
        this.aeadDem_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EciesAeadDemParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EciesAeadDemParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EciesAeadDemParams.newMessageInfo(EciesAeadDemParams.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"aeadDem_"});
            }
            case 4: {
                return EciesAeadDemParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EciesAeadDemParams.PARSER;
                if(parser0 == null) {
                    Class class0 = EciesAeadDemParams.class;
                    synchronized(class0) {
                        Parser parser1 = EciesAeadDemParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EciesAeadDemParams.DEFAULT_INSTANCE);
                            EciesAeadDemParams.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.EciesAeadDemParamsOrBuilder
    public KeyTemplate getAeadDem() {
        return this.aeadDem_ == null ? KeyTemplate.getDefaultInstance() : this.aeadDem_;
    }

    public static EciesAeadDemParams getDefaultInstance() {
        return EciesAeadDemParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadDemParamsOrBuilder
    public boolean hasAeadDem() {
        return this.aeadDem_ != null;
    }

    private void mergeAeadDem(KeyTemplate keyTemplate0) {
        keyTemplate0.getClass();
        if(this.aeadDem_ != null && this.aeadDem_ != KeyTemplate.getDefaultInstance()) {
            this.aeadDem_ = (KeyTemplate)((com.google.crypto.tink.proto.KeyTemplate.Builder)KeyTemplate.newBuilder(this.aeadDem_).mergeFrom(keyTemplate0)).buildPartial();
            return;
        }
        this.aeadDem_ = keyTemplate0;
    }

    public static Builder newBuilder() {
        return (Builder)EciesAeadDemParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EciesAeadDemParams eciesAeadDemParams0) {
        return (Builder)EciesAeadDemParams.DEFAULT_INSTANCE.createBuilder(eciesAeadDemParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EciesAeadDemParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadDemParams)EciesAeadDemParams.parseDelimitedFrom(EciesAeadDemParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadDemParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadDemParams)EciesAeadDemParams.parseDelimitedFrom(EciesAeadDemParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadDemParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, byteString0);
    }

    public static EciesAeadDemParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EciesAeadDemParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EciesAeadDemParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EciesAeadDemParams parseFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadDemParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadDemParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EciesAeadDemParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EciesAeadDemParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, arr_b);
    }

    public static EciesAeadDemParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams)GeneratedMessageLite.parseFrom(EciesAeadDemParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EciesAeadDemParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAeadDem(KeyTemplate keyTemplate0) {
        keyTemplate0.getClass();
        this.aeadDem_ = keyTemplate0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

