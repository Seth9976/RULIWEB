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

public final class EciesAeadHkdfPrivateKey extends GeneratedMessageLite implements EciesAeadHkdfPrivateKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EciesAeadHkdfPrivateKeyOrBuilder {
        private Builder() {
            super(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EciesAeadHkdfPrivateKey.1 eciesAeadHkdfPrivateKey$10) {
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
            ((EciesAeadHkdfPrivateKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearPublicKey() {
            this.copyOnWrite();
            ((EciesAeadHkdfPrivateKey)this.instance).clearPublicKey();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((EciesAeadHkdfPrivateKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
        public ByteString getKeyValue() {
            return ((EciesAeadHkdfPrivateKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
        public EciesAeadHkdfPublicKey getPublicKey() {
            return ((EciesAeadHkdfPrivateKey)this.instance).getPublicKey();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
        public int getVersion() {
            return ((EciesAeadHkdfPrivateKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
        public boolean hasPublicKey() {
            return ((EciesAeadHkdfPrivateKey)this.instance).hasPublicKey();
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

        public Builder mergePublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPrivateKey)this.instance).mergePublicKey(eciesAeadHkdfPublicKey0);
            return this;
        }

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPrivateKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setPublicKey(com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.Builder eciesAeadHkdfPublicKey$Builder0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPrivateKey)this.instance).setPublicKey(((EciesAeadHkdfPublicKey)eciesAeadHkdfPublicKey$Builder0.build()));
            return this;
        }

        public Builder setPublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPrivateKey)this.instance).setPublicKey(eciesAeadHkdfPublicKey0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((EciesAeadHkdfPrivateKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final EciesAeadHkdfPrivateKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private EciesAeadHkdfPublicKey publicKey_;
    private int version_;

    static {
        EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey0 = new EciesAeadHkdfPrivateKey();
        EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE = eciesAeadHkdfPrivateKey0;
        GeneratedMessageLite.registerDefaultInstance(EciesAeadHkdfPrivateKey.class, eciesAeadHkdfPrivateKey0);
    }

    private EciesAeadHkdfPrivateKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = EciesAeadHkdfPrivateKey.getDefaultInstance().getKeyValue();
    }

    private void clearPublicKey() {
        this.publicKey_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EciesAeadHkdfPrivateKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EciesAeadHkdfPrivateKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EciesAeadHkdfPrivateKey.newMessageInfo(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"version_", "publicKey_", "keyValue_"});
            }
            case 4: {
                return EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EciesAeadHkdfPrivateKey.PARSER;
                if(parser0 == null) {
                    Class class0 = EciesAeadHkdfPrivateKey.class;
                    synchronized(class0) {
                        Parser parser1 = EciesAeadHkdfPrivateKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE);
                            EciesAeadHkdfPrivateKey.PARSER = parser1;
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

    public static EciesAeadHkdfPrivateKey getDefaultInstance() {
        return EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
    public EciesAeadHkdfPublicKey getPublicKey() {
        return this.publicKey_ == null ? EciesAeadHkdfPublicKey.getDefaultInstance() : this.publicKey_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPrivateKeyOrBuilder
    public boolean hasPublicKey() {
        return this.publicKey_ != null;
    }

    private void mergePublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0) {
        eciesAeadHkdfPublicKey0.getClass();
        if(this.publicKey_ != null && this.publicKey_ != EciesAeadHkdfPublicKey.getDefaultInstance()) {
            this.publicKey_ = (EciesAeadHkdfPublicKey)((com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.Builder)EciesAeadHkdfPublicKey.newBuilder(this.publicKey_).mergeFrom(eciesAeadHkdfPublicKey0)).buildPartial();
            return;
        }
        this.publicKey_ = eciesAeadHkdfPublicKey0;
    }

    public static Builder newBuilder() {
        return (Builder)EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey0) {
        return (Builder)EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE.createBuilder(eciesAeadHkdfPrivateKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EciesAeadHkdfPrivateKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfPrivateKey)EciesAeadHkdfPrivateKey.parseDelimitedFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfPrivateKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfPrivateKey)EciesAeadHkdfPrivateKey.parseDelimitedFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, byteString0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, arr_b);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyValue(ByteString byteString0) {
        byteString0.getClass();
        this.keyValue_ = byteString0;
    }

    private void setPublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0) {
        eciesAeadHkdfPublicKey0.getClass();
        this.publicKey_ = eciesAeadHkdfPublicKey0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

