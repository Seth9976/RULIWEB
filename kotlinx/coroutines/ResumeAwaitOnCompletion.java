package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001A\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\tH\u0096\u0002R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", "T", "Lkotlinx/coroutines/JobNode;", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ResumeAwaitOnCompletion extends JobNode {
    private final CancellableContinuationImpl continuation;

    public ResumeAwaitOnCompletion(CancellableContinuationImpl cancellableContinuationImpl0) {
        this.continuation = cancellableContinuationImpl0;
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.invoke(((Throwable)object0));
        return Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.CompletionHandlerBase
    public void invoke(Throwable throwable0) {
        Object object0 = this.getJob().getState$kotlinx_coroutines_core();
        if(object0 instanceof CompletedExceptionally) {
            Object object1 = Result.constructor-impl(ResultKt.createFailure(((CompletedExceptionally)object0).cause));
            this.continuation.resumeWith(object1);
            return;
        }
        Object object2 = Result.constructor-impl(JobSupportKt.unboxState(object0));
        this.continuation.resumeWith(object2);
    }
}

