package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class CodedOutputStream {
    public static class OutOfSpaceException extends IOException {
        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private final byte[] buffer;
    private final int limit;
    private final OutputStream output;
    private int position;
    private int totalBytesWritten;

    private CodedOutputStream(OutputStream outputStream0, byte[] arr_b) {
        this.totalBytesWritten = 0;
        this.output = outputStream0;
        this.buffer = arr_b;
        this.position = 0;
        this.limit = arr_b.length;
    }

    public static int computeBoolSize(int v, boolean z) {
        return CodedOutputStream.computeTagSize(v) + 1;
    }

    public static int computeBoolSizeNoTag(boolean z) [...] // Inlined contents

    public static int computeByteArraySizeNoTag(byte[] arr_b) {
        return CodedOutputStream.computeRawVarint32Size(arr_b.length) + arr_b.length;
    }

    public static int computeBytesSize(int v, ByteString byteString0) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeBytesSizeNoTag(byteString0);
    }

    public static int computeBytesSizeNoTag(ByteString byteString0) {
        return CodedOutputStream.computeRawVarint32Size(byteString0.size()) + byteString0.size();
    }

    public static int computeDoubleSize(int v, double f) {
        return CodedOutputStream.computeTagSize(v) + 8;
    }

    public static int computeDoubleSizeNoTag(double f) [...] // Inlined contents

    public static int computeEnumSize(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeEnumSizeNoTag(v1);
    }

    public static int computeEnumSizeNoTag(int v) {
        return CodedOutputStream.computeInt32SizeNoTag(v);
    }

    public static int computeFixed32SizeNoTag(int v) {
        return 4;
    }

    public static int computeFixed64SizeNoTag(long v) {
        return 8;
    }

    public static int computeFloatSize(int v, float f) {
        return CodedOutputStream.computeTagSize(v) + 4;
    }

    public static int computeFloatSizeNoTag(float f) [...] // Inlined contents

    public static int computeGroupSizeNoTag(MessageLite messageLite0) {
        return messageLite0.getSerializedSize();
    }

    public static int computeInt32Size(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeInt32SizeNoTag(v1);
    }

    public static int computeInt32SizeNoTag(int v) {
        return v < 0 ? 10 : CodedOutputStream.computeRawVarint32Size(v);
    }

    public static int computeInt64SizeNoTag(long v) {
        return CodedOutputStream.computeRawVarint64Size(v);
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite lazyFieldLite0) {
        int v = lazyFieldLite0.getSerializedSize();
        return CodedOutputStream.computeRawVarint32Size(v) + v;
    }

    public static int computeMessageSize(int v, MessageLite messageLite0) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeMessageSizeNoTag(messageLite0);
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite0) {
        int v = messageLite0.getSerializedSize();
        return CodedOutputStream.computeRawVarint32Size(v) + v;
    }

    static int computePreferredBufferSize(int v) {
        return v <= 0x1000 ? v : 0x1000;
    }

    public static int computeRawVarint32Size(int v) {
        if((v & 0xFFFFFF80) == 0) {
            return 1;
        }
        if((v & 0xFFFFC000) == 0) {
            return 2;
        }
        if((0xFFE00000 & v) == 0) {
            return 3;
        }
        return (v & 0xF0000000) == 0 ? 4 : 5;
    }

    public static int computeRawVarint64Size(long v) {
        if((0xFFFFFFFFFFFFFF80L & v) == 0L) {
            return 1;
        }
        if((0xFFFFFFFFFFFFC000L & v) == 0L) {
            return 2;
        }
        if((0xFFFFFFFFFFE00000L & v) == 0L) {
            return 3;
        }
        if((0xFFFFFFFFF0000000L & v) == 0L) {
            return 4;
        }
        if((0xFFFFFFF800000000L & v) == 0L) {
            return 5;
        }
        if((0xFFFFFC0000000000L & v) == 0L) {
            return 6;
        }
        if((0xFFFE000000000000L & v) == 0L) {
            return 7;
        }
        if((0xFF00000000000000L & v) == 0L) {
            return 8;
        }
        return (v & 0x8000000000000000L) == 0L ? 9 : 10;
    }

    public static int computeSFixed32SizeNoTag(int v) {
        return 4;
    }

    public static int computeSFixed64SizeNoTag(long v) {
        return 8;
    }

    public static int computeSInt32SizeNoTag(int v) {
        return CodedOutputStream.computeRawVarint32Size(v >> 0x1F ^ v << 1);
    }

    public static int computeSInt64Size(int v, long v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeSInt64SizeNoTag(v1);
    }

    public static int computeSInt64SizeNoTag(long v) {
        return CodedOutputStream.computeRawVarint64Size(v >> 0x3F ^ v << 1);
    }

    public static int computeStringSizeNoTag(String s) {
        try {
            byte[] arr_b = s.getBytes("UTF-8");
            return CodedOutputStream.computeRawVarint32Size(arr_b.length) + arr_b.length;
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new RuntimeException("UTF-8 not supported.", unsupportedEncodingException0);
        }
    }

    public static int computeTagSize(int v) {
        return CodedOutputStream.computeRawVarint32Size(v << 3);
    }

    public static int computeUInt32SizeNoTag(int v) {
        return CodedOutputStream.computeRawVarint32Size(v);
    }

    public static int computeUInt64SizeNoTag(long v) {
        return CodedOutputStream.computeRawVarint64Size(v);
    }

    public static int encodeZigZag32(int v) [...] // Inlined contents

    public static long encodeZigZag64(long v) [...] // Inlined contents

    public void flush() throws IOException {
        if(this.output != null) {
            this.refreshBuffer();
        }
    }

    public static CodedOutputStream newInstance(OutputStream outputStream0, int v) {
        return new CodedOutputStream(outputStream0, new byte[v]);
    }

    private void refreshBuffer() throws IOException {
        OutputStream outputStream0 = this.output;
        if(outputStream0 == null) {
            throw new OutOfSpaceException();
        }
        outputStream0.write(this.buffer, 0, this.position);
        this.position = 0;
    }

    public void writeBool(int v, boolean z) throws IOException {
        this.writeTag(v, 0);
        this.writeBoolNoTag(z);
    }

    public void writeBoolNoTag(boolean z) throws IOException {
        this.writeRawByte(((int)z));
    }

    public void writeByteArrayNoTag(byte[] arr_b) throws IOException {
        this.writeRawVarint32(arr_b.length);
        this.writeRawBytes(arr_b);
    }

    public void writeBytes(int v, ByteString byteString0) throws IOException {
        this.writeTag(v, 2);
        this.writeBytesNoTag(byteString0);
    }

    public void writeBytesNoTag(ByteString byteString0) throws IOException {
        this.writeRawVarint32(byteString0.size());
        this.writeRawBytes(byteString0);
    }

    public void writeDouble(int v, double f) throws IOException {
        this.writeTag(v, 1);
        this.writeDoubleNoTag(f);
    }

    public void writeDoubleNoTag(double f) throws IOException {
        this.writeRawLittleEndian64(Double.doubleToRawLongBits(f));
    }

    public void writeEnum(int v, int v1) throws IOException {
        this.writeTag(v, 0);
        this.writeEnumNoTag(v1);
    }

    public void writeEnumNoTag(int v) throws IOException {
        this.writeInt32NoTag(v);
    }

    public void writeFixed32NoTag(int v) throws IOException {
        this.writeRawLittleEndian32(v);
    }

    public void writeFixed64NoTag(long v) throws IOException {
        this.writeRawLittleEndian64(v);
    }

    public void writeFloat(int v, float f) throws IOException {
        this.writeTag(v, 5);
        this.writeFloatNoTag(f);
    }

    public void writeFloatNoTag(float f) throws IOException {
        this.writeRawLittleEndian32(Float.floatToRawIntBits(f));
    }

    public void writeGroup(int v, MessageLite messageLite0) throws IOException {
        this.writeTag(v, 3);
        this.writeGroupNoTag(messageLite0);
        this.writeTag(v, 4);
    }

    public void writeGroupNoTag(MessageLite messageLite0) throws IOException {
        messageLite0.writeTo(this);
    }

    public void writeInt32(int v, int v1) throws IOException {
        this.writeTag(v, 0);
        this.writeInt32NoTag(v1);
    }

    public void writeInt32NoTag(int v) throws IOException {
        if(v >= 0) {
            this.writeRawVarint32(v);
            return;
        }
        this.writeRawVarint64(((long)v));
    }

    public void writeInt64NoTag(long v) throws IOException {
        this.writeRawVarint64(v);
    }

    public void writeMessage(int v, MessageLite messageLite0) throws IOException {
        this.writeTag(v, 2);
        this.writeMessageNoTag(messageLite0);
    }

    public void writeMessageNoTag(MessageLite messageLite0) throws IOException {
        this.writeRawVarint32(messageLite0.getSerializedSize());
        messageLite0.writeTo(this);
    }

    public void writeMessageSetExtension(int v, MessageLite messageLite0) throws IOException {
        this.writeTag(1, 3);
        this.writeUInt32(2, v);
        this.writeMessage(3, messageLite0);
        this.writeTag(1, 4);
    }

    public void writeRawByte(byte b) throws IOException {
        if(this.position == this.limit) {
            this.refreshBuffer();
        }
        int v = this.position;
        this.position = v + 1;
        this.buffer[v] = b;
        ++this.totalBytesWritten;
    }

    public void writeRawByte(int v) throws IOException {
        this.writeRawByte(((byte)v));
    }

    public void writeRawBytes(ByteString byteString0) throws IOException {
        this.writeRawBytes(byteString0, 0, byteString0.size());
    }

    public void writeRawBytes(ByteString byteString0, int v, int v1) throws IOException {
        int v2 = this.limit;
        int v3 = this.position;
        if(v2 - v3 >= v1) {
            byteString0.copyTo(this.buffer, v, v3, v1);
            this.position += v1;
            this.totalBytesWritten += v1;
            return;
        }
        int v4 = v2 - v3;
        byteString0.copyTo(this.buffer, v, v3, v4);
        int v5 = v + v4;
        int v6 = v1 - v4;
        this.position = this.limit;
        this.totalBytesWritten += v4;
        this.refreshBuffer();
        if(v6 <= this.limit) {
            byteString0.copyTo(this.buffer, v5, 0, v6);
            this.position = v6;
        }
        else {
            byteString0.writeTo(this.output, v5, v6);
        }
        this.totalBytesWritten += v6;
    }

    public void writeRawBytes(byte[] arr_b) throws IOException {
        this.writeRawBytes(arr_b, 0, arr_b.length);
    }

    public void writeRawBytes(byte[] arr_b, int v, int v1) throws IOException {
        int v2 = this.limit;
        int v3 = this.position;
        if(v2 - v3 >= v1) {
            System.arraycopy(arr_b, v, this.buffer, v3, v1);
            this.position += v1;
            this.totalBytesWritten += v1;
            return;
        }
        int v4 = v2 - v3;
        System.arraycopy(arr_b, v, this.buffer, v3, v4);
        int v5 = v + v4;
        int v6 = v1 - v4;
        this.position = this.limit;
        this.totalBytesWritten += v4;
        this.refreshBuffer();
        if(v6 <= this.limit) {
            System.arraycopy(arr_b, v5, this.buffer, 0, v6);
            this.position = v6;
        }
        else {
            this.output.write(arr_b, v5, v6);
        }
        this.totalBytesWritten += v6;
    }

    public void writeRawLittleEndian32(int v) throws IOException {
        this.writeRawByte(v & 0xFF);
        this.writeRawByte(v >> 8 & 0xFF);
        this.writeRawByte(v >> 16 & 0xFF);
        this.writeRawByte(v >> 24 & 0xFF);
    }

    public void writeRawLittleEndian64(long v) throws IOException {
        this.writeRawByte(((int)v) & 0xFF);
        this.writeRawByte(((int)(v >> 8)) & 0xFF);
        this.writeRawByte(((int)(v >> 16)) & 0xFF);
        this.writeRawByte(((int)(v >> 24)) & 0xFF);
        this.writeRawByte(((int)(v >> 0x20)) & 0xFF);
        this.writeRawByte(((int)(v >> 40)) & 0xFF);
        this.writeRawByte(((int)(v >> 0x30)) & 0xFF);
        this.writeRawByte(((int)(v >> 56)) & 0xFF);
    }

    public void writeRawVarint32(int v) throws IOException {
        while((v & 0xFFFFFF80) != 0) {
            this.writeRawByte(v & 0x7F | 0x80);
            v >>>= 7;
        }
        this.writeRawByte(v);
    }

    public void writeRawVarint64(long v) throws IOException {
        while((0xFFFFFFFFFFFFFF80L & v) != 0L) {
            this.writeRawByte(((int)v) & 0x7F | 0x80);
            v >>>= 7;
        }
        this.writeRawByte(((int)v));
    }

    public void writeSFixed32NoTag(int v) throws IOException {
        this.writeRawLittleEndian32(v);
    }

    public void writeSFixed64NoTag(long v) throws IOException {
        this.writeRawLittleEndian64(v);
    }

    public void writeSInt32NoTag(int v) throws IOException {
        this.writeRawVarint32(v >> 0x1F ^ v << 1);
    }

    public void writeSInt64(int v, long v1) throws IOException {
        this.writeTag(v, 0);
        this.writeSInt64NoTag(v1);
    }

    public void writeSInt64NoTag(long v) throws IOException {
        this.writeRawVarint64(v >> 0x3F ^ v << 1);
    }

    public void writeStringNoTag(String s) throws IOException {
        byte[] arr_b = s.getBytes("UTF-8");
        this.writeRawVarint32(arr_b.length);
        this.writeRawBytes(arr_b);
    }

    public void writeTag(int v, int v1) throws IOException {
        this.writeRawVarint32(v << 3 | v1);
    }

    public void writeUInt32(int v, int v1) throws IOException {
        this.writeTag(v, 0);
        this.writeUInt32NoTag(v1);
    }

    public void writeUInt32NoTag(int v) throws IOException {
        this.writeRawVarint32(v);
    }

    public void writeUInt64NoTag(long v) throws IOException {
        this.writeRawVarint64(v);
    }
}

