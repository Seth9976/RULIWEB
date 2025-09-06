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

public final class KmsAeadKey extends GeneratedMessageLite implements KmsAeadKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KmsAeadKeyOrBuilder {
        private Builder() {
            super(KmsAeadKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KmsAeadKey.1 kmsAeadKey$10) {
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
            ((KmsAeadKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((KmsAeadKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
        public KmsAeadKeyFormat getParams() {
            return ((KmsAeadKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
        public int getVersion() {
            return ((KmsAeadKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
        public boolean hasParams() {
            return ((KmsAeadKey)this.instance).hasParams();
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

        public Builder mergeParams(KmsAeadKeyFormat kmsAeadKeyFormat0) {
            this.copyOnWrite();
            ((KmsAeadKey)this.instance).mergeParams(kmsAeadKeyFormat0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.KmsAeadKeyFormat.Builder kmsAeadKeyFormat$Builder0) {
            this.copyOnWrite();
            ((KmsAeadKey)this.instance).setParams(((KmsAeadKeyFormat)kmsAeadKeyFormat$Builder0.build()));
            return this;
        }

        public Builder setParams(KmsAeadKeyFormat kmsAeadKeyFormat0) {
            this.copyOnWrite();
            ((KmsAeadKey)this.instance).setParams(kmsAeadKeyFormat0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((KmsAeadKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final KmsAeadKey DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private KmsAeadKeyFormat params_;
    private int version_;

    static {
        KmsAeadKey kmsAeadKey0 = new KmsAeadKey();
        KmsAeadKey.DEFAULT_INSTANCE = kmsAeadKey0;
        GeneratedMessageLite.registerDefaultInstance(KmsAeadKey.class, kmsAeadKey0);
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KmsAeadKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KmsAeadKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KmsAeadKey.newMessageInfo(KmsAeadKey.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\t", new Object[]{"version_", "params_"});
            }
            case 4: {
                return KmsAeadKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KmsAeadKey.PARSER;
                if(parser0 == null) {
                    Class class0 = KmsAeadKey.class;
                    synchronized(class0) {
                        Parser parser1 = KmsAeadKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KmsAeadKey.DEFAULT_INSTANCE);
                            KmsAeadKey.PARSER = parser1;
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

    public static KmsAeadKey getDefaultInstance() {
        return KmsAeadKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
    public KmsAeadKeyFormat getParams() {
        return this.params_ == null ? KmsAeadKeyFormat.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(KmsAeadKeyFormat kmsAeadKeyFormat0) {
        kmsAeadKeyFormat0.getClass();
        if(this.params_ != null && this.params_ != KmsAeadKeyFormat.getDefaultInstance()) {
            this.params_ = (KmsAeadKeyFormat)((com.google.crypto.tink.proto.KmsAeadKeyFormat.Builder)KmsAeadKeyFormat.newBuilder(this.params_).mergeFrom(kmsAeadKeyFormat0)).buildPartial();
            return;
        }
        this.params_ = kmsAeadKeyFormat0;
    }

    public static Builder newBuilder() {
        return (Builder)KmsAeadKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KmsAeadKey kmsAeadKey0) {
        return (Builder)KmsAeadKey.DEFAULT_INSTANCE.createBuilder(kmsAeadKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KmsAeadKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KmsAeadKey)KmsAeadKey.parseDelimitedFrom(KmsAeadKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsAeadKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsAeadKey)KmsAeadKey.parseDelimitedFrom(KmsAeadKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsAeadKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, byteString0);
    }

    public static KmsAeadKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KmsAeadKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KmsAeadKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KmsAeadKey parseFrom(InputStream inputStream0) throws IOException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsAeadKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsAeadKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KmsAeadKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KmsAeadKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, arr_b);
    }

    public static KmsAeadKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsAeadKey)GeneratedMessageLite.parseFrom(KmsAeadKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KmsAeadKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(KmsAeadKeyFormat kmsAeadKeyFormat0) {
        kmsAeadKeyFormat0.getClass();
        this.params_ = kmsAeadKeyFormat0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

