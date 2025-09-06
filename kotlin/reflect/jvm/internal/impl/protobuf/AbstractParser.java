package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.InputStream;

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

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
    public Object parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseDelimitedFrom(inputStream0, extensionRegistryLite0);
    }

    public MessageLite parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(this.parsePartialDelimitedFrom(inputStream0, extensionRegistryLite0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
    public Object parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(inputStream0, extensionRegistryLite0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
    public Object parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.parseFrom(byteString0, extensionRegistryLite0);
    }

    public MessageLite parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(this.parsePartialFrom(inputStream0, extensionRegistryLite0));
    }

    public MessageLite parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return this.checkMessageInitialized(this.parsePartialFrom(byteString0, extensionRegistryLite0));
    }

    public MessageLite parsePartialDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        try {
            int v = inputStream0.read();
            return v == -1 ? null : this.parsePartialFrom(new LimitedInputStream(inputStream0, CodedInputStream.readRawVarint32(v, inputStream0)), extensionRegistryLite0);
        }
        catch(IOException iOException0) {
            throw new InvalidProtocolBufferException(iOException0.getMessage());
        }
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
}

