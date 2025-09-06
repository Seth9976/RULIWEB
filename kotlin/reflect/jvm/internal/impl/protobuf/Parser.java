package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.InputStream;

public interface Parser {
    Object parseDelimitedFrom(InputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parseFrom(InputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parseFrom(ByteString arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parsePartialFrom(CodedInputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;
}

