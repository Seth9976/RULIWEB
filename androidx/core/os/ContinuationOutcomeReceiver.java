package androidx.core.os;

import android.os.OutcomeReceiver;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\u00020\u0005B\u0013\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000FJ\b\u0010\u0010\u001A\u00020\u0011H\u0016R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/core/os/ContinuationOutcomeReceiver;", "R", "E", "", "Landroid/os/OutcomeReceiver;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "continuation", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "onError", "", "error", "(Ljava/lang/Throwable;)V", "onResult", "result", "(Ljava/lang/Object;)V", "toString", "", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ContinuationOutcomeReceiver extends AtomicBoolean implements OutcomeReceiver {
    private final Continuation continuation;

    public ContinuationOutcomeReceiver(Continuation continuation0) {
        super(false);
        this.continuation = continuation0;
    }

    @Override  // android.os.OutcomeReceiver
    public void onError(Throwable throwable0) {
        if(this.compareAndSet(false, true)) {
            Object object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
            this.continuation.resumeWith(object0);
        }
    }

    @Override  // android.os.OutcomeReceiver
    public void onResult(Object object0) {
        if(this.compareAndSet(false, true)) {
            this.continuation.resumeWith(object0);
        }
    }

    @Override
    public String toString() {
        return "ContinuationOutcomeReceiver(outcomeReceived = " + this.get() + ')';
    }
}

