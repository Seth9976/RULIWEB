package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\b\u0012\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\nH\u0014¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/StandaloneCoroutine;", "Lkotlinx/coroutines/AbstractCoroutine;", "", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Z)V", "handleJobException", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
class StandaloneCoroutine extends AbstractCoroutine {
    public StandaloneCoroutine(CoroutineContext coroutineContext0, boolean z) {
        super(coroutineContext0, true, z);
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected boolean handleJobException(Throwable throwable0) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), throwable0);
        return true;
    }
}

