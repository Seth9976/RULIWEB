package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CheckReturnValue
interface Reader {
    public static final int READ_DONE = 0x7FFFFFFF;
    public static final int TAG_UNKNOWN;

    int getFieldNumber() throws IOException;

    int getTag();

    void mergeGroupField(Object arg1, Schema arg2, ExtensionRegistryLite arg3) throws IOException;

    void mergeMessageField(Object arg1, Schema arg2, ExtensionRegistryLite arg3) throws IOException;

    boolean readBool() throws IOException;

    void readBoolList(List arg1) throws IOException;

    ByteString readBytes() throws IOException;

    void readBytesList(List arg1) throws IOException;

    double readDouble() throws IOException;

    void readDoubleList(List arg1) throws IOException;

    int readEnum() throws IOException;

    void readEnumList(List arg1) throws IOException;

    int readFixed32() throws IOException;

    void readFixed32List(List arg1) throws IOException;

    long readFixed64() throws IOException;

    void readFixed64List(List arg1) throws IOException;

    float readFloat() throws IOException;

    void readFloatList(List arg1) throws IOException;

    @Deprecated
    Object readGroup(Class arg1, ExtensionRegistryLite arg2) throws IOException;

    @Deprecated
    Object readGroupBySchemaWithCheck(Schema arg1, ExtensionRegistryLite arg2) throws IOException;

    @Deprecated
    void readGroupList(List arg1, Schema arg2, ExtensionRegistryLite arg3) throws IOException;

    @Deprecated
    void readGroupList(List arg1, Class arg2, ExtensionRegistryLite arg3) throws IOException;

    int readInt32() throws IOException;

    void readInt32List(List arg1) throws IOException;

    long readInt64() throws IOException;

    void readInt64List(List arg1) throws IOException;

    void readMap(Map arg1, Metadata arg2, ExtensionRegistryLite arg3) throws IOException;

    Object readMessage(Class arg1, ExtensionRegistryLite arg2) throws IOException;

    Object readMessageBySchemaWithCheck(Schema arg1, ExtensionRegistryLite arg2) throws IOException;

    void readMessageList(List arg1, Schema arg2, ExtensionRegistryLite arg3) throws IOException;

    void readMessageList(List arg1, Class arg2, ExtensionRegistryLite arg3) throws IOException;

    int readSFixed32() throws IOException;

    void readSFixed32List(List arg1) throws IOException;

    long readSFixed64() throws IOException;

    void readSFixed64List(List arg1) throws IOException;

    int readSInt32() throws IOException;

    void readSInt32List(List arg1) throws IOException;

    long readSInt64() throws IOException;

    void readSInt64List(List arg1) throws IOException;

    String readString() throws IOException;

    void readStringList(List arg1) throws IOException;

    void readStringListRequireUtf8(List arg1) throws IOException;

    String readStringRequireUtf8() throws IOException;

    int readUInt32() throws IOException;

    void readUInt32List(List arg1) throws IOException;

    long readUInt64() throws IOException;

    void readUInt64List(List arg1) throws IOException;

    boolean shouldDiscardUnknownFields();

    boolean skipField() throws IOException;
}

