package androidx.work.impl.utils.taskexecutor;

import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;

public interface TaskExecutor {
    void executeOnTaskThread(Runnable arg1);

    Executor getMainThreadExecutor();

    SerialExecutor getSerialTaskExecutor();

    CoroutineDispatcher getTaskCoroutineDispatcher();
}

