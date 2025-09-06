package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0006\b\'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u0002H\u00012\b\u0012\u0004\u0012\u0002H\u00030\u0004B8\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012#\u0010\u0006\u001A\u001F\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0007¢\u0006\u0002\u0010\u000BJ\u0019\u0010\r\u001A\u00020\u000E2\n\u0010\u000F\u001A\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0002\b\u0010J\u0019\u0010\u0011\u001A\u0004\u0018\u00018\u00012\u0006\u0010\n\u001A\u00020\u0002H\u0000¢\u0006\u0004\b\u0012\u0010\u0013R+\u0010\u0006\u001A\u001F\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001A\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlin/coroutines/AbstractCoroutineContextKey;", "B", "Lkotlin/coroutines/CoroutineContext$Element;", "E", "Lkotlin/coroutines/CoroutineContext$Key;", "baseKey", "safeCast", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "element", "(Lkotlin/coroutines/CoroutineContext$Key;Lkotlin/jvm/functions/Function1;)V", "topmostKey", "isSubKey", "", "key", "isSubKey$kotlin_stdlib", "tryCast", "tryCast$kotlin_stdlib", "(Lkotlin/coroutines/CoroutineContext$Element;)Lkotlin/coroutines/CoroutineContext$Element;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractCoroutineContextKey implements Key {
    private final Function1 safeCast;
    private final Key topmostKey;

    public AbstractCoroutineContextKey(Key coroutineContext$Key0, Function1 function10) {
        Intrinsics.checkNotNullParameter(coroutineContext$Key0, "baseKey");
        Intrinsics.checkNotNullParameter(function10, "safeCast");
        super();
        this.safeCast = function10;
        if(coroutineContext$Key0 instanceof AbstractCoroutineContextKey) {
            coroutineContext$Key0 = ((AbstractCoroutineContextKey)coroutineContext$Key0).topmostKey;
        }
        this.topmostKey = coroutineContext$Key0;
    }

    public final boolean isSubKey$kotlin_stdlib(Key coroutineContext$Key0) {
        Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
        return coroutineContext$Key0 == this || this.topmostKey == coroutineContext$Key0;
    }

    public final Element tryCast$kotlin_stdlib(Element coroutineContext$Element0) {
        Intrinsics.checkNotNullParameter(coroutineContext$Element0, "element");
        return (Element)this.safeCast.invoke(coroutineContext$Element0);
    }
}

