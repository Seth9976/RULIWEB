package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref.LongRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.time.Duration;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.channels.ChannelResult.Closed;
import kotlinx.coroutines.channels.ChannelResult.Failed;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.internal.ChildCancelledException;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.selects.OnTimeoutKt;
import kotlinx.coroutines.selects.SelectBuilder;
import kotlinx.coroutines.selects.SelectImplementation;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001A2\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0007\u001A:\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\b\b\u001A&\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0005H\u0007\u001A3\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0007H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\t\u0010\n\u001A7\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\f\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00A2\u0006\u0002\b\r\u001A$\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F*\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00052\b\b\u0002\u0010\u0013\u001A\u00020\u0005H\u0000\u001A&\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0015\u001A\u00020\u0005H\u0007\u001A3\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0016\u001A\u00020\u0007H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\n\u001A3\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0007H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0018\u0010\n\u001A3\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0007H\u0002\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001A\u0010\n\u0082\u0002\u000B\n\u0002\b\u0019\n\u0005\b\u00A1\u001E0\u0001\u00A8\u0006\u001B"}, d2 = {"debounce", "Lkotlinx/coroutines/flow/Flow;", "T", "timeoutMillis", "Lkotlin/Function1;", "", "timeout", "Lkotlin/time/Duration;", "debounceDuration", "debounce-HG0u8IE", "(Lkotlinx/coroutines/flow/Flow;J)Lkotlinx/coroutines/flow/Flow;", "debounceInternal", "timeoutMillisSelector", "debounceInternal$FlowKt__DelayKt", "fixedPeriodTicker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "delayMillis", "initialDelayMillis", "sample", "periodMillis", "period", "sample-HG0u8IE", "timeout-HG0u8IE", "timeoutInternal", "timeoutInternal-HG0u8IE$FlowKt__DelayKt", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__DelayKt {
    public static final Flow debounce(Flow flow0, long v) {
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            throw new IllegalArgumentException("Debounce timeout should not be negative");
        }
        return v1 == 0 ? flow0 : FlowKt__DelayKt.debounceInternal$FlowKt__DelayKt(flow0, new Function1(v) {
            final long $timeoutMillis;

            {
                this.$timeoutMillis = v;
                super(1);
            }

            public final Long invoke(Object object0) {
                return this.$timeoutMillis;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        });
    }

    public static final Flow debounce(Flow flow0, Function1 function10) {
        return FlowKt__DelayKt.debounceInternal$FlowKt__DelayKt(flow0, function10);
    }

    public static final Flow debounce-HG0u8IE(Flow flow0, long v) {
        return FlowKt.debounce(flow0, DelayKt.toDelayMillis-LRDsOJo(v));
    }

    public static final Flow debounceDuration(Flow flow0, Function1 function10) {
        return FlowKt__DelayKt.debounceInternal$FlowKt__DelayKt(flow0, new Function1(function10) {
            final Function1 $timeout;

            {
                this.$timeout = function10;
                super(1);
            }

            public final Long invoke(Object object0) {
                return DelayKt.toDelayMillis-LRDsOJo(((Duration)this.$timeout.invoke(object0)).unbox-impl());
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        });
    }

    private static final Flow debounceInternal$FlowKt__DelayKt(Flow flow0, Function1 function10) {
        return FlowCoroutineKt.scopedFlow(new Function3(function10, flow0, null) {
            final Flow $this_debounceInternal;
            final Function1 $timeoutMillisSelector;
            private Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            {
                this.$timeoutMillisSelector = function10;
                this.$this_debounceInternal = flow0;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((CoroutineScope)object0), ((FlowCollector)object1), ((Continuation)object2));
            }

            public final Object invoke(CoroutineScope coroutineScope0, FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1 flowKt__DelayKt$debounceInternal$10 = new kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1(this.$timeoutMillisSelector, this.$this_debounceInternal, continuation0);
                flowKt__DelayKt$debounceInternal$10.L$0 = coroutineScope0;
                flowKt__DelayKt$debounceInternal$10.L$1 = flowCollector0;
                return flowKt__DelayKt$debounceInternal$10.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector2;
                ReceiveChannel receiveChannel2;
                ObjectRef ref$ObjectRef1;
                LongRef ref$LongRef0;
                ReceiveChannel receiveChannel1;
                FlowCollector flowCollector1;
                ObjectRef ref$ObjectRef0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        FlowCollector flowCollector0 = (FlowCollector)this.L$1;
                        ReceiveChannel receiveChannel0 = ProduceKt.produce$default(coroutineScope0, null, 0, new Function2(null) {
                            final Flow $this_debounceInternal;
                            private Object L$0;
                            int label;

                            {
                                this.$this_debounceInternal = flow0;
                                super(2, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object object0, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.values.1 flowKt__DelayKt$debounceInternal$1$values$10 = new kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.values.1(this.$this_debounceInternal, continuation0);
                                flowKt__DelayKt$debounceInternal$1$values$10.L$0 = object0;
                                return flowKt__DelayKt$debounceInternal$1$values$10;
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
                            }

                            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                                return ((kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.values.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = new FlowCollector() {
                                            @Override  // kotlinx.coroutines.flow.FlowCollector
                                            public final Object emit(Object object0, Continuation continuation0) {
                                                kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.values.1.1.emit.1 flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10;
                                                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.values.1.1.emit.1) {
                                                    flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.values.1.1.emit.1)continuation0;
                                                    if((flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10.label & 0x80000000) == 0) {
                                                        flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                        flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10.label ^= 0x80000000;
                                                    }
                                                }
                                                else {
                                                    flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                Object object1 = flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10.result;
                                                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch(flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10.label) {
                                                    case 0: {
                                                        ResultKt.throwOnFailure(object1);
                                                        ProducerScope producerScope0 = this.$$this$produce;
                                                        if(object0 == null) {
                                                            object0 = NullSurrogateKt.NULL;
                                                        }
                                                        flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10.label = 1;
                                                        return producerScope0.send(object0, flowKt__DelayKt$debounceInternal$1$values$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
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
                                        this.label = 1;
                                        return this.$this_debounceInternal.collect(flowCollector0, this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        }, 3, null);
                        ref$ObjectRef0 = new ObjectRef();
                        flowCollector1 = flowCollector0;
                        receiveChannel1 = receiveChannel0;
                        goto label_25;
                    }
                    case 1: {
                        ref$LongRef0 = (LongRef)this.L$3;
                        ref$ObjectRef0 = (ObjectRef)this.L$2;
                        receiveChannel1 = (ReceiveChannel)this.L$1;
                        flowCollector1 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        ref$ObjectRef0.element = null;
                        goto label_40;
                    }
                    case 2: {
                        ref$ObjectRef1 = (ObjectRef)this.L$2;
                        receiveChannel2 = (ReceiveChannel)this.L$1;
                        flowCollector2 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    flowCollector1 = flowCollector2;
                    receiveChannel1 = receiveChannel2;
                    ref$ObjectRef0 = ref$ObjectRef1;
                label_25:
                    if(ref$ObjectRef0.element == NullSurrogateKt.DONE) {
                        break;
                    }
                    ref$LongRef0 = new LongRef();
                    if(ref$ObjectRef0.element != null) {
                        ref$LongRef0.element = ((Number)this.$timeoutMillisSelector.invoke((ref$ObjectRef0.element == NullSurrogateKt.NULL ? null : ref$ObjectRef0.element))).longValue();
                        if(ref$LongRef0.element < 0L) {
                            throw new IllegalArgumentException("Debounce timeout should not be negative");
                        }
                        if(ref$LongRef0.element == 0L) {
                            this.L$0 = flowCollector1;
                            this.L$1 = receiveChannel1;
                            this.L$2 = ref$ObjectRef0;
                            this.L$3 = ref$LongRef0;
                            this.label = 1;
                            if(flowCollector1.emit((ref$ObjectRef0.element == NullSurrogateKt.NULL ? null : ref$ObjectRef0.element), this) == object1) {
                                return object1;
                            }
                            ref$ObjectRef0.element = null;
                        }
                    }
                label_40:
                    ref$ObjectRef1 = ref$ObjectRef0;
                    receiveChannel2 = receiveChannel1;
                    flowCollector2 = flowCollector1;
                    SelectImplementation selectImplementation0 = new SelectImplementation(this.getContext());
                    SelectBuilder selectBuilder0 = selectImplementation0;
                    if(ref$ObjectRef1.element != null) {
                        OnTimeoutKt.onTimeout(selectBuilder0, ref$LongRef0.element, new Function1(ref$ObjectRef1, null) {
                            final FlowCollector $downstream;
                            final ObjectRef $lastValue;
                            int label;

                            {
                                this.$downstream = flowCollector0;
                                this.$lastValue = ref$ObjectRef0;
                                super(1, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Continuation continuation0) {
                                return new kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.3.1(this.$downstream, this.$lastValue, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                return this.invoke(((Continuation)object0));
                            }

                            public final Object invoke(Continuation continuation0) {
                                return ((kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.3.1)this.create(continuation0)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        this.label = 1;
                                        if(this.$downstream.emit((this.$lastValue.element == NullSurrogateKt.NULL ? null : this.$lastValue.element), this) == object1) {
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
                                this.$lastValue.element = null;
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    selectBuilder0.invoke(receiveChannel2.getOnReceiveCatching(), new Function2(flowCollector2, null) {
                        final FlowCollector $downstream;
                        final ObjectRef $lastValue;
                        Object L$0;
                        Object L$1;
                        int label;

                        {
                            this.$lastValue = ref$ObjectRef0;
                            this.$downstream = flowCollector0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.3.2 flowKt__DelayKt$debounceInternal$1$3$20 = new kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.3.2(this.$lastValue, this.$downstream, continuation0);
                            flowKt__DelayKt$debounceInternal$1$3$20.L$0 = object0;
                            return flowKt__DelayKt$debounceInternal$1$3$20;
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke-WpGqRn0(((ChannelResult)object0).unbox-impl(), ((Continuation)object1));
                        }

                        public final Object invoke-WpGqRn0(Object object0, Continuation continuation0) {
                            return ((kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1.3.2)this.create(ChannelResult.box-impl(object0), continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            ObjectRef ref$ObjectRef1;
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    Object object2 = ((ChannelResult)this.L$0).unbox-impl();
                                    ObjectRef ref$ObjectRef0 = this.$lastValue;
                                    if(!(object2 instanceof Failed)) {
                                        ref$ObjectRef0.element = object2;
                                    }
                                    ref$ObjectRef1 = this.$lastValue;
                                    FlowCollector flowCollector0 = this.$downstream;
                                    if(!(object2 instanceof Failed)) {
                                        return Unit.INSTANCE;
                                    }
                                    Throwable throwable0 = ChannelResult.exceptionOrNull-impl(object2);
                                    if(throwable0 != null) {
                                        throw throwable0;
                                    }
                                    if(ref$ObjectRef1.element != null) {
                                        this.L$0 = object2;
                                        this.L$1 = ref$ObjectRef1;
                                        this.label = 1;
                                        if(flowCollector0.emit((ref$ObjectRef1.element == NullSurrogateKt.NULL ? null : ref$ObjectRef1.element), this) == object1) {
                                            return object1;
                                        }
                                    }
                                    break;
                                }
                                case 1: {
                                    ObjectRef ref$ObjectRef2 = (ObjectRef)this.L$1;
                                    ResultKt.throwOnFailure(object0);
                                    ref$ObjectRef1 = ref$ObjectRef2;
                                    break;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                            ref$ObjectRef1.element = NullSurrogateKt.DONE;
                            return Unit.INSTANCE;
                        }
                    });
                    this.L$0 = flowCollector2;
                    this.L$1 = receiveChannel2;
                    this.L$2 = ref$ObjectRef1;
                    this.L$3 = null;
                    this.label = 2;
                    if(selectImplementation0.doSelect(this) != object1) {
                        continue;
                    }
                    return object1;
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static final ReceiveChannel fixedPeriodTicker(CoroutineScope coroutineScope0, long v, long v1) {
        if(v < 0L) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + v + " ms").toString());
        }
        if(v1 < 0L) {
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + v1 + " ms").toString());
        }
        return ProduceKt.produce$default(coroutineScope0, null, 0, new Function2(v1, v, null) {
            final long $delayMillis;
            final long $initialDelayMillis;
            private Object L$0;
            int label;

            {
                this.$initialDelayMillis = v;
                this.$delayMillis = v1;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__DelayKt.fixedPeriodTicker.3 flowKt__DelayKt$fixedPeriodTicker$30 = new kotlinx.coroutines.flow.FlowKt__DelayKt.fixedPeriodTicker.3(this.$initialDelayMillis, this.$delayMillis, continuation0);
                flowKt__DelayKt$fixedPeriodTicker$30.L$0 = object0;
                return flowKt__DelayKt$fixedPeriodTicker$30;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__DelayKt.fixedPeriodTicker.3)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            alab1:
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        this.L$0 = producerScope0;
                        this.label = 1;
                        if(DelayKt.delay(this.$initialDelayMillis, this) != object1) {
                            goto label_17;
                        }
                        break;
                    }
                    case 2: {
                        producerScope0 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        while(true) {
                            this.L$0 = producerScope0;
                            this.label = 3;
                            if(DelayKt.delay(this.$delayMillis, this) == object1) {
                                break alab1;
                            }
                        label_17:
                            SendChannel sendChannel0 = producerScope0.getChannel();
                            this.L$0 = producerScope0;
                            this.label = 2;
                            if(sendChannel0.send(Unit.INSTANCE, this) != object1) {
                                continue;
                            }
                            break alab1;
                        }
                    }
                    case 1: 
                    case 3: {
                        producerScope0 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_17;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                return object1;
            }
        }, 1, null);
    }

    public static ReceiveChannel fixedPeriodTicker$default(CoroutineScope coroutineScope0, long v, long v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v1 = v;
        }
        return FlowKt.fixedPeriodTicker(coroutineScope0, v, v1);
    }

    public static final Flow sample(Flow flow0, long v) {
        if(v <= 0L) {
            throw new IllegalArgumentException("Sample period should be positive");
        }
        return FlowCoroutineKt.scopedFlow(new Function3(v, flow0, null) {
            final long $periodMillis;
            final Flow $this_sample;
            private Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            {
                this.$periodMillis = v;
                this.$this_sample = flow0;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((CoroutineScope)object0), ((FlowCollector)object1), ((Continuation)object2));
            }

            public final Object invoke(CoroutineScope coroutineScope0, FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2 flowKt__DelayKt$sample$20 = new kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2(this.$periodMillis, this.$this_sample, continuation0);
                flowKt__DelayKt$sample$20.L$0 = coroutineScope0;
                flowKt__DelayKt$sample$20.L$1 = flowCollector0;
                return flowKt__DelayKt$sample$20.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ObjectRef ref$ObjectRef1;
                ReceiveChannel receiveChannel2;
                ReceiveChannel receiveChannel1;
                FlowCollector flowCollector1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        FlowCollector flowCollector0 = (FlowCollector)this.L$1;
                        ReceiveChannel receiveChannel0 = ProduceKt.produce$default(coroutineScope0, null, -1, new Function2(null) {
                            final Flow $this_sample;
                            private Object L$0;
                            int label;

                            {
                                this.$this_sample = flow0;
                                super(2, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object object0, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.values.1 flowKt__DelayKt$sample$2$values$10 = new kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.values.1(this.$this_sample, continuation0);
                                flowKt__DelayKt$sample$2$values$10.L$0 = object0;
                                return flowKt__DelayKt$sample$2$values$10;
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
                            }

                            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                                return ((kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.values.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        FlowCollector flowCollector0 = new FlowCollector() {
                                            @Override  // kotlinx.coroutines.flow.FlowCollector
                                            public final Object emit(Object object0, Continuation continuation0) {
                                                kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.values.1.1.emit.1 flowKt__DelayKt$sample$2$values$1$1$emit$10;
                                                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.values.1.1.emit.1) {
                                                    flowKt__DelayKt$sample$2$values$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.values.1.1.emit.1)continuation0;
                                                    if((flowKt__DelayKt$sample$2$values$1$1$emit$10.label & 0x80000000) == 0) {
                                                        flowKt__DelayKt$sample$2$values$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                        flowKt__DelayKt$sample$2$values$1$1$emit$10.label ^= 0x80000000;
                                                    }
                                                }
                                                else {
                                                    flowKt__DelayKt$sample$2$values$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                Object object1 = flowKt__DelayKt$sample$2$values$1$1$emit$10.result;
                                                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch(flowKt__DelayKt$sample$2$values$1$1$emit$10.label) {
                                                    case 0: {
                                                        ResultKt.throwOnFailure(object1);
                                                        ProducerScope producerScope0 = this.$$this$produce;
                                                        if(object0 == null) {
                                                            object0 = NullSurrogateKt.NULL;
                                                        }
                                                        flowKt__DelayKt$sample$2$values$1$1$emit$10.label = 1;
                                                        return producerScope0.send(object0, flowKt__DelayKt$sample$2$values$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
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
                                        this.label = 1;
                                        return this.$this_sample.collect(flowCollector0, this) == object1 ? object1 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }
                        }, 1, null);
                        ObjectRef ref$ObjectRef0 = new ObjectRef();
                        flowCollector1 = flowCollector0;
                        receiveChannel1 = receiveChannel0;
                        receiveChannel2 = FlowKt__DelayKt.fixedPeriodTicker$default(coroutineScope0, this.$periodMillis, 0L, 2, null);
                        ref$ObjectRef1 = ref$ObjectRef0;
                        break;
                    }
                    case 1: {
                        receiveChannel2 = (ReceiveChannel)this.L$3;
                        ref$ObjectRef1 = (ObjectRef)this.L$2;
                        receiveChannel1 = (ReceiveChannel)this.L$1;
                        flowCollector1 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(ref$ObjectRef1.element != NullSurrogateKt.DONE) {
                    SelectImplementation selectImplementation0 = new SelectImplementation(this.getContext());
                    selectImplementation0.invoke(receiveChannel1.getOnReceiveCatching(), new Function2(receiveChannel2, null) {
                        final ObjectRef $lastValue;
                        final ReceiveChannel $ticker;
                        Object L$0;
                        int label;

                        {
                            this.$lastValue = ref$ObjectRef0;
                            this.$ticker = receiveChannel0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.1.1 flowKt__DelayKt$sample$2$1$10 = new kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.1.1(this.$lastValue, this.$ticker, continuation0);
                            flowKt__DelayKt$sample$2$1$10.L$0 = object0;
                            return flowKt__DelayKt$sample$2$1$10;
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke-WpGqRn0(((ChannelResult)object0).unbox-impl(), ((Continuation)object1));
                        }

                        public final Object invoke-WpGqRn0(Object object0, Continuation continuation0) {
                            return ((kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.1.1)this.create(ChannelResult.box-impl(object0), continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            if(this.label != 0) {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                            ResultKt.throwOnFailure(object0);
                            Object object1 = ((ChannelResult)this.L$0).unbox-impl();
                            ObjectRef ref$ObjectRef0 = this.$lastValue;
                            if(!(object1 instanceof Failed)) {
                                ref$ObjectRef0.element = object1;
                            }
                            ReceiveChannel receiveChannel0 = this.$ticker;
                            ObjectRef ref$ObjectRef1 = this.$lastValue;
                            if(object1 instanceof Failed) {
                                Throwable throwable0 = ChannelResult.exceptionOrNull-impl(object1);
                                if(throwable0 != null) {
                                    throw throwable0;
                                }
                                receiveChannel0.cancel(new ChildCancelledException());
                                ref$ObjectRef1.element = NullSurrogateKt.DONE;
                                return Unit.INSTANCE;
                            }
                            return Unit.INSTANCE;
                        }
                    });
                    selectImplementation0.invoke(receiveChannel2.getOnReceive(), new Function2(flowCollector1, null) {
                        final FlowCollector $downstream;
                        final ObjectRef $lastValue;
                        int label;

                        {
                            this.$lastValue = ref$ObjectRef0;
                            this.$downstream = flowCollector0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            return new kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.1.2(this.$lastValue, this.$downstream, continuation0);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((Unit)object0), ((Continuation)object1));
                        }

                        public final Object invoke(Unit unit0, Continuation continuation0) {
                            return ((kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2.1.2)this.create(unit0, continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    Object object2 = this.$lastValue.element;
                                    if(object2 == null) {
                                        return Unit.INSTANCE;
                                    }
                                    this.$lastValue.element = null;
                                    FlowCollector flowCollector0 = this.$downstream;
                                    if(object2 == NullSurrogateKt.NULL) {
                                        object2 = null;
                                    }
                                    this.label = 1;
                                    return flowCollector0.emit(object2, this) == object1 ? object1 : Unit.INSTANCE;
                                }
                                case 1: {
                                    ResultKt.throwOnFailure(object0);
                                    return Unit.INSTANCE;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                        }
                    });
                    this.L$0 = flowCollector1;
                    this.L$1 = receiveChannel1;
                    this.L$2 = ref$ObjectRef1;
                    this.L$3 = receiveChannel2;
                    this.label = 1;
                    if(selectImplementation0.doSelect(this) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static final Flow sample-HG0u8IE(Flow flow0, long v) {
        return FlowKt.sample(flow0, DelayKt.toDelayMillis-LRDsOJo(v));
    }

    public static final Flow timeout-HG0u8IE(Flow flow0, long v) {
        return FlowKt__DelayKt.timeoutInternal-HG0u8IE$FlowKt__DelayKt(flow0, v);
    }

    private static final Flow timeoutInternal-HG0u8IE$FlowKt__DelayKt(Flow flow0, long v) {
        return FlowCoroutineKt.scopedFlow(new Function3(v, flow0, null) {
            final Flow $this_timeoutInternal;
            final long $timeout;
            long J$0;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$timeout = v;
                this.$this_timeoutInternal = flow0;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((CoroutineScope)object0), ((FlowCollector)object1), ((Continuation)object2));
            }

            public final Object invoke(CoroutineScope coroutineScope0, FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1 flowKt__DelayKt$timeoutInternal$10 = new kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1(this.$timeout, this.$this_timeoutInternal, continuation0);
                flowKt__DelayKt$timeoutInternal$10.L$0 = coroutineScope0;
                flowKt__DelayKt$timeoutInternal$10.L$1 = flowCollector0;
                return flowKt__DelayKt$timeoutInternal$10.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ReceiveChannel receiveChannel1;
                FlowCollector flowCollector1;
                long v;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        FlowCollector flowCollector0 = (FlowCollector)this.L$1;
                        if(Duration.compareTo-LRDsOJo(this.$timeout, 0L) <= 0) {
                            throw new TimeoutCancellationException("Timed out immediately");
                        }
                        ReceiveChannel receiveChannel0 = FlowKt.produceIn(FlowKt__ContextKt.buffer$default(this.$this_timeoutInternal, 0, null, 2, null), coroutineScope0);
                        v = this.$timeout;
                        flowCollector1 = flowCollector0;
                        receiveChannel1 = receiveChannel0;
                        goto label_19;
                    }
                    case 1: {
                        v = this.J$0;
                        receiveChannel1 = (ReceiveChannel)this.L$1;
                        flowCollector1 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                do {
                    if(!((Boolean)object0).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                label_19:
                    SelectImplementation selectImplementation0 = new SelectImplementation(this.getContext());
                    selectImplementation0.invoke(receiveChannel1.getOnReceiveCatching(), new Function2(null) {
                        final FlowCollector $downStream;
                        Object L$0;
                        int label;

                        {
                            this.$downStream = flowCollector0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1.1.1 flowKt__DelayKt$timeoutInternal$1$1$10 = new kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1.1.1(this.$downStream, continuation0);
                            flowKt__DelayKt$timeoutInternal$1$1$10.L$0 = object0;
                            return flowKt__DelayKt$timeoutInternal$1$1$10;
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke-WpGqRn0(((ChannelResult)object0).unbox-impl(), ((Continuation)object1));
                        }

                        public final Object invoke-WpGqRn0(Object object0, Continuation continuation0) {
                            return ((kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1.1.1)this.create(ChannelResult.box-impl(object0), continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            Object object2;
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    object2 = ((ChannelResult)this.L$0).unbox-impl();
                                    FlowCollector flowCollector0 = this.$downStream;
                                    if(!(object2 instanceof Failed)) {
                                        this.L$0 = object2;
                                        this.label = 1;
                                        if(flowCollector0.emit(object2, this) == object1) {
                                            return object1;
                                        }
                                    }
                                    break;
                                }
                                case 1: {
                                    Object object3 = this.L$0;
                                    ResultKt.throwOnFailure(object0);
                                    object2 = object3;
                                    break;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                            if(object2 instanceof Closed) {
                                ChannelResult.exceptionOrNull-impl(object2);
                                return Boxing.boxBoolean(false);
                            }
                            return Boxing.boxBoolean(true);
                        }
                    });
                    OnTimeoutKt.onTimeout-8Mi8wO0(selectImplementation0, v, new Function1(null) {
                        final long $timeout;
                        int label;

                        {
                            this.$timeout = v;
                            super(1, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Continuation continuation0) {
                            return new kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1.1.2(this.$timeout, continuation0);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            return this.invoke(((Continuation)object0));
                        }

                        public final Object invoke(Continuation continuation0) {
                            return ((kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1.1.2)this.create(continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            if(this.label != 0) {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                            ResultKt.throwOnFailure(object0);
                            throw new TimeoutCancellationException("Timed out waiting for " + Duration.toString-impl(this.$timeout));
                        }
                    });
                    this.L$0 = flowCollector1;
                    this.L$1 = receiveChannel1;
                    this.J$0 = v;
                    this.label = 1;
                    object0 = selectImplementation0.doSelect(this);
                }
                while(object0 != object1);
                return object1;
            }
        });
    }
}

