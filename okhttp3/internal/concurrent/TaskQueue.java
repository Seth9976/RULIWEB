package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u00013B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\u0006\u0010!\u001A\u00020\"J\r\u0010#\u001A\u00020\u000EH\u0000\u00A2\u0006\u0002\b$J0\u0010%\u001A\u00020\"2\u0006\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010&\u001A\u00020\'2\b\b\u0002\u0010(\u001A\u00020\u000E2\f\u0010)\u001A\b\u0012\u0004\u0012\u00020\"0*J\u0006\u0010+\u001A\u00020,J&\u0010-\u001A\u00020\"2\u0006\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010&\u001A\u00020\'2\f\u0010)\u001A\b\u0012\u0004\u0012\u00020\'0*J\u0018\u0010-\u001A\u00020\"2\u0006\u0010.\u001A\u00020\b2\b\b\u0002\u0010&\u001A\u00020\'J%\u0010/\u001A\u00020\u000E2\u0006\u0010.\u001A\u00020\b2\u0006\u0010&\u001A\u00020\'2\u0006\u00100\u001A\u00020\u000EH\u0000\u00A2\u0006\u0002\b1J\u0006\u0010\u001C\u001A\u00020\"J\b\u00102\u001A\u00020\u0005H\u0016R\u001C\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\fR\u001A\u0010\r\u001A\u00020\u000EX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001A\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\b0\u0014X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0004\u001A\u00020\u0005X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\b0\u001A8F\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u0016R\u001A\u0010\u001C\u001A\u00020\u000EX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001D\u0010\u0010\"\u0004\b\u001E\u0010\u0012R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 \u00A8\u00064"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue;", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "name", "", "(Lokhttp3/internal/concurrent/TaskRunner;Ljava/lang/String;)V", "activeTask", "Lokhttp3/internal/concurrent/Task;", "getActiveTask$okhttp", "()Lokhttp3/internal/concurrent/Task;", "setActiveTask$okhttp", "(Lokhttp3/internal/concurrent/Task;)V", "cancelActiveTask", "", "getCancelActiveTask$okhttp", "()Z", "setCancelActiveTask$okhttp", "(Z)V", "futureTasks", "", "getFutureTasks$okhttp", "()Ljava/util/List;", "getName$okhttp", "()Ljava/lang/String;", "scheduledTasks", "", "getScheduledTasks", "shutdown", "getShutdown$okhttp", "setShutdown$okhttp", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "cancelAll", "", "cancelAllAndDecide", "cancelAllAndDecide$okhttp", "execute", "delayNanos", "", "cancelable", "block", "Lkotlin/Function0;", "idleLatch", "Ljava/util/concurrent/CountDownLatch;", "schedule", "task", "scheduleAndDecide", "recurrence", "scheduleAndDecide$okhttp", "toString", "AwaitIdleTask", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class TaskQueue {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001A\u00020\bH\u0016R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue$AwaitIdleTask;", "Lokhttp3/internal/concurrent/Task;", "()V", "latch", "Ljava/util/concurrent/CountDownLatch;", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "runOnce", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    static final class AwaitIdleTask extends Task {
        private final CountDownLatch latch;

        public AwaitIdleTask() {
            super(_UtilJvmKt.okHttpName + " awaitIdle", false);
            this.latch = new CountDownLatch(1);
        }

        public final CountDownLatch getLatch() {
            return this.latch;
        }

        @Override  // okhttp3.internal.concurrent.Task
        public long runOnce() {
            this.latch.countDown();
            return -1L;
        }
    }

    private Task activeTask;
    private boolean cancelActiveTask;
    private final List futureTasks;
    private final String name;
    private boolean shutdown;
    private final TaskRunner taskRunner;

    public TaskQueue(TaskRunner taskRunner0, String s) {
        Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
        Intrinsics.checkNotNullParameter(s, "name");
        super();
        this.taskRunner = taskRunner0;
        this.name = s;
        this.futureTasks = new ArrayList();
    }

    public final void cancelAll() {
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13341 MUST NOT hold lock on " + this);
        }
        synchronized(this.taskRunner) {
            if(this.cancelAllAndDecide$okhttp()) {
                this.taskRunner.kickCoordinator$okhttp(this);
            }
        }
    }

    public final boolean cancelAllAndDecide$okhttp() {
        Task task0 = this.activeTask;
        if(task0 != null) {
            Intrinsics.checkNotNull(task0);
            if(task0.getCancelable()) {
                this.cancelActiveTask = true;
            }
        }
        int v = this.futureTasks.size() - 1;
        boolean z = false;
        while(-1 < v) {
            if(((Task)this.futureTasks.get(v)).getCancelable()) {
                Logger logger0 = this.taskRunner.getLogger$okhttp();
                Task task1 = (Task)this.futureTasks.get(v);
                if(logger0.isLoggable(Level.FINE)) {
                    TaskLoggerKt.log(logger0, task1, this, "canceled");
                }
                this.futureTasks.remove(v);
                z = true;
            }
            --v;
        }
        return z;
    }

    public final void execute(String s, long v, boolean z, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(function00, "block");
        this.schedule(new Task(z, function00) {
            @Override  // okhttp3.internal.concurrent.Task
            public long runOnce() {
                this.$block.invoke();
                return -1L;
            }
        }, v);
    }

    public static void execute$default(TaskQueue taskQueue0, String s, long v, boolean z, Function0 function00, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0L;
        }
        taskQueue0.execute(s, v, ((v1 & 4) == 0 ? z : true), function00);
    }

    public final Task getActiveTask$okhttp() {
        return this.activeTask;
    }

    public final boolean getCancelActiveTask$okhttp() {
        return this.cancelActiveTask;
    }

    public final List getFutureTasks$okhttp() {
        return this.futureTasks;
    }

    public final String getName$okhttp() {
        return this.name;
    }

    public final List getScheduledTasks() {
        synchronized(this.taskRunner) {
            return CollectionsKt.toList(this.futureTasks);
        }
    }

    public final boolean getShutdown$okhttp() {
        return this.shutdown;
    }

    public final TaskRunner getTaskRunner$okhttp() {
        return this.taskRunner;
    }

    public final CountDownLatch idleLatch() {
        AwaitIdleTask taskQueue$AwaitIdleTask0;
        synchronized(this.taskRunner) {
            if(this.activeTask == null && this.futureTasks.isEmpty()) {
                return new CountDownLatch(0);
            }
            Task task0 = this.activeTask;
            if(task0 instanceof AwaitIdleTask) {
                return ((AwaitIdleTask)task0).getLatch();
            }
            for(Object object0: this.futureTasks) {
                Task task1 = (Task)object0;
                if(task1 instanceof AwaitIdleTask) {
                    return ((AwaitIdleTask)task1).getLatch();
                }
                if(false) {
                    break;
                }
            }
            taskQueue$AwaitIdleTask0 = new AwaitIdleTask();
            if(this.scheduleAndDecide$okhttp(taskQueue$AwaitIdleTask0, 0L, false)) {
                this.taskRunner.kickCoordinator$okhttp(this);
            }
        }
        return taskQueue$AwaitIdleTask0.getLatch();
    }

    public final void schedule(String s, long v, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(function00, "block");
        this.schedule(new Task(function00) {
            final Function0 $block;

            {
                this.$block = function00;
                super(s, false, 2, null);
            }

            @Override  // okhttp3.internal.concurrent.Task
            public long runOnce() {
                return ((Number)this.$block.invoke()).longValue();
            }
        }, v);
    }

    public final void schedule(Task task0, long v) {
        Intrinsics.checkNotNullParameter(task0, "task");
        synchronized(this.taskRunner) {
            if(this.shutdown) {
                if(task0.getCancelable()) {
                    Logger logger0 = this.taskRunner.getLogger$okhttp();
                    if(logger0.isLoggable(Level.FINE)) {
                        TaskLoggerKt.log(logger0, task0, this, "schedule canceled (queue is shutdown)");
                    }
                    return;
                }
                Logger logger1 = this.taskRunner.getLogger$okhttp();
                if(logger1.isLoggable(Level.FINE)) {
                    TaskLoggerKt.log(logger1, task0, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
            if(this.scheduleAndDecide$okhttp(task0, v, false)) {
                this.taskRunner.kickCoordinator$okhttp(this);
            }
        }
    }

    public static void schedule$default(TaskQueue taskQueue0, String s, long v, Function0 function00, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0L;
        }
        taskQueue0.schedule(s, v, function00);
    }

    public static void schedule$default(TaskQueue taskQueue0, Task task0, long v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0L;
        }
        taskQueue0.schedule(task0, v);
    }

    public final boolean scheduleAndDecide$okhttp(Task task0, long v, boolean z) {
        Intrinsics.checkNotNullParameter(task0, "task");
        task0.initQueue$okhttp(this);
        long v1 = this.taskRunner.getBackend().nanoTime();
        long v2 = v1 + v;
        int v3 = this.futureTasks.indexOf(task0);
        if(v3 != -1) {
            if(task0.getNextExecuteNanoTime$okhttp() <= v2) {
                Logger logger0 = this.taskRunner.getLogger$okhttp();
                if(logger0.isLoggable(Level.FINE)) {
                    TaskLoggerKt.log(logger0, task0, this, "already scheduled");
                }
                return false;
            }
            this.futureTasks.remove(v3);
        }
        task0.setNextExecuteNanoTime$okhttp(v2);
        Logger logger1 = this.taskRunner.getLogger$okhttp();
        if(logger1.isLoggable(Level.FINE)) {
            TaskLoggerKt.log(logger1, task0, this, (z ? "run again after " + TaskLoggerKt.formatDuration(v2 - v1) : "scheduled after " + TaskLoggerKt.formatDuration(v2 - v1)));
        }
        int v4 = 0;
        Iterator iterator0 = this.futureTasks.iterator();
        while(true) {
            if(!iterator0.hasNext()) {
                v4 = -1;
                break;
            }
            Object object0 = iterator0.next();
            if(((Task)object0).getNextExecuteNanoTime$okhttp() - v1 > v) {
                break;
            }
            ++v4;
        }
        if(v4 == -1) {
            v4 = this.futureTasks.size();
        }
        this.futureTasks.add(v4, task0);
        return v4 == 0;
    }

    public final void setActiveTask$okhttp(Task task0) {
        this.activeTask = task0;
    }

    public final void setCancelActiveTask$okhttp(boolean z) {
        this.cancelActiveTask = z;
    }

    public final void setShutdown$okhttp(boolean z) {
        this.shutdown = z;
    }

    public final void shutdown() {
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13523 MUST NOT hold lock on " + this);
        }
        synchronized(this.taskRunner) {
            this.shutdown = true;
            if(this.cancelAllAndDecide$okhttp()) {
                this.taskRunner.kickCoordinator$okhttp(this);
            }
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}

