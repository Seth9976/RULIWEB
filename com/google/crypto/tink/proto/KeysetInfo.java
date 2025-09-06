package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class KeysetInfo extends GeneratedMessageLite implements KeysetInfoOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KeysetInfoOrBuilder {
        private Builder() {
            super(KeysetInfo.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.KeysetInfo.1 keysetInfo$10) {
        }

        public Builder addAllKeyInfo(Iterable iterable0) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).addAllKeyInfo(iterable0);
            return this;
        }

        public Builder addKeyInfo(int v, com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder keysetInfo$KeyInfo$Builder0) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).addKeyInfo(v, ((KeyInfo)keysetInfo$KeyInfo$Builder0.build()));
            return this;
        }

        public Builder addKeyInfo(int v, KeyInfo keysetInfo$KeyInfo0) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).addKeyInfo(v, keysetInfo$KeyInfo0);
            return this;
        }

        public Builder addKeyInfo(com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder keysetInfo$KeyInfo$Builder0) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).addKeyInfo(((KeyInfo)keysetInfo$KeyInfo$Builder0.build()));
            return this;
        }

        public Builder addKeyInfo(KeyInfo keysetInfo$KeyInfo0) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).addKeyInfo(keysetInfo$KeyInfo0);
            return this;
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

        public Builder clearKeyInfo() {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).clearKeyInfo();
            return this;
        }

        public Builder clearPrimaryKeyId() {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).clearPrimaryKeyId();
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

        @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
        public KeyInfo getKeyInfo(int v) {
            return ((KeysetInfo)this.instance).getKeyInfo(v);
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
        public int getKeyInfoCount() {
            return ((KeysetInfo)this.instance).getKeyInfoCount();
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
        public List getKeyInfoList() {
            return Collections.unmodifiableList(((KeysetInfo)this.instance).getKeyInfoList());
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
        public int getPrimaryKeyId() {
            return ((KeysetInfo)this.instance).getPrimaryKeyId();
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

        public Builder removeKeyInfo(int v) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).removeKeyInfo(v);
            return this;
        }

        public Builder setKeyInfo(int v, com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder keysetInfo$KeyInfo$Builder0) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).setKeyInfo(v, ((KeyInfo)keysetInfo$KeyInfo$Builder0.build()));
            return this;
        }

        public Builder setKeyInfo(int v, KeyInfo keysetInfo$KeyInfo0) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).setKeyInfo(v, keysetInfo$KeyInfo0);
            return this;
        }

        public Builder setPrimaryKeyId(int v) {
            this.copyOnWrite();
            ((KeysetInfo)this.instance).setPrimaryKeyId(v);
            return this;
        }
    }

    public static final class KeyInfo extends GeneratedMessageLite implements KeyInfoOrBuilder {
        public static final class com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KeyInfoOrBuilder {
            private com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder() {
                super(KeyInfo.DEFAULT_INSTANCE);
            }

            com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder(com.google.crypto.tink.proto.KeysetInfo.1 keysetInfo$10) {
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

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder clearKeyId() {
                this.copyOnWrite();
                ((KeyInfo)this.instance).clearKeyId();
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder clearOutputPrefixType() {
                this.copyOnWrite();
                ((KeyInfo)this.instance).clearOutputPrefixType();
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder clearStatus() {
                this.copyOnWrite();
                ((KeyInfo)this.instance).clearStatus();
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder clearTypeUrl() {
                this.copyOnWrite();
                ((KeyInfo)this.instance).clearTypeUrl();
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

            @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
            public int getKeyId() {
                return ((KeyInfo)this.instance).getKeyId();
            }

            @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
            public OutputPrefixType getOutputPrefixType() {
                return ((KeyInfo)this.instance).getOutputPrefixType();
            }

            @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
            public int getOutputPrefixTypeValue() {
                return ((KeyInfo)this.instance).getOutputPrefixTypeValue();
            }

            @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
            public KeyStatusType getStatus() {
                return ((KeyInfo)this.instance).getStatus();
            }

            @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
            public int getStatusValue() {
                return ((KeyInfo)this.instance).getStatusValue();
            }

            // 去混淆评级： 低(20)
            @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
            public String getTypeUrl() {
                return "";
            }

            @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
            public ByteString getTypeUrlBytes() {
                return ((KeyInfo)this.instance).getTypeUrlBytes();
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

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder setKeyId(int v) {
                this.copyOnWrite();
                ((KeyInfo)this.instance).setKeyId(v);
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder setOutputPrefixType(OutputPrefixType outputPrefixType0) {
                this.copyOnWrite();
                ((KeyInfo)this.instance).setOutputPrefixType(outputPrefixType0);
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder setOutputPrefixTypeValue(int v) {
                this.copyOnWrite();
                ((KeyInfo)this.instance).setOutputPrefixTypeValue(v);
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder setStatus(KeyStatusType keyStatusType0) {
                this.copyOnWrite();
                ((KeyInfo)this.instance).setStatus(keyStatusType0);
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder setStatusValue(int v) {
                this.copyOnWrite();
                ((KeyInfo)this.instance).setStatusValue(v);
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder setTypeUrl(String s) {
                this.copyOnWrite();
                ((KeyInfo)this.instance).setTypeUrl(s);
                return this;
            }

            public com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder setTypeUrlBytes(ByteString byteString0) {
                this.copyOnWrite();
                ((KeyInfo)this.instance).setTypeUrlBytes(byteString0);
                return this;
            }
        }

        private static final KeyInfo DEFAULT_INSTANCE = null;
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        private static volatile Parser PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        public static final int TYPE_URL_FIELD_NUMBER = 1;
        private int keyId_;
        private int outputPrefixType_;
        private int status_;
        private String typeUrl_;

        static {
            KeyInfo keysetInfo$KeyInfo0 = new KeyInfo();
            KeyInfo.DEFAULT_INSTANCE = keysetInfo$KeyInfo0;
            GeneratedMessageLite.registerDefaultInstance(KeyInfo.class, keysetInfo$KeyInfo0);
        }

        private KeyInfo() {
            this.typeUrl_ = "";
        }

        private void clearKeyId() {
            this.keyId_ = 0;
        }

        private void clearOutputPrefixType() {
            this.outputPrefixType_ = 0;
        }

        private void clearStatus() {
            this.status_ = 0;
        }

        private void clearTypeUrl() {
            this.typeUrl_ = "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
            switch(com.google.crypto.tink.proto.KeysetInfo.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
                case 1: {
                    return new KeyInfo();
                }
                case 2: {
                    return new com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder(null);
                }
                case 3: {
                    return KeyInfo.newMessageInfo(KeyInfo.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000B\u0004\f", new Object[]{"typeUrl_", "status_", "keyId_", "outputPrefixType_"});
                }
                case 4: {
                    return KeyInfo.DEFAULT_INSTANCE;
                }
                case 5: {
                    Parser parser0 = KeyInfo.PARSER;
                    if(parser0 == null) {
                        Class class0 = KeyInfo.class;
                        synchronized(class0) {
                            Parser parser1 = KeyInfo.PARSER;
                            if(parser1 == null) {
                                parser1 = new DefaultInstanceBasedParser(KeyInfo.DEFAULT_INSTANCE);
                                KeyInfo.PARSER = parser1;
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

        public static KeyInfo getDefaultInstance() {
            return KeyInfo.DEFAULT_INSTANCE;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
        public int getKeyId() {
            return this.keyId_;
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
        public OutputPrefixType getOutputPrefixType() {
            OutputPrefixType outputPrefixType0 = OutputPrefixType.forNumber(this.outputPrefixType_);
            return outputPrefixType0 == null ? OutputPrefixType.UNRECOGNIZED : outputPrefixType0;
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
        public int getOutputPrefixTypeValue() {
            return this.outputPrefixType_;
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
        public KeyStatusType getStatus() {
            KeyStatusType keyStatusType0 = KeyStatusType.forNumber(this.status_);
            return keyStatusType0 == null ? KeyStatusType.UNRECOGNIZED : keyStatusType0;
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
        public int getStatusValue() {
            return this.status_;
        }

        @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
        public String getTypeUrl() [...] // 潜在的解密器

        @Override  // com.google.crypto.tink.proto.KeysetInfo$KeyInfoOrBuilder
        public ByteString getTypeUrlBytes() {
            return ByteString.copyFromUtf8(this.typeUrl_);
        }

        public static com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder newBuilder() {
            return (com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder)KeyInfo.DEFAULT_INSTANCE.createBuilder();
        }

        public static com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder newBuilder(KeyInfo keysetInfo$KeyInfo0) {
            return (com.google.crypto.tink.proto.KeysetInfo.KeyInfo.Builder)KeyInfo.DEFAULT_INSTANCE.createBuilder(keysetInfo$KeyInfo0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        public static KeyInfo parseDelimitedFrom(InputStream inputStream0) throws IOException {
            return (KeyInfo)KeyInfo.parseDelimitedFrom(KeyInfo.DEFAULT_INSTANCE, inputStream0);
        }

        public static KeyInfo parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (KeyInfo)KeyInfo.parseDelimitedFrom(KeyInfo.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
        }

        public static KeyInfo parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, byteString0);
        }

        public static KeyInfo parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
        }

        public static KeyInfo parseFrom(CodedInputStream codedInputStream0) throws IOException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, codedInputStream0);
        }

        public static KeyInfo parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
        }

        public static KeyInfo parseFrom(InputStream inputStream0) throws IOException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, inputStream0);
        }

        public static KeyInfo parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
        }

        public static KeyInfo parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, byteBuffer0);
        }

        public static KeyInfo parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
        }

        public static KeyInfo parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, arr_b);
        }

        public static KeyInfo parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (KeyInfo)GeneratedMessageLite.parseFrom(KeyInfo.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
        }

        public static Parser parser() {
            return KeyInfo.DEFAULT_INSTANCE.getParserForType();
        }

        private void setKeyId(int v) {
            this.keyId_ = v;
        }

        private void setOutputPrefixType(OutputPrefixType outputPrefixType0) {
            this.outputPrefixType_ = outputPrefixType0.getNumber();
        }

        private void setOutputPrefixTypeValue(int v) {
            this.outputPrefixType_ = v;
        }

        private void setStatus(KeyStatusType keyStatusType0) {
            this.status_ = keyStatusType0.getNumber();
        }

        private void setStatusValue(int v) {
            this.status_ = v;
        }

        private void setTypeUrl(String s) {
            s.getClass();
            this.typeUrl_ = s;
        }

        private void setTypeUrlBytes(ByteString byteString0) {
            KeyInfo.checkByteStringIsUtf8(byteString0);
            this.typeUrl_ = byteString0.toStringUtf8();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }
    }

    public interface KeyInfoOrBuilder extends MessageLiteOrBuilder {
        int getKeyId();

        OutputPrefixType getOutputPrefixType();

        int getOutputPrefixTypeValue();

        KeyStatusType getStatus();

        int getStatusValue();

        String getTypeUrl();

        ByteString getTypeUrlBytes();
    }

    private static final KeysetInfo DEFAULT_INSTANCE = null;
    public static final int KEY_INFO_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    private ProtobufList keyInfo_;
    private int primaryKeyId_;

    static {
        KeysetInfo keysetInfo0 = new KeysetInfo();
        KeysetInfo.DEFAULT_INSTANCE = keysetInfo0;
        GeneratedMessageLite.registerDefaultInstance(KeysetInfo.class, keysetInfo0);
    }

    private KeysetInfo() {
        this.keyInfo_ = KeysetInfo.emptyProtobufList();
    }

    private void addAllKeyInfo(Iterable iterable0) {
        this.ensureKeyInfoIsMutable();
        AbstractMessageLite.addAll(iterable0, this.keyInfo_);
    }

    private void addKeyInfo(int v, KeyInfo keysetInfo$KeyInfo0) {
        keysetInfo$KeyInfo0.getClass();
        this.ensureKeyInfoIsMutable();
        this.keyInfo_.add(v, keysetInfo$KeyInfo0);
    }

    private void addKeyInfo(KeyInfo keysetInfo$KeyInfo0) {
        keysetInfo$KeyInfo0.getClass();
        this.ensureKeyInfoIsMutable();
        this.keyInfo_.add(keysetInfo$KeyInfo0);
    }

    private void clearKeyInfo() {
        this.keyInfo_ = KeysetInfo.emptyProtobufList();
    }

    private void clearPrimaryKeyId() {
        this.primaryKeyId_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.KeysetInfo.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new KeysetInfo();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return KeysetInfo.newMessageInfo(KeysetInfo.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000B\u0002\u001B", new Object[]{"primaryKeyId_", "keyInfo_", KeyInfo.class});
            }
            case 4: {
                return KeysetInfo.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = KeysetInfo.PARSER;
                if(parser0 == null) {
                    Class class0 = KeysetInfo.class;
                    synchronized(class0) {
                        Parser parser1 = KeysetInfo.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(KeysetInfo.DEFAULT_INSTANCE);
                            KeysetInfo.PARSER = parser1;
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

    private void ensureKeyInfoIsMutable() {
        ProtobufList internal$ProtobufList0 = this.keyInfo_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.keyInfo_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    public static KeysetInfo getDefaultInstance() {
        return KeysetInfo.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
    public KeyInfo getKeyInfo(int v) {
        return (KeyInfo)this.keyInfo_.get(v);
    }

    @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
    public int getKeyInfoCount() {
        return this.keyInfo_.size();
    }

    @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
    public List getKeyInfoList() {
        return this.keyInfo_;
    }

    public KeyInfoOrBuilder getKeyInfoOrBuilder(int v) {
        return (KeyInfoOrBuilder)this.keyInfo_.get(v);
    }

    public List getKeyInfoOrBuilderList() {
        return this.keyInfo_;
    }

    @Override  // com.google.crypto.tink.proto.KeysetInfoOrBuilder
    public int getPrimaryKeyId() {
        return this.primaryKeyId_;
    }

    public static Builder newBuilder() {
        return (Builder)KeysetInfo.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(KeysetInfo keysetInfo0) {
        return (Builder)KeysetInfo.DEFAULT_INSTANCE.createBuilder(keysetInfo0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static KeysetInfo parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (KeysetInfo)KeysetInfo.parseDelimitedFrom(KeysetInfo.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeysetInfo parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeysetInfo)KeysetInfo.parseDelimitedFrom(KeysetInfo.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeysetInfo parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, byteString0);
    }

    public static KeysetInfo parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static KeysetInfo parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static KeysetInfo parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static KeysetInfo parseFrom(InputStream inputStream0) throws IOException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, inputStream0);
    }

    public static KeysetInfo parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static KeysetInfo parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static KeysetInfo parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static KeysetInfo parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, arr_b);
    }

    public static KeysetInfo parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (KeysetInfo)GeneratedMessageLite.parseFrom(KeysetInfo.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return KeysetInfo.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeKeyInfo(int v) {
        this.ensureKeyInfoIsMutable();
        this.keyInfo_.remove(v);
    }

    private void setKeyInfo(int v, KeyInfo keysetInfo$KeyInfo0) {
        keysetInfo$KeyInfo0.getClass();
        this.ensureKeyInfoIsMutable();
        this.keyInfo_.set(v, keysetInfo$KeyInfo0);
    }

    private void setPrimaryKeyId(int v) {
        this.primaryKeyId_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

