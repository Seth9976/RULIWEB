package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0014\u0010\b\u001A\u00020\u00042\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\nH&J\u0010\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\'J\b\u0010\u000F\u001A\u00020\fH\'J1\u0010\u0010\u001A\u00020\f2\'\u0010\u0011\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f0\u0012j\u0002`\u0015H&J:\u0010\u0016\u001A\u00020\f2\u0006\u0010\u0017\u001A\u00028\u00002#\u0010\u0018\u001A\u001F\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012H\'¢\u0006\u0002\u0010\u0019J#\u0010\u001A\u001A\u0004\u0018\u00010\u000E2\u0006\u0010\u0017\u001A\u00028\u00002\n\b\u0002\u0010\u001B\u001A\u0004\u0018\u00010\u000EH\'¢\u0006\u0002\u0010\u001CJF\u0010\u001A\u001A\u0004\u0018\u00010\u000E2\u0006\u0010\u0017\u001A\u00028\u00002\b\u0010\u001B\u001A\u0004\u0018\u00010\u000E2#\u0010\u0018\u001A\u001F\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012H\'¢\u0006\u0002\u0010\u001DJ\u0012\u0010\u001E\u001A\u0004\u0018\u00010\u000E2\u0006\u0010\u001F\u001A\u00020\nH\'J\u0019\u0010 \u001A\u00020\f*\u00020!2\u0006\u0010\u0017\u001A\u00028\u0000H\'¢\u0006\u0002\u0010\"J\u0014\u0010#\u001A\u00020\f*\u00020!2\u0006\u0010\u001F\u001A\u00020\nH\'R\u0012\u0010\u0003\u001A\u00020\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0005R\u0012\u0010\u0006\u001A\u00020\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0005R\u0012\u0010\u0007\u001A\u00020\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\u0005¨\u0006$"}, d2 = {"Lkotlinx/coroutines/CancellableContinuation;", "T", "Lkotlin/coroutines/Continuation;", "isActive", "", "()Z", "isCancelled", "isCompleted", "cancel", "cause", "", "completeResume", "", "token", "", "initCancellability", "invokeOnCancellation", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "resume", "value", "onCancellation", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "tryResume", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "tryResumeWithException", "exception", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface CancellableContinuation extends Continuation {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static boolean cancel$default(CancellableContinuation cancellableContinuation0, Throwable throwable0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if((v & 1) != 0) {
                throwable0 = null;
            }
            return cancellableContinuation0.cancel(throwable0);
        }

        public static Object tryResume$default(CancellableContinuation cancellableContinuation0, Object object0, Object object1, int v, Object object2) {
            if(object2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryResume");
            }
            if((v & 2) != 0) {
                object1 = null;
            }
            return cancellableContinuation0.tryResume(object0, object1);
        }
    }

    boolean cancel(Throwable arg1);

    void completeResume(Object arg1);

    void initCancellability();

    void invokeOnCancellation(Function1 arg1);

    boolean isActive();

    boolean isCancelled();

    boolean isCompleted();

    void resume(Object arg1, Function1 arg2);

    void resumeUndispatched(CoroutineDispatcher arg1, Object arg2);

    void resumeUndispatchedWithException(CoroutineDispatcher arg1, Throwable arg2);

    Object tryResume(Object arg1, Object arg2);

    Object tryResume(Object arg1, Object arg2, Function1 arg3);

    Object tryResumeWithException(Throwable arg1);
}

