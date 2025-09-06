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

public final class AesGcmSivKey extends GeneratedMessageLite implements AesGcmSivKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesGcmSivKeyOrBuilder {
        private Builder() {
            super(AesGcmSivKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesGcmSivKey.1 aesGcmSivKey$10) {
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
            ((AesGcmSivKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesGcmSivKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesGcmSivKeyOrBuilder
        public ByteString getKeyValue() {
            return ((AesGcmSivKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.AesGcmSivKeyOrBuilder
        public int getVersion() {
            return ((AesGcmSivKey)this.instance).getVersion();
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

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((AesGcmSivKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesGcmSivKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesGcmSivKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private int version_;

    static {
        AesGcmSivKey aesGcmSivKey0 = new AesGcmSivKey();
        AesGcmSivKey.DEFAULT_INSTANCE = aesGcmSivKey0;
        GeneratedMessageLite.registerDefaultInstance(AesGcmSivKey.class, aesGcmSivKey0);
    }

    private AesGcmSivKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = AesGcmSivKey.getDefaultInstance().getKeyValue();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesGcmSivKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesGcmSivKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesGcmSivKey.newMessageInfo(AesGcmSivKey.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000B\u0003\n", new Object[]{"version_", "keyValue_"});
            }
            case 4: {
                return AesGcmSivKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesGcmSivKey.PARSER;
                if(parser0 == null) {
                    Class class0 = AesGcmSivKey.class;
                    synchronized(class0) {
                        Parser parser1 = AesGcmSivKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesGcmSivKey.DEFAULT_INSTANCE);
                            AesGcmSivKey.PARSER = parser1;
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

    public static AesGcmSivKey getDefaultInstance() {
        return AesGcmSivKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesGcmSivKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.AesGcmSivKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)AesGcmSivKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesGcmSivKey aesGcmSivKey0) {
        return (Builder)AesGcmSivKey.DEFAULT_INSTANCE.createBuilder(aesGcmSivKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesGcmSivKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesGcmSivKey)AesGcmSivKey.parseDelimitedFrom(AesGcmSivKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmSivKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmSivKey)AesGcmSivKey.parseDelimitedFrom(AesGcmSivKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmSivKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, byteString0);
    }

    public static AesGcmSivKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesGcmSivKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesGcmSivKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesGcmSivKey parseFrom(InputStream inputStream0) throws IOException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesGcmSivKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesGcmSivKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesGcmSivKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesGcmSivKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, arr_b);
    }

    public static AesGcmSivKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesGcmSivKey)GeneratedMessageLite.parseFrom(AesGcmSivKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesGcmSivKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyValue(ByteString byteString0) {
        byteString0.getClass();
        this.keyValue_ = byteString0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

