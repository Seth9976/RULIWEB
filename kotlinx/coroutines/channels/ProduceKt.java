package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A)\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u000E\b\u0002\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0005\u001A\u009E\u0001\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\b\b\u0002\u0010\n\u001A\u00020\u000B2\b\b\u0002\u0010\f\u001A\u00020\r2\b\b\u0002\u0010\u000E\u001A\u00020\u000F2-\b\u0002\u0010\u0010\u001A\'\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011j\u0004\u0018\u0001`\u00162/\b\u0001\u0010\u0003\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017\u00A2\u0006\u0002\b\u001AH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001B\u001A\u00A8\u0001\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\b\b\u0002\u0010\n\u001A\u00020\u000B2\b\b\u0002\u0010\f\u001A\u00020\r2\b\b\u0002\u0010\u001C\u001A\u00020\u001D2\b\b\u0002\u0010\u000E\u001A\u00020\u000F2-\b\u0002\u0010\u0010\u001A\'\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011j\u0004\u0018\u0001`\u00162/\b\u0001\u0010\u0003\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017\u00A2\u0006\u0002\b\u001AH\u0000\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001E\u001Ae\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\b\b\u0002\u0010\n\u001A\u00020\u000B2\b\b\u0002\u0010\f\u001A\u00020\r2/\b\u0001\u0010\u0003\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017\u00A2\u0006\u0002\b\u001AH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001F\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006 "}, d2 = {"awaitClose", "", "Lkotlinx/coroutines/channels/ProducerScope;", "block", "Lkotlin/Function0;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "produce", "Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlinx/coroutines/CompletionHandler;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ProduceKt {
    public static final Object awaitClose(ProducerScope producerScope0, Function0 function00, Continuation continuation0) {
        kotlinx.coroutines.channels.ProduceKt.awaitClose.1 produceKt$awaitClose$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ProduceKt.awaitClose.1) {
            produceKt$awaitClose$10 = (kotlinx.coroutines.channels.ProduceKt.awaitClose.1)continuation0;
            if((produceKt$awaitClose$10.label & 0x80000000) == 0) {
                produceKt$awaitClose$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ProduceKt.awaitClose(null, null, this);
                    }
                };
            }
            else {
                produceKt$awaitClose$10.label ^= 0x80000000;
            }
        }
        else {
            produceKt$awaitClose$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ProduceKt.awaitClose(null, null, this);
                }
            };
        }
        Object object0 = produceKt$awaitClose$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(produceKt$awaitClose$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                if(produceKt$awaitClose$10.getContext().get(Job.Key) != producerScope0) {
                    throw new IllegalStateException("awaitClose() can only be invoked from the producer context");
                }
                try {
                    produceKt$awaitClose$10.L$0 = producerScope0;
                    produceKt$awaitClose$10.L$1 = function00;
                    produceKt$awaitClose$10.label = 1;
                    CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(produceKt$awaitClose$10), 1);
                    cancellableContinuationImpl0.initCancellability();
                    producerScope0.invokeOnClose(new Function1(cancellableContinuationImpl0) {
                        final CancellableContinuation $cont;

                        {
                            this.$cont = cancellableContinuation0;
                            super(1);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            this.invoke(((Throwable)object0));
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Throwable throwable0) {
                            this.$cont.resumeWith(Unit.INSTANCE);
                        }
                    });
                    Object object2 = cancellableContinuationImpl0.getResult();
                    if(object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(produceKt$awaitClose$10);
                    }
                }
                catch(Throwable throwable0) {
                    function00.invoke();
                    throw throwable0;
                }
                if(object2 == object1) {
                    return object1;
                }
                break;
            }
            case 1: {
                function00 = (Function0)produceKt$awaitClose$10.L$1;
                ProducerScope producerScope1 = (ProducerScope)produceKt$awaitClose$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(Throwable throwable0) {
                    function00.invoke();
                    throw throwable0;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        function00.invoke();
        return Unit.INSTANCE;
    }

    public static Object awaitClose$default(ProducerScope producerScope0, Function0 function00, Continuation continuation0, int v, Object object0) {
        if((v & 1) != 0) {
            function00 = kotlinx.coroutines.channels.ProduceKt.awaitClose.2.INSTANCE;
        }
        return ProduceKt.awaitClose(producerScope0, function00, continuation0);

        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.ProduceKt.awaitClose.2 extends Lambda implements Function0 {
            public static final kotlinx.coroutines.channels.ProduceKt.awaitClose.2 INSTANCE;

            static {
                kotlinx.coroutines.channels.ProduceKt.awaitClose.2.INSTANCE = new kotlinx.coroutines.channels.ProduceKt.awaitClose.2();
            }

            kotlinx.coroutines.channels.ProduceKt.awaitClose.2() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return Unit.INSTANCE;
            }

            public final void invoke() {
            }
        }

    }

    public static final ReceiveChannel produce(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, Function2 function20) {
        return ProduceKt.produce(coroutineScope0, coroutineContext0, v, BufferOverflow.SUSPEND, CoroutineStart.DEFAULT, null, function20);
    }

    public static final ReceiveChannel produce(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, CoroutineStart coroutineStart0, Function1 function10, Function2 function20) {
        return ProduceKt.produce(coroutineScope0, coroutineContext0, v, BufferOverflow.SUSPEND, coroutineStart0, function10, function20);
    }

    public static final ReceiveChannel produce(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0, CoroutineStart coroutineStart0, Function1 function10, Function2 function20) {
        Channel channel0 = ChannelKt.Channel$default(v, bufferOverflow0, null, 4, null);
        ProducerCoroutine producerCoroutine0 = new ProducerCoroutine(CoroutineContextKt.newCoroutineContext(coroutineScope0, coroutineContext0), channel0);
        if(function10 != null) {
            producerCoroutine0.invokeOnCompletion(function10);
        }
        producerCoroutine0.start(coroutineStart0, producerCoroutine0, function20);
        return producerCoroutine0;
    }

    public static ReceiveChannel produce$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, Function2 function20, int v1, Object object0) {
        if((v1 & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v1 & 2) != 0) {
            v = 0;
        }
        return ProduceKt.produce(coroutineScope0, coroutineContext0, v, function20);
    }

    public static ReceiveChannel produce$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, CoroutineStart coroutineStart0, Function1 function10, Function2 function20, int v1, Object object0) {
        if((v1 & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v1 & 2) != 0) {
            v = 0;
        }
        if((v1 & 4) != 0) {
            coroutineStart0 = CoroutineStart.DEFAULT;
        }
        if((v1 & 8) != 0) {
            function10 = null;
        }
        return ProduceKt.produce(coroutineScope0, coroutineContext0, v, coroutineStart0, function10, function20);
    }

    public static ReceiveChannel produce$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0, CoroutineStart coroutineStart0, Function1 function10, Function2 function20, int v1, Object object0) {
        if((v1 & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v1 & 2) != 0) {
            v = 0;
        }
        if((v1 & 4) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        if((v1 & 8) != 0) {
            coroutineStart0 = CoroutineStart.DEFAULT;
        }
        if((v1 & 16) != 0) {
            function10 = null;
        }
        return ProduceKt.produce(coroutineScope0, coroutineContext0, v, bufferOverflow0, coroutineStart0, function10, function20);
    }
}

