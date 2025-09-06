package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000F\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001D\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u0004H\u0000¢\u0006\u0002\b\fJ$\u0010\u0006\u001A\u00020\r2\u0006\u0010\u0006\u001A\u00020\u00042\b\b\u0002\u0010\b\u001A\u00020\u00042\b\b\u0002\u0010\u0007\u001A\u00020\u0004H\u0007J\u000E\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u000E\u001A\u00020\u000FJ\u000E\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0010\u001A\u00020\u0011J\u0015\u0010\u0012\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u0004H\u0000¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001A\u00020\r2\u0006\u0010\u0015\u001A\u00020\u0004H\u0002J\f\u0010\u0016\u001A\u00020\u0004*\u00020\u0004H\u0002J\f\u0010\u0017\u001A\u00020\u0004*\u00020\u0004H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokio/Throttler;", "", "()V", "allocatedUntil", "", "(J)V", "bytesPerSecond", "maxByteCount", "waitByteCount", "byteCountOrWaitNanos", "now", "byteCount", "byteCountOrWaitNanos$okio", "", "sink", "Lokio/Sink;", "source", "Lokio/Source;", "take", "take$okio", "waitNanos", "nanosToWait", "bytesToNanos", "nanosToBytes", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Throttler {
    private long allocatedUntil;
    private long bytesPerSecond;
    private long maxByteCount;
    private long waitByteCount;

    public Throttler() {
        this(System.nanoTime());
    }

    public Throttler(long v) {
        this.allocatedUntil = v;
        this.waitByteCount = 0x2000L;
        this.maxByteCount = 0x40000L;
    }

    public final long byteCountOrWaitNanos$okio(long v, long v1) {
        if(this.bytesPerSecond == 0L) {
            return v1;
        }
        long v2 = Math.max(this.allocatedUntil - v, 0L);
        long v3 = this.maxByteCount - this.nanosToBytes(v2);
        if(v3 >= v1) {
            this.allocatedUntil = v + v2 + this.bytesToNanos(v1);
            return v1;
        }
        long v4 = this.waitByteCount;
        if(v3 >= v4) {
            this.allocatedUntil = v + this.bytesToNanos(this.maxByteCount);
            return v3;
        }
        long v5 = Math.min(v4, v1);
        long v6 = v2 + this.bytesToNanos(v5 - this.maxByteCount);
        if(v6 == 0L) {
            this.allocatedUntil = v + this.bytesToNanos(this.maxByteCount);
            return v5;
        }
        return -v6;
    }

    public final void bytesPerSecond(long v) {
        Throttler.bytesPerSecond$default(this, v, 0L, 0L, 6, null);
    }

    public final void bytesPerSecond(long v, long v1) {
        Throttler.bytesPerSecond$default(this, v, v1, 0L, 4, null);
    }

    public final void bytesPerSecond(long v, long v1, long v2) {
        synchronized(this) {
            if(v >= 0L && (v1 > 0L && v2 >= v1)) {
                this.bytesPerSecond = v;
                this.waitByteCount = v1;
                this.maxByteCount = v2;
                this.notifyAll();
                return;
            }
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static void bytesPerSecond$default(Throttler throttler0, long v, long v1, long v2, int v3, Object object0) {
        if((v3 & 2) != 0) {
            v1 = throttler0.waitByteCount;
        }
        if((v3 & 4) != 0) {
            v2 = throttler0.maxByteCount;
        }
        throttler0.bytesPerSecond(v, v1, v2);
    }

    private final long bytesToNanos(long v) {
        return v * 1000000000L / this.bytesPerSecond;
    }

    private final long nanosToBytes(long v) {
        return v * this.bytesPerSecond / 1000000000L;
    }

    public final Sink sink(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        return new ForwardingSink(this) {
            @Override  // okio.ForwardingSink
            public void write(Buffer buffer0, long v) throws IOException {
                Intrinsics.checkNotNullParameter(buffer0, "source");
                while(v > 0L) {
                    try {
                        long v1 = Throttler.this.take$okio(v);
                        super.write(buffer0, v1);
                        v -= v1;
                    }
                    catch(InterruptedException unused_ex) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException("interrupted");
                    }
                }
            }
        };
    }

    public final Source source(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        return new ForwardingSource(this) {
            @Override  // okio.ForwardingSource
            public long read(Buffer buffer0, long v) {
                Intrinsics.checkNotNullParameter(buffer0, "sink");
                try {
                    return super.read(buffer0, Throttler.this.take$okio(v));
                }
                catch(InterruptedException unused_ex) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException("interrupted");
                }
            }
        };
    }

    public final long take$okio(long v) {
        if(v > 0L) {
            synchronized(this) {
                long v2;
                while((v2 = this.byteCountOrWaitNanos$okio(System.nanoTime(), v)) < 0L) {
                    this.waitNanos(-v2);
                }
                return v2;
            }
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    private final void waitNanos(long v) {
        this.wait(v / 1000000L, ((int)(v - 1000000L * (v / 1000000L))));
    }
}

