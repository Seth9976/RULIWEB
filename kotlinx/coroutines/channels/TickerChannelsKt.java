package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A/\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A/\u0010\b\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A4\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00032\b\b\u0002\u0010\u000B\u001A\u00020\f2\b\b\u0002\u0010\r\u001A\u00020\u000EH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000F"}, d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TickerChannelsKt {
    private static final Object fixedDelayTicker(long v, long v1, SendChannel sendChannel0, Continuation continuation0) {
        SendChannel sendChannel1;
        kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker.1 tickerChannelsKt$fixedDelayTicker$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker.1) {
            tickerChannelsKt$fixedDelayTicker$10 = (kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker.1)continuation0;
            if((tickerChannelsKt$fixedDelayTicker$10.label & 0x80000000) == 0) {
                tickerChannelsKt$fixedDelayTicker$10 = new ContinuationImpl(continuation0) {
                    long J$0;
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
                    }
                };
            }
            else {
                tickerChannelsKt$fixedDelayTicker$10.label ^= 0x80000000;
            }
        }
        else {
            tickerChannelsKt$fixedDelayTicker$10 = new ContinuationImpl(continuation0) {
                long J$0;
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
                }
            };
        }
        Object object0 = tickerChannelsKt$fixedDelayTicker$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    alab1:
        switch(tickerChannelsKt$fixedDelayTicker$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                tickerChannelsKt$fixedDelayTicker$10.L$0 = sendChannel0;
                tickerChannelsKt$fixedDelayTicker$10.J$0 = v;
                tickerChannelsKt$fixedDelayTicker$10.label = 1;
                if(DelayKt.delay(v1, tickerChannelsKt$fixedDelayTicker$10) != object1) {
                    goto label_30;
                }
                break;
            }
            case 1: {
                v = tickerChannelsKt$fixedDelayTicker$10.J$0;
                sendChannel0 = (SendChannel)tickerChannelsKt$fixedDelayTicker$10.L$0;
                ResultKt.throwOnFailure(object0);
                goto label_30;
            }
            case 2: {
                v = tickerChannelsKt$fixedDelayTicker$10.J$0;
                sendChannel1 = (SendChannel)tickerChannelsKt$fixedDelayTicker$10.L$0;
                ResultKt.throwOnFailure(object0);
                goto label_35;
            }
            case 3: {
                v = tickerChannelsKt$fixedDelayTicker$10.J$0;
                sendChannel1 = (SendChannel)tickerChannelsKt$fixedDelayTicker$10.L$0;
                ResultKt.throwOnFailure(object0);
                while(true) {
                    sendChannel0 = sendChannel1;
                label_30:
                    tickerChannelsKt$fixedDelayTicker$10.L$0 = sendChannel0;
                    tickerChannelsKt$fixedDelayTicker$10.J$0 = v;
                    tickerChannelsKt$fixedDelayTicker$10.label = 2;
                    if(sendChannel0.send(Unit.INSTANCE, tickerChannelsKt$fixedDelayTicker$10) == object1) {
                        break alab1;
                    }
                    sendChannel1 = sendChannel0;
                label_35:
                    tickerChannelsKt$fixedDelayTicker$10.L$0 = sendChannel1;
                    tickerChannelsKt$fixedDelayTicker$10.J$0 = v;
                    tickerChannelsKt$fixedDelayTicker$10.label = 3;
                    if(DelayKt.delay(v, tickerChannelsKt$fixedDelayTicker$10) != object1) {
                        continue;
                    }
                    break alab1;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        return object1;
    }

    private static final Object fixedPeriodTicker(long v, long v1, SendChannel sendChannel0, Continuation continuation0) {
        SendChannel sendChannel1;
        long v3;
        long v2;
        long v6;
        SendChannel sendChannel3;
        long v4;
        kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker.1 tickerChannelsKt$fixedPeriodTicker$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker.1) {
            tickerChannelsKt$fixedPeriodTicker$10 = (kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker.1)continuation0;
            if((tickerChannelsKt$fixedPeriodTicker$10.label & 0x80000000) == 0) {
                tickerChannelsKt$fixedPeriodTicker$10 = new ContinuationImpl(continuation0) {
                    long J$0;
                    long J$1;
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return TickerChannelsKt.fixedPeriodTicker(0L, 0L, null, this);
                    }
                };
            }
            else {
                tickerChannelsKt$fixedPeriodTicker$10.label ^= 0x80000000;
            }
        }
        else {
            tickerChannelsKt$fixedPeriodTicker$10 = new ContinuationImpl(continuation0) {
                long J$0;
                long J$1;
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return TickerChannelsKt.fixedPeriodTicker(0L, 0L, null, this);
                }
            };
        }
        Object object0 = tickerChannelsKt$fixedPeriodTicker$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(tickerChannelsKt$fixedPeriodTicker$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
                long v7 = (abstractTimeSource0 == null ? System.nanoTime() : abstractTimeSource0.nanoTime()) + EventLoop_commonKt.delayToNanos(v1);
                sendChannel3 = sendChannel0;
                tickerChannelsKt$fixedPeriodTicker$10.L$0 = sendChannel3;
                tickerChannelsKt$fixedPeriodTicker$10.J$0 = v;
                tickerChannelsKt$fixedPeriodTicker$10.J$1 = v7;
                tickerChannelsKt$fixedPeriodTicker$10.label = 1;
                if(DelayKt.delay(v1, tickerChannelsKt$fixedPeriodTicker$10) != object1) {
                    v4 = v7;
                    v6 = EventLoop_commonKt.delayToNanos(v);
                    while(true) {
                    label_45:
                        long v8 = v4 + v6;
                        tickerChannelsKt$fixedPeriodTicker$10.L$0 = sendChannel3;
                        tickerChannelsKt$fixedPeriodTicker$10.J$0 = v8;
                        tickerChannelsKt$fixedPeriodTicker$10.J$1 = v6;
                        tickerChannelsKt$fixedPeriodTicker$10.label = 2;
                        if(sendChannel3.send(Unit.INSTANCE, tickerChannelsKt$fixedPeriodTicker$10) == object1) {
                            break;
                        }
                        v3 = v8;
                        v2 = v6;
                        sendChannel1 = sendChannel3;
                    label_54:
                        AbstractTimeSource abstractTimeSource1 = AbstractTimeSourceKt.getTimeSource();
                        long v9 = abstractTimeSource1 == null ? System.nanoTime() : abstractTimeSource1.nanoTime();
                        long v10 = RangesKt.coerceAtLeast(v3 - v9, 0L);
                        if(v10 != 0L || v2 == 0L) {
                            tickerChannelsKt$fixedPeriodTicker$10.L$0 = sendChannel1;
                            tickerChannelsKt$fixedPeriodTicker$10.J$0 = v3;
                            tickerChannelsKt$fixedPeriodTicker$10.J$1 = v2;
                            tickerChannelsKt$fixedPeriodTicker$10.label = 4;
                            if(DelayKt.delay(v10 / 1000000L, tickerChannelsKt$fixedPeriodTicker$10) != object1) {
                                goto label_71;
                            }
                            break;
                        }
                        else {
                            long v11 = v2 - (v9 - v3) % v2;
                            v3 = v9 + v11;
                            tickerChannelsKt$fixedPeriodTicker$10.L$0 = sendChannel1;
                            tickerChannelsKt$fixedPeriodTicker$10.J$0 = v3;
                            tickerChannelsKt$fixedPeriodTicker$10.J$1 = v2;
                            tickerChannelsKt$fixedPeriodTicker$10.label = 3;
                            if(DelayKt.delay(v11 / 1000000L, tickerChannelsKt$fixedPeriodTicker$10) == object1) {
                                return object1;
                            }
                        }
                    label_71:
                        v6 = v2;
                        v4 = v3;
                        sendChannel3 = sendChannel1;
                    }
                }
                break;
            }
            case 1: {
                v4 = tickerChannelsKt$fixedPeriodTicker$10.J$1;
                long v5 = tickerChannelsKt$fixedPeriodTicker$10.J$0;
                SendChannel sendChannel2 = (SendChannel)tickerChannelsKt$fixedPeriodTicker$10.L$0;
                ResultKt.throwOnFailure(object0);
                sendChannel3 = sendChannel2;
                v6 = EventLoop_commonKt.delayToNanos(v5);
                goto label_45;
            }
            case 2: {
                v2 = tickerChannelsKt$fixedPeriodTicker$10.J$1;
                v3 = tickerChannelsKt$fixedPeriodTicker$10.J$0;
                sendChannel1 = (SendChannel)tickerChannelsKt$fixedPeriodTicker$10.L$0;
                ResultKt.throwOnFailure(object0);
                goto label_54;
            }
            case 3: {
                v2 = tickerChannelsKt$fixedPeriodTicker$10.J$1;
                v3 = tickerChannelsKt$fixedPeriodTicker$10.J$0;
                sendChannel1 = (SendChannel)tickerChannelsKt$fixedPeriodTicker$10.L$0;
                ResultKt.throwOnFailure(object0);
                goto label_71;
            }
            case 4: {
                v2 = tickerChannelsKt$fixedPeriodTicker$10.J$1;
                v3 = tickerChannelsKt$fixedPeriodTicker$10.J$0;
                sendChannel1 = (SendChannel)tickerChannelsKt$fixedPeriodTicker$10.L$0;
                ResultKt.throwOnFailure(object0);
                goto label_71;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        return object1;
    }

    public static final ReceiveChannel ticker(long v, long v1, CoroutineContext coroutineContext0, TickerMode tickerMode0) {
        if(v < 0L) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + v + " ms").toString());
        }
        if(v1 < 0L) {
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + v1 + " ms").toString());
        }
        CoroutineContext coroutineContext1 = Dispatchers.getUnconfined().plus(coroutineContext0);
        kotlinx.coroutines.channels.TickerChannelsKt.ticker.3 tickerChannelsKt$ticker$30 = new Function2(tickerMode0, v, v1, null) {
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            public final class WhenMappings {
                public static final int[] $EnumSwitchMapping$0;

                static {
                    int[] arr_v = new int[TickerMode.values().length];
                    try {
                        arr_v[TickerMode.FIXED_PERIOD.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    try {
                        arr_v[TickerMode.FIXED_DELAY.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    WhenMappings.$EnumSwitchMapping$0 = arr_v;
                }
            }

            final long $delayMillis;
            final long $initialDelayMillis;
            final TickerMode $mode;
            private Object L$0;
            int label;

            {
                this.$mode = tickerMode0;
                this.$delayMillis = v;
                this.$initialDelayMillis = v1;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.TickerChannelsKt.ticker.3 tickerChannelsKt$ticker$30 = new kotlinx.coroutines.channels.TickerChannelsKt.ticker.3(this.$mode, this.$delayMillis, this.$initialDelayMillis, continuation0);
                tickerChannelsKt$ticker$30.L$0 = object0;
                return tickerChannelsKt$ticker$30;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((ProducerScope)object0), ((Continuation)object1));
            }

            public final Object invoke(ProducerScope producerScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.TickerChannelsKt.ticker.3)this.create(producerScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ProducerScope producerScope0 = (ProducerScope)this.L$0;
                        switch(WhenMappings.$EnumSwitchMapping$0[this.$mode.ordinal()]) {
                            case 1: {
                                SendChannel sendChannel0 = producerScope0.getChannel();
                                this.label = 1;
                                if(TickerChannelsKt.fixedPeriodTicker(this.$delayMillis, this.$initialDelayMillis, sendChannel0, this) == object1) {
                                    return object1;
                                }
                                break;
                            }
                            case 2: {
                                SendChannel sendChannel1 = producerScope0.getChannel();
                                this.label = 2;
                                if(TickerChannelsKt.fixedDelayTicker(this.$delayMillis, this.$initialDelayMillis, sendChannel1, this) == object1) {
                                    return object1;
                                }
                                break;
                            }
                            default: {
                                return Unit.INSTANCE;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    case 1: 
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        };
        return ProduceKt.produce(() -> EmptyCoroutineContext.INSTANCE, coroutineContext1, 0, tickerChannelsKt$ticker$30);
    }

    public static ReceiveChannel ticker$default(long v, long v1, CoroutineContext coroutineContext0, TickerMode tickerMode0, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v1 = v;
        }
        if((v2 & 4) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v2 & 8) != 0) {
            tickerMode0 = TickerMode.FIXED_PERIOD;
        }
        return TickerChannelsKt.ticker(v, v1, coroutineContext0, tickerMode0);
    }
}

