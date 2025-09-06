package kotlinx.coroutines.future;

import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0018\u0012\u0006\u0012\u0004\u0018\u0001H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0015\u0012\u000E\u0010\u0005\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J!\u0010\b\u001A\u00020\u00042\b\u0010\t\u001A\u0004\u0018\u00018\u00002\b\u0010\n\u001A\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u000BR\u001A\u0010\u0005\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00068\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/future/ContinuationHandler;", "T", "Ljava/util/function/BiFunction;", "", "", "cont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "apply", "result", "exception", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ContinuationHandler implements BiFunction {
    public volatile Continuation cont;

    public ContinuationHandler(Continuation continuation0) {
        this.cont = continuation0;
    }

    @Override
    public Object apply(Object object0, Object object1) {
        this.apply(object0, ((Throwable)object1));
        return Unit.INSTANCE;
    }

    public void apply(Object object0, Throwable throwable0) {
        Continuation continuation0 = this.cont;
        if(continuation0 == null) {
            return;
        }
        if(throwable0 == null) {
            continuation0.resumeWith(object0);
            return;
        }
        CompletionException completionException0 = Platform..ExternalSyntheticApiModelOutline0.m(throwable0) ? ((CompletionException)throwable0) : null;
        if(completionException0 != null) {
            Throwable throwable1 = Platform..ExternalSyntheticApiModelOutline0.m(completionException0);
            if(throwable1 != null) {
                throwable0 = throwable1;
            }
        }
        continuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
    }
}

