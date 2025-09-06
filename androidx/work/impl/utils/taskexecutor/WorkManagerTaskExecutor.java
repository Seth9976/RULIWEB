package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import androidx.work.impl.utils.SerialExecutorImpl;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

public class WorkManagerTaskExecutor implements TaskExecutor {
    private final SerialExecutorImpl mBackgroundExecutor;
    private final Executor mMainThreadExecutor;
    final Handler mMainThreadHandler;
    private final CoroutineDispatcher mTaskDispatcher;

    public WorkManagerTaskExecutor(Executor executor0) {
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.mMainThreadExecutor = new Executor() {
            @Override
            public void execute(Runnable runnable0) {
                WorkManagerTaskExecutor.this.mMainThreadHandler.post(runnable0);
            }
        };
        SerialExecutorImpl serialExecutorImpl0 = new SerialExecutorImpl(executor0);
        this.mBackgroundExecutor = serialExecutorImpl0;
        this.mTaskDispatcher = ExecutorsKt.from(serialExecutorImpl0);
    }

    @Override  // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public void executeOnTaskThread(Runnable runnable0) {
        TaskExecutor.-CC.$default$executeOnTaskThread(this, runnable0);
    }

    @Override  // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    public SerialExecutorImpl getSerialTaskExecutor() {
        return this.mBackgroundExecutor;
    }

    @Override  // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public SerialExecutor getSerialTaskExecutor() {
        return this.getSerialTaskExecutor();
    }

    @Override  // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public CoroutineDispatcher getTaskCoroutineDispatcher() {
        return this.mTaskDispatcher;
    }
}

