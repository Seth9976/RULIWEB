package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement.DefaultImpls;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001B\u0012\u0006\u0010\u0003\u001A\u00028\u0000\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J(\u0010\f\u001A\u0004\u0018\u0001H\r\"\b\b\u0001\u0010\r*\u00020\u000E2\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\r0\bH\u0096\u0002¢\u0006\u0002\u0010\u000FJ\u0014\u0010\u0010\u001A\u00020\u00112\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\bH\u0016J\u001D\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u00112\u0006\u0010\u0015\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001A\u00020\u0018H\u0016J\u0015\u0010\u0019\u001A\u00028\u00002\u0006\u0010\u0014\u001A\u00020\u0011H\u0016¢\u0006\u0002\u0010\u001AR\u0018\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\bX\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001A\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000B¨\u0006\u001B"}, d2 = {"Lkotlinx/coroutines/internal/ThreadLocalElement;", "T", "Lkotlinx/coroutines/ThreadContextElement;", "value", "threadLocal", "Ljava/lang/ThreadLocal;", "(Ljava/lang/Object;Ljava/lang/ThreadLocal;)V", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "Ljava/lang/Object;", "get", "E", "Lkotlin/coroutines/CoroutineContext$Element;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "restoreThreadContext", "", "context", "oldState", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "toString", "", "updateThreadContext", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ThreadLocalElement implements ThreadContextElement {
    private final Key key;
    private final ThreadLocal threadLocal;
    private final Object value;

    public ThreadLocalElement(Object object0, ThreadLocal threadLocal0) {
        this.value = object0;
        this.threadLocal = threadLocal0;
        this.key = new ThreadLocalKey(threadLocal0);
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Object fold(Object object0, Function2 function20) {
        return DefaultImpls.fold(this, object0, function20);
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Element get(Key coroutineContext$Key0) {
        if(Intrinsics.areEqual(this.getKey(), coroutineContext$Key0)) {
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type E of kotlinx.coroutines.internal.ThreadLocalElement.get");
            return this;
        }
        return null;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Key getKey() {
        return this.key;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public CoroutineContext minusKey(Key coroutineContext$Key0) {
        return Intrinsics.areEqual(this.getKey(), coroutineContext$Key0) ? EmptyCoroutineContext.INSTANCE : this;
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext0) {
        return DefaultImpls.plus(this, coroutineContext0);
    }

    @Override  // kotlinx.coroutines.ThreadContextElement
    public void restoreThreadContext(CoroutineContext coroutineContext0, Object object0) {
        this.threadLocal.set(object0);
    }

    @Override
    public String toString() {
        return "ThreadLocal(value=" + this.value + ", threadLocal = " + this.threadLocal + ')';
    }

    @Override  // kotlinx.coroutines.ThreadContextElement
    public Object updateThreadContext(CoroutineContext coroutineContext0) {
        Object object0 = this.threadLocal.get();
        this.threadLocal.set(this.value);
        return object0;
    }
}

