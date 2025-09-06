package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u000E\u001A\u00020\u0002J\u001E\u0010\u000F\u001A\u00020\u00022\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0004\u001A\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R(\u0010\b\u001A\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0086\u000Eø\u0001\u0000ø\u0001\u0001¢\u0006\u000E\n\u0000\u001A\u0004\b\n\u0010\u000B\"\u0004\b\f\u0010\r\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0011"}, d2 = {"Lkotlin/coroutines/jvm/internal/RunSuspend;", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "result", "Lkotlin/Result;", "getResult-xLWZpok", "()Lkotlin/Result;", "setResult", "(Lkotlin/Result;)V", "await", "resumeWith", "(Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class RunSuspend implements Continuation {
    private Result result;

    public final void await() {
        synchronized(this) {
            Result result0;
            while((result0 = this.result) == null) {
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                this.wait();
            }
            ResultKt.throwOnFailure(result0.unbox-impl());
        }
    }

    @Override  // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    public final Result getResult-xLWZpok() {
        return this.result;
    }

    @Override  // kotlin.coroutines.Continuation
    public void resumeWith(Object object0) {
        synchronized(this) {
            this.result = Result.box-impl(object0);
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
            this.notifyAll();
        }
    }

    public final void setResult(Result result0) {
        this.result = result0;
    }
}

