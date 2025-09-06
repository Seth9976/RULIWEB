package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt", "kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt"}, k = 4, mv = {1, 8, 0}, xi = 0x30)
public final class ChannelsKt {
    public static final String DEFAULT_CLOSE_MESSAGE = "Channel was closed";

    public static final void cancelConsumed(ReceiveChannel receiveChannel0, Throwable throwable0) {
        ChannelsKt__Channels_commonKt.cancelConsumed(receiveChannel0, throwable0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    public static final Object consume(BroadcastChannel broadcastChannel0, Function1 function10) {
        return ChannelsKt__Channels_commonKt.consume(broadcastChannel0, function10);
    }

    public static final Object consume(ReceiveChannel receiveChannel0, Function1 function10) {
        return ChannelsKt__Channels_commonKt.consume(receiveChannel0, function10);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    public static final Object consumeEach(BroadcastChannel broadcastChannel0, Function1 function10, Continuation continuation0) {
        return ChannelsKt__Channels_commonKt.consumeEach(broadcastChannel0, function10, continuation0);
    }

    public static final Object consumeEach(ReceiveChannel receiveChannel0, Function1 function10, Continuation continuation0) {
        return ChannelsKt__Channels_commonKt.consumeEach(receiveChannel0, function10, continuation0);
    }

    public static final Function1 consumes(ReceiveChannel receiveChannel0) {
        return ChannelsKt__DeprecatedKt.consumes(receiveChannel0);
    }

    public static final Function1 consumesAll(ReceiveChannel[] arr_receiveChannel) {
        return ChannelsKt__DeprecatedKt.consumesAll(arr_receiveChannel);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel distinct(ReceiveChannel receiveChannel0) {
        return ChannelsKt__DeprecatedKt.distinct(receiveChannel0);
    }

    public static final ReceiveChannel distinctBy(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        return ChannelsKt__DeprecatedKt.distinctBy(receiveChannel0, coroutineContext0, function20);
    }

    public static ReceiveChannel drop$default(ReceiveChannel receiveChannel0, int v, CoroutineContext coroutineContext0, int v1, Object object0) {
        return ChannelsKt__DeprecatedKt.drop$default(receiveChannel0, v, coroutineContext0, v1, object0);
    }

    public static ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.dropWhile$default(receiveChannel0, coroutineContext0, function20, v, object0);
    }

    public static final ReceiveChannel filter(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        return ChannelsKt__DeprecatedKt.filter(receiveChannel0, coroutineContext0, function20);
    }

    public static ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.filterIndexed$default(receiveChannel0, coroutineContext0, function30, v, object0);
    }

    public static ReceiveChannel filterNot$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.filterNot$default(receiveChannel0, coroutineContext0, function20, v, object0);
    }

    public static final ReceiveChannel filterNotNull(ReceiveChannel receiveChannel0) {
        return ChannelsKt__DeprecatedKt.filterNotNull(receiveChannel0);
    }

    public static ReceiveChannel flatMap$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.flatMap$default(receiveChannel0, coroutineContext0, function20, v, object0);
    }

    public static final ReceiveChannel map(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20) {
        return ChannelsKt__DeprecatedKt.map(receiveChannel0, coroutineContext0, function20);
    }

    public static final ReceiveChannel mapIndexed(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30) {
        return ChannelsKt__DeprecatedKt.mapIndexed(receiveChannel0, coroutineContext0, function30);
    }

    public static ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.mapIndexed$default(receiveChannel0, coroutineContext0, function30, v, object0);
    }

    public static ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function3 function30, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.mapIndexedNotNull$default(receiveChannel0, coroutineContext0, function30, v, object0);
    }

    public static ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.mapNotNull$default(receiveChannel0, coroutineContext0, function20, v, object0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of \'onReceiveCatching\'")
    public static final SelectClause1 onReceiveOrNull(ReceiveChannel receiveChannel0) {
        return ChannelsKt__Channels_commonKt.onReceiveOrNull(receiveChannel0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of \'receiveCatching\'", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public static final Object receiveOrNull(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt__Channels_commonKt.receiveOrNull(receiveChannel0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left for binary compatibility")
    public static final ReceiveChannel requireNoNulls(ReceiveChannel receiveChannel0) {
        return ChannelsKt__DeprecatedKt.requireNoNulls(receiveChannel0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of \'trySendBlocking\'. Consider handling the result of \'trySendBlocking\' explicitly and rethrow exception if necessary", replaceWith = @ReplaceWith(expression = "trySendBlocking(element)", imports = {}))
    public static final void sendBlocking(SendChannel sendChannel0, Object object0) {
        ChannelsKt__ChannelsKt.sendBlocking(sendChannel0, object0);
    }

    public static ReceiveChannel take$default(ReceiveChannel receiveChannel0, int v, CoroutineContext coroutineContext0, int v1, Object object0) {
        return ChannelsKt__DeprecatedKt.take$default(receiveChannel0, v, coroutineContext0, v1, object0);
    }

    public static ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.takeWhile$default(receiveChannel0, coroutineContext0, function20, v, object0);
    }

    public static final Object toChannel(ReceiveChannel receiveChannel0, SendChannel sendChannel0, Continuation continuation0) {
        return ChannelsKt__DeprecatedKt.toChannel(receiveChannel0, sendChannel0, continuation0);
    }

    public static final Object toCollection(ReceiveChannel receiveChannel0, Collection collection0, Continuation continuation0) {
        return ChannelsKt__DeprecatedKt.toCollection(receiveChannel0, collection0, continuation0);
    }

    public static final Object toList(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt__Channels_commonKt.toList(receiveChannel0, continuation0);
    }

    public static final Object toMap(ReceiveChannel receiveChannel0, Map map0, Continuation continuation0) {
        return ChannelsKt__DeprecatedKt.toMap(receiveChannel0, map0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toMap(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt__DeprecatedKt.toMap(receiveChannel0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toMutableList(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt__DeprecatedKt.toMutableList(receiveChannel0, continuation0);
    }

    public static final Object toMutableSet(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt__DeprecatedKt.toMutableSet(receiveChannel0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toSet(ReceiveChannel receiveChannel0, Continuation continuation0) {
        return ChannelsKt__DeprecatedKt.toSet(receiveChannel0, continuation0);
    }

    public static final Object trySendBlocking(SendChannel sendChannel0, Object object0) {
        return ChannelsKt__ChannelsKt.trySendBlocking(sendChannel0, object0);
    }

    public static ReceiveChannel withIndex$default(ReceiveChannel receiveChannel0, CoroutineContext coroutineContext0, int v, Object object0) {
        return ChannelsKt__DeprecatedKt.withIndex$default(receiveChannel0, coroutineContext0, v, object0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel zip(ReceiveChannel receiveChannel0, ReceiveChannel receiveChannel1) {
        return ChannelsKt__DeprecatedKt.zip(receiveChannel0, receiveChannel1);
    }

    public static final ReceiveChannel zip(ReceiveChannel receiveChannel0, ReceiveChannel receiveChannel1, CoroutineContext coroutineContext0, Function2 function20) {
        return ChannelsKt__DeprecatedKt.zip(receiveChannel0, receiveChannel1, coroutineContext0, function20);
    }
}

