package androidx.work.impl.utils.taskexecutor;

import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

public final class TaskExecutor.-CC {
    public static void $default$executeOnTaskThread(TaskExecutor _this, Runnable runnable0) {
        _this.getSerialTaskExecutor().execute(runnable0);
    }

    public static CoroutineDispatcher $default$getTaskCoroutineDispatcher(TaskExecutor _this) {
        return ExecutorsKt.from(_this.getSerialTaskExecutor());
    }
}

