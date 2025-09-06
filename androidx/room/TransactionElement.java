package androidx.room;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext.Element.DefaultImpls;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001A\u00020\u000EJ\u0006\u0010\u000F\u001A\u00020\u000ER\u001A\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00000\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\bR\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u0011"}, d2 = {"Landroidx/room/TransactionElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "transactionDispatcher", "Lkotlin/coroutines/ContinuationInterceptor;", "(Lkotlin/coroutines/ContinuationInterceptor;)V", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "referenceCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "getTransactionDispatcher$room_ktx_release", "()Lkotlin/coroutines/ContinuationInterceptor;", "acquire", "", "release", "Key", "room-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class TransactionElement implements Element {
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/room/TransactionElement$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Landroidx/room/TransactionElement;", "()V", "room-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Key implements kotlin.coroutines.CoroutineContext.Key {
        private Key() {
        }

        public Key(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Key Key;
    private final AtomicInteger referenceCount;
    private final ContinuationInterceptor transactionDispatcher;

    static {
        TransactionElement.Key = new Key(null);
    }

    public TransactionElement(ContinuationInterceptor continuationInterceptor0) {
        this.transactionDispatcher = continuationInterceptor0;
        this.referenceCount = new AtomicInteger(0);
    }

    public final void acquire() {
        this.referenceCount.incrementAndGet();
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Object fold(Object object0, Function2 function20) {
        return DefaultImpls.fold(this, object0, function20);
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public Element get(kotlin.coroutines.CoroutineContext.Key coroutineContext$Key0) {
        return DefaultImpls.get(this, coroutineContext$Key0);
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public kotlin.coroutines.CoroutineContext.Key getKey() {
        return TransactionElement.Key;
    }

    public final ContinuationInterceptor getTransactionDispatcher$room_ktx_release() {
        return this.transactionDispatcher;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    public CoroutineContext minusKey(kotlin.coroutines.CoroutineContext.Key coroutineContext$Key0) {
        return DefaultImpls.minusKey(this, coroutineContext$Key0);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext0) {
        return DefaultImpls.plus(this, coroutineContext0);
    }

    public final void release() {
        if(this.referenceCount.decrementAndGet() < 0) {
            throw new IllegalStateException("Transaction was never started or was already released.");
        }
    }
}

