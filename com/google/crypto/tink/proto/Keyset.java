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

public final class Keyset extends GeneratedMessageLite implements KeysetOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KeysetOrBuilder {
        private Builder() {
            super(Keyset.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.Keyset.1 keyset$10) {
        }

        public Builder addAllKey(Iterable iterable0) {
            this.copyOnWrite();
            ((Keyset)this.instance).addAllKey(iterable0);
            return this;
        }

        public Builder addKey(int v, com.google.crypto.tink.proto.Keyset.Key.Builder keyset$Key$Builder0) {
            this.copyOnWrite();
            ((Keyset)this.instance).addKey(v, ((Key)keyset$Key$Builder0.build()));
            return this;
        }

        public Builder addKey(int v, Key keyset$Key0) {
            this.copyOnWrite();
            ((Keyset)this.instance).addKey(v, keyset$Key0);
            return this;
        }

        public Builder addKey(com.google.crypto.tink.proto.Keyset.Key.Builder keyset$Key$Builder0) {
            this.copyOnWrite();
            ((Keyset)this.instance).addKey(((Key)keyset$Key$Builder0.build()));
            return this;
        }

        public Builder addKey(Key keyset$Key0) {
            this.copyOnWrite();
            ((Keyset)this.instance).addKey(keyset$Key0);
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

        public Builder clearKey() {
            this.copyOnWrite();
            ((Keyset)this.instance).clearKey();
            return this;
        }

        public Builder clearPrimaryKeyId() {
            this.copyOnWrite();
            ((Keyset)this.instance).clearPrimaryKeyId();
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

        @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
        public Key getKey(int v) {
            return ((Keyset)this.instance).getKey(v);
        }

        @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
        public int getKeyCount() {
            return ((Keyset)this.instance).getKeyCount();
        }

        @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
        public List getKeyList() {
            return Collections.unmodifiableList(((Keyset)this.instance).getKeyList());
        }

        @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
        public int getPrimaryKeyId() {
            return ((Keyset)this.instance).getPrimaryKeyId();
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

        public Builder removeKey(int v) {
            this.copyOnWrite();
            ((Keyset)this.instance).removeKey(v);
            return this;
        }

        public Builder setKey(int v, com.google.crypto.tink.proto.Keyset.Key.Builder keyset$Key$Builder0) {
            this.copyOnWrite();
            ((Keyset)this.instance).setKey(v, ((Key)keyset$Key$Builder0.build()));
            return this;
        }

        public Builder setKey(int v, Key keyset$Key0) {
            this.copyOnWrite();
            ((Keyset)this.instance).setKey(v, keyset$Key0);
            return this;
        }

        public Builder setPrimaryKeyId(int v) {
            this.copyOnWrite();
            ((Keyset)this.instance).setPrimaryKeyId(v);
            return this;
        }
    }

    public static final class Key extends GeneratedMessageLite implements KeyOrBuilder {
        public static final class com.google.crypto.tink.proto.Keyset.Key.Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements KeyOrBuilder {
            private com.google.crypto.tink.proto.Keyset.Key.Builder() {
                super(Key.DEFAULT_INSTANCE);
            }

            com.google.crypto.tink.proto.Keyset.Key.Builder(com.google.crypto.tink.proto.Keyset.1 keyset$10) {
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

            public com.google.crypto.tink.proto.Keyset.Key.Builder clearKeyData() {
                this.copyOnWrite();
                ((Key)this.instance).clearKeyData();
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder clearKeyId() {
                this.copyOnWrite();
                ((Key)this.instance).clearKeyId();
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder clearOutputPrefixType() {
                this.copyOnWrite();
                ((Key)this.instance).clearOutputPrefixType();
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder clearStatus() {
                this.copyOnWrite();
                ((Key)this.instance).clearStatus();
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

            @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
            public KeyData getKeyData() {
                return ((Key)this.instance).getKeyData();
            }

            @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
            public int getKeyId() {
                return ((Key)this.instance).getKeyId();
            }

            @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
            public OutputPrefixType getOutputPrefixType() {
                return ((Key)this.instance).getOutputPrefixType();
            }

            @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
            public int getOutputPrefixTypeValue() {
                return ((Key)this.instance).getOutputPrefixTypeValue();
            }

            @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
            public KeyStatusType getStatus() {
                return ((Key)this.instance).getStatus();
            }

            @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
            public int getStatusValue() {
                return ((Key)this.instance).getStatusValue();
            }

            @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
            public boolean hasKeyData() {
                return ((Key)this.instance).hasKeyData();
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

            public com.google.crypto.tink.proto.Keyset.Key.Builder mergeKeyData(KeyData keyData0) {
                this.copyOnWrite();
                ((Key)this.instance).mergeKeyData(keyData0);
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder setKeyData(com.google.crypto.tink.proto.KeyData.Builder keyData$Builder0) {
                this.copyOnWrite();
                ((Key)this.instance).setKeyData(((KeyData)keyData$Builder0.build()));
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder setKeyData(KeyData keyData0) {
                this.copyOnWrite();
                ((Key)this.instance).setKeyData(keyData0);
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder setKeyId(int v) {
                this.copyOnWrite();
                ((Key)this.instance).setKeyId(v);
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder setOutputPrefixType(OutputPrefixType outputPrefixType0) {
                this.copyOnWrite();
                ((Key)this.instance).setOutputPrefixType(outputPrefixType0);
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder setOutputPrefixTypeValue(int v) {
                this.copyOnWrite();
                ((Key)this.instance).setOutputPrefixTypeValue(v);
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder setStatus(KeyStatusType keyStatusType0) {
                this.copyOnWrite();
                ((Key)this.instance).setStatus(keyStatusType0);
                return this;
            }

            public com.google.crypto.tink.proto.Keyset.Key.Builder setStatusValue(int v) {
                this.copyOnWrite();
                ((Key)this.instance).setStatusValue(v);
                return this;
            }
        }

        private static final Key DEFAULT_INSTANCE = null;
        public static final int KEY_DATA_FIELD_NUMBER = 1;
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        private static volatile Parser PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private KeyData keyData_;
        private int keyId_;
        private int outputPrefixType_;
        private int status_;

        static {
            Key keyset$Key0 = new Key();
            Key.DEFAULT_INSTANCE = keyset$Key0;
            GeneratedMessageLite.registerDefaultInstance(Key.class, keyset$Key0);
        }

        private void clearKeyData() {
            this.keyData_ = null;
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

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
            switch(com.google.crypto.tink.proto.Keyset.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
                case 1: {
                    return new Key();
                }
                case 2: {
                    return new com.google.crypto.tink.proto.Keyset.Key.Builder(null);
                }
                case 3: {
                    return Key.newMessageInfo(Key.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000B\u0004\f", new Object[]{"keyData_", "status_", "keyId_", "outputPrefixType_"});
                }
                case 4: {
                    return Key.DEFAULT_INSTANCE;
                }
                case 5: {
                    Parser parser0 = Key.PARSER;
                    if(parser0 == null) {
                        Class class0 = Key.class;
                        synchronized(class0) {
                            Parser parser1 = Key.PARSER;
                            if(parser1 == null) {
                                parser1 = new DefaultInstanceBasedParser(Key.DEFAULT_INSTANCE);
                                Key.PARSER = parser1;
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

        public static Key getDefaultInstance() {
            return Key.DEFAULT_INSTANCE;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
        public KeyData getKeyData() {
            return this.keyData_ == null ? KeyData.getDefaultInstance() : this.keyData_;
        }

        @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
        public int getKeyId() {
            return this.keyId_;
        }

        @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
        public OutputPrefixType getOutputPrefixType() {
            OutputPrefixType outputPrefixType0 = OutputPrefixType.forNumber(this.outputPrefixType_);
            return outputPrefixType0 == null ? OutputPrefixType.UNRECOGNIZED : outputPrefixType0;
        }

        @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
        public int getOutputPrefixTypeValue() {
            return this.outputPrefixType_;
        }

        @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
        public KeyStatusType getStatus() {
            KeyStatusType keyStatusType0 = KeyStatusType.forNumber(this.status_);
            return keyStatusType0 == null ? KeyStatusType.UNRECOGNIZED : keyStatusType0;
        }

        @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
        public int getStatusValue() {
            return this.status_;
        }

        @Override  // com.google.crypto.tink.proto.Keyset$KeyOrBuilder
        public boolean hasKeyData() {
            return this.keyData_ != null;
        }

        private void mergeKeyData(KeyData keyData0) {
            keyData0.getClass();
            if(this.keyData_ != null && this.keyData_ != KeyData.getDefaultInstance()) {
                this.keyData_ = (KeyData)((com.google.crypto.tink.proto.KeyData.Builder)KeyData.newBuilder(this.keyData_).mergeFrom(keyData0)).buildPartial();
                return;
            }
            this.keyData_ = keyData0;
        }

        public static com.google.crypto.tink.proto.Keyset.Key.Builder newBuilder() {
            return (com.google.crypto.tink.proto.Keyset.Key.Builder)Key.DEFAULT_INSTANCE.createBuilder();
        }

        public static com.google.crypto.tink.proto.Keyset.Key.Builder newBuilder(Key keyset$Key0) {
            return (com.google.crypto.tink.proto.Keyset.Key.Builder)Key.DEFAULT_INSTANCE.createBuilder(keyset$Key0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        public static Key parseDelimitedFrom(InputStream inputStream0) throws IOException {
            return (Key)Key.parseDelimitedFrom(Key.DEFAULT_INSTANCE, inputStream0);
        }

        public static Key parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (Key)Key.parseDelimitedFrom(Key.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
        }

        public static Key parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, byteString0);
        }

        public static Key parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
        }

        public static Key parseFrom(CodedInputStream codedInputStream0) throws IOException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, codedInputStream0);
        }

        public static Key parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
        }

        public static Key parseFrom(InputStream inputStream0) throws IOException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, inputStream0);
        }

        public static Key parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
        }

        public static Key parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, byteBuffer0);
        }

        public static Key parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
        }

        public static Key parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, arr_b);
        }

        public static Key parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return (Key)GeneratedMessageLite.parseFrom(Key.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
        }

        public static Parser parser() {
            return Key.DEFAULT_INSTANCE.getParserForType();
        }

        private void setKeyData(KeyData keyData0) {
            keyData0.getClass();
            this.keyData_ = keyData0;
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

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }
    }

    public interface KeyOrBuilder extends MessageLiteOrBuilder {
        KeyData getKeyData();

        int getKeyId();

        OutputPrefixType getOutputPrefixType();

        int getOutputPrefixTypeValue();

        KeyStatusType getStatus();

        int getStatusValue();

        boolean hasKeyData();
    }

    private static final Keyset DEFAULT_INSTANCE = null;
    public static final int KEY_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    private ProtobufList key_;
    private int primaryKeyId_;

    static {
        Keyset keyset0 = new Keyset();
        Keyset.DEFAULT_INSTANCE = keyset0;
        GeneratedMessageLite.registerDefaultInstance(Keyset.class, keyset0);
    }

    private Keyset() {
        this.key_ = Keyset.emptyProtobufList();
    }

    private void addAllKey(Iterable iterable0) {
        this.ensureKeyIsMutable();
        AbstractMessageLite.addAll(iterable0, this.key_);
    }

    private void addKey(int v, Key keyset$Key0) {
        keyset$Key0.getClass();
        this.ensureKeyIsMutable();
        this.key_.add(v, keyset$Key0);
    }

    private void addKey(Key keyset$Key0) {
        keyset$Key0.getClass();
        this.ensureKeyIsMutable();
        this.key_.add(keyset$Key0);
    }

    private void clearKey() {
        this.key_ = Keyset.emptyProtobufList();
    }

    private void clearPrimaryKeyId() {
        this.primaryKeyId_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.Keyset.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Keyset();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Keyset.newMessageInfo(Keyset.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000B\u0002\u001B", new Object[]{"primaryKeyId_", "key_", Key.class});
            }
            case 4: {
                return Keyset.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Keyset.PARSER;
                if(parser0 == null) {
                    Class class0 = Keyset.class;
                    synchronized(class0) {
                        Parser parser1 = Keyset.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Keyset.DEFAULT_INSTANCE);
                            Keyset.PARSER = parser1;
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

    private void ensureKeyIsMutable() {
        ProtobufList internal$ProtobufList0 = this.key_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.key_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    public static Keyset getDefaultInstance() {
        return Keyset.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
    public Key getKey(int v) {
        return (Key)this.key_.get(v);
    }

    @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
    public int getKeyCount() {
        return this.key_.size();
    }

    @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
    public List getKeyList() {
        return this.key_;
    }

    public KeyOrBuilder getKeyOrBuilder(int v) {
        return (KeyOrBuilder)this.key_.get(v);
    }

    public List getKeyOrBuilderList() {
        return this.key_;
    }

    @Override  // com.google.crypto.tink.proto.KeysetOrBuilder
    public int getPrimaryKeyId() {
        return this.primaryKeyId_;
    }

    public static Builder newBuilder() {
        return (Builder)Keyset.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Keyset keyset0) {
        return (Builder)Keyset.DEFAULT_INSTANCE.createBuilder(keyset0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static Keyset parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Keyset)Keyset.parseDelimitedFrom(Keyset.DEFAULT_INSTANCE, inputStream0);
    }

    public static Keyset parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Keyset)Keyset.parseDelimitedFrom(Keyset.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Keyset parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, byteString0);
    }

    public static Keyset parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Keyset parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Keyset parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Keyset parseFrom(InputStream inputStream0) throws IOException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, inputStream0);
    }

    public static Keyset parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Keyset parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Keyset parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Keyset parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, arr_b);
    }

    public static Keyset parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Keyset)GeneratedMessageLite.parseFrom(Keyset.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Keyset.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeKey(int v) {
        this.ensureKeyIsMutable();
        this.key_.remove(v);
    }

    private void setKey(int v, Key keyset$Key0) {
        keyset$Key0.getClass();
        this.ensureKeyIsMutable();
        this.key_.set(v, keyset$Key0);
    }

    private void setPrimaryKeyId(int v) {
        this.primaryKeyId_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

