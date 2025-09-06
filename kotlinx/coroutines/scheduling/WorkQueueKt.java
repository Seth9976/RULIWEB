package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0006\u001A\u00060\u0001j\u0002`\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\b\u001A\u00060\u0001j\u0002`\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u0012\u0010\t\u001A\u00060\u0001j\u0002`\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\n\u001A\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u0019\u0010\u000B\u001A\u00020\u0001*\u00020\f8À\u0002X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000E*\f\b\u0000\u0010\u000F\"\u00020\u00012\u00020\u0001¨\u0006\u0010"}, d2 = {"BUFFER_CAPACITY", "", "BUFFER_CAPACITY_BASE", "MASK", "NOTHING_TO_STEAL", "", "STEAL_ANY", "Lkotlinx/coroutines/scheduling/StealingMode;", "STEAL_BLOCKING_ONLY", "STEAL_CPU_ONLY", "TASK_STOLEN", "maskForStealingMode", "Lkotlinx/coroutines/scheduling/Task;", "getMaskForStealingMode", "(Lkotlinx/coroutines/scheduling/Task;)I", "StealingMode", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkQueueKt {
    public static final int BUFFER_CAPACITY = 0x80;
    public static final int BUFFER_CAPACITY_BASE = 7;
    public static final int MASK = 0x7F;
    public static final long NOTHING_TO_STEAL = -2L;
    public static final int STEAL_ANY = 3;
    public static final int STEAL_BLOCKING_ONLY = 1;
    public static final int STEAL_CPU_ONLY = 2;
    public static final long TASK_STOLEN = -1L;

    public static final int getMaskForStealingMode(Task task0) {
        return task0.taskContext.getTaskMode() == 1 ? 1 : 2;
    }
}

