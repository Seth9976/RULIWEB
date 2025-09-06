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

public final class KmsEnvelopeAeadKey extends GeneratedMessageLite implements KmsEnvelopeAeadKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KmsEnvelopeAeadKeyOrBuilder {
        private Builder() {
            super(KmsEnvelopeAeadKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KmsEnvelopeAeadKey.1 kmsEnvelopeAeadKey$10) {
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
            ((KmsEnvelopeAeadKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyOrBuilder
        public KmsEnvelopeAeadKeyFormat getParams() {
            return ((KmsEnvelopeAeadKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyOrBuilder
        public int getVersion() {
            return ((KmsEnvelopeAeadKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyOrBuilder
        public boolean hasParams() {
            return ((KmsEnvelopeAeadKey)this.instance).hasParams();
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

        public Builder mergeParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKey)this.instance).mergeParams(kmsEnvelopeAeadKeyFormat0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.Builder kmsEnvelopeAeadKeyFormat$Builder0) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKey)this.instance).setParams(((KmsEnvelopeAeadKeyFormat)kmsEnvelopeAeadKeyFormat$Builder0.build()));
            return this;
        }

        public Builder setParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKey)this.instance).setParams(kmsEnvelopeAeadKeyFormat0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final KmsEnvelopeAeadKey DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private KmsEnvelopeAeadKeyFormat params_;
    private int version_;

    static {
        KmsEnvelopeAeadKey kmsEnvelopeAeadKey0 = new KmsEnvelopeAeadKey();
        KmsEnvelopeAeadKey.DEFAULT_INSTANCE = kmsEnvelopeAeadKey0;
        GeneratedMessageLite.registerDefaultInstance(KmsEnvelopeAeadKey.class, kmsEnvelopeAeadKey0);
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KmsEnvelopeAeadKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KmsEnvelopeAeadKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KmsEnvelopeAeadKey.newMessageInfo(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\t", new Object[]{"version_", "params_"});
            }
            case 4: {
                return KmsEnvelopeAeadKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KmsEnvelopeAeadKey.PARSER;
                if(parser0 == null) {
                    Class class0 = KmsEnvelopeAeadKey.class;
                    synchronized(class0) {
                        Parser parser1 = KmsEnvelopeAeadKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KmsEnvelopeAeadKey.DEFAULT_INSTANCE);
                            KmsEnvelopeAeadKey.PARSER = parser1;
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

    public static KmsEnvelopeAeadKey getDefaultInstance() {
        return KmsEnvelopeAeadKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyOrBuilder
    public KmsEnvelopeAeadKeyFormat getParams() {
        return this.params_ == null ? KmsEnvelopeAeadKeyFormat.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0) {
        kmsEnvelopeAeadKeyFormat0.getClass();
        if(this.params_ != null && this.params_ != KmsEnvelopeAeadKeyFormat.getDefaultInstance()) {
            this.params_ = (KmsEnvelopeAeadKeyFormat)((com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.Builder)KmsEnvelopeAeadKeyFormat.newBuilder(this.params_).mergeFrom(kmsEnvelopeAeadKeyFormat0)).buildPartial();
            return;
        }
        this.params_ = kmsEnvelopeAeadKeyFormat0;
    }

    public static Builder newBuilder() {
        return (Builder)KmsEnvelopeAeadKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KmsEnvelopeAeadKey kmsEnvelopeAeadKey0) {
        return (Builder)KmsEnvelopeAeadKey.DEFAULT_INSTANCE.createBuilder(kmsEnvelopeAeadKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KmsEnvelopeAeadKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KmsEnvelopeAeadKey)KmsEnvelopeAeadKey.parseDelimitedFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsEnvelopeAeadKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsEnvelopeAeadKey)KmsEnvelopeAeadKey.parseDelimitedFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, byteString0);
    }

    public static KmsEnvelopeAeadKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KmsEnvelopeAeadKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKey parseFrom(InputStream inputStream0) throws IOException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsEnvelopeAeadKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KmsEnvelopeAeadKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, arr_b);
    }

    public static KmsEnvelopeAeadKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KmsEnvelopeAeadKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0) {
        kmsEnvelopeAeadKeyFormat0.getClass();
        this.params_ = kmsEnvelopeAeadKeyFormat0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

