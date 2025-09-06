package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b!\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0010¢\u0006\u0002\u0010\u0003B\u0017\b\u0000\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bR\u0015\u0010\t\u001A\u00020\n8À\u0002X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u0012\u0010\u0004\u001A\u00020\u00058\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001A\u00020\u00078\u0000@\u0000X\u0081\u000E¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/scheduling/Task;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "()V", "submissionTime", "", "taskContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "(JLkotlinx/coroutines/scheduling/TaskContext;)V", "mode", "", "getMode$kotlinx_coroutines_core", "()I", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class Task implements Runnable {
    public long submissionTime;
    public TaskContext taskContext;

    public Task() {
        this(0L, TasksKt.NonBlockingContext);
    }

    public Task(long v, TaskContext taskContext0) {
        this.submissionTime = v;
        this.taskContext = taskContext0;
    }

    public final int getMode$kotlinx_coroutines_core() {
        return this.taskContext.getTaskMode();
    }
}

