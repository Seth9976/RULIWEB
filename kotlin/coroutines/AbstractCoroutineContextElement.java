package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\'\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u0018\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)V", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractCoroutineContextElement implements Element {
    private final Key key;

    public AbstractCoroutineContextElement(Key coroutineContext$Key0) {
        Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
        super();
        this.key = coroutineContext$Key0;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Object fold(Object object0, Function2 function20) {
        return DefaultImpls.fold(this, object0, function20);
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Element get(Key coroutineContext$Key0) {
        return DefaultImpls.get(this, coroutineContext$Key0);
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Key getKey() {
        return this.key;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public CoroutineContext minusKey(Key coroutineContext$Key0) {
        return DefaultImpls.minusKey(this, coroutineContext$Key0);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext0) {
        return DefaultImpls.plus(this, coroutineContext0);
    }
}

