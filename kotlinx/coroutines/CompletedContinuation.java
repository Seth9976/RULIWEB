package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BZ\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u0012%\b\u0002\u0010\u0005\u001A\u001F\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u0007\u00A2\u0006\u0002\u0010\u000EJ\u000B\u0010\u0013\u001A\u0004\u0018\u00010\u0001H\u00C6\u0003J\u000B\u0010\u0014\u001A\u0004\u0018\u00010\u0004H\u00C6\u0003J&\u0010\u0015\u001A\u001F\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\u0006H\u00C6\u0003J\u000B\u0010\u0016\u001A\u0004\u0018\u00010\u0001H\u00C6\u0003J\u000B\u0010\u0017\u001A\u0004\u0018\u00010\u0007H\u00C6\u0003J`\u0010\u0018\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001A\u0004\u0018\u00010\u00042%\b\u0002\u0010\u0005\u001A\u001F\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\u00062\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u0007H\u00C6\u0001J\u0013\u0010\u0019\u001A\u00020\u00102\b\u0010\u001A\u001A\u0004\u0018\u00010\u0001H\u00D6\u0003J\t\u0010\u001B\u001A\u00020\u001CH\u00D6\u0001J\u001A\u0010\u001D\u001A\u00020\u000B2\n\u0010\u001E\u001A\u0006\u0012\u0002\b\u00030\u001F2\u0006\u0010\n\u001A\u00020\u0007J\t\u0010 \u001A\u00020!H\u00D6\u0001R\u0012\u0010\r\u001A\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001A\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u000F\u001A\u00020\u00108F\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0012\u0010\f\u001A\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R-\u0010\u0005\u001A\u001F\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001A\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\""}, d2 = {"Lkotlinx/coroutines/CompletedContinuation;", "", "result", "cancelHandler", "Lkotlinx/coroutines/CancelHandler;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "idempotentResume", "cancelCause", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelled", "", "getCancelled", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "invokeHandlers", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class CompletedContinuation {
    public final Throwable cancelCause;
    public final CancelHandler cancelHandler;
    public final Object idempotentResume;
    public final Function1 onCancellation;
    public final Object result;

    public CompletedContinuation(Object object0, CancelHandler cancelHandler0, Function1 function10, Object object1, Throwable throwable0) {
        this.result = object0;
        this.cancelHandler = cancelHandler0;
        this.onCancellation = function10;
        this.idempotentResume = object1;
        this.cancelCause = throwable0;
    }

    public CompletedContinuation(Object object0, CancelHandler cancelHandler0, Function1 function10, Object object1, Throwable throwable0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            cancelHandler0 = null;
        }
        if((v & 4) != 0) {
            function10 = null;
        }
        if((v & 8) != 0) {
            object1 = null;
        }
        this(object0, cancelHandler0, function10, object1, ((v & 16) == 0 ? throwable0 : null));
    }

    public final Object component1() {
        return this.result;
    }

    public final CancelHandler component2() {
        return this.cancelHandler;
    }

    public final Function1 component3() {
        return this.onCancellation;
    }

    public final Object component4() {
        return this.idempotentResume;
    }

    public final Throwable component5() {
        return this.cancelCause;
    }

    public final CompletedContinuation copy(Object object0, CancelHandler cancelHandler0, Function1 function10, Object object1, Throwable throwable0) {
        return new CompletedContinuation(object0, cancelHandler0, function10, object1, throwable0);
    }

    public static CompletedContinuation copy$default(CompletedContinuation completedContinuation0, Object object0, CancelHandler cancelHandler0, Function1 function10, Object object1, Throwable throwable0, int v, Object object2) {
        if((v & 1) != 0) {
            object0 = completedContinuation0.result;
        }
        if((v & 2) != 0) {
            cancelHandler0 = completedContinuation0.cancelHandler;
        }
        if((v & 4) != 0) {
            function10 = completedContinuation0.onCancellation;
        }
        if((v & 8) != 0) {
            object1 = completedContinuation0.idempotentResume;
        }
        if((v & 16) != 0) {
            throwable0 = completedContinuation0.cancelCause;
        }
        return completedContinuation0.copy(object0, cancelHandler0, function10, object1, throwable0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof CompletedContinuation)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.result, ((CompletedContinuation)object0).result)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.cancelHandler, ((CompletedContinuation)object0).cancelHandler)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.onCancellation, ((CompletedContinuation)object0).onCancellation)) {
            return false;
        }
        return Intrinsics.areEqual(this.idempotentResume, ((CompletedContinuation)object0).idempotentResume) ? Intrinsics.areEqual(this.cancelCause, ((CompletedContinuation)object0).cancelCause) : false;
    }

    public final boolean getCancelled() {
        return this.cancelCause != null;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.result == null ? 0 : this.result.hashCode();
        int v2 = this.cancelHandler == null ? 0 : this.cancelHandler.hashCode();
        int v3 = this.onCancellation == null ? 0 : this.onCancellation.hashCode();
        int v4 = this.idempotentResume == null ? 0 : this.idempotentResume.hashCode();
        Throwable throwable0 = this.cancelCause;
        if(throwable0 != null) {
            v = throwable0.hashCode();
        }
        return (((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v;
    }

    public final void invokeHandlers(CancellableContinuationImpl cancellableContinuationImpl0, Throwable throwable0) {
        CancelHandler cancelHandler0 = this.cancelHandler;
        if(cancelHandler0 != null) {
            cancellableContinuationImpl0.callCancelHandler(cancelHandler0, throwable0);
        }
        Function1 function10 = this.onCancellation;
        if(function10 != null) {
            cancellableContinuationImpl0.callOnCancellation(function10, throwable0);
        }
    }

    @Override
    public String toString() {
        return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=" + this.idempotentResume + ", cancelCause=" + this.cancelCause + ')';
    }
}

