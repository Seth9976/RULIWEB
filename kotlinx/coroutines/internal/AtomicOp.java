package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.Volatile;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b\'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001F\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00028\u00002\b\u0010\r\u001A\u0004\u0018\u00010\u0006H&¢\u0006\u0002\u0010\u000EJ\u0014\u0010\u000F\u001A\u0004\u0018\u00010\u00062\b\u0010\u0010\u001A\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u0011\u001A\u0004\u0018\u00010\u00062\b\u0010\f\u001A\u0004\u0018\u00010\u0006J\u0017\u0010\u0012\u001A\u0004\u0018\u00010\u00062\u0006\u0010\f\u001A\u00028\u0000H&¢\u0006\u0002\u0010\u0013R\u0011\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004R\u0018\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\u00008VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/internal/AtomicOp;", "T", "Lkotlinx/coroutines/internal/OpDescriptor;", "()V", "_consensus", "Lkotlinx/atomicfu/AtomicRef;", "", "atomicOp", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "complete", "", "affected", "failure", "(Ljava/lang/Object;Ljava/lang/Object;)V", "decide", "decision", "perform", "prepare", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class AtomicOp extends OpDescriptor {
    @Volatile
    private volatile Object _consensus;
    private static final AtomicReferenceFieldUpdater _consensus$FU;

    static {
        AtomicOp._consensus$FU = AtomicReferenceFieldUpdater.newUpdater(AtomicOp.class, Object.class, "_consensus");
    }

    public AtomicOp() {
        this._consensus = AtomicKt.NO_DECISION;
    }

    public abstract void complete(Object arg1, Object arg2);

    private final Object decide(Object object0) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = AtomicOp._consensus$FU;
        Object object1 = atomicReferenceFieldUpdater0.get(this);
        if(object1 != AtomicKt.NO_DECISION) {
            return object1;
        }
        return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, AtomicKt.NO_DECISION, object0) ? object0 : atomicReferenceFieldUpdater0.get(this);
    }

    @Override  // kotlinx.coroutines.internal.OpDescriptor
    public AtomicOp getAtomicOp() {
        return this;
    }

    @Override  // kotlinx.coroutines.internal.OpDescriptor
    public final Object perform(Object object0) {
        Object object1 = AtomicOp._consensus$FU.get(this);
        if(object1 == AtomicKt.NO_DECISION) {
            object1 = this.decide(this.prepare(object0));
        }
        this.complete(object0, object1);
        return object1;
    }

    public abstract Object prepare(Object arg1);
}

