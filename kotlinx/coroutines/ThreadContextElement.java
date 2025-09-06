package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001D\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00028\u0000H&¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001A\u00028\u00002\u0006\u0010\u0005\u001A\u00020\u0006H&¢\u0006\u0002\u0010\n¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/ThreadContextElement;", "S", "Lkotlin/coroutines/CoroutineContext$Element;", "restoreThreadContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "oldState", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "updateThreadContext", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface ThreadContextElement extends Element {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static Object fold(ThreadContextElement threadContextElement0, Object object0, Function2 function20) {
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.fold(threadContextElement0, object0, function20);
        }

        public static Element get(ThreadContextElement threadContextElement0, Key coroutineContext$Key0) {
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.get(threadContextElement0, coroutineContext$Key0);
        }

        public static CoroutineContext minusKey(ThreadContextElement threadContextElement0, Key coroutineContext$Key0) {
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.minusKey(threadContextElement0, coroutineContext$Key0);
        }

        public static CoroutineContext plus(ThreadContextElement threadContextElement0, CoroutineContext coroutineContext0) {
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.plus(threadContextElement0, coroutineContext0);
        }
    }

    void restoreThreadContext(CoroutineContext arg1, Object arg2);

    Object updateThreadContext(CoroutineContext arg1);
}

