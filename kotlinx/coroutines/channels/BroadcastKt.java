package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u009E\u0001\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\t2-\b\u0002\u0010\n\u001A\'\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000E\u0012\u0004\b\b(\u000F\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000Bj\u0004\u0018\u0001`\u00112/\b\u0001\u0010\u0012\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0013¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001A2\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00192\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\tH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001A"}, d2 = {"broadcast", "Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class BroadcastKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    public static final BroadcastChannel broadcast(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, CoroutineStart coroutineStart0, Function1 function10, Function2 function20) {
        BroadcastCoroutine broadcastCoroutine0 = new BroadcastCoroutine(CoroutineContextKt.newCoroutineContext(coroutineScope0, coroutineContext0), BroadcastChannelKt.BroadcastChannel(v), true);
        if(function10 != null) {
            broadcastCoroutine0.invokeOnCompletion(function10);
        }
        broadcastCoroutine0.start(coroutineStart0, broadcastCoroutine0, function20);
        return broadcastCoroutine0;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    public static final BroadcastChannel broadcast(ReceiveChannel receiveChannel0, int v, CoroutineStart coroutineStart0) {
        return BroadcastKt.broadcast$default(CoroutineScopeKt.plus(CoroutineScopeKt.plus(() -> EmptyCoroutineContext.INSTANCE, Dispatchers.getUnconfined()), new CoroutineExceptionHandler(CoroutineExceptionHandler.Key) {
            @Override  // kotlinx.coroutines.CoroutineExceptionHandler
            public void handleException(CoroutineContext coroutineContext0, Throwable throwable0) {
            }
        }), null, v, coroutineStart0, new Function1(receiveChannel0) {
            final ReceiveChannel $this_broadcast;

            {
                this.$this_broadcast = receiveChannel0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                ChannelsKt.cancelConsumed(this.$this_broadcast, throwable0);
            }
        }, new Function2(receiveChannel0, null) {
            final ReceiveChannel $channel;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$channel = receiveChannel0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.BroadcastKt.broadcast.2 broadcastKt$broadcast$20 = new kotlinx.coroutines.channels.BroadcastKt.broadcast.2(this.$channel, continuation0);
                broadcastKt$broadcast$20.L$0 = object0;
                return broadcastKt$broadcast$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.BroadcastKt.broadcast.2)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                ProducerScope producerScope1;
                ChannelIterator channelIterator0;
                ProducerScope producerScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        producerScope0 = (ProducerScope)this.L$0;
                        channelIterator0 = this.$channel.iterator();
                        goto label_15;
                    }
                    case 1: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_22;
                    }
                    case 2: {
                        channelIterator0 = (ChannelIterator)this.L$1;
                        producerScope1 = (ProducerScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                do {
                    producerScope0 = producerScope1;
                label_15:
                    this.L$0 = producerScope0;
                    this.L$1 = channelIterator0;
                    this.label = 1;
                    Object object2 = channelIterator0.hasNext(this);
                    if(object2 == object1) {
                        return object1;
                    }
                    producerScope1 = producerScope0;
                    object0 = object2;
                label_22:
                    if(!((Boolean)object0).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                    Object object3 = channelIterator0.next();
                    this.L$0 = producerScope1;
                    this.L$1 = channelIterator0;
                    this.label = 2;
                }
                while(producerScope1.send(object3, this) != object1);
                return object1;
            }
        }, 1, null);
    }

    public static BroadcastChannel broadcast$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, CoroutineStart coroutineStart0, Function1 function10, Function2 function20, int v1, Object object0) {
        if((v1 & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v1 & 2) != 0) {
            v = 1;
        }
        if((v1 & 4) != 0) {
            coroutineStart0 = CoroutineStart.LAZY;
        }
        if((v1 & 8) != 0) {
            function10 = null;
        }
        return BroadcastKt.broadcast(coroutineScope0, coroutineContext0, v, coroutineStart0, function10, function20);
    }

    public static BroadcastChannel broadcast$default(ReceiveChannel receiveChannel0, int v, CoroutineStart coroutineStart0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 1;
        }
        if((v1 & 2) != 0) {
            coroutineStart0 = CoroutineStart.LAZY;
        }
        return BroadcastKt.broadcast(receiveChannel0, v, coroutineStart0);
    }
}

