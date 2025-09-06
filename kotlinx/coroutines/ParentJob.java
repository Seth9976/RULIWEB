package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\f\u0010\u0002\u001A\u00060\u0003j\u0002`\u0004H\'¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/Job;", "getChildJobCancellationCause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface ParentJob extends Job {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
        public static void cancel(ParentJob parentJob0) {
            kotlinx.coroutines.Job.DefaultImpls.cancel(parentJob0);
        }

        public static Object fold(ParentJob parentJob0, Object object0, Function2 function20) {
            return kotlinx.coroutines.Job.DefaultImpls.fold(parentJob0, object0, function20);
        }

        public static Element get(ParentJob parentJob0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Job.DefaultImpls.get(parentJob0, coroutineContext$Key0);
        }

        public static CoroutineContext minusKey(ParentJob parentJob0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Job.DefaultImpls.minusKey(parentJob0, coroutineContext$Key0);
        }

        public static CoroutineContext plus(ParentJob parentJob0, CoroutineContext coroutineContext0) {
            return kotlinx.coroutines.Job.DefaultImpls.plus(parentJob0, coroutineContext0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator \'+\' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(ParentJob parentJob0, Job job0) {
            return job0;
        }
    }

    CancellationException getChildJobCancellationCause();
}

