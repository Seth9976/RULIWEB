package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CompletionStateKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u001B\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0012\u0010\u0010\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0013H\u0014J\u000E\u0010\u0015\u001A\n\u0018\u00010\u0016j\u0004\u0018\u0001`\u0017R\u0019\u0010\n\u001A\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048F¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u0014\u0010\r\u001A\u00020\u000E8DX\u0084\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000FR\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/internal/ScopeCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "isScopedCoroutine", "", "()Z", "afterCompletion", "", "state", "", "afterResume", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ScopeCoroutine extends AbstractCoroutine implements CoroutineStackFrame {
    public final Continuation uCont;

    public ScopeCoroutine(CoroutineContext coroutineContext0, Continuation continuation0) {
        super(coroutineContext0, true, true);
        this.uCont = continuation0;
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected void afterCompletion(Object object0) {
        DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt.intercepted(this.uCont), CompletionStateKt.recoverResult(object0, this.uCont), null, 2, null);
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    protected void afterResume(Object object0) {
        Object object1 = CompletionStateKt.recoverResult(object0, this.uCont);
        this.uCont.resumeWith(object1);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        return this.uCont instanceof CoroutineStackFrame ? ((CoroutineStackFrame)this.uCont) : null;
    }

    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected final boolean isScopedCoroutine() {
        return true;
    }
}

