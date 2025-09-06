package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class KeyData extends GeneratedMessageLite implements KeyDataOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KeyDataOrBuilder {
        private Builder() {
            super(KeyData.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KeyData.1 keyData$10) {
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

        public Builder clearKeyMaterialType() {
            this.copyOnWrite();
            ((KeyData)this.instance).clearKeyMaterialType();
            return this;
        }

        public Builder clearTypeUrl() {
            this.copyOnWrite();
            ((KeyData)this.instance).clearTypeUrl();
            return this;
        }

        public Builder clearValue() {
            this.copyOnWrite();
            ((KeyData)this.instance).clearValue();
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

        @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
        public KeyMaterialType getKeyMaterialType() {
            return ((KeyData)this.instance).getKeyMaterialType();
        }

        @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
        public int getKeyMaterialTypeValue() {
            return ((KeyData)this.instance).getKeyMaterialTypeValue();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
        public String getTypeUrl() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
        public ByteString getTypeUrlBytes() {
            return ((KeyData)this.instance).getTypeUrlBytes();
        }

        @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
        public ByteString getValue() {
            return ((KeyData)this.instance).getValue();
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

        public Builder setKeyMaterialType(KeyMaterialType keyData$KeyMaterialType0) {
            this.copyOnWrite();
            ((KeyData)this.instance).setKeyMaterialType(keyData$KeyMaterialType0);
            return this;
        }

        public Builder setKeyMaterialTypeValue(int v) {
            this.copyOnWrite();
            ((KeyData)this.instance).setKeyMaterialTypeValue(v);
            return this;
        }

        public Builder setTypeUrl(String s) {
            this.copyOnWrite();
            ((KeyData)this.instance).setTypeUrl(s);
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((KeyData)this.instance).setTypeUrlBytes(byteString0);
            return this;
        }

        public Builder setValue(ByteString byteString0) {
            this.copyOnWrite();
            ((KeyData)this.instance).setValue(byteString0);
            return this;
        }
    }

    public static enum KeyMaterialType implements EnumLite {
        static final class KeyMaterialTypeVerifier implements EnumVerifier {
            static final EnumVerifier INSTANCE;

            static {
                KeyMaterialTypeVerifier.INSTANCE = new KeyMaterialTypeVerifier();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
            public boolean isInRange(int v) {
                return KeyMaterialType.forNumber(v) != null;
            }
        }

        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);

        public static final int ASYMMETRIC_PRIVATE_VALUE = 2;
        public static final int ASYMMETRIC_PUBLIC_VALUE = 3;
        public static final int REMOTE_VALUE = 4;
        public static final int SYMMETRIC_VALUE = 1;
        public static final int UNKNOWN_KEYMATERIAL_VALUE;
        private static final EnumLiteMap internalValueMap;
        private final int value;

        static {
            KeyMaterialType.internalValueMap = new EnumLiteMap() {
                public KeyMaterialType findValueByNumber(int v) {
                    return KeyMaterialType.forNumber(v);
                }

                @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
                public EnumLite findValueByNumber(int v) {
                    return this.findValueByNumber(v);
                }
            };
        }

        private KeyMaterialType(int v1) {
            this.value = v1;
        }

        public static KeyMaterialType forNumber(int v) {
            switch(v) {
                case 0: {
                    return KeyMaterialType.UNKNOWN_KEYMATERIAL;
                }
                case 1: {
                    return KeyMaterialType.SYMMETRIC;
                }
                case 2: {
                    return KeyMaterialType.ASYMMETRIC_PRIVATE;
                }
                case 3: {
                    return KeyMaterialType.ASYMMETRIC_PUBLIC;
                }
                case 4: {
                    return KeyMaterialType.REMOTE;
                }
                default: {
                    return null;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
        public final int getNumber() {
            if(this == KeyMaterialType.UNRECOGNIZED) {
                throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
            }
            return this.value;
        }

        public static EnumLiteMap internalGetValueMap() {
            return KeyMaterialType.internalValueMap;
        }

        public static EnumVerifier internalGetVerifier() {
            return KeyMaterialTypeVerifier.INSTANCE;
        }

        @Deprecated
        public static KeyMaterialType valueOf(int v) {
            return KeyMaterialType.forNumber(v);
        }
    }

    private static final KeyData DEFAULT_INSTANCE = null;
    public static final int KEY_MATERIAL_TYPE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int keyMaterialType_;
    private String typeUrl_;
    private ByteString value_;

    static {
        KeyData keyData0 = new KeyData();
        KeyData.DEFAULT_INSTANCE = keyData0;
        GeneratedMessageLite.registerDefaultInstance(KeyData.class, keyData0);
    }

    private KeyData() {
        this.typeUrl_ = "";
        this.value_ = ByteString.EMPTY;
    }

    private void clearKeyMaterialType() {
        this.keyMaterialType_ = 0;
    }

    private void clearTypeUrl() {
        this.typeUrl_ = "";
    }

    private void clearValue() {
        this.value_ = KeyData.getDefaultInstance().getValue();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KeyData.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KeyData();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KeyData.newMessageInfo(KeyData.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"typeUrl_", "value_", "keyMaterialType_"});
            }
            case 4: {
                return KeyData.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KeyData.PARSER;
                if(parser0 == null) {
                    Class class0 = KeyData.class;
                    synchronized(class0) {
                        Parser parser1 = KeyData.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KeyData.DEFAULT_INSTANCE);
                            KeyData.PARSER = parser1;
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

    public static KeyData getDefaultInstance() {
        return KeyData.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
    public KeyMaterialType getKeyMaterialType() {
        KeyMaterialType keyData$KeyMaterialType0 = KeyMaterialType.forNumber(this.keyMaterialType_);
        return keyData$KeyMaterialType0 == null ? KeyMaterialType.UNRECOGNIZED : keyData$KeyMaterialType0;
    }

    @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
    public int getKeyMaterialTypeValue() {
        return this.keyMaterialType_;
    }

    @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
    public String getTypeUrl() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
    public ByteString getTypeUrlBytes() {
        return ByteString.copyFromUtf8(this.typeUrl_);
    }

    @Override  // com.google.crypto.tink.proto.KeyDataOrBuilder
    public ByteString getValue() {
        return this.value_;
    }

    public static Builder newBuilder() {
        return (Builder)KeyData.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KeyData keyData0) {
        return (Builder)KeyData.DEFAULT_INSTANCE.createBuilder(keyData0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KeyData parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KeyData)KeyData.parseDelimitedFrom(KeyData.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeyData parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyData)KeyData.parseDelimitedFrom(KeyData.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeyData parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, byteString0);
    }

    public static KeyData parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KeyData parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KeyData parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KeyData parseFrom(InputStream inputStream0) throws IOException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeyData parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeyData parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KeyData parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KeyData parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, arr_b);
    }

    public static KeyData parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeyData)GeneratedMessageLite.parseFrom(KeyData.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KeyData.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeyMaterialType(KeyMaterialType keyData$KeyMaterialType0) {
        this.keyMaterialType_ = keyData$KeyMaterialType0.getNumber();
    }

    private void setKeyMaterialTypeValue(int v) {
        this.keyMaterialType_ = v;
    }

    private void setTypeUrl(String s) {
        s.getClass();
        this.typeUrl_ = s;
    }

    private void setTypeUrlBytes(ByteString byteString0) {
        KeyData.checkByteStringIsUtf8(byteString0);
        this.typeUrl_ = byteString0.toStringUtf8();
    }

    private void setValue(ByteString byteString0) {
        byteString0.getClass();
        this.value_ = byteString0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

