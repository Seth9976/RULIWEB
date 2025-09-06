package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001A\u0010\u0010\u000B\u001A\u00020\u00042\u0006\u0010\f\u001A\u00020\u0004H\u0000\u001A\u0010\u0010\r\u001A\u00020\u00042\u0006\u0010\u000E\u001A\u00020\u0004H\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\n\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000*\u001E\b\u0002\u0010\u000F\u001A\u0004\b\u0000\u0010\u0010\"\b\u0012\u0004\u0012\u0002H\u00100\u00112\b\u0012\u0004\u0012\u0002H\u00100\u0011¨\u0006\u0012"}, d2 = {"CLOSED_EMPTY", "Lkotlinx/coroutines/internal/Symbol;", "DISPOSED_TASK", "MAX_DELAY_NS", "", "MAX_MS", "MS_TO_NS", "SCHEDULE_COMPLETED", "", "SCHEDULE_DISPOSED", "SCHEDULE_OK", "delayNanosToMillis", "timeNanos", "delayToNanos", "timeMillis", "Queue", "T", "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class EventLoop_commonKt {
    private static final Symbol CLOSED_EMPTY = null;
    private static final Symbol DISPOSED_TASK = null;
    private static final long MAX_DELAY_NS = 0x3FFFFFFFFFFFFFFFL;
    private static final long MAX_MS = 0x8637BD05AF6L;
    private static final long MS_TO_NS = 1000000L;
    private static final int SCHEDULE_COMPLETED = 1;
    private static final int SCHEDULE_DISPOSED = 2;
    private static final int SCHEDULE_OK;

    static {
        EventLoop_commonKt.DISPOSED_TASK = new Symbol("REMOVED_TASK");
        EventLoop_commonKt.CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");
    }

    public static final Symbol access$getCLOSED_EMPTY$p() {
        return EventLoop_commonKt.CLOSED_EMPTY;
    }

    public static final Symbol access$getDISPOSED_TASK$p() {
        return EventLoop_commonKt.DISPOSED_TASK;
    }

    public static final long delayNanosToMillis(long v) [...] // Inlined contents

    public static final long delayToNanos(long v) {
        if(v <= 0L) {
            return 0L;
        }
        return v < 0x8637BD05AF6L ? v * 1000000L : 0x7FFFFFFFFFFFFFFFL;
    }
}

