package okio;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0005\u001A\u00020\u0006H\u0016J\r\u0010\u0002\u001A\u00020\u0001H\u0007¢\u0006\u0002\b\u0007J\u0018\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\tH\u0016J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016R\u0013\u0010\u0002\u001A\u00020\u00018\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0004¨\u0006\u0011"}, d2 = {"Lokio/ForwardingSource;", "Lokio/Source;", "delegate", "(Lokio/Source;)V", "()Lokio/Source;", "close", "", "-deprecated_delegate", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "toString", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public abstract class ForwardingSource implements AutoCloseable, Source {
    private final Source delegate;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "delegate", imports = {}))
    public final Source -deprecated_delegate() {
        return this.delegate;
    }

    public ForwardingSource(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "delegate");
        super();
        this.delegate = source0;
    }

    @Override  // okio.Source
    public void close() throws IOException {
        this.delegate.close();
    }

    public final Source delegate() {
        return this.delegate;
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        return this.delegate.read(buffer0, v);
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '(' + this.delegate + ')';
    }
}

