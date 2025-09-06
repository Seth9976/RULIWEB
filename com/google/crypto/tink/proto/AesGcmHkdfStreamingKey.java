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

public final class AesGcmHkdfStreamingKey extends GeneratedMessageLite implements AesGcmHkdfStreamingKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesGcmHkdfStreamingKeyOrBuilder {
        private Builder() {
            super(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesGcmHkdfStreamingKey.1 aesGcmHkdfStreamingKey$10) {
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

        public Builder clearKeyValue() {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
        public ByteString getKeyValue() {
            return ((AesGcmHkdfStreamingKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
        public AesGcmHkdfStreamingParams getParams() {
            return ((AesGcmHkdfStreamingKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
        public int getVersion() {
            return ((AesGcmHkdfStreamingKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
        public boolean hasParams() {
            return ((AesGcmHkdfStreamingKey)this.instance).hasParams();
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
            ((AesGcmHkdfStreamingKey)this.instance).mergeParams(aesGcmHkdfStreamingParams0);
            return this;
        }

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder aesGcmHkdfStreamingParams$Builder0) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKey)this.instance).setParams(((AesGcmHkdfStreamingParams)aesGcmHkdfStreamingParams$Builder0.build()));
            return this;
        }

        public Builder setParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKey)this.instance).setParams(aesGcmHkdfStreamingParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesGcmHkdfStreamingKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesGcmHkdfStreamingKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private AesGcmHkdfStreamingParams params_;
    private int version_;

    static {
        AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey0 = new AesGcmHkdfStreamingKey();
        AesGcmHkdfStreamingKey.DEFAULT_INSTANCE = aesGcmHkdfStreamingKey0;
        GeneratedMessageLite.registerDefaultInstance(AesGcmHkdfStreamingKey.class, aesGcmHkdfStreamingKey0);
    }

    private AesGcmHkdfStreamingKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = AesGcmHkdfStreamingKey.getDefaultInstance().getKeyValue();
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesGcmHkdfStreamingKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesGcmHkdfStreamingKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesGcmHkdfStreamingKey.newMessageInfo(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"version_", "params_", "keyValue_"});
            }
            case 4: {
                return AesGcmHkdfStreamingKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesGcmHkdfStreamingKey.PARSER;
                if(parser0 == null) {
                    Class class0 = AesGcmHkdfStreamingKey.class;
                    synchronized(class0) {
                        Parser parser1 = AesGcmHkdfStreamingKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE);
                            AesGcmHkdfStreamingKey.PARSER = parser1;
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

    public static AesGcmHkdfStreamingKey getDefaultInstance() {
        return AesGcmHkdfStreamingKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
    public AesGcmHkdfStreamingParams getParams() {
        return this.params_ == null ? AesGcmHkdfStreamingParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyOrBuilder
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
        return (Builder)AesGcmHkdfStreamingKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey0) {
        return (Builder)AesGcmHkdfStreamingKey.DEFAULT_INSTANCE.createBuilder(aesGcmHkdfStreamingKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesGcmHkdfStreamingKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesGcmHkdfStreamingKey)AesGcmHkdfStreamingKey.parseDelimitedFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmHkdfStreamingKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingKey)AesGcmHkdfStreamingKey.parseDelimitedFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, byteString0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(InputStream inputStream0) throws IOException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesGcmHkdfStreamingKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, arr_b);
    }

    public static AesGcmHkdfStreamingKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey)GeneratedMessageLite.parseFrom(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesGcmHkdfStreamingKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyValue(ByteString byteString0) {
        byteString0.getClass();
        this.keyValue_ = byteString0;
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

