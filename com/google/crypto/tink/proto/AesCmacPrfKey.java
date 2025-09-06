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

public final class AesCmacPrfKey extends GeneratedMessageLite implements AesCmacPrfKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCmacPrfKeyOrBuilder {
        private Builder() {
            super(AesCmacPrfKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCmacPrfKey.1 aesCmacPrfKey$10) {
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
            ((AesCmacPrfKey)this.instance).clearKeyValue();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesCmacPrfKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesCmacPrfKeyOrBuilder
        public ByteString getKeyValue() {
            return ((AesCmacPrfKey)this.instance).getKeyValue();
        }

        @Override  // com.google.crypto.tink.proto.AesCmacPrfKeyOrBuilder
        public int getVersion() {
            return ((AesCmacPrfKey)this.instance).getVersion();
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

        public Builder setKeyValue(ByteString byteString0) {
            this.copyOnWrite();
            ((AesCmacPrfKey)this.instance).setKeyValue(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesCmacPrfKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesCmacPrfKey DEFAULT_INSTANCE = null;
    public static final int KEY_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_;
    private int version_;

    static {
        AesCmacPrfKey aesCmacPrfKey0 = new AesCmacPrfKey();
        AesCmacPrfKey.DEFAULT_INSTANCE = aesCmacPrfKey0;
        GeneratedMessageLite.registerDefaultInstance(AesCmacPrfKey.class, aesCmacPrfKey0);
    }

    private AesCmacPrfKey() {
        this.keyValue_ = ByteString.EMPTY;
    }

    private void clearKeyValue() {
        this.keyValue_ = AesCmacPrfKey.getDefaultInstance().getKeyValue();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCmacPrfKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCmacPrfKey.newMessageInfo(AesCmacPrfKey.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\n", new Object[]{"version_", "keyValue_"});
            }
            case 4: {
                return AesCmacPrfKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCmacPrfKey.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCmacPrfKey.class;
                    synchronized(class0) {
                        Parser parser1 = AesCmacPrfKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCmacPrfKey.DEFAULT_INSTANCE);
                            AesCmacPrfKey.PARSER = parser1;
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

    public static AesCmacPrfKey getDefaultInstance() {
        return AesCmacPrfKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCmacPrfKeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override  // com.google.crypto.tink.proto.AesCmacPrfKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)AesCmacPrfKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCmacPrfKey aesCmacPrfKey0) {
        return (Builder)AesCmacPrfKey.DEFAULT_INSTANCE.createBuilder(aesCmacPrfKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCmacPrfKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCmacPrfKey)AesCmacPrfKey.parseDelimitedFrom(AesCmacPrfKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCmacPrfKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCmacPrfKey)AesCmacPrfKey.parseDelimitedFrom(AesCmacPrfKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCmacPrfKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCmacPrfKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCmacPrfKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCmacPrfKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCmacPrfKey parseFrom(InputStream inputStream0) throws IOException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCmacPrfKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCmacPrfKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCmacPrfKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCmacPrfKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCmacPrfKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCmacPrfKey)GeneratedMessageLite.parseFrom(AesCmacPrfKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCmacPrfKey.DEFAULT_INSTANCE.getParserForType();
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

    class com.google.crypto.tink.proto.AesCmacPrfKey.1 {
        static final int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] arr_v = new int[MethodToInvoke.values().length];
            com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = arr_v;
            try {
                arr_v[MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.GET_PARSER.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacPrfKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

