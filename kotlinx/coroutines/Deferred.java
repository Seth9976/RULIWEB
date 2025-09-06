package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0011\u0010\u0007\u001A\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\bJ\r\u0010\t\u001A\u00028\u0000H\'¢\u0006\u0002\u0010\nJ\n\u0010\u000B\u001A\u0004\u0018\u00010\fH\'R\u0018\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/Deferred;", "T", "Lkotlinx/coroutines/Job;", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "getCompletionExceptionOrNull", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface Deferred extends Job {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static Object fold(Deferred deferred0, Object object0, Function2 function20) {
            return kotlinx.coroutines.Job.DefaultImpls.fold(deferred0, object0, function20);
        }

        public static Element get(Deferred deferred0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Job.DefaultImpls.get(deferred0, coroutineContext$Key0);
        }

        public static CoroutineContext minusKey(Deferred deferred0, Key coroutineContext$Key0) {
            return kotlinx.coroutines.Job.DefaultImpls.minusKey(deferred0, coroutineContext$Key0);
        }

        public static CoroutineContext plus(Deferred deferred0, CoroutineContext coroutineContext0) {
            return kotlinx.coroutines.Job.DefaultImpls.plus(deferred0, coroutineContext0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator \'+\' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(Deferred deferred0, Job job0) {
            return job0;
        }
    }

    Object await(Continuation arg1);

    Object getCompleted();

    Throwable getCompletionExceptionOrNull();

    SelectClause1 getOnAwait();
}

