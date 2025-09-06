package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001F\u0010\u0006\u001A\u00020\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nR\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/flow/CancellableFlowImpl;", "T", "Lkotlinx/coroutines/flow/CancellableFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class CancellableFlowImpl implements CancellableFlow {
    private final Flow flow;

    public CancellableFlowImpl(Flow flow0) {
        this.flow = flow0;
    }

    @Override  // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        FlowCollector flowCollector1 = new FlowCollector() {
            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.CancellableFlowImpl.collect.2.emit.1 cancellableFlowImpl$collect$2$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.CancellableFlowImpl.collect.2.emit.1) {
                    cancellableFlowImpl$collect$2$emit$10 = (kotlinx.coroutines.flow.CancellableFlowImpl.collect.2.emit.1)continuation0;
                    if((cancellableFlowImpl$collect$2$emit$10.label & 0x80000000) == 0) {
                        cancellableFlowImpl$collect$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            int label;
                            Object result;

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                this.result = object0;
                                this.label |= 0x80000000;
                                return continuation0.emit(null, this);
                            }
                        };
                    }
                    else {
                        cancellableFlowImpl$collect$2$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    cancellableFlowImpl$collect$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        int label;
                        Object result;

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            this.result = object0;
                            this.label |= 0x80000000;
                            return continuation0.emit(null, this);
                        }
                    };
                }
                Object object1 = cancellableFlowImpl$collect$2$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(cancellableFlowImpl$collect$2$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        JobKt.ensureActive(cancellableFlowImpl$collect$2$emit$10.getContext());
                        cancellableFlowImpl$collect$2$emit$10.label = 1;
                        return this.$collector.emit(object0, cancellableFlowImpl$collect$2$emit$10) == object2 ? object2 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object1);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        };
        Object object0 = this.flow.collect(flowCollector1, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }
}

