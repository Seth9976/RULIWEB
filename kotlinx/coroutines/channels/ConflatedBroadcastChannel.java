package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.selects.SelectClause2;

@Deprecated(level = DeprecationLevel.WARNING, message = "ConflatedBroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0003B\u000F\b\u0016\u0012\u0006\u0010\u0004\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u0005B\u0015\b\u0002\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007\u00A2\u0006\u0002\u0010\bJ\u0015\u0010\u0015\u001A\u00020\n2\n\b\u0002\u0010\u0016\u001A\u0004\u0018\u00010\u0017H\u0097\u0001J\u001B\u0010\u0015\u001A\u00020\u00182\u0010\b\u0002\u0010\u0016\u001A\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001AH\u0096\u0001J\u0013\u0010\u001B\u001A\u00020\n2\b\u0010\u0016\u001A\u0004\u0018\u00010\u0017H\u0096\u0001J.\u0010\u001C\u001A\u00020\u00182#\u0010\u001D\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u0017\u00A2\u0006\f\b\u001F\u0012\b\b \u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00180\u001EH\u0096\u0001J\u0016\u0010!\u001A\u00020\n2\u0006\u0010\"\u001A\u00028\u0000H\u0097\u0001\u00A2\u0006\u0002\u0010#J\u000F\u0010$\u001A\b\u0012\u0004\u0012\u00028\u00000%H\u0096\u0001J\u0019\u0010&\u001A\u00020\u00182\u0006\u0010\"\u001A\u00028\u0000H\u0096A\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\'J\'\u0010(\u001A\b\u0012\u0004\u0012\u00020\u00180)2\u0006\u0010\"\u001A\u00028\u0000H\u0096\u0001\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b*\u0010+R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\u00020\n8\u0016X\u0097\u0005\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\u000BR$\u0010\f\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000E0\rX\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0004\u001A\u00028\u00008F\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001A\u0004\u0018\u00018\u00008F\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0012\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00A1\u001E0\u0001\u00A8\u0006,"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "E", "Lkotlinx/coroutines/channels/BroadcastChannel;", "()V", "value", "(Ljava/lang/Object;)V", "broadcast", "Lkotlinx/coroutines/channels/BroadcastChannelImpl;", "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "isClosedForSend", "", "()Z", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "getValue", "()Ljava/lang/Object;", "valueOrNull", "getValueOrNull", "cancel", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "offer", "element", "(Ljava/lang/Object;)Z", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ConflatedBroadcastChannel implements BroadcastChannel {
    private final BroadcastChannelImpl broadcast;

    public ConflatedBroadcastChannel() {
        this(new BroadcastChannelImpl(-1));
    }

    public ConflatedBroadcastChannel(Object object0) {
        this.trySend-JP2dKIU(object0);
    }

    private ConflatedBroadcastChannel(BroadcastChannelImpl broadcastChannelImpl0) {
        this.broadcast = broadcastChannelImpl0;
    }

    @Override  // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(CancellationException cancellationException0) {
        this.broadcast.cancel(cancellationException0);
    }

    @Override  // kotlinx.coroutines.channels.BroadcastChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility only")
    public boolean cancel(Throwable throwable0) {
        return this.broadcast.cancel(throwable0);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable throwable0) {
        return this.broadcast.close(throwable0);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public SelectClause2 getOnSend() {
        return this.broadcast.getOnSend();
    }

    public final Object getValue() {
        return this.broadcast.getValue();
    }

    public final Object getValueOrNull() {
        return this.broadcast.getValueOrNull();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1 function10) {
        this.broadcast.invokeOnClose(function10);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this.broadcast.isClosedForSend();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(Object object0) {
        return this.broadcast.offer(object0);
    }

    @Override  // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel openSubscription() {
        return this.broadcast.openSubscription();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object send(Object object0, Continuation continuation0) {
        return this.broadcast.send(object0, continuation0);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object trySend-JP2dKIU(Object object0) {
        return this.broadcast.trySend-JP2dKIU(object0);
    }
}

