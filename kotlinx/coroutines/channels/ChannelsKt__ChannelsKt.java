package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A%\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001A\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0005\u001A,\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001A\u0002H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"sendBlocking", "", "E", "Lkotlinx/coroutines/channels/SendChannel;", "element", "(Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Object;)V", "trySendBlocking", "Lkotlinx/coroutines/channels/ChannelResult;", "(Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/channels/ChannelsKt")
final class ChannelsKt__ChannelsKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of \'trySendBlocking\'. Consider handling the result of \'trySendBlocking\' explicitly and rethrow exception if necessary", replaceWith = @ReplaceWith(expression = "trySendBlocking(element)", imports = {}))
    public static final void sendBlocking(SendChannel sendChannel0, Object object0) {
        if(ChannelResult.isSuccess-impl(sendChannel0.trySend-JP2dKIU(object0))) {
            return;
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new Function2(sendChannel0, object0, null) {
            final Object $element;
            final SendChannel $this_sendBlocking;
            int label;

            {
                this.$this_sendBlocking = sendChannel0;
                this.$element = object0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.channels.ChannelsKt__ChannelsKt.sendBlocking.1(this.$this_sendBlocking, this.$element, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__ChannelsKt.sendBlocking.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return this.$this_sendBlocking.send(this.$element, this) == object1 ? object1 : Unit.INSTANCE;
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
    }

    public static final Object trySendBlocking(SendChannel sendChannel0, Object object0) {
        Object object1 = sendChannel0.trySend-JP2dKIU(object0);
        if(!(object1 instanceof Failed)) {
            Unit unit0 = (Unit)object1;
            return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
        }
        return ((ChannelResult)BuildersKt__BuildersKt.runBlocking$default(null, new Function2(sendChannel0, object0, null) {
            final Object $element;
            final SendChannel $this_trySendBlocking;
            private Object L$0;
            int label;

            {
                this.$this_trySendBlocking = sendChannel0;
                this.$element = object0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.channels.ChannelsKt__ChannelsKt.trySendBlocking.2 channelsKt__ChannelsKt$trySendBlocking$20 = new kotlinx.coroutines.channels.ChannelsKt__ChannelsKt.trySendBlocking.2(this.$this_trySendBlocking, this.$element, continuation0);
                channelsKt__ChannelsKt$trySendBlocking$20.L$0 = object0;
                return channelsKt__ChannelsKt$trySendBlocking$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.ChannelsKt__ChannelsKt.trySendBlocking.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Unit unit0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        try {
                            this.label = 1;
                            if(this.$this_trySendBlocking.send(this.$element, this) == object1) {
                                return object1;
                            label_8:
                                ResultKt.throwOnFailure(object0);
                            }
                            unit0 = Unit.INSTANCE;
                            goto label_13;
                        }
                        catch(Throwable throwable0) {
                        }
                        break;
                    }
                    case 1: {
                        goto label_8;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                unit0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
            label_13:
                if(Result.isSuccess-impl(unit0)) {
                    return ChannelResult.box-impl(ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE));
                }
                Throwable throwable1 = Result.exceptionOrNull-impl(unit0);
                return ChannelResult.box-impl(ChannelResult.Companion.closed-JP2dKIU(throwable1));
            }
        }, 1, null)).unbox-impl();
    }
}

