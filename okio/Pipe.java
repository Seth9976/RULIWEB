package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jeb.synthetic.FIN;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010!\u001A\u00020\"J\u000E\u0010#\u001A\u00020\"2\u0006\u0010\u0017\u001A\u00020\u0010J\r\u0010\u0017\u001A\u00020\u0010H\u0007¢\u0006\u0002\b$J\r\u0010\u001B\u001A\u00020\u001CH\u0007¢\u0006\u0002\b%J&\u0010&\u001A\u00020\"*\u00020\u00102\u0017\u0010\'\u001A\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\"0(¢\u0006\u0002\b)H\u0082\bR\u0014\u0010\u0005\u001A\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u001A\u0010\t\u001A\u00020\nX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER\u001C\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001A\u00020\u00108G¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0012R\u001A\u0010\u0018\u001A\u00020\nX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0019\u0010\f\"\u0004\b\u001A\u0010\u000ER\u0013\u0010\u001B\u001A\u00020\u001C8G¢\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u001DR\u001A\u0010\u001E\u001A\u00020\nX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001F\u0010\f\"\u0004\b \u0010\u000E¨\u0006*"}, d2 = {"Lokio/Pipe;", "", "maxBufferSize", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$okio", "()Lokio/Buffer;", "canceled", "", "getCanceled$okio", "()Z", "setCanceled$okio", "(Z)V", "foldedSink", "Lokio/Sink;", "getFoldedSink$okio", "()Lokio/Sink;", "setFoldedSink$okio", "(Lokio/Sink;)V", "getMaxBufferSize$okio", "()J", "sink", "sinkClosed", "getSinkClosed$okio", "setSinkClosed$okio", "source", "Lokio/Source;", "()Lokio/Source;", "sourceClosed", "getSourceClosed$okio", "setSourceClosed$okio", "cancel", "", "fold", "-deprecated_sink", "-deprecated_source", "forward", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Pipe {
    private final Buffer buffer;
    private boolean canceled;
    private Sink foldedSink;
    private final long maxBufferSize;
    private final Sink sink;
    private boolean sinkClosed;
    private final Source source;
    private boolean sourceClosed;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = {}))
    public final Sink -deprecated_sink() {
        return this.sink;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = {}))
    public final Source -deprecated_source() {
        return this.source;
    }

    public Pipe(long v) {
        this.maxBufferSize = v;
        this.buffer = new Buffer();
        if(v < 1L) {
            throw new IllegalArgumentException(("maxBufferSize < 1: " + v).toString());
        }
        this.sink = new Object() {
            private final Timeout timeout;

            {
                this.timeout = new Timeout();
            }

            @Override  // okio.Sink
            public void close() {
                Sink sink0;
                Buffer buffer0 = Pipe.this.getBuffer$okio();
                Pipe pipe0 = Pipe.this;
                synchronized(buffer0) {
                    if(pipe0.getSinkClosed$okio()) {
                        return;
                    }
                    sink0 = pipe0.getFoldedSink$okio();
                    if(sink0 == null) {
                        if(pipe0.getSourceClosed$okio() && pipe0.getBuffer$okio().size() > 0L) {
                            throw new IOException("source is closed");
                        }
                        pipe0.setSinkClosed$okio(true);
                        pipe0.getBuffer$okio().notifyAll();
                        sink0 = null;
                    }
                }
                if(sink0 != null) {
                    Timeout timeout0 = sink0.timeout();
                    Timeout timeout1 = Pipe.this.sink().timeout();
                    long v1 = timeout0.timeoutNanos();
                    long v2 = timeout1.timeoutNanos();
                    long v3 = timeout0.timeoutNanos();
                    timeout0.timeout(Timeout.Companion.minTimeout(v2, v3), TimeUnit.NANOSECONDS);
                    if(timeout0.hasDeadline()) {
                        long v4 = timeout0.deadlineNanoTime();
                        if(timeout1.hasDeadline()) {
                            timeout0.deadlineNanoTime(Math.min(timeout0.deadlineNanoTime(), timeout1.deadlineNanoTime()));
                        }
                        try {
                            sink0.close();
                        }
                        catch(Throwable throwable0) {
                            timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                            if(timeout1.hasDeadline()) {
                                timeout0.deadlineNanoTime(v4);
                            }
                            throw throwable0;
                        }
                        timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                        if(timeout1.hasDeadline()) {
                            timeout0.deadlineNanoTime(v4);
                        }
                    }
                    else {
                        if(timeout1.hasDeadline()) {
                            timeout0.deadlineNanoTime(timeout1.deadlineNanoTime());
                        }
                        try {
                            sink0.close();
                        }
                        catch(Throwable throwable1) {
                            timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                            if(timeout1.hasDeadline()) {
                                timeout0.clearDeadline();
                            }
                            throw throwable1;
                        }
                        timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                        if(timeout1.hasDeadline()) {
                            timeout0.clearDeadline();
                        }
                    }
                }
            }

            @Override  // okio.Sink
            public void flush() {
                Buffer buffer0 = Pipe.this.getBuffer$okio();
                Pipe pipe0 = Pipe.this;
                synchronized(buffer0) {
                    if(!pipe0.getSinkClosed$okio()) {
                        if(pipe0.getCanceled$okio()) {
                            throw new IOException("canceled");
                        }
                        Sink sink0 = pipe0.getFoldedSink$okio();
                        if(sink0 == null) {
                            if(pipe0.getSourceClosed$okio() && pipe0.getBuffer$okio().size() > 0L) {
                                throw new IOException("source is closed");
                            }
                            sink0 = null;
                        }
                        if(sink0 != null) {
                            Timeout timeout0 = sink0.timeout();
                            Timeout timeout1 = Pipe.this.sink().timeout();
                            long v1 = timeout0.timeoutNanos();
                            long v2 = timeout1.timeoutNanos();
                            long v3 = timeout0.timeoutNanos();
                            timeout0.timeout(Timeout.Companion.minTimeout(v2, v3), TimeUnit.NANOSECONDS);
                            if(timeout0.hasDeadline()) {
                                long v4 = timeout0.deadlineNanoTime();
                                if(timeout1.hasDeadline()) {
                                    timeout0.deadlineNanoTime(Math.min(timeout0.deadlineNanoTime(), timeout1.deadlineNanoTime()));
                                }
                                try {
                                    sink0.flush();
                                }
                                catch(Throwable throwable0) {
                                    timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                                    if(timeout1.hasDeadline()) {
                                        timeout0.deadlineNanoTime(v4);
                                    }
                                    throw throwable0;
                                }
                                timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                                if(timeout1.hasDeadline()) {
                                    timeout0.deadlineNanoTime(v4);
                                    return;
                                }
                            }
                            else {
                                if(timeout1.hasDeadline()) {
                                    timeout0.deadlineNanoTime(timeout1.deadlineNanoTime());
                                }
                                try {
                                    sink0.flush();
                                }
                                catch(Throwable throwable1) {
                                    timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                                    if(timeout1.hasDeadline()) {
                                        timeout0.clearDeadline();
                                    }
                                    throw throwable1;
                                }
                                timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                                if(timeout1.hasDeadline()) {
                                    timeout0.clearDeadline();
                                    return;
                                }
                            }
                        }
                        return;
                    }
                }
                throw new IllegalStateException("closed");
            }

            @Override  // okio.Sink
            public Timeout timeout() {
                return this.timeout;
            }

            @Override  // okio.Sink
            public void write(Buffer buffer0, long v) {
                Sink sink1;
                Intrinsics.checkNotNullParameter(buffer0, "source");
                Buffer buffer1 = Pipe.this.getBuffer$okio();
                Pipe pipe0 = Pipe.this;
                synchronized(buffer1) {
                    if(!pipe0.getSinkClosed$okio()) {
                        if(pipe0.getCanceled$okio()) {
                            throw new IOException("canceled");
                        }
                        while(v > 0L) {
                            Sink sink0 = pipe0.getFoldedSink$okio();
                            if(sink0 != null) {
                                sink1 = sink0;
                                goto label_27;
                            }
                            if(pipe0.getSourceClosed$okio()) {
                                throw new IOException("source is closed");
                            }
                            long v2 = pipe0.getMaxBufferSize$okio() - pipe0.getBuffer$okio().size();
                            if(v2 == 0L) {
                                this.timeout.waitUntilNotified(pipe0.getBuffer$okio());
                                if(pipe0.getCanceled$okio()) {
                                    throw new IOException("canceled");
                                }
                            }
                            else {
                                long v3 = Math.min(v2, v);
                                pipe0.getBuffer$okio().write(buffer0, v3);
                                v -= v3;
                                pipe0.getBuffer$okio().notifyAll();
                            }
                        }
                        sink1 = null;
                    label_27:
                        if(sink1 != null) {
                            Timeout timeout0 = sink1.timeout();
                            Timeout timeout1 = Pipe.this.sink().timeout();
                            long v4 = timeout0.timeoutNanos();
                            long v5 = timeout1.timeoutNanos();
                            long v6 = timeout0.timeoutNanos();
                            timeout0.timeout(Timeout.Companion.minTimeout(v5, v6), TimeUnit.NANOSECONDS);
                            if(timeout0.hasDeadline()) {
                                long v7 = timeout0.deadlineNanoTime();
                                if(timeout1.hasDeadline()) {
                                    timeout0.deadlineNanoTime(Math.min(timeout0.deadlineNanoTime(), timeout1.deadlineNanoTime()));
                                }
                                try {
                                    sink1.write(buffer0, v);
                                }
                                catch(Throwable throwable0) {
                                    timeout0.timeout(v4, TimeUnit.NANOSECONDS);
                                    if(timeout1.hasDeadline()) {
                                        timeout0.deadlineNanoTime(v7);
                                    }
                                    throw throwable0;
                                }
                                timeout0.timeout(v4, TimeUnit.NANOSECONDS);
                                if(timeout1.hasDeadline()) {
                                    timeout0.deadlineNanoTime(v7);
                                    return;
                                }
                            }
                            else {
                                if(timeout1.hasDeadline()) {
                                    timeout0.deadlineNanoTime(timeout1.deadlineNanoTime());
                                }
                                try {
                                    sink1.write(buffer0, v);
                                }
                                catch(Throwable throwable1) {
                                    timeout0.timeout(v4, TimeUnit.NANOSECONDS);
                                    if(timeout1.hasDeadline()) {
                                        timeout0.clearDeadline();
                                    }
                                    throw throwable1;
                                }
                                timeout0.timeout(v4, TimeUnit.NANOSECONDS);
                                if(timeout1.hasDeadline()) {
                                    timeout0.clearDeadline();
                                    return;
                                }
                            }
                        }
                        return;
                    }
                }
                throw new IllegalStateException("closed");
            }
        };
        this.source = new Object() {
            private final Timeout timeout;

            {
                this.timeout = new Timeout();
            }

            @Override  // okio.Source
            public void close() {
                synchronized(Pipe.this.getBuffer$okio()) {
                    Pipe.this.setSourceClosed$okio(true);
                    Pipe.this.getBuffer$okio().notifyAll();
                }
            }

            @Override  // okio.Source
            public long read(Buffer buffer0, long v) {
                Intrinsics.checkNotNullParameter(buffer0, "sink");
                Buffer buffer1 = Pipe.this.getBuffer$okio();
                Pipe pipe0 = Pipe.this;
                synchronized(buffer1) {
                    if(!pipe0.getSourceClosed$okio()) {
                        if(pipe0.getCanceled$okio()) {
                            throw new IOException("canceled");
                        }
                        while(pipe0.getBuffer$okio().size() == 0L) {
                            if(pipe0.getSinkClosed$okio()) {
                                return -1L;
                            }
                            this.timeout.waitUntilNotified(pipe0.getBuffer$okio());
                            if(pipe0.getCanceled$okio()) {
                                throw new IOException("canceled");
                            }
                            if(false) {
                                break;
                            }
                        }
                        long v2 = pipe0.getBuffer$okio().read(buffer0, v);
                        pipe0.getBuffer$okio().notifyAll();
                        return v2;
                    }
                }
                throw new IllegalStateException("closed");
            }

            @Override  // okio.Source
            public Timeout timeout() {
                return this.timeout;
            }
        };
    }

    public final void cancel() {
        synchronized(this.buffer) {
            this.canceled = true;
            this.buffer.clear();
            this.buffer.notifyAll();
        }
    }

    public final void fold(Sink sink0) throws IOException {
        Buffer buffer1;
        int v;
        Intrinsics.checkNotNullParameter(sink0, "sink");
        while(true) {
            synchronized(this.buffer) {
                v = FIN.finallyOpen$NT();
                if(this.foldedSink != null) {
                    goto label_36;
                }
                if(this.canceled) {
                    goto label_33;
                }
                if(this.buffer.exhausted()) {
                    this.sourceClosed = true;
                    this.foldedSink = sink0;
                    FIN.finallyExec$NT(v);
                    return;
                }
                boolean z = this.sinkClosed;
                buffer1 = new Buffer();
                buffer1.write(this.buffer, this.buffer.size());
                this.buffer.notifyAll();
                FIN.finallyCodeBegin$NT(v);
            }
            FIN.finallyCodeEnd$NT(v);
            try {
                sink0.write(buffer1, buffer1.size());
                if(z) {
                    sink0.close();
                    continue;
                }
                sink0.flush();
                continue;
            }
            catch(Throwable throwable0) {
            }
            break;
        }
        synchronized(this.buffer) {
            this.sourceClosed = true;
            this.buffer.notifyAll();
        }
        throw throwable0;
    label_33:
        this.foldedSink = sink0;
        FIN.finallyExec$NT(v);
        throw new IOException("canceled");
    label_36:
        FIN.finallyExec$NT(v);
        throw new IllegalStateException("sink already folded");
    }

    private final void forward(Sink sink0, Function1 function10) {
        Timeout timeout0 = sink0.timeout();
        Timeout timeout1 = this.sink().timeout();
        long v = timeout0.timeoutNanos();
        long v1 = timeout1.timeoutNanos();
        long v2 = timeout0.timeoutNanos();
        timeout0.timeout(Timeout.Companion.minTimeout(v1, v2), TimeUnit.NANOSECONDS);
        if(timeout0.hasDeadline()) {
            long v3 = timeout0.deadlineNanoTime();
            if(timeout1.hasDeadline()) {
                timeout0.deadlineNanoTime(Math.min(timeout0.deadlineNanoTime(), timeout1.deadlineNanoTime()));
            }
            try {
                function10.invoke(sink0);
            }
            finally {
                timeout0.timeout(v, TimeUnit.NANOSECONDS);
                if(timeout1.hasDeadline()) {
                    timeout0.deadlineNanoTime(v3);
                }
            }
            return;
        }
        if(timeout1.hasDeadline()) {
            timeout0.deadlineNanoTime(timeout1.deadlineNanoTime());
        }
        try {
            function10.invoke(sink0);
        }
        finally {
            timeout0.timeout(v, TimeUnit.NANOSECONDS);
            if(timeout1.hasDeadline()) {
                timeout0.clearDeadline();
            }
        }
    }

    public final Buffer getBuffer$okio() {
        return this.buffer;
    }

    public final boolean getCanceled$okio() {
        return this.canceled;
    }

    public final Sink getFoldedSink$okio() {
        return this.foldedSink;
    }

    public final long getMaxBufferSize$okio() {
        return this.maxBufferSize;
    }

    public final boolean getSinkClosed$okio() {
        return this.sinkClosed;
    }

    public final boolean getSourceClosed$okio() {
        return this.sourceClosed;
    }

    public final void setCanceled$okio(boolean z) {
        this.canceled = z;
    }

    public final void setFoldedSink$okio(Sink sink0) {
        this.foldedSink = sink0;
    }

    public final void setSinkClosed$okio(boolean z) {
        this.sinkClosed = z;
    }

    public final void setSourceClosed$okio(boolean z) {
        this.sourceClosed = z;
    }

    public final Sink sink() {
        return this.sink;
    }

    public final Source source() {
        return this.source;
    }
}

