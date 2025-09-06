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

public final class AesEaxParams extends GeneratedMessageLite implements AesEaxParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesEaxParamsOrBuilder {
        private Builder() {
            super(AesEaxParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesEaxParams.1 aesEaxParams$10) {
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

        public Builder clearIvSize() {
            this.copyOnWrite();
            ((AesEaxParams)this.instance).clearIvSize();
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

        @Override  // com.google.crypto.tink.proto.AesEaxParamsOrBuilder
        public int getIvSize() {
            return ((AesEaxParams)this.instance).getIvSize();
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

        public Builder setIvSize(int v) {
            this.copyOnWrite();
            ((AesEaxParams)this.instance).setIvSize(v);
            return this;
        }
    }

    private static final AesEaxParams DEFAULT_INSTANCE = null;
    public static final int IV_SIZE_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int ivSize_;

    static {
        AesEaxParams aesEaxParams0 = new AesEaxParams();
        AesEaxParams.DEFAULT_INSTANCE = aesEaxParams0;
        GeneratedMessageLite.registerDefaultInstance(AesEaxParams.class, aesEaxParams0);
    }

    private void clearIvSize() {
        this.ivSize_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesEaxParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesEaxParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesEaxParams.newMessageInfo(AesEaxParams.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"ivSize_"});
            }
            case 4: {
                return AesEaxParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesEaxParams.PARSER;
                if(parser0 == null) {
                    Class class0 = AesEaxParams.class;
                    synchronized(class0) {
                        Parser parser1 = AesEaxParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesEaxParams.DEFAULT_INSTANCE);
                            AesEaxParams.PARSER = parser1;
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

    public static AesEaxParams getDefaultInstance() {
        return AesEaxParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesEaxParamsOrBuilder
    public int getIvSize() {
        return this.ivSize_;
    }

    public static Builder newBuilder() {
        return (Builder)AesEaxParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesEaxParams aesEaxParams0) {
        return (Builder)AesEaxParams.DEFAULT_INSTANCE.createBuilder(aesEaxParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesEaxParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesEaxParams)AesEaxParams.parseDelimitedFrom(AesEaxParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesEaxParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxParams)AesEaxParams.parseDelimitedFrom(AesEaxParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesEaxParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, byteString0);
    }

    public static AesEaxParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesEaxParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesEaxParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesEaxParams parseFrom(InputStream inputStream0) throws IOException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesEaxParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesEaxParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesEaxParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesEaxParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, arr_b);
    }

    public static AesEaxParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxParams)GeneratedMessageLite.parseFrom(AesEaxParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesEaxParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setIvSize(int v) {
        this.ivSize_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

