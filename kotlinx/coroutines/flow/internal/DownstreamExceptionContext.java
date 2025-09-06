package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0001¢\u0006\u0002\u0010\u0005J6\u0010\u0006\u001A\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001A\u0002H\u00072\u0018\u0010\t\u001A\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u000B\u0012\u0004\u0012\u0002H\u00070\nH\u0096\u0001¢\u0006\u0002\u0010\fJ(\u0010\r\u001A\u0004\u0018\u0001H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000B2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0010H\u0096\u0003¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001A\u00020\u00012\n\u0010\u000F\u001A\u0006\u0012\u0002\b\u00030\u0010H\u0096\u0001J\u0011\u0010\u0013\u001A\u00020\u00012\u0006\u0010\u0014\u001A\u00020\u0001H\u0096\u0003R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/flow/internal/DownstreamExceptionContext;", "Lkotlin/coroutines/CoroutineContext;", "e", "", "originalContext", "(Ljava/lang/Throwable;Lkotlin/coroutines/CoroutineContext;)V", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "plus", "context", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DownstreamExceptionContext implements CoroutineContext {
    private final CoroutineContext $$delegate_0;
    public final Throwable e;

    public DownstreamExceptionContext(Throwable throwable0, CoroutineContext coroutineContext0) {
        this.e = throwable0;
        this.$$delegate_0 = coroutineContext0;
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public Object fold(Object object0, Function2 function20) {
        return this.$$delegate_0.fold(object0, function20);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public Element get(Key coroutineContext$Key0) {
        return this.$$delegate_0.get(coroutineContext$Key0);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(Key coroutineContext$Key0) {
        return this.$$delegate_0.minusKey(coroutineContext$Key0);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext0) {
        return this.$$delegate_0.plus(coroutineContext0);
    }
}

