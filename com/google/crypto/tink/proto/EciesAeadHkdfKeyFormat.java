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

public final class EciesAeadHkdfKeyFormat extends GeneratedMessageLite implements EciesAeadHkdfKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EciesAeadHkdfKeyFormatOrBuilder {
        private Builder() {
            super(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat.1 eciesAeadHkdfKeyFormat$10) {
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

        public Builder clearParams() {
            this.copyOnWrite();
            ((EciesAeadHkdfKeyFormat)this.instance).clearParams();
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

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfKeyFormatOrBuilder
        public EciesAeadHkdfParams getParams() {
            return ((EciesAeadHkdfKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfKeyFormatOrBuilder
        public boolean hasParams() {
            return ((EciesAeadHkdfKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfKeyFormat)this.instance).mergeParams(eciesAeadHkdfParams0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder eciesAeadHkdfParams$Builder0) {
            this.copyOnWrite();
            ((EciesAeadHkdfKeyFormat)this.instance).setParams(((EciesAeadHkdfParams)eciesAeadHkdfParams$Builder0.build()));
            return this;
        }

        public Builder setParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfKeyFormat)this.instance).setParams(eciesAeadHkdfParams0);
            return this;
        }
    }

    private static final EciesAeadHkdfKeyFormat DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private EciesAeadHkdfParams params_;

    static {
        EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat0 = new EciesAeadHkdfKeyFormat();
        EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE = eciesAeadHkdfKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(EciesAeadHkdfKeyFormat.class, eciesAeadHkdfKeyFormat0);
    }

    private void clearParams() {
        this.params_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EciesAeadHkdfKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EciesAeadHkdfKeyFormat.newMessageInfo(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"params_"});
            }
            case 4: {
                return EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EciesAeadHkdfKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = EciesAeadHkdfKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = EciesAeadHkdfKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE);
                            EciesAeadHkdfKeyFormat.PARSER = parser1;
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

    public static EciesAeadHkdfKeyFormat getDefaultInstance() {
        return EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfKeyFormatOrBuilder
    public EciesAeadHkdfParams getParams() {
        return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
        eciesAeadHkdfParams0.getClass();
        if(this.params_ != null && this.params_ != EciesAeadHkdfParams.getDefaultInstance()) {
            this.params_ = (EciesAeadHkdfParams)((com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder)EciesAeadHkdfParams.newBuilder(this.params_).mergeFrom(eciesAeadHkdfParams0)).buildPartial();
            return;
        }
        this.params_ = eciesAeadHkdfParams0;
    }

    public static Builder newBuilder() {
        return (Builder)EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat0) {
        return (Builder)EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE.createBuilder(eciesAeadHkdfKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EciesAeadHkdfKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfKeyFormat)EciesAeadHkdfKeyFormat.parseDelimitedFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfKeyFormat)EciesAeadHkdfKeyFormat.parseDelimitedFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat)GeneratedMessageLite.parseFrom(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
        eciesAeadHkdfParams0.getClass();
        this.params_ = eciesAeadHkdfParams0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

