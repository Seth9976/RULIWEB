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

public final class AesEaxKeyFormat extends GeneratedMessageLite implements AesEaxKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesEaxKeyFormatOrBuilder {
        private Builder() {
            super(AesEaxKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesEaxKeyFormat.1 aesEaxKeyFormat$10) {
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

        public Builder clearKeySize() {
            this.copyOnWrite();
            ((AesEaxKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((AesEaxKeyFormat)this.instance).clearParams();
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

        @Override  // com.google.crypto.tink.proto.AesEaxKeyFormatOrBuilder
        public int getKeySize() {
            return ((AesEaxKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.AesEaxKeyFormatOrBuilder
        public AesEaxParams getParams() {
            return ((AesEaxKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.AesEaxKeyFormatOrBuilder
        public boolean hasParams() {
            return ((AesEaxKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(AesEaxParams aesEaxParams0) {
            this.copyOnWrite();
            ((AesEaxKeyFormat)this.instance).mergeParams(aesEaxParams0);
            return this;
        }

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((AesEaxKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesEaxParams.Builder aesEaxParams$Builder0) {
            this.copyOnWrite();
            ((AesEaxKeyFormat)this.instance).setParams(((AesEaxParams)aesEaxParams$Builder0.build()));
            return this;
        }

        public Builder setParams(AesEaxParams aesEaxParams0) {
            this.copyOnWrite();
            ((AesEaxKeyFormat)this.instance).setParams(aesEaxParams0);
            return this;
        }
    }

    private static final AesEaxKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int keySize_;
    private AesEaxParams params_;

    static {
        AesEaxKeyFormat aesEaxKeyFormat0 = new AesEaxKeyFormat();
        AesEaxKeyFormat.DEFAULT_INSTANCE = aesEaxKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(AesEaxKeyFormat.class, aesEaxKeyFormat0);
    }

    private void clearKeySize() {
        this.keySize_ = 0;
    }

    private void clearParams() {
        this.params_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesEaxKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesEaxKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesEaxKeyFormat.newMessageInfo(AesEaxKeyFormat.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000B", new Object[]{"params_", "keySize_"});
            }
            case 4: {
                return AesEaxKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesEaxKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = AesEaxKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = AesEaxKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesEaxKeyFormat.DEFAULT_INSTANCE);
                            AesEaxKeyFormat.PARSER = parser1;
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

    public static AesEaxKeyFormat getDefaultInstance() {
        return AesEaxKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesEaxKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.AesEaxKeyFormatOrBuilder
    public AesEaxParams getParams() {
        return this.params_ == null ? AesEaxParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.AesEaxKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(AesEaxParams aesEaxParams0) {
        aesEaxParams0.getClass();
        if(this.params_ != null && this.params_ != AesEaxParams.getDefaultInstance()) {
            this.params_ = (AesEaxParams)((com.google.crypto.tink.proto.AesEaxParams.Builder)AesEaxParams.newBuilder(this.params_).mergeFrom(aesEaxParams0)).buildPartial();
            return;
        }
        this.params_ = aesEaxParams0;
    }

    public static Builder newBuilder() {
        return (Builder)AesEaxKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesEaxKeyFormat aesEaxKeyFormat0) {
        return (Builder)AesEaxKeyFormat.DEFAULT_INSTANCE.createBuilder(aesEaxKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesEaxKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesEaxKeyFormat)AesEaxKeyFormat.parseDelimitedFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesEaxKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxKeyFormat)AesEaxKeyFormat.parseDelimitedFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesEaxKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static AesEaxKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesEaxKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesEaxKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesEaxKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesEaxKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesEaxKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesEaxKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesEaxKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static AesEaxKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat)GeneratedMessageLite.parseFrom(AesEaxKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesEaxKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setParams(AesEaxParams aesEaxParams0) {
        aesEaxParams0.getClass();
        this.params_ = aesEaxParams0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

