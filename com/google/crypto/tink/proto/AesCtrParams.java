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

public final class AesCtrParams extends GeneratedMessageLite implements AesCtrParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCtrParamsOrBuilder {
        private Builder() {
            super(AesCtrParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCtrParams.1 aesCtrParams$10) {
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
            ((AesCtrParams)this.instance).clearIvSize();
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

        @Override  // com.google.crypto.tink.proto.AesCtrParamsOrBuilder
        public int getIvSize() {
            return ((AesCtrParams)this.instance).getIvSize();
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
            ((AesCtrParams)this.instance).setIvSize(v);
            return this;
        }
    }

    private static final AesCtrParams DEFAULT_INSTANCE = null;
    public static final int IV_SIZE_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int ivSize_;

    static {
        AesCtrParams aesCtrParams0 = new AesCtrParams();
        AesCtrParams.DEFAULT_INSTANCE = aesCtrParams0;
        GeneratedMessageLite.registerDefaultInstance(AesCtrParams.class, aesCtrParams0);
    }

    private void clearIvSize() {
        this.ivSize_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCtrParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCtrParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCtrParams.newMessageInfo(AesCtrParams.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"ivSize_"});
            }
            case 4: {
                return AesCtrParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCtrParams.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCtrParams.class;
                    synchronized(class0) {
                        Parser parser1 = AesCtrParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCtrParams.DEFAULT_INSTANCE);
                            AesCtrParams.PARSER = parser1;
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

    public static AesCtrParams getDefaultInstance() {
        return AesCtrParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCtrParamsOrBuilder
    public int getIvSize() {
        return this.ivSize_;
    }

    public static Builder newBuilder() {
        return (Builder)AesCtrParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCtrParams aesCtrParams0) {
        return (Builder)AesCtrParams.DEFAULT_INSTANCE.createBuilder(aesCtrParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCtrParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCtrParams)AesCtrParams.parseDelimitedFrom(AesCtrParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrParams)AesCtrParams.parseDelimitedFrom(AesCtrParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCtrParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCtrParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCtrParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCtrParams parseFrom(InputStream inputStream0) throws IOException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCtrParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCtrParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCtrParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrParams)GeneratedMessageLite.parseFrom(AesCtrParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCtrParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setIvSize(int v) {
        this.ivSize_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

