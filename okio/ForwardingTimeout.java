package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001A\u00020\u0001H\u0016J\b\u0010\u0007\u001A\u00020\u0001H\u0016J\b\u0010\b\u001A\u00020\tH\u0016J\u0010\u0010\b\u001A\u00020\u00012\u0006\u0010\b\u001A\u00020\tH\u0016J\b\u0010\n\u001A\u00020\u000BH\u0016J\u000E\u0010\u0005\u001A\u00020\u00002\u0006\u0010\u0002\u001A\u00020\u0001J\b\u0010\f\u001A\u00020\rH\u0016J\u0018\u0010\u000E\u001A\u00020\u00012\u0006\u0010\u000E\u001A\u00020\t2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0011\u001A\u00020\tH\u0016R\u001C\u0010\u0002\u001A\u00020\u00018\u0007X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0003¨\u0006\u0012"}, d2 = {"Lokio/ForwardingTimeout;", "Lokio/Timeout;", "delegate", "(Lokio/Timeout;)V", "()Lokio/Timeout;", "setDelegate", "clearDeadline", "clearTimeout", "deadlineNanoTime", "", "hasDeadline", "", "throwIfReached", "", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "timeoutNanos", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public class ForwardingTimeout extends Timeout {
    private Timeout delegate;

    public ForwardingTimeout(Timeout timeout0) {
        Intrinsics.checkNotNullParameter(timeout0, "delegate");
        super();
        this.delegate = timeout0;
    }

    @Override  // okio.Timeout
    public Timeout clearDeadline() {
        return this.delegate.clearDeadline();
    }

    @Override  // okio.Timeout
    public Timeout clearTimeout() {
        return this.delegate.clearTimeout();
    }

    @Override  // okio.Timeout
    public long deadlineNanoTime() {
        return this.delegate.deadlineNanoTime();
    }

    @Override  // okio.Timeout
    public Timeout deadlineNanoTime(long v) {
        return this.delegate.deadlineNanoTime(v);
    }

    public final Timeout delegate() {
        return this.delegate;
    }

    @Override  // okio.Timeout
    public boolean hasDeadline() {
        return this.delegate.hasDeadline();
    }

    public final ForwardingTimeout setDelegate(Timeout timeout0) {
        Intrinsics.checkNotNullParameter(timeout0, "delegate");
        this.delegate = timeout0;
        return this;
    }

    public final void setDelegate(Timeout timeout0) {
        Intrinsics.checkNotNullParameter(timeout0, "<set-?>");
        this.delegate = timeout0;
    }

    @Override  // okio.Timeout
    public void throwIfReached() throws IOException {
        this.delegate.throwIfReached();
    }

    @Override  // okio.Timeout
    public Timeout timeout(long v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "unit");
        return this.delegate.timeout(v, timeUnit0);
    }

    @Override  // okio.Timeout
    public long timeoutNanos() {
        return this.delegate.timeoutNanos();
    }
}

