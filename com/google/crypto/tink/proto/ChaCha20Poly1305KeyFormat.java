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

public final class ChaCha20Poly1305KeyFormat extends GeneratedMessageLite implements ChaCha20Poly1305KeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements ChaCha20Poly1305KeyFormatOrBuilder {
        private Builder() {
            super(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.ChaCha20Poly1305KeyFormat.1 chaCha20Poly1305KeyFormat$10) {
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
    }

    private static final ChaCha20Poly1305KeyFormat DEFAULT_INSTANCE;
    private static volatile Parser PARSER;

    static {
        ChaCha20Poly1305KeyFormat chaCha20Poly1305KeyFormat0 = new ChaCha20Poly1305KeyFormat();
        ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE = chaCha20Poly1305KeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(ChaCha20Poly1305KeyFormat.class, chaCha20Poly1305KeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.ChaCha20Poly1305KeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new ChaCha20Poly1305KeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return ChaCha20Poly1305KeyFormat.newMessageInfo(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, "\u0000\u0000", null);
            }
            case 4: {
                return ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = ChaCha20Poly1305KeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = ChaCha20Poly1305KeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = ChaCha20Poly1305KeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE);
                            ChaCha20Poly1305KeyFormat.PARSER = parser1;
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

    public static ChaCha20Poly1305KeyFormat getDefaultInstance() {
        return ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public static Builder newBuilder() {
        return (Builder)ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ChaCha20Poly1305KeyFormat chaCha20Poly1305KeyFormat0) {
        return (Builder)ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE.createBuilder(chaCha20Poly1305KeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static ChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (ChaCha20Poly1305KeyFormat)ChaCha20Poly1305KeyFormat.parseDelimitedFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static ChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (ChaCha20Poly1305KeyFormat)ChaCha20Poly1305KeyFormat.parseDelimitedFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat)GeneratedMessageLite.parseFrom(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

