package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.R.bool;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001A%\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0007\u00A2\u0006\u0002\b\b\u001A\u00DF\u0001\u0010\t\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\n\u001A\u00020\u000B2\b\b\u0002\u0010\f\u001A\u00020\r2\b\b\u0002\u0010\u000E\u001A\u00020\u000F2\u0097\u0001\b\u0002\u0010\u0010\u001A\u0090\u0001\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u000F\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0011j\u0002`\u0016H\u0007\u00A2\u0006\u0002\b\u0017\u001A\u0015\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u0007H\u0001\u00A2\u0006\u0002\b\u001B\u001A>\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FH\u0002\u001A\u00AE\u0001\u0010\u001D\u001A\u0090\u0001\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u000F\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0011j\u0002`\u00162\u0012\u0010\u001D\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u001E\"\u00020\u0015\u00A2\u0006\u0002\u0010\u001F\u001A\n\u0010 \u001A\u00020!*\u00020\u0001*\u00A0\u0002\u0010\"\"\u008C\u0001\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u000F\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00112\u008C\u0001\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u000F\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0011\u00A8\u0006#"}, d2 = {"TestWorkManagerImpl", "Landroidx/work/impl/WorkManagerImpl;", "context", "Landroid/content/Context;", "configuration", "Landroidx/work/Configuration;", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "createTestWorkManager", "WorkManagerImpl", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "trackers", "Landroidx/work/impl/constraints/trackers/Trackers;", "processor", "Landroidx/work/impl/Processor;", "schedulersCreator", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "", "Landroidx/work/impl/Scheduler;", "Landroidx/work/impl/SchedulersCreator;", "createWorkManager", "WorkManagerScope", "Lkotlinx/coroutines/CoroutineScope;", "taskExecutor", "createWorkManagerScope", "createSchedulers", "schedulers", "", "([Landroidx/work/impl/Scheduler;)Lkotlin/jvm/functions/Function6;", "close", "", "SchedulersCreator", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkManagerImplExtKt {
    public static final void close(WorkManagerImpl workManagerImpl0) {
        Intrinsics.checkNotNullParameter(workManagerImpl0, "<this>");
        BuildersKt.runBlocking$default(null, new Function2(workManagerImpl0, null) {
            final WorkManagerImpl $this_close;
            int label;

            {
                this.$this_close = workManagerImpl0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.work.impl.WorkManagerImplExtKt.close.1(this.$this_close, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.work.impl.WorkManagerImplExtKt.close.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        Element coroutineContext$Element0 = this.$this_close.getWorkManagerScope().getCoroutineContext().get(Job.Key);
                        Intrinsics.checkNotNull(coroutineContext$Element0);
                        this.label = 1;
                        return JobKt.cancelAndJoin(((Job)coroutineContext$Element0), this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }, 1, null);
        workManagerImpl0.getWorkDatabase().close();
    }

    private static final List createSchedulers(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, Trackers trackers0, Processor processor0) {
        Scheduler[] arr_scheduler = new Scheduler[2];
        Scheduler scheduler0 = Schedulers.createBestAvailableBackgroundScheduler(context0, workDatabase0, configuration0);
        Intrinsics.checkNotNullExpressionValue(scheduler0, "createBestAvailableBackg…kDatabase, configuration)");
        arr_scheduler[0] = scheduler0;
        arr_scheduler[1] = new GreedyScheduler(context0, configuration0, trackers0, processor0, new WorkLauncherImpl(processor0, taskExecutor0), taskExecutor0);
        return CollectionsKt.listOf(arr_scheduler);
    }

    public static final WorkManagerImpl createTestWorkManager(Context context0, Configuration configuration0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
        SerialExecutor serialExecutor0 = taskExecutor0.getSerialTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(serialExecutor0, "workTaskExecutor.serialTaskExecutor");
        return WorkManagerImplExtKt.createWorkManager$default(context0, configuration0, taskExecutor0, WorkDatabase.Companion.create(context0, serialExecutor0, configuration0.getClock(), true), null, null, null, 0x70, null);
    }

    public static final WorkManagerImpl createWorkManager(Context context0, Configuration configuration0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        return WorkManagerImplExtKt.createWorkManager$default(context0, configuration0, null, null, null, null, null, 0x7C, null);
    }

    public static final WorkManagerImpl createWorkManager(Context context0, Configuration configuration0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
        return WorkManagerImplExtKt.createWorkManager$default(context0, configuration0, taskExecutor0, null, null, null, null, 120, null);
    }

    public static final WorkManagerImpl createWorkManager(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
        Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
        return WorkManagerImplExtKt.createWorkManager$default(context0, configuration0, taskExecutor0, workDatabase0, null, null, null, 0x70, null);
    }

    public static final WorkManagerImpl createWorkManager(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, Trackers trackers0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
        Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
        Intrinsics.checkNotNullParameter(trackers0, "trackers");
        return WorkManagerImplExtKt.createWorkManager$default(context0, configuration0, taskExecutor0, workDatabase0, trackers0, null, null, 0x60, null);
    }

    public static final WorkManagerImpl createWorkManager(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, Trackers trackers0, Processor processor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
        Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
        Intrinsics.checkNotNullParameter(trackers0, "trackers");
        Intrinsics.checkNotNullParameter(processor0, "processor");
        return WorkManagerImplExtKt.createWorkManager$default(context0, configuration0, taskExecutor0, workDatabase0, trackers0, processor0, null, 0x40, null);
    }

    public static final WorkManagerImpl createWorkManager(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, Trackers trackers0, Processor processor0, Function6 function60) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
        Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
        Intrinsics.checkNotNullParameter(trackers0, "trackers");
        Intrinsics.checkNotNullParameter(processor0, "processor");
        Intrinsics.checkNotNullParameter(function60, "schedulersCreator");
        Object object0 = function60.invoke(context0, configuration0, taskExecutor0, workDatabase0, trackers0, processor0);
        return new WorkManagerImpl(context0.getApplicationContext(), configuration0, taskExecutor0, workDatabase0, ((List)object0), processor0, trackers0);
    }

    public static WorkManagerImpl createWorkManager$default(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, Trackers trackers0, Processor processor0, Function6 function60, int v, Object object0) {
        Trackers trackers1;
        if((v & 4) != 0) {
            taskExecutor0 = new WorkManagerTaskExecutor(configuration0.getTaskExecutor());
        }
        if((v & 8) != 0) {
            Context context1 = context0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(context1, "context.applicationContext");
            SerialExecutor serialExecutor0 = taskExecutor0.getSerialTaskExecutor();
            Intrinsics.checkNotNullExpressionValue(serialExecutor0, "workTaskExecutor.serialTaskExecutor");
            boolean z = context0.getResources().getBoolean(bool.workmanager_test_configuration);
            workDatabase0 = WorkDatabase.Companion.create(context1, serialExecutor0, configuration0.getClock(), z);
        }
        if((v & 16) == 0) {
            trackers1 = trackers0;
        }
        else {
            Context context2 = context0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context.applicationContext");
            trackers1 = new Trackers(context2, taskExecutor0, null, null, null, null, 60, null);
        }
        if((v & 0x20) != 0) {
            processor0 = new Processor(context0.getApplicationContext(), configuration0, taskExecutor0, workDatabase0);
        }
        return (v & 0x40) == 0 ? WorkManagerImplExtKt.createWorkManager(context0, configuration0, taskExecutor0, workDatabase0, trackers1, processor0, function60) : WorkManagerImplExtKt.createWorkManager(context0, configuration0, taskExecutor0, workDatabase0, trackers1, processor0, androidx.work.impl.WorkManagerImplExtKt.WorkManagerImpl.1.INSTANCE);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.work.impl.WorkManagerImplExtKt.WorkManagerImpl.1 extends FunctionReferenceImpl implements Function6 {
            public static final androidx.work.impl.WorkManagerImplExtKt.WorkManagerImpl.1 INSTANCE;

            static {
                androidx.work.impl.WorkManagerImplExtKt.WorkManagerImpl.1.INSTANCE = new androidx.work.impl.WorkManagerImplExtKt.WorkManagerImpl.1();
            }

            androidx.work.impl.WorkManagerImplExtKt.WorkManagerImpl.1() {
                super(6, WorkManagerImplExtKt.class, "createSchedulers", "createSchedulers(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;)Ljava/util/List;", 1);
            }

            @Override  // kotlin.jvm.functions.Function6
            public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5) {
                return this.invoke(((Context)object0), ((Configuration)object1), ((TaskExecutor)object2), ((WorkDatabase)object3), ((Trackers)object4), ((Processor)object5));
            }

            public final List invoke(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, Trackers trackers0, Processor processor0) {
                Intrinsics.checkNotNullParameter(context0, "p0");
                Intrinsics.checkNotNullParameter(configuration0, "p1");
                Intrinsics.checkNotNullParameter(taskExecutor0, "p2");
                Intrinsics.checkNotNullParameter(workDatabase0, "p3");
                Intrinsics.checkNotNullParameter(trackers0, "p4");
                Intrinsics.checkNotNullParameter(processor0, "p5");
                return WorkManagerImplExtKt.createSchedulers(context0, configuration0, taskExecutor0, workDatabase0, trackers0, processor0);
            }
        }

    }

    public static final CoroutineScope createWorkManagerScope(TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        CoroutineDispatcher coroutineDispatcher0 = taskExecutor0.getTaskCoroutineDispatcher();
        Intrinsics.checkNotNullExpressionValue(coroutineDispatcher0, "taskExecutor.taskCoroutineDispatcher");
        return CoroutineScopeKt.CoroutineScope(coroutineDispatcher0);
    }

    public static final Function6 schedulers(Scheduler[] arr_scheduler) {
        Intrinsics.checkNotNullParameter(arr_scheduler, "schedulers");
        return new Function6(arr_scheduler) {
            final Scheduler[] $schedulers;

            {
                this.$schedulers = arr_scheduler;
                super(6);
            }

            @Override  // kotlin.jvm.functions.Function6
            public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5) {
                return this.invoke(((Context)object0), ((Configuration)object1), ((TaskExecutor)object2), ((WorkDatabase)object3), ((Trackers)object4), ((Processor)object5));
            }

            public final List invoke(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, Trackers trackers0, Processor processor0) {
                Intrinsics.checkNotNullParameter(context0, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(configuration0, "<anonymous parameter 1>");
                Intrinsics.checkNotNullParameter(taskExecutor0, "<anonymous parameter 2>");
                Intrinsics.checkNotNullParameter(workDatabase0, "<anonymous parameter 3>");
                Intrinsics.checkNotNullParameter(trackers0, "<anonymous parameter 4>");
                Intrinsics.checkNotNullParameter(processor0, "<anonymous parameter 5>");
                return ArraysKt.toList(this.$schedulers);
            }
        };
    }
}

