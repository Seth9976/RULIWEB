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

public final class AesCtrHmacAeadKeyFormat extends GeneratedMessageLite implements AesCtrHmacAeadKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCtrHmacAeadKeyFormatOrBuilder {
        private Builder() {
            super(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat.1 aesCtrHmacAeadKeyFormat$10) {
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

        public Builder clearAesCtrKeyFormat() {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).clearAesCtrKeyFormat();
            return this;
        }

        public Builder clearHmacKeyFormat() {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).clearHmacKeyFormat();
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

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
        public AesCtrKeyFormat getAesCtrKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat)this.instance).getAesCtrKeyFormat();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
        public HmacKeyFormat getHmacKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat)this.instance).getHmacKeyFormat();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
        public boolean hasAesCtrKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat)this.instance).hasAesCtrKeyFormat();
        }

        @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
        public boolean hasHmacKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat)this.instance).hasHmacKeyFormat();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        public Builder mergeAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).mergeAesCtrKeyFormat(aesCtrKeyFormat0);
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

        public Builder mergeHmacKeyFormat(HmacKeyFormat hmacKeyFormat0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).mergeHmacKeyFormat(hmacKeyFormat0);
            return this;
        }

        public Builder setAesCtrKeyFormat(com.google.crypto.tink.proto.AesCtrKeyFormat.Builder aesCtrKeyFormat$Builder0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).setAesCtrKeyFormat(((AesCtrKeyFormat)aesCtrKeyFormat$Builder0.build()));
            return this;
        }

        public Builder setAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).setAesCtrKeyFormat(aesCtrKeyFormat0);
            return this;
        }

        public Builder setHmacKeyFormat(com.google.crypto.tink.proto.HmacKeyFormat.Builder hmacKeyFormat$Builder0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).setHmacKeyFormat(((HmacKeyFormat)hmacKeyFormat$Builder0.build()));
            return this;
        }

        public Builder setHmacKeyFormat(HmacKeyFormat hmacKeyFormat0) {
            this.copyOnWrite();
            ((AesCtrHmacAeadKeyFormat)this.instance).setHmacKeyFormat(hmacKeyFormat0);
            return this;
        }
    }

    public static final int AES_CTR_KEY_FORMAT_FIELD_NUMBER = 1;
    private static final AesCtrHmacAeadKeyFormat DEFAULT_INSTANCE = null;
    public static final int HMAC_KEY_FORMAT_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private AesCtrKeyFormat aesCtrKeyFormat_;
    private HmacKeyFormat hmacKeyFormat_;

    static {
        AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat0 = new AesCtrHmacAeadKeyFormat();
        AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE = aesCtrHmacAeadKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(AesCtrHmacAeadKeyFormat.class, aesCtrHmacAeadKeyFormat0);
    }

    private void clearAesCtrKeyFormat() {
        this.aesCtrKeyFormat_ = null;
    }

    private void clearHmacKeyFormat() {
        this.hmacKeyFormat_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCtrHmacAeadKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCtrHmacAeadKeyFormat.newMessageInfo(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"aesCtrKeyFormat_", "hmacKeyFormat_"});
            }
            case 4: {
                return AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCtrHmacAeadKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCtrHmacAeadKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = AesCtrHmacAeadKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE);
                            AesCtrHmacAeadKeyFormat.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
    public AesCtrKeyFormat getAesCtrKeyFormat() {
        return this.aesCtrKeyFormat_ == null ? AesCtrKeyFormat.getDefaultInstance() : this.aesCtrKeyFormat_;
    }

    public static AesCtrHmacAeadKeyFormat getDefaultInstance() {
        return AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
    public HmacKeyFormat getHmacKeyFormat() {
        return this.hmacKeyFormat_ == null ? HmacKeyFormat.getDefaultInstance() : this.hmacKeyFormat_;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
    public boolean hasAesCtrKeyFormat() {
        return this.aesCtrKeyFormat_ != null;
    }

    @Override  // com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormatOrBuilder
    public boolean hasHmacKeyFormat() {
        return this.hmacKeyFormat_ != null;
    }

    private void mergeAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat0) {
        aesCtrKeyFormat0.getClass();
        if(this.aesCtrKeyFormat_ != null && this.aesCtrKeyFormat_ != AesCtrKeyFormat.getDefaultInstance()) {
            this.aesCtrKeyFormat_ = (AesCtrKeyFormat)((com.google.crypto.tink.proto.AesCtrKeyFormat.Builder)AesCtrKeyFormat.newBuilder(this.aesCtrKeyFormat_).mergeFrom(aesCtrKeyFormat0)).buildPartial();
            return;
        }
        this.aesCtrKeyFormat_ = aesCtrKeyFormat0;
    }

    private void mergeHmacKeyFormat(HmacKeyFormat hmacKeyFormat0) {
        hmacKeyFormat0.getClass();
        if(this.hmacKeyFormat_ != null && this.hmacKeyFormat_ != HmacKeyFormat.getDefaultInstance()) {
            this.hmacKeyFormat_ = (HmacKeyFormat)((com.google.crypto.tink.proto.HmacKeyFormat.Builder)HmacKeyFormat.newBuilder(this.hmacKeyFormat_).mergeFrom(hmacKeyFormat0)).buildPartial();
            return;
        }
        this.hmacKeyFormat_ = hmacKeyFormat0;
    }

    public static Builder newBuilder() {
        return (Builder)AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat0) {
        return (Builder)AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE.createBuilder(aesCtrHmacAeadKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCtrHmacAeadKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacAeadKeyFormat)AesCtrHmacAeadKeyFormat.parseDelimitedFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacAeadKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacAeadKeyFormat)AesCtrHmacAeadKeyFormat.parseDelimitedFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat)GeneratedMessageLite.parseFrom(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat0) {
        aesCtrKeyFormat0.getClass();
        this.aesCtrKeyFormat_ = aesCtrKeyFormat0;
    }

    private void setHmacKeyFormat(HmacKeyFormat hmacKeyFormat0) {
        hmacKeyFormat0.getClass();
        this.hmacKeyFormat_ = hmacKeyFormat0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

