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

public final class EncryptedKeyset extends GeneratedMessageLite implements EncryptedKeysetOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EncryptedKeysetOrBuilder {
        private Builder() {
            super(EncryptedKeyset.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EncryptedKeyset.1 encryptedKeyset$10) {
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

        public Builder clearEncryptedKeyset() {
            this.copyOnWrite();
            ((EncryptedKeyset)this.instance).clearEncryptedKeyset();
            return this;
        }

        public Builder clearKeysetInfo() {
            this.copyOnWrite();
            ((EncryptedKeyset)this.instance).clearKeysetInfo();
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

        @Override  // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
        public ByteString getEncryptedKeyset() {
            return ((EncryptedKeyset)this.instance).getEncryptedKeyset();
        }

        @Override  // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
        public KeysetInfo getKeysetInfo() {
            return ((EncryptedKeyset)this.instance).getKeysetInfo();
        }

        @Override  // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
        public boolean hasKeysetInfo() {
            return ((EncryptedKeyset)this.instance).hasKeysetInfo();
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

        public Builder mergeKeysetInfo(KeysetInfo keysetInfo0) {
            this.copyOnWrite();
            ((EncryptedKeyset)this.instance).mergeKeysetInfo(keysetInfo0);
            return this;
        }

        public Builder setEncryptedKeyset(ByteString byteString0) {
            this.copyOnWrite();
            ((EncryptedKeyset)this.instance).setEncryptedKeyset(byteString0);
            return this;
        }

        public Builder setKeysetInfo(com.google.crypto.tink.proto.KeysetInfo.Builder keysetInfo$Builder0) {
            this.copyOnWrite();
            ((EncryptedKeyset)this.instance).setKeysetInfo(((KeysetInfo)keysetInfo$Builder0.build()));
            return this;
        }

        public Builder setKeysetInfo(KeysetInfo keysetInfo0) {
            this.copyOnWrite();
            ((EncryptedKeyset)this.instance).setKeysetInfo(keysetInfo0);
            return this;
        }
    }

    private static final EncryptedKeyset DEFAULT_INSTANCE = null;
    public static final int ENCRYPTED_KEYSET_FIELD_NUMBER = 2;
    public static final int KEYSET_INFO_FIELD_NUMBER = 3;
    private static volatile Parser PARSER;
    private ByteString encryptedKeyset_;
    private KeysetInfo keysetInfo_;

    static {
        EncryptedKeyset encryptedKeyset0 = new EncryptedKeyset();
        EncryptedKeyset.DEFAULT_INSTANCE = encryptedKeyset0;
        GeneratedMessageLite.registerDefaultInstance(EncryptedKeyset.class, encryptedKeyset0);
    }

    private EncryptedKeyset() {
        this.encryptedKeyset_ = ByteString.EMPTY;
    }

    private void clearEncryptedKeyset() {
        this.encryptedKeyset_ = EncryptedKeyset.getDefaultInstance().getEncryptedKeyset();
    }

    private void clearKeysetInfo() {
        this.keysetInfo_ = null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EncryptedKeyset.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EncryptedKeyset();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EncryptedKeyset.newMessageInfo(EncryptedKeyset.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\t", new Object[]{"encryptedKeyset_", "keysetInfo_"});
            }
            case 4: {
                return EncryptedKeyset.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EncryptedKeyset.PARSER;
                if(parser0 == null) {
                    Class class0 = EncryptedKeyset.class;
                    synchronized(class0) {
                        Parser parser1 = EncryptedKeyset.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EncryptedKeyset.DEFAULT_INSTANCE);
                            EncryptedKeyset.PARSER = parser1;
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

    public static EncryptedKeyset getDefaultInstance() {
        return EncryptedKeyset.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
    public ByteString getEncryptedKeyset() {
        return this.encryptedKeyset_;
    }

    @Override  // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
    public KeysetInfo getKeysetInfo() {
        return this.keysetInfo_ == null ? KeysetInfo.getDefaultInstance() : this.keysetInfo_;
    }

    @Override  // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
    public boolean hasKeysetInfo() {
        return this.keysetInfo_ != null;
    }

    private void mergeKeysetInfo(KeysetInfo keysetInfo0) {
        keysetInfo0.getClass();
        if(this.keysetInfo_ != null && this.keysetInfo_ != KeysetInfo.getDefaultInstance()) {
            this.keysetInfo_ = (KeysetInfo)((com.google.crypto.tink.proto.KeysetInfo.Builder)KeysetInfo.newBuilder(this.keysetInfo_).mergeFrom(keysetInfo0)).buildPartial();
            return;
        }
        this.keysetInfo_ = keysetInfo0;
    }

    public static Builder newBuilder() {
        return (Builder)EncryptedKeyset.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EncryptedKeyset encryptedKeyset0) {
        return (Builder)EncryptedKeyset.DEFAULT_INSTANCE.createBuilder(encryptedKeyset0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EncryptedKeyset parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EncryptedKeyset)EncryptedKeyset.parseDelimitedFrom(EncryptedKeyset.DEFAULT_INSTANCE, inputStream0);
    }

    public static EncryptedKeyset parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EncryptedKeyset)EncryptedKeyset.parseDelimitedFrom(EncryptedKeyset.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EncryptedKeyset parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, byteString0);
    }

    public static EncryptedKeyset parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EncryptedKeyset parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EncryptedKeyset parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EncryptedKeyset parseFrom(InputStream inputStream0) throws IOException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, inputStream0);
    }

    public static EncryptedKeyset parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EncryptedKeyset parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EncryptedKeyset parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EncryptedKeyset parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, arr_b);
    }

    public static EncryptedKeyset parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EncryptedKeyset)GeneratedMessageLite.parseFrom(EncryptedKeyset.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EncryptedKeyset.DEFAULT_INSTANCE.getParserForType();
    }

    private void setEncryptedKeyset(ByteString byteString0) {
        byteString0.getClass();
        this.encryptedKeyset_ = byteString0;
    }

    private void setKeysetInfo(KeysetInfo keysetInfo0) {
        keysetInfo0.getClass();
        this.keysetInfo_ = keysetInfo0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

