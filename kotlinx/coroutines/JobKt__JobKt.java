package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A\u0012\u0010\b\u001A\u00020\t2\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\u0005\u001A\u0019\u0010\u000B\u001A\u00020\u00052\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\u0005H\u0007\u00A2\u0006\u0002\b\b\u001A\f\u0010\f\u001A\u00020\r*\u00020\u0002H\u0007\u001A\u0018\u0010\f\u001A\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0007\u001A\u001C\u0010\f\u001A\u00020\r*\u00020\u00022\u0010\b\u0002\u0010\u000E\u001A\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011\u001A\u001E\u0010\f\u001A\u00020\r*\u00020\u00052\u0006\u0010\u0012\u001A\u00020\u00132\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\u000F\u001A\u0015\u0010\u0014\u001A\u00020\r*\u00020\u0005H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001A\f\u0010\u0016\u001A\u00020\r*\u00020\u0002H\u0007\u001A\u0018\u0010\u0016\u001A\u00020\r*\u00020\u00022\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0007\u001A\u001C\u0010\u0016\u001A\u00020\r*\u00020\u00022\u0010\b\u0002\u0010\u000E\u001A\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011\u001A\f\u0010\u0016\u001A\u00020\r*\u00020\u0005H\u0007\u001A\u0018\u0010\u0016\u001A\u00020\r*\u00020\u00052\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0007\u001A\u001C\u0010\u0016\u001A\u00020\r*\u00020\u00052\u0010\b\u0002\u0010\u000E\u001A\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011\u001A\u0014\u0010\u0017\u001A\u00020\u0018*\u00020\u00052\u0006\u0010\u0019\u001A\u00020\u0018H\u0000\u001A\n\u0010\u001A\u001A\u00020\r*\u00020\u0002\u001A\n\u0010\u001A\u001A\u00020\r*\u00020\u0005\u001A\u001B\u0010\u001B\u001A\u00020\u000F*\u0004\u0018\u00010\u000F2\u0006\u0010\u0004\u001A\u00020\u0005H\u0002\u00A2\u0006\u0002\b\u001C\"\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001A\u00020\u0005*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u001D"}, d2 = {"isActive", "", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)Z", "job", "Lkotlinx/coroutines/Job;", "getJob", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Job;", "Job", "Lkotlinx/coroutines/CompletableJob;", "parent", "Job0", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelChildren", "disposeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "handle", "ensureActive", "orCancellation", "orCancellation$JobKt__JobKt", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/JobKt")
final class JobKt__JobKt {
    public static final CompletableJob Job(Job job0) {
        return new JobImpl(job0);
    }

    public static CompletableJob Job$default(Job job0, int v, Object object0) {
        if((v & 1) != 0) {
            job0 = null;
        }
        return JobKt.Job(job0);
    }

