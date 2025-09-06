package androidx.work.impl.background.greedy;

import androidx.work.RunnableScheduler;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.WorkLauncher;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u000E\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\fJ\u000E\u0010\u0011\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\fR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000BX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/work/impl/background/greedy/TimeLimiter;", "", "runnableScheduler", "Landroidx/work/RunnableScheduler;", "launcher", "Landroidx/work/impl/WorkLauncher;", "timeoutMs", "", "(Landroidx/work/RunnableScheduler;Landroidx/work/impl/WorkLauncher;J)V", "lock", "tracked", "", "Landroidx/work/impl/StartStopToken;", "Ljava/lang/Runnable;", "cancel", "", "token", "track", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class TimeLimiter {
    private final WorkLauncher launcher;
    private final Object lock;
    private final RunnableScheduler runnableScheduler;
    private final long timeoutMs;
    private final Map tracked;

    // 检测为 Lambda 实现
    public static void $r8$lambda$dwAYwAFpPKNbMC8c13ZgeRxadTI(TimeLimiter timeLimiter0, StartStopToken startStopToken0) [...]

    public TimeLimiter(RunnableScheduler runnableScheduler0, WorkLauncher workLauncher0) {
        Intrinsics.checkNotNullParameter(runnableScheduler0, "runnableScheduler");
        Intrinsics.checkNotNullParameter(workLauncher0, "launcher");
        this(runnableScheduler0, workLauncher0, 0L, 4, null);
    }

    public TimeLimiter(RunnableScheduler runnableScheduler0, WorkLauncher workLauncher0, long v) {
        Intrinsics.checkNotNullParameter(runnableScheduler0, "runnableScheduler");
        Intrinsics.checkNotNullParameter(workLauncher0, "launcher");
        super();
        this.runnableScheduler = runnableScheduler0;
        this.launcher = workLauncher0;
        this.timeoutMs = v;
        this.lock = new Object();
        this.tracked = new LinkedHashMap();
    }

    public TimeLimiter(RunnableScheduler runnableScheduler0, WorkLauncher workLauncher0, long v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 4) != 0) {
            v = TimeUnit.MINUTES.toMillis(90L);
        }
        this(runnableScheduler0, workLauncher0, v);
    }

    public final void cancel(StartStopToken startStopToken0) {
        Runnable runnable0;
        Intrinsics.checkNotNullParameter(startStopToken0, "token");
        synchronized(this.lock) {
            runnable0 = (Runnable)this.tracked.remove(startStopToken0);
        }
        if(runnable0 != null) {
            this.runnableScheduler.cancel(runnable0);
        }
    }

    public final void track(StartStopToken startStopToken0) {
        Intrinsics.checkNotNullParameter(startStopToken0, "token");
        TimeLimiter..ExternalSyntheticLambda0 timeLimiter$$ExternalSyntheticLambda00 = () -> TimeLimiter.track$lambda$0(this, startStopToken0);
        synchronized(this.lock) {
            Runnable runnable0 = (Runnable)this.tracked.put(startStopToken0, timeLimiter$$ExternalSyntheticLambda00);
        }
        this.runnableScheduler.scheduleWithDelay(this.timeoutMs, timeLimiter$$ExternalSyntheticLambda00);
    }

    private static final void track$lambda$0(TimeLimiter timeLimiter0, StartStopToken startStopToken0) {
        timeLimiter0.launcher.stopWork(startStopToken0, 3);
    }
}

