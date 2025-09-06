package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001A\u00020\u0006H\u0016J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\b\u0010\u0010\u001A\u00020\u0001H\u0016J\b\u0010\u0011\u001A\u00020\u0001H\u0016J\b\u0010\u0012\u001A\u00020\u000FH\u0016J\b\u0010\u0013\u001A\u00020\rH\u0016J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\b\u0010\u0016\u001A\u00020\u0017H\u0016J\b\u0010\u0018\u001A\u00020\u0019H\u0016J\u0010\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u001DH\u0016J\u0010\u0010\u001A\u001A\u00020\u00012\u0006\u0010\u001C\u001A\u00020\u001EH\u0016J \u0010\u001A\u001A\u00020\u00012\u0006\u0010\u001C\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\u001B2\u0006\u0010 \u001A\u00020\u001BH\u0016J\u0018\u0010\u001A\u001A\u00020\u000F2\u0006\u0010\u001C\u001A\u00020\u00062\u0006\u0010 \u001A\u00020!H\u0016J\u0010\u0010\u001A\u001A\u00020\u00012\u0006\u0010\"\u001A\u00020#H\u0016J \u0010\u001A\u001A\u00020\u00012\u0006\u0010\"\u001A\u00020#2\u0006\u0010\u001F\u001A\u00020\u001B2\u0006\u0010 \u001A\u00020\u001BH\u0016J\u0018\u0010\u001A\u001A\u00020\u00012\u0006\u0010\u001C\u001A\u00020$2\u0006\u0010 \u001A\u00020!H\u0016J\u0010\u0010%\u001A\u00020!2\u0006\u0010\u001C\u001A\u00020$H\u0016J\u0010\u0010&\u001A\u00020\u00012\u0006\u0010\'\u001A\u00020\u001BH\u0016J\u0010\u0010(\u001A\u00020\u00012\u0006\u0010)\u001A\u00020!H\u0016J\u0010\u0010*\u001A\u00020\u00012\u0006\u0010)\u001A\u00020!H\u0016J\u0010\u0010+\u001A\u00020\u00012\u0006\u0010,\u001A\u00020\u001BH\u0016J\u0010\u0010-\u001A\u00020\u00012\u0006\u0010,\u001A\u00020\u001BH\u0016J\u0010\u0010.\u001A\u00020\u00012\u0006\u0010)\u001A\u00020!H\u0016J\u0010\u0010/\u001A\u00020\u00012\u0006\u0010)\u001A\u00020!H\u0016J\u0010\u00100\u001A\u00020\u00012\u0006\u00101\u001A\u00020\u001BH\u0016J\u0010\u00102\u001A\u00020\u00012\u0006\u00101\u001A\u00020\u001BH\u0016J\u0018\u00103\u001A\u00020\u00012\u0006\u00104\u001A\u00020\u00192\u0006\u00105\u001A\u000206H\u0016J(\u00103\u001A\u00020\u00012\u0006\u00104\u001A\u00020\u00192\u0006\u00107\u001A\u00020\u001B2\u0006\u00108\u001A\u00020\u001B2\u0006\u00105\u001A\u000206H\u0016J\u0010\u00109\u001A\u00020\u00012\u0006\u00104\u001A\u00020\u0019H\u0016J \u00109\u001A\u00020\u00012\u0006\u00104\u001A\u00020\u00192\u0006\u00107\u001A\u00020\u001B2\u0006\u00108\u001A\u00020\u001BH\u0016J\u0010\u0010:\u001A\u00020\u00012\u0006\u0010;\u001A\u00020\u001BH\u0016R\u001B\u0010\u0005\u001A\u00020\u00068\u00D6\u0002X\u0096\u0004\u00A2\u0006\f\u0012\u0004\b\u0007\u0010\b\u001A\u0004\b\t\u0010\nR\u0010\u0010\u000B\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\f\u001A\u00020\r8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006<"}, d2 = {"Lokio/RealBufferedSink;", "Lokio/BufferedSink;", "sink", "Lokio/Sink;", "(Lokio/Sink;)V", "buffer", "Lokio/Buffer;", "getBuffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "emit", "emitCompleteSegments", "flush", "isOpen", "outputStream", "Ljava/io/OutputStream;", "timeout", "Lokio/Timeout;", "toString", "", "write", "", "source", "Ljava/nio/ByteBuffer;", "", "offset", "byteCount", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "writeAll", "writeByte", "b", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "charset", "Ljava/nio/charset/Charset;", "beginIndex", "endIndex", "writeUtf8", "writeUtf8CodePoint", "codePoint", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class RealBufferedSink implements AutoCloseable, BufferedSink {
    public final Buffer bufferField;
    public boolean closed;
    public final Sink sink;

    public RealBufferedSink(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        super();
        this.sink = sink0;
        this.bufferField = new Buffer();
    }

    @Override  // okio.BufferedSink
    public Buffer buffer() {
        return this.bufferField;
    }

    @Override  // okio.Sink
    public void close() {
        if(!this.closed) {
            try {
                if(this.bufferField.size() > 0L) {
                    this.sink.write(this.bufferField, this.bufferField.size());
                }
                throwable0 = null;
            }
            catch(Throwable throwable0) {
            }
            try {
                this.sink.close();
            }
            catch(Throwable throwable1) {
                if(throwable0 == null) {
                    throwable0 = throwable1;
                }
            }
            this.closed = true;
            if(throwable0 != null) {
                throw throwable0;
            }
        }
    }

    @Override  // okio.BufferedSink
    public BufferedSink emit() {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        long v = this.bufferField.size();
        if(v > 0L) {
            this.sink.write(this.bufferField, v);
        }
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink emitCompleteSegments() {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        long v = this.bufferField.completeSegmentByteCount();
        if(v > 0L) {
            this.sink.write(this.bufferField, v);
        }
        return this;
    }

    @Override  // okio.BufferedSink
    public void flush() {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        if(this.bufferField.size() > 0L) {
            this.sink.write(this.bufferField, this.bufferField.size());
        }
        this.sink.flush();
    }

    @Override  // okio.BufferedSink
    public Buffer getBuffer() {
        return this.bufferField;
    }

    public static void getBuffer$annotations() {
    }

    @Override
    public boolean isOpen() {
        return !this.closed;
    }

    @Override  // okio.BufferedSink
    public OutputStream outputStream() {
        return new AutoCloseable() {
            @Override
            public void close() {
                RealBufferedSink.this.close();
            }

            @Override
            public void flush() {
                if(!RealBufferedSink.this.closed) {
                    RealBufferedSink.this.flush();
                }
            }

            @Override
            public String toString() {
                return RealBufferedSink.this + ".outputStream()";
            }

            @Override
            public void write(int v) {
                if(RealBufferedSink.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSink.this.bufferField.writeByte(((int)(((byte)v))));
                RealBufferedSink.this.emitCompleteSegments();
            }

            @Override
            public void write(byte[] arr_b, int v, int v1) {
                Intrinsics.checkNotNullParameter(arr_b, "data");
                if(RealBufferedSink.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSink.this.bufferField.write(arr_b, v, v1);
                RealBufferedSink.this.emitCompleteSegments();
            }
        };
    }

    @Override  // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @Override
    public String toString() {
        return "buffer(" + this.sink + ')';
    }

    @Override
    public int write(ByteBuffer byteBuffer0) {
        Intrinsics.checkNotNullParameter(byteBuffer0, "source");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        int v = this.bufferField.write(byteBuffer0);
        this.emitCompleteSegments();
        return v;
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.write(byteString0);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(ByteString byteString0, int v, int v1) {
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.write(byteString0, v, v1);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(Source source0, long v) {
        Intrinsics.checkNotNullParameter(source0, "source");
        while(v > 0L) {
            long v1 = source0.read(this.bufferField, v);
            if(v1 == -1L) {
                throw new EOFException();
            }
            v -= v1;
            this.emitCompleteSegments();
        }
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.write(arr_b);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.write(arr_b, v, v1);
        return this.emitCompleteSegments();
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.write(buffer0, v);
        this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public long writeAll(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        long v = 0L;
        long v1;
        while((v1 = source0.read(this.bufferField, 0x2000L)) != -1L) {
            v += v1;
            this.emitCompleteSegments();
        }
        return v;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeByte(int v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeByte(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeDecimalLong(long v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeDecimalLong(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeHexadecimalUnsignedLong(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeInt(int v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeInt(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeIntLe(int v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeIntLe(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeLong(long v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeLong(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeLongLe(long v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeLongLe(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeShort(int v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeShort(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeShortLe(int v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeShortLe(v);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeString(String s, int v, int v1, Charset charset0) {
        Intrinsics.checkNotNullParameter(s, "string");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeString(s, v, v1, charset0);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeString(String s, Charset charset0) {
        Intrinsics.checkNotNullParameter(s, "string");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeString(s, charset0);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeUtf8(String s) {
        Intrinsics.checkNotNullParameter(s, "string");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeUtf8(s);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeUtf8(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "string");
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeUtf8(s, v, v1);
        return this.emitCompleteSegments();
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeUtf8CodePoint(int v) {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        this.bufferField.writeUtf8CodePoint(v);
        return this.emitCompleteSegments();
    }
}

