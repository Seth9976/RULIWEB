package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u000F2\u00020\u0001:\u0001\u000FJ(\u0010\u0002\u001A\u0004\u0018\u0001H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0096\u0002¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\t0\bH&J\u0014\u0010\u000B\u001A\u00020\f2\n\u0010\u0004\u001A\u0006\u0012\u0002\b\u00030\u0005H\u0016J\u0014\u0010\r\u001A\u00020\u000E2\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\bH\u0016¨\u0006\u0010"}, d2 = {"Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/CoroutineContext$Element;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "interceptContinuation", "Lkotlin/coroutines/Continuation;", "T", "continuation", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "releaseInterceptedContinuation", "", "Key", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface ContinuationInterceptor extends Element {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static Object fold(ContinuationInterceptor continuationInterceptor0, Object object0, Function2 function20) {
            Intrinsics.checkNotNullParameter(function20, "operation");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.fold(continuationInterceptor0, object0, function20);
        }

        public static Element get(ContinuationInterceptor continuationInterceptor0, Key coroutineContext$Key0) {
            Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
            if(coroutineContext$Key0 instanceof AbstractCoroutineContextKey) {
                if(((AbstractCoroutineContextKey)coroutineContext$Key0).isSubKey$kotlin_stdlib(continuationInterceptor0.getKey())) {
                    Element coroutineContext$Element0 = ((AbstractCoroutineContextKey)coroutineContext$Key0).tryCast$kotlin_stdlib(continuationInterceptor0);
                    return coroutineContext$Element0 instanceof Element ? coroutineContext$Element0 : null;
                }
                return null;
            }
            if(ContinuationInterceptor.Key == coroutineContext$Key0) {
                Intrinsics.checkNotNull(continuationInterceptor0, "null cannot be cast to non-null type E of kotlin.coroutines.ContinuationInterceptor.get");
                return continuationInterceptor0;
            }
            return null;
        }

        public static CoroutineContext minusKey(ContinuationInterceptor continuationInterceptor0, Key coroutineContext$Key0) {
            Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
            if(coroutineContext$Key0 instanceof AbstractCoroutineContextKey) {
                return ((AbstractCoroutineContextKey)coroutineContext$Key0).isSubKey$kotlin_stdlib(continuationInterceptor0.getKey()) && ((AbstractCoroutineContextKey)coroutineContext$Key0).tryCast$kotlin_stdlib(continuationInterceptor0) != null ? EmptyCoroutineContext.INSTANCE : continuationInterceptor0;
            }
            return ContinuationInterceptor.Key == coroutineContext$Key0 ? EmptyCoroutineContext.INSTANCE : continuationInterceptor0;
        }

        public static CoroutineContext plus(ContinuationInterceptor continuationInterceptor0, CoroutineContext coroutineContext0) {
            Intrinsics.checkNotNullParameter(coroutineContext0, "context");
            return kotlin.coroutines.CoroutineContext.Element.DefaultImpls.plus(continuationInterceptor0, coroutineContext0);
        }

        public static void releaseInterceptedContinuation(ContinuationInterceptor continuationInterceptor0, Continuation continuation0) {
            Intrinsics.checkNotNullParameter(continuation0, "continuation");
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/coroutines/ContinuationInterceptor$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/ContinuationInterceptor;", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class kotlin.coroutines.ContinuationInterceptor.Key implements Key {
        static final kotlin.coroutines.ContinuationInterceptor.Key $$INSTANCE;

        static {
            kotlin.coroutines.ContinuationInterceptor.Key.$$INSTANCE = new kotlin.coroutines.ContinuationInterceptor.Key();
        }
    }

    public static final kotlin.coroutines.ContinuationInterceptor.Key Key;

    static {
        ContinuationInterceptor.Key = kotlin.coroutines.ContinuationInterceptor.Key.$$INSTANCE;
    }

    @Override  // kotlin.coroutines.CoroutineContext$Element
    Element get(Key arg1);

    Continuation interceptContinuation(Continuation arg1);

    @Override  // kotlin.coroutines.CoroutineContext$Element
    CoroutineContext minusKey(Key arg1);

    void releaseInterceptedContinuation(Continuation arg1);
}

