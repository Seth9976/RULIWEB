package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001C\u0012\u0012\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001D\u0010\u0007\u001A\u00020\b2\n\u0010\t\u001A\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000B\u001A\u00020\fH\u0096\u0001R\u001F\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u00048\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveCatching;", "E", "Lkotlinx/coroutines/Waiter;", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "Lkotlinx/coroutines/channels/ChannelResult;", "(Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invokeOnCancellation", "", "segment", "Lkotlinx/coroutines/internal/Segment;", "index", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ReceiveCatching implements Waiter {
    public final CancellableContinuationImpl cont;

    public ReceiveCatching(CancellableContinuationImpl cancellableContinuationImpl0) {
        this.cont = cancellableContinuationImpl0;
    }

    @Override  // kotlinx.coroutines.Waiter
    public void invokeOnCancellation(Segment segment0, int v) {
        this.cont.invokeOnCancellation(segment0, v);
    }
}

