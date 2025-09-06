package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005B\u001D\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\t¢\u0006\u0002\u0010\u000BJ\u0012\u0010\u0015\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001A\u00020\u001AH\u0014J\u0015\u0010\u001B\u001A\u00020\u00162\u0006\u0010\u001C\u001A\u00020\u001DH\u0000¢\u0006\u0002\b\u001EJ\r\u0010\u001F\u001A\u00020\u001AH\u0010¢\u0006\u0002\b J\u0018\u0010!\u001A\u00020\u00162\u0006\u0010\"\u001A\u00020\u001D2\u0006\u0010#\u001A\u00020\tH\u0014J\u0015\u0010$\u001A\u00020\u00162\u0006\u0010%\u001A\u00028\u0000H\u0014¢\u0006\u0002\u0010&J\u0012\u0010\'\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018H\u0004J\u001C\u0010(\u001A\u00020\u00162\f\u0010)\u001A\b\u0012\u0004\u0012\u00028\u00000*ø\u0001\u0000¢\u0006\u0002\u0010&JM\u0010+\u001A\u00020\u0016\"\u0004\b\u0001\u0010,2\u0006\u0010+\u001A\u00020-2\u0006\u0010.\u001A\u0002H,2\'\u0010/\u001A#\b\u0001\u0012\u0004\u0012\u0002H,\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001800¢\u0006\u0002\b1ø\u0001\u0000¢\u0006\u0002\u00102R\u0017\u0010\f\u001A\u00020\u0007¢\u0006\u000E\n\u0000\u0012\u0004\b\r\u0010\u000E\u001A\u0004\b\u000F\u0010\u0010R\u0014\u0010\u0011\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001A\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lkotlinx/coroutines/AbstractCoroutine;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/CoroutineScope;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "initParentJob", "", "active", "(Lkotlin/coroutines/CoroutineContext;ZZ)V", "context", "getContext$annotations", "()V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "getCoroutineContext", "isActive", "()Z", "afterResume", "", "state", "", "cancellationExceptionMessage", "", "handleOnCompletionException", "exception", "", "handleOnCompletionException$kotlinx_coroutines_core", "nameString", "nameString$kotlinx_coroutines_core", "onCancelled", "cause", "handled", "onCompleted", "value", "(Ljava/lang/Object;)V", "onCompletionInternal", "resumeWith", "result", "Lkotlin/Result;", "start", "R", "Lkotlinx/coroutines/CoroutineStart;", "receiver", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineStart;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class AbstractCoroutine extends JobSupport implements Continuation, CoroutineScope, Job {
    private final CoroutineContext context;

    public AbstractCoroutine(CoroutineContext coroutineContext0, boolean z, boolean z1) {
        super(z1);
        if(z) {
            this.initParentJob(((Job)coroutineContext0.get(Job.Key)));
        }
        this.context = coroutineContext0.plus(this);
    }

    protected void afterResume(Object object0) {
        this.afterCompletion(object0);
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected String cancellationExceptionMessage() {
        return DebugStringsKt.getClassSimpleName(this) + " was cancelled";
    }

    @Override  // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    public static void getContext$annotations() {
    }

    @Override  // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override  // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$kotlinx_coroutines_core(Throwable throwable0) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, throwable0);
    }

    @Override  // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override  // kotlinx.coroutines.JobSupport
    public String nameString$kotlinx_coroutines_core() {
        String s = CoroutineContextKt.getCoroutineName(this.context);
        return s == null ? super.nameString$kotlinx_coroutines_core() : "\"" + s + "\":" + super.nameString$kotlinx_coroutines_core();
    }

    protected void onCancelled(Throwable throwable0, boolean z) {
    }

    protected void onCompleted(Object object0) {
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected final void onCompletionInternal(Object object0) {
        if(object0 instanceof CompletedExceptionally) {
            boolean z = ((CompletedExceptionally)object0).getHandled();
            this.onCancelled(((CompletedExceptionally)object0).cause, z);
            return;
        }
        this.onCompleted(object0);
    }

    @Override  // kotlin.coroutines.Continuation
    public final void resumeWith(Object object0) {
        Object object1 = this.makeCompletingOnce$kotlinx_coroutines_core(CompletionStateKt.toState$default(object0, null, 1, null));
        if(object1 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return;
        }
        this.afterResume(object1);
    }

    public final void start(CoroutineStart coroutineStart0, Object object0, Function2 function20) {
        coroutineStart0.invoke(function20, object0, this);
    }
}

