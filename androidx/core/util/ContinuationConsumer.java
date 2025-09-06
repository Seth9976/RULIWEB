package androidx.core.util;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0013\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\nJ\b\u0010\u000B\u001A\u00020\fH\u0016R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/core/util/ContinuationConsumer;", "T", "Ljava/util/function/Consumer;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "continuation", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "accept", "", "value", "(Ljava/lang/Object;)V", "toString", "", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ContinuationConsumer extends AtomicBoolean implements Consumer {
    private final Continuation continuation;

    public ContinuationConsumer(Continuation continuation0) {
        super(false);
        this.continuation = continuation0;
    }

    @Override
    public void accept(Object object0) {
        if(this.compareAndSet(false, true)) {
            this.continuation.resumeWith(object0);
        }
    }

    @Override
    public String toString() {
        return "ContinuationConsumer(resultAccepted = " + this.get() + ')';
    }
}

