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

public final class JwtEcdsaPublicKey extends GeneratedMessageLite implements JwtEcdsaPublicKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements JwtEcdsaPublicKeyOrBuilder {
        private Builder() {
            super(JwtEcdsaPublicKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.JwtEcdsaPublicKey.1 jwtEcdsaPublicKey$10) {
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
            ((JwtEcdsaPublicKey)this.instance).clearAlgorithm();
            return this;
        }

        public Builder clearCustomKid() {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).clearCustomKid();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).clearVersion();
            return this;
        }

        public Builder clearX() {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).clearX();
            return this;
        }

        public Builder clearY() {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).clearY();
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

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
        public JwtEcdsaAlgorithm getAlgorithm() {
            return ((JwtEcdsaPublicKey)this.instance).getAlgorithm();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
        public int getAlgorithmValue() {
            return ((JwtEcdsaPublicKey)this.instance).getAlgorithmValue();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
        public CustomKid getCustomKid() {
            return ((JwtEcdsaPublicKey)this.instance).getCustomKid();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
        public int getVersion() {
            return ((JwtEcdsaPublicKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
        public ByteString getX() {
            return ((JwtEcdsaPublicKey)this.instance).getX();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
        public ByteString getY() {
            return ((JwtEcdsaPublicKey)this.instance).getY();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
        public boolean hasCustomKid() {
            return ((JwtEcdsaPublicKey)this.instance).hasCustomKid();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        public Builder mergeCustomKid(CustomKid jwtEcdsaPublicKey$CustomKid0) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).mergeCustomKid(jwtEcdsaPublicKey$CustomKid0);
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

        public Builder setAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).setAlgorithm(jwtEcdsaAlgorithm0);
            return this;
        }

        public Builder setAlgorithmValue(int v) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).setAlgorithmValue(v);
            return this;
        }

        public Builder setCustomKid(com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder jwtEcdsaPublicKey$CustomKid$Builder0) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).setCustomKid(((CustomKid)jwtEcdsaPublicKey$CustomKid$Builder0.build()));
            return this;
        }

        public Builder setCustomKid(CustomKid jwtEcdsaPublicKey$CustomKid0) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).setCustomKid(jwtEcdsaPublicKey$CustomKid0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).setVersion(v);
            return this;
        }

        public Builder setX(ByteString byteString0) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).setX(byteString0);
            return this;
        }

        public Builder setY(ByteString byteString0) {
            this.copyOnWrite();
            ((JwtEcdsaPublicKey)this.instance).setY(byteString0);
            return this;
        }
    }

    public static final class CustomKid extends GeneratedMessageLite implements CustomKidOrBuilder {
        public static final class com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements CustomKidOrBuilder {
            private com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder() {
                super(CustomKid.DEFAULT_INSTANCE);
            }

            com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder(com.google.crypto.tink.proto.JwtEcdsaPublicKey.1 jwtEcdsaPublicKey$10) {
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

            public com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder clearValue() {
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
            @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKey$CustomKidOrBuilder
            public String getValue() {
                return "";
            }

            @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKey$CustomKidOrBuilder
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

            public com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder setValue(String s) {
                this.copyOnWrite();
                ((CustomKid)this.instance).setValue(s);
                return this;
            }

            public com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder setValueBytes(ByteString byteString0) {
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
            CustomKid jwtEcdsaPublicKey$CustomKid0 = new CustomKid();
            CustomKid.DEFAULT_INSTANCE = jwtEcdsaPublicKey$CustomKid0;
            GeneratedMessageLite.registerDefaultInstance(CustomKid.class, jwtEcdsaPublicKey$CustomKid0);
        }

        private CustomKid() {
            this.value_ = "";
        }

        private void clearValue() {
            this.value_ = "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
            switch(com.google.crypto.tink.proto.JwtEcdsaPublicKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
                case 1: {
                    return new CustomKid();
                }
                case 2: {
                    return new com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder(null);
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

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKey$CustomKidOrBuilder
        public String getValue() [...] // 潜在的解密器

        @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKey$CustomKidOrBuilder
        public ByteString getValueBytes() {
            return ByteString.copyFromUtf8(this.value_);
        }

        public static com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder newBuilder() {
            return (com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder)CustomKid.DEFAULT_INSTANCE.createBuilder();
        }

        public static com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder newBuilder(CustomKid jwtEcdsaPublicKey$CustomKid0) {
            return (com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder)CustomKid.DEFAULT_INSTANCE.createBuilder(jwtEcdsaPublicKey$CustomKid0);
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
    public static final int CUSTOM_KID_FIELD_NUMBER = 5;
    private static final JwtEcdsaPublicKey DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    public static final int X_FIELD_NUMBER = 3;
    public static final int Y_FIELD_NUMBER = 4;
    private int algorithm_;
    private CustomKid customKid_;
    private int version_;
    private ByteString x_;
    private ByteString y_;

    static {
        JwtEcdsaPublicKey jwtEcdsaPublicKey0 = new JwtEcdsaPublicKey();
        JwtEcdsaPublicKey.DEFAULT_INSTANCE = jwtEcdsaPublicKey0;
        GeneratedMessageLite.registerDefaultInstance(JwtEcdsaPublicKey.class, jwtEcdsaPublicKey0);
    }

    private JwtEcdsaPublicKey() {
        this.x_ = ByteString.EMPTY;
        this.y_ = ByteString.EMPTY;
    }

    private void clearAlgorithm() {
        this.algorithm_ = 0;
    }

    private void clearCustomKid() {
        this.customKid_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    private void clearX() {
        this.x_ = JwtEcdsaPublicKey.getDefaultInstance().getX();
    }

    private void clearY() {
        this.y_ = JwtEcdsaPublicKey.getDefaultInstance().getY();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.JwtEcdsaPublicKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new JwtEcdsaPublicKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return JwtEcdsaPublicKey.newMessageInfo(JwtEcdsaPublicKey.DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u000B\u0002\f\u0003\n\u0004\n\u0005\t", new Object[]{"version_", "algorithm_", "x_", "y_", "customKid_"});
            }
            case 4: {
                return JwtEcdsaPublicKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = JwtEcdsaPublicKey.PARSER;
                if(parser0 == null) {
                    Class class0 = JwtEcdsaPublicKey.class;
                    synchronized(class0) {
                        Parser parser1 = JwtEcdsaPublicKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(JwtEcdsaPublicKey.DEFAULT_INSTANCE);
                            JwtEcdsaPublicKey.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
    public JwtEcdsaAlgorithm getAlgorithm() {
        JwtEcdsaAlgorithm jwtEcdsaAlgorithm0 = JwtEcdsaAlgorithm.forNumber(this.algorithm_);
        return jwtEcdsaAlgorithm0 == null ? JwtEcdsaAlgorithm.UNRECOGNIZED : jwtEcdsaAlgorithm0;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
    public int getAlgorithmValue() {
        return this.algorithm_;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
    public CustomKid getCustomKid() {
        return this.customKid_ == null ? CustomKid.getDefaultInstance() : this.customKid_;
    }

    public static JwtEcdsaPublicKey getDefaultInstance() {
        return JwtEcdsaPublicKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
    public ByteString getX() {
        return this.x_;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
    public ByteString getY() {
        return this.y_;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaPublicKeyOrBuilder
    public boolean hasCustomKid() {
        return this.customKid_ != null;
    }

    private void mergeCustomKid(CustomKid jwtEcdsaPublicKey$CustomKid0) {
        jwtEcdsaPublicKey$CustomKid0.getClass();
        if(this.customKid_ != null && this.customKid_ != CustomKid.getDefaultInstance()) {
            this.customKid_ = (CustomKid)((com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid.Builder)CustomKid.newBuilder(this.customKid_).mergeFrom(jwtEcdsaPublicKey$CustomKid0)).buildPartial();
            return;
        }
        this.customKid_ = jwtEcdsaPublicKey$CustomKid0;
    }

    public static Builder newBuilder() {
        return (Builder)JwtEcdsaPublicKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtEcdsaPublicKey jwtEcdsaPublicKey0) {
        return (Builder)JwtEcdsaPublicKey.DEFAULT_INSTANCE.createBuilder(jwtEcdsaPublicKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static JwtEcdsaPublicKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (JwtEcdsaPublicKey)JwtEcdsaPublicKey.parseDelimitedFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtEcdsaPublicKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaPublicKey)JwtEcdsaPublicKey.parseDelimitedFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaPublicKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, byteString0);
    }

    public static JwtEcdsaPublicKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static JwtEcdsaPublicKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static JwtEcdsaPublicKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaPublicKey parseFrom(InputStream inputStream0) throws IOException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtEcdsaPublicKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaPublicKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static JwtEcdsaPublicKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static JwtEcdsaPublicKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, arr_b);
    }

    public static JwtEcdsaPublicKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaPublicKey)GeneratedMessageLite.parseFrom(JwtEcdsaPublicKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return JwtEcdsaPublicKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0) {
        this.algorithm_ = jwtEcdsaAlgorithm0.getNumber();
    }

    private void setAlgorithmValue(int v) {
        this.algorithm_ = v;
    }

    private void setCustomKid(CustomKid jwtEcdsaPublicKey$CustomKid0) {
        jwtEcdsaPublicKey$CustomKid0.getClass();
        this.customKid_ = jwtEcdsaPublicKey$CustomKid0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    private void setX(ByteString byteString0) {
        byteString0.getClass();
        this.x_ = byteString0;
    }

    private void setY(ByteString byteString0) {
        byteString0.getClass();
        this.y_ = byteString0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

