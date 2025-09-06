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

public final class HmacKeyFormat extends GeneratedMessageLite implements HmacKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HmacKeyFormatOrBuilder {
        private Builder() {
            super(HmacKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HmacKeyFormat.1 hmacKeyFormat$10) {
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
            ((HmacKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((HmacKeyFormat)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((HmacKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
        public int getKeySize() {
            return ((HmacKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
        public HmacParams getParams() {
            return ((HmacKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
        public int getVersion() {
            return ((HmacKeyFormat)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
        public boolean hasParams() {
            return ((HmacKeyFormat)this.instance).hasParams();
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

        public Builder mergeParams(HmacParams hmacParams0) {
            this.copyOnWrite();
            ((HmacKeyFormat)this.instance).mergeParams(hmacParams0);
            return this;
        }

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((HmacKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.HmacParams.Builder hmacParams$Builder0) {
            this.copyOnWrite();
            ((HmacKeyFormat)this.instance).setParams(((HmacParams)hmacParams$Builder0.build()));
            return this;
        }

        public Builder setParams(HmacParams hmacParams0) {
            this.copyOnWrite();
            ((HmacKeyFormat)this.instance).setParams(hmacParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((HmacKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    private static final HmacKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    private int keySize_;
    private HmacParams params_;
    private int version_;

    static {
        HmacKeyFormat hmacKeyFormat0 = new HmacKeyFormat();
        HmacKeyFormat.DEFAULT_INSTANCE = hmacKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(HmacKeyFormat.class, hmacKeyFormat0);
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
        switch(com.google.crypto.tink.proto.HmacKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HmacKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HmacKeyFormat.newMessageInfo(HmacKeyFormat.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000B\u0003\u000B", new Object[]{"params_", "keySize_", "version_"});
            }
            case 4: {
                return HmacKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HmacKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = HmacKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = HmacKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HmacKeyFormat.DEFAULT_INSTANCE);
                            HmacKeyFormat.PARSER = parser1;
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

    public static HmacKeyFormat getDefaultInstance() {
        return HmacKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
    public HmacParams getParams() {
        return this.params_ == null ? HmacParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.HmacKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(HmacParams hmacParams0) {
        hmacParams0.getClass();
        if(this.params_ != null && this.params_ != HmacParams.getDefaultInstance()) {
            this.params_ = (HmacParams)((com.google.crypto.tink.proto.HmacParams.Builder)HmacParams.newBuilder(this.params_).mergeFrom(hmacParams0)).buildPartial();
            return;
        }
        this.params_ = hmacParams0;
    }

    public static Builder newBuilder() {
        return (Builder)HmacKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HmacKeyFormat hmacKeyFormat0) {
        return (Builder)HmacKeyFormat.DEFAULT_INSTANCE.createBuilder(hmacKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HmacKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HmacKeyFormat)HmacKeyFormat.parseDelimitedFrom(HmacKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacKeyFormat)HmacKeyFormat.parseDelimitedFrom(HmacKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static HmacKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HmacKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HmacKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HmacKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HmacKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HmacKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static HmacKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacKeyFormat)GeneratedMessageLite.parseFrom(HmacKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HmacKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setParams(HmacParams hmacParams0) {
        hmacParams0.getClass();
        this.params_ = hmacParams0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

