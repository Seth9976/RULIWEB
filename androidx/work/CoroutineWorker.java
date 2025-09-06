package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0001\u001EB\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000E\u0010\r\u001A\u00020\u000EH¦@¢\u0006\u0002\u0010\u000FJ\u000E\u0010\u0010\u001A\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u000FJ\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00110\u0013J\u0006\u0010\u0014\u001A\u00020\u0015J\u0016\u0010\u0016\u001A\u00020\u00152\u0006\u0010\u0017\u001A\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001A\u00020\u00152\u0006\u0010\u001A\u001A\u00020\u001BH\u0086@¢\u0006\u0002\u0010\u001CJ\f\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0013R\u001C\u0010\u0007\u001A\u00020\b8\u0016X\u0097\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\t\u0010\n\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Landroidx/work/CoroutineWorker;", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "coroutineContext", "Lkotlinx/coroutines/CoroutineDispatcher;", "getCoroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlinx/coroutines/CoroutineDispatcher;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForegroundInfo", "Landroidx/work/ForegroundInfo;", "getForegroundInfoAsync", "Lcom/google/common/util/concurrent/ListenableFuture;", "onStopped", "", "setForeground", "foregroundInfo", "(Landroidx/work/ForegroundInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setProgress", "data", "Landroidx/work/Data;", "(Landroidx/work/Data;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startWork", "DeprecatedDispatcher", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class CoroutineWorker extends ListenableWorker {
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\n\u0010\n\u001A\u00060\u000Bj\u0002`\fH\u0016J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\b\u001A\u00020\tH\u0016R\u0011\u0010\u0003\u001A\u00020\u0001¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0005¨\u0006\u000F"}, d2 = {"Landroidx/work/CoroutineWorker$DeprecatedDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "dispatcher", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "isDispatchNeeded", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class DeprecatedDispatcher extends CoroutineDispatcher {
        public static final DeprecatedDispatcher INSTANCE;
        private static final CoroutineDispatcher dispatcher;

        static {
            DeprecatedDispatcher.INSTANCE = new DeprecatedDispatcher();
            DeprecatedDispatcher.dispatcher = Dispatchers.getDefault();
        }

        @Override  // kotlinx.coroutines.CoroutineDispatcher
        public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
            Intrinsics.checkNotNullParameter(coroutineContext0, "context");
            Intrinsics.checkNotNullParameter(runnable0, "block");
            DeprecatedDispatcher.dispatcher.dispatch(coroutineContext0, runnable0);
        }

        public final CoroutineDispatcher getDispatcher() {
            return DeprecatedDispatcher.dispatcher;
        }

        @Override  // kotlinx.coroutines.CoroutineDispatcher
        public boolean isDispatchNeeded(CoroutineContext coroutineContext0) {
            Intrinsics.checkNotNullParameter(coroutineContext0, "context");
            return DeprecatedDispatcher.dispatcher.isDispatchNeeded(coroutineContext0);
        }
    }

    private final CoroutineDispatcher coroutineContext;
    private final WorkerParameters params;

    public CoroutineWorker(Context context0, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters0, "params");
        super(context0, workerParameters0);
        this.params = workerParameters0;
        this.coroutineContext = DeprecatedDispatcher.INSTANCE;
    }

    public abstract Object doWork(Continuation arg1);

    public CoroutineDispatcher getCoroutineContext() {
        return this.coroutineContext;
    }

    @Deprecated(message = "use withContext(...) inside doWork() instead.")
    public static void getCoroutineContext$annotations() {
    }

    public Object getForegroundInfo(Continuation continuation0) {
        return CoroutineWorker.getForegroundInfo$suspendImpl(this, continuation0);
    }

    static Object getForegroundInfo$suspendImpl(CoroutineWorker coroutineWorker0, Continuation continuation0) {
        throw new IllegalStateException("Not implemented");
    }

    @Override  // androidx.work.ListenableWorker
    public final ListenableFuture getForegroundInfoAsync() {
        return ListenableFutureKt.launchFuture$default(this.getCoroutineContext().plus(JobKt.Job$default(null, 1, null)), null, new Function2(null) {
            int label;

            {
                CoroutineWorker.this = coroutineWorker0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.work.CoroutineWorker.getForegroundInfoAsync.1(CoroutineWorker.this, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.work.CoroutineWorker.getForegroundInfoAsync.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        Object object2 = CoroutineWorker.this.getForegroundInfo(this);
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
        }, 2, null);
    }

    @Override  // androidx.work.ListenableWorker
    public final void onStopped() {
        super.onStopped();
    }

    public final Object setForeground(ForegroundInfo foregroundInfo0, Continuation continuation0) {
        ListenableFuture listenableFuture0 = this.setForegroundAsync(foregroundInfo0);
        Intrinsics.checkNotNullExpressionValue(listenableFuture0, "setForegroundAsync(foregroundInfo)");
        Object object0 = androidx.concurrent.futures.ListenableFutureKt.await(listenableFuture0, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public final Object setProgress(Data data0, Continuation continuation0) {
        ListenableFuture listenableFuture0 = this.setProgressAsync(data0);
        Intrinsics.checkNotNullExpressionValue(listenableFuture0, "setProgressAsync(data)");
        Object object0 = androidx.concurrent.futures.ListenableFutureKt.await(listenableFuture0, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    @Override  // androidx.work.ListenableWorker
    public final ListenableFuture startWork() {
        CoroutineContext coroutineContext0 = Intrinsics.areEqual(this.getCoroutineContext(), DeprecatedDispatcher.INSTANCE) ? this.params.getWorkerContext() : this.getCoroutineContext();
        Intrinsics.checkNotNullExpressionValue(coroutineContext0, "if (coroutineContext != …rkerContext\n            }");
        return ListenableFutureKt.launchFuture$default(coroutineContext0.plus(JobKt.Job$default(null, 1, null)), null, new Function2(null) {
            int label;

            {
                CoroutineWorker.this = coroutineWorker0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new androidx.work.CoroutineWorker.startWork.1(CoroutineWorker.this, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.work.CoroutineWorker.startWork.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        Object object2 = CoroutineWorker.this.doWork(this);
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
        }, 2, null);
    }
}

