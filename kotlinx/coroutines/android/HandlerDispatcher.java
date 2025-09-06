package kotlinx.coroutines.android;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.Delay.DefaultImpls;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0004¢\u0006\u0002\u0010\u0003R\u0012\u0010\u0004\u001A\u00020\u0000X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\u0007¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/android/HandlerDispatcher;", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "()V", "immediate", "getImmediate", "()Lkotlinx/coroutines/android/HandlerDispatcher;", "Lkotlinx/coroutines/android/HandlerContext;", "kotlinx-coroutines-android"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class HandlerDispatcher extends MainCoroutineDispatcher implements Delay {
    private HandlerDispatcher() {
    }

    public HandlerDispatcher(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    @Override  // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long v, Continuation continuation0) {
        return DefaultImpls.delay(this, v, continuation0);
    }

    public abstract HandlerDispatcher getImmediate();

    @Override  // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long v, Runnable runnable0, CoroutineContext coroutineContext0) {
        return DefaultImpls.invokeOnTimeout(this, v, runnable0, coroutineContext0);
    }
}

