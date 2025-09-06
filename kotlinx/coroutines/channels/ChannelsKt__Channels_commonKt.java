package kotlinx.coroutines.channels;

import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u001A\u001A\u0010\u0002\u001A\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0001\u001AC\u0010\u0007\u001A\u0002H\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\t0\n2\u001D\u0010\u000B\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0004\u0012\u0004\u0012\u0002H\b0\f¢\u0006\u0002\b\rH\u0087\b¢\u0006\u0002\u0010\u000E\u001AP\u0010\u0007\u001A\u0002H\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\t0\u00042\u001D\u0010\u000B\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0004\u0012\u0004\u0012\u0002H\b0\f¢\u0006\u0002\b\rH\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u000F\u001A5\u0010\u0010\u001A\u00020\u0003\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\n2\u0012\u0010\u0011\u001A\u000E\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00030\fH\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001A5\u0010\u0010\u001A\u00020\u0003\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u00042\u0012\u0010\u0011\u001A\u000E\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00030\fH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001A$\u0010\u0014\u001A\n\u0012\u0006\u0012\u0004\u0018\u0001H\t0\u0015\"\b\b\u0000\u0010\t*\u00020\u0016*\b\u0012\u0004\u0012\u0002H\t0\u0004H\u0007\u001A\'\u0010\u0017\u001A\u0004\u0018\u0001H\t\"\b\b\u0000\u0010\t*\u00020\u0016*\b\u0012\u0004\u0012\u0002H\t0\u0004H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001A\'\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\t0\u001A\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001B"}, d2 = {"DEFAULT_CLOSE_MESSAGE", "", "cancelConsumed", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "cause", "", "consume", "R", "E", "Lkotlinx/coroutines/channels/BroadcastChannel;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "consumeEach", "action", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onReceiveOrNull", "Lkotlinx/coroutines/selects/SelectClause1;", "", "receiveOrNull", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toList", "", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/channels/ChannelsKt")
final class ChannelsKt__Channels_commonKt {
    public static final void cancelConsumed(ReceiveChannel receiveChannel0, Throwable throwable0) {
        CancellationException cancellationException0 = null;
        if(throwable0 != null) {
            if(throwable0 instanceof CancellationException) {
                cancellationException0 = (CancellationException)throwable0;
            }
            if(cancellationException0 == null) {
                cancellationException0 = ExceptionsKt.CancellationException("Channel was consumed, consumer had failed", throwable0);
            }
        }
        receiveChannel0.cancel(cancellationException0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    public static final Object consume(BroadcastChannel broadcastChannel0, Function1 function10) {
        ReceiveChannel receiveChannel0 = broadcastChannel0.openSubscription();
        try {
            return function10.invoke(receiveChannel0);
        }
        finally {
            DefaultImpls.cancel$default(receiveChannel0, null, 1, null);
        }
    }

    public static final Object consume(ReceiveChannel receiveChannel0, Function1 function10) {
        Object object0;
        try {
            object0 = function10.invoke(receiveChannel0);
        }
        catch(Throwable throwable0) {
            ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
            throw throwable0;
        }
        ChannelsKt.cancelConsumed(receiveChannel0, null);
        return object0;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    public static final Object consumeEach(BroadcastChannel broadcastChannel0, Function1 function10, Continuation continuation0) {
        Object object2;
        ReceiveChannel receiveChannel3;
        kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach.3 channelsKt__Channels_commonKt$consumeEach$31;
        Throwable throwable1;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach.3 channelsKt__Channels_commonKt$consumeEach$30;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach.3) {
            channelsKt__Channels_commonKt$consumeEach$30 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach.3)continuation0;
            if((channelsKt__Channels_commonKt$consumeEach$30.label & 0x80000000) == 0) {
                channelsKt__Channels_commonKt$consumeEach$30 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__Channels_commonKt.consumeEach(null, null, this);
                    }
                };
            }
            else {
                channelsKt__Channels_commonKt$consumeEach$30.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__Channels_commonKt$consumeEach$30 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__Channels_commonKt.consumeEach(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__Channels_commonKt$consumeEach$30.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__Channels_commonKt$consumeEach$30.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ReceiveChannel receiveChannel0 = broadcastChannel0.openSubscription();
                try {
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    goto label_40;
                }
                catch(Throwable throwable0) {
                    receiveChannel2 = receiveChannel0;
                    throwable1 = throwable0;
                    DefaultImpls.cancel$default(receiveChannel2, null, 1, null);
                    throw throwable1;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__Channels_commonKt$consumeEach$30.L$2;
                receiveChannel2 = (ReceiveChannel)channelsKt__Channels_commonKt$consumeEach$30.L$1;
                Function1 function11 = (Function1)channelsKt__Channels_commonKt$consumeEach$30.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    channelsKt__Channels_commonKt$consumeEach$31 = channelsKt__Channels_commonKt$consumeEach$30;
                    receiveChannel3 = receiveChannel2;
                    function10 = function11;
                    break;
                }
                catch(Throwable throwable1) {
                }
                DefaultImpls.cancel$default(receiveChannel2, null, 1, null);
                throw throwable1;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        while(true) {
            try {
                if(!((Boolean)object0).booleanValue()) {
                    break;
                }
                function10.invoke(channelIterator0.next());
                receiveChannel1 = receiveChannel3;
                channelsKt__Channels_commonKt$consumeEach$30 = channelsKt__Channels_commonKt$consumeEach$31;
            }
            catch(Throwable throwable1) {
                receiveChannel2 = receiveChannel3;
                DefaultImpls.cancel$default(receiveChannel2, null, 1, null);
                throw throwable1;
            }
            try {
            label_40:
                channelsKt__Channels_commonKt$consumeEach$30.L$0 = function10;
                channelsKt__Channels_commonKt$consumeEach$30.L$1 = receiveChannel1;
                channelsKt__Channels_commonKt$consumeEach$30.L$2 = channelIterator0;
                channelsKt__Channels_commonKt$consumeEach$30.label = 1;
                object2 = channelIterator0.hasNext(channelsKt__Channels_commonKt$consumeEach$30);
            }
            catch(Throwable throwable1) {
                receiveChannel2 = receiveChannel1;
                DefaultImpls.cancel$default(receiveChannel2, null, 1, null);
                throw throwable1;
            }
            if(object2 == object1) {
                return object1;
            }
            channelsKt__Channels_commonKt$consumeEach$31 = channelsKt__Channels_commonKt$consumeEach$30;
            receiveChannel3 = receiveChannel1;
            object0 = object2;
        }
        DefaultImpls.cancel$default(receiveChannel3, null, 1, null);
        return Unit.INSTANCE;
    }

    public static final Object consumeEach(ReceiveChannel receiveChannel0, Function1 function10, Continuation continuation0) {
        Throwable throwable1;
        Function1 function11;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach.1 channelsKt__Channels_commonKt$consumeEach$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach.1) {
            channelsKt__Channels_commonKt$consumeEach$10 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach.1)continuation0;
            if((channelsKt__Channels_commonKt$consumeEach$10.label & 0x80000000) == 0) {
                channelsKt__Channels_commonKt$consumeEach$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt__Channels_commonKt.consumeEach(null, null, this);
                    }
                };
            }
            else {
                channelsKt__Channels_commonKt$consumeEach$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__Channels_commonKt$consumeEach$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt__Channels_commonKt.consumeEach(null, null, this);
                }
            };
        }
        Object object0 = channelsKt__Channels_commonKt$consumeEach$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__Channels_commonKt$consumeEach$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    function11 = function10;
                    goto label_27;
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    break;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__Channels_commonKt$consumeEach$10.L$2;
                receiveChannel1 = (ReceiveChannel)channelsKt__Channels_commonKt$consumeEach$10.L$1;
                Function1 function12 = (Function1)channelsKt__Channels_commonKt$consumeEach$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_42;
                        }
                        function12.invoke(channelIterator0.next());
                        function11 = function12;
                    label_27:
                        channelsKt__Channels_commonKt$consumeEach$10.L$0 = function11;
                        channelsKt__Channels_commonKt$consumeEach$10.L$1 = receiveChannel1;
                        channelsKt__Channels_commonKt$consumeEach$10.L$2 = channelIterator0;
                        channelsKt__Channels_commonKt$consumeEach$10.label = 1;
                        Object object2 = channelIterator0.hasNext(channelsKt__Channels_commonKt$consumeEach$10);
                        if(object2 == object1) {
                            return object1;
                        }
                        function12 = function11;
                        object0 = object2;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_42:
        ChannelsKt.cancelConsumed(receiveChannel1, null);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    private static final Object consumeEach$$forInline(BroadcastChannel broadcastChannel0, Function1 function10, Continuation continuation0) {
        ReceiveChannel receiveChannel0 = broadcastChannel0.openSubscription();
        try {
            ChannelIterator channelIterator0 = receiveChannel0.iterator();
            while(((Boolean)channelIterator0.hasNext(null)).booleanValue()) {
                function10.invoke(channelIterator0.next());
            }
            return Unit.INSTANCE;
        }
        finally {
            DefaultImpls.cancel$default(receiveChannel0, null, 1, null);
        }
    }

    private static final Object consumeEach$$forInline(ReceiveChannel receiveChannel0, Function1 function10, Continuation continuation0) {
        try {
            ChannelIterator channelIterator0 = receiveChannel0.iterator();
            while(((Boolean)channelIterator0.hasNext(null)).booleanValue()) {
                function10.invoke(channelIterator0.next());
            }
        }
        catch(Throwable throwable0) {
            ChannelsKt.cancelConsumed(receiveChannel0, throwable0);
            throw throwable0;
        }
        ChannelsKt.cancelConsumed(receiveChannel0, null);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of \'onReceiveCatching\'")
    public static final SelectClause1 onReceiveOrNull(ReceiveChannel receiveChannel0) {
        Intrinsics.checkNotNull(receiveChannel0, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.onReceiveOrNull?>");
        return receiveChannel0.getOnReceiveOrNull();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of \'receiveCatching\'", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public static final Object receiveOrNull(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Intrinsics.checkNotNull(receiveChannel0, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.receiveOrNull?>");
        return receiveChannel0.receiveOrNull(continuation0);
    }

    public static final Object toList(ReceiveChannel receiveChannel0, Continuation continuation0) {
        Object object2;
        ReceiveChannel receiveChannel2;
        Throwable throwable1;
        ChannelIterator channelIterator0;
        ReceiveChannel receiveChannel1;
        List list2;
        List list1;
        kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toList.1 channelsKt__Channels_commonKt$toList$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toList.1) {
            channelsKt__Channels_commonKt$toList$10 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toList.1)continuation0;
            if((channelsKt__Channels_commonKt$toList$10.label & 0x80000000) == 0) {
                channelsKt__Channels_commonKt$toList$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return ChannelsKt.toList(null, this);
                    }
                };
            }
            else {
                channelsKt__Channels_commonKt$toList$10.label ^= 0x80000000;
            }
        }
        else {
            channelsKt__Channels_commonKt$toList$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return ChannelsKt.toList(null, this);
                }
            };
        }
        Object object0 = channelsKt__Channels_commonKt$toList$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(channelsKt__Channels_commonKt$toList$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                List list0 = CollectionsKt.createListBuilder();
                try {
                    list1 = list0;
                    list2 = list1;
                    receiveChannel1 = receiveChannel0;
                    channelIterator0 = receiveChannel0.iterator();
                    goto label_30;
                }
                catch(Throwable throwable0) {
                    throwable1 = throwable0;
                    throw throwable1;
                }
            }
            case 1: {
                channelIterator0 = (ChannelIterator)channelsKt__Channels_commonKt$toList$10.L$3;
                receiveChannel2 = (ReceiveChannel)channelsKt__Channels_commonKt$toList$10.L$2;
                list1 = (List)channelsKt__Channels_commonKt$toList$10.L$1;
                list2 = (List)channelsKt__Channels_commonKt$toList$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    while(true) {
                        if(!((Boolean)object0).booleanValue()) {
                            goto label_50;
                        }
                        list1.add(channelIterator0.next());
                        receiveChannel1 = receiveChannel2;
                    label_30:
                        channelsKt__Channels_commonKt$toList$10.L$0 = list2;
                        channelsKt__Channels_commonKt$toList$10.L$1 = list1;
                        channelsKt__Channels_commonKt$toList$10.L$2 = receiveChannel1;
                        channelsKt__Channels_commonKt$toList$10.L$3 = channelIterator0;
                        channelsKt__Channels_commonKt$toList$10.label = 1;
                        object2 = channelIterator0.hasNext(channelsKt__Channels_commonKt$toList$10);
                        if(object2 == object1) {
                            return object1;
                        }
                        receiveChannel2 = receiveChannel1;
                        object0 = object2;
                    }
                }
                catch(Throwable throwable1) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        receiveChannel1 = receiveChannel2;
        try {
            throw throwable1;
        }
        catch(Throwable throwable2) {
            ChannelsKt.cancelConsumed(receiveChannel1, throwable1);
            throw throwable2;
        }
    label_50:
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return CollectionsKt.build(list2);
    }
}

