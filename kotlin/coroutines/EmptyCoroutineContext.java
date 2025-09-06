package kotlin.coroutines;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\bÇ\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J5\u0010\u0007\u001A\u0002H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001A\u0002H\b2\u0018\u0010\n\u001A\u0014\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\b0\u000BH\u0016¢\u0006\u0002\u0010\rJ(\u0010\u000E\u001A\u0004\u0018\u0001H\u000F\"\b\b\u0000\u0010\u000F*\u00020\f2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u0011H\u0096\u0002¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\u0014\u0010\u0015\u001A\u00020\u00012\n\u0010\u0010\u001A\u0006\u0012\u0002\b\u00030\u0011H\u0016J\u0011\u0010\u0016\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0096\u0002J\b\u0010\u0018\u001A\u00020\u0019H\u0002J\b\u0010\u001A\u001A\u00020\u001BH\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Lkotlin/coroutines/EmptyCoroutineContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "serialVersionUID", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "hashCode", "", "minusKey", "plus", "context", "readResolve", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class EmptyCoroutineContext implements Serializable, CoroutineContext {
    public static final EmptyCoroutineContext INSTANCE;
    private static final long serialVersionUID;

    static {
        EmptyCoroutineContext.INSTANCE = new EmptyCoroutineContext();
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public Object fold(Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "operation");
        return object0;
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public Element get(Key coroutineContext$Key0) {
        Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(Key coroutineContext$Key0) {
        Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
        return this;
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext0) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "context");
        return coroutineContext0;
    }

    private final Object readResolve() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override
    public String toString() {
        return "EmptyCoroutineContext";
    }
}

