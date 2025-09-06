package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.internal._BufferKt;

@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001A\u00020\u0006H\u0016J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\b\u0010\u0010\u001A\u00020\rH\u0016J\u0010\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0014H\u0016J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0012H\u0016J \u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00122\u0006\u0010\u0016\u001A\u00020\u0012H\u0016J\u0010\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0017\u001A\u00020\u0018H\u0016J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0015\u001A\u00020\u0012H\u0016J\u0010\u0010\u0019\u001A\u00020\u00122\u0006\u0010\u001A\u001A\u00020\u0018H\u0016J\u0018\u0010\u0019\u001A\u00020\u00122\u0006\u0010\u001A\u001A\u00020\u00182\u0006\u0010\u0015\u001A\u00020\u0012H\u0016J\b\u0010\u001B\u001A\u00020\u001CH\u0016J\b\u0010\u001D\u001A\u00020\rH\u0016J\b\u0010\u001E\u001A\u00020\u0001H\u0016J\u0018\u0010\u001F\u001A\u00020\r2\u0006\u0010 \u001A\u00020\u00122\u0006\u0010\u0017\u001A\u00020\u0018H\u0016J(\u0010\u001F\u001A\u00020\r2\u0006\u0010 \u001A\u00020\u00122\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010!\u001A\u00020\"2\u0006\u0010#\u001A\u00020\"H\u0016J\u0010\u0010$\u001A\u00020\"2\u0006\u0010%\u001A\u00020&H\u0016J\u0010\u0010$\u001A\u00020\"2\u0006\u0010%\u001A\u00020\'H\u0016J \u0010$\u001A\u00020\"2\u0006\u0010%\u001A\u00020\'2\u0006\u0010 \u001A\u00020\"2\u0006\u0010#\u001A\u00020\"H\u0016J\u0018\u0010$\u001A\u00020\u00122\u0006\u0010%\u001A\u00020\u00062\u0006\u0010#\u001A\u00020\u0012H\u0016J\u0010\u0010(\u001A\u00020\u00122\u0006\u0010%\u001A\u00020)H\u0016J\b\u0010*\u001A\u00020\u0014H\u0016J\b\u0010+\u001A\u00020\'H\u0016J\u0010\u0010+\u001A\u00020\'2\u0006\u0010#\u001A\u00020\u0012H\u0016J\b\u0010,\u001A\u00020\u0018H\u0016J\u0010\u0010,\u001A\u00020\u00182\u0006\u0010#\u001A\u00020\u0012H\u0016J\b\u0010-\u001A\u00020\u0012H\u0016J\u0010\u0010.\u001A\u00020\u000F2\u0006\u0010%\u001A\u00020\'H\u0016J\u0018\u0010.\u001A\u00020\u000F2\u0006\u0010%\u001A\u00020\u00062\u0006\u0010#\u001A\u00020\u0012H\u0016J\b\u0010/\u001A\u00020\u0012H\u0016J\b\u00100\u001A\u00020\"H\u0016J\b\u00101\u001A\u00020\"H\u0016J\b\u00102\u001A\u00020\u0012H\u0016J\b\u00103\u001A\u00020\u0012H\u0016J\b\u00104\u001A\u000205H\u0016J\b\u00106\u001A\u000205H\u0016J\u0010\u00107\u001A\u0002082\u0006\u00109\u001A\u00020:H\u0016J\u0018\u00107\u001A\u0002082\u0006\u0010#\u001A\u00020\u00122\u0006\u00109\u001A\u00020:H\u0016J\b\u0010;\u001A\u000208H\u0016J\u0010\u0010;\u001A\u0002082\u0006\u0010#\u001A\u00020\u0012H\u0016J\b\u0010<\u001A\u00020\"H\u0016J\n\u0010=\u001A\u0004\u0018\u000108H\u0016J\b\u0010>\u001A\u000208H\u0016J\u0010\u0010>\u001A\u0002082\u0006\u0010?\u001A\u00020\u0012H\u0016J\u0010\u0010@\u001A\u00020\r2\u0006\u0010#\u001A\u00020\u0012H\u0016J\u0010\u0010A\u001A\u00020\u000F2\u0006\u0010#\u001A\u00020\u0012H\u0016J\u0010\u0010B\u001A\u00020\"2\u0006\u0010C\u001A\u00020DH\u0016J\u0010\u0010E\u001A\u00020\u000F2\u0006\u0010#\u001A\u00020\u0012H\u0016J\b\u0010F\u001A\u00020GH\u0016J\b\u0010H\u001A\u000208H\u0016R\u001B\u0010\u0005\u001A\u00020\u00068\u00D6\u0002X\u0096\u0004\u00A2\u0006\f\u0012\u0004\b\u0007\u0010\b\u001A\u0004\b\t\u0010\nR\u0010\u0010\u000B\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\f\u001A\u00020\r8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006I"}, d2 = {"Lokio/RealBufferedSource;", "Lokio/BufferedSource;", "source", "Lokio/Source;", "(Lokio/Source;)V", "buffer", "Lokio/Buffer;", "getBuffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "exhausted", "indexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "peek", "rangeEquals", "offset", "bytesOffset", "", "byteCount", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "", "charset", "Ljava/nio/charset/Charset;", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "skip", "timeout", "Lokio/Timeout;", "toString", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class RealBufferedSource implements AutoCloseable, BufferedSource {
    public final Buffer bufferField;
    public boolean closed;
    public final Source source;

    public RealBufferedSource(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        super();
        this.source = source0;
        this.bufferField = new Buffer();
    }

    @Override  // okio.BufferedSource
    public Buffer buffer() {
        return this.bufferField;
    }

    @Override  // okio.Source
    public void close() {
        if(!this.closed) {
            this.closed = true;
            this.source.close();
            this.bufferField.clear();
        }
    }

    @Override  // okio.BufferedSource
    public boolean exhausted() {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.bufferField.exhausted() && this.source.read(this.bufferField, 0x2000L) == -1L;
    }

    @Override  // okio.BufferedSource
    public Buffer getBuffer() {
        return this.bufferField;
    }

    public static void getBuffer$annotations() {
    }

    @Override  // okio.BufferedSource
    public long indexOf(byte b) {
        return this.indexOf(b, 0L, 0x7FFFFFFFFFFFFFFFL);
    }

    @Override  // okio.BufferedSource
    public long indexOf(byte b, long v) {
        return this.indexOf(b, v, 0x7FFFFFFFFFFFFFFFL);
    }

    @Override  // okio.BufferedSource
    public long indexOf(byte b, long v, long v1) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        if(0L > v || v > v1) {
            throw new IllegalArgumentException(("fromIndex=" + v + " toIndex=" + v1).toString());
        }
        for(long v2 = v; v2 < v1; v2 = Math.max(v2, v4)) {
            long v3 = this.bufferField.indexOf(b, v2, v1);
            if(v3 != -1L) {
                return v3;
            }
            long v4 = this.bufferField.size();
            if(v4 >= v1 || this.source.read(this.bufferField, 0x2000L) == -1L) {
                break;
            }
        }
        return -1L;
    }

    @Override  // okio.BufferedSource
    public long indexOf(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        return this.indexOf(byteString0, 0L);
    }

    @Override  // okio.BufferedSource
    public long indexOf(ByteString byteString0, long v) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(!this.closed) {
            while(true) {
                long v1 = this.bufferField.indexOf(byteString0, v);
                if(v1 != -1L) {
                    return v1;
                }
                long v2 = this.bufferField.size();
                if(this.source.read(this.bufferField, 0x2000L) == -1L) {
                    return -1L;
                }
                v = Math.max(v, v2 - ((long)byteString0.size()) + 1L);
            }
        }
        throw new IllegalStateException("closed");
    }

    @Override  // okio.BufferedSource
    public long indexOfElement(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "targetBytes");
        return this.indexOfElement(byteString0, 0L);
    }

    @Override  // okio.BufferedSource
    public long indexOfElement(ByteString byteString0, long v) {
        Intrinsics.checkNotNullParameter(byteString0, "targetBytes");
        if(!this.closed) {
            while(true) {
                long v1 = this.bufferField.indexOfElement(byteString0, v);
                if(v1 != -1L) {
                    return v1;
                }
                long v2 = this.bufferField.size();
                if(this.source.read(this.bufferField, 0x2000L) == -1L) {
                    return -1L;
                }
                v = Math.max(v, v2);
            }
        }
        throw new IllegalStateException("closed");
    }

    @Override  // okio.BufferedSource
    public InputStream inputStream() {
        return new AutoCloseable() {
            @Override
            public int available() {
                if(RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                return (int)Math.min(RealBufferedSource.this.bufferField.size(), 0x7FFFFFFFL);
            }

            @Override
            public void close() {
                RealBufferedSource.this.close();
            }

            @Override
            public int read() {
                if(RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                return RealBufferedSource.this.bufferField.size() != 0L || RealBufferedSource.this.source.read(RealBufferedSource.this.bufferField, 0x2000L) != -1L ? RealBufferedSource.this.bufferField.readByte() & 0xFF : -1;
            }

            @Override
            public int read(byte[] arr_b, int v, int v1) {
                Intrinsics.checkNotNullParameter(arr_b, "data");
                if(RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                _UtilKt.checkOffsetAndCount(arr_b.length, v, v1);
                return RealBufferedSource.this.bufferField.size() != 0L || RealBufferedSource.this.source.read(RealBufferedSource.this.bufferField, 0x2000L) != -1L ? RealBufferedSource.this.bufferField.read(arr_b, v, v1) : -1;
            }

            @Override
            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }

    @Override
    public boolean isOpen() {
        return !this.closed;
    }

    @Override  // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override  // okio.BufferedSource
    public boolean rangeEquals(long v, ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        return this.rangeEquals(v, byteString0, 0, byteString0.size());
    }

    @Override  // okio.BufferedSource
    public boolean rangeEquals(long v, ByteString byteString0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        if(v >= 0L && v1 >= 0 && v2 >= 0 && byteString0.size() - v1 >= v2) {
            for(int v3 = 0; v3 < v2; ++v3) {
                long v4 = ((long)v3) + v;
                if(!this.request(v4 + 1L)) {
                    return false;
                }
                if(this.bufferField.getByte(v4) != byteString0.getByte(v1 + v3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int read(ByteBuffer byteBuffer0) {
        Intrinsics.checkNotNullParameter(byteBuffer0, "sink");
        return this.bufferField.size() != 0L || this.source.read(this.bufferField, 0x2000L) != -1L ? this.bufferField.read(byteBuffer0) : -1;
    }

    @Override  // okio.BufferedSource
    public int read(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        return this.read(arr_b, 0, arr_b.length);
    }

    @Override  // okio.BufferedSource
    public int read(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        _UtilKt.checkOffsetAndCount(arr_b.length, v, v1);
        return this.bufferField.size() != 0L || this.source.read(this.bufferField, 0x2000L) != -1L ? this.bufferField.read(arr_b, v, ((int)Math.min(v1, this.bufferField.size()))) : -1;
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        if(v < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.bufferField.size() != 0L || this.source.read(this.bufferField, 0x2000L) != -1L ? this.bufferField.read(buffer0, Math.min(v, this.bufferField.size())) : -1L;
    }

    @Override  // okio.BufferedSource
    public long readAll(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        long v = 0L;
        while(this.source.read(this.bufferField, 0x2000L) != -1L) {
            long v1 = this.bufferField.completeSegmentByteCount();
            if(v1 > 0L) {
                v += v1;
                sink0.write(this.bufferField, v1);
            }
        }
        if(this.bufferField.size() > 0L) {
            v += this.bufferField.size();
            sink0.write(this.bufferField, this.bufferField.size());
        }
        return v;
    }

    @Override  // okio.BufferedSource
    public byte readByte() {
        this.require(1L);
        return this.bufferField.readByte();
    }

    @Override  // okio.BufferedSource
    public byte[] readByteArray() {
        this.bufferField.writeAll(this.source);
        return new byte[0];
    }

    @Override  // okio.BufferedSource
    public byte[] readByteArray(long v) {
        this.require(v);
        return this.bufferField.readByteArray(v);
    }

    @Override  // okio.BufferedSource
    public ByteString readByteString() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteString();
    }

    @Override  // okio.BufferedSource
    public ByteString readByteString(long v) {
        this.require(v);
        return this.bufferField.readByteString(v);
    }

    @Override  // okio.BufferedSource
    public long readDecimalLong() {
        this.require(1L);
        for(long v = 0L; this.request(v + 1L); ++v) {
            int v1 = this.bufferField.getByte(v);
            if(v1 < 0x30 || v1 > 57) {
                int v2 = Long.compare(v, 0L);
                if(v2 != 0 || v1 != 45) {
                    if(v2 != 0) {
                        break;
                    }
                    String s = Integer.toString(v1, CharsKt.checkRadix(CharsKt.checkRadix(16)));
                    Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
                    throw new NumberFormatException("Expected a digit or \'-\' but was 0x" + s);
                }
            }
        }
        return this.bufferField.readDecimalLong();
    }

    @Override  // okio.BufferedSource
    public void readFully(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        try {
            this.require(v);
        }
        catch(EOFException eOFException0) {
            buffer0.writeAll(this.bufferField);
            throw eOFException0;
        }
        this.bufferField.readFully(buffer0, v);
    }

    @Override  // okio.BufferedSource
    public void readFully(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        try {
            this.require(((long)arr_b.length));
        }
        catch(EOFException eOFException0) {
            for(int v = 0; this.bufferField.size() > 0L; v += v1) {
                int v1 = this.bufferField.read(arr_b, v, ((int)this.bufferField.size()));
                if(v1 == -1) {
                    throw new AssertionError();
                }
            }
            throw eOFException0;
        }
        this.bufferField.readFully(arr_b);
    }

    @Override  // okio.BufferedSource
    public long readHexadecimalUnsignedLong() {
        this.require(1L);
        int v = 0;
        while(this.request(((long)(v + 1)))) {
            int v1 = this.bufferField.getByte(((long)v));
            if(v1 >= 0x30 && v1 <= 57 || v1 >= 97 && v1 <= 102 || v1 >= 65 && v1 <= 70) {
                ++v;
                continue;
            }
            if(v != 0) {
                break;
            }
            String s = Integer.toString(v1, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + s);
        }
        return this.bufferField.readHexadecimalUnsignedLong();
    }

    @Override  // okio.BufferedSource
    public int readInt() {
        this.require(4L);
        return this.bufferField.readInt();
    }

    @Override  // okio.BufferedSource
    public int readIntLe() {
        this.require(4L);
        return this.bufferField.readIntLe();
    }

    @Override  // okio.BufferedSource
    public long readLong() {
        this.require(8L);
        return this.bufferField.readLong();
    }

    @Override  // okio.BufferedSource
    public long readLongLe() {
        this.require(8L);
        return this.bufferField.readLongLe();
    }

    @Override  // okio.BufferedSource
    public short readShort() {
        this.require(2L);
        return this.bufferField.readShort();
    }

    @Override  // okio.BufferedSource
    public short readShortLe() {
        this.require(2L);
        return this.bufferField.readShortLe();
    }

    @Override  // okio.BufferedSource
    public String readString(long v, Charset charset0) {
        Intrinsics.checkNotNullParameter(charset0, "charset");
        this.require(v);
        return this.bufferField.readString(v, charset0);
    }

    @Override  // okio.BufferedSource
    public String readString(Charset charset0) {
        Intrinsics.checkNotNullParameter(charset0, "charset");
        this.bufferField.writeAll(this.source);
        return this.bufferField.readString(charset0);
    }

    @Override  // okio.BufferedSource
    public String readUtf8() {
        this.bufferField.writeAll(this.source);
        return "";
    }

    @Override  // okio.BufferedSource
    public String readUtf8(long v) {
        this.require(v);
        return this.bufferField.readUtf8(v);
    }

    @Override  // okio.BufferedSource
    public int readUtf8CodePoint() {
        this.require(1L);
        int v = this.bufferField.getByte(0L);
        if((v & 0xE0) == 0xC0) {
            this.require(2L);
            return this.bufferField.readUtf8CodePoint();
        }
        if((v & 0xF0) == 0xE0) {
            this.require(3L);
            return this.bufferField.readUtf8CodePoint();
        }
        if((v & 0xF8) == 0xF0) {
            this.require(4L);
        }
        return this.bufferField.readUtf8CodePoint();
    }

    @Override  // okio.BufferedSource
    public String readUtf8Line() {
        long v = this.indexOf(10);
        if(v == -1L) {
            return this.bufferField.size() == 0L ? null : this.readUtf8(this.bufferField.size());
        }
        return _BufferKt.readUtf8Line(this.bufferField, v);
    }

    @Override  // okio.BufferedSource
    public String readUtf8LineStrict() {
        return this.readUtf8LineStrict(0x7FFFFFFFFFFFFFFFL);
    }

    @Override  // okio.BufferedSource
    public String readUtf8LineStrict(long v) {
        if(v < 0L) {
            throw new IllegalArgumentException(("limit < 0: " + v).toString());
        }
        long v1 = v == 0x7FFFFFFFFFFFFFFFL ? 0x7FFFFFFFFFFFFFFFL : v + 1L;
        long v2 = this.indexOf(10, 0L, v1);
        if(v2 != -1L) {
            return _BufferKt.readUtf8Line(this.bufferField, v2);
        }
        if(v1 < 0x7FFFFFFFFFFFFFFFL && this.request(v1) && this.bufferField.getByte(v1 - 1L) == 13 && this.request(v1 + 1L) && this.bufferField.getByte(v1) == 10) {
            return _BufferKt.readUtf8Line(this.bufferField, v1);
        }
        Buffer buffer0 = new Buffer();
        this.bufferField.copyTo(buffer0, 0L, Math.min(0x20L, this.bufferField.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.bufferField.size(), v) + " content=" + buffer0.readByteString().hex() + '…');
    }

    @Override  // okio.BufferedSource
    public boolean request(long v) {
        if(v < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        while(this.bufferField.size() < v) {
            if(this.source.read(this.bufferField, 0x2000L) == -1L) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override  // okio.BufferedSource
    public void require(long v) {
        if(!this.request(v)) {
            throw new EOFException();
        }
    }

    @Override  // okio.BufferedSource
    public int select(Options options0) {
        Intrinsics.checkNotNullParameter(options0, "options");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
    alab1:
        while(true) {
            int v = _BufferKt.selectPrefix(this.bufferField, options0, true);
            switch(v) {
                case -2: {
                    if(this.source.read(this.bufferField, 0x2000L) != -1L) {
                        break;
                    }
                    break alab1;
                }
                case -1: {
                    return -1;
                }
                default: {
                    this.bufferField.skip(((long)options0.getByteStrings$okio()[v].size()));
                    return v;
                }
            }
        }
        return -1;
    }

    @Override  // okio.BufferedSource
    public void skip(long v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        while(v > 0L) {
            if(this.bufferField.size() == 0L && this.source.read(this.bufferField, 0x2000L) == -1L) {
                throw new EOFException();
            }
            long v1 = Math.min(v, this.bufferField.size());
            this.bufferField.skip(v1);
            v -= v1;
        }
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    @Override
    public String toString() {
        return "buffer(" + this.source + ')';
    }
}

