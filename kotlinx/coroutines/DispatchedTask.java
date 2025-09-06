package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import jeb.synthetic.FIN;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000F\b!\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00060\u0002j\u0002`\u0003B\u000F\b\u0000\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001F\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J\u0019\u0010\u0012\u001A\u0004\u0018\u00010\u00102\b\u0010\u0013\u001A\u0004\u0018\u00010\u000EH\u0010¢\u0006\u0002\b\u0014J\u001F\u0010\u0015\u001A\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u0013\u001A\u0004\u0018\u00010\u000EH\u0010¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u0018\u001A\u00020\f2\b\u0010\u0019\u001A\u0004\u0018\u00010\u00102\b\u0010\u001A\u001A\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u001BJ\u0006\u0010\u001C\u001A\u00020\fJ\u000F\u0010\u001D\u001A\u0004\u0018\u00010\u000EH ¢\u0006\u0002\b\u001ER\u0018\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bX \u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0012\u0010\u0004\u001A\u00020\u00058\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Lkotlinx/coroutines/DispatchedTask;", "T", "Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/SchedulerTask;", "resumeMode", "", "(I)V", "delegate", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "cancelCompletedResult", "", "takenState", "", "cause", "", "cancelCompletedResult$kotlinx_coroutines_core", "getExceptionalResult", "state", "getExceptionalResult$kotlinx_coroutines_core", "getSuccessfulResult", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "handleFatalException", "exception", "finallyException", "handleFatalException$kotlinx_coroutines_core", "run", "takeState", "takeState$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class DispatchedTask extends Task {
    public int resumeMode;

    public DispatchedTask(int v) {
        this.resumeMode = v;
    }

    public void cancelCompletedResult$kotlinx_coroutines_core(Object object0, Throwable throwable0) {
    }

    public abstract Continuation getDelegate$kotlinx_coroutines_core();

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object object0) {
        CompletedExceptionally completedExceptionally0 = object0 instanceof CompletedExceptionally ? ((CompletedExceptionally)object0) : null;
        return completedExceptionally0 == null ? null : completedExceptionally0.cause;
    }

    public Object getSuccessfulResult$kotlinx_coroutines_core(Object object0) {
        return object0;
    }

    public final void handleFatalException$kotlinx_coroutines_core(Throwable throwable0, Throwable throwable1) {
        if(throwable0 == null && throwable1 == null) {
            return;
        }
        if(throwable0 != null && throwable1 != null) {
            ExceptionsKt.addSuppressed(throwable0, throwable1);
        }
        if(throwable0 == null) {
            throwable0 = throwable1;
        }
        Intrinsics.checkNotNull(throwable0);
        CoroutinesInternalError coroutinesInternalError0 = new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to \'handleFatalException\' method and report this incident to maintainers", throwable0);
        CoroutineExceptionHandlerKt.handleCoroutineException(this.getDelegate$kotlinx_coroutines_core().getContext(), coroutinesInternalError0);
    }

    @Override
    public final void run() {
        Unit unit1;
        Unit unit0;
        int v;
        TaskContext taskContext0;
        try {
            taskContext0 = this.taskContext;
            Continuation continuation0 = this.getDelegate$kotlinx_coroutines_core();
            Intrinsics.checkNotNull(continuation0, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            Continuation continuation1 = ((DispatchedContinuation)continuation0).continuation;
            CoroutineContext coroutineContext0 = continuation1.getContext();
            Object object0 = ThreadContextKt.updateThreadContext(coroutineContext0, ((DispatchedContinuation)continuation0).countOrElement);
            UndispatchedCoroutine undispatchedCoroutine0 = object0 == ThreadContextKt.NO_THREAD_ELEMENTS ? null : CoroutineContextKt.updateUndispatchedCompletion(continuation1, coroutineContext0, object0);
            v = FIN.finallyOpen$NT();
            CoroutineContext coroutineContext1 = continuation1.getContext();
            Object object1 = this.takeState$kotlinx_coroutines_core();
            Throwable throwable1 = this.getExceptionalResult$kotlinx_coroutines_core(object1);
            Job job0 = throwable1 != null || !DispatchedTaskKt.isCancellableMode(this.resumeMode) ? null : ((Job)coroutineContext1.get(Job.Key));
            if(job0 != null && !job0.isActive()) {
                CancellationException cancellationException0 = job0.getCancellationException();
                this.cancelCompletedResult$kotlinx_coroutines_core(object1, cancellationException0);
                continuation1.resumeWith(Result.constructor-impl(ResultKt.createFailure(cancellationException0)));
            }
            else if(throwable1 != null) {
                continuation1.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable1)));
            }
            else {
                continuation1.resumeWith(Result.constructor-impl(this.getSuccessfulResult$kotlinx_coroutines_core(object1)));
            }
            FIN.finallyCodeBegin$NT(v);
            if(undispatchedCoroutine0 == null) {
                ThreadContextKt.restoreThreadContext(coroutineContext0, object0);
            }
            else if(undispatchedCoroutine0.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(coroutineContext0, object0);
            }
        }
        catch(Throwable throwable0) {
            try {
                taskContext0.afterTask();
                unit0 = Unit.INSTANCE;
            }
            catch(Throwable throwable2) {
                unit0 = Result.constructor-impl(ResultKt.createFailure(throwable2));
            }
            this.handleFatalException$kotlinx_coroutines_core(throwable0, Result.exceptionOrNull-impl(unit0));
            return;
        }
        try {
            FIN.finallyCodeEnd$NT(v);
            taskContext0.afterTask();
            unit1 = Unit.INSTANCE;
        }
        catch(Throwable throwable3) {
            unit1 = Result.constructor-impl(ResultKt.createFailure(throwable3));
        }
        this.handleFatalException$kotlinx_coroutines_core(null, Result.exceptionOrNull-impl(unit1));
    }

    public abstract Object takeState$kotlinx_coroutines_core();
}

