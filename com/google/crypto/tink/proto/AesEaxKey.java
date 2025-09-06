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

public final class AesEaxKey extends GeneratedMessageLite implements AesEaxKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesEaxKeyOrBuilder {
        private Builder() {
            super(AesEaxKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesEaxKey.1 aesEaxKey$10) {
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
            ((AesEaxKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((AesEaxKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesEaxKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
        public ByteString getKeyValue() {
            return ((AesEaxKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
        public AesEaxParams getParams() {
            return ((AesEaxKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
        public int getVersion() {
            return ((AesEaxKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
        public boolean hasParams() {
            return ((AesEaxKey)this.instance).hasParams();
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

        public Builder mergeParams(AesEaxParams aesEaxParams0) {
            this.copyOnWrite();
            ((AesEaxKey)this.instance).mergeParams(aesEaxParams0);
            return this;
        }

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((AesEaxKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesEaxParams.Builder aesEaxParams$Builder0) {
            this.copyOnWrite();
            ((AesEaxKey)this.instance).setParams(((AesEaxParams)aesEaxParams$Builder0.build()));
            return this;
        }

        public Builder setParams(AesEaxParams aesEaxParams0) {
            this.copyOnWrite();
            ((AesEaxKey)this.instance).setParams(aesEaxParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesEaxKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesEaxKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private AesEaxParams params_;
    private int version_;

    static {
        AesEaxKey aesEaxKey0 = new AesEaxKey();
        AesEaxKey.DEFAULT_INSTANCE = aesEaxKey0;
        GeneratedMessageLite.registerDefaultInstance(AesEaxKey.class, aesEaxKey0);
    }

    private AesEaxKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = AesEaxKey.getDefaultInstance().getKeyValue();
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesEaxKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesEaxKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesEaxKey.newMessageInfo(AesEaxKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"version_", "params_", "keyValue_"});
            }
            case 4: {
                return AesEaxKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesEaxKey.PARSER;
                if(parser0 == null) {
                    Class class0 = AesEaxKey.class;
                    synchronized(class0) {
                        Parser parser1 = AesEaxKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesEaxKey.DEFAULT_INSTANCE);
                            AesEaxKey.PARSER = parser1;
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

    public static AesEaxKey getDefaultInstance() {
        return AesEaxKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
    public AesEaxParams getParams() {
        return this.params_ == null ? AesEaxParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.AesEaxKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(AesEaxParams aesEaxParams0) {
        aesEaxParams0.getClass();
        if(this.params_ != null && this.params_ != AesEaxParams.getDefaultInstance()) {
            this.params_ = (AesEaxParams)((com.google.crypto.tink.proto.AesEaxParams.Builder)AesEaxParams.newBuilder(this.params_).mergeFrom(aesEaxParams0)).buildPartial();
            return;
        }
        this.params_ = aesEaxParams0;
    }

    public static Builder newBuilder() {
        return (Builder)AesEaxKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesEaxKey aesEaxKey0) {
        return (Builder)AesEaxKey.DEFAULT_INSTANCE.createBuilder(aesEaxKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesEaxKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesEaxKey)AesEaxKey.parseDelimitedFrom(AesEaxKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesEaxKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxKey)AesEaxKey.parseDelimitedFrom(AesEaxKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesEaxKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, byteString0);
    }

    public static AesEaxKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesEaxKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesEaxKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesEaxKey parseFrom(InputStream inputStream0) throws IOException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesEaxKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesEaxKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesEaxKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesEaxKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, arr_b);
    }

    public static AesEaxKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesEaxKey)GeneratedMessageLite.parseFrom(AesEaxKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesEaxKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyValue(ByteString byteString0) {
        byteString0.getClass();
        this.keyValue_ = byteString0;
    }

    private void setParams(AesEaxParams aesEaxParams0) {
        aesEaxParams0.getClass();
        this.params_ = aesEaxParams0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

