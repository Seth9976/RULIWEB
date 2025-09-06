package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.internal.SafeCollector;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BE\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012-\u0010\u0004\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00028\u0000H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010\u000EJ\u0011\u0010\u000F\u001A\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R:\u0010\u0004\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000BR\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/flow/SubscribedFlowCollector;", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "action", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSubscription", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SubscribedFlowCollector implements FlowCollector {
    private final Function2 action;
    private final FlowCollector collector;

    public SubscribedFlowCollector(FlowCollector flowCollector0, Function2 function20) {
        this.collector = flowCollector0;
        this.action = function20;
    }

    @Override  // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object object0, Continuation continuation0) {
        return this.collector.emit(object0, continuation0);
    }

    public final Object onSubscription(Continuation continuation0) {
        SubscribedFlowCollector subscribedFlowCollector0;
        SafeCollector safeCollector0;
        kotlinx.coroutines.flow.SubscribedFlowCollector.onSubscription.1 subscribedFlowCollector$onSubscription$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.SubscribedFlowCollector.onSubscription.1) {
            subscribedFlowCollector$onSubscription$10 = (kotlinx.coroutines.flow.SubscribedFlowCollector.onSubscription.1)continuation0;
            if((subscribedFlowCollector$onSubscription$10.label & 0x80000000) == 0) {
                subscribedFlowCollector$onSubscription$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.onSubscription(this);
                    }
                };
            }
            else {
                subscribedFlowCollector$onSubscription$10.label ^= 0x80000000;
            }
        }
        else {
            subscribedFlowCollector$onSubscription$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.onSubscription(this);
                }
            };
        }
        Object object0 = subscribedFlowCollector$onSubscription$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(subscribedFlowCollector$onSubscription$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                CoroutineContext coroutineContext0 = subscribedFlowCollector$onSubscription$10.getContext();
                safeCollector0 = new SafeCollector(this.collector, coroutineContext0);
                try {
                    subscribedFlowCollector$onSubscription$10.L$0 = this;
                    subscribedFlowCollector$onSubscription$10.L$1 = safeCollector0;
                    subscribedFlowCollector$onSubscription$10.label = 1;
                    if(this.action.invoke(safeCollector0, subscribedFlowCollector$onSubscription$10) != object1) {
                        subscribedFlowCollector0 = this;
                        break;
                    }
                    return object1;
                }
                catch(Throwable throwable0) {
                    safeCollector0.releaseIntercepted();
                    throw throwable0;
                }
                subscribedFlowCollector0 = this;
                break;
            }
            case 1: {
                safeCollector0 = (SafeCollector)subscribedFlowCollector$onSubscription$10.L$1;
                subscribedFlowCollector0 = (SubscribedFlowCollector)subscribedFlowCollector$onSubscription$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(Throwable throwable0) {
                    safeCollector0.releaseIntercepted();
                    throw throwable0;
                }
            }
            case 2: {
                ResultKt.throwOnFailure(object0);
                return Unit.INSTANCE;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        safeCollector0.releaseIntercepted();
        FlowCollector flowCollector0 = subscribedFlowCollector0.collector;
        if(flowCollector0 instanceof SubscribedFlowCollector) {
            subscribedFlowCollector$onSubscription$10.L$0 = null;
            subscribedFlowCollector$onSubscription$10.L$1 = null;
            subscribedFlowCollector$onSubscription$10.label = 2;
            return ((SubscribedFlowCollector)flowCollector0).onSubscription(subscribedFlowCollector$onSubscription$10) == object1 ? object1 : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}

