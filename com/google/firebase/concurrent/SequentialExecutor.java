package com.google.firebase.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import jeb.synthetic.FIN;

final class SequentialExecutor implements Executor {
    final class QueueWorker implements Runnable {
        @CheckForNull
        Runnable task;

        private QueueWorker() {
        }

        QueueWorker(com.google.firebase.concurrent.SequentialExecutor.1 sequentialExecutor$10) {
        }

        @Override
        public void run() {
            try {
                this.workOnQueue();
            }
            catch(Error error0) {
                Deque deque0 = SequentialExecutor.this.queue;
                synchronized(deque0) {
                    SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                }
                throw error0;
            }
        }

        @Override
        public String toString() {
            return this.task == null ? "SequentialExecutorWorker{state=" + SequentialExecutor.this.workerRunningState + "}" : "SequentialExecutorWorker{running=" + this.task + "}";
        }

        private void workOnQueue() {
            int v;
            boolean z = false;
            boolean z1 = false;
            while(true) {
                try {
                label_2:
                    Deque deque0 = SequentialExecutor.this.queue;
                    __monitor_enter(deque0);
                    v = FIN.finallyOpen$NT();
                    if(z) {
                    label_16:
                        Runnable runnable0 = (Runnable)SequentialExecutor.this.queue.poll();
                        this.task = runnable0;
                        if(runnable0 == null) {
                            SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                            FIN.finallyExec$NT(v);
                            if(!z1) {
                                return;
                            }
                        }
                        else {
                            goto label_25;
                        }
                    }
                    else if(SequentialExecutor.this.workerRunningState != WorkerRunningState.RUNNING) {
                        SequentialExecutor.access$308(SequentialExecutor.this);
                        SequentialExecutor.this.workerRunningState = WorkerRunningState.RUNNING;
                        z = true;
                        goto label_16;
                    }
                    else {
                        FIN.finallyCodeBegin$NT(v);
                        __monitor_exit(deque0);
                        FIN.finallyCodeEnd$NT(v);
                        if(!z1) {
                            return;
                        }
                    }
                }
                catch(Throwable throwable0) {
                    break;
                }
                Thread.currentThread().interrupt();
                return;
                try {
                label_25:
                    FIN.finallyExec$NT(v);
                    z1 |= Thread.interrupted();
                    try {
                        this.task.run();
                    }
                    catch(RuntimeException runtimeException0) {
                        SequentialExecutor.log.log(Level.SEVERE, "Exception while executing runnable " + this.task, runtimeException0);
                    }
                    finally {
                        this.task = null;
                    }
                    goto label_2;
                }
                catch(Throwable throwable0) {
                }
                break;
            }
            if(z1) {
                Thread.currentThread().interrupt();
            }
            throw throwable0;
        }
    }

    static enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING;

    }

    private final Executor executor;
    private static final Logger log;
    private final Deque queue;
    private final QueueWorker worker;
    private long workerRunCount;
    private WorkerRunningState workerRunningState;

    static {
        SequentialExecutor.log = Logger.getLogger("com.google.firebase.concurrent.SequentialExecutor");
    }

    SequentialExecutor(Executor executor0) {
        this.queue = new ArrayDeque();
        this.workerRunningState = WorkerRunningState.IDLE;
        this.workerRunCount = 0L;
        this.worker = new QueueWorker(this, null);
        this.executor = (Executor)Preconditions.checkNotNull(executor0);
    }

    static long access$308(SequentialExecutor sequentialExecutor0) {
        long v = sequentialExecutor0.workerRunCount;
        sequentialExecutor0.workerRunCount = v + 1L;
        return v;
    }

    @Override
    public void execute(Runnable runnable0) {
        Preconditions.checkNotNull(runnable0);
        synchronized(this.queue) {
            if(this.workerRunningState != WorkerRunningState.RUNNING && this.workerRunningState != WorkerRunningState.QUEUED) {
                long v1 = this.workerRunCount;
                com.google.firebase.concurrent.SequentialExecutor.1 sequentialExecutor$10 = new Runnable() {
                    @Override
                    public void run() {
                        runnable0.run();
                    }

                    @Override
                    public String toString() {
                        return runnable0.toString();
                    }
                };
                this.queue.add(sequentialExecutor$10);
                this.workerRunningState = WorkerRunningState.QUEUING;
                try {
                    this.executor.execute(this.worker);
                }
                catch(RuntimeException | Error runtimeException0) {
                    Deque deque1 = this.queue;
                    synchronized(deque1) {
                        if(runtimeException0 instanceof RejectedExecutionException && (this.workerRunningState != WorkerRunningState.IDLE && this.workerRunningState != WorkerRunningState.QUEUING || !this.queue.removeLastOccurrence(sequentialExecutor$10))) {
                            return;
                        }
                    }
                    throw runtimeException0;
                }
                if(this.workerRunningState != WorkerRunningState.QUEUING) {
                    return;
                }
                Deque deque2 = this.queue;
                synchronized(deque2) {
                    if(this.workerRunCount == v1 && this.workerRunningState == WorkerRunningState.QUEUING) {
                        this.workerRunningState = WorkerRunningState.QUEUED;
                    }
                }
                return;
            }
            this.queue.add(runnable0);
        }
    }

    @Override
    public String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.executor + "}";
    }
}

