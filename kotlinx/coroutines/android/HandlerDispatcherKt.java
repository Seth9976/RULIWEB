package kotlinx.coroutines.android;

import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\u001A\u0011\u0010\b\u001A\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001A\u0011\u0010\n\u001A\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001A\u001E\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0006\u001A\u00020\u00072\f\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u00010\u000EH\u0002\u001A\u0016\u0010\u000F\u001A\u00020\f2\f\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u00010\u000EH\u0002\u001A\u001D\u0010\u0010\u001A\u00020\u0003*\u00020\u00112\n\b\u0002\u0010\u0012\u001A\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0002\b\u0014\u001A\u0014\u0010\u0015\u001A\u00020\u0011*\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018H\u0001\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0004\u0010\u0005\"\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"MAX_DELAY", "", "Main", "Lkotlinx/coroutines/android/HandlerDispatcher;", "getMain$annotations", "()V", "choreographer", "Landroid/view/Choreographer;", "awaitFrame", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFrameSlowPath", "postFrameCallback", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "updateChoreographerAndPostFrameCallback", "asCoroutineDispatcher", "Landroid/os/Handler;", "name", "", "from", "asHandler", "Landroid/os/Looper;", "async", "", "kotlinx-coroutines-android"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class HandlerDispatcherKt {
    private static final long MAX_DELAY = 0x3FFFFFFFFFFFFFFFL;
    public static final HandlerDispatcher Main;
    private static volatile Choreographer choreographer;

    // 检测为 Lambda 实现
    public static void $r8$lambda$_-s4SOKmmdhN7PexQng1D-Olurw(CancellableContinuation cancellableContinuation0, long v) [...]

    static {
        Object object1;
        Object object0 = null;
        try {
            object1 = Result.constructor-impl(new HandlerContext(HandlerDispatcherKt.asHandler(Looper.getMainLooper(), true), null, 2, null));
        }
        catch(Throwable throwable0) {
            object1 = Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
        if(!Result.isFailure-impl(object1)) {
            object0 = object1;
        }
        HandlerDispatcherKt.Main = (HandlerDispatcher)object0;
    }

    public static final Object access$awaitFrameSlowPath(Continuation continuation0) {
        return HandlerDispatcherKt.awaitFrameSlowPath(continuation0);
    }

    public static final Handler asHandler(Looper looper0, boolean z) {
        if(z) {
            if(Build.VERSION.SDK_INT >= 28) {
                Handler handler0 = Handler.createAsync(looper0);
                Intrinsics.checkNotNull(handler0, "null cannot be cast to non-null type android.os.Handler");
                return handler0;
            }
            try {
                return (Handler)Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper0, null, Boolean.TRUE);
            }
            catch(NoSuchMethodException unused_ex) {
                return new Handler(looper0);
            }
        }
        return new Handler(looper0);
    }

    public static final Object awaitFrame(Continuation continuation0) {
        Choreographer choreographer0 = HandlerDispatcherKt.choreographer;
        if(choreographer0 != null) {
            CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
            cancellableContinuationImpl0.initCancellability();
            HandlerDispatcherKt.postFrameCallback(choreographer0, cancellableContinuationImpl0);
            Object object0 = cancellableContinuationImpl0.getResult();
            if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation0);
            }
            return object0;
        }
        return HandlerDispatcherKt.awaitFrameSlowPath(continuation0);
    }

    private static final Object awaitFrameSlowPath(Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        if(Looper.myLooper() == Looper.getMainLooper()) {
            HandlerDispatcherKt.updateChoreographerAndPostFrameCallback(cancellableContinuationImpl0);
        }
        else {
            Dispatchers.getMain().dispatch(cancellableContinuationImpl0.getContext(), () -> {
                Choreographer choreographer0 = HandlerDispatcherKt.choreographer;
                if(choreographer0 == null) {
                    choreographer0 = Choreographer.getInstance();
                    Intrinsics.checkNotNull(choreographer0);
                    HandlerDispatcherKt.choreographer = choreographer0;
                }
                HandlerDispatcherKt.postFrameCallback(choreographer0, this.$cont$inlined);
            });
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class kotlinx.coroutines.android.HandlerDispatcherKt.awaitFrameSlowPath.lambda.3..inlined.Runnable.1 implements Runnable {
            public kotlinx.coroutines.android.HandlerDispatcherKt.awaitFrameSlowPath.lambda.3..inlined.Runnable.1(CancellableContinuation cancellableContinuation0) {
            }

            @Override
            public final void run() {
                HandlerDispatcherKt.updateChoreographerAndPostFrameCallback(this.$cont$inlined);
            }
        }

    }

    public static final HandlerDispatcher from(Handler handler0) {
        return HandlerDispatcherKt.from$default(handler0, null, 1, null);
    }

    public static final HandlerDispatcher from(Handler handler0, String s) {
        return new HandlerContext(handler0, s);
    }

    public static HandlerDispatcher from$default(Handler handler0, String s, int v, Object object0) {
        if((v & 1) != 0) {
            s = null;
        }
        return HandlerDispatcherKt.from(handler0, s);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Dispatchers.Main instead")
    public static void getMain$annotations() {
    }

    private static final void postFrameCallback(Choreographer choreographer0, CancellableContinuation cancellableContinuation0) {
        choreographer0.postFrameCallback((long v) -> HandlerDispatcherKt.postFrameCallback$lambda$6(cancellableContinuation0, v));
    }

    private static final void postFrameCallback$lambda$6(CancellableContinuation cancellableContinuation0, long v) {
        cancellableContinuation0.resumeUndispatched(Dispatchers.getMain(), v);
    }

    // 检测为 Lambda 实现
    private static final void updateChoreographerAndPostFrameCallback(CancellableContinuation cancellableContinuation0) [...]
}

