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

public final class AesCtrHmacStreamingKey extends GeneratedMessageLite implements AesCtrHmacStreamingKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCtrHmacStreamingKeyOrBuilder {
        private Builder() {
            super(AesCtrHmacStreamingKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCtrHmacStreamingKey.1 aesCtrHmacStreamingKey$10) {
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
            ((AesCtrHmacStreamingKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
        public ByteString getKeyValue() {
            return ((AesCtrHmacStreamingKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
        public AesCtrHmacStreamingParams getParams() {
            return ((AesCtrHmacStreamingKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
        public int getVersion() {
            return ((AesCtrHmacStreamingKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
        public boolean hasParams() {
            return ((AesCtrHmacStreamingKey)this.instance).hasParams();
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
            ((AesCtrHmacStreamingKey)this.instance).mergeParams(aesCtrHmacStreamingParams0);
            return this;
        }

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder aesCtrHmacStreamingParams$Builder0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKey)this.instance).setParams(((AesCtrHmacStreamingParams)aesCtrHmacStreamingParams$Builder0.build()));
            return this;
        }

        public Builder setParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams0) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKey)this.instance).setParams(aesCtrHmacStreamingParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesCtrHmacStreamingKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesCtrHmacStreamingKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private AesCtrHmacStreamingParams params_;
    private int version_;

    static {
        AesCtrHmacStreamingKey aesCtrHmacStreamingKey0 = new AesCtrHmacStreamingKey();
        AesCtrHmacStreamingKey.DEFAULT_INSTANCE = aesCtrHmacStreamingKey0;
        GeneratedMessageLite.registerDefaultInstance(AesCtrHmacStreamingKey.class, aesCtrHmacStreamingKey0);
    }

    private AesCtrHmacStreamingKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = AesCtrHmacStreamingKey.getDefaultInstance().getKeyValue();
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCtrHmacStreamingKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCtrHmacStreamingKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCtrHmacStreamingKey.newMessageInfo(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"version_", "params_", "keyValue_"});
            }
            case 4: {
                return AesCtrHmacStreamingKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCtrHmacStreamingKey.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCtrHmacStreamingKey.class;
                    synchronized(class0) {
                        Parser parser1 = AesCtrHmacStreamingKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCtrHmacStreamingKey.DEFAULT_INSTANCE);
                            AesCtrHmacStreamingKey.PARSER = parser1;
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

    public static AesCtrHmacStreamingKey getDefaultInstance() {
        return AesCtrHmacStreamingKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
    public AesCtrHmacStreamingParams getParams() {
        return this.params_ == null ? AesCtrHmacStreamingParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacStreamingKeyOrBuilder
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
        return (Builder)AesCtrHmacStreamingKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCtrHmacStreamingKey aesCtrHmacStreamingKey0) {
        return (Builder)AesCtrHmacStreamingKey.DEFAULT_INSTANCE.createBuilder(aesCtrHmacStreamingKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCtrHmacStreamingKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacStreamingKey)AesCtrHmacStreamingKey.parseDelimitedFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacStreamingKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingKey)AesCtrHmacStreamingKey.parseDelimitedFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCtrHmacStreamingKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCtrHmacStreamingKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKey parseFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacStreamingKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCtrHmacStreamingKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCtrHmacStreamingKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCtrHmacStreamingKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey)GeneratedMessageLite.parseFrom(AesCtrHmacStreamingKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCtrHmacStreamingKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyValue(ByteString byteString0) {
        byteString0.getClass();
        this.keyValue_ = byteString0;
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

