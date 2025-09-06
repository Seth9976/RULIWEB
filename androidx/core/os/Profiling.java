package androidx.core.os;

import android.content.Context;
import android.os.ProfilingManager;
import android.os.ProfilingResult;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0016\u0010\n\u001A\b\u0012\u0004\u0012\u00020\f0\u000B2\u0006\u0010\r\u001A\u00020\u000EH\u0007\u001A&\u0010\n\u001A\u00020\u000F2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0010\u001A\u00020\u00112\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\f0\u0013H\u0007\u001A2\u0010\u0014\u001A\u00020\u000F2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0015\u001A\u00020\u00162\b\u0010\u0010\u001A\u0004\u0018\u00010\u00112\u000E\u0010\u0012\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0013H\u0007\u001A\u001E\u0010\u0017\u001A\u00020\u000F2\u0006\u0010\r\u001A\u00020\u000E2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\f0\u0013H\u0007\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"KEY_BUFFER_FILL_POLICY", "", "KEY_DURATION_MS", "KEY_FREQUENCY_HZ", "KEY_SAMPLING_INTERVAL_BYTES", "KEY_SIZE_KB", "KEY_TRACK_JAVA_ALLOCATIONS", "VALUE_BUFFER_FILL_POLICY_DISCARD", "", "VALUE_BUFFER_FILL_POLICY_RING_BUFFER", "registerForAllProfilingResults", "Lkotlinx/coroutines/flow/Flow;", "Landroid/os/ProfilingResult;", "context", "Landroid/content/Context;", "", "executor", "Ljava/util/concurrent/Executor;", "listener", "Ljava/util/function/Consumer;", "requestProfiling", "profilingRequest", "Landroidx/core/os/ProfilingRequest;", "unregisterForAllProfilingResults", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class Profiling {
    private static final String KEY_BUFFER_FILL_POLICY = "KEY_BUFFER_FILL_POLICY";
    private static final String KEY_DURATION_MS = "KEY_DURATION_MS";
    private static final String KEY_FREQUENCY_HZ = "KEY_FREQUENCY_HZ";
    private static final String KEY_SAMPLING_INTERVAL_BYTES = "KEY_SAMPLING_INTERVAL_BYTES";
    private static final String KEY_SIZE_KB = "KEY_SIZE_KB";
    private static final String KEY_TRACK_JAVA_ALLOCATIONS = "KEY_TRACK_JAVA_ALLOCATIONS";
    private static final int VALUE_BUFFER_FILL_POLICY_DISCARD = 1;
    private static final int VALUE_BUFFER_FILL_POLICY_RING_BUFFER = 2;

    public static final Flow registerForAllProfilingResults(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return FlowKt.callbackFlow(new Function2(context0, null) {
            final Context $context;
            private Object L$0;
            int label;

            {
                this.$context = context0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.core.os.Profiling.registerForAllProfilingResults.1 profiling$registerForAllProfilingResults$10 = new androidx.core.os.Profiling.registerForAllProfilingResults.1(this.$context, continuation0);
                profiling$registerForAllProfilingResults$10.L$0 = object0;
                return profiling$registerForAllProfilingResults$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((androidx.core.os.Profiling.registerForAllProfilingResults.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        Profiling.registerForAllProfilingResults.1..ExternalSyntheticLambda0 profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda00 = (ProfilingResult profilingResult0) -> {
                            Intrinsics.checkNotNullExpressionValue(profilingResult0, "result");
                            producerScope0.trySend-JP2dKIU(profilingResult0);
                        };
                        ProfilingManager profilingManager0 = HalfKt..ExternalSyntheticApiModelOutline0.m(this.$context.getSystemService(ProfilingManager.class));
                        profilingManager0.registerForAllProfilingResults((Runnable runnable0) -> runnable0.run(), profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda00);
                        Function0 function00 = new Function0(profiling$registerForAllProfilingResults$1$$ExternalSyntheticLambda00) {
                            final Consumer $listener;
                            final ProfilingManager $service;

                            {
                                this.$service = profilingManager0;
                                this.$listener = consumer0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                this.invoke();
                                return Unit.INSTANCE;
                            }

                            public final void invoke() {
                                this.$service.unregisterForAllProfilingResults(this.$listener);
                            }
                        };
                        this.label = 1;
                        return ProduceKt.awaitClose(producerScope0, function00, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }

            // 检测为 Lambda 实现
            private static final void invokeSuspend$lambda$0(ProducerScope producerScope0, ProfilingResult profilingResult0) [...]

            // 检测为 Lambda 实现
            private static final void invokeSuspend$lambda$1(Runnable runnable0) [...]
        });
    }

    public static final void registerForAllProfilingResults(Context context0, Executor executor0, Consumer consumer0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        HalfKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ProfilingManager.class)).registerForAllProfilingResults(executor0, consumer0);
    }

    public static final void requestProfiling(Context context0, ProfilingRequest profilingRequest0, Executor executor0, Consumer consumer0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(profilingRequest0, "profilingRequest");
        HalfKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ProfilingManager.class)).requestProfiling(profilingRequest0.getProfilingType(), profilingRequest0.getParams(), profilingRequest0.getTag(), profilingRequest0.getCancellationSignal(), executor0, consumer0);
    }

    public static final void unregisterForAllProfilingResults(Context context0, Consumer consumer0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        HalfKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ProfilingManager.class)).unregisterForAllProfilingResults(consumer0);
    }
}

