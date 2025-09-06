package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u000F"}, d2 = {"Landroidx/work/WorkerExceptionInfo;", "", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "throwable", "", "(Ljava/lang/String;Landroidx/work/WorkerParameters;Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "getWorkerClassName", "()Ljava/lang/String;", "getWorkerParameters", "()Landroidx/work/WorkerParameters;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkerExceptionInfo {
    private final Throwable throwable;
    private final String workerClassName;
    private final WorkerParameters workerParameters;

    public WorkerExceptionInfo(String s, WorkerParameters workerParameters0, Throwable throwable0) {
        Intrinsics.checkNotNullParameter(s, "workerClassName");
        Intrinsics.checkNotNullParameter(workerParameters0, "workerParameters");
        Intrinsics.checkNotNullParameter(throwable0, "throwable");
        super();
        this.workerClassName = s;
        this.workerParameters = workerParameters0;
        this.throwable = throwable0;
    }

    public final Throwable getThrowable() {
        return this.throwable;
    }

    public final String getWorkerClassName() {
        return this.workerClassName;
    }

    public final WorkerParameters getWorkerParameters() {
        return this.workerParameters;
    }
}

