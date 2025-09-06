package okio;

import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\t\u001A\u00020\bH\u0016J\b\u0010\u0004\u001A\u00020\u0005H\u0016J\b\u0010\n\u001A\u00020\u000BH\u0016J\u0018\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokio/OutputStreamSink;", "Lokio/Sink;", "out", "Ljava/io/OutputStream;", "timeout", "Lokio/Timeout;", "(Ljava/io/OutputStream;Lokio/Timeout;)V", "close", "", "flush", "toString", "", "write", "source", "Lokio/Buffer;", "byteCount", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class OutputStreamSink implements AutoCloseable, Sink {
    private final OutputStream out;
    private final Timeout timeout;

    public OutputStreamSink(OutputStream outputStream0, Timeout timeout0) {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        Intrinsics.checkNotNullParameter(timeout0, "timeout");
        super();
        this.out = outputStream0;
        this.timeout = timeout0;
    }

    @Override  // okio.Sink
    public void close() {
        this.out.close();
    }

    @Override  // okio.Sink
    public void flush() {
        this.out.flush();
    }

    @Override  // okio.Sink
    public Timeout timeout() {
        return this.timeout;
    }

    @Override
    public String toString() {
        return "sink(" + this.out + ')';
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v);
        while(v > 0L) {
            this.timeout.throwIfReached();
            Segment segment0 = buffer0.head;
            Intrinsics.checkNotNull(segment0);
            int v1 = (int)Math.min(v, segment0.limit - segment0.pos);
            this.out.write(segment0.data, segment0.pos, v1);
            segment0.pos += v1;
            v -= (long)v1;
            buffer0.setSize$okio(buffer0.size() - ((long)v1));
            if(segment0.pos == segment0.limit) {
                buffer0.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
        }
    }
}

