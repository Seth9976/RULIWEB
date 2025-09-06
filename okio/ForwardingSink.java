package okio;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0005\u001A\u00020\u0006H\u0016J\r\u0010\u0002\u001A\u00020\u0001H\u0007¢\u0006\u0002\b\u0007J\b\u0010\b\u001A\u00020\u0006H\u0016J\b\u0010\t\u001A\u00020\nH\u0016J\b\u0010\u000B\u001A\u00020\fH\u0016J\u0018\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0011H\u0016R\u0013\u0010\u0002\u001A\u00020\u00018\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0004¨\u0006\u0012"}, d2 = {"Lokio/ForwardingSink;", "Lokio/Sink;", "delegate", "(Lokio/Sink;)V", "()Lokio/Sink;", "close", "", "-deprecated_delegate", "flush", "timeout", "Lokio/Timeout;", "toString", "", "write", "source", "Lokio/Buffer;", "byteCount", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public abstract class ForwardingSink implements AutoCloseable, Sink {
    private final Sink delegate;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "delegate", imports = {}))
    public final Sink -deprecated_delegate() {
        return this.delegate;
    }

    public ForwardingSink(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "delegate");
        super();
        this.delegate = sink0;
    }

    @Override  // okio.Sink
    public void close() throws IOException {
        this.delegate.close();
    }

    public final Sink delegate() {
        return this.delegate;
    }

    @Override  // okio.Sink
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override  // okio.Sink
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '(' + this.delegate + ')';
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        this.delegate.write(buffer0, v);
    }
}

