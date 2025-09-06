package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0006\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001A\u00020\rH\u0016J\u0018\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u000FH\u0016J\u0016\u0010\u0013\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u000FJ\u0006\u0010\u0014\u001A\u00020\u000BJ\b\u0010\u0015\u001A\u00020\rH\u0002J\b\u0010\u0016\u001A\u00020\u0017H\u0016R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokio/InflaterSource;", "Lokio/Source;", "source", "inflater", "Ljava/util/zip/Inflater;", "(Lokio/Source;Ljava/util/zip/Inflater;)V", "Lokio/BufferedSource;", "(Lokio/BufferedSource;Ljava/util/zip/Inflater;)V", "bufferBytesHeldByInflater", "", "closed", "", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "readOrInflate", "refill", "releaseBytesAfterInflate", "timeout", "Lokio/Timeout;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class InflaterSource implements AutoCloseable, Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final Inflater inflater;
    private final BufferedSource source;

    public InflaterSource(BufferedSource bufferedSource0, Inflater inflater0) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        Intrinsics.checkNotNullParameter(inflater0, "inflater");
        super();
        this.source = bufferedSource0;
        this.inflater = inflater0;
    }

    public InflaterSource(Source source0, Inflater inflater0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        Intrinsics.checkNotNullParameter(inflater0, "inflater");
        this(Okio.buffer(source0), inflater0);
    }

    @Override  // okio.Source
    public void close() throws IOException {
        if(this.closed) {
            return;
        }
        this.inflater.end();
        this.closed = true;
        this.source.close();
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        while(true) {
            long v1 = this.readOrInflate(buffer0, v);
            if(v1 > 0L) {
                return v1;
            }
            if(this.inflater.finished() || this.inflater.needsDictionary()) {
                break;
            }
            if(this.source.exhausted()) {
                throw new EOFException("source exhausted prematurely");
            }
        }
        return -1L;
    }

    public final long readOrInflate(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(!this.closed) {
            if(v1 == 0) {
                return 0L;
            }
            try {
                Segment segment0 = buffer0.writableSegment$okio(1);
                int v2 = (int)Math.min(v, 0x2000 - segment0.limit);
                this.refill();
                int v3 = this.inflater.inflate(segment0.data, segment0.limit, v2);
                this.releaseBytesAfterInflate();
                if(v3 > 0) {
                    segment0.limit += v3;
                    buffer0.setSize$okio(buffer0.size() + ((long)v3));
                    return (long)v3;
                }
                if(segment0.pos == segment0.limit) {
                    buffer0.head = segment0.pop();
                    SegmentPool.recycle(segment0);
                }
                return 0L;
            }
            catch(DataFormatException dataFormatException0) {
                throw new IOException(dataFormatException0);
            }
        }
        throw new IllegalStateException("closed");
    }

    public final boolean refill() throws IOException {
        if(!this.inflater.needsInput()) {
            return false;
        }
        if(this.source.exhausted()) {
            return true;
        }
        Segment segment0 = this.source.getBuffer().head;
        Intrinsics.checkNotNull(segment0);
        this.bufferBytesHeldByInflater = segment0.limit - segment0.pos;
        this.inflater.setInput(segment0.data, segment0.pos, this.bufferBytesHeldByInflater);
        return false;
    }

    private final void releaseBytesAfterInflate() {
        int v = this.bufferBytesHeldByInflater;
        if(v == 0) {
            return;
        }
        int v1 = v - this.inflater.getRemaining();
        this.bufferBytesHeldByInflater -= v1;
        this.source.skip(((long)v1));
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }
}

