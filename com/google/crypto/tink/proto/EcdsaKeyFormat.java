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

public final class EcdsaKeyFormat extends GeneratedMessageLite implements EcdsaKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EcdsaKeyFormatOrBuilder {
        private Builder() {
            super(EcdsaKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EcdsaKeyFormat.1 ecdsaKeyFormat$10) {
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
            ((EcdsaKeyFormat)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((EcdsaKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.EcdsaKeyFormatOrBuilder
        public EcdsaParams getParams() {
            return ((EcdsaKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaKeyFormatOrBuilder
        public int getVersion() {
            return ((EcdsaKeyFormat)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaKeyFormatOrBuilder
        public boolean hasParams() {
            return ((EcdsaKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(EcdsaParams ecdsaParams0) {
            this.copyOnWrite();
            ((EcdsaKeyFormat)this.instance).mergeParams(ecdsaParams0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.EcdsaParams.Builder ecdsaParams$Builder0) {
            this.copyOnWrite();
            ((EcdsaKeyFormat)this.instance).setParams(((EcdsaParams)ecdsaParams$Builder0.build()));
            return this;
        }

        public Builder setParams(EcdsaParams ecdsaParams0) {
            this.copyOnWrite();
            ((EcdsaKeyFormat)this.instance).setParams(ecdsaParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((EcdsaKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    private static final EcdsaKeyFormat DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    private EcdsaParams params_;
    private int version_;

    static {
        EcdsaKeyFormat ecdsaKeyFormat0 = new EcdsaKeyFormat();
        EcdsaKeyFormat.DEFAULT_INSTANCE = ecdsaKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(EcdsaKeyFormat.class, ecdsaKeyFormat0);
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EcdsaKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EcdsaKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EcdsaKeyFormat.newMessageInfo(EcdsaKeyFormat.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\t\u0003\u000B", new Object[]{"params_", "version_"});
            }
            case 4: {
                return EcdsaKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EcdsaKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = EcdsaKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = EcdsaKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EcdsaKeyFormat.DEFAULT_INSTANCE);
                            EcdsaKeyFormat.PARSER = parser1;
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

    public static EcdsaKeyFormat getDefaultInstance() {
        return EcdsaKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EcdsaKeyFormatOrBuilder
    public EcdsaParams getParams() {
        return this.params_ == null ? EcdsaParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(EcdsaParams ecdsaParams0) {
        ecdsaParams0.getClass();
        if(this.params_ != null && this.params_ != EcdsaParams.getDefaultInstance()) {
            this.params_ = (EcdsaParams)((com.google.crypto.tink.proto.EcdsaParams.Builder)EcdsaParams.newBuilder(this.params_).mergeFrom(ecdsaParams0)).buildPartial();
            return;
        }
        this.params_ = ecdsaParams0;
    }

    public static Builder newBuilder() {
        return (Builder)EcdsaKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EcdsaKeyFormat ecdsaKeyFormat0) {
        return (Builder)EcdsaKeyFormat.DEFAULT_INSTANCE.createBuilder(ecdsaKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EcdsaKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EcdsaKeyFormat)EcdsaKeyFormat.parseDelimitedFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static EcdsaKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaKeyFormat)EcdsaKeyFormat.parseDelimitedFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EcdsaKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static EcdsaKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EcdsaKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EcdsaKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EcdsaKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static EcdsaKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EcdsaKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EcdsaKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EcdsaKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static EcdsaKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat)GeneratedMessageLite.parseFrom(EcdsaKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EcdsaKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(EcdsaParams ecdsaParams0) {
        ecdsaParams0.getClass();
        this.params_ = ecdsaParams0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

