package okio.internal;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u0005H\u0016J\u0014\u0010\u000E\u001A\u00020\u000F*\u00020\f2\u0006\u0010\u0010\u001A\u00020\u0005H\u0002R\u000E\u0010\t\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokio/internal/FixedLengthSource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "size", "", "truncate", "", "(Lokio/Source;JZ)V", "bytesReceived", "read", "sink", "Lokio/Buffer;", "byteCount", "truncateToSize", "", "newSize", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class FixedLengthSource extends ForwardingSource {
    private long bytesReceived;
    private final long size;
    private final boolean truncate;

    public FixedLengthSource(Source source0, long v, boolean z) {
        Intrinsics.checkNotNullParameter(source0, "delegate");
        super(source0);
        this.size = v;
        this.truncate = z;
    }

    @Override  // okio.ForwardingSource
    public long read(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        long v1 = this.bytesReceived;
        long v2 = this.size;
        if(v1 > v2) {
            v = 0L;
        }
        else if(this.truncate) {
            long v3 = v2 - v1;
            if(v3 == 0L) {
                return -1L;
            }
            v = Math.min(v, v3);
        }
        long v4 = super.read(buffer0, v);
        int v5 = Long.compare(v4, -1L);
        if(v5 != 0) {
            this.bytesReceived += v4;
        }
        long v6 = this.bytesReceived;
        long v7 = this.size;
        if(v6 < v7 && v5 == 0 || v6 > v7) {
            if(v4 > 0L && v6 > v7) {
                this.truncateToSize(buffer0, buffer0.size() - (this.bytesReceived - this.size));
            }
            throw new IOException("expected " + this.size + " bytes but got " + this.bytesReceived);
        }
        return v4;
    }

    private final void truncateToSize(Buffer buffer0, long v) {
        Buffer buffer1 = new Buffer();
        buffer1.writeAll(buffer0);
        buffer0.write(buffer1, v);
        buffer1.clear();
    }
}

