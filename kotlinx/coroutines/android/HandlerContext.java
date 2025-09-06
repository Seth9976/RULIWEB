package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.NonDisposableHandle;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001B\b\u0016\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0002\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u001C\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\n\u0010\u0013\u001A\u00060\u0014j\u0002`\u0015H\u0002J\u001C\u0010\u0016\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\n\u0010\u0013\u001A\u00060\u0014j\u0002`\u0015H\u0016J\u0013\u0010\u0017\u001A\u00020\t2\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019H\u0096\u0002J\b\u0010\u001A\u001A\u00020\u001BH\u0016J$\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001F2\n\u0010\u0013\u001A\u00060\u0014j\u0002`\u00152\u0006\u0010\u0011\u001A\u00020\u0012H\u0016J\u0010\u0010 \u001A\u00020\t2\u0006\u0010\u0011\u001A\u00020\u0012H\u0016J\u001E\u0010!\u001A\u00020\u00102\u0006\u0010\u001E\u001A\u00020\u001F2\f\u0010\"\u001A\b\u0012\u0004\u0012\u00020\u00100#H\u0016J\b\u0010$\u001A\u00020\u0006H\u0016R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\u0000X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlinx/coroutines/android/HandlerContext;", "Lkotlinx/coroutines/android/HandlerDispatcher;", "Lkotlinx/coroutines/Delay;", "handler", "Landroid/os/Handler;", "name", "", "(Landroid/os/Handler;Ljava/lang/String;)V", "invokeImmediately", "", "(Landroid/os/Handler;Ljava/lang/String;Z)V", "_immediate", "immediate", "getImmediate", "()Lkotlinx/coroutines/android/HandlerContext;", "cancelOnRejection", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatch", "equals", "other", "", "hashCode", "", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "isDispatchNeeded", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "kotlinx-coroutines-android"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class HandlerContext extends HandlerDispatcher implements Delay {
    private volatile HandlerContext _immediate;
    private final Handler handler;
    private final HandlerContext immediate;
    private final boolean invokeImmediately;
    private final String name;

    // 检测为 Lambda 实现
    public static void $r8$lambda$AXU9Ipeju1KWZ01G6KHwtONG4Wk(HandlerContext handlerContext0, Runnable runnable0) [...]

    public HandlerContext(Handler handler0, String s) {
        this(handler0, s, false);
    }

    public HandlerContext(Handler handler0, String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            s = null;
        }
        this(handler0, s);
    }

    private HandlerContext(Handler handler0, String s, boolean z) {
        HandlerContext handlerContext0 = null;
        super(null);
        this.handler = handler0;
        this.name = s;
        this.invokeImmediately = z;
        if(z) {
            handlerContext0 = this;
        }
        this._immediate = handlerContext0;
        HandlerContext handlerContext1 = this._immediate;
        if(handlerContext1 == null) {
            handlerContext1 = new HandlerContext(handler0, s, true);
            this._immediate = handlerContext1;
        }
        this.immediate = handlerContext1;
    }

    private final void cancelOnRejection(CoroutineContext coroutineContext0, Runnable runnable0) {
        JobKt.cancel(coroutineContext0, new CancellationException("The task was rejected, the handler underlying the dispatcher \'" + this + "\' was closed"));
        Dispatchers.getIO().dispatch(coroutineContext0, runnable0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        if(!this.handler.post(runnable0)) {
            this.cancelOnRejection(coroutineContext0, runnable0);
        }
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof HandlerContext && ((HandlerContext)object0).handler == this.handler;
    }

    @Override  // kotlinx.coroutines.MainCoroutineDispatcher
    public MainCoroutineDispatcher getImmediate() {
        return this.getImmediate();
    }

    public HandlerContext getImmediate() {
        return this.immediate;
    }

    @Override  // kotlinx.coroutines.android.HandlerDispatcher
    public HandlerDispatcher getImmediate() {
        return this.getImmediate();
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this.handler);
    }

    @Override  // kotlinx.coroutines.android.HandlerDispatcher, kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long v, Runnable runnable0, CoroutineContext coroutineContext0) {
        long v1 = RangesKt.coerceAtMost(v, 0x3FFFFFFFFFFFFFFFL);
        if(this.handler.postDelayed(runnable0, v1)) {
            return () -> HandlerContext.invokeOnTimeout$lambda$3(this, runnable0);
        }
        this.cancelOnRejection(coroutineContext0, runnable0);
        return NonDisposableHandle.INSTANCE;
    }

    private static final void invokeOnTimeout$lambda$3(HandlerContext handlerContext0, Runnable runnable0) {
        handlerContext0.handler.removeCallbacks(runnable0);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(CoroutineContext coroutineContext0) {
        return !this.invokeImmediately || !Intrinsics.areEqual(Looper.myLooper(), this.handler.getLooper());
    }

    @Override  // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long v, CancellableContinuation cancellableContinuation0) {
        Runnable runnable0 = new Runnable() {
            @Override
            public final void run() {
                this.resumeUndispatched(HandlerContext.this, Unit.INSTANCE);
            }
        };
        long v1 = RangesKt.coerceAtMost(v, 0x3FFFFFFFFFFFFFFFL);
        if(this.handler.postDelayed(runnable0, v1)) {
            cancellableContinuation0.invokeOnCancellation(new Function1(runnable0) {
                final Runnable $block;

                {
                    HandlerContext.this = handlerContext0;
                    this.$block = runnable0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    this.invoke(((Throwable)object0));
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable throwable0) {
                    HandlerContext.this.handler.removeCallbacks(this.$block);
                }
            });
            return;
        }
        this.cancelOnRejection(cancellableContinuation0.getContext(), runnable0);
    }

    @Override  // kotlinx.coroutines.MainCoroutineDispatcher
    public String toString() {
        String s = this.toStringInternalImpl();
        if(s == null) {
            s = this.name == null ? this.handler.toString() : this.name;
            return this.invokeImmediately ? s + ".immediate" : s;
        }
        return s;
    }
}

