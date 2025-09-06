package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0016J\b\u0010\u0005\u001A\u00020\u0004H\u0016J\b\u0010\u0006\u001A\u00020\u0007H\u0016J\u0018\u0010\b\u001A\u00020\u00042\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lokio/BlackholeSink;", "Lokio/Sink;", "()V", "close", "", "flush", "timeout", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class BlackholeSink implements AutoCloseable, Sink {
    @Override  // okio.Sink
    public void close() {
    }

    @Override  // okio.Sink
    public void flush() {
    }

    @Override  // okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        buffer0.skip(v);
    }
}

