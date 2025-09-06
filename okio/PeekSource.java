package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\u0018\u0010\u0011\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\u00062\u0006\u0010\u0013\u001A\u00020\u000EH\u0016J\b\u0010\u0014\u001A\u00020\u0015H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lokio/PeekSource;", "Lokio/Source;", "upstream", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "buffer", "Lokio/Buffer;", "closed", "", "expectedPos", "", "expectedSegment", "Lokio/Segment;", "pos", "", "close", "", "read", "sink", "byteCount", "timeout", "Lokio/Timeout;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class PeekSource implements AutoCloseable, Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    public PeekSource(BufferedSource bufferedSource0) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "upstream");
        super();
        this.upstream = bufferedSource0;
        Buffer buffer0 = bufferedSource0.getBuffer();
        this.buffer = buffer0;
        this.expectedSegment = buffer0.head;
        this.expectedPos = buffer0.head == null ? -1 : buffer0.head.pos;
    }

    @Override  // okio.Source
    public void close() {
        this.closed = true;
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        Segment segment0 = this.expectedSegment;
        if(segment0 != null) {
            if(segment0 != this.buffer.head) {
                throw new IllegalStateException("Peek source is invalid because upstream source was used");
            }
            int v2 = this.expectedPos;
            Segment segment1 = this.buffer.head;
            Intrinsics.checkNotNull(segment1);
            if(v2 != segment1.pos) {
                throw new IllegalStateException("Peek source is invalid because upstream source was used");
            }
        }
        if(v1 == 0) {
            return 0L;
        }
        if(!this.upstream.request(this.pos + 1L)) {
            return -1L;
        }
        if(this.expectedSegment == null && this.buffer.head != null) {
            this.expectedSegment = this.buffer.head;
            Segment segment2 = this.buffer.head;
            Intrinsics.checkNotNull(segment2);
            this.expectedPos = segment2.pos;
        }
        long v3 = Math.min(v, this.buffer.size() - this.pos);
        this.buffer.copyTo(buffer0, this.pos, v3);
        this.pos += v3;
        return v3;
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.upstream.timeout();
    }
}

