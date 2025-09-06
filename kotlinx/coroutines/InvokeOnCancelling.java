package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B.\u0012\'\u0010\u0002\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t¢\u0006\u0002\u0010\nJ\u0013\u0010\r\u001A\u00020\b2\b\u0010\u0007\u001A\u0004\u0018\u00010\u0004H\u0096\u0002R\t\u0010\u000B\u001A\u00020\fX\u0082\u0004R/\u0010\u0002\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/InvokeOnCancelling;", "Lkotlinx/coroutines/JobCancellingNode;", "handler", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "(Lkotlin/jvm/functions/Function1;)V", "_invoked", "Lkotlinx/atomicfu/AtomicInt;", "invoke", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class InvokeOnCancelling extends JobCancellingNode {
    @Volatile
    private volatile int _invoked;
    private static final AtomicIntegerFieldUpdater _invoked$FU;
    private final Function1 handler;

    static {
        InvokeOnCancelling._invoked$FU = AtomicIntegerFieldUpdater.newUpdater(InvokeOnCancelling.class, "_invoked");
    }

    public InvokeOnCancelling(Function1 function10) {
        this.handler = function10;
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.invoke(((Throwable)object0));
        return Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.CompletionHandlerBase
    public void invoke(Throwable throwable0) {
        if(InvokeOnCancelling._invoked$FU.compareAndSet(this, 0, 1)) {
            this.handler.invoke(throwable0);
        }
    }
}

