package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\bÀ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J \u0010\b\u001A\u00020\t2\u000E\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000BH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\fJ\b\u0010\r\u001A\u00020\u000EH\u0016R\u0014\u0010\u0004\u001A\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000F"}, d2 = {"Lkotlin/coroutines/jvm/internal/CompletedContinuation;", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class CompletedContinuation implements Continuation {
    public static final CompletedContinuation INSTANCE;

    static {
        CompletedContinuation.INSTANCE = new CompletedContinuation();
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.Continuation
    public void resumeWith(Object object0) {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override
    public String toString() {
        return "This continuation is already complete";
    }
}

