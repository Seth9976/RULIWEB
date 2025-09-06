package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\u001AV\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u00062\'\u0010\u0007\u001A#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\b¢\u0006\u0002\b\fH\u0000¢\u0006\u0002\u0010\r\u001A.\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u0001\"\u0004\b\u0000\u0010\u000F*\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u0013H\u0000¨\u0006\u0014"}, d2 = {"launchFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "start", "Lkotlinx/coroutines/CoroutineStart;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lcom/google/common/util/concurrent/ListenableFuture;", "executeAsync", "V", "Ljava/util/concurrent/Executor;", "debugTag", "", "Lkotlin/Function0;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ListenableFutureKt {
    // 检测为 Lambda 实现
    public static void $r8$lambda$2J7WTnmfyHeSyx3GGU57K1DCNjw(AtomicBoolean atomicBoolean0, Completer callbackToFutureAdapter$Completer0, Function0 function00) [...]

    // 检测为 Lambda 实现
    public static Object $r8$lambda$GaXsP0J9ZiqT-NKOjjWkBCoRah8(CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20, Completer callbackToFutureAdapter$Completer0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$TzZqNjsaPiH60pYTFt2BnJ0hmLw(AtomicBoolean atomicBoolean0) [...]

    // 检测为 Lambda 实现
    public static Object $r8$lambda$X5HWWttRZ_Ir0xD9aqd6GXUt6fY(Executor executor0, String s, Function0 function00, Completer callbackToFutureAdapter$Completer0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$vr_XgQ5qIrw8Yb6kuj8zI4IjUEw(Job job0) [...]

    public static final ListenableFuture executeAsync(Executor executor0, String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(executor0, "<this>");
        Intrinsics.checkNotNullParameter(s, "debugTag");
        Intrinsics.checkNotNullParameter(function00, "block");
        ListenableFuture listenableFuture0 = CallbackToFutureAdapter.getFuture((Completer callbackToFutureAdapter$Completer0) -> ListenableFutureKt.executeAsync$lambda$4(executor0, s, function00, callbackToFutureAdapter$Completer0));
        Intrinsics.checkNotNullExpressionValue(listenableFuture0, "getFuture { completer ->… }\n        debugTag\n    }");
        return listenableFuture0;
    }

    private static final Object executeAsync$lambda$4(Executor executor0, String s, Function0 function00, Completer callbackToFutureAdapter$Completer0) {
        Intrinsics.checkNotNullParameter(callbackToFutureAdapter$Completer0, "completer");
        AtomicBoolean atomicBoolean0 = new AtomicBoolean(false);
        callbackToFutureAdapter$Completer0.addCancellationListener(() -> ListenableFutureKt.executeAsync$lambda$4$lambda$2(atomicBoolean0), DirectExecutor.INSTANCE);
        executor0.execute(() -> ListenableFutureKt.executeAsync$lambda$4$lambda$3(atomicBoolean0, callbackToFutureAdapter$Completer0, function00));
        return s;
    }

    private static final void executeAsync$lambda$4$lambda$2(AtomicBoolean atomicBoolean0) {
        atomicBoolean0.set(true);
    }

    private static final void executeAsync$lambda$4$lambda$3(AtomicBoolean atomicBoolean0, Completer callbackToFutureAdapter$Completer0, Function0 function00) {
        if(!atomicBoolean0.get()) {
            try {
                callbackToFutureAdapter$Completer0.set(function00.invoke());
            }
            catch(Throwable throwable0) {
                callbackToFutureAdapter$Completer0.setException(throwable0);
            }
        }
    }

    public static final ListenableFuture launchFuture(CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "context");
        Intrinsics.checkNotNullParameter(coroutineStart0, "start");
        Intrinsics.checkNotNullParameter(function20, "block");
        ListenableFuture listenableFuture0 = CallbackToFutureAdapter.getFuture((Completer callbackToFutureAdapter$Completer0) -> ListenableFutureKt.launchFuture$lambda$1(coroutineContext0, coroutineStart0, function20, callbackToFutureAdapter$Completer0));
        Intrinsics.checkNotNullExpressionValue(listenableFuture0, "getFuture { completer ->…owable)\n        }\n    }\n}");
        return listenableFuture0;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008A@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        @DebugMetadata(c = "androidx.work.ListenableFutureKt$launchFuture$1$2", f = "ListenableFuture.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, s = {})
        final class androidx.work.ListenableFutureKt.launchFuture.1.2 extends SuspendLambda implements Function2 {
            final Function2 $block;
            final Completer $completer;
            private Object L$0;
            int label;

            androidx.work.ListenableFutureKt.launchFuture.1.2(Function2 function20, Completer callbackToFutureAdapter$Completer0, Continuation continuation0) {
                this.$block = function20;
                this.$completer = callbackToFutureAdapter$Completer0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.work.ListenableFutureKt.launchFuture.1.2 listenableFutureKt$launchFuture$1$20 = new androidx.work.ListenableFutureKt.launchFuture.1.2(this.$block, this.$completer, continuation0);
                listenableFutureKt$launchFuture$1$20.L$0 = object0;
                return listenableFutureKt$launchFuture$1$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.work.ListenableFutureKt.launchFuture.1.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        try {
                            this.label = 1;
                            object0 = this.$block.invoke(coroutineScope0, this);
                            if(object0 == object1) {
                                return object1;
                            label_9:
                                ResultKt.throwOnFailure(object0);
                            }
                            this.$completer.set(object0);
                            return Unit.INSTANCE;
                        }
                        catch(CancellationException unused_ex) {
                            break;
                        }
                        catch(Throwable throwable0) {
                            this.$completer.setException(throwable0);
                            return Unit.INSTANCE;
                        }
                    }
                    case 1: {
                        goto label_9;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                this.$completer.setCancelled();
                return Unit.INSTANCE;
            }
        }

    }

    public static ListenableFuture launchFuture$default(CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v & 2) != 0) {
            coroutineStart0 = CoroutineStart.DEFAULT;
        }
        return ListenableFutureKt.launchFuture(coroutineContext0, coroutineStart0, function20);
    }

    private static final Object launchFuture$lambda$1(CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20, Completer callbackToFutureAdapter$Completer0) {
        Intrinsics.checkNotNullParameter(callbackToFutureAdapter$Completer0, "completer");
        callbackToFutureAdapter$Completer0.addCancellationListener(() -> ListenableFutureKt.launchFuture$lambda$1$lambda$0(((Job)coroutineContext0.get(Job.Key))), DirectExecutor.INSTANCE);
        return BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(coroutineContext0), null, coroutineStart0, new androidx.work.ListenableFutureKt.launchFuture.1.2(function20, callbackToFutureAdapter$Completer0, null), 1, null);
    }

    private static final void launchFuture$lambda$1$lambda$0(Job job0) {
        if(job0 != null) {
            DefaultImpls.cancel$default(job0, null, 1, null);
        }
    }
}

