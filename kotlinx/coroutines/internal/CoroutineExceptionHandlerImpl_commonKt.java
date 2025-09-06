package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"handleUncaughtCoroutineException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutineExceptionHandlerImpl_commonKt {
    public static final void handleUncaughtCoroutineException(CoroutineContext coroutineContext0, Throwable throwable0) {
        for(Object object0: CoroutineExceptionHandlerImplKt.getPlatformExceptionHandlers()) {
            CoroutineExceptionHandler coroutineExceptionHandler0 = (CoroutineExceptionHandler)object0;
            try {
                coroutineExceptionHandler0.handleException(coroutineContext0, throwable0);
            }
            catch(ExceptionSuccessfullyProcessed unused_ex) {
                return;
            }
            catch(Throwable throwable1) {
                CoroutineExceptionHandlerImplKt.propagateExceptionFinalResort(CoroutineExceptionHandlerKt.handlerException(throwable0, throwable1));
            }
        }
        try {
            ExceptionsKt.addSuppressed(throwable0, new DiagnosticCoroutineContextException(coroutineContext0));
        }
        catch(Throwable unused_ex) {
        }
        CoroutineExceptionHandlerImplKt.propagateExceptionFinalResort(throwable0);
    }
}

