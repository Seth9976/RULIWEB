package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class AbstractParser implements Parser {
    private static final ExtensionRegistryLite EMPTY_REGISTRY;

    static {
        AbstractParser.EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();
    }

    private MessageLite checkMessageInitialized(MessageLite messageLite0) throws InvalidProtocolBufferException {
        if(messageLite0 != null && !messageLite0.isInitialized()) {
            throw this.newUninitializedMessageException(messageLite0).asInvalidProtocolBufferException().setUnfinishedMessage(messageLite0);
        }
        return messageLite0;
    }

    // 去混淆评级： 低(20)
    private UninitializedMessageException newUninitializedMessageException(MessageLite messageLite0) {
        return messageLite0 instanceof AbstractMessageLite ? ((AbstractMessageLite)messageLite0).newUninitializedMessageException() : new UninitializedMessageException(messageLite0);
    }

    public MessageLite parseDelimitedFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parseDelimitedFrom(inputStream0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(this.parsePartialDelimitedFrom(inputStream0, extensionRegistryLite0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseDelimitedFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parseDelimitedFrom(inputStream0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseDelimitedFrom(inputStream0, extensionRegistryLite0);
    }

    public MessageLite parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseFrom(byteString0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(this.parsePartialFrom(byteString0, extensionRegistryLite0));
    }

    public MessageLite parseFrom(CodedInputStream codedInputStream0) throws InvalidProtocolBufferException {
        return this.parseFrom(codedInputStream0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(((MessageLite)this.parsePartialFrom(codedInputStream0, extensionRegistryLite0)));
    }

    public MessageLite parseFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parseFrom(inputStream0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(this.parsePartialFrom(inputStream0, extensionRegistryLite0));
    }

    public MessageLite parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return this.parseFrom(byteBuffer0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        CodedInputStream codedInputStream0 = CodedInputStream.newInstance(byteBuffer0);
        MessageLite messageLite0 = (MessageLite)this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
        try {
            codedInputStream0.checkLastTagWas(0);
            return this.checkMessageInitialized(messageLite0);
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw invalidProtocolBufferException0.setUnfinishedMessage(messageLite0);
        }
    }

    public MessageLite parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return this.parseFrom(arr_b, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parseFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
        return this.parseFrom(arr_b, v, v1, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parseFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(this.parsePartialFrom(arr_b, v, v1, extensionRegistryLite0));
    }

    public MessageLite parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(arr_b, 0, arr_b.length, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseFrom(byteString0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(byteString0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(CodedInputStream codedInputStream0) throws InvalidProtocolBufferException {
        return this.parseFrom(codedInputStream0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(codedInputStream0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parseFrom(inputStream0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(inputStream0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return this.parseFrom(byteBuffer0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(byteBuffer0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return this.parseFrom(arr_b);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
        return this.parseFrom(arr_b, v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(arr_b, v, v1, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(arr_b, extensionRegistryLite0);
    }

    public MessageLite parsePartialDelimitedFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parsePartialDelimitedFrom(inputStream0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parsePartialDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        try {
            int v = inputStream0.read();
            return v == -1 ? null : this.parsePartialFrom(new LimitedInputStream(inputStream0, CodedInputStream.readRawVarint32(v, inputStream0)), extensionRegistryLite0);
        }
        catch(IOException iOException0) {
            throw new InvalidProtocolBufferException(iOException0);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialDelimitedFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parsePartialDelimitedFrom(inputStream0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parsePartialDelimitedFrom(inputStream0, extensionRegistryLite0);
    }

    public MessageLite parsePartialFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(byteString0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parsePartialFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        CodedInputStream codedInputStream0 = byteString0.newCodedInput();
        MessageLite messageLite0 = (MessageLite)this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
        try {
            codedInputStream0.checkLastTagWas(0);
            return messageLite0;
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw invalidProtocolBufferException0.setUnfinishedMessage(messageLite0);
        }
    }

    public MessageLite parsePartialFrom(CodedInputStream codedInputStream0) throws InvalidProtocolBufferException {
        return (MessageLite)this.parsePartialFrom(codedInputStream0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parsePartialFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(inputStream0, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parsePartialFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        CodedInputStream codedInputStream0 = CodedInputStream.newInstance(inputStream0);
        MessageLite messageLite0 = (MessageLite)this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
        try {
            codedInputStream0.checkLastTagWas(0);
            return messageLite0;
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw invalidProtocolBufferException0.setUnfinishedMessage(messageLite0);
        }
    }

    public MessageLite parsePartialFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(arr_b, 0, arr_b.length, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parsePartialFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(arr_b, v, v1, AbstractParser.EMPTY_REGISTRY);
    }

    public MessageLite parsePartialFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        CodedInputStream codedInputStream0 = CodedInputStream.newInstance(arr_b, v, v1);
        MessageLite messageLite0 = (MessageLite)this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
        try {
            codedInputStream0.checkLastTagWas(0);
            return messageLite0;
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw invalidProtocolBufferException0.setUnfinishedMessage(messageLite0);
        }
    }

    public MessageLite parsePartialFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(arr_b, 0, arr_b.length, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(byteString0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(byteString0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(CodedInputStream codedInputStream0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(codedInputStream0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(InputStream inputStream0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(inputStream0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(inputStream0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(arr_b);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(arr_b, v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(arr_b, v, v1, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Parser
    public Object parsePartialFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parsePartialFrom(arr_b, extensionRegistryLite0);
    }
}

