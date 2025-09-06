package androidx.work.impl.utils;

import android.content.Context;
import android.os.Build.VERSION;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.WorkerWrapperKt;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A6\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0086@¢\u0006\u0002\u0010\u000E\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"TAG", "", "workForeground", "", "context", "Landroid/content/Context;", "spec", "Landroidx/work/impl/model/WorkSpec;", "worker", "Landroidx/work/ListenableWorker;", "foregroundUpdater", "Landroidx/work/ForegroundUpdater;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "(Landroid/content/Context;Landroidx/work/impl/model/WorkSpec;Landroidx/work/ListenableWorker;Landroidx/work/ForegroundUpdater;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkForegroundKt {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-WorkForegroundRunnab", "tagWithPrefix(\"WorkForegroundRunnable\")");
        WorkForegroundKt.TAG = "WM-WorkForegroundRunnab";
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器

    public static final Object workForeground(Context context0, WorkSpec workSpec0, ListenableWorker listenableWorker0, ForegroundUpdater foregroundUpdater0, TaskExecutor taskExecutor0, Continuation continuation0) {
        if(workSpec0.expedited && Build.VERSION.SDK_INT < 0x1F) {
            Executor executor0 = taskExecutor0.getMainThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(executor0, "taskExecutor.mainThreadExecutor");
            Object object0 = BuildersKt.withContext(ExecutorsKt.from(executor0), new Function2(listenableWorker0, workSpec0, foregroundUpdater0, context0, null) {
                final Context $context;
                final ForegroundUpdater $foregroundUpdater;
                final WorkSpec $spec;
                final ListenableWorker $worker;
                int label;

                {
                    this.$worker = listenableWorker0;
                    this.$spec = workSpec0;
                    this.$foregroundUpdater = foregroundUpdater0;
                    this.$context = context0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    return new androidx.work.impl.utils.WorkForegroundKt.workForeground.2(this.$worker, this.$spec, this.$foregroundUpdater, this.$context, continuation0);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                }

                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                    return ((androidx.work.impl.utils.WorkForegroundKt.workForeground.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch(this.label) {
                        case 0: {
                            ResultKt.throwOnFailure(object0);
                            ListenableFuture listenableFuture0 = this.$worker.getForegroundInfoAsync();
                            Intrinsics.checkNotNullExpressionValue(listenableFuture0, "worker.getForegroundInfoAsync()");
                            this.label = 1;
                            object0 = WorkerWrapperKt.awaitWithin(listenableFuture0, this.$worker, this);
                            if(object0 == object1) {
                                return object1;
                            }
                            break;
                        }
                        case 1: {
                            ResultKt.throwOnFailure(object0);
                            break;
                        }
                        case 2: {
                            ResultKt.throwOnFailure(object0);
                            return object0;
                        }
                        default: {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        }
                    }
                    if(((ForegroundInfo)object0) == null) {
                        throw new IllegalStateException("Worker was marked important (" + this.$spec.workerClassName + ") but did not provide ForegroundInfo");
                    }
                    Logger.get().debug("WM-WorkForegroundRunnab", "Updating notification for " + this.$spec.workerClassName);
                    UUID uUID0 = this.$worker.getId();
                    ListenableFuture listenableFuture1 = this.$foregroundUpdater.setForegroundAsync(this.$context, uUID0, ((ForegroundInfo)object0));
                    Intrinsics.checkNotNullExpressionValue(listenableFuture1, "foregroundUpdater.setFor…orker.id, foregroundInfo)");
                    this.label = 2;
                    Object object2 = ListenableFutureKt.await(listenableFuture1, this);
                    return object2 == object1 ? object1 : object2;
                }
            }, continuation0);
            return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}

