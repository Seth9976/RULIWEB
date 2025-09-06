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

public final class XChaCha20Poly1305KeyFormat extends GeneratedMessageLite implements XChaCha20Poly1305KeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements XChaCha20Poly1305KeyFormatOrBuilder {
        private Builder() {
            super(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormat.1 xChaCha20Poly1305KeyFormat$10) {
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

        public Builder clearVersion() {
            this.copyOnWrite();
            ((XChaCha20Poly1305KeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormatOrBuilder
        public int getVersion() {
            return ((XChaCha20Poly1305KeyFormat)this.instance).getVersion();
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

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((XChaCha20Poly1305KeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    private static final XChaCha20Poly1305KeyFormat DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int version_;

    static {
        XChaCha20Poly1305KeyFormat xChaCha20Poly1305KeyFormat0 = new XChaCha20Poly1305KeyFormat();
        XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE = xChaCha20Poly1305KeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(XChaCha20Poly1305KeyFormat.class, xChaCha20Poly1305KeyFormat0);
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new XChaCha20Poly1305KeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return XChaCha20Poly1305KeyFormat.newMessageInfo(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"version_"});
            }
            case 4: {
                return XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = XChaCha20Poly1305KeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = XChaCha20Poly1305KeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = XChaCha20Poly1305KeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE);
                            XChaCha20Poly1305KeyFormat.PARSER = parser1;
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

    public static XChaCha20Poly1305KeyFormat getDefaultInstance() {
        return XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(XChaCha20Poly1305KeyFormat xChaCha20Poly1305KeyFormat0) {
        return (Builder)XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE.createBuilder(xChaCha20Poly1305KeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static XChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (XChaCha20Poly1305KeyFormat)XChaCha20Poly1305KeyFormat.parseDelimitedFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static XChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (XChaCha20Poly1305KeyFormat)XChaCha20Poly1305KeyFormat.parseDelimitedFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static XChaCha20Poly1305KeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (XChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return XChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

