package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001A\u00020\u0006H\u0016J\b\u0010\u0007\u001A\u00020\bH\u0016R\u0012\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/DisposableFutureHandle;", "Lkotlinx/coroutines/DisposableHandle;", "future", "Ljava/util/concurrent/Future;", "(Ljava/util/concurrent/Future;)V", "dispose", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class DisposableFutureHandle implements DisposableHandle {
    private final Future future;

    public DisposableFutureHandle(Future future0) {
        this.future = future0;
    }

    @Override  // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        this.future.cancel(false);
    }

    @Override
    public String toString() {
        return "DisposableFutureHandle[" + this.future + ']';
    }
}

