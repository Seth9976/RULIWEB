package kotlinx.coroutines.scheduling;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\b\u0000\u0018\u0000 I2\u00020\u00012\u00020\u0002:\u0003IJKB)\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\nJ\u0010\u0010\u001E\u001A\u00020\u00182\u0006\u0010\u001F\u001A\u00020 H\u0002J\u0011\u0010\r\u001A\u00020\u00042\u0006\u0010!\u001A\u00020\u0007H\u0086\bJ\u0011\u0010\"\u001A\u00020\u00042\u0006\u0010!\u001A\u00020\u0007H\u0082\bJ\b\u0010#\u001A\u00020$H\u0016J\b\u0010%\u001A\u00020\u0004H\u0002J\u001A\u0010&\u001A\u00020 2\n\u0010\'\u001A\u00060(j\u0002`)2\u0006\u0010*\u001A\u00020+J\u0011\u0010\u0012\u001A\u00020\u00042\u0006\u0010!\u001A\u00020\u0007H\u0082\bJ\u000E\u0010,\u001A\b\u0018\u00010\u001DR\u00020\u0000H\u0002J\t\u0010-\u001A\u00020$H\u0082\bJ\t\u0010.\u001A\u00020\u0004H\u0082\bJ&\u0010/\u001A\u00020$2\n\u0010\'\u001A\u00060(j\u0002`)2\b\b\u0002\u0010*\u001A\u00020+2\b\b\u0002\u00100\u001A\u00020\u0018J\u0014\u00101\u001A\u00020$2\n\u00102\u001A\u00060(j\u0002`)H\u0016J\t\u00103\u001A\u00020\u0007H\u0082\bJ\t\u00104\u001A\u00020\u0004H\u0082\bJ\u0014\u00105\u001A\u00020\u00042\n\u00106\u001A\u00060\u001DR\u00020\u0000H\u0002J\u000E\u00107\u001A\b\u0018\u00010\u001DR\u00020\u0000H\u0002J\u0012\u00108\u001A\u00020\u00182\n\u00106\u001A\u00060\u001DR\u00020\u0000J\"\u00109\u001A\u00020$2\n\u00106\u001A\u00060\u001DR\u00020\u00002\u0006\u0010:\u001A\u00020\u00042\u0006\u0010;\u001A\u00020\u0004J\t\u0010<\u001A\u00020\u0007H\u0082\bJ\u000E\u0010=\u001A\u00020$2\u0006\u0010\u001F\u001A\u00020 J\u000E\u0010>\u001A\u00020$2\u0006\u0010?\u001A\u00020\u0007J\u0018\u0010@\u001A\u00020$2\u0006\u0010A\u001A\u00020\u00072\u0006\u0010B\u001A\u00020\u0018H\u0002J\u0006\u0010C\u001A\u00020$J\b\u0010D\u001A\u00020\tH\u0016J\t\u0010E\u001A\u00020\u0018H\u0082\bJ\u0012\u0010F\u001A\u00020\u00182\b\b\u0002\u0010!\u001A\u00020\u0007H\u0002J\b\u0010G\u001A\u00020\u0018H\u0002J$\u0010H\u001A\u0004\u0018\u00010 *\b\u0018\u00010\u001DR\u00020\u00002\u0006\u0010\u001F\u001A\u00020 2\u0006\u00100\u001A\u00020\u0018H\u0002R\t\u0010\u000B\u001A\u00020\fX\u0082\u0004R\u0015\u0010\r\u001A\u00020\u00048\u00C2\u0002X\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\t\u0010\u0010\u001A\u00020\u0011X\u0082\u0004R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0015\u0010\u0012\u001A\u00020\u00048\u00C2\u0002X\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u000FR\u0010\u0010\u0014\u001A\u00020\u00158\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001A\u00020\u00158\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00078\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001A\u00020\u00188F\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0019R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\t\u0010\u001A\u001A\u00020\u0011X\u0082\u0004R\u0010\u0010\b\u001A\u00020\t8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u001B\u001A\f\u0012\b\u0012\u00060\u001DR\u00020\u00000\u001C8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006L"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "Ljava/util/concurrent/Executor;", "Ljava/io/Closeable;", "corePoolSize", "", "maxPoolSize", "idleWorkerKeepAliveNs", "", "schedulerName", "", "(IIJLjava/lang/String;)V", "_isTerminated", "Lkotlinx/atomicfu/AtomicBoolean;", "availableCpuPermits", "getAvailableCpuPermits", "()I", "controlState", "Lkotlinx/atomicfu/AtomicLong;", "createdWorkers", "getCreatedWorkers", "globalBlockingQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalCpuQueue", "isTerminated", "", "()Z", "parkedWorkersStack", "workers", "Lkotlinx/coroutines/internal/ResizableAtomicArray;", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "addToGlobalQueue", "task", "Lkotlinx/coroutines/scheduling/Task;", "state", "blockingTasks", "close", "", "createNewWorker", "createTask", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "taskContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "currentWorker", "decrementBlockingTasks", "decrementCreatedWorkers", "dispatch", "tailDispatch", "execute", "command", "incrementBlockingTasks", "incrementCreatedWorkers", "parkedWorkersStackNextIndex", "worker", "parkedWorkersStackPop", "parkedWorkersStackPush", "parkedWorkersStackTopUpdate", "oldIndex", "newIndex", "releaseCpuPermit", "runSafely", "shutdown", "timeout", "signalBlockingWork", "stateSnapshot", "skipUnpark", "signalCpuWork", "toString", "tryAcquireCpuPermit", "tryCreateWorker", "tryUnpark", "submitToLocalQueue", "Companion", "Worker", "WorkerState", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutineScheduler implements Closeable, AutoCloseable, Executor {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u00020\u000E8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Companion;", "", "()V", "BLOCKING_MASK", "", "BLOCKING_SHIFT", "", "CLAIMED", "CPU_PERMITS_MASK", "CPU_PERMITS_SHIFT", "CREATED_MASK", "MAX_SUPPORTED_POOL_SIZE", "MIN_SUPPORTED_POOL_SIZE", "NOT_IN_STACK", "Lkotlinx/coroutines/internal/Symbol;", "PARKED", "PARKED_INDEX_MASK", "PARKED_VERSION_INC", "PARKED_VERSION_MASK", "TERMINATED", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[WorkerState.values().length];
            try {
                arr_v[WorkerState.PARKING.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[WorkerState.BLOCKING.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[WorkerState.CPU_ACQUIRED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[WorkerState.DORMANT.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[WorkerState.TERMINATED.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u001A\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0005J\u0010\u0010$\u001A\u00020%2\u0006\u0010&\u001A\u00020\u0003H\u0002J\u0010\u0010\'\u001A\u00020%2\u0006\u0010&\u001A\u00020\u0003H\u0002J\u0010\u0010(\u001A\u00020%2\u0006\u0010)\u001A\u00020 H\u0002J\u0012\u0010*\u001A\u0004\u0018\u00010 2\u0006\u0010+\u001A\u00020\u000EH\u0002J\n\u0010,\u001A\u0004\u0018\u00010 H\u0002J\n\u0010-\u001A\u0004\u0018\u00010 H\u0002J\u0010\u0010.\u001A\u0004\u0018\u00010 2\u0006\u0010\r\u001A\u00020\u000EJ\u0010\u0010/\u001A\u00020%2\u0006\u00100\u001A\u00020\u0003H\u0002J\b\u00101\u001A\u00020\u000EH\u0002J\u0006\u00102\u001A\u00020\u000EJ\u000E\u00103\u001A\u00020\u00032\u0006\u00104\u001A\u00020\u0003J\b\u00105\u001A\u00020%H\u0002J\n\u00106\u001A\u0004\u0018\u00010 H\u0002J\b\u00107\u001A\u00020%H\u0016J\u0006\u00108\u001A\u00020\u0010J\b\u00109\u001A\u00020%H\u0002J\b\u0010:\u001A\u00020\u000EH\u0002J\b\u0010;\u001A\u00020%H\u0002J\u000E\u0010<\u001A\u00020\u000E2\u0006\u0010=\u001A\u00020\u001DJ\u0016\u0010>\u001A\u0004\u0018\u00010 2\n\u0010?\u001A\u00060\u0003j\u0002`@H\u0002J\b\u0010A\u001A\u00020%H\u0002R$\u0010\u0006\u001A\u00020\u00032\u0006\u0010\u0002\u001A\u00020\u0003@FX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000B\u001A\u00020\f8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\r\u001A\u00020\u000E8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001C\u0010\u0011\u001A\u0004\u0018\u00010\u0012X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000E\u0010\u0017\u001A\u00020\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001A\u00020\u00198\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u001A\u0010\u001BR\u0012\u0010\u001C\u001A\u00020\u001D8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u001E\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u001FX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010!\u001A\u00020\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0006\u0010\"\u001A\u00020#\u00A8\u0006B"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "Ljava/lang/Thread;", "index", "", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;I)V", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;)V", "indexInArray", "getIndexInArray", "()I", "setIndexInArray", "(I)V", "localQueue", "Lkotlinx/coroutines/scheduling/WorkQueue;", "mayHaveLocalTasks", "", "minDelayUntilStealableTaskNs", "", "nextParkedWorker", "", "getNextParkedWorker", "()Ljava/lang/Object;", "setNextParkedWorker", "(Ljava/lang/Object;)V", "rngState", "scheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "getScheduler", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "state", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "stolenTask", "Lkotlin/jvm/internal/Ref$ObjectRef;", "Lkotlinx/coroutines/scheduling/Task;", "terminationDeadline", "workerCtl", "Lkotlinx/atomicfu/AtomicInt;", "afterTask", "", "taskMode", "beforeTask", "executeTask", "task", "findAnyTask", "scanLocalQueue", "findBlockingTask", "findCpuTask", "findTask", "idleReset", "mode", "inStack", "isIo", "nextInt", "upperBound", "park", "pollGlobalQueues", "run", "runSingleTask", "runWorker", "tryAcquireCpuPermit", "tryPark", "tryReleaseCpu", "newState", "trySteal", "stealingMode", "Lkotlinx/coroutines/scheduling/StealingMode;", "tryTerminateWorker", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public final class Worker extends Thread {
        private volatile int indexInArray;
        public final WorkQueue localQueue;
        public boolean mayHaveLocalTasks;
        private long minDelayUntilStealableTaskNs;
        private volatile Object nextParkedWorker;
        private int rngState;
        public WorkerState state;
        private final ObjectRef stolenTask;
        private long terminationDeadline;
        @Volatile
        private volatile int workerCtl;
        private static final AtomicIntegerFieldUpdater workerCtl$FU;

        static {
            Worker.workerCtl$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl");
        }

        private Worker() {
            this.setDaemon(true);
            this.localQueue = new WorkQueue();
            this.stolenTask = new ObjectRef();
            this.state = WorkerState.DORMANT;
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            this.rngState = Random.Default.nextInt();
        }

        public Worker(int v) {
            this.setIndexInArray(v);
        }

        private final void afterTask(int v) {
            if(v != 0) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, 0xFFFFFFFFFFE00000L);
                if(this.state != WorkerState.TERMINATED) {
                    this.state = WorkerState.DORMANT;
                }
            }
        }

        private final void beforeTask(int v) {
            if(v != 0 && this.tryReleaseCpu(WorkerState.BLOCKING)) {
                CoroutineScheduler.this.signalCpuWork();
            }
        }

        private final void executeTask(Task task0) {
            int v = task0.taskContext.getTaskMode();
            this.idleReset(v);
            this.beforeTask(v);
            CoroutineScheduler.this.runSafely(task0);
            this.afterTask(v);
        }

        private final Task findAnyTask(boolean z) {
            if(z) {
                boolean z1 = this.nextInt(CoroutineScheduler.this.corePoolSize * 2) == 0;
                if(z1) {
                    Task task0 = this.pollGlobalQueues();
                    if(task0 != null) {
                        return task0;
                    }
                }
                Task task1 = this.localQueue.poll();
                if(task1 != null) {
                    return task1;
                }
                if(!z1) {
                    Task task2 = this.pollGlobalQueues();
                    return task2 == null ? this.trySteal(3) : task2;
                }
                return this.trySteal(3);
            }
            Task task3 = this.pollGlobalQueues();
            return task3 == null ? this.trySteal(3) : task3;
        }

        private final Task findBlockingTask() {
            Task task0 = this.localQueue.pollBlocking();
            if(task0 == null) {
                task0 = (Task)CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
                return task0 == null ? this.trySteal(1) : task0;
            }
            return task0;
        }

        private final Task findCpuTask() {
            Task task0 = this.localQueue.pollCpu();
            if(task0 == null) {
                task0 = (Task)CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
                return task0 == null ? this.trySteal(2) : task0;
            }
            return task0;
        }

        // 去混淆评级： 低(20)
        public final Task findTask(boolean z) {
            return this.tryAcquireCpuPermit() ? this.findAnyTask(z) : this.findBlockingTask();
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final CoroutineScheduler getScheduler() {
            return CoroutineScheduler.this;
        }

        public final int getWorkerCtl() {
            return this.workerCtl;
        }

        public static final AtomicIntegerFieldUpdater getWorkerCtl$FU() {
            return Worker.workerCtl$FU;
        }

        private final void idleReset(int v) {
            this.terminationDeadline = 0L;
            if(this.state == WorkerState.PARKING) {
                this.state = WorkerState.BLOCKING;
            }
        }

        private final boolean inStack() {
            return this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK;
        }

        public final boolean isIo() {
            return this.state == WorkerState.BLOCKING;
        }

        public final int nextInt(int v) {
            int v1 = this.rngState ^ this.rngState << 13;
            int v2 = v1 ^ v1 >> 17;
            int v3 = v2 ^ v2 << 5;
            this.rngState = v3;
            return (v - 1 & v) == 0 ? v3 & v - 1 : (v3 & 0x7FFFFFFF) % v;
        }

        private final void park() {
            if(this.terminationDeadline == 0L) {
                this.terminationDeadline = System.nanoTime() + CoroutineScheduler.this.idleWorkerKeepAliveNs;
            }
            LockSupport.parkNanos(CoroutineScheduler.this.idleWorkerKeepAliveNs);
            if(System.nanoTime() - this.terminationDeadline >= 0L) {
                this.terminationDeadline = 0L;
                this.tryTerminateWorker();
            }
        }

        private final Task pollGlobalQueues() {
            if(this.nextInt(2) == 0) {
                Task task0 = (Task)CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
                return task0 == null ? ((Task)CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull()) : task0;
            }
            Task task1 = (Task)CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            return task1 == null ? ((Task)CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull()) : task1;
        }

        @Override
        public void run() {
            this.runWorker();
        }

        public final long runSingleTask() {
            boolean z = this.state == WorkerState.CPU_ACQUIRED;
            Task task0 = z ? this.findCpuTask() : this.findBlockingTask();
            if(task0 == null) {
                return this.minDelayUntilStealableTaskNs == 0L ? -1L : this.minDelayUntilStealableTaskNs;
            }
            CoroutineScheduler.this.runSafely(task0);
            if(!z) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, 0xFFFFFFFFFFE00000L);
            }
            return 0L;
        }

        private final void runWorker() {
        alab1:
            while(true) {
                boolean z = false;
                while(true) {
                    if(CoroutineScheduler.this.isTerminated() || this.state == WorkerState.TERMINATED) {
                        break alab1;
                    }
                    Task task0 = this.findTask(this.mayHaveLocalTasks);
                    if(task0 != null) {
                        this.minDelayUntilStealableTaskNs = 0L;
                        this.executeTask(task0);
                        break;
                    }
                    this.mayHaveLocalTasks = false;
                    if(this.minDelayUntilStealableTaskNs == 0L) {
                        this.tryPark();
                    }
                    else {
                        if(z) {
                            this.tryReleaseCpu(WorkerState.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
                            this.minDelayUntilStealableTaskNs = 0L;
                            break;
                        }
                        z = true;
                    }
                }
            }
            this.tryReleaseCpu(WorkerState.TERMINATED);
        }

        public final void setIndexInArray(int v) {
            this.setName(CoroutineScheduler.this.schedulerName + "-worker-" + (v == 0 ? "TERMINATED" : String.valueOf(v)));
            this.indexInArray = v;
        }

        public final void setNextParkedWorker(Object object0) {
            this.nextParkedWorker = object0;
        }

        private final boolean tryAcquireCpuPermit() {
            if(this.state == WorkerState.CPU_ACQUIRED) {
                return true;
            }
            CoroutineScheduler coroutineScheduler0 = CoroutineScheduler.this;
            AtomicLongFieldUpdater atomicLongFieldUpdater0 = CoroutineScheduler.controlState$FU;
            do {
                long v = atomicLongFieldUpdater0.get(coroutineScheduler0);
                if(((int)((0x7FFFFC0000000000L & v) >> 42)) == 0) {
                    return false;
                }
            }
            while(!CoroutineScheduler.controlState$FU.compareAndSet(coroutineScheduler0, v, v - 0x40000000000L));
            this.state = WorkerState.CPU_ACQUIRED;
            return true;
        }

        private final void tryPark() {
            if(!this.inStack()) {
                CoroutineScheduler.this.parkedWorkersStackPush(this);
                return;
            }
            Worker.workerCtl$FU.set(this, -1);
            while(this.inStack() && Worker.workerCtl$FU.get(this) == -1 && !CoroutineScheduler.this.isTerminated() && this.state != WorkerState.TERMINATED) {
                this.tryReleaseCpu(WorkerState.PARKING);
                Thread.interrupted();
                this.park();
            }
        }

        public final boolean tryReleaseCpu(WorkerState coroutineScheduler$WorkerState0) {
            WorkerState coroutineScheduler$WorkerState1 = this.state;
            boolean z = coroutineScheduler$WorkerState1 == WorkerState.CPU_ACQUIRED;
            if(z) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, 0x40000000000L);
            }
            if(coroutineScheduler$WorkerState1 != coroutineScheduler$WorkerState0) {
                this.state = coroutineScheduler$WorkerState0;
            }
            return z;
        }

        private final Task trySteal(int v) {
            long v1 = CoroutineScheduler.controlState$FU.get(CoroutineScheduler.this);
            if(((int)(v1 & 0x1FFFFFL)) < 2) {
                return null;
            }
            int v2 = this.nextInt(((int)(v1 & 0x1FFFFFL)));
            CoroutineScheduler coroutineScheduler0 = CoroutineScheduler.this;
            long v4 = 0x7FFFFFFFFFFFFFFFL;
            for(int v3 = 0; v3 < ((int)(v1 & 0x1FFFFFL)); ++v3) {
                ++v2;
                v2 = v2 <= ((int)(v1 & 0x1FFFFFL)) ? v2 + 1 : 1;
                Worker coroutineScheduler$Worker0 = (Worker)coroutineScheduler0.workers.get(v2);
                if(coroutineScheduler$Worker0 != null && coroutineScheduler$Worker0 != this) {
                    long v5 = coroutineScheduler$Worker0.localQueue.trySteal(v, this.stolenTask);
                    if(v5 == -1L) {
                        Task task0 = (Task)this.stolenTask.element;
                        this.stolenTask.element = null;
                        return task0;
                    }
                    if(v5 > 0L) {
                        v4 = Math.min(v4, v5);
                    }
                }
            }
            if(v4 == 0x7FFFFFFFFFFFFFFFL) {
                v4 = 0L;
            }
            this.minDelayUntilStealableTaskNs = v4;
            return null;
        }

        private final void tryTerminateWorker() {
            ResizableAtomicArray resizableAtomicArray0 = CoroutineScheduler.this.workers;
            CoroutineScheduler coroutineScheduler0 = CoroutineScheduler.this;
            synchronized(resizableAtomicArray0) {
                if(coroutineScheduler0.isTerminated()) {
                    return;
                }
                if(((int)(CoroutineScheduler.controlState$FU.get(coroutineScheduler0) & 0x1FFFFFL)) <= coroutineScheduler0.corePoolSize) {
                    return;
                }
                if(!Worker.workerCtl$FU.compareAndSet(this, -1, 1)) {
                    return;
                }
                int v1 = this.indexInArray;
                this.setIndexInArray(0);
                coroutineScheduler0.parkedWorkersStackTopUpdate(this, v1, 0);
                long v2 = CoroutineScheduler.controlState$FU.getAndDecrement(coroutineScheduler0);
                if(((int)(0x1FFFFFL & v2)) != v1) {
                    Object object0 = coroutineScheduler0.workers.get(((int)(0x1FFFFFL & v2)));
                    Intrinsics.checkNotNull(object0);
                    coroutineScheduler0.workers.setSynchronized(v1, ((Worker)object0));
                    ((Worker)object0).setIndexInArray(v1);
                    coroutineScheduler0.parkedWorkersStackTopUpdate(((Worker)object0), ((int)(0x1FFFFFL & v2)), v1);
                }
                coroutineScheduler0.workers.setSynchronized(((int)(0x1FFFFFL & v2)), null);
            }
            this.state = WorkerState.TERMINATED;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "", "(Ljava/lang/String;I)V", "CPU_ACQUIRED", "BLOCKING", "PARKING", "DORMANT", "TERMINATED", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED;

        private static final WorkerState[] $values() [...] // Inlined contents
    }

    private static final long BLOCKING_MASK = 0x3FFFFE00000L;
    private static final int BLOCKING_SHIFT = 21;
    private static final int CLAIMED = 0;
    private static final long CPU_PERMITS_MASK = 0x7FFFFC0000000000L;
    private static final int CPU_PERMITS_SHIFT = 42;
    private static final long CREATED_MASK = 0x1FFFFFL;
    public static final Companion Companion = null;
    public static final int MAX_SUPPORTED_POOL_SIZE = 0x1FFFFE;
    public static final int MIN_SUPPORTED_POOL_SIZE = 1;
    public static final Symbol NOT_IN_STACK = null;
    private static final int PARKED = -1;
    private static final long PARKED_INDEX_MASK = 0x1FFFFFL;
    private static final long PARKED_VERSION_INC = 0x200000L;
    private static final long PARKED_VERSION_MASK = 0xFFFFFFFFFFE00000L;
    private static final int TERMINATED = 1;
    @Volatile
    private volatile int _isTerminated;
    private static final AtomicIntegerFieldUpdater _isTerminated$FU;
    @Volatile
    private volatile long controlState;
    private static final AtomicLongFieldUpdater controlState$FU;
    public final int corePoolSize;
    public final GlobalQueue globalBlockingQueue;
    public final GlobalQueue globalCpuQueue;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;
    @Volatile
    private volatile long parkedWorkersStack;
    private static final AtomicLongFieldUpdater parkedWorkersStack$FU;
    public final String schedulerName;
    public final ResizableAtomicArray workers;

    static {
        CoroutineScheduler.Companion = new Companion(null);
        CoroutineScheduler.parkedWorkersStack$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");
        CoroutineScheduler.controlState$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
        CoroutineScheduler._isTerminated$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");
        CoroutineScheduler.NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    }

    public CoroutineScheduler(int v, int v1, long v2, String s) {
        this.corePoolSize = v;
        this.maxPoolSize = v1;
        this.idleWorkerKeepAliveNs = v2;
        this.schedulerName = s;
        if(v < 1) {
            throw new IllegalArgumentException(("Core pool size " + v + " should be at least 1").toString());
        }
        if(v1 < v) {
            throw new IllegalArgumentException(("Max pool size " + v1 + " should be greater than or equals to core pool size " + v).toString());
        }
        if(v1 > 0x1FFFFE) {
            throw new IllegalArgumentException(("Max pool size " + v1 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if(v2 <= 0L) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + v2 + " must be positive").toString());
        }
        this.globalCpuQueue = new GlobalQueue();
        this.globalBlockingQueue = new GlobalQueue();
        this.workers = new ResizableAtomicArray((v + 1) * 2);
        this.controlState = ((long)v) << 42;
        this._isTerminated = 0;
    }

    public CoroutineScheduler(int v, int v1, long v2, String s, int v3, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v3 & 4) != 0) {
            v2 = TasksKt.IDLE_WORKER_KEEP_ALIVE_NS;
        }
        if((v3 & 8) != 0) {
            s = "DefaultDispatcher";
        }
        this(v, v1, v2, s);
    }

    private final boolean addToGlobalQueue(Task task0) {
        return task0.taskContext.getTaskMode() == 1 ? this.globalBlockingQueue.addLast(task0) : this.globalCpuQueue.addLast(task0);
    }

    public final int availableCpuPermits(long v) {
        return (int)((v & 0x7FFFFC0000000000L) >> 42);
    }

    private final int blockingTasks(long v) {
        return (int)((v & 0x3FFFFE00000L) >> 21);
    }

    @Override
    public void close() {
        this.shutdown(10000L);
    }

    private final int createNewWorker() {
        synchronized(this.workers) {
            if(this.isTerminated()) {
                return -1;
            }
            AtomicLongFieldUpdater atomicLongFieldUpdater0 = CoroutineScheduler.controlState$FU;
            long v1 = atomicLongFieldUpdater0.get(this);
            int v2 = RangesKt.coerceAtLeast(((int)(v1 & 0x1FFFFFL)) - ((int)((v1 & 0x3FFFFE00000L) >> 21)), 0);
            if(v2 >= this.corePoolSize) {
                return 0;
            }
            if(((int)(v1 & 0x1FFFFFL)) >= this.maxPoolSize) {
                return 0;
            }
            int v3 = ((int)(CoroutineScheduler.controlState$FU.get(this) & 0x1FFFFFL)) + 1;
            if(v3 > 0 && this.workers.get(v3) == null) {
                Worker coroutineScheduler$Worker0 = new Worker(this, v3);
                this.workers.setSynchronized(v3, coroutineScheduler$Worker0);
                if(v3 != ((int)(0x1FFFFFL & atomicLongFieldUpdater0.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                coroutineScheduler$Worker0.start();
                return v2 + 1;
            }
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public final Task createTask(Runnable runnable0, TaskContext taskContext0) {
        long v = TasksKt.schedulerTimeSource.nanoTime();
        if(runnable0 instanceof Task) {
            ((Task)runnable0).submissionTime = v;
            ((Task)runnable0).taskContext = taskContext0;
            return (Task)runnable0;
        }
        return new TaskImpl(runnable0, v, taskContext0);
    }

    private final int createdWorkers(long v) {
        return (int)(v & 0x1FFFFFL);
    }

    private final Worker currentWorker() {
        Thread thread0 = Thread.currentThread();
        Worker coroutineScheduler$Worker0 = thread0 instanceof Worker ? ((Worker)thread0) : null;
        return coroutineScheduler$Worker0 == null || !Intrinsics.areEqual(Worker.access$getThis$0$p(coroutineScheduler$Worker0), this) ? null : coroutineScheduler$Worker0;
    }

    private final void decrementBlockingTasks() {
        CoroutineScheduler.controlState$FU.addAndGet(this, 0xFFFFFFFFFFE00000L);
    }

    private final int decrementCreatedWorkers() {
        return (int)(CoroutineScheduler.controlState$FU.getAndDecrement(this) & 0x1FFFFFL);
    }

    public final void dispatch(Runnable runnable0, TaskContext taskContext0, boolean z) {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.trackTask();
        }
        Task task0 = this.createTask(runnable0, taskContext0);
        boolean z1 = false;
        boolean z2 = task0.taskContext.getTaskMode() == 1;
        long v = z2 ? CoroutineScheduler.controlState$FU.addAndGet(this, 0x200000L) : 0L;
        Worker coroutineScheduler$Worker0 = this.currentWorker();
        Task task1 = this.submitToLocalQueue(coroutineScheduler$Worker0, task0, z);
        if(task1 != null && !this.addToGlobalQueue(task1)) {
            throw new RejectedExecutionException(this.schedulerName + " was terminated");
        }
        if(z && coroutineScheduler$Worker0 != null) {
            z1 = true;
        }
        if(z2) {
            this.signalBlockingWork(v, z1);
            return;
        }
        if(z1) {
            return;
        }
        this.signalCpuWork();
    }

    public static void dispatch$default(CoroutineScheduler coroutineScheduler0, Runnable runnable0, TaskContext taskContext0, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            taskContext0 = TasksKt.NonBlockingContext;
        }
        if((v & 4) != 0) {
            z = false;
        }
        coroutineScheduler0.dispatch(runnable0, taskContext0, z);
    }

    @Override
    public void execute(Runnable runnable0) {
        CoroutineScheduler.dispatch$default(this, runnable0, null, false, 6, null);
    }

    private final int getAvailableCpuPermits() {
        return (int)((CoroutineScheduler.controlState$FU.get(this) & 0x7FFFFC0000000000L) >> 42);
    }

    private final int getCreatedWorkers() {
        return (int)(CoroutineScheduler.controlState$FU.get(this) & 0x1FFFFFL);
    }

    private final long incrementBlockingTasks() {
        return CoroutineScheduler.controlState$FU.addAndGet(this, 0x200000L);
    }

    private final int incrementCreatedWorkers() {
        return (int)(CoroutineScheduler.controlState$FU.incrementAndGet(this) & 0x1FFFFFL);
    }

    public final boolean isTerminated() {
        return CoroutineScheduler._isTerminated$FU.get(this) != 0;
    }

    private final void loop$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicLongFieldUpdater0.get(object0));
        }
    }

    private final int parkedWorkersStackNextIndex(Worker coroutineScheduler$Worker0) {
        for(Object object0 = coroutineScheduler$Worker0.getNextParkedWorker(); true; object0 = ((Worker)object0).getNextParkedWorker()) {
            if(object0 == CoroutineScheduler.NOT_IN_STACK) {
                return -1;
            }
            if(object0 == null) {
                return 0;
            }
            int v = ((Worker)object0).getIndexInArray();
            if(v != 0) {
                return v;
            }
        }
    }

    private final Worker parkedWorkersStackPop() {
        Worker coroutineScheduler$Worker0;
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = CoroutineScheduler.parkedWorkersStack$FU;
        do {
            long v = atomicLongFieldUpdater0.get(this);
            coroutineScheduler$Worker0 = (Worker)this.workers.get(((int)(0x1FFFFFL & v)));
            if(coroutineScheduler$Worker0 == null) {
                return null;
            }
            int v1 = this.parkedWorkersStackNextIndex(coroutineScheduler$Worker0);
        }
        while(v1 < 0 || !CoroutineScheduler.parkedWorkersStack$FU.compareAndSet(this, v, ((long)v1) | v + 0x200000L & 0xFFFFFFFFFFE00000L));
        coroutineScheduler$Worker0.setNextParkedWorker(CoroutineScheduler.NOT_IN_STACK);
        return coroutineScheduler$Worker0;
    }

    public final boolean parkedWorkersStackPush(Worker coroutineScheduler$Worker0) {
        if(coroutineScheduler$Worker0.getNextParkedWorker() != CoroutineScheduler.NOT_IN_STACK) {
            return false;
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = CoroutineScheduler.parkedWorkersStack$FU;
        do {
            long v = atomicLongFieldUpdater0.get(this);
            coroutineScheduler$Worker0.setNextParkedWorker(this.workers.get(((int)(0x1FFFFFL & v))));
        }
        while(!CoroutineScheduler.parkedWorkersStack$FU.compareAndSet(this, v, v + 0x200000L & 0xFFFFFFFFFFE00000L | ((long)coroutineScheduler$Worker0.getIndexInArray())));
        return true;
    }

    public final void parkedWorkersStackTopUpdate(Worker coroutineScheduler$Worker0, int v, int v1) {
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = CoroutineScheduler.parkedWorkersStack$FU;
        do {
            long v2 = atomicLongFieldUpdater0.get(this);
            int v3 = (int)(0x1FFFFFL & v2);
            if(v3 == v) {
                v3 = v1 == 0 ? this.parkedWorkersStackNextIndex(coroutineScheduler$Worker0) : v1;
            }
        }
        while(v3 < 0 || !CoroutineScheduler.parkedWorkersStack$FU.compareAndSet(this, v2, v2 + 0x200000L & 0xFFFFFFFFFFE00000L | ((long)v3)));
    }

    private final long releaseCpuPermit() {
        return CoroutineScheduler.controlState$FU.addAndGet(this, 0x40000000000L);
    }

    public final void runSafely(Task task0) {
        AbstractTimeSource abstractTimeSource1;
        AbstractTimeSource abstractTimeSource0;
        try {
            task0.run();
            abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        }
        catch(Throwable throwable0) {
            try {
                Thread thread0 = Thread.currentThread();
                thread0.getUncaughtExceptionHandler().uncaughtException(thread0, throwable0);
                abstractTimeSource1 = AbstractTimeSourceKt.getTimeSource();
            }
            catch(Throwable throwable1) {
                AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.getTimeSource();
                if(abstractTimeSource2 != null) {
                    abstractTimeSource2.unTrackTask();
                }
                throw throwable1;
            }
            if(abstractTimeSource1 != null) {
                abstractTimeSource1.unTrackTask();
                return;
            }
            return;
        }
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.unTrackTask();
        }
    }

    public final void shutdown(long v) {
        Task task1;
        if(!CoroutineScheduler._isTerminated$FU.compareAndSet(this, 0, 1)) {
            return;
        }
        Worker coroutineScheduler$Worker0 = this.currentWorker();
        synchronized(this.workers) {
            long v2 = CoroutineScheduler.controlState$FU.get(this);
        }
        if(1 <= ((int)(v2 & 0x1FFFFFL))) {
            for(int v3 = 1; true; ++v3) {
                Object object0 = this.workers.get(v3);
                Intrinsics.checkNotNull(object0);
                Worker coroutineScheduler$Worker1 = (Worker)object0;
                if(coroutineScheduler$Worker1 != coroutineScheduler$Worker0) {
                    while(coroutineScheduler$Worker1.isAlive()) {
                        LockSupport.unpark(coroutineScheduler$Worker1);
                        coroutineScheduler$Worker1.join(v);
                    }
                    coroutineScheduler$Worker1.localQueue.offloadAllWorkTo(this.globalBlockingQueue);
                }
                if(v3 == ((int)(v2 & 0x1FFFFFL))) {
                    break;
                }
            }
        }
        this.globalBlockingQueue.close();
        this.globalCpuQueue.close();
        while(true) {
            if(coroutineScheduler$Worker0 == null) {
                Task task2 = (Task)this.globalCpuQueue.removeFirstOrNull();
                if(task2 == null) {
                    task1 = (Task)this.globalBlockingQueue.removeFirstOrNull();
                    if(task1 != null) {
                        goto label_36;
                    }
                    break;
                }
                else {
                    task1 = task2;
                }
            }
            else {
                Task task0 = coroutineScheduler$Worker0.findTask(true);
                if(task0 != null) {
                    task1 = task0;
                }
            }
        label_36:
            this.runSafely(task1);
        }
        if(coroutineScheduler$Worker0 != null) {
            coroutineScheduler$Worker0.tryReleaseCpu(WorkerState.TERMINATED);
        }
        CoroutineScheduler.parkedWorkersStack$FU.set(this, 0L);
        CoroutineScheduler.controlState$FU.set(this, 0L);
    }

    private final void signalBlockingWork(long v, boolean z) {
        if(z || this.tryUnpark() || this.tryCreateWorker(v)) {
            return;
        }
        this.tryUnpark();
    }

    public final void signalCpuWork() {
        if(this.tryUnpark() || CoroutineScheduler.tryCreateWorker$default(this, 0L, 1, null)) {
            return;
        }
        this.tryUnpark();
    }

    private final Task submitToLocalQueue(Worker coroutineScheduler$Worker0, Task task0, boolean z) {
        if(coroutineScheduler$Worker0 == null || coroutineScheduler$Worker0.state == WorkerState.TERMINATED || task0.taskContext.getTaskMode() == 0 && coroutineScheduler$Worker0.state == WorkerState.BLOCKING) {
            return task0;
        }
        coroutineScheduler$Worker0.mayHaveLocalTasks = true;
        return coroutineScheduler$Worker0.localQueue.add(task0, z);
    }

    @Override
    public String toString() {
        ArrayList arrayList0 = new ArrayList();
        int v = this.workers.currentLength();
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        for(int v6 = 1; v6 < v; ++v6) {
            Worker coroutineScheduler$Worker0 = (Worker)this.workers.get(v6);
            if(coroutineScheduler$Worker0 != null) {
                int v7 = coroutineScheduler$Worker0.localQueue.getSize$kotlinx_coroutines_core();
                switch(WhenMappings.$EnumSwitchMapping$0[coroutineScheduler$Worker0.state.ordinal()]) {
                    case 1: {
                        ++v3;
                        break;
                    }
                    case 2: {
                        ++v2;
                        arrayList0.add(v7 + 'b');
                        break;
                    }
                    case 3: {
                        ++v1;
                        arrayList0.add(v7 + 'c');
                        break;
                    }
                    case 4: {
                        ++v4;
                        if(v7 > 0) {
                            arrayList0.add(v7 + 'd');
                        }
                        break;
                    }
                    case 5: {
                        ++v5;
                    }
                }
            }
        }
        long v8 = CoroutineScheduler.controlState$FU.get(this);
        return this.schedulerName + '@' + DebugStringsKt.getHexAddress(this) + "[Pool Size {core = " + this.corePoolSize + ", max = " + this.maxPoolSize + "}, Worker States {CPU = " + v1 + ", blocking = " + v2 + ", parked = " + v3 + ", dormant = " + v4 + ", terminated = " + v5 + "}, running workers queues = " + arrayList0 + ", global CPU queue size = " + this.globalCpuQueue.getSize() + ", global blocking queue size = " + this.globalBlockingQueue.getSize() + ", Control State {created workers= " + ((int)(0x1FFFFFL & v8)) + ", blocking tasks = " + ((int)((0x3FFFFE00000L & v8) >> 21)) + ", CPUs acquired = " + (this.corePoolSize - ((int)((0x7FFFFC0000000000L & v8) >> 42))) + "}]";
    }

    private final boolean tryAcquireCpuPermit() {
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = CoroutineScheduler.controlState$FU;
        do {
            long v = atomicLongFieldUpdater0.get(this);
            if(((int)((0x7FFFFC0000000000L & v) >> 42)) == 0) {
                return false;
            }
        }
        while(!CoroutineScheduler.controlState$FU.compareAndSet(this, v, v - 0x40000000000L));
        return true;
    }

    private final boolean tryCreateWorker(long v) {
        if(RangesKt.coerceAtLeast(((int)(0x1FFFFFL & v)) - ((int)((v & 0x3FFFFE00000L) >> 21)), 0) < this.corePoolSize) {
            int v1 = this.createNewWorker();
            if(v1 == 1 && this.corePoolSize > 1) {
                this.createNewWorker();
            }
            return v1 > 0;
        }
        return false;
    }

    static boolean tryCreateWorker$default(CoroutineScheduler coroutineScheduler0, long v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = CoroutineScheduler.controlState$FU.get(coroutineScheduler0);
        }
        return coroutineScheduler0.tryCreateWorker(v);
    }

    private final boolean tryUnpark() {
        Worker coroutineScheduler$Worker0;
        do {
            coroutineScheduler$Worker0 = this.parkedWorkersStackPop();
            if(coroutineScheduler$Worker0 == null) {
                return false;
            }
        }
        while(!Worker.getWorkerCtl$FU().compareAndSet(coroutineScheduler$Worker0, -1, 0));
        LockSupport.unpark(coroutineScheduler$Worker0);
        return true;
    }
}

