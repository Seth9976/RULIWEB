package androidx.work.impl.utils;

import androidx.work.Logger;
import androidx.work.impl.Processor;
import androidx.work.impl.StartStopToken;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\t\u001A\u00020\n¢\u0006\u0002\u0010\u000BJ\b\u0010\f\u001A\u00020\rH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/work/impl/utils/StopWorkRunnable;", "Ljava/lang/Runnable;", "processor", "Landroidx/work/impl/Processor;", "token", "Landroidx/work/impl/StartStopToken;", "stopInForeground", "", "(Landroidx/work/impl/Processor;Landroidx/work/impl/StartStopToken;Z)V", "reason", "", "(Landroidx/work/impl/Processor;Landroidx/work/impl/StartStopToken;ZI)V", "run", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class StopWorkRunnable implements Runnable {
    private final Processor processor;
    private final int reason;
    private final boolean stopInForeground;
    private final StartStopToken token;

    public StopWorkRunnable(Processor processor0, StartStopToken startStopToken0, boolean z) {
        Intrinsics.checkNotNullParameter(processor0, "processor");
        Intrinsics.checkNotNullParameter(startStopToken0, "token");
        this(processor0, startStopToken0, z, 0xFFFFFE00);
    }

    public StopWorkRunnable(Processor processor0, StartStopToken startStopToken0, boolean z, int v) {
        Intrinsics.checkNotNullParameter(processor0, "processor");
        Intrinsics.checkNotNullParameter(startStopToken0, "token");
        super();
        this.processor = processor0;
        this.token = startStopToken0;
        this.stopInForeground = z;
        this.reason = v;
    }

    @Override
    public void run() {
        boolean z = this.stopInForeground ? this.processor.stopForegroundWork(this.token, this.reason) : this.processor.stopWork(this.token, this.reason);
        Logger.get().debug("WM-StopWorkRunnable", "StopWorkRunnable for " + this.token.getId().getWorkSpecId() + "; Processor.stopWork = " + z);
    }
}

