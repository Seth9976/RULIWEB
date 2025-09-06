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

@Deprecated
public final class KeyTypeEntry extends GeneratedMessageLite implements KeyTypeEntryOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KeyTypeEntryOrBuilder {
        private Builder() {
            super(KeyTypeEntry.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KeyTypeEntry.1 keyTypeEntry$10) {
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

        public Builder clearCatalogueName() {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).clearCatalogueName();
            return this;
        }

        public Builder clearKeyManagerVersion() {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).clearKeyManagerVersion();
            return this;
        }

        public Builder clearNewKeyAllowed() {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).clearNewKeyAllowed();
            return this;
        }

        public Builder clearPrimitiveName() {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).clearPrimitiveName();
            return this;
        }

        public Builder clearTypeUrl() {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).clearTypeUrl();
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

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public String getCatalogueName() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public ByteString getCatalogueNameBytes() {
            return ((KeyTypeEntry)this.instance).getCatalogueNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public int getKeyManagerVersion() {
            return ((KeyTypeEntry)this.instance).getKeyManagerVersion();
        }

        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public boolean getNewKeyAllowed() {
            return ((KeyTypeEntry)this.instance).getNewKeyAllowed();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public String getPrimitiveName() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public ByteString getPrimitiveNameBytes() {
            return ((KeyTypeEntry)this.instance).getPrimitiveNameBytes();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public String getTypeUrl() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
        public ByteString getTypeUrlBytes() {
            return ((KeyTypeEntry)this.instance).getTypeUrlBytes();
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

        public Builder setCatalogueName(String s) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setCatalogueName(s);
            return this;
        }

        public Builder setCatalogueNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setCatalogueNameBytes(byteString0);
            return this;
        }

        public Builder setKeyManagerVersion(int v) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setKeyManagerVersion(v);
            return this;
        }

        public Builder setNewKeyAllowed(boolean z) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setNewKeyAllowed(z);
            return this;
        }

        public Builder setPrimitiveName(String s) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setPrimitiveName(s);
            return this;
        }

        public Builder setPrimitiveNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setPrimitiveNameBytes(byteString0);
            return this;
        }

        public Builder setTypeUrl(String s) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setTypeUrl(s);
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((KeyTypeEntry)this.instance).setTypeUrlBytes(byteString0);
            return this;
        }
    }

    public static final int CATALOGUE_NAME_FIELD_NUMBER = 5;
    private static final KeyTypeEntry DEFAULT_INSTANCE = null;
    public static final int KEY_MANAGER_VERSION_FIELD_NUMBER = 3;
    public static final int NEW_KEY_ALLOWED_FIELD_NUMBER = 4;
    private static volatile Parser PARSER = null;
    public static final int PRIMITIVE_NAME_FIELD_NUMBER = 1;
    public static final int TYPE_URL_FIELD_NUMBER = 2;
    private String catalogueName_;
    private int keyManagerVersion_;
    private boolean newKeyAllowed_;
    private String primitiveName_;
    private String typeUrl_;

    static {
        KeyTypeEntry keyTypeEntry0 = new KeyTypeEntry();
        KeyTypeEntry.DEFAULT_INSTANCE = keyTypeEntry0;
        GeneratedMessageLite.registerDefaultInstance(KeyTypeEntry.class, keyTypeEntry0);
    }

    private KeyTypeEntry() {
        this.primitiveName_ = "";
        this.typeUrl_ = "";
        this.catalogueName_ = "";
    }

    private void clearCatalogueName() {
        this.catalogueName_ = "";
    }

    private void clearKeyManagerVersion() {
        this.keyManagerVersion_ = 0;
    }

    private void clearNewKeyAllowed() {
        this.newKeyAllowed_ = false;
    }

    private void clearPrimitiveName() {
        this.primitiveName_ = "";
    }

    private void clearTypeUrl() {
        this.typeUrl_ = "";
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KeyTypeEntry.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KeyTypeEntry();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KeyTypeEntry.newMessageInfo(KeyTypeEntry.DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000B\u0004\u0007\u0005Ȉ", new Object[]{"primitiveName_", "typeUrl_", "keyManagerVersion_", "newKeyAllowed_", "catalogueName_"});
            }
            case 4: {
                return KeyTypeEntry.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KeyTypeEntry.PARSER;
                if(parser0 == null) {
                    Class class0 = KeyTypeEntry.class;
                    synchronized(class0) {
                        Parser parser1 = KeyTypeEntry.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KeyTypeEntry.DEFAULT_INSTANCE);
                            KeyTypeEntry.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public String getCatalogueName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public ByteString getCatalogueNameBytes() {
        return ByteString.copyFromUtf8(this.catalogueName_);
    }

    public static KeyTypeEntry getDefaultInstance() {
        return KeyTypeEntry.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public int getKeyManagerVersion() {
        return this.keyManagerVersion_;
    }

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public boolean getNewKeyAllowed() {
        return this.newKeyAllowed_;
    }

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public String getPrimitiveName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public ByteString getPrimitiveNameBytes() {
        return ByteString.copyFromUtf8(this.primitiveName_);
    }

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public String getTypeUrl() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.KeyTypeEntryOrBuilder
    public ByteString getTypeUrlBytes() {
        return ByteString.copyFromUtf8(this.typeUrl_);
    }

    public static Builder newBuilder() {
        return (Builder)KeyTypeEntry.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KeyTypeEntry keyTypeEntry0) {
        return (Builder)KeyTypeEntry.DEFAULT_INSTANCE.createBuilder(keyTypeEntry0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KeyTypeEntry parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KeyTypeEntry)KeyTypeEntry.parseDelimitedFrom(KeyTypeEntry.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeyTypeEntry parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyTypeEntry)KeyTypeEntry.parseDelimitedFrom(KeyTypeEntry.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeyTypeEntry parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, byteString0);
    }

    public static KeyTypeEntry parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KeyTypeEntry parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KeyTypeEntry parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KeyTypeEntry parseFrom(InputStream inputStream0) throws IOException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeyTypeEntry parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeyTypeEntry parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KeyTypeEntry parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KeyTypeEntry parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, arr_b);
    }

    public static KeyTypeEntry parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyTypeEntry)GeneratedMessageLite.parseFrom(KeyTypeEntry.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KeyTypeEntry.DEFAULT_INSTANCE.getParserForType();
    }

    private void setCatalogueName(String s) {
        s.getClass();
        this.catalogueName_ = s;
    }

    private void setCatalogueNameBytes(ByteString byteString0) {
        KeyTypeEntry.checkByteStringIsUtf8(byteString0);
        this.catalogueName_ = byteString0.toStringUtf8();
    }

    private void setKeyManagerVersion(int v) {
        this.keyManagerVersion_ = v;
    }

    private void setNewKeyAllowed(boolean z) {
        this.newKeyAllowed_ = z;
    }

    private void setPrimitiveName(String s) {
        s.getClass();
        this.primitiveName_ = s;
    }

    private void setPrimitiveNameBytes(ByteString byteString0) {
        KeyTypeEntry.checkByteStringIsUtf8(byteString0);
        this.primitiveName_ = byteString0.toStringUtf8();
    }

    private void setTypeUrl(String s) {
        s.getClass();
        this.typeUrl_ = s;
    }

    private void setTypeUrlBytes(ByteString byteString0) {
        KeyTypeEntry.checkByteStringIsUtf8(byteString0);
        this.typeUrl_ = byteString0.toStringUtf8();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

