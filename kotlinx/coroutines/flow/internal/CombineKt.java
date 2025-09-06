package kotlinx.coroutines.flow.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult.Failed;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel.DefaultImpls;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001An\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u00022\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00040\u00012(\u0010\u0007\u001A$\b\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001A\u0090\u0001\u0010\f\u001A\u00020\r\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u00020\u000F2\u0014\u0010\u0010\u001A\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u000E0\u00010\u00112\u0016\u0010\u0012\u001A\u0012\u0012\u000E\u0012\f\u0012\u0006\u0012\u0004\u0018\u0001H\u000E\u0018\u00010\u00110\u001329\u0010\u0007\u001A5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000F\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000E0\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u0014H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u0015*\u001C\b\u0002\u0010\u0016\"\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00172\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"zipImpl", "Lkotlinx/coroutines/flow/Flow;", "R", "T1", "T2", "flow", "flow2", "transform", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "combineInternal", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "flows", "", "arrayFactory", "Lkotlin/Function0;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/FlowCollector;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Update", "Lkotlin/collections/IndexedValue;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CombineKt {
    public static final Object combineInternal(FlowCollector flowCollector0, Flow[] arr_flow, Function0 function00, Function3 function30, Continuation continuation0) {
        Object object0 = FlowCoroutineKt.flowScope(new Function2(arr_flow, function00, function30, flowCollector0, null) {
            final Function0 $arrayFactory;
            final Flow[] $flows;
            final FlowCollector $this_combineInternal;
            final Function3 $transform;
            int I$0;
            int I$1;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                this.$flows = arr_flow;
                this.$arrayFactory = function00;
                this.$transform = function30;
                this.$this_combineInternal = flowCollector0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2 combineKt$combineInternal$20 = new kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2(this.$flows, this.$arrayFactory, this.$transform, this.$this_combineInternal, continuation0);
                combineKt$combineInternal$20.L$0 = object0;
                return combineKt$combineInternal$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object[] arr_object3;
                Object object2;
                Object[] arr_object2;
                byte[] arr_b1;
                int v4;
                int v3;
                Channel channel1;
                Object[] arr_object1;
                byte[] arr_b;
                int v1;
                int v;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        v = this.$flows.length;
                        if(v == 0) {
                            return Unit.INSTANCE;
                        }
                        Object[] arr_object = new Object[v];
                        ArraysKt.fill$default(arr_object, NullSurrogateKt.UNINITIALIZED, 0, 0, 6, null);
                        Channel channel0 = ChannelKt.Channel$default(v, null, null, 6, null);
                        AtomicInteger atomicInteger0 = new AtomicInteger(v);
                        v1 = 0;
                        for(int v2 = 0; v2 < v; ++v2) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope0, null, null, new Function2(v2, atomicInteger0, channel0, null) {
                                final Flow[] $flows;
                                final int $i;
                                final AtomicInteger $nonClosed;
                                final Channel $resultChannel;
                                int label;

                                {
                                    this.$flows = arr_flow;
                                    this.$i = v;
                                    this.$nonClosed = atomicInteger0;
                                    this.$resultChannel = channel0;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    return new kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2.1(this.$flows, this.$i, this.$nonClosed, this.$resultChannel, continuation0);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                    return ((kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            try {
                                                Flow flow0 = this.$flows[this.$i];
                                                kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2.1.1 combineKt$combineInternal$2$1$10 = new FlowCollector() {
                                                    @Override  // kotlinx.coroutines.flow.FlowCollector
                                                    public final Object emit(Object object0, Continuation continuation0) {
                                                        kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2.1.1.emit.1 combineKt$combineInternal$2$1$1$emit$10;
                                                        if(continuation0 instanceof kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2.1.1.emit.1) {
                                                            combineKt$combineInternal$2$1$1$emit$10 = (kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2.1.1.emit.1)continuation0;
                                                            if((combineKt$combineInternal$2$1$1$emit$10.label & 0x80000000) == 0) {
                                                                combineKt$combineInternal$2$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                                combineKt$combineInternal$2$1$1$emit$10.label ^= 0x80000000;
                                                            }
                                                        }
                                                        else {
                                                            combineKt$combineInternal$2$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                        Object object1 = combineKt$combineInternal$2$1$1$emit$10.result;
                                                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        switch(combineKt$combineInternal$2$1$1$emit$10.label) {
                                                            case 0: {
                                                                ResultKt.throwOnFailure(object1);
                                                                IndexedValue indexedValue0 = new IndexedValue(this.$i, object0);
                                                                combineKt$combineInternal$2$1$1$emit$10.label = 1;
                                                                if(this.$i.send(indexedValue0, combineKt$combineInternal$2$1$1$emit$10) == object2) {
                                                                    return object2;
                                                                }
                                                                break;
                                                            }
                                                            case 1: {
                                                                ResultKt.throwOnFailure(object1);
                                                                break;
                                                            }
                                                            case 2: {
                                                                ResultKt.throwOnFailure(object1);
                                                                return Unit.INSTANCE;
                                                            }
                                                            default: {
                                                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                                            }
                                                        }
                                                        combineKt$combineInternal$2$1$1$emit$10.label = 2;
                                                        return YieldKt.yield(combineKt$combineInternal$2$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                                                    }
                                                };
                                                this.label = 1;
                                                if(flow0.collect(combineKt$combineInternal$2$1$10, this) == object1) {
                                                    return object1;
                                                label_9:
                                                    ResultKt.throwOnFailure(object0);
                                                }
                                                break;
                                            }
                                            catch(Throwable throwable0) {
                                                goto label_12;
                                            }
                                        }
                                        case 1: {
                                            goto label_9;
                                        label_12:
                                            if(this.$nonClosed.decrementAndGet() == 0) {
                                                DefaultImpls.close$default(this.$resultChannel, null, 1, null);
                                            }
                                            throw throwable0;
                                        }
                                        default: {
                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                        }
                                    }
                                    if(this.$nonClosed.decrementAndGet() == 0) {
                                        DefaultImpls.close$default(this.$resultChannel, null, 1, null);
                                    }
                                    return Unit.INSTANCE;
                                }
                            }, 3, null);
                        }
                        arr_b = new byte[v];
                        arr_object1 = arr_object;
                        channel1 = channel0;
                        goto label_40;
                    }
                    case 1: {
                        v3 = this.I$1;
                        v4 = this.I$0;
                        arr_b1 = (byte[])this.L$2;
                        channel1 = (Channel)this.L$1;
                        arr_object2 = (Object[])this.L$0;
                        ResultKt.throwOnFailure(object0);
                        object2 = ((ChannelResult)object0).unbox-impl();
                        goto label_52;
                    }
                    case 2: 
                    case 3: {
                        v3 = this.I$1;
                        v4 = this.I$0;
                        arr_b1 = (byte[])this.L$2;
                        channel1 = (Channel)this.L$1;
                        arr_object2 = (Object[])this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            alab1:
                do {
                alab2:
                    while(true) {
                        while(true) {
                            v1 = v3;
                            v = v4;
                            arr_b = arr_b1;
                            arr_object1 = arr_object2;
                        label_40:
                            this.L$0 = arr_object1;
                            this.L$1 = channel1;
                            this.L$2 = arr_b;
                            this.I$0 = v;
                            this.I$1 = (byte)(v1 + 1);
                            this.label = 1;
                            object2 = channel1.receiveCatching-JP2dKIU(this);
                            if(object2 == object1) {
                                break alab1;
                            }
                            v4 = v;
                            v3 = (byte)(v1 + 1);
                            arr_object2 = arr_object1;
                            arr_b1 = arr_b;
                        label_52:
                            IndexedValue indexedValue0 = (IndexedValue)ChannelResult.getOrNull-impl(object2);
                            if(indexedValue0 == null) {
                                return Unit.INSTANCE;
                            }
                            while(true) {
                                int v5 = indexedValue0.getIndex();
                                Object object3 = arr_object2[v5];
                                arr_object2[v5] = indexedValue0.getValue();
                                if(object3 == NullSurrogateKt.UNINITIALIZED) {
                                    --v4;
                                }
                                if(arr_b1[v5] == v3) {
                                    break;
                                }
                                arr_b1[v5] = (byte)v3;
                                IndexedValue indexedValue1 = (IndexedValue)ChannelResult.getOrNull-impl(channel1.tryReceive-PtdJZtk());
                                if(indexedValue1 == null) {
                                    break;
                                }
                                indexedValue0 = indexedValue1;
                            }
                            if(v4 != 0) {
                                break;
                            }
                            arr_object3 = (Object[])this.$arrayFactory.invoke();
                            if(arr_object3 == null) {
                                this.L$0 = arr_object2;
                                this.L$1 = channel1;
                                this.L$2 = arr_b1;
                                this.I$0 = 0;
                                this.I$1 = v3;
                                this.label = 2;
                                if(this.$transform.invoke(this.$this_combineInternal, arr_object2, this) != object1) {
                                    break;
                                }
                                break alab2;
                            }
                            goto label_77;
                        }
                    }
                    return object1;
                label_77:
                    ArraysKt.copyInto$default(arr_object2, arr_object3, 0, 0, 0, 14, null);
                    this.L$0 = arr_object2;
                    this.L$1 = channel1;
                    this.L$2 = arr_b1;
                    this.I$0 = 0;
                    this.I$1 = v3;
                    this.label = 3;
                }
                while(this.$transform.invoke(this.$this_combineInternal, arr_object3, this) != object1);
                return object1;
            }
        }, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public static final Flow zipImpl(Flow flow0, Flow flow1, Function3 function30) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Object object0 = CoroutineScopeKt.coroutineScope(new kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1(flowCollector0, flow1, flow0, function30, null), continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\u008A@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", i = {0}, l = {0x7E}, m = "invokeSuspend", n = {"second"}, s = {"L$0"})
        final class kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1 extends SuspendLambda implements Function2 {
            final Flow $flow;
            final Flow $flow2;
            final FlowCollector $this_unsafeFlow;
            final Function3 $transform;
            private Object L$0;
            int label;

            kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1(FlowCollector flowCollector0, Flow flow0, Flow flow1, Function3 function30, Continuation continuation0) {
                this.$this_unsafeFlow = flowCollector0;
                this.$flow2 = flow0;
                this.$flow = flow1;
                this.$transform = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1 combineKt$zipImpl$1$10 = new kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1(this.$this_unsafeFlow, this.$flow2, this.$flow, this.$transform, continuation0);
                combineKt$zipImpl$1$10.L$0 = object0;
                return combineKt$zipImpl$1$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ReceiveChannel receiveChannel1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        ReceiveChannel receiveChannel0 = ProduceKt.produce$default(coroutineScope0, null, 0, new Function2(null) {
                            final Flow $flow2;
                            private Object L$0;
                            int label;

                            {
                                this.$flow2 = flow0;
                                super(2, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object object0, Continuation continuation0) {
                                kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.second.1 combineKt$zipImpl$1$1$second$10 = new kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.second.1(this.$flow2, continuation0);
                                combineKt$zipImpl$1$1$second$10.L$0 = object0;
                                return combineKt$zipImpl$1$1$second$10;
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
                            }

                            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                                return ((kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.second.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
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
                                                kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.second.1.1.emit.1 combineKt$zipImpl$1$1$second$1$1$emit$10;
                                                if(continuation0 instanceof kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.second.1.1.emit.1) {
                                                    combineKt$zipImpl$1$1$second$1$1$emit$10 = (kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.second.1.1.emit.1)continuation0;
                                                    if((combineKt$zipImpl$1$1$second$1$1$emit$10.label & 0x80000000) == 0) {
                                                        combineKt$zipImpl$1$1$second$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                        combineKt$zipImpl$1$1$second$1$1$emit$10.label ^= 0x80000000;
                                                    }
                                                }
                                                else {
                                                    combineKt$zipImpl$1$1$second$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                Object object1 = combineKt$zipImpl$1$1$second$1$1$emit$10.result;
                                                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch(combineKt$zipImpl$1$1$second$1$1$emit$10.label) {
                                                    case 0: {
                                                        ResultKt.throwOnFailure(object1);
                                                        SendChannel sendChannel0 = this.$$this$produce.getChannel();
                                                        if(object0 == null) {
                                                            object0 = NullSurrogateKt.NULL;
                                                        }
                                                        combineKt$zipImpl$1$1$second$1$1$emit$10.label = 1;
                                                        return sendChannel0.send(object0, combineKt$zipImpl$1$1$second$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
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
                                        return this.$flow2.collect(flowCollector0, this) == object1 ? object1 : Unit.INSTANCE;
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
                        CompletableJob completableJob0 = JobKt__JobKt.Job$default(null, 1, null);
                        Intrinsics.checkNotNull(receiveChannel0, "null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>");
                        ((SendChannel)receiveChannel0).invokeOnClose(new Function1(this.$this_unsafeFlow) {
                            final CompletableJob $collectJob;
                            final FlowCollector $this_unsafeFlow;

                            {
                                this.$collectJob = completableJob0;
                                this.$this_unsafeFlow = flowCollector0;
                                super(1);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                this.invoke(((Throwable)object0));
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Throwable throwable0) {
                                if(this.$collectJob.isActive()) {
                                    AbortFlowException abortFlowException0 = new AbortFlowException(this.$this_unsafeFlow);
                                    this.$collectJob.cancel(abortFlowException0);
                                }
                            }
                        });
                        try {
                            CoroutineContext coroutineContext0 = coroutineScope0.getCoroutineContext();
                            Object object2 = ThreadContextKt.threadContextElements(coroutineContext0);
                            CoroutineContext coroutineContext1 = coroutineScope0.getCoroutineContext().plus(completableJob0);
                            kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2 combineKt$zipImpl$1$1$20 = new Function2(coroutineContext0, object2, receiveChannel0, this.$this_unsafeFlow, this.$transform, null) {
                                final Object $cnt;
                                final Flow $flow;
                                final CoroutineContext $scopeContext;
                                final ReceiveChannel $second;
                                final FlowCollector $this_unsafeFlow;
                                final Function3 $transform;
                                int label;

                                {
                                    this.$flow = flow0;
                                    this.$scopeContext = coroutineContext0;
                                    this.$cnt = object0;
                                    this.$second = receiveChannel0;
                                    this.$this_unsafeFlow = flowCollector0;
                                    this.$transform = function30;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    return new kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2(this.$flow, this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform, continuation0);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((Unit)object0), ((Continuation)object1));
                                }

                                public final Object invoke(Unit unit0, Continuation continuation0) {
                                    return ((kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2)this.create(unit0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2.1 combineKt$zipImpl$1$1$2$10 = new FlowCollector() {
                                                @Override  // kotlinx.coroutines.flow.FlowCollector
                                                public final Object emit(Object object0, Continuation continuation0) {
                                                    kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2.1.emit.1 combineKt$zipImpl$1$1$2$1$emit$10;
                                                    if(continuation0 instanceof kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2.1.emit.1) {
                                                        combineKt$zipImpl$1$1$2$1$emit$10 = (kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2.1.emit.1)continuation0;
                                                        if((combineKt$zipImpl$1$1$2$1$emit$10.label & 0x80000000) == 0) {
                                                            combineKt$zipImpl$1$1$2$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                            combineKt$zipImpl$1$1$2$1$emit$10.label ^= 0x80000000;
                                                        }
                                                    }
                                                    else {
                                                        combineKt$zipImpl$1$1$2$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                                    Object object1 = combineKt$zipImpl$1$1$2$1$emit$10.result;
                                                    Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch(combineKt$zipImpl$1$1$2$1$emit$10.label) {
                                                        case 0: {
                                                            ResultKt.throwOnFailure(object1);
                                                            kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2.1.1 combineKt$zipImpl$1$1$2$1$10 = new Function2(this.$transform, this.$transform, object0, null) {
                                                                final ReceiveChannel $second;
                                                                final FlowCollector $this_unsafeFlow;
                                                                final Function3 $transform;
                                                                final Object $value;
                                                                Object L$0;
                                                                int label;

                                                                {
                                                                    this.$second = receiveChannel0;
                                                                    this.$this_unsafeFlow = flowCollector0;
                                                                    this.$transform = function30;
                                                                    this.$value = object0;
                                                                    super(2, continuation0);
                                                                }

                                                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                public final Continuation create(Object object0, Continuation continuation0) {
                                                                    return new kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2.1.1(this.$second, this.$this_unsafeFlow, this.$transform, this.$value, continuation0);
                                                                }

                                                                @Override  // kotlin.jvm.functions.Function2
                                                                public Object invoke(Object object0, Object object1) {
                                                                    return this.invoke(((Unit)object0), ((Continuation)object1));
                                                                }

                                                                public final Object invoke(Unit unit0, Continuation continuation0) {
                                                                    return ((kotlinx.coroutines.flow.internal.CombineKt.zipImpl.1.1.2.1.1)this.create(unit0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                                                }

                                                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                public final Object invokeSuspend(Object object0) {
                                                                    FlowCollector flowCollector0;
                                                                    Object object2;
                                                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                    switch(this.label) {
                                                                        case 0: {
                                                                            ResultKt.throwOnFailure(object0);
                                                                            this.label = 1;
                                                                            object2 = this.$second.receiveCatching-JP2dKIU(this);
                                                                            if(object2 == object1) {
                                                                                return object1;
                                                                            }
                                                                            goto label_12;
                                                                        }
                                                                        case 1: {
                                                                            ResultKt.throwOnFailure(object0);
                                                                            object2 = ((ChannelResult)object0).unbox-impl();
                                                                        label_12:
                                                                            flowCollector0 = this.$this_unsafeFlow;
                                                                            if(object2 instanceof Failed) {
                                                                                Throwable throwable0 = ChannelResult.exceptionOrNull-impl(object2);
                                                                                if(throwable0 == null) {
                                                                                    throwable0 = new AbortFlowException(flowCollector0);
                                                                                }
                                                                                throw throwable0;
                                                                            }
                                                                            Function3 function30 = this.$transform;
                                                                            Object object3 = this.$value;
                                                                            if(object2 == NullSurrogateKt.NULL) {
                                                                                object2 = null;
                                                                            }
                                                                            this.L$0 = flowCollector0;
                                                                            this.label = 2;
                                                                            object0 = function30.invoke(object3, object2, this);
                                                                            if(object0 == object1) {
                                                                                return object1;
                                                                            }
                                                                            break;
                                                                        }
                                                                        case 2: {
                                                                            flowCollector0 = (FlowCollector)this.L$0;
                                                                            ResultKt.throwOnFailure(object0);
                                                                            break;
                                                                        }
                                                                        case 3: {
                                                                            ResultKt.throwOnFailure(object0);
                                                                            return Unit.INSTANCE;
                                                                        }
                                                                        default: {
                                                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                                                        }
                                                                    }
                                                                    this.L$0 = null;
                                                                    this.label = 3;
                                                                    return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
                                                                }
                                                            };
                                                            combineKt$zipImpl$1$1$2$1$emit$10.label = 1;
                                                            return ChannelFlowKt.withContextUndispatched(this.$cnt, Unit.INSTANCE, this.$second, combineKt$zipImpl$1$1$2$1$10, combineKt$zipImpl$1$1$2$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
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
                                            return this.$flow.collect(combineKt$zipImpl$1$1$2$10, this) == object1 ? object1 : Unit.INSTANCE;
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
                            this.L$0 = receiveChannel0;
                            this.label = 1;
                            if(ChannelFlowKt.withContextUndispatched$default(coroutineContext1, Unit.INSTANCE, null, combineKt$zipImpl$1$1$20, this, 4, null) == object1) {
                                return object1;
                            }
                            receiveChannel1 = receiveChannel0;
                            break;
                        }
                        catch(AbortFlowException abortFlowException0) {
                            receiveChannel1 = receiveChannel0;
                            goto label_30;
                        }
                        catch(Throwable throwable0) {
                            receiveChannel1 = receiveChannel0;
                            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(receiveChannel1, null, 1, null);
                            throw throwable0;
                        }
                        return object1;
                    }
                    case 1: {
                        receiveChannel1 = (ReceiveChannel)this.L$0;
                        try {
                            try {
                                ResultKt.throwOnFailure(object0);
                                break;
                            }
                            catch(AbortFlowException abortFlowException0) {
                            }
                        label_30:
                            FlowExceptions_commonKt.checkOwnership(abortFlowException0, this.$this_unsafeFlow);
                            break;
                        }
                        catch(Throwable throwable0) {
                        }
                        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(receiveChannel1, null, 1, null);
                        throw throwable0;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(receiveChannel1, null, 1, null);
                return Unit.INSTANCE;
            }
        }

    }
}

