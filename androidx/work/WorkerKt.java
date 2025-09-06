package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A&\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"future", "Lcom/google/common/util/concurrent/ListenableFuture;", "T", "Ljava/util/concurrent/Executor;", "block", "Lkotlin/Function0;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkerKt {
    // 检测为 Lambda 实现
    public static void $r8$lambda$06LNzu7McnKR6G06fSbfQ2BCegc(AtomicBoolean atomicBoolean0, Completer callbackToFutureAdapter$Completer0, Function0 function00) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$fQfnDvynAvnSQwUWbYAmb21KapE(AtomicBoolean atomicBoolean0) [...]

    // 检测为 Lambda 实现
    public static Unit $r8$lambda$stbDiVrUcYsUSVFbJy_5-j0wuK4(Executor executor0, Function0 function00, Completer callbackToFutureAdapter$Completer0) [...]

    public static final ListenableFuture access$future(Executor executor0, Function0 function00) {
        return WorkerKt.future(executor0, function00);
    }

    private static final ListenableFuture future(Executor executor0, Function0 function00) {
        ListenableFuture listenableFuture0 = CallbackToFutureAdapter.getFuture((Completer callbackToFutureAdapter$Completer0) -> WorkerKt.future$lambda$2(executor0, function00, callbackToFutureAdapter$Completer0));
        Intrinsics.checkNotNullExpressionValue(listenableFuture0, "getFuture {\n        val …        }\n        }\n    }");
        return listenableFuture0;
    }

    private static final Unit future$lambda$2(Executor executor0, Function0 function00, Completer callbackToFutureAdapter$Completer0) {
        Intrinsics.checkNotNullParameter(callbackToFutureAdapter$Completer0, "it");
        AtomicBoolean atomicBoolean0 = new AtomicBoolean(false);
        callbackToFutureAdapter$Completer0.addCancellationListener(() -> WorkerKt.future$lambda$2$lambda$0(atomicBoolean0), DirectExecutor.INSTANCE);
        executor0.execute(() -> WorkerKt.future$lambda$2$lambda$1(atomicBoolean0, callbackToFutureAdapter$Completer0, function00));
        return Unit.INSTANCE;
    }

    private static final void future$lambda$2$lambda$0(AtomicBoolean atomicBoolean0) {
        atomicBoolean0.set(true);
    }

    private static final void future$lambda$2$lambda$1(AtomicBoolean atomicBoolean0, Completer callbackToFutureAdapter$Completer0, Function0 function00) {
        if(!atomicBoolean0.get()) {
            try {
                callbackToFutureAdapter$Completer0.set(function00.invoke());
            }
            catch(Throwable throwable0) {
                callbackToFutureAdapter$Completer0.setException(throwable0);
            }
        }
    }
}

