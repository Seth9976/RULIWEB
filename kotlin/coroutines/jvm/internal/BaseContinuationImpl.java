package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\b!\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\u00020\u0004B\u0017\u0012\u0010\u0010\u0005\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001¢\u0006\u0002\u0010\u0006J$\u0010\f\u001A\b\u0012\u0004\u0012\u00020\r0\u00012\b\u0010\u000E\u001A\u0004\u0018\u00010\u00022\n\u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u0001H\u0016J\u001A\u0010\f\u001A\b\u0012\u0004\u0012\u00020\r0\u00012\n\u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u0001H\u0016J\n\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0016J\"\u0010\u0011\u001A\u0004\u0018\u00010\u00022\u000E\u0010\u0012\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0013H$ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001A\u00020\rH\u0014J\u001E\u0010\u0016\u001A\u00020\r2\u000E\u0010\u0012\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0013ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001A\u00020\u0019H\u0016R\u0016\u0010\u0007\u001A\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\tR\u001B\u0010\u0005\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001A"}, d2 = {"Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Ljava/io/Serializable;", "completion", "(Lkotlin/coroutines/Continuation;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getCompletion", "()Lkotlin/coroutines/Continuation;", "create", "", "value", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "invokeSuspend", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "resumeWith", "(Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class BaseContinuationImpl implements Serializable, Continuation, CoroutineStackFrame {
    private final Continuation completion;

    public BaseContinuationImpl(Continuation continuation0) {
        this.completion = continuation0;
    }

    public Continuation create(Object object0, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public Continuation create(Continuation continuation0) {
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        return this.completion instanceof CoroutineStackFrame ? ((CoroutineStackFrame)this.completion) : null;
    }

    public final Continuation getCompletion() {
        return this.completion;
    }

    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return DebugMetadataKt.getStackTraceElement(this);
    }

    protected abstract Object invokeSuspend(Object arg1);

    protected void releaseIntercepted() {
    }

    @Override  // kotlin.coroutines.Continuation
    public final void resumeWith(Object object0) {
        Continuation continuation1;
        for(Continuation continuation0 = this; true; continuation0 = continuation1) {
            DebugProbesKt.probeCoroutineResumed(continuation0);
            BaseContinuationImpl baseContinuationImpl0 = (BaseContinuationImpl)continuation0;
            continuation1 = baseContinuationImpl0.completion;
            Intrinsics.checkNotNull(continuation1);
            try {
                Object object1 = baseContinuationImpl0.invokeSuspend(object0);
                if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return;
                }
                object0 = object1;
            }
            catch(Throwable throwable0) {
                object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
            }
            baseContinuationImpl0.releaseIntercepted();
            if(!(continuation1 instanceof BaseContinuationImpl)) {
                break;
            }
        }
        continuation1.resumeWith(object0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("Continuation at ");
        StackTraceElement stackTraceElement0 = this.getStackTraceElement();
        if(stackTraceElement0 == null) {
            stackTraceElement0 = this.getClass().getName();
        }
        stringBuilder0.append(stackTraceElement0);
        return stringBuilder0.toString();
    }
}

