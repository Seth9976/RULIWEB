package kotlinx.coroutines;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.CoroutineExceptionHandlerImpl_commonKt;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u001A%\u0010\u0000\u001A\u00020\u00012\u001A\b\u0004\u0010\u0002\u001A\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u0086\b\u001A\u0018\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u00042\u0006\u0010\t\u001A\u00020\u0005H\u0007\u001A\u0018\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\u00052\u0006\u0010\f\u001A\u00020\u0005H\u0000¨\u0006\r"}, d2 = {"CoroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "", "", "handleCoroutineException", "context", "exception", "handlerException", "originalException", "thrownException", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutineExceptionHandlerKt {
    public static final CoroutineExceptionHandler CoroutineExceptionHandler(Function2 function20) {
        return new CoroutineExceptionHandler(CoroutineExceptionHandler.Key) {
            @Override  // kotlinx.coroutines.CoroutineExceptionHandler
            public void handleException(CoroutineContext coroutineContext0, Throwable throwable0) {
                function20.invoke(coroutineContext0, throwable0);
            }
        };
    }

    public static final void handleCoroutineException(CoroutineContext coroutineContext0, Throwable throwable0) {
        try {
            CoroutineExceptionHandler coroutineExceptionHandler0 = (CoroutineExceptionHandler)coroutineContext0.get(CoroutineExceptionHandler.Key);
            if(coroutineExceptionHandler0 != null) {
                coroutineExceptionHandler0.handleException(coroutineContext0, throwable0);
                return;
            }
        }
        catch(Throwable throwable1) {
            CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(coroutineContext0, CoroutineExceptionHandlerKt.handlerException(throwable0, throwable1));
            return;
        }
        CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(coroutineContext0, throwable0);
    }

    public static final Throwable handlerException(Throwable throwable0, Throwable throwable1) {
        if(throwable0 == throwable1) {
            return throwable0;
        }
        RuntimeException runtimeException0 = new RuntimeException("Exception while trying to handle coroutine exception", throwable1);
        ExceptionsKt.addSuppressed(runtimeException0, throwable0);
        return runtimeException0;
    }
}

