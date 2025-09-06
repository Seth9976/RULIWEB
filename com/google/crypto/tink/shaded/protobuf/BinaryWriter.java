package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Queue;

@CheckReturnValue
abstract class BinaryWriter extends ByteOutput implements Writer {
    static final class SafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private int limitMinusOne;
        private int pos;

        SafeDirectWriter(BufferAllocator bufferAllocator0, int v) {
            super(bufferAllocator0, v, null);
            this.nextBuffer();
        }

        private int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void finishCurrentBuffer() {
            if(this.buffer != null) {
                this.totalDoneBytes += this.bytesWrittenToCurrentBuffer();
                this.buffer.position(this.pos + 1);
                this.buffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + this.bytesWrittenToCurrentBuffer();
        }

        private void nextBuffer() {
            this.nextBuffer(this.newDirectBuffer());
        }

        private void nextBuffer(int v) {
            this.nextBuffer(this.newDirectBuffer(v));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer0) {
            if(!allocatedBuffer0.hasNioBuffer()) {
                throw new RuntimeException("Allocated buffer does not have NIO buffer");
            }
            ByteBuffer byteBuffer0 = allocatedBuffer0.nioBuffer();
            if(!byteBuffer0.isDirect()) {
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            this.finishCurrentBuffer();
            this.buffers.addFirst(allocatedBuffer0);
            this.buffer = byteBuffer0;
            byteBuffer0.limit(byteBuffer0.capacity());
            this.buffer.position(0);
            this.buffer.order(ByteOrder.LITTLE_ENDIAN);
            int v = this.buffer.limit();
            this.limitMinusOne = v - 1;
            this.pos = v - 1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void requireSpace(int v) {
            if(this.spaceLeft() < v) {
                this.nextBuffer(v);
            }
        }

        private int spaceLeft() {
            return this.pos + 1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b) {
            int v = this.pos;
            this.pos = v - 1;
            this.buffer.put(v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            if(this.spaceLeft() < v) {
                this.nextBuffer(v);
            }
            int v1 = this.pos - v;
            this.pos = v1;
            this.buffer.position(v1 + 1);
            this.buffer.put(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] arr_b, int v, int v1) {
            if(this.spaceLeft() < v1) {
                this.nextBuffer(v1);
            }
            int v2 = this.pos - v1;
            this.pos = v2;
            this.buffer.position(v2 + 1);
            this.buffer.put(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int v, boolean z) {
            this.requireSpace(6);
            this.write(((byte)z));
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeBool(boolean z) {
            this.write(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int v, ByteString byteString0) {
            try {
                byteString0.writeToReverse(this);
            }
            catch(IOException iOException0) {
                throw new RuntimeException(iOException0);
            }
            this.requireSpace(10);
            this.writeVarint32(byteString0.size());
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        @Deprecated
        public void writeEndGroup(int v) {
            this.writeTag(v, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed32(int v) {
            int v1 = this.pos;
            this.pos = v1 - 4;
            this.buffer.putInt(v1 - 3, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int v, int v1) {
            this.requireSpace(9);
            this.writeFixed32(v1);
            this.writeTag(v, 5);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int v, long v1) {
            this.requireSpace(13);
            this.writeFixed64(v1);
            this.writeTag(v, 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed64(long v) {
            int v1 = this.pos;
            this.pos = v1 - 8;
            this.buffer.putLong(v1 - 7, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        @Deprecated
        public void writeGroup(int v, Object object0) throws IOException {
            this.writeTag(v, 4);
            Protobuf.getInstance().writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int v, Object object0, Schema schema0) throws IOException {
            this.writeTag(v, 4);
            schema0.writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeInt32(int v) {
            if(v >= 0) {
                this.writeVarint32(v);
                return;
            }
            this.writeVarint64(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int v, int v1) {
            this.requireSpace(15);
            this.writeInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            if(this.spaceLeft() < v) {
                this.totalDoneBytes += v;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer0));
                this.nextBuffer();
                return;
            }
            int v1 = this.pos - v;
            this.pos = v1;
            this.buffer.position(v1 + 1);
            this.buffer.put(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] arr_b, int v, int v1) {
            if(this.spaceLeft() < v1) {
                this.totalDoneBytes += v1;
                this.buffers.addFirst(AllocatedBuffer.wrap(arr_b, v, v1));
                this.nextBuffer();
                return;
            }
            int v2 = this.pos - v1;
            this.pos = v2;
            this.buffer.position(v2 + 1);
            this.buffer.put(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            Protobuf.getInstance().writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0, Schema schema0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            schema0.writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt32(int v) {
            this.writeVarint32(v >> 0x1F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int v, int v1) {
            this.requireSpace(10);
            this.writeSInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int v, long v1) {
            this.requireSpace(15);
            this.writeSInt64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt64(long v) {
            this.writeVarint64(v >> 0x3F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        @Deprecated
        public void writeStartGroup(int v) {
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int v, String s) {
            int v1 = this.getTotalBytesWritten();
            this.writeString(s);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeString(String s) {
            this.requireSpace(s.length());
            int v = s.length() - 1;
            this.pos -= v;
            while(v >= 0) {
                int v1 = s.charAt(v);
                if(v1 >= 0x80) {
                    break;
                }
                this.buffer.put(this.pos + v, ((byte)v1));
                --v;
            }
            if(v == -1) {
                --this.pos;
                return;
            }
            this.pos += v;
            while(v >= 0) {
                int v2 = s.charAt(v);
                if(v2 < 0x80) {
                    int v3 = this.pos;
                    if(v3 >= 0) {
                        this.pos = v3 - 1;
                        this.buffer.put(v3, ((byte)v2));
                    }
                }
                else {
                    if(v2 < 0x800) {
                        int v4 = this.pos;
                        if(v4 > 0) {
                            this.pos = v4 - 1;
                            this.buffer.put(v4, ((byte)(v2 & 0x3F | 0x80)));
                            int v5 = this.pos;
                            this.pos = v5 - 1;
                            this.buffer.put(v5, ((byte)(v2 >>> 6 | 960)));
                            goto label_64;
                        }
                    }
                    if(v2 < 0xD800 || 0xDFFF < v2) {
                        int v6 = this.pos;
                        if(v6 > 1) {
                            this.pos = v6 - 1;
                            this.buffer.put(v6, ((byte)(v2 & 0x3F | 0x80)));
                            int v7 = this.pos;
                            this.pos = v7 - 1;
                            this.buffer.put(v7, ((byte)(v2 >>> 6 & 0x3F | 0x80)));
                            int v8 = this.pos;
                            this.pos = v8 - 1;
                            this.buffer.put(v8, ((byte)(v2 >>> 12 | 480)));
                            goto label_64;
                        }
                    }
                    if(this.pos > 2) {
                        if(v != 0) {
                            int v9 = s.charAt(v - 1);
                            if(Character.isSurrogatePair(((char)v9), ((char)v2))) {
                                --v;
                                int v10 = Character.toCodePoint(((char)v9), ((char)v2));
                                int v11 = this.pos;
                                this.pos = v11 - 1;
                                this.buffer.put(v11, ((byte)(v10 & 0x3F | 0x80)));
                                int v12 = this.pos;
                                this.pos = v12 - 1;
                                this.buffer.put(v12, ((byte)(v10 >>> 6 & 0x3F | 0x80)));
                                int v13 = this.pos;
                                this.pos = v13 - 1;
                                this.buffer.put(v13, ((byte)(v10 >>> 12 & 0x3F | 0x80)));
                                int v14 = this.pos;
                                this.pos = v14 - 1;
                                this.buffer.put(v14, ((byte)(v10 >>> 18 | 0xF0)));
                                goto label_64;
                            }
                        }
                        throw new UnpairedSurrogateException(v - 1, v);
                    }
                    else {
                        this.requireSpace(v);
                        ++v;
                    }
                }
            label_64:
                --v;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int v, int v1) {
            this.writeVarint32(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int v, int v1) {
            this.requireSpace(10);
            this.writeVarint32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int v, long v1) {
            this.requireSpace(15);
            this.writeVarint64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint32(int v) {
            if((v & 0xFFFFFF80) == 0) {
                this.writeVarint32OneByte(v);
                return;
            }
            if((v & 0xFFFFC000) == 0) {
                this.writeVarint32TwoBytes(v);
                return;
            }
            if((0xFFE00000 & v) == 0) {
                this.writeVarint32ThreeBytes(v);
                return;
            }
            if((0xF0000000 & v) == 0) {
                this.writeVarint32FourBytes(v);
                return;
            }
            this.writeVarint32FiveBytes(v);
        }

        private void writeVarint32FiveBytes(int v) {
            int v1 = this.pos;
            this.pos = v1 - 1;
            this.buffer.put(v1, ((byte)(v >>> 28)));
            int v2 = this.pos;
            this.pos = v2 - 4;
            this.buffer.putInt(v2 - 3, v & 0x7F | 0x80 | ((v >>> 21 & 0x7F | 0x80) << 24 | (v >>> 14 & 0x7F | 0x80) << 16 | (v >>> 7 & 0x7F | 0x80) << 8));
        }

        private void writeVarint32FourBytes(int v) {
            int v1 = this.pos;
            this.pos = v1 - 4;
            this.buffer.putInt(v1 - 3, v & 0x7F | 0x80 | ((0xFE00000 & v) << 3 | (0x1FC000 & v | 0x200000) << 2 | (v & 0x3F80 | 0x4000) << 1));
        }

        private void writeVarint32OneByte(int v) {
            int v1 = this.pos;
            this.pos = v1 - 1;
            this.buffer.put(v1, ((byte)v));
        }

        private void writeVarint32ThreeBytes(int v) {
            int v1 = this.pos - 3;
            this.pos = v1;
            this.buffer.putInt(v1, (v & 0x7F | 0x80) << 8 | ((0x1FC000 & v) << 10 | (v & 0x3F80 | 0x4000) << 9));
        }

        private void writeVarint32TwoBytes(int v) {
            int v1 = this.pos;
            this.pos = v1 - 2;
            this.buffer.putShort(v1 - 1, ((short)(v & 0x7F | 0x80 | (v & 0x3F80) << 1)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint64(long v) {
            switch(BinaryWriter.computeUInt64SizeNoTag(v)) {
                case 1: {
                    this.writeVarint64OneByte(v);
                    return;
                }
                case 2: {
                    this.writeVarint64TwoBytes(v);
                    return;
                }
                case 3: {
                    this.writeVarint64ThreeBytes(v);
                    return;
                }
                case 4: {
                    this.writeVarint64FourBytes(v);
                    return;
                }
                case 5: {
                    this.writeVarint64FiveBytes(v);
                    return;
                }
                case 6: {
                    this.writeVarint64SixBytes(v);
                    return;
                }
                case 7: {
                    this.writeVarint64SevenBytes(v);
                    return;
                }
                case 8: {
                    this.writeVarint64EightBytes(v);
                    return;
                }
                case 9: {
                    this.writeVarint64NineBytes(v);
                    return;
                }
                case 10: {
                    this.writeVarint64TenBytes(v);
                }
            }
        }

        private void writeVarint64EightBytes(long v) {
            int v1 = this.pos;
            this.pos = v1 - 8;
            this.buffer.putLong(v1 - 7, v & 0x7FL | 0x80L | ((0xFE000000000000L & v) << 7 | (0x1FC0000000000L & v | 0x2000000000000L) << 6 | (0x3F800000000L & v | 0x40000000000L) << 5 | (0x7F0000000L & v | 0x800000000L) << 4 | (0xFE00000L & v | 0x10000000L) << 3 | (0x1FC000L & v | 0x200000L) << 2 | (0x3F80L & v | 0x4000L) << 1));
        }

        private void writeVarint64EightBytesWithSign(long v) {
            int v1 = this.pos;
            this.pos = v1 - 8;
            this.buffer.putLong(v1 - 7, v & 0x7FL | 0x80L | ((0xFE000000000000L & v | 0x100000000000000L) << 7 | (0x1FC0000000000L & v | 0x2000000000000L) << 6 | (0x3F800000000L & v | 0x40000000000L) << 5 | (0x7F0000000L & v | 0x800000000L) << 4 | (0xFE00000L & v | 0x10000000L) << 3 | (0x1FC000L & v | 0x200000L) << 2 | (0x3F80L & v | 0x4000L) << 1));
        }

        private void writeVarint64FiveBytes(long v) {
            int v1 = this.pos;
            this.pos = v1 - 5;
            this.buffer.putLong(v1 - 7, (v & 0x7FL | 0x80L) << 24 | ((0x7F0000000L & v) << 28 | (0xFE00000L & v | 0x10000000L) << 27 | (0x1FC000L & v | 0x200000L) << 26 | (0x3F80L & v | 0x4000L) << 25));
        }

        private void writeVarint64FourBytes(long v) {
            this.writeVarint32FourBytes(((int)v));
        }

        private void writeVarint64NineBytes(long v) {
            int v1 = this.pos;
            this.pos = v1 - 1;
            this.buffer.put(v1, ((byte)(((int)(v >>> 56)))));
            this.writeVarint64EightBytesWithSign(v & 0xFFFFFFFFFFFFFFL);
        }

        private void writeVarint64OneByte(long v) {
            this.writeVarint32OneByte(((int)v));
        }

        private void writeVarint64SevenBytes(long v) {
            int v1 = this.pos - 7;
            this.pos = v1;
            this.buffer.putLong(v1, (v & 0x7FL | 0x80L) << 8 | ((0x1FC0000000000L & v) << 14 | (0x3F800000000L & v | 0x40000000000L) << 13 | (0x7F0000000L & v | 0x800000000L) << 12 | (0xFE00000L & v | 0x10000000L) << 11 | (0x1FC000L & v | 0x200000L) << 10 | (0x3F80L & v | 0x4000L) << 9));
        }

        private void writeVarint64SixBytes(long v) {
            int v1 = this.pos;
            this.pos = v1 - 6;
            this.buffer.putLong(v1 - 7, (v & 0x7FL | 0x80L) << 16 | ((0x3F800000000L & v) << 21 | (0x7F0000000L & v | 0x800000000L) << 20 | (0xFE00000L & v | 0x10000000L) << 19 | (0x1FC000L & v | 0x200000L) << 18 | (0x3F80L & v | 0x4000L) << 17));
        }

        private void writeVarint64TenBytes(long v) {
            int v1 = this.pos;
            this.pos = v1 - 1;
            this.buffer.put(v1, ((byte)(((int)(v >>> 0x3F)))));
            int v2 = this.pos;
            this.pos = v2 - 1;
            this.buffer.put(v2, ((byte)(((int)(v >>> 56 & 0x7FL | 0x80L)))));
            this.writeVarint64EightBytesWithSign(v & 0xFFFFFFFFFFFFFFL);
        }

        private void writeVarint64ThreeBytes(long v) {
            this.writeVarint32ThreeBytes(((int)v));
        }

        private void writeVarint64TwoBytes(long v) {
            this.writeVarint32TwoBytes(((int)v));
        }
    }

    static final class SafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private int limit;
        private int limitMinusOne;
        private int offset;
        private int offsetMinusOne;
        private int pos;

        SafeHeapWriter(BufferAllocator bufferAllocator0, int v) {
            super(bufferAllocator0, v, null);
            this.nextBuffer();
        }

        int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void finishCurrentBuffer() {
            if(this.allocatedBuffer != null) {
                this.totalDoneBytes += this.bytesWrittenToCurrentBuffer();
                this.allocatedBuffer.position(this.pos - this.allocatedBuffer.arrayOffset() + 1);
                this.allocatedBuffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + this.bytesWrittenToCurrentBuffer();
        }

        private void nextBuffer() {
            this.nextBuffer(this.newHeapBuffer());
        }

        private void nextBuffer(int v) {
            this.nextBuffer(this.newHeapBuffer(v));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer0) {
            if(!allocatedBuffer0.hasArray()) {
                throw new RuntimeException("Allocator returned non-heap buffer");
            }
            this.finishCurrentBuffer();
            this.buffers.addFirst(allocatedBuffer0);
            this.allocatedBuffer = allocatedBuffer0;
            this.buffer = allocatedBuffer0.array();
            int v = allocatedBuffer0.arrayOffset();
            this.limit = allocatedBuffer0.limit() + v;
            int v1 = v + allocatedBuffer0.position();
            this.offset = v1;
            this.offsetMinusOne = v1 - 1;
            this.limitMinusOne = this.limit - 1;
            this.pos = this.limit - 1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void requireSpace(int v) {
            if(this.spaceLeft() < v) {
                this.nextBuffer(v);
            }
        }

        int spaceLeft() {
            return this.pos - this.offsetMinusOne;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b) {
            int v = this.pos;
            this.pos = v - 1;
            this.buffer[v] = b;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            if(this.spaceLeft() < v) {
                this.nextBuffer(v);
            }
            int v1 = this.pos - v;
            this.pos = v1;
            byteBuffer0.get(this.buffer, v1 + 1, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] arr_b, int v, int v1) {
            if(this.spaceLeft() < v1) {
                this.nextBuffer(v1);
            }
            int v2 = this.pos - v1;
            this.pos = v2;
            System.arraycopy(arr_b, v, this.buffer, v2 + 1, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int v, boolean z) throws IOException {
            this.requireSpace(6);
            this.write(((byte)z));
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeBool(boolean z) {
            this.write(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int v, ByteString byteString0) throws IOException {
            try {
                byteString0.writeToReverse(this);
            }
            catch(IOException iOException0) {
                throw new RuntimeException(iOException0);
            }
            this.requireSpace(10);
            this.writeVarint32(byteString0.size());
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeEndGroup(int v) {
            this.writeTag(v, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed32(int v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(v >> 24 & 0xFF);
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(v >> 16 & 0xFF);
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(v >> 8 & 0xFF);
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(v & 0xFF);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int v, int v1) throws IOException {
            this.requireSpace(9);
            this.writeFixed32(v1);
            this.writeTag(v, 5);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int v, long v1) throws IOException {
            this.requireSpace(13);
            this.writeFixed64(v1);
            this.writeTag(v, 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed64(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >> 56)) & 0xFF);
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >> 0x30)) & 0xFF);
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >> 40)) & 0xFF);
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v >> 0x20)) & 0xFF);
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(((int)(v >> 24)) & 0xFF);
            this.pos = v1 - 6;
            arr_b[v1 - 5] = (byte)(((int)(v >> 16)) & 0xFF);
            this.pos = v1 - 7;
            arr_b[v1 - 6] = (byte)(((int)(v >> 8)) & 0xFF);
            this.pos = v1 - 8;
            arr_b[v1 - 7] = (byte)(((int)v) & 0xFF);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        @Deprecated
        public void writeGroup(int v, Object object0) throws IOException {
            this.writeTag(v, 4);
            Protobuf.getInstance().writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int v, Object object0, Schema schema0) throws IOException {
            this.writeTag(v, 4);
            schema0.writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeInt32(int v) {
            if(v >= 0) {
                this.writeVarint32(v);
                return;
            }
            this.writeVarint64(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int v, int v1) throws IOException {
            this.requireSpace(15);
            this.writeInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            if(this.spaceLeft() < v) {
                this.totalDoneBytes += v;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer0));
                this.nextBuffer();
            }
            int v1 = this.pos - v;
            this.pos = v1;
            byteBuffer0.get(this.buffer, v1 + 1, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] arr_b, int v, int v1) {
            if(this.spaceLeft() < v1) {
                this.totalDoneBytes += v1;
                this.buffers.addFirst(AllocatedBuffer.wrap(arr_b, v, v1));
                this.nextBuffer();
                return;
            }
            int v2 = this.pos - v1;
            this.pos = v2;
            System.arraycopy(arr_b, v, this.buffer, v2 + 1, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            Protobuf.getInstance().writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0, Schema schema0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            schema0.writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt32(int v) {
            this.writeVarint32(v >> 0x1F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int v, int v1) throws IOException {
            this.requireSpace(10);
            this.writeSInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int v, long v1) throws IOException {
            this.requireSpace(15);
            this.writeSInt64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt64(long v) {
            this.writeVarint64(v >> 0x3F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeStartGroup(int v) {
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int v, String s) throws IOException {
            int v1 = this.getTotalBytesWritten();
            this.writeString(s);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeString(String s) {
            this.requireSpace(s.length());
            int v = s.length() - 1;
            this.pos -= v;
            while(v >= 0) {
                int v1 = s.charAt(v);
                if(v1 >= 0x80) {
                    break;
                }
                this.buffer[this.pos + v] = (byte)v1;
                --v;
            }
            if(v == -1) {
                --this.pos;
                return;
            }
            this.pos += v;
            while(v >= 0) {
                int v2 = s.charAt(v);
                if(v2 < 0x80) {
                    int v3 = this.pos;
                    if(v3 > this.offsetMinusOne) {
                        this.pos = v3 - 1;
                        this.buffer[v3] = (byte)v2;
                    }
                }
                else {
                    if(v2 < 0x800) {
                        int v4 = this.pos;
                        if(v4 > this.offset) {
                            byte[] arr_b = this.buffer;
                            this.pos = v4 - 1;
                            arr_b[v4] = (byte)(v2 & 0x3F | 0x80);
                            this.pos = v4 - 2;
                            arr_b[v4 - 1] = (byte)(v2 >>> 6 | 960);
                            goto label_61;
                        }
                    }
                    if(v2 < 0xD800 || 0xDFFF < v2) {
                        int v5 = this.pos;
                        if(v5 > this.offset + 1) {
                            byte[] arr_b1 = this.buffer;
                            this.pos = v5 - 1;
                            arr_b1[v5] = (byte)(v2 & 0x3F | 0x80);
                            this.pos = v5 - 2;
                            arr_b1[v5 - 1] = (byte)(v2 >>> 6 & 0x3F | 0x80);
                            this.pos = v5 - 3;
                            arr_b1[v5 - 2] = (byte)(v2 >>> 12 | 480);
                            goto label_61;
                        }
                    }
                    if(this.pos > this.offset + 2) {
                        if(v != 0) {
                            int v6 = s.charAt(v - 1);
                            if(Character.isSurrogatePair(((char)v6), ((char)v2))) {
                                --v;
                                int v7 = Character.toCodePoint(((char)v6), ((char)v2));
                                byte[] arr_b2 = this.buffer;
                                int v8 = this.pos;
                                this.pos = v8 - 1;
                                arr_b2[v8] = (byte)(v7 & 0x3F | 0x80);
                                this.pos = v8 - 2;
                                arr_b2[v8 - 1] = (byte)(v7 >>> 6 & 0x3F | 0x80);
                                this.pos = v8 - 3;
                                arr_b2[v8 - 2] = (byte)(v7 >>> 12 & 0x3F | 0x80);
                                this.pos = v8 - 4;
                                arr_b2[v8 - 3] = (byte)(v7 >>> 18 | 0xF0);
                                goto label_61;
                            }
                        }
                        throw new UnpairedSurrogateException(v - 1, v);
                    }
                    else {
                        this.requireSpace(v);
                        ++v;
                    }
                }
            label_61:
                --v;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int v, int v1) {
            this.writeVarint32(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int v, int v1) throws IOException {
            this.requireSpace(10);
            this.writeVarint32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int v, long v1) throws IOException {
            this.requireSpace(15);
            this.writeVarint64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint32(int v) {
            if((v & 0xFFFFFF80) == 0) {
                this.writeVarint32OneByte(v);
                return;
            }
            if((v & 0xFFFFC000) == 0) {
                this.writeVarint32TwoBytes(v);
                return;
            }
            if((0xFFE00000 & v) == 0) {
                this.writeVarint32ThreeBytes(v);
                return;
            }
            if((0xF0000000 & v) == 0) {
                this.writeVarint32FourBytes(v);
                return;
            }
            this.writeVarint32FiveBytes(v);
        }

        private void writeVarint32FiveBytes(int v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(v >>> 28);
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(v >>> 21 & 0x7F | 0x80);
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(v >>> 14 & 0x7F | 0x80);
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(v >>> 7 & 0x7F | 0x80);
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(v & 0x7F | 0x80);
        }

        private void writeVarint32FourBytes(int v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(v >>> 21);
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(v >>> 14 & 0x7F | 0x80);
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(v >>> 7 & 0x7F | 0x80);
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(v & 0x7F | 0x80);
        }

        private void writeVarint32OneByte(int v) {
            int v1 = this.pos;
            this.pos = v1 - 1;
            this.buffer[v1] = (byte)v;
        }

        private void writeVarint32ThreeBytes(int v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(v >>> 14);
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(v >>> 7 & 0x7F | 0x80);
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(v & 0x7F | 0x80);
        }

        private void writeVarint32TwoBytes(int v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(v >>> 7);
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(v & 0x7F | 0x80);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint64(long v) {
            switch(BinaryWriter.computeUInt64SizeNoTag(v)) {
                case 1: {
                    this.writeVarint64OneByte(v);
                    return;
                }
                case 2: {
                    this.writeVarint64TwoBytes(v);
                    return;
                }
                case 3: {
                    this.writeVarint64ThreeBytes(v);
                    return;
                }
                case 4: {
                    this.writeVarint64FourBytes(v);
                    return;
                }
                case 5: {
                    this.writeVarint64FiveBytes(v);
                    return;
                }
                case 6: {
                    this.writeVarint64SixBytes(v);
                    return;
                }
                case 7: {
                    this.writeVarint64SevenBytes(v);
                    return;
                }
                case 8: {
                    this.writeVarint64EightBytes(v);
                    return;
                }
                case 9: {
                    this.writeVarint64NineBytes(v);
                    return;
                }
                case 10: {
                    this.writeVarint64TenBytes(v);
                }
            }
        }

        private void writeVarint64EightBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 49)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 42 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >>> 35 & 0x7FL | 0x80L)));
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v >>> 28 & 0x7FL | 0x80L)));
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(((int)(v >>> 21 & 0x7FL | 0x80L)));
            this.pos = v1 - 6;
            arr_b[v1 - 5] = (byte)(((int)(v >>> 14 & 0x7FL | 0x80L)));
            this.pos = v1 - 7;
            arr_b[v1 - 6] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 8;
            arr_b[v1 - 7] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64FiveBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 28)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 21 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >>> 14 & 0x7FL | 0x80L)));
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64FourBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 21)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 14 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64NineBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 56)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 49 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >>> 42 & 0x7FL | 0x80L)));
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v >>> 35 & 0x7FL | 0x80L)));
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(((int)(v >>> 28 & 0x7FL | 0x80L)));
            this.pos = v1 - 6;
            arr_b[v1 - 5] = (byte)(((int)(v >>> 21 & 0x7FL | 0x80L)));
            this.pos = v1 - 7;
            arr_b[v1 - 6] = (byte)(((int)(v >>> 14 & 0x7FL | 0x80L)));
            this.pos = v1 - 8;
            arr_b[v1 - 7] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 9;
            arr_b[v1 - 8] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64OneByte(long v) {
            int v1 = this.pos;
            this.pos = v1 - 1;
            this.buffer[v1] = (byte)(((int)v));
        }

        private void writeVarint64SevenBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 42)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 35 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >>> 28 & 0x7FL | 0x80L)));
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v >>> 21 & 0x7FL | 0x80L)));
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(((int)(v >>> 14 & 0x7FL | 0x80L)));
            this.pos = v1 - 6;
            arr_b[v1 - 5] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 7;
            arr_b[v1 - 6] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64SixBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 35)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 28 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >>> 21 & 0x7FL | 0x80L)));
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v >>> 14 & 0x7FL | 0x80L)));
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 6;
            arr_b[v1 - 5] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64TenBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 0x3F)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 56 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v >>> 49 & 0x7FL | 0x80L)));
            this.pos = v1 - 4;
            arr_b[v1 - 3] = (byte)(((int)(v >>> 42 & 0x7FL | 0x80L)));
            this.pos = v1 - 5;
            arr_b[v1 - 4] = (byte)(((int)(v >>> 35 & 0x7FL | 0x80L)));
            this.pos = v1 - 6;
            arr_b[v1 - 5] = (byte)(((int)(v >>> 28 & 0x7FL | 0x80L)));
            this.pos = v1 - 7;
            arr_b[v1 - 6] = (byte)(((int)(v >>> 21 & 0x7FL | 0x80L)));
            this.pos = v1 - 8;
            arr_b[v1 - 7] = (byte)(((int)(v >>> 14 & 0x7FL | 0x80L)));
            this.pos = v1 - 9;
            arr_b[v1 - 8] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 10;
            arr_b[v1 - 9] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64ThreeBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)v) >>> 14);
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)(v >>> 7 & 0x7FL | 0x80L)));
            this.pos = v1 - 3;
            arr_b[v1 - 2] = (byte)(((int)(v & 0x7FL | 0x80L)));
        }

        private void writeVarint64TwoBytes(long v) {
            byte[] arr_b = this.buffer;
            int v1 = this.pos;
            this.pos = v1 - 1;
            arr_b[v1] = (byte)(((int)(v >>> 7)));
            this.pos = v1 - 2;
            arr_b[v1 - 1] = (byte)(((int)v) & 0x7F | 0x80);
        }
    }

    static final class UnsafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private long bufferOffset;
        private long limitMinusOne;
        private long pos;

        UnsafeDirectWriter(BufferAllocator bufferAllocator0, int v) {
            super(bufferAllocator0, v, null);
            this.nextBuffer();
        }

        // 去混淆评级： 低(20)
        static boolean access$000() [...] // 潜在的解密器

        private int bufferPos() {
            return (int)(this.pos - this.bufferOffset);
        }

        private int bytesWrittenToCurrentBuffer() {
            return (int)(this.limitMinusOne - this.pos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void finishCurrentBuffer() {
            if(this.buffer != null) {
                this.totalDoneBytes += this.bytesWrittenToCurrentBuffer();
                this.buffer.position(this.bufferPos() + 1);
                this.buffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + this.bytesWrittenToCurrentBuffer();
        }

        // 去混淆评级： 低(20)
        private static boolean isSupported() [...] // 潜在的解密器

        private void nextBuffer() {
            this.nextBuffer(this.newDirectBuffer());
        }

        private void nextBuffer(int v) {
            this.nextBuffer(this.newDirectBuffer(v));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer0) {
            if(!allocatedBuffer0.hasNioBuffer()) {
                throw new RuntimeException("Allocated buffer does not have NIO buffer");
            }
            ByteBuffer byteBuffer0 = allocatedBuffer0.nioBuffer();
            if(!byteBuffer0.isDirect()) {
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            this.finishCurrentBuffer();
            this.buffers.addFirst(allocatedBuffer0);
            this.buffer = byteBuffer0;
            byteBuffer0.limit(byteBuffer0.capacity());
            this.buffer.position(0);
            long v = UnsafeUtil.addressOffset(this.buffer);
            this.bufferOffset = v;
            long v1 = v + ((long)(this.buffer.limit() - 1));
            this.limitMinusOne = v1;
            this.pos = v1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void requireSpace(int v) {
            if(this.spaceLeft() < v) {
                this.nextBuffer(v);
            }
        }

        private int spaceLeft() {
            return this.bufferPos() + 1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b) {
            long v = this.pos;
            this.pos = v - 1L;
            UnsafeUtil.putByte(v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            if(this.spaceLeft() < v) {
                this.nextBuffer(v);
            }
            this.pos -= (long)v;
            this.buffer.position(this.bufferPos() + 1);
            this.buffer.put(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] arr_b, int v, int v1) {
            if(this.spaceLeft() < v1) {
                this.nextBuffer(v1);
            }
            this.pos -= (long)v1;
            this.buffer.position(this.bufferPos() + 1);
            this.buffer.put(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int v, boolean z) {
            this.requireSpace(6);
            this.write(((byte)z));
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeBool(boolean z) {
            this.write(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int v, ByteString byteString0) {
            try {
                byteString0.writeToReverse(this);
            }
            catch(IOException iOException0) {
                throw new RuntimeException(iOException0);
            }
            this.requireSpace(10);
            this.writeVarint32(byteString0.size());
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        @Deprecated
        public void writeEndGroup(int v) {
            this.writeTag(v, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed32(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(v >> 24 & 0xFF)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(v >> 16 & 0xFF)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(v >> 8 & 0xFF)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(v & 0xFF)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int v, int v1) {
            this.requireSpace(9);
            this.writeFixed32(v1);
            this.writeTag(v, 5);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int v, long v1) {
            this.requireSpace(13);
            this.writeFixed64(v1);
            this.writeTag(v, 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed64(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >> 56)) & 0xFF)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >> 0x30)) & 0xFF)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >> 40)) & 0xFF)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v >> 0x20)) & 0xFF)));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(((int)(v >> 24)) & 0xFF)));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(v6, ((byte)(((int)(v >> 16)) & 0xFF)));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(v7, ((byte)(((int)(v >> 8)) & 0xFF)));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(v8, ((byte)(((int)v) & 0xFF)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int v, Object object0) throws IOException {
            this.writeTag(v, 4);
            Protobuf.getInstance().writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int v, Object object0, Schema schema0) throws IOException {
            this.writeTag(v, 4);
            schema0.writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeInt32(int v) {
            if(v >= 0) {
                this.writeVarint32(v);
                return;
            }
            this.writeVarint64(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int v, int v1) {
            this.requireSpace(15);
            this.writeInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            if(this.spaceLeft() < v) {
                this.totalDoneBytes += v;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer0));
                this.nextBuffer();
                return;
            }
            this.pos -= (long)v;
            this.buffer.position(this.bufferPos() + 1);
            this.buffer.put(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] arr_b, int v, int v1) {
            if(this.spaceLeft() < v1) {
                this.totalDoneBytes += v1;
                this.buffers.addFirst(AllocatedBuffer.wrap(arr_b, v, v1));
                this.nextBuffer();
                return;
            }
            this.pos -= (long)v1;
            this.buffer.position(this.bufferPos() + 1);
            this.buffer.put(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            Protobuf.getInstance().writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0, Schema schema0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            schema0.writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt32(int v) {
            this.writeVarint32(v >> 0x1F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int v, int v1) {
            this.requireSpace(10);
            this.writeSInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int v, long v1) {
            this.requireSpace(15);
            this.writeSInt64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt64(long v) {
            this.writeVarint64(v >> 0x3F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        @Deprecated
        public void writeStartGroup(int v) {
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int v, String s) {
            int v1 = this.getTotalBytesWritten();
            this.writeString(s);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeString(String s) {
            this.requireSpace(s.length());
            int v;
            for(v = s.length() - 1; v >= 0; --v) {
                int v1 = s.charAt(v);
                if(v1 >= 0x80) {
                    break;
                }
                long v2 = this.pos;
                this.pos = v2 - 1L;
                UnsafeUtil.putByte(v2, ((byte)v1));
            }
            if(v != -1) {
                while(v >= 0) {
                    int v3 = s.charAt(v);
                    if(v3 < 0x80) {
                        long v4 = this.pos;
                        if(v4 >= this.bufferOffset) {
                            this.pos = v4 - 1L;
                            UnsafeUtil.putByte(v4, ((byte)v3));
                        }
                    }
                    else {
                        if(v3 < 0x800) {
                            long v5 = this.pos;
                            if(v5 > this.bufferOffset) {
                                this.pos = v5 - 1L;
                                UnsafeUtil.putByte(v5, ((byte)(v3 & 0x3F | 0x80)));
                                long v6 = this.pos;
                                this.pos = v6 - 1L;
                                UnsafeUtil.putByte(v6, ((byte)(v3 >>> 6 | 960)));
                                goto label_62;
                            }
                        }
                        if(v3 < 0xD800 || 0xDFFF < v3) {
                            long v7 = this.pos;
                            if(v7 > this.bufferOffset + 1L) {
                                this.pos = v7 - 1L;
                                UnsafeUtil.putByte(v7, ((byte)(v3 & 0x3F | 0x80)));
                                long v8 = this.pos;
                                this.pos = v8 - 1L;
                                UnsafeUtil.putByte(v8, ((byte)(v3 >>> 6 & 0x3F | 0x80)));
                                long v9 = this.pos;
                                this.pos = v9 - 1L;
                                UnsafeUtil.putByte(v9, ((byte)(v3 >>> 12 | 480)));
                                goto label_62;
                            }
                        }
                        if(this.pos > this.bufferOffset + 2L) {
                            if(v != 0) {
                                int v10 = s.charAt(v - 1);
                                if(Character.isSurrogatePair(((char)v10), ((char)v3))) {
                                    --v;
                                    int v11 = Character.toCodePoint(((char)v10), ((char)v3));
                                    long v12 = this.pos;
                                    this.pos = v12 - 1L;
                                    UnsafeUtil.putByte(v12, ((byte)(v11 & 0x3F | 0x80)));
                                    long v13 = this.pos;
                                    this.pos = v13 - 1L;
                                    UnsafeUtil.putByte(v13, ((byte)(v11 >>> 6 & 0x3F | 0x80)));
                                    long v14 = this.pos;
                                    this.pos = v14 - 1L;
                                    UnsafeUtil.putByte(v14, ((byte)(v11 >>> 12 & 0x3F | 0x80)));
                                    long v15 = this.pos;
                                    this.pos = v15 - 1L;
                                    UnsafeUtil.putByte(v15, ((byte)(v11 >>> 18 | 0xF0)));
                                    goto label_62;
                                }
                            }
                            throw new UnpairedSurrogateException(v - 1, v);
                        }
                        else {
                            this.requireSpace(v);
                            ++v;
                        }
                    }
                label_62:
                    --v;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int v, int v1) {
            this.writeVarint32(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int v, int v1) {
            this.requireSpace(10);
            this.writeVarint32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int v, long v1) {
            this.requireSpace(15);
            this.writeVarint64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint32(int v) {
            if((v & 0xFFFFFF80) == 0) {
                this.writeVarint32OneByte(v);
                return;
            }
            if((v & 0xFFFFC000) == 0) {
                this.writeVarint32TwoBytes(v);
                return;
            }
            if((0xFFE00000 & v) == 0) {
                this.writeVarint32ThreeBytes(v);
                return;
            }
            if((0xF0000000 & v) == 0) {
                this.writeVarint32FourBytes(v);
                return;
            }
            this.writeVarint32FiveBytes(v);
        }

        private void writeVarint32FiveBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(v >>> 28)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(v >>> 21 & 0x7F | 0x80)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(v >>> 14 & 0x7F | 0x80)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(v >>> 7 & 0x7F | 0x80)));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(v & 0x7F | 0x80)));
        }

        private void writeVarint32FourBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(v >>> 21)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(v >>> 14 & 0x7F | 0x80)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(v >>> 7 & 0x7F | 0x80)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(v & 0x7F | 0x80)));
        }

        private void writeVarint32OneByte(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)v));
        }

        private void writeVarint32ThreeBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(v >>> 14)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(v >>> 7 & 0x7F | 0x80)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(v & 0x7F | 0x80)));
        }

        private void writeVarint32TwoBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(v >>> 7)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(v & 0x7F | 0x80)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint64(long v) {
            switch(BinaryWriter.computeUInt64SizeNoTag(v)) {
                case 1: {
                    this.writeVarint64OneByte(v);
                    return;
                }
                case 2: {
                    this.writeVarint64TwoBytes(v);
                    return;
                }
                case 3: {
                    this.writeVarint64ThreeBytes(v);
                    return;
                }
                case 4: {
                    this.writeVarint64FourBytes(v);
                    return;
                }
                case 5: {
                    this.writeVarint64FiveBytes(v);
                    return;
                }
                case 6: {
                    this.writeVarint64SixBytes(v);
                    return;
                }
                case 7: {
                    this.writeVarint64SevenBytes(v);
                    return;
                }
                case 8: {
                    this.writeVarint64EightBytes(v);
                    return;
                }
                case 9: {
                    this.writeVarint64NineBytes(v);
                    return;
                }
                case 10: {
                    this.writeVarint64TenBytes(v);
                }
            }
        }

        private void writeVarint64EightBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 49)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 42 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(v6, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(v7, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(v8, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64FiveBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 28)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64FourBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 21)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64NineBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 56)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 49 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >>> 42 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(v6, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(v7, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(v8, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v9 = this.pos;
            this.pos = v9 - 1L;
            UnsafeUtil.putByte(v9, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64OneByte(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)v))));
        }

        private void writeVarint64SevenBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 42)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(v6, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(v7, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64SixBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 35)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(v6, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64TenBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 0x3F)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 56 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v >>> 49 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(v4, ((byte)(((int)(v >>> 42 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(v5, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(v6, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(v7, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(v8, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v9 = this.pos;
            this.pos = v9 - 1L;
            UnsafeUtil.putByte(v9, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v10 = this.pos;
            this.pos = v10 - 1L;
            UnsafeUtil.putByte(v10, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64ThreeBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)v) >>> 14)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(v3, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64TwoBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(v1, ((byte)(((int)(v >>> 7)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(v2, ((byte)(((int)v) & 0x7F | 0x80)));
        }
    }

    static final class UnsafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private long limit;
        private long limitMinusOne;
        private long offset;
        private long offsetMinusOne;
        private long pos;

        UnsafeHeapWriter(BufferAllocator bufferAllocator0, int v) {
            super(bufferAllocator0, v, null);
            this.nextBuffer();
        }

        private int arrayPos() {
            return (int)this.pos;
        }

        int bytesWrittenToCurrentBuffer() {
            return (int)(this.limitMinusOne - this.pos);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void finishCurrentBuffer() {
            if(this.allocatedBuffer != null) {
                this.totalDoneBytes += this.bytesWrittenToCurrentBuffer();
                this.allocatedBuffer.position(this.arrayPos() - this.allocatedBuffer.arrayOffset() + 1);
                this.allocatedBuffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + this.bytesWrittenToCurrentBuffer();
        }

        // 去混淆评级： 低(20)
        static boolean isSupported() [...] // 潜在的解密器

        private void nextBuffer() {
            this.nextBuffer(this.newHeapBuffer());
        }

        private void nextBuffer(int v) {
            this.nextBuffer(this.newHeapBuffer(v));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer0) {
            if(!allocatedBuffer0.hasArray()) {
                throw new RuntimeException("Allocator returned non-heap buffer");
            }
            this.finishCurrentBuffer();
            this.buffers.addFirst(allocatedBuffer0);
            this.allocatedBuffer = allocatedBuffer0;
            this.buffer = allocatedBuffer0.array();
            long v = (long)allocatedBuffer0.arrayOffset();
            this.limit = ((long)allocatedBuffer0.limit()) + v;
            long v1 = v + ((long)allocatedBuffer0.position());
            this.offset = v1;
            this.offsetMinusOne = v1 - 1L;
            this.limitMinusOne = this.limit - 1L;
            this.pos = this.limit - 1L;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void requireSpace(int v) {
            if(this.spaceLeft() < v) {
                this.nextBuffer(v);
            }
        }

        int spaceLeft() {
            return (int)(this.pos - this.offsetMinusOne);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b) {
            long v = this.pos;
            this.pos = v - 1L;
            UnsafeUtil.putByte(this.buffer, v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            this.requireSpace(v);
            this.pos -= (long)v;
            byteBuffer0.get(this.buffer, this.arrayPos() + 1, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] arr_b, int v, int v1) {
            if(v < 0 || v + v1 > arr_b.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", ((int)arr_b.length), v, v1));
            }
            this.requireSpace(v1);
            this.pos -= (long)v1;
            System.arraycopy(arr_b, v, this.buffer, this.arrayPos() + 1, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int v, boolean z) {
            this.requireSpace(6);
            this.write(((byte)z));
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeBool(boolean z) {
            this.write(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int v, ByteString byteString0) {
            try {
                byteString0.writeToReverse(this);
            }
            catch(IOException iOException0) {
                throw new RuntimeException(iOException0);
            }
            this.requireSpace(10);
            this.writeVarint32(byteString0.size());
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeEndGroup(int v) {
            this.writeTag(v, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed32(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(v >> 24 & 0xFF)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(v >> 16 & 0xFF)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(v >> 8 & 0xFF)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(v & 0xFF)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int v, int v1) {
            this.requireSpace(9);
            this.writeFixed32(v1);
            this.writeTag(v, 5);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int v, long v1) {
            this.requireSpace(13);
            this.writeFixed64(v1);
            this.writeTag(v, 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeFixed64(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >> 56)) & 0xFF)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >> 0x30)) & 0xFF)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >> 40)) & 0xFF)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v >> 0x20)) & 0xFF)));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(((int)(v >> 24)) & 0xFF)));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(this.buffer, v6, ((byte)(((int)(v >> 16)) & 0xFF)));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(this.buffer, v7, ((byte)(((int)(v >> 8)) & 0xFF)));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(this.buffer, v8, ((byte)(((int)v) & 0xFF)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int v, Object object0) throws IOException {
            this.writeTag(v, 4);
            Protobuf.getInstance().writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int v, Object object0, Schema schema0) throws IOException {
            this.writeTag(v, 4);
            schema0.writeTo(object0, this);
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeInt32(int v) {
            if(v >= 0) {
                this.writeVarint32(v);
                return;
            }
            this.writeVarint64(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int v, int v1) {
            this.requireSpace(15);
            this.writeInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer0) {
            int v = byteBuffer0.remaining();
            if(this.spaceLeft() < v) {
                this.totalDoneBytes += v;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer0));
                this.nextBuffer();
            }
            this.pos -= (long)v;
            byteBuffer0.get(this.buffer, this.arrayPos() + 1, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] arr_b, int v, int v1) {
            if(v < 0 || v + v1 > arr_b.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", ((int)arr_b.length), v, v1));
            }
            if(this.spaceLeft() < v1) {
                this.totalDoneBytes += v1;
                this.buffers.addFirst(AllocatedBuffer.wrap(arr_b, v, v1));
                this.nextBuffer();
                return;
            }
            this.pos -= (long)v1;
            System.arraycopy(arr_b, v, this.buffer, this.arrayPos() + 1, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            Protobuf.getInstance().writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int v, Object object0, Schema schema0) throws IOException {
            int v1 = this.getTotalBytesWritten();
            schema0.writeTo(object0, this);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt32(int v) {
            this.writeVarint32(v >> 0x1F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int v, int v1) {
            this.requireSpace(10);
            this.writeSInt32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int v, long v1) {
            this.requireSpace(15);
            this.writeSInt64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeSInt64(long v) {
            this.writeVarint64(v >> 0x3F ^ v << 1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeStartGroup(int v) {
            this.writeTag(v, 3);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int v, String s) {
            int v1 = this.getTotalBytesWritten();
            this.writeString(s);
            int v2 = this.getTotalBytesWritten();
            this.requireSpace(10);
            this.writeVarint32(v2 - v1);
            this.writeTag(v, 2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeString(String s) {
            this.requireSpace(s.length());
            int v;
            for(v = s.length() - 1; v >= 0; --v) {
                int v1 = s.charAt(v);
                if(v1 >= 0x80) {
                    break;
                }
                long v2 = this.pos;
                this.pos = v2 - 1L;
                UnsafeUtil.putByte(this.buffer, v2, ((byte)v1));
            }
            if(v != -1) {
                while(v >= 0) {
                    int v3 = s.charAt(v);
                    if(v3 < 0x80) {
                        long v4 = this.pos;
                        if(v4 > this.offsetMinusOne) {
                            this.pos = v4 - 1L;
                            UnsafeUtil.putByte(this.buffer, v4, ((byte)v3));
                        }
                    }
                    else {
                        if(v3 < 0x800) {
                            long v5 = this.pos;
                            if(v5 > this.offset) {
                                this.pos = v5 - 1L;
                                UnsafeUtil.putByte(this.buffer, v5, ((byte)(v3 & 0x3F | 0x80)));
                                long v6 = this.pos;
                                this.pos = v6 - 1L;
                                UnsafeUtil.putByte(this.buffer, v6, ((byte)(v3 >>> 6 | 960)));
                                goto label_62;
                            }
                        }
                        if(v3 < 0xD800 || 0xDFFF < v3) {
                            long v7 = this.pos;
                            if(v7 > this.offset + 1L) {
                                this.pos = v7 - 1L;
                                UnsafeUtil.putByte(this.buffer, v7, ((byte)(v3 & 0x3F | 0x80)));
                                long v8 = this.pos;
                                this.pos = v8 - 1L;
                                UnsafeUtil.putByte(this.buffer, v8, ((byte)(v3 >>> 6 & 0x3F | 0x80)));
                                long v9 = this.pos;
                                this.pos = v9 - 1L;
                                UnsafeUtil.putByte(this.buffer, v9, ((byte)(v3 >>> 12 | 480)));
                                goto label_62;
                            }
                        }
                        if(this.pos > this.offset + 2L) {
                            if(v != 0) {
                                int v10 = s.charAt(v - 1);
                                if(Character.isSurrogatePair(((char)v10), ((char)v3))) {
                                    --v;
                                    int v11 = Character.toCodePoint(((char)v10), ((char)v3));
                                    long v12 = this.pos;
                                    this.pos = v12 - 1L;
                                    UnsafeUtil.putByte(this.buffer, v12, ((byte)(v11 & 0x3F | 0x80)));
                                    long v13 = this.pos;
                                    this.pos = v13 - 1L;
                                    UnsafeUtil.putByte(this.buffer, v13, ((byte)(v11 >>> 6 & 0x3F | 0x80)));
                                    long v14 = this.pos;
                                    this.pos = v14 - 1L;
                                    UnsafeUtil.putByte(this.buffer, v14, ((byte)(v11 >>> 12 & 0x3F | 0x80)));
                                    long v15 = this.pos;
                                    this.pos = v15 - 1L;
                                    UnsafeUtil.putByte(this.buffer, v15, ((byte)(v11 >>> 18 | 0xF0)));
                                    goto label_62;
                                }
                            }
                            throw new UnpairedSurrogateException(v - 1, v);
                        }
                        else {
                            this.requireSpace(v);
                            ++v;
                        }
                    }
                label_62:
                    --v;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int v, int v1) {
            this.writeVarint32(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int v, int v1) {
            this.requireSpace(10);
            this.writeVarint32(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int v, long v1) {
            this.requireSpace(15);
            this.writeVarint64(v1);
            this.writeTag(v, 0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint32(int v) {
            if((v & 0xFFFFFF80) == 0) {
                this.writeVarint32OneByte(v);
                return;
            }
            if((v & 0xFFFFC000) == 0) {
                this.writeVarint32TwoBytes(v);
                return;
            }
            if((0xFFE00000 & v) == 0) {
                this.writeVarint32ThreeBytes(v);
                return;
            }
            if((0xF0000000 & v) == 0) {
                this.writeVarint32FourBytes(v);
                return;
            }
            this.writeVarint32FiveBytes(v);
        }

        private void writeVarint32FiveBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(v >>> 28)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(v >>> 21 & 0x7F | 0x80)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(v >>> 14 & 0x7F | 0x80)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(v >>> 7 & 0x7F | 0x80)));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(v & 0x7F | 0x80)));
        }

        private void writeVarint32FourBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(v >>> 21)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(v >>> 14 & 0x7F | 0x80)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(v >>> 7 & 0x7F | 0x80)));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(v & 0x7F | 0x80)));
        }

        private void writeVarint32OneByte(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)v));
        }

        private void writeVarint32ThreeBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(v >>> 14)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(v >>> 7 & 0x7F | 0x80)));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(v & 0x7F | 0x80)));
        }

        private void writeVarint32TwoBytes(int v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(v >>> 7)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(v & 0x7F | 0x80)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeVarint64(long v) {
            switch(BinaryWriter.computeUInt64SizeNoTag(v)) {
                case 1: {
                    this.writeVarint64OneByte(v);
                    return;
                }
                case 2: {
                    this.writeVarint64TwoBytes(v);
                    return;
                }
                case 3: {
                    this.writeVarint64ThreeBytes(v);
                    return;
                }
                case 4: {
                    this.writeVarint64FourBytes(v);
                    return;
                }
                case 5: {
                    this.writeVarint64FiveBytes(v);
                    return;
                }
                case 6: {
                    this.writeVarint64SixBytes(v);
                    return;
                }
                case 7: {
                    this.writeVarint64SevenBytes(v);
                    return;
                }
                case 8: {
                    this.writeVarint64EightBytes(v);
                    return;
                }
                case 9: {
                    this.writeVarint64NineBytes(v);
                    return;
                }
                case 10: {
                    this.writeVarint64TenBytes(v);
                }
            }
        }

        private void writeVarint64EightBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 49)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 42 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(this.buffer, v6, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(this.buffer, v7, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(this.buffer, v8, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64FiveBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 28)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64FourBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 21)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64NineBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 56)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 49 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >>> 42 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(this.buffer, v6, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(this.buffer, v7, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(this.buffer, v8, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v9 = this.pos;
            this.pos = v9 - 1L;
            UnsafeUtil.putByte(this.buffer, v9, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64OneByte(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)v))));
        }

        private void writeVarint64SevenBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 42)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(this.buffer, v6, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(this.buffer, v7, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64SixBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 35)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(this.buffer, v6, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64TenBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 0x3F)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 56 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v >>> 49 & 0x7FL | 0x80L)))));
            long v4 = this.pos;
            this.pos = v4 - 1L;
            UnsafeUtil.putByte(this.buffer, v4, ((byte)(((int)(v >>> 42 & 0x7FL | 0x80L)))));
            long v5 = this.pos;
            this.pos = v5 - 1L;
            UnsafeUtil.putByte(this.buffer, v5, ((byte)(((int)(v >>> 35 & 0x7FL | 0x80L)))));
            long v6 = this.pos;
            this.pos = v6 - 1L;
            UnsafeUtil.putByte(this.buffer, v6, ((byte)(((int)(v >>> 28 & 0x7FL | 0x80L)))));
            long v7 = this.pos;
            this.pos = v7 - 1L;
            UnsafeUtil.putByte(this.buffer, v7, ((byte)(((int)(v >>> 21 & 0x7FL | 0x80L)))));
            long v8 = this.pos;
            this.pos = v8 - 1L;
            UnsafeUtil.putByte(this.buffer, v8, ((byte)(((int)(v >>> 14 & 0x7FL | 0x80L)))));
            long v9 = this.pos;
            this.pos = v9 - 1L;
            UnsafeUtil.putByte(this.buffer, v9, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v10 = this.pos;
            this.pos = v10 - 1L;
            UnsafeUtil.putByte(this.buffer, v10, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64ThreeBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)v) >>> 14)));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)(v >>> 7 & 0x7FL | 0x80L)))));
            long v3 = this.pos;
            this.pos = v3 - 1L;
            UnsafeUtil.putByte(this.buffer, v3, ((byte)(((int)(v & 0x7FL | 0x80L)))));
        }

        private void writeVarint64TwoBytes(long v) {
            long v1 = this.pos;
            this.pos = v1 - 1L;
            UnsafeUtil.putByte(this.buffer, v1, ((byte)(((int)(v >>> 7)))));
            long v2 = this.pos;
            this.pos = v2 - 1L;
            UnsafeUtil.putByte(this.buffer, v2, ((byte)(((int)v) & 0x7F | 0x80)));
        }
    }

    public static final int DEFAULT_CHUNK_SIZE = 0x1000;
    private static final int MAP_KEY_NUMBER = 1;
    private static final int MAP_VALUE_NUMBER = 2;
    private final BufferAllocator alloc;
    final ArrayDeque buffers;
    private final int chunkSize;
    int totalDoneBytes;

    private BinaryWriter(BufferAllocator bufferAllocator0, int v) {
        this.buffers = new ArrayDeque(4);
        if(v <= 0) {
            throw new IllegalArgumentException("chunkSize must be > 0");
        }
        this.alloc = (BufferAllocator)Internal.checkNotNull(bufferAllocator0, "alloc");
        this.chunkSize = v;
    }

    BinaryWriter(BufferAllocator bufferAllocator0, int v, com.google.crypto.tink.shaded.protobuf.BinaryWriter.1 binaryWriter$10) {
        this(bufferAllocator0, v);
    }

    public final Queue complete() {
        this.finishCurrentBuffer();
        return this.buffers;
    }

    private static byte computeUInt64SizeNoTag(long value) {
        byte b;
        if((0xFFFFFFFFFFFFFF80L & value) == 0L) {
            return 1;
        }
        if(value < 0L) {
            return 10;
        }
        if((0xFFFFFFF800000000L & value) == 0L) {
            b = 2;
        }
        else {
            b = 6;
            value >>>= 28;
        }
        if((0xFFFFFFFFFFE00000L & value) != 0L) {
            b = (byte)(b + 2);
            value >>>= 14;
        }
        return (value & 0xFFFFFFFFFFFFC000L) == 0L ? b : ((byte)(b + 1));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final FieldOrder fieldOrder() {
        return FieldOrder.DESCENDING;
    }

    abstract void finishCurrentBuffer();

    public abstract int getTotalBytesWritten();

    // 去混淆评级： 低(20)
    static boolean isUnsafeDirectSupported() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    static boolean isUnsafeHeapSupported() [...] // 潜在的解密器

    final AllocatedBuffer newDirectBuffer() {
        return this.alloc.allocateDirectBuffer(this.chunkSize);
    }

    final AllocatedBuffer newDirectBuffer(int v) {
        return this.alloc.allocateDirectBuffer(Math.max(v, this.chunkSize));
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator0) {
        return BinaryWriter.newDirectInstance(bufferAllocator0, 0x1000);
    }

    // 去混淆评级： 低(30)
    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator0, int v) {
        return BinaryWriter.newUnsafeDirectInstance(bufferAllocator0, v);
    }

    final AllocatedBuffer newHeapBuffer() {
        return this.alloc.allocateHeapBuffer(this.chunkSize);
    }

    final AllocatedBuffer newHeapBuffer(int v) {
        return this.alloc.allocateHeapBuffer(Math.max(v, this.chunkSize));
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator0) {
        return BinaryWriter.newHeapInstance(bufferAllocator0, 0x1000);
    }

    // 去混淆评级： 低(30)
    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator0, int v) {
        return BinaryWriter.newUnsafeHeapInstance(bufferAllocator0, v);
    }

    static BinaryWriter newSafeDirectInstance(BufferAllocator bufferAllocator0, int v) {
        return new SafeDirectWriter(bufferAllocator0, v);
    }

    static BinaryWriter newSafeHeapInstance(BufferAllocator bufferAllocator0, int v) {
        return new SafeHeapWriter(bufferAllocator0, v);
    }

    // 去混淆评级： 低(30)
    static BinaryWriter newUnsafeDirectInstance(BufferAllocator bufferAllocator0, int v) {
        return new UnsafeDirectWriter(bufferAllocator0, v);
    }

    // 去混淆评级： 低(30)
    static BinaryWriter newUnsafeHeapInstance(BufferAllocator bufferAllocator0, int v) {
        return new UnsafeHeapWriter(bufferAllocator0, v);
    }

    abstract void requireSpace(int arg1);

    abstract void writeBool(boolean arg1);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeBoolList(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof BooleanArrayList) {
            this.writeBoolList_Internal(v, ((BooleanArrayList)list0), z);
            return;
        }
        this.writeBoolList_Internal(v, list0, z);
    }

    private final void writeBoolList_Internal(int v, BooleanArrayList booleanArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(booleanArrayList0.size() + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = booleanArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeBool(booleanArrayList0.getBoolean(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = booleanArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeBool(v, booleanArrayList0.getBoolean(v3));
        }
    }

    private final void writeBoolList_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeBool(((Boolean)list0.get(v2)).booleanValue());
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeBool(v, ((Boolean)list0.get(v3)).booleanValue());
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeBytesList(int v, List list0) throws IOException {
        for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
            this.writeBytes(v, ((ByteString)list0.get(v1)));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeDouble(int v, double f) throws IOException {
        this.writeFixed64(v, Double.doubleToRawLongBits(f));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeDoubleList(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof DoubleArrayList) {
            this.writeDoubleList_Internal(v, ((DoubleArrayList)list0), z);
            return;
        }
        this.writeDoubleList_Internal(v, list0, z);
    }

    private final void writeDoubleList_Internal(int v, DoubleArrayList doubleArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(doubleArrayList0.size() * 8 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = doubleArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed64(Double.doubleToRawLongBits(doubleArrayList0.getDouble(v2)));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = doubleArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeDouble(v, doubleArrayList0.getDouble(v3));
        }
    }

    private final void writeDoubleList_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 8 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed64(Double.doubleToRawLongBits(((double)(((Double)list0.get(v2))))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeDouble(v, ((double)(((Double)list0.get(v3)))));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeEnum(int v, int v1) throws IOException {
        this.writeInt32(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeEnumList(int v, List list0, boolean z) throws IOException {
        this.writeInt32List(v, list0, z);
    }

    abstract void writeFixed32(int arg1);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFixed32List(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof IntArrayList) {
            this.writeFixed32List_Internal(v, ((IntArrayList)list0), z);
            return;
        }
        this.writeFixed32List_Internal(v, list0, z);
    }

    private final void writeFixed32List_Internal(int v, IntArrayList intArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(intArrayList0.size() * 4 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = intArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed32(intArrayList0.getInt(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = intArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeFixed32(v, intArrayList0.getInt(v3));
        }
    }

    private final void writeFixed32List_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 4 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed32(((int)(((Integer)list0.get(v2)))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeFixed32(v, ((int)(((Integer)list0.get(v3)))));
        }
    }

    abstract void writeFixed64(long arg1);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFixed64List(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof LongArrayList) {
            this.writeFixed64List_Internal(v, ((LongArrayList)list0), z);
            return;
        }
        this.writeFixed64List_Internal(v, list0, z);
    }

    private final void writeFixed64List_Internal(int v, LongArrayList longArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(longArrayList0.size() * 8 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = longArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed64(longArrayList0.getLong(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = longArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeFixed64(v, longArrayList0.getLong(v3));
        }
    }

    private final void writeFixed64List_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 8 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed64(((long)(((Long)list0.get(v2)))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeFixed64(v, ((long)(((Long)list0.get(v3)))));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFloat(int v, float f) throws IOException {
        this.writeFixed32(v, Float.floatToRawIntBits(f));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFloatList(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof FloatArrayList) {
            this.writeFloatList_Internal(v, ((FloatArrayList)list0), z);
            return;
        }
        this.writeFloatList_Internal(v, list0, z);
    }

    private final void writeFloatList_Internal(int v, FloatArrayList floatArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(floatArrayList0.size() * 4 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = floatArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed32(Float.floatToRawIntBits(floatArrayList0.getFloat(v2)));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = floatArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeFloat(v, floatArrayList0.getFloat(v3));
        }
    }

    private final void writeFloatList_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 4 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeFixed32(Float.floatToRawIntBits(((float)(((Float)list0.get(v2))))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeFloat(v, ((float)(((Float)list0.get(v3)))));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    @Deprecated
    public final void writeGroupList(int v, List list0) throws IOException {
        for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
            this.writeGroup(v, list0.get(v1));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    @Deprecated
    public final void writeGroupList(int v, List list0, Schema schema0) throws IOException {
        for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
            this.writeGroup(v, list0.get(v1), schema0);
        }
    }

    abstract void writeInt32(int arg1);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeInt32List(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof IntArrayList) {
            this.writeInt32List_Internal(v, ((IntArrayList)list0), z);
            return;
        }
        this.writeInt32List_Internal(v, list0, z);
    }

    private final void writeInt32List_Internal(int v, IntArrayList intArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(intArrayList0.size() * 10 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = intArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeInt32(intArrayList0.getInt(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = intArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeInt32(v, intArrayList0.getInt(v3));
        }
    }

    private final void writeInt32List_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 10 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeInt32(((int)(((Integer)list0.get(v2)))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeInt32(v, ((int)(((Integer)list0.get(v3)))));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeInt64(int v, long v1) throws IOException {
        this.writeUInt64(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeInt64List(int v, List list0, boolean z) throws IOException {
        this.writeUInt64List(v, list0, z);
    }

    private void writeLazyString(int v, Object object0) throws IOException {
        if(object0 instanceof String) {
            this.writeString(v, ((String)object0));
            return;
        }
        this.writeBytes(v, ((ByteString)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMap(int v, Metadata mapEntryLite$Metadata0, Map map0) throws IOException {
        for(Object object0: map0.entrySet()) {
            int v1 = this.getTotalBytesWritten();
            Object object1 = ((Map.Entry)object0).getValue();
            BinaryWriter.writeMapEntryField(this, 2, mapEntryLite$Metadata0.valueType, object1);
            Object object2 = ((Map.Entry)object0).getKey();
            BinaryWriter.writeMapEntryField(this, 1, mapEntryLite$Metadata0.keyType, object2);
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
        }
    }

    static final void writeMapEntryField(Writer writer0, int v, FieldType wireFormat$FieldType0, Object object0) throws IOException {
        switch(com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
            case 1: {
                writer0.writeBool(v, ((Boolean)object0).booleanValue());
                return;
            }
            case 2: {
                writer0.writeFixed32(v, ((int)(((Integer)object0))));
                return;
            }
            case 3: {
                writer0.writeFixed64(v, ((long)(((Long)object0))));
                return;
            }
            case 4: {
                writer0.writeInt32(v, ((int)(((Integer)object0))));
                return;
            }
            case 5: {
                writer0.writeInt64(v, ((long)(((Long)object0))));
                return;
            }
            case 6: {
                writer0.writeSFixed32(v, ((int)(((Integer)object0))));
                return;
            }
            case 7: {
                writer0.writeSFixed64(v, ((long)(((Long)object0))));
                return;
            }
            case 8: {
                writer0.writeSInt32(v, ((int)(((Integer)object0))));
                return;
            }
            case 9: {
                writer0.writeSInt64(v, ((long)(((Long)object0))));
                return;
            }
            case 10: {
                writer0.writeString(v, ((String)object0));
                return;
            }
            case 11: {
                writer0.writeUInt32(v, ((int)(((Integer)object0))));
                return;
            }
            case 12: {
                writer0.writeUInt64(v, ((long)(((Long)object0))));
                return;
            }
            case 13: {
                writer0.writeFloat(v, ((float)(((Float)object0))));
                return;
            }
            case 14: {
                writer0.writeDouble(v, ((double)(((Double)object0))));
                return;
            }
            case 15: {
                writer0.writeMessage(v, object0);
                return;
            }
            case 16: {
                writer0.writeBytes(v, ((ByteString)object0));
                return;
            }
            case 17: {
                if(object0 instanceof EnumLite) {
                    writer0.writeEnum(v, ((EnumLite)object0).getNumber());
                    return;
                }
                if(!(object0 instanceof Integer)) {
                    throw new IllegalArgumentException("Unexpected type for enum in map.");
                }
                writer0.writeEnum(v, ((int)(((Integer)object0))));
                return;
            }
            default: {
                throw new IllegalArgumentException("Unsupported map value type for: " + wireFormat$FieldType0);
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageList(int v, List list0) throws IOException {
        for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
            this.writeMessage(v, list0.get(v1));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageList(int v, List list0, Schema schema0) throws IOException {
        for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
            this.writeMessage(v, list0.get(v1), schema0);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageSetItem(int v, Object object0) throws IOException {
        this.writeTag(1, 4);
        if(object0 instanceof ByteString) {
            this.writeBytes(3, ((ByteString)object0));
        }
        else {
            this.writeMessage(3, object0);
        }
        this.writeUInt32(2, v);
        this.writeTag(1, 3);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed32(int v, int v1) throws IOException {
        this.writeFixed32(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed32List(int v, List list0, boolean z) throws IOException {
        this.writeFixed32List(v, list0, z);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed64(int v, long v1) throws IOException {
        this.writeFixed64(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed64List(int v, List list0, boolean z) throws IOException {
        this.writeFixed64List(v, list0, z);
    }

    abstract void writeSInt32(int arg1);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSInt32List(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof IntArrayList) {
            this.writeSInt32List_Internal(v, ((IntArrayList)list0), z);
            return;
        }
        this.writeSInt32List_Internal(v, list0, z);
    }

    private final void writeSInt32List_Internal(int v, IntArrayList intArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(intArrayList0.size() * 5 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = intArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeSInt32(intArrayList0.getInt(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = intArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeSInt32(v, intArrayList0.getInt(v3));
        }
    }

    private final void writeSInt32List_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 5 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeSInt32(((int)(((Integer)list0.get(v2)))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeSInt32(v, ((int)(((Integer)list0.get(v3)))));
        }
    }

    abstract void writeSInt64(long arg1);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSInt64List(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof LongArrayList) {
            this.writeSInt64List_Internal(v, ((LongArrayList)list0), z);
            return;
        }
        this.writeSInt64List_Internal(v, list0, z);
    }

    private final void writeSInt64List_Internal(int v, LongArrayList longArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(longArrayList0.size() * 10 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = longArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeSInt64(longArrayList0.getLong(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = longArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeSInt64(v, longArrayList0.getLong(v3));
        }
    }

    private final void writeSInt64List_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 10 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeSInt64(((long)(((Long)list0.get(v2)))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeSInt64(v, ((long)(((Long)list0.get(v3)))));
        }
    }

    abstract void writeString(String arg1);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeStringList(int v, List list0) throws IOException {
        if(list0 instanceof LazyStringList) {
            for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
                this.writeLazyString(v, ((LazyStringList)list0).getRaw(v1));
            }
            return;
        }
        for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
            this.writeString(v, ((String)list0.get(v2)));
        }
    }

    abstract void writeTag(int arg1, int arg2);

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeUInt32List(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof IntArrayList) {
            this.writeUInt32List_Internal(v, ((IntArrayList)list0), z);
            return;
        }
        this.writeUInt32List_Internal(v, list0, z);
    }

    private final void writeUInt32List_Internal(int v, IntArrayList intArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(intArrayList0.size() * 5 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = intArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeVarint32(intArrayList0.getInt(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = intArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeUInt32(v, intArrayList0.getInt(v3));
        }
    }

    private final void writeUInt32List_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 5 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeVarint32(((int)(((Integer)list0.get(v2)))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeUInt32(v, ((int)(((Integer)list0.get(v3)))));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeUInt64List(int v, List list0, boolean z) throws IOException {
        if(list0 instanceof LongArrayList) {
            this.writeUInt64List_Internal(v, ((LongArrayList)list0), z);
            return;
        }
        this.writeUInt64List_Internal(v, list0, z);
    }

    private final void writeUInt64List_Internal(int v, LongArrayList longArrayList0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(longArrayList0.size() * 10 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = longArrayList0.size() - 1; v2 >= 0; --v2) {
                this.writeVarint64(longArrayList0.getLong(v2));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = longArrayList0.size() - 1; v3 >= 0; --v3) {
            this.writeUInt64(v, longArrayList0.getLong(v3));
        }
    }

    private final void writeUInt64List_Internal(int v, List list0, boolean z) throws IOException {
        if(z) {
            this.requireSpace(list0.size() * 10 + 10);
            int v1 = this.getTotalBytesWritten();
            for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                this.writeVarint64(((long)(((Long)list0.get(v2)))));
            }
            this.writeVarint32(this.getTotalBytesWritten() - v1);
            this.writeTag(v, 2);
            return;
        }
        for(int v3 = list0.size() - 1; v3 >= 0; --v3) {
            this.writeUInt64(v, ((long)(((Long)list0.get(v3)))));
        }
    }

    abstract void writeVarint32(int arg1);

    abstract void writeVarint64(long arg1);

    class com.google.crypto.tink.shaded.protobuf.BinaryWriter.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.BOOL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.DOUBLE.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

