package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B2\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0001\u0012!\u0010\u0003\u001A\u001D\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\u0002\u0010\nJ\u000B\u0010\u000B\u001A\u0004\u0018\u00010\u0001HÆ\u0003J$\u0010\f\u001A\u001D\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004HÆ\u0003J:\u0010\r\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00012#\b\u0002\u0010\u0003\u001A\u001D\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004HÆ\u0001J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001A\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001A\u00020\u0014HÖ\u0001R+\u0010\u0003\u001A\u001D\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001A\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/CompletedWithCancellation;", "", "result", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CompletedWithCancellation {
    public final Function1 onCancellation;
    public final Object result;

    public CompletedWithCancellation(Object object0, Function1 function10) {
        this.result = object0;
        this.onCancellation = function10;
    }

    public final Object component1() {
        return this.result;
    }

    public final Function1 component2() {
        return this.onCancellation;
    }

    public final CompletedWithCancellation copy(Object object0, Function1 function10) {
        return new CompletedWithCancellation(object0, function10);
    }

    public static CompletedWithCancellation copy$default(CompletedWithCancellation completedWithCancellation0, Object object0, Function1 function10, int v, Object object1) {
        if((v & 1) != 0) {
            object0 = completedWithCancellation0.result;
        }
        if((v & 2) != 0) {
            function10 = completedWithCancellation0.onCancellation;
        }
        return completedWithCancellation0.copy(object0, function10);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof CompletedWithCancellation)) {
            return false;
        }
        return Intrinsics.areEqual(this.result, ((CompletedWithCancellation)object0).result) ? Intrinsics.areEqual(this.onCancellation, ((CompletedWithCancellation)object0).onCancellation) : false;
    }

    @Override
    public int hashCode() {
        return this.result == null ? this.onCancellation.hashCode() : this.result.hashCode() * 0x1F + this.onCancellation.hashCode();
    }

    @Override
    public String toString() {
        return "CompletedWithCancellation(result=" + this.result + ", onCancellation=" + this.onCancellation + ')';
    }
}

