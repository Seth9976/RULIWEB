package kotlinx.coroutines.flow.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001D\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\n\u0010\u0015\u001A\u0004\u0018\u00010\u0016H\u0014J\u001F\u0010\u0017\u001A\u00020\u000E2\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001AJ\u001F\u0010\u001B\u001A\u00020\u000E2\f\u0010\u001C\u001A\b\u0012\u0004\u0012\u00028\u00000\fH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u001DJ&\u0010\u001E\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH$J\u0010\u0010\u001F\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010 H\u0016J&\u0010!\u001A\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0016\u0010\"\u001A\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010\u001C\u001A\u00020$H\u0016J\b\u0010%\u001A\u00020\u0016H\u0016R\u0010\u0010\u0005\u001A\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R9\u0010\n\u001A$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000E0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000F0\u000B8@X\u0080\u0004ø\u0001\u0000¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001A\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlow;", "T", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collectToFun", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "getCollectToFun$kotlinx_coroutines_core", "()Lkotlin/jvm/functions/Function2;", "produceCapacity", "getProduceCapacity$kotlinx_coroutines_core", "()I", "additionalToStringProps", "", "collect", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "dropChannelOperators", "Lkotlinx/coroutines/flow/Flow;", "fuse", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ChannelFlow implements FusibleFlow {
    public final int capacity;
    public final CoroutineContext context;
    public final BufferOverflow onBufferOverflow;

    public ChannelFlow(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        this.context = coroutineContext0;
        this.capacity = v;
        this.onBufferOverflow = bufferOverflow0;
    }

    protected String additionalToStringProps() {
        return null;
    }

    @Override  // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        return ChannelFlow.collect$suspendImpl(this, flowCollector0, continuation0);
    }

    static Object collect$suspendImpl(ChannelFlow channelFlow0, FlowCollector flowCollector0, Continuation continuation0) {
        Object object0 = CoroutineScopeKt.coroutineScope(new Function2(flowCollector0, channelFlow0, null) {
            final FlowCollector $collector;
            private Object L$0;
            int label;

            {
                this.$collector = flowCollector0;
                ChannelFlow.this = channelFlow0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.ChannelFlow.collect.2 channelFlow$collect$20 = new kotlinx.coroutines.flow.internal.ChannelFlow.collect.2(this.$collector, ChannelFlow.this, continuation0);
                channelFlow$collect$20.L$0 = object0;
                return channelFlow$collect$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.internal.ChannelFlow.collect.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ReceiveChannel receiveChannel0 = ChannelFlow.this.produceImpl(((CoroutineScope)this.L$0));
                        this.label = 1;
                        return FlowKt.emitAll(this.$collector, receiveChannel0, this) == object1 ? object1 : Unit.INSTANCE;
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
        }, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    protected abstract Object collectTo(ProducerScope arg1, Continuation arg2);

    protected abstract ChannelFlow create(CoroutineContext arg1, int arg2, BufferOverflow arg3);

    public Flow dropChannelOperators() {
        return null;
    }

    @Override  // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow fuse(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        CoroutineContext coroutineContext1 = coroutineContext0.plus(this.context);
        if(bufferOverflow0 == BufferOverflow.SUSPEND) {
            int v1 = this.capacity;
            if(v1 != -3) {
                if(v == -3) {
                    v = v1;
                }
                else if(v1 != -2) {
                    if(v == -2) {
                        v = v1;
                    }
                    else {
                        v1 = this.capacity + v;
                        v = v1 < 0 ? 0x7FFFFFFF : v1;
                    }
                }
            }
            bufferOverflow0 = this.onBufferOverflow;
        }
        return !Intrinsics.areEqual(coroutineContext1, this.context) || v != this.capacity || bufferOverflow0 != this.onBufferOverflow ? this.create(coroutineContext1, v, bufferOverflow0) : this;
    }

    public final Function2 getCollectToFun$kotlinx_coroutines_core() {
        return new Function2(null) {
            Object L$0;
            int label;

            {
                ChannelFlow.this = channelFlow0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.ChannelFlow.collectToFun.1 channelFlow$collectToFun$10 = new kotlinx.coroutines.flow.internal.ChannelFlow.collectToFun.1(ChannelFlow.this, continuation0);
                channelFlow$collectToFun$10.L$0 = object0;
                return channelFlow$collectToFun$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.internal.ChannelFlow.collectToFun.1)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return ChannelFlow.this.collectTo(((ProducerScope)this.L$0), this) == object1 ? object1 : Unit.INSTANCE;
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
    }

    public final int getProduceCapacity$kotlinx_coroutines_core() {
        return this.capacity == -3 ? -2 : this.capacity;
    }

    public ReceiveChannel produceImpl(CoroutineScope coroutineScope0) {
        int v = this.getProduceCapacity$kotlinx_coroutines_core();
        Function2 function20 = this.getCollectToFun$kotlinx_coroutines_core();
        return ProduceKt.produce$default(coroutineScope0, this.context, v, this.onBufferOverflow, CoroutineStart.ATOMIC, null, function20, 16, null);
    }

    @Override
    public String toString() {
        ArrayList arrayList0 = new ArrayList(4);
        String s = this.additionalToStringProps();
        if(s != null) {
            arrayList0.add(s);
        }
        if(this.context != EmptyCoroutineContext.INSTANCE) {
            arrayList0.add("context=" + this.context);
        }
        if(this.capacity != -3) {
            arrayList0.add("capacity=" + this.capacity);
        }
        if(this.onBufferOverflow != BufferOverflow.SUSPEND) {
            arrayList0.add("onBufferOverflow=" + this.onBufferOverflow);
        }
        return DebugStringsKt.getClassSimpleName(this) + '[' + CollectionsKt.joinToString$default(arrayList0, ", ", null, null, 0, null, null, 62, null) + ']';
    }
}

