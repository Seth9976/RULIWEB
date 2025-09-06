package okio;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\u0016J\u0018\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\nH\u0016J\b\u0010\u0004\u001A\u00020\u0005H\u0016J\b\u0010\u000E\u001A\u00020\u000FH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokio/InputStreamSource;", "Lokio/Source;", "input", "Ljava/io/InputStream;", "timeout", "Lokio/Timeout;", "(Ljava/io/InputStream;Lokio/Timeout;)V", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "toString", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
class InputStreamSource implements AutoCloseable, Source {
    private final InputStream input;
    private final Timeout timeout;

    public InputStreamSource(InputStream inputStream0, Timeout timeout0) {
        Intrinsics.checkNotNullParameter(inputStream0, "input");
        Intrinsics.checkNotNullParameter(timeout0, "timeout");
        super();
        this.input = inputStream0;
        this.timeout = timeout0;
    }

    @Override  // okio.Source
    public void close() {
        this.input.close();
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        int v1 = Long.compare(v, 0L);
        if(v1 == 0) {
            return 0L;
        }
        if(v1 >= 0) {
            try {
                this.timeout.throwIfReached();
                Segment segment0 = buffer0.writableSegment$okio(1);
                int v2 = this.input.read(segment0.data, segment0.limit, ((int)Math.min(v, 0x2000 - segment0.limit)));
                if(v2 == -1) {
                    if(segment0.pos == segment0.limit) {
                        buffer0.head = segment0.pop();
                        SegmentPool.recycle(segment0);
                    }
                    return -1L;
                }
                segment0.limit += v2;
                buffer0.setSize$okio(buffer0.size() + ((long)v2));
                return (long)v2;
            }
            catch(AssertionError assertionError0) {
                if(!Okio.isAndroidGetsocknameError(assertionError0)) {
                    throw assertionError0;
                }
                throw new IOException(assertionError0);
            }
        }
        throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.timeout;
    }

    @Override
    public String toString() {
        return "source(" + this.input + ')';
    }
}

