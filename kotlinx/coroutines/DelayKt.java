package kotlinx.coroutines;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A\u0011\u0010\u0005\u001A\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A\u0019\u0010\u0000\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001A!\u0010\u0000\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\rH\u0086@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u000E\u0010\u000B\u001A\u0019\u0010\u000F\u001A\u00020\n*\u00020\rH\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\"\u0018\u0010\u0000\u001A\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\u0082\u0002\u000B\n\u0002\b\u0019\n\u0005\b¡\u001E0\u0001¨\u0006\u0012"}, d2 = {"delay", "Lkotlinx/coroutines/Delay;", "Lkotlin/coroutines/CoroutineContext;", "getDelay", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Delay;", "awaitCancellation", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "timeMillis", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "duration", "Lkotlin/time/Duration;", "delay-VtjQ1oo", "toDelayMillis", "toDelayMillis-LRDsOJo", "(J)J", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DelayKt {
    public static final Object awaitCancellation(Continuation continuation0) {
        kotlinx.coroutines.DelayKt.awaitCancellation.1 delayKt$awaitCancellation$10;
        if(continuation0 instanceof kotlinx.coroutines.DelayKt.awaitCancellation.1) {
            delayKt$awaitCancellation$10 = (kotlinx.coroutines.DelayKt.awaitCancellation.1)continuation0;
            if((delayKt$awaitCancellation$10.label & 0x80000000) == 0) {
                delayKt$awaitCancellation$10 = new ContinuationImpl(continuation0) {
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return DelayKt.awaitCancellation(this);
                    }
                };
            }
            else {
                delayKt$awaitCancellation$10.label ^= 0x80000000;
            }
        }
        else {
            delayKt$awaitCancellation$10 = new ContinuationImpl(continuation0) {
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return DelayKt.awaitCancellation(this);
                }
            };
        }
        Object object0 = delayKt$awaitCancellation$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(delayKt$awaitCancellation$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                delayKt$awaitCancellation$10.label = 1;
                CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(delayKt$awaitCancellation$10), 1);
                cancellableContinuationImpl0.initCancellability();
                Object object2 = cancellableContinuationImpl0.getResult();
                if(object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(delayKt$awaitCancellation$10);
                }
                if(object2 == object1) {
                    return object1;
                }
                break;
            }
            case 1: {
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        throw new KotlinNothingValueException();
    }

    public static final Object delay(long v, Continuation continuation0) {
        if(v <= 0L) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        if(v < 0x7FFFFFFFFFFFFFFFL) {
            DelayKt.getDelay(cancellableContinuationImpl0.getContext()).scheduleResumeAfterDelay(v, cancellableContinuationImpl0);
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public static final Object delay-VtjQ1oo(long v, Continuation continuation0) {
        Object object0 = DelayKt.delay(DelayKt.toDelayMillis-LRDsOJo(v), continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public static final Delay getDelay(CoroutineContext coroutineContext0) {
        Element coroutineContext$Element0 = coroutineContext0.get(ContinuationInterceptor.Key);
        Delay delay0 = coroutineContext$Element0 instanceof Delay ? ((Delay)coroutineContext$Element0) : null;
        return delay0 == null ? DefaultExecutorKt.getDefaultDelay() : delay0;
    }

    // 去混淆评级： 低(20)
    public static final long toDelayMillis-LRDsOJo(long v) {
        return Duration.compareTo-LRDsOJo(v, 0L) <= 0 ? 0L : RangesKt.coerceAtLeast(Duration.getInWholeMilliseconds-impl(v), 1L);
    }
}

