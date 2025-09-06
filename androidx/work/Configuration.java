package androidx.work;

import android.os.Build.VERSION;
import androidx.core.util.Consumer;
import androidx.work.impl.DefaultRunnableScheduler;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 F2\u00020\u0001:\u0003EFGB\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001A\u00020\n\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0013\u0010\r\u001A\u0004\u0018\u00010\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0011\u001A\u00020\u0012\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001A\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001A\u001A\u00020\u001B\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001DR\u001C\u0010\u001E\u001A\u00020\u001F8GX\u0087\u0004\u00A2\u0006\u000E\n\u0000\u0012\u0004\b \u0010!\u001A\u0004\b\u001E\u0010\"R\u0013\u0010#\u001A\u00020\u001F8G\u00A2\u0006\b\n\u0000\u001A\u0004\b#\u0010\"R\u0011\u0010$\u001A\u00020\n\u00A2\u0006\b\n\u0000\u001A\u0004\b%\u0010\fR\u0013\u0010&\u001A\u00020\n8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\'\u0010\fR\u0011\u0010(\u001A\u00020\n\u00A2\u0006\b\n\u0000\u001A\u0004\b)\u0010\fR\u0013\u0010*\u001A\u00020\n8G\u00A2\u0006\b\n\u0000\u001A\u0004\b+\u0010\fR\u0011\u0010,\u001A\u00020-\u00A2\u0006\b\n\u0000\u001A\u0004\b.\u0010/R\u0019\u00100\u001A\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016\u00A2\u0006\b\n\u0000\u001A\u0004\b1\u0010\u0019R\u0011\u00102\u001A\u00020\u0012\u00A2\u0006\b\n\u0000\u001A\u0004\b3\u0010\u0014R\u0013\u00104\u001A\u0002058G\u00A2\u0006\b\n\u0000\u001A\u0004\b6\u00107R\u0011\u00108\u001A\u000209\u00A2\u0006\b\n\u0000\u001A\u0004\b:\u0010;R\u0019\u0010<\u001A\n\u0012\u0004\u0012\u00020=\u0018\u00010\u0016\u00A2\u0006\b\n\u0000\u001A\u0004\b>\u0010\u0019R\u0011\u0010?\u001A\u00020@\u00A2\u0006\b\n\u0000\u001A\u0004\bA\u0010BR\u0019\u0010C\u001A\n\u0012\u0004\u0012\u00020=\u0018\u00010\u0016\u00A2\u0006\b\n\u0000\u001A\u0004\bD\u0010\u0019\u00A8\u0006H"}, d2 = {"Landroidx/work/Configuration;", "", "builder", "Landroidx/work/Configuration$Builder;", "(Landroidx/work/Configuration$Builder;)V", "clock", "Landroidx/work/Clock;", "getClock", "()Landroidx/work/Clock;", "contentUriTriggerWorkersLimit", "", "getContentUriTriggerWorkersLimit", "()I", "defaultProcessName", "", "getDefaultProcessName", "()Ljava/lang/String;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "initializationExceptionHandler", "Landroidx/core/util/Consumer;", "", "getInitializationExceptionHandler", "()Landroidx/core/util/Consumer;", "inputMergerFactory", "Landroidx/work/InputMergerFactory;", "getInputMergerFactory", "()Landroidx/work/InputMergerFactory;", "isMarkingJobsAsImportantWhileForeground", "", "isMarkingJobsAsImportantWhileForeground$annotations", "()V", "()Z", "isUsingDefaultTaskExecutor", "maxJobSchedulerId", "getMaxJobSchedulerId", "maxSchedulerLimit", "getMaxSchedulerLimit", "minJobSchedulerId", "getMinJobSchedulerId", "minimumLoggingLevel", "getMinimumLoggingLevel", "runnableScheduler", "Landroidx/work/RunnableScheduler;", "getRunnableScheduler", "()Landroidx/work/RunnableScheduler;", "schedulingExceptionHandler", "getSchedulingExceptionHandler", "taskExecutor", "getTaskExecutor", "tracer", "Landroidx/work/Tracer;", "getTracer", "()Landroidx/work/Tracer;", "workerCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getWorkerCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "workerExecutionExceptionHandler", "Landroidx/work/WorkerExceptionInfo;", "getWorkerExecutionExceptionHandler", "workerFactory", "Landroidx/work/WorkerFactory;", "getWorkerFactory", "()Landroidx/work/WorkerFactory;", "workerInitializationExceptionHandler", "getWorkerInitializationExceptionHandler", "Builder", "Companion", "Provider", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Configuration {
    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \u0018\u00002\u00020\u0001B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0002B\u000F\b\u0017\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0006\u0010b\u001A\u00020\u0004J\u000E\u0010c\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007J\u000E\u0010d\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020\rJ\u000E\u0010e\u001A\u00020\u00002\u0006\u0010f\u001A\u00020\u0013J\u000E\u0010g\u001A\u00020\u00002\u0006\u0010\u0018\u001A\u00020\u0019J\u0014\u0010h\u001A\u00020\u00002\f\u0010i\u001A\b\u0012\u0004\u0012\u00020 0\u001FJ\u000E\u0010j\u001A\u00020\u00002\u0006\u0010%\u001A\u00020&J\u0016\u0010k\u001A\u00020\u00002\u0006\u0010:\u001A\u00020\r2\u0006\u00104\u001A\u00020\rJ\u0010\u0010l\u001A\u00020\u00002\u0006\u0010m\u001A\u00020/H\u0007J\u000E\u0010n\u001A\u00020\u00002\u0006\u00107\u001A\u00020\rJ\u000E\u0010o\u001A\u00020\u00002\u0006\u0010+\u001A\u00020\rJ\u000E\u0010p\u001A\u00020\u00002\u0006\u0010=\u001A\u00020>J\u0014\u0010q\u001A\u00020\u00002\f\u0010C\u001A\b\u0012\u0004\u0012\u00020 0\u001FJ\u000E\u0010r\u001A\u00020\u00002\u0006\u0010F\u001A\u00020\u0019J\u0010\u0010s\u001A\u00020\u00002\u0006\u0010I\u001A\u00020JH\u0007J\u000E\u0010t\u001A\u00020\u00002\u0006\u0010u\u001A\u00020PJ\u0014\u0010v\u001A\u00020\u00002\f\u0010w\u001A\b\u0012\u0004\u0012\u00020V0\u001FJ\u000E\u0010x\u001A\u00020\u00002\u0006\u0010Y\u001A\u00020ZJ\u0014\u0010y\u001A\u00020\u00002\f\u0010w\u001A\b\u0012\u0004\u0012\u00020V0\u001FR\u001C\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000BR\u001A\u0010\f\u001A\u00020\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u000F\"\u0004\b\u0010\u0010\u0011R\u001C\u0010\u0012\u001A\u0004\u0018\u00010\u0013X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001C\u0010\u0018\u001A\u0004\u0018\u00010\u0019X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001A\u0010\u001B\"\u0004\b\u001C\u0010\u001DR\"\u0010\u001E\u001A\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001FX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001C\u0010%\u001A\u0004\u0018\u00010&X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001A\u0010+\u001A\u00020\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b,\u0010\u000F\"\u0004\b-\u0010\u0011R\u001A\u0010.\u001A\u00020/X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b0\u00101\"\u0004\b2\u00103R\u001A\u00104\u001A\u00020\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b5\u0010\u000F\"\u0004\b6\u0010\u0011R\u001A\u00107\u001A\u00020\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b8\u0010\u000F\"\u0004\b9\u0010\u0011R\u001A\u0010:\u001A\u00020\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b;\u0010\u000F\"\u0004\b<\u0010\u0011R\u001C\u0010=\u001A\u0004\u0018\u00010>X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b?\u0010@\"\u0004\bA\u0010BR\"\u0010C\u001A\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001FX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bD\u0010\"\"\u0004\bE\u0010$R\u001C\u0010F\u001A\u0004\u0018\u00010\u0019X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bG\u0010\u001B\"\u0004\bH\u0010\u001DR\u001C\u0010I\u001A\u0004\u0018\u00010JX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001C\u0010O\u001A\u0004\u0018\u00010PX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bQ\u0010R\"\u0004\bS\u0010TR\"\u0010U\u001A\n\u0012\u0004\u0012\u00020V\u0018\u00010\u001FX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bW\u0010\"\"\u0004\bX\u0010$R\u001C\u0010Y\u001A\u0004\u0018\u00010ZX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b[\u0010\\\"\u0004\b]\u0010^R\"\u0010_\u001A\n\u0012\u0004\u0012\u00020V\u0018\u00010\u001FX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b`\u0010\"\"\u0004\ba\u0010$\u00A8\u0006z"}, d2 = {"Landroidx/work/Configuration$Builder;", "", "()V", "configuration", "Landroidx/work/Configuration;", "(Landroidx/work/Configuration;)V", "clock", "Landroidx/work/Clock;", "getClock$work_runtime_release", "()Landroidx/work/Clock;", "setClock$work_runtime_release", "(Landroidx/work/Clock;)V", "contentUriTriggerWorkersLimit", "", "getContentUriTriggerWorkersLimit$work_runtime_release", "()I", "setContentUriTriggerWorkersLimit$work_runtime_release", "(I)V", "defaultProcessName", "", "getDefaultProcessName$work_runtime_release", "()Ljava/lang/String;", "setDefaultProcessName$work_runtime_release", "(Ljava/lang/String;)V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor$work_runtime_release", "()Ljava/util/concurrent/Executor;", "setExecutor$work_runtime_release", "(Ljava/util/concurrent/Executor;)V", "initializationExceptionHandler", "Landroidx/core/util/Consumer;", "", "getInitializationExceptionHandler$work_runtime_release", "()Landroidx/core/util/Consumer;", "setInitializationExceptionHandler$work_runtime_release", "(Landroidx/core/util/Consumer;)V", "inputMergerFactory", "Landroidx/work/InputMergerFactory;", "getInputMergerFactory$work_runtime_release", "()Landroidx/work/InputMergerFactory;", "setInputMergerFactory$work_runtime_release", "(Landroidx/work/InputMergerFactory;)V", "loggingLevel", "getLoggingLevel$work_runtime_release", "setLoggingLevel$work_runtime_release", "markJobsAsImportantWhileForeground", "", "getMarkJobsAsImportantWhileForeground$work_runtime_release", "()Z", "setMarkJobsAsImportantWhileForeground$work_runtime_release", "(Z)V", "maxJobSchedulerId", "getMaxJobSchedulerId$work_runtime_release", "setMaxJobSchedulerId$work_runtime_release", "maxSchedulerLimit", "getMaxSchedulerLimit$work_runtime_release", "setMaxSchedulerLimit$work_runtime_release", "minJobSchedulerId", "getMinJobSchedulerId$work_runtime_release", "setMinJobSchedulerId$work_runtime_release", "runnableScheduler", "Landroidx/work/RunnableScheduler;", "getRunnableScheduler$work_runtime_release", "()Landroidx/work/RunnableScheduler;", "setRunnableScheduler$work_runtime_release", "(Landroidx/work/RunnableScheduler;)V", "schedulingExceptionHandler", "getSchedulingExceptionHandler$work_runtime_release", "setSchedulingExceptionHandler$work_runtime_release", "taskExecutor", "getTaskExecutor$work_runtime_release", "setTaskExecutor$work_runtime_release", "tracer", "Landroidx/work/Tracer;", "getTracer$work_runtime_release", "()Landroidx/work/Tracer;", "setTracer$work_runtime_release", "(Landroidx/work/Tracer;)V", "workerContext", "Lkotlin/coroutines/CoroutineContext;", "getWorkerContext$work_runtime_release", "()Lkotlin/coroutines/CoroutineContext;", "setWorkerContext$work_runtime_release", "(Lkotlin/coroutines/CoroutineContext;)V", "workerExecutionExceptionHandler", "Landroidx/work/WorkerExceptionInfo;", "getWorkerExecutionExceptionHandler$work_runtime_release", "setWorkerExecutionExceptionHandler$work_runtime_release", "workerFactory", "Landroidx/work/WorkerFactory;", "getWorkerFactory$work_runtime_release", "()Landroidx/work/WorkerFactory;", "setWorkerFactory$work_runtime_release", "(Landroidx/work/WorkerFactory;)V", "workerInitializationExceptionHandler", "getWorkerInitializationExceptionHandler$work_runtime_release", "setWorkerInitializationExceptionHandler$work_runtime_release", "build", "setClock", "setContentUriTriggerWorkersLimit", "setDefaultProcessName", "processName", "setExecutor", "setInitializationExceptionHandler", "exceptionHandler", "setInputMergerFactory", "setJobSchedulerJobIdRange", "setMarkingJobsAsImportantWhileForeground", "markAsImportant", "setMaxSchedulerLimit", "setMinimumLoggingLevel", "setRunnableScheduler", "setSchedulingExceptionHandler", "setTaskExecutor", "setTracer", "setWorkerCoroutineContext", "context", "setWorkerExecutionExceptionHandler", "workerExceptionHandler", "setWorkerFactory", "setWorkerInitializationExceptionHandler", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder {
        private Clock clock;
        private int contentUriTriggerWorkersLimit;
        private String defaultProcessName;
        private Executor executor;
        private Consumer initializationExceptionHandler;
        private InputMergerFactory inputMergerFactory;
        private int loggingLevel;
        private boolean markJobsAsImportantWhileForeground;
        private int maxJobSchedulerId;
        private int maxSchedulerLimit;
        private int minJobSchedulerId;
        private RunnableScheduler runnableScheduler;
        private Consumer schedulingExceptionHandler;
        private Executor taskExecutor;
        private Tracer tracer;
        private CoroutineContext workerContext;
        private Consumer workerExecutionExceptionHandler;
        private WorkerFactory workerFactory;
        private Consumer workerInitializationExceptionHandler;

        public Builder() {
            this.loggingLevel = 4;
            this.maxJobSchedulerId = 0x7FFFFFFF;
            this.maxSchedulerLimit = 20;
            this.contentUriTriggerWorkersLimit = 8;
            this.markJobsAsImportantWhileForeground = true;
        }

        public Builder(Configuration configuration0) {
            Intrinsics.checkNotNullParameter(configuration0, "configuration");
            super();
            this.loggingLevel = 4;
            this.maxJobSchedulerId = 0x7FFFFFFF;
            this.maxSchedulerLimit = 20;
            this.contentUriTriggerWorkersLimit = 8;
            this.markJobsAsImportantWhileForeground = true;
            this.executor = configuration0.getExecutor();
            this.workerFactory = configuration0.getWorkerFactory();
            this.inputMergerFactory = configuration0.getInputMergerFactory();
            this.taskExecutor = configuration0.getTaskExecutor();
            this.clock = configuration0.getClock();
            this.loggingLevel = configuration0.getMinimumLoggingLevel();
            this.minJobSchedulerId = configuration0.getMinJobSchedulerId();
            this.maxJobSchedulerId = configuration0.getMaxJobSchedulerId();
            this.maxSchedulerLimit = configuration0.getMaxSchedulerLimit();
            this.runnableScheduler = configuration0.getRunnableScheduler();
            this.initializationExceptionHandler = configuration0.getInitializationExceptionHandler();
            this.schedulingExceptionHandler = configuration0.getSchedulingExceptionHandler();
            this.workerInitializationExceptionHandler = configuration0.getWorkerInitializationExceptionHandler();
            this.workerExecutionExceptionHandler = configuration0.getWorkerExecutionExceptionHandler();
            this.defaultProcessName = configuration0.getDefaultProcessName();
            this.contentUriTriggerWorkersLimit = configuration0.getContentUriTriggerWorkersLimit();
            this.markJobsAsImportantWhileForeground = configuration0.isMarkingJobsAsImportantWhileForeground();
            this.tracer = configuration0.getTracer();
        }

        public final Configuration build() {
            return new Configuration(this);
        }

        public final Clock getClock$work_runtime_release() {
            return this.clock;
        }

        public final int getContentUriTriggerWorkersLimit$work_runtime_release() {
            return this.contentUriTriggerWorkersLimit;
        }

        public final String getDefaultProcessName$work_runtime_release() {
            return this.defaultProcessName;
        }

        public final Executor getExecutor$work_runtime_release() {
            return this.executor;
        }

        public final Consumer getInitializationExceptionHandler$work_runtime_release() {
            return this.initializationExceptionHandler;
        }

        public final InputMergerFactory getInputMergerFactory$work_runtime_release() {
            return this.inputMergerFactory;
        }

        public final int getLoggingLevel$work_runtime_release() {
            return this.loggingLevel;
        }

        public final boolean getMarkJobsAsImportantWhileForeground$work_runtime_release() {
            return this.markJobsAsImportantWhileForeground;
        }

        public final int getMaxJobSchedulerId$work_runtime_release() {
            return this.maxJobSchedulerId;
        }

        public final int getMaxSchedulerLimit$work_runtime_release() {
            return this.maxSchedulerLimit;
        }

        public final int getMinJobSchedulerId$work_runtime_release() {
            return this.minJobSchedulerId;
        }

        public final RunnableScheduler getRunnableScheduler$work_runtime_release() {
            return this.runnableScheduler;
        }

        public final Consumer getSchedulingExceptionHandler$work_runtime_release() {
            return this.schedulingExceptionHandler;
        }

        public final Executor getTaskExecutor$work_runtime_release() {
            return this.taskExecutor;
        }

        public final Tracer getTracer$work_runtime_release() {
            return this.tracer;
        }

        public final CoroutineContext getWorkerContext$work_runtime_release() {
            return this.workerContext;
        }

        public final Consumer getWorkerExecutionExceptionHandler$work_runtime_release() {
            return this.workerExecutionExceptionHandler;
        }

        public final WorkerFactory getWorkerFactory$work_runtime_release() {
            return this.workerFactory;
        }

        public final Consumer getWorkerInitializationExceptionHandler$work_runtime_release() {
            return this.workerInitializationExceptionHandler;
        }

        public final Builder setClock(Clock clock0) {
            Intrinsics.checkNotNullParameter(clock0, "clock");
            this.clock = clock0;
            return this;
        }

        public final void setClock$work_runtime_release(Clock clock0) {
            this.clock = clock0;
        }

        public final Builder setContentUriTriggerWorkersLimit(int v) {
            this.contentUriTriggerWorkersLimit = Math.max(v, 0);
            return this;
        }

        public final void setContentUriTriggerWorkersLimit$work_runtime_release(int v) {
            this.contentUriTriggerWorkersLimit = v;
        }

        public final Builder setDefaultProcessName(String s) {
            Intrinsics.checkNotNullParameter(s, "processName");
            this.defaultProcessName = s;
            return this;
        }

        public final void setDefaultProcessName$work_runtime_release(String s) {
            this.defaultProcessName = s;
        }

        public final Builder setExecutor(Executor executor0) {
            Intrinsics.checkNotNullParameter(executor0, "executor");
            this.executor = executor0;
            return this;
        }

        public final void setExecutor$work_runtime_release(Executor executor0) {
            this.executor = executor0;
        }

        public final Builder setInitializationExceptionHandler(Consumer consumer0) {
            Intrinsics.checkNotNullParameter(consumer0, "exceptionHandler");
            this.initializationExceptionHandler = consumer0;
            return this;
        }

        public final void setInitializationExceptionHandler$work_runtime_release(Consumer consumer0) {
            this.initializationExceptionHandler = consumer0;
        }

        public final Builder setInputMergerFactory(InputMergerFactory inputMergerFactory0) {
            Intrinsics.checkNotNullParameter(inputMergerFactory0, "inputMergerFactory");
            this.inputMergerFactory = inputMergerFactory0;
            return this;
        }

        public final void setInputMergerFactory$work_runtime_release(InputMergerFactory inputMergerFactory0) {
            this.inputMergerFactory = inputMergerFactory0;
        }

        public final Builder setJobSchedulerJobIdRange(int v, int v1) {
            if(v1 - v < 1000) {
                throw new IllegalArgumentException("WorkManager needs a range of at least 1000 job ids.");
            }
            this.minJobSchedulerId = v;
            this.maxJobSchedulerId = v1;
            return this;
        }

        public final void setLoggingLevel$work_runtime_release(int v) {
            this.loggingLevel = v;
        }

        public final void setMarkJobsAsImportantWhileForeground$work_runtime_release(boolean z) {
            this.markJobsAsImportantWhileForeground = z;
        }

        public final Builder setMarkingJobsAsImportantWhileForeground(boolean z) {
            this.markJobsAsImportantWhileForeground = z;
            return this;
        }

        public final void setMaxJobSchedulerId$work_runtime_release(int v) {
            this.maxJobSchedulerId = v;
        }

        public final Builder setMaxSchedulerLimit(int v) {
            if(v < 20) {
                throw new IllegalArgumentException("WorkManager needs to be able to schedule at least 20 jobs in JobScheduler.");
            }
            this.maxSchedulerLimit = Math.min(v, 50);
            return this;
        }

        public final void setMaxSchedulerLimit$work_runtime_release(int v) {
            this.maxSchedulerLimit = v;
        }

        public final void setMinJobSchedulerId$work_runtime_release(int v) {
            this.minJobSchedulerId = v;
        }

        public final Builder setMinimumLoggingLevel(int v) {
            this.loggingLevel = v;
            return this;
        }

        public final Builder setRunnableScheduler(RunnableScheduler runnableScheduler0) {
            Intrinsics.checkNotNullParameter(runnableScheduler0, "runnableScheduler");
            this.runnableScheduler = runnableScheduler0;
            return this;
        }

        public final void setRunnableScheduler$work_runtime_release(RunnableScheduler runnableScheduler0) {
            this.runnableScheduler = runnableScheduler0;
        }

        public final Builder setSchedulingExceptionHandler(Consumer consumer0) {
            Intrinsics.checkNotNullParameter(consumer0, "schedulingExceptionHandler");
            this.schedulingExceptionHandler = consumer0;
            return this;
        }

        public final void setSchedulingExceptionHandler$work_runtime_release(Consumer consumer0) {
            this.schedulingExceptionHandler = consumer0;
        }

        public final Builder setTaskExecutor(Executor executor0) {
            Intrinsics.checkNotNullParameter(executor0, "taskExecutor");
            this.taskExecutor = executor0;
            return this;
        }

        public final void setTaskExecutor$work_runtime_release(Executor executor0) {
            this.taskExecutor = executor0;
        }

        public final Builder setTracer(Tracer tracer0) {
            Intrinsics.checkNotNullParameter(tracer0, "tracer");
            this.tracer = tracer0;
            return this;
        }

        public final void setTracer$work_runtime_release(Tracer tracer0) {
            this.tracer = tracer0;
        }

        public final void setWorkerContext$work_runtime_release(CoroutineContext coroutineContext0) {
            this.workerContext = coroutineContext0;
        }

        public final Builder setWorkerCoroutineContext(CoroutineContext coroutineContext0) {
            Intrinsics.checkNotNullParameter(coroutineContext0, "context");
            this.workerContext = coroutineContext0;
            return this;
        }

        public final Builder setWorkerExecutionExceptionHandler(Consumer consumer0) {
            Intrinsics.checkNotNullParameter(consumer0, "workerExceptionHandler");
            this.workerExecutionExceptionHandler = consumer0;
            return this;
        }

        public final void setWorkerExecutionExceptionHandler$work_runtime_release(Consumer consumer0) {
            this.workerExecutionExceptionHandler = consumer0;
        }

        public final Builder setWorkerFactory(WorkerFactory workerFactory0) {
            Intrinsics.checkNotNullParameter(workerFactory0, "workerFactory");
            this.workerFactory = workerFactory0;
            return this;
        }

        public final void setWorkerFactory$work_runtime_release(WorkerFactory workerFactory0) {
            this.workerFactory = workerFactory0;
        }

        public final Builder setWorkerInitializationExceptionHandler(Consumer consumer0) {
            Intrinsics.checkNotNullParameter(consumer0, "workerExceptionHandler");
            this.workerInitializationExceptionHandler = consumer0;
            return this;
        }

        public final void setWorkerInitializationExceptionHandler$work_runtime_release(Consumer consumer0) {
            this.workerInitializationExceptionHandler = consumer0;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/work/Configuration$Companion;", "", "()V", "MIN_SCHEDULER_LIMIT", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001A\u00020\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/work/Configuration$Provider;", "", "workManagerConfiguration", "Landroidx/work/Configuration;", "getWorkManagerConfiguration", "()Landroidx/work/Configuration;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface Provider {
        Configuration getWorkManagerConfiguration();
    }

    public static final Companion Companion = null;
    public static final int MIN_SCHEDULER_LIMIT = 20;
    private final Clock clock;
    private final int contentUriTriggerWorkersLimit;
    private final String defaultProcessName;
    private final Executor executor;
    private final Consumer initializationExceptionHandler;
    private final InputMergerFactory inputMergerFactory;
    private final boolean isMarkingJobsAsImportantWhileForeground;
    private final boolean isUsingDefaultTaskExecutor;
    private final int maxJobSchedulerId;
    private final int maxSchedulerLimit;
    private final int minJobSchedulerId;
    private final int minimumLoggingLevel;
    private final RunnableScheduler runnableScheduler;
    private final Consumer schedulingExceptionHandler;
    private final Executor taskExecutor;
    private final Tracer tracer;
    private final CoroutineContext workerCoroutineContext;
    private final Consumer workerExecutionExceptionHandler;
    private final WorkerFactory workerFactory;
    private final Consumer workerInitializationExceptionHandler;

    static {
        Configuration.Companion = new Companion(null);
    }

    public Configuration(Builder configuration$Builder0) {
        Intrinsics.checkNotNullParameter(configuration$Builder0, "builder");
        super();
        CoroutineContext coroutineContext0 = configuration$Builder0.getWorkerContext$work_runtime_release();
        Executor executor0 = configuration$Builder0.getExecutor$work_runtime_release();
        boolean z = false;
        if(executor0 == null) {
            executor0 = coroutineContext0 == null ? null : ConfigurationKt.access$asExecutor(coroutineContext0);
            if(executor0 == null) {
                executor0 = ConfigurationKt.access$createDefaultExecutor(false);
            }
        }
        this.executor = executor0;
        if(coroutineContext0 == null) {
            coroutineContext0 = configuration$Builder0.getExecutor$work_runtime_release() == null ? Dispatchers.getDefault() : ExecutorsKt.from(executor0);
        }
        this.workerCoroutineContext = coroutineContext0;
        if(configuration$Builder0.getTaskExecutor$work_runtime_release() == null) {
            z = true;
        }
        this.isUsingDefaultTaskExecutor = z;
        this.taskExecutor = configuration$Builder0.getTaskExecutor$work_runtime_release() == null ? ConfigurationKt.access$createDefaultExecutor(true) : configuration$Builder0.getTaskExecutor$work_runtime_release();
        Clock clock0 = configuration$Builder0.getClock$work_runtime_release();
        if(clock0 == null) {
            clock0 = new SystemClock();
        }
        this.clock = clock0;
        WorkerFactory workerFactory0 = configuration$Builder0.getWorkerFactory$work_runtime_release();
        if(workerFactory0 == null) {
            workerFactory0 = DefaultWorkerFactory.INSTANCE;
        }
        this.workerFactory = workerFactory0;
        InputMergerFactory inputMergerFactory0 = configuration$Builder0.getInputMergerFactory$work_runtime_release();
        if(inputMergerFactory0 == null) {
            inputMergerFactory0 = NoOpInputMergerFactory.INSTANCE;
        }
        this.inputMergerFactory = inputMergerFactory0;
        RunnableScheduler runnableScheduler0 = configuration$Builder0.getRunnableScheduler$work_runtime_release();
        if(runnableScheduler0 == null) {
            runnableScheduler0 = new DefaultRunnableScheduler();
        }
        this.runnableScheduler = runnableScheduler0;
        this.minimumLoggingLevel = configuration$Builder0.getLoggingLevel$work_runtime_release();
        this.minJobSchedulerId = configuration$Builder0.getMinJobSchedulerId$work_runtime_release();
        this.maxJobSchedulerId = configuration$Builder0.getMaxJobSchedulerId$work_runtime_release();
        this.maxSchedulerLimit = Build.VERSION.SDK_INT == 23 ? configuration$Builder0.getMaxSchedulerLimit$work_runtime_release() / 2 : configuration$Builder0.getMaxSchedulerLimit$work_runtime_release();
        this.initializationExceptionHandler = configuration$Builder0.getInitializationExceptionHandler$work_runtime_release();
        this.schedulingExceptionHandler = configuration$Builder0.getSchedulingExceptionHandler$work_runtime_release();
        this.workerInitializationExceptionHandler = configuration$Builder0.getWorkerInitializationExceptionHandler$work_runtime_release();
        this.workerExecutionExceptionHandler = configuration$Builder0.getWorkerExecutionExceptionHandler$work_runtime_release();
        this.defaultProcessName = configuration$Builder0.getDefaultProcessName$work_runtime_release();
        this.contentUriTriggerWorkersLimit = configuration$Builder0.getContentUriTriggerWorkersLimit$work_runtime_release();
        this.isMarkingJobsAsImportantWhileForeground = configuration$Builder0.getMarkJobsAsImportantWhileForeground$work_runtime_release();
        this.tracer = configuration$Builder0.getTracer$work_runtime_release() == null ? ConfigurationKt.access$createDefaultTracer() : configuration$Builder0.getTracer$work_runtime_release();
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final int getContentUriTriggerWorkersLimit() {
        return this.contentUriTriggerWorkersLimit;
    }

    public final String getDefaultProcessName() {
        return this.defaultProcessName;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final Consumer getInitializationExceptionHandler() {
        return this.initializationExceptionHandler;
    }

    public final InputMergerFactory getInputMergerFactory() {
        return this.inputMergerFactory;
    }

    public final int getMaxJobSchedulerId() {
        return this.maxJobSchedulerId;
    }

    public final int getMaxSchedulerLimit() {
        return this.maxSchedulerLimit;
    }

    public final int getMinJobSchedulerId() {
        return this.minJobSchedulerId;
    }

    public final int getMinimumLoggingLevel() {
        return this.minimumLoggingLevel;
    }

    public final RunnableScheduler getRunnableScheduler() {
        return this.runnableScheduler;
    }

    public final Consumer getSchedulingExceptionHandler() {
        return this.schedulingExceptionHandler;
    }

    public final Executor getTaskExecutor() {
        return this.taskExecutor;
    }

    public final Tracer getTracer() {
        return this.tracer;
    }

    public final CoroutineContext getWorkerCoroutineContext() {
        return this.workerCoroutineContext;
    }

    public final Consumer getWorkerExecutionExceptionHandler() {
        return this.workerExecutionExceptionHandler;
    }

    public final WorkerFactory getWorkerFactory() {
        return this.workerFactory;
    }

    public final Consumer getWorkerInitializationExceptionHandler() {
        return this.workerInitializationExceptionHandler;
    }

    public final boolean isMarkingJobsAsImportantWhileForeground() {
        return this.isMarkingJobsAsImportantWhileForeground;
    }

    public static void isMarkingJobsAsImportantWhileForeground$annotations() {
    }

    public final boolean isUsingDefaultTaskExecutor() {
        return this.isUsingDefaultTaskExecutor;
    }
}

