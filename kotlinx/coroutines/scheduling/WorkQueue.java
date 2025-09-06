package kotlinx.coroutines.scheduling;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.channels.ChannelSegment..ExternalSyntheticBackportWithForwarding0;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005\u00A2\u0006\u0002\u0010\u0002J\u001A\u0010\u0012\u001A\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001A\u00020\u00072\b\b\u0002\u0010\u0014\u001A\u00020\u0015J\u0012\u0010\u0016\u001A\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001A\u00020\u0007H\u0002J\u000E\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001AJ\b\u0010\u001B\u001A\u0004\u0018\u00010\u0007J\b\u0010\u001C\u001A\u0004\u0018\u00010\u0007J\n\u0010\u001D\u001A\u0004\u0018\u00010\u0007H\u0002J\b\u0010\u001E\u001A\u0004\u0018\u00010\u0007J\u0010\u0010\u001F\u001A\u00020\u00152\u0006\u0010 \u001A\u00020\u001AH\u0002J\u0012\u0010!\u001A\u0004\u0018\u00010\u00072\u0006\u0010\"\u001A\u00020\u0015H\u0002J\u0016\u0010#\u001A\u0004\u0018\u00010\u00072\n\u0010$\u001A\u00060\tj\u0002`%H\u0002J\u001A\u0010&\u001A\u0004\u0018\u00010\u00072\u0006\u0010\'\u001A\u00020\t2\u0006\u0010\"\u001A\u00020\u0015H\u0002J\"\u0010(\u001A\u00020)2\n\u0010$\u001A\u00060\tj\u0002`%2\u000E\u0010*\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070+J$\u0010,\u001A\u00020)2\n\u0010$\u001A\u00060\tj\u0002`%2\u000E\u0010*\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070+H\u0002J\u000E\u0010-\u001A\u00020\u0018*\u0004\u0018\u00010\u0007H\u0002R\t\u0010\u0003\u001A\u00020\u0004X\u0082\u0004R\u0016\u0010\u0005\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\t8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\t\u0010\f\u001A\u00020\u0004X\u0082\u0004R\u0011\u0010\r\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000EX\u0082\u0004R\t\u0010\u000F\u001A\u00020\u0004X\u0082\u0004R\u0014\u0010\u0010\u001A\u00020\t8@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u000B\u00A8\u0006."}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "", "()V", "blockingTasksInBuffer", "Lkotlinx/atomicfu/AtomicInt;", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Lkotlinx/coroutines/scheduling/Task;", "bufferSize", "", "getBufferSize", "()I", "consumerIndex", "lastScheduledTask", "Lkotlinx/atomicfu/AtomicRef;", "producerIndex", "size", "getSize$kotlinx_coroutines_core", "add", "task", "fair", "", "addLast", "offloadAllWorkTo", "", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "poll", "pollBlocking", "pollBuffer", "pollCpu", "pollTo", "queue", "pollWithExclusiveMode", "onlyBlocking", "stealWithExclusiveMode", "stealingMode", "Lkotlinx/coroutines/scheduling/StealingMode;", "tryExtractFromTheMiddle", "index", "trySteal", "", "stolenTaskRef", "Lkotlin/jvm/internal/Ref$ObjectRef;", "tryStealLastScheduled", "decrementIfBlocking", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkQueue {
    @Volatile
    private volatile int blockingTasksInBuffer;
    private static final AtomicIntegerFieldUpdater blockingTasksInBuffer$FU;
    private final AtomicReferenceArray buffer;
    @Volatile
    private volatile int consumerIndex;
    private static final AtomicIntegerFieldUpdater consumerIndex$FU;
    @Volatile
    private volatile Object lastScheduledTask;
    private static final AtomicReferenceFieldUpdater lastScheduledTask$FU;
    @Volatile
    private volatile int producerIndex;
    private static final AtomicIntegerFieldUpdater producerIndex$FU;

    static {
        WorkQueue.lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
        WorkQueue.producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
        WorkQueue.consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
        WorkQueue.blockingTasksInBuffer$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");
    }

    public WorkQueue() {
        this.buffer = new AtomicReferenceArray(0x80);
    }

    public final Task add(Task task0, boolean z) {
        if(z) {
            return this.addLast(task0);
        }
        Task task1 = (Task)WorkQueue.lastScheduledTask$FU.getAndSet(this, task0);
        return task1 == null ? null : this.addLast(task1);
    }

    public static Task add$default(WorkQueue workQueue0, Task task0, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        return workQueue0.add(task0, z);
    }

    private final Task addLast(Task task0) {
        if(this.getBufferSize() == 0x7F) {
            return task0;
        }
        if(task0.taskContext.getTaskMode() == 1) {
            WorkQueue.blockingTasksInBuffer$FU.incrementAndGet(this);
        }
        int v = WorkQueue.producerIndex$FU.get(this);
        while(this.buffer.get(v & 0x7F) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(v & 0x7F, task0);
        WorkQueue.producerIndex$FU.incrementAndGet(this);
        return null;
    }

    private final void decrementIfBlocking(Task task0) {
        if(task0 != null && task0.taskContext.getTaskMode() == 1 && false) {
            throw new AssertionError();
        }
    }

    private final int getBufferSize() {
        return WorkQueue.producerIndex$FU.get(this) - WorkQueue.consumerIndex$FU.get(this);
    }

    public final int getSize$kotlinx_coroutines_core() {
        return WorkQueue.lastScheduledTask$FU.get(this) == null ? this.getBufferSize() : this.getBufferSize() + 1;
    }

    public final void offloadAllWorkTo(GlobalQueue globalQueue0) {
        Task task0 = (Task)WorkQueue.lastScheduledTask$FU.getAndSet(this, null);
        if(task0 != null) {
            globalQueue0.addLast(task0);
        }
        while(this.pollTo(globalQueue0)) {
        }
    }

    public final Task poll() {
        Task task0 = (Task)WorkQueue.lastScheduledTask$FU.getAndSet(this, null);
        return task0 == null ? this.pollBuffer() : task0;
    }

    public final Task pollBlocking() {
        return this.pollWithExclusiveMode(true);
    }

    private final Task pollBuffer() {
        Task task0;
        int v;
        do {
            do {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = WorkQueue.consumerIndex$FU;
                v = atomicIntegerFieldUpdater0.get(this);
                if(v - WorkQueue.producerIndex$FU.get(this) == 0) {
                    return null;
                }
            }
            while(!atomicIntegerFieldUpdater0.compareAndSet(this, v, v + 1));
            task0 = (Task)this.buffer.getAndSet(v & 0x7F, null);
        }
        while(task0 == null);
        this.decrementIfBlocking(task0);
        return task0;
    }

    public final Task pollCpu() {
        return this.pollWithExclusiveMode(false);
    }

    private final boolean pollTo(GlobalQueue globalQueue0) {
        Task task0 = this.pollBuffer();
        if(task0 == null) {
            return false;
        }
        globalQueue0.addLast(task0);
        return true;
    }

    private final Task pollWithExclusiveMode(boolean z) {
        Task task1;
        while(true) {
            boolean z1 = true;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = WorkQueue.lastScheduledTask$FU;
            Task task0 = (Task)atomicReferenceFieldUpdater0.get(this);
            if(task0 == null) {
                break;
            }
            if(task0.taskContext.getTaskMode() != 1) {
                z1 = false;
            }
            if(z1 != z) {
                break;
            }
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, task0, null)) {
                return task0;
            }
        }
        int v = WorkQueue.consumerIndex$FU.get(this);
        int v1 = WorkQueue.producerIndex$FU.get(this);
        do {
            if(v == v1 || z && WorkQueue.blockingTasksInBuffer$FU.get(this) == 0) {
                return null;
            }
            --v1;
            task1 = this.tryExtractFromTheMiddle(v1, z);
        }
        while(task1 == null);
        return task1;
    }

    private final Task stealWithExclusiveMode(int v) {
        Task task0;
        int v1 = WorkQueue.consumerIndex$FU.get(this);
        int v2 = WorkQueue.producerIndex$FU.get(this);
        boolean z = true;
        if(v != 1) {
            z = false;
        }
        while(true) {
            if(v1 == v2 || z && WorkQueue.blockingTasksInBuffer$FU.get(this) == 0) {
                return null;
            }
            task0 = this.tryExtractFromTheMiddle(v1, z);
            if(task0 != null) {
                break;
            }
            ++v1;
        }
        return task0;
    }

    private final Task tryExtractFromTheMiddle(int v, boolean z) {
        boolean z1 = true;
        Task task0 = (Task)this.buffer.get(v & 0x7F);
        if(task0 != null) {
            if(task0.taskContext.getTaskMode() != 1) {
                z1 = false;
            }
            if(z1 == z && ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(this.buffer, v & 0x7F, task0, null)) {
                if(z) {
                    WorkQueue.blockingTasksInBuffer$FU.decrementAndGet(this);
                }
                return task0;
            }
        }
        return null;
    }

    public final long trySteal(int v, ObjectRef ref$ObjectRef0) {
        Task task0 = v == 3 ? this.pollBuffer() : this.stealWithExclusiveMode(v);
        if(task0 != null) {
            ref$ObjectRef0.element = task0;
            return -1L;
        }
        return this.tryStealLastScheduled(v, ref$ObjectRef0);
    }

    private final long tryStealLastScheduled(int v, ObjectRef ref$ObjectRef0) {
        Task task0;
        do {
            int v1 = 1;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = WorkQueue.lastScheduledTask$FU;
            task0 = (Task)atomicReferenceFieldUpdater0.get(this);
            if(task0 == null) {
                return -2L;
            }
            if(task0.taskContext.getTaskMode() != 1) {
                v1 = 2;
            }
            if((v1 & v) == 0) {
                return -2L;
            }
            long v2 = TasksKt.schedulerTimeSource.nanoTime() - task0.submissionTime;
            if(v2 < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS) {
                return TasksKt.WORK_STEALING_TIME_RESOLUTION_NS - v2;
            }
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, task0, null));
        ref$ObjectRef0.element = task0;
        return -1L;
    }
}

