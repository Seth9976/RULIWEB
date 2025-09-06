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

public final class AesSivKeyFormat extends GeneratedMessageLite implements AesSivKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesSivKeyFormatOrBuilder {
        private Builder() {
            super(AesSivKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesSivKeyFormat.1 aesSivKeyFormat$10) {
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

        public Builder clearKeySize() {
            this.copyOnWrite();
            ((AesSivKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((AesSivKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.AesSivKeyFormatOrBuilder
        public int getKeySize() {
            return ((AesSivKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.AesSivKeyFormatOrBuilder
        public int getVersion() {
            return ((AesSivKeyFormat)this.instance).getVersion();
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

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((AesSivKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((AesSivKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    private static final AesSivKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 2;
    private int keySize_;
    private int version_;

    static {
        AesSivKeyFormat aesSivKeyFormat0 = new AesSivKeyFormat();
        AesSivKeyFormat.DEFAULT_INSTANCE = aesSivKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(AesSivKeyFormat.class, aesSivKeyFormat0);
    }

    private void clearKeySize() {
        this.keySize_ = 0;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesSivKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesSivKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesSivKeyFormat.newMessageInfo(AesSivKeyFormat.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\u000B", new Object[]{"keySize_", "version_"});
            }
            case 4: {
                return AesSivKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesSivKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = AesSivKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = AesSivKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesSivKeyFormat.DEFAULT_INSTANCE);
                            AesSivKeyFormat.PARSER = parser1;
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

    public static AesSivKeyFormat getDefaultInstance() {
        return AesSivKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesSivKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.AesSivKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)AesSivKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesSivKeyFormat aesSivKeyFormat0) {
        return (Builder)AesSivKeyFormat.DEFAULT_INSTANCE.createBuilder(aesSivKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesSivKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesSivKeyFormat)AesSivKeyFormat.parseDelimitedFrom(AesSivKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesSivKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesSivKeyFormat)AesSivKeyFormat.parseDelimitedFrom(AesSivKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesSivKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static AesSivKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesSivKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesSivKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesSivKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesSivKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesSivKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesSivKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesSivKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static AesSivKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat)GeneratedMessageLite.parseFrom(AesSivKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesSivKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

