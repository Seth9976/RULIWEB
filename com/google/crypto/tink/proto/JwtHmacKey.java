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
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class JwtHmacKey extends GeneratedMessageLite implements JwtHmacKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements JwtHmacKeyOrBuilder {
        private Builder() {
            super(JwtHmacKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.JwtHmacKey.1 jwtHmacKey$10) {
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

        public Builder clearAlgorithm() {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).clearAlgorithm();
            return this;
        }

        public Builder clearCustomKid() {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).clearCustomKid();
            return this;
        }

        public Builder clearKeyValue() {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
        public JwtHmacAlgorithm getAlgorithm() {
            return ((JwtHmacKey)this.instance).getAlgorithm();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
        public int getAlgorithmValue() {
            return ((JwtHmacKey)this.instance).getAlgorithmValue();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
        public CustomKid getCustomKid() {
            return ((JwtHmacKey)this.instance).getCustomKid();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
        public ByteString getKeyValue() {
            return ((JwtHmacKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
        public int getVersion() {
            return ((JwtHmacKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
        public boolean hasCustomKid() {
            return ((JwtHmacKey)this.instance).hasCustomKid();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        public Builder mergeCustomKid(CustomKid jwtHmacKey$CustomKid0) {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).mergeCustomKid(jwtHmacKey$CustomKid0);
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

        public Builder setAlgorithm(JwtHmacAlgorithm jwtHmacAlgorithm0) {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).setAlgorithm(jwtHmacAlgorithm0);
            return this;
        }

        public Builder setAlgorithmValue(int v) {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).setAlgorithmValue(v);
            return this;
        }

        public Builder setCustomKid(com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder jwtHmacKey$CustomKid$Builder0) {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).setCustomKid(((CustomKid)jwtHmacKey$CustomKid$Builder0.build()));
            return this;
        }

        public Builder setCustomKid(CustomKid jwtHmacKey$CustomKid0) {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).setCustomKid(jwtHmacKey$CustomKid0);
            return this;
        }

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((JwtHmacKey)this.instance).setVersion(v);
            return this;
        }
    }

    public static final class CustomKid extends GeneratedMessageLite implements CustomKidOrBuilder {
        public static final class com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements CustomKidOrBuilder {
            private com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder() {
                super(CustomKid.DEFAULT_INSTANCE);
            }

            com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder(com.google.crypto.tink.proto.JwtHmacKey.1 jwtHmacKey$10) {
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

            public com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder clearValue() {
                this.copyOnWrite();
                ((CustomKid)this.instance).clearValue();
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
            @Override  // com.google.crypto.tink.proto.JwtHmacKey$CustomKidOrBuilder
            public String getValue() {
                return "";
            }

            @Override  // com.google.crypto.tink.proto.JwtHmacKey$CustomKidOrBuilder
            public ByteString getValueBytes() {
                return ((CustomKid)this.instance).getValueBytes();
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

            public com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder setValue(String s) {
                this.copyOnWrite();
                ((CustomKid)this.instance).setValue(s);
                return this;
            }

            public com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder setValueBytes(ByteString byteString0) {
                this.copyOnWrite();
                ((CustomKid)this.instance).setValueBytes(byteString0);
                return this;
            }
        }

        private static final CustomKid DEFAULT_INSTANCE = null;
        private static volatile Parser PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 1;
        private String value_;

        static {
            CustomKid jwtHmacKey$CustomKid0 = new CustomKid();
            CustomKid.DEFAULT_INSTANCE = jwtHmacKey$CustomKid0;
            GeneratedMessageLite.registerDefaultInstance(CustomKid.class, jwtHmacKey$CustomKid0);
        }

        private CustomKid() {
            this.value_ = "";
        }

        private void clearValue() {
            this.value_ = "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
            switch(com.google.crypto.tink.proto.JwtHmacKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
                case 1: {
                    return new CustomKid();
                }
                case 2: {
                    return new com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder(null);
                }
                case 3: {
                    return CustomKid.newMessageInfo(CustomKid.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"value_"});
                }
                case 4: {
                    return CustomKid.DEFAULT_INSTANCE;
                }
                case 5: {
                    Parser parser0 = CustomKid.PARSER;
                    if(parser0 == null) {
                        Class class0 = CustomKid.class;
                        synchronized(class0) {
                            Parser parser1 = CustomKid.PARSER;
                            if(parser1 == null) {
                                parser1 = new DefaultInstanceBasedParser(CustomKid.DEFAULT_INSTANCE);
                                CustomKid.PARSER = parser1;
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

        public static CustomKid getDefaultInstance() {
            return CustomKid.DEFAULT_INSTANCE;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKey$CustomKidOrBuilder
        public String getValue() [...] // 潜在的解密器

        @Override  // com.google.crypto.tink.proto.JwtHmacKey$CustomKidOrBuilder
        public ByteString getValueBytes() {
            return ByteString.copyFromUtf8(this.value_);
        }

        public static com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder newBuilder() {
            return (com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder)CustomKid.DEFAULT_INSTANCE.createBuilder();
        }

        public static com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder newBuilder(CustomKid jwtHmacKey$CustomKid0) {
            return (com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder)CustomKid.DEFAULT_INSTANCE.createBuilder(jwtHmacKey$CustomKid0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        public static CustomKid parseDelimitedFrom(InputStream inputStream0) throws IOException {
            return (CustomKid)CustomKid.parseDelimitedFrom(CustomKid.DEFAULT_INSTANCE, inputStream0);
        }

        public static CustomKid parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (CustomKid)CustomKid.parseDelimitedFrom(CustomKid.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
        }

        public static CustomKid parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, byteString0);
        }

        public static CustomKid parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
        }

        public static CustomKid parseFrom(CodedInputStream codedInputStream0) throws IOException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, codedInputStream0);
        }

        public static CustomKid parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
        }

        public static CustomKid parseFrom(InputStream inputStream0) throws IOException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, inputStream0);
        }

        public static CustomKid parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
        }

        public static CustomKid parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, byteBuffer0);
        }

        public static CustomKid parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
        }

        public static CustomKid parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, arr_b);
        }

        public static CustomKid parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (CustomKid)GeneratedMessageLite.parseFrom(CustomKid.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
        }

        public static Parser parser() {
            return CustomKid.DEFAULT_INSTANCE.getParserForType();
        }

        private void setValue(String s) {
            s.getClass();
            this.value_ = s;
        }

        private void setValueBytes(ByteString byteString0) {
            CustomKid.checkByteStringIsUtf8(byteString0);
            this.value_ = byteString0.toStringUtf8();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }
    }

    public interface CustomKidOrBuilder extends MessageLiteOrBuilder {
        String getValue();

        ByteString getValueBytes();
    }

    public static final int ALGORITHM_FIELD_NUMBER = 2;
    public static final int CUSTOM_KID_FIELD_NUMBER = 4;
    private static final JwtHmacKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int algorithm_;
    private CustomKid customKid_;
    private ByteString keyValue_;
    private int version_;

    static {
        JwtHmacKey jwtHmacKey0 = new JwtHmacKey();
        JwtHmacKey.DEFAULT_INSTANCE = jwtHmacKey0;
        GeneratedMessageLite.registerDefaultInstance(JwtHmacKey.class, jwtHmacKey0);
    }

    private JwtHmacKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearAlgorithm() {
        this.algorithm_ = 0;
    }

    private void clearCustomKid() {
        this.customKid_ = null;
    }

    private void clearKeyValue() {
        this.keyValue_ = JwtHmacKey.getDefaultInstance().getKeyValue();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.JwtHmacKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new JwtHmacKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return JwtHmacKey.newMessageInfo(JwtHmacKey.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\f\u0003\n\u0004\t", new Object[]{"version_", "algorithm_", "keyValue_", "customKid_"});
            }
            case 4: {
                return JwtHmacKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = JwtHmacKey.PARSER;
                if(parser0 == null) {
                    Class class0 = JwtHmacKey.class;
                    synchronized(class0) {
                        Parser parser1 = JwtHmacKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(JwtHmacKey.DEFAULT_INSTANCE);
                            JwtHmacKey.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
    public JwtHmacAlgorithm getAlgorithm() {
        JwtHmacAlgorithm jwtHmacAlgorithm0 = JwtHmacAlgorithm.forNumber(this.algorithm_);
        return jwtHmacAlgorithm0 == null ? JwtHmacAlgorithm.UNRECOGNIZED : jwtHmacAlgorithm0;
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
    public int getAlgorithmValue() {
        return this.algorithm_;
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
    public CustomKid getCustomKid() {
        return this.customKid_ == null ? CustomKid.getDefaultInstance() : this.customKid_;
    }

    public static JwtHmacKey getDefaultInstance() {
        return JwtHmacKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyOrBuilder
    public boolean hasCustomKid() {
        return this.customKid_ != null;
    }

    private void mergeCustomKid(CustomKid jwtHmacKey$CustomKid0) {
        jwtHmacKey$CustomKid0.getClass();
        if(this.customKid_ != null && this.customKid_ != CustomKid.getDefaultInstance()) {
            this.customKid_ = (CustomKid)((com.google.crypto.tink.proto.JwtHmacKey.CustomKid.Builder)CustomKid.newBuilder(this.customKid_).mergeFrom(jwtHmacKey$CustomKid0)).buildPartial();
            return;
        }
        this.customKid_ = jwtHmacKey$CustomKid0;
    }

    public static Builder newBuilder() {
        return (Builder)JwtHmacKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtHmacKey jwtHmacKey0) {
        return (Builder)JwtHmacKey.DEFAULT_INSTANCE.createBuilder(jwtHmacKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static JwtHmacKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (JwtHmacKey)JwtHmacKey.parseDelimitedFrom(JwtHmacKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtHmacKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtHmacKey)JwtHmacKey.parseDelimitedFrom(JwtHmacKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtHmacKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, byteString0);
    }

    public static JwtHmacKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static JwtHmacKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static JwtHmacKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static JwtHmacKey parseFrom(InputStream inputStream0) throws IOException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtHmacKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtHmacKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static JwtHmacKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static JwtHmacKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, arr_b);
    }

    public static JwtHmacKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtHmacKey)GeneratedMessageLite.parseFrom(JwtHmacKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return JwtHmacKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAlgorithm(JwtHmacAlgorithm jwtHmacAlgorithm0) {
        this.algorithm_ = jwtHmacAlgorithm0.getNumber();
    }

    private void setAlgorithmValue(int v) {
        this.algorithm_ = v;
    }

    private void setCustomKid(CustomKid jwtHmacKey$CustomKid0) {
        jwtHmacKey$CustomKid0.getClass();
        this.customKid_ = jwtHmacKey$CustomKid0;
    }

    private void setKeyValue(ByteString byteString0) {
        byteString0.getClass();
        this.keyValue_ = byteString0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

