package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build.VERSION;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.core.util.Consumer;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker.Result;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerExceptionInfo;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.WorkerExceptionUtilsKt;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000E\u0010\u0007\u001A\u00020\bH\u0096@¢\u0006\u0002\u0010\tJ&\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u0011J\u000E\u0010\u0012\u001A\u00020\bH\u0082@¢\u0006\u0002\u0010\tR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runWorker", "delegate", "Landroidx/work/ListenableWorker;", "workConstraintsTracker", "Landroidx/work/impl/constraints/WorkConstraintsTracker;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "(Landroidx/work/ListenableWorker;Landroidx/work/impl/constraints/WorkConstraintsTracker;Landroidx/work/impl/model/WorkSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupAndRunConstraintTrackingWork", "ConstraintUnsatisfiedException", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ConstraintTrackingWorker extends CoroutineWorker {
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker$ConstraintUnsatisfiedException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "stopReason", "", "(I)V", "getStopReason", "()I", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class ConstraintUnsatisfiedException extends CancellationException {
        private final int stopReason;

        public ConstraintUnsatisfiedException(int v) {
            this.stopReason = v;
        }

        public final int getStopReason() {
            return this.stopReason;
        }
    }

    private final WorkerParameters workerParameters;

    public ConstraintTrackingWorker(Context context0, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters0, "workerParameters");
        super(context0, workerParameters0);
        this.workerParameters = workerParameters0;
    }

    @Override  // androidx.work.CoroutineWorker
    public Object doWork(Continuation continuation0) {
        Executor executor0 = this.getBackgroundExecutor();
        Intrinsics.checkNotNullExpressionValue(executor0, "backgroundExecutor");
        return BuildersKt.withContext(ExecutorsKt.from(executor0), new Function2(null) {
            int label;

            {
                ConstraintTrackingWorker.this = constraintTrackingWorker0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.work.impl.workers.ConstraintTrackingWorker.doWork.2(ConstraintTrackingWorker.this, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.work.impl.workers.ConstraintTrackingWorker.doWork.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        Object object2 = ConstraintTrackingWorker.this.setupAndRunConstraintTrackingWork(this);
                        return object2 == object1 ? object1 : object2;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return object0;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }, continuation0);
    }

    private final Object runWorker(ListenableWorker listenableWorker0, WorkConstraintsTracker workConstraintsTracker0, WorkSpec workSpec0, Continuation continuation0) {
        androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.1 constraintTrackingWorker$runWorker$10;
        if(continuation0 instanceof androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.1) {
            constraintTrackingWorker$runWorker$10 = (androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.1)continuation0;
            if((constraintTrackingWorker$runWorker$10.label & 0x80000000) == 0) {
                constraintTrackingWorker$runWorker$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.runWorker(null, null, null, this);
                    }
                };
            }
            else {
                constraintTrackingWorker$runWorker$10.label ^= 0x80000000;
            }
        }
        else {
            constraintTrackingWorker$runWorker$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.runWorker(null, null, null, this);
                }
            };
        }
        Object object0 = constraintTrackingWorker$runWorker$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(constraintTrackingWorker$runWorker$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.2 constraintTrackingWorker$runWorker$20 = new Function2(workConstraintsTracker0, workSpec0, null) {
                    final ListenableWorker $delegate;
                    final WorkConstraintsTracker $workConstraintsTracker;
                    final WorkSpec $workSpec;
                    private Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;

                    {
                        this.$delegate = listenableWorker0;
                        this.$workConstraintsTracker = workConstraintsTracker0;
                        this.$workSpec = workSpec0;
                        super(2, continuation0);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object object0, Continuation continuation0) {
                        androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.2 constraintTrackingWorker$runWorker$20 = new androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.2(this.$delegate, this.$workConstraintsTracker, this.$workSpec, continuation0);
                        constraintTrackingWorker$runWorker$20.L$0 = object0;
                        return constraintTrackingWorker$runWorker$20;
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                    }

                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                        return ((androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        Result listenableWorker$Result0;
                        Throwable throwable2;
                        ListenableFuture listenableFuture1;
                        Job job1;
                        AtomicInteger atomicInteger1;
                        Object object2;
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                                AtomicInteger atomicInteger0 = new AtomicInteger(0xFFFFFF00);
                                ListenableFuture listenableFuture0 = this.$delegate.startWork();
                                Intrinsics.checkNotNullExpressionValue(listenableFuture0, "delegate.startWork()");
                                Job job0 = BuildersKt.launch$default(coroutineScope0, null, null, new Function2(this.$workSpec, atomicInteger0, listenableFuture0, null) {
                                    final AtomicInteger $atomicReason;
                                    final ListenableFuture $future;
                                    final WorkConstraintsTracker $workConstraintsTracker;
                                    final WorkSpec $workSpec;
                                    int label;

                                    {
                                        this.$workConstraintsTracker = workConstraintsTracker0;
                                        this.$workSpec = workSpec0;
                                        this.$atomicReason = atomicInteger0;
                                        this.$future = listenableFuture0;
                                        super(2, continuation0);
                                    }

                                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation create(Object object0, Continuation continuation0) {
                                        return new androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.2.constraintTrackingJob.1(this.$workConstraintsTracker, this.$workSpec, this.$atomicReason, this.$future, continuation0);
                                    }

                                    @Override  // kotlin.jvm.functions.Function2
                                    public Object invoke(Object object0, Object object1) {
                                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                    }

                                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                        return ((androidx.work.impl.workers.ConstraintTrackingWorker.runWorker.2.constraintTrackingJob.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object object0) {
                                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch(this.label) {
                                            case 0: {
                                                ResultKt.throwOnFailure(object0);
                                                this.label = 1;
                                                object0 = ConstraintTrackingWorkerKt.access$awaitConstraintsNotMet(this.$workConstraintsTracker, this.$workSpec, this);
                                                if(object0 == object1) {
                                                    return object1;
                                                }
                                                break;
                                            }
                                            case 1: {
                                                ResultKt.throwOnFailure(object0);
                                                break;
                                            }
                                            default: {
                                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                            }
                                        }
                                        this.$atomicReason.set(((Number)object0).intValue());
                                        this.$future.cancel(true);
                                        return Unit.INSTANCE;
                                    }
                                }, 3, null);
                                try {
                                    this.L$0 = atomicInteger0;
                                    this.L$1 = listenableFuture0;
                                    this.L$2 = job0;
                                    this.label = 1;
                                    object2 = ListenableFutureKt.await(listenableFuture0, this);
                                }
                                catch(CancellationException cancellationException0) {
                                    try {
                                        atomicInteger1 = atomicInteger0;
                                        job1 = job0;
                                        listenableFuture1 = listenableFuture0;
                                        break;
                                    }
                                    catch(Throwable throwable1) {
                                        goto label_48;
                                    }
                                }
                                catch(Throwable throwable0) {
                                    throwable2 = throwable0;
                                    job1 = job0;
                                    goto label_45;
                                }
                                if(object2 == object1) {
                                    return object1;
                                }
                                listenableFuture1 = listenableFuture0;
                                object0 = object2;
                                atomicInteger1 = atomicInteger0;
                                job1 = job0;
                                goto label_35;
                            }
                            case 1: {
                                job1 = (Job)this.L$2;
                                listenableFuture1 = (ListenableFuture)this.L$1;
                                atomicInteger1 = (AtomicInteger)this.L$0;
                                try {
                                    ResultKt.throwOnFailure(object0);
                                label_35:
                                    listenableWorker$Result0 = (Result)object0;
                                    goto label_50;
                                }
                                catch(CancellationException cancellationException0) {
                                    break;
                                }
                                catch(Throwable throwable3) {
                                    goto label_44;
                                }
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        try {
                            Logger.get().debug("WM-ConstraintTrkngWrkr", "Delegated worker " + this.$delegate.getClass() + " was cancelled", cancellationException0);
                            boolean z = atomicInteger1.get() != 0xFFFFFF00;
                            if(!listenableFuture1.isCancelled() || !z) {
                                throw cancellationException0;
                            }
                            throw new ConstraintUnsatisfiedException(atomicInteger1.get());
                        label_44:
                            throwable2 = throwable3;
                        label_45:
                            Logger.get().debug("WM-ConstraintTrkngWrkr", "Delegated worker " + this.$delegate.getClass() + " threw exception in startWork.", throwable2);
                            throw throwable2;
                        }
                        catch(Throwable throwable1) {
                        label_48:
                            DefaultImpls.cancel$default(job1, null, 1, null);
                            throw throwable1;
                        }
                    label_50:
                        DefaultImpls.cancel$default(job1, null, 1, null);
                        return listenableWorker$Result0;
                    }
                };
                constraintTrackingWorker$runWorker$10.label = 1;
                object0 = CoroutineScopeKt.coroutineScope(constraintTrackingWorker$runWorker$20, constraintTrackingWorker$runWorker$10);
                if(object0 == object1) {
                    return object1;
                }
                break;
            }
            case 1: {
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        Intrinsics.checkNotNullExpressionValue(object0, "delegate: ListenableWork….cancel()\n        }\n    }");
        return object0;
    }

    private final Object setupAndRunConstraintTrackingWork(Continuation continuation0) {
        ConstraintTrackingWorker constraintTrackingWorker0;
        ListenableWorker listenableWorker0;
        androidx.work.impl.workers.ConstraintTrackingWorker.setupAndRunConstraintTrackingWork.1 constraintTrackingWorker$setupAndRunConstraintTrackingWork$10;
        if(continuation0 instanceof androidx.work.impl.workers.ConstraintTrackingWorker.setupAndRunConstraintTrackingWork.1) {
            constraintTrackingWorker$setupAndRunConstraintTrackingWork$10 = (androidx.work.impl.workers.ConstraintTrackingWorker.setupAndRunConstraintTrackingWork.1)continuation0;
            if((constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.label & 0x80000000) == 0) {
                constraintTrackingWorker$setupAndRunConstraintTrackingWork$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.setupAndRunConstraintTrackingWork(this);
                    }
                };
            }
            else {
                constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.label ^= 0x80000000;
            }
        }
        else {
            constraintTrackingWorker$setupAndRunConstraintTrackingWork$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.setupAndRunConstraintTrackingWork(this);
                }
            };
        }
        Object object0 = constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                String s = this.getInputData().getString("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
                if(s != null && s.length() != 0) {
                    WorkManagerImpl workManagerImpl0 = WorkManagerImpl.getInstance(this.getApplicationContext());
                    Intrinsics.checkNotNullExpressionValue(workManagerImpl0, "getInstance(applicationContext)");
                    WorkSpecDao workSpecDao0 = workManagerImpl0.getWorkDatabase().workSpecDao();
                    String s1 = this.getId().toString();
                    Intrinsics.checkNotNullExpressionValue(s1, "id.toString()");
                    WorkSpec workSpec0 = workSpecDao0.getWorkSpec(s1);
                    if(workSpec0 == null) {
                        Result listenableWorker$Result0 = Result.failure();
                        Intrinsics.checkNotNullExpressionValue(listenableWorker$Result0, "failure()");
                        return listenableWorker$Result0;
                    }
                    Trackers trackers0 = workManagerImpl0.getTrackers();
                    Intrinsics.checkNotNullExpressionValue(trackers0, "workManagerImpl.trackers");
                    WorkConstraintsTracker workConstraintsTracker0 = new WorkConstraintsTracker(trackers0);
                    if(!workConstraintsTracker0.areAllConstraintsMet(workSpec0)) {
                        Logger.get().debug("WM-ConstraintTrkngWrkr", "Constraints not met for delegate " + s + ". Requesting retry.");
                        Result listenableWorker$Result1 = Result.retry();
                        Intrinsics.checkNotNullExpressionValue(listenableWorker$Result1, "retry()");
                        return listenableWorker$Result1;
                    }
                    Logger.get().debug("WM-ConstraintTrkngWrkr", "Constraints met for delegate " + s);
                    try {
                        WorkerFactory workerFactory0 = this.getWorkerFactory();
                        Context context0 = this.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(context0, "applicationContext");
                        listenableWorker0 = workerFactory0.createWorkerWithDefaultFallback(context0, s, this.workerParameters);
                    }
                    catch(Throwable throwable0) {
                        Logger.get().debug("WM-ConstraintTrkngWrkr", "No worker to delegate to.");
                        Consumer consumer0 = workManagerImpl0.getConfiguration().getWorkerInitializationExceptionHandler();
                        if(consumer0 != null) {
                            WorkerExceptionUtilsKt.safeAccept(consumer0, new WorkerExceptionInfo(s, this.workerParameters, throwable0), "WM-ConstraintTrkngWrkr");
                        }
                        Result listenableWorker$Result2 = Result.failure();
                        Intrinsics.checkNotNullExpressionValue(listenableWorker$Result2, "failure()");
                        return listenableWorker$Result2;
                    }
                    Executor executor0 = this.workerParameters.getTaskExecutor().getMainThreadExecutor();
                    Intrinsics.checkNotNullExpressionValue(executor0, "workerParameters.taskExecutor.mainThreadExecutor");
                    try {
                        CoroutineDispatcher coroutineDispatcher0 = ExecutorsKt.from(executor0);
                        androidx.work.impl.workers.ConstraintTrackingWorker.setupAndRunConstraintTrackingWork.5 constraintTrackingWorker$setupAndRunConstraintTrackingWork$50 = new Function2(listenableWorker0, workConstraintsTracker0, workSpec0, null) {
                            final ListenableWorker $delegate;
                            final WorkConstraintsTracker $workConstraintsTracker;
                            final WorkSpec $workSpec;
                            int label;

                            {
                                ConstraintTrackingWorker.this = constraintTrackingWorker0;
                                this.$delegate = listenableWorker0;
                                this.$workConstraintsTracker = workConstraintsTracker0;
                                this.$workSpec = workSpec0;
                                super(2, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object object0, Continuation continuation0) {
                                return new androidx.work.impl.workers.ConstraintTrackingWorker.setupAndRunConstraintTrackingWork.5(ConstraintTrackingWorker.this, this.$delegate, this.$workConstraintsTracker, this.$workSpec, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                            }

                            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                return ((androidx.work.impl.workers.ConstraintTrackingWorker.setupAndRunConstraintTrackingWork.5)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        this.label = 1;
                                        Object object2 = ConstraintTrackingWorker.this.runWorker(this.$delegate, this.$workConstraintsTracker, this.$workSpec, this);
                                        return object2 == object1 ? object1 : object2;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return object0;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        };
                        constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.L$0 = this;
                        constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.L$1 = listenableWorker0;
                        constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.label = 1;
                        object0 = BuildersKt.withContext(coroutineDispatcher0, constraintTrackingWorker$setupAndRunConstraintTrackingWork$50, constraintTrackingWorker$setupAndRunConstraintTrackingWork$10);
                    }
                    catch(CancellationException cancellationException0) {
                        constraintTrackingWorker0 = this;
                        break;
                    }
                    if(object0 == object1) {
                        return object1;
                    }
                    constraintTrackingWorker0 = this;
                    return (Result)object0;
                }
                Logger.get().error("WM-ConstraintTrkngWrkr", "No worker to delegate to.");
                Result listenableWorker$Result3 = Result.failure();
                Intrinsics.checkNotNullExpressionValue(listenableWorker$Result3, "failure()");
                return listenableWorker$Result3;
            }
            case 1: {
                listenableWorker0 = (ListenableWorker)constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.L$1;
                constraintTrackingWorker0 = (ConstraintTrackingWorker)constraintTrackingWorker$setupAndRunConstraintTrackingWork$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    return (Result)object0;
                }
                catch(CancellationException cancellationException0) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        if(constraintTrackingWorker0.isStopped() || cancellationException0 instanceof ConstraintUnsatisfiedException) {
            if(Build.VERSION.SDK_INT < 0x1F) {
                listenableWorker0.stop(0xFFFFFE00);
            }
            else if(constraintTrackingWorker0.isStopped()) {
                listenableWorker0.stop(constraintTrackingWorker0.getStopReason());
            }
            else {
                if(!(cancellationException0 instanceof ConstraintUnsatisfiedException)) {
                    throw new IllegalStateException("Unreachable");
                }
                listenableWorker0.stop(((ConstraintUnsatisfiedException)cancellationException0).getStopReason());
            }
        }
        if(!(cancellationException0 instanceof ConstraintUnsatisfiedException)) {
            throw cancellationException0;
        }
        Result listenableWorker$Result4 = Result.retry();
        Intrinsics.checkNotNullExpressionValue(listenableWorker$Result4, "{\n            // there a…throw cancelled\n        }");
        return listenableWorker$Result4;
    }
}

