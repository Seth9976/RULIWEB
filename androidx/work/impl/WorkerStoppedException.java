package androidx.work.impl;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/WorkerStoppedException;", "Ljava/util/concurrent/CancellationException;", "reason", "", "(I)V", "getReason", "()I", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkerStoppedException extends CancellationException {
    private final int reason;

    public WorkerStoppedException(int v) {
        this.reason = v;
    }

    public final int getReason() {
        return this.reason;
    }
}

