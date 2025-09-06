package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0015\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\tH&¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/CompletableDeferred;", "T", "Lkotlinx/coroutines/Deferred;", "complete", "", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface CompletableDeferred extends Deferred {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
        public static void cancel(CompletableDeferred completableDeferred0) {
            kotlinx.coroutines.Job.DefaultImpls.cancel(completableDeferred0);
        }

        public static Object fold(CompletableDeferred completableDeferred0, Object object0, Function2 function20) {
            return kotlinx.coroutines.Deferred.DefaultImpls.fold(completableDeferred0, object0, function20);
        }

        public static Element get(CompletableDeferred completableDeferred0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Deferred.DefaultImpls.get(completableDeferred0, coroutineContext$Key0);
        }

        public static CoroutineContext minusKey(CompletableDeferred completableDeferred0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Deferred.DefaultImpls.minusKey(completableDeferred0, coroutineContext$Key0);
        }

        public static CoroutineContext plus(CompletableDeferred completableDeferred0, CoroutineContext coroutineContext0) {
            return kotlinx.coroutines.Deferred.DefaultImpls.plus(completableDeferred0, coroutineContext0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator \'+\' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(CompletableDeferred completableDeferred0, Job job0) {
            return kotlinx.coroutines.Deferred.DefaultImpls.plus(completableDeferred0, job0);
        }
    }

    boolean complete(Object arg1);

    boolean completeExceptionally(Throwable arg1);
}

