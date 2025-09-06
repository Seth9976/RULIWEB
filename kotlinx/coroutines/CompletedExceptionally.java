package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000B\u001A\u00020\u0005J\b\u0010\f\u001A\u00020\rH\u0016R\t\u0010\u0007\u001A\u00020\bX\u0082\u0004R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001A\u00020\u00058F¢\u0006\u0006\u001A\u0004\b\t\u0010\n¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/CompletedExceptionally;", "", "cause", "", "handled", "", "(Ljava/lang/Throwable;Z)V", "_handled", "Lkotlinx/atomicfu/AtomicBoolean;", "getHandled", "()Z", "makeHandled", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class CompletedExceptionally {
    @Volatile
    private volatile int _handled;
    private static final AtomicIntegerFieldUpdater _handled$FU;
    public final Throwable cause;

    static {
        CompletedExceptionally._handled$FU = AtomicIntegerFieldUpdater.newUpdater(CompletedExceptionally.class, "_handled");
    }

    public CompletedExceptionally(Throwable throwable0, boolean z) {
        this.cause = throwable0;
        this._handled = z;
    }

    public CompletedExceptionally(Throwable throwable0, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            z = false;
        }
        this(throwable0, z);
    }

    public final boolean getHandled() {
        return CompletedExceptionally._handled$FU.get(this) != 0;
    }

    public final boolean makeHandled() {
        return CompletedExceptionally._handled$FU.compareAndSet(this, 0, 1);
    }

    @Override
    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '[' + this.cause + ']';
    }
}

