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

public final class Ed25519PublicKey extends GeneratedMessageLite implements Ed25519PublicKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements Ed25519PublicKeyOrBuilder {
        private Builder() {
            super(Ed25519PublicKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.Ed25519PublicKey.1 ed25519PublicKey$10) {
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
            ((Ed25519PublicKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((Ed25519PublicKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.Ed25519PublicKeyOrBuilder
        public ByteString getKeyValue() {
            return ((Ed25519PublicKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.Ed25519PublicKeyOrBuilder
        public int getVersion() {
            return ((Ed25519PublicKey)this.instance).getVersion();
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
            ((Ed25519PublicKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((Ed25519PublicKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final Ed25519PublicKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private int version_;

    static {
        Ed25519PublicKey ed25519PublicKey0 = new Ed25519PublicKey();
        Ed25519PublicKey.DEFAULT_INSTANCE = ed25519PublicKey0;
        GeneratedMessageLite.registerDefaultInstance(Ed25519PublicKey.class, ed25519PublicKey0);
    }

    private Ed25519PublicKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = Ed25519PublicKey.getDefaultInstance().getKeyValue();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.Ed25519PublicKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Ed25519PublicKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Ed25519PublicKey.newMessageInfo(Ed25519PublicKey.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\n", new Object[]{"version_", "keyValue_"});
            }
            case 4: {
                return Ed25519PublicKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Ed25519PublicKey.PARSER;
                if(parser0 == null) {
                    Class class0 = Ed25519PublicKey.class;
                    synchronized(class0) {
                        Parser parser1 = Ed25519PublicKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Ed25519PublicKey.DEFAULT_INSTANCE);
                            Ed25519PublicKey.PARSER = parser1;
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

    public static Ed25519PublicKey getDefaultInstance() {
        return Ed25519PublicKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.Ed25519PublicKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.Ed25519PublicKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)Ed25519PublicKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Ed25519PublicKey ed25519PublicKey0) {
        return (Builder)Ed25519PublicKey.DEFAULT_INSTANCE.createBuilder(ed25519PublicKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static Ed25519PublicKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Ed25519PublicKey)Ed25519PublicKey.parseDelimitedFrom(Ed25519PublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static Ed25519PublicKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Ed25519PublicKey)Ed25519PublicKey.parseDelimitedFrom(Ed25519PublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Ed25519PublicKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, byteString0);
    }

    public static Ed25519PublicKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Ed25519PublicKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Ed25519PublicKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Ed25519PublicKey parseFrom(InputStream inputStream0) throws IOException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static Ed25519PublicKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Ed25519PublicKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Ed25519PublicKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Ed25519PublicKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, arr_b);
    }

    public static Ed25519PublicKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Ed25519PublicKey)GeneratedMessageLite.parseFrom(Ed25519PublicKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Ed25519PublicKey.DEFAULT_INSTANCE.getParserForType();
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

