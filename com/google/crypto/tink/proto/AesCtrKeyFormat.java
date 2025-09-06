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

public final class AesCtrKeyFormat extends GeneratedMessageLite implements AesCtrKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCtrKeyFormatOrBuilder {
        private Builder() {
            super(AesCtrKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCtrKeyFormat.1 aesCtrKeyFormat$10) {
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
            ((AesCtrKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((AesCtrKeyFormat)this.instance).clearParams();
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

        @Override  // com.google.crypto.tink.proto.AesCtrKeyFormatOrBuilder
        public int getKeySize() {
            return ((AesCtrKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrKeyFormatOrBuilder
        public AesCtrParams getParams() {
            return ((AesCtrKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrKeyFormatOrBuilder
        public boolean hasParams() {
            return ((AesCtrKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(AesCtrParams aesCtrParams0) {
            this.copyOnWrite();
            ((AesCtrKeyFormat)this.instance).mergeParams(aesCtrParams0);
            return this;
        }

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((AesCtrKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesCtrParams.Builder aesCtrParams$Builder0) {
            this.copyOnWrite();
            ((AesCtrKeyFormat)this.instance).setParams(((AesCtrParams)aesCtrParams$Builder0.build()));
            return this;
        }

        public Builder setParams(AesCtrParams aesCtrParams0) {
            this.copyOnWrite();
            ((AesCtrKeyFormat)this.instance).setParams(aesCtrParams0);
            return this;
        }
    }

    private static final AesCtrKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int keySize_;
    private AesCtrParams params_;

    static {
        AesCtrKeyFormat aesCtrKeyFormat0 = new AesCtrKeyFormat();
        AesCtrKeyFormat.DEFAULT_INSTANCE = aesCtrKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(AesCtrKeyFormat.class, aesCtrKeyFormat0);
    }

    private void clearKeySize() {
        this.keySize_ = 0;
    }

    private void clearParams() {
        this.params_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCtrKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCtrKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCtrKeyFormat.newMessageInfo(AesCtrKeyFormat.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000B", new Object[]{"params_", "keySize_"});
            }
            case 4: {
                return AesCtrKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCtrKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCtrKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = AesCtrKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCtrKeyFormat.DEFAULT_INSTANCE);
                            AesCtrKeyFormat.PARSER = parser1;
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

    public static AesCtrKeyFormat getDefaultInstance() {
        return AesCtrKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCtrKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrKeyFormatOrBuilder
    public AesCtrParams getParams() {
        return this.params_ == null ? AesCtrParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(AesCtrParams aesCtrParams0) {
        aesCtrParams0.getClass();
        if(this.params_ != null && this.params_ != AesCtrParams.getDefaultInstance()) {
            this.params_ = (AesCtrParams)((com.google.crypto.tink.proto.AesCtrParams.Builder)AesCtrParams.newBuilder(this.params_).mergeFrom(aesCtrParams0)).buildPartial();
            return;
        }
        this.params_ = aesCtrParams0;
    }

    public static Builder newBuilder() {
        return (Builder)AesCtrKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCtrKeyFormat aesCtrKeyFormat0) {
        return (Builder)AesCtrKeyFormat.DEFAULT_INSTANCE.createBuilder(aesCtrKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCtrKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCtrKeyFormat)AesCtrKeyFormat.parseDelimitedFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrKeyFormat)AesCtrKeyFormat.parseDelimitedFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCtrKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCtrKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCtrKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCtrKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCtrKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCtrKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCtrKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat)GeneratedMessageLite.parseFrom(AesCtrKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCtrKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setParams(AesCtrParams aesCtrParams0) {
        aesCtrParams0.getClass();
        this.params_ = aesCtrParams0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

