package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Empty extends GeneratedMessageLite implements EmptyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EmptyOrBuilder {
        private Builder() {
            super(Empty.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Empty.1 empty$10) {
        }
    }

    private static final Empty DEFAULT_INSTANCE;
    private static volatile Parser PARSER;

    static {
        Empty empty0 = new Empty();
        Empty.DEFAULT_INSTANCE = empty0;
        GeneratedMessageLite.registerDefaultInstance(Empty.class, empty0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Empty.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Empty();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Empty.newMessageInfo(Empty.DEFAULT_INSTANCE, "\u0000\u0000", null);
            }
            case 4: {
                return Empty.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Empty.PARSER;
                if(parser0 == null) {
                    Class class0 = Empty.class;
                    synchronized(class0) {
                        Parser parser1 = Empty.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Empty.DEFAULT_INSTANCE);
                            Empty.PARSER = parser1;
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

    public static Empty getDefaultInstance() {
        return Empty.DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder)Empty.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Empty empty0) {
        return (Builder)Empty.DEFAULT_INSTANCE.createBuilder(empty0);
    }

    public static Empty parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Empty)Empty.parseDelimitedFrom(Empty.DEFAULT_INSTANCE, inputStream0);
    }

    public static Empty parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Empty)Empty.parseDelimitedFrom(Empty.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Empty parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, byteString0);
    }

    public static Empty parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Empty parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Empty parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Empty parseFrom(InputStream inputStream0) throws IOException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, inputStream0);
    }

    public static Empty parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Empty parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Empty parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Empty parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, arr_b);
    }

    public static Empty parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Empty)GeneratedMessageLite.parseFrom(Empty.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Empty.DEFAULT_INSTANCE.getParserForType();
    }
}

