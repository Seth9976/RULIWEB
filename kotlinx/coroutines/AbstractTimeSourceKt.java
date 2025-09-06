package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\t\u0010\u0006\u001A\u00020\u0007H\u0081\b\u001A\t\u0010\b\u001A\u00020\u0007H\u0081\b\u001A\u0019\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u0007H\u0081\b\u001A\t\u0010\u000E\u001A\u00020\nH\u0081\b\u001A\t\u0010\u000F\u001A\u00020\nH\u0081\b\u001A\t\u0010\u0010\u001A\u00020\nH\u0081\b\u001A\u0011\u0010\u0011\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u0013H\u0081\b\u001A\t\u0010\u0014\u001A\u00020\nH\u0081\b\u001A\u0019\u0010\u0015\u001A\u00060\u0016j\u0002`\u00172\n\u0010\u0018\u001A\u00060\u0016j\u0002`\u0017H\u0081\b\"\u001C\u0010\u0000\u001A\u0004\u0018\u00010\u0001X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0019"}, d2 = {"timeSource", "Lkotlinx/coroutines/AbstractTimeSource;", "getTimeSource", "()Lkotlinx/coroutines/AbstractTimeSource;", "setTimeSource", "(Lkotlinx/coroutines/AbstractTimeSource;)V", "currentTimeMillis", "", "nanoTime", "parkNanos", "", "blocker", "", "nanos", "registerTimeLoopThread", "trackTask", "unTrackTask", "unpark", "thread", "Ljava/lang/Thread;", "unregisterTimeLoopThread", "wrapTask", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class AbstractTimeSourceKt {
    private static AbstractTimeSource timeSource;

    static {
    }

    private static final long currentTimeMillis() {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        return abstractTimeSource0 == null ? System.currentTimeMillis() : abstractTimeSource0.currentTimeMillis();
    }

    public static final AbstractTimeSource getTimeSource() {
        return AbstractTimeSourceKt.timeSource;
    }

    private static final long nanoTime() {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        return abstractTimeSource0 == null ? System.nanoTime() : abstractTimeSource0.nanoTime();
    }

    private static final void parkNanos(Object object0, long v) {
        Unit unit0;
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 == null) {
            unit0 = null;
        }
        else {
            abstractTimeSource0.parkNanos(object0, v);
            unit0 = Unit.INSTANCE;
        }
        if(unit0 == null) {
            LockSupport.parkNanos(object0, v);
        }
    }

    private static final void registerTimeLoopThread() {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.registerTimeLoopThread();
        }
    }

    public static final void setTimeSource(AbstractTimeSource abstractTimeSource0) {
        AbstractTimeSourceKt.timeSource = abstractTimeSource0;
    }

    private static final void trackTask() {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.trackTask();
        }
    }

    private static final void unTrackTask() {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.unTrackTask();
        }
    }

    private static final void unpark(Thread thread0) {
        Unit unit0;
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 == null) {
            unit0 = null;
        }
        else {
            abstractTimeSource0.unpark(thread0);
            unit0 = Unit.INSTANCE;
        }
        if(unit0 == null) {
            LockSupport.unpark(thread0);
        }
    }

    private static final void unregisterTimeLoopThread() {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.unregisterTimeLoopThread();
        }
    }

    private static final Runnable wrapTask(Runnable runnable0) {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            Runnable runnable1 = abstractTimeSource0.wrapTask(runnable0);
            return runnable1 == null ? runnable0 : runnable1;
        }
        return runnable0;
    }
}