    public static Job Job$default(Job job0, int v, Object object0) {
        if((v & 1) != 0) {
            job0 = null;
        }
        return JobKt.Job(job0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancel(CoroutineContext coroutineContext0) {
        JobKt.cancel(coroutineContext0, null);
    }

    public static final void cancel(CoroutineContext coroutineContext0, CancellationException cancellationException0) {
        Job job0 = (Job)coroutineContext0.get(Job.Key);
        if(job0 != null) {
            job0.cancel(cancellationException0);
        }
    }

    public static final void cancel(Job job0, String s, Throwable throwable0) {
        job0.cancel(ExceptionsKt.CancellationException(s, throwable0));
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final boolean cancel(CoroutineContext coroutineContext0, Throwable throwable0) {
        Element coroutineContext$Element0 = coroutineContext0.get(Job.Key);
        JobSupport jobSupport0 = coroutineContext$Element0 instanceof JobSupport ? ((JobSupport)coroutineContext$Element0) : null;
        if(jobSupport0 == null) {
            return false;
        }
        jobSupport0.cancelInternal(JobKt__JobKt.orCancellation$JobKt__JobKt(throwable0, jobSupport0));
        return true;
    }

    public static void cancel$default(CoroutineContext coroutineContext0, CancellationException cancellationException0, int v, Object object0) {
        if((v & 1) != 0) {
            cancellationException0 = null;
        }
        JobKt.cancel(coroutineContext0, cancellationException0);
    }

    public static void cancel$default(Job job0, String s, Throwable throwable0, int v, Object object0) {
        if((v & 2) != 0) {
            throwable0 = null;
        }
        JobKt.cancel(job0, s, throwable0);
    }

    public static boolean cancel$default(CoroutineContext coroutineContext0, Throwable throwable0, int v, Object object0) {
        if((v & 1) != 0) {
            throwable0 = null;
        }
        return JobKt__JobKt.cancel(coroutineContext0, throwable0);
    }

    public static final Object cancelAndJoin(Job job0, Continuation continuation0) {
        DefaultImpls.cancel$default(job0, null, 1, null);
        Object object0 = job0.join(continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(CoroutineContext coroutineContext0) {
        JobKt.cancelChildren(coroutineContext0, null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(CoroutineContext coroutineContext0, Throwable throwable0) {
        Job job0 = (Job)coroutineContext0.get(Job.Key);
        if(job0 != null) {
            for(Object object0: job0.getChildren()) {
                Job job1 = (Job)object0;
                JobSupport jobSupport0 = job1 instanceof JobSupport ? ((JobSupport)job1) : null;
                if(jobSupport0 != null) {
                    jobSupport0.cancelInternal(JobKt__JobKt.orCancellation$JobKt__JobKt(throwable0, job0));
                }
            }
        }
    }

    public static final void cancelChildren(CoroutineContext coroutineContext0, CancellationException cancellationException0) {
        Job job0 = (Job)coroutineContext0.get(Job.Key);
        if(job0 != null) {
            Sequence sequence0 = job0.getChildren();
            if(sequence0 != null) {
                for(Object object0: sequence0) {
                    ((Job)object0).cancel(cancellationException0);
                }
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(Job job0) {
        JobKt.cancelChildren(job0, null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(Job job0, Throwable throwable0) {
        for(Object object0: job0.getChildren()) {
            Job job1 = (Job)object0;
            JobSupport jobSupport0 = job1 instanceof JobSupport ? ((JobSupport)job1) : null;
            if(jobSupport0 != null) {
                jobSupport0.cancelInternal(JobKt__JobKt.orCancellation$JobKt__JobKt(throwable0, job0));
            }
        }
    }

    public static final void cancelChildren(Job job0, CancellationException cancellationException0) {
        for(Object object0: job0.getChildren()) {
            ((Job)object0).cancel(cancellationException0);
        }
    }

    public static void cancelChildren$default(CoroutineContext coroutineContext0, Throwable throwable0, int v, Object object0) {
        if((v & 1) != 0) {
            throwable0 = null;
        }
        JobKt__JobKt.cancelChildren(coroutineContext0, throwable0);
    }

    public static void cancelChildren$default(CoroutineContext coroutineContext0, CancellationException cancellationException0, int v, Object object0) {
        if((v & 1) != 0) {
            cancellationException0 = null;
        }
        JobKt.cancelChildren(coroutineContext0, cancellationException0);
    }

    public static void cancelChildren$default(Job job0, Throwable throwable0, int v, Object object0) {
        if((v & 1) != 0) {
            throwable0 = null;
        }
        JobKt__JobKt.cancelChildren(job0, throwable0);
    }

    public static void cancelChildren$default(Job job0, CancellationException cancellationException0, int v, Object object0) {
        if((v & 1) != 0) {
            cancellationException0 = null;
        }
        JobKt.cancelChildren(job0, cancellationException0);
    }

    public static final DisposableHandle disposeOnCompletion(Job job0, DisposableHandle disposableHandle0) {
        return job0.invokeOnCompletion(new DisposeOnCompletion(disposableHandle0));
    }

    public static final void ensureActive(CoroutineContext coroutineContext0) {
        Job job0 = (Job)coroutineContext0.get(Job.Key);
        if(job0 != null) {
            JobKt.ensureActive(job0);
        }
    }

    public static final void ensureActive(Job job0) {
        if(!job0.isActive()) {
            throw job0.getCancellationException();
        }
    }

    public static final Job getJob(CoroutineContext coroutineContext0) {
        Job job0 = (Job)coroutineContext0.get(Job.Key);
        if(job0 == null) {
            throw new IllegalStateException(("Current context doesn\'t contain Job in it: " + coroutineContext0).toString());
        }
        return job0;
    }

    public static final boolean isActive(CoroutineContext coroutineContext0) {
        Job job0 = (Job)coroutineContext0.get(Job.Key);
        return job0 == null ? true : job0.isActive();
    }

    private static final Throwable orCancellation$JobKt__JobKt(Throwable throwable0, Job job0) {
        return throwable0 == null ? new JobCancellationException("Job was cancelled", null, job0) : throwable0;
    }
}

