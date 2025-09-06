package androidx.work.impl;

import android.content.Context;
import androidx.core.util.Consumer;
import androidx.work.Clock;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.ForegroundUpdater;
import androidx.work.InputMerger;
import androidx.work.ListenableFutureKt;
import androidx.work.ListenableWorker.Result.Failure;
import androidx.work.ListenableWorker.Result.Retry;
import androidx.work.ListenableWorker.Result.Success;
import androidx.work.ListenableWorker.Result;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkInfo.State;
import androidx.work.WorkerExceptionInfo;
import androidx.work.WorkerParameters.RuntimeExtras;
import androidx.work.WorkerParameters;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.WorkForegroundKt;
import androidx.work.impl.utils.WorkForegroundUpdater;
import androidx.work.impl.utils.WorkProgressUpdater;
import androidx.work.impl.utils.WorkerExceptionUtilsKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000\u009E\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u0002>?B\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u0016\u0010(\u001A\u00020\u00152\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u0012\u0010)\u001A\u00020*2\b\u0010+\u001A\u0004\u0018\u00010,H\u0002J\u0010\u0010-\u001A\u00020.2\u0006\u0010/\u001A\u000200H\u0007J\u0010\u00101\u001A\u00020.2\u0006\u0010#\u001A\u00020\u0015H\u0002J\f\u00102\u001A\b\u0012\u0004\u0012\u00020*03J\u0010\u00104\u001A\u00020*2\u0006\u0010+\u001A\u00020,H\u0002J\u0010\u00105\u001A\u00020*2\u0006\u0010/\u001A\u000200H\u0002J\b\u00106\u001A\u00020*H\u0002J\u0010\u00107\u001A\u00020*2\u0006\u0010/\u001A\u000200H\u0002J\u000E\u00108\u001A\u000209H\u0082@\u00A2\u0006\u0002\u0010:J\u0010\u0010;\u001A\u00020*2\u0006\u0010+\u001A\u00020,H\u0007J\u0010\u0010<\u001A\u00020*2\u0006\u0010+\u001A\u00020,H\u0002J\b\u0010=\u001A\u00020*H\u0002R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0012X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u0017X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\u0015X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001A\u00020\u001A8F\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u001CR\u0011\u0010\u001D\u001A\u00020\u001E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 R\u000E\u0010!\u001A\u00020\"X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010#\u001A\u00020\u0015X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010$\u001A\u00020%X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010&\u001A\u00020\'X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006@"}, d2 = {"Landroidx/work/impl/WorkerWrapper;", "", "builder", "Landroidx/work/impl/WorkerWrapper$Builder;", "(Landroidx/work/impl/WorkerWrapper$Builder;)V", "appContext", "Landroid/content/Context;", "builderWorker", "Landroidx/work/ListenableWorker;", "clock", "Landroidx/work/Clock;", "configuration", "Landroidx/work/Configuration;", "dependencyDao", "Landroidx/work/impl/model/DependencyDao;", "foregroundProcessor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "tags", "", "", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "workDescription", "workGenerationalId", "Landroidx/work/impl/model/WorkGenerationalId;", "getWorkGenerationalId", "()Landroidx/work/impl/model/WorkGenerationalId;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "workSpecDao", "Landroidx/work/impl/model/WorkSpecDao;", "workSpecId", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "workerJob", "Lkotlinx/coroutines/CompletableJob;", "createWorkDescription", "handleResult", "", "result", "Landroidx/work/ListenableWorker$Result;", "interrupt", "", "stopReason", "", "iterativelyFailWorkAndDependents", "launch", "Lcom/google/common/util/concurrent/ListenableFuture;", "onWorkFinished", "reschedule", "resetPeriodic", "resetWorkerStatus", "runWorker", "Landroidx/work/impl/WorkerWrapper$Resolution;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setFailed", "setSucceeded", "trySetRunning", "Builder", "Resolution", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkerWrapper {
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BE\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B\u0012\u0006\u0010\f\u001A\u00020\r\u0012\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F¢\u0006\u0002\u0010\u0011J\u0006\u0010-\u001A\u00020.J\u0010\u0010/\u001A\u00020\u00002\b\u0010\u0019\u001A\u0004\u0018\u00010\u001AJ\u0010\u00100\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020(H\u0007R\u0011\u0010\u0012\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u001A\u0010\u0019\u001A\u00020\u001AX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001B\u0010\u001C\"\u0004\b\u001D\u0010\u001ER\u0017\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F¢\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 R\u0011\u0010\n\u001A\u00020\u000B¢\u0006\b\n\u0000\u001A\u0004\b!\u0010\"R\u0011\u0010\f\u001A\u00020\r¢\u0006\b\n\u0000\u001A\u0004\b#\u0010$R\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b%\u0010&R\u001C\u0010\'\u001A\u0004\u0018\u00010(X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u00061"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Builder;", "", "context", "Landroid/content/Context;", "configuration", "Landroidx/work/Configuration;", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "foregroundProcessor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "tags", "", "", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/foreground/ForegroundProcessor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/model/WorkSpec;Ljava/util/List;)V", "appContext", "getAppContext", "()Landroid/content/Context;", "getConfiguration", "()Landroidx/work/Configuration;", "getForegroundProcessor", "()Landroidx/work/impl/foreground/ForegroundProcessor;", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "getRuntimeExtras", "()Landroidx/work/WorkerParameters$RuntimeExtras;", "setRuntimeExtras", "(Landroidx/work/WorkerParameters$RuntimeExtras;)V", "getTags", "()Ljava/util/List;", "getWorkDatabase", "()Landroidx/work/impl/WorkDatabase;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "getWorkTaskExecutor", "()Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "worker", "Landroidx/work/ListenableWorker;", "getWorker", "()Landroidx/work/ListenableWorker;", "setWorker", "(Landroidx/work/ListenableWorker;)V", "build", "Landroidx/work/impl/WorkerWrapper;", "withRuntimeExtras", "withWorker", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder {
        private final Context appContext;
        private final Configuration configuration;
        private final ForegroundProcessor foregroundProcessor;
        private RuntimeExtras runtimeExtras;
        private final List tags;
        private final WorkDatabase workDatabase;
        private final WorkSpec workSpec;
        private final TaskExecutor workTaskExecutor;
        private ListenableWorker worker;

        public Builder(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, ForegroundProcessor foregroundProcessor0, WorkDatabase workDatabase0, WorkSpec workSpec0, List list0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(configuration0, "configuration");
            Intrinsics.checkNotNullParameter(taskExecutor0, "workTaskExecutor");
            Intrinsics.checkNotNullParameter(foregroundProcessor0, "foregroundProcessor");
            Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
            Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
            Intrinsics.checkNotNullParameter(list0, "tags");
            super();
            this.configuration = configuration0;
            this.workTaskExecutor = taskExecutor0;
            this.foregroundProcessor = foregroundProcessor0;
            this.workDatabase = workDatabase0;
            this.workSpec = workSpec0;
            this.tags = list0;
            Context context1 = context0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(context1, "context.applicationContext");
            this.appContext = context1;
            this.runtimeExtras = new RuntimeExtras();
        }

        public final WorkerWrapper build() {
            return new WorkerWrapper(this);
        }

        public final Context getAppContext() {
            return this.appContext;
        }

        public final Configuration getConfiguration() {
            return this.configuration;
        }

        public final ForegroundProcessor getForegroundProcessor() {
            return this.foregroundProcessor;
        }

        public final RuntimeExtras getRuntimeExtras() {
            return this.runtimeExtras;
        }

        public final List getTags() {
            return this.tags;
        }

        public final WorkDatabase getWorkDatabase() {
            return this.workDatabase;
        }

        public final WorkSpec getWorkSpec() {
            return this.workSpec;
        }

        public final TaskExecutor getWorkTaskExecutor() {
            return this.workTaskExecutor;
        }

        public final ListenableWorker getWorker() {
            return this.worker;
        }

        public final void setRuntimeExtras(RuntimeExtras workerParameters$RuntimeExtras0) {
            Intrinsics.checkNotNullParameter(workerParameters$RuntimeExtras0, "<set-?>");
            this.runtimeExtras = workerParameters$RuntimeExtras0;
        }

        public final void setWorker(ListenableWorker listenableWorker0) {
            this.worker = listenableWorker0;
        }

        public final Builder withRuntimeExtras(RuntimeExtras workerParameters$RuntimeExtras0) {
            if(workerParameters$RuntimeExtras0 != null) {
                this.runtimeExtras = workerParameters$RuntimeExtras0;
            }
            return this;
        }

        public final Builder withWorker(ListenableWorker listenableWorker0) {
            Intrinsics.checkNotNullParameter(listenableWorker0, "worker");
            this.worker = listenableWorker0;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution;", "", "()V", "Failed", "Finished", "ResetWorkerStatus", "Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static abstract class Resolution {
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Failed extends Resolution {
            private final Result result;

            public Failed() {
                this(null, 1, null);
            }

            public Failed(Result listenableWorker$Result0) {
                Intrinsics.checkNotNullParameter(listenableWorker$Result0, "result");
                super(null);
                this.result = listenableWorker$Result0;
            }

            public Failed(Result listenableWorker$Result0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
                if((v & 1) != 0) {
                    listenableWorker$Result0 = new Failure();
                }
                this(listenableWorker$Result0);
            }

            public final Result getResult() {
                return this.result;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Finished extends Resolution {
            private final Result result;

            public Finished(Result listenableWorker$Result0) {
                Intrinsics.checkNotNullParameter(listenableWorker$Result0, "result");
                super(null);
                this.result = listenableWorker$Result0;
            }

            public final Result getResult() {
                return this.result;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "reason", "", "(I)V", "getReason", "()I", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class ResetWorkerStatus extends Resolution {
            private final int reason;

            public ResetWorkerStatus() {
                this(0, 1, null);
            }

            public ResetWorkerStatus(int v) {
                super(null);
                this.reason = v;
            }

            public ResetWorkerStatus(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
                if((v1 & 1) != 0) {
                    v = 0xFFFFFF00;
                }
                this(v);
            }

            public final int getReason() {
                return this.reason;
            }
        }

        private Resolution() {
        }

        public Resolution(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private final Context appContext;
    private final ListenableWorker builderWorker;
    private final Clock clock;
    private final Configuration configuration;
    private final DependencyDao dependencyDao;
    private final ForegroundProcessor foregroundProcessor;
    private final RuntimeExtras runtimeExtras;
    private final List tags;
    private final WorkDatabase workDatabase;
    private final String workDescription;
    private final WorkSpec workSpec;
    private final WorkSpecDao workSpecDao;
    private final String workSpecId;
    private final TaskExecutor workTaskExecutor;
    private final CompletableJob workerJob;

    // 检测为 Lambda 实现
    public static Boolean $r8$lambda$O0DrdUDQSEqVo7n3_JmOuQMb-xQ(WorkerWrapper workerWrapper0) [...]

    // 检测为 Lambda 实现
    public static Boolean $r8$lambda$pgTj0TbLDPXkl5H_yrOVgH1dL-U(WorkerWrapper workerWrapper0) [...]

    public WorkerWrapper(Builder workerWrapper$Builder0) {
        Intrinsics.checkNotNullParameter(workerWrapper$Builder0, "builder");
        super();
        WorkSpec workSpec0 = workerWrapper$Builder0.getWorkSpec();
        this.workSpec = workSpec0;
        this.appContext = workerWrapper$Builder0.getAppContext();
        this.workSpecId = workSpec0.id;
        this.runtimeExtras = workerWrapper$Builder0.getRuntimeExtras();
        this.builderWorker = workerWrapper$Builder0.getWorker();
        this.workTaskExecutor = workerWrapper$Builder0.getWorkTaskExecutor();
        Configuration configuration0 = workerWrapper$Builder0.getConfiguration();
        this.configuration = configuration0;
        this.clock = configuration0.getClock();
        this.foregroundProcessor = workerWrapper$Builder0.getForegroundProcessor();
        WorkDatabase workDatabase0 = workerWrapper$Builder0.getWorkDatabase();
        this.workDatabase = workDatabase0;
        this.workSpecDao = workDatabase0.workSpecDao();
        this.dependencyDao = workDatabase0.dependencyDao();
        List list0 = workerWrapper$Builder0.getTags();
        this.tags = list0;
        this.workDescription = this.createWorkDescription(list0);
        this.workerJob = JobKt.Job$default(null, 1, null);
    }

    private final String createWorkDescription(List list0) {
        return "Work [ id=" + this.workSpecId + ", tags={ " + CollectionsKt.joinToString$default(list0, ",", null, null, 0, null, null, 62, null) + " } ]";
    }

    public final WorkGenerationalId getWorkGenerationalId() {
        return WorkSpecKt.generationalId(this.workSpec);
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }

    private final boolean handleResult(Result listenableWorker$Result0) {
        if(listenableWorker$Result0 instanceof Success) {
            Logger.get().info("WM-WorkerWrapper", "Worker result SUCCESS for " + this.workDescription);
            return this.workSpec.isPeriodic() ? this.resetPeriodic() : this.setSucceeded(listenableWorker$Result0);
        }
        if(listenableWorker$Result0 instanceof Retry) {
            Logger.get().info("WM-WorkerWrapper", "Worker result RETRY for " + this.workDescription);
            return this.reschedule(0xFFFFFF00);
        }
        Logger.get().info("WM-WorkerWrapper", "Worker result FAILURE for " + this.workDescription);
        if(this.workSpec.isPeriodic()) {
            return this.resetPeriodic();
        }
        if(listenableWorker$Result0 == null) {
            listenableWorker$Result0 = new Failure();
        }
        return this.setFailed(listenableWorker$Result0);
    }

    public final void interrupt(int v) {
        CancellationException cancellationException0 = new WorkerStoppedException(v);
        this.workerJob.cancel(cancellationException0);
    }

    private final void iterativelyFailWorkAndDependents(String s) {
        List list0 = CollectionsKt.mutableListOf(new String[]{s});
        while(!list0.isEmpty()) {
            String s1 = (String)CollectionsKt.removeLast(list0);
            if(this.workSpecDao.getState(s1) != State.CANCELLED) {
                this.workSpecDao.setState(State.FAILED, s1);
            }
            list0.addAll(this.dependencyDao.getDependentWorkIds(s1));
        }
    }

    public final ListenableFuture launch() {
        return ListenableFutureKt.launchFuture$default(this.workTaskExecutor.getTaskCoroutineDispatcher().plus(JobKt.Job$default(null, 1, null)), null, new Function2(null) {
            int label;

            // 检测为 Lambda 实现
            public static Boolean $r8$lambda$Y7GYLbRKyArUGZkgQLkfflh3BxY(Resolution workerWrapper$Resolution0, WorkerWrapper workerWrapper0) [...]

            {
                WorkerWrapper.this = workerWrapper0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.work.impl.WorkerWrapper.launch.1(WorkerWrapper.this, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.work.impl.WorkerWrapper.launch.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Resolution workerWrapper$Resolution0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        try {
                            androidx.work.impl.WorkerWrapper.launch.1.resolution.1 workerWrapper$launch$1$resolution$10 = new Function2(null) {
                                int label;

                                {
                                    WorkerWrapper.this = workerWrapper0;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    return new androidx.work.impl.WorkerWrapper.launch.1.resolution.1(WorkerWrapper.this, continuation0);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                    return ((androidx.work.impl.WorkerWrapper.launch.1.resolution.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            this.label = 1;
                                            Object object2 = WorkerWrapper.this.runWorker(this);
                                            return object2 == object1 ? object1 : object2;
                                        }
                                        case 1: {
                                            ResultKt.throwOnFailure(object0);
                                            return object0;
                                        }
                                        default: {
                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                        }
                                    }
                                }
                            };
                            this.label = 1;
                            object0 = BuildersKt.withContext(WorkerWrapper.this.workerJob, workerWrapper$launch$1$resolution$10, this);
                            if(object0 == object1) {
                                return object1;
                            label_9:
                                ResultKt.throwOnFailure(object0);
                            }
                            workerWrapper$Resolution0 = (Resolution)object0;
                            goto label_20;
                        }
                        catch(WorkerStoppedException workerStoppedException0) {
                            break;
                        }
                        catch(CancellationException unused_ex) {
                            workerWrapper$Resolution0 = new Failed(null, 1, null);
                            goto label_20;
                        }
                        catch(Throwable throwable0) {
                            goto label_18;
                        }
                    }
                    case 1: {
                        goto label_9;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                workerWrapper$Resolution0 = new ResetWorkerStatus(workerStoppedException0.getReason());
                goto label_20;
            label_18:
                Logger.get().error("WM-WorkerWrapper", "Unexpected error in WorkerWrapper", throwable0);
                workerWrapper$Resolution0 = new Failed(null, 1, null);
            label_20:
                Object object2 = WorkerWrapper.this.workDatabase.runInTransaction(() -> androidx.work.impl.WorkerWrapper.launch.1.invokeSuspend$lambda$1(workerWrapper$Resolution0, WorkerWrapper.this));
                Intrinsics.checkNotNullExpressionValue(object2, "workDatabase.runInTransa…          }\n            )");
                return object2;
            }

            private static final Boolean invokeSuspend$lambda$1(Resolution workerWrapper$Resolution0, WorkerWrapper workerWrapper0) {
                if(workerWrapper$Resolution0 instanceof Finished) {
                    return Boolean.valueOf(workerWrapper0.onWorkFinished(((Finished)workerWrapper$Resolution0).getResult()));
                }
                if(workerWrapper$Resolution0 instanceof Failed) {
                    workerWrapper0.setFailed(((Failed)workerWrapper$Resolution0).getResult());
                    return false;
                }
                if(!(workerWrapper$Resolution0 instanceof ResetWorkerStatus)) {
                    throw new NoWhenBranchMatchedException();
                }
                return Boolean.valueOf(workerWrapper0.resetWorkerStatus(((ResetWorkerStatus)workerWrapper$Resolution0).getReason()));
            }
        }, 2, null);
    }

    private final boolean onWorkFinished(Result listenableWorker$Result0) {
        State workInfo$State0 = this.workSpecDao.getState(this.workSpecId);
        this.workDatabase.workProgressDao().delete(this.workSpecId);
        if(workInfo$State0 == null) {
            return false;
        }
        return workInfo$State0 == State.RUNNING ? this.handleResult(listenableWorker$Result0) : this.reschedule(0xFFFFFE00);
    }

    private final boolean reschedule(int v) {
        this.workSpecDao.setState(State.ENQUEUED, this.workSpecId);
        long v1 = this.clock.currentTimeMillis();
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, v1);
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1L);
        this.workSpecDao.setStopReason(this.workSpecId, v);
        return true;
    }

    private final boolean resetPeriodic() {
        long v = this.clock.currentTimeMillis();
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, v);
        this.workSpecDao.setState(State.ENQUEUED, this.workSpecId);
        this.workSpecDao.resetWorkSpecRunAttemptCount(this.workSpecId);
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.incrementPeriodCount(this.workSpecId);
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1L);
        return false;
    }

    private final boolean resetWorkerStatus(int v) {
        State workInfo$State0 = this.workSpecDao.getState(this.workSpecId);
        if(workInfo$State0 != null) {
            Logger.get().debug("WM-WorkerWrapper", "Status for " + this.workSpecId + " is " + workInfo$State0 + "; not doing any work and rescheduling for later execution");
            this.workSpecDao.setState(State.ENQUEUED, this.workSpecId);
            this.workSpecDao.setStopReason(this.workSpecId, v);
            this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1L);
            return true;
        }
        Logger.get().debug("WM-WorkerWrapper", "Status for " + this.workSpecId + " is " + null + " ; not doing any work");
        return false;
    }

    private final Object runWorker(Continuation continuation0) {
        WorkerParameters workerParameters1;
        WorkerWrapper workerWrapper0;
        Data data0;
        androidx.work.impl.WorkerWrapper.runWorker.1 workerWrapper$runWorker$10;
        if(continuation0 instanceof androidx.work.impl.WorkerWrapper.runWorker.1) {
            workerWrapper$runWorker$10 = (androidx.work.impl.WorkerWrapper.runWorker.1)continuation0;
            if((workerWrapper$runWorker$10.label & 0x80000000) == 0) {
                workerWrapper$runWorker$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.runWorker(this);
                    }
                };
            }
            else {
                workerWrapper$runWorker$10.label ^= 0x80000000;
            }
        }
        else {
            workerWrapper$runWorker$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.runWorker(this);
                }
            };
        }
        Object object0 = workerWrapper$runWorker$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(workerWrapper$runWorker$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                boolean z = this.configuration.getTracer().isEnabled();
                String s = this.workSpec.getTraceTag();
                if(z && s != null) {
                    this.configuration.getTracer().beginAsyncSection(s, this.workSpec.hashCode());
                }
                WorkerWrapper..ExternalSyntheticLambda0 workerWrapper$$ExternalSyntheticLambda00 = () -> WorkerWrapper.runWorker$lambda$1(this);
                Boolean boolean0 = (Boolean)this.workDatabase.runInTransaction(workerWrapper$$ExternalSyntheticLambda00);
                Intrinsics.checkNotNullExpressionValue(boolean0, "shouldExit");
                if(boolean0.booleanValue()) {
                    return new ResetWorkerStatus(0, 1, null);
                }
                if(this.workSpec.isPeriodic()) {
                    data0 = this.workSpec.input;
                }
                else {
                    InputMerger inputMerger0 = this.configuration.getInputMergerFactory().createInputMergerWithDefaultFallback(this.workSpec.inputMergerClassName);
                    if(inputMerger0 == null) {
                        Logger.get().error("WM-WorkerWrapper", "Could not create Input Merger " + this.workSpec.inputMergerClassName);
                        return new Failed(null, 1, null);
                    }
                    data0 = inputMerger0.merge(CollectionsKt.plus(CollectionsKt.listOf(this.workSpec.input), this.workSpecDao.getInputsFromPrerequisites(this.workSpecId)));
                }
                UUID uUID0 = UUID.fromString(this.workSpecId);
                int v = this.workSpec.runAttemptCount;
                WorkProgressUpdater workProgressUpdater0 = new WorkProgressUpdater(this.workDatabase, this.workTaskExecutor);
                WorkForegroundUpdater workForegroundUpdater0 = new WorkForegroundUpdater(this.workDatabase, this.foregroundProcessor, this.workTaskExecutor);
                WorkerParameters workerParameters0 = new WorkerParameters(uUID0, data0, this.tags, this.runtimeExtras, v, this.workSpec.getGeneration(), this.configuration.getExecutor(), this.configuration.getWorkerCoroutineContext(), this.workTaskExecutor, this.configuration.getWorkerFactory(), workProgressUpdater0, workForegroundUpdater0);
                ListenableWorker listenableWorker0 = this.builderWorker;
                if(listenableWorker0 == null) {
                    try {
                        listenableWorker0 = this.configuration.getWorkerFactory().createWorkerWithDefaultFallback(this.appContext, this.workSpec.workerClassName, workerParameters0);
                    }
                    catch(Throwable throwable0) {
                        Logger.get().error("WM-WorkerWrapper", "Could not create Worker " + this.workSpec.workerClassName);
                        Consumer consumer0 = this.configuration.getWorkerInitializationExceptionHandler();
                        if(consumer0 != null) {
                            WorkerExceptionUtilsKt.safeAccept(consumer0, new WorkerExceptionInfo(this.workSpec.workerClassName, workerParameters0, throwable0), "WM-WorkerWrapper");
                        }
                        return new Failed(null, 1, null);
                    }
                }
                listenableWorker0.setUsed();
                Element coroutineContext$Element0 = workerWrapper$runWorker$10.getContext().get(Job.Key);
                Intrinsics.checkNotNull(coroutineContext$Element0);
                ((Job)coroutineContext$Element0).invokeOnCompletion(new Function1(z, s, this) {
                    final boolean $isTracingEnabled;
                    final String $traceTag;
                    final ListenableWorker $worker;

                    {
                        this.$worker = listenableWorker0;
                        this.$isTracingEnabled = z;
                        this.$traceTag = s;
                        WorkerWrapper.this = workerWrapper0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((Throwable)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable throwable0) {
                        if(throwable0 instanceof WorkerStoppedException) {
                            int v = ((WorkerStoppedException)throwable0).getReason();
                            this.$worker.stop(v);
                        }
                        if(this.$isTracingEnabled && this.$traceTag != null) {
                            WorkerWrapper.this.configuration.getTracer().endAsyncSection(this.$traceTag, WorkerWrapper.this.getWorkSpec().hashCode());
                        }
                    }
                });
                if(!this.trySetRunning()) {
                    return new ResetWorkerStatus(0, 1, null);
                }
                if(((Job)coroutineContext$Element0).isCancelled()) {
                    return new ResetWorkerStatus(0, 1, null);
                }
                ForegroundUpdater foregroundUpdater0 = workerParameters0.getForegroundUpdater();
                Intrinsics.checkNotNullExpressionValue(foregroundUpdater0, "params.foregroundUpdater");
                Executor executor0 = this.workTaskExecutor.getMainThreadExecutor();
                Intrinsics.checkNotNullExpressionValue(executor0, "workTaskExecutor.getMainThreadExecutor()");
                CoroutineDispatcher coroutineDispatcher0 = ExecutorsKt.from(executor0);
                try {
                    androidx.work.impl.WorkerWrapper.runWorker.result.1 workerWrapper$runWorker$result$10 = new Function2(listenableWorker0, foregroundUpdater0, null) {
                        final ForegroundUpdater $foregroundUpdater;
                        final ListenableWorker $worker;
                        int label;

                        {
                            WorkerWrapper.this = workerWrapper0;
                            this.$worker = listenableWorker0;
                            this.$foregroundUpdater = foregroundUpdater0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            return new androidx.work.impl.WorkerWrapper.runWorker.result.1(WorkerWrapper.this, this.$worker, this.$foregroundUpdater, continuation0);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                            return ((androidx.work.impl.WorkerWrapper.runWorker.result.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    this.label = 1;
                                    if(WorkForegroundKt.workForeground(WorkerWrapper.this.appContext, WorkerWrapper.this.getWorkSpec(), this.$worker, this.$foregroundUpdater, WorkerWrapper.this.workTaskExecutor, this) == object1) {
                                        return object1;
                                    }
                                    break;
                                }
                                case 1: {
                                    ResultKt.throwOnFailure(object0);
                                    break;
                                }
                                case 2: {
                                    ResultKt.throwOnFailure(object0);
                                    return object0;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                            Logger.get().debug("WM-WorkerWrapper", "Starting work for " + WorkerWrapper.this.getWorkSpec().workerClassName);
                            ListenableFuture listenableFuture0 = this.$worker.startWork();
                            Intrinsics.checkNotNullExpressionValue(listenableFuture0, "worker.startWork()");
                            this.label = 2;
                            Object object2 = WorkerWrapperKt.awaitWithin(listenableFuture0, this.$worker, this);
                            return object2 == object1 ? object1 : object2;
                        }
                    };
                    workerWrapper$runWorker$10.L$0 = this;
                    workerWrapper$runWorker$10.L$1 = workerParameters0;
                    workerWrapper$runWorker$10.label = 1;
                    object0 = BuildersKt.withContext(coroutineDispatcher0, workerWrapper$runWorker$result$10, workerWrapper$runWorker$10);
                }
                catch(CancellationException cancellationException0) {
                    workerWrapper0 = this;
                    break;
                }
                catch(Throwable throwable1) {
                    workerWrapper0 = this;
                    workerParameters1 = workerParameters0;
                    goto label_85;
                }
                if(object0 == object1) {
                    return object1;
                }
                workerWrapper0 = this;
                workerParameters1 = workerParameters0;
                goto label_79;
            }
            case 1: {
                workerParameters1 = (WorkerParameters)workerWrapper$runWorker$10.L$1;
                workerWrapper0 = (WorkerWrapper)workerWrapper$runWorker$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                label_79:
                    Intrinsics.checkNotNullExpressionValue(((Result)object0), "result");
                    return new Finished(((Result)object0));
                }
                catch(CancellationException cancellationException0) {
                    break;
                }
                catch(Throwable throwable1) {
                    goto label_85;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        Logger.get().info("WM-WorkerWrapper", workerWrapper0.workDescription + " was cancelled", cancellationException0);
        throw cancellationException0;
    label_85:
        Logger.get().error("WM-WorkerWrapper", workerWrapper0.workDescription + " failed because it threw an exception/error", throwable1);
        Consumer consumer1 = workerWrapper0.configuration.getWorkerExecutionExceptionHandler();
        if(consumer1 != null) {
            WorkerExceptionUtilsKt.safeAccept(consumer1, new WorkerExceptionInfo(workerWrapper0.workSpec.workerClassName, workerParameters1, throwable1), "WM-WorkerWrapper");
        }
        return new Failed(null, 1, null);
    }

    private static final Boolean runWorker$lambda$1(WorkerWrapper workerWrapper0) {
        if(workerWrapper0.workSpec.state != State.ENQUEUED) {
            Logger.get().debug("WM-WorkerWrapper", workerWrapper0.workSpec.workerClassName + " is not in ENQUEUED state. Nothing more to do");
            return true;
        }
        if((workerWrapper0.workSpec.isPeriodic() || workerWrapper0.workSpec.isBackedOff()) && workerWrapper0.clock.currentTimeMillis() < workerWrapper0.workSpec.calculateNextRunTime()) {
            Logger.get().debug("WM-WorkerWrapper", "Delaying execution for " + workerWrapper0.workSpec.workerClassName + " because it is being executed before schedule.");
            return true;
        }
        return false;
    }

    public final boolean setFailed(Result listenableWorker$Result0) {
        Intrinsics.checkNotNullParameter(listenableWorker$Result0, "result");
        this.iterativelyFailWorkAndDependents(this.workSpecId);
        Data data0 = ((Failure)listenableWorker$Result0).getOutputData();
        Intrinsics.checkNotNullExpressionValue(data0, "failure.outputData");
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.setOutput(this.workSpecId, data0);
        return false;
    }

    private final boolean setSucceeded(Result listenableWorker$Result0) {
        this.workSpecDao.setState(State.SUCCEEDED, this.workSpecId);
        Intrinsics.checkNotNull(listenableWorker$Result0, "null cannot be cast to non-null type androidx.work.ListenableWorker.Result.Success");
        Data data0 = ((Success)listenableWorker$Result0).getOutputData();
        Intrinsics.checkNotNullExpressionValue(data0, "success.outputData");
        this.workSpecDao.setOutput(this.workSpecId, data0);
        long v = this.clock.currentTimeMillis();
        for(Object object0: this.dependencyDao.getDependentWorkIds(this.workSpecId)) {
            String s = (String)object0;
            if(this.workSpecDao.getState(s) == State.BLOCKED && this.dependencyDao.hasCompletedAllPrerequisites(s)) {
                Logger.get().info("WM-WorkerWrapper", "Setting status to enqueued for " + s);
                this.workSpecDao.setState(State.ENQUEUED, s);
                this.workSpecDao.setLastEnqueueTime(s, v);
            }
        }
        return false;
    }

    private final boolean trySetRunning() {
        WorkerWrapper..ExternalSyntheticLambda1 workerWrapper$$ExternalSyntheticLambda10 = () -> WorkerWrapper.trySetRunning$lambda$11(this);
        Object object0 = this.workDatabase.runInTransaction(workerWrapper$$ExternalSyntheticLambda10);
        Intrinsics.checkNotNullExpressionValue(object0, "workDatabase.runInTransa…e\n            }\n        )");
        return ((Boolean)object0).booleanValue();
    }

    private static final Boolean trySetRunning$lambda$11(WorkerWrapper workerWrapper0) {
        if(workerWrapper0.workSpecDao.getState(workerWrapper0.workSpecId) == State.ENQUEUED) {
            workerWrapper0.workSpecDao.setState(State.RUNNING, workerWrapper0.workSpecId);
            workerWrapper0.workSpecDao.incrementWorkSpecRunAttemptCount(workerWrapper0.workSpecId);
            workerWrapper0.workSpecDao.setStopReason(workerWrapper0.workSpecId, 0xFFFFFF00);
            return true;
        }
        return false;
    }
}

