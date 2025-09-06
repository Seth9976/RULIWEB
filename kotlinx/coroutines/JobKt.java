package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"kotlinx/coroutines/JobKt__FutureKt", "kotlinx/coroutines/JobKt__JobKt"}, k = 4, mv = {1, 8, 0}, xi = 0x30)
public final class JobKt {
    public static final CompletableJob Job(Job job0) {
        return JobKt__JobKt.Job(job0);
    }

    public static Job Job$default(Job job0, int v, Object object0) {
        return JobKt__JobKt.Job$default(job0, v, object0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancel(CoroutineContext coroutineContext0) {
        JobKt__JobKt.cancel(coroutineContext0);
    }

    public static final void cancel(CoroutineContext coroutineContext0, CancellationException cancellationException0) {
        JobKt__JobKt.cancel(coroutineContext0, cancellationException0);
    }

    public static final void cancel(Job job0, String s, Throwable throwable0) {
        JobKt__JobKt.cancel(job0, s, throwable0);
    }

    public static void cancel$default(Job job0, String s, Throwable throwable0, int v, Object object0) {
        JobKt__JobKt.cancel$default(job0, s, throwable0, v, object0);
    }

    public static boolean cancel$default(CoroutineContext coroutineContext0, Throwable throwable0, int v, Object object0) {
        return JobKt__JobKt.cancel$default(coroutineContext0, throwable0, v, object0);
    }

    public static final Object cancelAndJoin(Job job0, Continuation continuation0) {
        return JobKt__JobKt.cancelAndJoin(job0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(CoroutineContext coroutineContext0) {
        JobKt__JobKt.cancelChildren(coroutineContext0);
    }

    public static final void cancelChildren(CoroutineContext coroutineContext0, CancellationException cancellationException0) {
        JobKt__JobKt.cancelChildren(coroutineContext0, cancellationException0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(Job job0) {
        JobKt__JobKt.cancelChildren(job0);
    }

    public static final void cancelChildren(Job job0, CancellationException cancellationException0) {
        JobKt__JobKt.cancelChildren(job0, cancellationException0);
    }

    public static void cancelChildren$default(CoroutineContext coroutineContext0, Throwable throwable0, int v, Object object0) {
        JobKt__JobKt.cancelChildren$default(coroutineContext0, throwable0, v, object0);
    }

    public static void cancelChildren$default(CoroutineContext coroutineContext0, CancellationException cancellationException0, int v, Object object0) {
        JobKt__JobKt.cancelChildren$default(coroutineContext0, cancellationException0, v, object0);
    }

    public static void cancelChildren$default(Job job0, Throwable throwable0, int v, Object object0) {
        JobKt__JobKt.cancelChildren$default(job0, throwable0, v, object0);
    }

    public static void cancelChildren$default(Job job0, CancellationException cancellationException0, int v, Object object0) {
        JobKt__JobKt.cancelChildren$default(job0, cancellationException0, v, object0);
    }

    public static final void cancelFutureOnCancellation(CancellableContinuation cancellableContinuation0, Future future0) {
        JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation0, future0);
    }

    public static final DisposableHandle cancelFutureOnCompletion(Job job0, Future future0) {
        return JobKt__FutureKt.cancelFutureOnCompletion(job0, future0);
    }

    public static final DisposableHandle disposeOnCompletion(Job job0, DisposableHandle disposableHandle0) {
        return JobKt__JobKt.disposeOnCompletion(job0, disposableHandle0);
    }

    public static final void ensureActive(CoroutineContext coroutineContext0) {
        JobKt__JobKt.ensureActive(coroutineContext0);
    }

    public static final void ensureActive(Job job0) {
        JobKt__JobKt.ensureActive(job0);
    }

    public static final Job getJob(CoroutineContext coroutineContext0) {
        return JobKt__JobKt.getJob(coroutineContext0);
    }

    public static final boolean isActive(CoroutineContext coroutineContext0) {
        return JobKt__JobKt.isActive(coroutineContext0);
    }
}

