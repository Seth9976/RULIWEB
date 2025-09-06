package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "acc", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
final class CoroutineContext.plus.1 extends Lambda implements Function2 {
    public static final CoroutineContext.plus.1 INSTANCE;

    static {
        CoroutineContext.plus.1.INSTANCE = new CoroutineContext.plus.1();
    }

    CoroutineContext.plus.1() {
        super(2);
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return this.invoke(((CoroutineContext)object0), ((Element)object1));
    }

    public final CoroutineContext invoke(CoroutineContext coroutineContext0, Element coroutineContext$Element0) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "acc");
        Intrinsics.checkNotNullParameter(coroutineContext$Element0, "element");
        CoroutineContext coroutineContext1 = coroutineContext0.minusKey(coroutineContext$Element0.getKey());
        if(coroutineContext1 == EmptyCoroutineContext.INSTANCE) {
            return coroutineContext$Element0;
        }
        ContinuationInterceptor continuationInterceptor0 = (ContinuationInterceptor)coroutineContext1.get(ContinuationInterceptor.Key);
        if(continuationInterceptor0 == null) {
            return new CombinedContext(coroutineContext1, coroutineContext$Element0);
        }
        CoroutineContext coroutineContext2 = coroutineContext1.minusKey(ContinuationInterceptor.Key);
        return coroutineContext2 == EmptyCoroutineContext.INSTANCE ? new CombinedContext(coroutineContext$Element0, continuationInterceptor0) : new CombinedContext(new CombinedContext(coroutineContext2, coroutineContext$Element0), continuationInterceptor0);
    }
}

