package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001A\u00020\u0000H\u0016J\b\u0010\t\u001A\u00020\u0000H\u0016J\u0016\u0010\n\u001A\u00020\u00002\u0006\u0010\u000B\u001A\u00020\u00042\u0006\u0010\f\u001A\u00020\rJ\b\u0010\u0003\u001A\u00020\u0004H\u0016J\u0010\u0010\u0003\u001A\u00020\u00002\u0006\u0010\u0003\u001A\u00020\u0004H\u0016J\b\u0010\u0005\u001A\u00020\u0006H\u0016J-\u0010\u000E\u001A\u0002H\u000F\"\u0004\b\u0000\u0010\u000F2\u0006\u0010\u0010\u001A\u00020\u00002\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u0012H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\u0018\u0010\u0016\u001A\u00020\u00002\u0006\u0010\u0016\u001A\u00020\u00042\u0006\u0010\f\u001A\u00020\rH\u0016J\b\u0010\u0007\u001A\u00020\u0004H\u0016J\u000E\u0010\u0017\u001A\u00020\u00152\u0006\u0010\u0018\u001A\u00020\u0001R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001A"}, d2 = {"Lokio/Timeout;", "", "()V", "deadlineNanoTime", "", "hasDeadline", "", "timeoutNanos", "clearDeadline", "clearTimeout", "deadline", "duration", "unit", "Ljava/util/concurrent/TimeUnit;", "intersectWith", "T", "other", "block", "Lkotlin/Function0;", "(Lokio/Timeout;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "throwIfReached", "", "timeout", "waitUntilNotified", "monitor", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public class Timeout {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u0006R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokio/Timeout$Companion;", "", "()V", "NONE", "Lokio/Timeout;", "minTimeout", "", "aNanos", "bNanos", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final long minTimeout(long v, long v1) {
            return v == 0L || v1 != 0L && v >= v1 ? v1 : v;
        }
    }

    public static final Companion Companion;
    public static final Timeout NONE;
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    static {
        Timeout.Companion = new Companion(null);
        Timeout.NONE = new Timeout.Companion.NONE.1();
    }

    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public Timeout clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    public final Timeout deadline(long v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "unit");
        if(v <= 0L) {
            throw new IllegalArgumentException(("duration <= 0: " + v).toString());
        }
        return this.deadlineNanoTime(System.nanoTime() + timeUnit0.toNanos(v));
    }

    public long deadlineNanoTime() {
        if(!this.hasDeadline) {
            throw new IllegalStateException("No deadline");
        }
        return this.deadlineNanoTime;
    }

    public Timeout deadlineNanoTime(long v) {
        this.hasDeadline = true;
        this.deadlineNanoTime = v;
        return this;
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public final Object intersectWith(Timeout timeout0, Function0 function00) {
        Intrinsics.checkNotNullParameter(timeout0, "other");
        Intrinsics.checkNotNullParameter(function00, "block");
        long v = this.timeoutNanos();
        long v1 = timeout0.timeoutNanos();
        long v2 = this.timeoutNanos();
        this.timeout(Timeout.Companion.minTimeout(v1, v2), TimeUnit.NANOSECONDS);
        if(this.hasDeadline()) {
            long v3 = this.deadlineNanoTime();
            if(timeout0.hasDeadline()) {
                this.deadlineNanoTime(Math.min(this.deadlineNanoTime(), timeout0.deadlineNanoTime()));
            }
            try {
                return function00.invoke();
            }
            finally {
                this.timeout(v, TimeUnit.NANOSECONDS);
                if(timeout0.hasDeadline()) {
                    this.deadlineNanoTime(v3);
                }
            }
        }
        if(timeout0.hasDeadline()) {
            this.deadlineNanoTime(timeout0.deadlineNanoTime());
        }
        try {
            return function00.invoke();
        }
        finally {
            this.timeout(v, TimeUnit.NANOSECONDS);
            if(timeout0.hasDeadline()) {
                this.clearDeadline();
            }
        }
    }

    public void throwIfReached() throws IOException {
        if(Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if(this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0L) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public Timeout timeout(long v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "unit");
        if(v < 0L) {
            throw new IllegalArgumentException(("timeout < 0: " + v).toString());
        }
        this.timeoutNanos = timeUnit0.toNanos(v);
        return this;
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public final void waitUntilNotified(Object object0) throws InterruptedIOException {
        Intrinsics.checkNotNullParameter(object0, "monitor");
        try {
            boolean z = this.hasDeadline();
            long v = this.timeoutNanos();
            long v1 = 0L;
            if(!z && v == 0L) {
                object0.wait();
                return;
            }
            long v2 = System.nanoTime();
            if(z && v != 0L) {
                v = Math.min(v, this.deadlineNanoTime() - v2);
            }
            else if(z) {
                v = this.deadlineNanoTime() - v2;
            }
            if(v > 0L) {
                object0.wait(v / 1000000L, ((int)(v - 1000000L * (v / 1000000L))));
                v1 = System.nanoTime() - v2;
            }
            if(v1 >= v) {
                throw new InterruptedIOException("timeout");
            }
        }
        catch(InterruptedException unused_ex) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }
}

