package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.internal.SafeCollector;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001F\u0010\u0005\u001A\u00020\u00062\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001F\u0010\n\u001A\u00020\u00062\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/flow/AbstractFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "()V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectSafely", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class AbstractFlow implements CancellableFlow, Flow {
    @Override  // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        SafeCollector safeCollector1;
        Throwable throwable1;
        kotlinx.coroutines.flow.AbstractFlow.collect.1 abstractFlow$collect$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.AbstractFlow.collect.1) {
            abstractFlow$collect$10 = (kotlinx.coroutines.flow.AbstractFlow.collect.1)continuation0;
            if((abstractFlow$collect$10.label & 0x80000000) == 0) {
                abstractFlow$collect$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.collect(null, this);
                    }
                };
            }
            else {
                abstractFlow$collect$10.label ^= 0x80000000;
            }
        }
        else {
            abstractFlow$collect$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.collect(null, this);
                }
            };
        }
        Object object0 = abstractFlow$collect$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(abstractFlow$collect$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                SafeCollector safeCollector0 = new SafeCollector(flowCollector0, abstractFlow$collect$10.getContext());
                try {
                    abstractFlow$collect$10.L$0 = safeCollector0;
                    abstractFlow$collect$10.label = 1;
                    if(this.collectSafely(safeCollector0, abstractFlow$collect$10) == object1) {
                        return object1;
                    }
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    safeCollector1 = safeCollector0;
                    safeCollector1.releaseIntercepted();
                    throw throwable1;
                }
                safeCollector1 = safeCollector0;
                break;
            }
            case 1: {
                safeCollector1 = (SafeCollector)abstractFlow$collect$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(Throwable throwable1) {
                }
                safeCollector1.releaseIntercepted();
                throw throwable1;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        safeCollector1.releaseIntercepted();
        return Unit.INSTANCE;
    }

    public abstract Object collectSafely(FlowCollector arg1, Continuation arg2);
}

