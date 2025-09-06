package kotlinx.coroutines.future;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.Job.DefaultImpls;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0018\u0012\u0006\u0012\u0004\u0018\u0001H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003B\u001B\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\u0010\nJ!\u0010\u000B\u001A\u00020\u00052\b\u0010\f\u001A\u0004\u0018\u00018\u00002\b\u0010\r\u001A\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u000EJ\u0018\u0010\u000F\u001A\u00020\u00052\u0006\u0010\u0010\u001A\u00020\u00042\u0006\u0010\u0011\u001A\u00020\u0012H\u0014J\u0015\u0010\u0013\u001A\u00020\u00052\u0006\u0010\f\u001A\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0014R\u0014\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/future/CompletableFutureCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Ljava/util/function/BiFunction;", "", "", "context", "Lkotlin/coroutines/CoroutineContext;", "future", "Ljava/util/concurrent/CompletableFuture;", "(Lkotlin/coroutines/CoroutineContext;Ljava/util/concurrent/CompletableFuture;)V", "apply", "value", "exception", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "onCancelled", "cause", "handled", "", "onCompleted", "(Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class CompletableFutureCoroutine extends AbstractCoroutine implements BiFunction {
    private final CompletableFuture future;

    public CompletableFutureCoroutine(CoroutineContext coroutineContext0, CompletableFuture completableFuture0) {
        super(coroutineContext0, true, true);
        this.future = completableFuture0;
    }

    @Override
    public Object apply(Object object0, Object object1) {
        this.apply(object0, ((Throwable)object1));
        return Unit.INSTANCE;
    }

    public void apply(Object object0, Throwable throwable0) {
        DefaultImpls.cancel$default(this, null, 1, null);
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    protected void onCancelled(Throwable throwable0, boolean z) {
        Platform..ExternalSyntheticApiModelOutline0.m(this.future, throwable0);
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    protected void onCompleted(Object object0) {
        this.future.complete(object0);
    }
}

