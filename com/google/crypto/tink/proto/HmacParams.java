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

public final class HmacParams extends GeneratedMessageLite implements HmacParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements HmacParamsOrBuilder {
        private Builder() {
            super(HmacParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.HmacParams.1 hmacParams$10) {
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
            ((HmacParams)this.instance).clearHash();
            return this;
        }

        public Builder clearTagSize() {
            this.copyOnWrite();
            ((HmacParams)this.instance).clearTagSize();
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

        @Override  // com.google.crypto.tink.proto.HmacParamsOrBuilder
        public HashType getHash() {
            return ((HmacParams)this.instance).getHash();
        }

        @Override  // com.google.crypto.tink.proto.HmacParamsOrBuilder
        public int getHashValue() {
            return ((HmacParams)this.instance).getHashValue();
        }

        @Override  // com.google.crypto.tink.proto.HmacParamsOrBuilder
        public int getTagSize() {
            return ((HmacParams)this.instance).getTagSize();
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
            ((HmacParams)this.instance).setHash(hashType0);
            return this;
        }

        public Builder setHashValue(int v) {
            this.copyOnWrite();
            ((HmacParams)this.instance).setHashValue(v);
            return this;
        }

        public Builder setTagSize(int v) {
            this.copyOnWrite();
            ((HmacParams)this.instance).setTagSize(v);
            return this;
        }
    }

    private static final HmacParams DEFAULT_INSTANCE = null;
    public static final int HASH_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int TAG_SIZE_FIELD_NUMBER = 2;
    private int hash_;
    private int tagSize_;

    static {
        HmacParams hmacParams0 = new HmacParams();
        HmacParams.DEFAULT_INSTANCE = hmacParams0;
        GeneratedMessageLite.registerDefaultInstance(HmacParams.class, hmacParams0);
    }

    private void clearHash() {
        this.hash_ = 0;
    }

    private void clearTagSize() {
        this.tagSize_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.HmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new HmacParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return HmacParams.newMessageInfo(HmacParams.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000B", new Object[]{"hash_", "tagSize_"});
            }
            case 4: {
                return HmacParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = HmacParams.PARSER;
                if(parser0 == null) {
                    Class class0 = HmacParams.class;
                    synchronized(class0) {
                        Parser parser1 = HmacParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(HmacParams.DEFAULT_INSTANCE);
                            HmacParams.PARSER = parser1;
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

    public static HmacParams getDefaultInstance() {
        return HmacParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.HmacParamsOrBuilder
    public HashType getHash() {
        HashType hashType0 = HashType.forNumber(this.hash_);
        return hashType0 == null ? HashType.UNRECOGNIZED : hashType0;
    }

    @Override  // com.google.crypto.tink.proto.HmacParamsOrBuilder
    public int getHashValue() {
        return this.hash_;
    }

    @Override  // com.google.crypto.tink.proto.HmacParamsOrBuilder
    public int getTagSize() {
        return this.tagSize_;
    }

    public static Builder newBuilder() {
        return (Builder)HmacParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HmacParams hmacParams0) {
        return (Builder)HmacParams.DEFAULT_INSTANCE.createBuilder(hmacParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static HmacParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (HmacParams)HmacParams.parseDelimitedFrom(HmacParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacParams)HmacParams.parseDelimitedFrom(HmacParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, byteString0);
    }

    public static HmacParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static HmacParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static HmacParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static HmacParams parseFrom(InputStream inputStream0) throws IOException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static HmacParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static HmacParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static HmacParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static HmacParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, arr_b);
    }

    public static HmacParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (HmacParams)GeneratedMessageLite.parseFrom(HmacParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return HmacParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setHash(HashType hashType0) {
        this.hash_ = hashType0.getNumber();
    }

    private void setHashValue(int v) {
        this.hash_ = v;
    }

    private void setTagSize(int v) {
        this.tagSize_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

