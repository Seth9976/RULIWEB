package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\n\u0010\t\u001A\u00060\nj\u0002`\u000BH\u0016J\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u0007\u001A\u00020\bH\u0016R\u0010\u0010\u0003\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/lifecycle/PausingDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "dispatchQueue", "Landroidx/lifecycle/DispatchQueue;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "isDispatchNeeded", "", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class PausingDispatcher extends CoroutineDispatcher {
    public final DispatchQueue dispatchQueue;

    public PausingDispatcher() {
        this.dispatchQueue = new DispatchQueue();
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "context");
        Intrinsics.checkNotNullParameter(runnable0, "block");
        this.dispatchQueue.dispatchAndEnqueue(coroutineContext0, runnable0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(CoroutineContext coroutineContext0) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "context");
        return Dispatchers.getMain().getImmediate().isDispatchNeeded(coroutineContext0) ? true : !this.dispatchQueue.canRun();
    }
}

