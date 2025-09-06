package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CodedOutputStream extends ByteOutput {
    static abstract class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] buffer;
        final int limit;
        int position;
        int totalBytesWritten;

        AbstractBufferedEncoder(int v) {
            super(null);
            if(v < 0) {
                throw new IllegalArgumentException("bufferSize must be >= 0");
            }
            byte[] arr_b = new byte[Math.max(v, 20)];
            this.buffer = arr_b;
            this.limit = arr_b.length;
        }

        final void buffer(byte b) {
            int v = this.position;
            this.position = v + 1;
            this.buffer[v] = b;
            ++this.totalBytesWritten;
        }

        final void bufferFixed32NoTag(int v) {
            int v1 = this.position;
            this.position = v1 + 1;
            this.buffer[v1] = (byte)(v & 0xFF);
            this.position = v1 + 2;
            this.buffer[v1 + 1] = (byte)(v >> 8 & 0xFF);
            this.position = v1 + 3;
            this.buffer[v1 + 2] = (byte)(v >> 16 & 0xFF);
            this.position = v1 + 4;
            this.buffer[v1 + 3] = (byte)(v >> 24 & 0xFF);
            this.totalBytesWritten += 4;
        }

        final void bufferFixed64NoTag(long v) {
            int v1 = this.position;
            this.position = v1 + 1;
            this.buffer[v1] = (byte)(((int)(v & 0xFFL)));
            this.position = v1 + 2;
            this.buffer[v1 + 1] = (byte)(((int)(v >> 8 & 0xFFL)));
            this.position = v1 + 3;
            this.buffer[v1 + 2] = (byte)(((int)(v >> 16 & 0xFFL)));
            this.position = v1 + 4;
            this.buffer[v1 + 3] = (byte)(((int)(0xFFL & v >> 24)));
            this.position = v1 + 5;
            this.buffer[v1 + 4] = (byte)(((int)(v >> 0x20)) & 0xFF);
            this.position = v1 + 6;
            this.buffer[v1 + 5] = (byte)(((int)(v >> 40)) & 0xFF);
            this.position = v1 + 7;
            this.buffer[v1 + 6] = (byte)(((int)(v >> 0x30)) & 0xFF);
            this.position = v1 + 8;
            this.buffer[v1 + 7] = (byte)(((int)(v >> 56)) & 0xFF);
            this.totalBytesWritten += 8;
        }

        final void bufferInt32NoTag(int v) {
            if(v >= 0) {
                this.bufferUInt32NoTag(v);
                return;
            }
            this.bufferUInt64NoTag(((long)v));
        }

        final void bufferTag(int v, int v1) {
            this.bufferUInt32NoTag(v << 3 | v1);
        }

        final void bufferUInt32NoTag(int v) {
            long v1 = (long)this.position;
            while((v & 0xFFFFFF80) != 0) {
                int v2 = this.position;
                this.position = v2 + 1;
                UnsafeUtil.putByte(this.buffer, ((long)v2), ((byte)(v & 0x7F | 0x80)));
                v >>>= 7;
            }
            int v3 = this.position;
            this.position = v3 + 1;
            UnsafeUtil.putByte(this.buffer, ((long)v3), ((byte)v));
            this.totalBytesWritten += (int)(((long)this.position) - v1);
        }

        final void bufferUInt64NoTag(long v) {
            long v1 = (long)this.position;
            while((v & 0xFFFFFFFFFFFFFF80L) != 0L) {
                int v2 = this.position;
                this.position = v2 + 1;
                UnsafeUtil.putByte(this.buffer, ((long)v2), ((byte)(((int)v) & 0x7F | 0x80)));
                v >>>= 7;
            }
            int v3 = this.position;
            this.position = v3 + 1;
            UnsafeUtil.putByte(this.buffer, ((long)v3), ((byte)(((int)v))));
            this.totalBytesWritten += (int)(((long)this.position) - v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final int getTotalBytesWritten() {
            return this.totalBytesWritten;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final int spaceLeft() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }
    }

    static class ArrayEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        ArrayEncoder(byte[] arr_b, int v, int v1) {
            super(null);
            if(arr_b == null) {
                throw new NullPointerException("buffer");
            }
            int v2 = v + v1;
            if((v | v1 | arr_b.length - v2) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", ((int)arr_b.length), v, v1));
            }
            this.buffer = arr_b;
            this.offset = v;
            this.position = v;
            this.limit = v2;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void flush() {
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final int getTotalBytesWritten() {
            return this.position - this.offset;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final int spaceLeft() {
            return this.limit - this.position;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void write(byte b) throws IOException {
            try {
                int v = this.position;
                this.position = v + 1;
                this.buffer[v] = b;
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void write(ByteBuffer byteBuffer0) throws IOException {
            int v = byteBuffer0.remaining();
            try {
                byteBuffer0.get(this.buffer, this.position, v);
                this.position += v;
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, v), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void write(byte[] arr_b, int v, int v1) throws IOException {
            try {
                System.arraycopy(arr_b, v, this.buffer, this.position, v1);
                this.position += v1;
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, v1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeBool(int v, boolean z) throws IOException {
            this.writeTag(v, 0);
            this.write(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeByteArray(int v, byte[] arr_b) throws IOException {
            this.writeByteArray(v, arr_b, 0, arr_b.length);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeByteArray(int v, byte[] arr_b, int v1, int v2) throws IOException {
            this.writeTag(v, 2);
            this.writeByteArrayNoTag(arr_b, v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeByteArrayNoTag(byte[] arr_b, int v, int v1) throws IOException {
            this.writeUInt32NoTag(v1);
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeByteBuffer(int v, ByteBuffer byteBuffer0) throws IOException {
            this.writeTag(v, 2);
            this.writeUInt32NoTag(byteBuffer0.capacity());
            this.writeRawBytes(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeBytes(int v, ByteString byteString0) throws IOException {
            this.writeTag(v, 2);
            this.writeBytesNoTag(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeBytesNoTag(ByteString byteString0) throws IOException {
            this.writeUInt32NoTag(byteString0.size());
            byteString0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeFixed32(int v, int v1) throws IOException {
            this.writeTag(v, 5);
            this.writeFixed32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeFixed32NoTag(int v) throws IOException {
            try {
                int v1 = this.position;
                this.position = v1 + 1;
                this.buffer[v1] = (byte)(v & 0xFF);
                this.position = v1 + 2;
                this.buffer[v1 + 1] = (byte)(v >> 8 & 0xFF);
                this.position = v1 + 3;
                this.buffer[v1 + 2] = (byte)(v >> 16 & 0xFF);
                this.position = v1 + 4;
                this.buffer[v1 + 3] = (byte)(v >> 24 & 0xFF);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeFixed64(int v, long v1) throws IOException {
            this.writeTag(v, 1);
            this.writeFixed64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeFixed64NoTag(long v) throws IOException {
            try {
                int v1 = this.position;
                this.position = v1 + 1;
                this.buffer[v1] = (byte)(((int)v) & 0xFF);
                this.position = v1 + 2;
                this.buffer[v1 + 1] = (byte)(((int)(v >> 8)) & 0xFF);
                this.position = v1 + 3;
                this.buffer[v1 + 2] = (byte)(((int)(v >> 16)) & 0xFF);
                this.position = v1 + 4;
                this.buffer[v1 + 3] = (byte)(((int)(v >> 24)) & 0xFF);
                this.position = v1 + 5;
                this.buffer[v1 + 4] = (byte)(((int)(v >> 0x20)) & 0xFF);
                this.position = v1 + 6;
                this.buffer[v1 + 5] = (byte)(((int)(v >> 40)) & 0xFF);
                this.position = v1 + 7;
                this.buffer[v1 + 6] = (byte)(((int)(v >> 0x30)) & 0xFF);
                this.position = v1 + 8;
                this.buffer[v1 + 7] = (byte)(((int)(v >> 56)) & 0xFF);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeInt32(int v, int v1) throws IOException {
            this.writeTag(v, 0);
            this.writeInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeInt32NoTag(int v) throws IOException {
            if(v >= 0) {
                this.writeUInt32NoTag(v);
                return;
            }
            this.writeUInt64NoTag(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeLazy(ByteBuffer byteBuffer0) throws IOException {
            this.write(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeLazy(byte[] arr_b, int v, int v1) throws IOException {
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeMessage(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        final void writeMessage(int v, MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeTag(v, 2);
            this.writeUInt32NoTag(((AbstractMessageLite)messageLite0).getSerializedSize(schema0));
            schema0.writeTo(messageLite0, this.wrapper);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeMessageNoTag(MessageLite messageLite0) throws IOException {
            this.writeUInt32NoTag(messageLite0.getSerializedSize());
            messageLite0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        final void writeMessageNoTag(MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeUInt32NoTag(((AbstractMessageLite)messageLite0).getSerializedSize(schema0));
            schema0.writeTo(messageLite0, this.wrapper);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeMessageSetExtension(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeMessage(3, messageLite0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeRawBytes(ByteBuffer byteBuffer0) throws IOException {
            if(byteBuffer0.hasArray()) {
                this.write(byteBuffer0.array(), byteBuffer0.arrayOffset(), byteBuffer0.capacity());
                return;
            }
            ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
            byteBuffer1.clear();
            this.write(byteBuffer1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeRawMessageSetExtension(int v, ByteString byteString0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeBytes(3, byteString0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeString(int v, String s) throws IOException {
            this.writeTag(v, 2);
            this.writeStringNoTag(s);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeStringNoTag(String s) throws IOException {
            int v;
            try {
                v = this.position;
                int v1 = ArrayEncoder.computeUInt32SizeNoTag(s.length() * 3);
                int v2 = ArrayEncoder.computeUInt32SizeNoTag(s.length());
                if(v2 == v1) {
                    int v3 = v + v2;
                    this.position = v3;
                    int v4 = Utf8.encode(s, this.buffer, v3, this.spaceLeft());
                    this.position = v;
                    this.writeUInt32NoTag(v4 - v - v2);
                    this.position = v4;
                    return;
                }
                this.writeUInt32NoTag(Utf8.encodedLength(s));
                this.position = Utf8.encode(s, this.buffer, this.position, this.spaceLeft());
            }
            catch(UnpairedSurrogateException utf8$UnpairedSurrogateException0) {
                this.position = v;
                this.inefficientWriteStringNoTag(s, utf8$UnpairedSurrogateException0);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeTag(int v, int v1) throws IOException {
            this.writeUInt32NoTag(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeUInt32(int v, int v1) throws IOException {
            this.writeTag(v, 0);
            this.writeUInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeUInt32NoTag(int v) throws IOException {
            try {
                while(true) {
                    if((v & 0xFFFFFF80) == 0) {
                        int v1 = this.position;
                        this.position = v1 + 1;
                        this.buffer[v1] = (byte)v;
                        return;
                    }
                    int v2 = this.position;
                    this.position = v2 + 1;
                    this.buffer[v2] = (byte)(v & 0x7F | 0x80);
                    v >>>= 7;
                }
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeUInt64(int v, long v1) throws IOException {
            this.writeTag(v, 0);
            this.writeUInt64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public final void writeUInt64NoTag(long v) throws IOException {
            if(this.spaceLeft() >= 10) {
                while((v & 0xFFFFFFFFFFFFFF80L) != 0L) {
                    int v1 = this.position;
                    this.position = v1 + 1;
                    UnsafeUtil.putByte(this.buffer, ((long)v1), ((byte)(((int)v) & 0x7F | 0x80)));
                    v >>>= 7;
                }
                int v2 = this.position;
                this.position = v2 + 1;
                UnsafeUtil.putByte(this.buffer, ((long)v2), ((byte)(((int)v))));
                return;
            }
            try {
                while(true) {
                    if((v & 0xFFFFFFFFFFFFFF80L) == 0L) {
                        int v3 = this.position;
                        this.position = v3 + 1;
                        this.buffer[v3] = (byte)(((int)v));
                        return;
                    }
                    int v4 = this.position;
                    this.position = v4 + 1;
                    this.buffer[v4] = (byte)(((int)v) & 0x7F | 0x80);
                    v >>>= 7;
                }
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }
    }

    static final class ByteOutputEncoder extends AbstractBufferedEncoder {
        private final ByteOutput out;

        ByteOutputEncoder(ByteOutput byteOutput0, int v) {
            super(v);
            if(byteOutput0 == null) {
                throw new NullPointerException("out");
            }
            this.out = byteOutput0;
        }

        private void doFlush() throws IOException {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void flush() throws IOException {
            if(this.position > 0) {
                this.doFlush();
            }
        }

        private void flushIfNotAvailable(int v) throws IOException {
            if(this.limit - this.position < v) {
                this.doFlush();
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte b) throws IOException {
            if(this.position == this.limit) {
                this.doFlush();
            }
            this.buffer(b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(ByteBuffer byteBuffer0) throws IOException {
            this.flush();
            this.out.write(byteBuffer0);
            this.totalBytesWritten += byteBuffer0.remaining();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte[] arr_b, int v, int v1) throws IOException {
            this.flush();
            this.out.write(arr_b, v, v1);
            this.totalBytesWritten += v1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBool(int v, boolean z) throws IOException {
            this.flushIfNotAvailable(11);
            this.bufferTag(v, 0);
            this.buffer(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b) throws IOException {
            this.writeByteArray(v, arr_b, 0, arr_b.length);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b, int v1, int v2) throws IOException {
            this.writeTag(v, 2);
            this.writeByteArrayNoTag(arr_b, v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(byte[] arr_b, int v, int v1) throws IOException {
            this.writeUInt32NoTag(v1);
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteBuffer(int v, ByteBuffer byteBuffer0) throws IOException {
            this.writeTag(v, 2);
            this.writeUInt32NoTag(byteBuffer0.capacity());
            this.writeRawBytes(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytes(int v, ByteString byteString0) throws IOException {
            this.writeTag(v, 2);
            this.writeBytesNoTag(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytesNoTag(ByteString byteString0) throws IOException {
            this.writeUInt32NoTag(byteString0.size());
            byteString0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32(int v, int v1) throws IOException {
            this.flushIfNotAvailable(14);
            this.bufferTag(v, 5);
            this.bufferFixed32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32NoTag(int v) throws IOException {
            this.flushIfNotAvailable(4);
            this.bufferFixed32NoTag(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64(int v, long v1) throws IOException {
            this.flushIfNotAvailable(18);
            this.bufferTag(v, 1);
            this.bufferFixed64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64NoTag(long v) throws IOException {
            this.flushIfNotAvailable(8);
            this.bufferFixed64NoTag(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32(int v, int v1) throws IOException {
            this.flushIfNotAvailable(20);
            this.bufferTag(v, 0);
            this.bufferInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32NoTag(int v) throws IOException {
            if(v >= 0) {
                this.writeUInt32NoTag(v);
                return;
            }
            this.writeUInt64NoTag(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(ByteBuffer byteBuffer0) throws IOException {
            this.flush();
            this.out.writeLazy(byteBuffer0);
            this.totalBytesWritten += byteBuffer0.remaining();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(byte[] arr_b, int v, int v1) throws IOException {
            this.flush();
            this.out.writeLazy(arr_b, v, v1);
            this.totalBytesWritten += v1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessage(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessage(int v, MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0, schema0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageNoTag(MessageLite messageLite0) throws IOException {
            this.writeUInt32NoTag(messageLite0.getSerializedSize());
            messageLite0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessageNoTag(MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeUInt32NoTag(((AbstractMessageLite)messageLite0).getSerializedSize(schema0));
            schema0.writeTo(messageLite0, this.wrapper);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageSetExtension(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeMessage(3, messageLite0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawBytes(ByteBuffer byteBuffer0) throws IOException {
            if(byteBuffer0.hasArray()) {
                this.write(byteBuffer0.array(), byteBuffer0.arrayOffset(), byteBuffer0.capacity());
                return;
            }
            ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
            byteBuffer1.clear();
            this.write(byteBuffer1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(int v, ByteString byteString0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeBytes(3, byteString0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeString(int v, String s) throws IOException {
            this.writeTag(v, 2);
            this.writeStringNoTag(s);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeStringNoTag(String s) throws IOException {
            int v4;
            int v = s.length();
            int v1 = ByteOutputEncoder.computeUInt32SizeNoTag(v * 3);
            int v2 = v1 + v * 3;
            if(v2 > this.limit) {
                byte[] arr_b = new byte[v * 3];
                int v3 = Utf8.encode(s, arr_b, 0, v * 3);
                this.writeUInt32NoTag(v3);
                this.writeLazy(arr_b, 0, v3);
                return;
            }
            if(v2 > this.limit - this.position) {
                this.doFlush();
            }
            try {
                v4 = this.position;
                int v5 = ByteOutputEncoder.computeUInt32SizeNoTag(s.length());
                if(v5 == v1) {
                    this.position = v4 + v5;
                    int v6 = Utf8.encode(s, this.buffer, this.position, this.limit - this.position);
                    this.position = v4;
                    int v7 = v6 - v4 - v5;
                    this.bufferUInt32NoTag(v7);
                    this.position = v6;
                    this.totalBytesWritten += v7;
                    return;
                }
                int v8 = Utf8.encodedLength(s);
                this.bufferUInt32NoTag(v8);
                this.position = Utf8.encode(s, this.buffer, this.position, v8);
                this.totalBytesWritten += v8;
            }
            catch(UnpairedSurrogateException utf8$UnpairedSurrogateException0) {
                this.totalBytesWritten -= this.position - v4;
                this.position = v4;
                this.inefficientWriteStringNoTag(s, utf8$UnpairedSurrogateException0);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeTag(int v, int v1) throws IOException {
            this.writeUInt32NoTag(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32(int v, int v1) throws IOException {
            this.flushIfNotAvailable(20);
            this.bufferTag(v, 0);
            this.bufferUInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int v) throws IOException {
            this.flushIfNotAvailable(5);
            this.bufferUInt32NoTag(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64(int v, long v1) throws IOException {
            this.flushIfNotAvailable(20);
            this.bufferTag(v, 0);
            this.bufferUInt64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long v) throws IOException {
            this.flushIfNotAvailable(10);
            this.bufferUInt64NoTag(v);
        }
    }

    static final class HeapNioEncoder extends ArrayEncoder {
        private final ByteBuffer byteBuffer;
        private int initialPosition;

        HeapNioEncoder(ByteBuffer byteBuffer0) {
            super(byteBuffer0.array(), byteBuffer0.arrayOffset() + byteBuffer0.position(), byteBuffer0.remaining());
            this.byteBuffer = byteBuffer0;
            this.initialPosition = byteBuffer0.position();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream$ArrayEncoder
        public void flush() {
            int v = this.initialPosition;
            int v1 = this.getTotalBytesWritten();
            this.byteBuffer.position(v + v1);
        }
    }

    public static class OutOfSpaceException extends IOException {
        private static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";
        private static final long serialVersionUID = 0x9F95917C52CE6E25L;

        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        OutOfSpaceException(String s) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + s);
        }

        OutOfSpaceException(String s, Throwable throwable0) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + s, throwable0);
        }

        OutOfSpaceException(Throwable throwable0) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", throwable0);
        }
    }

    static final class OutputStreamEncoder extends AbstractBufferedEncoder {
        private final OutputStream out;

        OutputStreamEncoder(OutputStream outputStream0, int v) {
            super(v);
            if(outputStream0 == null) {
                throw new NullPointerException("out");
            }
            this.out = outputStream0;
        }

        private void doFlush() throws IOException {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void flush() throws IOException {
            if(this.position > 0) {
                this.doFlush();
            }
        }

        private void flushIfNotAvailable(int v) throws IOException {
            if(this.limit - this.position < v) {
                this.doFlush();
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte b) throws IOException {
            if(this.position == this.limit) {
                this.doFlush();
            }
            this.buffer(b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(ByteBuffer byteBuffer0) throws IOException {
            int v = byteBuffer0.remaining();
            if(this.limit - this.position >= v) {
                byteBuffer0.get(this.buffer, this.position, v);
                this.position += v;
                this.totalBytesWritten += v;
                return;
            }
            int v1 = this.limit - this.position;
            byteBuffer0.get(this.buffer, this.position, v1);
            int v2 = v - v1;
            this.position = this.limit;
            this.totalBytesWritten += v1;
            this.doFlush();
            while(v2 > this.limit) {
                byteBuffer0.get(this.buffer, 0, this.limit);
                this.out.write(this.buffer, 0, this.limit);
                v2 -= this.limit;
                this.totalBytesWritten += this.limit;
            }
            byteBuffer0.get(this.buffer, 0, v2);
            this.position = v2;
            this.totalBytesWritten += v2;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte[] arr_b, int v, int v1) throws IOException {
            if(this.limit - this.position >= v1) {
                System.arraycopy(arr_b, v, this.buffer, this.position, v1);
                this.position += v1;
                this.totalBytesWritten += v1;
                return;
            }
            int v2 = this.limit - this.position;
            System.arraycopy(arr_b, v, this.buffer, this.position, v2);
            int v3 = v + v2;
            int v4 = v1 - v2;
            this.position = this.limit;
            this.totalBytesWritten += v2;
            this.doFlush();
            if(v4 <= this.limit) {
                System.arraycopy(arr_b, v3, this.buffer, 0, v4);
                this.position = v4;
            }
            else {
                this.out.write(arr_b, v3, v4);
            }
            this.totalBytesWritten += v4;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBool(int v, boolean z) throws IOException {
            this.flushIfNotAvailable(11);
            this.bufferTag(v, 0);
            this.buffer(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b) throws IOException {
            this.writeByteArray(v, arr_b, 0, arr_b.length);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b, int v1, int v2) throws IOException {
            this.writeTag(v, 2);
            this.writeByteArrayNoTag(arr_b, v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(byte[] arr_b, int v, int v1) throws IOException {
            this.writeUInt32NoTag(v1);
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteBuffer(int v, ByteBuffer byteBuffer0) throws IOException {
            this.writeTag(v, 2);
            this.writeUInt32NoTag(byteBuffer0.capacity());
            this.writeRawBytes(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytes(int v, ByteString byteString0) throws IOException {
            this.writeTag(v, 2);
            this.writeBytesNoTag(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytesNoTag(ByteString byteString0) throws IOException {
            this.writeUInt32NoTag(byteString0.size());
            byteString0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32(int v, int v1) throws IOException {
            this.flushIfNotAvailable(14);
            this.bufferTag(v, 5);
            this.bufferFixed32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32NoTag(int v) throws IOException {
            this.flushIfNotAvailable(4);
            this.bufferFixed32NoTag(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64(int v, long v1) throws IOException {
            this.flushIfNotAvailable(18);
            this.bufferTag(v, 1);
            this.bufferFixed64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64NoTag(long v) throws IOException {
            this.flushIfNotAvailable(8);
            this.bufferFixed64NoTag(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32(int v, int v1) throws IOException {
            this.flushIfNotAvailable(20);
            this.bufferTag(v, 0);
            this.bufferInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32NoTag(int v) throws IOException {
            if(v >= 0) {
                this.writeUInt32NoTag(v);
                return;
            }
            this.writeUInt64NoTag(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(ByteBuffer byteBuffer0) throws IOException {
            this.write(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(byte[] arr_b, int v, int v1) throws IOException {
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessage(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessage(int v, MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0, schema0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageNoTag(MessageLite messageLite0) throws IOException {
            this.writeUInt32NoTag(messageLite0.getSerializedSize());
            messageLite0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessageNoTag(MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeUInt32NoTag(((AbstractMessageLite)messageLite0).getSerializedSize(schema0));
            schema0.writeTo(messageLite0, this.wrapper);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageSetExtension(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeMessage(3, messageLite0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawBytes(ByteBuffer byteBuffer0) throws IOException {
            if(byteBuffer0.hasArray()) {
                this.write(byteBuffer0.array(), byteBuffer0.arrayOffset(), byteBuffer0.capacity());
                return;
            }
            ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
            byteBuffer1.clear();
            this.write(byteBuffer1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(int v, ByteString byteString0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeBytes(3, byteString0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeString(int v, String s) throws IOException {
            this.writeTag(v, 2);
            this.writeStringNoTag(s);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeStringNoTag(String s) throws IOException {
            int v7;
            try {
                int v = s.length();
                int v1 = OutputStreamEncoder.computeUInt32SizeNoTag(v * 3);
                int v2 = v1 + v * 3;
                if(v2 > this.limit) {
                    byte[] arr_b = new byte[v * 3];
                    int v3 = Utf8.encode(s, arr_b, 0, v * 3);
                    this.writeUInt32NoTag(v3);
                    this.writeLazy(arr_b, 0, v3);
                    return;
                }
                if(v2 > this.limit - this.position) {
                    this.doFlush();
                }
                int v4 = OutputStreamEncoder.computeUInt32SizeNoTag(s.length());
                int v5 = this.position;
                if(v4 == v1) {
                    try {
                        this.position = v5 + v4;
                        int v6 = Utf8.encode(s, this.buffer, this.position, this.limit - this.position);
                        this.position = v5;
                        v7 = v6 - v5 - v4;
                        this.bufferUInt32NoTag(v7);
                        this.position = v6;
                        this.totalBytesWritten += v7;
                        return;
                    label_21:
                        v7 = Utf8.encodedLength(s);
                        this.bufferUInt32NoTag(v7);
                        this.position = Utf8.encode(s, this.buffer, this.position, v7);
                        this.totalBytesWritten += v7;
                        return;
                    }
                    catch(UnpairedSurrogateException utf8$UnpairedSurrogateException1) {
                        this.totalBytesWritten -= this.position - v5;
                        this.position = v5;
                        throw utf8$UnpairedSurrogateException1;
                    }
                    catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
                        throw new OutOfSpaceException(arrayIndexOutOfBoundsException0);
                    }
                }
                else {
                    goto label_21;
                }
                this.totalBytesWritten += v7;
            }
            catch(UnpairedSurrogateException utf8$UnpairedSurrogateException0) {
                this.inefficientWriteStringNoTag(s, utf8$UnpairedSurrogateException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeTag(int v, int v1) throws IOException {
            this.writeUInt32NoTag(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32(int v, int v1) throws IOException {
            this.flushIfNotAvailable(20);
            this.bufferTag(v, 0);
            this.bufferUInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int v) throws IOException {
            this.flushIfNotAvailable(5);
            this.bufferUInt32NoTag(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64(int v, long v1) throws IOException {
            this.flushIfNotAvailable(20);
            this.bufferTag(v, 0);
            this.bufferUInt64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long v) throws IOException {
            this.flushIfNotAvailable(10);
            this.bufferUInt64NoTag(v);
        }
    }

    static final class SafeDirectNioEncoder extends CodedOutputStream {
        private final ByteBuffer buffer;
        private final int initialPosition;
        private final ByteBuffer originalBuffer;

        SafeDirectNioEncoder(ByteBuffer byteBuffer0) {
            super(null);
            this.originalBuffer = byteBuffer0;
            this.buffer = byteBuffer0.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.initialPosition = byteBuffer0.position();
        }

        private void encode(String s) throws IOException {
            try {
                Utf8.encodeUtf8(s, this.buffer);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void flush() {
            this.originalBuffer.position(this.buffer.position());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public int getTotalBytesWritten() {
            return this.buffer.position() - this.initialPosition;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public int spaceLeft() {
            return this.buffer.remaining();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte b) throws IOException {
            try {
                this.buffer.put(b);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(ByteBuffer byteBuffer0) throws IOException {
            try {
                this.buffer.put(byteBuffer0);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte[] arr_b, int v, int v1) throws IOException {
            try {
                this.buffer.put(arr_b, v, v1);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(indexOutOfBoundsException0);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBool(int v, boolean z) throws IOException {
            this.writeTag(v, 0);
            this.write(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b) throws IOException {
            this.writeByteArray(v, arr_b, 0, arr_b.length);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b, int v1, int v2) throws IOException {
            this.writeTag(v, 2);
            this.writeByteArrayNoTag(arr_b, v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(byte[] arr_b, int v, int v1) throws IOException {
            this.writeUInt32NoTag(v1);
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteBuffer(int v, ByteBuffer byteBuffer0) throws IOException {
            this.writeTag(v, 2);
            this.writeUInt32NoTag(byteBuffer0.capacity());
            this.writeRawBytes(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytes(int v, ByteString byteString0) throws IOException {
            this.writeTag(v, 2);
            this.writeBytesNoTag(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytesNoTag(ByteString byteString0) throws IOException {
            this.writeUInt32NoTag(byteString0.size());
            byteString0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32(int v, int v1) throws IOException {
            this.writeTag(v, 5);
            this.writeFixed32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32NoTag(int v) throws IOException {
            try {
                this.buffer.putInt(v);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64(int v, long v1) throws IOException {
            this.writeTag(v, 1);
            this.writeFixed64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64NoTag(long v) throws IOException {
            try {
                this.buffer.putLong(v);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32(int v, int v1) throws IOException {
            this.writeTag(v, 0);
            this.writeInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32NoTag(int v) throws IOException {
            if(v >= 0) {
                this.writeUInt32NoTag(v);
                return;
            }
            this.writeUInt64NoTag(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(ByteBuffer byteBuffer0) throws IOException {
            this.write(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(byte[] arr_b, int v, int v1) throws IOException {
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessage(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessage(int v, MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0, schema0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageNoTag(MessageLite messageLite0) throws IOException {
            this.writeUInt32NoTag(messageLite0.getSerializedSize());
            messageLite0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessageNoTag(MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeUInt32NoTag(((AbstractMessageLite)messageLite0).getSerializedSize(schema0));
            schema0.writeTo(messageLite0, this.wrapper);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageSetExtension(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeMessage(3, messageLite0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawBytes(ByteBuffer byteBuffer0) throws IOException {
            if(byteBuffer0.hasArray()) {
                this.write(byteBuffer0.array(), byteBuffer0.arrayOffset(), byteBuffer0.capacity());
                return;
            }
            ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
            byteBuffer1.clear();
            this.write(byteBuffer1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(int v, ByteString byteString0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeBytes(3, byteString0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeString(int v, String s) throws IOException {
            this.writeTag(v, 2);
            this.writeStringNoTag(s);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeStringNoTag(String s) throws IOException {
            int v = this.buffer.position();
            try {
                int v1 = SafeDirectNioEncoder.computeUInt32SizeNoTag(s.length() * 3);
                int v2 = SafeDirectNioEncoder.computeUInt32SizeNoTag(s.length());
                if(v2 == v1) {
                    int v3 = this.buffer.position() + v2;
                    this.buffer.position(v3);
                    this.encode(s);
                    int v4 = this.buffer.position();
                    this.buffer.position(v);
                    this.writeUInt32NoTag(v4 - v3);
                    this.buffer.position(v4);
                    return;
                }
                this.writeUInt32NoTag(Utf8.encodedLength(s));
                this.encode(s);
            }
            catch(UnpairedSurrogateException utf8$UnpairedSurrogateException0) {
                this.buffer.position(v);
                this.inefficientWriteStringNoTag(s, utf8$UnpairedSurrogateException0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new OutOfSpaceException(illegalArgumentException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeTag(int v, int v1) throws IOException {
            this.writeUInt32NoTag(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32(int v, int v1) throws IOException {
            this.writeTag(v, 0);
            this.writeUInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int v) throws IOException {
            try {
                while(true) {
                    if((v & 0xFFFFFF80) == 0) {
                        this.buffer.put(((byte)v));
                        return;
                    }
                    this.buffer.put(((byte)(v & 0x7F | 0x80)));
                    v >>>= 7;
                }
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64(int v, long v1) throws IOException {
            this.writeTag(v, 0);
            this.writeUInt64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long v) throws IOException {
            try {
                while(true) {
                    if((0xFFFFFFFFFFFFFF80L & v) == 0L) {
                        this.buffer.put(((byte)(((int)v))));
                        return;
                    }
                    this.buffer.put(((byte)(((int)v) & 0x7F | 0x80)));
                    v >>>= 7;
                }
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }
    }

    static final class UnsafeDirectNioEncoder extends CodedOutputStream {
        private final long address;
        private final ByteBuffer buffer;
        private final long initialPosition;
        private final long limit;
        private final long oneVarintLimit;
        private final ByteBuffer originalBuffer;
        private long position;

        UnsafeDirectNioEncoder(ByteBuffer byteBuffer0) {
            super(null);
            this.originalBuffer = byteBuffer0;
            this.buffer = byteBuffer0.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            long v = UnsafeUtil.addressOffset(byteBuffer0);
            this.address = v;
            long v1 = ((long)byteBuffer0.position()) + v;
            this.initialPosition = v1;
            long v2 = v + ((long)byteBuffer0.limit());
            this.limit = v2;
            this.oneVarintLimit = v2 - 10L;
            this.position = v1;
        }

        private int bufferPos(long v) {
            return (int)(v - this.address);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void flush() {
            this.originalBuffer.position(this.bufferPos(this.position));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public int getTotalBytesWritten() {
            return (int)(this.position - this.initialPosition);
        }

        // 去混淆评级： 低(20)
        static boolean isSupported() [...] // 潜在的解密器

        private void repositionBuffer(long v) {
            this.buffer.position(this.bufferPos(v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public int spaceLeft() {
            return (int)(this.limit - this.position);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte b) throws IOException {
            long v = this.position;
            if(v >= this.limit) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1));
            }
            this.position = v + 1L;
            UnsafeUtil.putByte(v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(ByteBuffer byteBuffer0) throws IOException {
            try {
                this.repositionBuffer(this.position);
                this.buffer.put(byteBuffer0);
                this.position += (long)byteBuffer0.remaining();
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new OutOfSpaceException(bufferOverflowException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void write(byte[] arr_b, int v, int v1) throws IOException {
            if(arr_b != null && v >= 0 && v1 >= 0 && arr_b.length - v1 >= v) {
                long v2 = this.position;
                if(this.limit - ((long)v1) >= v2) {
                    UnsafeUtil.copyMemory(arr_b, ((long)v), v2, ((long)v1));
                    this.position += (long)v1;
                    return;
                }
            }
            if(arr_b == null) {
                throw new NullPointerException("value");
            }
            throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, v1));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBool(int v, boolean z) throws IOException {
            this.writeTag(v, 0);
            this.write(((byte)z));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b) throws IOException {
            this.writeByteArray(v, arr_b, 0, arr_b.length);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArray(int v, byte[] arr_b, int v1, int v2) throws IOException {
            this.writeTag(v, 2);
            this.writeByteArrayNoTag(arr_b, v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(byte[] arr_b, int v, int v1) throws IOException {
            this.writeUInt32NoTag(v1);
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeByteBuffer(int v, ByteBuffer byteBuffer0) throws IOException {
            this.writeTag(v, 2);
            this.writeUInt32NoTag(byteBuffer0.capacity());
            this.writeRawBytes(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytes(int v, ByteString byteString0) throws IOException {
            this.writeTag(v, 2);
            this.writeBytesNoTag(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeBytesNoTag(ByteString byteString0) throws IOException {
            this.writeUInt32NoTag(byteString0.size());
            byteString0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32(int v, int v1) throws IOException {
            this.writeTag(v, 5);
            this.writeFixed32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed32NoTag(int v) throws IOException {
            this.buffer.putInt(this.bufferPos(this.position), v);
            this.position += 4L;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64(int v, long v1) throws IOException {
            this.writeTag(v, 1);
            this.writeFixed64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeFixed64NoTag(long v) throws IOException {
            this.buffer.putLong(this.bufferPos(this.position), v);
            this.position += 8L;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32(int v, int v1) throws IOException {
            this.writeTag(v, 0);
            this.writeInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeInt32NoTag(int v) throws IOException {
            if(v >= 0) {
                this.writeUInt32NoTag(v);
                return;
            }
            this.writeUInt64NoTag(((long)v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(ByteBuffer byteBuffer0) throws IOException {
            this.write(byteBuffer0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeLazy(byte[] arr_b, int v, int v1) throws IOException {
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessage(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessage(int v, MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeTag(v, 2);
            this.writeMessageNoTag(messageLite0, schema0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageNoTag(MessageLite messageLite0) throws IOException {
            this.writeUInt32NoTag(messageLite0.getSerializedSize());
            messageLite0.writeTo(this);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        void writeMessageNoTag(MessageLite messageLite0, Schema schema0) throws IOException {
            this.writeUInt32NoTag(((AbstractMessageLite)messageLite0).getSerializedSize(schema0));
            schema0.writeTo(messageLite0, this.wrapper);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeMessageSetExtension(int v, MessageLite messageLite0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeMessage(3, messageLite0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawBytes(ByteBuffer byteBuffer0) throws IOException {
            if(byteBuffer0.hasArray()) {
                this.write(byteBuffer0.array(), byteBuffer0.arrayOffset(), byteBuffer0.capacity());
                return;
            }
            ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
            byteBuffer1.clear();
            this.write(byteBuffer1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(int v, ByteString byteString0) throws IOException {
            this.writeTag(1, 3);
            this.writeUInt32(2, v);
            this.writeBytes(3, byteString0);
            this.writeTag(1, 4);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeString(int v, String s) throws IOException {
            this.writeTag(v, 2);
            this.writeStringNoTag(s);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeStringNoTag(String s) throws IOException {
            long v;
            try {
                v = this.position;
                int v1 = UnsafeDirectNioEncoder.computeUInt32SizeNoTag(s.length() * 3);
                int v2 = UnsafeDirectNioEncoder.computeUInt32SizeNoTag(s.length());
                if(v2 == v1) {
                    int v3 = this.bufferPos(this.position) + v2;
                    this.buffer.position(v3);
                    Utf8.encodeUtf8(s, this.buffer);
                    int v4 = this.buffer.position() - v3;
                    this.writeUInt32NoTag(v4);
                    this.position += (long)v4;
                    return;
                }
                int v5 = Utf8.encodedLength(s);
                this.writeUInt32NoTag(v5);
                this.repositionBuffer(this.position);
                Utf8.encodeUtf8(s, this.buffer);
                this.position += (long)v5;
            }
            catch(UnpairedSurrogateException utf8$UnpairedSurrogateException0) {
                this.position = v;
                this.repositionBuffer(v);
                this.inefficientWriteStringNoTag(s, utf8$UnpairedSurrogateException0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new OutOfSpaceException(illegalArgumentException0);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new OutOfSpaceException(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeTag(int v, int v1) throws IOException {
            this.writeUInt32NoTag(v << 3 | v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32(int v, int v1) throws IOException {
            this.writeTag(v, 0);
            this.writeUInt32NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int v) throws IOException {
            if(this.position <= this.oneVarintLimit) {
                while((v & 0xFFFFFF80) != 0) {
                    long v1 = this.position;
                    this.position = v1 + 1L;
                    UnsafeUtil.putByte(v1, ((byte)(v & 0x7F | 0x80)));
                    v >>>= 7;
                }
                long v2 = this.position;
                this.position = v2 + 1L;
                UnsafeUtil.putByte(v2, ((byte)v));
                return;
            }
            long v3;
            while((v3 = this.position) < this.limit) {
                if((v & 0xFFFFFF80) == 0) {
                    this.position = v3 + 1L;
                    UnsafeUtil.putByte(v3, ((byte)v));
                    return;
                }
                this.position = v3 + 1L;
                UnsafeUtil.putByte(v3, ((byte)(v & 0x7F | 0x80)));
                v >>>= 7;
            }
            throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64(int v, long v1) throws IOException {
            this.writeTag(v, 0);
            this.writeUInt64NoTag(v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long v) throws IOException {
            if(this.position <= this.oneVarintLimit) {
                while((v & 0xFFFFFFFFFFFFFF80L) != 0L) {
                    long v1 = this.position;
                    this.position = v1 + 1L;
                    UnsafeUtil.putByte(v1, ((byte)(((int)v) & 0x7F | 0x80)));
                    v >>>= 7;
                }
                long v2 = this.position;
                this.position = v2 + 1L;
                UnsafeUtil.putByte(v2, ((byte)(((int)v))));
                return;
            }
            long v3;
            while((v3 = this.position) < this.limit) {
                if((v & 0xFFFFFFFFFFFFFF80L) == 0L) {
                    this.position = v3 + 1L;
                    UnsafeUtil.putByte(v3, ((byte)(((int)v))));
                    return;
                }
                this.position = v3 + 1L;
                UnsafeUtil.putByte(v3, ((byte)(((int)v) & 0x7F | 0x80)));
                v >>>= 7;
            }
            throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1));
        }
    }

    public static final int DEFAULT_BUFFER_SIZE = 0x1000;
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = false;
    @Deprecated
    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    private static final Logger logger;
    private boolean serializationDeterministic;
    CodedOutputStreamWriter wrapper;

    static {
        CodedOutputStream.logger = Logger.getLogger("com.google.crypto.tink.shaded.protobuf.CodedOutputStream");
        CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS = true;
    }

    private CodedOutputStream() {
    }

    CodedOutputStream(com.google.crypto.tink.shaded.protobuf.CodedOutputStream.1 codedOutputStream$10) {
    }

    static boolean access$100() [...] // 潜在的解密器

    public final void checkNoSpaceLeft() {
        if(this.spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public static int computeBoolSize(int v, boolean z) {
        return CodedOutputStream.computeTagSize(v) + 1;
    }

    public static int computeBoolSizeNoTag(boolean unused) [...] // Inlined contents

    public static int computeByteArraySize(int v, byte[] arr_b) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeByteArraySizeNoTag(arr_b);
    }

    public static int computeByteArraySizeNoTag(byte[] arr_b) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(arr_b.length);
    }

    public static int computeByteBufferSize(int v, ByteBuffer byteBuffer0) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeByteBufferSizeNoTag(byteBuffer0);
    }

    public static int computeByteBufferSizeNoTag(ByteBuffer byteBuffer0) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(byteBuffer0.capacity());
    }

    public static int computeBytesSize(int v, ByteString byteString0) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeBytesSizeNoTag(byteString0);
    }

    public static int computeBytesSizeNoTag(ByteString byteString0) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(byteString0.size());
    }

    public static int computeDoubleSize(int v, double f) {
        return CodedOutputStream.computeTagSize(v) + 8;
    }

    public static int computeDoubleSizeNoTag(double unused) [...] // Inlined contents

    public static int computeEnumSize(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeEnumSizeNoTag(v1);
    }

    public static int computeEnumSizeNoTag(int v) {
        return CodedOutputStream.computeInt32SizeNoTag(v);
    }

    public static int computeFixed32Size(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + 4;
    }

    public static int computeFixed32SizeNoTag(int unused) [...] // Inlined contents

    public static int computeFixed64Size(int v, long v1) {
        return CodedOutputStream.computeTagSize(v) + 8;
    }

    public static int computeFixed64SizeNoTag(long unused) [...] // Inlined contents

    public static int computeFloatSize(int v, float f) {
        return CodedOutputStream.computeTagSize(v) + 4;
    }

    public static int computeFloatSizeNoTag(float unused) [...] // Inlined contents

    @Deprecated
    public static int computeGroupSize(int v, MessageLite messageLite0) {
        return CodedOutputStream.computeTagSize(v) * 2 + messageLite0.getSerializedSize();
    }

    @Deprecated
    static int computeGroupSize(int v, MessageLite messageLite0, Schema schema0) {
        return CodedOutputStream.computeTagSize(v) * 2 + CodedOutputStream.computeGroupSizeNoTag(messageLite0, schema0);
    }

    @Deprecated
    public static int computeGroupSizeNoTag(MessageLite messageLite0) {
        return messageLite0.getSerializedSize();
    }

    @Deprecated
    static int computeGroupSizeNoTag(MessageLite messageLite0, Schema schema0) {
        return ((AbstractMessageLite)messageLite0).getSerializedSize(schema0);
    }

    public static int computeInt32Size(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeInt32SizeNoTag(v1);
    }

    public static int computeInt32SizeNoTag(int v) {
        return v < 0 ? 10 : CodedOutputStream.computeUInt32SizeNoTag(v);
    }

    public static int computeInt64Size(int v, long v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeInt64SizeNoTag(v1);
    }

    public static int computeInt64SizeNoTag(long v) {
        return CodedOutputStream.computeUInt64SizeNoTag(v);
    }

    // 去混淆评级： 低(20)
    public static int computeLazyFieldMessageSetExtensionSize(int v, LazyFieldLite lazyFieldLite0) {
        return CodedOutputStream.computeUInt32Size(2, v) + 2 + CodedOutputStream.computeLazyFieldSize(3, lazyFieldLite0);
    }

    public static int computeLazyFieldSize(int v, LazyFieldLite lazyFieldLite0) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeLazyFieldSizeNoTag(lazyFieldLite0);
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite lazyFieldLite0) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(lazyFieldLite0.getSerializedSize());
    }

    static int computeLengthDelimitedFieldSize(int v) {
        return CodedOutputStream.computeUInt32SizeNoTag(v) + v;
    }

    // 去混淆评级： 低(20)
    public static int computeMessageSetExtensionSize(int v, MessageLite messageLite0) {
        return CodedOutputStream.computeUInt32Size(2, v) + 2 + CodedOutputStream.computeMessageSize(3, messageLite0);
    }

    public static int computeMessageSize(int v, MessageLite messageLite0) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeMessageSizeNoTag(messageLite0);
    }

    static int computeMessageSize(int v, MessageLite messageLite0, Schema schema0) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeMessageSizeNoTag(messageLite0, schema0);
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite0) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(messageLite0.getSerializedSize());
    }

    static int computeMessageSizeNoTag(MessageLite messageLite0, Schema schema0) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(((AbstractMessageLite)messageLite0).getSerializedSize(schema0));
    }

    static int computePreferredBufferSize(int dataLength) {
        return dataLength <= 0x1000 ? dataLength : 0x1000;
    }

    // 去混淆评级： 低(20)
    public static int computeRawMessageSetExtensionSize(int v, ByteString byteString0) {
        return CodedOutputStream.computeUInt32Size(2, v) + 2 + CodedOutputStream.computeBytesSize(3, byteString0);
    }

    @Deprecated
    public static int computeRawVarint32Size(int v) {
        return CodedOutputStream.computeUInt32SizeNoTag(v);
    }

    @Deprecated
    public static int computeRawVarint64Size(long v) {
        return CodedOutputStream.computeUInt64SizeNoTag(v);
    }

    public static int computeSFixed32Size(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + 4;
    }

    public static int computeSFixed32SizeNoTag(int unused) [...] // Inlined contents

    public static int computeSFixed64Size(int v, long v1) {
        return CodedOutputStream.computeTagSize(v) + 8;
    }

    public static int computeSFixed64SizeNoTag(long unused) [...] // Inlined contents

    public static int computeSInt32Size(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeSInt32SizeNoTag(v1);
    }

    public static int computeSInt32SizeNoTag(int v) {
        return CodedOutputStream.computeUInt32SizeNoTag(v >> 0x1F ^ v << 1);
    }

    public static int computeSInt64Size(int v, long v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeSInt64SizeNoTag(v1);
    }

    public static int computeSInt64SizeNoTag(long v) {
        return CodedOutputStream.computeUInt64SizeNoTag(v >> 0x3F ^ v << 1);
    }

    public static int computeStringSize(int v, String s) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeStringSizeNoTag(s);
    }

    public static int computeStringSizeNoTag(String s) {
        try {
            return CodedOutputStream.computeLengthDelimitedFieldSize(Utf8.encodedLength(s));
        }
        catch(UnpairedSurrogateException unused_ex) {
            return CodedOutputStream.computeLengthDelimitedFieldSize(s.getBytes(Internal.UTF_8).length);
        }
    }

    public static int computeTagSize(int v) [...] // 潜在的解密器

    public static int computeUInt32Size(int v, int v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeUInt32SizeNoTag(v1);
    }

    public static int computeUInt32SizeNoTag(int value) {
        if((value & 0xFFFFFF80) == 0) {
            return 1;
        }
        if((value & 0xFFFFC000) == 0) {
            return 2;
        }
        if((0xFFE00000 & value) == 0) {
            return 3;
        }
        return (value & 0xF0000000) == 0 ? 4 : 5;
    }

    public static int computeUInt64Size(int v, long v1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeUInt64SizeNoTag(v1);
    }

    public static int computeUInt64SizeNoTag(long value) {
        int v1;
        if((0xFFFFFFFFFFFFFF80L & value) == 0L) {
            return 1;
        }
        if(value < 0L) {
            return 10;
        }
        if((0xFFFFFFF800000000L & value) == 0L) {
            v1 = 2;
        }
        else {
            value >>>= 28;
            v1 = 6;
        }
        if((0xFFFFFFFFFFE00000L & value) != 0L) {
            v1 += 2;
            value >>>= 14;
        }
        return (value & 0xFFFFFFFFFFFFC000L) == 0L ? v1 : v1 + 1;
    }

    public static int encodeZigZag32(int n) [...] // Inlined contents

    public static long encodeZigZag64(long n) [...] // Inlined contents

    public abstract void flush() throws IOException;

    public abstract int getTotalBytesWritten();

    final void inefficientWriteStringNoTag(String s, UnpairedSurrogateException utf8$UnpairedSurrogateException0) throws IOException {
        CodedOutputStream.logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", utf8$UnpairedSurrogateException0);
        byte[] arr_b = s.getBytes(Internal.UTF_8);
        try {
            this.writeUInt32NoTag(arr_b.length);
            this.writeLazy(arr_b, 0, arr_b.length);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new OutOfSpaceException(indexOutOfBoundsException0);
        }
    }

    boolean isSerializationDeterministic() {
        return this.serializationDeterministic;
    }

    static CodedOutputStream newInstance(ByteOutput byteOutput0, int v) {
        if(v < 0) {
            throw new IllegalArgumentException("bufferSize must be positive");
        }
        return new ByteOutputEncoder(byteOutput0, v);
    }

    public static CodedOutputStream newInstance(OutputStream outputStream0) {
        return CodedOutputStream.newInstance(outputStream0, 0x1000);
    }

    public static CodedOutputStream newInstance(OutputStream outputStream0, int v) {
        return new OutputStreamEncoder(outputStream0, v);
    }

    public static CodedOutputStream newInstance(ByteBuffer byteBuffer0) {
        if(byteBuffer0.hasArray()) {
            return new HeapNioEncoder(byteBuffer0);
        }
        if(!byteBuffer0.isDirect() || byteBuffer0.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
        return CodedOutputStream.newUnsafeInstance(byteBuffer0);
    }

    @Deprecated
    public static CodedOutputStream newInstance(ByteBuffer byteBuffer0, int v) {
        return CodedOutputStream.newInstance(byteBuffer0);
    }

    public static CodedOutputStream newInstance(byte[] arr_b) {
        return CodedOutputStream.newInstance(arr_b, 0, arr_b.length);
    }

    public static CodedOutputStream newInstance(byte[] arr_b, int v, int v1) {
        return new ArrayEncoder(arr_b, v, v1);
    }

    static CodedOutputStream newSafeInstance(ByteBuffer byteBuffer0) {
        return new SafeDirectNioEncoder(byteBuffer0);
    }

    static CodedOutputStream newUnsafeInstance(ByteBuffer byteBuffer0) {
        return new UnsafeDirectNioEncoder(byteBuffer0);
    }

    public abstract int spaceLeft();

    public void useDeterministicSerialization() {
        this.serializationDeterministic = true;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
    public abstract void write(byte arg1) throws IOException;

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
    public abstract void write(ByteBuffer arg1) throws IOException;

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
    public abstract void write(byte[] arg1, int arg2, int arg3) throws IOException;

    public abstract void writeBool(int arg1, boolean arg2) throws IOException;

    public final void writeBoolNoTag(boolean z) throws IOException {
        this.write(((byte)z));
    }

    public abstract void writeByteArray(int arg1, byte[] arg2) throws IOException;

    public abstract void writeByteArray(int arg1, byte[] arg2, int arg3, int arg4) throws IOException;

    public final void writeByteArrayNoTag(byte[] arr_b) throws IOException {
        this.writeByteArrayNoTag(arr_b, 0, arr_b.length);
    }

    abstract void writeByteArrayNoTag(byte[] arg1, int arg2, int arg3) throws IOException;

    public abstract void writeByteBuffer(int arg1, ByteBuffer arg2) throws IOException;

    public abstract void writeBytes(int arg1, ByteString arg2) throws IOException;

    public abstract void writeBytesNoTag(ByteString arg1) throws IOException;

    public final void writeDouble(int v, double f) throws IOException {
        this.writeFixed64(v, Double.doubleToRawLongBits(f));
    }

    public final void writeDoubleNoTag(double f) throws IOException {
        this.writeFixed64NoTag(Double.doubleToRawLongBits(f));
    }

    public final void writeEnum(int v, int v1) throws IOException {
        this.writeInt32(v, v1);
    }

    public final void writeEnumNoTag(int v) throws IOException {
        this.writeInt32NoTag(v);
    }

    public abstract void writeFixed32(int arg1, int arg2) throws IOException;

    public abstract void writeFixed32NoTag(int arg1) throws IOException;

    public abstract void writeFixed64(int arg1, long arg2) throws IOException;

    public abstract void writeFixed64NoTag(long arg1) throws IOException;

    public final void writeFloat(int v, float f) throws IOException {
        this.writeFixed32(v, Float.floatToRawIntBits(f));
    }

    public final void writeFloatNoTag(float f) throws IOException {
        this.writeFixed32NoTag(Float.floatToRawIntBits(f));
    }

    @Deprecated
    public final void writeGroup(int v, MessageLite messageLite0) throws IOException {
        this.writeTag(v, 3);
        this.writeGroupNoTag(messageLite0);
        this.writeTag(v, 4);
    }

    @Deprecated
    final void writeGroup(int v, MessageLite messageLite0, Schema schema0) throws IOException {
        this.writeTag(v, 3);
        this.writeGroupNoTag(messageLite0, schema0);
        this.writeTag(v, 4);
    }

    @Deprecated
    public final void writeGroupNoTag(MessageLite messageLite0) throws IOException {
        messageLite0.writeTo(this);
    }

    @Deprecated
    final void writeGroupNoTag(MessageLite messageLite0, Schema schema0) throws IOException {
        schema0.writeTo(messageLite0, this.wrapper);
    }

    public abstract void writeInt32(int arg1, int arg2) throws IOException;

    public abstract void writeInt32NoTag(int arg1) throws IOException;

    public final void writeInt64(int v, long v1) throws IOException {
        this.writeUInt64(v, v1);
    }

    public final void writeInt64NoTag(long v) throws IOException {
        this.writeUInt64NoTag(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
    public abstract void writeLazy(ByteBuffer arg1) throws IOException;

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteOutput
    public abstract void writeLazy(byte[] arg1, int arg2, int arg3) throws IOException;

    public abstract void writeMessage(int arg1, MessageLite arg2) throws IOException;

    abstract void writeMessage(int arg1, MessageLite arg2, Schema arg3) throws IOException;

    public abstract void writeMessageNoTag(MessageLite arg1) throws IOException;

    abstract void writeMessageNoTag(MessageLite arg1, Schema arg2) throws IOException;

    public abstract void writeMessageSetExtension(int arg1, MessageLite arg2) throws IOException;

    public final void writeRawByte(byte b) throws IOException {
        this.write(b);
    }

    public final void writeRawByte(int v) throws IOException {
        this.write(((byte)v));
    }

    public final void writeRawBytes(ByteString byteString0) throws IOException {
        byteString0.writeTo(this);
    }

    public abstract void writeRawBytes(ByteBuffer arg1) throws IOException;

    public final void writeRawBytes(byte[] arr_b) throws IOException {
        this.write(arr_b, 0, arr_b.length);
    }

    public final void writeRawBytes(byte[] arr_b, int v, int v1) throws IOException {
        this.write(arr_b, v, v1);
    }

    @Deprecated
    public final void writeRawLittleEndian32(int v) throws IOException {
        this.writeFixed32NoTag(v);
    }

    @Deprecated
    public final void writeRawLittleEndian64(long v) throws IOException {
        this.writeFixed64NoTag(v);
    }

    public abstract void writeRawMessageSetExtension(int arg1, ByteString arg2) throws IOException;

    @Deprecated
    public final void writeRawVarint32(int v) throws IOException {
        this.writeUInt32NoTag(v);
    }

    @Deprecated
    public final void writeRawVarint64(long v) throws IOException {
        this.writeUInt64NoTag(v);
    }

    public final void writeSFixed32(int v, int v1) throws IOException {
        this.writeFixed32(v, v1);
    }

    public final void writeSFixed32NoTag(int v) throws IOException {
        this.writeFixed32NoTag(v);
    }

    public final void writeSFixed64(int v, long v1) throws IOException {
        this.writeFixed64(v, v1);
    }

    public final void writeSFixed64NoTag(long v) throws IOException {
        this.writeFixed64NoTag(v);
    }

    public final void writeSInt32(int v, int v1) throws IOException {
        this.writeUInt32(v, v1 >> 0x1F ^ v1 << 1);
    }

    public final void writeSInt32NoTag(int v) throws IOException {
        this.writeUInt32NoTag(v >> 0x1F ^ v << 1);
    }

    public final void writeSInt64(int v, long v1) throws IOException {
        this.writeUInt64(v, v1 >> 0x3F ^ v1 << 1);
    }

    public final void writeSInt64NoTag(long v) throws IOException {
        this.writeUInt64NoTag(v >> 0x3F ^ v << 1);
    }

    public abstract void writeString(int arg1, String arg2) throws IOException;

    public abstract void writeStringNoTag(String arg1) throws IOException;

    public abstract void writeTag(int arg1, int arg2) throws IOException;

    public abstract void writeUInt32(int arg1, int arg2) throws IOException;

    public abstract void writeUInt32NoTag(int arg1) throws IOException;

    public abstract void writeUInt64(int arg1, long arg2) throws IOException;

    public abstract void writeUInt64NoTag(long arg1) throws IOException;

    class com.google.crypto.tink.shaded.protobuf.CodedOutputStream.1 {
    }

}

