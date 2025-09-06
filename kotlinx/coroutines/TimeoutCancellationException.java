package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u000F\b\u0010\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0000\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\n\u001A\u00020\u0000H\u0016R\u0012\u0010\u0007\u001A\u0004\u0018\u00010\b8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/TimeoutCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lkotlinx/coroutines/CopyableThrowable;", "message", "", "(Ljava/lang/String;)V", "coroutine", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Lkotlinx/coroutines/Job;)V", "createCopy", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class TimeoutCancellationException extends CancellationException implements CopyableThrowable {
    public final transient Job coroutine;

    public TimeoutCancellationException(String s) {
        this(s, null);
    }

    public TimeoutCancellationException(String s, Job job0) {
        super(s);
        this.coroutine = job0;
    }

    @Override  // kotlinx.coroutines.CopyableThrowable
    public Throwable createCopy() {
        return this.createCopy();
    }

    public TimeoutCancellationException createCopy() {
        TimeoutCancellationException timeoutCancellationException0 = new TimeoutCancellationException((this.getMessage() == null ? "" : this.getMessage()), this.coroutine);
        timeoutCancellationException0.initCause(this);
        return timeoutCancellationException0;
    }
}

