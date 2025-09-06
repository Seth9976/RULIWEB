package com.google.crypto.tink.shaded.protobuf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class CodedInputStream {
    static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private ArrayDecoder(byte[] arr_b, int v, int v1, boolean z) {
            super(null);
            this.currentLimit = 0x7FFFFFFF;
            this.buffer = arr_b;
            this.limit = v1 + v;
            this.pos = v;
            this.startPos = v;
            this.immutable = z;
        }

        ArrayDecoder(byte[] arr_b, int v, int v1, boolean z, com.google.crypto.tink.shaded.protobuf.CodedInputStream.1 codedInputStream$10) {
            this(arr_b, v, v1, z);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int v) throws InvalidProtocolBufferException {
            if(this.lastTag != v) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            return this.currentLimit == 0x7FFFFFFF ? -1 : this.currentLimit - this.getTotalBytesRead();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int v) {
            this.currentLimit = v;
            this.recomputeBufferSizeAfterLimit();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int v) throws InvalidProtocolBufferException {
            if(v < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int v1 = v + this.getTotalBytesRead();
            if(v1 < 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            int v2 = this.currentLimit;
            if(v1 > v2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = v1;
            this.recomputeBufferSizeAfterLimit();
            return v2;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return this.readRawVarint64() != 0L;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return this.readRawBytes(this.readRawVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0) {
                int v1 = this.pos;
                if(v <= this.limit - v1) {
                    ByteBuffer byteBuffer0 = this.immutable || !this.enableAliasing ? ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, v1, v1 + v)) : ByteBuffer.wrap(this.buffer, v1, v).slice();
                    this.pos += v;
                    return byteBuffer0;
                }
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return Internal.EMPTY_BYTE_BUFFER;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0) {
                int v1 = this.pos;
                if(v <= this.limit - v1) {
                    ByteString byteString0 = !this.immutable || !this.enableAliasing ? ByteString.copyFrom(this.buffer, v1, v) : ByteString.wrap(this.buffer, v1, v);
                    this.pos += v;
                    return byteString0;
                }
            }
            return v == 0 ? ByteString.EMPTY : ByteString.wrap(this.readRawBytes(v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(this.readRawLittleEndian64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(this.readRawLittleEndian32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readGroup(int v, Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int v, Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readMessage(Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            int v = this.pos;
            if(v == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = v + 1;
            return this.buffer[v];
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int v) throws IOException {
            if(v > 0) {
                int v1 = this.pos;
                if(v <= this.limit - v1) {
                    int v2 = v + v1;
                    this.pos = v2;
                    return Arrays.copyOfRange(this.buffer, v1, v2);
                }
            }
            if(v > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if(v != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            return Internal.EMPTY_BYTE_ARRAY;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            int v = this.pos;
            if(this.limit - v < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = v + 4;
            return (this.buffer[v + 3] & 0xFF) << 24 | (this.buffer[v] & 0xFF | (this.buffer[v + 1] & 0xFF) << 8 | (this.buffer[v + 2] & 0xFF) << 16);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            int v = this.pos;
            if(this.limit - v < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = v + 8;
            return (((long)this.buffer[v + 7]) & 0xFFL) << 56 | (((long)this.buffer[v]) & 0xFFL | (((long)this.buffer[v + 1]) & 0xFFL) << 8 | (((long)this.buffer[v + 2]) & 0xFFL) << 16 | (((long)this.buffer[v + 3]) & 0xFFL) << 24 | (((long)this.buffer[v + 4]) & 0xFFL) << 0x20 | (((long)this.buffer[v + 5]) & 0xFFL) << 40 | (((long)this.buffer[v + 6]) & 0xFFL) << 0x30);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawVarint32() throws IOException {
            int v5;
            int v = this.pos;
            int v1 = this.limit;
            if(v1 != v) {
                byte[] arr_b = this.buffer;
                int v2 = arr_b[v];
                if(v2 >= 0) {
                    this.pos = v + 1;
                    return v2;
                }
                if(v1 - (v + 1) >= 9) {
                    int v3 = v + 2;
                    int v4 = arr_b[v + 1] << 7 ^ v2;
                    if(v4 < 0) {
                        v5 = v4 ^ 0xFFFFFF80;
                        this.pos = v3;
                        return v5;
                    }
                    int v6 = v + 3;
                    int v7 = arr_b[v3] << 14 ^ v4;
                    if(v7 >= 0) {
                        v5 = v7 ^ 0x3F80;
                        v3 = v6;
                        this.pos = v3;
                        return v5;
                    }
                    int v8 = v + 4;
                    int v9 = v7 ^ arr_b[v6] << 21;
                    if(v9 < 0) {
                        v5 = 0xFFE03F80 ^ v9;
                        v3 = v8;
                        this.pos = v3;
                        return v5;
                    }
                    v6 = v + 5;
                    int v10 = arr_b[v8];
                    int v11 = v9 ^ v10 << 28 ^ 0xFE03F80;
                    if(v10 >= 0) {
                        v3 = v6;
                        this.pos = v3;
                        return v11;
                    }
                    v8 = v + 6;
                    if(arr_b[v6] >= 0) {
                        v3 = v8;
                        this.pos = v3;
                        return v11;
                    }
                    v6 = v + 7;
                    if(arr_b[v8] >= 0) {
                        v3 = v6;
                        this.pos = v3;
                        return v11;
                    }
                    v8 = v + 8;
                    if(arr_b[v6] >= 0) {
                        v3 = v8;
                        this.pos = v3;
                        return v11;
                    }
                    v6 = v + 9;
                    if(arr_b[v8] >= 0) {
                        v3 = v6;
                        this.pos = v3;
                        return v11;
                    }
                    else if(arr_b[v6] >= 0) {
                        v3 = v + 10;
                        this.pos = v3;
                        return v11;
                    }
                }
            }
            return (int)this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long v13;
            long v11;
            long v9;
            long v5;
            int v = this.pos;
            int v1 = this.limit;
            if(v1 != v) {
                byte[] arr_b = this.buffer;
                int v2 = arr_b[v];
                if(v2 >= 0) {
                    this.pos = v + 1;
                    return (long)v2;
                }
                if(v1 - (v + 1) >= 9) {
                    int v3 = v + 2;
                    int v4 = arr_b[v + 1] << 7 ^ v2;
                    if(v4 < 0) {
                        v5 = (long)(v4 ^ 0xFFFFFF80);
                        this.pos = v3;
                        return v5;
                    }
                    int v6 = arr_b[v3] << 14 ^ v4;
                    if(v6 >= 0) {
                        v5 = (long)(v6 ^ 0x3F80);
                        v3 = v + 3;
                        this.pos = v3;
                        return v5;
                    }
                    int v7 = v + 4;
                    int v8 = v6 ^ arr_b[v + 3] << 21;
                    if(v8 < 0) {
                        v9 = (long)(0xFFE03F80 ^ v8);
                        v3 = v7;
                        this.pos = v3;
                        return v9;
                    }
                    v3 = v + 5;
                    long v10 = ((long)v8) ^ ((long)arr_b[v7]) << 28;
                    if(v10 >= 0L) {
                        v11 = 0xFE03F80L;
                        v5 = v11 ^ v10;
                        this.pos = v3;
                        return v5;
                    }
                    v7 = v + 6;
                    long v12 = v10 ^ ((long)arr_b[v3]) << 35;
                    if(v12 < 0L) {
                        v13 = 0xFFFFFFF80FE03F80L;
                        v9 = v13 ^ v12;
                        v3 = v7;
                        this.pos = v3;
                        return v9;
                    }
                    v3 = v + 7;
                    v10 = v12 ^ ((long)arr_b[v7]) << 42;
                    if(v10 >= 0L) {
                        v11 = 0x3F80FE03F80L;
                        v5 = v11 ^ v10;
                        this.pos = v3;
                        return v5;
                    }
                    v7 = v + 8;
                    v12 = v10 ^ ((long)arr_b[v3]) << 49;
                    if(v12 < 0L) {
                        v13 = 0xFFFE03F80FE03F80L;
                        v9 = v13 ^ v12;
                        v3 = v7;
                        this.pos = v3;
                        return v9;
                    }
                    v3 = v + 9;
                    long v14 = v12 ^ ((long)arr_b[v7]) << 56 ^ 0xFE03F80FE03F80L;
                    boolean z = false;
                    if(v14 >= 0L) {
                        z = true;
                    }
                    else if(((long)arr_b[v3]) >= 0L) {
                        z = true;
                        v3 = v + 10;
                    }
                    if(z) {
                        this.pos = v3;
                        return v14;
                    }
                }
            }
            return this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return ArrayDecoder.decodeZigZag32(this.readRawVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return ArrayDecoder.decodeZigZag64(this.readRawVarint64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0 && v <= this.limit - this.pos) {
                String s = new String(this.buffer, this.pos, v, Internal.UTF_8);
                this.pos += v;
                return s;
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0) {
                int v1 = this.pos;
                if(v <= this.limit - v1) {
                    String s = Utf8.decodeUtf8(this.buffer, v1, v);
                    this.pos += v;
                    return s;
                }
            }
            if(v != 0) {
                throw v > 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int v, Builder messageLite$Builder0) throws IOException {
            this.readGroup(v, messageLite$Builder0, ExtensionRegistryLite.getEmptyRegistry());
        }

        private void recomputeBufferSizeAfterLimit() {
            int v = this.limit + this.bufferSizeAfterLimit;
            this.limit = v;
            int v1 = v - this.startPos;
            int v2 = this.currentLimit;
            if(v1 > v2) {
                int v3 = v1 - v2;
                this.bufferSizeAfterLimit = v3;
                this.limit = v - v3;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v) throws IOException {
            switch(v & 7) {
                case 0: {
                    this.skipRawVarint();
                    return true;
                }
                case 1: {
                    this.skipRawBytes(8);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                this.skipMessage();
                                this.checkLastTagWas(v >>> 3 << 3 | 4);
                                return true;
                            }
                            case 4: {
                                return false;
                            }
                            default: {
                                if((v & 7) != 5) {
                                    throw InvalidProtocolBufferException.invalidWireType();
                                }
                                this.skipRawBytes(4);
                                return true;
                            }
                        }
                    }
                    this.skipRawBytes(this.readRawVarint32());
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v, CodedOutputStream codedOutputStream0) throws IOException {
            switch(v & 7) {
                case 0: {
                    long v3 = this.readInt64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeUInt64NoTag(v3);
                    return true;
                }
                case 1: {
                    long v4 = this.readRawLittleEndian64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeFixed64NoTag(v4);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                codedOutputStream0.writeUInt32NoTag(v);
                                this.skipMessage(codedOutputStream0);
                                int v2 = v >>> 3 << 3 | 4;
                                this.checkLastTagWas(v2);
                                codedOutputStream0.writeUInt32NoTag(v2);
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
                                codedOutputStream0.writeUInt32NoTag(v);
                                codedOutputStream0.writeFixed32NoTag(v1);
                                return true;
                            }
                        }
                    }
                    ByteString byteString0 = this.readBytes();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeBytesNoTag(byteString0);
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream0) throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v, codedOutputStream0));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int v) throws IOException {
            if(v >= 0) {
                int v1 = this.pos;
                if(v <= this.limit - v1) {
                    this.pos = v1 + v;
                    return;
                }
            }
            throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
        }

        private void skipRawVarint() throws IOException {
            if(this.limit - this.pos >= 10) {
                this.skipRawVarintFastPath();
                return;
            }
            this.skipRawVarintSlowPath();
        }

        private void skipRawVarintFastPath() throws IOException {
            for(int v = 0; v < 10; ++v) {
                int v1 = this.pos;
                this.pos = v1 + 1;
                if(this.buffer[v1] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            for(int v = 0; v < 10; ++v) {
                if(this.readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }
    }

    static final class IterableDirectByteBufferDecoder extends CodedInputStream {
        private int bufferSizeAfterCurrentLimit;
        private long currentAddress;
        private ByteBuffer currentByteBuffer;
        private long currentByteBufferLimit;
        private long currentByteBufferPos;
        private long currentByteBufferStartPos;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private final Iterable input;
        private final Iterator iterator;
        private int lastTag;
        private int startOffset;
        private int totalBufferSize;
        private int totalBytesRead;

        private IterableDirectByteBufferDecoder(Iterable iterable0, int v, boolean z) {
            super(null);
            this.currentLimit = 0x7FFFFFFF;
            this.totalBufferSize = v;
            this.input = iterable0;
            this.iterator = iterable0.iterator();
            this.immutable = z;
            this.totalBytesRead = 0;
            this.startOffset = 0;
            if(v == 0) {
                this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
                this.currentByteBufferPos = 0L;
                this.currentByteBufferStartPos = 0L;
                this.currentByteBufferLimit = 0L;
                this.currentAddress = 0L;
                return;
            }
            this.tryGetNextByteBuffer();
        }

        IterableDirectByteBufferDecoder(Iterable iterable0, int v, boolean z, com.google.crypto.tink.shaded.protobuf.CodedInputStream.1 codedInputStream$10) {
            this(iterable0, v, z);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int v) throws InvalidProtocolBufferException {
            if(this.lastTag != v) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        private long currentRemaining() {
            return this.currentByteBufferLimit - this.currentByteBufferPos;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            return this.currentLimit == 0x7FFFFFFF ? -1 : this.currentLimit - this.getTotalBytesRead();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        private void getNextByteBuffer() throws InvalidProtocolBufferException {
            if(!this.iterator.hasNext()) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.tryGetNextByteBuffer();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int)(((long)(this.totalBytesRead - this.startOffset)) + this.currentByteBufferPos - this.currentByteBufferStartPos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return ((long)this.totalBytesRead) + this.currentByteBufferPos - this.currentByteBufferStartPos == ((long)this.totalBufferSize);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int v) {
            this.currentLimit = v;
            this.recomputeBufferSizeAfterLimit();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int v) throws InvalidProtocolBufferException {
            if(v < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int v1 = v + this.getTotalBytesRead();
            int v2 = this.currentLimit;
            if(v1 > v2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = v1;
            this.recomputeBufferSizeAfterLimit();
            return v2;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return this.readRawVarint64() != 0L;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return this.readRawBytes(this.readRawVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0 && ((long)v) <= this.currentRemaining()) {
                if(!this.immutable && this.enableAliasing) {
                    long v1 = this.currentByteBufferPos + ((long)v);
                    this.currentByteBufferPos = v1;
                    return this.slice(((int)(v1 - this.currentAddress - ((long)v))), ((int)(v1 - this.currentAddress)));
                }
                byte[] arr_b = new byte[v];
                UnsafeUtil.copyMemory(this.currentByteBufferPos, arr_b, 0L, v);
                this.currentByteBufferPos += (long)v;
                return ByteBuffer.wrap(arr_b);
            }
            if(v > 0 && v <= this.remaining()) {
                byte[] arr_b1 = new byte[v];
                this.readRawBytesTo(arr_b1, 0, v);
                return ByteBuffer.wrap(arr_b1);
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return Internal.EMPTY_BYTE_BUFFER;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0) {
                long v1 = this.currentByteBufferPos;
                if(((long)v) <= this.currentByteBufferLimit - v1) {
                    if(this.immutable && this.enableAliasing) {
                        int v2 = (int)(v1 - this.currentAddress);
                        ByteString byteString0 = ByteString.wrap(this.slice(v2, v + v2));
                        this.currentByteBufferPos += (long)v;
                        return byteString0;
                    }
                    byte[] arr_b = new byte[v];
                    UnsafeUtil.copyMemory(v1, arr_b, 0L, v);
                    this.currentByteBufferPos += (long)v;
                    return ByteString.wrap(arr_b);
                }
            }
            if(v > 0 && v <= this.remaining()) {
                if(this.immutable && this.enableAliasing) {
                    ArrayList arrayList0 = new ArrayList();
                    while(v > 0) {
                        if(this.currentRemaining() == 0L) {
                            this.getNextByteBuffer();
                        }
                        int v3 = Math.min(v, ((int)this.currentRemaining()));
                        int v4 = (int)(this.currentByteBufferPos - this.currentAddress);
                        arrayList0.add(ByteString.wrap(this.slice(v4, v4 + v3)));
                        v -= v3;
                        this.currentByteBufferPos += (long)v3;
                    }
                    return ByteString.copyFrom(arrayList0);
                }
                byte[] arr_b1 = new byte[v];
                this.readRawBytesTo(arr_b1, 0, v);
                return ByteString.wrap(arr_b1);
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return ByteString.EMPTY;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(this.readRawLittleEndian64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(this.readRawLittleEndian32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readGroup(int v, Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int v, Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readMessage(Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            if(this.currentRemaining() == 0L) {
                this.getNextByteBuffer();
            }
            long v = this.currentByteBufferPos;
            this.currentByteBufferPos = v + 1L;
            return UnsafeUtil.getByte(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int v) throws IOException {
            if(v >= 0 && ((long)v) <= this.currentRemaining()) {
                byte[] arr_b = new byte[v];
                UnsafeUtil.copyMemory(this.currentByteBufferPos, arr_b, 0L, v);
                this.currentByteBufferPos += (long)v;
                return arr_b;
            }
            if(v >= 0 && v <= this.remaining()) {
                byte[] arr_b1 = new byte[v];
                this.readRawBytesTo(arr_b1, 0, v);
                return arr_b1;
            }
            if(v > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if(v != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            return Internal.EMPTY_BYTE_ARRAY;
        }

        private void readRawBytesTo(byte[] arr_b, int v, int v1) throws IOException {
            if(v1 >= 0 && v1 <= this.remaining()) {
                int v2 = v1;
                while(v2 > 0) {
                    if(this.currentRemaining() == 0L) {
                        this.getNextByteBuffer();
                    }
                    int v3 = Math.min(v2, ((int)this.currentRemaining()));
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, arr_b, v1 - v2 + v, v3);
                    v2 -= v3;
                    this.currentByteBufferPos += (long)v3;
                }
                return;
            }
            if(v1 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if(v1 != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            if(this.currentRemaining() >= 4L) {
                long v = this.currentByteBufferPos;
                this.currentByteBufferPos = v + 4L;
                int v1 = UnsafeUtil.getByte(v);
                int v2 = UnsafeUtil.getByte(v + 1L);
                int v3 = UnsafeUtil.getByte(v + 2L);
                return (UnsafeUtil.getByte(v + 3L) & 0xFF) << 24 | (v1 & 0xFF | (v2 & 0xFF) << 8 | (v3 & 0xFF) << 16);
            }
            return this.readRawByte() & 0xFF | (this.readRawByte() & 0xFF) << 8 | (this.readRawByte() & 0xFF) << 16 | (this.readRawByte() & 0xFF) << 24;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            if(this.currentRemaining() >= 8L) {
                long v = this.currentByteBufferPos;
                this.currentByteBufferPos = v + 8L;
                long v1 = ((long)UnsafeUtil.getByte(v)) & 0xFFL | (((long)UnsafeUtil.getByte(v + 1L)) & 0xFFL) << 8;
                long v2 = (((long)UnsafeUtil.getByte(v + 2L)) & 0xFFL) << 16 | v1 | (((long)UnsafeUtil.getByte(v + 3L)) & 0xFFL) << 24;
                long v3 = (((long)UnsafeUtil.getByte(v + 4L)) & 0xFFL) << 0x20 | v2 | (((long)UnsafeUtil.getByte(v + 5L)) & 0xFFL) << 40;
                long v4 = (((long)UnsafeUtil.getByte(v + 6L)) & 0xFFL) << 0x30 | v3;
                return (((long)UnsafeUtil.getByte(v + 7L)) & 0xFFL) << 56 | v4;
            }
            return ((long)this.readRawByte()) & 0xFFL | (((long)this.readRawByte()) & 0xFFL) << 8 | (((long)this.readRawByte()) & 0xFFL) << 16 | (((long)this.readRawByte()) & 0xFFL) << 24 | (((long)this.readRawByte()) & 0xFFL) << 0x20 | (((long)this.readRawByte()) & 0xFFL) << 40 | (((long)this.readRawByte()) & 0xFFL) << 0x30 | (((long)this.readRawByte()) & 0xFFL) << 56;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawVarint32() throws IOException {
            int v4;
            long v = this.currentByteBufferPos;
            if(this.currentByteBufferLimit != v) {
                int v1 = UnsafeUtil.getByte(v);
                if(v1 >= 0) {
                    ++this.currentByteBufferPos;
                    return v1;
                }
                if(this.currentByteBufferLimit - this.currentByteBufferPos >= 10L) {
                    long v2 = v + 2L;
                    int v3 = UnsafeUtil.getByte(v + 1L) << 7 ^ v1;
                    if(v3 < 0) {
                        v4 = v3 ^ 0xFFFFFF80;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    long v5 = v + 3L;
                    int v6 = UnsafeUtil.getByte(v2) << 14 ^ v3;
                    if(v6 >= 0) {
                        v4 = v6 ^ 0x3F80;
                        v2 = v5;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    long v7 = v + 4L;
                    int v8 = v6 ^ UnsafeUtil.getByte(v5) << 21;
                    if(v8 < 0) {
                        v4 = 0xFFE03F80 ^ v8;
                        v2 = v7;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    v5 = v + 5L;
                    int v9 = UnsafeUtil.getByte(v7);
                    int v10 = v8 ^ v9 << 28 ^ 0xFE03F80;
                    if(v9 >= 0) {
                        v2 = v5;
                        this.currentByteBufferPos = v2;
                        return v10;
                    }
                    v7 = v + 6L;
                    if(UnsafeUtil.getByte(v5) >= 0) {
                        v2 = v7;
                        this.currentByteBufferPos = v2;
                        return v10;
                    }
                    v5 = v + 7L;
                    if(UnsafeUtil.getByte(v7) >= 0) {
                        v2 = v5;
                        this.currentByteBufferPos = v2;
                        return v10;
                    }
                    v7 = v + 8L;
                    if(UnsafeUtil.getByte(v5) >= 0) {
                        v2 = v7;
                        this.currentByteBufferPos = v2;
                        return v10;
                    }
                    v5 = v + 9L;
                    if(UnsafeUtil.getByte(v7) >= 0) {
                        v2 = v5;
                        this.currentByteBufferPos = v2;
                        return v10;
                    }
                    else if(UnsafeUtil.getByte(v5) >= 0) {
                        v2 = v + 10L;
                        this.currentByteBufferPos = v2;
                        return v10;
                    }
                }
            }
            return (int)this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long v12;
            long v9;
            long v4;
            long v = this.currentByteBufferPos;
            if(this.currentByteBufferLimit != v) {
                int v1 = UnsafeUtil.getByte(v);
                if(v1 >= 0) {
                    ++this.currentByteBufferPos;
                    return (long)v1;
                }
                if(this.currentByteBufferLimit - this.currentByteBufferPos >= 10L) {
                    long v2 = v + 2L;
                    int v3 = UnsafeUtil.getByte(v + 1L) << 7 ^ v1;
                    if(v3 < 0) {
                        v4 = (long)(v3 ^ 0xFFFFFF80);
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    int v5 = UnsafeUtil.getByte(v2) << 14 ^ v3;
                    if(v5 >= 0) {
                        v4 = (long)(v5 ^ 0x3F80);
                        v2 = v + 3L;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    int v6 = v5 ^ UnsafeUtil.getByte(v + 3L) << 21;
                    if(v6 < 0) {
                        v4 = (long)(0xFFE03F80 ^ v6);
                        v2 = v + 4L;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    long v7 = v + 5L;
                    long v8 = ((long)UnsafeUtil.getByte(v + 4L)) << 28 ^ ((long)v6);
                    if(v8 >= 0L) {
                        v9 = 0xFE03F80L;
                        v4 = v9 ^ v8;
                        v2 = v7;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    long v10 = v + 6L;
                    long v11 = v8 ^ ((long)UnsafeUtil.getByte(v7)) << 35;
                    if(v11 < 0L) {
                        v12 = 0xFFFFFFF80FE03F80L;
                        v4 = v12 ^ v11;
                        v2 = v10;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    v7 = v + 7L;
                    v8 = v11 ^ ((long)UnsafeUtil.getByte(v10)) << 42;
                    if(v8 >= 0L) {
                        v9 = 0x3F80FE03F80L;
                        v4 = v9 ^ v8;
                        v2 = v7;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    v10 = v + 8L;
                    v11 = v8 ^ ((long)UnsafeUtil.getByte(v7)) << 49;
                    if(v11 < 0L) {
                        v12 = 0xFFFE03F80FE03F80L;
                        v4 = v12 ^ v11;
                        v2 = v10;
                        this.currentByteBufferPos = v2;
                        return v4;
                    }
                    v7 = v + 9L;
                    long v13 = v11 ^ ((long)UnsafeUtil.getByte(v10)) << 56 ^ 0xFE03F80FE03F80L;
                    if(v13 >= 0L) {
                        v2 = v7;
                        this.currentByteBufferPos = v2;
                        return v13;
                    }
                    else if(((long)UnsafeUtil.getByte(v7)) >= 0L) {
                        v2 = v + 10L;
                        this.currentByteBufferPos = v2;
                        return v13;
                    }
                }
            }
            return this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return IterableDirectByteBufferDecoder.decodeZigZag32(this.readRawVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return IterableDirectByteBufferDecoder.decodeZigZag64(this.readRawVarint64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0) {
                long v1 = this.currentByteBufferPos;
                if(((long)v) <= this.currentByteBufferLimit - v1) {
                    byte[] arr_b = new byte[v];
                    UnsafeUtil.copyMemory(v1, arr_b, 0L, v);
                    this.currentByteBufferPos += (long)v;
                    return new String(arr_b, Internal.UTF_8);
                }
            }
            if(v > 0 && v <= this.remaining()) {
                byte[] arr_b1 = new byte[v];
                this.readRawBytesTo(arr_b1, 0, v);
                return new String(arr_b1, Internal.UTF_8);
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0) {
                long v1 = this.currentByteBufferPos;
                if(((long)v) <= this.currentByteBufferLimit - v1) {
                    String s = Utf8.decodeUtf8(this.currentByteBuffer, ((int)(v1 - this.currentByteBufferStartPos)), v);
                    this.currentByteBufferPos += (long)v;
                    return s;
                }
            }
            if(v >= 0 && v <= this.remaining()) {
                byte[] arr_b = new byte[v];
                this.readRawBytesTo(arr_b, 0, v);
                return Utf8.decodeUtf8(arr_b, 0, v);
            }
            if(v != 0) {
                throw v > 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int v, Builder messageLite$Builder0) throws IOException {
            this.readGroup(v, messageLite$Builder0, ExtensionRegistryLite.getEmptyRegistry());
        }

        private void recomputeBufferSizeAfterLimit() {
            int v = this.totalBufferSize + this.bufferSizeAfterCurrentLimit;
            this.totalBufferSize = v;
            int v1 = v - this.startOffset;
            int v2 = this.currentLimit;
            if(v1 > v2) {
                int v3 = v1 - v2;
                this.bufferSizeAfterCurrentLimit = v3;
                this.totalBufferSize = v - v3;
                return;
            }
            this.bufferSizeAfterCurrentLimit = 0;
        }

        private int remaining() {
            return (int)(((long)(this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos + this.currentByteBufferStartPos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startOffset = (int)(((long)this.totalBytesRead) + this.currentByteBufferPos - this.currentByteBufferStartPos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v) throws IOException {
            switch(v & 7) {
                case 0: {
                    this.skipRawVarint();
                    return true;
                }
                case 1: {
                    this.skipRawBytes(8);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                this.skipMessage();
                                this.checkLastTagWas(v >>> 3 << 3 | 4);
                                return true;
                            }
                            case 4: {
                                return false;
                            }
                            default: {
                                if((v & 7) != 5) {
                                    throw InvalidProtocolBufferException.invalidWireType();
                                }
                                this.skipRawBytes(4);
                                return true;
                            }
                        }
                    }
                    this.skipRawBytes(this.readRawVarint32());
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v, CodedOutputStream codedOutputStream0) throws IOException {
            switch(v & 7) {
                case 0: {
                    long v3 = this.readInt64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeUInt64NoTag(v3);
                    return true;
                }
                case 1: {
                    long v4 = this.readRawLittleEndian64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeFixed64NoTag(v4);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                codedOutputStream0.writeUInt32NoTag(v);
                                this.skipMessage(codedOutputStream0);
                                int v2 = v >>> 3 << 3 | 4;
                                this.checkLastTagWas(v2);
                                codedOutputStream0.writeUInt32NoTag(v2);
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
                                codedOutputStream0.writeUInt32NoTag(v);
                                codedOutputStream0.writeFixed32NoTag(v1);
                                return true;
                            }
                        }
                    }
                    ByteString byteString0 = this.readBytes();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeBytesNoTag(byteString0);
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream0) throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v, codedOutputStream0));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int v) throws IOException {
            if(v < 0 || ((long)v) > ((long)(this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos + this.currentByteBufferStartPos) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            while(v > 0) {
                if(this.currentRemaining() == 0L) {
                    this.getNextByteBuffer();
                }
                int v1 = Math.min(v, ((int)this.currentRemaining()));
                v -= v1;
                this.currentByteBufferPos += (long)v1;
            }
        }

        private void skipRawVarint() throws IOException {
            for(int v = 0; v < 10; ++v) {
                if(this.readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private ByteBuffer slice(int v, int v1) throws IOException {
            int v2 = this.currentByteBuffer.position();
            int v3 = this.currentByteBuffer.limit();
            ByteBuffer byteBuffer0 = this.currentByteBuffer;
            try {
                byteBuffer0.position(v);
                byteBuffer0.limit(v1);
                return this.currentByteBuffer.slice();
            }
            catch(IllegalArgumentException unused_ex) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            finally {
                byteBuffer0.position(v2);
                byteBuffer0.limit(v3);
            }
        }

        private void tryGetNextByteBuffer() {
            Object object0 = this.iterator.next();
            this.currentByteBuffer = (ByteBuffer)object0;
            this.totalBytesRead += (int)(this.currentByteBufferPos - this.currentByteBufferStartPos);
            long v = (long)((ByteBuffer)object0).position();
            this.currentByteBufferPos = v;
            this.currentByteBufferStartPos = v;
            this.currentByteBufferLimit = (long)this.currentByteBuffer.limit();
            long v1 = UnsafeUtil.addressOffset(this.currentByteBuffer);
            this.currentAddress = v1;
            this.currentByteBufferPos += v1;
            this.currentByteBufferStartPos += v1;
            this.currentByteBufferLimit += v1;
        }
    }

    static final class StreamDecoder extends CodedInputStream {
        interface RefillCallback {
            void onRefill();
        }

        class SkippedDataSink implements RefillCallback {
            private ByteArrayOutputStream byteArrayStream;
            private int lastPos;

            private SkippedDataSink() {
                this.lastPos = codedInputStream$StreamDecoder0.pos;
            }

            ByteBuffer getSkippedData() {
                ByteArrayOutputStream byteArrayOutputStream0 = this.byteArrayStream;
                if(byteArrayOutputStream0 == null) {
                    return ByteBuffer.wrap(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                }
                byteArrayOutputStream0.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos);
                return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream$StreamDecoder$RefillCallback
            public void onRefill() {
                if(this.byteArrayStream == null) {
                    this.byteArrayStream = new ByteArrayOutputStream();
                }
                this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                this.lastPos = 0;
            }
        }

        private final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private RefillCallback refillCallback;
        private int totalBytesRetired;

        private StreamDecoder(InputStream inputStream0, int v) {
            super(null);
            this.currentLimit = 0x7FFFFFFF;
            this.refillCallback = null;
            Internal.checkNotNull(inputStream0, "input");
            this.input = inputStream0;
            this.buffer = new byte[v];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        StreamDecoder(InputStream inputStream0, int v, com.google.crypto.tink.shaded.protobuf.CodedInputStream.1 codedInputStream$10) {
            this(inputStream0, v);
        }

        private static int available(InputStream inputStream0) throws IOException {
            try {
                return inputStream0.available();
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                invalidProtocolBufferException0.setThrownFromInputStream();
                throw invalidProtocolBufferException0;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int v) throws InvalidProtocolBufferException {
            if(this.lastTag != v) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean enabled) {
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            return this.currentLimit == 0x7FFFFFFF ? -1 : this.currentLimit - (this.totalBytesRetired + this.pos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.pos == this.bufferSize && !this.tryRefillBuffer(1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int v) {
            this.currentLimit = v;
            this.recomputeBufferSizeAfterLimit();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int v) throws InvalidProtocolBufferException {
            if(v < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int v1 = v + (this.totalBytesRetired + this.pos);
            int v2 = this.currentLimit;
            if(v1 > v2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = v1;
            this.recomputeBufferSizeAfterLimit();
            return v2;
        }

        private static int read(InputStream inputStream0, byte[] arr_b, int v, int v1) throws IOException {
            try {
                return inputStream0.read(arr_b, v, v1);
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                invalidProtocolBufferException0.setThrownFromInputStream();
                throw invalidProtocolBufferException0;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return this.readRawVarint64() != 0L;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            int v = this.readRawVarint32();
            int v1 = this.pos;
            if(v <= this.bufferSize - v1 && v > 0) {
                byte[] arr_b = Arrays.copyOfRange(this.buffer, v1, v1 + v);
                this.pos += v;
                return arr_b;
            }
            return this.readRawBytesSlowPath(v, false);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int v = this.readRawVarint32();
            int v1 = this.pos;
            if(v <= this.bufferSize - v1 && v > 0) {
                ByteBuffer byteBuffer0 = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, v1, v1 + v));
                this.pos += v;
                return byteBuffer0;
            }
            return v == 0 ? Internal.EMPTY_BYTE_BUFFER : ByteBuffer.wrap(this.readRawBytesSlowPath(v, true));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int v = this.readRawVarint32();
            int v1 = this.pos;
            if(v <= this.bufferSize - v1 && v > 0) {
                ByteString byteString0 = ByteString.copyFrom(this.buffer, v1, v);
                this.pos += v;
                return byteString0;
            }
            return v == 0 ? ByteString.EMPTY : this.readBytesSlowPath(v);
        }

        private ByteString readBytesSlowPath(int v) throws IOException {
            byte[] arr_b = this.readRawBytesSlowPathOneChunk(v);
            if(arr_b != null) {
                return ByteString.copyFrom(arr_b);
            }
            int v1 = this.pos;
            int v2 = this.bufferSize - v1;
            this.totalBytesRetired += this.bufferSize;
            this.pos = 0;
            this.bufferSize = 0;
            List list0 = this.readRawBytesSlowPathRemainingChunks(v - v2);
            byte[] arr_b1 = new byte[v];
            System.arraycopy(this.buffer, v1, arr_b1, 0, v2);
            for(Object object0: list0) {
                System.arraycopy(((byte[])object0), 0, arr_b1, v2, ((byte[])object0).length);
                v2 += ((byte[])object0).length;
            }
            return ByteString.wrap(arr_b1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(this.readRawLittleEndian64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(this.readRawLittleEndian32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readGroup(int v, Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int v, Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readMessage(Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            if(this.pos == this.bufferSize) {
                this.refillBuffer(1);
            }
            int v = this.pos;
            this.pos = v + 1;
            return this.buffer[v];
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int v) throws IOException {
            int v1 = this.pos;
            if(v <= this.bufferSize - v1 && v > 0) {
                int v2 = v + v1;
                this.pos = v2;
                return Arrays.copyOfRange(this.buffer, v1, v2);
            }
            return this.readRawBytesSlowPath(v, false);
        }

        private byte[] readRawBytesSlowPath(int v, boolean z) throws IOException {
            byte[] arr_b = this.readRawBytesSlowPathOneChunk(v);
            if(arr_b != null) {
                return z ? ((byte[])arr_b.clone()) : arr_b;
            }
            int v1 = this.pos;
            int v2 = this.bufferSize - v1;
            this.totalBytesRetired += this.bufferSize;
            this.pos = 0;
            this.bufferSize = 0;
            List list0 = this.readRawBytesSlowPathRemainingChunks(v - v2);
            byte[] arr_b1 = new byte[v];
            System.arraycopy(this.buffer, v1, arr_b1, 0, v2);
            for(Object object0: list0) {
                System.arraycopy(((byte[])object0), 0, arr_b1, v2, ((byte[])object0).length);
                v2 += ((byte[])object0).length;
            }
            return arr_b1;
        }

        private byte[] readRawBytesSlowPathOneChunk(int v) throws IOException {
            if(v == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if(v < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int v1 = this.totalBytesRetired + this.pos + v;
            if(v1 - this.sizeLimit > 0) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            int v2 = this.currentLimit;
            if(v1 <= v2) {
                int v3 = this.bufferSize - this.pos;
                if(v - v3 >= 0x1000 && v - v3 > StreamDecoder.available(this.input)) {
                    return null;
                }
                byte[] arr_b = new byte[v];
                System.arraycopy(this.buffer, this.pos, arr_b, 0, v3);
                this.totalBytesRetired += this.bufferSize;
                this.pos = 0;
                this.bufferSize = 0;
                while(v3 < v) {
                    int v4 = StreamDecoder.read(this.input, arr_b, v3, v - v3);
                    if(v4 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += v4;
                    v3 += v4;
                }
                return arr_b;
            }
            this.skipRawBytes(v2 - this.totalBytesRetired - this.pos);
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private List readRawBytesSlowPathRemainingChunks(int v) throws IOException {
            List list0 = new ArrayList();
            while(v > 0) {
                int v1 = Math.min(v, 0x1000);
                byte[] arr_b = new byte[v1];
                for(int v2 = 0; v2 < v1; v2 += v3) {
                    int v3 = this.input.read(arr_b, v2, v1 - v2);
                    if(v3 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += v3;
                }
                v -= v1;
                list0.add(arr_b);
            }
            return list0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            int v = this.pos;
            if(this.bufferSize - v < 4) {
                this.refillBuffer(4);
                v = this.pos;
            }
            this.pos = v + 4;
            return (this.buffer[v + 3] & 0xFF) << 24 | (this.buffer[v] & 0xFF | (this.buffer[v + 1] & 0xFF) << 8 | (this.buffer[v + 2] & 0xFF) << 16);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            int v = this.pos;
            if(this.bufferSize - v < 8) {
                this.refillBuffer(8);
                v = this.pos;
            }
            this.pos = v + 8;
            return (((long)this.buffer[v + 7]) & 0xFFL) << 56 | (((long)this.buffer[v]) & 0xFFL | (((long)this.buffer[v + 1]) & 0xFFL) << 8 | (((long)this.buffer[v + 2]) & 0xFFL) << 16 | (((long)this.buffer[v + 3]) & 0xFFL) << 24 | (((long)this.buffer[v + 4]) & 0xFFL) << 0x20 | (((long)this.buffer[v + 5]) & 0xFFL) << 40 | (((long)this.buffer[v + 6]) & 0xFFL) << 0x30);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawVarint32() throws IOException {
            int v5;
            int v = this.pos;
            int v1 = this.bufferSize;
            if(v1 != v) {
                byte[] arr_b = this.buffer;
                int v2 = arr_b[v];
                if(v2 >= 0) {
                    this.pos = v + 1;
                    return v2;
                }
                if(v1 - (v + 1) >= 9) {
                    int v3 = v + 2;
                    int v4 = arr_b[v + 1] << 7 ^ v2;
                    if(v4 < 0) {
                        v5 = v4 ^ 0xFFFFFF80;
                        this.pos = v3;
                        return v5;
                    }
                    int v6 = v + 3;
                    int v7 = arr_b[v3] << 14 ^ v4;
                    if(v7 >= 0) {
                        v5 = v7 ^ 0x3F80;
                        v3 = v6;
                        this.pos = v3;
                        return v5;
                    }
                    int v8 = v + 4;
                    int v9 = v7 ^ arr_b[v6] << 21;
                    if(v9 < 0) {
                        v5 = 0xFFE03F80 ^ v9;
                        v3 = v8;
                        this.pos = v3;
                        return v5;
                    }
                    v6 = v + 5;
                    int v10 = arr_b[v8];
                    int v11 = v9 ^ v10 << 28 ^ 0xFE03F80;
                    if(v10 >= 0) {
                        v3 = v6;
                        this.pos = v3;
                        return v11;
                    }
                    v8 = v + 6;
                    if(arr_b[v6] >= 0) {
                        v3 = v8;
                        this.pos = v3;
                        return v11;
                    }
                    v6 = v + 7;
                    if(arr_b[v8] >= 0) {
                        v3 = v6;
                        this.pos = v3;
                        return v11;
                    }
                    v8 = v + 8;
                    if(arr_b[v6] >= 0) {
                        v3 = v8;
                        this.pos = v3;
                        return v11;
                    }
                    v6 = v + 9;
                    if(arr_b[v8] >= 0) {
                        v3 = v6;
                        this.pos = v3;
                        return v11;
                    }
                    else if(arr_b[v6] >= 0) {
                        v3 = v + 10;
                        this.pos = v3;
                        return v11;
                    }
                }
            }
            return (int)this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long v13;
            long v11;
            long v9;
            long v5;
            int v = this.pos;
            int v1 = this.bufferSize;
            if(v1 != v) {
                byte[] arr_b = this.buffer;
                int v2 = arr_b[v];
                if(v2 >= 0) {
                    this.pos = v + 1;
                    return (long)v2;
                }
                if(v1 - (v + 1) >= 9) {
                    int v3 = v + 2;
                    int v4 = arr_b[v + 1] << 7 ^ v2;
                    if(v4 < 0) {
                        v5 = (long)(v4 ^ 0xFFFFFF80);
                        this.pos = v3;
                        return v5;
                    }
                    int v6 = arr_b[v3] << 14 ^ v4;
                    if(v6 >= 0) {
                        v5 = (long)(v6 ^ 0x3F80);
                        v3 = v + 3;
                        this.pos = v3;
                        return v5;
                    }
                    int v7 = v + 4;
                    int v8 = v6 ^ arr_b[v + 3] << 21;
                    if(v8 < 0) {
                        v9 = (long)(0xFFE03F80 ^ v8);
                        v3 = v7;
                        this.pos = v3;
                        return v9;
                    }
                    v3 = v + 5;
                    long v10 = ((long)v8) ^ ((long)arr_b[v7]) << 28;
                    if(v10 >= 0L) {
                        v11 = 0xFE03F80L;
                        v5 = v11 ^ v10;
                        this.pos = v3;
                        return v5;
                    }
                    v7 = v + 6;
                    long v12 = v10 ^ ((long)arr_b[v3]) << 35;
                    if(v12 < 0L) {
                        v13 = 0xFFFFFFF80FE03F80L;
                        v9 = v13 ^ v12;
                        v3 = v7;
                        this.pos = v3;
                        return v9;
                    }
                    v3 = v + 7;
                    v10 = v12 ^ ((long)arr_b[v7]) << 42;
                    if(v10 >= 0L) {
                        v11 = 0x3F80FE03F80L;
                        v5 = v11 ^ v10;
                        this.pos = v3;
                        return v5;
                    }
                    v7 = v + 8;
                    v12 = v10 ^ ((long)arr_b[v3]) << 49;
                    if(v12 < 0L) {
                        v13 = 0xFFFE03F80FE03F80L;
                        v9 = v13 ^ v12;
                        v3 = v7;
                        this.pos = v3;
                        return v9;
                    }
                    v3 = v + 9;
                    long v14 = v12 ^ ((long)arr_b[v7]) << 56 ^ 0xFE03F80FE03F80L;
                    boolean z = false;
                    if(v14 >= 0L) {
                        z = true;
                    }
                    else if(((long)arr_b[v3]) >= 0L) {
                        z = true;
                        v3 = v + 10;
                    }
                    if(z) {
                        this.pos = v3;
                        return v14;
                    }
                }
            }
            return this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return StreamDecoder.decodeZigZag32(this.readRawVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return StreamDecoder.decodeZigZag64(this.readRawVarint64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0 && v <= this.bufferSize - this.pos) {
                String s = new String(this.buffer, this.pos, v, Internal.UTF_8);
                this.pos += v;
                return s;
            }
            if(v == 0) {
                return "";
            }
            if(v <= this.bufferSize) {
                this.refillBuffer(v);
                String s1 = new String(this.buffer, this.pos, v, Internal.UTF_8);
                this.pos += v;
                return s1;
            }
            return new String(this.readRawBytesSlowPath(v, false), Internal.UTF_8);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int v = this.readRawVarint32();
            int v1 = this.pos;
            int v2 = this.bufferSize;
            if(v <= v2 - v1 && v > 0) {
                this.pos = v1 + v;
                return Utf8.decodeUtf8(this.buffer, v1, v);
            }
            if(v == 0) {
                return "";
            }
            if(v <= v2) {
                this.refillBuffer(v);
                this.pos = v;
                return Utf8.decodeUtf8(this.buffer, v1, v);
            }
            return Utf8.decodeUtf8(this.readRawBytesSlowPath(v, false), v1, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int v, Builder messageLite$Builder0) throws IOException {
            this.readGroup(v, messageLite$Builder0, ExtensionRegistryLite.getEmptyRegistry());
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
                throw v <= this.sizeLimit - this.totalBytesRetired - this.pos ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.sizeLimitExceeded();
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        private static long skip(InputStream inputStream0, long v) throws IOException {
            try {
                return inputStream0.skip(v);
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                invalidProtocolBufferException0.setThrownFromInputStream();
                throw invalidProtocolBufferException0;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v) throws IOException {
            switch(v & 7) {
                case 0: {
                    this.skipRawVarint();
                    return true;
                }
                case 1: {
                    this.skipRawBytes(8);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                this.skipMessage();
                                this.checkLastTagWas(v >>> 3 << 3 | 4);
                                return true;
                            }
                            case 4: {
                                return false;
                            }
                            default: {
                                if((v & 7) != 5) {
                                    throw InvalidProtocolBufferException.invalidWireType();
                                }
                                this.skipRawBytes(4);
                                return true;
                            }
                        }
                    }
                    this.skipRawBytes(this.readRawVarint32());
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v, CodedOutputStream codedOutputStream0) throws IOException {
            switch(v & 7) {
                case 0: {
                    long v3 = this.readInt64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeUInt64NoTag(v3);
                    return true;
                }
                case 1: {
                    long v4 = this.readRawLittleEndian64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeFixed64NoTag(v4);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                codedOutputStream0.writeUInt32NoTag(v);
                                this.skipMessage(codedOutputStream0);
                                int v2 = v >>> 3 << 3 | 4;
                                this.checkLastTagWas(v2);
                                codedOutputStream0.writeUInt32NoTag(v2);
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
                                codedOutputStream0.writeUInt32NoTag(v);
                                codedOutputStream0.writeFixed32NoTag(v1);
                                return true;
                            }
                        }
                    }
                    ByteString byteString0 = this.readBytes();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeBytesNoTag(byteString0);
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream0) throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v, codedOutputStream0));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int v) throws IOException {
            int v1 = this.pos;
            if(v <= this.bufferSize - v1 && v >= 0) {
                this.pos = v1 + v;
                return;
            }
            this.skipRawBytesSlowPath(v);
        }

        private void skipRawBytesSlowPath(int v) throws IOException {
            int v11;
            if(v < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int v1 = 0;
            int v2 = this.totalBytesRetired;
            int v3 = this.pos;
            int v4 = this.currentLimit;
            if(v2 + v3 + v <= v4) {
                if(this.refillCallback == null) {
                    this.totalBytesRetired = v2 + v3;
                    int v5 = this.bufferSize - v3;
                    this.bufferSize = 0;
                    this.pos = 0;
                    v1 = v5;
                    try {
                        while(v1 < v) {
                            long v7 = (long)(v - v1);
                            long v8 = StreamDecoder.skip(this.input, v7);
                            int v9 = Long.compare(v8, 0L);
                            if(v9 < 0 || v8 > v7) {
                                throw new IllegalStateException(this.input.getClass() + "#skip returned invalid result: " + v8 + "\nThe InputStream implementation is buggy.");
                            }
                            if(v9 == 0) {
                                break;
                            }
                            v1 += (int)v8;
                        }
                    }
                    finally {
                        this.totalBytesRetired += v1;
                        this.recomputeBufferSizeAfterLimit();
                    }
                }
                if(v1 < v) {
                    int v10 = this.bufferSize - this.pos;
                    this.pos = this.bufferSize;
                    this.refillBuffer(1);
                    while(true) {
                        v11 = v - v10;
                        int v12 = this.bufferSize;
                        if(v11 <= v12) {
                            break;
                        }
                        v10 += v12;
                        this.pos = v12;
                        this.refillBuffer(1);
                    }
                    this.pos = v11;
                }
                return;
            }
            this.skipRawBytes(v4 - v2 - v3);
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private void skipRawVarint() throws IOException {
            if(this.bufferSize - this.pos >= 10) {
                this.skipRawVarintFastPath();
                return;
            }
            this.skipRawVarintSlowPath();
        }

        private void skipRawVarintFastPath() throws IOException {
            for(int v = 0; v < 10; ++v) {
                int v1 = this.pos;
                this.pos = v1 + 1;
                if(this.buffer[v1] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            for(int v = 0; v < 10; ++v) {
                if(this.readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private boolean tryRefillBuffer(int v) throws IOException {
            if(this.pos + v <= this.bufferSize) {
                throw new IllegalStateException("refillBuffer() called when " + v + " bytes were already available in buffer");
            }
            int v1 = this.totalBytesRetired;
            int v2 = this.pos;
            if(v > this.sizeLimit - v1 - v2) {
                return false;
            }
            if(v1 + v2 + v > this.currentLimit) {
                return false;
            }
            RefillCallback codedInputStream$StreamDecoder$RefillCallback0 = this.refillCallback;
            if(codedInputStream$StreamDecoder$RefillCallback0 != null) {
                codedInputStream$StreamDecoder$RefillCallback0.onRefill();
            }
            int v3 = this.pos;
            if(v3 > 0) {
                int v4 = this.bufferSize;
                if(v4 > v3) {
                    System.arraycopy(this.buffer, v3, this.buffer, 0, v4 - v3);
                }
                this.totalBytesRetired += v3;
                this.bufferSize -= v3;
                this.pos = 0;
            }
            int v5 = StreamDecoder.read(this.input, this.buffer, this.bufferSize, Math.min(this.buffer.length - this.bufferSize, this.sizeLimit - this.totalBytesRetired - this.bufferSize));
            if(v5 == 0 || v5 < -1 || v5 > this.buffer.length) {
                throw new IllegalStateException(this.input.getClass() + "#read(byte[]) returned invalid result: " + v5 + "\nThe InputStream implementation is buggy.");
            }
            if(v5 > 0) {
                this.bufferSize += v5;
                this.recomputeBufferSizeAfterLimit();
                return this.bufferSize < v ? this.tryRefillBuffer(v) : true;
            }
            return false;
        }
    }

    static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        private UnsafeDirectNioDecoder(ByteBuffer byteBuffer0, boolean z) {
            super(null);
            this.currentLimit = 0x7FFFFFFF;
            this.buffer = byteBuffer0;
            long v = UnsafeUtil.addressOffset(byteBuffer0);
            this.address = v;
            this.limit = ((long)byteBuffer0.limit()) + v;
            long v1 = v + ((long)byteBuffer0.position());
            this.pos = v1;
            this.startPos = v1;
            this.immutable = z;
        }

        UnsafeDirectNioDecoder(ByteBuffer byteBuffer0, boolean z, com.google.crypto.tink.shaded.protobuf.CodedInputStream.1 codedInputStream$10) {
            this(byteBuffer0, z);
        }

        private int bufferPos(long v) {
            return (int)(v - this.address);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void checkLastTagWas(int v) throws InvalidProtocolBufferException {
            if(this.lastTag != v) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            return this.currentLimit == 0x7FFFFFFF ? -1 : this.currentLimit - this.getTotalBytesRead();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int)(this.pos - this.startPos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        // 去混淆评级： 低(20)
        static boolean isSupported() [...] // 潜在的解密器

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void popLimit(int v) {
            this.currentLimit = v;
            this.recomputeBufferSizeAfterLimit();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int pushLimit(int v) throws InvalidProtocolBufferException {
            if(v < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int v1 = v + this.getTotalBytesRead();
            int v2 = this.currentLimit;
            if(v1 > v2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = v1;
            this.recomputeBufferSizeAfterLimit();
            return v2;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return this.readRawVarint64() != 0L;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return this.readRawBytes(this.readRawVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0 && v <= this.remaining()) {
                if(!this.immutable && this.enableAliasing) {
                    ByteBuffer byteBuffer0 = this.slice(this.pos, this.pos + ((long)v));
                    this.pos += (long)v;
                    return byteBuffer0;
                }
                byte[] arr_b = new byte[v];
                UnsafeUtil.copyMemory(this.pos, arr_b, 0L, v);
                this.pos += (long)v;
                return ByteBuffer.wrap(arr_b);
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return Internal.EMPTY_BYTE_BUFFER;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0 && v <= this.remaining()) {
                if(this.immutable && this.enableAliasing) {
                    ByteBuffer byteBuffer0 = this.slice(this.pos, this.pos + ((long)v));
                    this.pos += (long)v;
                    return ByteString.wrap(byteBuffer0);
                }
                byte[] arr_b = new byte[v];
                UnsafeUtil.copyMemory(this.pos, arr_b, 0L, v);
                this.pos += (long)v;
                return ByteString.wrap(arr_b);
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return ByteString.EMPTY;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(this.readRawLittleEndian64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(this.readRawLittleEndian32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readGroup(int v, Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readGroup(int v, Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.checkRecursionLimit();
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(v << 3 | 4);
            --this.recursionDepth;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public MessageLite readMessage(Parser parser0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            MessageLite messageLite0 = (MessageLite)parser0.parsePartialFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
            return messageLite0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void readMessage(Builder messageLite$Builder0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readRawVarint32();
            this.checkRecursionLimit();
            int v1 = this.pushLimit(v);
            ++this.recursionDepth;
            messageLite$Builder0.mergeFrom(this, extensionRegistryLite0);
            this.checkLastTagWas(0);
            --this.recursionDepth;
            if(this.getBytesUntilLimit() != 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.popLimit(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            long v = this.pos;
            if(v == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = v + 1L;
            return UnsafeUtil.getByte(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public byte[] readRawBytes(int v) throws IOException {
            if(v >= 0 && v <= this.remaining()) {
                byte[] arr_b = new byte[v];
                this.slice(this.pos, this.pos + ((long)v)).get(arr_b);
                this.pos += (long)v;
                return arr_b;
            }
            if(v > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if(v != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            return Internal.EMPTY_BYTE_ARRAY;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            long v = this.pos;
            if(this.limit - v < 4L) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = v + 4L;
            int v1 = UnsafeUtil.getByte(v);
            int v2 = UnsafeUtil.getByte(v + 1L);
            int v3 = UnsafeUtil.getByte(v + 2L);
            return (UnsafeUtil.getByte(v + 3L) & 0xFF) << 24 | (v1 & 0xFF | (v2 & 0xFF) << 8 | (v3 & 0xFF) << 16);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            long v = this.pos;
            if(this.limit - v < 8L) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = v + 8L;
            long v1 = ((long)UnsafeUtil.getByte(v)) & 0xFFL | (((long)UnsafeUtil.getByte(v + 1L)) & 0xFFL) << 8 | (((long)UnsafeUtil.getByte(v + 2L)) & 0xFFL) << 16 | (((long)UnsafeUtil.getByte(v + 3L)) & 0xFFL) << 24 | (((long)UnsafeUtil.getByte(v + 4L)) & 0xFFL) << 0x20 | (((long)UnsafeUtil.getByte(v + 5L)) & 0xFFL) << 40 | (((long)UnsafeUtil.getByte(v + 6L)) & 0xFFL) << 0x30;
            return (((long)UnsafeUtil.getByte(v + 7L)) & 0xFFL) << 56 | v1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readRawVarint32() throws IOException {
            int v4;
            long v = this.pos;
            if(this.limit != v) {
                int v1 = UnsafeUtil.getByte(v);
                if(v1 >= 0) {
                    this.pos = v + 1L;
                    return v1;
                }
                if(this.limit - (v + 1L) >= 9L) {
                    long v2 = v + 2L;
                    int v3 = UnsafeUtil.getByte(v + 1L) << 7 ^ v1;
                    if(v3 < 0) {
                        v4 = v3 ^ 0xFFFFFF80;
                        this.pos = v2;
                        return v4;
                    }
                    long v5 = v + 3L;
                    int v6 = v3 ^ UnsafeUtil.getByte(v2) << 14;
                    if(v6 >= 0) {
                        v4 = v6 ^ 0x3F80;
                        v2 = v5;
                        this.pos = v2;
                        return v4;
                    }
                    v2 = v + 4L;
                    int v7 = v6 ^ UnsafeUtil.getByte(v5) << 21;
                    if(v7 < 0) {
                        v4 = 0xFFE03F80 ^ v7;
                        this.pos = v2;
                        return v4;
                    }
                    v5 = v + 5L;
                    int v8 = UnsafeUtil.getByte(v2);
                    int v9 = v7 ^ v8 << 28 ^ 0xFE03F80;
                    if(v8 >= 0) {
                        v2 = v5;
                        this.pos = v2;
                        return v9;
                    }
                    v2 = v + 6L;
                    if(UnsafeUtil.getByte(v5) >= 0) {
                        this.pos = v2;
                        return v9;
                    }
                    v5 = v + 7L;
                    if(UnsafeUtil.getByte(v2) >= 0) {
                        v2 = v5;
                        this.pos = v2;
                        return v9;
                    }
                    v2 = v + 8L;
                    if(UnsafeUtil.getByte(v5) >= 0) {
                        this.pos = v2;
                        return v9;
                    }
                    v5 = v + 9L;
                    if(UnsafeUtil.getByte(v2) >= 0) {
                        v2 = v5;
                        this.pos = v2;
                        return v9;
                    }
                    v2 = v + 10L;
                    if(UnsafeUtil.getByte(v5) >= 0) {
                        this.pos = v2;
                        return v9;
                    }
                }
            }
            return (int)this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long v13;
            long v10;
            long v6;
            int v4;
            long v = this.pos;
            if(this.limit != v) {
                int v1 = UnsafeUtil.getByte(v);
                if(v1 >= 0) {
                    this.pos = v + 1L;
                    return (long)v1;
                }
                if(this.limit - (v + 1L) >= 9L) {
                    long v2 = v + 2L;
                    int v3 = UnsafeUtil.getByte(v + 1L) << 7 ^ v1;
                    if(v3 < 0) {
                        v4 = v3 ^ 0xFFFFFF80;
                        this.pos = v2;
                        return (long)v4;
                    }
                    int v5 = v3 ^ UnsafeUtil.getByte(v2) << 14;
                    if(v5 >= 0) {
                        v6 = (long)(v5 ^ 0x3F80);
                        v2 = v + 3L;
                        this.pos = v2;
                        return v6;
                    }
                    v2 = v + 4L;
                    int v7 = v5 ^ UnsafeUtil.getByte(v + 3L) << 21;
                    if(v7 < 0) {
                        v4 = 0xFFE03F80 ^ v7;
                        this.pos = v2;
                        return (long)v4;
                    }
                    long v8 = v + 5L;
                    long v9 = ((long)v7) ^ ((long)UnsafeUtil.getByte(v2)) << 28;
                    if(v9 >= 0L) {
                        v10 = 0xFE03F80L;
                        v6 = v10 ^ v9;
                        v2 = v8;
                        this.pos = v2;
                        return v6;
                    }
                    long v11 = v + 6L;
                    long v12 = v9 ^ ((long)UnsafeUtil.getByte(v8)) << 35;
                    if(v12 < 0L) {
                        v13 = 0xFFFFFFF80FE03F80L;
                        v6 = v13 ^ v12;
                        v2 = v11;
                        this.pos = v2;
                        return v6;
                    }
                    v8 = v + 7L;
                    v9 = v12 ^ ((long)UnsafeUtil.getByte(v11)) << 42;
                    if(v9 >= 0L) {
                        v10 = 0x3F80FE03F80L;
                        v6 = v10 ^ v9;
                        v2 = v8;
                        this.pos = v2;
                        return v6;
                    }
                    v11 = v + 8L;
                    v12 = v9 ^ ((long)UnsafeUtil.getByte(v8)) << 49;
                    if(v12 < 0L) {
                        v13 = 0xFFFE03F80FE03F80L;
                        v6 = v13 ^ v12;
                        v2 = v11;
                        this.pos = v2;
                        return v6;
                    }
                    long v14 = v12 ^ ((long)UnsafeUtil.getByte(v11)) << 56 ^ 0xFE03F80FE03F80L;
                    if(v14 >= 0L) {
                        v2 = v + 9L;
                        this.pos = v2;
                        return v14;
                    }
                    else if(((long)UnsafeUtil.getByte(v + 9L)) >= 0L) {
                        v2 = v + 10L;
                        this.pos = v2;
                        return v14;
                    }
                }
            }
            return this.readRawVarint64SlowPath();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return this.readRawLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return this.readRawLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return UnsafeDirectNioDecoder.decodeZigZag32(this.readRawVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return UnsafeDirectNioDecoder.decodeZigZag64(this.readRawVarint64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readString() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0 && v <= this.remaining()) {
                byte[] arr_b = new byte[v];
                UnsafeUtil.copyMemory(this.pos, arr_b, 0L, v);
                this.pos += (long)v;
                return new String(arr_b, Internal.UTF_8);
            }
            if(v != 0) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int v = this.readRawVarint32();
            if(v > 0 && v <= this.remaining()) {
                String s = Utf8.decodeUtf8(this.buffer, this.bufferPos(this.pos), v);
                this.pos += (long)v;
                return s;
            }
            if(v != 0) {
                throw v > 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
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

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return this.readRawVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return this.readRawVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int v, Builder messageLite$Builder0) throws IOException {
            this.readGroup(v, messageLite$Builder0, ExtensionRegistryLite.getEmptyRegistry());
        }

        private void recomputeBufferSizeAfterLimit() {
            long v = this.limit + ((long)this.bufferSizeAfterLimit);
            this.limit = v;
            int v1 = (int)(v - this.startPos);
            int v2 = this.currentLimit;
            if(v1 > v2) {
                int v3 = v1 - v2;
                this.bufferSizeAfterLimit = v3;
                this.limit = v - ((long)v3);
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private int remaining() {
            return (int)(this.limit - this.pos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v) throws IOException {
            switch(v & 7) {
                case 0: {
                    this.skipRawVarint();
                    return true;
                }
                case 1: {
                    this.skipRawBytes(8);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                this.skipMessage();
                                this.checkLastTagWas(v >>> 3 << 3 | 4);
                                return true;
                            }
                            case 4: {
                                return false;
                            }
                            default: {
                                if((v & 7) != 5) {
                                    throw InvalidProtocolBufferException.invalidWireType();
                                }
                                this.skipRawBytes(4);
                                return true;
                            }
                        }
                    }
                    this.skipRawBytes(this.readRawVarint32());
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public boolean skipField(int v, CodedOutputStream codedOutputStream0) throws IOException {
            switch(v & 7) {
                case 0: {
                    long v3 = this.readInt64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeUInt64NoTag(v3);
                    return true;
                }
                case 1: {
                    long v4 = this.readRawLittleEndian64();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeFixed64NoTag(v4);
                    return true;
                }
                default: {
                    if((v & 7) != 2) {
                        switch(v & 7) {
                            case 3: {
                                codedOutputStream0.writeUInt32NoTag(v);
                                this.skipMessage(codedOutputStream0);
                                int v2 = v >>> 3 << 3 | 4;
                                this.checkLastTagWas(v2);
                                codedOutputStream0.writeUInt32NoTag(v2);
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
                                codedOutputStream0.writeUInt32NoTag(v);
                                codedOutputStream0.writeFixed32NoTag(v1);
                                return true;
                            }
                        }
                    }
                    ByteString byteString0 = this.readBytes();
                    codedOutputStream0.writeUInt32NoTag(v);
                    codedOutputStream0.writeBytesNoTag(byteString0);
                    return true;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream0) throws IOException {
            do {
                int v = this.readTag();
            }
            while(v != 0 && this.skipField(v, codedOutputStream0));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedInputStream
        public void skipRawBytes(int v) throws IOException {
            if(v < 0 || v > this.remaining()) {
                throw v >= 0 ? InvalidProtocolBufferException.truncatedMessage() : InvalidProtocolBufferException.negativeSize();
            }
            this.pos += (long)v;
        }

        private void skipRawVarint() throws IOException {
            if(this.remaining() >= 10) {
                this.skipRawVarintFastPath();
                return;
            }
            this.skipRawVarintSlowPath();
        }

        private void skipRawVarintFastPath() throws IOException {
            for(int v = 0; v < 10; ++v) {
                long v1 = this.pos;
                this.pos = v1 + 1L;
                if(UnsafeUtil.getByte(v1) >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            for(int v = 0; v < 10; ++v) {
                if(this.readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private ByteBuffer slice(long v, long v1) throws IOException {
            int v2 = this.buffer.position();
            int v3 = this.buffer.limit();
            ByteBuffer byteBuffer0 = this.buffer;
            try {
                byteBuffer0.position(this.bufferPos(v));
                byteBuffer0.limit(this.bufferPos(v1));
                return this.buffer.slice();
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                InvalidProtocolBufferException invalidProtocolBufferException0 = InvalidProtocolBufferException.truncatedMessage();
                invalidProtocolBufferException0.initCause(illegalArgumentException0);
                throw invalidProtocolBufferException0;
            }
            finally {
                byteBuffer0.position(v2);
                byteBuffer0.limit(v3);
            }
        }
    }

    private static final int DEFAULT_BUFFER_SIZE = 0x1000;
    private static final int DEFAULT_SIZE_LIMIT = 0x7FFFFFFF;
    private static volatile int defaultRecursionLimit = 100;
    int recursionDepth;
    int recursionLimit;
    private boolean shouldDiscardUnknownFields;
    int sizeLimit;
    CodedInputStreamReader wrapper;

    static {
    }

    private CodedInputStream() {
        this.recursionLimit = 100;
        this.sizeLimit = 0x7FFFFFFF;
        this.shouldDiscardUnknownFields = false;
    }

    CodedInputStream(com.google.crypto.tink.shaded.protobuf.CodedInputStream.1 codedInputStream$10) {
    }

    public abstract void checkLastTagWas(int arg1) throws InvalidProtocolBufferException;

    public void checkRecursionLimit() throws InvalidProtocolBufferException {
        if(this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    public static int decodeZigZag32(int n) [...] // Inlined contents

    public static long decodeZigZag64(long n) [...] // Inlined contents

    final void discardUnknownFields() {
        this.shouldDiscardUnknownFields = true;
    }

    public abstract void enableAliasing(boolean arg1);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd() throws IOException;

    public static CodedInputStream newInstance(InputStream inputStream0) {
        return CodedInputStream.newInstance(inputStream0, 0x1000);
    }

    public static CodedInputStream newInstance(InputStream inputStream0, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("bufferSize must be > 0");
        }
        return inputStream0 == null ? CodedInputStream.newInstance(Internal.EMPTY_BYTE_ARRAY) : new StreamDecoder(inputStream0, v, null);
    }

    // 去混淆评级： 低(30)
    public static CodedInputStream newInstance(Iterable iterable0) {
        return CodedInputStream.newInstance(iterable0, false);
    }

    static CodedInputStream newInstance(Iterable iterable0, boolean z) {
        int v = 0;
        int v1 = 0;
        for(Object object0: iterable0) {
            ByteBuffer byteBuffer0 = (ByteBuffer)object0;
            v1 += byteBuffer0.remaining();
            if(byteBuffer0.hasArray()) {
                v |= 1;
            }
            else {
                v |= (byteBuffer0.isDirect() ? 2 : 4);
            }
        }
        return v == 2 ? new IterableDirectByteBufferDecoder(iterable0, v1, z, null) : CodedInputStream.newInstance(new IterableByteBufferInputStream(iterable0));
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer0) {
        return CodedInputStream.newInstance(byteBuffer0, false);
    }

    static CodedInputStream newInstance(ByteBuffer byteBuffer0, boolean z) {
        if(byteBuffer0.hasArray()) {
            return CodedInputStream.newInstance(byteBuffer0.array(), byteBuffer0.arrayOffset() + byteBuffer0.position(), byteBuffer0.remaining(), z);
        }
        if(byteBuffer0.isDirect()) {
            return new UnsafeDirectNioDecoder(byteBuffer0, z, null);
        }
        int v = byteBuffer0.remaining();
        byte[] arr_b = new byte[v];
        byteBuffer0.duplicate().get(arr_b);
        return CodedInputStream.newInstance(arr_b, 0, v, true);
    }

    public static CodedInputStream newInstance(byte[] arr_b) {
        return CodedInputStream.newInstance(arr_b, 0, arr_b.length);
    }

    public static CodedInputStream newInstance(byte[] arr_b, int v, int v1) {
        return CodedInputStream.newInstance(arr_b, v, v1, false);
    }

    static CodedInputStream newInstance(byte[] arr_b, int v, int v1, boolean z) {
        CodedInputStream codedInputStream0 = new ArrayDecoder(arr_b, v, v1, z, null);
        try {
            ((ArrayDecoder)codedInputStream0).pushLimit(v1);
            return codedInputStream0;
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new IllegalArgumentException(invalidProtocolBufferException0);
        }
    }

    public abstract void popLimit(int arg1);

    public abstract int pushLimit(int arg1) throws InvalidProtocolBufferException;

    public abstract boolean readBool() throws IOException;

    public abstract byte[] readByteArray() throws IOException;

    public abstract ByteBuffer readByteBuffer() throws IOException;

    public abstract ByteString readBytes() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract int readEnum() throws IOException;

    public abstract int readFixed32() throws IOException;

    public abstract long readFixed64() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract MessageLite readGroup(int arg1, Parser arg2, ExtensionRegistryLite arg3) throws IOException;

    public abstract void readGroup(int arg1, Builder arg2, ExtensionRegistryLite arg3) throws IOException;

    public abstract int readInt32() throws IOException;

    public abstract long readInt64() throws IOException;

    public abstract MessageLite readMessage(Parser arg1, ExtensionRegistryLite arg2) throws IOException;

    public abstract void readMessage(Builder arg1, ExtensionRegistryLite arg2) throws IOException;

    public abstract byte readRawByte() throws IOException;

    public abstract byte[] readRawBytes(int arg1) throws IOException;

    public abstract int readRawLittleEndian32() throws IOException;

    public abstract long readRawLittleEndian64() throws IOException;

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

    static int readRawVarint32(InputStream inputStream0) throws IOException {
        int v = inputStream0.read();
        if(v == -1) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return CodedInputStream.readRawVarint32(v, inputStream0);
    }

    public abstract int readRawVarint32() throws IOException;

    public abstract long readRawVarint64() throws IOException;

    abstract long readRawVarint64SlowPath() throws IOException;

    public abstract int readSFixed32() throws IOException;

    public abstract long readSFixed64() throws IOException;

    public abstract int readSInt32() throws IOException;

    public abstract long readSInt64() throws IOException;

    public abstract String readString() throws IOException;

    public abstract String readStringRequireUtf8() throws IOException;

    public abstract int readTag() throws IOException;

    public abstract int readUInt32() throws IOException;

    public abstract long readUInt64() throws IOException;

    @Deprecated
    public abstract void readUnknownGroup(int arg1, Builder arg2) throws IOException;

    public abstract void resetSizeCounter();

    public final int setRecursionLimit(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("Recursion limit cannot be negative: " + v);
        }
        int v1 = this.recursionLimit;
        this.recursionLimit = v;
        return v1;
    }

    public final int setSizeLimit(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("Size limit cannot be negative: " + v);
        }
        int v1 = this.sizeLimit;
        this.sizeLimit = v;
        return v1;
    }

    final boolean shouldDiscardUnknownFields() {
        return this.shouldDiscardUnknownFields;
    }

    public abstract boolean skipField(int arg1) throws IOException;

    @Deprecated
    public abstract boolean skipField(int arg1, CodedOutputStream arg2) throws IOException;

    public abstract void skipMessage() throws IOException;

    public abstract void skipMessage(CodedOutputStream arg1) throws IOException;

    public abstract void skipRawBytes(int arg1) throws IOException;

    final void unsetDiscardUnknownFields() {
        this.shouldDiscardUnknownFields = false;
    }

    class com.google.crypto.tink.shaded.protobuf.CodedInputStream.1 {
    }

}

