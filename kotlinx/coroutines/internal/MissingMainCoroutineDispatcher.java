package kotlinx.coroutines.internal;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Delay.DefaultImpls;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001B\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u001C\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\n\u0010\u000F\u001A\u00060\u0010j\u0002`\u0011H\u0016J$\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u00152\n\u0010\u000F\u001A\u00060\u0010j\u0002`\u00112\u0006\u0010\r\u001A\u00020\u000EH\u0016J\u0010\u0010\u0016\u001A\u00020\u00172\u0006\u0010\r\u001A\u00020\u000EH\u0016J\u0010\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u001BH\u0016J\b\u0010\u001C\u001A\u00020\fH\u0002J\u001E\u0010\u001D\u001A\u00020\f2\u0006\u0010\u0014\u001A\u00020\u00152\f\u0010\u001E\u001A\b\u0012\u0004\u0012\u00020 0\u001FH\u0016J\b\u0010!\u001A\u00020\u0006H\u0016R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\n¨\u0006\""}, d2 = {"Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcher;", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "cause", "", "errorHint", "", "(Ljava/lang/Throwable;Ljava/lang/String;)V", "immediate", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "isDispatchNeeded", "", "limitedParallelism", "Lkotlinx/coroutines/CoroutineDispatcher;", "parallelism", "", "missing", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class MissingMainCoroutineDispatcher extends MainCoroutineDispatcher implements Delay {
    private final Throwable cause;
    private final String errorHint;

    public MissingMainCoroutineDispatcher(Throwable throwable0, String s) {
        this.cause = throwable0;
        this.errorHint = s;
    }

    public MissingMainCoroutineDispatcher(Throwable throwable0, String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            s = null;
        }
        this(throwable0, s);
    }

    @Override  // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long v, Continuation continuation0) {
        return DefaultImpls.delay(this, v, continuation0);
    }

    public Void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.missing();
        throw new KotlinNothingValueException();
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.dispatch(coroutineContext0, runnable0);
    }

    @Override  // kotlinx.coroutines.MainCoroutineDispatcher
    public MainCoroutineDispatcher getImmediate() {
        return this;
    }

    @Override  // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long v, Runnable runnable0, CoroutineContext coroutineContext0) {
        this.missing();
        throw new KotlinNothingValueException();
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(CoroutineContext coroutineContext0) {
        this.missing();
        throw new KotlinNothingValueException();
    }

    @Override  // kotlinx.coroutines.MainCoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int v) {
        this.missing();
        throw new KotlinNothingValueException();
    }

    private final Void missing() {
        String s1;
        if(this.cause != null) {
            StringBuilder stringBuilder0 = new StringBuilder("Module with the Main dispatcher had failed to initialize");
            String s = this.errorHint;
            if(s == null) {
                s1 = "";
            }
            else {
                s1 = ". " + s;
                if(s1 == null) {
                    s1 = "";
                }
            }
            stringBuilder0.append(s1);
            throw new IllegalStateException(stringBuilder0.toString(), this.cause);
        }
        MainDispatchersKt.throwMissingMainDispatcherException();
        throw new KotlinNothingValueException();
    }

    public Void scheduleResumeAfterDelay(long v, CancellableContinuation cancellableContinuation0) {
        this.missing();
        throw new KotlinNothingValueException();
    }

    @Override  // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long v, CancellableContinuation cancellableContinuation0) {
        this.scheduleResumeAfterDelay(v, cancellableContinuation0);
    }

    @Override  // kotlinx.coroutines.MainCoroutineDispatcher
    public String toString() {
        return "Dispatchers.Main[missing" + (this.cause == null ? "" : ", cause=" + this.cause) + ']';
    }
}

