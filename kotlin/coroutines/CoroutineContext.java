package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001:\u0002\u0011\u0012J5\u0010\u0002\u001A\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001A\u0002H\u00032\u0018\u0010\u0005\u001A\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001A\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000E\u001A\u00020\u00002\n\u0010\u000B\u001A\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000F\u001A\u00020\u00002\u0006\u0010\u0010\u001A\u00020\u0000H\u0096\u0002¨\u0006\u0013"}, d2 = {"Lkotlin/coroutines/CoroutineContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "plus", "context", "Element", "Key", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface CoroutineContext {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static CoroutineContext plus(CoroutineContext coroutineContext0, CoroutineContext coroutineContext1) {
            Intrinsics.checkNotNullParameter(coroutineContext1, "context");
            return coroutineContext1 == EmptyCoroutineContext.INSTANCE ? coroutineContext0 : ((CoroutineContext)coroutineContext1.fold(coroutineContext0, CoroutineContext.plus.1.INSTANCE));
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001A\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001A\u0002H\u00072\u0018\u0010\t\u001A\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000BJ(\u0010\f\u001A\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00002\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0096\u0002¢\u0006\u0002\u0010\u000EJ\u0014\u0010\u000F\u001A\u00020\u00012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0016\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public interface Element extends CoroutineContext {
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
        public static final class kotlin.coroutines.CoroutineContext.Element.DefaultImpls {
            public static Object fold(Element coroutineContext$Element0, Object object0, Function2 function20) {
                Intrinsics.checkNotNullParameter(function20, "operation");
                return function20.invoke(object0, coroutineContext$Element0);
            }

            public static Element get(Element coroutineContext$Element0, Key coroutineContext$Key0) {
                Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
                if(Intrinsics.areEqual(coroutineContext$Element0.getKey(), coroutineContext$Key0)) {
                    Intrinsics.checkNotNull(coroutineContext$Element0, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                    return coroutineContext$Element0;
                }
                return null;
            }

            public static CoroutineContext minusKey(Element coroutineContext$Element0, Key coroutineContext$Key0) {
                Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
                return Intrinsics.areEqual(coroutineContext$Element0.getKey(), coroutineContext$Key0) ? EmptyCoroutineContext.INSTANCE : coroutineContext$Element0;
            }

            public static CoroutineContext plus(Element coroutineContext$Element0, CoroutineContext coroutineContext0) {
                Intrinsics.checkNotNullParameter(coroutineContext0, "context");
                return DefaultImpls.plus(coroutineContext$Element0, coroutineContext0);
            }
        }

        @Override  // kotlin.coroutines.CoroutineContext
        Object fold(Object arg1, Function2 arg2);

        @Override  // kotlin.coroutines.CoroutineContext
        Element get(Key arg1);

        Key getKey();

        @Override  // kotlin.coroutines.CoroutineContext
        CoroutineContext minusKey(Key arg1);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/coroutines/CoroutineContext$Key;", "E", "Lkotlin/coroutines/CoroutineContext$Element;", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public interface Key {
    }

    Object fold(Object arg1, Function2 arg2);

    Element get(Key arg1);

    CoroutineContext minusKey(Key arg1);

    CoroutineContext plus(CoroutineContext arg1);
}

