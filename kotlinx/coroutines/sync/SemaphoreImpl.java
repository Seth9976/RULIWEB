package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelSegment..ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0005J\u0011\u0010\u0016\u001A\u00020\u0014H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0017Jb\u0010\u0016\u001A\u00020\u0014\"\u0004\b\u0000\u0010\u00182\u0006\u0010\u0019\u001A\u0002H\u00182!\u0010\u001A\u001A\u001D\u0012\u0013\u0012\u0011H\u0018\u00A2\u0006\f\b\u001B\u0012\b\b\u001C\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001D0\u00122!\u0010\u001E\u001A\u001D\u0012\u0013\u0012\u0011H\u0018\u00A2\u0006\f\b\u001B\u0012\b\b\u001C\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0012H\u0083\b\u00A2\u0006\u0002\u0010\u001FJ\u0016\u0010\u0016\u001A\u00020\u00142\f\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\u00140 H\u0005J\u0011\u0010!\u001A\u00020\u0014H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0017J\u0010\u0010\"\u001A\u00020\u001D2\u0006\u0010\u0019\u001A\u00020#H\u0002J\b\u0010$\u001A\u00020\u0014H\u0002J\b\u0010%\u001A\u00020\u0003H\u0002J\u001E\u0010&\u001A\u00020\u00142\n\u0010\'\u001A\u0006\u0012\u0002\b\u00030(2\b\u0010)\u001A\u0004\u0018\u00010*H\u0004J\b\u0010+\u001A\u00020\u0014H\u0016J\b\u0010,\u001A\u00020\u001DH\u0016J\b\u0010-\u001A\u00020\u001DH\u0002J\f\u0010.\u001A\u00020\u001D*\u00020*H\u0002R\t\u0010\u0006\u001A\u00020\u0007X\u0082\u0004R\u0014\u0010\b\u001A\u00020\u00038VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\t\u0010\u000B\u001A\u00020\fX\u0082\u0004R\t\u0010\r\u001A\u00020\fX\u0082\u0004R\u000F\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000FX\u0082\u0004R\u001A\u0010\u0011\u001A\u000E\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00100\u000FX\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006/"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "Lkotlinx/coroutines/sync/Semaphore;", "permits", "", "acquiredPermits", "(II)V", "_availablePermits", "Lkotlinx/atomicfu/AtomicInt;", "availablePermits", "getAvailablePermits", "()I", "deqIdx", "Lkotlinx/atomicfu/AtomicLong;", "enqIdx", "head", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "onCancellationRelease", "Lkotlin/Function1;", "", "", "tail", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "W", "waiter", "suspend", "Lkotlin/ParameterName;", "name", "", "onAcquired", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/CancellableContinuation;", "acquireSlowPath", "addAcquireToQueue", "Lkotlinx/coroutines/Waiter;", "coerceAvailablePermitsAtMaximum", "decPermits", "onAcquireRegFunction", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "ignoredParam", "", "release", "tryAcquire", "tryResumeNextFromQueue", "tryResumeAcquire", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class SemaphoreImpl implements Semaphore {
    @Volatile
    private volatile int _availablePermits;
    private static final AtomicIntegerFieldUpdater _availablePermits$FU;
    @Volatile
    private volatile long deqIdx;
    private static final AtomicLongFieldUpdater deqIdx$FU;
    @Volatile
    private volatile long enqIdx;
    private static final AtomicLongFieldUpdater enqIdx$FU;
    @Volatile
    private volatile Object head;
    private static final AtomicReferenceFieldUpdater head$FU;
    private final Function1 onCancellationRelease;
    private final int permits;
    @Volatile
    private volatile Object tail;
    private static final AtomicReferenceFieldUpdater tail$FU;

    static {
        SemaphoreImpl.head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
        SemaphoreImpl.deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
        SemaphoreImpl.tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
        SemaphoreImpl.enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
        SemaphoreImpl._availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");
    }

    public SemaphoreImpl(int v, int v1) {
        this.permits = v;
        if(v <= 0) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + v).toString());
        }
        if(v1 < 0 || v1 > v) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + v).toString());
        }
        SemaphoreSegment semaphoreSegment0 = new SemaphoreSegment(0L, null, 2);
        this.head = semaphoreSegment0;
        this.tail = semaphoreSegment0;
        this._availablePermits = v - v1;
        this.onCancellationRelease = new Function1() {
            {
                SemaphoreImpl.this = semaphoreImpl0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                SemaphoreImpl.this.release();
            }
        };
    }

    public static final Object access$acquireSlowPath(SemaphoreImpl semaphoreImpl0, Continuation continuation0) {
        return semaphoreImpl0.acquireSlowPath(continuation0);
    }

    private final void acquire(Object object0, Function1 function10, Function1 function11) {
        do {
            if(this.decPermits() > 0) {
                function11.invoke(object0);
                return;
            }
        }
        while(!((Boolean)function10.invoke(object0)).booleanValue());
    }

    @Override  // kotlinx.coroutines.sync.Semaphore
    public Object acquire(Continuation continuation0) {
        return SemaphoreImpl.acquire$suspendImpl(this, continuation0);
    }

    protected final void acquire(CancellableContinuation cancellableContinuation0) {
        do {
            if(this.decPermits() > 0) {
                cancellableContinuation0.resume(Unit.INSTANCE, this.onCancellationRelease);
                return;
            }
            Intrinsics.checkNotNull(cancellableContinuation0, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
        }
        while(!this.addAcquireToQueue(((Waiter)cancellableContinuation0)));
    }

    static Object acquire$suspendImpl(SemaphoreImpl semaphoreImpl0, Continuation continuation0) {
        if(semaphoreImpl0.decPermits() > 0) {
            return Unit.INSTANCE;
        }
        Object object0 = semaphoreImpl0.acquireSlowPath(continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    private final Object acquireSlowPath(Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation0));
        try {
            if(!this.addAcquireToQueue(cancellableContinuationImpl0)) {
                this.acquire(cancellableContinuationImpl0);
            }
        }
        catch(Throwable throwable0) {
            cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw throwable0;
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    private final boolean addAcquireToQueue(Waiter waiter0) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = SemaphoreImpl.tail$FU;
        SemaphoreSegment semaphoreSegment0 = (SemaphoreSegment)atomicReferenceFieldUpdater0.get(this);
        long v = SemaphoreImpl.enqIdx$FU.getAndIncrement(this);
        KFunction kFunction0 = kotlinx.coroutines.sync.SemaphoreImpl.addAcquireToQueue.createNewSegment.1.INSTANCE;
    alab1:
        while(true) {
            object0 = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment0, v / 16L, ((Function2)kFunction0));
            if(SegmentOrClosed.isClosed-impl(object0)) {
                break;
            }
            Segment segment0 = SegmentOrClosed.getSegment-impl(object0);
            while(true) {
                Segment segment1 = (Segment)atomicReferenceFieldUpdater0.get(this);
                if(segment1.id >= segment0.id) {
                    break alab1;
                }
                if(!segment0.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, segment1, segment0)) {
                    if(!segment1.decPointers$kotlinx_coroutines_core()) {
                        break alab1;
                    }
                    segment1.remove();
                    break alab1;
                }
                if(segment0.decPointers$kotlinx_coroutines_core()) {
                    segment0.remove();
                }
            }
        }
        SemaphoreSegment semaphoreSegment1 = (SemaphoreSegment)SegmentOrClosed.getSegment-impl(object0);
        if(ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment1.getAcquirers(), ((int)(v % 16L)), null, waiter0)) {
            waiter0.invokeOnCancellation(semaphoreSegment1, ((int)(v % 16L)));
            return true;
        }
        if(ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment1.getAcquirers(), ((int)(v % 16L)), SemaphoreKt.access$getPERMIT$p(), SemaphoreKt.access$getTAKEN$p())) {
            if(waiter0 instanceof CancellableContinuation) {
                Intrinsics.checkNotNull(waiter0, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
                ((CancellableContinuation)waiter0).resume(Unit.INSTANCE, this.onCancellationRelease);
                return true;
            }
            if(!(waiter0 instanceof SelectInstance)) {
                throw new IllegalStateException(("unexpected: " + waiter0).toString());
            }
            ((SelectInstance)waiter0).selectInRegistrationPhase(Unit.INSTANCE);
            return true;
        }
        return false;

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.sync.SemaphoreImpl.addAcquireToQueue.createNewSegment.1 extends FunctionReferenceImpl implements Function2 {
            public static final kotlinx.coroutines.sync.SemaphoreImpl.addAcquireToQueue.createNewSegment.1 INSTANCE;

            static {
                kotlinx.coroutines.sync.SemaphoreImpl.addAcquireToQueue.createNewSegment.1.INSTANCE = new kotlinx.coroutines.sync.SemaphoreImpl.addAcquireToQueue.createNewSegment.1();
            }

            kotlinx.coroutines.sync.SemaphoreImpl.addAcquireToQueue.createNewSegment.1() {
                super(2, SemaphoreKt.class, "createSegment", "createSegment(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", 1);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((Number)object0).longValue(), ((SemaphoreSegment)object1));
            }

            public final SemaphoreSegment invoke(long v, SemaphoreSegment semaphoreSegment0) {
                return SemaphoreKt.access$createSegment(v, semaphoreSegment0);
            }
        }

    }

    private final void coerceAvailablePermitsAtMaximum() {
        do {
            int v = SemaphoreImpl._availablePermits$FU.get(this);
        }
        while(v > this.permits && !SemaphoreImpl._availablePermits$FU.compareAndSet(this, v, this.permits));
    }

    private final int decPermits() {
        int v;
        do {
            v = SemaphoreImpl._availablePermits$FU.getAndDecrement(this);
        }
        while(v > this.permits);
        return v;
    }

    @Override  // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(SemaphoreImpl._availablePermits$FU.get(this), 0);
    }

    protected final void onAcquireRegFunction(SelectInstance selectInstance0, Object object0) {
        do {
            if(this.decPermits() > 0) {
                selectInstance0.selectInRegistrationPhase(Unit.INSTANCE);
                return;
            }
            Intrinsics.checkNotNull(selectInstance0, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
        }
        while(!this.addAcquireToQueue(((Waiter)selectInstance0)));
    }

    @Override  // kotlinx.coroutines.sync.Semaphore
    public void release() {
        int v;
        while((v = SemaphoreImpl._availablePermits$FU.getAndIncrement(this)) < this.permits) {
            if(v >= 0 || this.tryResumeNextFromQueue()) {
                return;
            }
        }
        this.coerceAvailablePermitsAtMaximum();
        throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
    }

    @Override  // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        while(true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = SemaphoreImpl._availablePermits$FU;
            int v = atomicIntegerFieldUpdater0.get(this);
            if(v > this.permits) {
                this.coerceAvailablePermitsAtMaximum();
            }
            else {
                if(v <= 0) {
                    return false;
                }
                if(atomicIntegerFieldUpdater0.compareAndSet(this, v, v - 1)) {
                    break;
                }
            }
        }
        return true;
    }

    private final boolean tryResumeAcquire(Object object0) {
        if(object0 instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            Object object1 = ((CancellableContinuation)object0).tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
            if(object1 != null) {
                ((CancellableContinuation)object0).completeResume(object1);
                return true;
            }
            return false;
        }
        if(!(object0 instanceof SelectInstance)) {
            throw new IllegalStateException(("unexpected: " + object0).toString());
        }
        return ((SelectInstance)object0).trySelect(this, Unit.INSTANCE);
    }

    private final boolean tryResumeNextFromQueue() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = SemaphoreImpl.head$FU;
        SemaphoreSegment semaphoreSegment0 = (SemaphoreSegment)atomicReferenceFieldUpdater0.get(this);
        long v = SemaphoreImpl.deqIdx$FU.getAndIncrement(this);
        KFunction kFunction0 = kotlinx.coroutines.sync.SemaphoreImpl.tryResumeNextFromQueue.createNewSegment.1.INSTANCE;
    alab1:
        while(true) {
            object0 = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment0, v / 16L, ((Function2)kFunction0));
            if(SegmentOrClosed.isClosed-impl(object0)) {
                break;
            }
            Segment segment0 = SegmentOrClosed.getSegment-impl(object0);
            while(true) {
                Segment segment1 = (Segment)atomicReferenceFieldUpdater0.get(this);
                if(segment1.id >= segment0.id) {
                    break alab1;
                }
                if(!segment0.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, segment1, segment0)) {
                    if(!segment1.decPointers$kotlinx_coroutines_core()) {
                        break alab1;
                    }
                    segment1.remove();
                    break alab1;
                }
                if(segment0.decPointers$kotlinx_coroutines_core()) {
                    segment0.remove();
                }
            }
        }
        SemaphoreSegment semaphoreSegment1 = (SemaphoreSegment)SegmentOrClosed.getSegment-impl(object0);
        semaphoreSegment1.cleanPrev();
        if(semaphoreSegment1.id > v / 16L) {
            return false;
        }
        Object object1 = semaphoreSegment1.getAcquirers().getAndSet(((int)(v % 16L)), SemaphoreKt.PERMIT);
        if(object1 == null) {
            for(int v1 = 0; v1 < 100; ++v1) {
                if(semaphoreSegment1.getAcquirers().get(((int)(v % 16L))) == SemaphoreKt.TAKEN) {
                    return true;
                }
            }
            return !ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment1.getAcquirers(), ((int)(v % 16L)), SemaphoreKt.PERMIT, SemaphoreKt.BROKEN);
        }
        return object1 == SemaphoreKt.CANCELLED ? false : this.tryResumeAcquire(object1);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.sync.SemaphoreImpl.tryResumeNextFromQueue.createNewSegment.1 extends FunctionReferenceImpl implements Function2 {
            public static final kotlinx.coroutines.sync.SemaphoreImpl.tryResumeNextFromQueue.createNewSegment.1 INSTANCE;

            static {
                kotlinx.coroutines.sync.SemaphoreImpl.tryResumeNextFromQueue.createNewSegment.1.INSTANCE = new kotlinx.coroutines.sync.SemaphoreImpl.tryResumeNextFromQueue.createNewSegment.1();
            }

            kotlinx.coroutines.sync.SemaphoreImpl.tryResumeNextFromQueue.createNewSegment.1() {
                super(2, SemaphoreKt.class, "createSegment", "createSegment(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", 1);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((Number)object0).longValue(), ((SemaphoreSegment)object1));
            }

            public final SemaphoreSegment invoke(long v, SemaphoreSegment semaphoreSegment0) {
                return SemaphoreKt.createSegment(v, semaphoreSegment0);
            }
        }

    }
}

