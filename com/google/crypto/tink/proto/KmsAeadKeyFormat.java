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

public final class KmsAeadKeyFormat extends GeneratedMessageLite implements KmsAeadKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KmsAeadKeyFormatOrBuilder {
        private Builder() {
            super(KmsAeadKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KmsAeadKeyFormat.1 kmsAeadKeyFormat$10) {
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

        public Builder clearKeyUri() {
            this.copyOnWrite();
            ((KmsAeadKeyFormat)this.instance).clearKeyUri();
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

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.KmsAeadKeyFormatOrBuilder
        public String getKeyUri() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.KmsAeadKeyFormatOrBuilder
        public ByteString getKeyUriBytes() {
            return ((KmsAeadKeyFormat)this.instance).getKeyUriBytes();
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

        public Builder setKeyUri(String s) {
            this.copyOnWrite();
            ((KmsAeadKeyFormat)this.instance).setKeyUri(s);
            return this;
        }

        public Builder setKeyUriBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((KmsAeadKeyFormat)this.instance).setKeyUriBytes(byteString0);
            return this;
        }
    }

    private static final KmsAeadKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_URI_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private String keyUri_;

    static {
        KmsAeadKeyFormat kmsAeadKeyFormat0 = new KmsAeadKeyFormat();
        KmsAeadKeyFormat.DEFAULT_INSTANCE = kmsAeadKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(KmsAeadKeyFormat.class, kmsAeadKeyFormat0);
    }

    private KmsAeadKeyFormat() {
        this.keyUri_ = "";
    }

    private void clearKeyUri() {
        this.keyUri_ = "";
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KmsAeadKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KmsAeadKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KmsAeadKeyFormat.newMessageInfo(KmsAeadKeyFormat.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"keyUri_"});
            }
            case 4: {
                return KmsAeadKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KmsAeadKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = KmsAeadKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = KmsAeadKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KmsAeadKeyFormat.DEFAULT_INSTANCE);
                            KmsAeadKeyFormat.PARSER = parser1;
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

    public static KmsAeadKeyFormat getDefaultInstance() {
        return KmsAeadKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KmsAeadKeyFormatOrBuilder
    public String getKeyUri() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.KmsAeadKeyFormatOrBuilder
    public ByteString getKeyUriBytes() {
        return ByteString.copyFromUtf8(this.keyUri_);
    }

    public static Builder newBuilder() {
        return (Builder)KmsAeadKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KmsAeadKeyFormat kmsAeadKeyFormat0) {
        return (Builder)KmsAeadKeyFormat.DEFAULT_INSTANCE.createBuilder(kmsAeadKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KmsAeadKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KmsAeadKeyFormat)KmsAeadKeyFormat.parseDelimitedFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsAeadKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsAeadKeyFormat)KmsAeadKeyFormat.parseDelimitedFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsAeadKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static KmsAeadKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KmsAeadKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KmsAeadKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KmsAeadKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static KmsAeadKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KmsAeadKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KmsAeadKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KmsAeadKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static KmsAeadKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat)GeneratedMessageLite.parseFrom(KmsAeadKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KmsAeadKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyUri(String s) {
        s.getClass();
        this.keyUri_ = s;
    }

    private void setKeyUriBytes(ByteString byteString0) {
        KmsAeadKeyFormat.checkByteStringIsUtf8(byteString0);
        this.keyUri_ = byteString0.toStringUtf8();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

