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

public final class HpkePrivateKey extends GeneratedMessageLite implements HpkePrivateKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HpkePrivateKeyOrBuilder {
        private Builder() {
            super(HpkePrivateKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HpkePrivateKey.1 hpkePrivateKey$10) {
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

        public Builder clearPrivateKey() {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).clearPrivateKey();
            return this;
        }

        public Builder clearPublicKey() {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).clearPublicKey();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
        public ByteString getPrivateKey() {
            return ((HpkePrivateKey)this.instance).getPrivateKey();
        }

        @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
        public HpkePublicKey getPublicKey() {
            return ((HpkePrivateKey)this.instance).getPublicKey();
        }

        @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
        public int getVersion() {
            return ((HpkePrivateKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
        public boolean hasPublicKey() {
            return ((HpkePrivateKey)this.instance).hasPublicKey();
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

        public Builder mergePublicKey(HpkePublicKey hpkePublicKey0) {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).mergePublicKey(hpkePublicKey0);
            return this;
        }

        public Builder setPrivateKey(ByteString byteString0) {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).setPrivateKey(byteString0);
            return this;
        }

        public Builder setPublicKey(com.google.crypto.tink.proto.HpkePublicKey.Builder hpkePublicKey$Builder0) {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).setPublicKey(((HpkePublicKey)hpkePublicKey$Builder0.build()));
            return this;
        }

        public Builder setPublicKey(HpkePublicKey hpkePublicKey0) {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).setPublicKey(hpkePublicKey0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((HpkePrivateKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final HpkePrivateKey DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int PRIVATE_KEY_FIELD_NUMBER = 3;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString privateKey_;
    private HpkePublicKey publicKey_;
    private int version_;

    static {
        HpkePrivateKey hpkePrivateKey0 = new HpkePrivateKey();
        HpkePrivateKey.DEFAULT_INSTANCE = hpkePrivateKey0;
        GeneratedMessageLite.registerDefaultInstance(HpkePrivateKey.class, hpkePrivateKey0);
    }

    private HpkePrivateKey() {
        this.privateKey_ = ByteString.EMPTY;
    }

    private void clearPrivateKey() {
        this.privateKey_ = HpkePrivateKey.getDefaultInstance().getPrivateKey();
    }

    private void clearPublicKey() {
        this.publicKey_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.HpkePrivateKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HpkePrivateKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HpkePrivateKey.newMessageInfo(HpkePrivateKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"version_", "publicKey_", "privateKey_"});
            }
            case 4: {
                return HpkePrivateKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HpkePrivateKey.PARSER;
                if(parser0 == null) {
                    Class class0 = HpkePrivateKey.class;
                    synchronized(class0) {
                        Parser parser1 = HpkePrivateKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HpkePrivateKey.DEFAULT_INSTANCE);
                            HpkePrivateKey.PARSER = parser1;
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

    public static HpkePrivateKey getDefaultInstance() {
        return HpkePrivateKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
    public ByteString getPrivateKey() {
        return this.privateKey_;
    }

    @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
    public HpkePublicKey getPublicKey() {
        return this.publicKey_ == null ? HpkePublicKey.getDefaultInstance() : this.publicKey_;
    }

    @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.HpkePrivateKeyOrBuilder
    public boolean hasPublicKey() {
        return this.publicKey_ != null;
    }

    private void mergePublicKey(HpkePublicKey hpkePublicKey0) {
        hpkePublicKey0.getClass();
        if(this.publicKey_ != null && this.publicKey_ != HpkePublicKey.getDefaultInstance()) {
            this.publicKey_ = (HpkePublicKey)((com.google.crypto.tink.proto.HpkePublicKey.Builder)HpkePublicKey.newBuilder(this.publicKey_).mergeFrom(hpkePublicKey0)).buildPartial();
            return;
        }
        this.publicKey_ = hpkePublicKey0;
    }

    public static Builder newBuilder() {
        return (Builder)HpkePrivateKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HpkePrivateKey hpkePrivateKey0) {
        return (Builder)HpkePrivateKey.DEFAULT_INSTANCE.createBuilder(hpkePrivateKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HpkePrivateKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HpkePrivateKey)HpkePrivateKey.parseDelimitedFrom(HpkePrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkePrivateKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkePrivateKey)HpkePrivateKey.parseDelimitedFrom(HpkePrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkePrivateKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, byteString0);
    }

    public static HpkePrivateKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HpkePrivateKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HpkePrivateKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HpkePrivateKey parseFrom(InputStream inputStream0) throws IOException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkePrivateKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkePrivateKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HpkePrivateKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HpkePrivateKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, arr_b);
    }

    public static HpkePrivateKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkePrivateKey)GeneratedMessageLite.parseFrom(HpkePrivateKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HpkePrivateKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setPrivateKey(ByteString byteString0) {
        byteString0.getClass();
        this.privateKey_ = byteString0;
    }

    private void setPublicKey(HpkePublicKey hpkePublicKey0) {
        hpkePublicKey0.getClass();
        this.publicKey_ = hpkePublicKey0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

