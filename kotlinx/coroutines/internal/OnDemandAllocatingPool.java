package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001A\u00020\rJ\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u000FJ\r\u0010\u0010\u001A\u00020\u0011H\u0000¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001A\u00020\u0011H\u0016J\t\u0010\u0014\u001A\u00020\u0004H\u0082\bJ\r\u0010\u0015\u001A\u00020\r*\u00020\u0004H\u0082\bR\t\u0010\b\u001A\u00020\tX\u0082\u0004R\u001A\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000BX\u0082\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/internal/OnDemandAllocatingPool;", "T", "", "maxCapacity", "", "create", "Lkotlin/Function1;", "(ILkotlin/jvm/functions/Function1;)V", "controlState", "Lkotlinx/atomicfu/AtomicInt;", "elements", "Lkotlinx/atomicfu/AtomicArray;", "allocate", "", "close", "", "stateRepresentation", "", "stateRepresentation$kotlinx_coroutines_core", "toString", "tryForbidNewElements", "isClosed", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class OnDemandAllocatingPool {
    @Volatile
    private volatile int controlState;
    private static final AtomicIntegerFieldUpdater controlState$FU;
    private final Function1 create;
    private final AtomicReferenceArray elements;
    private final int maxCapacity;

    static {
        OnDemandAllocatingPool.controlState$FU = AtomicIntegerFieldUpdater.newUpdater(OnDemandAllocatingPool.class, "controlState");
    }

    public OnDemandAllocatingPool(int v, Function1 function10) {
        this.maxCapacity = v;
        this.create = function10;
        this.elements = new AtomicReferenceArray(v);
    }

    public final boolean allocate() {
        int v;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = OnDemandAllocatingPool.controlState$FU;
        do {
            v = atomicIntegerFieldUpdater0.get(this);
            if((0x80000000 & v) != 0) {
                return false;
            }
            if(v >= this.maxCapacity) {
                return true;
            }
        }
        while(!OnDemandAllocatingPool.controlState$FU.compareAndSet(this, v, v + 1));
        Object object0 = this.create.invoke(v);
        this.elements.set(v, object0);
        return true;
    }

    public final List close() {
        Object object0;
        int v;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = OnDemandAllocatingPool.controlState$FU;
        do {
            v = atomicIntegerFieldUpdater0.get(this);
            if((v & 0x80000000) != 0) {
                v = 0;
                break;
            }
        }
        while(!OnDemandAllocatingPool.controlState$FU.compareAndSet(this, v, 0x80000000 | v));
        Iterable iterable0 = RangesKt.until(0, v);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        Iterator iterator0 = iterable0.iterator();
        while(iterator0.hasNext()) {
            int v1 = ((IntIterator)iterator0).nextInt();
            do {
                object0 = this.elements.getAndSet(v1, null);
            }
            while(object0 == null);
            arrayList0.add(object0);
        }
        return arrayList0;
    }

    private final boolean isClosed(int v) {
        return (v & 0x80000000) != 0;
    }

    private final void loop$atomicfu(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicIntegerFieldUpdater0.get(object0));
        }
    }

    public final String stateRepresentation$kotlinx_coroutines_core() {
        int v = OnDemandAllocatingPool.controlState$FU.get(this);
        Iterable iterable0 = RangesKt.until(0, 0x7FFFFFFF & v);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        Iterator iterator0 = iterable0.iterator();
        while(iterator0.hasNext()) {
            int v1 = ((IntIterator)iterator0).nextInt();
            arrayList0.add(this.elements.get(v1));
        }
        String s = arrayList0.toString();
        return (v & 0x80000000) == 0 ? s + "" : s + "[closed]";
    }

    @Override
    public String toString() {
        return "OnDemandAllocatingPool(" + this.stateRepresentation$kotlinx_coroutines_core() + ')';
    }

    private final int tryForbidNewElements() {
        int v;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = OnDemandAllocatingPool.controlState$FU;
        do {
            v = atomicIntegerFieldUpdater0.get(this);
            if((v & 0x80000000) != 0) {
                return 0;
            }
        }
        while(!OnDemandAllocatingPool.controlState$FU.compareAndSet(this, v, 0x80000000 | v));
        return v;
    }
}

