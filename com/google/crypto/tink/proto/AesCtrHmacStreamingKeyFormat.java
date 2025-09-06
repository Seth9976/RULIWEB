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

public final class AesCtrHmacStreamingKeyFormat extends GeneratedMessageLite implements AesCtrHmacStreamingKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCtrHmacStreamingKeyFormatOrBuilder {
        private Builder() {
            super(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormat.1 aesCtrHmacStreamingKeyFormat$10) {
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
            ((AesCtrHmacStreamingKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
        public int getKeySize() {
            return ((AesCtrHmacStreamingKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
        public AesCtrHmacStreamingParams getParams() {
            return ((AesCtrHmacStreamingKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
        public int getVersion() {
            return ((AesCtrHmacStreamingKeyFormat)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
        public boolean hasParams() {
            return ((AesCtrHmacStreamingKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat)this.instance).mergeParams(aesCtrHmacStreamingParams0);
            return this;
        }

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder aesCtrHmacStreamingParams$Builder0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat)this.instance).setParams(((AesCtrHmacStreamingParams)aesCtrHmacStreamingParams$Builder0.build()));
            return this;
        }

        public Builder setParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat)this.instance).setParams(aesCtrHmacStreamingParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesCtrHmacStreamingKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    private int keySize_;
    private AesCtrHmacStreamingParams params_;
    private int version_;

    static {
        AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat0 = new AesCtrHmacStreamingKeyFormat();
        AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE = aesCtrHmacStreamingKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(AesCtrHmacStreamingKeyFormat.class, aesCtrHmacStreamingKeyFormat0);
    }

    private void clearKeySize() {
        this.keySize_ = 0;
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCtrHmacStreamingKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCtrHmacStreamingKeyFormat.newMessageInfo(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000B\u0003\u000B", new Object[]{"params_", "keySize_", "version_"});
            }
            case 4: {
                return AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCtrHmacStreamingKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCtrHmacStreamingKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = AesCtrHmacStreamingKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE);
                            AesCtrHmacStreamingKeyFormat.PARSER = parser1;
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

    public static AesCtrHmacStreamingKeyFormat getDefaultInstance() {
        return AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
    public AesCtrHmacStreamingParams getParams() {
        return this.params_ == null ? AesCtrHmacStreamingParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams0) {
        aesCtrHmacStreamingParams0.getClass();
        if(this.params_ != null && this.params_ != AesCtrHmacStreamingParams.getDefaultInstance()) {
            this.params_ = (AesCtrHmacStreamingParams)((com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder)AesCtrHmacStreamingParams.newBuilder(this.params_).mergeFrom(aesCtrHmacStreamingParams0)).buildPartial();
            return;
        }
        this.params_ = aesCtrHmacStreamingParams0;
    }

    public static Builder newBuilder() {
        return (Builder)AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat0) {
        return (Builder)AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE.createBuilder(aesCtrHmacStreamingKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCtrHmacStreamingKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacStreamingKeyFormat)AesCtrHmacStreamingKeyFormat.parseDelimitedFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacStreamingKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingKeyFormat)AesCtrHmacStreamingKeyFormat.parseDelimitedFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams0) {
        aesCtrHmacStreamingParams0.getClass();
        this.params_ = aesCtrHmacStreamingParams0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

