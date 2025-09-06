package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.RealBufferedSink;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0080\b\u001A\r\u0010\u0003\u001A\u00020\u0004*\u00020\u0002H\u0080\b\u001A\r\u0010\u0005\u001A\u00020\u0004*\u00020\u0002H\u0080\b\u001A\r\u0010\u0006\u001A\u00020\u0001*\u00020\u0002H\u0080\b\u001A\r\u0010\u0007\u001A\u00020\b*\u00020\u0002H\u0080\b\u001A\r\u0010\t\u001A\u00020\n*\u00020\u0002H\u0080\b\u001A\u0015\u0010\u000B\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001A\u00020\rH\u0080\b\u001A%\u0010\u000B\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u000FH\u0080\b\u001A\u001D\u0010\u000B\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001A\u00020\u00112\u0006\u0010\u0010\u001A\u00020\u0012H\u0080\b\u001A\u0015\u0010\u000B\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001A\u00020\u0014H\u0080\b\u001A%\u0010\u000B\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u000FH\u0080\b\u001A\u001D\u0010\u000B\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001A\u00020\u00152\u0006\u0010\u0010\u001A\u00020\u0012H\u0080\b\u001A\u0015\u0010\u0016\u001A\u00020\u0012*\u00020\u00022\u0006\u0010\f\u001A\u00020\u0015H\u0080\b\u001A\u0015\u0010\u0017\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u0018\u001A\u00020\u000FH\u0080\b\u001A\u0015\u0010\u0019\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0012H\u0080\b\u001A\u0015\u0010\u001B\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0012H\u0080\b\u001A\u0015\u0010\u001C\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u001D\u001A\u00020\u000FH\u0080\b\u001A\u0015\u0010\u001E\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u001D\u001A\u00020\u000FH\u0080\b\u001A\u0015\u0010\u001F\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0012H\u0080\b\u001A\u0015\u0010 \u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0012H\u0080\b\u001A\u0015\u0010!\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001A\u00020\u000FH\u0080\b\u001A\u0015\u0010#\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001A\u00020\u000FH\u0080\b\u001A\u0015\u0010$\u001A\u00020\u0004*\u00020\u00022\u0006\u0010%\u001A\u00020\nH\u0080\b\u001A%\u0010$\u001A\u00020\u0004*\u00020\u00022\u0006\u0010%\u001A\u00020\n2\u0006\u0010&\u001A\u00020\u000F2\u0006\u0010\'\u001A\u00020\u000FH\u0080\b\u001A\u0015\u0010(\u001A\u00020\u0004*\u00020\u00022\u0006\u0010)\u001A\u00020\u000FH\u0080\b\u00A8\u0006*"}, d2 = {"commonClose", "", "Lokio/RealBufferedSink;", "commonEmit", "Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "commonTimeout", "Lokio/Timeout;", "commonToString", "", "commonWrite", "source", "", "offset", "", "byteCount", "Lokio/Buffer;", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "b", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "commonWriteShort", "s", "commonWriteShortLe", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _RealBufferedSinkKt {
    public static final void commonClose(RealBufferedSink realBufferedSink0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(!realBufferedSink0.closed) {
            try {
                if(realBufferedSink0.bufferField.size() > 0L) {
                    realBufferedSink0.sink.write(realBufferedSink0.bufferField, realBufferedSink0.bufferField.size());
                }
                throwable0 = null;
            }
            catch(Throwable throwable0) {
            }
            try {
                realBufferedSink0.sink.close();
            }
            catch(Throwable throwable1) {
                if(throwable0 == null) {
                    throwable0 = throwable1;
                }
            }
            realBufferedSink0.closed = true;
            if(throwable0 != null) {
                throw throwable0;
            }
        }
    }

    public static final BufferedSink commonEmit(RealBufferedSink realBufferedSink0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        long v = realBufferedSink0.bufferField.size();
        if(v > 0L) {
            realBufferedSink0.sink.write(realBufferedSink0.bufferField, v);
        }
        return realBufferedSink0;
    }

    public static final BufferedSink commonEmitCompleteSegments(RealBufferedSink realBufferedSink0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        long v = realBufferedSink0.bufferField.completeSegmentByteCount();
        if(v > 0L) {
            realBufferedSink0.sink.write(realBufferedSink0.bufferField, v);
        }
        return realBufferedSink0;
    }

    public static final void commonFlush(RealBufferedSink realBufferedSink0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        if(realBufferedSink0.bufferField.size() > 0L) {
            realBufferedSink0.sink.write(realBufferedSink0.bufferField, realBufferedSink0.bufferField.size());
        }
        realBufferedSink0.sink.flush();
    }

    public static final Timeout commonTimeout(RealBufferedSink realBufferedSink0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        return realBufferedSink0.sink.timeout();
    }

    public static final String commonToString(RealBufferedSink realBufferedSink0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        return "buffer(" + realBufferedSink0.sink + ')';
    }

    public static final BufferedSink commonWrite(RealBufferedSink realBufferedSink0, ByteString byteString0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.write(byteString0);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(RealBufferedSink realBufferedSink0, ByteString byteString0, int v, int v1) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.write(byteString0, v, v1);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(RealBufferedSink realBufferedSink0, Source source0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(source0, "source");
        while(v > 0L) {
            long v1 = source0.read(realBufferedSink0.bufferField, v);
            if(v1 == -1L) {
                throw new EOFException();
            }
            v -= v1;
            realBufferedSink0.emitCompleteSegments();
        }
        return realBufferedSink0;
    }

    public static final BufferedSink commonWrite(RealBufferedSink realBufferedSink0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "source");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.write(arr_b);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(RealBufferedSink realBufferedSink0, byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "source");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.write(arr_b, v, v1);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final void commonWrite(RealBufferedSink realBufferedSink0, Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(buffer0, "source");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.write(buffer0, v);
        realBufferedSink0.emitCompleteSegments();
    }

    public static final long commonWriteAll(RealBufferedSink realBufferedSink0, Source source0) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(source0, "source");
        long v = 0L;
        long v1;
        while((v1 = source0.read(realBufferedSink0.bufferField, 0x2000L)) != -1L) {
            v += v1;
            realBufferedSink0.emitCompleteSegments();
        }
        return v;
    }

    public static final BufferedSink commonWriteByte(RealBufferedSink realBufferedSink0, int v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeByte(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteDecimalLong(RealBufferedSink realBufferedSink0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeDecimalLong(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteHexadecimalUnsignedLong(RealBufferedSink realBufferedSink0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeHexadecimalUnsignedLong(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteInt(RealBufferedSink realBufferedSink0, int v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeInt(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteIntLe(RealBufferedSink realBufferedSink0, int v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeIntLe(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLong(RealBufferedSink realBufferedSink0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeLong(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLongLe(RealBufferedSink realBufferedSink0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeLongLe(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShort(RealBufferedSink realBufferedSink0, int v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeShort(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShortLe(RealBufferedSink realBufferedSink0, int v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeShortLe(v);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(RealBufferedSink realBufferedSink0, String s) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(s, "string");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeUtf8(s);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(RealBufferedSink realBufferedSink0, String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        Intrinsics.checkNotNullParameter(s, "string");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeUtf8(s, v, v1);
        return realBufferedSink0.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8CodePoint(RealBufferedSink realBufferedSink0, int v) {
        Intrinsics.checkNotNullParameter(realBufferedSink0, "<this>");
        if(realBufferedSink0.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink0.bufferField.writeUtf8CodePoint(v);
        return realBufferedSink0.emitCompleteSegments();
    }
}

