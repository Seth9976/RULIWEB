package androidx.work.impl;

import androidx.work.WorkerParameters.RuntimeExtras;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001A\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\u0013H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/WorkLauncherImpl;", "Landroidx/work/impl/WorkLauncher;", "processor", "Landroidx/work/impl/Processor;", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "(Landroidx/work/impl/Processor;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "getProcessor", "()Landroidx/work/impl/Processor;", "getWorkTaskExecutor", "()Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "startWork", "", "workSpecId", "Landroidx/work/impl/StartStopToken;", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "stopWork", "reason", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkLauncherImpl implements WorkLauncher {
    private final Processor processor;
    private final TaskExecutor workTaskExecutor;

    // 检测为 Lambda 实现
    public static void $r8$lambda$IJHwq8ui46LmYGGj0wRpXW6AsK0(WorkLauncherImpl workLauncherImpl0, StartStopToken startStopToken0, RuntimeExtras workerParameters$RuntimeExtras0) [...]

    public WorkLauncherImpl(Processor processor0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(processor0, "processor");
        Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
        super();
        this.processor = processor0;
        this.workTaskExecutor = taskExecutor0;
    }

    public final Processor getProcessor() {
        return this.processor;
    }

    public final TaskExecutor getWorkTaskExecutor() {
        return this.workTaskExecutor;
    }

    @Override  // androidx.work.impl.WorkLauncher
    public void startWork(StartStopToken startStopToken0) {
        WorkLauncher.-CC.$default$startWork(this, startStopToken0);
    }

    @Override  // androidx.work.impl.WorkLauncher
    public void startWork(StartStopToken startStopToken0, RuntimeExtras workerParameters$RuntimeExtras0) {
        Intrinsics.checkNotNullParameter(startStopToken0, "workSpecId");
        WorkLauncherImpl..ExternalSyntheticLambda0 workLauncherImpl$$ExternalSyntheticLambda00 = () -> WorkLauncherImpl.startWork$lambda$0(this, startStopToken0, workerParameters$RuntimeExtras0);
        this.workTaskExecutor.executeOnTaskThread(workLauncherImpl$$ExternalSyntheticLambda00);
    }

    private static final void startWork$lambda$0(WorkLauncherImpl workLauncherImpl0, StartStopToken startStopToken0, RuntimeExtras workerParameters$RuntimeExtras0) {
        workLauncherImpl0.processor.startWork(startStopToken0, workerParameters$RuntimeExtras0);
    }

    @Override  // androidx.work.impl.WorkLauncher
    public void stopWork(StartStopToken startStopToken0) {
        WorkLauncher.-CC.$default$stopWork(this, startStopToken0);
    }

    @Override  // androidx.work.impl.WorkLauncher
    public void stopWork(StartStopToken startStopToken0, int v) {
        Intrinsics.checkNotNullParameter(startStopToken0, "workSpecId");
        StopWorkRunnable stopWorkRunnable0 = new StopWorkRunnable(this.processor, startStopToken0, false, v);
        this.workTaskExecutor.executeOnTaskThread(stopWorkRunnable0);
    }

    @Override  // androidx.work.impl.WorkLauncher
    public void stopWorkWithReason(StartStopToken startStopToken0, int v) {
        WorkLauncher.-CC.$default$stopWorkWithReason(this, startStopToken0, v);
    }
}

