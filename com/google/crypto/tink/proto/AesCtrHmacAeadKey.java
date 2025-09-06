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

public final class AesCtrHmacAeadKey extends GeneratedMessageLite implements AesCtrHmacAeadKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCtrHmacAeadKeyOrBuilder {
        private Builder() {
            super(AesCtrHmacAeadKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCtrHmacAeadKey.1 aesCtrHmacAeadKey$10) {
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

        public Builder clearAesCtrKey() {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).clearAesCtrKey();
            return this;
        }

        public Builder clearHmacKey() {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).clearHmacKey();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
        public AesCtrKey getAesCtrKey() {
            return ((AesCtrHmacAeadKey)this.instance).getAesCtrKey();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
        public HmacKey getHmacKey() {
            return ((AesCtrHmacAeadKey)this.instance).getHmacKey();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
        public int getVersion() {
            return ((AesCtrHmacAeadKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
        public boolean hasAesCtrKey() {
            return ((AesCtrHmacAeadKey)this.instance).hasAesCtrKey();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
        public boolean hasHmacKey() {
            return ((AesCtrHmacAeadKey)this.instance).hasHmacKey();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        public Builder mergeAesCtrKey(AesCtrKey aesCtrKey0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).mergeAesCtrKey(aesCtrKey0);
            return this;
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

        public Builder mergeHmacKey(HmacKey hmacKey0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).mergeHmacKey(hmacKey0);
            return this;
        }

        public Builder setAesCtrKey(com.google.crypto.tink.proto.AesCtrKey.Builder aesCtrKey$Builder0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).setAesCtrKey(((AesCtrKey)aesCtrKey$Builder0.build()));
            return this;
        }

        public Builder setAesCtrKey(AesCtrKey aesCtrKey0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).setAesCtrKey(aesCtrKey0);
            return this;
        }

        public Builder setHmacKey(com.google.crypto.tink.proto.HmacKey.Builder hmacKey$Builder0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).setHmacKey(((HmacKey)hmacKey$Builder0.build()));
            return this;
        }

        public Builder setHmacKey(HmacKey hmacKey0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).setHmacKey(hmacKey0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKey)this.instance).setVersion(v);
            return this;
        }
    }

    public static final int AES_CTR_KEY_FIELD_NUMBER = 2;
    private static final AesCtrHmacAeadKey DEFAULT_INSTANCE = null;
    public static final int HMAC_KEY_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private AesCtrKey aesCtrKey_;
    private HmacKey hmacKey_;
    private int version_;

    static {
        AesCtrHmacAeadKey aesCtrHmacAeadKey0 = new AesCtrHmacAeadKey();
        AesCtrHmacAeadKey.DEFAULT_INSTANCE = aesCtrHmacAeadKey0;
        GeneratedMessageLite.registerDefaultInstance(AesCtrHmacAeadKey.class, aesCtrHmacAeadKey0);
    }

    private void clearAesCtrKey() {
        this.aesCtrKey_ = null;
    }

    private void clearHmacKey() {
        this.hmacKey_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCtrHmacAeadKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCtrHmacAeadKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCtrHmacAeadKey.newMessageInfo(AesCtrHmacAeadKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\t", new Object[]{"version_", "aesCtrKey_", "hmacKey_"});
            }
            case 4: {
                return AesCtrHmacAeadKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCtrHmacAeadKey.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCtrHmacAeadKey.class;
                    synchronized(class0) {
                        Parser parser1 = AesCtrHmacAeadKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCtrHmacAeadKey.DEFAULT_INSTANCE);
                            AesCtrHmacAeadKey.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
    public AesCtrKey getAesCtrKey() {
        return this.aesCtrKey_ == null ? AesCtrKey.getDefaultInstance() : this.aesCtrKey_;
    }

    public static AesCtrHmacAeadKey getDefaultInstance() {
        return AesCtrHmacAeadKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
    public HmacKey getHmacKey() {
        return this.hmacKey_ == null ? HmacKey.getDefaultInstance() : this.hmacKey_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
    public boolean hasAesCtrKey() {
        return this.aesCtrKey_ != null;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyOrBuilder
    public boolean hasHmacKey() {
        return this.hmacKey_ != null;
    }

    private void mergeAesCtrKey(AesCtrKey aesCtrKey0) {
        aesCtrKey0.getClass();
        if(this.aesCtrKey_ != null && this.aesCtrKey_ != AesCtrKey.getDefaultInstance()) {
            this.aesCtrKey_ = (AesCtrKey)((com.google.crypto.tink.proto.AesCtrKey.Builder)AesCtrKey.newBuilder(this.aesCtrKey_).mergeFrom(aesCtrKey0)).buildPartial();
            return;
        }
        this.aesCtrKey_ = aesCtrKey0;
    }

    private void mergeHmacKey(HmacKey hmacKey0) {
        hmacKey0.getClass();
        if(this.hmacKey_ != null && this.hmacKey_ != HmacKey.getDefaultInstance()) {
            this.hmacKey_ = (HmacKey)((com.google.crypto.tink.proto.HmacKey.Builder)HmacKey.newBuilder(this.hmacKey_).mergeFrom(hmacKey0)).buildPartial();
            return;
        }
        this.hmacKey_ = hmacKey0;
    }

    public static Builder newBuilder() {
        return (Builder)AesCtrHmacAeadKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCtrHmacAeadKey aesCtrHmacAeadKey0) {
        return (Builder)AesCtrHmacAeadKey.DEFAULT_INSTANCE.createBuilder(aesCtrHmacAeadKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCtrHmacAeadKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacAeadKey)AesCtrHmacAeadKey.parseDelimitedFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacAeadKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacAeadKey)AesCtrHmacAeadKey.parseDelimitedFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCtrHmacAeadKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCtrHmacAeadKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKey parseFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacAeadKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCtrHmacAeadKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCtrHmacAeadKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCtrHmacAeadKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAesCtrKey(AesCtrKey aesCtrKey0) {
        aesCtrKey0.getClass();
        this.aesCtrKey_ = aesCtrKey0;
    }

    private void setHmacKey(HmacKey hmacKey0) {
        hmacKey0.getClass();
        this.hmacKey_ = hmacKey0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

