package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CheckReturnValue
interface Writer {
    public static enum FieldOrder {
        ASCENDING,
        DESCENDING;

    }

    FieldOrder fieldOrder();

    void writeBool(int arg1, boolean arg2) throws IOException;

    void writeBoolList(int arg1, List arg2, boolean arg3) throws IOException;

    void writeBytes(int arg1, ByteString arg2) throws IOException;

    void writeBytesList(int arg1, List arg2) throws IOException;

    void writeDouble(int arg1, double arg2) throws IOException;

    void writeDoubleList(int arg1, List arg2, boolean arg3) throws IOException;

    @Deprecated
    void writeEndGroup(int arg1) throws IOException;

    void writeEnum(int arg1, int arg2) throws IOException;

    void writeEnumList(int arg1, List arg2, boolean arg3) throws IOException;

    void writeFixed32(int arg1, int arg2) throws IOException;

    void writeFixed32List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeFixed64(int arg1, long arg2) throws IOException;

    void writeFixed64List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeFloat(int arg1, float arg2) throws IOException;

    void writeFloatList(int arg1, List arg2, boolean arg3) throws IOException;

    @Deprecated
    void writeGroup(int arg1, Object arg2) throws IOException;

    @Deprecated
    void writeGroup(int arg1, Object arg2, Schema arg3) throws IOException;

    @Deprecated
    void writeGroupList(int arg1, List arg2) throws IOException;

    @Deprecated
    void writeGroupList(int arg1, List arg2, Schema arg3) throws IOException;

    void writeInt32(int arg1, int arg2) throws IOException;

    void writeInt32List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeInt64(int arg1, long arg2) throws IOException;

    void writeInt64List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeMap(int arg1, Metadata arg2, Map arg3) throws IOException;

    void writeMessage(int arg1, Object arg2) throws IOException;

    void writeMessage(int arg1, Object arg2, Schema arg3) throws IOException;

    void writeMessageList(int arg1, List arg2) throws IOException;

    void writeMessageList(int arg1, List arg2, Schema arg3) throws IOException;

    void writeMessageSetItem(int arg1, Object arg2) throws IOException;

    void writeSFixed32(int arg1, int arg2) throws IOException;

    void writeSFixed32List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeSFixed64(int arg1, long arg2) throws IOException;

    void writeSFixed64List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeSInt32(int arg1, int arg2) throws IOException;

    void writeSInt32List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeSInt64(int arg1, long arg2) throws IOException;

    void writeSInt64List(int arg1, List arg2, boolean arg3) throws IOException;

    @Deprecated
    void writeStartGroup(int arg1) throws IOException;

    void writeString(int arg1, String arg2) throws IOException;

    void writeStringList(int arg1, List arg2) throws IOException;

    void writeUInt32(int arg1, int arg2) throws IOException;

    void writeUInt32List(int arg1, List arg2, boolean arg3) throws IOException;

    void writeUInt64(int arg1, long arg2) throws IOException;

    void writeUInt64List(int arg1, List arg2, boolean arg3) throws IOException;
}

