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

public final class JwtEcdsaPrivateKey extends GeneratedMessageLite implements JwtEcdsaPrivateKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements JwtEcdsaPrivateKeyOrBuilder {
        private Builder() {
            super(JwtEcdsaPrivateKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.JwtEcdsaPrivateKey.1 jwtEcdsaPrivateKey$10) {
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
            ((JwtEcdsaPrivateKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearPublicKey() {
            this.copyOnWrite();
            ((JwtEcdsaPrivateKey)this.instance).clearPublicKey();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((JwtEcdsaPrivateKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
        public ByteString getKeyValue() {
            return ((JwtEcdsaPrivateKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
        public JwtEcdsaPublicKey getPublicKey() {
            return ((JwtEcdsaPrivateKey)this.instance).getPublicKey();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
        public int getVersion() {
            return ((JwtEcdsaPrivateKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
        public boolean hasPublicKey() {
            return ((JwtEcdsaPrivateKey)this.instance).hasPublicKey();
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

        public Builder mergePublicKey(JwtEcdsaPublicKey jwtEcdsaPublicKey0) {
            this.copyOnWrite();
            ((JwtEcdsaPrivateKey)this.instance).mergePublicKey(jwtEcdsaPublicKey0);
            return this;
        }

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((JwtEcdsaPrivateKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setPublicKey(com.google.crypto.tink.proto.JwtEcdsaPublicKey.Builder jwtEcdsaPublicKey$Builder0) {
            this.copyOnWrite();
            ((JwtEcdsaPrivateKey)this.instance).setPublicKey(((JwtEcdsaPublicKey)jwtEcdsaPublicKey$Builder0.build()));
            return this;
        }

        public Builder setPublicKey(JwtEcdsaPublicKey jwtEcdsaPublicKey0) {
            this.copyOnWrite();
            ((JwtEcdsaPrivateKey)this.instance).setPublicKey(jwtEcdsaPublicKey0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((JwtEcdsaPrivateKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final JwtEcdsaPrivateKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private JwtEcdsaPublicKey publicKey_;
    private int version_;

    static {
        JwtEcdsaPrivateKey jwtEcdsaPrivateKey0 = new JwtEcdsaPrivateKey();
        JwtEcdsaPrivateKey.DEFAULT_INSTANCE = jwtEcdsaPrivateKey0;
        GeneratedMessageLite.registerDefaultInstance(JwtEcdsaPrivateKey.class, jwtEcdsaPrivateKey0);
    }

    private JwtEcdsaPrivateKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = JwtEcdsaPrivateKey.getDefaultInstance().getKeyValue();
    }

    private void clearPublicKey() {
        this.publicKey_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.JwtEcdsaPrivateKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new JwtEcdsaPrivateKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return JwtEcdsaPrivateKey.newMessageInfo(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"version_", "publicKey_", "keyValue_"});
            }
            case 4: {
                return JwtEcdsaPrivateKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = JwtEcdsaPrivateKey.PARSER;
                if(parser0 == null) {
                    Class class0 = JwtEcdsaPrivateKey.class;
                    synchronized(class0) {
                        Parser parser1 = JwtEcdsaPrivateKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(JwtEcdsaPrivateKey.DEFAULT_INSTANCE);
                            JwtEcdsaPrivateKey.PARSER = parser1;
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

    public static JwtEcdsaPrivateKey getDefaultInstance() {
        return JwtEcdsaPrivateKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
    public JwtEcdsaPublicKey getPublicKey() {
        return this.publicKey_ == null ? JwtEcdsaPublicKey.getDefaultInstance() : this.publicKey_;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPrivateKeyOrBuilder
    public boolean hasPublicKey() {
        return this.publicKey_ != null;
    }

    private void mergePublicKey(JwtEcdsaPublicKey jwtEcdsaPublicKey0) {
        jwtEcdsaPublicKey0.getClass();
        if(this.publicKey_ != null && this.publicKey_ != JwtEcdsaPublicKey.getDefaultInstance()) {
            this.publicKey_ = (JwtEcdsaPublicKey)((com.google.crypto.tink.proto.JwtEcdsaPublicKey.Builder)JwtEcdsaPublicKey.newBuilder(this.publicKey_).mergeFrom(jwtEcdsaPublicKey0)).buildPartial();
            return;
        }
        this.publicKey_ = jwtEcdsaPublicKey0;
    }

    public static Builder newBuilder() {
        return (Builder)JwtEcdsaPrivateKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtEcdsaPrivateKey jwtEcdsaPrivateKey0) {
        return (Builder)JwtEcdsaPrivateKey.DEFAULT_INSTANCE.createBuilder(jwtEcdsaPrivateKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static JwtEcdsaPrivateKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (JwtEcdsaPrivateKey)JwtEcdsaPrivateKey.parseDelimitedFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtEcdsaPrivateKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaPrivateKey)JwtEcdsaPrivateKey.parseDelimitedFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaPrivateKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, byteString0);
    }

    public static JwtEcdsaPrivateKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static JwtEcdsaPrivateKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static JwtEcdsaPrivateKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaPrivateKey parseFrom(InputStream inputStream0) throws IOException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtEcdsaPrivateKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaPrivateKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static JwtEcdsaPrivateKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static JwtEcdsaPrivateKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, arr_b);
    }

    public static JwtEcdsaPrivateKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPrivateKey)GeneratedMessageLite.parseFrom(JwtEcdsaPrivateKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return JwtEcdsaPrivateKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyValue(ByteString byteString0) {
        byteString0.getClass();
        this.keyValue_ = byteString0;
    }

    private void setPublicKey(JwtEcdsaPublicKey jwtEcdsaPublicKey0) {
        jwtEcdsaPublicKey0.getClass();
        this.publicKey_ = jwtEcdsaPublicKey0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

