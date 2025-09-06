package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 (2\u00020\u0001:\u0003\'()B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u000B0\u0019J\u0018\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u000FH\u0002J\b\u0010\u001F\u001A\u0004\u0018\u00010\u001DJ\u0010\u0010 \u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u001DH\u0002J\u0006\u0010!\u001A\u00020\u001BJ\u0015\u0010\"\u001A\u00020\u001B2\u0006\u0010#\u001A\u00020\u000BH\u0000¢\u0006\u0002\b$J\u0006\u0010%\u001A\u00020\u000BJ\u0010\u0010&\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u001DH\u0002R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u000B0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0082\u000E¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001A\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\b\u0012\u001A\u0004\b\u0010\u0010\u0011R\u000E\u0010\u0013\u001A\u00020\u0014X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u000B0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner;", "", "backend", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "logger", "Ljava/util/logging/Logger;", "(Lokhttp3/internal/concurrent/TaskRunner$Backend;Ljava/util/logging/Logger;)V", "getBackend", "()Lokhttp3/internal/concurrent/TaskRunner$Backend;", "busyQueues", "", "Lokhttp3/internal/concurrent/TaskQueue;", "coordinatorWaiting", "", "coordinatorWakeUpAt", "", "getLogger$okhttp", "()Ljava/util/logging/Logger;", "logger$1", "nextQueueName", "", "readyQueues", "runnable", "Ljava/lang/Runnable;", "activeQueues", "", "afterRun", "", "task", "Lokhttp3/internal/concurrent/Task;", "delayNanos", "awaitTaskToRun", "beforeRun", "cancelAll", "kickCoordinator", "taskQueue", "kickCoordinator$okhttp", "newQueue", "runTask", "Backend", "Companion", "RealBackend", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class TaskRunner {
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0018\u0010\u0006\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\bH&J\"\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u000B0\n\"\u0004\b\u0000\u0010\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u000B0\nH&J\u0018\u0010\r\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u000E\u001A\u00020\u000FH&J\b\u0010\u0010\u001A\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Backend;", "", "coordinatorNotify", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorWait", "nanos", "", "decorate", "Ljava/util/concurrent/BlockingQueue;", "T", "queue", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Backend {
        void coordinatorNotify(TaskRunner arg1);

        void coordinatorWait(TaskRunner arg1, long arg2);

        BlockingQueue decorate(BlockingQueue arg1);

        void execute(TaskRunner arg1, Runnable arg2);

        long nanoTime();
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Companion;", "", "()V", "INSTANCE", "Lokhttp3/internal/concurrent/TaskRunner;", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Logger getLogger() {
            return TaskRunner.logger;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\f\u001A\u00020\rH\u0016J\"\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u00100\u000F\"\u0004\b\u0000\u0010\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u00100\u000FH\u0016J\u0018\u0010\u0012\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0013\u001A\u00020\u0014H\u0016J\b\u0010\u0015\u001A\u00020\rH\u0016J\u0006\u0010\u0016\u001A\u00020\bR\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "(Ljava/util/concurrent/ThreadFactory;)V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "coordinatorNotify", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorWait", "nanos", "", "decorate", "Ljava/util/concurrent/BlockingQueue;", "T", "queue", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "shutdown", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class RealBackend implements Backend {
        private final ThreadPoolExecutor executor;

        public RealBackend(ThreadFactory threadFactory0) {
            Intrinsics.checkNotNullParameter(threadFactory0, "threadFactory");
            super();
            this.executor = new ThreadPoolExecutor(0, 0x7FFFFFFF, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory0);
        }

        @Override  // okhttp3.internal.concurrent.TaskRunner$Backend
        public void coordinatorNotify(TaskRunner taskRunner0) {
            Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
            taskRunner0.notify();
        }

        @Override  // okhttp3.internal.concurrent.TaskRunner$Backend
        public void coordinatorWait(TaskRunner taskRunner0, long v) throws InterruptedException {
            Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
            if(v / 1000000L <= 0L && v <= 0L) {
                return;
            }
            taskRunner0.wait(v / 1000000L, ((int)(v - 1000000L * (v / 1000000L))));
        }

        @Override  // okhttp3.internal.concurrent.TaskRunner$Backend
        public BlockingQueue decorate(BlockingQueue blockingQueue0) {
            Intrinsics.checkNotNullParameter(blockingQueue0, "queue");
            return blockingQueue0;
        }

        @Override  // okhttp3.internal.concurrent.TaskRunner$Backend
        public void execute(TaskRunner taskRunner0, Runnable runnable0) {
            Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
            Intrinsics.checkNotNullParameter(runnable0, "runnable");
            this.executor.execute(runnable0);
        }

        @Override  // okhttp3.internal.concurrent.TaskRunner$Backend
        public long nanoTime() {
            return System.nanoTime();
        }

        public final void shutdown() {
            this.executor.shutdown();
        }
    }

    public static final Companion Companion;
    public static final TaskRunner INSTANCE;
    private final Backend backend;
    private final List busyQueues;
    private boolean coordinatorWaiting;
    private long coordinatorWakeUpAt;
    private static final Logger logger;
    private final Logger logger$1;
    private int nextQueueName;
    private final List readyQueues;
    private final Runnable runnable;

    static {
        TaskRunner.Companion = new Companion(null);
        Logger logger0 = Logger.getLogger("okhttp3.internal.concurrent.TaskRunner");
        Intrinsics.checkNotNullExpressionValue(logger0, "getLogger(TaskRunner::class.java.name)");
        TaskRunner.logger = logger0;
        TaskRunner.INSTANCE = new TaskRunner(new RealBackend(_UtilJvmKt.threadFactory((_UtilJvmKt.okHttpName + " TaskRunner"), true)), null, 2, null);
    }

    public TaskRunner(Backend taskRunner$Backend0, Logger logger0) {
        Intrinsics.checkNotNullParameter(taskRunner$Backend0, "backend");
        Intrinsics.checkNotNullParameter(logger0, "logger");
        super();
        this.backend = taskRunner$Backend0;
        this.logger$1 = logger0;
        this.nextQueueName = 10000;
        this.busyQueues = new ArrayList();
        this.readyQueues = new ArrayList();
        this.runnable = new Runnable() {
            @Override
            public void run() {
                Task task0;
                long v1;
                while(true) {
                    synchronized(TaskRunner.this) {
                        task0 = TaskRunner.this.awaitTaskToRun();
                    }
                    if(task0 == null) {
                        return;
                    }
                    Logger logger0 = TaskRunner.this.getLogger$okhttp();
                    TaskQueue taskQueue0 = task0.getQueue$okhttp();
                    Intrinsics.checkNotNull(taskQueue0);
                    TaskRunner taskRunner0 = TaskRunner.this;
                    boolean z = logger0.isLoggable(Level.FINE);
                    if(z) {
                        v1 = taskQueue0.getTaskRunner$okhttp().getBackend().nanoTime();
                        TaskLoggerKt.log(logger0, task0, taskQueue0, "starting");
                    }
                    else {
                        v1 = -1L;
                    }
                    try {
                        TaskRunner.access$runTask(taskRunner0, task0);
                        if(!z) {
                            continue;
                        }
                    }
                    catch(Throwable throwable0) {
                        try {
                            synchronized(taskRunner0) {
                                taskRunner0.getBackend().execute(taskRunner0, this);
                            }
                            throw throwable0;
                        }
                        catch(Throwable throwable1) {
                            if(z) {
                                TaskLoggerKt.log(logger0, task0, taskQueue0, "failed a run in " + TaskLoggerKt.formatDuration(taskQueue0.getTaskRunner$okhttp().getBackend().nanoTime() - v1));
                            }
                            throw throwable1;
                        }
                    }
                    TaskLoggerKt.log(logger0, task0, taskQueue0, "finished run in " + TaskLoggerKt.formatDuration(taskQueue0.getTaskRunner$okhttp().getBackend().nanoTime() - v1));
                }
            }
        };
    }

    public TaskRunner(Backend taskRunner$Backend0, Logger logger0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            logger0 = TaskRunner.logger;
        }
        this(taskRunner$Backend0, logger0);
    }

    public static final void access$runTask(TaskRunner taskRunner0, Task task0) {
        taskRunner0.runTask(task0);
    }

    public final List activeQueues() {
        synchronized(this) {
            return CollectionsKt.plus(this.busyQueues, this.readyQueues);
        }
    }

    private final void afterRun(Task task0, long v) {
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13508 MUST hold lock on " + this);
        }
        TaskQueue taskQueue0 = task0.getQueue$okhttp();
        Intrinsics.checkNotNull(taskQueue0);
        if(taskQueue0.getActiveTask$okhttp() != task0) {
            throw new IllegalStateException("Check failed.");
        }
        taskQueue0.setCancelActiveTask$okhttp(false);
        taskQueue0.setActiveTask$okhttp(null);
        this.busyQueues.remove(taskQueue0);
        if(v != -1L && !taskQueue0.getCancelActiveTask$okhttp() && !taskQueue0.getShutdown$okhttp()) {
            taskQueue0.scheduleAndDecide$okhttp(task0, v, true);
        }
        if(!taskQueue0.getFutureTasks$okhttp().isEmpty()) {
            this.readyQueues.add(taskQueue0);
        }
    }

    public final Task awaitTaskToRun() {
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13207 MUST hold lock on " + this);
        }
        while(true) {
            if(this.readyQueues.isEmpty()) {
                return null;
            }
            long v = this.backend.nanoTime();
            long v1 = 0x7FFFFFFFFFFFFFFFL;
            Task task0 = null;
            Iterator iterator0 = this.readyQueues.iterator();
            while(true) {
                boolean z = false;
                if(iterator0.hasNext()) {
                    Object object0 = iterator0.next();
                    Task task1 = (Task)((TaskQueue)object0).getFutureTasks$okhttp().get(0);
                    long v2 = Math.max(0L, task1.getNextExecuteNanoTime$okhttp() - v);
                    if(v2 > 0L) {
                        v1 = Math.min(v2, v1);
                        continue;
                    }
                    else if(task0 == null) {
                        task0 = task1;
                        continue;
                    }
                    else {
                        z = true;
                    }
                }
                break;
            }
            if(task0 != null) {
                this.beforeRun(task0);
                if(z || !this.coordinatorWaiting && !this.readyQueues.isEmpty()) {
                    this.backend.execute(this, this.runnable);
                }
                return task0;
            }
            if(this.coordinatorWaiting) {
                if(v1 < this.coordinatorWakeUpAt - v) {
                    this.backend.coordinatorNotify(this);
                }
                return null;
            }
            try {
                this.coordinatorWaiting = true;
                this.coordinatorWakeUpAt = v + v1;
                this.backend.coordinatorWait(this, v1);
            }
            catch(InterruptedException unused_ex) {
                this.cancelAll();
            }
            finally {
                this.coordinatorWaiting = false;
            }
        }
    }

    private final void beforeRun(Task task0) {
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13521 MUST hold lock on " + this);
        }
        task0.setNextExecuteNanoTime$okhttp(-1L);
        TaskQueue taskQueue0 = task0.getQueue$okhttp();
        Intrinsics.checkNotNull(taskQueue0);
        taskQueue0.getFutureTasks$okhttp().remove(task0);
        this.readyQueues.remove(taskQueue0);
        taskQueue0.setActiveTask$okhttp(task0);
        this.busyQueues.add(taskQueue0);
    }

    public final void cancelAll() {
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13476 MUST hold lock on " + this);
        }
        for(int v = this.busyQueues.size() - 1; -1 < v; --v) {
            ((TaskQueue)this.busyQueues.get(v)).cancelAllAndDecide$okhttp();
        }
        for(int v1 = this.readyQueues.size() - 1; -1 < v1; --v1) {
            TaskQueue taskQueue0 = (TaskQueue)this.readyQueues.get(v1);
            taskQueue0.cancelAllAndDecide$okhttp();
            if(taskQueue0.getFutureTasks$okhttp().isEmpty()) {
                this.readyQueues.remove(v1);
            }
        }
    }

    public final Backend getBackend() {
        return this.backend;
    }

    public final Logger getLogger$okhttp() {
        return this.logger$1;
    }

    public final void kickCoordinator$okhttp(TaskQueue taskQueue0) {
        Intrinsics.checkNotNullParameter(taskQueue0, "taskQueue");
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13467 MUST hold lock on " + this);
        }
        if(taskQueue0.getActiveTask$okhttp() == null) {
            if(taskQueue0.getFutureTasks$okhttp().isEmpty()) {
                this.readyQueues.remove(taskQueue0);
            }
            else {
                _UtilCommonKt.addIfAbsent(this.readyQueues, taskQueue0);
            }
        }
        if(this.coordinatorWaiting) {
            this.backend.coordinatorNotify(this);
            return;
        }
        this.backend.execute(this, this.runnable);
    }

    public final TaskQueue newQueue() {
        int v;
        synchronized(this) {
            v = this.nextQueueName;
            this.nextQueueName = v + 1;
        }
        return new TaskQueue(this, "Q" + v);
    }

    private final void runTask(Task task0) {
        long v;
        Thread thread0 = Thread.currentThread();
        thread0.setName(task0.getName());
        try {
            v = task0.runOnce();
        }
        catch(Throwable throwable0) {
            synchronized(this) {
                this.afterRun(task0, -1L);
            }
            thread0.setName("jeb-dexdec-sb-st-13443");
            throw throwable0;
        }
        synchronized(this) {
            this.afterRun(task0, v);
        }
        thread0.setName("jeb-dexdec-sb-st-13443");
    }
}

