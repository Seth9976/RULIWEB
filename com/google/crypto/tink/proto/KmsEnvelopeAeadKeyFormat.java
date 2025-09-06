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

public final class KmsEnvelopeAeadKeyFormat extends GeneratedMessageLite implements KmsEnvelopeAeadKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KmsEnvelopeAeadKeyFormatOrBuilder {
        private Builder() {
            super(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.1 kmsEnvelopeAeadKeyFormat$10) {
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

        public Builder clearDekTemplate() {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat)this.instance).clearDekTemplate();
            return this;
        }

        public Builder clearKekUri() {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat)this.instance).clearKekUri();
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

        @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
        public KeyTemplate getDekTemplate() {
            return ((KmsEnvelopeAeadKeyFormat)this.instance).getDekTemplate();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
        public String getKekUri() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
        public ByteString getKekUriBytes() {
            return ((KmsEnvelopeAeadKeyFormat)this.instance).getKekUriBytes();
        }

        @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
        public boolean hasDekTemplate() {
            return ((KmsEnvelopeAeadKeyFormat)this.instance).hasDekTemplate();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        public Builder mergeDekTemplate(KeyTemplate keyTemplate0) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat)this.instance).mergeDekTemplate(keyTemplate0);
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

        public Builder setDekTemplate(com.google.crypto.tink.proto.KeyTemplate.Builder keyTemplate$Builder0) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat)this.instance).setDekTemplate(((KeyTemplate)keyTemplate$Builder0.build()));
            return this;
        }

        public Builder setDekTemplate(KeyTemplate keyTemplate0) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat)this.instance).setDekTemplate(keyTemplate0);
            return this;
        }

        public Builder setKekUri(String s) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat)this.instance).setKekUri(s);
            return this;
        }

        public Builder setKekUriBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat)this.instance).setKekUriBytes(byteString0);
            return this;
        }
    }

    private static final KmsEnvelopeAeadKeyFormat DEFAULT_INSTANCE = null;
    public static final int DEK_TEMPLATE_FIELD_NUMBER = 2;
    public static final int KEK_URI_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private KeyTemplate dekTemplate_;
    private String kekUri_;

    static {
        KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0 = new KmsEnvelopeAeadKeyFormat();
        KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE = kmsEnvelopeAeadKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(KmsEnvelopeAeadKeyFormat.class, kmsEnvelopeAeadKeyFormat0);
    }

    private KmsEnvelopeAeadKeyFormat() {
        this.kekUri_ = "";
    }

    private void clearDekTemplate() {
        this.dekTemplate_ = null;
    }

    private void clearKekUri() {
        this.kekUri_ = "";
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KmsEnvelopeAeadKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KmsEnvelopeAeadKeyFormat.newMessageInfo(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"kekUri_", "dekTemplate_"});
            }
            case 4: {
                return KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KmsEnvelopeAeadKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = KmsEnvelopeAeadKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = KmsEnvelopeAeadKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE);
                            KmsEnvelopeAeadKeyFormat.PARSER = parser1;
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

    public static KmsEnvelopeAeadKeyFormat getDefaultInstance() {
        return KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
    public KeyTemplate getDekTemplate() {
        return this.dekTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.dekTemplate_;
    }

    @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
    public String getKekUri() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
    public ByteString getKekUriBytes() {
        return ByteString.copyFromUtf8(this.kekUri_);
    }

    @Override  // com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormatOrBuilder
    public boolean hasDekTemplate() {
        return this.dekTemplate_ != null;
    }

    private void mergeDekTemplate(KeyTemplate keyTemplate0) {
        keyTemplate0.getClass();
        if(this.dekTemplate_ != null && this.dekTemplate_ != KeyTemplate.getDefaultInstance()) {
            this.dekTemplate_ = (KeyTemplate)((com.google.crypto.tink.proto.KeyTemplate.Builder)KeyTemplate.newBuilder(this.dekTemplate_).mergeFrom(keyTemplate0)).buildPartial();
            return;
        }
        this.dekTemplate_ = keyTemplate0;
    }

    public static Builder newBuilder() {
        return (Builder)KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0) {
        return (Builder)KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE.createBuilder(kmsEnvelopeAeadKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KmsEnvelopeAeadKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KmsEnvelopeAeadKeyFormat)KmsEnvelopeAeadKeyFormat.parseDelimitedFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsEnvelopeAeadKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsEnvelopeAeadKeyFormat)KmsEnvelopeAeadKeyFormat.parseDelimitedFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setDekTemplate(KeyTemplate keyTemplate0) {
        keyTemplate0.getClass();
        this.dekTemplate_ = keyTemplate0;
    }

    private void setKekUri(String s) {
        s.getClass();
        this.kekUri_ = s;
    }

    private void setKekUriBytes(ByteString byteString0) {
        KmsEnvelopeAeadKeyFormat.checkByteStringIsUtf8(byteString0);
        this.kekUri_ = byteString0.toStringUtf8();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

