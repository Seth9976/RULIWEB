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

public final class HmacPrfKeyFormat extends GeneratedMessageLite implements HmacPrfKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HmacPrfKeyFormatOrBuilder {
        private Builder() {
            super(HmacPrfKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HmacPrfKeyFormat.1 hmacPrfKeyFormat$10) {
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
            ((HmacPrfKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((HmacPrfKeyFormat)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((HmacPrfKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public int getKeySize() {
            return ((HmacPrfKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public HmacPrfParams getParams() {
            return ((HmacPrfKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public int getVersion() {
            return ((HmacPrfKeyFormat)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public boolean hasParams() {
            return ((HmacPrfKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(HmacPrfParams hmacPrfParams0) {
            this.copyOnWrite();
            ((HmacPrfKeyFormat)this.instance).mergeParams(hmacPrfParams0);
            return this;
        }

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((HmacPrfKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.HmacPrfParams.Builder hmacPrfParams$Builder0) {
            this.copyOnWrite();
            ((HmacPrfKeyFormat)this.instance).setParams(((HmacPrfParams)hmacPrfParams$Builder0.build()));
            return this;
        }

        public Builder setParams(HmacPrfParams hmacPrfParams0) {
            this.copyOnWrite();
            ((HmacPrfKeyFormat)this.instance).setParams(hmacPrfParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((HmacPrfKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    private static final HmacPrfKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    private int keySize_;
    private HmacPrfParams params_;
    private int version_;

    static {
        HmacPrfKeyFormat hmacPrfKeyFormat0 = new HmacPrfKeyFormat();
        HmacPrfKeyFormat.DEFAULT_INSTANCE = hmacPrfKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(HmacPrfKeyFormat.class, hmacPrfKeyFormat0);
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
        switch(com.google.crypto.tink.proto.HmacPrfKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HmacPrfKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HmacPrfKeyFormat.newMessageInfo(HmacPrfKeyFormat.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000B\u0003\u000B", new Object[]{"params_", "keySize_", "version_"});
            }
            case 4: {
                return HmacPrfKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HmacPrfKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = HmacPrfKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = HmacPrfKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HmacPrfKeyFormat.DEFAULT_INSTANCE);
                            HmacPrfKeyFormat.PARSER = parser1;
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

    public static HmacPrfKeyFormat getDefaultInstance() {
        return HmacPrfKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public HmacPrfParams getParams() {
        return this.params_ == null ? HmacPrfParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(HmacPrfParams hmacPrfParams0) {
        hmacPrfParams0.getClass();
        if(this.params_ != null && this.params_ != HmacPrfParams.getDefaultInstance()) {
            this.params_ = (HmacPrfParams)((com.google.crypto.tink.proto.HmacPrfParams.Builder)HmacPrfParams.newBuilder(this.params_).mergeFrom(hmacPrfParams0)).buildPartial();
            return;
        }
        this.params_ = hmacPrfParams0;
    }

    public static Builder newBuilder() {
        return (Builder)HmacPrfKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HmacPrfKeyFormat hmacPrfKeyFormat0) {
        return (Builder)HmacPrfKeyFormat.DEFAULT_INSTANCE.createBuilder(hmacPrfKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HmacPrfKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HmacPrfKeyFormat)HmacPrfKeyFormat.parseDelimitedFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacPrfKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacPrfKeyFormat)HmacPrfKeyFormat.parseDelimitedFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacPrfKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static HmacPrfKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HmacPrfKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HmacPrfKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HmacPrfKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacPrfKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacPrfKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HmacPrfKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HmacPrfKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static HmacPrfKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat)GeneratedMessageLite.parseFrom(HmacPrfKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HmacPrfKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setParams(HmacPrfParams hmacPrfParams0) {
        hmacPrfParams0.getClass();
        this.params_ = hmacPrfParams0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

