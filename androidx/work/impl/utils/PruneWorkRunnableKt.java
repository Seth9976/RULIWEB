package androidx.work.impl.utils;

import androidx.work.Configuration;
import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u001C\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"pruneWork", "Landroidx/work/Operation;", "Landroidx/work/impl/WorkDatabase;", "configuration", "Landroidx/work/Configuration;", "executor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class PruneWorkRunnableKt {
    public static final Operation pruneWork(WorkDatabase workDatabase0, Configuration configuration0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(workDatabase0, "<this>");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(taskExecutor0, "executor");
        SerialExecutor serialExecutor0 = taskExecutor0.getSerialTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(serialExecutor0, "executor.serialTaskExecutor");
        return OperationKt.launchOperation(configuration0.getTracer(), "PruneWork", serialExecutor0, new Function0(workDatabase0) {
            final WorkDatabase $this_pruneWork;

            {
                this.$this_pruneWork = workDatabase0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                this.$this_pruneWork.workSpecDao().pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();
            }
        });
    }
}

