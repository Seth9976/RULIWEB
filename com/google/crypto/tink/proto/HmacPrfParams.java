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

public final class HmacPrfParams extends GeneratedMessageLite implements HmacPrfParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HmacPrfParamsOrBuilder {
        private Builder() {
            super(HmacPrfParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HmacPrfParams.1 hmacPrfParams$10) {
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

        public Builder clearHash() {
            this.copyOnWrite();
            ((HmacPrfParams)this.instance).clearHash();
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

        @Override  // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
        public HashType getHash() {
            return ((HmacPrfParams)this.instance).getHash();
        }

        @Override  // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
        public int getHashValue() {
            return ((HmacPrfParams)this.instance).getHashValue();
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

        public Builder setHash(HashType hashType0) {
            this.copyOnWrite();
            ((HmacPrfParams)this.instance).setHash(hashType0);
            return this;
        }

        public Builder setHashValue(int v) {
            this.copyOnWrite();
            ((HmacPrfParams)this.instance).setHashValue(v);
            return this;
        }
    }

    private static final HmacPrfParams DEFAULT_INSTANCE = null;
    public static final int HASH_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int hash_;

    static {
        HmacPrfParams hmacPrfParams0 = new HmacPrfParams();
        HmacPrfParams.DEFAULT_INSTANCE = hmacPrfParams0;
        GeneratedMessageLite.registerDefaultInstance(HmacPrfParams.class, hmacPrfParams0);
    }

    private void clearHash() {
        this.hash_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.HmacPrfParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HmacPrfParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HmacPrfParams.newMessageInfo(HmacPrfParams.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"hash_"});
            }
            case 4: {
                return HmacPrfParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HmacPrfParams.PARSER;
                if(parser0 == null) {
                    Class class0 = HmacPrfParams.class;
                    synchronized(class0) {
                        Parser parser1 = HmacPrfParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HmacPrfParams.DEFAULT_INSTANCE);
                            HmacPrfParams.PARSER = parser1;
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

    public static HmacPrfParams getDefaultInstance() {
        return HmacPrfParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
    public HashType getHash() {
        HashType hashType0 = HashType.forNumber(this.hash_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.HmacPrfParamsOrBuilder
    public int getHashValue() {
        return this.hash_;
    }

    public static Builder newBuilder() {
        return (Builder)HmacPrfParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HmacPrfParams hmacPrfParams0) {
        return (Builder)HmacPrfParams.DEFAULT_INSTANCE.createBuilder(hmacPrfParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HmacPrfParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HmacPrfParams)HmacPrfParams.parseDelimitedFrom(HmacPrfParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacPrfParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacPrfParams)HmacPrfParams.parseDelimitedFrom(HmacPrfParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacPrfParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, byteString0);
    }

    public static HmacPrfParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HmacPrfParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HmacPrfParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HmacPrfParams parseFrom(InputStream inputStream0) throws IOException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacPrfParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacPrfParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HmacPrfParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HmacPrfParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, arr_b);
    }

    public static HmacPrfParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacPrfParams)GeneratedMessageLite.parseFrom(HmacPrfParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HmacPrfParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setHash(HashType hashType0) {
        this.hash_ = hashType0.getNumber();
    }

    private void setHashValue(int v) {
        this.hash_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

