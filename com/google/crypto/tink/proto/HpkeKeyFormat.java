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

public final class HpkeKeyFormat extends GeneratedMessageLite implements HpkeKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HpkeKeyFormatOrBuilder {
        private Builder() {
            super(HpkeKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HpkeKeyFormat.1 hpkeKeyFormat$10) {
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
            ((HpkeKeyFormat)this.instance).clearParams();
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

        @Override  // com.google.crypto.tink.proto.HpkeKeyFormatOrBuilder
        public HpkeParams getParams() {
            return ((HpkeKeyFormat)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.HpkeKeyFormatOrBuilder
        public boolean hasParams() {
            return ((HpkeKeyFormat)this.instance).hasParams();
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
            ((HpkeKeyFormat)this.instance).mergeParams(hpkeParams0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.HpkeParams.Builder hpkeParams$Builder0) {
            this.copyOnWrite();
            ((HpkeKeyFormat)this.instance).setParams(((HpkeParams)hpkeParams$Builder0.build()));
            return this;
        }

        public Builder setParams(HpkeParams hpkeParams0) {
            this.copyOnWrite();
            ((HpkeKeyFormat)this.instance).setParams(hpkeParams0);
            return this;
        }
    }

    private static final HpkeKeyFormat DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private HpkeParams params_;

    static {
        HpkeKeyFormat hpkeKeyFormat0 = new HpkeKeyFormat();
        HpkeKeyFormat.DEFAULT_INSTANCE = hpkeKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(HpkeKeyFormat.class, hpkeKeyFormat0);
    }

    private void clearParams() {
        this.params_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.HpkeKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HpkeKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HpkeKeyFormat.newMessageInfo(HpkeKeyFormat.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"params_"});
            }
            case 4: {
                return HpkeKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HpkeKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = HpkeKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = HpkeKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HpkeKeyFormat.DEFAULT_INSTANCE);
                            HpkeKeyFormat.PARSER = parser1;
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

    public static HpkeKeyFormat getDefaultInstance() {
        return HpkeKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HpkeKeyFormatOrBuilder
    public HpkeParams getParams() {
        return this.params_ == null ? HpkeParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.HpkeKeyFormatOrBuilder
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
        return (Builder)HpkeKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HpkeKeyFormat hpkeKeyFormat0) {
        return (Builder)HpkeKeyFormat.DEFAULT_INSTANCE.createBuilder(hpkeKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HpkeKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HpkeKeyFormat)HpkeKeyFormat.parseDelimitedFrom(HpkeKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkeKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkeKeyFormat)HpkeKeyFormat.parseDelimitedFrom(HpkeKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkeKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static HpkeKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HpkeKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HpkeKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HpkeKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static HpkeKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HpkeKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HpkeKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HpkeKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static HpkeKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HpkeKeyFormat)GeneratedMessageLite.parseFrom(HpkeKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HpkeKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(HpkeParams hpkeParams0) {
        hpkeParams0.getClass();
        this.params_ = hpkeParams0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

