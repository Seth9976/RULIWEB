package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001A\u00020\u00048\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/YieldContext;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "()V", "dispatcherWasUnconfined", "", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class YieldContext extends AbstractCoroutineContextElement {
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/YieldContext$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/YieldContext;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Key implements kotlin.coroutines.CoroutineContext.Key {
        private Key() {
        }

        public Key(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Key Key;
    public boolean dispatcherWasUnconfined;

    static {
        YieldContext.Key = new Key(null);
    }

    public YieldContext() {
        super(YieldContext.Key);
    }
}

