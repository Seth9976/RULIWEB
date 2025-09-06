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

public final class AesGcmHkdfStreamingKeyFormat extends GeneratedMessageLite implements AesGcmHkdfStreamingKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesGcmHkdfStreamingKeyFormatOrBuilder {
        private Builder() {
            super(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat.1 aesGcmHkdfStreamingKeyFormat$10) {
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
            ((AesGcmHkdfStreamingKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
        public int getKeySize() {
            return ((AesGcmHkdfStreamingKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
        public AesGcmHkdfStreamingParams getParams() {
            return ((AesGcmHkdfStreamingKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
        public int getVersion() {
            return ((AesGcmHkdfStreamingKeyFormat)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
        public boolean hasParams() {
            return ((AesGcmHkdfStreamingKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat)this.instance).mergeParams(aesGcmHkdfStreamingParams0);
            return this;
        }

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder aesGcmHkdfStreamingParams$Builder0) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat)this.instance).setParams(((AesGcmHkdfStreamingParams)aesGcmHkdfStreamingParams$Builder0.build()));
            return this;
        }

        public Builder setParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat)this.instance).setParams(aesGcmHkdfStreamingParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesGcmHkdfStreamingKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    private int keySize_;
    private AesGcmHkdfStreamingParams params_;
    private int version_;

    static {
        AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat0 = new AesGcmHkdfStreamingKeyFormat();
        AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE = aesGcmHkdfStreamingKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(AesGcmHkdfStreamingKeyFormat.class, aesGcmHkdfStreamingKeyFormat0);
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
        switch(com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesGcmHkdfStreamingKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesGcmHkdfStreamingKeyFormat.newMessageInfo(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000B\u0003\u000B", new Object[]{"params_", "keySize_", "version_"});
            }
            case 4: {
                return AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesGcmHkdfStreamingKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = AesGcmHkdfStreamingKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = AesGcmHkdfStreamingKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE);
                            AesGcmHkdfStreamingKeyFormat.PARSER = parser1;
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

    public static AesGcmHkdfStreamingKeyFormat getDefaultInstance() {
        return AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
    public AesGcmHkdfStreamingParams getParams() {
        return this.params_ == null ? AesGcmHkdfStreamingParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0) {
        aesGcmHkdfStreamingParams0.getClass();
        if(this.params_ != null && this.params_ != AesGcmHkdfStreamingParams.getDefaultInstance()) {
            this.params_ = (AesGcmHkdfStreamingParams)((com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder)AesGcmHkdfStreamingParams.newBuilder(this.params_).mergeFrom(aesGcmHkdfStreamingParams0)).buildPartial();
            return;
        }
        this.params_ = aesGcmHkdfStreamingParams0;
    }

    public static Builder newBuilder() {
        return (Builder)AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat0) {
        return (Builder)AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE.createBuilder(aesGcmHkdfStreamingKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesGcmHkdfStreamingKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat)AesGcmHkdfStreamingKeyFormat.parseDelimitedFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat)AesGcmHkdfStreamingKeyFormat.parseDelimitedFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0) {
        aesGcmHkdfStreamingParams0.getClass();
        this.params_ = aesGcmHkdfStreamingParams0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

