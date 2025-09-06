package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&J\u0010\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/Job;", "complete", "", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface CompletableJob extends Job {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
        public static void cancel(CompletableJob completableJob0) {
            kotlinx.coroutines.Job.DefaultImpls.cancel(completableJob0);
        }

        public static Object fold(CompletableJob completableJob0, Object object0, Function2 function20) {
            return kotlinx.coroutines.Job.DefaultImpls.fold(completableJob0, object0, function20);
        }

        public static Element get(CompletableJob completableJob0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Job.DefaultImpls.get(completableJob0, coroutineContext$Key0);
        }

        public static CoroutineContext minusKey(CompletableJob completableJob0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Job.DefaultImpls.minusKey(completableJob0, coroutineContext$Key0);
        }

        public static CoroutineContext plus(CompletableJob completableJob0, CoroutineContext coroutineContext0) {
            return kotlinx.coroutines.Job.DefaultImpls.plus(completableJob0, coroutineContext0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator \'+\' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(CompletableJob completableJob0, Job job0) {
            return job0;
        }
    }

    boolean complete();

    boolean completeExceptionally(Throwable arg1);
}

