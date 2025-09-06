package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Be\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0014\u0010\u0004\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012:\u0010\u0007\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0002\u0010\u000EJ\u001F\u0010\u000F\u001A\u00020\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013RD\u0010\u0007\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000B\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001E\u0010\u0004\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/flow/DistinctFlowImpl;", "T", "Lkotlinx/coroutines/flow/Flow;", "upstream", "keySelector", "Lkotlin/Function1;", "", "areEquivalent", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "old", "new", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class DistinctFlowImpl implements Flow {
    public final Function2 areEquivalent;
    public final Function1 keySelector;
    private final Flow upstream;

    public DistinctFlowImpl(Flow flow0, Function1 function10, Function2 function20) {
        this.upstream = flow0;
        this.keySelector = function10;
        this.areEquivalent = function20;
    }

    @Override  // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        ref$ObjectRef0.element = NullSurrogateKt.NULL;
        FlowCollector flowCollector1 = new FlowCollector() {
            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.DistinctFlowImpl.collect.2.emit.1 distinctFlowImpl$collect$2$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.DistinctFlowImpl.collect.2.emit.1) {
                    distinctFlowImpl$collect$2$emit$10 = (kotlinx.coroutines.flow.DistinctFlowImpl.collect.2.emit.1)continuation0;
                    if((distinctFlowImpl$collect$2$emit$10.label & 0x80000000) == 0) {
                        distinctFlowImpl$collect$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        distinctFlowImpl$collect$2$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    distinctFlowImpl$collect$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = distinctFlowImpl$collect$2$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(distinctFlowImpl$collect$2$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        Object object3 = ref$ObjectRef0.keySelector.invoke(object0);
                        if(flowCollector0.element != NullSurrogateKt.NULL && ((Boolean)ref$ObjectRef0.areEquivalent.invoke(flowCollector0.element, object3)).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                        flowCollector0.element = object3;
                        distinctFlowImpl$collect$2$emit$10.label = 1;
                        return this.$collector.emit(object0, distinctFlowImpl$collect$2$emit$10) == object2 ? object2 : Unit.INSTANCE;
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
        Object object0 = this.upstream.collect(flowCollector1, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }
}

