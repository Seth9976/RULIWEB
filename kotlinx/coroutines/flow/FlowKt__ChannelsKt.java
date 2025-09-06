package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u001E\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001A\u001C\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001A/\u0010\u0006\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001A9\u0010\u000B\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\f\u001A\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000E\u0010\u000F\u001A$\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0011\u001A\u00020\u0012\u001A\u001C\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Lkotlinx/coroutines/channels/BroadcastChannel;", "consumeAsFlow", "Lkotlinx/coroutines/channels/ReceiveChannel;", "emitAll", "", "Lkotlinx/coroutines/flow/FlowCollector;", "channel", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitAllImpl", "consume", "", "emitAllImpl$FlowKt__ChannelsKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "produceIn", "scope", "Lkotlinx/coroutines/CoroutineScope;", "receiveAsFlow", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__ChannelsKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "\'BroadcastChannel\' is obsolete and all corresponding operators are deprecated in the favour of StateFlow and SharedFlow")
    public static final Flow asFlow(BroadcastChannel broadcastChannel0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Object object0 = FlowKt.emitAll(flowCollector0, broadcastChannel0.openSubscription(), continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow consumeAsFlow(ReceiveChannel receiveChannel0) {
        return new ChannelAsFlow(receiveChannel0, true, null, 0, null, 28, null);
    }

    public static final Object emitAll(FlowCollector flowCollector0, ReceiveChannel receiveChannel0, Continuation continuation0) {
        Object object0 = FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(flowCollector0, receiveChannel0, true, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    private static final Object emitAllImpl$FlowKt__ChannelsKt(FlowCollector flowCollector0, ReceiveChannel receiveChannel0, boolean z, Continuation continuation0) {
        FlowCollector flowCollector1;
        ChannelIterator channelIterator1;
        ChannelIterator channelIterator0;
        kotlinx.coroutines.flow.FlowKt__ChannelsKt.emitAllImpl.1 flowKt__ChannelsKt$emitAllImpl$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ChannelsKt.emitAllImpl.1) {
            flowKt__ChannelsKt$emitAllImpl$10 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt.emitAllImpl.1)continuation0;
            if((flowKt__ChannelsKt$emitAllImpl$10.label & 0x80000000) == 0) {
                flowKt__ChannelsKt$emitAllImpl$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    boolean Z$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(null, null, false, this);
                    }
                };
            }
            else {
                flowKt__ChannelsKt$emitAllImpl$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ChannelsKt$emitAllImpl$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                boolean Z$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(null, null, false, this);
                }
            };
        }
        Object object0 = flowKt__ChannelsKt$emitAllImpl$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ChannelsKt$emitAllImpl$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                FlowKt.ensureActive(flowCollector0);
                try {
                    channelIterator0 = receiveChannel0.iterator();
                    goto label_29;
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            case 1: {
                z = flowKt__ChannelsKt$emitAllImpl$10.Z$0;
                channelIterator1 = (ChannelIterator)flowKt__ChannelsKt$emitAllImpl$10.L$2;
                receiveChannel0 = (ReceiveChannel)flowKt__ChannelsKt$emitAllImpl$10.L$1;
                flowCollector1 = (FlowCollector)flowKt__ChannelsKt$emitAllImpl$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    goto label_39;
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            case 2: {
                z = flowKt__ChannelsKt$emitAllImpl$10.Z$0;
                channelIterator1 = (ChannelIterator)flowKt__ChannelsKt$emitAllImpl$10.L$2;
                receiveChannel0 = (ReceiveChannel)flowKt__ChannelsKt$emitAllImpl$10.L$1;
                flowCollector1 = (FlowCollector)flowKt__ChannelsKt$emitAllImpl$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                        channelIterator0 = channelIterator1;
                        flowCollector0 = flowCollector1;
                    label_29:
                        flowKt__ChannelsKt$emitAllImpl$10.L$0 = flowCollector0;
                        flowKt__ChannelsKt$emitAllImpl$10.L$1 = receiveChannel0;
                        flowKt__ChannelsKt$emitAllImpl$10.L$2 = channelIterator0;
                        flowKt__ChannelsKt$emitAllImpl$10.Z$0 = z;
                        flowKt__ChannelsKt$emitAllImpl$10.label = 1;
                        Object object2 = channelIterator0.hasNext(flowKt__ChannelsKt$emitAllImpl$10);
                        if(object2 != object1) {
                            flowCollector1 = flowCollector0;
                            channelIterator1 = channelIterator0;
                            object0 = object2;
                        label_39:
                            if(!((Boolean)object0).booleanValue()) {
                                goto label_52;
                            }
                            Object object3 = channelIterator1.next();
                            flowKt__ChannelsKt$emitAllImpl$10.L$0 = flowCollector1;
                            flowKt__ChannelsKt$emitAllImpl$10.L$1 = receiveChannel0;
                            flowKt__ChannelsKt$emitAllImpl$10.L$2 = channelIterator1;
                            flowKt__ChannelsKt$emitAllImpl$10.Z$0 = z;
                            flowKt__ChannelsKt$emitAllImpl$10.label = 2;
                            if(flowCollector1.emit(object3, flowKt__ChannelsKt$emitAllImpl$10) != object1) {
                                continue;
                            }
                        }
                        return object1;
                    }
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        if(z) {
            ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
        }
        throw throwable0;
    label_52:
        if(z) {
            ChannelsKt.cancelConsumed(receiveChannel0, null);
        }
        return Unit.INSTANCE;
    }

    public static final ReceiveChannel produceIn(Flow flow0, CoroutineScope coroutineScope0) {
        return ChannelFlowKt.asChannelFlow(flow0).produceImpl(coroutineScope0);
    }

    public static final Flow receiveAsFlow(ReceiveChannel receiveChannel0) {
        return new ChannelAsFlow(receiveChannel0, false, null, 0, null, 28, null);
    }
}

