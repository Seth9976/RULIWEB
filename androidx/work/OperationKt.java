package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A.\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\tH\u0000\u001A\u0012\u0010\u000B\u001A\u00020\f*\u00020\u0001H\u0086H¢\u0006\u0002\u0010\r¨\u0006\u000E"}, d2 = {"launchOperation", "Landroidx/work/Operation;", "tracer", "Landroidx/work/Tracer;", "label", "", "executor", "Ljava/util/concurrent/Executor;", "block", "Lkotlin/Function0;", "", "await", "Landroidx/work/Operation$State$SUCCESS;", "(Landroidx/work/Operation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class OperationKt {
    // 检测为 Lambda 实现
    public static Unit $r8$lambda$4AmAQmnwY87AwH_dAIVR-wuDub0(Executor executor0, Tracer tracer0, String s, Function0 function00, MutableLiveData mutableLiveData0, Completer callbackToFutureAdapter$Completer0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$XKAkIiEN7OgIvwuLUZRQpJhjmyE(Tracer tracer0, String s, Function0 function00, MutableLiveData mutableLiveData0, Completer callbackToFutureAdapter$Completer0) [...]

    public static final Object await(Operation operation0, Continuation continuation0) {
        androidx.work.OperationKt.await.1 operationKt$await$10;
        if(continuation0 instanceof androidx.work.OperationKt.await.1) {
            operationKt$await$10 = (androidx.work.OperationKt.await.1)continuation0;
            if((operationKt$await$10.label & 0x80000000) == 0) {
                operationKt$await$10 = new ContinuationImpl(continuation0) {
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return OperationKt.await(null, this);
                    }
                };
            }
            else {
                operationKt$await$10.label ^= 0x80000000;
            }
        }
        else {
            operationKt$await$10 = new ContinuationImpl(continuation0) {
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return OperationKt.await(null, this);
                }
            };
        }
        Object object0 = operationKt$await$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(operationKt$await$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ListenableFuture listenableFuture0 = operation0.getResult();
                Intrinsics.checkNotNullExpressionValue(listenableFuture0, "result");
                operationKt$await$10.label = 1;
                object0 = ListenableFutureKt.await(listenableFuture0, operationKt$await$10);
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
        Intrinsics.checkNotNullExpressionValue(object0, "result.await()");
        return object0;
    }

    private static final Object await$$forInline(Operation operation0, Continuation continuation0) {
        ListenableFuture listenableFuture0 = operation0.getResult();
        Intrinsics.checkNotNullExpressionValue(listenableFuture0, "result");
        Object object0 = ListenableFutureKt.await(listenableFuture0, continuation0);
        Intrinsics.checkNotNullExpressionValue(object0, "result.await()");
        return object0;
    }

    public static final Operation launchOperation(Tracer tracer0, String s, Executor executor0, Function0 function00) {
        Intrinsics.checkNotNullParameter(tracer0, "tracer");
        Intrinsics.checkNotNullParameter(s, "label");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        Intrinsics.checkNotNullParameter(function00, "block");
        MutableLiveData mutableLiveData0 = new MutableLiveData(Operation.IN_PROGRESS);
        ListenableFuture listenableFuture0 = CallbackToFutureAdapter.getFuture((Completer callbackToFutureAdapter$Completer0) -> OperationKt.launchOperation$lambda$2(executor0, tracer0, s, function00, mutableLiveData0, callbackToFutureAdapter$Completer0));
        Intrinsics.checkNotNullExpressionValue(listenableFuture0, "getFuture { completer ->…}\n            }\n        }");
        return new OperationImpl(mutableLiveData0, listenableFuture0);
    }

    private static final Unit launchOperation$lambda$2(Executor executor0, Tracer tracer0, String s, Function0 function00, MutableLiveData mutableLiveData0, Completer callbackToFutureAdapter$Completer0) {
        Intrinsics.checkNotNullParameter(callbackToFutureAdapter$Completer0, "completer");
        executor0.execute(() -> OperationKt.launchOperation$lambda$2$lambda$1(tracer0, s, function00, mutableLiveData0, callbackToFutureAdapter$Completer0));
        return Unit.INSTANCE;
    }

    private static final void launchOperation$lambda$2$lambda$1(Tracer tracer0, String s, Function0 function00, MutableLiveData mutableLiveData0, Completer callbackToFutureAdapter$Completer0) {
        boolean z = tracer0.isEnabled();
        try {
            if(z) {
                tracer0.beginSection(s);
            }
            try {
                function00.invoke();
                mutableLiveData0.postValue(Operation.SUCCESS);
                callbackToFutureAdapter$Completer0.set(Operation.SUCCESS);
            }
            catch(Throwable throwable0) {
                mutableLiveData0.postValue(new FAILURE(throwable0));
                callbackToFutureAdapter$Completer0.setException(throwable0);
            }
        }
        finally {
            if(z) {
                tracer0.endSection();
            }
        }
    }
}

