package kotlin.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0001\u0018\u0000 \u001A*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0001\u001AB\u0015\b\u0011\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005B\u001F\b\u0000\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\n\u0010\u0011\u001A\u0004\u0018\u00010\u0007H\u0001J\n\u0010\u0012\u001A\u0004\u0018\u00010\u0013H\u0016J\u001E\u0010\u0014\u001A\u00020\u00152\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001A\u00020\u0019H\u0016R\u0016\u0010\t\u001A\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\f\u001A\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001B"}, d2 = {"Lkotlin/coroutines/SafeContinuation;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "(Lkotlin/coroutines/Continuation;)V", "initialResult", "", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "result", "getOrThrow", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class SafeContinuation implements Continuation, CoroutineStackFrame {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Rd\u0010\u0003\u001AR\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001 \u0006*(\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lkotlin/coroutines/SafeContinuation$Companion;", "", "()V", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/SafeContinuation;", "kotlin.jvm.PlatformType", "getRESULT$annotations", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        private static void getRESULT$annotations() {
        }
    }

    private static final Companion Companion;
    private static final AtomicReferenceFieldUpdater RESULT;
    private final Continuation delegate;
    private volatile Object result;

    static {
        SafeContinuation.Companion = new Companion(null);
        SafeContinuation.RESULT = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "result");
    }

    public SafeContinuation(Continuation continuation0) {
        Intrinsics.checkNotNullParameter(continuation0, "delegate");
        this(continuation0, CoroutineSingletons.UNDECIDED);
    }

    public SafeContinuation(Continuation continuation0, Object object0) {
        Intrinsics.checkNotNullParameter(continuation0, "delegate");
        super();
        this.delegate = continuation0;
        this.result = object0;
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        return this.delegate instanceof CoroutineStackFrame ? ((CoroutineStackFrame)this.delegate) : null;
    }

    @Override  // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.delegate.getContext();
    }

    public final Object getOrThrow() {
        Object object0 = this.result;
        if(object0 == CoroutineSingletons.UNDECIDED) {
            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(SafeContinuation.RESULT, this, CoroutineSingletons.UNDECIDED, object1)) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            object0 = this.result;
        }
        if(object0 == CoroutineSingletons.RESUMED) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if(object0 instanceof Failure) {
            throw ((Failure)object0).exception;
        }
        return object0;
    }

    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override  // kotlin.coroutines.Continuation
    public void resumeWith(Object object0) {
        while(true) {
            Object object1;
            while((object1 = this.result) == CoroutineSingletons.UNDECIDED) {
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(SafeContinuation.RESULT, this, CoroutineSingletons.UNDECIDED, object0)) {
                    return;
                }
            }
            if(object1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                break;
            }
            Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(SafeContinuation.RESULT, this, object2, CoroutineSingletons.RESUMED)) {
                this.delegate.resumeWith(object0);
                return;
            }
        }
        throw new IllegalStateException("Already resumed");
    }

    @Override
    public String toString() {
        return "SafeContinuation for " + this.delegate;
    }
}

