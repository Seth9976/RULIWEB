package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public final class CodedInputStream {
    interface RefillCallback {
        void onRefill();
    }

    private final byte[] buffer;
    private final boolean bufferIsImmutable;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private boolean enableAliasing;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private RefillCallback refillCallback;
    private int sizeLimit;
    private int totalBytesRetired;

    private CodedInputStream(InputStream inputStream0) {
        this.enableAliasing = false;
        this.currentLimit = 0x7FFFFFFF;
        this.recursionLimit = 0x40;
        this.sizeLimit = 0x4000000;
        this.refillCallback = null;
        this.buffer = new byte[0x1000];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.totalBytesRetired = 0;
        this.input = inputStream0;
        this.bufferIsImmutable = false;
    }

    private CodedInputStream(LiteralByteString literalByteString0) {
        this.enableAliasing = false;
        this.currentLimit = 0x7FFFFFFF;
        this.recursionLimit = 0x40;
        this.sizeLimit = 0x4000000;
        this.refillCallback = null;
        this.buffer = literalByteString0.bytes;
        int v = literalByteString0.getOffsetIntoBytes();
        this.bufferPos = v;
        this.bufferSize = v + literalByteString0.size();
        this.totalBytesRetired = -this.bufferPos;
        this.input = null;
        this.bufferIsImmutable = true;
    }

    public void checkLastTagWas(int v) throws InvalidProtocolBufferException {
        if(this.lastTag != v) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public static int decodeZigZag32(int v) {
        return -(v & 1) ^ v >>> 1;
    }

    public static long decodeZigZag64(long v) {
        return -(v & 1L) ^ v >>> 1;
    }

    private void ensureAvailable(int v) throws IOException {
        if(this.bufferSize - this.bufferPos < v) {
            this.refillBuffer(v);
        }
    }

    public int getBytesUntilLimit() {
        return this.currentLimit == 0x7FFFFFFF ? -1 : this.currentLimit - (this.totalBytesRetired + this.bufferPos);
    }

    public boolean isAtEnd() throws IOException {
        return this.bufferPos == this.bufferSize && !this.tryRefillBuffer(1);
    }

    public static CodedInputStream newInstance(InputStream inputStream0) {
        return new CodedInputStream(inputStream0);
    }

    static CodedInputStream newInstance(LiteralByteString literalByteString0) {
        CodedInputStream codedInputStream0 = new CodedInputStream(literalByteString0);
        try {
            codedInputStream0.pushLimit(literalByteString0.size());
            return codedInputStream0;
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new IllegalArgumentException(invalidProtocolBufferException0);
        }
    }

    public void popLimit(int v) {
        this.currentLimit = v;
        this.recomputeBufferSizeAfterLimit();
    }

    public int pushLimit(int v) throws InvalidProtocolBufferException {
        if(v < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        int v1 = v + (this.totalBytesRetired + this.bufferPos);
        int v2 = this.currentLimit;
        if(v1 > v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        this.currentLimit = v1;
        this.recomputeBufferSizeAfterLimit();
        return v2;
    }

    public boolean readBool() throws IOException {
        return this.readRawVarint64() != 0L;
    }

    public ByteString readBytes() throws IOException {
        int v = this.readRawVarint32();
        int v1 = this.bufferPos;
        if(v <= this.bufferSize - v1 && v > 0) {
            ByteString byteString0 = !this.bufferIsImmutable || !this.enableAliasing ? ByteString.copyFrom(this.buffer, v1, v) : new BoundedByteString(this.buffer, this.bufferPos, v);
            this.bufferPos += v;
            return byteString0;
        }
        return v == 0 ? ByteString.EMPTY : new LiteralByteString(this.readRawBytesSlowPath(v));
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.readRawLittleEndian64());
    }

    public int readEnum() throws IOException {
        return this.readRawVarint32();
    }

    public int readFixed32() throws IOException {
        return this.readRawLittleEndian32();
    }

    public long readFixed64() throws IOException {
        return this.readRawLittleEndian64();
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readRawLittleEndian32());
    }

    public void readGroup(int v, Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        int v1 = this.recursionDepth;
        if(v1 >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        this.recursionDepth = v1 + 1;
        messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
        this.checkLastTagWas(v << 3 | 4);
        --this.recursionDepth;
    }

    public int readInt32() throws IOException {
        return this.readRawVarint32();
    }

    public long readInt64() throws IOException {
        return this.readRawVarint64();
    }

    public MessageLite readMessage(Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        int v = this.readRawVarint32();
        if(this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int v1 = this.pushLimit(v);
        ++this.recursionDepth;
        MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
        this.checkLastTagWas(0);
        --this.recursionDepth;
        this.popLimit(v1);
        return messageLite0;
    }

    public void readMessage(Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        int v = this.readRawVarint32();
        if(this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int v1 = this.pushLimit(v);
        ++this.recursionDepth;
        messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
        this.checkLastTagWas(0);
        --this.recursionDepth;
        this.popLimit(v1);
    }

    public byte readRawByte() throws IOException {
        if(this.bufferPos == this.bufferSize) {
            this.refillBuffer(1);
        }
        int v = this.bufferPos;
        this.bufferPos = v + 1;
        return this.buffer[v];
    }

    private byte[] readRawBytesSlowPath(int v) throws IOException {
        if(v <= 0) {
            if(v != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            return Internal.EMPTY_BYTE_ARRAY;
        }
        int v1 = this.totalBytesRetired;
        int v2 = this.bufferPos;
        int v3 = this.currentLimit;
        if(v1 + v2 + v <= v3) {
            if(v < 0x1000) {
                byte[] arr_b = new byte[v];
                int v4 = this.bufferSize - v2;
                System.arraycopy(this.buffer, v2, arr_b, 0, v4);
                this.bufferPos = this.bufferSize;
                int v5 = v - v4;
                this.ensureAvailable(v5);
                System.arraycopy(this.buffer, 0, arr_b, v4, v5);
                this.bufferPos = v5;
                return arr_b;
            }
            int v6 = this.bufferSize;
            this.totalBytesRetired = v1 + v6;
            this.bufferPos = 0;
            this.bufferSize = 0;
            int v7 = v6 - v2;
            int v8 = v - v7;
            ArrayList arrayList0 = new ArrayList();
            while(v8 > 0) {
                int v9 = Math.min(v8, 0x1000);
                byte[] arr_b1 = new byte[v9];
                for(int v10 = 0; v10 < v9; v10 += v11) {
                    int v11 = this.input == null ? -1 : this.input.read(arr_b1, v10, v9 - v10);
                    if(v11 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += v11;
                }
                v8 -= v9;
                arrayList0.add(arr_b1);
            }
            byte[] arr_b2 = new byte[v];
            System.arraycopy(this.buffer, v2, arr_b2, 0, v7);
            for(Object object0: arrayList0) {
                System.arraycopy(((byte[])object0), 0, arr_b2, v7, ((byte[])object0).length);
                v7 += ((byte[])object0).length;
            }
            return arr_b2;
        }
        this.skipRawBytes(v3 - v1 - v2);
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public int readRawLittleEndian32() throws IOException {
        int v = this.bufferPos;
        if(this.bufferSize - v < 4) {
            this.refillBuffer(4);
            v = this.bufferPos;
        }
        this.bufferPos = v + 4;
        return (this.buffer[v + 3] & 0xFF) << 24 | (this.buffer[v] & 0xFF | (this.buffer[v + 1] & 0xFF) << 8 | (this.buffer[v + 2] & 0xFF) << 16);
    }

    public long readRawLittleEndian64() throws IOException {
        int v = this.bufferPos;
        if(this.bufferSize - v < 8) {
            this.refillBuffer(8);
            v = this.bufferPos;
        }
        this.bufferPos = v + 8;
        return (((long)this.buffer[v + 7]) & 0xFFL) << 56 | (((long)this.buffer[v]) & 0xFFL | (((long)this.buffer[v + 1]) & 0xFFL) << 8 | (((long)this.buffer[v + 2]) & 0xFFL) << 16 | (((long)this.buffer[v + 3]) & 0xFFL) << 24 | (((long)this.buffer[v + 4]) & 0xFFL) << 0x20 | (((long)this.buffer[v + 5]) & 0xFFL) << 40 | (((long)this.buffer[v + 6]) & 0xFFL) << 0x30);
    }

    public static int readRawVarint32(int v, InputStream inputStream0) throws IOException {
        if((v & 0x80) == 0) {
            return v;
        }
        int v1 = v & 0x7F;
        int v2;
        for(v2 = 7; v2 < 0x20; v2 += 7) {
            int v3 = inputStream0.read();
            if(v3 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            v1 |= (v3 & 0x7F) << v2;
            if((v3 & 0x80) == 0) {
                return v1;
            }
        }
        while(v2 < 0x40) {
            int v4 = inputStream0.read();
            if(v4 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if((v4 & 0x80) == 0) {
                return v1;
            }
            v2 += 7;
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public int readRawVarint32() throws IOException {
        int v5;
        int v = this.bufferPos;
        int v1 = this.bufferSize;
        if(v1 != v) {
            byte[] arr_b = this.buffer;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.bufferPos = v + 1;
                return v2;
            }
            if(v1 - (v + 1) >= 9) {
                int v3 = v + 2;
                int v4 = arr_b[v + 1] << 7 ^ v2;
                if(((long)v4) < 0L) {
                    v5 = (int)(0xFFFFFFFFFFFFFF80L ^ ((long)v4));
                    this.bufferPos = v3;
                    return v5;
                }
                int v6 = v + 3;
                int v7 = arr_b[v3] << 14 ^ v4;
                if(((long)v7) >= 0L) {
                    v5 = (int)(0x3F80L ^ ((long)v7));
                    v3 = v6;
                    this.bufferPos = v3;
                    return v5;
                }
                int v8 = v + 4;
                int v9 = v7 ^ arr_b[v6] << 21;
                if(((long)v9) < 0L) {
                    v5 = (int)(0xFFFFFFFFFFE03F80L ^ ((long)v9));
                    v3 = v8;
                    this.bufferPos = v3;
                    return v5;
                }
                v6 = v + 5;
                int v10 = arr_b[v8];
                int v11 = (int)(((long)(v9 ^ v10 << 28)) ^ 0xFE03F80L);
                if(v10 >= 0) {
                    v3 = v6;
                    this.bufferPos = v3;
                    return v11;
                }
                v8 = v + 6;
                if(arr_b[v6] >= 0) {
                    v3 = v8;
                    this.bufferPos = v3;
                    return v11;
                }
                v6 = v + 7;
                if(arr_b[v8] >= 0) {
                    v3 = v6;
                    this.bufferPos = v3;
                    return v11;
                }
                v8 = v + 8;
                if(arr_b[v6] >= 0) {
                    v3 = v8;
                    this.bufferPos = v3;
                    return v11;
                }
                v6 = v + 9;
                if(arr_b[v8] >= 0) {
                    v3 = v6;
                    this.bufferPos = v3;
                    return v11;
                }
                else if(arr_b[v6] >= 0) {
                    v3 = v + 10;
                    this.bufferPos = v3;
                    return v11;
                }
            }
        }
        return (int)this.readRawVarint64SlowPath();
    }

    public long readRawVarint64() throws IOException {
        long v5;
        int v = this.bufferPos;
        int v1 = this.bufferSize;
        if(v1 != v) {
            byte[] arr_b = this.buffer;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.bufferPos = v + 1;
                return (long)v2;
            }
            if(v1 - (v + 1) >= 9) {
                int v3 = v + 2;
                long v4 = (long)(arr_b[v + 1] << 7 ^ v2);
                if(v4 < 0L) {
                    v5 = v4 ^ 0xFFFFFFFFFFFFFF80L;
                }
                else {
                    long v6 = v4 ^ ((long)(arr_b[v3] << 14));
                    if(v6 >= 0L) {
                        v5 = v6 ^ 0x3F80L;
                        v3 = v + 3;
                    }
                    else {
                        v3 = v + 4;
                        v4 = v6 ^ ((long)(arr_b[v + 3] << 21));
                        if(v4 < 0L) {
                            v5 = v4 ^ 0xFFFFFFFFFFE03F80L;
                        }
                        else {
                            v6 = v4 ^ ((long)arr_b[v3]) << 28;
                            if(v6 >= 0L) {
                                v5 = v6 ^ 0xFE03F80L;
                                v3 = v + 5;
                            }
                            else {
                                v3 = v + 6;
                                v4 = v6 ^ ((long)arr_b[v + 5]) << 35;
                                if(v4 < 0L) {
                                    v5 = v4 ^ 0xFFFFFFF80FE03F80L;
                                }
                                else {
                                    v6 = v4 ^ ((long)arr_b[v3]) << 42;
                                    if(v6 >= 0L) {
                                        v5 = v6 ^ 0x3F80FE03F80L;
                                        v3 = v + 7;
                                    }
                                    else {
                                        v3 = v + 8;
                                        v4 = v6 ^ ((long)arr_b[v + 7]) << 49;
                                        if(v4 < 0L) {
                                            v5 = v4 ^ 0xFFFE03F80FE03F80L;
                                        }
                                        else {
                                            v5 = v4 ^ ((long)arr_b[v3]) << 56 ^ 0xFE03F80FE03F80L;
                                            if(v5 < 0L) {
                                                v3 = v + 10;
                                                if(((long)arr_b[v + 9]) < 0L) {
                                                    return this.readRawVarint64SlowPath();
                                                }
                                            }
                                            else {
                                                v3 = v + 9;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.bufferPos = v3;
                return v5;
            }
        }
        return this.readRawVarint64SlowPath();
    }

    long readRawVarint64SlowPath() throws IOException {
        long v = 0L;
        for(int v1 = 0; v1 < 0x40; v1 += 7) {
            int v2 = this.readRawByte();
            v |= ((long)(v2 & 0x7F)) << v1;
            if((v2 & 0x80) == 0) {
                return v;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public int readSFixed32() throws IOException {
        return this.readRawLittleEndian32();
    }

    public long readSFixed64() throws IOException {
        return this.readRawLittleEndian64();
    }

    public int readSInt32() throws IOException {
        return CodedInputStream.decodeZigZag32(this.readRawVarint32());
    }

    public long readSInt64() throws IOException {
        return CodedInputStream.decodeZigZag64(this.readRawVarint64());
    }

    public String readString() throws IOException {
        int v = this.readRawVarint32();
        if(v <= this.bufferSize - this.bufferPos && v > 0) {
            String s = new String(this.buffer, this.bufferPos, v, "UTF-8");
            this.bufferPos += v;
            return s;
        }
        return v == 0 ? "" : new String(this.readRawBytesSlowPath(v), "UTF-8");
    }

    public String readStringRequireUtf8() throws IOException {
        byte[] arr_b;
        int v = this.readRawVarint32();
        int v1 = this.bufferPos;
        if(v > this.bufferSize - v1 || v <= 0) {
            if(v == 0) {
                return "";
            }
            arr_b = this.readRawBytesSlowPath(v);
            v1 = 0;
        }
        else {
            arr_b = this.buffer;
            this.bufferPos = v1 + v;
        }
        if(!Utf8.isValidUtf8(arr_b, v1, v1 + v)) {
            throw InvalidProtocolBufferException.invalidUtf8();
        }
        return new String(arr_b, v1, v, "UTF-8");
    }

    public int readTag() throws IOException {
        if(this.isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        int v = this.readRawVarint32();
        this.lastTag = v;
        if(v >>> 3 == 0) {
            throw InvalidProtocolBufferException.invalidTag();
        }
        return this.lastTag;
    }

    public int readUInt32() throws IOException {
        return this.readRawVarint32();
    }

    public long readUInt64() throws IOException {
        return this.readRawVarint64();
    }

    private void recomputeBufferSizeAfterLimit() {
        int v = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = v;
        int v1 = this.totalBytesRetired + v;
        int v2 = this.currentLimit;
        if(v1 > v2) {
            int v3 = v1 - v2;
            this.bufferSizeAfterLimit = v3;
            this.bufferSize = v - v3;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    private void refillBuffer(int v) throws IOException {
        if(!this.tryRefillBuffer(v)) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public boolean skipField(int v, CodedOutputStream codedOutputStream0) throws IOException {
        switch(v & 7) {
            case 0: {
                long v3 = this.readInt64();
                codedOutputStream0.writeRawVarint32(v);
                codedOutputStream0.writeUInt64NoTag(v3);
                return true;
            }
            case 1: {
                long v4 = this.readRawLittleEndian64();
                codedOutputStream0.writeRawVarint32(v);
                codedOutputStream0.writeFixed64NoTag(v4);
                return true;
            }
            default: {
                if((v & 7) != 2) {
                    switch(v & 7) {
                        case 3: {
                            codedOutputStream0.writeRawVarint32(v);
                            this.skipMessage(codedOutputStream0);
                            int v2 = v >>> 3 << 3 | 4;
                            this.checkLastTagWas(v2);
                            codedOutputStream0.writeRawVarint32(v2);
                            return true;
                        }
                        case 4: {
                            return false;
                        }
                        default: {
                            if((v & 7) != 5) {
                                throw InvalidProtocolBufferException.invalidWireType();
                            }
                            int v1 = this.readRawLittleEndian32();
                            codedOutputStream0.writeRawVarint32(v);
                            codedOutputStream0.writeFixed32NoTag(v1);
                            return true;
                        }
                    }
                }
                ByteString byteString0 = this.readBytes();
                codedOutputStream0.writeRawVarint32(v);
                codedOutputStream0.writeBytesNoTag(byteString0);
                return true;
            }
        }
    }

    public void skipMessage(CodedOutputStream codedOutputStream0) throws IOException {
        do {
            int v = this.readTag();
        }
        while(v != 0 && this.skipField(v, codedOutputStream0));
    }

    public void skipRawBytes(int v) throws IOException {
        int v1 = this.bufferPos;
        if(v <= this.bufferSize - v1 && v >= 0) {
            this.bufferPos = v1 + v;
            return;
        }
        this.skipRawBytesSlowPath(v);
    }

    private void skipRawBytesSlowPath(int v) throws IOException {
        int v5;
        if(v < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        int v1 = this.totalBytesRetired;
        int v2 = this.bufferPos;
        int v3 = this.currentLimit;
        if(v1 + v2 + v <= v3) {
            int v4 = this.bufferSize - v2;
            this.bufferPos = this.bufferSize;
            this.refillBuffer(1);
            while(true) {
                v5 = v - v4;
                int v6 = this.bufferSize;
                if(v5 <= v6) {
                    break;
                }
                v4 += v6;
                this.bufferPos = v6;
                this.refillBuffer(1);
            }
            this.bufferPos = v5;
            return;
        }
        this.skipRawBytes(v3 - v1 - v2);
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    private boolean tryRefillBuffer(int v) throws IOException {
        int v1 = this.bufferPos;
        if(v1 + v <= this.bufferSize) {
            throw new IllegalStateException("refillBuffer() called when " + v + " bytes were already available in buffer");
        }
        if(this.totalBytesRetired + v1 + v > this.currentLimit) {
            return false;
        }
        RefillCallback codedInputStream$RefillCallback0 = this.refillCallback;
        if(codedInputStream$RefillCallback0 != null) {
            codedInputStream$RefillCallback0.onRefill();
        }
        if(this.input != null) {
            int v2 = this.bufferPos;
            if(v2 > 0) {
                int v3 = this.bufferSize;
                if(v3 > v2) {
                    System.arraycopy(this.buffer, v2, this.buffer, 0, v3 - v2);
                }
                this.totalBytesRetired += v2;
                this.bufferSize -= v2;
                this.bufferPos = 0;
            }
            int v4 = this.input.read(this.buffer, this.bufferSize, this.buffer.length - this.bufferSize);
            if(v4 == 0 || v4 < -1 || v4 > this.buffer.length) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + v4 + "\nThe InputStream implementation is buggy.");
            }
            if(v4 > 0) {
                this.bufferSize += v4;
                if(this.totalBytesRetired + v - this.sizeLimit > 0) {
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
                this.recomputeBufferSizeAfterLimit();
                return this.bufferSize < v ? this.tryRefillBuffer(v) : true;
            }
        }
        return false;
    }
}

