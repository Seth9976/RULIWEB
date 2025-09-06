package kotlinx.coroutines.channels;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000B\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\"\b\u0002\u0010\u0007\u001A\u001C\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n\u00A2\u0006\u0002\u0010\u000BJ\u001E\u0010\u000F\u001A\u00020\t2\n\u0010\u0010\u001A\u0006\u0012\u0002\b\u00030\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0013H\u0014J\u0019\u0010\u0014\u001A\u00020\t2\u0006\u0010\u0012\u001A\u00028\u0000H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015J\u001B\u0010\u0016\u001A\u00020\r2\u0006\u0010\u0012\u001A\u00028\u0000H\u0090@\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\u0015J\r\u0010\u0018\u001A\u00020\rH\u0010\u00A2\u0006\u0002\b\u0019J&\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\t0\u001B2\u0006\u0010\u0012\u001A\u00028\u0000H\u0016\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001C\u0010\u001DJ.\u0010\u001E\u001A\b\u0012\u0004\u0012\u00020\t0\u001B2\u0006\u0010\u0012\u001A\u00028\u00002\u0006\u0010\u001F\u001A\u00020\rH\u0002\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b \u0010!J&\u0010\"\u001A\b\u0012\u0004\u0012\u00020\t0\u001B2\u0006\u0010\u0012\u001A\u00028\u0000H\u0002\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b#\u0010\u001DJ.\u0010$\u001A\b\u0012\u0004\u0012\u00020\t0\u001B2\u0006\u0010\u0012\u001A\u00028\u00002\u0006\u0010\u001F\u001A\u00020\rH\u0002\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b%\u0010!R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00020\r8TX\u0094\u0004\u00A2\u0006\u0006\u001A\u0004\b\f\u0010\u000ER\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00A1\u001E0\u0001\u00A8\u0006&"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBufferedChannel;", "E", "Lkotlinx/coroutines/channels/BufferedChannel;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "isConflatedDropOldest", "", "()Z", "registerSelectForSend", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "element", "", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBroadcast", "sendBroadcast$kotlinx_coroutines_core", "shouldSendSuspend", "shouldSendSuspend$kotlinx_coroutines_core", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "trySendDropLatest", "isSendOp", "trySendDropLatest-Mj0NB7M", "(Ljava/lang/Object;Z)Ljava/lang/Object;", "trySendDropOldest", "trySendDropOldest-JP2dKIU", "trySendImpl", "trySendImpl-Mj0NB7M", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ConflatedBufferedChannel extends BufferedChannel {
    private final int capacity;
    private final BufferOverflow onBufferOverflow;

    public ConflatedBufferedChannel(int v, BufferOverflow bufferOverflow0, Function1 function10) {
        super(v, function10);
        this.capacity = v;
        this.onBufferOverflow = bufferOverflow0;
        if(bufferOverflow0 == BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + Reflection.getOrCreateKotlinClass(BufferedChannel.class).getSimpleName() + " instead").toString());
        }
        if(v < 1) {
            throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + v + " was specified").toString());
        }
    }

    public ConflatedBufferedChannel(int v, BufferOverflow bufferOverflow0, Function1 function10, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 4) != 0) {
            function10 = null;
        }
        this(v, bufferOverflow0, function10);
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    protected boolean isConflatedDropOldest() {
        return this.onBufferOverflow == BufferOverflow.DROP_OLDEST;
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    protected void registerSelectForSend(SelectInstance selectInstance0, Object object0) {
        Object object1 = this.trySend-JP2dKIU(object0);
        if(!(object1 instanceof Failed)) {
            Unit unit0 = (Unit)object1;
            selectInstance0.selectInRegistrationPhase(Unit.INSTANCE);
            return;
        }
        if(!(object1 instanceof Closed)) {
            throw new IllegalStateException("unreachable");
        }
        ChannelResult.exceptionOrNull-impl(object1);
        selectInstance0.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    public Object send(Object object0, Continuation continuation0) {
        return ConflatedBufferedChannel.send$suspendImpl(this, object0, continuation0);
    }

    static Object send$suspendImpl(ConflatedBufferedChannel conflatedBufferedChannel0, Object object0, Continuation continuation0) {
        Object object1 = conflatedBufferedChannel0.trySendImpl-Mj0NB7M(object0, true);
        if(object1 instanceof Closed) {
            ChannelResult.exceptionOrNull-impl(object1);
            Function1 function10 = conflatedBufferedChannel0.onUndeliveredElement;
            if(function10 != null) {
                UndeliveredElementException undeliveredElementException0 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function10, object0, null, 2, null);
                if(undeliveredElementException0 != null) {
                    ExceptionsKt.addSuppressed(undeliveredElementException0, conflatedBufferedChannel0.getSendException());
                    throw undeliveredElementException0;
                }
            }
            throw conflatedBufferedChannel0.getSendException();
        }
        return Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    public Object sendBroadcast$kotlinx_coroutines_core(Object object0, Continuation continuation0) {
        return ConflatedBufferedChannel.sendBroadcast$suspendImpl(this, object0, continuation0);
    }

    static Object sendBroadcast$suspendImpl(ConflatedBufferedChannel conflatedBufferedChannel0, Object object0, Continuation continuation0) {
        Object object1 = conflatedBufferedChannel0.trySendImpl-Mj0NB7M(object0, true);
        if(!(object1 instanceof Failed)) {
            Unit unit0 = (Unit)object1;
            return Boxing.boxBoolean(true);
        }
        return Boxing.boxBoolean(false);
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    public boolean shouldSendSuspend$kotlinx_coroutines_core() {
        return false;
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    public Object trySend-JP2dKIU(Object object0) {
        return this.trySendImpl-Mj0NB7M(object0, false);
    }

    private final Object trySendDropLatest-Mj0NB7M(Object object0, boolean z) {
        Object object1 = super.trySend-JP2dKIU(object0);
        if(!ChannelResult.isSuccess-impl(object1) && !ChannelResult.isClosed-impl(object1)) {
            if(z) {
                Function1 function10 = this.onUndeliveredElement;
                if(function10 != null) {
                    UndeliveredElementException undeliveredElementException0 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function10, object0, null, 2, null);
                    if(undeliveredElementException0 != null) {
                        throw undeliveredElementException0;
                    }
                }
            }
            return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
        }
        return object1;
    }

    private final Object trySendDropOldest-JP2dKIU(Object object0) {
        Symbol symbol0 = BufferedChannelKt.BUFFERED;
        ChannelSegment channelSegment0 = (ChannelSegment)BufferedChannel.sendSegment$FU.get(this);
        while(true) {
            long v = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
            long v1 = 0xFFFFFFFFFFFFFFFL & v;
            boolean z = BufferedChannel.access$isClosedForSend0(this, v);
            long v2 = v1 / ((long)BufferedChannelKt.SEGMENT_SIZE);
            int v3 = (int)(v1 % ((long)BufferedChannelKt.SEGMENT_SIZE));
            if(channelSegment0.id != v2) {
                ChannelSegment channelSegment1 = this.findSegmentSend(v2, channelSegment0);
                if(channelSegment1 == null) {
                    if(!z) {
                        continue;
                    }
                    Throwable throwable0 = this.getSendException();
                    return ChannelResult.Companion.closed-JP2dKIU(throwable0);
                }
                else {
                    channelSegment0 = channelSegment1;
                }
            }
            switch(BufferedChannel.access$updateCellSend(this, channelSegment0, v3, object0, v1, symbol0, z)) {
                case 0: {
                    channelSegment0.cleanPrev();
                    return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
                }
                case 1: {
                    return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
                }
                case 2: {
                    if(z) {
                        channelSegment0.onSlotCleaned();
                        Throwable throwable2 = this.getSendException();
                        return ChannelResult.Companion.closed-JP2dKIU(throwable2);
                    }
                    Waiter waiter0 = symbol0 instanceof Waiter ? ((Waiter)symbol0) : null;
                    if(waiter0 != null) {
                        BufferedChannel.access$prepareSenderForSuspension(this, waiter0, channelSegment0, v3);
                    }
                    this.dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(channelSegment0.id * ((long)BufferedChannelKt.SEGMENT_SIZE) + ((long)v3));
                    return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
                }
                case 3: {
                    throw new IllegalStateException("unexpected");
                }
                case 4: {
                    if(v1 < this.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment0.cleanPrev();
                    }
                    Throwable throwable1 = this.getSendException();
                    return ChannelResult.Companion.closed-JP2dKIU(throwable1);
                }
                case 5: {
                    channelSegment0.cleanPrev();
                }
            }
        }
    }

    private final Object trySendImpl-Mj0NB7M(Object object0, boolean z) {
        return this.onBufferOverflow == BufferOverflow.DROP_LATEST ? this.trySendDropLatest-Mj0NB7M(object0, z) : this.trySendDropOldest-JP2dKIU(object0);
    }
}

