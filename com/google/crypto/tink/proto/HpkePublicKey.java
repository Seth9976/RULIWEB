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

public final class HpkePublicKey extends GeneratedMessageLite implements HpkePublicKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HpkePublicKeyOrBuilder {
        private Builder() {
            super(HpkePublicKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HpkePublicKey.1 hpkePublicKey$10) {
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

        public Builder clearParams() {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).clearParams();
            return this;
        }

        public Builder clearPublicKey() {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).clearPublicKey();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
        public HpkeParams getParams() {
            return ((HpkePublicKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
        public ByteString getPublicKey() {
            return ((HpkePublicKey)this.instance).getPublicKey();
        }

        @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
        public int getVersion() {
            return ((HpkePublicKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
        public boolean hasParams() {
            return ((HpkePublicKey)this.instance).hasParams();
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

        public Builder mergeParams(HpkeParams hpkeParams0) {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).mergeParams(hpkeParams0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.HpkeParams.Builder hpkeParams$Builder0) {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).setParams(((HpkeParams)hpkeParams$Builder0.build()));
            return this;
        }

        public Builder setParams(HpkeParams hpkeParams0) {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).setParams(hpkeParams0);
            return this;
        }

        public Builder setPublicKey(ByteString byteString0) {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).setPublicKey(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((HpkePublicKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final HpkePublicKey DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 3;
    public static final int VERSION_FIELD_NUMBER = 1;
    private HpkeParams params_;
    private ByteString publicKey_;
    private int version_;

    static {
        HpkePublicKey hpkePublicKey0 = new HpkePublicKey();
        HpkePublicKey.DEFAULT_INSTANCE = hpkePublicKey0;
        GeneratedMessageLite.registerDefaultInstance(HpkePublicKey.class, hpkePublicKey0);
    }

    private HpkePublicKey() {
        this.publicKey_ = ByteString.EMPTY;
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearPublicKey() {
        this.publicKey_ = HpkePublicKey.getDefaultInstance().getPublicKey();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.HpkePublicKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HpkePublicKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HpkePublicKey.newMessageInfo(HpkePublicKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"version_", "params_", "publicKey_"});
            }
            case 4: {
                return HpkePublicKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HpkePublicKey.PARSER;
                if(parser0 == null) {
                    Class class0 = HpkePublicKey.class;
                    synchronized(class0) {
                        Parser parser1 = HpkePublicKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HpkePublicKey.DEFAULT_INSTANCE);
                            HpkePublicKey.PARSER = parser1;
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

    public static HpkePublicKey getDefaultInstance() {
        return HpkePublicKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
    public HpkeParams getParams() {
        return this.params_ == null ? HpkeParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
    public ByteString getPublicKey() {
        return this.publicKey_;
    }

    @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.HpkePublicKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(HpkeParams hpkeParams0) {
        hpkeParams0.getClass();
        if(this.params_ != null && this.params_ != HpkeParams.getDefaultInstance()) {
            this.params_ = (HpkeParams)((com.google.crypto.tink.proto.HpkeParams.Builder)HpkeParams.newBuilder(this.params_).mergeFrom(hpkeParams0)).buildPartial();
            return;
        }
        this.params_ = hpkeParams0;
    }

    public static Builder newBuilder() {
        return (Builder)HpkePublicKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HpkePublicKey hpkePublicKey0) {
        return (Builder)HpkePublicKey.DEFAULT_INSTANCE.createBuilder(hpkePublicKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HpkePublicKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HpkePublicKey)HpkePublicKey.parseDelimitedFrom(HpkePublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkePublicKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkePublicKey)HpkePublicKey.parseDelimitedFrom(HpkePublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkePublicKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, byteString0);
    }

    public static HpkePublicKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HpkePublicKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HpkePublicKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HpkePublicKey parseFrom(InputStream inputStream0) throws IOException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkePublicKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkePublicKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HpkePublicKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HpkePublicKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, arr_b);
    }

    public static HpkePublicKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkePublicKey)GeneratedMessageLite.parseFrom(HpkePublicKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HpkePublicKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(HpkeParams hpkeParams0) {
        hpkeParams0.getClass();
        this.params_ = hpkeParams0;
    }

    private void setPublicKey(ByteString byteString0) {
        byteString0.getClass();
        this.publicKey_ = byteString0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

