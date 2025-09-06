package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel.DefaultImpls;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A.\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"flowWithLifecycle", "Lkotlinx/coroutines/flow/Flow;", "T", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "minActiveState", "Landroidx/lifecycle/Lifecycle$State;", "lifecycle-runtime-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class FlowExtKt {
    public static final Flow flowWithLifecycle(Flow flow0, Lifecycle lifecycle0, State lifecycle$State0) {
        Intrinsics.checkNotNullParameter(flow0, "<this>");
        Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
        Intrinsics.checkNotNullParameter(lifecycle$State0, "minActiveState");
        return FlowKt.callbackFlow(new Function2(lifecycle0, lifecycle$State0, flow0, null) {
            final Lifecycle $lifecycle;
            final State $minActiveState;
            final Flow $this_flowWithLifecycle;
            private Object L$0;
            int label;

            {
                this.$lifecycle = lifecycle0;
                this.$minActiveState = lifecycle$State0;
                this.$this_flowWithLifecycle = flow0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.lifecycle.FlowExtKt.flowWithLifecycle.1 flowExtKt$flowWithLifecycle$10 = new androidx.lifecycle.FlowExtKt.flowWithLifecycle.1(this.$lifecycle, this.$minActiveState, this.$this_flowWithLifecycle, continuation0);
                flowExtKt$flowWithLifecycle$10.L$0 = object0;
                return flowExtKt$flowWithLifecycle$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((androidx.lifecycle.FlowExtKt.flowWithLifecycle.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        androidx.lifecycle.FlowExtKt.flowWithLifecycle.1.1 flowExtKt$flowWithLifecycle$1$10 = new Function2(producerScope0, null) {
                            final ProducerScope $$this$callbackFlow;
                            final Flow $this_flowWithLifecycle;
                            int label;

                            {
                                this.$this_flowWithLifecycle = flow0;
                                this.$$this$callbackFlow = producerScope0;
                                super(2, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object object0, Continuation continuation0) {
                                return new androidx.lifecycle.FlowExtKt.flowWithLifecycle.1.1(this.$this_flowWithLifecycle, this.$$this$callbackFlow, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                            }

                            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                return ((androidx.lifecycle.FlowExtKt.flowWithLifecycle.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        androidx.lifecycle.FlowExtKt.flowWithLifecycle.1.1.1 flowExtKt$flowWithLifecycle$1$1$10 = new FlowCollector() {
                                            @Override  // kotlinx.coroutines.flow.FlowCollector
                                            public final Object emit(Object object0, Continuation continuation0) {
                                                Object object1 = this.$$this$callbackFlow.send(object0, continuation0);
                                                return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
                                            }
                                        };
                                        this.label = 1;
                                        return this.$this_flowWithLifecycle.collect(flowExtKt$flowWithLifecycle$1$1$10, this) == object1 ? object1 : Unit.INSTANCE;
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
                        };
                        this.L$0 = producerScope0;
                        this.label = 1;
                        if(RepeatOnLifecycleKt.repeatOnLifecycle(this.$lifecycle, this.$minActiveState, flowExtKt$flowWithLifecycle$1$10, this) == object1) {
                            return object1;
                        }
                        producerScope1 = producerScope0;
                        break;
                    }
                    case 1: {
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                DefaultImpls.close$default(producerScope1, null, 1, null);
                return Unit.INSTANCE;
            }
        });
    }

    public static Flow flowWithLifecycle$default(Flow flow0, Lifecycle lifecycle0, State lifecycle$State0, int v, Object object0) {
        if((v & 2) != 0) {
            lifecycle$State0 = State.STARTED;
        }
        return FlowExtKt.flowWithLifecycle(flow0, lifecycle0, lifecycle$State0);
    }
}

