package androidx.work.impl.utils;

import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.WorkInfo.State;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0002\u001A\u000E\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u0016\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u0016\u0010\u000B\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u0016\u0010\r\u001A\u00020\u00012\u0006\u0010\f\u001A\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u0016\u0010\u000E\u001A\u00020\u00072\u0006\u0010\u000F\u001A\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u0018\u0010\u0010\u001A\u00020\u00012\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0004\u001A\u00020\u0005H\u0002\u001A\u0010\u0010\u0013\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0002¨\u0006\u0014"}, d2 = {"cancel", "", "workManagerImpl", "Landroidx/work/impl/WorkManagerImpl;", "workSpecId", "", "forAll", "Landroidx/work/Operation;", "forId", "id", "Ljava/util/UUID;", "forName", "name", "forNameInline", "forTag", "tag", "iterativelyCancelWorkAndDependents", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "reschedulePendingWorkers", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CancelWorkRunnable {
    // 检测为 Lambda 实现
    public static void $r8$lambda$gmz-7SyxTGDd6CwHjvOsJ11-hcc(WorkDatabase workDatabase0, String s, WorkManagerImpl workManagerImpl0) [...]

    private static final void cancel(WorkManagerImpl workManagerImpl0, String s) {
        WorkDatabase workDatabase0 = workManagerImpl0.getWorkDatabase();
        Intrinsics.checkNotNullExpressionValue(workDatabase0, "workManagerImpl.workDatabase");
        CancelWorkRunnable.iterativelyCancelWorkAndDependents(workDatabase0, s);
        Processor processor0 = workManagerImpl0.getProcessor();
        Intrinsics.checkNotNullExpressionValue(processor0, "workManagerImpl.processor");
        processor0.stopAndCancelWork(s, 1);
        for(Object object0: workManagerImpl0.getSchedulers()) {
            ((Scheduler)object0).cancel(s);
        }
    }

    public static final Operation forAll(WorkManagerImpl workManagerImpl0) {
        Intrinsics.checkNotNullParameter(workManagerImpl0, "workManagerImpl");
        SerialExecutor serialExecutor0 = workManagerImpl0.getWorkTaskExecutor().getSerialTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(serialExecutor0, "workManagerImpl.workTask…ecutor.serialTaskExecutor");
        return OperationKt.launchOperation(workManagerImpl0.getConfiguration().getTracer(), "CancelAllWork", serialExecutor0, new Function0(workManagerImpl0) {
            final WorkManagerImpl $workManagerImpl;

            // 检测为 Lambda 实现
            public static void $r8$lambda$FiwYY7euQVFoWhAgDTmAY3zxQHo(WorkDatabase workDatabase0, WorkManagerImpl workManagerImpl0) [...]

            {
                this.$workManagerImpl = workManagerImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                WorkDatabase workDatabase0 = this.$workManagerImpl.getWorkDatabase();
                Intrinsics.checkNotNullExpressionValue(workDatabase0, "workManagerImpl.workDatabase");
                workDatabase0.runInTransaction(() -> androidx.work.impl.utils.CancelWorkRunnable.forAll.1.invoke$lambda$0(workDatabase0, this.$workManagerImpl));
            }

            private static final void invoke$lambda$0(WorkDatabase workDatabase0, WorkManagerImpl workManagerImpl0) {
                for(Object object0: workDatabase0.workSpecDao().getAllUnfinishedWork()) {
                    CancelWorkRunnable.cancel(workManagerImpl0, ((String)object0));
                }
                new PreferenceUtils(workDatabase0).setLastCancelAllTimeMillis(workManagerImpl0.getConfiguration().getClock().currentTimeMillis());
            }
        });
    }

    public static final Operation forId(UUID uUID0, WorkManagerImpl workManagerImpl0) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workManagerImpl0, "workManagerImpl");
        SerialExecutor serialExecutor0 = workManagerImpl0.getWorkTaskExecutor().getSerialTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(serialExecutor0, "workManagerImpl.workTask…ecutor.serialTaskExecutor");
        return OperationKt.launchOperation(workManagerImpl0.getConfiguration().getTracer(), "CancelWorkById", serialExecutor0, new Function0(workManagerImpl0, uUID0) {
            final UUID $id;
            final WorkManagerImpl $workManagerImpl;

            // 检测为 Lambda 实现
            public static void $r8$lambda$c6ckNuSXH3At6SBb4mDMZynE_5I(WorkManagerImpl workManagerImpl0, UUID uUID0) [...]

            {
                this.$workManagerImpl = workManagerImpl0;
                this.$id = uUID0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                WorkDatabase workDatabase0 = this.$workManagerImpl.getWorkDatabase();
                Intrinsics.checkNotNullExpressionValue(workDatabase0, "workManagerImpl.workDatabase");
                workDatabase0.runInTransaction(() -> androidx.work.impl.utils.CancelWorkRunnable.forId.1.invoke$lambda$0(this.$workManagerImpl, this.$id));
                CancelWorkRunnable.reschedulePendingWorkers(this.$workManagerImpl);
            }

            private static final void invoke$lambda$0(WorkManagerImpl workManagerImpl0, UUID uUID0) {
                String s = uUID0.toString();
                Intrinsics.checkNotNullExpressionValue(s, "id.toString()");
                CancelWorkRunnable.cancel(workManagerImpl0, s);
            }
        });
    }

    public static final Operation forName(String s, WorkManagerImpl workManagerImpl0) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(workManagerImpl0, "workManagerImpl");
        SerialExecutor serialExecutor0 = workManagerImpl0.getWorkTaskExecutor().getSerialTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(serialExecutor0, "workManagerImpl.workTask…ecutor.serialTaskExecutor");
        return OperationKt.launchOperation(workManagerImpl0.getConfiguration().getTracer(), "CancelWorkByName_" + s, serialExecutor0, new Function0(s, workManagerImpl0) {
            final String $name;
            final WorkManagerImpl $workManagerImpl;

            {
                this.$name = s;
                this.$workManagerImpl = workManagerImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                CancelWorkRunnable.forNameInline(this.$name, this.$workManagerImpl);
                CancelWorkRunnable.reschedulePendingWorkers(this.$workManagerImpl);
            }
        });
    }

    public static final void forNameInline(String s, WorkManagerImpl workManagerImpl0) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(workManagerImpl0, "workManagerImpl");
        WorkDatabase workDatabase0 = workManagerImpl0.getWorkDatabase();
        Intrinsics.checkNotNullExpressionValue(workDatabase0, "workManagerImpl.workDatabase");
        workDatabase0.runInTransaction(() -> CancelWorkRunnable.forNameInline$lambda$0(workDatabase0, s, workManagerImpl0));
    }

    private static final void forNameInline$lambda$0(WorkDatabase workDatabase0, String s, WorkManagerImpl workManagerImpl0) {
        for(Object object0: workDatabase0.workSpecDao().getUnfinishedWorkWithName(s)) {
            CancelWorkRunnable.cancel(workManagerImpl0, ((String)object0));
        }
    }

    public static final Operation forTag(String s, WorkManagerImpl workManagerImpl0) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(workManagerImpl0, "workManagerImpl");
        SerialExecutor serialExecutor0 = workManagerImpl0.getWorkTaskExecutor().getSerialTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(serialExecutor0, "workManagerImpl.workTask…ecutor.serialTaskExecutor");
        return OperationKt.launchOperation(workManagerImpl0.getConfiguration().getTracer(), "CancelWorkByTag_" + s, serialExecutor0, new Function0(workManagerImpl0, s) {
            final String $tag;
            final WorkManagerImpl $workManagerImpl;

            // 检测为 Lambda 实现
            public static void $r8$lambda$dnx612loOpf-S5MeOf7de81-yec(WorkDatabase workDatabase0, String s, WorkManagerImpl workManagerImpl0) [...]

            {
                this.$workManagerImpl = workManagerImpl0;
                this.$tag = s;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                WorkDatabase workDatabase0 = this.$workManagerImpl.getWorkDatabase();
                Intrinsics.checkNotNullExpressionValue(workDatabase0, "workManagerImpl.workDatabase");
                workDatabase0.runInTransaction(() -> androidx.work.impl.utils.CancelWorkRunnable.forTag.1.invoke$lambda$0(workDatabase0, this.$tag, this.$workManagerImpl));
                CancelWorkRunnable.reschedulePendingWorkers(this.$workManagerImpl);
            }

            private static final void invoke$lambda$0(WorkDatabase workDatabase0, String s, WorkManagerImpl workManagerImpl0) {
                for(Object object0: workDatabase0.workSpecDao().getUnfinishedWorkWithTag(s)) {
                    CancelWorkRunnable.cancel(workManagerImpl0, ((String)object0));
                }
            }
        });
    }

    private static final void iterativelyCancelWorkAndDependents(WorkDatabase workDatabase0, String s) {
        WorkSpecDao workSpecDao0 = workDatabase0.workSpecDao();
        DependencyDao dependencyDao0 = workDatabase0.dependencyDao();
        List list0 = CollectionsKt.mutableListOf(new String[]{s});
        while(!list0.isEmpty()) {
            String s1 = (String)CollectionsKt.removeLast(list0);
            State workInfo$State0 = workSpecDao0.getState(s1);
            if(workInfo$State0 != State.SUCCEEDED && workInfo$State0 != State.FAILED) {
                workSpecDao0.setCancelledState(s1);
            }
            list0.addAll(dependencyDao0.getDependentWorkIds(s1));
        }
    }

    private static final void reschedulePendingWorkers(WorkManagerImpl workManagerImpl0) {
        Schedulers.schedule(workManagerImpl0.getConfiguration(), workManagerImpl0.getWorkDatabase(), workManagerImpl0.getSchedulers());
    }
}

