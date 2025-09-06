package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0006\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001A\u00020\u000BH\u0016J\u0010\u0010\f\u001A\u00020\u000B2\u0006\u0010\r\u001A\u00020\tH\u0003J\r\u0010\u000E\u001A\u00020\u000BH\u0000¢\u0006\u0002\b\u000FJ\b\u0010\u0010\u001A\u00020\u000BH\u0016J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\u0018\u0010\u0015\u001A\u00020\u000B2\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0019H\u0016R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Lokio/DeflaterSink;", "Lokio/Sink;", "sink", "deflater", "Ljava/util/zip/Deflater;", "(Lokio/Sink;Ljava/util/zip/Deflater;)V", "Lokio/BufferedSink;", "(Lokio/BufferedSink;Ljava/util/zip/Deflater;)V", "closed", "", "close", "", "deflate", "syncFlush", "finishDeflate", "finishDeflate$okio", "flush", "timeout", "Lokio/Timeout;", "toString", "", "write", "source", "Lokio/Buffer;", "byteCount", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class DeflaterSink implements AutoCloseable, Sink {
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;

    public DeflaterSink(BufferedSink bufferedSink0, Deflater deflater0) {
        Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
        Intrinsics.checkNotNullParameter(deflater0, "deflater");
        super();
        this.sink = bufferedSink0;
        this.deflater = deflater0;
    }

    public DeflaterSink(Sink sink0, Deflater deflater0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        Intrinsics.checkNotNullParameter(deflater0, "deflater");
        this(Okio.buffer(sink0), deflater0);
    }

    @Override  // okio.Sink
    public void close() throws IOException {
        if(!this.closed) {
            try {
                this.finishDeflate$okio();
                throwable0 = null;
            }
            catch(Throwable throwable0) {
            }
            try {
                this.deflater.end();
            }
            catch(Throwable throwable1) {
                if(throwable0 == null) {
                    throwable0 = throwable1;
                }
            }
            try {
                this.sink.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
            }
            this.closed = true;
            if(throwable0 != null) {
                throw throwable0;
            }
        }
    }

    private final void deflate(boolean z) {
        Segment segment0;
        Buffer buffer0 = this.sink.getBuffer();
        while(true) {
            segment0 = buffer0.writableSegment$okio(1);
            int v = z ? this.deflater.deflate(segment0.data, segment0.limit, 0x2000 - segment0.limit, 2) : this.deflater.deflate(segment0.data, segment0.limit, 0x2000 - segment0.limit);
            if(v > 0) {
                segment0.limit += v;
                buffer0.setSize$okio(buffer0.size() + ((long)v));
                this.sink.emitCompleteSegments();
            }
            else if(this.deflater.needsInput()) {
                break;
            }
        }
        if(segment0.pos == segment0.limit) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
    }

    public final void finishDeflate$okio() {
        this.deflater.finish();
        this.deflate(false);
    }

    @Override  // okio.Sink
    public void flush() throws IOException {
        this.deflate(true);
        this.sink.flush();
    }

    @Override  // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @Override
    public String toString() {
        return "DeflaterSink(" + this.sink + ')';
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v);
        while(v > 0L) {
            Segment segment0 = buffer0.head;
            Intrinsics.checkNotNull(segment0);
            int v1 = (int)Math.min(v, segment0.limit - segment0.pos);
            this.deflater.setInput(segment0.data, segment0.pos, v1);
            this.deflate(false);
            buffer0.setSize$okio(buffer0.size() - ((long)v1));
            segment0.pos += v1;
            if(segment0.pos == segment0.limit) {
                buffer0.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
            v -= (long)v1;
        }
    }
}

