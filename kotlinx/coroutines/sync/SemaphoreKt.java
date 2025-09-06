package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0018\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u00042\b\b\u0002\u0010\u000B\u001A\u00020\u0004\u001A\u001A\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\rH\u0002\u001A6\u0010\u0011\u001A\u0002H\u0012\"\u0004\b\u0000\u0010\u0012*\u00020\t2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0086Hø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0015\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"BROKEN", "Lkotlinx/coroutines/internal/Symbol;", "CANCELLED", "MAX_SPIN_CYCLES", "", "PERMIT", "SEGMENT_SIZE", "TAKEN", "Semaphore", "Lkotlinx/coroutines/sync/Semaphore;", "permits", "acquiredPermits", "createSegment", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "id", "", "prev", "withPermit", "T", "action", "Lkotlin/Function0;", "(Lkotlinx/coroutines/sync/Semaphore;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SemaphoreKt {
    private static final Symbol BROKEN;
    private static final Symbol CANCELLED;
    private static final int MAX_SPIN_CYCLES;
    private static final Symbol PERMIT;
    private static final int SEGMENT_SIZE;
    private static final Symbol TAKEN;

    static {
        SemaphoreKt.MAX_SPIN_CYCLES = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, null);
        SemaphoreKt.PERMIT = new Symbol("PERMIT");
        SemaphoreKt.TAKEN = new Symbol("TAKEN");
        SemaphoreKt.BROKEN = new Symbol("BROKEN");
        SemaphoreKt.CANCELLED = new Symbol("CANCELLED");
        SemaphoreKt.SEGMENT_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, null);
    }

    public static final Semaphore Semaphore(int v, int v1) {
        return new SemaphoreImpl(v, v1);
    }

    public static Semaphore Semaphore$default(int v, int v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v1 = 0;
        }
        return SemaphoreKt.Semaphore(v, v1);
    }

    public static final int access$getMAX_SPIN_CYCLES$p() [...] // 潜在的解密器

    public static final int access$getSEGMENT_SIZE$p() [...] // 潜在的解密器

    private static final SemaphoreSegment createSegment(long v, SemaphoreSegment semaphoreSegment0) {
        return new SemaphoreSegment(v, semaphoreSegment0, 0);
    }

    public static final Object withPermit(Semaphore semaphore0, Function0 function00, Continuation continuation0) {
        kotlinx.coroutines.sync.SemaphoreKt.withPermit.1 semaphoreKt$withPermit$10;
        if(continuation0 instanceof kotlinx.coroutines.sync.SemaphoreKt.withPermit.1) {
            semaphoreKt$withPermit$10 = (kotlinx.coroutines.sync.SemaphoreKt.withPermit.1)continuation0;
            if((semaphoreKt$withPermit$10.label & 0x80000000) == 0) {
                semaphoreKt$withPermit$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return SemaphoreKt.withPermit(null, null, this);
                    }
                };
            }
            else {
                semaphoreKt$withPermit$10.label ^= 0x80000000;
            }
        }
        else {
            semaphoreKt$withPermit$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return SemaphoreKt.withPermit(null, null, this);
                }
            };
        }
        Object object0 = semaphoreKt$withPermit$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(semaphoreKt$withPermit$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                semaphoreKt$withPermit$10.L$0 = semaphore0;
                semaphoreKt$withPermit$10.L$1 = function00;
                semaphoreKt$withPermit$10.label = 1;
                if(semaphore0.acquire(semaphoreKt$withPermit$10) == object1) {
                    return object1;
                }
                break;
            }
            case 1: {
                function00 = (Function0)semaphoreKt$withPermit$10.L$1;
                semaphore0 = (Semaphore)semaphoreKt$withPermit$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            return function00.invoke();
        }
        finally {
            semaphore0.release();
        }
    }

    private static final Object withPermit$$forInline(Semaphore semaphore0, Function0 function00, Continuation continuation0) {
        semaphore0.acquire(continuation0);
        try {
            return function00.invoke();
        }
        finally {
            semaphore0.release();
        }
    }
}

