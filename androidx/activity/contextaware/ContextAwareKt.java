package androidx.activity.contextaware;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(d1 = {"\u0000\u001A\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A=\u0010\u0000\u001A\u0007H\u0001¢\u0006\u0002\b\u0002\"\u0004\b\u0000\u0010\u0001*\u00020\u00032\u001E\b\u0004\u0010\u0004\u001A\u0018\u0012\t\u0012\u00070\u0006¢\u0006\u0002\b\u0002\u0012\t\u0012\u0007H\u0001¢\u0006\u0002\b\u00020\u0005H\u0086H¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"withContextAvailable", "R", "Lkotlin/jvm/JvmSuppressWildcards;", "Landroidx/activity/contextaware/ContextAware;", "onContextAvailable", "Lkotlin/Function1;", "Landroid/content/Context;", "(Landroidx/activity/contextaware/ContextAware;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ContextAwareKt {
    public static final Object withContextAvailable(ContextAware contextAware0, Function1 function10, Continuation continuation0) {
        Context context0 = contextAware0.peekAvailableContext();
        if(context0 != null) {
            return function10.invoke(context0);
        }
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        androidx.activity.contextaware.ContextAwareKt.withContextAvailable.2.listener.1 contextAwareKt$withContextAvailable$2$listener$10 = new OnContextAvailableListener() {
            @Override  // androidx.activity.contextaware.OnContextAvailableListener
            public void onContextAvailable(Context context0) {
                Object object0;
                Intrinsics.checkNotNullParameter(context0, "context");
                CancellableContinuation cancellableContinuation0 = cancellableContinuationImpl0;
                try {
                    object0 = Result.constructor-impl(function10.invoke(context0));
                }
                catch(Throwable throwable0) {
                    object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                }
                cancellableContinuation0.resumeWith(object0);
            }
        };
        contextAware0.addOnContextAvailableListener(contextAwareKt$withContextAvailable$2$listener$10);
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(contextAware0, contextAwareKt$withContextAvailable$2$listener$10) {
            final androidx.activity.contextaware.ContextAwareKt.withContextAvailable.2.listener.1 $listener;
            final ContextAware $this_withContextAvailable;

            {
                this.$this_withContextAvailable = contextAware0;
                this.$listener = contextAwareKt$withContextAvailable$2$listener$10;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                this.$this_withContextAvailable.removeOnContextAvailableListener(this.$listener);
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    private static final Object withContextAvailable$$forInline(ContextAware contextAware0, Function1 function10, Continuation continuation0) {
        Context context0 = contextAware0.peekAvailableContext();
        if(context0 != null) {
            return function10.invoke(context0);
        }
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        androidx.activity.contextaware.ContextAwareKt.withContextAvailable.2.listener.1 contextAwareKt$withContextAvailable$2$listener$10 = new androidx.activity.contextaware.ContextAwareKt.withContextAvailable.2.listener.1(cancellableContinuationImpl0, function10);
        contextAware0.addOnContextAvailableListener(contextAwareKt$withContextAvailable$2$listener$10);
        cancellableContinuationImpl0.invokeOnCancellation(new androidx.activity.contextaware.ContextAwareKt.withContextAvailable.2.1(contextAware0, contextAwareKt$withContextAvailable$2$listener$10));
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }
}

