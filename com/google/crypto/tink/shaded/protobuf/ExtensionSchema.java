package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.Map.Entry;

@CheckReturnValue
abstract class ExtensionSchema {
    abstract int extensionNumber(Map.Entry arg1);

    abstract Object findExtensionByNumber(ExtensionRegistryLite arg1, MessageLite arg2, int arg3);

    abstract FieldSet getExtensions(Object arg1);

    abstract FieldSet getMutableExtensions(Object arg1);

    abstract boolean hasExtensions(MessageLite arg1);

    abstract void makeImmutable(Object arg1);

    abstract Object parseExtension(Object arg1, Reader arg2, Object arg3, ExtensionRegistryLite arg4, FieldSet arg5, Object arg6, UnknownFieldSchema arg7) throws IOException;

    abstract void parseLengthPrefixedMessageSetItem(Reader arg1, Object arg2, ExtensionRegistryLite arg3, FieldSet arg4) throws IOException;

    abstract void parseMessageSetItem(ByteString arg1, Object arg2, ExtensionRegistryLite arg3, FieldSet arg4) throws IOException;

    abstract void serializeExtension(Writer arg1, Map.Entry arg2) throws IOException;

    abstract void setExtensions(Object arg1, FieldSet arg2);
}

